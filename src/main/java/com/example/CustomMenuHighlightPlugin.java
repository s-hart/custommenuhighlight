package net.runelite.client.plugins.custommenuhighlight;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

@Slf4j
@PluginDescriptor(
    name = "Custom Menu Highlight",
    description = "Highlights the hovered menu option",
    tags = {"menu", "highlight", "overlay", "custom"}
)
public class CustomMenuHighlightPlugin extends Plugin
{
    @Inject
    private OverlayManager overlayManager;

    @Inject
    private CustomMenuHighlightOverlay overlay;

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
    CustomMenuHighlightConfig provideConfig(ConfigManager configManager)
    {
        return configManager.getConfig(CustomMenuHighlightConfig.class);
    }
}
