package com.franco.clasefabric.blocks.avanzado;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties; // Aquí están las propiedades predefinidas
import net.minecraft.util.math.Direction;

public class MiBloqueOrientable extends Block {

    // 1. DEFINICIÓN: Usamos una propiedad estática predefinida.
    // HORIZONTAL_FACING permite: North, South, East, West (sin arriba/abajo).
    // Si quisieras que mire al techo/suelo (como un dispensador), usarías Properties.FACING.
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    public MiBloqueOrientable(Settings settings) {
        super(settings);
        // 2. CONSTRUCTOR: Seteamos el valor por defecto inicial.
        // Es buena práctica iniciar siempre mirando al NORTE para evitar nulos.
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH));
    }

    // 3. REGISTRO: Le decimos al BlockState Manager que este bloque usa la propiedad FACING.
    // Sin esto, el juego crashea al iniciar porque no sabe que la propiedad existe.
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    // 4. LÓGICA DE COLOCACIÓN: Aquí ocurre la magia matemática.
    // Este método se ejecuta justo cuando vas a poner el bloque (evento).
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        // ctx.getPlayerFacing() devuelve la dirección cardinal hacia la que mira el jugador.
        // .getOpposite() es CLAVE: Si tú miras al Norte, quieres que la cara frontal del bloque mire al Sur (hacia ti).
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }
}