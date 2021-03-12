package com.keybindsOverlay;

import com.google.inject.Provides;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

import javax.inject.Inject;

@PluginDescriptor(
        name = "Keybinds overlay",
        description = "It is easier to learn keybinds, when they are at your sight.",
        tags = {"keybinds", "overlay"}
)

public class keybindsOverlayPlugin extends Plugin {

    @Inject
    private OverlayManager overlayManager;

    @Inject
    private keybindsOverlayOverlay overlay;

    @Provides
    keybindsOverlayConfig provideConfig(ConfigManager configManager)
    {
        return configManager.getConfig(keybindsOverlayConfig.class);
    }

    @Override
    protected void startUp()
    {
        overlayManager.add(overlay);

    }

    @Override
    protected void shutDown()
    {
        overlayManager.remove(overlay);
    }
}
