package net.luka.ll.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
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

import java.util.Random;

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

            Random random = new Random();
            for (int i = 0; i < 20; i++) {
                double offsetX = (random.nextDouble() - 0.5) * 2.0;
                double offsetY = (random.nextDouble() - 0.5) * 2.0;
                double offsetZ = (random.nextDouble() - 0.5) * 2.0;
                level.addParticle(ParticleTypes.EXPLOSION, entity.getX(), entity.getY() + 1.0, entity.getZ(), offsetX, offsetY, offsetZ);
            }

            level.playLocalSound(entity.getX(), entity.getY(), entity.getZ(), SoundEvents.GENERIC_EXPLODE, SoundSource.PLAYERS, 0.5F, 1.0F, false);
            double lookX = entity.getViewVector(1.0f).x();
            double lookZ = entity.getViewVector(1.0f).z();
            entity.push(0, 0.7, 0);
            entity.push(lookX * 1.5, 0, lookZ * 1.5);

        }


        super.stepOn(level, blockPos, blockState, entity);
    }
}
