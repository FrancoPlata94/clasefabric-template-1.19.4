package com.franco.clasefabric.items.Herramientas.custom;


import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class EspadaFantasmal extends SwordItem {

    // ESTRUCTURA DE DATOS (MEMORIA)
    // Usamos un Map estático porque en Minecraft solo existe UNA instancia de la clase 'CX'.
    // Si no fuera estático, perderíamos los datos entre ticks.
    // Key: UUID de la víctima. Value: Datos del golpe pendiente.
    private static final Map<UUID, PendingHit> PENDING_HITS = new ConcurrentHashMap<>();
    public static final Map<DefaultParticleType, StatusEffect> Ataque =Map.of(ParticleTypes.FLAME,StatusEffects.WITHER,
            ParticleTypes.ITEM_SNOWBALL,StatusEffects.SLOWNESS,
            ParticleTypes.ELECTRIC_SPARK,StatusEffects.GLOWING);

    public EspadaFantasmal(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    // PASO 1: EL GATILLO (GOLPE INICIAL)
    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!Screen.hasShiftDown()){
        if (!target.getWorld().isClient) { // Solo lógica del servidor
            // Registramos el golpe para que ocurra en el futuro (20 ticks = 1 segundo)
            // Guardamos quién atacó para que la kill cuente como tuya.
            PENDING_HITS.put(target.getUuid(), new PendingHit(attacker.getUuid(), 80));

            // Sonido sutil para indicar que la "marca" está puesta
            target.getWorld().playSound(null, target.getBlockPos(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.PLAYERS, 0.5f, 2.0f);
        }}
        else {
            if (!target.getWorld().isClient()){
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS,4,10));
                target.getWorld().addParticle(ParticleTypes.ITEM_SNOWBALL,target.getX(),target.getY(),target.getZ(),3,3,3);
            }
        }
        return super.postHit(stack, target, attacker);

    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {

        return super.finishUsing(stack, world, user);
    }

    // PASO 2: EL RELOJ (PROCESAMIENTO)
    // Este método se ejecuta 20 veces por segundo si tienes el ítem en el inventario.
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        // Solo procesamos en el servidor y si hay golpes pendientes
        if (!world.isClient && !PENDING_HITS.isEmpty()) {

            // Usamos un iterador para poder borrar elementos mientras recorremos la lista
            Iterator<Map.Entry<UUID, PendingHit>> iterator = PENDING_HITS.entrySet().iterator();

            while (iterator.hasNext()) {
                Map.Entry<UUID, PendingHit> entry = iterator.next();
                UUID targetUuid = entry.getKey();
                PendingHit hitData = entry.getValue();

                // Restamos 1 tick al tiempo restante
                hitData.ticksLeft--;

                // PASO 3: EL GOLPE FANTASMA (EJECUCIÓN)
                if (hitData.ticksLeft <= 0) {
                    // El tiempo se acabó, hora del ECO.

                    // Necesitamos buscar a la entidad en el mundo actual usando su UUID
                    // Nota: Esto asume que la entidad sigue en el mismo mundo (dimension).
                    if (world instanceof ServerWorld serverWorld) {
                        Entity victim = serverWorld.getEntity(targetUuid);
                        Entity attacker = serverWorld.getEntity(hitData.attackerUuid);

                        // Verificamos que la víctima siga viva y exista
                        if (victim instanceof LivingEntity livingVictim && livingVictim.isAlive()) {

                            // 1. APLICAR DAÑO (50% del daño base, por ejemplo 4.0f)
                            // Usamos damageSources().magic() para que ignore armadura, o mobAttack() para físico.
                            livingVictim.damage(serverWorld.getDamageSources().magic(), 4.0f);

                            // 2. EL EFECTO "WAOS" (VISUALES Y SONIDO)
                            // Generamos partículas de "SWEEP_ATTACK" (el corte de espada) en la posición de la víctima
                            serverWorld.spawnParticles(ParticleTypes.SWEEP_ATTACK,
                                    livingVictim.getX(), livingVictim.getBodyY(0.5), livingVictim.getZ(),
                                    1, 0.0, 0.0, 0.0, 0.0);

                            // Partículas de magia negra (Squid Ink o Ash)
                            serverWorld.spawnParticles(ParticleTypes.SQUID_INK,
                                    livingVictim.getX(), livingVictim.getY() + 1, livingVictim.getZ(),
                                    10, 0.5, 0.5, 0.5, 0.1);

                            // Sonido de corte fantasmal
                            serverWorld.playSound(null, livingVictim.getBlockPos(), SoundEvents.ENTITY_PHANTOM_BITE, SoundCategory.HOSTILE, 1.0f, 0.5f);
                        }
                    }

                    // Importante: Eliminar del mapa para que no siga golpeando infinitamente
                    iterator.remove();
                }
            }
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }

    // CLASE INTERNA PARA GUARDAR DATOS
    // Funciona como un pequeño "struct" o registro de datos.
    private static class PendingHit {
        UUID attackerUuid;
        int ticksLeft;

        public PendingHit(UUID attackerUuid, int ticksLeft) {
            this.attackerUuid = attackerUuid;
            this.ticksLeft = ticksLeft;
        }
    }
}