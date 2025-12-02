package com.franco.clasefabric.items;

import com.franco.clasefabric.ClaseFabric;
import com.franco.clasefabric.items.Herramientas.ModHerramientaMateriales;
import com.franco.clasefabric.items.Herramientas.custom.EspadaFantasmal;
import com.franco.clasefabric.items.Herramientas.custom.Hammer;
import com.franco.clasefabric.items.armadura.ModArmaduraMaterial;
import com.franco.clasefabric.items.avanzado.ChiselItem;
import com.franco.clasefabric.items.avanzado.PipaItem;
import com.franco.clasefabric.items.avanzado.PlacajeItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;



public class ItemMod {
    public static final Item Nose=registerItem("nose",new Item(new FabricItemSettings()));
    public static final Item PIPA=registerItem("pipa",new PipaItem(new FabricItemSettings()));
    public static final Item HIERBA=registerItem("hierba",new Item(new FabricItemSettings()));
    public static final Item CHISEL=registerItem("chisel",new ChiselItem(new FabricItemSettings().maxDamage(20)));
    public static final Item CRISTAL=registerItem("cristal_del_reino",new Item(new FabricItemSettings()));
    public static  final Item ESPADA_CRISTAL=registerItem("espada_crista",new EspadaFantasmal(ModHerramientaMateriales.CRISTAL,10,2f,new FabricItemSettings()));
    public static  final Item PICO_CRISTAL=registerItem("pico_crista",new PickaxeItem(ModHerramientaMateriales.CRISTAL,10,2f,new FabricItemSettings()));
    public static  final Item HACHA_CRISTAL=registerItem("hacha_crista",new AxeItem(ModHerramientaMateriales.CRISTAL,10,2f,new FabricItemSettings()));
    public static  final Item PALA_CRISTAL=registerItem("pala_crista",new ShovelItem(ModHerramientaMateriales.CRISTAL,10,2f,new FabricItemSettings()));
    public static  final Item HAMMER_CRISTAL=registerItem("hammer_cristal",new Hammer(ModHerramientaMateriales.CRISTAL,10,2f,new FabricItemSettings()));
    public static  final Item CASCO_CRISTAL=registerItem("casco",new ArmorItem(ModArmaduraMaterial.CRISTAL, ArmorItem.Type.HELMET,new FabricItemSettings()));
    public static  final Item PECHERA_CRISTAL=registerItem("pechera_cristal",new ArmorItem(ModArmaduraMaterial.CRISTAL, ArmorItem.Type.CHESTPLATE,new FabricItemSettings()));
    public static  final Item PANTALONM_CRISTAL=registerItem("pantalon_cristal",new ArmorItem(ModArmaduraMaterial.CRISTAL, ArmorItem.Type.LEGGINGS,new FabricItemSettings()));
    public static  final Item BOTA_CRISTAL=registerItem("bota_cristal",new ArmorItem(ModArmaduraMaterial.CRISTAL, ArmorItem.Type.BOOTS,new FabricItemSettings()));
    public static final Item Placaje=registerItem("placaje_item",new PlacajeItem(new FabricItemSettings()));




    private static Item registerItem(String itemId,Item item){
        return Registry.register(Registries.ITEM, new Identifier(ClaseFabric.MOD_ID, itemId),item);}





    public static void  registerItems(){
        ClaseFabric.LOGGER.info("registrando items");
    }
}
