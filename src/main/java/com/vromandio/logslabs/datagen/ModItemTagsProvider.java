package com.vromandio.logslabs.datagen;

import com.vromandio.logslabs.registry.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends ItemTagsProvider {

    public ModItemTagsProvider(PackOutput output,
                               CompletableFuture<HolderLookup.Provider> lookupProvider,
                               CompletableFuture<TagsProvider.TagLookup<Block>> blockTags,
                               String modId,
                               @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, modId, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        // Copy entries from block tags to item tags
        this.copy(BlockTags.SLABS, ItemTags.SLABS);

        // Manually adding items of non-flammable slabs to item tag
        for (ModBlocks.LogSlabEntry<?> entry : ModBlocks.ALL_NON_FLAMMABLE_LOG_SLABS) {
            this.tag(ItemTags.NON_FLAMMABLE_WOOD).add(entry.item().get());
        }
        // For a custom tag need to create an Item version of it
        this.copy(ModBlockTagsProvider.LOG_SLABS, ItemTags.create(ModBlockTagsProvider.LOG_SLABS.location()));

    }
}