package com.magafin.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import vectorwing.farmersdelight.common.block.FeastBlock;

import java.util.function.Supplier;

public class VenisonRouladeBlock extends FeastBlock {
    protected static final VoxelShape PLATE_SHAPE_Z = Block.box(2.0, 0.0, 0.0, 14.0, 2.0, 16.0);
    protected static final VoxelShape FOOD_SHAPE_Z;
    protected static final VoxelShape PLATE_SHAPE_X;
    protected static final VoxelShape FOOD_SHAPE_X;

    public VenisonRouladeBlock(BlockBehaviour.Properties properties, Supplier<Item> servingItem, boolean hasLeftovers) {
        super(properties, servingItem, hasLeftovers);

    }
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        boolean empty = (Integer)state.getValue(this.getServingsProperty()) == 0;
        VoxelShape var10000;
        switch ((Direction)state.getValue(FACING)) {
            case NORTH:
            case SOUTH:
                var10000 = empty ? PLATE_SHAPE_Z : FOOD_SHAPE_Z;
                break;
            default:
                var10000 = empty ? PLATE_SHAPE_X : FOOD_SHAPE_X;
        }

        return var10000;
    }

    static {
        FOOD_SHAPE_Z = Shapes.or(PLATE_SHAPE_Z, Block.box(4.0, 2.0, 2.0, 12.0, 10.0, 14.0));
        PLATE_SHAPE_X = Block.box(0.0, 0.0, 2.0, 16.0, 2.0, 14.0);
        FOOD_SHAPE_X = Shapes.or(PLATE_SHAPE_X, Block.box(2.0, 2.0, 4.0, 14.0, 10.0, 12.0));
    }
}
