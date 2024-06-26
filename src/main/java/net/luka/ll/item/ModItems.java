package net.luka.ll.item;

import net.luka.ll.Ll;
import net.luka.ll.block.ModBlocks;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.luka.ll.item.custom.*;
import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Ll.MOD_ID);

        public static final Tier ZIRCON_SWORD_TIER = new ForgeTier(
        3,  // Harvest level
        2500,  // Durability
        8.0f,  // Mining speed
        3.0f,  // Attack damage
        10,  // Enchantability
        null,  // Tag for the blocks this material can mine
        () -> Ingredient.of(Items.NETHERITE_INGOT)  // Repair ingredient
        );

        public static final RegistryObject<Item> KATANA = ITEMS.register("katana",
    () -> new Katana(new Item.Properties().tab(ModCreativeModeTab.Custom_tab).stacksTo(1)));

    public static final RegistryObject<Item> DASH = ITEMS.register("dash",
    () -> new DashItem(new Item.Properties().tab(ModCreativeModeTab.Custom_tab).stacksTo(1)));

    public static final RegistryObject<Item> STRENGTH_TOTEM = ITEMS.register("strength_totem",
    () -> new StrengthTotemItem(new Item.Properties().tab(ModCreativeModeTab.Custom_tab).stacksTo(1)));

    public static final RegistryObject<Item> EIGHT_BALL = ITEMS.register("eight_ball",
        () -> new EightBallItem(new Item.Properties().tab(ModCreativeModeTab.Custom_tab).stacksTo(1)));

    public static final RegistryObject<Item> stalinium = ITEMS.register("stalinium",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.Custom_tab)));

    public static final RegistryObject<Item> raw_stalinium = ITEMS.register("raw_stalinium",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.Custom_tab)));

    public static final RegistryObject<Item> zircon = ITEMS.register("zircon",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.Custom_tab)));

        public static final RegistryObject<Item> raw_zircon = ITEMS.register("raw_zircon",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.Custom_tab)));

    public static final RegistryObject<Item> ZIRCON_SWORD = ITEMS.register("zircon_sword",
        () -> new SwordItem(ZIRCON_SWORD_TIER, 5, -1.4f,
            new Item.Properties().tab(ModCreativeModeTab.Custom_tab)));

        public static final RegistryObject<Item> DODGE = ITEMS.register("dodge",
    () -> new DodgeItem(new Item.Properties().tab(ModCreativeModeTab.Custom_tab).stacksTo(1)));

                public static final RegistryObject<Item> BLUEBERRY_SEEDS = ITEMS.register("blueberry_seeds",
    () -> new ItemNameBlockItem(ModBlocks.BLUEBERRY_CROP.get(),new Item.Properties().tab(ModCreativeModeTab.Custom_tab)));

public static final RegistryObject<Item> BLUEBERRY = ITEMS.register("blueberry",
    () -> new Item(new Item.Properties().tab(ModCreativeModeTab.Custom_tab).food(new FoodProperties.Builder().alwaysEat().nutrition(2).saturationMod(2f).fast()
            .effect(() -> new MobEffectInstance(MobEffects.SATURATION, 200), 1.0F).build())));



    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}

