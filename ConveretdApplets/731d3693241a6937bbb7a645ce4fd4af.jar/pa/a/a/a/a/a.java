// 
// Decompiled by Procyon v0.5.30
// 

package pa.a.a.a.a;

import java.applet.AppletContext;
import java.awt.Panel;

public abstract class a extends Panel implements Runnable
{
    private Thread void;
    private boolean do;
    private int int;
    public static String long;
    public static String goto;
    public static String else;
    public static String char;
    public static String case;
    public static String byte;
    public static String try;
    public static String null;
    public static final String a = "splashscreen.gif";
    public AppletContext b;
    public boolean if;
    public boolean new;
    public pa.a.a.a.d.a for;
    
    static {
        pa.a.a.a.a.a.long = " ";
        pa.a.a.a.a.a.goto = " ";
        pa.a.a.a.a.a.else = " ";
        pa.a.a.a.a.a.char = " ";
        pa.a.a.a.a.a.case = " ";
        pa.a.a.a.a.a.byte = " ";
        pa.a.a.a.a.a.try = " ";
        pa.a.a.a.a.a.null = " ";
    }
    
    public a() {
        this.for = null;
    }
    
    public void char() {
        this.if = false;
        if (this.for != null) {
            this.for.if(1, 4);
        }
        this.new = true;
    }
    
    public void try() {
    }
    
    public abstract void byte();
    
    public void if() {
        this.if = true;
        if (this.for != null) {
            this.for.if(1, 3);
        }
        this.new = true;
    }
    
    public void goto() {
    }
    
    public void case() {
    }
    
    public boolean do() {
        final String property = System.getProperty("java.version");
        if (property.startsWith("1.1") && pa.a.a.a.c.a.char) {
            int intValue = 5;
            final String substring = property.substring(property.lastIndexOf(".") + 1, property.lastIndexOf(".") + 2);
            try {
                intValue = Integer.valueOf(substring);
            }
            catch (NumberFormatException ex) {}
            return intValue > 7;
        }
        return true;
    }
    
    public void run() {
        try {
            while (!this.do) {
                if (this.a()) {
                    Thread.yield();
                    this.int = 10;
                }
                else {
                    Thread.sleep(this.int);
                    if (this.int >= 75) {
                        continue;
                    }
                    this.int += 5;
                }
            }
        }
        catch (Throwable t) {}
        finally {
            this.void = null;
        }
    }
    
    protected abstract boolean a();
    
    public static void a(final String long1, final String goto1, final String else1, final String char1, final String case1, final String byte1, final String try1, final String null) {
        pa.a.a.a.a.a.long = long1;
        pa.a.a.a.a.a.goto = goto1;
        pa.a.a.a.a.a.else = else1;
        pa.a.a.a.a.a.char = char1;
        pa.a.a.a.a.a.case = case1;
        pa.a.a.a.a.a.byte = byte1;
        pa.a.a.a.a.a.try = try1;
        pa.a.a.a.a.a.null = null;
    }
    
    public void new() {
        if (this.void == null) {
            this.for();
            this.do = false;
            this.int = 10;
            (this.void = new Thread(this)).start();
        }
        else {
            this.repaint();
            this.requestFocus();
        }
    }
    
    protected abstract void for();
    
    public void else() {
        if (this.void != null) {
            this.do = true;
            while (this.void != null) {
                Thread.yield();
            }
            this.int();
        }
    }
    
    protected abstract void int();
    
    public abstract void a(final int p0);
}
