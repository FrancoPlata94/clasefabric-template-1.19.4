package com.franco.clasefabric.items.avanzado;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class PlacajeItem extends Item {
    public static final int MAX_USE_TIME =32;
    public PlacajeItem(Settings settings) {
        super(settings);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.CROSSBOW;
    }
    public int getMaxUseTime(ItemStack stack) {
        return 1;
    }



    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.setCurrentHand(hand);

        // Devuelve CONSUME para indicar que el clic funcionó
        return TypedActionResult.consume(user.getStackInHand(hand));
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity entity) {
        int amplifier=0;
        double radio = 4 + amplifier;
        Box Campo = entity.getBoundingBox().expand(radio);
        List<Entity> listaEntidades = entity.getWorld().getOtherEntities(entity, Campo);

        Vec3d b=entity.getRotationVec(1);
        entity.setVelocity(b.x*1.2,0.15,b.z*1.2);
        entity.velocityModified = true;
        for (Entity target : listaEntidades) {

            if (target instanceof PlayerEntity player) {
                if (player.isCreative() || player.isSpectator()) {
                    continue;
                }
            }

            Vec3d direccion = target.getPos().subtract(entity.getPos()).normalize();

            double fuerzaEmpuje = 2.0 + (amplifier * 0.5);

            target.setVelocity(
                    direccion.x * fuerzaEmpuje,
                    0.5,
                    direccion.z * fuerzaEmpuje
            );
            target.velocityModified = true;
        }
        return super.finishUsing(stack, world, entity);
    }
}
