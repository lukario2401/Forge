package net.luka.ll.item.custom;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;

import java.util.Random;


public class DashItem extends Item {

    public DashItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (level.isClientSide()) {

//            level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.GENERIC_EXPLODE, SoundSource.PLAYERS, 1.0F, 1.0F);

            Random random = new Random();
            for (int i = 0; i < 20; i++) {
                double offsetX = (random.nextDouble() - 0.5) * 2.0;
                double offsetY = (random.nextDouble() - 0.5) * 2.0;
                double offsetZ = (random.nextDouble() - 0.5) * 2.0;
                level.addParticle(ParticleTypes.EXPLOSION, player.getX(), player.getY() + 1.0, player.getZ(), offsetX, offsetY, offsetZ);
            }

            double lookX = player.getViewVector(1.0f).x();
            double lookZ = player.getViewVector(1.0f).z();
            player.push(lookX * 1.1, 0.2, lookZ * 1.1);
            player.getCooldowns().addCooldown(this, 20);

        }
        return super.use(level, player, hand);
    }
}