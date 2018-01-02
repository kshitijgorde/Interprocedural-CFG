// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.factory.objects;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import org.jfree.util.Log;
import java.awt.Color;

public class ColorObjectDescription extends AbstractObjectDescription
{
    static /* synthetic */ Class class$java$awt$Color;
    static /* synthetic */ Class class$java$lang$String;
    
    public ColorObjectDescription() {
        super((ColorObjectDescription.class$java$awt$Color == null) ? (ColorObjectDescription.class$java$awt$Color = class$("java.awt.Color")) : ColorObjectDescription.class$java$awt$Color);
        this.setParameterDefinition("value", (ColorObjectDescription.class$java$lang$String == null) ? (ColorObjectDescription.class$java$lang$String = class$("java.lang.String")) : ColorObjectDescription.class$java$lang$String);
    }
    
    public Object createObject() {
        final String s = (String)this.getParameter("value");
        if (s == null) {
            return Color.black;
        }
        try {
            return Color.decode(s);
        }
        catch (NumberFormatException ex) {
            try {
                return ((ColorObjectDescription.class$java$awt$Color == null) ? (ColorObjectDescription.class$java$awt$Color = class$("java.awt.Color")) : ColorObjectDescription.class$java$awt$Color).getField(s).get(null);
            }
            catch (Exception ex2) {
                Log.info("No such Color : " + s);
                return Color.black;
            }
        }
    }
    
    public void setParameterFromObject(final Object o) throws ObjectFactoryException {
        if (!(o instanceof Color)) {
            throw new ObjectFactoryException("Is no instance of java.awt.Color");
        }
        final Color color = (Color)o;
        try {
            final Field[] fields = ((ColorObjectDescription.class$java$awt$Color == null) ? (ColorObjectDescription.class$java$awt$Color = class$("java.awt.Color")) : ColorObjectDescription.class$java$awt$Color).getFields();
            for (int i = 0; i < fields.length; ++i) {
                final Field field = fields[i];
                if (Modifier.isPublic(field.getModifiers()) && Modifier.isFinal(field.getModifiers()) && Modifier.isStatic(field.getModifiers())) {
                    final String name = field.getName();
                    final Object value = field.get(null);
                    if (value instanceof Color && color.equals(value)) {
                        this.setParameter("value", name);
                        return;
                    }
                }
            }
        }
        catch (Exception ex) {}
        final String hexString = Integer.toHexString(color.getRGB() & 0xFFFFFF);
        final StringBuffer sb = new StringBuffer(7);
        sb.append("#");
        for (int n = 6 - hexString.length(), j = 0; j < n; ++j) {
            sb.append("0");
        }
        sb.append(hexString);
        this.setParameter("value", sb.toString());
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
