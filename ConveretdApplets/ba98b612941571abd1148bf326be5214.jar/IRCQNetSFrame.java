import java.awt.Dimension;
import java.util.StringTokenizer;
import java.awt.Event;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.Vector;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class IRCQNetSFrame extends Canvas
{
    private boolean upSideDown;
    private int Mwidth;
    private int Mheight;
    private String Label;
    private String Topic;
    private IRCQNet theApp;
    private Vector Rect;
    private Rectangle tRect;
    private boolean xPressed;
    
    public IRCQNetSFrame(final IRCQNet theApp) {
        this.upSideDown = false;
        this.Rect = new Vector(2);
        this.xPressed = false;
        this.theApp = theApp;
        this.setBackground(Color.blue.darker());
    }
    
    public void reshape(final int n, final int n2, final int mwidth, final int mheight) {
        super.reshape(n, n2, mwidth, mheight);
        this.Mwidth = mwidth;
        this.Mheight = mheight;
    }
    
    public void ChangeOrder(final boolean upSideDown) {
        this.upSideDown = upSideDown;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (!this.isVisible()) {
            return;
        }
        String label;
        if (this.Label.equalsIgnoreCase("!CHANNEL!")) {
            label = "Rooms";
        }
        else {
            label = this.Label;
        }
        graphics.setColor(Color.blue.darker());
        graphics.fillRect(0, 0, this.Mwidth, this.Mheight);
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        graphics.setColor(Color.white);
        final int n = this.Mwidth - 2;
        final int n2 = n - 15;
        final int n3 = this.Mheight - 3;
        final int n4 = n3 - 13;
        graphics.setColor(IRCQNetColors.controlColor);
        graphics.fillRect(n2, n4, 16, 14);
        this.Rect.addElement(new Rectangle(n2, n4, 16, 14));
        this.tRect = new Rectangle(n2, n4, 16, 14);
        graphics.setColor(new Color(255, 255, 255));
        graphics.drawLine(n2, n4, n, n4);
        graphics.drawLine(n2, n4, n2, n3);
        graphics.setColor(Color.black);
        graphics.drawLine(n2, n3, n, n3);
        graphics.drawLine(n, n4, n, n3);
        graphics.setColor(IRCQNetColors.controlColor.darker());
        graphics.drawLine(n2 + 1, n3 - 1, n - 1, n3 - 1);
        graphics.drawLine(n - 1, n4 + 1, n - 1, n3 - 1);
        graphics.setColor(new Color(230, 230, 230));
        graphics.drawLine(n2 + 1, n4 + 1, n - 2, n4 + 1);
        graphics.drawLine(n2 + 1, n4 + 1, n2 + 1, n3 - 2);
        final int n5 = (n - n2) / 2 + n2 - 3;
        final int n6 = (n - n2) / 2 + n2 + 3;
        final int n7 = (n3 - n4) / 2 + n4 - 3;
        final int n8 = (n3 - n4) / 2 + n4 + 3;
        graphics.setColor(Color.black);
        graphics.drawLine(n5, n7, n6, n8);
        graphics.drawLine(n5 + 1, n7, n6 + 1, n8);
        graphics.drawLine(n5, n8, n6, n7);
        graphics.drawLine(n5 + 1, n8, n6 + 1, n7);
        final int n9 = this.Mwidth - 21 - 15;
        final int n10 = this.Mheight - 3 - 13;
        if (label != null) {
            if (this.upSideDown) {
                graphics.setColor(Color.white);
                if (this.Topic != null) {
                    graphics.drawString(this.Topic + " - " + label, 4, fontMetrics.getHeight() - 1);
                    return;
                }
                graphics.drawString(label, 4, fontMetrics.getHeight());
            }
            else {
                graphics.setColor(Color.white);
                if (this.Topic != null) {
                    graphics.drawString(label + " - " + this.Topic, 4, fontMetrics.getHeight() - 1);
                    return;
                }
                graphics.drawString(label, 4, fontMetrics.getHeight());
            }
        }
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.xPressed || this.tRect == null) {
            return true;
        }
        if (this.tRect.inside(n, n2)) {
            this.xPressed = true;
            if (this.Label.equalsIgnoreCase("!CHANNEL!")) {
                this.theApp.MPanel.DelListCard();
            }
            else if (this.Label.startsWith("#")) {
                this.postEvent(new Event(this, 10008, this.Label + ";/part " + this.Label));
            }
            else {
                this.theApp.MPanel.DelPrivChatCard(this.Label);
            }
        }
        return super.mouseUp(event, n, n2);
    }
    
    public void setLabel(final String label) {
        this.Label = label;
        this.repaint();
    }
    
    public void setTopic(final String s) {
        new StringTokenizer(s);
        String string = "";
        int i = 0;
    Label_0188_Outer:
        while (i < s.length()) {
            while (true) {
                switch (s.charAt(i)) {
                    case '\u0003': {
                        try {
                            if (!Character.isDigit(s.charAt(i + 1))) {
                                break Label_0188;
                            }
                            ++i;
                            if (Character.isDigit(s.charAt(i + 1))) {
                                ++i;
                            }
                            if (s.substring(i + 1, i + 2).indexOf(",") != -1) {
                                ++i;
                                try {
                                    if (Character.isDigit(s.charAt(i + 1))) {
                                        ++i;
                                        if (Character.isDigit(s.charAt(i + 1))) {
                                            ++i;
                                        }
                                    }
                                }
                                catch (NumberFormatException ex) {}
                            }
                            break Label_0188;
                        }
                        catch (NumberFormatException ex2) {
                            break Label_0188;
                        }
                        break;
                    }
                    case '\u0001':
                    case '\u0002':
                    case '\u001f': {
                        ++i;
                        continue Label_0188_Outer;
                    }
                }
                string += s.charAt(i);
                continue;
            }
        }
        this.Topic = string;
        this.repaint();
    }
    
    public Dimension preferredSize() {
        return new Dimension(2048, 18);
    }
    
    public Dimension minimumSize() {
        return new Dimension(2048, 18);
    }
}
