// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.editorpanel;

import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Component;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Panel;

public class ToolTip extends Panel implements Runnable
{
    private static Font font;
    private static FontMetrics metrics;
    private Component parent;
    private String curText;
    private Color bgColor;
    private static int HORIZ_MARGIN;
    private static int VERT_MARGIN;
    private Thread delayThread;
    private Component tipOwner;
    
    public ToolTip(final Component parent) {
        this.parent = parent;
        this.bgColor = new Color(255, 255, 224);
        if (parent == null) {
            System.out.println("Null parent not allowed in ToolTip() constructor!");
        }
    }
    
    public void setFont(final Font font) {
        ToolTip.font = font;
    }
    
    public Font getFont() {
        return ToolTip.font;
    }
    
    public void setText(final String curText) {
        this.curText = curText;
    }
    
    public String getText() {
        return this.curText;
    }
    
    public void showTip(final Component tipOwner, int max, int max2) {
        if (ToolTip.metrics == null && this.parent != null) {
            ToolTip.metrics = this.parent.getGraphics().getFontMetrics(ToolTip.font);
        }
        this.tipOwner = tipOwner;
        int stringWidth;
        int height;
        if (ToolTip.metrics != null) {
            stringWidth = ToolTip.metrics.stringWidth(this.curText);
            height = ToolTip.metrics.getHeight();
        }
        else {
            stringWidth = (height = 0);
        }
        final int n = stringWidth + 2 * ToolTip.HORIZ_MARGIN;
        final int n2 = height + 2 * ToolTip.VERT_MARGIN;
        Component parent;
        for (parent = this.parent; parent != null && !(parent instanceof ToolHost); parent = parent.getParent()) {}
        if (parent != null && parent instanceof ToolHost) {
            this.parent = parent;
        }
        if (this.parent != null) {
            final Dimension size = this.parent.size();
            if (max2 + n2 > size.height) {
                max2 = Math.max(size.height - n2, 0);
            }
            if (max + n > size.width) {
                max = Math.max(size.width - n, 0);
            }
        }
        this.reshape(max, max2, n, n2);
        if (this.isShowing()) {
            this.repaint();
        }
        else {
            (this.delayThread = new Thread(this)).start();
        }
    }
    
    public void hideTip(final Component component) {
        if (component == this.tipOwner) {
            this.hide();
            if (this.delayThread != null) {
                this.delayThread.stop();
            }
        }
    }
    
    public void run() {
        try {
            final Thread delayThread = this.delayThread;
            Thread.sleep(500L);
            this.show();
            this.delayThread = null;
        }
        catch (InterruptedException ex) {}
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.size();
        graphics.setFont(ToolTip.font);
        graphics.setColor(this.bgColor);
        graphics.fillRect(0, 0, size.width, size.height);
        graphics.setColor(Color.black);
        graphics.drawRect(0, 0, size.width - 1, size.height - 1);
        graphics.drawString(this.curText, ToolTip.HORIZ_MARGIN, ToolTip.metrics.getAscent() + ToolTip.VERT_MARGIN);
    }
    
    static {
        ToolTip.font = new Font("Helvetica", 0, 12);
        ToolTip.HORIZ_MARGIN = 5;
        ToolTip.VERT_MARGIN = 3;
    }
}
