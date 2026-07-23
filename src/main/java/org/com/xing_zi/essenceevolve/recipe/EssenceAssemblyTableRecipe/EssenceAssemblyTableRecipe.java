package org.com.xing_zi.essenceevolve.recipe.EssenceAssemblyTableRecipe;

import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.com.xing_zi.essenceevolve.recipe.EssRecipesRegister;

public class EssenceAssemblyTableRecipe implements Recipe<SimpleContainer> {
    private final ResourceLocation recipeId;

    private final NonNullList<Ingredient> inputItems;
    private final ItemStack outputItem;

    public EssenceAssemblyTableRecipe(ResourceLocation recipeId, NonNullList<Ingredient> inputItems, ItemStack outputItem) {
        this.recipeId = recipeId;
        this.inputItems = inputItems;
        this.outputItem = outputItem;

    }

    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) {
        if(pLevel.isClientSide()){return false;}
        int count =  Math.min(inputItems.size(),5);
        for(int i = 0; i < count; i++){
            if (!inputItems.get(i).test(pContainer.getItem(i))){
                return false;
            }
        }
        for (int i = count; i < 5; i++) {
            if (!pContainer.getItem(i).isEmpty()){
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack assemble(SimpleContainer pContainer, RegistryAccess pRegistryAccess) {//组装；拼接；整合
        return outputItem.copy();
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {//可在对应维度合成
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess pRegistryAccess) {//获取成品物品
        return outputItem.copy();
    }

    @Override
    public ResourceLocation getId() {//获取ID
        return this.recipeId;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {//获取序列化器
        return EssRecipesRegister.ESSENCE_ASSEMBLY_TABLE_RECIPE_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {//获取类型
        return EssRecipesRegister.ESSENCE_ASSEMBLY_TABLE_RECIPE.get();
    }

    public NonNullList<Ingredient> getInputItems() {
        return inputItems;
    }
}
