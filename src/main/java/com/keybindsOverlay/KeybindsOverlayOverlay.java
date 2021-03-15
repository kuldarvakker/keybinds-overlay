package com.keybindsOverlay;

import net.runelite.client.config.Keybind;
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

        addTabToPanel(sidePanelTabs.COMBAT);
        addTabToPanel(sidePanelTabs.SKILLS);
        addTabToPanel(sidePanelTabs.QUESTS);
        addTabToPanel(sidePanelTabs.INVENTORY);
        addTabToPanel(sidePanelTabs.EQUIPMENT);
        addTabToPanel(sidePanelTabs.PRAYER);
        addTabToPanel(sidePanelTabs.SPELLBOOK);

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

    private void addTabToPanel(sidePanelTabs tab)
    {
        addIcon(tab.getIcon());
        addKeybinding(tab.getKeybinding());
    }

    private void addKeybinding(Keybind keybind)
    {
        addLine(String.valueOf(keybind));
    }

}
