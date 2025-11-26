package com.franco.clasefabric.datagen;

import com.franco.clasefabric.blocks.Modblock;
import com.franco.clasefabric.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class DatagenBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public DatagenBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(Modblock.CUMBLOCK.getLeft())
                .add(Modblock.FID_BLOCK.getLeft());
        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
                .add(Modblock.TABLE_BLOCK.getLeft())
                .add(Modblock.WOOD_BLOCK.getLeft())
                .add(Modblock.LOG_BLOCK.getLeft());
        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(Modblock.FID_BLOCK.getLeft())
                .add(Modblock.TABLE_BLOCK.getLeft());
        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(Modblock.CUMBLOCK.getLeft());
        getOrCreateTagBuilder(ModTags.Blocks.NEEDS_CRISTAL_TOOL)
                .add(Modblock.BloqueContador.getLeft());
    }

}
