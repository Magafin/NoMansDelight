package com.magafin.common.nmdreg;

import com.magafin.common.entity.Ricebomb;
import com.magafin.nomansdelight;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class EntityReg {
    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(Registries.ENTITY_TYPE, nomansdelight.MODID);
    public static final Supplier<EntityType<Ricebomb>> RICE_BOMB =
            ENTITIES.register("rice_bomb", () -> EntityType.Builder.<Ricebomb>of(Ricebomb::new, MobCategory.MISC)
                    .sized(0.375F, 0.375F).clientTrackingRange(4).updateInterval(20).build("rice_bomb"));
}
