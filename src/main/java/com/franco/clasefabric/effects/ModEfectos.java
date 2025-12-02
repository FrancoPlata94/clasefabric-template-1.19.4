package com.franco.clasefabric.effects;

import com.franco.clasefabric.ClaseFabric;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.particle.AbstractDustParticleEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

import java.util.logging.Logger;

public class ModEfectos  {

    public static final RegistryEntry<StatusEffect> Random=registrarEfecto("random_efe",new EfectoRandom(StatusEffectCategory.NEUTRAL,0x36ebab));


    private static RegistryEntry<StatusEffect> registrarEfecto(String nombre,StatusEffect s){
        return Registry.registerReference(Registries.STATUS_EFFECT,Identifier.of(ClaseFabric.MOD_ID,nombre),s);
    }
    public static void efectosRegister(){
        ClaseFabric.LOGGER.info("registrando efectos");
    }
}
