package com.keybindsOverlay;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class KeybindsOverlayTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(keybindsOverlayPlugin.class);
		RuneLite.main(args);
	}
}