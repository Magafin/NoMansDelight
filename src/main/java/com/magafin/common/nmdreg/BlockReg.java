package com.magafin.common.nmdreg;

import com.magafin.common.block.LivingSoupBlock;
import com.magafin.common.block.PestoPizzaBlock;
import com.magafin.common.block.VenisonRouladeBlock;
import com.magafin.nomansdelight;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static com.magafin.common.nmdreg.ItemReg.LIVING_SOUP_BOWL;
import static com.magafin.common.nmdreg.ItemReg.VENISON_ROULADE_PLATE;
import static com.magafin.common.nmdreg.ItemReg.PESTO_PIZZA_SLICE;
import static net.minecraft.world.level.block.state.BlockBehaviour.Properties.ofFullCopy;

public class BlockReg {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK, nomansdelight.MODID);

    public static final Supplier<Block> LIVING_SOUP = BLOCKS.register("living_soup",
            () -> new LivingSoupBlock(ofFullCopy(Blocks.FLOWER_POT).sound(SoundType.DECORATED_POT), LIVING_SOUP_BOWL, true));
    public static final Supplier<Block> VENISON_ROULADE_BLOCK = BLOCKS.register("venison_roulade_block",
            () -> new VenisonRouladeBlock(ofFullCopy(Blocks.CAKE), VENISON_ROULADE_PLATE, true));
    public static final Supplier<Block> PESTO_PIZZA = BLOCKS.register("pesto_pizza",
            () -> new PestoPizzaBlock(ofFullCopy(Blocks.CAKE).noOcclusion(), PESTO_PIZZA_SLICE));
}
