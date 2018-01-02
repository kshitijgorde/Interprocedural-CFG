// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.applet;

import java.util.Hashtable;
import java.lang.reflect.Array;
import java.util.Vector;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public final class k
{
    private String a;
    private String b;
    private Properties c;
    private URL d;
    private URL e;
    
    public k(final String s, String substring, String s2) {
        if (s2.equalsIgnoreCase("file")) {
            s2 = "http";
        }
        if (substring.endsWith("/")) {
            substring = substring.substring(0, substring.length() - 1);
        }
        this.a = s2 + "://" + substring + "/Messages_" + s + ".properties";
        this.b = s2 + "://" + substring + "/Messages_de.properties";
        try {
            this.d = new URL(this.a);
            this.e = new URL(this.b);
        }
        catch (MalformedURLException ex) {}
    }
    
    private void a(final URL url) {
        PushbackInputStream pushbackInputStream = null;
        BufferedReader bufferedReader = null;
        try {
            pushbackInputStream = new PushbackInputStream(url.openStream(), 4);
            final byte[] array = new byte[4];
            final int read = pushbackInputStream.read();
            String s = null;
            int n = 0;
            int n2 = 0;
            if (read != -1) {
                array[0] = (byte)read;
                ++n;
                final int read2;
                if ((read2 = pushbackInputStream.read()) != -1) {
                    array[1] = (byte)read2;
                    ++n;
                    if (array[0] == -2 && array[1] == -1) {
                        s = "UTF-16BE";
                        n2 = 2;
                    }
                    else if (array[0] == -17 && array[1] == -69) {
                        final int read3;
                        if ((read3 = pushbackInputStream.read()) != -1) {
                            array[2] = (byte)read3;
                            ++n;
                            if (array[2] == -65) {
                                s = "UTF-8";
                                n2 = 3;
                            }
                        }
                    }
                    else if (array[0] == 0 && array[1] == 0) {
                        final int read4;
                        if ((read4 = pushbackInputStream.read()) != -1) {
                            array[2] = (byte)read4;
                            ++n;
                            final int read5;
                            if ((read5 = pushbackInputStream.read()) != -1) {
                                array[3] = (byte)read5;
                                ++n;
                                if (array[2] == -2 && array[3] == -1) {
                                    s = "UTF-32BE";
                                    n2 = 4;
                                }
                            }
                        }
                    }
                    else if (array[0] == -1 && array[1] == -2) {
                        s = "UTF-16LE";
                        n2 = 2;
                        final int read6;
                        if ((read6 = pushbackInputStream.read()) != -1) {
                            array[2] = (byte)read6;
                            ++n;
                            final int read7;
                            if ((read7 = pushbackInputStream.read()) != -1) {
                                array[3] = (byte)read7;
                                ++n;
                                if (array[2] == 0 && array[3] == 0) {
                                    s = "UTF-32LE";
                                    n2 = 4;
                                }
                            }
                        }
                    }
                }
            }
            if (s == null) {
                pushbackInputStream.unread(array, 0, n);
            }
            else {
                final int n3 = n2;
                pushbackInputStream.unread(array, n3, n - n3);
            }
            this.c = new Properties();
            if (s == null) {
                this.c.load(pushbackInputStream);
            }
            else {
                bufferedReader = new BufferedReader(new InputStreamReader(pushbackInputStream, s));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    final char char1;
                    if (line.length() > 0 && (char1 = line.charAt(0)) != '#' && char1 != '!') {
                        int n4;
                        if ((n4 = line.indexOf("=")) == -1) {
                            n4 = line.indexOf(":");
                        }
                        ((Hashtable<String, String>)this.c).put(c((n4 != -1) ? line.substring(0, n4) : line), c((n4 != -1) ? line.substring(n4 + 1) : ""));
                    }
                }
            }
            pushbackInputStream.close();
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
        catch (Exception ex) {
            EventimApplet.a("Failed to create properties: " + ex, 3);
        }
        finally {
            if (pushbackInputStream != null) {
                pushbackInputStream.close();
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
    }
    
    public final String a(final String s, final String[] array) {
        if (array == null) {
            return this.a(s);
        }
        return b(this.a(s), array);
    }
    
    public final String a(final String s) {
        String property = null;
        try {
            if (this.c == null) {
                this.a(this.d);
            }
            if (this.c == null) {
                this.a(this.e);
            }
            if ((property = this.c.getProperty(s)) == null || property.length() == 0) {
                EventimApplet.a("Kein Text f\u00fcr " + s + " vorhanden", 3);
                property = " ";
            }
        }
        catch (Exception ex) {
            EventimApplet.a("Fehler beim Laden der Properties!", 3);
        }
        return property;
    }
    
    private static String b(String string, final String[] array) {
        if ((string = string) != null && array != null) {
            try {
                final StringBuffer sb = new StringBuffer();
                for (int i = 0; i < string.length(); ++i) {
                    final char char1;
                    if ((char1 = string.charAt(i)) == '{') {
                        final StringBuffer sb2 = new StringBuffer();
                        ++i;
                        char char2;
                        while (i < string.length() && (char2 = string.charAt(i)) != '}') {
                            sb2.append(char2);
                            ++i;
                        }
                        sb.append(array[Integer.parseInt(sb2.toString())]);
                    }
                    else {
                        sb.append(char1);
                    }
                }
                string = sb.toString();
            }
            catch (Exception ex) {
                return null;
            }
        }
        return string;
    }
    
    public final boolean b(final String s) {
        try {
            if (this.c == null) {
                this.a(this.d);
            }
            if (this.c == null) {
                this.a(this.e);
            }
            final String property;
            return (property = this.c.getProperty(s)) != null && property.length() != 0;
        }
        catch (Exception ex) {
            EventimApplet.a("Fehler beim Laden der Properties!", 3);
            return false;
        }
    }
    
    public static String[] a(final String s, final String s2) {
        if (s != null) {
            int n = 0;
            int n2 = 0;
            int i = 0;
            while (i < s.length()) {
                if (s.indexOf(s2, n) != -1) {
                    final int index = s.indexOf(s2, n);
                    i += s.substring(n, index).length();
                    ++n2;
                    n = index + 1;
                }
                else {
                    ++n2;
                    i = s.length();
                }
            }
            final String[] array = new String[n2];
            int n3 = 0;
            int j = 0;
            while (j < n2) {
                if (s.indexOf(s2, n3) != -1) {
                    final int index2 = s.indexOf(s2, n3);
                    array[j] = s.substring(n3, index2);
                    n3 = index2 + 1;
                    ++j;
                }
                else {
                    array[j] = s.substring(n3, s.length());
                    j = n2;
                }
            }
            return array;
        }
        return null;
    }
    
    private static String c(final String s) {
        final StringBuffer sb = new StringBuffer(s.length());
        int i = 0;
        while (i < s.length()) {
            char c;
            if ((c = s.charAt(i++)) == '\\') {
                if ((c = s.charAt(i++)) == 't') {
                    c = '\t';
                }
                else if (c == 'r') {
                    c = '\r';
                }
                else if (c == 'n') {
                    c = '\n';
                }
                else if (c == 'f') {
                    c = '\f';
                }
            }
            sb.append(c);
        }
        return sb.toString();
    }
    
    public k() {
    }
    
    public static boolean a() {
        final String lowerCase = System.getProperty("os.name").toLowerCase();
        final String property = System.getProperty("java.version");
        return lowerCase.indexOf("windows") < 0 || (property.length() >= 3 && property.substring(0, 3).compareTo("1.5") >= 0);
    }
    
    public static int a(int i, int n) {
        while (i != 0) {
            if (n == 0) {
                return i;
            }
            if (i > n) {
                final int n2 = n;
                n = i;
                i = n2;
            }
            else {
                if (i == n) {
                    return i;
                }
                final int n3 = i;
                n -= i;
                i = n3;
            }
        }
        return n;
    }
    
    public static boolean a(final int n, final int[] array) {
        if (array != null) {
            for (int i = 0; i < array.length; ++i) {
                if (n == array[i]) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static Object[] a(final Vector vector, Object[] array) {
        final int size = vector.size();
        if (array.length < size) {
            array = (Object[])Array.newInstance(array.getClass().getComponentType(), size);
        }
        for (int i = 0; i < array.length; ++i) {
            array[i] = vector.elementAt(i);
        }
        if (array.length > size) {
            array[size] = null;
        }
        return array;
    }
}
