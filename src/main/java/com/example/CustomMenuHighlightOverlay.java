package net.runelite.client.plugins.custommenuhighlight;

import net.runelite.api.Client;
import net.runelite.api.Point;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayPriority;

import javax.inject.Inject;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class CustomMenuHighlightOverlay extends Overlay {
    private final Client client;
    private final CustomMenuHighlightConfig config;

    @Inject
    public CustomMenuHighlightOverlay(Client client, CustomMenuHighlightConfig config) {
        this.client = client;
        this.config = config;
        setPosition(OverlayPosition.DYNAMIC);
        setPriority(OverlayPriority.HIGH);
    }

    @Override
    public Dimension render(Graphics2D graphics) {
        if (!client.isMenuOpen()) {
            return null;
        }

        Point mousePos = client.getMouseCanvasPosition();
        int mouseX = mousePos.getX();
        int mouseY = mousePos.getY();

        final int menuX = client.getMenuX();
        final int menuY = client.getMenuY();
        final int entryHeight = 20;
        final int menuWidth = client.getMenuWidth();
        final int menuCount = client.getMenuEntries().length;

        int hoveredIndex = (mouseY - menuY) / entryHeight;
        if (hoveredIndex < 0 || hoveredIndex >= menuCount) {
            return null;
        }

        int highlightY = menuY + (hoveredIndex * entryHeight);
        Rectangle highlight = new Rectangle(menuX, highlightY, menuWidth, entryHeight);

        Color base = config.getHighlightColor();
        Color fill = new Color(base.getRed(), base.getGreen(), base.getBlue(), config.getHighlightAlpha());

        graphics.setColor(fill);
        graphics.fill(highlight);
        graphics.setColor(base.darker());
        graphics.draw(highlight);

        return null;
    }
}
