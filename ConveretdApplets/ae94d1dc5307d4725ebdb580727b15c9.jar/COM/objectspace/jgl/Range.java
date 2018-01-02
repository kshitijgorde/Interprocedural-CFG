// 
// Decompiled by Procyon v0.5.30
// 

package COM.objectspace.jgl;

public class Range
{
    public ForwardIterator begin;
    public ForwardIterator end;
    
    public Range(final ForwardIterator begin, final ForwardIterator end) {
        this.begin = begin;
        this.end = end;
    }
    
    public Range() {
        this.begin = null;
        this.end = null;
    }
    
    public String toString() {
        return String.valueOf(String.valueOf(String.valueOf(String.valueOf("Range( ").concat(String.valueOf(this.begin))).concat(String.valueOf(", "))).concat(String.valueOf(this.end))).concat(String.valueOf(" )"));
    }
    
    public boolean equals(final Object o) {
        return o instanceof Range && this.equals((Range)o);
    }
    
    public boolean equals(final Range range) {
        return this.begin.equals(range.begin) && this.end.equals(range.end);
    }
}
