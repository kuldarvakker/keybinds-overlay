package com.keybindsOverlay;

import net.runelite.client.config.Keybind;
import net.runelite.client.util.ImageUtil;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

enum sidePanelTabs {

    ACCOUNT_MANAGEMENT, CLAN_CHAT, COMBAT, EMOTE,
    EQUIPMENT, FRIENDS, INVENTORY, LOGOUT, MUSIC,
    OPTIONS, PRAYER, QUESTS, SKILLS, SPELLBOOK;

    public BufferedImage getIcon() {
        try {
            return ImageUtil.resizeImage(ImageUtil.loadImageResource(getClass(), "/sidePanel/tabs/"+ name().toLowerCase() + ".png"), 18, 18);
        } catch (Exception e) {
            System.err.println("errorLoading image");
        }
        throw new RuntimeException("Shouldn't be here.");
    }

    public Keybind getKeybinding() {
        return ConfigPuller.getKeybinding(name(), "Key");
    }

    public int getTabLocation() {
        return ConfigPuller.getTabLocation(name(), "Int");
    }

}

public class ConfigPuller {

    public static KeybindsOverlayConfig config;

    private static Method getMethod(String mainSpecifier, String secondSpecifier)
    {
        Method[] methods = KeybindsOverlayConfig.class.getMethods();
        Pattern pattern = Pattern.compile(mainSpecifier.toLowerCase() + secondSpecifier);
        for (Method method : methods) {
            Matcher matcher = pattern.matcher(method.getName());
            if (matcher.lookingAt()) {
                return method;
            }
        }
        throw new RuntimeException("Programming error.");
    }

    public static Keybind getKeybinding(String mainSpecifier, String secondSpecifier)
    {
        Keybind keybind;
        try {
            keybind = (Keybind) getMethod(mainSpecifier, secondSpecifier).invoke(config);
        } catch (IllegalAccessException | InvocationTargetException e) {
            keybind = new Keybind(KeyEvent.VK_UNDEFINED, 0);
        }
        return keybind;
    }

    public static int getTabLocation(String mainSpecifier, String secondSpecifier) {
        int location;
        try {
            location = (int) getMethod(mainSpecifier, secondSpecifier).invoke(config);
        } catch (IllegalAccessException | InvocationTargetException e) {
            Random random = new Random();
            location = 100 + random.nextInt(30);
        }
        return location;
    }

}