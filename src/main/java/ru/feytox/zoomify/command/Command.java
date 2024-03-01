package ru.feytox.zoomify.command;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import ru.feytox.zoomify.Config;
import ru.feytox.zoomify.c.cControls;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class Command {
    public static void init() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> dispatcher.register(literal("clicker")
                .executes(context -> {
                    Config.lTimer = 0;
                    Config.rTimer = 0;
                    cControls.setActive(!cControls.getActive());

                    return 1;
                }))
        );
    }
}