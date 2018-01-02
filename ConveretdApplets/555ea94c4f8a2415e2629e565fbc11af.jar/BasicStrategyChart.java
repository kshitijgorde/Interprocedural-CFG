import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Point;
import java.awt.Component;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.event.WindowListener;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.Frame;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

public class BasicStrategyChart extends Dialog
{
    BJOptions bjOptions;
    boolean DoubleOn10_or_11_only;
    boolean DoubleAfterSplit;
    boolean SurrenderAllowed;
    boolean MultiDeck;
    private static final String[] headingString;
    private static final int headingStartLeft = 60;
    private static final int underlineTop = 3;
    private static final int underlineLength = 250;
    private static final int vertLineLeft = 45;
    private static final int hardTotalsLeft = 60;
    private static final int hardTotalsTop = 50;
    private static final int lineSeperation = 20;
    private static final int columnSeperation = 25;
    private static final int softTotalsLeft = 60;
    private static final int softTotalsTop = 280;
    private static final int pairsLeft = 440;
    private static final int pairsTop = 50;
    boolean fComponentsAdjusted;
    
    public BasicStrategyChart(final Frame frame, final String s) {
        super(frame, s);
        this.fComponentsAdjusted = false;
        this.setLayout(null);
        this.setBackground(new Color(0, 128, 0));
        this.setSize(756, 462);
        this.setVisible(false);
        this.addWindowListener(new SymWindow());
    }
    
    public BasicStrategyChart(final Frame frame, final String s, final BJOptions bjOptions, final boolean b, final boolean b2, final boolean b3, final boolean b4) {
        this(frame, s);
        (this.bjOptions = bjOptions).setTempOptions(b, b2, b3, b4);
    }
    
    private void drawHeading(final Graphics graphics, final int n, final int n2) {
        graphics.drawString("Dealer Upcard--->", n - 48, n2);
        for (int i = 0; i <= 9; ++i) {
            graphics.drawString(BasicStrategyChart.headingString[i], n + i * 25 + 60, n2);
        }
        graphics.drawLine(n, n2 + 3, n + 250 + 60, n2 + 3);
        graphics.drawString("Player", n - 48, n2 + 55);
    }
    
    public void paint(final Graphics graphics) {
        if (graphics == null) {
            return;
        }
        super.paint(graphics);
        this.paintHardTotalsTable(graphics);
        this.paintSoftTotalsTable(graphics);
        this.paintPairsTable(graphics);
    }
    
    private void paintHardTotalsTable(final Graphics graphics) {
        final int n = 60 + this.getInsets().left;
        final int n2 = 50 + this.getInsets().top;
        this.drawHeading(graphics, n, n2);
        graphics.drawString("Hard Totals", n - 48, n2 + 75);
        graphics.drawLine(n + 45, n2 + 3, n + 45, n2 + 3 + 160);
        for (int i = 9; i <= 16; ++i) {
            graphics.drawString(String.valueOf(i), n + 45 - 25, n2 + (i - 8) * 20);
            for (int j = 2; j <= 11; ++j) {
                graphics.drawString(this.bjOptions.getTempBasicStrategy(j, i, false, false), n + 60 + 25 * (j - 2), n2 + (i - 8) * 20);
            }
        }
        graphics.drawString("Always Hit 8 or less", n + 45, n2 + 180);
        graphics.drawString("Always Stand on Hard 17 or above", n + 45, n2 + 200);
    }
    
    private void paintSoftTotalsTable(final Graphics graphics) {
        final int n = 60 + this.getInsets().left;
        final int n2 = 280 + this.getInsets().top;
        this.drawHeading(graphics, n, n2);
        graphics.drawString("Soft Totals", n - 48, n2 + 75);
        graphics.drawLine(n + 45, n2 + 3, n + 45, n2 + 3 + 140);
        for (int i = 13; i <= 19; ++i) {
            graphics.drawString("A" + String.valueOf(i - 11), n + 45 - 25, n2 + (i - 12) * 20);
            for (int j = 2; j <= 11; ++j) {
                graphics.drawString(this.bjOptions.getTempBasicStrategy(j, i, true, false), n + 60 + 25 * (j - 2), n2 + (i - 12) * 20);
            }
        }
        graphics.drawString("Always Stand on Soft 20 or above", n + 45, n2 + 160);
    }
    
    private void paintPairsTable(final Graphics graphics) {
        final int n = 440 + this.getInsets().left;
        final int n2 = 50 + this.getInsets().top;
        this.drawHeading(graphics, n, n2);
        graphics.drawString("Pairs", n - 48, n2 + 75);
        graphics.drawLine(n + 45, n2 + 3, n + 45, n2 + 3 + 200);
        for (int i = 1; i <= 10; ++i) {
            String value = String.valueOf(i);
            if (i == 1) {
                value = "A";
            }
            if (i == 10) {
                value = "T";
            }
            graphics.drawString(String.valueOf(value) + value, n + 45 - 25, n2 + i * 20);
            for (int j = 2; j <= 11; ++j) {
                String s;
                if (i == 1) {
                    s = this.bjOptions.getTempBasicStrategy(j, 12, true, true);
                }
                else {
                    s = this.bjOptions.getTempBasicStrategy(j, i * 2, false, true);
                }
                graphics.drawString(s, n + 60 + 25 * (j - 2), n2 + i * 20);
            }
        }
        graphics.drawString("Y=Split", n + 45, n2 + 220);
        graphics.drawString("N=Don't Split", n + 45, n2 + 240);
        graphics.drawString("H=Hit", n + 45, n2 + 260);
        graphics.drawString("S=Stand", n + 45, n2 + 280);
        graphics.drawString("DH=Double if possible, otherwise Hit", n + 45, n2 + 300);
        graphics.drawString("DS=Double if possible, otherwise Stand", n + 45, n2 + 320);
        graphics.drawString("XH=Surrender if possible, otherwise Hit", n + 45, n2 + 340);
    }
    
    public void setVisible(final boolean visible) {
        if (visible) {
            final Rectangle bounds = this.getParent().getBounds();
            final Rectangle bounds2 = this.getBounds();
            this.setLocation(bounds.x + (bounds.width - bounds2.width) / 2, bounds.y + (bounds.height - bounds2.height) / 2);
        }
        super.setVisible(visible);
    }
    
    public void addNotify() {
        final Dimension size = this.getSize();
        super.addNotify();
        if (this.fComponentsAdjusted) {
            return;
        }
        final Insets insets = this.getInsets();
        this.setSize(insets.left + insets.right + size.width, insets.top + insets.bottom + size.height);
        final Component[] components = this.getComponents();
        for (int i = 0; i < components.length; ++i) {
            final Point location = components[i].getLocation();
            location.translate(insets.left, insets.top);
            components[i].setLocation(location);
        }
        this.fComponentsAdjusted = true;
    }
    
    void HelpFrame_WindowClosing(final WindowEvent windowEvent) {
        this.setVisible(false);
    }
    
    static {
        headingString = new String[] { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Ace" };
    }
    
    class SymWindow extends WindowAdapter
    {
        public void windowClosing(final WindowEvent windowEvent) {
            if (windowEvent.getSource() == BasicStrategyChart.this) {
                BasicStrategyChart.this.HelpFrame_WindowClosing(windowEvent);
            }
        }
    }
}
