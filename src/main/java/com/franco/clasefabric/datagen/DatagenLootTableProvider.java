package com.franco.clasefabric.datagen;

import com.franco.clasefabric.blocks.Modblock;
import com.franco.clasefabric.items.ItemMod;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

public class DatagenLootTableProvider extends FabricBlockLootTableProvider {
    public DatagenLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(Modblock.CUMBLOCK.getLeft(),drops(Modblock.CUMBLOCK.getLeft()));
        addDrop(Modblock.FID_BLOCK.getLeft());
        addDrop(Modblock.TABLE_BLOCK.getLeft());
        addDrop(Modblock.WOOD_BLOCK.getLeft(),oreDrops(Modblock.WOOD_BLOCK.getLeft(), ItemMod.CRISTAL));


    }
}
