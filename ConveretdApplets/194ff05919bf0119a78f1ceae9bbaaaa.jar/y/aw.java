// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.util.NoSuchElementException;
import java.util.Enumeration;

public final class aw implements Enumeration
{
    private int a;
    private String a;
    private char a;
    
    public aw(final String a) {
        this.a = -1;
        this.a = a;
        this.a = '\n';
    }
    
    private boolean a() {
        return this.a < this.a.length();
    }
    
    public final String a() {
        if (!this.a()) {
            throw new NoSuchElementException();
        }
        ++this.a;
        final int a = this.a;
        while (this.a < this.a.length() && this.a.charAt(this.a) != this.a) {
            ++this.a;
        }
        return this.a.substring(a, this.a);
    }
    
    public final boolean hasMoreElements() {
        return this.a();
    }
    
    public final Object nextElement() {
        return this.a();
    }
    
    public final int a() {
        int n = 1;
        for (int i = 0; i < this.a.length(); ++i) {
            if (this.a.charAt(i) == this.a) {
                ++n;
            }
        }
        return n;
    }
}
