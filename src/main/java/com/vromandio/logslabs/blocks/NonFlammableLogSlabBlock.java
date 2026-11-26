package com.vromandio.logslabs.blocks;

import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

// As non-axis slabs but without flammability
public class NonFlammableLogSlabBlock extends SlabBlock {
    public NonFlammableLogSlabBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }
}