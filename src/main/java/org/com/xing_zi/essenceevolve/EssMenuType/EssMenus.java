package org.com.xing_zi.essenceevolve.EssMenuType;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.com.xing_zi.essenceevolve.EssMenuType.EssenceAssemblyTableMenu.EssenceAssemblyTableMenu;
import org.com.xing_zi.essenceevolve.EssMenuType.HerbCauldronMenu.HerbCauldronMenu;
import org.com.xing_zi.essenceevolve.Essenceevolve;

public class EssMenus {
    public static final DeferredRegister<MenuType<?>> ESS_MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, Essenceevolve.MODID);


    public static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(String name, IContainerFactory<T> factory){
        return ESS_MENUS.register(name, () -> IForgeMenuType.create(factory));
    }


    public static final RegistryObject<MenuType<EssenceAssemblyTableMenu>> ESSENCE_ASSEMBLY_TABLE_MENU =
            registerMenuType("essence_assembly_table_menu",
                    EssenceAssemblyTableMenu::new);
    public static final RegistryObject<MenuType<HerbCauldronMenu>> HERB_CAULDRON_MENU =
            registerMenuType("herb_cauldron_menu",
                    HerbCauldronMenu::new);






    public static void register(IEventBus bus) {
        ESS_MENUS.register(bus);
    }
}
