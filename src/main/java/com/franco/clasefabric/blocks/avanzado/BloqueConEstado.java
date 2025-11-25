package com.franco.clasefabric.blocks.avanzado;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BloqueConEstado extends Block {
    public static final BooleanProperty Clicked= BooleanProperty.of("cliqueado");
    public BloqueConEstado(Settings settings) {
        super(settings);
        setDefaultState(this.getDefaultState().with(Clicked,false));
    }

    @Override
    @Deprecated
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient()){
            world.setBlockState(pos,state.cycle(Clicked));
        }
        return ActionResult.SUCCESS;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(Clicked);
    }
}
