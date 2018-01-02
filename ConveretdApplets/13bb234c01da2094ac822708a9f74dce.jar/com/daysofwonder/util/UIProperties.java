// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.util;

import java.util.Enumeration;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.util.Vector;

public class UIProperties
{
    private Vector a;
    private Vector b;
    private int c;
    
    public UIProperties() {
        this.a = new Vector();
        this.b = new Vector();
        this.c = 0;
    }
    
    public UIProperties(final InputStream inputStream, final String s) {
        this.a = new Vector();
        this.b = new Vector();
        this.c = 0;
        this.a(inputStream, s);
    }
    
    public void a(final InputStream inputStream, final String s) {
        BufferedReader bufferedReader;
        if (s != null) {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream, s));
        }
        else {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        }
        String line;
        do {
            line = bufferedReader.readLine();
            if (line != null) {
                if (line.length() > 0 && line.charAt(0) == ';') {
                    continue;
                }
                final int index = line.indexOf("=");
                if (index == -1) {
                    continue;
                }
                final String trim = line.substring(0, index).trim();
                final String d = this.d(line.substring(index + 1).trim());
                final int index2 = this.a.indexOf(trim);
                if (index2 == -1) {
                    this.a.addElement(trim);
                    this.b.addElement(d);
                }
                else {
                    this.b.setElementAt(d, index2);
                }
            }
        } while (line != null);
    }
    
    public Object a(final Object o) {
        final int index = this.a.indexOf(o);
        if (index == -1) {
            return null;
        }
        return this.b.elementAt(index);
    }
    
    public String a(final String s) {
        final int index = this.a.indexOf(s);
        if (index != -1) {
            return (String)this.b.elementAt(index);
        }
        return null;
    }
    
    public String b(final String s) {
        final String a = this.a(s);
        if (a == null) {
            System.out.println("key: " + s + " has null value");
        }
        return a;
    }
    
    public String a(final String s, final Object[] array) {
        return w.a(this.a(s), array);
    }
    
    public boolean c(final String s) {
        final String a = this.a(s);
        return a != null && (a.equalsIgnoreCase("true") || a.equalsIgnoreCase("yes") || a.equalsIgnoreCase("on"));
    }
    
    public int a(final String s, final int n) {
        final String a = this.a(s);
        if (a != null) {
            return Integer.parseInt(a);
        }
        return n;
    }
    
    public Enumeration a() {
        return this.a.elements();
    }
    
    private String d(final String s) {
        final StringBuffer sb = new StringBuffer();
        if (s != null) {
            for (int i = 0; i < s.length(); ++i) {
                final char char1 = s.charAt(i);
                if (char1 == '\\') {
                    char char2 = s.charAt(++i);
                    if (char2 == 'u') {
                        int n = 0;
                        for (int j = 0; j < 4; ++j) {
                            final char char3 = s.charAt(++i);
                            switch (char3) {
                                case 48:
                                case 49:
                                case 50:
                                case 51:
                                case 52:
                                case 53:
                                case 54:
                                case 55:
                                case 56:
                                case 57: {
                                    n = (n << 4) + char3 - 48;
                                    break;
                                }
                                case 97:
                                case 98:
                                case 99:
                                case 100:
                                case 101:
                                case 102: {
                                    n = (n << 4) + 10 + char3 - 97;
                                    break;
                                }
                                case 65:
                                case 66:
                                case 67:
                                case 68:
                                case 69:
                                case 70: {
                                    n = (n << 4) + 10 + char3 - 65;
                                    break;
                                }
                                default: {
                                    throw new IllegalArgumentException("Malformed \\uxxxx encoding.");
                                }
                            }
                        }
                        sb.append((char)n);
                    }
                    else {
                        if (char2 == 't') {
                            char2 = '\t';
                        }
                        else if (char2 == 'r') {
                            char2 = '\r';
                        }
                        else if (char2 == 'n') {
                            char2 = '\n';
                        }
                        else if (char2 == 'f') {
                            char2 = '\f';
                        }
                        sb.append(char2);
                    }
                }
                else {
                    sb.append(char1);
                }
            }
        }
        return sb.toString();
    }
}
