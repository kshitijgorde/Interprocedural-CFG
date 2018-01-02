// 
// Decompiled by Procyon v0.5.30
// 

package ji.v1base;

import java.awt.Image;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

public class jiAppletContainerBeanInfo extends SimpleBeanInfo
{
    Class beanClass;
    String iconColor16x16Filename;
    String iconColor32x32Filename;
    String iconMono16x16Filename;
    String iconMono32x32Filename;
    static /* synthetic */ Class class$ji$v1base$jiAppletContainer;
    
    public jiAppletContainerBeanInfo() {
        this.beanClass = ((jiAppletContainerBeanInfo.class$ji$v1base$jiAppletContainer == null) ? (jiAppletContainerBeanInfo.class$ji$v1base$jiAppletContainer = class$("ji.v1base.jiAppletContainer")) : jiAppletContainerBeanInfo.class$ji$v1base$jiAppletContainer);
    }
    
    public PropertyDescriptor[] getPropertyDescriptors() {
        try {
            return new PropertyDescriptor[] { new PropertyDescriptor("contentPane", this.beanClass, "getContentPane", null) };
        }
        catch (IntrospectionException ex) {
            ex.printStackTrace();
            return null;
        }
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
    
    static Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}
