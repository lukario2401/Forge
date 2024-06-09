package net.luka.ll.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class JumpBock extends Block {

    public JumpBock(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult blockHitResult) {

        if(!level.isClientSide){
            if(hand == InteractionHand.MAIN_HAND){
                player.sendSystemMessage(Component.literal("right clicked this!"));
            }
        }




        return super.use(blockState, level, blockPos, player, hand, blockHitResult);


    }


    @Override
    public void stepOn(Level level, BlockPos blockPos, BlockState blockState, Entity entity) {
        if(entity instanceof LivingEntity livingEntity){
            double lookX = entity.getViewVector(1.0f).x();
            double lookZ = entity.getViewVector(1.0f).z();
            entity.push(lookX * 1.1, 0.2, lookZ * 1.1);

        }


        super.stepOn(level, blockPos, blockState, entity);
    }
}
