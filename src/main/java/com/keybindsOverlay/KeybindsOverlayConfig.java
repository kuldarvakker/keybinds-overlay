package com.keybindsOverlay;

import net.runelite.client.config.*;

import java.awt.event.KeyEvent;

@ConfigGroup("example")
public interface KeybindsOverlayConfig extends Config
{
	@ConfigSection(
			position = 1,
			name = "Overlay",
			description = "Settings for the overlay."

	)
	String keybindsSection = "keybinds";


	@ConfigItem(
			position = 3,
			keyName = "combat",
			name = "Combat",
			description = "",
			section = keybindsSection
	)
	default Keybind combatKey(){ return new Keybind(KeyEvent.VK_F1, 0); }

	@ConfigItem(
			position = 4,
			keyName = "skills",
			name = "Skills",
			description = "",
			section = keybindsSection
	)
	default Keybind skillsKey(){ return new Keybind(KeyEvent.VK_F2, 0); }

	@ConfigItem(
			position = 5,
			keyName = "quests",
			name = "Quests",
			description = "",
			section = keybindsSection
	)
	default Keybind questsKey(){ return new Keybind(KeyEvent.VK_F5, 0); }

	@ConfigItem(
			position = 6,
			keyName = "inventory",
			name = "Inventory",
			description = "",
			section = keybindsSection
	)
	default Keybind inventoryKey(){ return new Keybind(KeyEvent.VK_ESCAPE, 0); }

	@ConfigItem(
			position = 7,
			keyName = "equipment",
			name = "Equipment",
			description = "",
			section = keybindsSection
	)
	default Keybind equipmentKey(){ return new Keybind(KeyEvent.VK_F4, 0); }

	@ConfigItem(
			position = 8,
			keyName = "prayers",
			name = "Prayers",
			description = "",
			section = keybindsSection
	)
	default Keybind prayerKey(){ return new Keybind(KeyEvent.VK_F5, 0); }

	@ConfigItem(
			position = 9,
			keyName = "spellbook",
			name = "Spellbook",
			description = "",
			section = keybindsSection
	)
	default Keybind spellbookKey(){ return new Keybind(KeyEvent.VK_F6, 0); }
}
