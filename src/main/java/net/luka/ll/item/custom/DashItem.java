package net.luka.ll.item.custom;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class DashItem extends Item {
    private static final Map<Player, Integer> playerParticleTicks = new HashMap<>();

    public DashItem(Properties properties) {
        super(properties);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (level.isClientSide()) {
            level.playLocalSound(player.getX(), player.getY(), player.getZ(), SoundEvents.FIREWORK_ROCKET_LAUNCH, SoundSource.PLAYERS, 0.5F, 1.0F, false);

            double lookX = player.getViewVector(1.0f).x();
            double lookZ = player.getViewVector(1.0f).z();
            player.push(lookX * 1.1, 0.2, lookZ * 1.1);
            player.getCooldowns().addCooldown(this, 20);

            // Start the particle effect
            playerParticleTicks.put(player, 20); // 20 ticks = 1 second
        }
        return super.use(level, player, hand);
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            playerParticleTicks.entrySet().removeIf(entry -> {
                Player player = entry.getKey();
                int ticks = entry.getValue();
                if (ticks > 0 && !player.isOnGround()) {
                    spawnParticles(player.level, player);
                    entry.setValue(ticks - 1);
                    return false;
                }
                return true;
            });
        }
    }

    private void spawnParticles(Level level, Player player) {

        for (int i = 0; i < 5; i++) {
            double offsetX = 0;
            double offsetY = 0;
            double offsetZ = 0;
            level.addParticle(ParticleTypes.FIREWORK, player.getX() + offsetX, player.getY() + offsetY, player.getZ() + offsetZ, 0, 0, 0);
        }
    }
}
