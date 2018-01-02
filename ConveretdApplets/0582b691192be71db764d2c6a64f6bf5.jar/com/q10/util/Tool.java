// 
// Decompiled by Procyon v0.5.30
// 

package com.q10.util;

import java.util.Hashtable;
import java.util.Vector;
import com.q10.comum.cot.Ultima;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Font;
import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Properties;
import java.applet.Applet;

public class Tool
{
    public static final int[] diasDosMeses;
    static /* synthetic */ Class class$java$lang$String;
    
    public static void initializeApplet(final Applet applet, final String s, Properties properties) {
        final Field[] fields = applet.getClass().getFields();
        if (properties == null) {
            properties = new Properties();
        }
        for (int i = 0; i < fields.length; ++i) {
            try {
                final String parameter = applet.getParameter(fields[i].getName());
                if (parameter != null && !Modifier.isFinal(fields[i].getModifiers())) {
                    if (s == null || fields[i].getName().startsWith(s)) {
                        final Class<?> type = fields[i].getType();
                        if (type.equals(Boolean.TYPE)) {
                            fields[i].setBoolean(applet, Boolean.valueOf(parameter));
                        }
                        else if (type.equals(Byte.TYPE)) {
                            fields[i].setByte(applet, Byte.valueOf(parameter));
                        }
                        else if (type.equals(Character.TYPE)) {
                            fields[i].setChar(applet, parameter.charAt(0));
                        }
                        else if (type.equals(Double.TYPE)) {
                            fields[i].setDouble(applet, Double.valueOf(parameter));
                        }
                        else if (type.equals(Float.TYPE)) {
                            fields[i].setFloat(applet, Float.valueOf(parameter));
                        }
                        else if (type.equals(Integer.TYPE)) {
                            fields[i].setInt(applet, Integer.valueOf(parameter));
                        }
                        else if (type.equals(Long.TYPE)) {
                            fields[i].setLong(applet, Long.valueOf(parameter));
                        }
                        else if (type.equals(Short.TYPE)) {
                            fields[i].setShort(applet, Short.valueOf(parameter));
                        }
                        else if (type.equals((Tool.class$java$lang$String != null) ? Tool.class$java$lang$String : (Tool.class$java$lang$String = class$("java.lang.String")))) {
                            fields[i].set(applet, parameter);
                        }
                        ((Hashtable<String, String>)properties).put(fields[i].getName(), parameter);
                    }
                }
            }
            catch (Exception ex) {
                System.err.println(String.valueOf(ex) + " while initializing " + fields[i]);
            }
        }
    }
    
    public static Color cor(final String s) {
        return new Color((s != null) ? Integer.parseInt(s, 16) : 16777215);
    }
    
    public static String mmes(final int n) {
        return ddia(n);
    }
    
    public static String ddia(final int n) {
        String s;
        if (n < 10) {
            s = "0" + n;
        }
        else {
            s = String.valueOf(n);
        }
        return s;
    }
    
    public static Font setaFonte(final Applet applet, final String s, final String s2, final String s3) {
        final String parameter = applet.getParameter(s);
        final String parameter2 = applet.getParameter(s2);
        final String parameter3 = applet.getParameter(s3);
        return new Font((parameter != null) ? parameter : applet.getFont().getName(), (parameter3 == null) ? applet.getFont().getStyle() : (parameter3.equalsIgnoreCase("plain") ? 0 : (parameter3.equalsIgnoreCase("bold") ? 1 : 2)), (parameter2 != null) ? Integer.parseInt(parameter2) : applet.getFont().getSize());
    }
    
    public static boolean strToBool(final String s) {
        return "true".equalsIgnoreCase(s);
    }
    
    public static void abreJanela(final Applet applet, final String s) {
        try {
            applet.getAppletContext().showDocument(new URL(s), "_blank");
        }
        catch (MalformedURLException ex) {
            System.out.println(String.valueOf(ex) + ": " + s);
        }
    }
    
    public static void ordenaPorData(Ultima[] retiraNulls, final int n, int length) {
        if (retiraNulls.length <= 1) {
            return;
        }
        retiraNulls = retiraNulls(retiraNulls);
        length = retiraNulls.length;
        for (int i = length - 2; i >= 0; --i) {
            final Ultima ultima = retiraNulls[i];
            int n2 = i + 1;
            try {
                if (retiraNulls[n2] == null) {
                    System.out.println("j: " + n2);
                }
                while (n2 <= length - 1 && retiraNulls[n2].retornaData().before(ultima.retornaData())) {
                    retiraNulls[n2 - 1] = retiraNulls[n2];
                    ++n2;
                }
                retiraNulls[n2 - 1] = ultima;
            }
            catch (NullPointerException ex) {
                System.out.println("NPE em ordenaPorData");
            }
        }
    }
    
    public static Ultima[] retiraNulls(Ultima[] array) {
        boolean b = false;
        final Vector vector = new Vector<Ultima>();
        for (int i = 0; i < array.length; ++i) {
            if (array[i] == null) {
                b = true;
            }
            else {
                vector.addElement(array[i]);
            }
        }
        if (b) {
            array = new Ultima[vector.size()];
            vector.copyInto(array);
        }
        return array;
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        diasDosMeses = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
    }
}
