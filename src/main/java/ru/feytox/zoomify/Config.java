package ru.feytox.zoomify;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Path;

public class Config {
    // Constants
    private static final Path CONFIG = FabricLoader.getInstance().getGameDir().resolve("evo-plus").resolve("megacfg.json");
    private static final Gson GSON = new GsonBuilder()
            .excludeFieldsWithModifiers(Modifier.TRANSIENT, Modifier.FINAL)
            .setPrettyPrinting()
            .create();


    // Config fields
    public static int lClickDelayInt = 0;
    public static int rClickDelayInt = 0;
    public static boolean Active = false;
    public static boolean lActive = false;
    public static boolean rActive = false;
    public static boolean lHoldMode = false;
    public static boolean rHoldMode = false;
    public static boolean lTargetEntityMode = false;
    public static boolean rTargetEntityMode = false;
    public static boolean lCooldownAttackMode = false;
    public static int lTimer = 0;
    public static int rTimer = 0;

    public static void load() {
        try {
            if (!Files.isRegularFile(CONFIG)) {
                System.out.println("no config found. will use default values.");
                return;
            }
            String value = Files.readString(CONFIG);
            GSON.fromJson(value, Config.class);
            System.out.println("Loaded config.");
        } catch (Throwable t) {
            System.err.println("Unable to read config.");
            t.printStackTrace();
        }
    }

    public static void save() {
        try {
            @SuppressWarnings("InstantiationOfUtilityClass")
            String value = GSON.toJson(new Config());
            Files.createDirectories(CONFIG.toAbsolutePath().getParent());
            Files.writeString(CONFIG, value);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public static int getLClickDelayInt() {
        return lClickDelayInt;
    }

    public static int getRClickDelayInt() {
        return rClickDelayInt;
    }
}