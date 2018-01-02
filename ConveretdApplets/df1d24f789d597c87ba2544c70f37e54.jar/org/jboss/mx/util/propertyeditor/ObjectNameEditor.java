// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.mx.util.propertyeditor;

import javax.management.MalformedObjectNameException;
import org.jboss.util.NestedRuntimeException;
import javax.management.ObjectName;
import org.jboss.util.propertyeditor.TextPropertyEditorSupport;

public class ObjectNameEditor extends TextPropertyEditorSupport
{
    public Object getValue() {
        try {
            return new ObjectName(this.getAsText());
        }
        catch (MalformedObjectNameException e) {
            throw new NestedRuntimeException(e);
        }
    }
}
