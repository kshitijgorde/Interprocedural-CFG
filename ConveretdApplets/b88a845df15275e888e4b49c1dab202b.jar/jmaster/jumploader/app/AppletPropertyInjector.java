// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.app;

import java.util.ArrayList;
import java.lang.reflect.Method;
import java.util.Locale;
import jmaster.util.log.B;
import java.applet.Applet;
import jmaster.util.log.A;
import java.util.List;

public class AppletPropertyInjector
{
    public static final String PREFIX_SET = "set";
    private static final List C;
    private A B;
    private Applet A;
    static /* synthetic */ Class class$java$lang$Boolean;
    static /* synthetic */ Class class$java$lang$Byte;
    static /* synthetic */ Class class$java$lang$Character;
    static /* synthetic */ Class class$java$lang$Double;
    static /* synthetic */ Class class$java$lang$Float;
    static /* synthetic */ Class class$java$lang$Integer;
    static /* synthetic */ Class class$java$lang$Long;
    static /* synthetic */ Class class$java$lang$Short;
    static /* synthetic */ Class class$java$lang$String;
    
    public AppletPropertyInjector(final Applet a) {
        this.B = jmaster.util.log.B.getInstance().getLog(this);
        this.A = a;
    }
    
    private boolean J(final String s) {
        return this.A.getParameter(s) != null;
    }
    
    private String D(final String s) {
        return this.A.getParameter(s);
    }
    
    private int C(final String s) {
        return Integer.parseInt(this.D(s));
    }
    
    private long F(final String s) {
        return Long.parseLong(this.D(s));
    }
    
    private short A(final String s) {
        return Short.parseShort(this.D(s));
    }
    
    private byte I(final String s) {
        return Byte.parseByte(this.D(s));
    }
    
    private char B(final String s) {
        return this.D(s).charAt(0);
    }
    
    private float H(final String s) {
        return Float.parseFloat(this.D(s));
    }
    
    private double E(final String s) {
        return Double.parseDouble(this.D(s));
    }
    
    private boolean G(final String s) {
        return "true".equalsIgnoreCase(this.D(s));
    }
    
    private Object A(final Class clazz, final String s) {
        Object o = null;
        if (clazz.equals(Boolean.TYPE) || clazz.equals((AppletPropertyInjector.class$java$lang$Boolean == null) ? (AppletPropertyInjector.class$java$lang$Boolean = class$("java.lang.Boolean")) : AppletPropertyInjector.class$java$lang$Boolean)) {
            o = this.G(s);
        }
        else if (clazz.equals(Byte.TYPE) || clazz.equals((AppletPropertyInjector.class$java$lang$Byte == null) ? (AppletPropertyInjector.class$java$lang$Byte = class$("java.lang.Byte")) : AppletPropertyInjector.class$java$lang$Byte)) {
            o = new Byte(this.I(s));
        }
        else if (clazz.equals(Character.TYPE) || clazz.equals((AppletPropertyInjector.class$java$lang$Character == null) ? (AppletPropertyInjector.class$java$lang$Character = class$("java.lang.Character")) : AppletPropertyInjector.class$java$lang$Character)) {
            o = new Character(this.B(s));
        }
        else if (clazz.equals(Double.TYPE) || clazz.equals((AppletPropertyInjector.class$java$lang$Double == null) ? (AppletPropertyInjector.class$java$lang$Double = class$("java.lang.Double")) : AppletPropertyInjector.class$java$lang$Double)) {
            o = new Double(this.E(s));
        }
        else if (clazz.equals(Float.TYPE) || clazz.equals((AppletPropertyInjector.class$java$lang$Float == null) ? (AppletPropertyInjector.class$java$lang$Float = class$("java.lang.Float")) : AppletPropertyInjector.class$java$lang$Float)) {
            o = new Float(this.H(s));
        }
        else if (clazz.equals(Integer.TYPE) || clazz.equals((AppletPropertyInjector.class$java$lang$Integer == null) ? (AppletPropertyInjector.class$java$lang$Integer = class$("java.lang.Integer")) : AppletPropertyInjector.class$java$lang$Integer)) {
            o = new Integer(this.C(s));
        }
        else if (clazz.equals(Long.TYPE) || clazz.equals((AppletPropertyInjector.class$java$lang$Long == null) ? (AppletPropertyInjector.class$java$lang$Long = class$("java.lang.Long")) : AppletPropertyInjector.class$java$lang$Long)) {
            o = new Long(this.F(s));
        }
        else if (clazz.equals(Short.TYPE) || clazz.equals((AppletPropertyInjector.class$java$lang$Short == null) ? (AppletPropertyInjector.class$java$lang$Short = class$("java.lang.Short")) : AppletPropertyInjector.class$java$lang$Short)) {
            o = new Short(this.A(s));
        }
        else if (clazz.equals((AppletPropertyInjector.class$java$lang$String == null) ? (AppletPropertyInjector.class$java$lang$String = class$("java.lang.String")) : AppletPropertyInjector.class$java$lang$String)) {
            o = this.D(s);
        }
        return o;
    }
    
    public void injectProperties(final Object o, String s) {
        if (s == null) {
            s = "";
        }
        final Method[] methods = o.getClass().getMethods();
        for (int i = 0; i < methods.length; ++i) {
            final Method method = methods[i];
            try {
                final String name = method.getName();
                final Class<?>[] parameterTypes = method.getParameterTypes();
                if (name.startsWith("set") && parameterTypes != null && parameterTypes.length == 1 && AppletPropertyInjector.C.contains(parameterTypes[0])) {
                    final String string = s + (name.substring("set".length(), "set".length() + 1).toLowerCase(Locale.ENGLISH) + name.substring("set".length() + 1));
                    if (this.J(string)) {
                        method.invoke(o, this.A(parameterTypes[0], string));
                    }
                }
            }
            catch (Exception ex) {
                this.B.E("Failed to inject property, target=" + o + ", method=" + method, ex);
            }
        }
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError().initCause(ex);
        }
    }
    
    static {
        (C = new ArrayList()).add(Boolean.TYPE);
        AppletPropertyInjector.C.add(Byte.TYPE);
        AppletPropertyInjector.C.add(Character.TYPE);
        AppletPropertyInjector.C.add(Double.TYPE);
        AppletPropertyInjector.C.add(Float.TYPE);
        AppletPropertyInjector.C.add(Integer.TYPE);
        AppletPropertyInjector.C.add(Long.TYPE);
        AppletPropertyInjector.C.add(Short.TYPE);
        AppletPropertyInjector.C.add((AppletPropertyInjector.class$java$lang$Boolean == null) ? (AppletPropertyInjector.class$java$lang$Boolean = class$("java.lang.Boolean")) : AppletPropertyInjector.class$java$lang$Boolean);
        AppletPropertyInjector.C.add((AppletPropertyInjector.class$java$lang$Byte == null) ? (AppletPropertyInjector.class$java$lang$Byte = class$("java.lang.Byte")) : AppletPropertyInjector.class$java$lang$Byte);
        AppletPropertyInjector.C.add((AppletPropertyInjector.class$java$lang$Character == null) ? (AppletPropertyInjector.class$java$lang$Character = class$("java.lang.Character")) : AppletPropertyInjector.class$java$lang$Character);
        AppletPropertyInjector.C.add((AppletPropertyInjector.class$java$lang$Double == null) ? (AppletPropertyInjector.class$java$lang$Double = class$("java.lang.Double")) : AppletPropertyInjector.class$java$lang$Double);
        AppletPropertyInjector.C.add((AppletPropertyInjector.class$java$lang$Float == null) ? (AppletPropertyInjector.class$java$lang$Float = class$("java.lang.Float")) : AppletPropertyInjector.class$java$lang$Float);
        AppletPropertyInjector.C.add((AppletPropertyInjector.class$java$lang$Integer == null) ? (AppletPropertyInjector.class$java$lang$Integer = class$("java.lang.Integer")) : AppletPropertyInjector.class$java$lang$Integer);
        AppletPropertyInjector.C.add((AppletPropertyInjector.class$java$lang$Long == null) ? (AppletPropertyInjector.class$java$lang$Long = class$("java.lang.Long")) : AppletPropertyInjector.class$java$lang$Long);
        AppletPropertyInjector.C.add((AppletPropertyInjector.class$java$lang$Short == null) ? (AppletPropertyInjector.class$java$lang$Short = class$("java.lang.Short")) : AppletPropertyInjector.class$java$lang$Short);
        AppletPropertyInjector.C.add((AppletPropertyInjector.class$java$lang$String == null) ? (AppletPropertyInjector.class$java$lang$String = class$("java.lang.String")) : AppletPropertyInjector.class$java$lang$String);
    }
}
