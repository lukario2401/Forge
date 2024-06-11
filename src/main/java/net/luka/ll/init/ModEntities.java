package net.luka.ll.init;

import net.luka.ll.Ll;
import net.luka.ll.Ll;
import net.luka.ll.entity.projectile.KatanaProjectile;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.IEventBus;

@Mod.EventBusSubscriber(modid = Ll.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Ll.MOD_ID);

    public static final RegistryObject<EntityType<KatanaProjectile>> KATANA_PROJECTILE = ENTITY_TYPES.register("katana_projectile", () ->
            EntityType.Builder.<KatanaProjectile>of(KatanaProjectile::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F)
                    .setTrackingRange(4)
                    .setUpdateInterval(10)
                    .setShouldReceiveVelocityUpdates(true)
                    .build(Ll.MOD_ID + ":katana_projectile"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
