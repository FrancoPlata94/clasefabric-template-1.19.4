package com.franco.clasefabric.datagen;

import com.franco.clasefabric.blocks.Modblock;
import com.franco.clasefabric.blocks.avanzado.BloqueConEstado;
import com.franco.clasefabric.items.ItemMod;
import com.franco.clasefabric.items.itemCombustible;
import com.franco.clasefabric.items.itemComida;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;
import net.minecraft.util.Identifier;

public class DatagenModelProvider extends FabricModelProvider {
    public DatagenModelProvider(FabricDataOutput output) {
        super(output);
    }


    //creacion de
    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(Modblock.CUMBLOCK.getLeft());
        blockStateModelGenerator.registerSimpleCubeAll(Modblock.FID_BLOCK.getLeft());
        blockStateModelGenerator.registerSimpleCubeAll(Modblock.TABLE_BLOCK.getLeft());
        blockStateModelGenerator.registerSimpleCubeAll(Modblock.LEAVES_BLOCK.getLeft());
        blockStateModelGenerator.registerLog(Modblock.LOG_BLOCK.getLeft()).log(Modblock.LOG_BLOCK.getLeft()).wood(Modblock.WOOD_BLOCK.getLeft());
        blockStateModelGenerator.registerTintableCrossBlockState(Modblock.DRUG_SAPLING_BLOCK.getLeft(),BlockStateModelGenerator.TintType.NOT_TINTED);
/*
        blockStateModelGenerator.registerSimpleCubeAll(Modblock.BLOQUE_CON_ESTADO.getLeft());
*/

        Identifier apagadoIdentidicador = TexturedModel.CUBE_ALL.upload(Modblock.BLOQUE_CON_ESTADO.getLeft(),blockStateModelGenerator.modelCollector);
        Identifier encendidoIdentidicador = blockStateModelGenerator.createSubModel(Modblock.BLOQUE_CON_ESTADO.getLeft(),"_encendido",Models.CUBE_ALL, TextureMap::all);
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(Modblock.BLOQUE_CON_ESTADO.getLeft())
                .coordinate(BlockStateModelGenerator.createBooleanModelMap(BloqueConEstado.Clicked,encendidoIdentidicador,apagadoIdentidicador)));


    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ItemMod.CHISEL, Models.GENERATED);
        itemModelGenerator.register(itemCombustible.PAJA_JUNTADA, Models.GENERATED);
        itemModelGenerator.register(itemComida.SALTENA,Models.GENERATED);
        itemModelGenerator.register(ItemMod.CRISTAL, Models.GENERATED);

    }
}
