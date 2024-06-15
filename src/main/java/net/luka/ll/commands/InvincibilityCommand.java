package net.luka.ll.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class InvincibilityCommand {

    @SubscribeEvent
    public static void registerCommands(RegisterCommandsEvent event) {
        register(event.getDispatcher());
    }

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("inv")
                .then(Commands.argument("state", BoolArgumentType.bool())
                .executes(context -> {
                    boolean state = BoolArgumentType.getBool(context, "state");
                    ServerPlayer player = context.getSource().getPlayerOrException();

                    if (state) {
                        player.getAbilities().invulnerable = true;
                        context.getSource().sendSuccess(Component.literal("Invincibility enabled"), false);
                    } else {
                        player.getAbilities().invulnerable = false;
                        context.getSource().sendSuccess(Component.literal("Invincibility disabled"), false);
                    }

                    player.onUpdateAbilities();
                    return 1;
                })));
    }
}
