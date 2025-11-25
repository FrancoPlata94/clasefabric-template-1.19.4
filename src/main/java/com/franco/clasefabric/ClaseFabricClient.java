package com.franco.clasefabric;

import com.franco.clasefabric.blocks.Modblock;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class ClaseFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(Modblock.LEAVES_BLOCK.getLeft(), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Modblock.DRUG_SAPLING_BLOCK.getLeft(), RenderLayer.getCutout());
    }
}
