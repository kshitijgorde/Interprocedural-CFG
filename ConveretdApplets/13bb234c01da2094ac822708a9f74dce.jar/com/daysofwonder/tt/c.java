// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.tt;

import com.daysofwonder.req.k;
import com.daysofwonder.util.UIProperties;
import java.util.StringTokenizer;
import java.util.Vector;
import java.awt.Point;

public class c
{
    private e a;
    private String b;
    private String c;
    private Point d;
    private Point e;
    private Point f;
    private Vector g;
    private boolean h;
    private int i;
    
    public c() {
        this.d = new Point(0, 0);
        this.e = new Point(0, 0);
        this.f = new Point(0, 0);
        this.g = new Vector();
    }
    
    public c(final e a) {
        this.d = new Point(0, 0);
        this.e = new Point(0, 0);
        this.f = new Point(0, 0);
        this.g = new Vector();
        this.a = a;
    }
    
    public boolean a(final String s) {
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
            this.b = stringTokenizer.nextToken();
            if (stringTokenizer.hasMoreTokens()) {
                this.d.x = Integer.parseInt(stringTokenizer.nextToken());
                this.d.y = Integer.parseInt(stringTokenizer.nextToken());
                final Point e = this.e;
                final Point f = this.f;
                final int int1 = Integer.parseInt(stringTokenizer.nextToken());
                f.x = int1;
                e.x = int1;
                final Point e2 = this.e;
                final Point f2 = this.f;
                final int int2 = Integer.parseInt(stringTokenizer.nextToken());
                f2.y = int2;
                e2.x = int2;
                if (this.a.g()) {
                    this.e.x = Integer.parseInt(stringTokenizer.nextToken());
                    this.e.y = Integer.parseInt(stringTokenizer.nextToken());
                }
            }
            else {
                this.h = true;
            }
        }
        catch (Exception ex) {
            return false;
        }
        return true;
    }
    
    public synchronized String a() {
        if (this.c == null) {
            final UIProperties i = this.a.i();
            if (i != null) {
                this.c = i.a(this.b);
            }
            if (this.c == null) {
                this.c = this.b;
            }
        }
        return this.c;
    }
    
    public boolean b() {
        return this.h;
    }
    
    public Point c() {
        return this.d;
    }
    
    public Point d() {
        return this.f;
    }
    
    public Point e() {
        return this.e;
    }
    
    public Point a(final int n) {
        switch (n) {
            case 0: {
                return this.c();
            }
            case 1: {
                return this.d();
            }
            case 2: {
                return this.e();
            }
            default: {
                return this.d();
            }
        }
    }
    
    public String toString() {
        return this.b;
    }
    
    public int f() {
        return this.i;
    }
    
    public void b(final int i) {
        this.i = i;
    }
    
    public String g() {
        return this.b;
    }
    
    public void a(final o o) {
        this.g.addElement(o);
    }
    
    public void a(final k k) {
        k.writeByte(this.i);
    }
    
    public void b(final k k) {
        this.i = k.readByte();
    }
}
