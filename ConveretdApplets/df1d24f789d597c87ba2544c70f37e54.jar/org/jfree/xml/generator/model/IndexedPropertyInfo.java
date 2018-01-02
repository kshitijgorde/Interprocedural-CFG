// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.generator.model;

public class IndexedPropertyInfo extends PropertyInfo
{
    private KeyDescription key;
    
    public IndexedPropertyInfo(final String s, final Class clazz) {
        super(s, clazz);
    }
    
    public KeyDescription getKey() {
        return this.key;
    }
    
    public void setKey(final KeyDescription key) {
        this.key = key;
    }
}
