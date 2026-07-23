package org.com.xing_zi.essenceevolve.recipe.HerbCauldronRecipe;

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

public class HerbCauldronRecipe implements Recipe<SimpleContainer> {
    private final ResourceLocation recipeId;
    public NonNullList<Ingredient> getInputItems() {
        return inputItems;
    }

    private final NonNullList<Ingredient> inputItems; //你这个配方对象自己持有的 `NonNullList<Ingredient>`，是写在 JSON 里、这个配方要求的**标准材料模板**
    private final ItemStack output;



    public  HerbCauldronRecipe(ResourceLocation recipeId, NonNullList<Ingredient> inputItems, ItemStack output) {
        this.recipeId = recipeId;
        this.inputItems = inputItems;//这个数据在Serializer里就传入了，fromJson方法通过反序列器读取json文件将物品获取到inputItems集合中
        this.output = output.copy();

    }
    //底层
    //`matches(SimpleContainer pContainer, Level pLevel)` 里的 `SimpleContainer` **不是你方块实体自己的物品存储**，是 MC 内部临时容器：
    //每次判断配方匹配时，MC 会**把你方块实体全部槽位复制一份**，封装成 `SimpleContainer` 传给 `matches`。
    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) {//当你打开熬药锅、tick 运行时，MC 会自动调用这个方法，判断当前容器槽位物品是否符合配方；
        if (pLevel.isClientSide()) {return false;}
        int count = Math.min(inputItems.size(),6);
        for (int i = 0; i < count; i++) {
            if (!inputItems.get(i).test(pContainer.getItem(i))) {
                return false;
            }
        }
        for (int i = count; i < 6; i++) {
            if (!pContainer.getItem(i).isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack assemble(SimpleContainer pContainer, RegistryAccess pRegistryAccess) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    // 6. 获取产物堆叠数量
    @Override
    public ItemStack getResultItem(RegistryAccess pRegistryAccess) {
        return output.copy();
    }

    @Override
    public ResourceLocation getId() {
        return recipeId;
    }


    @Override
    public RecipeSerializer<?> getSerializer() {
        return EssRecipesRegister.HERB_CAULDRON_RECIPE_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return EssRecipesRegister.HERB_CAULDRON_RECIPE.get();
    }
}
