package ru.katacan.clicker.gui;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.*;
import net.fabricmc.fabric.api.util.TriState;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import ru.katacan.clicker.Clicker;
import ru.katacan.clicker.Config;

public class SettingsGui extends LightweightGuiDescription {
    public static final Identifier SUPER_OFF_IMAGE = new Identifier(Clicker.MOD_ID, "textures/image_off.png");
    public static final Identifier SUPER_ON_IMAGE = new Identifier(Clicker.MOD_ID, "textures/image_on.png");

    public SettingsGui() {

        WPlainPanel ClickerSettings = new WPlainPanel();
        WPlainPanel back = new WPlainPanel();
        setRootPanel(back);
        back.setSize(ExampleScreen.screenWidth, ExampleScreen.screenHeight);

        back.add(ClickerSettings, (ExampleScreen.screenWidth / 2 - 125), (ExampleScreen.screenHeight / 2 - 73), 233, 146);


//        ClickerSettings.setBackgroundPainter(BackgroundPainter.VANILLA);


        //
        //
        // Clicker
        //
        //
        WToggleButton ToggleLeftClick = new WToggleButton(SUPER_ON_IMAGE, SUPER_OFF_IMAGE, Text.literal("Тоggle Lеft"));
        ToggleLeftClick.setColor(0xc3c3c3, 0xc3c3c3);
        if (Config.lActive) {
            ToggleLeftClick.setToggle(true);
        }
        ToggleLeftClick.setOnToggle(on -> {
            Config.lActive = !Config.lActive;
        });

        WTextField lClickDelay = new WTextField(Text.literal(Integer.toString(Config.getLClickDelayInt())));

        WToggleButton lHoldMode = new WToggleButton(SUPER_ON_IMAGE, SUPER_OFF_IMAGE, Text.literal("Ноld Mоd"));
        lHoldMode.setColor(0xc3c3c3, 0xc3c3c3);
        if (Config.lHoldMode) {
            lHoldMode.setToggle(true);
        }
        lHoldMode.setOnToggle(on -> {
            Config.lHoldMode = !Config.lHoldMode;
        });

        WToggleButton lTargetEntityMode = new WToggleButton(SUPER_ON_IMAGE, SUPER_OFF_IMAGE, Text.literal("Еntitу Mоd"));
        lTargetEntityMode.setColor(0xc3c3c3, 0xc3c3c3);
        if (Config.lTargetEntityMode) {
            lTargetEntityMode.setToggle(true);
        }
        lTargetEntityMode.setOnToggle(on -> {
            Config.lTargetEntityMode = !Config.lTargetEntityMode;
        });

        WToggleButton lCooldownAttackMode = new WToggleButton(SUPER_ON_IMAGE, SUPER_OFF_IMAGE, Text.literal("Сооldown Mоd"));
        lCooldownAttackMode.setColor(0xc3c3c3, 0xc3c3c3);
        if (Config.lCooldownAttackMode) {
            lCooldownAttackMode.setToggle(true);
        }
        lCooldownAttackMode.setOnToggle(on -> {
            Config.lCooldownAttackMode = !Config.lCooldownAttackMode;
        });


        WToggleButton ToggleRightClick = new WToggleButton(SUPER_ON_IMAGE, SUPER_OFF_IMAGE, Text.literal("Тоggle Right"));
        ToggleRightClick.setColor(0xc3c3c3, 0xc3c3c3);
        if (Config.rActive) {
            ToggleRightClick.setToggle(true);
        }
        ToggleRightClick.setOnToggle(on -> {
            Config.rActive = !Config.rActive;
        });

        WTextField rClickDelay = new WTextField(Text.literal(Integer.toString(Config.getRClickDelayInt())));


        WToggleButton rHoldMode = new WToggleButton(SUPER_ON_IMAGE, SUPER_OFF_IMAGE, Text.literal("Ноld Mоd"));
        rHoldMode.setColor(0xc3c3c3, 0xc3c3c3);
        if (Config.rHoldMode) {
            rHoldMode.setToggle(true);
        }
        rHoldMode.setOnToggle(on -> {
            Config.rHoldMode = !Config.rHoldMode;
        });

        WToggleButton rTargetEntityMode = new WToggleButton(SUPER_ON_IMAGE, SUPER_OFF_IMAGE, Text.literal("Еntity Mоd"));
        rTargetEntityMode.setColor(0xc3c3c3, 0xc3c3c3);
        if (Config.rTargetEntityMode) {
            rTargetEntityMode.setToggle(true);
        }
        rTargetEntityMode.setOnToggle(on -> {
            Config.rTargetEntityMode = !Config.rTargetEntityMode;
        });


        WButton OK = new WButton(Text.literal("Ok"));
        OK.setOnClick(() -> {
            if (!lClickDelay.getText().isEmpty() && !lClickDelay.getText().equals("0")) {
                Config.lClickDelayInt = convertStringToInt(lClickDelay.getText());
            }
        });
        WButton OKr = new WButton(Text.literal("Ok"));
        OKr.setOnClick(() -> {
            if (!rClickDelay.getText().isEmpty()) {
                Config.rClickDelayInt = convertStringToInt(rClickDelay.getText());
            }
        });

        WText command = new WText(Text.literal("Включить - /clicker"));
        command.setColor(0x828282, 0x828282);

        ClickerSettings.add(ToggleLeftClick, 10, 10);
        ClickerSettings.add(lClickDelay, 10, 30, 70, 10);
        ClickerSettings.add(OK, 83, 30, 20, 20);
        ClickerSettings.add(lHoldMode, 10, 50);
        ClickerSettings.add(lTargetEntityMode, 10, 65);
        ClickerSettings.add(lCooldownAttackMode, 10, 80);

        ClickerSettings.add(ToggleRightClick, 130, 10);
        ClickerSettings.add(rClickDelay, 130, 30, 70, 10);
        ClickerSettings.add(OKr, 203, 30, 20, 20);
        ClickerSettings.add(rHoldMode, 130, 50);
        ClickerSettings.add(rTargetEntityMode, 130, 65);

        ClickerSettings.add(command, 65, 127, 120, 1);
    }

    @Override
    public TriState isDarkMode() {
        return TriState.TRUE;
    }

    private int convertStringToInt(String str) {
        int value = 0;
        try {
            value = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            System.out.println("Convert String To Int Exception: " + e);
        }
        return value;
    }
}
