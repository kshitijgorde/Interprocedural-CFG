import java.util.Enumeration;
import java.util.Hashtable;
import java.awt.Font;
import java.awt.Color;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class an extends al
{
    public ao a;
    public String b;
    
    public void a(final n n, final Vector vector) {
        n.f = vector.lastElement();
        vector.removeElementAt(vector.size() - 1);
    }
    
    public void a(final n n) {
        int n2 = 0;
        if (super.n.length > 4) {
            n.e = super.n[4];
            n.b(0, 0, super.c - super.k, super.d - super.k);
        }
        if (!super.j) {
            n.e = super.n[0];
            n.c(0, 0, super.c - 1 - super.k, super.d - 1 - super.k);
            n.c(1, 1, super.c - 3 - super.k, super.d - 3 - super.k);
        }
        n.f = super.f;
        n.e = super.n[1];
        if (super.c != null) {
            n.a(super.c, (super.c - super.k - n.f.a(super.c)) / 2, 10 + n.f.e);
            n2 += 10 + n.f.e;
        }
        n.f = super.g;
        n.e = super.n[2];
        int n9;
        if (this.b != null) {
            final at at = new at();
            final Vector<Object> vector = new Vector<Object>();
            final Vector<Object> vector2 = new Vector<Object>();
            final Vector<Object> vector3 = new Vector<Object>();
            f f = null;
            final au au = new au(null);
            au.b(this.b);
            final int n3 = this.a.b + (super.j ? 0 : 2);
            final int n4 = super.c - super.k - this.a.c - (super.j ? 0 : 2);
            int n5 = n3;
            int n6 = n.f.d + this.a.d;
            try {
                while (au.a(at)) {
                    switch (at.a) {
                        case 1: {
                            if (at.c.equals("BR")) {
                                n5 = n3;
                                continue;
                            }
                            if (at.c.equals("Shadow")) {
                                f = new f(0, 0, 0, 255);
                                continue;
                            }
                            if (at.c.equals("Font")) {
                                vector.addElement(n.f.clone());
                                String s = "SansSerif";
                                int n7 = 0;
                                int int1 = 10;
                                if (at.e.containsKey("Name")) {
                                    s = at.e.get("Name");
                                }
                                if (at.e.containsKey("Style")) {
                                    final String s2 = at.e.get("Style");
                                    if (s2.equalsIgnoreCase("plain")) {
                                        n7 = 0;
                                    }
                                    else if (s2.equalsIgnoreCase("bold")) {
                                        n7 = 1;
                                    }
                                    else if (s2.equalsIgnoreCase("italic")) {
                                        n7 = 2;
                                    }
                                }
                                if (at.e.containsKey("Size")) {
                                    int1 = Integer.parseInt(at.e.get("Size"), 10);
                                }
                                final u a = this.a(s, n7, int1);
                                if (a != null) {
                                    n.f = a;
                                    continue;
                                }
                                continue;
                            }
                            else {
                                if (at.c.equals("DialogText")) {
                                    vector.addElement(n.f.clone());
                                    vector2.addElement(n.e.clone());
                                    vector3.addElement(this.a.clone());
                                    continue;
                                }
                                if (at.c.equals("Color")) {
                                    vector2.addElement(n.e.clone());
                                    int a2;
                                    int b;
                                    int c;
                                    if (at.e.containsKey("Value")) {
                                        final int int2 = Integer.parseInt(at.e.get("Value"), 16);
                                        a2 = (int2 & 0xFF0000) >> 16;
                                        b = (int2 & 0xFF00) >> 8;
                                        c = (int2 & 0xFF);
                                    }
                                    else {
                                        a2 = n.e.a;
                                        b = n.e.b;
                                        c = n.e.c;
                                    }
                                    int n8;
                                    if (at.e.containsKey("Alpha")) {
                                        n8 = Integer.parseInt(at.e.get("Alpha"));
                                    }
                                    else {
                                        n8 = n.e.d;
                                    }
                                    n.e = new f(a2, b, c, n8);
                                    continue;
                                }
                                if (at.c.equals("Bold")) {
                                    vector.addElement(n.f.clone());
                                    final Font a3 = this.a(n.f);
                                    if (a3 == null) {
                                        continue;
                                    }
                                    final u a4 = this.a(a3.getName(), 1, a3.getSize());
                                    if (a4 != null) {
                                        n.f = a4;
                                        continue;
                                    }
                                    continue;
                                }
                                else {
                                    if (at.c.equals("Center")) {
                                        vector3.addElement(this.a.clone());
                                        this.a.a();
                                        continue;
                                    }
                                    if (at.c.equals("Right")) {
                                        vector3.addElement(this.a.clone());
                                        if (at.e.containsKey("Pad")) {
                                            this.a.c(Integer.parseInt((String)at.e.get("Pad")));
                                            continue;
                                        }
                                        this.a.a(1);
                                        continue;
                                    }
                                    else {
                                        if (!at.c.equals("Left")) {
                                            continue;
                                        }
                                        vector3.addElement(this.a.clone());
                                        if (at.e.containsKey("Pad")) {
                                            this.a.b(Integer.parseInt((String)at.e.get("Pad")));
                                            continue;
                                        }
                                        this.a.a(-1);
                                        continue;
                                    }
                                }
                            }
                            break;
                        }
                        case 3: {
                            switch (this.a.a) {
                                default: {
                                    continue;
                                }
                                case 0: {
                                    n5 = n3 + (Math.max(n4, n3) - Math.min(n3, n4)) / 2;
                                    this.a(n, at.c, n5 - n.f.a(at.c) / 2, n6, f);
                                    continue;
                                }
                                case -1: {
                                    this.a(n, at.c, n5, n6, f);
                                    n5 += n.f.a(at.c);
                                    continue;
                                }
                                case 1: {
                                    this.a(n, at.c, n4 - n.f.a(at.c), n6, f);
                                    n6 += n.f.d + this.a.d;
                                    n5 = n4;
                                    continue;
                                }
                            }
                            break;
                        }
                        case 2: {
                            if (at.c.equals("BR")) {
                                n6 += n.f.d + this.a.d;
                                n5 = this.a.b + (super.j ? 0 : 2);
                                continue;
                            }
                            if (at.c.equals("Shadow")) {
                                f = null;
                                continue;
                            }
                            if (at.c.equals("Font") || at.c.equals("Bold")) {
                                this.a(n, vector);
                                continue;
                            }
                            if (at.c.equals("DialogText")) {
                                this.a(n, vector);
                                this.c(n, vector2);
                                this.b(n, vector3);
                                continue;
                            }
                            if (at.c.equals("Color")) {
                                this.c(n, vector2);
                                continue;
                            }
                            if (at.c.equals("Left") || at.c.equals("Right")) {
                                this.b(n, vector3);
                                n5 = n3;
                                continue;
                            }
                            if (at.c.equals("Center")) {
                                this.b(n, vector3);
                                n6 += n.f.d + this.a.d;
                                n5 = n3;
                                continue;
                            }
                            continue;
                        }
                        default: {
                            continue;
                        }
                    }
                }
            }
            catch (CloneNotSupportedException ex) {
                ex.printStackTrace();
                super.m.b.a("clone", "clone not supported in FancyDialog");
            }
            n9 = n2 + n6;
        }
        else {
            n9 = n2 + this.a(n, new Rectangle(0, n2 + n.f.d, super.c - super.k, super.d - 40), super.e, -1, 0);
        }
        if (super.b != 2 && super.d != null) {
            n.f = super.f;
            n.e = super.n[3];
            n.a(super.d, (super.c - super.k - n.f.a(super.d)) / 2, n9 + 50 + n.f.e);
        }
        if (super.k > 0) {
            if (super.n.length > 5) {
                n.e = super.n[5];
            }
            else {
                n.a(Color.black);
            }
            n.b(super.c - super.k, super.k, super.k, super.d - super.k);
            n.b(super.k, super.d - super.k, super.c - super.k * 2, super.k);
        }
    }
    
    public an(final k k, final b b, final String s, final String s2, final String s3, final int n) {
        super(k, b, s, s2, s3, n);
        this.a = new Cloneable() {
            public int a;
            public int b;
            public int c;
            public int d;
            public final /* synthetic */ an e;
            
            public void a() {
                this.a(0);
            }
            
            {
                an.this.getClass();
                this.a = -1;
            }
            
            public int a(final int a) {
                switch (a) {
                    case -1:
                    case 0:
                    case 1: {
                        this.a = a;
                        break;
                    }
                }
                return this.a;
            }
            
            public void b(final int b) {
                this.a(-1);
                this.b = b;
            }
            
            public void c(final int c) {
                this.a(1);
                this.c = c;
            }
            
            public synchronized Object clone() throws CloneNotSupportedException {
                final Cloneable cloneable = new Cloneable() {
                    public int a;
                    public int b;
                    public int c;
                    public int d;
                    public final /* synthetic */ an e = an.this;
                    
                    public void a() {
                        this.a(0);
                    }
                    
                    {
                        an.this.getClass();
                        this.a = -1;
                    }
                    
                    public int a(final int a) {
                        switch (a) {
                            case -1:
                            case 0:
                            case 1: {
                                this.a = a;
                                break;
                            }
                        }
                        return this.a;
                    }
                    
                    public void b(final int b) {
                        this.a(-1);
                        this.b = b;
                    }
                    
                    public void c(final int c) {
                        this.a(1);
                        this.c = c;
                    }
                };
                cloneable.a = this.a;
                cloneable.b = this.b;
                cloneable.c = this.c;
                cloneable.d = this.d;
                return cloneable;
            }
        };
    }
    
    public void a(final n n, final String s, final int n2, final int n3, final f e) {
        if (e != null) {
            final f e2 = n.e;
            n.e = e;
            n.a(s, n2 + 1, n3 + 1);
            n.e = e2;
        }
        n.a(s, n2, n3);
    }
    
    public void b(final n n, final Vector vector) {
        this.a = vector.lastElement();
        vector.removeElementAt(vector.size() - 1);
    }
    
    public Font a(final u u) {
        Font font = null;
        final Hashtable al = super.m.b.al;
        Font font2;
        for (Enumeration keys = al.keys(); keys.hasMoreElements() && font == null; font = font2) {
            font2 = keys.nextElement();
            if (al.get(font2).equals(u)) {}
        }
        return font;
    }
    
    public u a(final String s, final int n, final int n2) {
        u u = null;
        Font font = null;
        final Enumeration keys = super.m.b.al.keys();
        while (keys.hasMoreElements() && u == null) {
            final Font font2 = keys.nextElement();
            if (font2.getName().equals(s)) {
                if (font2.getStyle() == n) {
                    if (font2.getSize() == n2) {
                        u = super.m.b.a(font2);
                    }
                    else if (font == null) {
                        font = font2;
                    }
                    else {
                        if (Math.abs(font2.getSize() - n2) >= Math.abs(font.getSize() - n2)) {
                            continue;
                        }
                        font = font2;
                    }
                }
                else if (font == null) {
                    font = font2;
                }
                else if (n == 0) {
                    if (font.getStyle() != 2 || font2.getStyle() != 1) {
                        continue;
                    }
                    font = font2;
                }
                else if (n == 2) {
                    if (font.getStyle() != 0 || font2.getStyle() != 1) {
                        continue;
                    }
                    font = font2;
                }
                else {
                    if (n != 1 || font.getStyle() != 0 || font2.getStyle() != 2) {
                        continue;
                    }
                    font = font2;
                }
            }
        }
        if (u == null) {
            if (font != null) {
                u = super.m.b.a(font);
            }
            else {
                u = super.m.b.b(s, n, n2);
            }
        }
        return u;
    }
    
    public void c(final n n, final Vector vector) {
        n.e = vector.lastElement();
        vector.removeElementAt(vector.size() - 1);
    }
    
    public void b(final String b, final int n, final int n2) {
        this.b = b;
    }
}
