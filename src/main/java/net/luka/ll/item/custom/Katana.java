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
import net.minecraft.world.level.ClipContext;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

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

                ClipContext context = new ClipContext(start, end, ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, player);
                HitResult hitResult = level.clip(context);

                // Visualize the raycast with particles
                double stepSize = 0.5;
                for (double d = 0; d < 20.0; d += stepSize) {
                    Vec3 point = start.add(look.scale(d));
                    level.addParticle(ParticleTypes.CRIT, point.x, point.y, point.z, 0, 0, 0);

                    // Perform a small raycast at each step to check for entities
                    ClipContext stepContext = new ClipContext(start, point, ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, player);
                    HitResult stepResult = level.clip(stepContext);

                    // Check if the result is an entity hit
                    if (stepResult.getType() == HitResult.Type.ENTITY) {
                        Entity entity = ((EntityHitResult) stepResult).getEntity();

                        if (entity instanceof LivingEntity) {
                            // Deal damage to the entity
                            entity.hurt(DamageSource.playerAttack(player), 24.0F);
                            level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_ATTACK_STRONG, SoundSource.PLAYERS, 1.0F, 1.0F);
                            break; // Stop the raycast after damaging the mob
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
