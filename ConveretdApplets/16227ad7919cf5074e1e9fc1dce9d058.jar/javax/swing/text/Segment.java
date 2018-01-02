// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.text;

public class Segment
{
    public char[] array;
    public int offset;
    public int count;
    
    public Segment() {
        this.array = null;
        this.offset = 0;
        this.count = 0;
    }
    
    public Segment(final char[] array, final int offset, final int count) {
        this.array = array;
        this.offset = offset;
        this.count = count;
    }
    
    public String toString() {
        if (this.array != null) {
            return new String(this.array, this.offset, this.count);
        }
        return new String();
    }
}
