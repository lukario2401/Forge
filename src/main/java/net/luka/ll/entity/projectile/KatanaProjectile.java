package net.luka.ll.entity.projectile;

import net.luka.ll.init.ModEntities;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class KatanaProjectile extends ThrowableProjectile {
    public KatanaProjectile(EntityType<? extends KatanaProjectile> entityType, Level level) {
        super(entityType, level);
    }

    public KatanaProjectile(Level level, LivingEntity shooter) {
        super(ModEntities.KATANA_PROJECTILE.get(), shooter, level);
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        if (!this.level.isClientSide) {
            result.getEntity().hurt(DamageSource.thrown(this, this.getOwner()), 10.0F);
            this.discard();
        }
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        if (!this.level.isClientSide) {
            this.discard();
        }
    }

    @Override
    protected void defineSynchedData() {
    }
}
