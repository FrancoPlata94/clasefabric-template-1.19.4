package com.franco.clasefabric.blocks;

import com.franco.clasefabric.ClaseFabric;
import com.franco.clasefabric.blocks.avanzado.BloqueConEstado;
import com.franco.clasefabric.blocks.avanzado.BloqueContador;
import com.franco.clasefabric.blocks.avanzado.FidgeBlock;
import com.franco.clasefabric.blocks.avanzado.MiBloqueOrientable;
import com.franco.clasefabric.world.tree.ModSaplingGenerator;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;

public class Modblock {
    //añadido de bloque
    public static final Pair<Block, Item> CUMBLOCK = registerBlock("cumblock", new Block(FabricBlockSettings.of(Material.METAL).requiresTool().strength(1.0f)));
    public static final Pair<Block, Item> TABLE_BLOCK = registerBlock("table_block", new Block(FabricBlockSettings.of(Material.PORTAL).requiresTool().strength(1.0f)));
    public static final Pair<Block, Item> FID_BLOCK = registerBlock("fid_block", new FidgeBlock(FabricBlockSettings.of(Material.AMETHYST).requiresTool().strength(1.0f)));
    public static final Pair<Block, Item> BloqueContador = registerBlock("contador_block", new BloqueContador(FabricBlockSettings.of(Material.AMETHYST).requiresTool().strength(1.0f)));
    public static final Pair<Block, Item> LOG_BLOCK = registerBlock("log_block", new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG).strength(0.8f).requiresTool()));
    public static final Pair<Block, Item> WOOD_BLOCK = registerBlock("wood_block", new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_WOOD).strength(0.8f).requiresTool()));
    public static final Pair<Block, Item> DRUG_SAPLING_BLOCK = registerBlock("drug_sapling_block", new SaplingBlock(new ModSaplingGenerator(), FabricBlockSettings.copyOf(Blocks.ACACIA_SAPLING).strength(0.2f)));
    public static final Pair<Block, Item> LEAVES_BLOCK = registerBlock("leaves_block", new LeavesBlock(FabricBlockSettings.copyOf( Blocks.ACACIA_LEAVES).strength(0.3f).requiresTool()));
    public static final Pair<Block, Item> BLOQUE_CON_ESTADO =registerBlock("bloque_estado",new BloqueConEstado(FabricBlockSettings.copyOf(Blocks.DIRT).strength(0.8f).requiresTool()));





    //codigo de registro principal de los bloques

    private static Pair<Block, Item> registerBlock(String name, Block block){
        return new Pair<>(
                Registry.register(Registries.BLOCK, new Identifier(ClaseFabric.MOD_ID, name),block),
                Registry.register(Registries.ITEM, new Identifier(ClaseFabric.MOD_ID, name),new BlockItem(block, new FabricItemSettings()))

        );
    }

//registro de bloques (activacion)
    public static void registerBlock(){
        ClaseFabric.LOGGER.info("regsitrando blockes");
    }
}
