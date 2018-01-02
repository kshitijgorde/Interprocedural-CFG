import java.awt.Graphics;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class DigitalLight
{
    private boolean onLastDraw;
    private Color onColour;
    private Color dimColour;
    private Color dimHighlight;
    private int xPos;
    private int yPos;
    private boolean on;
    
    public DigitalLight() {
        this.onLastDraw = false;
        this.onColour = null;
        this.dimColour = null;
        this.dimHighlight = null;
        this.xPos = -1;
        this.yPos = -1;
        this.on = false;
        this.init();
    }
    
    public DigitalLight(final Color colour) {
        this.onLastDraw = false;
        this.onColour = null;
        this.dimColour = null;
        this.dimHighlight = null;
        this.xPos = -1;
        this.yPos = -1;
        this.on = false;
        this.onColour = colour;
        this.init();
    }
    
    public DigitalLight(final Color colour, final int xPos, final int yPos) {
        this.onLastDraw = false;
        this.onColour = null;
        this.dimColour = null;
        this.dimHighlight = null;
        this.xPos = -1;
        this.yPos = -1;
        this.on = false;
        this.onColour = colour;
        this.xPos = xPos;
        this.yPos = yPos;
        this.init();
    }
    
    public DigitalLight(final int xPos, final int yPos) {
        this.onLastDraw = false;
        this.onColour = null;
        this.dimColour = null;
        this.dimHighlight = null;
        this.xPos = -1;
        this.yPos = -1;
        this.on = false;
        this.xPos = xPos;
        this.yPos = yPos;
        this.init();
    }
    
    private void init() {
        if (this.onColour == null) {
            this.onColour = Color.green;
        }
        this.dimColour = Color.darkGray;
        this.dimHighlight = this.dimColour.brighter();
    }
    
    public void setColour(final Color colour) {
        this.onColour = colour;
    }
    
    public Color getColour() {
        return this.onColour;
    }
    
    public int getPositionX() {
        return this.xPos;
    }
    
    public int getPositionY() {
        return this.yPos;
    }
    
    public void setPosition(final int xPos, final int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }
    
    public void on() {
        this.on = true;
    }
    
    public void off() {
        this.on = false;
    }
    
    public void draw(final Graphics gfx, final boolean updateOnly) {
        if (updateOnly && this.onLastDraw == this.on) {
            return;
        }
        gfx.setColor(this.on ? this.onColour : this.dimColour);
        gfx.fillRect(this.xPos, this.yPos, 2, 2);
        if (!this.on) {
            gfx.setColor(this.dimHighlight);
            gfx.fillRect(this.xPos, this.yPos, 1, 1);
        }
        this.onLastDraw = this.on;
    }
    
    public void draw(final Graphics gfx, final int xPos, final int yPos) {
    }
}
