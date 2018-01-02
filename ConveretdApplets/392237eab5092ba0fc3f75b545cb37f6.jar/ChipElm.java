import java.awt.Checkbox;
import java.awt.Point;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.StringTokenizer;

// 
// Decompiled by Procyon v0.5.30
// 

abstract class ChipElm extends CircuitElm
{
    int csize;
    int cspc;
    int cspc2;
    int bits;
    final int FLAG_SMALL = 1;
    final int FLAG_FLIP_X = 1024;
    final int FLAG_FLIP_Y = 2048;
    int[] rectPointsX;
    int[] rectPointsY;
    int[] clockPointsX;
    int[] clockPointsY;
    Pin[] pins;
    int sizeX;
    int sizeY;
    boolean lastClock;
    final int SIDE_N = 0;
    final int SIDE_S = 1;
    final int SIDE_W = 2;
    final int SIDE_E = 3;
    
    public ChipElm(final int n, final int n2) {
        super(n, n2);
        if (this.needsBits()) {
            this.bits = ((this instanceof DecadeElm) ? 10 : 4);
        }
        this.noDiagonal = true;
        this.setupPins();
        this.setSize(ChipElm.sim.smallGridCheckItem.getState() ? 1 : 2);
    }
    
    public ChipElm(final int n, final int n2, final int n3, final int n4, final int n5, final StringTokenizer stringTokenizer) {
        super(n, n2, n3, n4, n5);
        if (this.needsBits()) {
            this.bits = new Integer(stringTokenizer.nextToken());
        }
        this.noDiagonal = true;
        this.setupPins();
        this.setSize(((n5 & 0x1) != 0x0) ? 1 : 2);
        for (int i = 0; i != this.getPostCount(); ++i) {
            if (this.pins[i].state) {
                this.volts[i] = new Double(stringTokenizer.nextToken());
                this.pins[i].value = (this.volts[i] > 2.5);
            }
        }
    }
    
    boolean needsBits() {
        return false;
    }
    
    void setSize(final int csize) {
        this.csize = csize;
        this.cspc = 8 * csize;
        this.cspc2 = this.cspc * 2;
        this.flags &= 0xFFFFFFFE;
        this.flags |= ((csize == 1) ? 1 : 0);
    }
    
    abstract void setupPins();
    
    void draw(final Graphics graphics) {
        this.drawChip(graphics);
    }
    
    void drawChip(final Graphics graphics) {
        graphics.setFont(new Font("SansSerif", 0, 10 * this.csize));
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        for (int i = 0; i != this.getPostCount(); ++i) {
            final Pin pin = this.pins[i];
            this.setVoltageColor(graphics, this.volts[i]);
            final Point post = pin.post;
            final Point stub = pin.stub;
            CircuitElm.drawThickLine(graphics, post, stub);
            this.drawDots(graphics, stub, post, pin.curcount = this.updateDotCount(pin.current, pin.curcount));
            if (pin.bubble) {
                graphics.setColor(ChipElm.sim.printableCheckItem.getState() ? Color.white : Color.black);
                CircuitElm.drawThickCircle(graphics, pin.bubbleX, pin.bubbleY, 1);
                graphics.setColor(ChipElm.lightGrayColor);
                CircuitElm.drawThickCircle(graphics, pin.bubbleX, pin.bubbleY, 3);
            }
            graphics.setColor(ChipElm.whiteColor);
            final int stringWidth = fontMetrics.stringWidth(pin.text);
            graphics.drawString(pin.text, pin.textloc.x - stringWidth / 2, pin.textloc.y + fontMetrics.getAscent() / 2);
            if (pin.lineOver) {
                final int n = pin.textloc.y - fontMetrics.getAscent() / 2;
                graphics.drawLine(pin.textloc.x - stringWidth / 2, n, pin.textloc.x + stringWidth / 2, n);
            }
        }
        graphics.setColor(this.needsHighlight() ? ChipElm.selectColor : ChipElm.lightGrayColor);
        CircuitElm.drawThickPolygon(graphics, this.rectPointsX, this.rectPointsY, 4);
        if (this.clockPointsX != null) {
            graphics.drawPolyline(this.clockPointsX, this.clockPointsY, 3);
        }
        for (int j = 0; j != this.getPostCount(); ++j) {
            this.drawPost(graphics, this.pins[j].post.x, this.pins[j].post.y, this.nodes[j]);
        }
    }
    
    void drag(int x, int n) {
        n = ChipElm.sim.snapGrid(n);
        if (x < this.x) {
            x = this.x;
            n = this.y;
        }
        else {
            final int n2 = n;
            this.y2 = n2;
            this.y = n2;
            this.x2 = ChipElm.sim.snapGrid(x);
        }
        this.setPoints();
    }
    
    void setPoints() {
        if (this.x2 - this.x > this.sizeX * this.cspc2 && this == ChipElm.sim.dragElm) {
            this.setSize(2);
        }
        final int cspc = this.cspc;
        final int n = this.x + this.cspc2;
        final int y = this.y;
        final int n2 = n - this.cspc;
        final int n3 = y - this.cspc;
        final int n4 = this.sizeX * this.cspc2;
        final int n5 = this.sizeY * this.cspc2;
        this.rectPointsX = new int[] { n2, n2 + n4, n2 + n4, n2 };
        this.rectPointsY = new int[] { n3, n3, n3 + n5, n3 + n5 };
        this.setBbox(n2, n3, this.rectPointsX[2], this.rectPointsY[2]);
        for (int i = 0; i != this.getPostCount(); ++i) {
            final Pin pin = this.pins[i];
            switch (pin.side) {
                case 0: {
                    pin.setPoint(n, y, 1, 0, 0, -1, 0, 0);
                    break;
                }
                case 1: {
                    pin.setPoint(n, y, 1, 0, 0, 1, 0, n5 - this.cspc2);
                    break;
                }
                case 2: {
                    pin.setPoint(n, y, 0, 1, -1, 0, 0, 0);
                    break;
                }
                case 3: {
                    pin.setPoint(n, y, 0, 1, 1, 0, n4 - this.cspc2, 0);
                    break;
                }
            }
        }
    }
    
    Point getPost(final int n) {
        return this.pins[n].post;
    }
    
    abstract int getVoltageSourceCount();
    
    void setVoltageSource(int n, final int voltSource) {
        for (int i = 0; i != this.getPostCount(); ++i) {
            final Pin pin = this.pins[i];
            if (pin.output && n-- == 0) {
                pin.voltSource = voltSource;
                return;
            }
        }
        System.out.println("setVoltageSource failed for " + this);
    }
    
    void stamp() {
        for (int i = 0; i != this.getPostCount(); ++i) {
            final Pin pin = this.pins[i];
            if (pin.output) {
                ChipElm.sim.stampVoltageSource(0, this.nodes[i], pin.voltSource);
            }
        }
    }
    
    void execute() {
    }
    
    void doStep() {
        for (int i = 0; i != this.getPostCount(); ++i) {
            final Pin pin = this.pins[i];
            if (!pin.output) {
                pin.value = (this.volts[i] > 2.5);
            }
        }
        this.execute();
        for (int j = 0; j != this.getPostCount(); ++j) {
            final Pin pin2 = this.pins[j];
            if (pin2.output) {
                ChipElm.sim.updateVoltageSource(0, this.nodes[j], pin2.voltSource, pin2.value ? 5.0 : 0.0);
            }
        }
    }
    
    void reset() {
        for (int i = 0; i != this.getPostCount(); ++i) {
            this.pins[i].value = false;
            this.pins[i].curcount = 0.0;
            this.volts[i] = 0.0;
        }
        this.lastClock = false;
    }
    
    String dump() {
        this.getDumpType();
        String s = super.dump();
        if (this.needsBits()) {
            s = s + " " + this.bits;
        }
        for (int i = 0; i != this.getPostCount(); ++i) {
            if (this.pins[i].state) {
                s = s + " " + this.volts[i];
            }
        }
        return s;
    }
    
    void getInfo(final String[] array) {
        array[0] = this.getChipName();
        int n = 1;
        for (int i = 0; i != this.getPostCount(); ++i) {
            final Pin pin = this.pins[i];
            if (array[n] != null) {
                final StringBuilder sb = new StringBuilder();
                final int n2 = n;
                array[n2] = sb.append(array[n2]).append("; ").toString();
            }
            else {
                array[n] = "";
            }
            String s = pin.text;
            if (pin.lineOver) {
                s += '\'';
            }
            if (pin.clock) {
                s = "Clk";
            }
            final StringBuilder sb2 = new StringBuilder();
            final int n3 = n;
            array[n3] = sb2.append(array[n3]).append(s).append(" = ").append(CircuitElm.getVoltageText(this.volts[i])).toString();
            if (i % 2 == 1) {
                ++n;
            }
        }
    }
    
    void setCurrent(final int n, final double current) {
        for (int i = 0; i != this.getPostCount(); ++i) {
            if (this.pins[i].output && this.pins[i].voltSource == n) {
                this.pins[i].current = current;
            }
        }
    }
    
    String getChipName() {
        return "chip";
    }
    
    boolean getConnection(final int n, final int n2) {
        return false;
    }
    
    boolean hasGroundConnection(final int n) {
        return this.pins[n].output;
    }
    
    public EditInfo getEditInfo(final int n) {
        if (n == 0) {
            final EditInfo editInfo = new EditInfo("", 0.0, -1.0, -1.0);
            editInfo.checkbox = new Checkbox("Flip X", (this.flags & 0x400) != 0x0);
            return editInfo;
        }
        if (n == 1) {
            final EditInfo editInfo2 = new EditInfo("", 0.0, -1.0, -1.0);
            editInfo2.checkbox = new Checkbox("Flip Y", (this.flags & 0x800) != 0x0);
            return editInfo2;
        }
        return null;
    }
    
    public void setEditValue(final int n, final EditInfo editInfo) {
        if (n == 0) {
            if (editInfo.checkbox.getState()) {
                this.flags |= 0x400;
            }
            else {
                this.flags &= 0xFFFFFBFF;
            }
            this.setPoints();
        }
        if (n == 1) {
            if (editInfo.checkbox.getState()) {
                this.flags |= 0x800;
            }
            else {
                this.flags &= 0xFFFFF7FF;
            }
            this.setPoints();
        }
    }
    
    class Pin
    {
        Point post;
        Point stub;
        Point textloc;
        int pos;
        int side;
        int voltSource;
        int bubbleX;
        int bubbleY;
        String text;
        boolean lineOver;
        boolean bubble;
        boolean clock;
        boolean output;
        boolean value;
        boolean state;
        double curcount;
        double current;
        
        Pin(final int pos, final int side, final String text) {
            this.pos = pos;
            this.side = side;
            this.text = text;
        }
        
        void setPoint(int n, int n2, int n3, int n4, int n5, int n6, int n7, int n8) {
            if ((ChipElm.this.flags & 0x400) != 0x0) {
                n3 = -n3;
                n5 = -n5;
                n += ChipElm.this.cspc2 * (ChipElm.this.sizeX - 1);
                n7 = -n7;
            }
            if ((ChipElm.this.flags & 0x800) != 0x0) {
                n4 = -n4;
                n6 = -n6;
                n2 += ChipElm.this.cspc2 * (ChipElm.this.sizeY - 1);
                n8 = -n8;
            }
            final int n9 = n + ChipElm.this.cspc2 * n3 * this.pos + n7;
            final int n10 = n2 + ChipElm.this.cspc2 * n4 * this.pos + n8;
            this.post = new Point(n9 + n5 * ChipElm.this.cspc2, n10 + n6 * ChipElm.this.cspc2);
            this.stub = new Point(n9 + n5 * ChipElm.this.cspc, n10 + n6 * ChipElm.this.cspc);
            this.textloc = new Point(n9, n10);
            if (this.bubble) {
                this.bubbleX = n9 + n5 * 10 * ChipElm.this.csize;
                this.bubbleY = n10 + n6 * 10 * ChipElm.this.csize;
            }
            if (this.clock) {
                ChipElm.this.clockPointsX = new int[3];
                ChipElm.this.clockPointsY = new int[3];
                ChipElm.this.clockPointsX[0] = n9 + n5 * ChipElm.this.cspc - n3 * ChipElm.this.cspc / 2;
                ChipElm.this.clockPointsY[0] = n10 + n6 * ChipElm.this.cspc - n4 * ChipElm.this.cspc / 2;
                ChipElm.this.clockPointsX[1] = n9;
                ChipElm.this.clockPointsY[1] = n10;
                ChipElm.this.clockPointsX[2] = n9 + n5 * ChipElm.this.cspc + n3 * ChipElm.this.cspc / 2;
                ChipElm.this.clockPointsY[2] = n10 + n6 * ChipElm.this.cspc + n4 * ChipElm.this.cspc / 2;
            }
        }
    }
}
