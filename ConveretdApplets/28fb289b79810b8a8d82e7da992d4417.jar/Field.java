import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;

// 
// Decompiled by Procyon v0.5.30
// 

public class Field extends HashMap
{
    HashSet rty;
    final Photo ty6;
    
    public Field(final Photo ty6, final HashSet rty) {
        this.ty6 = ty6;
        this.rty = rty;
    }
    
    public Set entrySet() {
        return this.rty;
    }
}
