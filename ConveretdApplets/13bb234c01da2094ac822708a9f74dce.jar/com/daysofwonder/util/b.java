// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.util;

import java.util.Hashtable;

public class b
{
    private String b;
    private String c;
    private Hashtable d;
    final /* synthetic */ c a;
    
    public b(final c a, final String b) {
        this.a = a;
        this.b = b;
        this.c = null;
        this.d = null;
    }
    
    public String a() {
        if (this.c == null) {
            if (!this.d()) {
                return null;
            }
            final StringBuffer sb = new StringBuffer(8);
            for (int i = 1; i < this.b.length(); ++i) {
                final char char1 = this.b.charAt(i);
                if (i != 1 || char1 != '/') {
                    if (Character.isWhitespace(char1)) {
                        break;
                    }
                    if (char1 == '>') {
                        break;
                    }
                    sb.append(char1);
                }
            }
            this.c = sb.toString().toLowerCase();
        }
        return this.c;
    }
    
    public String a(final String s) {
        this.g();
        return this.d.get(s.toLowerCase());
    }
    
    public boolean b() {
        return this.b.charAt(0) != '<';
    }
    
    public String c() {
        return this.b;
    }
    
    public boolean d() {
        return this.b.charAt(0) == '<' && (this.b.length() == 0 || this.b.charAt(1) != '!');
    }
    
    public boolean e() {
        return this.b.charAt(0) == '<' && (this.b.length() == 0 || (this.b.charAt(1) != '!' && this.b.charAt(1) != '/'));
    }
    
    public boolean f() {
        return this.b.charAt(0) == '<' && this.b.length() != 0 && this.b.charAt(1) == '/';
    }
    
    private int a(int i, final String s, final StringBuffer sb) {
        while (i < this.b.length()) {
            final char char1 = this.b.charAt(i);
            if (!Character.isWhitespace(char1)) {
                break;
            }
            if (s.indexOf(char1) != -1) {
                break;
            }
            if (sb != null) {
                sb.append(char1);
            }
            ++i;
        }
        return i;
    }
    
    private int b(int i, final String s, final StringBuffer sb) {
        while (i < this.b.length()) {
            final char char1 = this.b.charAt(i);
            if (Character.isWhitespace(char1)) {
                break;
            }
            if (s.indexOf(char1) != -1) {
                break;
            }
            if (sb != null) {
                sb.append(char1);
            }
            ++i;
        }
        return i;
    }
    
    private void g() {
        if (this.d != null) {
            return;
        }
        final StringBuffer sb = new StringBuffer(256);
        this.d = new Hashtable();
        int i;
        for (i = 0; i < this.b.length(); ++i) {
            final char char1 = this.b.charAt(i);
            if (Character.isWhitespace(char1)) {
                break;
            }
            if (char1 == '>') {
                break;
            }
        }
        int j = this.a(i, ">", null);
        while (j < this.b.length()) {
            String s = "";
            if (this.b.charAt(j) == '>') {
                break;
            }
            sb.setLength(0);
            final int b = this.b(j, "=>", sb);
            final String string = sb.toString();
            j = this.a(b, ">", null);
            if (j < this.b.length() && this.b.charAt(j) == '=') {
                j = this.a(j + 1, ">", null);
                if (j < this.b.length()) {
                    final char char2 = this.b.charAt(j);
                    int n;
                    if (char2 == '\'' || char2 == '\"') {
                        ++j;
                        final int index = this.b.indexOf(char2, j);
                        if (index == -1) {
                            s = this.b.substring(j);
                            n = this.b.length();
                        }
                        else {
                            s = this.b.substring(j, index);
                            n = index + 1;
                        }
                    }
                    else {
                        sb.setLength(0);
                        n = this.b(j, ">", sb);
                        s = sb.toString();
                    }
                    j = this.a(n, ">", null);
                }
            }
            this.d.put(string.toLowerCase(), s);
        }
    }
}
