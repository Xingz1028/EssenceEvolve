package org.com.xing_zi.essenceevolve.recipe;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.com.xing_zi.essenceevolve.recipe.essence_assembly_table_recipe.EssenceAssemblyTableRecipe;
import org.com.xing_zi.essenceevolve.recipe.essence_assembly_table_recipe.EssenceAssemblyTableRecipeSerializer;
import org.com.xing_zi.essenceevolve.recipe.herb_cauldron_recipe.HerbCauldronRecipe;
import org.com.xing_zi.essenceevolve.recipe.herb_cauldron_recipe.HerbCauldronRecipeSerializer;
import org.com.xing_zi.essenceevolve.recipe.skill_infuser.SkillInfuserRecipe;
import org.com.xing_zi.essenceevolve.recipe.skill_infuser.SkillInfuserRecipeSerializer;


import java.util.function.Supplier;


public class EssRecipesRegister {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZER =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS,"essenceevolve");

    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES =
            DeferredRegister.create(ForgeRegistries.RECIPE_TYPES,"essenceevolve");

    public static final RegistryObject<RecipeType<HerbCauldronRecipe>> HERB_CAULDRON_RECIPE =
            RECIPE_TYPES.register("herb_cauldron", new Supplier<RecipeType<HerbCauldronRecipe>>() {
                @Override
                public RecipeType<HerbCauldronRecipe> get() {
                    return new RecipeType<HerbCauldronRecipe>() {
                    };
                }
            });
    public static final RegistryObject<RecipeSerializer<HerbCauldronRecipe>> HERB_CAULDRON_RECIPE_SERIALIZER =
            RECIPE_SERIALIZER.register("herb_cauldron_recipe_serializer",new Supplier<RecipeSerializer<HerbCauldronRecipe>>() {
                @Override
                public RecipeSerializer<HerbCauldronRecipe> get() {
                    return new HerbCauldronRecipeSerializer();
                    }
            });
    public static final RegistryObject<RecipeType<EssenceAssemblyTableRecipe>> ESSENCE_ASSEMBLY_TABLE_RECIPE =
            RECIPE_TYPES.register("essence_assembly_table", new Supplier<RecipeType<EssenceAssemblyTableRecipe>>() {
                @Override
                public RecipeType<EssenceAssemblyTableRecipe> get() {
                    return new RecipeType<EssenceAssemblyTableRecipe>() {
                    };
                }
            });
    public static final RegistryObject<RecipeSerializer<EssenceAssemblyTableRecipe>> ESSENCE_ASSEMBLY_TABLE_RECIPE_SERIALIZER =
            RECIPE_SERIALIZER.register("essence_assembly_table_recipe_serializer",new Supplier<RecipeSerializer<EssenceAssemblyTableRecipe>>() {
                @Override
                public RecipeSerializer<EssenceAssemblyTableRecipe> get() {
                    return new EssenceAssemblyTableRecipeSerializer();
                }
            });

    public static final RegistryObject<RecipeType<SkillInfuserRecipe>> SKILL_INFUSER_RECIPE =
            RECIPE_TYPES.register("skill_infuser_recipe", new Supplier<RecipeType<SkillInfuserRecipe>>() {
                @Override
                public RecipeType<SkillInfuserRecipe> get() {
                    return new RecipeType<SkillInfuserRecipe>() {
                    };
                }
            });
    public static final RegistryObject<RecipeSerializer<SkillInfuserRecipe>> SKILL_INFUSER_RECIPE_SERIALIZER =
            RECIPE_SERIALIZER.register("skill_infuser_recipe_serializer",new Supplier<RecipeSerializer<SkillInfuserRecipe>>() {
                @Override
                public RecipeSerializer<SkillInfuserRecipe> get() {
                    return new SkillInfuserRecipeSerializer();
                }
            });



    public static void registerRecipe(IEventBus bus) {
        RECIPE_SERIALIZER.register(bus);
        RECIPE_TYPES.register(bus);
    }
}
