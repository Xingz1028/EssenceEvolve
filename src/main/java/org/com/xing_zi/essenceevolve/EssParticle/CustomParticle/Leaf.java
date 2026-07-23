package org.com.xing_zi.essenceevolve.EssParticle.CustomParticle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

@OnlyIn(Dist.CLIENT)
public class Leaf extends TextureSheetParticle {
    public Leaf(ClientLevel pLevel, double pX, double pY, double pZ) {
        super(pLevel, pX, pY, pZ, 0, 0, 0);
// 粒子基础属性，在这里统一初始化
        this.lifetime = 40;       // 存活tick
        this.quadSize = 0.1F;      // 初始大小
        this.gravity = 0F;      // 下坠
        this.friction = 0.85F;    // 速度衰减
        this.rCol = 1F;          // 红通道
        this.gCol = 1F;          // 绿通道
        this.bCol = 1F;          // 蓝通道
        this.alpha = 1F;      //透明度
    }

    @Override
    public void tick() {
        // 保存上一帧坐标（必须写，否则拖影异常）
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;

        // 生命周期+1，到寿命直接销毁
        if (this.age++ >= this.lifetime) {
            this.remove();
            return;
        }

        // 摩擦力减速
        this.xd *= this.friction;
        this.zd *= this.friction;
        this.yd *= this.friction;
        // 移动粒子
        this.move(this.xd, this.yd, this.zd);

        // 核心：渐渐消失逻辑
        // 前20tick保持0.7透明度，20-40tick慢慢降到0
        float fadeStart = this.lifetime * 0.5F; // 20刻开始淡出
        if (this.age > fadeStart) {
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

        public Provider(SpriteSet sprite) {
            this.sprite = sprite;
        }

        @Override
        public @Nullable Particle createParticle(SimpleParticleType pType, ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
            Leaf leafLeft = new Leaf(pLevel, pX, pY, pZ);
            leafLeft.setSpriteFromAge(sprite);
            return leafLeft;
        }
    }
}