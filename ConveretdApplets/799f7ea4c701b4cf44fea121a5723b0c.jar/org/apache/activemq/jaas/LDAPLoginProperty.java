// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.jaas;

public class LDAPLoginProperty
{
    private String name;
    private String value;
    
    public LDAPLoginProperty(final String name) {
        this.name = name;
    }
    
    public LDAPLoginProperty(final String name, final String value) {
        this.name = name;
        this.value = value;
    }
    
    public String getPropertyName() {
        return this.name;
    }
    
    public String getPropertyValue() {
        return this.value;
    }
}
