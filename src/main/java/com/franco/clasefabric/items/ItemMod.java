package com.franco.clasefabric.items;

import com.franco.clasefabric.ClaseFabric;
import com.franco.clasefabric.items.avanzado.ChiselItem;
import com.franco.clasefabric.items.avanzado.PipaItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;



public class ItemMod {
    public static final Item Nose=registerItem("nose",new Item(new FabricItemSettings()));
    public static final Item PIPA=registerItem("pipa",new PipaItem(new FabricItemSettings()));
    public static final Item HIERBA=registerItem("hierba",new Item(new FabricItemSettings()));
    public static final Item CHISEL=registerItem("chisel",new ChiselItem(new FabricItemSettings().maxDamage(20)));
    public static final Item CRISTAL=registerItem("cristal_del_reino",new Item(new FabricItemSettings()));


    private static Item registerItem(String itemId,Item item){
        return Registry.register(Registries.ITEM, new Identifier(ClaseFabric.MOD_ID, itemId),item);}





    public static void  registerItems(){
        ClaseFabric.LOGGER.info("registrando items");
    }
}
