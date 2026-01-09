package com.magafin.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;

public class RiceParticle extends TextureSheetParticle
{
    private static final RandomSource RANDOM = RandomSource.create();
    protected RiceParticle(ClientLevel level, double posX, double posY, double posZ) {
        super(level, posX, posY, posZ, (0.5 - RANDOM.nextDouble()) * 1.25, 0.5D, (0.5 - RANDOM.nextDouble()) * 1.25);
        this.xd *= 5.0F;
        this.yd *= 5.0F;
        this.zd *= 5.0F;
        this.yd -= 0.6D;
        this.quadSize *= 0.8F;
        this.lifetime = 16;
        this.hasPhysics = true;
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    @Override
    public float getQuadSize(float scaleFactor) {
        return this.quadSize * Mth.clamp(((float) this.age + scaleFactor) / (float) this.lifetime * 32.0F, 0.0F, 1.0F);
    }

    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.age++ >= this.lifetime) {
            this.remove();
        } else {
            this.move(this.xd, this.yd, this.zd);
            if (this.y == this.yo) {
                this.xd *= 1.1D;
                this.zd *= 1.1D;
            }

            this.xd *= 0.86F;
            this.yd *= 0.86F;
            this.zd *= 0.86F;
            if (this.onGround) {
                this.xd *= 0.7F;
                this.zd *= 0.7F;
            }

        }
    }

    public static class Factory implements ParticleProvider<SimpleParticleType>
    {
        private final SpriteSet spriteSet;

        public Factory(SpriteSet sprite) {
            this.spriteSet = sprite;
        }

        @Override
        public Particle createParticle(SimpleParticleType typeIn, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            RiceParticle particle = new RiceParticle(level, x, y + 0.0D, z);
            particle.pickSprite(this.spriteSet);
            particle.setColor(1.0F, 1.0F, 1.0F);
            return particle;
        }
    }
}
