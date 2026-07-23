package org.com.xing_zi.essenceevolve.recipe.EssenceAssemblyTableRecipe;

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
import org.jetbrains.annotations.Nullable;

public class EssenceAssemblyTableRecipeSerializer implements RecipeSerializer<EssenceAssemblyTableRecipe> {
    @Override
    public EssenceAssemblyTableRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {
        ItemStack output = ShapedRecipe.itemStackFromJson(pSerializedRecipe.getAsJsonObject("output"));
        JsonArray ingredients = GsonHelper.getAsJsonArray(pSerializedRecipe, "ingredients");
        NonNullList<Ingredient> inputs = NonNullList.withSize(ingredients.size(), Ingredient.EMPTY);
        for (int i = 0; i < inputs.size(); i++) {
            inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
        }
        return new EssenceAssemblyTableRecipe(pRecipeId,inputs,output);
    }

    @Override
    public @Nullable EssenceAssemblyTableRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
        int length = pBuffer.readInt();
        NonNullList<Ingredient> inputs = NonNullList.withSize(length, Ingredient.EMPTY);
        for (int i = 0; i < length; i++) {
            inputs.set(i, Ingredient.fromNetwork(pBuffer));
        }
        ItemStack output = pBuffer.readItem();
        return new EssenceAssemblyTableRecipe(pRecipeId, inputs, output);
    }

    @Override
    public void toNetwork(FriendlyByteBuf pBuffer, EssenceAssemblyTableRecipe pRecipe) {
        pBuffer.writeInt(pRecipe.getIngredients().size());
        for (Ingredient ingredient : pRecipe.getIngredients()) {
            ingredient.toNetwork(pBuffer);
        }
        pBuffer.writeItemStack(pRecipe.getResultItem(null), false);
    }
}
