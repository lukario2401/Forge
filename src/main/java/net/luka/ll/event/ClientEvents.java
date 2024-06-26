package net.luka.ll.event;

import net.luka.ll.Ll;
import net.luka.ll.networking.ModMessages;
import net.luka.ll.networking.packet.DrinkWaterC2SPacket;
import net.luka.ll.networking.packet.ExampleC2SPacket;
import net.luka.ll.networking.packet.ExplosionC2SPacket;
import net.luka.ll.util.KeyBinding;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ClientEvents {
    @Mod.EventBusSubscriber(modid = Ll.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            if (KeyBinding.DRINKING_KEY.consumeClick()) {
                ModMessages.sendToServer(new DrinkWaterC2SPacket());
            }
            if (KeyBinding.LIGHTNING_KEY.consumeClick()) {
                ModMessages.sendToServer(new ExampleC2SPacket());
            }
            if (KeyBinding.EXPLOSION_KEY.consumeClick()) {
                ModMessages.sendToServer(new ExplosionC2SPacket());
            }
        }
    }

    @Mod.EventBusSubscriber(modid = Ll.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(KeyBinding.DRINKING_KEY);
            event.register(KeyBinding.LIGHTNING_KEY);
            event.register(KeyBinding.EXPLOSION_KEY);
        }
    }
}
