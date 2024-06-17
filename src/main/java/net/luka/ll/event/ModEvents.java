package net.luka.ll.event;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.luka.ll.Ll;
import net.luka.ll.item.ModItems;
import net.luka.ll.villager.ModVillagers;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = Ll.MOD_ID)
public class ModEvents {
    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
//-------------------------------------------------------------------------------------------------------
//        if (event.getType() == VillagerProfession.TOOLSMITH) {
//            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
//            ItemStack stack = new ItemStack(ModItems.EIGHT_BALL.get(), 1);
//            int villagerLevel = 1;
//
//            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
//                    new ItemStack(Items.EMERALD, 64),
//                    stack, 10, 8, 2.0F));
//        }
//-------------------------------------------------------------------------------------------------------
        if (event.getType() == VillagerProfession.TOOLSMITH) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ModItems.ZIRCON_SWORD.get(), 1);
            int villagerLevel = 4;

            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 64),
                    stack, 10, 8, 2.0F));
        }
//-------------------------------------------------------------------------------------------------------
        if (event.getType() == ModVillagers.JUMP_MASTER.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ModItems.DASH.get(), 1);
            int villagerLevel = 1;

            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 32),
                    stack, 10, 8, 2.0F));
        }
//-------------------------------------------------------------------------------------------------------
        if (event.getType() == ModVillagers.JUMP_MASTER.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ModItems.BLUEBERRY_SEEDS.get(), 1);
            int villagerLevel = 2;

            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 64),
                    new ItemStack(Items.EMERALD, 64), // Adding 64 emeralds as a second slot
                    stack, 10, 8, 2.0F));
        }
//-------------------------------------------------------------------------------------------------------

        if (event.getType() == ModVillagers.STALINIUM_TRADER.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ModItems.stalinium.get(), 1); // Adjust with your custom item
            int villagerLevel = 1;

            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 32), // Adjust the emerald cost
                    stack, 10, 8, 2.0F));
        }
//-------------------------------------------------------------------------------------------------------
    }
}
