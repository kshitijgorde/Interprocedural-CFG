import java.awt.Graphics;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class DigitalCell
{
    private static final int STATE_ALL_OFF = 0;
    private static final int STATE_ALL_ON = 1;
    private static final int STATE_MIX = 2;
    private DigitalLight[] lights;
    private Color lightColour;
    private int left;
    private int top;
    private int xNumLights;
    private int yNumLights;
    private boolean hasBorder;
    private int lightCount;
    private int lightState;
    
    public DigitalCell() {
        this.lights = null;
        this.lightColour = Color.green;
        this.left = 0;
        this.top = 0;
        this.xNumLights = 6;
        this.yNumLights = 8;
        this.hasBorder = false;
        this.lightCount = 0;
        this.lightState = 0;
        this.init();
    }
    
    public DigitalCell(final Color lightColour) {
        this.lights = null;
        this.lightColour = Color.green;
        this.left = 0;
        this.top = 0;
        this.xNumLights = 6;
        this.yNumLights = 8;
        this.hasBorder = false;
        this.lightCount = 0;
        this.lightState = 0;
        this.lightColour = lightColour;
        this.init();
    }
    
    public DigitalCell(final Color lightColour, final int left, final int top) {
        this.lights = null;
        this.lightColour = Color.green;
        this.left = 0;
        this.top = 0;
        this.xNumLights = 6;
        this.yNumLights = 8;
        this.hasBorder = false;
        this.lightCount = 0;
        this.lightState = 0;
        this.lightColour = lightColour;
        this.left = left;
        this.top = top;
        this.init();
    }
    
    public DigitalCell(final Color lightColour, final int left, final int top, final int xNumLights, final int yNumLights) {
        this.lights = null;
        this.lightColour = Color.green;
        this.left = 0;
        this.top = 0;
        this.xNumLights = 6;
        this.yNumLights = 8;
        this.hasBorder = false;
        this.lightCount = 0;
        this.lightState = 0;
        this.lightColour = lightColour;
        this.left = left;
        this.top = top;
        this.xNumLights = xNumLights;
        this.yNumLights = yNumLights;
        this.init();
    }
    
    public void setColour(final Color lightColour) {
        this.lightColour = lightColour;
        for (int i = 0; i < this.lightCount; ++i) {
            this.lights[i].setColour(lightColour);
        }
    }
    
    public Color getColour() {
        return this.lightColour;
    }
    
    public void setOrigin(final int left, final int top) {
        final int xOffset = left - this.left;
        final int yOffset = top - this.top;
        this.left = left;
        this.top = top;
        for (int i = 0; i < this.lightCount; ++i) {
            this.lights[i].setPosition(this.lights[i].getPositionX() + xOffset, this.lights[i].getPositionY() + yOffset);
        }
    }
    
    public void setBorder(final boolean hasBorder) {
        if (!this.hasBorder && hasBorder) {
            for (int i = 0; i < this.lightCount; ++i) {
                this.lights[i].setPosition(this.lights[i].getPositionX() + 2, this.lights[i].getPositionY() + 2);
            }
        }
        if (this.hasBorder && !hasBorder) {
            for (int i = 0; i < this.lightCount; ++i) {
                this.lights[i].setPosition(this.lights[i].getPositionX() - 2, this.lights[i].getPositionY() - 2);
            }
        }
        this.hasBorder = hasBorder;
    }
    
    public boolean getBorder() {
        return this.hasBorder;
    }
    
    public int getLeft() {
        return this.left;
    }
    
    public int getTop() {
        return this.top;
    }
    
    public int getWidth() {
        int borderAddition = -1;
        if (this.hasBorder) {
            borderAddition = 3;
        }
        return this.xNumLights * 3 + borderAddition;
    }
    
    public int getHeight() {
        int borderAddition = -1;
        if (this.hasBorder) {
            borderAddition = 3;
        }
        return this.yNumLights * 3 + borderAddition;
    }
    
    public void lightOn(final int x, final int y) {
        if (this.lightState != 1) {
            this.lightState = 2;
        }
        this.lights[x + y * this.xNumLights].on();
    }
    
    public void lightOff(final int x, final int y) {
        if (this.lightState != 0) {
            this.lightState = 2;
        }
        this.lights[x + y * this.xNumLights].off();
    }
    
    public void setLightPattern(final String[] set) {
        this.lightState = 2;
        if (set.length != this.yNumLights) {
            return;
        }
        for (int y = 0; y < set.length; ++y) {
            if (set[y].length() != this.xNumLights) {
                return;
            }
        }
        for (int y = 0; y < set.length; ++y) {
            for (int x = 0; x < set[y].length(); ++x) {
                if (set[y].charAt(x) == '1') {
                    this.lightOn(x, y);
                }
                else {
                    this.lightOff(x, y);
                }
            }
        }
    }
    
    public boolean allLightsOff() {
        if (this.lightState == 0) {
            return false;
        }
        this.lightState = 0;
        for (int i = 0; i < this.lightCount; ++i) {
            this.lights[i].off();
        }
        return true;
    }
    
    public boolean allLightsOn() {
        if (this.lightState == 1) {
            return false;
        }
        this.lightState = 1;
        for (int i = 0; i < this.lightCount; ++i) {
            this.lights[i].on();
        }
        return true;
    }
    
    private void init() {
        this.lightCount = this.xNumLights * this.yNumLights;
        this.lights = new DigitalLight[this.lightCount];
        for (int i = 0; i < this.lightCount; ++i) {
            final int xLight = i % this.xNumLights;
            final int yLight = i / this.xNumLights;
            this.lights[i] = new DigitalLight(this.lightColour, xLight * 3, yLight * 3);
        }
    }
    
    public void draw(final Graphics gfx, final boolean updateOnly) {
        for (int i = 0; i < this.lightCount; ++i) {
            this.lights[i].draw(gfx, updateOnly);
        }
        if (this.hasBorder && !updateOnly) {
            final int width = this.xNumLights * 3 + 2;
            final int height = this.yNumLights * 3 + 2;
            gfx.setColor(Color.darkGray);
            gfx.drawRect(this.left, this.top, this.getWidth() - 1, this.getHeight() - 1);
        }
    }
}
