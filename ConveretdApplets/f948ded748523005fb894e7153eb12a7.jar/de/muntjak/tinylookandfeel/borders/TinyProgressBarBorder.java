// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel.borders;

import de.muntjak.tinylookandfeel.controlpanel.DrawRoutines;
import java.awt.Color;
import de.muntjak.tinylookandfeel.Theme;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Insets;
import javax.swing.plaf.UIResource;
import javax.swing.border.AbstractBorder;

public class TinyProgressBarBorder extends AbstractBorder implements UIResource
{
    protected static final Insets INSETS_YQ;
    protected static final Insets INSETS_99;
    
    public void paintBorder(final Component component, final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        switch (Theme.derivedStyle[Theme.style]) {
            case 0: {
                this.drawTinyBorder(component, graphics, n, n2, n3, n4);
                break;
            }
            case 1: {
                this.drawWinBorder(component, graphics, n, n2, n3, n4);
                break;
            }
            case 2: {
                this.drawXpBorder(component, graphics, n, n2, n3, n4);
                break;
            }
        }
    }
    
    private void drawTinyBorder(final Component component, final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
    }
    
    private void drawWinBorder(final Component component, final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        graphics.setColor(Theme.progressDarkColor[Theme.style].getColor());
        graphics.drawLine(n, n2, n + n3 - 1, n2);
        graphics.drawLine(n, 1, n, n2 + n4 - 1);
        graphics.setColor(Theme.progressLightColor[Theme.style].getColor());
        graphics.drawLine(n + n3 - 1, n2 + 1, n + n3 - 1, n2 + n4 - 1);
        graphics.drawLine(n + 1, n2 + n4 - 1, n + n3 - 1, n2 + n4 - 1);
    }
    
    private void drawXpBorder(final Component component, final Graphics graphics, int n, int n2, int n3, int n4) {
        DrawRoutines.drawProgressBarBorder(graphics, Theme.progressBorderColor[Theme.style].getColor(), n, n2, n3, n4);
        DrawRoutines.drawProgressBarBorder(graphics, Theme.progressDarkColor[Theme.style].getColor(), n + 1, n2 + 1, n3 - 2, n4 - 2);
        n3 -= 4;
        n4 -= 4;
        n += 2;
        n2 += 2;
        graphics.setColor(Theme.progressLightColor[Theme.style].getColor());
        graphics.drawLine(n + 1, n2, n + n3 - 2, n2);
        graphics.drawLine(n, n2 + 1, n, n2 + n4 - 2);
        graphics.setColor(Theme.progressTrackColor[Theme.style].getColor());
        graphics.drawLine(n + 1, n2 + n4 - 1, n + n3 - 2, n2 + n4 - 1);
        graphics.drawLine(n + n3 - 1, n2 + 1, n + n3 - 1, n2 + n4 - 2);
    }
    
    public Insets getBorderInsets(final Component component) {
        if (Theme.derivedStyle[Theme.style] == 2) {
            return TinyProgressBarBorder.INSETS_YQ;
        }
        return TinyProgressBarBorder.INSETS_99;
    }
    
    static {
        INSETS_YQ = new Insets(3, 3, 3, 3);
        INSETS_99 = new Insets(2, 2, 2, 2);
    }
}
