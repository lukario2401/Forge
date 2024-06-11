package net.luka.ll.item.custom;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

public class Katana extends Item {
    public Katana(Item.Properties properties) {
        super(properties);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onLeftClick(PlayerInteractEvent.LeftClickEmpty event) {
        Player player = event.getEntity();
        Level level = player.getCommandSenderWorld();
        ItemStack heldItem = player.getMainHandItem();

        // Check if the player is holding the Katana
        if (heldItem.getItem() instanceof Katana) {
            // Only perform the raycast on the client side
            if (level.isClientSide()) {
                Vec3 start = player.getEyePosition(1.0F);
                Vec3 look = player.getViewVector(1.0F);
                Vec3 end = start.add(look.scale(20.0)); // 20 block range

                double stepSize = 0.2; // Step size for raycast
                for (double d = 0; d < 20.0; d += stepSize) {
                    Vec3 point = start.add(look.scale(d));
                    level.addParticle(ParticleTypes.CRIT, point.x, point.y, point.z, 0, 0, 0);

                    // Check for entities within a 0.2 block radius at this point
                    AABB aabb = new AABB(point.x - 0.1, point.y - 0.1, point.z - 0.1, point.x + 0.1, point.y + 0.1, point.z + 0.1);
                    List<Entity> entities = level.getEntities(player, aabb, entity -> entity instanceof LivingEntity && entity != player);

                    if (!entities.isEmpty()) {
                        for (Entity entity : entities) {
                            if (entity instanceof LivingEntity) {
                                // Deal damage to the entity
                                entity.hurt(DamageSource.playerAttack(player), 24.0F);
                                level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_ATTACK_STRONG, SoundSource.PLAYERS, 1.0F, 1.0F);

                                // Stop the raycast after damaging the mob
                                return;
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (level.isClientSide()) {
            player.displayClientMessage(Component.literal("Right-clicked!"), true);

            double lookX = player.getViewVector(1.0f).x;
            double lookZ = player.getViewVector(1.0f).z;
            player.push(lookX * 1.1, 0, lookZ * 1.1);
            player.getCooldowns().addCooldown(this, 10);
        }
        return super.use(level, player, hand);
    }
}
