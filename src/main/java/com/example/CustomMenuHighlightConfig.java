package net.runelite.client.plugins.custommenuhighlight;

import java.awt.Color;
import net.runelite.client.config.Alpha;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("custommenuhighlight")
public interface CustomMenuHighlightConfig extends Config {
	@Alpha
	@ConfigItem(keyName = "highlightColor", name = "Highlight Color", description = "Color of the hovered menu option")
	default Color getHighlightColor() {
		return new Color(255, 255, 0); // Yellow
	}

	@ConfigItem(keyName = "highlightAlpha", name = "Highlight Transparency", description = "Alpha (0-255) of highlight")
	default int getHighlightAlpha() {
		return 100;
	}
}
