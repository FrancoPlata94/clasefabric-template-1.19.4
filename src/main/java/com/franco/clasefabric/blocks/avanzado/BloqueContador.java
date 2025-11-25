package com.franco.clasefabric.blocks.avanzado;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BloqueContador extends Block {
    public static final IntProperty contador= IntProperty.of("contador",0,10);

    public BloqueContador(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(contador,0));
    }

    @Override
    @Deprecated
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient()){
            int actual=state.get(contador);
            if (actual<10){
                world.setBlockState(pos,state.with(contador,actual+1));
                world.playSound(null, pos, SoundEvents.BLOCK_STONE_HIT, SoundCategory.BLOCKS, 1f, 1f);
                player.sendMessage(Text.literal("sola " + actual));
            }
            else{
                world.setBlockState(pos,Blocks.DIAMOND_BLOCK.getDefaultState());
            }
        }
        return ActionResult.SUCCESS;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(contador);
    }
}
