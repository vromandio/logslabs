package com.vromandio.logslabs.registry;

import com.vromandio.logslabs.ModClass;
import com.vromandio.logslabs.blocks.AxisLogSlabBlock;
import com.vromandio.logslabs.blocks.LogSlabBlock;
import com.vromandio.logslabs.blocks.NonFlammableAxisLogSlabBlock;
import com.vromandio.logslabs.blocks.NonFlammableLogSlabBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ModClass.MOD_ID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ModClass.MOD_ID);

    // Templates
    public static final List<LogSlabEntry<?>> ALL_LOG_SLABS = new ArrayList<>();
    public static final List<LogSlabEntry<?>> ALL_NON_FLAMMABLE_LOG_SLABS = new ArrayList<>();

    public record LogSlabEntry<T extends Block>(
            String name,
            DeferredBlock<T> block,
            DeferredItem<BlockItem> item
    ) {}

    private static BlockBehaviour.Properties logSlabProperties(MapColor mapColor) {
        return BlockBehaviour.Properties.of()
                .mapColor(mapColor)
                .strength(2.0F, 3.0F)
                .sound(SoundType.WOOD)
                .ignitedByLava();
    }

    private static BlockBehaviour.Properties nonFlammableLogSlabProperties(MapColor mapColor) {
        return BlockBehaviour.Properties.of()
                .mapColor(mapColor)
                .strength(2.0F, 3.0F)
                .sound(SoundType.WOOD);
    }

    // Regitry block + item and adds it to arrays
    private static <T extends Block> LogSlabEntry<T> registerLogSlab(
            String name,
            Function<BlockBehaviour.Properties, T> factory,
            MapColor mapColor
    ) {
        DeferredBlock<T> block = BLOCKS.registerBlock(name, factory, logSlabProperties(mapColor));
        DeferredItem<BlockItem> item = ITEMS.registerSimpleBlockItem(block);
        LogSlabEntry<T> entry = new LogSlabEntry<>(name, block, item);
        ALL_LOG_SLABS.add(entry);
        return entry;
    }

    private static <T extends Block> LogSlabEntry<T> registerNonFlammableLogSlab(
            String name,
            Function<BlockBehaviour.Properties, T> factory,
            MapColor mapColor
    ) {
        DeferredBlock<T> block = BLOCKS.registerBlock(name, factory, nonFlammableLogSlabProperties(mapColor));
        DeferredItem<BlockItem> item = ITEMS.registerSimpleBlockItem(block);
        LogSlabEntry<T> entry = new LogSlabEntry<>(name, block, item);
        ALL_NON_FLAMMABLE_LOG_SLABS.add(entry);
        return entry;
    }

    // Registry all blocks
    // ALL LOGS
    public static final LogSlabEntry<LogSlabBlock> OAK_LOG_SLAB_ENTRY =
            registerLogSlab("oak_log_slab", LogSlabBlock::new, MapColor.WOOD);

    public static final LogSlabEntry<LogSlabBlock> STRIPPED_OAK_LOG_SLAB_ENTRY =
            registerLogSlab("stripped_oak_log_slab", LogSlabBlock::new, MapColor.WOOD);

    public static final LogSlabEntry<LogSlabBlock> SPRUCE_LOG_SLAB_ENTRY =
            registerLogSlab("spruce_log_slab", LogSlabBlock::new, MapColor.PODZOL);

    public static final LogSlabEntry<LogSlabBlock> STRIPPED_SPRUCE_LOG_SLAB_ENTRY =
            registerLogSlab("stripped_spruce_log_slab", LogSlabBlock::new, MapColor.PODZOL);

    public static final LogSlabEntry<LogSlabBlock> BIRCH_LOG_SLAB_ENTRY =
            registerLogSlab("birch_log_slab", LogSlabBlock::new, MapColor.SAND);

    public static final LogSlabEntry<LogSlabBlock> STRIPPED_BIRCH_LOG_SLAB_ENTRY =
            registerLogSlab("stripped_birch_log_slab", LogSlabBlock::new, MapColor.SAND);

    public static final LogSlabEntry<LogSlabBlock> JUNGLE_LOG_SLAB_ENTRY =
            registerLogSlab("jungle_log_slab", LogSlabBlock::new, MapColor.DIRT);

    public static final LogSlabEntry<LogSlabBlock> STRIPPED_JUNGLE_LOG_SLAB_ENTRY =
            registerLogSlab("stripped_jungle_log_slab", LogSlabBlock::new, MapColor.DIRT);

    public static final LogSlabEntry<LogSlabBlock> ACACIA_LOG_SLAB_ENTRY =
            registerLogSlab("acacia_log_slab", LogSlabBlock::new, MapColor.COLOR_ORANGE);

    public static final LogSlabEntry<LogSlabBlock> STRIPPED_ACACIA_LOG_SLAB_ENTRY =
            registerLogSlab("stripped_acacia_log_slab", LogSlabBlock::new, MapColor.COLOR_ORANGE);

    public static final LogSlabEntry<LogSlabBlock> DARK_OAK_LOG_SLAB_ENTRY =
            registerLogSlab("dark_oak_log_slab", LogSlabBlock::new, MapColor.COLOR_BROWN);

    public static final LogSlabEntry<LogSlabBlock> STRIPPED_DARK_OAK_LOG_SLAB_ENTRY =
            registerLogSlab("stripped_dark_oak_log_slab", LogSlabBlock::new, MapColor.COLOR_BROWN);

    public static final LogSlabEntry<LogSlabBlock> MANGROVE_LOG_SLAB_ENTRY =
            registerLogSlab("mangrove_log_slab", LogSlabBlock::new, MapColor.COLOR_RED);

    public static final LogSlabEntry<LogSlabBlock> STRIPPED_MANGROVE_LOG_SLAB_ENTRY =
            registerLogSlab("stripped_mangrove_log_slab", LogSlabBlock::new, MapColor.COLOR_RED);

    public static final LogSlabEntry<LogSlabBlock> CHERRY_LOG_SLAB_ENTRY =
            registerLogSlab("cherry_log_slab", LogSlabBlock::new, MapColor.TERRACOTTA_WHITE);

    public static final LogSlabEntry<LogSlabBlock> STRIPPED_CHERRY_LOG_SLAB_ENTRY =
            registerLogSlab("stripped_cherry_log_slab", LogSlabBlock::new, MapColor.TERRACOTTA_WHITE);

    // ALL WOODS
    public static final LogSlabEntry<AxisLogSlabBlock> OAK_WOOD_SLAB_ENTRY =
            registerLogSlab("oak_wood_slab", AxisLogSlabBlock::new, MapColor.WOOD);

    public static final LogSlabEntry<AxisLogSlabBlock> STRIPPED_OAK_WOOD_SLAB_ENTRY =
            registerLogSlab("stripped_oak_wood_slab", AxisLogSlabBlock::new, MapColor.WOOD);

    public static final LogSlabEntry<AxisLogSlabBlock> SPRUCE_WOOD_SLAB_ENTRY =
            registerLogSlab("spruce_wood_slab", AxisLogSlabBlock::new, MapColor.PODZOL);

    public static final LogSlabEntry<AxisLogSlabBlock> STRIPPED_SPRUCE_WOOD_SLAB_ENTRY =
            registerLogSlab("stripped_spruce_wood_slab", AxisLogSlabBlock::new, MapColor.PODZOL);

    public static final LogSlabEntry<AxisLogSlabBlock> BIRCH_WOOD_SLAB_ENTRY =
            registerLogSlab("birch_wood_slab", AxisLogSlabBlock::new, MapColor.SAND);

    public static final LogSlabEntry<AxisLogSlabBlock> STRIPPED_BIRCH_WOOD_SLAB_ENTRY =
            registerLogSlab("stripped_birch_wood_slab", AxisLogSlabBlock::new, MapColor.SAND);

    public static final LogSlabEntry<AxisLogSlabBlock> JUNGLE_WOOD_SLAB_ENTRY =
            registerLogSlab("jungle_wood_slab", AxisLogSlabBlock::new, MapColor.DIRT);

    public static final LogSlabEntry<AxisLogSlabBlock> STRIPPED_JUNGLE_WOOD_SLAB_ENTRY =
            registerLogSlab("stripped_jungle_wood_slab", AxisLogSlabBlock::new, MapColor.DIRT);

    public static final LogSlabEntry<AxisLogSlabBlock> ACACIA_WOOD_SLAB_ENTRY =
            registerLogSlab("acacia_wood_slab", AxisLogSlabBlock::new, MapColor.COLOR_GRAY);

    public static final LogSlabEntry<AxisLogSlabBlock> STRIPPED_ACACIA_WOOD_SLAB_ENTRY =
            registerLogSlab("stripped_acacia_wood_slab", AxisLogSlabBlock::new, MapColor.COLOR_ORANGE);

    public static final LogSlabEntry<AxisLogSlabBlock> DARK_OAK_WOOD_SLAB_ENTRY =
            registerLogSlab("dark_oak_wood_slab", AxisLogSlabBlock::new, MapColor.COLOR_BROWN);

    public static final LogSlabEntry<AxisLogSlabBlock> STRIPPED_DARK_OAK_WOOD_SLAB_ENTRY =
            registerLogSlab("stripped_dark_oak_wood_slab", AxisLogSlabBlock::new, MapColor.COLOR_BROWN);

    public static final LogSlabEntry<AxisLogSlabBlock> MANGROVE_WOOD_SLAB_ENTRY =
            registerLogSlab("mangrove_wood_slab", AxisLogSlabBlock::new, MapColor.COLOR_RED);

    public static final LogSlabEntry<AxisLogSlabBlock> STRIPPED_MANGROVE_WOOD_SLAB_ENTRY =
            registerLogSlab("stripped_mangrove_wood_slab", AxisLogSlabBlock::new, MapColor.COLOR_RED);

    public static final LogSlabEntry<AxisLogSlabBlock> CHERRY_WOOD_SLAB_ENTRY =
            registerLogSlab("cherry_wood_slab", AxisLogSlabBlock::new, MapColor.TERRACOTTA_GRAY);

    public static final LogSlabEntry<AxisLogSlabBlock> STRIPPED_CHERRY_WOOD_SLAB_ENTRY =
            registerLogSlab("stripped_cherry_wood_slab", AxisLogSlabBlock::new, MapColor.TERRACOTTA_PINK);

    // ALL STEMS
    public static final LogSlabEntry<NonFlammableLogSlabBlock> CRIMSON_STEM_SLAB_ENTRY =
            registerNonFlammableLogSlab("crimson_stem_slab", NonFlammableLogSlabBlock::new, MapColor.CRIMSON_STEM);

    public static final LogSlabEntry<NonFlammableLogSlabBlock> STRIPPED_CRIMSON_STEM_SLAB_ENTRY =
            registerNonFlammableLogSlab("stripped_crimson_stem_slab", NonFlammableLogSlabBlock::new, MapColor.CRIMSON_STEM);

    public static final LogSlabEntry<NonFlammableLogSlabBlock> WARPED_STEM_SLAB_ENTRY =
            registerNonFlammableLogSlab("warped_stem_slab", NonFlammableLogSlabBlock::new, MapColor.WARPED_STEM);

    public static final LogSlabEntry<NonFlammableLogSlabBlock> STRIPPED_WARPED_STEM_SLAB_ENTRY =
            registerNonFlammableLogSlab("stripped_warped_stem_slab", NonFlammableLogSlabBlock::new, MapColor.WARPED_STEM);

    // ALL HYPHAES
    public static final LogSlabEntry<NonFlammableAxisLogSlabBlock> CRIMSON_HYPHAE_SLAB_ENTRY =
            registerNonFlammableLogSlab("crimson_hyphae_slab", NonFlammableAxisLogSlabBlock::new, MapColor.CRIMSON_HYPHAE);

    public static final LogSlabEntry<NonFlammableAxisLogSlabBlock> STRIPPED_CRIMSON_HYPHAE_SLAB_ENTRY =
            registerNonFlammableLogSlab("stripped_crimson_hyphae_slab", NonFlammableAxisLogSlabBlock::new, MapColor.CRIMSON_HYPHAE);

    public static final LogSlabEntry<NonFlammableAxisLogSlabBlock> WARPED_HYPHAE_SLAB_ENTRY =
            registerNonFlammableLogSlab("warped_hyphae_slab", NonFlammableAxisLogSlabBlock::new, MapColor.WARPED_HYPHAE);

    public static final LogSlabEntry<NonFlammableAxisLogSlabBlock> STRIPPED_WARPED_HYPHAE_SLAB_ENTRY =
            registerNonFlammableLogSlab("stripped_warped_hyphae_slab", NonFlammableAxisLogSlabBlock::new, MapColor.WARPED_HYPHAE);
}