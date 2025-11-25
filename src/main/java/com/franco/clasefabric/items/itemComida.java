package com.franco.clasefabric.items;


import com.franco.clasefabric.ClaseFabric;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class itemComida {
    public static Item registrarItemComida(String idItem, Item item){
        return Registry.register(Registries.ITEM,new Identifier(ClaseFabric.MOD_ID,idItem),item);
    }
    public static final FoodComponent Salteña= new FoodComponent.Builder().hunger(1).saturationModifier(1).meat().alwaysEdible().snack().statusEffect(new StatusEffectInstance(StatusEffects.DARKNESS,200),1).build();
    public static final Item SALTENA=registrarItemComida("saltena",new/*aca podria poner una clase avanzada como ItemSAlateña*/ Item(new FabricItemSettings().food(itemComida.Salteña)));

    public static void RegistrandoItemsComida(){
        //solo se llama o invoca la funcion en el principal para que carge todos los final en la clase
    }
}

