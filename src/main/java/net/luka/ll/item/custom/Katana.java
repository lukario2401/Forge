package net.luka.ll.item.custom;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class Katana extends Item {
    public Katana(Item.Properties properties) {
        super(properties);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (level.isClientSide()) {
            player.displayClientMessage(Component.literal("Right-clicked!"), true);

            double lookX = player.getViewVector(1.0f).x();
            double lookZ = player.getViewVector(1.0f).z();
            player.push(lookX * 1.1, 0, lookZ * 1.1);
            player.getCooldowns().addCooldown(this, 10);
        }
        return super.use(level, player, hand);
    }

    @SubscribeEvent
    public void onLeftClick(PlayerInteractEvent.LeftClickEmpty event) {
        if (event.getHand() == InteractionHand.MAIN_HAND) {
            return; // Only handle main hand interactions
        }

        Player player = (Player) event.getEntity();
        ItemStack itemStack = player.getItemInHand(event.getHand());

        if (itemStack.getItem() instanceof Katana) {
            if (!player.level.isClientSide()) {
                Vec3 lookVec = player.getViewVector(1.0f);
                Vec3 eyePos = player.getEyePosition(1.0f);
                Vec3 reachVec = eyePos.add(lookVec.scale(5.0)); // 5 blocks reach
                AABB aabb = new AABB(eyePos, reachVec).inflate(1.0, 1.0, 1.0);

                List<LivingEntity> entities = player.level.getEntitiesOfClass(LivingEntity.class, aabb, entity -> entity != player);
                for (LivingEntity entity : entities) {
                    if (entity != player && entity.getBoundingBox().intersects(aabb)) {
                        entity.hurt(DamageSource.playerAttack(player), 5.0f); // 5 damage points
                    }
                }

                player.level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_ATTACK_STRONG, SoundSource.PLAYERS, 1.0F, 1.0F);
                player.getCooldowns().addCooldown(this, 10);
            }
        }
    }
}
