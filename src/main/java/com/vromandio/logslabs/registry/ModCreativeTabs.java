package com.vromandio.logslabs.registry;

import com.vromandio.logslabs.ModClass;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCreativeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ModClass.MOD_ID);

    // Registry creative tab and add all blocks
    // I'm not sure if this is the right way
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> LOG_SLABS_TAB =
            CREATIVE_MODE_TABS.register("log_slabs_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.logslabs.log_slabs_tab"))
                    .icon(() -> new ItemStack(ModBlocks.OAK_LOG_SLAB_ENTRY.item().get()))
                    .displayItems((parameters, output) -> {
                        output.accept(ModBlocks.OAK_LOG_SLAB_ENTRY.item().get());
                        output.accept(ModBlocks.OAK_WOOD_SLAB_ENTRY.item().get());
                        output.accept(ModBlocks.STRIPPED_OAK_LOG_SLAB_ENTRY.item().get());
                        output.accept(ModBlocks.STRIPPED_OAK_WOOD_SLAB_ENTRY.item().get());

                        output.accept(ModBlocks.SPRUCE_LOG_SLAB_ENTRY.item().get());
                        output.accept(ModBlocks.SPRUCE_WOOD_SLAB_ENTRY.item().get());
                        output.accept(ModBlocks.STRIPPED_SPRUCE_LOG_SLAB_ENTRY.item().get());
                        output.accept(ModBlocks.STRIPPED_SPRUCE_WOOD_SLAB_ENTRY.item().get());

                        output.accept(ModBlocks.BIRCH_LOG_SLAB_ENTRY.item().get());
                        output.accept(ModBlocks.BIRCH_WOOD_SLAB_ENTRY.item().get());
                        output.accept(ModBlocks.STRIPPED_BIRCH_LOG_SLAB_ENTRY.item().get());
                        output.accept(ModBlocks.STRIPPED_BIRCH_WOOD_SLAB_ENTRY.item().get());

                        output.accept(ModBlocks.JUNGLE_LOG_SLAB_ENTRY.item().get());
                        output.accept(ModBlocks.JUNGLE_WOOD_SLAB_ENTRY.item().get());
                        output.accept(ModBlocks.STRIPPED_JUNGLE_LOG_SLAB_ENTRY.item().get());
                        output.accept(ModBlocks.STRIPPED_JUNGLE_WOOD_SLAB_ENTRY.item().get());

                        output.accept(ModBlocks.ACACIA_LOG_SLAB_ENTRY.item().get());
                        output.accept(ModBlocks.ACACIA_WOOD_SLAB_ENTRY.item().get());
                        output.accept(ModBlocks.STRIPPED_ACACIA_LOG_SLAB_ENTRY.item().get());
                        output.accept(ModBlocks.STRIPPED_ACACIA_WOOD_SLAB_ENTRY.item().get());

                        output.accept(ModBlocks.DARK_OAK_LOG_SLAB_ENTRY.item().get());
                        output.accept(ModBlocks.DARK_OAK_WOOD_SLAB_ENTRY.item().get());
                        output.accept(ModBlocks.STRIPPED_DARK_OAK_LOG_SLAB_ENTRY.item().get());
                        output.accept(ModBlocks.STRIPPED_DARK_OAK_WOOD_SLAB_ENTRY.item().get());

                        output.accept(ModBlocks.MANGROVE_LOG_SLAB_ENTRY.item().get());
                        output.accept(ModBlocks.MANGROVE_WOOD_SLAB_ENTRY.item().get());
                        output.accept(ModBlocks.STRIPPED_MANGROVE_LOG_SLAB_ENTRY.item().get());
                        output.accept(ModBlocks.STRIPPED_MANGROVE_WOOD_SLAB_ENTRY.item().get());

                        output.accept(ModBlocks.CHERRY_LOG_SLAB_ENTRY.item().get());
                        output.accept(ModBlocks.CHERRY_WOOD_SLAB_ENTRY.item().get());
                        output.accept(ModBlocks.STRIPPED_CHERRY_LOG_SLAB_ENTRY.item().get());
                        output.accept(ModBlocks.STRIPPED_CHERRY_WOOD_SLAB_ENTRY.item().get());

                        output.accept(ModBlocks.CRIMSON_STEM_SLAB_ENTRY.item().get());
                        output.accept(ModBlocks.CRIMSON_HYPHAE_SLAB_ENTRY.item().get());
                        output.accept(ModBlocks.STRIPPED_CRIMSON_STEM_SLAB_ENTRY.item().get());
                        output.accept(ModBlocks.STRIPPED_CRIMSON_HYPHAE_SLAB_ENTRY.item().get());

                        output.accept(ModBlocks.WARPED_STEM_SLAB_ENTRY.item().get());
                        output.accept(ModBlocks.WARPED_HYPHAE_SLAB_ENTRY.item().get());
                        output.accept(ModBlocks.STRIPPED_WARPED_STEM_SLAB_ENTRY.item().get());
                        output.accept(ModBlocks.STRIPPED_WARPED_HYPHAE_SLAB_ENTRY.item().get());
                    })
                    .build());
}