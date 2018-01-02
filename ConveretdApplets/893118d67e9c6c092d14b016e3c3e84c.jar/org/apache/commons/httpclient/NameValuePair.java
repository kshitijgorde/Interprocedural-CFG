// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.httpclient;

import java.io.Serializable;

public class NameValuePair implements Serializable
{
    private String name;
    private String value;
    
    public NameValuePair() {
        this(null, null);
    }
    
    public NameValuePair(final String name, final String value) {
        this.name = null;
        this.value = null;
        this.name = name;
        this.value = value;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setValue(final String value) {
        this.value = value;
    }
    
    public String getValue() {
        return this.value;
    }
    
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (this.getClass().equals(object.getClass())) {
            final NameValuePair pair = (NameValuePair)object;
            if (this.name == null) {
                if (pair.name != null) {
                    return false;
                }
            }
            else if (!this.name.equals(pair.name)) {
                return false;
            }
            if (this.value == null) {
                if (pair.value != null) {
                    return false;
                }
            }
            else if (!this.value.equals(pair.value)) {
                return false;
            }
            return true;
        }
        return false;
    }
    
    public int hashCode() {
        return this.getClass().hashCode() ^ ((this.name == null) ? 0 : this.name.hashCode()) ^ ((this.value == null) ? 0 : this.value.hashCode());
    }
    
    public String toString() {
        return "name=" + this.name + ", " + "value=" + this.value;
    }
}
