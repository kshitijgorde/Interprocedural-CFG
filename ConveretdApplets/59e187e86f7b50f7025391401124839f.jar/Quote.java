import java.util.BitSet;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class Quote
{
    public Vector text;
    public String source;
    BitSet flags;
    public static final int small = 1;
    public static final int big = 2;
    public static final int bigger = 3;
    public static final int huge = 4;
    public static final int dialog = 10;
    public static final int serif = 11;
    public static final int courier = 12;
    public static final int monospaced = 13;
    public static final int srcface = 14;
    public static final int flash = 16;
    public static final int emphasis = 17;
    
    public Quote() {
        this.text = new Vector();
        this.source = "";
        this.flags = new BitSet();
    }
    
    public boolean getFlag(final int n) {
        return this.flags.get(n);
    }
    
    public void setFlag(final int n) {
        this.flags.set(n);
    }
}
