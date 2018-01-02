// 
// Decompiled by Procyon v0.5.30
// 

package COM.objectspace.jgl;

import java.io.Serializable;

public class Pair implements Serializable
{
    public Object first;
    public Object second;
    
    public Pair(final Object first, final Object second) {
        this.first = first;
        this.second = second;
    }
    
    public Pair() {
        this.first = null;
        this.second = null;
    }
    
    public Pair(final Pair pair) {
        this.first = pair.first;
        this.second = pair.second;
    }
    
    public int hashCode() {
        int n = (this.first == null) ? 0 : this.first.hashCode();
        if (this.second != null) {
            n ^= this.second.hashCode();
        }
        return n;
    }
    
    public String toString() {
        return String.valueOf(String.valueOf(String.valueOf(String.valueOf("Pair( ").concat(String.valueOf(this.first))).concat(String.valueOf(", "))).concat(String.valueOf(this.second))).concat(String.valueOf(" )"));
    }
    
    public boolean equals(final Object o) {
        return o instanceof Pair && this.equals((Pair)o);
    }
    
    public boolean equals(final Pair pair) {
        return pair != null && ((this.first == null) ? (pair.first == null) : this.first.equals(pair.first)) && ((this.second == null) ? (pair.second == null) : this.second.equals(pair.second));
    }
    
    public synchronized Object clone() {
        return new Pair(this);
    }
}
