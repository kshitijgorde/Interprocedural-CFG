import java.awt.Event;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class colsel_canvas extends Canvas
{
    static final int COLOURWIDTH = 10;
    static final int COLOURHEIGHT = 10;
    static final int GRIDWIDTH = 12;
    static final int GRIDHEIGHT = 18;
    static final int DISPLAYWIDTH = 120;
    static final int DISPLAYHEIGHT = 230;
    private int currIndex;
    private Color currColour;
    private int prevIndex;
    private Color prevColour;
    private draw_panel target;
    
    colsel_canvas(final draw_panel target) {
        this.currIndex = 6;
        this.currColour = new Color(0, 0, 0);
        this.prevIndex = 0;
        this.prevColour = new Color(255, 255, 255);
        this.target = target;
        this.resize(120, 230);
    }
    
    public Dimension preferredSize() {
        return new Dimension(120, 230);
    }
    
    public Dimension minimumSize() {
        return new Dimension(120, 230);
    }
    
    public void paint(final Graphics graphics) {
        for (int i = 0; i < 19; ++i) {
            for (int j = 0; j < 12; ++j) {
                graphics.setColor(this.target.col_array[this.coordsToIndex(j, i)]);
                graphics.fillRect(j * 10, i * 10, 10, 10);
            }
        }
        graphics.setColor(Color.black);
        graphics.drawLine(106, 196, 113, 203);
        graphics.drawLine(106, 196, 109, 196);
        graphics.drawLine(106, 196, 106, 199);
        graphics.drawLine(113, 203, 113, 200);
        graphics.drawLine(113, 203, 110, 203);
        this.drawCurrColour(this.target.current_colour, this.currIndex);
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 501: {
                if (event.x >= 105 && event.x < 115 && event.y >= 195 && event.y < 205) {
                    this.drawCurrColour(this.prevColour, this.prevIndex);
                    return true;
                }
                if (event.x >= 0 && event.x < 120 && event.y >= 0 && event.y < 190) {
                    final int coordsToIndex = this.coordsToIndex(event.x / 10, event.y / 10);
                    this.drawCurrColour(this.target.col_array[coordsToIndex], coordsToIndex);
                    return true;
                }
                break;
            }
        }
        return false;
    }
    
    public int coordsToIndex(final int n, int n2) {
        if (n2 == 0) {
            return n;
        }
        --n2;
        final int n3 = 6 * (2 + n2 % 6 + 6 * (n / 6 + 2 * (n2 / 6)));
        if (n < 6) {
            return n3 + n;
        }
        return n3 + (11 - n);
    }
    
    public void drawCurrColour(final Color color, final int currIndex) {
        final int prevIndex = this.prevIndex;
        if (currIndex != this.currIndex) {
            this.prevColour = this.target.current_colour;
            this.prevIndex = this.currIndex;
            this.currIndex = currIndex;
            this.target.set_current_colour(color);
        }
        final Graphics graphics = this.getGraphics();
        graphics.setColor(this.prevColour);
        graphics.fillRect(15, 205, 100, 20);
        graphics.setColor(this.target.current_colour);
        graphics.fillRect(5, 195, 100, 20);
        for (int i = 0; i < 19; ++i) {
            for (int j = 0; j < 12; ++j) {
                final int coordsToIndex = this.coordsToIndex(j, i);
                if (this.currIndex == coordsToIndex || this.prevIndex == coordsToIndex || prevIndex == coordsToIndex) {
                    graphics.setColor(this.target.col_array[this.coordsToIndex(j, i)]);
                    graphics.fillRect(j * 10, i * 10, 10, 10);
                    graphics.setColor(this.target.col_array[6]);
                    graphics.setXORMode(this.target.col_array[0]);
                    if (this.currIndex == coordsToIndex) {
                        graphics.fillOval(j * 10 + 1, i * 10 + 1, 8, 8);
                    }
                    else if (this.prevIndex == coordsToIndex) {
                        graphics.fillOval(j * 10 + 3, i * 10 + 3, 4, 4);
                    }
                    graphics.setPaintMode();
                }
            }
        }
        graphics.dispose();
    }
}
