import java.awt.Graphics;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class SheikLine
{
    public int x1;
    public int y1;
    public int x2;
    public int y2;
    private Color colour;
    boolean bolBrighter;
    static final double COLOURFACTOR = 0.93;
    static final int UPPERCOLOUR = 240;
    static final int LOWERCOLOUR = 64;
    
    public Color getColour() {
        return this.colour;
    }
    
    public void changeColour() {
        Color c1 = this.colour;
        Color c2 = Color.white;
        if (c1.getRed() > c1.getGreen() && c1.getRed() > c1.getBlue()) {
            if (c1.getRed() > 240) {
                this.bolBrighter = false;
            }
            else if (c1.getRed() < 64) {
                c1 = new Color(0, 64, 0);
                this.bolBrighter = true;
            }
            if (this.bolBrighter) {
                c2 = ColorUtils.brighter(c1, 0.93);
            }
            else {
                c2 = ColorUtils.darker(c1, 0.93);
            }
        }
        else if (c1.getGreen() > c1.getRed() && c1.getGreen() > c1.getBlue()) {
            if (c1.getGreen() > 240) {
                this.bolBrighter = false;
            }
            else if (this.colour.getGreen() < 64) {
                c1 = new Color(0, 0, 64);
                this.bolBrighter = true;
            }
            if (this.bolBrighter) {
                c2 = ColorUtils.brighter(c1, 0.93);
            }
            else {
                c2 = ColorUtils.darker(c1, 0.93);
            }
        }
        else if (c1.getBlue() > c1.getRed() && c1.getBlue() > c1.getGreen()) {
            if (c1.getBlue() > 240) {
                this.bolBrighter = false;
            }
            else if (c1.getBlue() < 64) {
                c1 = new Color(64, 0, 0);
                this.bolBrighter = true;
            }
            if (this.bolBrighter) {
                c2 = ColorUtils.brighter(c1, 0.93);
            }
            else {
                c2 = ColorUtils.darker(c1, 0.93);
            }
        }
        else {
            c1 = ColorUtils.brighter(c1, 0.93);
            if (c1.getBlue() > 200) {
                c2 = new Color(64, 64, 64);
            }
        }
        this.colour = c2;
    }
    
    public void draw(final Graphics g) {
        g.setColor(this.colour);
        g.drawLine(this.x1, this.y1, this.x2, this.y2);
    }
    
    public SheikLine() {
        this.bolBrighter = true;
        this.x1 = 0;
        this.y2 = 0;
        this.x2 = 0;
        this.y2 = 0;
    }
    
    public SheikLine(final int x1, final int y1, final int x2, final int y2, final Color c) {
        this.bolBrighter = true;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.colour = c;
    }
}
