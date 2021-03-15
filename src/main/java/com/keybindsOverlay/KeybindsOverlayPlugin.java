package com.keybindsOverlay;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

@Slf4j
@PluginDescriptor(
	name = "Keybinds Overlay"
)
public class KeybindsOverlayPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private KeybindsOverlayConfig config;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private KeybindsOverlayOverlay overlay;

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

	@Provides
	KeybindsOverlayConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(KeybindsOverlayConfig.class);
	}
}
