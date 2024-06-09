package net.luka.ll.item.custom;

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

    private void handleLeftClick(Player player) {
    Level level = player.level;

    player.displayClientMessage(Component.literal("Left-clicked!"), true);

    Vec3 playerPos = player.position();
    double range = 5.0; // 5 block range

    AABB aabb = new AABB(playerPos.subtract(range, range, range), playerPos.add(range, range, range));
    List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, aabb);

    for (LivingEntity entity : entities) {
        if (entity != player) {
            entity.hurt(DamageSource.playerAttack(player), 24.0f);
        }
    }
}



    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void onLeftClick(PlayerInteractEvent.LeftClickBlock event) {
        handleLeftClick(event.getEntity());
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void onLeftClickEmpty(PlayerInteractEvent.LeftClickEmpty event) {
        handleLeftClick(event.getEntity());
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void onLeftClickEntity(PlayerInteractEvent.EntityInteract event) {
        if (event.getHand() == InteractionHand.MAIN_HAND) {
            handleLeftClick(event.getEntity());
        }
    }
}
