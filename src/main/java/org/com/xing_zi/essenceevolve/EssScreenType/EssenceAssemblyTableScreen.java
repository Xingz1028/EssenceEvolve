package org.com.xing_zi.essenceevolve.EssScreenType;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.com.xing_zi.essenceevolve.EssMenuType.EssenceAssemblyTableMenu.EssenceAssemblyTableMenu;

public class EssenceAssemblyTableScreen extends AbstractContainerScreen<EssenceAssemblyTableMenu> {
    private static final ResourceLocation GUI =
            new ResourceLocation("essenceevolve", "textures/gui/essence_assembly_table_gui.png");
    private static final ResourceLocation PROGRESS =
            new ResourceLocation("essenceevolve", "textures/gui/essence_assembly_table_progress.png");


    @Override
    protected void init() {//gui打开时进行界面初始化
        super.init();
        this.inventoryLabelX = 178 + 25;    // 物品栏标题的 X 位置
        this.inventoryLabelY = 116;// 物品栏标题的 Y 位置
        this.titleLabelX = 178 + 25;
        this.titleLabelY = 16;

    }

    public EssenceAssemblyTableScreen(EssenceAssemblyTableMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        this.imageWidth = 228;
        this.imageHeight = 191;
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
        if (menu.crafting() && menu.data.get(1) == 1){
            pGuiGraphics.blit(PROGRESS, x+92, y+48, 0, 0, (menu.data.get(0) * 27) / menu.data.get(2) , imageHeight);
        }
        if (menu.crafting() && menu.data.get(1) >= 2){
            pGuiGraphics.blit(PROGRESS, x+92, y+48, 0, 0, (int) ((menu.data.get(0) * 27) / (menu.data.get(2)*0.5F)), imageHeight);
        }
    }


    /**
     * 整个界面的渲染入口。
     * <p>
     * 渲染顺序通常是：
     * 1. 绘制背景
     * 2. 绘制 GUI
     * 3. 绘制按钮、槽位等组件
     */
    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(pGuiGraphics);//黑框背景
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pGuiGraphics, pMouseX, pMouseY);//渲染鼠标悬停物品信息

    }
}
