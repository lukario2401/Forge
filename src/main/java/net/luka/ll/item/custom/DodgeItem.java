package net.luka.ll.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "ll", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class DodgeItem extends Item {

    public DodgeItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        if (!world.isClientSide) {
            Vec3 lookDirection = player.getLookAngle();
            BlockPos targetPos = new BlockPos(player.position().add(lookDirection.scale(6)));  // Move in the direction the player is looking

            int blocksBack = 0;
            while (world.getBlockState(targetPos).getMaterial().isSolid() && !world.getBlockState(targetPos).getBlock().equals(world.getBlockState(player.blockPosition()).getBlock())) {
                targetPos = new BlockPos(player.position().add(lookDirection.scale(6 - blocksBack)));
                blocksBack++;
            }

            // Move the player to the target position
            player.teleportTo(targetPos.getX() + 0.5, targetPos.getY() + 2, targetPos.getZ() + 0.5);

            // Place the player on the block
            while (world.getBlockState(targetPos.above()).isAir()) {
                targetPos = targetPos.below();
            }
            player.teleportTo(targetPos.getX() + 0.5, targetPos.getY() + 2, targetPos.getZ() + 0.5);

            world.playSound(null, targetPos, SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 1.0F, 1.0F);

            player.getCooldowns().addCooldown(this, 10);  // 0.5 seconds cooldown
            player.invulnerableTime = 10;  // 0.5 seconds invulnerability
        }

        return new InteractionResultHolder<>(InteractionResult.SUCCESS, player.getItemInHand(hand));
    }

    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (player.invulnerableTime > 0) {
                event.setCanceled(true);
            }
        }
    }
}