package com.magafin.common.entity;


import com.farcr.nomansland.NMLConfig;
import com.farcr.nomansland.common.entity.bombs.ThrowableBombEntity;
import com.farcr.nomansland.common.entity.deer.Deer;
import com.farcr.nomansland.common.entity.goose.Goose;
import com.farcr.nomansland.common.registry.blocks.NMLBlocks;
import com.farcr.nomansland.common.registry.entities.NMLEntities;
import com.magafin.common.nmdreg.EntityReg;
import com.magafin.common.nmdreg.ParticleReg;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.animal.camel.Camel;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.entity.animal.horse.Donkey;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.animal.horse.Mule;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;

import java.util.List;

import static net.minecraft.world.level.block.WallTorchBlock.FACING;

public class Ricebomb extends ThrowableBombEntity {

    private static final float VERTICAL_RESTITUTION = 0.3F;
    private static final float HORIZONTAL_RESTITUTION = 0.4F;

    public Ricebomb(EntityType<? extends ThrowableBombEntity> entityType, Level level) {
        super(entityType, level);
    }

    public Ricebomb(LivingEntity livingEntity, Level level) {
        super(EntityReg.RICE_BOMB.get(), livingEntity, level);
    }

    public Ricebomb(Level level, double x, double y, double z) {
        super(EntityReg.RICE_BOMB.get(), x, y, z, level);
    }

    private void spawnParticles(ParticleOptions particle, int amount) {
        for (int i = 0; i < amount; i++) {
            double theta = random.nextFloat() * 2 * Math.PI;
            double alpha = random.nextFloat() * 2 * Math.PI;
            double cos = Math.cos(alpha);
            double xVelocity = Math.sin(theta) * cos * (random.nextFloat() * 0.3 + 0.7);
            double yVelocity = cos * Math.cos(theta) * (random.nextFloat() * 0.3 + 0.7);
            double zVelocity = Math.sin(alpha) * (random.nextFloat() * 0.3 + 0.7);
            level().addParticle(particle, getX(), getY(), getZ(), xVelocity * 0.6, yVelocity * 0.6, zVelocity * 0.6);
        }
    }

    @Override
    public void handleEntityEvent(byte b) {
        if (b == 0) {
            spawnParticles(ParticleReg.RICE.get(), 60);
            spawnParticles(ParticleTypes.SMOKE, 300);

            for (int i = 0; i < 40; i++) {
                double theta = random.nextFloat() * 2 * Math.PI;
                double alpha = random.nextFloat() * 2 * Math.PI;
                double cos = Math.cos(alpha);
                double xVelocity = Math.sin(theta) * cos * (random.nextFloat() * 0.3 + 0.7);
                double yVelocity = cos * Math.cos(theta) * (random.nextFloat() * 0.3 + 0.7);
                double zVelocity = Math.sin(alpha) * (random.nextFloat() * 0.3 + 0.7);
                level().addParticle(ParticleTypes.FLAME, false, getX(), getY(), getZ(), xVelocity * 0.1, yVelocity * 0.1, zVelocity * 0.1);
            }
        } else if (b == 1) {
            spawnParticles(ParticleReg.RICE.get(), 60);
            spawnParticles(ParticleTypes.SMOKE, 300);
        } else {
            super.handleEntityEvent(b);
        }
    }

    @Override
    protected void explode() {
        Level level = level();
        level.getEntitiesOfClass(Player.class, getBoundingBox().inflate(2.0F)).forEach(entity -> {
            entity.getFoodData().eat(6, 8);
        });
        level.getEntitiesOfClass(Cow.class, getBoundingBox().inflate(2.0F)).forEach(entity -> {
            entity.setInLoveTime(200);
        });
        level.getEntitiesOfClass(Pig.class, getBoundingBox().inflate(2.0F)).forEach(entity -> {
            entity.setInLoveTime(200);
        });
        level.getEntitiesOfClass(Chicken.class, getBoundingBox().inflate(2.0F)).forEach(entity -> {
            entity.setInLoveTime(200);
        });
        level.getEntitiesOfClass(Sheep.class, getBoundingBox().inflate(2.0F)).forEach(entity -> {
            entity.setInLoveTime(200);
        });
        level.getEntitiesOfClass(Goose.class, getBoundingBox().inflate(2.0F)).forEach(entity -> {
            entity.setInLoveTime(200);
        });
        level.getEntitiesOfClass(Deer.class, getBoundingBox().inflate(2.0F)).forEach(entity -> {
            entity.setInLoveTime(200);
        });
        level.getEntitiesOfClass(Goat.class, getBoundingBox().inflate(2.0F)).forEach(entity -> {
            entity.setInLoveTime(200);
        });
        level.getEntitiesOfClass(Rabbit.class, getBoundingBox().inflate(2.0F)).forEach(entity -> {
            entity.setInLoveTime(200);
        });
        level.playSound(null, blockPosition(), SoundEvents.GENERIC_EXPLODE.value(), SoundSource.BLOCKS, 4, (1 + (random.nextFloat() - random.nextFloat()) * 0.2F) * 0.7F);

        level.broadcastEntityEvent(this, (byte) (isInWater() ? 1 : 0));
        discard();
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);

        if (!level().isClientSide()) {
            explode();
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);

        Vec3 motion = getDeltaMovement();
        if (motion.lengthSqr() < 0.1) {
            setDeltaMovement(Vec3.ZERO);
            setOnGround(true);
            return;
        }

        Direction direction = result.getDirection();
        switch (direction.getAxis()) {
            case X -> setDeltaMovement(
                    -motion.x() * HORIZONTAL_RESTITUTION,
                    motion.y(),
                    motion.z()
            );
            case Y ->
                    setDeltaMovement(motion.x() * VERTICAL_RESTITUTION, -motion.y() * VERTICAL_RESTITUTION, motion.z() * VERTICAL_RESTITUTION);
            case Z -> setDeltaMovement(
                    motion.x(),
                    motion.y(),
                    -motion.z() * HORIZONTAL_RESTITUTION
            );
        }
        if (!shouldFuse()) {
            startFuse(30);
        }
    }

    @Override
    protected ParticleOptions getParticle(LevelAccessor levelAccessor) {
        return ParticleTypes.SMOKE;
    }

    @Override
    public void startFuse(int maxFuse) {
        super.startFuse(maxFuse);
        level().playSound(null, getX(), getY(), getZ(), SoundEvents.TNT_PRIMED, SoundSource.PLAYERS, 1.0F, 1.0F);
    }
}