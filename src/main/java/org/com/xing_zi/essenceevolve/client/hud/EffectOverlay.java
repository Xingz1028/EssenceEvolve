package org.com.xing_zi.essenceevolve.client.hud;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.com.xing_zi.essenceevolve.Essenceevolve;
import org.com.xing_zi.essenceevolve.effect.EssEffectRegister;

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(modid = Essenceevolve.MODID, value = Dist.CLIENT)
public class EffectOverlay {
    private static final int FADE_IN_DURATION = 40;//淡入时长 fade in 淡入
    private static final int FADE_OUT_DURATION = 40;//淡出时长 fade out 淡出  duration 持续时长
    private static final Map<MobEffect, Integer> FADE_IN_PROGRESS = new HashMap<>();//定义这个集合，用来存放每个效果buff的进度值
    private static final ResourceLocation EARTH_OVERLAY =
            new ResourceLocation(Essenceevolve.MODID, "textures/hud/earth_overlay.png");


    @SubscribeEvent(priority = EventPriority.LOWEST)//priority 优先级优先权  //事件优先级，防止被其他东西挡住
    public static void setEarthOverlay(RenderGuiOverlayEvent.Post event) {
        Minecraft minecraft = Minecraft.getInstance();
        LocalPlayer player = minecraft.player;
        int screenWight = minecraft.getWindow().getGuiScaledWidth();
        int screenHeight = minecraft.getWindow().getGuiScaledHeight();
        if (player != null) {
            render(event, player, screenWight, screenHeight, EssEffectRegister.EARTH_EFFECT.get(), EARTH_OVERLAY);
        }
    }

    private static void render(RenderGuiOverlayEvent.Post event, LocalPlayer player, int screenWight, int screenHeight, MobEffect pEffect, ResourceLocation pResourceLocation) {
        if (player.hasEffect(pEffect)) {
            MobEffectInstance effectInstance = player.getEffect(pEffect);
            if (effectInstance != null) {
                int duration = effectInstance.getDuration();//当前剩余药水效果帧数
                if (!FADE_IN_PROGRESS.containsKey(pEffect)){
                    FADE_IN_PROGRESS.put(pEffect,duration);
                }
                Integer runTime = FADE_IN_PROGRESS.get(pEffect);

                float fadeInAlpha = (float)(runTime - duration) / FADE_IN_DURATION;
                float fadeOutAlpha = Math.min((float) duration / FADE_OUT_DURATION, 1.0F);
                float alpha = Math.min(fadeInAlpha, fadeOutAlpha);
                if (alpha > 0F){
                    RenderSystem.enableBlend();
                    RenderSystem.defaultBlendFunc();
                    RenderSystem.setShaderColor(1F, 1F, 1F, alpha);
                    event.getGuiGraphics().blit(pResourceLocation, 0, 0, 0, 0f, 0f, screenWight, screenHeight, screenWight, screenHeight);
                    RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                    RenderSystem.disableBlend();
                }
            }
        }else {
            FADE_IN_PROGRESS.remove(pEffect);
        }
    }
}
