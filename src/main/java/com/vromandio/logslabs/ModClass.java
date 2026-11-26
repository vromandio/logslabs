package com.vromandio.logslabs;

import com.vromandio.logslabs.datagen.*;
import com.vromandio.logslabs.registry.ModBlocks;
import com.vromandio.logslabs.registry.ModCreativeTabs;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.minecraft.data.DataGenerator;

import java.util.Set;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Mod(ModClass.MOD_ID)
public class ModClass {
    public static final String MOD_ID = "logslabs";

    public ModClass(IEventBus modEventBus) {

        ModBlocks.BLOCKS.register(modEventBus);
        ModBlocks.ITEMS.register(modEventBus);

        ModCreativeTabs.CREATIVE_MODE_TABS.register(modEventBus);

        modEventBus.addListener(this::gatherData);
    }

    private void gatherData(final GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        // Blocks registry
        ModBlockTagsProvider blockTagsProvider = new ModBlockTagsProvider(
                packOutput, lookupProvider, MOD_ID, event.getExistingFileHelper());
        generator.addProvider(event.includeServer(), blockTagsProvider);

        // Items registry
        generator.addProvider(event.includeServer(),
                new ModItemTagsProvider(
                        packOutput,
                        lookupProvider,
                        blockTagsProvider.contentsGetter(),
                        MOD_ID,
                        event.getExistingFileHelper()
                ));

        // Loot Tables
        generator.addProvider(event.includeServer(),
                new LootTableProvider(
                        generator.getPackOutput(),
                        Set.of(),
                        List.of(new LootTableProvider.SubProviderEntry(
                                ModBlockLootTables::new,
                                LootContextParamSets.BLOCK
                        )),
                        event.getLookupProvider()
                ));

        // Models and blockstates
        generator.addProvider(event.includeClient(),
                new ModBlockStateProvider(packOutput, event.getExistingFileHelper()));

        // Recipes
        generator.addProvider(event.includeServer(),
                new ModRecipeProvider(packOutput, lookupProvider));

    }
}
