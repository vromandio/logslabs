package com.vromandio.logslabs.datagen;

import com.vromandio.logslabs.ModClass;
import com.vromandio.logslabs.blocks.AxisLogSlabBlock;
import com.vromandio.logslabs.blocks.NonFlammableAxisLogSlabBlock;
import com.vromandio.logslabs.registry.ModBlocks;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, ModClass.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        // Flammable LogSlabBlock and AxisLogSlabBlock
        for (ModBlocks.LogSlabEntry<?> entry : ModBlocks.ALL_LOG_SLABS) {
            Block block = entry.block().get();
            if (block instanceof AxisLogSlabBlock) {
                generateAxisSlab(block, entry.name());
            } else {
                generateSimpleSlab(block, entry.name());
            }
        }
        // Not flammable NonFlammableLogSlabBlock and NonFlammableAxisLogSlabBlock
        for (ModBlocks.LogSlabEntry<?> entry : ModBlocks.ALL_NON_FLAMMABLE_LOG_SLABS) {
            Block block = entry.block().get();
            if (block instanceof NonFlammableAxisLogSlabBlock) {
                generateAxisSlab(block, entry.name());
            } else {
                generateSimpleSlab(block, entry.name());
            }
        }
    }

    // A pair of textures for a specific substrate (log/wood/stem/hyphae)
    private record LogTextures(ResourceLocation side, ResourceLocation top) {}

    // Rules for selecting textures based on the base name:
    // oak_log          -> side = oak_log;          top = oak_log_top
    // oak_wood         -> side = oak_log;          top = oak_log
    // crimson_stem     -> side = crimson_stem;     top = crimson_stem_top
    // crimson_hyphae   -> side = crimson_stem;     top = crimson_stem
    // Also works for stripped_*
    private LogTextures getLogTextures(String baseName) {
        if (baseName.endsWith("_wood")) {
            String stripped = baseName.substring(0, baseName.length() - "_wood".length());
            ResourceLocation t = mcLoc("block/" + stripped + "_log");
            return new LogTextures(t, t);
        }
        if (baseName.endsWith("_hyphae")) {
            String stripped = baseName.substring(0, baseName.length() - "_hyphae".length());
            ResourceLocation t = mcLoc("block/" + stripped + "_stem");
            return new LogTextures(t, t);
        }
        if (baseName.endsWith("_log") || baseName.endsWith("_stem")) {
            return new LogTextures(
                    mcLoc("block/" + baseName),
                    mcLoc("block/" + baseName + "_top")
            );
        }
        ResourceLocation t = mcLoc("block/" + baseName);
        return new LogTextures(t, t);
    }

    // Non-axis Slabs (LogSlabBlock and NonFlammableLogBlock)
    private void generateSimpleSlab(Block block, String slabName) {
        String baseName = slabName.replace("_slab", "");
        LogTextures tex = getLogTextures(baseName);
        ResourceLocation doubleSlab = mcLoc("block/" + baseName);

        // The order of the arguments: (block, doubleslab, side, bottom, top)
        // blockstate + models/block/<name>.json и <name>_top.json
        slabBlock((SlabBlock) block, doubleSlab, tex.side(), tex.top(), tex.top());

        // models/item/<name>.json
        itemModels().withExistingParent(slabName, modLoc("block/" + slabName));
    }

    @SuppressWarnings("unchecked")
    private EnumProperty<Direction.Axis> getAxisProperty(Block block) {
        // For AxisLogSlabBlock and NonFlammableAxisLogSlabBlock
        // Take the actually registered "axis" property from the state definition.
        return (EnumProperty<Direction.Axis>) block.getStateDefinition().getProperty("axis");
    }

    // Axis slabs (AxisLogSlabBlock and NonFlammableAxisLogSlabBlock)
    // Generate 6 blockstates options. (bottom|double|top) x (axis=x|axis=z)
    private void generateAxisSlab(Block block, String slabName) {
        String baseName = slabName.replace("_slab", "");
        LogTextures tex = getLogTextures(baseName);
        ResourceLocation doubleSlab = mcLoc("block/" + baseName);

        ModelFile bottomModel = models().slab(slabName, tex.side(), tex.top(), tex.side());
        ModelFile topModel    = models().slabTop(slabName + "_top", tex.side(), tex.top(), tex.side());
        itemModels().withExistingParent(slabName, modLoc("block/" + slabName));

        EnumProperty<Direction.Axis> axisProperty = getAxisProperty(block);

        getVariantBuilder(block).forAllStates(state -> {
            SlabType type = state.getValue(SlabBlock.TYPE);
            Direction.Axis axis = state.getValue(axisProperty);

            ModelFile model = switch (type) {
                case BOTTOM -> bottomModel;
                case TOP    -> topModel;
                case DOUBLE -> models().getExistingFile(doubleSlab);
            };

            int yRot = (axis == Direction.Axis.X) ? 90 : 0;

            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY(yRot)
                    .build();
        });
    }
}