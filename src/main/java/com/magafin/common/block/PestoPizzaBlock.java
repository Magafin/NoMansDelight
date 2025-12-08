package com.magafin.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import vectorwing.farmersdelight.common.block.PieBlock;
import vectorwing.farmersdelight.common.utility.ItemUtils;

import java.util.function.Supplier;

public class PestoPizzaBlock extends PieBlock {
    protected static final VoxelShape SHAPES = Block.box(0.0, 0.0, 0.0, 16.0, 2.0, 16.0);
    public PestoPizzaBlock(Properties properties, Supplier<Item> pieSlice) {
        super(properties, pieSlice);
    }
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPES;
    }
    @Override
    protected ItemInteractionResult cutSlice(Level level, BlockPos pos, BlockState state, Player player) {
        int bites = (Integer)state.getValue(BITES);
        if (bites < this.getMaxBites() - 1) {
            level.setBlock(pos, (BlockState)state.setValue(BITES, bites + 1), 3);
        } else {
            level.removeBlock(pos, false);
        }

        Direction direction = player.getDirection().getOpposite();
        ItemUtils.spawnItemEntity(level, this.getPieSliceItem(), (double)pos.getX() + 0.5, (double)pos.getY() + 0.3, (double)pos.getZ() + 0.5, (double)direction.getStepX() * 0.15, 0.05, (double)direction.getStepZ() * 0.15);
        level.playSound((Player)null, pos, SoundEvents.MUD_BRICKS_PLACE, SoundSource.PLAYERS, 0.8F, 0.8F);
        return ItemInteractionResult.SUCCESS;
    }
}
