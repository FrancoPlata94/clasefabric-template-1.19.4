package com.franco.clasefabric.datagen;

import com.franco.clasefabric.items.ItemMod;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.recipe.book.RecipeCategory;

import java.util.List;
import java.util.function.Consumer;

public class DatagenRecipeProvider extends FabricRecipeProvider {
    public DatagenRecipeProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        offerSmelting(exporter, List.of(ItemMod.HIERBA), RecipeCategory.BUILDING_BLOCKS, ItemMod.Nose,1.0f,100,"drugs");
    }


}
