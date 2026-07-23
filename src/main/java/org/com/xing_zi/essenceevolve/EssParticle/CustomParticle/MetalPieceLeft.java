package org.com.xing_zi.essenceevolve.EssParticle.CustomParticle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

@OnlyIn(Dist.CLIENT)
public class MetalPieceLeft extends TextureSheetParticle {


    protected MetalPieceLeft(ClientLevel pLevel, double pX, double pY, double pZ) {
        super(pLevel, pX, pY, pZ, 0, 0, 0);
// 粒子基础属性，在这里统一初始化
        this.lifetime = 60;       // 存活tick
        this.quadSize = 0.2F;      // 初始大小
        this.gravity = 1F;      // 下坠
        this.friction = 0.85F;    // 速度衰减
        this.rCol = 1F;          // 红通道
        this.gCol = 1F;          // 绿通道
        this.bCol = 1F;          // 蓝通道
        this.yd = -0.1F;
    }

    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.age++ >= this.lifetime) {
            this.remove();
            return;
        }
        this.xd = this.xd * friction;
        this.zd = this.zd * friction;

        this.move(this.xd, this.yd, this.zd);
        float fadeStart = this.lifetime*0.33F;
        if (this.age > fadeStart){
            float fadeRate = (this.age - fadeStart) / (this.lifetime - fadeStart);
            this.alpha = 0.7F * (1F - fadeRate);
        }
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }


    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;
        public Provider(SpriteSet spriteSet) {
            this.sprite = spriteSet;
        }
        @Override
        public @Nullable Particle createParticle(SimpleParticleType pType, ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
            MetalPieceLeft particle = new MetalPieceLeft(pLevel, pX, pY, pZ);
            particle.setSpriteFromAge(this.sprite);
            return particle;
        }
    }
}