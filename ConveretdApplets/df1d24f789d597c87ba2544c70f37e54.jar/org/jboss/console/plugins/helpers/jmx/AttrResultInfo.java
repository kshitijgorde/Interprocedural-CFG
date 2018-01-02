// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.console.plugins.helpers.jmx;

import java.beans.PropertyEditor;

public class AttrResultInfo
{
    public String name;
    public PropertyEditor editor;
    public Object result;
    public Throwable throwable;
    
    public AttrResultInfo(final String name, final PropertyEditor editor, final Object result, final Throwable throwable) {
        this.name = name;
        this.editor = editor;
        this.result = result;
        this.throwable = throwable;
    }
    
    public String getAsText() {
        if (this.throwable != null) {
            return this.throwable.toString();
        }
        if (this.result != null) {
            try {
                if (this.editor != null) {
                    this.editor.setValue(this.result);
                    return this.editor.getAsText();
                }
                return this.result.toString();
            }
            catch (Exception e) {
                return "String representation of " + this.name + "unavailable";
            }
        }
        return null;
    }
}
