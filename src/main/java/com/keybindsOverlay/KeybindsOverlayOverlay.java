package com.keybindsOverlay;

import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayPriority;
import net.runelite.client.ui.overlay.components.ComponentOrientation;
import net.runelite.client.ui.overlay.components.ImageComponent;
import net.runelite.client.ui.overlay.components.LineComponent;
import net.runelite.client.ui.overlay.components.PanelComponent;

import javax.inject.Inject;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.InvocationTargetException;

public class KeybindsOverlayOverlay extends Overlay {

    private final KeybindsOverlayConfig config;
    private final PanelComponent panelComponent = new PanelComponent();

    @Inject
    private KeybindsOverlayOverlay(KeybindsOverlayConfig config)
    {
        setPosition(OverlayPosition.ABOVE_CHATBOX_RIGHT);
        setPriority(OverlayPriority.HIGH);
        this.config = config;

    }


    @Override
    public Dimension render(Graphics2D graphics) {

        panelComponent.getChildren().clear();

        panelComponent.setOrientation(ComponentOrientation.HORIZONTAL);
        panelComponent.setGap(new Point(20, 0));

        addKeybinding(sidePanelTabs.COMBAT);
        addKeybinding(sidePanelTabs.SKILLS);
        addKeybinding(sidePanelTabs.QUESTS);
        addKeybinding(sidePanelTabs.INVENTORY);
        addKeybinding(sidePanelTabs.EQUIPMENT);
        addKeybinding(sidePanelTabs.PRAYER);
        addKeybinding(sidePanelTabs.SPELLBOOK);

        return panelComponent.render(graphics);
    }

    private void addLine(String text)
    {
        panelComponent.getChildren().add(LineComponent.builder()
                .right(text.substring(0, Math.min(3, text.length())).toUpperCase())
                .rightColor(Color.PINK)
                .build());
    }

    private void addIcon(BufferedImage icon)
    {
        panelComponent.getChildren()
                .add(new ImageComponent(icon));
    }

    private void addKeybinding(sidePanelTabs tab)
    {
        addIcon(tab.getIcon());
        try {
            addLine(String.valueOf(tab.getKeybinding().invoke(config)));
        } catch (IllegalAccessException | InvocationTargetException e) {
            addLine("");
        }
    }

}
