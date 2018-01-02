// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.util;

import java.io.Reader;

public class c
{
    private int a;
    private StringBuffer b;
    private Reader c;
    
    public c() {
        this.a = 0;
        this.b = new StringBuffer(1024);
    }
    
    public void a(final Reader c) {
        this.c = c;
    }
    
    public b a() {
        while (true) {
            final int read = this.c.read();
            if (read == -1) {
                return this.b();
            }
            final b a = this.a((char)read);
            if (a != null) {
                return a;
            }
        }
    }
    
    private b a(final char c) {
        switch (this.a) {
            case 0: {
                if (c == '<') {
                    b b;
                    if (this.b.length() > 0) {
                        b = new b(this, this.b.toString());
                    }
                    else {
                        b = null;
                    }
                    this.b.setLength(0);
                    this.b.append(c);
                    this.a = 1;
                    return b;
                }
                break;
            }
            case 1: {
                if (c == '!') {
                    this.a = 2;
                    break;
                }
                this.a = 6;
                break;
            }
            case 2: {
                if (c == '>') {
                    this.b.append(c);
                    final b b2 = new b(this, this.b.toString());
                    this.a = 0;
                    return b2;
                }
                if (c == '!') {
                    this.a = 3;
                    break;
                }
                break;
            }
            case 3: {
                if (c == '-') {
                    this.a = 4;
                    break;
                }
                this.a = 2;
                break;
            }
            case 4: {
                if (c == '-') {
                    this.a = 5;
                    break;
                }
                break;
            }
            case 5: {
                if (c == '-') {
                    this.a = 2;
                    break;
                }
                this.a = 4;
                break;
            }
            case 6: {
                if (c == '>') {
                    this.b.append(c);
                    final b b3 = new b(this, this.b.toString());
                    this.b.setLength(0);
                    this.a = 0;
                    return b3;
                }
                if (c == '\'') {
                    this.a = 8;
                    break;
                }
                if (c == '\"') {
                    this.a = 7;
                    break;
                }
                break;
            }
            case 8: {
                if (c == '\'') {
                    this.a = 6;
                    break;
                }
                break;
            }
            case 7: {
                if (c == '\"') {
                    this.a = 6;
                    break;
                }
                break;
            }
        }
        this.b.append(c);
        return null;
    }
    
    private b b() {
        b b;
        if (this.b.length() > 0) {
            b = new b(this, this.b.toString());
        }
        else {
            b = null;
        }
        this.b.setLength(0);
        this.a = 0;
        return b;
    }
}
