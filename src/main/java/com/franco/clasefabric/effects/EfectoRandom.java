package com.franco.clasefabric.effects;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class EfectoRandom extends StatusEffect {
    protected EfectoRandom(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {

        if (entity.isOnGround() ){
            BlockPos b=entity.getBlockPos();
            BlockPos a=new BlockPos(b.getX(),b.getY()-1,b.getZ());
            if (!entity.getWorld().getBlockState(a).isAir() && !entity.getWorld().getBlockState(a).isOf(Blocks.BEDROCK)){
                if(!entity.getWorld().getBlockState(a).isOf(Blocks.WATER) && !entity.getWorld().getBlockState(a).isOf(Blocks.DIAMOND_BLOCK)){
                    entity.getWorld().setBlockState(a, Blocks.GOLD_BLOCK.getDefaultState());
                }
                else entity.getWorld().setBlockState(a,Blocks.DIAMOND_BLOCK.getDefaultState());
            }
        }
        if (!Screen.hasShiftDown() ) {
            if (entity.verticalCollision) {
                entity.setVelocity(entity.getVelocity().x, 1.0, entity.getVelocity().z);
                entity.velocityModified = true;
            } else if (entity.horizontalCollision) {
                World world = entity.getWorld();
                BlockPos pos = entity.getBlockPos();

                double fuerza = 1.5;
                double empujeX = 0;
                double empujeZ = 0;

                if (world.getBlockState(pos.east()).isSolidBlock(world, pos.east())) {
                    empujeX -= fuerza;
                }
                if (world.getBlockState(pos.west()).isSolidBlock(world, pos.west())) {
                    empujeX += fuerza;
                }
                if (world.getBlockState(pos.south()).isSolidBlock(world, pos.south())) {
                    empujeZ -= fuerza;
                }
                if (world.getBlockState(pos.north()).isSolidBlock(world, pos.north())) {
                    empujeZ += fuerza;
                }
                if (empujeX != 0 || empujeZ != 0) {
                    entity.setVelocity(empujeX, 0.5, empujeZ);
                    entity.velocityModified = true;
                } else {
                    Vec3d look = entity.getRotationVec(1.0f);
                    entity.setVelocity(-look.x * fuerza, 0.5, -look.z * fuerza);
                    entity.velocityModified = true;
                }
            }
        }
        else{
            LivingEntity player=entity;
            double radio = 4.0 + amplifier;

            // 2. Crear una caja invisible alrededor del jugador para buscar víctimas
            Box area = player.getBoundingBox().expand(radio);
            List<Entity> entidadesCercanas = player.getWorld().getOtherEntities(player, area);

            // 3. Recorrer todo lo que encontró
            for (Entity target : entidadesCercanas) {

                // --- CASO A: PROYECTILES (Flechas, Tridentes, Bolas de Fuego) ---
                if (target instanceof ProjectileEntity) {
                    // Opción 1: Matrix (Detenerlas en el aire y que caigan)
                    // target.setVelocity(0, -0.1, 0);

                    // Opción 2: Rebote (Devolverlas al que disparó)
                    Vec3d repulsion = target.getPos().subtract(player.getPos()).normalize().multiply(0);
                    target.setVelocity(repulsion);
                    target.velocityModified = true;
                }

                // --- CASO B: ENEMIGOS (Zombies, Esqueletos...) ---
                // Usamos HostileEntity para no empujar cerditos ni aldeanos
                else if (target instanceof HostileEntity) {

                    // Calcular la distancia real
                    double distancia = player.distanceTo(target);

                    // Solo empujar si están muy cerca (dentro del radio)
                    if (distancia <= radio) {
                        // Matemáticas Vectoriales:
                        // Restar (Posición Enemigo - Posición Jugador) nos da un vector que apunta HACIA AFUERA
                        Vec3d direccionEmpuje = target.getPos().subtract(player.getPos()).normalize();

                        // Aplicar fuerza (más fuerte cuanto más cerca esté)
                        double fuerza = 0.3;
                        target.addVelocity(direccionEmpuje.x * fuerza, 0.1, direccionEmpuje.z * fuerza);
                    }
                }
            }

            // 4. VISUALES (El "Wow Factor")
            // Generar partículas eléctricas alrededor para que se vea el campo
            if (player.getWorld().isClient) {
                // Solo dibujamos 1 o 2 partículas por tick para no laggear
                for (int i = 0; i < 50; i++) {
                    if (player.getRandom().nextFloat() < 0.3f) {
                        double x = player.getX() + (player.getRandom().nextDouble() - 0.5) * radio * 2;
                        double y = player.getY() + (player.getRandom().nextDouble() - 0.5) * radio * 2;
                        double z = player.getZ() + (player.getRandom().nextDouble() - 0.5) * radio * 2;
                        player.getWorld().addParticle(ParticleTypes.ELECTRIC_SPARK, x, y, z, 0, 0, 0);
                    }
                }
            }
        }
        super.applyUpdateEffect(entity, amplifier);

    }
}
