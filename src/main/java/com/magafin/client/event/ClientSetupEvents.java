package com.magafin.client.event;

import com.farcr.nomansland.NoMansLand;
import com.magafin.client.particle.RiceParticle;
import com.magafin.client.renderer.RicebombRenderer;
import com.magafin.common.entity.Ricebomb;
import com.magafin.common.nmdreg.EntityReg;
import com.magafin.common.nmdreg.ParticleReg;
import com.magafin.nomansdelight;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

import static com.magafin.nomansdelight.MODID;

@EventBusSubscriber(modid = nomansdelight.MODID, value = Dist.CLIENT)
public class ClientSetupEvents {
    @SubscribeEvent
    public static void registerModels(ModelEvent.RegisterAdditional event) {
        event.register(ModelResourceLocation.standalone(nomansdelight.location("entity/bombs/rice_bomb")));
    }
    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityReg.RICE_BOMB.get(), RicebombRenderer::new);
    }
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void registerParticles(RegisterParticleProvidersEvent event) {
        Minecraft.getInstance().particleEngine.register(ParticleReg.RICE.get(), RiceParticle.Factory::new);
    }
}