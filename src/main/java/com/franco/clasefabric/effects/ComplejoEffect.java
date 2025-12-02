package com.franco.clasefabric.effects;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

import java.util.List;

public class ComplejoEffect extends StatusEffect {
    protected ComplejoEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        double radio = 4 + amplifier;
        Box Campo = entity.getBoundingBox().expand(radio);
        List<Entity> listaEntidades = entity.getWorld().getOtherEntities(entity, Campo);

        Vec3d b=entity.getRotationVec(1);
        entity.setVelocity(b.x*1.2,0,b.z*1.2);
        for (Entity target : listaEntidades) {

            if (target instanceof PlayerEntity player) {
                if (player.isCreative() || player.isSpectator()) {
                    continue;
                }
            }

            Vec3d direccion = target.getPos().subtract(entity.getPos()).normalize();

            double fuerzaEmpuje = 1.0 + (amplifier * 0.5);

            target.setVelocity(
                    direccion.x * fuerzaEmpuje,
                    0.3,
                    direccion.z * fuerzaEmpuje
            );
            target.velocityModified = true;
        }
        super.applyUpdateEffect(entity, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
