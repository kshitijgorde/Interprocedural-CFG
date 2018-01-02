// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a.c.a;

import java.util.Enumeration;
import java.util.Vector;

public class a
{
    public static final int b = -1;
    public static final int if = 0;
    public static final int byte = 1;
    public static final int f = 2;
    public static final int d = 3;
    public static final int for = 4;
    public static final int a = 5;
    protected transient b null;
    protected Vector int;
    private int else;
    private int long;
    private int try;
    private int do;
    private int case;
    private int c;
    private int void;
    private int e;
    private int char;
    private int new;
    public boolean goto;
    
    public a() {
        this.null = null;
        this.int = new Vector();
        this.goto = false;
        final int n = -1;
        this.long = n;
        this.else = n;
        final int n2 = -1;
        this.case = n2;
        this.do = n2;
        final boolean b = false;
        this.e = (b ? 1 : 0);
        this.void = (b ? 1 : 0);
        this.char = 0;
    }
    
    public void a(final c c) {
        this.int.addElement(c);
    }
    
    protected void a() {
        final Enumeration<c> elements = this.int.elements();
        while (elements.hasMoreElements()) {
            final c nextElement = elements.nextElement();
            if (nextElement instanceof c) {
                if (this.null == null) {
                    this.null = new b(this);
                }
                nextElement.a(this.null);
            }
        }
    }
    
    public int byte() {
        return this.do;
    }
    
    public int do() {
        return this.void;
    }
    
    public int if() {
        return this.else;
    }
    
    public int a(final String s) {
        if (s.equals("toolbar")) {
            return this.char;
        }
        if (s.equals("hotspots")) {
            return this.do;
        }
        if (s.equals("magnifier")) {
            return this.void;
        }
        if (s.equals("zoomin")) {
            if (this.else == -1) {
                return -1;
            }
            return (this.else == 2) ? 1 : 0;
        }
        else if (s.equals("zoomout")) {
            if (this.else == -1) {
                return -1;
            }
            return (this.else == 3) ? 1 : 0;
        }
        else if (s.equals("pan")) {
            if (this.else == -1) {
                return -1;
            }
            return (this.else == 4) ? 1 : 0;
        }
        else {
            if (!s.equals("rotate")) {
                return -1;
            }
            if (this.else == -1) {
                return -1;
            }
            return (this.else == 5) ? 1 : 0;
        }
    }
    
    public int char() {
        return this.char;
    }
    
    public void if(final c c) {
        this.int.removeElement(c);
    }
    
    public void int() {
        this.else = this.long;
        this.void = this.e;
        this.a();
    }
    
    public void for() {
        this.goto = false;
    }
    
    public void for(final int case1) {
        this.try(this.case = case1);
    }
    
    public void int(final int e) {
        this.new(this.e = e);
    }
    
    public void if(final int long1) {
        this.byte(this.long = long1);
    }
    
    public void do(final int new1) {
        this.case(this.new = new1);
    }
    
    public synchronized void try(final int do1) {
        this.do = do1;
        this.a();
    }
    
    public synchronized void new(final int void1) {
        if (this.void == 0) {
            this.try = this.else;
            this.else = -1;
            this.c = this.do;
            this.do = this.case;
        }
        else {
            this.else = this.try;
            this.do = this.c;
        }
        this.void = void1;
        this.a();
    }
    
    protected synchronized void byte(final int else1) {
        this.else = else1;
        this.a();
    }
    
    public void new() {
        this.goto = true;
    }
    
    protected synchronized void case(final int char1) {
        this.char = char1;
        this.a();
    }
    
    public void a(final int n) {
        if (this.else != -1) {
            this.byte(n);
        }
    }
    
    public void else() {
        if (this.do != -1) {
            if (this.do == 1) {
                this.try(0);
            }
            else {
                this.try(1);
            }
        }
    }
    
    public void case() {
        if (this.void != -1) {
            if (this.void == 1) {
                this.new(0);
            }
            else {
                this.new(1);
            }
        }
    }
    
    public void try() {
        if (this.char != -1) {
            if (this.char == 1) {
                this.case(0);
            }
            else {
                this.case(1);
            }
        }
    }
}
