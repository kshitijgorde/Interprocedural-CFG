import java.awt.Graphics;
import java.util.StringTokenizer;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class SevenSegElm extends ChipElm
{
    Color darkred;
    
    public SevenSegElm(final int n, final int n2) {
        super(n, n2);
    }
    
    public SevenSegElm(final int n, final int n2, final int n3, final int n4, final int n5, final StringTokenizer stringTokenizer) {
        super(n, n2, n3, n4, n5, stringTokenizer);
    }
    
    String getChipName() {
        return "7-segment driver/display";
    }
    
    void setupPins() {
        this.darkred = new Color(30, 0, 0);
        this.sizeX = 4;
        this.sizeY = 4;
        (this.pins = new Pin[7])[0] = new Pin(this, 0, 2, "a");
        this.pins[1] = new Pin(this, 1, 2, "b");
        this.pins[2] = new Pin(this, 2, 2, "c");
        this.pins[3] = new Pin(this, 3, 2, "d");
        this.pins[4] = new Pin(this, 1, 1, "e");
        this.pins[5] = new Pin(this, 2, 1, "f");
        this.pins[6] = new Pin(this, 3, 1, "g");
    }
    
    void draw(final Graphics graphics) {
        this.drawChip(graphics);
        graphics.setColor(Color.red);
        final int n = this.x + this.cspc * 5;
        final int n2 = this.y + this.cspc;
        this.setColor(graphics, 0);
        CircuitElm.drawThickLine(graphics, n, n2, n + this.cspc, n2);
        this.setColor(graphics, 1);
        CircuitElm.drawThickLine(graphics, n + this.cspc, n2, n + this.cspc, n2 + this.cspc);
        this.setColor(graphics, 2);
        CircuitElm.drawThickLine(graphics, n + this.cspc, n2 + this.cspc, n + this.cspc, n2 + this.cspc2);
        this.setColor(graphics, 3);
        CircuitElm.drawThickLine(graphics, n, n2 + this.cspc2, n + this.cspc, n2 + this.cspc2);
        this.setColor(graphics, 4);
        CircuitElm.drawThickLine(graphics, n, n2 + this.cspc, n, n2 + this.cspc2);
        this.setColor(graphics, 5);
        CircuitElm.drawThickLine(graphics, n, n2, n, n2 + this.cspc);
        this.setColor(graphics, 6);
        CircuitElm.drawThickLine(graphics, n, n2 + this.cspc, n + this.cspc, n2 + this.cspc);
    }
    
    void setColor(final Graphics graphics, final int n) {
        graphics.setColor(this.pins[n].value ? Color.red : (SevenSegElm.sim.printableCheckItem.getState() ? Color.white : this.darkred));
    }
    
    int getPostCount() {
        return 7;
    }
    
    int getVoltageSourceCount() {
        return 0;
    }
    
    int getDumpType() {
        return 157;
    }
}
