package org.com.xing_zi.essenceevolve.recipe.HerbCauldronRecipe;

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

public class HerbCauldronRecipeSerializer implements RecipeSerializer<HerbCauldronRecipe> {
    // 游戏启动读取data下的json配方，pRecipeId为配方文件唯一标识，必须传给配方构造
    @Override
    public HerbCauldronRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {

        ItemStack output = ShapedRecipe.itemStackFromJson(pSerializedRecipe.getAsJsonObject("output"));//有序合成配方
        JsonArray ingredients = GsonHelper.getAsJsonArray(pSerializedRecipe, "ingredients");//Gson工具辅助类
        NonNullList<Ingredient> inputs = NonNullList.withSize(ingredients.size(), Ingredient.EMPTY);
        for (int i = 0; i < inputs.size(); i++) {
            inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
        }
        return new HerbCauldronRecipe(pRecipeId, inputs, output);
    }

    // 多人联机：服务端下发数据包，客户端读取配方
    @Override
    public @Nullable HerbCauldronRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
        int length = pBuffer.readInt();
        NonNullList<Ingredient> inputs = NonNullList.withSize(length, Ingredient.EMPTY);
        for (int i = 0; i < length; i++) {
            inputs.set(i, Ingredient.fromNetwork(pBuffer));
        }
        ItemStack output = pBuffer.readItem();
        return new HerbCauldronRecipe(pRecipeId, inputs, output);
    }

    // 多人联机：服务端把配方写入数据包发给客户端
    @Override
    public void toNetwork(FriendlyByteBuf pBuffer, HerbCauldronRecipe pRecipe) {
        pBuffer.writeInt(pRecipe.getInputItems().size());
        for (Ingredient ingredient : pRecipe.getInputItems()){
            ingredient.toNetwork(pBuffer);
        }
        pBuffer.writeItemStack(pRecipe.getResultItem(null), false);
    }
}
