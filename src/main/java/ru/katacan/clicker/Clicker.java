package ru.katacan.clicker;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import ru.katacan.clicker.c.cControls;
import ru.katacan.clicker.gui.ExampleScreen;
import ru.katacan.clicker.gui.SettingsGui;

import static ru.katacan.clicker.Config.load;


public class Clicker implements ModInitializer {
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


        ru.katacan.clicker.command.Command.init();

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
