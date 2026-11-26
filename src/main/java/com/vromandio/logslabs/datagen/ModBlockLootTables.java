package com.vromandio.logslabs.datagen;

import com.vromandio.logslabs.registry.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.stream.Collectors;

public class ModBlockLootTables extends BlockLootSubProvider {

    public ModBlockLootTables(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        for (ModBlocks.LogSlabEntry<?> entry : ModBlocks.ALL_LOG_SLABS) {
            Block block = entry.block().get();
            this.add(block, this.createSlabItemTable(block));
        }
        for (ModBlocks.LogSlabEntry<?> entry : ModBlocks.ALL_NON_FLAMMABLE_LOG_SLABS) {
            Block block = entry.block().get();
            this.add(block, this.createSlabItemTable(block));
        }
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream()
                .map(holder -> (Block) holder.get())
                .collect(Collectors.toList());
    }
}