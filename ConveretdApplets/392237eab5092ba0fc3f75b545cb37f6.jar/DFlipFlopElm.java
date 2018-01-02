import java.awt.Checkbox;
import java.util.StringTokenizer;

// 
// Decompiled by Procyon v0.5.30
// 

class DFlipFlopElm extends ChipElm
{
    final int FLAG_RESET = 2;
    
    boolean hasReset() {
        return (this.flags & 0x2) != 0x0;
    }
    
    public DFlipFlopElm(final int n, final int n2) {
        super(n, n2);
    }
    
    public DFlipFlopElm(final int n, final int n2, final int n3, final int n4, final int n5, final StringTokenizer stringTokenizer) {
        super(n, n2, n3, n4, n5, stringTokenizer);
        this.pins[2].value = !this.pins[1].value;
    }
    
    String getChipName() {
        return "D flip-flop";
    }
    
    void setupPins() {
        this.sizeX = 2;
        this.sizeY = 3;
        (this.pins = new Pin[this.getPostCount()])[0] = new Pin(this, 0, 2, "D");
        this.pins[1] = new Pin(this, 0, 3, "Q");
        final Pin pin = this.pins[1];
        final Pin pin2 = this.pins[1];
        final boolean b = true;
        pin2.state = b;
        pin.output = b;
        this.pins[2] = new Pin(this, 2, 3, "Q");
        this.pins[2].output = true;
        this.pins[2].lineOver = true;
        this.pins[3] = new Pin(this, 1, 2, "");
        this.pins[3].clock = true;
        if (this.hasReset()) {
            this.pins[4] = new Pin(this, 2, 2, "R");
        }
    }
    
    int getPostCount() {
        return this.hasReset() ? 5 : 4;
    }
    
    int getVoltageSourceCount() {
        return 2;
    }
    
    void execute() {
        if (this.pins[3].value && !this.lastClock) {
            this.pins[1].value = this.pins[0].value;
            this.pins[2].value = !this.pins[0].value;
        }
        if (this.pins.length > 4 && this.pins[4].value) {
            this.pins[1].value = false;
            this.pins[2].value = true;
        }
        this.lastClock = this.pins[3].value;
    }
    
    int getDumpType() {
        return 155;
    }
    
    public EditInfo getEditInfo(final int n) {
        if (n == 2) {
            final EditInfo editInfo = new EditInfo("", 0.0, -1.0, -1.0);
            editInfo.checkbox = new Checkbox("Reset Pin", this.hasReset());
            return editInfo;
        }
        return super.getEditInfo(n);
    }
    
    public void setEditValue(final int n, final EditInfo editInfo) {
        if (n == 2) {
            if (editInfo.checkbox.getState()) {
                this.flags |= 0x2;
            }
            else {
                this.flags &= 0xFFFFFFFD;
            }
            this.setupPins();
            this.allocNodes();
            this.setPoints();
        }
        super.setEditValue(n, editInfo);
    }
}
