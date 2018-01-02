import java.awt.Color;
import java.util.Random;

// 
// Decompiled by Procyon v0.5.30
// 

class RandomPlus extends Random
{
    int v;
    
    public int nextNZInt(final int n) {
        do {
            this.v = this.nextInt() % n;
        } while (this.v == 0);
        return this.v;
    }
    
    public int nextInt(final int n) {
        return this.nextInt() % n;
    }
    
    public int nextPInt(final int n) {
        return Math.abs(this.nextInt()) % n;
    }
    
    public Color nextColor() {
        return new Color(this.nextPInt(16777216));
    }
}
