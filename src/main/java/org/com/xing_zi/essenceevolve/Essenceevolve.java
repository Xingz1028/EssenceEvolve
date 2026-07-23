package org.com.xing_zi.essenceevolve;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.com.xing_zi.essenceevolve.EssBlockEntity.BlockEntitiesRegister;
import org.com.xing_zi.essenceevolve.EssMenuType.EssMenus;
import org.com.xing_zi.essenceevolve.EssParticle.EssParticleRegister;
import org.com.xing_zi.essenceevolve.EssEffect.EssEffect;
import org.com.xing_zi.essenceevolve.EssItem.EssItems;
import org.com.xing_zi.essenceevolve.EssSounds.EssSoundRegister;
import org.com.xing_zi.essenceevolve.block.EssBlocks;
import org.com.xing_zi.essenceevolve.recipe.EssRecipesRegister;
import org.slf4j.Logger;

// 此处填写的modid必须和META-INF/mods.toml文件内配置一致
@Mod(Essenceevolve.MODID)
public class Essenceevolve {

    // 统一定义模组ID，方便全局各处调用
    public static final String MODID = "essenceevolve";
    // 直接引用SLF4J日志输出器
    private static final Logger LOGGER = LogUtils.getLogger();

    public Essenceevolve() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // 将通用初始化方法注册到模组加载事件总线
        modEventBus.addListener(this::commonSetup);
        EssEvoModCreativeModeTabs.register(modEventBus);
        EssItems.register(modEventBus);
        EssEffect.register(modEventBus);
        EssBlocks.register(modEventBus);
        BlockEntitiesRegister.register(modEventBus);
        EssMenus.register(modEventBus);
        EssRecipesRegister.register(modEventBus);
        EssParticleRegister.registerParticles(modEventBus);
        EssSoundRegister.registerSounds(modEventBus);
        // 将当前类注册到Forge游戏事件总线，用于监听服务器、实体等游戏原生事件
        MinecraftForge.EVENT_BUS.register(this);
        // 注册物品添加至创造物品栏的监听方法
        modEventBus.addListener(this::addCreative);
        // 注册模组配置文件，让Forge自动生成并加载配置
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // 通用初始化相关代码（客户端、服务端都会执行）
        LOGGER.info("HELLO FROM COMMON SETUP");
        LOGGER.info("泥土方块注册名 >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));

        if (Config.logDirtBlock) LOGGER.info("泥土方块注册名 >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));

        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);

        Config.items.forEach((item) -> LOGGER.info("物品 >> {}", item.toString()));
    }

    // 将自定义方块/物品添加到创造模式物品栏
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
    }

    // 使用@SubscribeEvent注解，事件总线会自动识别并调用该方法
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // 服务器启动时执行的逻辑
        LOGGER.info("HELLO from server starting");
    }

    // 使用EventBusSubscriber注解可以自动注册本类中所有带@SubscribeEvent的静态监听方法
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            // 客户端专属初始化代码
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("游戏内用户名 >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}
