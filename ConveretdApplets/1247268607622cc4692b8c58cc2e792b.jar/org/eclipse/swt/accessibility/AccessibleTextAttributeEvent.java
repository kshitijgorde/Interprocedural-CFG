// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.accessibility;

import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.swt.internal.SWTEventObject;

public class AccessibleTextAttributeEvent extends SWTEventObject
{
    public int offset;
    public int start;
    public int end;
    public TextStyle textStyle;
    public String[] attributes;
    public String result;
    static final long serialVersionUID = 7131825608864332802L;
    
    public AccessibleTextAttributeEvent(final Object o) {
        super(o);
    }
    
    public String toString() {
        return "AccessibleAttributeEvent { offset=" + this.offset + " start=" + this.start + " end=" + this.end + " textStyle=" + this.textStyle + " attributes=" + this.toAttributeString(this.attributes) + " result=" + this.result + "}";
    }
    
    String toAttributeString(final String[] array) {
        if (array == null || array.length == 0) {
            return new StringBuffer().append(array).toString();
        }
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            sb.append(array[i]);
            sb.append((i % 2 == 0) ? ":" : ";");
        }
        return sb.toString();
    }
}
