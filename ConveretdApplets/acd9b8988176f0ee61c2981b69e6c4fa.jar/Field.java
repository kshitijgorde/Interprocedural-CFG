import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;

// 
// Decompiled by Procyon v0.5.30
// 

public class Field extends HashMap
{
    Class[] re;
    HashSet f;
    final Photo g;
    
    public Field(final Photo a, final HashSet t) {
        this.re = new Class[1];
        this.g = a;
        this.f = t;
    }
    
    public Set entrySet() {
        return this.f;
    }
}
