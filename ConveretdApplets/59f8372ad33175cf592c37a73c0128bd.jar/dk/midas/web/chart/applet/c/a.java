// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet.c;

import dk.midas.web.chart.applet.d.c;
import java.util.Hashtable;

public class a implements b
{
    private String try;
    private String int;
    private String byte;
    private static Hashtable new;
    
    public static a a(final c c) {
        final String if1 = c.if("Instrument", "EURUSD");
        String if2 = c.if("InstrumentName", if1);
        if (if2 == null) {
            if2 = if1;
        }
        return new a(if2, if1, c.if("InstrumentType", ""));
    }
    
    public a(final String int1, final String byte1, final String try1) {
        this.try = "";
        this.int = "Unknown";
        this.int = int1;
        this.byte = byte1;
        this.try = try1;
    }
    
    public a(final a a) {
        this.try = "";
        this.int = "Unknown";
        this.int = a.do();
        this.byte = a.if();
        this.try = a.int();
    }
    
    public String if() {
        return this.byte;
    }
    
    public void a(final String byte1) {
        this.byte = byte1;
    }
    
    public String for() {
        final String int1 = this.int();
        final String s = a().get(int1);
        return (s == null) ? int1 : s;
    }
    
    public String do() {
        return this.int;
    }
    
    public String int() {
        return this.try;
    }
    
    private static synchronized Hashtable a() {
        if (a.new == null) {
            (a.new = new Hashtable()).put("VO", "Forex Option");
            a.new.put("VT", "Forex");
            a.new.put("SH", "Share");
            a.new.put("SF", "CFD");
            a.new.put("MF", "Managed Fund");
            a.new.put("CF", "Future Contract");
            a.new.put("SX", "Stock Index");
            a.new.put("BO", "Bond");
        }
        return a.new;
    }
}
