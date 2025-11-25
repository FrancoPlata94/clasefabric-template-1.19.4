package com.franco.clasefabric.blocks;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;

public class ModFLAMABLEBOCK {
    public static void registerFlamableblock() {
        FlammableBlockRegistry registry=FlammableBlockRegistry.getDefaultInstance();

        registry.add(Modblock.LOG_BLOCK.getLeft(), 5, 20);
        registry.add(Modblock.WOOD_BLOCK.getLeft(), 5, 20);
        registry.add(Modblock.LEAVES_BLOCK.getLeft(), 5, 20);
        registry.add(Modblock.DRUG_SAPLING_BLOCK.getLeft(), 5, 20);
    }
}
