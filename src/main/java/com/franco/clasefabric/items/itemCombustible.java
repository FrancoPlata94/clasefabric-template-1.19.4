package com.franco.clasefabric.items;

import com.franco.clasefabric.ClaseFabric;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class itemCombustible {
    private static Item registerItem(String itemId,Item item){
        return Registry.register(Registries.ITEM, new Identifier(ClaseFabric.MOD_ID, itemId),item);}
    public static final Item PAJA_JUNTADA=registerItem("paja_juntada",new Item(new FabricItemSettings()));


    public static void registerFuels() {
        FuelRegistry.INSTANCE.add(PAJA_JUNTADA, 400);
        // 400 = duración en ticks (20 ticks = 1 segundo)
        // carbón normal = 1600 ticks, así que 400 dura 1/4 del tiempo
    }

}
