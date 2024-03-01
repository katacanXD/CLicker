package ru.feytox.zoomify;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import ru.feytox.zoomify.c.cControls;
import ru.feytox.zoomify.gui.ExampleScreen;
import ru.feytox.zoomify.gui.SettingsGui;

import static ru.feytox.zoomify.Config.load;


public class Zoomify implements ModInitializer {
    public static final String MOD_ID = "clicker";

    private static KeyBinding registerKey(String keyname, int key) {

        return KeyBindingHelper.registerKeyBinding(
                new KeyBinding(
                        "key." + MOD_ID + "." + keyname,
                        InputUtil.Type.KEYSYM,
                        key, MOD_ID + ".midnightconfig.title"
                ));

    }

    private void clientStart(MinecraftClient client) {
        load();
    }

    @Override
    public void onInitialize() {
        ClientLifecycleEvents.CLIENT_STARTED.register(this::clientStart);


        ru.feytox.zoomify.command.Command.init();

        Config.lClickDelayInt = 10;


        KeyBinding open = registerKey("open", GLFW.GLFW_KEY_B);


        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            cControls.Update();
            Config.save();
            while (open.wasPressed()) {
                MinecraftClient.getInstance().setScreen(new ExampleScreen(new SettingsGui()));
                assert MinecraftClient.getInstance().currentScreen != null;
                MinecraftClient.getInstance().currentScreen.close();
                MinecraftClient.getInstance().setScreen(new ExampleScreen(new SettingsGui()));
            }
        });
    }
}
