package com.franco.clasefabric.items.avanzado;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

public class ChiselItem extends Item {
    public static final Map<Block, Block> cm=Map.of(Blocks.DIRT,Blocks.GRASS_BLOCK,
            Blocks.GRASS_BLOCK,Blocks.GOLD_BLOCK,
            Blocks.GOLD_BLOCK,Blocks.DIAMOND_BLOCK,
            Blocks.DIAMOND_BLOCK,Blocks.NETHERITE_BLOCK,
            Blocks.NETHERITE_BLOCK, Blocks.ANCIENT_DEBRIS,
            Blocks.ANCIENT_DEBRIS,Blocks.DIRT);
    public ChiselItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        Block c=context.getWorld().getBlockState(context.getBlockPos()).getBlock();
        if (!context.getWorld().isClient()){
            if (cm.containsKey(c)){
                context.getWorld().setBlockState(context.getBlockPos(),cm.get(c).getDefaultState());
                if (context.getPlayer()!=null){
                context.getStack().damage(1, context.getPlayer(), p -> p.sendToolBreakStatus(context.getHand()));}
            }
        }
        NbtCompound ntb=new NbtCompound();
        ntb.putString("clasefabric.ultima_poscicion","bloque " +c.asItem().getName().getString()+" en ("+ context.getBlockPos().getX()+", "+ context.getBlockPos().getY()+", "+context.getBlockPos().getZ()+ ") ");
        context.getStack().setNbt(ntb);
        return ActionResult.SUCCESS;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if(Screen.hasShiftDown()) {
            if (stack.hasNbt()) {
                tooltip.add(Text.literal(stack.getNbt().getString("clasefabric.ultima_poscicion")));
            } else {
                tooltip.add(Text.literal("añade algo"));
            }
        }
        else{
            tooltip.add((Text.literal("Presiona SHIFT ")));
        }
    }
}
