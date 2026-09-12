package org.com.xing_zi.essenceevolve.recipe.skill_infuser;

import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
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
    public ItemStack assemble(SimpleContainer pContainer, @NotNull RegistryAccess pRegistryAccess) {
        ItemStack inputItem = pContainer.getItem(0);
        ItemStack itemStack = inputItem.copy();
        ItemStack bookItem = pContainer.getItem(1);
        CompoundTag outputItemTag = itemStack.getOrCreateTag();
        if (bookItem.getItem() instanceof SkillBookItem skillBookItem){
            SkillType skill = skillBookItem.getSkill();
            String skillId = skill.getSkillId();
            int skillLevel = skill.getSkillLevel();
            String skillKey = skill.getSkillKey();
            System.out.println("skill 运行时类型: " + skill.getClass().getName());
            System.out.println("skillId: '" + skillId + "'");
            System.out.println("skillKey: '" + skillKey + "'");
            System.out.println("skillLevel: " + skillLevel);
            if (outputItemTag.contains(skillKey)){
                ListTag skillIdAndLevelTag = outputItemTag.getList(skillKey, Tag.TAG_COMPOUND);
                CompoundTag skillBox = new CompoundTag();
                Tag tag = skillIdAndLevelTag.get(0);
                skillIdAndLevelTag.clear();
                if (tag instanceof CompoundTag compoundTag){
                    int level = compoundTag.getInt(skillId);
                    if (level == skillLevel){
                        skillLevel++;
                        skillBox.putInt(skillId,skillLevel);
                    }else {
                        int maxSkillLevel = Math.max(level, skillLevel);
                        skillBox.putInt(skillId,maxSkillLevel);
                    }
                    skillIdAndLevelTag.add(skillBox);
                    outputItemTag.put(skillKey,skillIdAndLevelTag);
                }
            }else {
                CompoundTag skillBox = new CompoundTag();
                skillBox.putInt(skillId,skillLevel);
                ListTag list = new ListTag();
                list.add(skillBox);
                outputItemTag.put(skillKey,list);
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