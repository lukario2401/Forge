package net.luka.ll.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyBinding {
    public static final String KEY_CATEGORY_LL = "key.category.ll.lll";
    public static final String KEY_DRINK_WATER = "key.ll.drink_water";
    public static final String KEY_LIGHTNING = "key.ll.lightning";
    public static final String KEY_EXPLOSION = "key.ll.explosion";

    public static final KeyMapping DRINKING_KEY = new KeyMapping(KEY_DRINK_WATER, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_O, KEY_CATEGORY_LL);

    public static final KeyMapping LIGHTNING_KEY = new KeyMapping(KEY_LIGHTNING, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_I, KEY_CATEGORY_LL);

    public static final KeyMapping EXPLOSION_KEY = new KeyMapping(KEY_EXPLOSION, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_U, KEY_CATEGORY_LL);
}
