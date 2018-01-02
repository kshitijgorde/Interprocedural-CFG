import java.awt.Color;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

class Wire
{
    public int npoints;
    public int[] x;
    public int[] y;
    public int nSolderDots;
    public int[] xSD;
    public int[] ySD;
    Voltage v;
    String label;
    
    Wire(final int n, final int n2, final int n3, final int n4, final Voltage v) {
        this.npoints = 2;
        this.y = new int[2];
        (this.x = new int[2])[0] = n;
        this.x[1] = n3;
        this.y[0] = n2;
        this.y[1] = n4;
        this.v = v;
        this.label = "";
        this.nSolderDots = 0;
    }
    
    Wire(final int n, final int n2, final int n3, final int n4, final Voltage v, final String label) {
        this.npoints = 2;
        this.y = new int[2];
        (this.x = new int[2])[0] = n;
        this.x[1] = n3;
        this.y[0] = n2;
        this.y[1] = n4;
        this.v = v;
        this.label = label;
        this.nSolderDots = 0;
    }
    
    public void append(final int n, final int n2) {
        final int[] array = new int[this.npoints];
        final int[] array2 = new int[this.npoints];
        for (int i = 0; i < this.npoints; ++i) {
            array[i] = this.x[i];
            array2[i] = this.y[i];
        }
        ++this.npoints;
        this.x = new int[this.npoints];
        this.y = new int[this.npoints];
        for (int j = 0; j < this.npoints - 1; ++j) {
            this.x[j] = array[j];
            this.y[j] = array2[j];
        }
        this.x[this.npoints - 1] = n;
        this.y[this.npoints - 1] = n2;
    }
    
    public void addSolderDot(final int n, final int n2) {
        if (this.nSolderDots > 0) {
            final int[] array = new int[this.nSolderDots];
            final int[] array2 = new int[this.nSolderDots];
            for (int i = 0; i < this.nSolderDots; ++i) {
                array[i] = this.xSD[i];
                array2[i] = this.ySD[i];
            }
            ++this.nSolderDots;
            this.xSD = new int[this.nSolderDots];
            this.ySD = new int[this.nSolderDots];
            for (int j = 0; j < this.nSolderDots - 1; ++j) {
                this.xSD[j] = array[j];
                this.ySD[j] = array2[j];
            }
            this.xSD[this.nSolderDots - 1] = n;
            this.ySD[this.nSolderDots - 1] = n2;
            return;
        }
        this.nSolderDots = 1;
        this.xSD = new int[this.nSolderDots];
        this.ySD = new int[this.nSolderDots];
        this.xSD[0] = n;
        this.ySD[0] = n2;
    }
    
    public void setVoltage(final int n) {
        this.v.set(n);
    }
    
    public void setLabel(final String label) {
        this.label = label;
    }
    
    public void drawWire(final Graphics graphics) {
        switch (this.v.get()) {
            case 0: {
                graphics.setColor(Color.blue);
                break;
            }
            case 1: {
                graphics.setColor(Color.red);
                break;
            }
            case 2: {
                graphics.setColor(Color.pink);
                break;
            }
            case 3: {
                graphics.setColor(Color.orange);
                break;
            }
            case 4: {
                graphics.setColor(Color.green);
                break;
            }
            default: {
                graphics.setColor(Color.green);
                System.out.println("drawWire: Unknown voltage" + this.v);
                break;
            }
        }
        for (int i = 0; i < this.npoints - 1; ++i) {
            graphics.drawLine(this.x[i], this.y[i], this.x[i + 1], this.y[i + 1]);
        }
        for (int j = 0; j < this.nSolderDots; ++j) {
            graphics.fillRect(this.xSD[j] - 1, this.ySD[j] - 1, 3, 3);
        }
    }
    
    public void drawVoltageLabel(final Graphics graphics, final int n) {
        int n2;
        int n3;
        if (n == 1) {
            n2 = this.x[this.npoints - 1] + 5;
            n3 = this.y[this.npoints - 1] + 5;
        }
        else {
            n2 = this.x[0] - 30;
            n3 = this.y[0] + 5;
        }
        graphics.setColor(Color.black);
        switch (this.v.get()) {
            case 0: {
                graphics.drawString("GND", n2, n3);
            }
            case 1: {
                graphics.drawString("VCC", n2, n3);
            }
            case 2: {
                graphics.drawString(" X ", n2, n3);
            }
            case 3: {
                graphics.drawString(" Z ", n2, n3);
            }
            case 4: {
                graphics.drawString("ERROR", n2, n3);
            }
            default: {
                graphics.drawString("ERROR", n2, n3);
            }
        }
    }
    
    public void drawLabel(final Graphics graphics, final int n) {
        int n2;
        int n3;
        if (n == 1) {
            n2 = this.x[this.npoints - 1] + 5;
            n3 = this.y[this.npoints - 1] + 5;
        }
        else {
            n2 = this.x[0] - 40;
            n3 = this.y[0] + 5;
        }
        graphics.setColor(Color.black);
        graphics.drawString(this.label, n2, n3);
    }
    
    public boolean nearStartPoint(final int n, final int n2, final int n3) {
        return Math.abs(n - this.x[0]) <= n3 && Math.abs(n2 - this.y[0]) <= n3;
    }
}
