import java.awt.Checkbox;
import java.awt.Graphics;
import java.util.StringTokenizer;
import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

class Switch2Elm extends SwitchElm
{
    int link;
    static final int FLAG_CENTER_OFF = 1;
    final int openhs = 16;
    Point[] swposts;
    Point[] swpoles;
    
    public Switch2Elm(final int n, final int n2) {
        super(n, n2, false);
        this.noDiagonal = true;
    }
    
    Switch2Elm(final int n, final int n2, final boolean b) {
        super(n, n2, b);
        this.noDiagonal = true;
    }
    
    public Switch2Elm(final int n, final int n2, final int n3, final int n4, final int n5, final StringTokenizer stringTokenizer) {
        super(n, n2, n3, n4, n5, stringTokenizer);
        this.link = new Integer(stringTokenizer.nextToken());
        this.noDiagonal = true;
    }
    
    int getDumpType() {
        return 83;
    }
    
    String dump() {
        return super.dump() + " " + this.link;
    }
    
    void setPoints() {
        super.setPoints();
        this.calcLeads(32);
        this.swposts = this.newPointArray(2);
        this.swpoles = this.newPointArray(3);
        this.interpPoint2(this.lead1, this.lead2, this.swpoles[0], this.swpoles[1], 1.0, 16.0);
        this.swpoles[2] = this.lead2;
        this.interpPoint2(this.point1, this.point2, this.swposts[0], this.swposts[1], 1.0, 16.0);
        this.posCount = (this.hasCenterOff() ? 3 : 2);
    }
    
    void draw(final Graphics graphics) {
        this.setBbox(this.point1, this.point2, 16.0);
        this.setVoltageColor(graphics, this.volts[0]);
        CircuitElm.drawThickLine(graphics, this.point1, this.lead1);
        this.setVoltageColor(graphics, this.volts[1]);
        CircuitElm.drawThickLine(graphics, this.swpoles[0], this.swposts[0]);
        this.setVoltageColor(graphics, this.volts[2]);
        CircuitElm.drawThickLine(graphics, this.swpoles[1], this.swposts[1]);
        if (!this.needsHighlight()) {
            graphics.setColor(Switch2Elm.whiteColor);
        }
        CircuitElm.drawThickLine(graphics, this.lead1, this.swpoles[this.position]);
        this.updateDotCount();
        this.drawDots(graphics, this.point1, this.lead1, this.curcount);
        if (this.position != 2) {
            this.drawDots(graphics, this.swpoles[this.position], this.swposts[this.position], this.curcount);
        }
        this.drawPosts(graphics);
    }
    
    Point getPost(final int n) {
        return (n == 0) ? this.point1 : this.swposts[n - 1];
    }
    
    int getPostCount() {
        return 3;
    }
    
    void calculateCurrent() {
        if (this.position == 2) {
            this.current = 0.0;
        }
    }
    
    void stamp() {
        if (this.position == 2) {
            return;
        }
        Switch2Elm.sim.stampVoltageSource(this.nodes[0], this.nodes[this.position + 1], this.voltSource, 0.0);
    }
    
    int getVoltageSourceCount() {
        return (this.position != 2) ? 1 : 0;
    }
    
    void toggle() {
        super.toggle();
        if (this.link != 0) {
            for (int i = 0; i != Switch2Elm.sim.elmList.size(); ++i) {
                final Object element = Switch2Elm.sim.elmList.elementAt(i);
                if (element instanceof Switch2Elm) {
                    final Switch2Elm switch2Elm = (Switch2Elm)element;
                    if (switch2Elm.link == this.link) {
                        switch2Elm.position = this.position;
                    }
                }
            }
        }
    }
    
    boolean getConnection(final int n, final int n2) {
        return this.position != 2 && this.comparePair(n, n2, 0, 1 + this.position);
    }
    
    void getInfo(final String[] array) {
        array[0] = ((this.link == 0) ? "switch (SPDT)" : "switch (DPDT)");
        array[1] = "I = " + CircuitElm.getCurrentDText(this.getCurrent());
    }
    
    public EditInfo getEditInfo(final int n) {
        if (n == 1) {
            final EditInfo editInfo = new EditInfo("", 0.0, -1.0, -1.0);
            editInfo.checkbox = new Checkbox("Center Off", this.hasCenterOff());
            return editInfo;
        }
        return super.getEditInfo(n);
    }
    
    public void setEditValue(final int n, final EditInfo editInfo) {
        if (n == 1) {
            this.flags &= 0xFFFFFFFE;
            if (editInfo.checkbox.getState()) {
                this.flags |= 0x1;
            }
            if (this.hasCenterOff()) {
                this.momentary = false;
            }
            this.setPoints();
        }
        else {
            super.setEditValue(n, editInfo);
        }
    }
    
    boolean hasCenterOff() {
        return (this.flags & 0x1) != 0x0;
    }
}
