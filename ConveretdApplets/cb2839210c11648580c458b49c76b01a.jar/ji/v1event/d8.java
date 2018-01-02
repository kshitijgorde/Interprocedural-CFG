// 
// Decompiled by Procyon v0.5.30
// 

package ji.v1event;

import ji.v1base.jiPanel;
import ji.v1base.bz;
import java.awt.Point;
import java.awt.Component;
import ji.util.i;
import java.awt.Dimension;
import java.util.EventObject;

public class d8 extends EventObject
{
    private int a;
    private int b;
    private int c;
    boolean d;
    private Dimension e;
    private boolean f;
    private boolean g;
    private String h;
    
    public d8(final Object o, final int n, final int n2, final int n3) {
        this(o, n, n2, n3, "", true);
    }
    
    public d8(final Object o, final int a, final int b, final int c, final String h, final boolean f) {
        super(o);
        this.a = 0;
        this.b = 0;
        this.c = 0;
        this.d = false;
        this.e = new Dimension(8, 23);
        this.f = true;
        this.g = false;
        try {
            this.a = a;
            this.b = b;
            this.c = c;
            this.f = f;
            this.h = h;
            Point g = null;
            if (!i.c(142)) {
                g = this.g();
            }
            if (o instanceof Component) {
                if (g == null) {
                    if (this.d) {
                        return;
                    }
                }
                try {
                    switch (((Component)o).getCursor().getType()) {
                        case 12: {
                            this.e.width = 9;
                            this.e.height = 23;
                            break;
                        }
                        case 1: {
                            this.e.width = 7;
                            this.e.height = 7;
                            break;
                        }
                    }
                }
                catch (Exception ex) {}
            }
        }
        catch (Exception ex2) {}
    }
    
    public boolean a() {
        return this.f;
    }
    
    public String b() {
        return this.h;
    }
    
    public String toString() {
        return String.valueOf(String.valueOf(new StringBuffer("jiToolTipEvent(").append(this.a(this.a)).append(", x = ").append(this.b).append(", y = ").append(this.c).append(", source = ").append(this.getSource()).append(")")));
    }
    
    private String a(final int n) {
        switch (n) {
            case 1: {
                return "SHOW";
            }
            case 2: {
                return "HIDE";
            }
            case 3: {
                return "ENABLE";
            }
            case 4: {
                return "DISABLE";
            }
            default: {
                return "Unknown";
            }
        }
    }
    
    public int c() {
        return this.a;
    }
    
    public Dimension d() {
        return this.e;
    }
    
    public Point e() {
        if (i.c(142)) {
            this.g();
        }
        return new Point(this.b, this.c);
    }
    
    private Point g() {
        Point point = null;
        if (!this.d) {
            this.d = true;
            if (super.source instanceof bz) {
                try {
                    point = ((Component)super.source).getLocationOnScreen();
                    if (point != null) {
                        this.b += point.x;
                        this.c += point.y;
                    }
                }
                catch (Exception ex) {}
            }
            if (super.source instanceof jiPanel) {
                try {
                    point = ((Component)super.source).getLocationOnScreen();
                    if (point != null) {
                        this.b += point.x;
                        this.c += point.y;
                    }
                }
                catch (Exception ex2) {}
            }
        }
        return point;
    }
    
    public boolean f() {
        return this.g;
    }
}
