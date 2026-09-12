package org.com.xing_zi.essenceevolve.recipe.skill_infuser;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SkillInfuserRecipeSerializer implements RecipeSerializer<SkillInfuserRecipe> {
    @Override
    public @NotNull SkillInfuserRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {
        ItemStack output = ShapedRecipe.itemStackFromJson(pSerializedRecipe.getAsJsonObject("output"));
        JsonArray ingredients = GsonHelper.getAsJsonArray(pSerializedRecipe, "ingredients");
        NonNullList<Ingredient> inputs = NonNullList.withSize(ingredients.size(), Ingredient.EMPTY);
        for (int i = 0; i < inputs.size(); i++) {
            inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
        }
        return new SkillInfuserRecipe(pRecipeId, inputs, output);
    }

    @Override
    public @Nullable SkillInfuserRecipe fromNetwork(@NotNull ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
        int length = pBuffer.readInt();
        NonNullList<Ingredient> inputs = NonNullList.withSize(length, Ingredient.EMPTY);
        for (int i = 0; i < length; i++) {
            inputs.set(i, Ingredient.fromNetwork(pBuffer));
        }
        ItemStack output = pBuffer.readItem();
        return new SkillInfuserRecipe(pRecipeId, inputs, output);
    }

    @Override
    public void toNetwork(FriendlyByteBuf pBuffer, SkillInfuserRecipe pRecipe) {
        pBuffer.writeInt(pRecipe.getInputItems().size());
        for (Ingredient ingredient : pRecipe.getInputItems()) {
            ingredient.toNetwork(pBuffer);
        }
        pBuffer.writeItemStack(pRecipe.getResultItem(null), false);
    }
}