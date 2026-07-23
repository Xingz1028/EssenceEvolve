package org.com.xing_zi.essenceevolve.EssScreenType;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.com.xing_zi.essenceevolve.EssMenuType.HerbCauldronMenu.HerbCauldronMenu;

public class HerbCauldronScreen extends AbstractContainerScreen<HerbCauldronMenu> {
    private static final ResourceLocation GUI =new ResourceLocation("essenceevolve", "textures/gui/herb_cauldron_gui.png");
    private static final ResourceLocation GUI_herb_fire =new ResourceLocation("essenceevolve", "textures/gui/herb_fire.png");
    private static final ResourceLocation GUI_herb_progress_bar =new ResourceLocation("essenceevolve", "textures/gui/herb_progress_bar.png");


    @Override
    protected void init() {
        super.init();
        this.inventoryLabelX = 178 + 24;    // 物品栏标题的 X 位置
        this.inventoryLabelY = 93;// 物品栏标题的 Y 位置
        this.titleLabelX = 178 + 24;
        this.titleLabelY = 16;
    }

    public HerbCauldronScreen(HerbCauldronMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        this.imageWidth = 215;
        this.imageHeight = 166;
    }

    @Override
    protected void renderLabels(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY) {
        pGuiGraphics.drawString(this.font,this.title,this.titleLabelX,this.titleLabelY,0x363636);
        pGuiGraphics.drawString(this.font,this.playerInventoryTitle,this.inventoryLabelX,this.inventoryLabelY,0x363636);
    }

    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        //设置渲染使用的着色器
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        //设置颜色
        RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
        //绑定要绘制的纹理（图片）
        RenderSystem.setShaderTexture(0, GUI);
        //居中
        int x = (this.width - this.imageWidth) / 2 + 24;
        int y = (this.height - this.imageHeight) / 2;
        // 绘制贴图
        // 参数含义：
        // GUI：纹理
        // x,y：屏幕上的绘制位置
        //int texU, int texV,  贴图内部起始坐标 0,0 = 从图片左上角开始截取
        // imageWidth,imageHeight：绘制区域大小
        pGuiGraphics.blit(GUI, x, y, 0, 0, imageWidth, imageHeight);
        if (this.menu.isUnderBlock()) {
            pGuiGraphics.blit(GUI_herb_fire, x+101, y+25, 0, 0, imageWidth, imageHeight);
        }
        if (this.menu.isUnderBlock() && this.menu.crafting()) {
            pGuiGraphics.blit(GUI_herb_progress_bar, x + 94, y + 35, 0, 0, (menu.data.get(0) * 23) / menu.TOTAL_COOK_TIME, imageHeight);
        }
    }


    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(pGuiGraphics);
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pGuiGraphics, pMouseX, pMouseY);
    }
}
