// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.ui.about;

class SystemProperty
{
    private String name;
    private String value;
    
    public SystemProperty(final String name, final String value) {
        this.name = name;
        this.value = value;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getValue() {
        return this.value;
    }
}
