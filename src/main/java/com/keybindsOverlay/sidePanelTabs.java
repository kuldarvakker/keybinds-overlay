package com.keybindsOverlay;

import net.runelite.client.util.ImageUtil;

import java.awt.image.BufferedImage;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum sidePanelTabs {

    ACCOUNT_MANAGEMENT, CLAN_CHAT, COMBAT, EMOTE,
    EQUIPMENT, FRIENDS, INVENTORY, LOGOUT, MUSIC,
    OPTIONS, PRAYER, QUESTS, SKILLS, SPELLBOOK;


    public BufferedImage getIcon() {
        return ImageUtil.resizeImage(ImageUtil
                .loadImageResource(getClass(), "/sidePanel/tabs/"+ name().toLowerCase() + ".png"), 18, 18);
    }

    public Method getKeybinding() {
        Method[] methods = keybindsOverlayConfig.class.getMethods();
        Pattern pattern = Pattern.compile(name().toLowerCase());
        for (Method method : methods) {
            Matcher matcher = pattern.matcher(method.getName());
            if (matcher.lookingAt()) {
                return method;
            }
        }
        throw new RuntimeException("Keybinding not implemented.");
    }

}
