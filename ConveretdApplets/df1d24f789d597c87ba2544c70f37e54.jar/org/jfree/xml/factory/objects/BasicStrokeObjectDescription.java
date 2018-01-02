// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.factory.objects;

import java.awt.BasicStroke;

public class BasicStrokeObjectDescription extends AbstractObjectDescription
{
    static /* synthetic */ Class class$java$awt$BasicStroke;
    static /* synthetic */ Class class$java$lang$String;
    
    public BasicStrokeObjectDescription() {
        super((BasicStrokeObjectDescription.class$java$awt$BasicStroke == null) ? (BasicStrokeObjectDescription.class$java$awt$BasicStroke = class$("java.awt.BasicStroke")) : BasicStrokeObjectDescription.class$java$awt$BasicStroke);
        this.setParameterDefinition("value", (BasicStrokeObjectDescription.class$java$lang$String == null) ? (BasicStrokeObjectDescription.class$java$lang$String = class$("java.lang.String")) : BasicStrokeObjectDescription.class$java$lang$String);
    }
    
    private float getFloatParameter(final String s) {
        final String s2 = (String)this.getParameter(s);
        if (s2 == null) {
            return 0.0f;
        }
        try {
            return Float.parseFloat(s2);
        }
        catch (Exception ex) {
            return 0.0f;
        }
    }
    
    public Object createObject() {
        return new BasicStroke(this.getFloatParameter("value"));
    }
    
    public void setParameterFromObject(final Object o) throws ObjectFactoryException {
        if (!(o instanceof BasicStroke)) {
            throw new ObjectFactoryException("Expected object of type BasicStroke");
        }
        this.setParameter("value", String.valueOf(((BasicStroke)o).getLineWidth()));
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
