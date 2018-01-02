// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Checkbox;
import java.lang.reflect.Method;
import java.awt.Label;
import java.awt.Component;
import java.awt.Color;

public final class s
{
    a a;
    Color a;
    Color b;
    Color c;
    boolean a;
    boolean b;
    int a;
    int b;
    boolean c;
    boolean d;
    boolean e;
    int c;
    
    s(final a a) {
        this.a = Color.black;
        this.b = Color.white;
        this.c = j.c;
        this.a = true;
        this.b = false;
        this.a = a;
        this.a = 0;
        this.b = 0;
        this.c = 0;
        this.c = false;
        this.e = false;
        this.d = false;
    }
    
    public static Component a(final String s) {
        if (aV.b == null || !aV.b.equalsIgnoreCase("ar.lang")) {
            return new Label(s);
        }
        final Component a;
        a(a = a(), s);
        return a;
    }
    
    private static void b(final Component component, final boolean b) {
        final Method[] methods = component.getClass().getMethods();
        Method method = null;
        for (int i = 0; i < methods.length; ++i) {
            if ("setOpaque".equals(methods[i].getName())) {
                method = methods[i];
                break;
            }
        }
        try {
            method.invoke(component, new Boolean(false));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private static void a(final Component component, final String s) {
        final Method[] methods = component.getClass().getMethods();
        Method method = null;
        for (int i = 0; i < methods.length; ++i) {
            if ("setText".equals(methods[i].getName())) {
                method = methods[i];
                break;
            }
        }
        try {
            method.invoke(component, s);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private static Component a() {
        if (aV.b == null || !aV.b.equalsIgnoreCase("ar.lang")) {
            return new Label();
        }
        Component component = null;
        if (aZ.d) {
            try {
                b(component = (Component)Class.forName("javax.swing.JLabel").newInstance(), false);
                return component;
            }
            catch (ClassNotFoundException ex4) {
                try {
                    component = (Component)Class.forName("java.awt.Label").newInstance();
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
                return component;
            }
        }
        try {
            component = (Component)Class.forName("java.awt.Label").newInstance();
        }
        catch (Exception ex3) {
            ex3.printStackTrace();
        }
        return component;
    }
    
    public static Component b(final String s) {
        final Component b;
        final Component component;
        final Method[] methods = (component = (b = b())).getClass().getMethods();
        Method method = null;
        for (int i = 0; i < methods.length; ++i) {
            if ("setLabel".equals(methods[i].getName())) {
                method = methods[i];
                break;
            }
        }
        try {
            method.invoke(component, s);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return b;
    }
    
    private static Component b() {
        if (aV.b == null || !aV.b.equalsIgnoreCase("ar.lang")) {
            return new Checkbox();
        }
        Component component = null;
        if (aZ.d) {
            try {
                b(component = (Component)Class.forName("javax.swing.JCheckBox").newInstance(), false);
                return component;
            }
            catch (ClassNotFoundException ex4) {
                try {
                    component = (Component)Class.forName("java.awt.Checkbox").newInstance();
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
                return component;
            }
        }
        try {
            component = (Component)Class.forName("java.awt.Checkbox").newInstance();
        }
        catch (Exception ex3) {
            ex3.printStackTrace();
        }
        return component;
    }
    
    public static boolean a(final Component component) {
        if (component.getClass().getName().equals("java.awt.Checkbox")) {
            return ((Checkbox)component).getState();
        }
        final Method[] methods = component.getClass().getMethods();
        Method method = null;
        boolean booleanValue = false;
        for (int i = 0; i < methods.length; ++i) {
            if ("getState".equals(methods[i].getName()) || "isSelected".equals(methods[i].getName())) {
                method = methods[i];
                break;
            }
        }
        try {
            booleanValue = (boolean)method.invoke(component, new Object[0]);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            throw loadexception(java.lang.Throwable.class);
        }
        return booleanValue;
    }
    
    public static void a(final Component component, final boolean b) {
        if (component.getClass().getName().equals("java.awt.Checkbox")) {
            ((Checkbox)component).setState(true);
        }
        final Method[] methods = component.getClass().getMethods();
        Method method = null;
        for (int i = 0; i < methods.length; ++i) {
            if ("setState".equals(methods[i].getName()) || "setSelected".equals(methods[i].getName())) {
                method = methods[i];
                break;
            }
        }
        try {
            method.invoke(component, new Boolean(true));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public s() {
    }
}
