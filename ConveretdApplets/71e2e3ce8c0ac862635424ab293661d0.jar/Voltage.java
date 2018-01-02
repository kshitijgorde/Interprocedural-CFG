// 
// Decompiled by Procyon v0.5.30
// 

class Voltage
{
    public static final int GND = 0;
    public static final int VCC = 1;
    public static final int X = 2;
    public static final int Z = 3;
    public static final int SHORTED = 4;
    public int v;
    
    Voltage(final int v) {
        this.v = v;
    }
    
    Voltage(final Voltage voltage) {
        this.v = voltage.get();
    }
    
    public int get() {
        return this.v;
    }
    
    public void set(final int v) {
        this.v = v;
    }
    
    public void invert() {
        if (this.v == 0) {
            this.v = 1;
            return;
        }
        this.v = 0;
    }
    
    public void next() {
        ++this.v;
        if (this.v > 3) {
            this.v = 0;
        }
    }
    
    public void next01Z() {
        if (this.v == 0) {
            this.v = 1;
            return;
        }
        if (this.v == 1) {
            this.v = 3;
            return;
        }
        if (this.v == 3) {
            this.v = 0;
            return;
        }
        this.v = 0;
    }
    
    public int getinv() {
        if (this.v == 0) {
            return 1;
        }
        if (this.v == 1) {
            return 0;
        }
        return this.v;
    }
    
    public void setinv(final int v) {
        if (v == 0) {
            this.v = 1;
            return;
        }
        if (v == 1) {
            this.v = 0;
            return;
        }
        this.v = v;
    }
}
