import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class PayChart extends Panel
{
    private static final int NumPaylines = 13;
    private static final int DescriptionXOffset = 25;
    private static final int DescriptionYOffset = 31;
    private static final int PayColumnWidth = 45;
    private static final int PayColumnXOffset = 175;
    private static final int PayColumnYOffset = 31;
    private static final int PaylineSeperation = 13;
    private static final int HeadingXOffset = 175;
    private static final int HeadingYOffset = 16;
    private static final int PayColumnHighlightXOffset = 170;
    private static final int PayColumnHighlightYOffset = 20;
    private static final int PaylineHighlightXOffset = 20;
    private static final int PaylineHighlightYOffset = 33;
    private static final int PaylineHighlightHeight = 13;
    private static Color payColumnHighlightColor;
    private static Color paylineHighlightColor;
    private static int maxColumns;
    private int highlightedPayline;
    private int highlightedPayColumn;
    private VideoPokerGame game;
    
    public void highlightPayline(final int highlightedPayline) {
        if (highlightedPayline >= 0) {
            this.drawPayline(this.getGraphics(), highlightedPayline, PayChart.paylineHighlightColor);
            this.highlightedPayline = highlightedPayline;
        }
    }
    
    public void deHighlightPayline() {
        if (this.highlightedPayline != -1) {
            this.drawPayline(this.getGraphics(), this.highlightedPayline, this.getBackground());
            this.highlightedPayline = -1;
            if (this.highlightedPayColumn != -1) {
                this.drawPayColumn(this.getGraphics(), this.highlightedPayColumn, PayChart.payColumnHighlightColor);
            }
        }
    }
    
    public void highlightPayColumn(final int highlightedPayColumn) {
        if (highlightedPayColumn >= 0) {
            this.drawPayColumn(this.getGraphics(), highlightedPayColumn, PayChart.payColumnHighlightColor);
            this.highlightedPayColumn = highlightedPayColumn;
        }
    }
    
    public void deHighlightPayColumn() {
        if (this.highlightedPayColumn != -1) {
            this.drawPayColumn(this.getGraphics(), this.highlightedPayColumn, this.getBackground());
            this.highlightedPayColumn = -1;
        }
    }
    
    public void paint(final Graphics graphics) {
        super.paint(graphics);
        for (int i = 1; i <= PayChart.maxColumns; ++i) {
            graphics.drawString("Coin " + i, 175 + 45 * (i - 1) + this.getInsets().left, 16 + this.getInsets().top);
        }
        for (int j = 0; j < this.game.getNumPayoffLines(); ++j) {
            graphics.drawString(this.game.getDescription(j), 25 + this.getInsets().left, 31 + j * 13 + this.getInsets().top);
        }
        for (int k = 1; k <= PayChart.maxColumns; ++k) {
            if (k != this.highlightedPayColumn) {
                this.drawPayColumn(graphics, k, this.getBackground());
            }
            else {
                this.drawPayColumn(graphics, k, PayChart.payColumnHighlightColor);
            }
        }
        if (this.highlightedPayline != -1) {
            this.drawPayline(graphics, this.highlightedPayline, PayChart.paylineHighlightColor);
        }
    }
    
    private void drawPayline(final Graphics graphics, final int n, final Color color) {
        graphics.setColor(color);
        graphics.fillRect(20 + this.getInsets().left, 33 + (n - 1) * 13 + this.getInsets().top, 150 + PayChart.maxColumns * 45, 13);
        graphics.setColor(Color.black);
        graphics.drawString(this.game.getDescription(n), 25 + this.getInsets().left, 31 + n * 13 + this.getInsets().top);
        for (int i = 1; i <= PayChart.maxColumns; ++i) {
            this.drawPayoff(graphics, n, i);
        }
    }
    
    private void drawPayoff(final Graphics graphics, final int n, final int n2) {
        graphics.drawString(String.valueOf(this.game.getPayoff(n, n2)), 175 + 45 * (n2 - 1) + this.getInsets().left, 31 + n * 13 + this.getInsets().top);
    }
    
    private void drawPayColumn(final Graphics graphics, final int n, final Color color) {
        graphics.setColor(color);
        final int numPayoffLines = this.game.getNumPayoffLines();
        graphics.fillRect(170 + 45 * (n - 1) + this.getInsets().left, 20 + this.getInsets().top, 45, numPayoffLines * 13);
        graphics.setColor(Color.black);
        for (int i = 0; i < numPayoffLines; ++i) {
            this.drawPayoff(graphics, i, n);
        }
    }
    
    public PayChart() {
        this.highlightedPayline = -1;
        this.highlightedPayColumn = -1;
        this.setLayout(null);
        final Insets insets = this.getInsets();
        this.setSize(insets.left + insets.right + 430, insets.top + insets.bottom + 270);
    }
    
    public PayChart(final VideoPokerGame game) {
        this.highlightedPayline = -1;
        this.highlightedPayColumn = -1;
        this.game = game;
    }
    
    static {
        PayChart.payColumnHighlightColor = Color.red;
        PayChart.paylineHighlightColor = Color.lightGray;
        PayChart.maxColumns = 5;
    }
}
