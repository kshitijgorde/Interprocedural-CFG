// 
// Decompiled by Procyon v0.5.30
// 

package ji.v1base;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.BeanInfo;
import java.awt.Image;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

public class cBeanInfo extends SimpleBeanInfo
{
    Class beanClass;
    String iconColor16x16Filename;
    String iconColor32x32Filename;
    String iconMono16x16Filename;
    String iconMono32x32Filename;
    static /* synthetic */ Class class$ji$v1base$c;
    
    public cBeanInfo() {
        this.beanClass = ((cBeanInfo.class$ji$v1base$c == null) ? (cBeanInfo.class$ji$v1base$c = class$("ji.v1base.c")) : cBeanInfo.class$ji$v1base$c);
    }
    
    public PropertyDescriptor[] getPropertyDescriptors() {
        return new PropertyDescriptor[0];
    }
    
    public Image getIcon(final int n) {
        switch (n) {
            case 1: {
                return (this.iconColor16x16Filename != null) ? this.loadImage(this.iconColor16x16Filename) : null;
            }
            case 2: {
                return (this.iconColor32x32Filename != null) ? this.loadImage(this.iconColor32x32Filename) : null;
            }
            case 3: {
                return (this.iconMono16x16Filename != null) ? this.loadImage(this.iconMono16x16Filename) : null;
            }
            case 4: {
                return (this.iconMono32x32Filename != null) ? this.loadImage(this.iconMono32x32Filename) : null;
            }
            default: {
                return null;
            }
        }
    }
    
    public BeanInfo[] getAdditionalBeanInfo() {
        final Class superclass = this.beanClass.getSuperclass();
        try {
            return new BeanInfo[] { Introspector.getBeanInfo(superclass) };
        }
        catch (IntrospectionException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    static Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}
