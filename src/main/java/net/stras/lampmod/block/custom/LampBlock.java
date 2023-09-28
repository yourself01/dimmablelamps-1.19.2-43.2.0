package net.stras.lampmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.ItemSteerable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.Tags;
import net.stras.lampmod.LampMod;
import net.stras.lampmod.item.ModItems;

public class LampBlock extends Block {

    public static final IntegerProperty POWER = BlockStateProperties.POWER;
    public static final IntegerProperty LIT = IntegerProperty.create("lit",0,15);

    public LampBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos pos1, boolean b) {
        if(!level.isClientSide){
            int flag = state.getValue(LIT);
            if(level.hasNeighborSignal(pos)){
                int i = level.getBestNeighborSignal(pos);
                if(i<=15){
                    level.setBlock(pos,state.setValue(LIT, i),3);
                }
            } else {
                level.setBlock(pos,state.setValue(LIT, 0),3);
            }
        }
    }

//    public BlockState getStateForPlacement(BlockPlaceContext context) {
//        return this.defaultBlockState().setValue(LIT, int.valueOf(context.getLevel().hasNeighborSignal(context.getClickedPos())));
//    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos,
                                 Player player, InteractionHand hand, BlockHitResult result) {
        ItemStack itemstack = player.getItemInHand(hand);
        if(hand == InteractionHand.MAIN_HAND){
            if (itemstack.is(ModItems.WRENCH.get())){
            level.setBlock(pos, state.cycle(LIT),3);
        }}


        return super.use(state, level, pos, player, hand, result);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LIT, POWER);
    }
}
