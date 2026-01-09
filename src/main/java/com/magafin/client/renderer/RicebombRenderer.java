package com.magafin.client.renderer;

import com.farcr.nomansland.client.renderer.ThrowableBombRenderer;
import com.magafin.common.entity.Ricebomb;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;

import static com.magafin.nomansdelight.MODID;

public class RicebombRenderer extends ThrowableBombRenderer<Ricebomb> {
    private static final ModelResourceLocation MODEL = ModelResourceLocation.standalone(ResourceLocation.fromNamespaceAndPath(MODID,"entity/bombs/rice_bomb"));
    public RicebombRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }
    @Override
    public ModelResourceLocation getModelLocation(Ricebomb entity) {
        return MODEL;
    }
}
