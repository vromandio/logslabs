package com.vromandio.logslabs.datagen;

import com.vromandio.logslabs.ModClass;
import com.vromandio.logslabs.registry.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends BlockTagsProvider {

    public static final TagKey<Block> LOG_SLABS =
            BlockTags.create(ResourceLocation.fromNamespaceAndPath(ModClass.MOD_ID, "log_slabs"));

    public ModBlockTagsProvider(PackOutput output,
                                CompletableFuture<HolderLookup.Provider> lookupProvider,
                                String modId,
                                @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, modId, existingFileHelper);
    }

    private void addLogSlabTags(Block block) {
        this.tag(LOG_SLABS).add(block);
        this.tag(BlockTags.SLABS).add(block);
        this.tag(BlockTags.MINEABLE_WITH_AXE).add(block);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        for (ModBlocks.LogSlabEntry<?> entry : ModBlocks.ALL_LOG_SLABS) {
            addLogSlabTags(entry.block().get());
        }
        for (ModBlocks.LogSlabEntry<?> entry : ModBlocks.ALL_NON_FLAMMABLE_LOG_SLABS) {
            Block block = entry.block().get();
            this.tag(LOG_SLABS).add(block);
            this.tag(BlockTags.SLABS).add(block);
            this.tag(BlockTags.MINEABLE_WITH_AXE).add(block);
        }
    }
}