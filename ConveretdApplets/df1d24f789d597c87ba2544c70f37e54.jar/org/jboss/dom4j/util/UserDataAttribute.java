// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.dom4j.util;

import org.jboss.dom4j.QName;
import org.jboss.dom4j.tree.DefaultAttribute;

public class UserDataAttribute extends DefaultAttribute
{
    private Object data;
    
    public UserDataAttribute(final QName qname) {
        super(qname);
    }
    
    public UserDataAttribute(final QName qname, final String text) {
        super(qname, text);
    }
    
    public Object getData() {
        return this.data;
    }
    
    public void setData(final Object data) {
        this.data = data;
    }
}
