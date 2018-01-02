import java.awt.event.ItemEvent;
import java.awt.image.ImageProducer;
import java.util.StringTokenizer;
import java.awt.PopupMenu;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.MemoryImageSource;
import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

class Scope
{
    final int FLAG_YELM = 32;
    static final int VAL_POWER = 1;
    static final int VAL_IB = 1;
    static final int VAL_IC = 2;
    static final int VAL_IE = 3;
    static final int VAL_VBE = 4;
    static final int VAL_VBC = 5;
    static final int VAL_VCE = 6;
    static final int VAL_R = 2;
    double[] minV;
    double[] maxV;
    double minMaxV;
    double[] minI;
    double[] maxI;
    double minMaxI;
    int scopePointCount;
    int ptr;
    int ctr;
    int speed;
    int position;
    int value;
    int ivalue;
    String text;
    Rectangle rect;
    boolean showI;
    boolean showV;
    boolean showMax;
    boolean showMin;
    boolean showFreq;
    boolean lockScale;
    boolean plot2d;
    boolean plotXY;
    CircuitElm elm;
    CircuitElm xElm;
    CircuitElm yElm;
    MemoryImageSource imageSource;
    Image image;
    int[] pixels;
    int draw_ox;
    int draw_oy;
    float[] dpixels;
    CirSim sim;
    
    Scope(final CirSim sim) {
        this.scopePointCount = 128;
        this.rect = new Rectangle();
        this.reset();
        this.sim = sim;
    }
    
    void showCurrent(final boolean showI) {
        this.showI = showI;
        final boolean b = false;
        this.ivalue = (b ? 1 : 0);
        this.value = (b ? 1 : 0);
    }
    
    void showVoltage(final boolean showV) {
        this.showV = showV;
        final boolean b = false;
        this.ivalue = (b ? 1 : 0);
        this.value = (b ? 1 : 0);
    }
    
    void showMax(final boolean showMax) {
        this.showMax = showMax;
    }
    
    void showMin(final boolean showMin) {
        this.showMin = showMin;
    }
    
    void showFreq(final boolean showFreq) {
        this.showFreq = showFreq;
    }
    
    void setLockScale(final boolean lockScale) {
        this.lockScale = lockScale;
    }
    
    void resetGraph() {
        this.scopePointCount = 1;
        while (this.scopePointCount <= this.rect.width) {
            this.scopePointCount *= 2;
        }
        this.minV = new double[this.scopePointCount];
        this.maxV = new double[this.scopePointCount];
        this.minI = new double[this.scopePointCount];
        this.maxI = new double[this.scopePointCount];
        final boolean b = false;
        this.ctr = (b ? 1 : 0);
        this.ptr = (b ? 1 : 0);
        this.allocImage();
    }
    
    boolean active() {
        return this.elm != null;
    }
    
    void reset() {
        this.resetGraph();
        this.minMaxV = 5.0;
        this.minMaxI = 0.1;
        this.speed = 64;
        final boolean showI = true;
        this.showMax = showI;
        this.showV = showI;
        this.showI = showI;
        final boolean showFreq = false;
        this.showMin = showFreq;
        this.lockScale = showFreq;
        this.showFreq = showFreq;
        this.plot2d = false;
        if (this.elm != null && (this.elm instanceof OutputElm || this.elm instanceof LogicOutputElm || this.elm instanceof ProbeElm)) {
            this.showI = false;
        }
        final boolean b = false;
        this.ivalue = (b ? 1 : 0);
        this.value = (b ? 1 : 0);
        if (this.elm instanceof TransistorElm) {
            this.value = 6;
        }
    }
    
    void setRect(final Rectangle rect) {
        this.rect = rect;
        this.resetGraph();
    }
    
    int getWidth() {
        return this.rect.width;
    }
    
    int rightEdge() {
        return this.rect.x + this.rect.width;
    }
    
    void setElm(final CircuitElm elm) {
        this.elm = elm;
        this.reset();
    }
    
    void timeStep() {
        if (this.elm == null) {
            return;
        }
        final double scopeValue = this.elm.getScopeValue(this.value);
        if (scopeValue < this.minV[this.ptr]) {
            this.minV[this.ptr] = scopeValue;
        }
        if (scopeValue > this.maxV[this.ptr]) {
            this.maxV[this.ptr] = scopeValue;
        }
        double n = 0.0;
        if (this.value == 0 || this.ivalue != 0) {
            n = ((this.ivalue == 0) ? this.elm.getCurrent() : this.elm.getScopeValue(this.ivalue));
            if (n < this.minI[this.ptr]) {
                this.minI[this.ptr] = n;
            }
            if (n > this.maxI[this.ptr]) {
                this.maxI[this.ptr] = n;
            }
        }
        if (this.plot2d && this.dpixels != null) {
            boolean b = false;
            while (scopeValue > this.minMaxV || scopeValue < -this.minMaxV) {
                this.minMaxV *= 2.0;
                b = true;
            }
            double n2 = n;
            if (this.plotXY) {
                n2 = ((this.yElm == null) ? 0.0 : this.yElm.getVoltageDiff());
            }
            while (n2 > this.minMaxI || n2 < -this.minMaxI) {
                this.minMaxI *= 2.0;
                b = true;
            }
            if (b) {
                this.clear2dView();
            }
            this.drawTo((int)(this.rect.width * (1.0 + scopeValue / this.minMaxV) * 0.499), (int)(this.rect.height * (1.0 - n2 / this.minMaxI) * 0.499));
        }
        else {
            ++this.ctr;
            if (this.ctr >= this.speed) {
                this.ptr = (this.ptr + 1 & this.scopePointCount - 1);
                this.minV[this.ptr] = (this.maxV[this.ptr] = scopeValue);
                this.minI[this.ptr] = (this.maxI[this.ptr] = n);
                this.ctr = 0;
            }
        }
    }
    
    void drawTo(final int n, final int n2) {
        if (this.draw_ox == -1) {
            this.draw_ox = n;
            this.draw_oy = n2;
        }
        if (this.draw_ox == n && this.draw_oy == n2) {
            this.dpixels[n + this.rect.width * n2] = 1.0f;
        }
        else if (CircuitElm.abs(n2 - this.draw_oy) > CircuitElm.abs(n - this.draw_ox)) {
            final double n3 = CircuitElm.sign(n2 - this.draw_oy);
            for (int draw_oy = this.draw_oy; draw_oy != n2 + n3; draw_oy += (int)n3) {
                this.dpixels[this.draw_ox + (n - this.draw_ox) * (draw_oy - this.draw_oy) / (n2 - this.draw_oy) + this.rect.width * draw_oy] = 1.0f;
            }
        }
        else {
            final double n4 = CircuitElm.sign(n - this.draw_ox);
            for (int draw_ox = this.draw_ox; draw_ox != n + n4; draw_ox += (int)n4) {
                this.dpixels[draw_ox + this.rect.width * (this.draw_oy + (n2 - this.draw_oy) * (draw_ox - this.draw_ox) / (n - this.draw_ox))] = 1.0f;
            }
        }
        this.draw_ox = n;
        this.draw_oy = n2;
    }
    
    void clear2dView() {
        for (int i = 0; i != this.dpixels.length; ++i) {
            this.dpixels[i] = 0.0f;
        }
        final int n = -1;
        this.draw_oy = n;
        this.draw_ox = n;
    }
    
    void adjustScale(final double n) {
        this.minMaxV *= n;
        this.minMaxI *= n;
    }
    
    void draw2d(final Graphics graphics) {
        if (this.pixels == null || this.dpixels == null) {
            return;
        }
        final int n = this.sim.printableCheckItem.getState() ? -1 : 0;
        for (int i = 0; i != this.pixels.length; ++i) {
            this.pixels[i] = n;
        }
        for (int j = 0; j != this.rect.width; ++j) {
            this.pixels[j + this.rect.width * (this.rect.height / 2)] = -16711936;
        }
        final int n2 = this.plotXY ? -16711936 : -256;
        for (int k = 0; k != this.rect.height; ++k) {
            this.pixels[this.rect.width / 2 + this.rect.width * k] = n2;
        }
        for (int l = 0; l != this.pixels.length; ++l) {
            final int n3 = (int)(255.0f * this.dpixels[l]);
            if (n3 > 0) {
                this.pixels[l] = (0xFF000000 | 65793 * n3);
            }
            final float[] dpixels = this.dpixels;
            final int n4 = l;
            dpixels[n4] *= 0.997;
        }
        graphics.drawImage(this.image, this.rect.x, this.rect.y, null);
        final CircuitElm elm = this.elm;
        graphics.setColor(CircuitElm.whiteColor);
        graphics.fillOval(this.rect.x + this.draw_ox - 2, this.rect.y + this.draw_oy - 2, 5, 5);
        int n5 = this.rect.y + 10;
        final int x = this.rect.x;
        if (this.text != null && this.rect.y + this.rect.height > n5 + 5) {
            graphics.drawString(this.text, x, n5);
            n5 += 15;
        }
    }
    
    void draw(final Graphics graphics) {
        if (this.elm == null) {
            return;
        }
        if (this.plot2d) {
            this.draw2d(graphics);
            return;
        }
        if (this.pixels == null) {
            return;
        }
        final int n = this.sim.printableCheckItem.getState() ? -1 : 0;
        for (int i = 0; i != this.pixels.length; ++i) {
            this.pixels[i] = n;
        }
        final int n2 = 0;
        final int n4;
        final int n3 = n4 = (this.rect.height - 1) / 2;
        boolean b = false;
        boolean b2 = false;
        final int n5 = 4;
        double n6 = -1.0E8;
        double n7 = -1.0E8;
        double n8 = 1.0E8;
        double n9 = 1.0E8;
        int n10 = -256;
        int n11 = (this.value > 0) ? -1 : -16711936;
        if (this.sim.scopeSelected == -1 && this.elm == this.sim.mouseElm) {
            n11 = (n10 = -16711681);
        }
        final int n12 = this.ptr + this.scopePointCount - this.rect.width;
        for (int j = 0; j != this.rect.width; ++j) {
            final int n13 = j + n12 & this.scopePointCount - 1;
            while (this.maxV[n13] > this.minMaxV) {
                this.minMaxV *= 2.0;
            }
            while (this.minV[n13] < -this.minMaxV) {
                this.minMaxV *= 2.0;
            }
            while (this.maxI[n13] > this.minMaxI) {
                this.minMaxI *= 2.0;
            }
            while (this.minI[n13] < -this.minMaxI) {
                this.minMaxI *= 2.0;
            }
        }
        double n14;
        double n15;
        for (n14 = 1.0E-8, n15 = (this.showI ? this.minMaxI : this.minMaxV); n14 * 100.0 < n15; n14 *= 10.0) {}
        if (n3 * n14 / n15 < 0.3) {
            n14 = 0.0;
        }
        final boolean b3 = n3 * n14 / n15 > 3.0;
        for (int k = -100; k <= 100; ++k) {
            if (k != 0) {
                if (this.showI && this.showV) {
                    continue;
                }
                if (n14 == 0.0) {
                    continue;
                }
            }
            final int n16 = n3 - (int)(n3 * k * n14 / n15);
            if (n16 >= 0) {
                if (n16 < this.rect.height - 1) {
                    int n17 = (k == 0) ? -7303024 : -12566464;
                    if (k % 10 != 0) {
                        n17 = -15724528;
                        if (!b3) {
                            continue;
                        }
                    }
                    for (int l = 0; l != this.rect.width; ++l) {
                        this.pixels[l + n16 * this.rect.width] = n17;
                    }
                }
            }
        }
        double n18;
        double n19;
        for (n18 = 1.0E-15, n19 = this.sim.timeStep * this.speed; n18 < n19 * 5.0; n18 *= 10.0) {}
        final double n20 = this.sim.t - this.sim.timeStep * this.speed * this.rect.width;
        final double n21 = this.sim.t - this.sim.t % n18;
        int n22 = 0;
        while (true) {
            final double n23 = n21 - n18 * n22;
            final int n24 = (int)((n23 - n20) / n19);
            if (n24 < 0) {
                break;
            }
            if (n24 < this.rect.width) {
                if (n23 >= 0.0) {
                    int n25 = -14671840;
                    if ((n23 + n18 / 4.0) % (n18 * 10.0) < n18) {
                        n25 = -7303024;
                        if ((n23 + n18 / 4.0) % (n18 * 100.0) < n18) {
                            n25 = -12566320;
                        }
                    }
                    for (int n26 = 0; n26 < this.pixels.length; n26 += this.rect.width) {
                        this.pixels[n26 + n24] = n25;
                    }
                }
            }
            ++n22;
        }
        if (this.value == 0 && this.showI) {
            int n27 = -1;
            int n28 = -1;
            int n29;
            for (n29 = 0; n29 != this.rect.width; ++n29) {
                final int n30 = n29 + n12 & this.scopePointCount - 1;
                final int n31 = (int)(n3 / this.minMaxI * this.minI[n30]);
                final int n32 = (int)(n3 / this.minMaxI * this.maxI[n30]);
                if (this.maxI[n30] > n7) {
                    n7 = this.maxI[n30];
                }
                if (this.minI[n30] < n9) {
                    n9 = this.minI[n30];
                }
                if (n31 <= n3) {
                    if (n31 < -n5 || n32 > n5) {
                        b = true;
                    }
                    if (n27 != -1) {
                        if (n31 == n28 && n32 == n28) {
                            continue;
                        }
                        for (int n33 = n27; n33 != n2 + n29; ++n33) {
                            this.pixels[n33 + this.rect.width * (n4 - n28)] = n10;
                        }
                        n28 = (n27 = -1);
                    }
                    if (n31 == n32) {
                        n27 = n2 + n29;
                        n28 = n31;
                    }
                    else {
                        for (int n34 = n31; n34 <= n32; ++n34) {
                            this.pixels[n2 + n29 + this.rect.width * (n4 - n34)] = n10;
                        }
                    }
                }
            }
            if (n27 != -1) {
                for (int n35 = n27; n35 != n2 + n29; ++n35) {
                    this.pixels[n35 + this.rect.width * (n4 - n28)] = n10;
                }
            }
        }
        if (this.value != 0 || this.showV) {
            int n36 = -1;
            int n37 = -1;
            int n38;
            for (n38 = 0; n38 != this.rect.width; ++n38) {
                final int n39 = n38 + n12 & this.scopePointCount - 1;
                final int n40 = (int)(n3 / this.minMaxV * this.minV[n39]);
                final int n41 = (int)(n3 / this.minMaxV * this.maxV[n39]);
                if (this.maxV[n39] > n6) {
                    n6 = this.maxV[n39];
                }
                if (this.minV[n39] < n8) {
                    n8 = this.minV[n39];
                }
                if ((this.value != 0 || this.showV) && n40 <= n3) {
                    if (n40 < -n5 || n41 > n5) {
                        b2 = true;
                    }
                    if (n36 != -1) {
                        if (n40 == n37 && n41 == n37) {
                            continue;
                        }
                        for (int n42 = n36; n42 != n2 + n38; ++n42) {
                            this.pixels[n42 + this.rect.width * (n4 - n37)] = n11;
                        }
                        n37 = (n36 = -1);
                    }
                    if (n40 == n41) {
                        n36 = n2 + n38;
                        n37 = n40;
                    }
                    else {
                        for (int n43 = n40; n43 <= n41; ++n43) {
                            this.pixels[n2 + n38 + this.rect.width * (n4 - n43)] = n11;
                        }
                    }
                }
            }
            if (n36 != -1) {
                for (int n44 = n36; n44 != n2 + n38; ++n44) {
                    this.pixels[n44 + this.rect.width * (n4 - n37)] = n11;
                }
            }
        }
        double n45 = 0.0;
        if (this.showFreq) {
            double n46 = 0.0;
            int n47;
            for (n47 = 0; n47 != this.rect.width; ++n47) {
                final int n48 = n47 + n12 & this.scopePointCount - 1;
                n46 += this.minV[n48] + this.maxV[n48];
            }
            final double n49 = n46 / (n47 * 2);
            int n50 = 0;
            final double n51 = n49 * 0.05;
            int n52 = 0;
            double n53 = 0.0;
            int n54 = -1;
            double n55 = 0.0;
            for (int n56 = 0; n56 != this.rect.width; ++n56) {
                final double n57 = this.maxV[n56 + n12 & this.scopePointCount - 1] - n49;
                final int n58 = n50;
                if (n57 < n51) {
                    n50 = 1;
                }
                else if (n57 > -n51) {
                    n50 = 2;
                }
                if (n50 == 2 && n58 == 1) {
                    final int n59 = n56 - n52;
                    n52 = n56;
                    if (n59 >= 12) {
                        if (n54 >= 0) {
                            n53 += n59;
                            n55 += n59 * n59;
                        }
                        ++n54;
                    }
                }
            }
            final double n60 = n53 / n54;
            final double sqrt = Math.sqrt(n55 / n54 - n60 * n60);
            n45 = 1.0 / (n60 * this.sim.timeStep * this.speed);
            if (n54 < 1 || sqrt > 2.0) {
                n45 = 0.0;
            }
        }
        graphics.drawImage(this.image, this.rect.x, this.rect.y, null);
        final CircuitElm elm = this.elm;
        graphics.setColor(CircuitElm.whiteColor);
        int n61 = this.rect.y + 10;
        final int n62 = n2 + this.rect.x;
        if (this.showMax) {
            if (this.value != 0) {
                final CircuitElm elm2 = this.elm;
                graphics.drawString(CircuitElm.getUnitText(n6, this.elm.getScopeUnits(this.value)), n62, n61);
            }
            else if (this.showV) {
                final CircuitElm elm3 = this.elm;
                graphics.drawString(CircuitElm.getVoltageText(n6), n62, n61);
            }
            else if (this.showI) {
                final CircuitElm elm4 = this.elm;
                graphics.drawString(CircuitElm.getCurrentText(n7), n62, n61);
            }
            n61 += 15;
        }
        if (this.showMin) {
            final int n63 = this.rect.y + this.rect.height - 5;
            if (this.value != 0) {
                final CircuitElm elm5 = this.elm;
                graphics.drawString(CircuitElm.getUnitText(n8, this.elm.getScopeUnits(this.value)), n62, n63);
            }
            else if (this.showV) {
                final CircuitElm elm6 = this.elm;
                graphics.drawString(CircuitElm.getVoltageText(n8), n62, n63);
            }
            else if (this.showI) {
                final CircuitElm elm7 = this.elm;
                graphics.drawString(CircuitElm.getCurrentText(n9), n62, n63);
            }
        }
        if (this.text != null && this.rect.y + this.rect.height > n61 + 5) {
            graphics.drawString(this.text, n62, n61);
            n61 += 15;
        }
        if (this.showFreq && n45 != 0.0 && this.rect.y + this.rect.height > n61 + 5) {
            final CircuitElm elm8 = this.elm;
            graphics.drawString(CircuitElm.getUnitText(n45, "Hz"), n62, n61);
        }
        if (this.ptr > 5 && !this.lockScale) {
            if (!b && this.minMaxI > 1.0E-4) {
                this.minMaxI /= 2.0;
            }
            if (!b2 && this.minMaxV > 1.0E-4) {
                this.minMaxV /= 2.0;
            }
        }
    }
    
    void speedUp() {
        if (this.speed > 1) {
            this.speed /= 2;
            this.resetGraph();
        }
    }
    
    void slowDown() {
        this.speed *= 2;
        this.resetGraph();
    }
    
    PopupMenu getMenu() {
        if (this.elm == null) {
            return null;
        }
        if (this.elm instanceof TransistorElm) {
            this.sim.scopeIbMenuItem.setState(this.value == 1);
            this.sim.scopeIcMenuItem.setState(this.value == 2);
            this.sim.scopeIeMenuItem.setState(this.value == 3);
            this.sim.scopeVbeMenuItem.setState(this.value == 4);
            this.sim.scopeVbcMenuItem.setState(this.value == 5);
            this.sim.scopeVceMenuItem.setState(this.value == 6 && this.ivalue != 2);
            this.sim.scopeVceIcMenuItem.setState(this.value == 6 && this.ivalue == 2);
            return this.sim.transScopeMenu;
        }
        this.sim.scopeVMenuItem.setState(this.showV && this.value == 0);
        this.sim.scopeIMenuItem.setState(this.showI && this.value == 0);
        this.sim.scopeMaxMenuItem.setState(this.showMax);
        this.sim.scopeMinMenuItem.setState(this.showMin);
        this.sim.scopeFreqMenuItem.setState(this.showFreq);
        this.sim.scopePowerMenuItem.setState(this.value == 1);
        this.sim.scopeVIMenuItem.setState(this.plot2d && !this.plotXY);
        this.sim.scopeXYMenuItem.setState(this.plotXY);
        this.sim.scopeSelectYMenuItem.setEnabled(this.plotXY);
        this.sim.scopeResistMenuItem.setState(this.value == 2);
        this.sim.scopeResistMenuItem.setEnabled(this.elm instanceof MemristorElm);
        return this.sim.scopeMenu;
    }
    
    void setValue(final int value) {
        this.reset();
        this.value = value;
    }
    
    String dump() {
        if (this.elm == null) {
            return null;
        }
        final int n = (this.showI ? 1 : 0) | (this.showV ? 2 : 0) | (this.showMax ? 0 : 4) | (this.showFreq ? 8 : 0) | (this.lockScale ? 16 : 0) | (this.plot2d ? 64 : 0) | (this.plotXY ? 128 : 0) | (this.showMin ? 256 : 0) | 0x20;
        final int locateElm = this.sim.locateElm(this.elm);
        if (locateElm < 0) {
            return null;
        }
        String s = "o " + locateElm + " " + this.speed + " " + this.value + " " + n + " " + this.minMaxV + " " + this.minMaxI + " " + this.position + " " + ((this.yElm == null) ? -1 : this.sim.locateElm(this.yElm));
        if (this.text != null) {
            s = s + " " + this.text;
        }
        return s;
    }
    
    void undump(final StringTokenizer stringTokenizer) {
        this.reset();
        final int intValue = new Integer(stringTokenizer.nextToken());
        if (intValue == -1) {
            return;
        }
        this.elm = this.sim.getElm(intValue);
        this.speed = new Integer(stringTokenizer.nextToken());
        this.value = new Integer(stringTokenizer.nextToken());
        final int intValue2 = new Integer(stringTokenizer.nextToken());
        this.minMaxV = new Double(stringTokenizer.nextToken());
        this.minMaxI = new Double(stringTokenizer.nextToken());
        if (this.minMaxV == 0.0) {
            this.minMaxV = 0.5;
        }
        if (this.minMaxI == 0.0) {
            this.minMaxI = 1.0;
        }
        this.text = null;
        this.yElm = null;
        try {
            this.position = new Integer(stringTokenizer.nextToken());
            if ((intValue2 & 0x20) != 0x0) {
                final int intValue3 = new Integer(stringTokenizer.nextToken());
                if (intValue3 != -1) {
                    this.yElm = this.sim.getElm(intValue3);
                }
            }
            while (stringTokenizer.hasMoreTokens()) {
                if (this.text == null) {
                    this.text = stringTokenizer.nextToken();
                }
                else {
                    this.text = this.text + " " + stringTokenizer.nextToken();
                }
            }
        }
        catch (Exception ex) {}
        this.showI = ((intValue2 & 0x1) != 0x0);
        this.showV = ((intValue2 & 0x2) != 0x0);
        this.showMax = ((intValue2 & 0x4) == 0x0);
        this.showFreq = ((intValue2 & 0x8) != 0x0);
        this.lockScale = ((intValue2 & 0x10) != 0x0);
        this.plot2d = ((intValue2 & 0x40) != 0x0);
        this.plotXY = ((intValue2 & 0x80) != 0x0);
        this.showMin = ((intValue2 & 0x100) != 0x0);
    }
    
    void allocImage() {
        this.pixels = null;
        final int width = this.rect.width;
        final int height = this.rect.height;
        if (width == 0 || height == 0) {
            return;
        }
        if (this.sim.useBufferedImage) {
            try {
                final Class<?> forName = Class.forName("java.awt.image.BufferedImage");
                final Class<?> forName2 = Class.forName("java.awt.image.DataBufferInt");
                final Class<?> forName3 = Class.forName("java.awt.image.Raster");
                this.image = (Image)forName.getConstructor(Integer.TYPE, Integer.TYPE, Integer.TYPE).newInstance(new Integer(width), new Integer(height), new Integer(1));
                this.pixels = (int[])forName2.getMethod("getData", (Class<?>[])new Class[0]).invoke(forName3.getMethod("getDataBuffer", (Class<?>[])new Class[0]).invoke(forName.getMethod("getRaster", (Class<?>[])new Class[0]).invoke(this.image, new Object[0]), new Object[0]), new Object[0]);
            }
            catch (Exception ex) {
                System.out.println("BufferedImage failed");
            }
        }
        if (this.pixels == null) {
            this.pixels = new int[width * height];
            for (int i = 0; i != width * height; ++i) {
                this.pixels[i] = -16777216;
            }
            (this.imageSource = new MemoryImageSource(width, height, this.pixels, 0, width)).setAnimated(true);
            this.imageSource.setFullBufferUpdates(true);
            this.image = this.sim.cv.createImage(this.imageSource);
        }
        this.dpixels = new float[width * height];
        final int n = -1;
        this.draw_oy = n;
        this.draw_ox = n;
    }
    
    void handleMenu(final ItemEvent itemEvent, final Object o) {
        if (o == this.sim.scopeVMenuItem) {
            this.showVoltage(this.sim.scopeVMenuItem.getState());
        }
        if (o == this.sim.scopeIMenuItem) {
            this.showCurrent(this.sim.scopeIMenuItem.getState());
        }
        if (o == this.sim.scopeMaxMenuItem) {
            this.showMax(this.sim.scopeMaxMenuItem.getState());
        }
        if (o == this.sim.scopeMinMenuItem) {
            this.showMin(this.sim.scopeMinMenuItem.getState());
        }
        if (o == this.sim.scopeFreqMenuItem) {
            this.showFreq(this.sim.scopeFreqMenuItem.getState());
        }
        if (o == this.sim.scopePowerMenuItem) {
            this.setValue(1);
        }
        if (o == this.sim.scopeIbMenuItem) {
            this.setValue(1);
        }
        if (o == this.sim.scopeIcMenuItem) {
            this.setValue(2);
        }
        if (o == this.sim.scopeIeMenuItem) {
            this.setValue(3);
        }
        if (o == this.sim.scopeVbeMenuItem) {
            this.setValue(4);
        }
        if (o == this.sim.scopeVbcMenuItem) {
            this.setValue(5);
        }
        if (o == this.sim.scopeVceMenuItem) {
            this.setValue(6);
        }
        if (o == this.sim.scopeVceIcMenuItem) {
            this.plot2d = true;
            this.plotXY = false;
            this.value = 6;
            this.ivalue = 2;
            this.resetGraph();
        }
        if (o == this.sim.scopeVIMenuItem) {
            this.plot2d = this.sim.scopeVIMenuItem.getState();
            this.plotXY = false;
            this.resetGraph();
        }
        if (o == this.sim.scopeXYMenuItem) {
            final boolean state = this.sim.scopeXYMenuItem.getState();
            this.plot2d = state;
            this.plotXY = state;
            if (this.yElm == null) {
                this.selectY();
            }
            this.resetGraph();
        }
        if (o == this.sim.scopeResistMenuItem) {
            this.setValue(2);
        }
    }
    
    void select() {
        this.sim.mouseElm = this.elm;
        if (this.plotXY) {
            this.sim.plotXElm = this.elm;
            this.sim.plotYElm = this.yElm;
        }
    }
    
    void selectY() {
        int n;
        int i = n = ((this.yElm == null) ? -1 : this.sim.locateElm(this.yElm));
        while (true) {
            ++i;
            while (i < this.sim.elmList.size()) {
                final CircuitElm elm = this.sim.getElm(i);
                if ((elm instanceof OutputElm || elm instanceof ProbeElm) && elm != this.elm) {
                    this.yElm = elm;
                    return;
                }
                ++i;
            }
            if (n == -1) {
                return;
            }
            n = (i = -1);
        }
    }
}
