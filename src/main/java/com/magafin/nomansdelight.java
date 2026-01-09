package com.magafin;

import com.farcr.nomansland.common.registry.items.NMLCreativeTabs;
import com.farcr.nomansland.common.registry.items.NMLItems;
import com.farcr.nomansland.datagen.tags.NMLItemTagsProvider;
import com.magafin.common.nmdreg.ItemReg;
import com.magafin.common.nmdreg.ParticleReg;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;
import vectorwing.farmersdelight.common.registry.ModCreativeTabs;
import vectorwing.farmersdelight.common.registry.ModItems;

import static com.magafin.common.nmdreg.BlockReg.BLOCKS;
import static com.magafin.common.nmdreg.EntityReg.ENTITIES;
import static com.magafin.common.nmdreg.ItemReg.ITEMS;
import static com.magafin.common.nmdreg.ParticleReg.PARTICLE_TYPES;
import static net.minecraft.core.registries.Registries.PARTICLE_TYPE;

@Mod(nomansdelight.MODID)
public class nomansdelight {
    public static final String MODID = "nomansdelight";
    private static final Logger LOGGER = LogUtils.getLogger();

    public nomansdelight(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);

        NeoForge.EVENT_BUS.register(this);

        ITEMS.register(modEventBus);

        BLOCKS.register(modEventBus);

        ENTITIES.register(modEventBus);

        PARTICLE_TYPES.register(modEventBus);

        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }
    public static ResourceLocation location(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTab() == NMLCreativeTabs.NO_MANS_TAB.get()){
            event.insertAfter(NMLItems.WATER_MOSAIC.get().getDefaultInstance(), ItemReg.LIVING_SOUP_ITEM.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(ItemReg.LIVING_SOUP_ITEM.get().getDefaultInstance(), ItemReg.LIVING_SOUP_BOWL.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(ItemReg.LIVING_SOUP_BOWL.get().getDefaultInstance(), ItemReg.VENISON_ROULADE_BLOCK.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(ItemReg.VENISON_ROULADE_BLOCK.get().getDefaultInstance(), ItemReg.VENISON_ROULADE_PLATE.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(ItemReg.VENISON_ROULADE_PLATE.get().getDefaultInstance(), ItemReg.SPORE_SALAD.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(ItemReg.SPORE_SALAD.get().getDefaultInstance(), ItemReg.COOKED_WEEDS.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(ItemReg.COOKED_WEEDS.get().getDefaultInstance(), ItemReg.FROG_SANDWICH.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NMLItems.SWEET_TART.get().getDefaultInstance(), ItemReg.FRUIT_TART.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(ItemReg.FROG_SANDWICH.get().getDefaultInstance(), ItemReg.NUT_BUN.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NMLItems.HARDTACK.get().getDefaultInstance(), ItemReg.SMORE.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NMLItems.RAW_VENISON.get().getDefaultInstance(), ItemReg.VENISON_CHOP.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NMLItems.COOKED_VENISON.get().getDefaultInstance(), ItemReg.COOKED_VENISON_CHOP.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NMLItems.RAW_HORSE.get().getDefaultInstance(), ItemReg.HORSE_CUTS.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NMLItems.HORSE_STEAK.get().getDefaultInstance(), ItemReg.COOKED_HORSE_CUTS.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(ItemReg.NUT_BUN.get().getDefaultInstance(), ItemReg.LAVENDER_TEA.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(ItemReg.VENISON_ROULADE_PLATE.get().getDefaultInstance(), ItemReg.PESTO_PIZZA_ITEM.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(ItemReg.PESTO_PIZZA_ITEM.get().getDefaultInstance(), ItemReg.PESTO_PIZZA_SLICE.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NMLItems.BILLHOOK_BASS.get().getDefaultInstance(), ItemReg.BILLHOOK_BASS_SLICE.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NMLItems.COOKED_BILLHOOK_BASS.get().getDefaultInstance(), ItemReg.COOKED_BILLHOOK_BASS_SLICE.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(ItemReg.COOKED_WEEDS.get().getDefaultInstance(), ItemReg.BILLHOOK_BASS_WITH_EGGS.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(ItemReg.BILLHOOK_BASS_WITH_EGGS.get().getDefaultInstance(), ItemReg.VENISON_TARTARE.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(ItemReg.FROG_SANDWICH.get().getDefaultInstance(), ItemReg.SHROOMBURGER.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(ItemReg.FROG_SANDWICH.get().getDefaultInstance(), ItemReg.HORSE_WRAP.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(ItemReg.FROG_SANDWICH.get().getDefaultInstance(), ItemReg.BILLHOOK_BASS_ROLL.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NMLItems.INK_BOMB.get().getDefaultInstance(), ItemReg.RICEBOMB.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);


            event.insertAfter(NMLItems.ANCIENT_BRONZE_MASK.get().getDefaultInstance(), ItemReg.CLEAVER.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
        }
    }
}