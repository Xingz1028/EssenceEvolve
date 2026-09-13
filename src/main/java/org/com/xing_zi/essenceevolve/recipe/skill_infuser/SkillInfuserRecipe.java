package org.com.xing_zi.essenceevolve.recipe.skill_infuser;

import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.com.xing_zi.essenceevolve.items.weapon_skill.SkillBookItem;
import org.com.xing_zi.essenceevolve.items.weapon_skill.SkillType;
import org.com.xing_zi.essenceevolve.recipe.EssRecipesRegister;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SkillInfuserRecipe implements Recipe<SimpleContainer> {
    private ResourceLocation id;
    public NonNullList<Ingredient> inputItems;
    private ItemStack outputItem;

    public  SkillInfuserRecipe(ResourceLocation recipeId, NonNullList<Ingredient> inputItems, ItemStack output) {
        this.id = recipeId;
        this.inputItems = inputItems;//这个数据在Serializer里就传入了，fromJson方法通过反序列器读取json文件将物品获取到inputItems集合中
        this.outputItem = output.copy();
    }

    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) {
        if(!pLevel.isClientSide()){
            int count = 2;
            for (int i = 0; i < count; i++) {
                if (!inputItems.get(i).test(pContainer.getItem(i))){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public @Nullable ItemStack assemble(SimpleContainer pContainer, @NotNull RegistryAccess pRegistryAccess) {
        ItemStack inputItem = pContainer.getItem(0);
        ItemStack itemStack = inputItem.copy();
        ItemStack bookItem = pContainer.getItem(1);
        CompoundTag outputItemTag = itemStack.getOrCreateTag();
        if (bookItem.getItem() instanceof SkillBookItem skillBookItem){
            SkillType skill = skillBookItem.getSkill();
            String skillId = skill.getSkillId();
            int skillLevel = skill.getSkillLevel();
            if (outputItemTag.contains(skillId)){
                int level = outputItemTag.getInt(skillId);
                if (skillLevel < level){
                    return outputItem.copy();
                }else if (skillLevel == level){
                   outputItemTag.putInt(skillId,level+1);
                }else {
                    outputItemTag.putInt(skillId,skillLevel);//skillLevel > level
                }
            }else {
                outputItemTag.putInt(skillId,skillLevel);
            }
        }
        return itemStack;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess pRegistryAccess) {
        return outputItem.copy();
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return EssRecipesRegister.SKILL_INFUSER_RECIPE_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return EssRecipesRegister.SKILL_INFUSER_RECIPE.get();
    }
    public NonNullList<Ingredient> getInputItems() {
        return inputItems;
    }
}