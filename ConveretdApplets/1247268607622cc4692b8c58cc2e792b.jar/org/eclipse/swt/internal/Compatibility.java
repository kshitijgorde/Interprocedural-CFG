// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.io.BufferedInputStream;
import java.util.zip.InflaterInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.util.zip.DeflaterOutputStream;
import java.io.OutputStream;
import org.eclipse.swt.SWT;
import java.io.File;
import java.util.ResourceBundle;

public final class Compatibility
{
    public static double PI;
    static double toRadians;
    private static ResourceBundle msgs;
    
    static {
        Compatibility.PI = Math.PI;
        Compatibility.toRadians = Compatibility.PI / 180.0;
        Compatibility.msgs = null;
    }
    
    public static int cos(final int n, final int n2) {
        return (int)(Math.cos(n * Compatibility.toRadians) * n2);
    }
    
    public static int sin(final int n, final int n2) {
        return (int)(Math.sin(n * Compatibility.toRadians) * n2);
    }
    
    public static int ceil(final int n, final int n2) {
        return (int)Math.ceil(n / n2);
    }
    
    public static boolean fileExists(final String s, final String s2) {
        return new File(s, s2).exists();
    }
    
    public static int floor(final int n, final int n2) {
        return (int)Math.floor(n / n2);
    }
    
    public static int round(final int n, final int n2) {
        return Math.round(n / n2);
    }
    
    public static int pow2(final int n) {
        if (n >= 1 && n <= 30) {
            return 2 << n - 1;
        }
        if (n != 0) {
            SWT.error(6);
        }
        return 1;
    }
    
    public static OutputStream newDeflaterOutputStream(final OutputStream outputStream) throws IOException {
        return new DeflaterOutputStream(outputStream);
    }
    
    public static InputStream newFileInputStream(final String s) throws IOException {
        return new FileInputStream(s);
    }
    
    public static OutputStream newFileOutputStream(final String s) throws IOException {
        return new FileOutputStream(s);
    }
    
    public static InputStream newInflaterInputStream(final InputStream inputStream) throws IOException {
        return new BufferedInputStream(new InflaterInputStream(inputStream));
    }
    
    public static boolean isLetter(final char c) {
        return Character.isLetter(c);
    }
    
    public static boolean isLetterOrDigit(final char c) {
        return Character.isLetterOrDigit(c);
    }
    
    public static boolean isSpaceChar(final char c) {
        return Character.isSpaceChar(c);
    }
    
    public static boolean isWhitespace(final char c) {
        return Character.isWhitespace(c);
    }
    
    public static void exec(final String s) throws IOException {
        Runtime.getRuntime().exec(s);
    }
    
    public static void exec(final String[] array) throws IOException {
        Runtime.getRuntime().exec(array);
    }
    
    public static void exec(final String[] array, final String[] array2, final String s) throws IOException {
        Runtime.getRuntime().exec(array, null, (s != null) ? new File(s) : null);
    }
    
    public static String getMessage(final String s) {
        String s2 = s;
        if (s == null) {
            SWT.error(4);
        }
        if (Compatibility.msgs == null) {
            try {
                Compatibility.msgs = ResourceBundle.getBundle("org.eclipse.swt.internal.SWTMessages");
            }
            catch (MissingResourceException ex) {
                s2 = String.valueOf(s) + " (no resource bundle)";
            }
        }
        if (Compatibility.msgs != null) {
            try {
                s2 = Compatibility.msgs.getString(s);
            }
            catch (MissingResourceException ex2) {}
        }
        return s2;
    }
    
    public static String getMessage(final String s, final Object[] array) {
        String s2 = s;
        if (s == null || array == null) {
            SWT.error(4);
        }
        if (Compatibility.msgs == null) {
            try {
                Compatibility.msgs = ResourceBundle.getBundle("org.eclipse.swt.internal.SWTMessages");
            }
            catch (MissingResourceException ex) {
                s2 = String.valueOf(s) + " (no resource bundle)";
            }
        }
        if (Compatibility.msgs != null) {
            try {
                final MessageFormat messageFormat = new MessageFormat("");
                messageFormat.applyPattern(Compatibility.msgs.getString(s));
                s2 = messageFormat.format(array);
            }
            catch (MissingResourceException ex2) {}
        }
        return s2;
    }
    
    public static void interrupt() {
        Thread.currentThread().interrupt();
    }
    
    public static boolean equalsIgnoreCase(final String s, final String s2) {
        return s.equalsIgnoreCase(s2);
    }
}
