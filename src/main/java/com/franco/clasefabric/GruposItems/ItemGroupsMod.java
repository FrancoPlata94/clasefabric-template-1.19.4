package com.franco.clasefabric.GruposItems;

import com.franco.clasefabric.ClaseFabric;
import com.franco.clasefabric.blocks.Modblock;
import com.franco.clasefabric.items.ItemMod;
import com.franco.clasefabric.items.itemCombustible;
import com.franco.clasefabric.items.itemComida;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ItemGroupsMod {

    public static final ItemGroup NOSE_ITEM_GROUP = FabricItemGroup.builder(new Identifier(ClaseFabric.MOD_ID, "nose_group"))
            .displayName(Text.translatable("Drugs"))
            .icon(() -> new ItemStack(ItemMod.Nose))
            .entries((displayContext, entries) -> {
                entries.add(ItemMod.Nose);
                entries.add(ItemMod.PIPA);
                entries.add(ItemMod.HIERBA);
                entries.add(Modblock.CUMBLOCK.getLeft().asItem());
                entries.add(Modblock.FID_BLOCK.getLeft().asItem());
                entries.add(Modblock.TABLE_BLOCK.getLeft().asItem());
                entries.add(Modblock.LOG_BLOCK.getLeft().asItem());
                entries.add(Modblock.LEAVES_BLOCK.getLeft().asItem());
                entries.add(Modblock.DRUG_SAPLING_BLOCK.getLeft().asItem());
                entries.add(ItemMod.CRISTAL);
                entries.add(Modblock.WOOD_BLOCK.getLeft());
                entries.add(ItemMod.CHISEL);
                entries.add(itemCombustible.PAJA_JUNTADA);
                entries.add(itemComida.SALTENA);
                entries.add(Modblock.BLOQUE_CON_ESTADO.getLeft().asItem());
                entries.add(Modblock.BloqueContador.getLeft().asItem());


            })
            .build();
    //una pestaña del menu items
    public static final ItemGroup ADA_ITEM_GROUP = FabricItemGroup.builder(new Identifier(ClaseFabric.MOD_ID, "ada_group"))
            .displayName(Text.translatable("itemGroup.ejemplo.ada_group"))
            .icon(() -> new ItemStack(ItemMod.PIPA))
            .entries((displayContext, entries) -> {

                entries.add(ItemMod.HIERBA);
            })
            .build();
    //otra pestaña del menu items

    public static final ItemGroup DADA_ITEM_GROUP = FabricItemGroup.builder(new Identifier(ClaseFabric.MOD_ID, "dada_group"))
            .displayName(Text.translatable("itemGroup.ejemplo2.dada_group"))
            .icon(() -> new ItemStack(ItemMod.HIERBA))
            .entries((displayContext, entries) -> {
                entries.add(ItemMod.HIERBA);
            })
            .build();
    public static void registerItemGroups() {
        ClaseFabric.LOGGER.info("registrando grupos de items" +ClaseFabric.MOD_ID);
    }
}