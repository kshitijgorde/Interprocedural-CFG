// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Panel;

public class IrisStyledPanel extends Panel
{
    private Insets margins;
    static String NO_BORDER_STR;
    static String LINE_BORDER_STR;
    static String SHADOW_BORDER_STR;
    static final int NO_BORDER = 0;
    static final int LINE_BORDER = 1;
    static final int SHADOW_BORDER = 2;
    static int BORDER_WIDTH;
    static int SHADOW_WIDTH;
    private int panelStyle;
    
    public IrisStyledPanel() {
        this.margins = new Insets(0, 0, 0, 0);
        this.panelStyle = 2;
    }
    
    public void setPanelStyle(final String s) {
        if (s != null) {
            if (s.equalsIgnoreCase(IrisStyledPanel.LINE_BORDER_STR)) {
                this.panelStyle = 1;
            }
            else if (s.equalsIgnoreCase(IrisStyledPanel.SHADOW_BORDER_STR)) {
                this.panelStyle = 2;
            }
            else if (s.equalsIgnoreCase(IrisStyledPanel.NO_BORDER_STR)) {
                this.panelStyle = 0;
            }
        }
        int n = 0;
        int shadow_WIDTH = 0;
        switch (this.panelStyle) {
            case 1: {
                n = IrisStyledPanel.BORDER_WIDTH;
                break;
            }
            case 2: {
                n = IrisStyledPanel.BORDER_WIDTH;
                shadow_WIDTH = IrisStyledPanel.SHADOW_WIDTH;
                break;
            }
        }
        final Insets margins = this.margins;
        final Insets margins2 = this.margins;
        final int n2 = n;
        margins2.left = n2;
        margins.top = n2;
        final Insets margins3 = this.margins;
        final Insets margins4 = this.margins;
        final int n3 = n + shadow_WIDTH;
        margins4.right = n3;
        margins3.bottom = n3;
    }
    
    public String getPanelStyle() {
        switch (this.panelStyle) {
            case 1: {
                return IrisStyledPanel.LINE_BORDER_STR;
            }
            case 2: {
                return IrisStyledPanel.SHADOW_BORDER_STR;
            }
            default: {
                return IrisStyledPanel.NO_BORDER_STR;
            }
        }
    }
    
    public Insets insets() {
        return this.margins;
    }
    
    public void paint(final Graphics graphics) {
        if (this.panelStyle == 0) {
            return;
        }
        final int height = this.size().height;
        final int width = this.size().width;
        for (int i = 0; i < IrisStyledPanel.BORDER_WIDTH; ++i) {
            graphics.drawRect(i, i, width - 2 * i - 1, height - 2 * i - 1);
        }
        final int n = this.margins.bottom - 1;
        if (this.panelStyle == 2) {
            final int blue = Color.darkGray.getBlue();
            final int green = Color.darkGray.getGreen();
            final int red = Color.darkGray.getRed();
            final Color background = this.getParent().getBackground();
            final int blue2 = background.getBlue();
            final int green2 = background.getGreen();
            final int red2 = background.getRed();
            final int n2 = this.margins.bottom - 1;
            if (n2 == 0) {
                return;
            }
            final int n3 = (red2 - red) / n2;
            final int n4 = (green2 - green) / n2;
            final int n5 = (blue2 - blue) / n2;
            for (int j = n; j >= 1; --j) {
                graphics.setColor(new Color(red2 - n3 * (j - 1), green2 - n4 * (j - 1), blue2 - n5 * (j - 1)));
                graphics.drawLine(0, height - j, width - 1, height - j);
                graphics.drawLine(width - j, 0, width - j, height - 1);
                graphics.setColor(background);
                graphics.drawLine(0, height - j, n - j, height - j);
                graphics.drawLine(width - j, 0, width - j, n - j);
            }
        }
    }
    
    static {
        IrisStyledPanel.NO_BORDER_STR = "NO_BORDER";
        IrisStyledPanel.LINE_BORDER_STR = "LINE_BORDER";
        IrisStyledPanel.SHADOW_BORDER_STR = "SHADOW_BORDER";
        IrisStyledPanel.BORDER_WIDTH = 1;
        IrisStyledPanel.SHADOW_WIDTH = 6;
    }
}
