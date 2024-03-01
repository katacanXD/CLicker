package ru.katacan.clicker.c;

import io.github.cottonmc.cotton.gui.client.CottonHud;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import ru.katacan.clicker.Config;
import ru.katacan.clicker.c.gui.Hud;

public class cControls {

    public static void Update() {
        if (getActive()) {
            assert MinecraftClient.getInstance().player != null;
            if (Config.lActive) {
                CottonHud.add(Hud.item, Hud.itemPositioner());
                if (!Config.lHoldMode) {
                    if (!Config.lTargetEntityMode) {
                        if (Config.lCooldownAttackMode) {
                            if (MinecraftClient.getInstance().player.getAttackCooldownProgress(0) == 1) {
                                KeyBinding.onKeyPressed(InputUtil.Type.MOUSE.createFromCode(GLFW.GLFW_MOUSE_BUTTON_1));
                            }
                        } else {
                            if (Config.lTimer < Config.lClickDelayInt) Config.lTimer++;
                            else {
                                KeyBinding.onKeyPressed(InputUtil.Type.MOUSE.createFromCode(GLFW.GLFW_MOUSE_BUTTON_1));
                                Config.lTimer = 0;
                            }
                        }
                    } else {
                        if (MinecraftClient.getInstance().targetedEntity != null) {
                            if (Config.lCooldownAttackMode) {
                                if (MinecraftClient.getInstance().player.getAttackCooldownProgress(0) == 1) {
                                    KeyBinding.onKeyPressed(InputUtil.Type.MOUSE.createFromCode(GLFW.GLFW_MOUSE_BUTTON_1));
                                }
                            } else {
                                if (Config.lTimer < Config.lClickDelayInt) Config.lTimer++;
                                else {
                                    KeyBinding.onKeyPressed(InputUtil.Type.MOUSE.createFromCode(GLFW.GLFW_MOUSE_BUTTON_1));
                                    Config.lTimer = 0;
                                }
                            }
                        }
                    }
                } else {
                    KeyBinding.setKeyPressed(InputUtil.Type.MOUSE.createFromCode(GLFW.GLFW_MOUSE_BUTTON_1), true);
                }
            }
            if (Config.rActive) {
                CottonHud.add(Hud.item, Hud.itemPositioner());
                if (!Config.rHoldMode) {
                    if (!Config.rTargetEntityMode) {
                        if (Config.rTimer < Config.rClickDelayInt) Config.rTimer++;
                        else {
                            KeyBinding.onKeyPressed(InputUtil.Type.MOUSE.createFromCode(GLFW.GLFW_MOUSE_BUTTON_2));
                            Config.rTimer = 0;
                        }
                    } else {
                        if (MinecraftClient.getInstance().targetedEntity != null) {
                            if (Config.rTimer < Config.rClickDelayInt) Config.rTimer++;
                            else {
                                KeyBinding.onKeyPressed(InputUtil.Type.MOUSE.createFromCode(GLFW.GLFW_MOUSE_BUTTON_2));
                                Config.rTimer = 0;
                            }
                        }
                    }
                } else {
                    KeyBinding.setKeyPressed(InputUtil.Type.MOUSE.createFromCode(GLFW.GLFW_MOUSE_BUTTON_2), true);
                }
            }
        }
    }

    public static boolean getActive() {
        return Config.Active;
    }

    public static void setActive(boolean active) {
        if (!active) {
            if (Config.lHoldMode)
                KeyBinding.setKeyPressed(InputUtil.Type.MOUSE.createFromCode(GLFW.GLFW_MOUSE_BUTTON_1), false);
            if (Config.rHoldMode)
                KeyBinding.setKeyPressed(InputUtil.Type.MOUSE.createFromCode(GLFW.GLFW_MOUSE_BUTTON_2), false);
        }
        Config.Active = active;
        if (!Config.Active) CottonHud.remove(Hud.item);
    }
}
