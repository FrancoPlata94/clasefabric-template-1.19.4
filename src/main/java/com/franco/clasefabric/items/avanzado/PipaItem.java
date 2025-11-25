package com.franco.clasefabric.items.avanzado;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PipaItem extends Item {
    public static final int MAX_USE_TIME =32;
    public PipaItem(Settings settings) {
        super(settings);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.CROSSBOW   ;
    }

    @Override
    public void onCraft(ItemStack stack, World world, PlayerEntity player) {
        super.onCraft(stack, world, player);
    }
    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        if (!world.isClient) {
            // Efectos visuales o lógicos del servidor
        } else {
            // Aquí las partículas, porque son CLIENT-SIDE
            for (int i=0; i<15;i++){

            world.addParticle(
                    ParticleTypes.CAMPFIRE_SIGNAL_SMOKE,
                    user.getX() + (world.random.nextDouble() - 0.5) * 2,
                    user.getY() + 1.0,
                    user.getZ() + (world.random.nextDouble() - 0.5) * 2 ,
                    0, 0.1, 0
            );};
        }

        super.usageTick(world, user, stack, remainingUseTicks);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if(!world.isClient){
            if (user instanceof PlayerEntity player) {
                player.sendMessage(Text.literal("¡La policía está detrás de ti!"), true);
            }
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA,200,3));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH,200,3));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION,20,5));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.DARKNESS,50,3));
            ZombieEntity zombie = new ZombieEntity(EntityType.ZOMBIE, world);
            zombie.refreshPositionAndAngles(user.getX(), user.getY(), user.getZ(), 0, 0);
            world.spawnEntity(zombie);
            world.playSound(null,user.getBlockPos(), SoundEvents.BLOCK_BEACON_ACTIVATE, SoundCategory.BLOCKS,43f,2);
            stack.getOrCreateNbt().putInt("powerdadwada", 10);
            stack.decrement(1);
        }


                // efectos del servidor (por ejemplo, aplicar pociones)
            else {
                // partículas al terminar
                for (int i = 0; i < 20; i++) {
                    world.addParticle(
                            ParticleTypes.EXPLOSION,
                            user.getX() + (world.random.nextDouble() - 0.5),
                            user.getY() + 1.0,
                            user.getZ() + (world.random.nextDouble() - 0.5),
                            0, 0.2, 0
                    );
                }

        }
        return super.finishUsing(stack, world, user);
    }
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world.isClient) {
            world.addParticle(ParticleTypes.HAPPY_VILLAGER, user.getX(), user.getY() + 3.0, user.getZ(), 0, 0.1, 0);
        }
        return ItemUsage.consumeHeldItem(world, user, hand);
    }
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.literal("§aItem wadaaaa – úsalo con cuidado"));
        tooltip.add(Text.literal("§7Genera humo, efectos y un zombie loco"));
    }

    public int getMaxUseTime(ItemStack stack) {
        return 90;
    }

}
