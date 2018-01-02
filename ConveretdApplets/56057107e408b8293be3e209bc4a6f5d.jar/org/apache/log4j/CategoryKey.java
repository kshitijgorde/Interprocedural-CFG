// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.log4j;

class CategoryKey
{
    String name;
    int hashCache;
    static /* synthetic */ Class class$org$apache$log4j$CategoryKey;
    
    CategoryKey(final String name) {
        this.name = name;
        this.hashCache = name.hashCode();
    }
    
    public final int hashCode() {
        return this.hashCache;
    }
    
    public final boolean equals(final Object o) {
        return this == o || (o != null && ((CategoryKey.class$org$apache$log4j$CategoryKey == null) ? (CategoryKey.class$org$apache$log4j$CategoryKey = class$("org.apache.log4j.CategoryKey")) : CategoryKey.class$org$apache$log4j$CategoryKey) == o.getClass() && this.name.equals(((CategoryKey)o).name));
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}
