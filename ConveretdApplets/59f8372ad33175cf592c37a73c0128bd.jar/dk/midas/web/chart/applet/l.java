// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import java.applet.Applet;

public class l
{
    public static final int new = 0;
    public static final int case = 1;
    public static final int try = 2;
    private static final String int = "SAXOBANK";
    private static final int byte = 5000;
    private static final int if = 10000;
    private int for;
    private String a;
    private int do;
    
    public l(final AppletChart appletChart) {
        this.for = new z(appletChart).int();
        this.a = appletChart.getProperties().a("companyName", "SAXOBANK").toUpperCase();
        this.do = appletChart.getProperties().if("delay", 3000);
    }
    
    public String new() {
        return this.a;
    }
    
    public int do() {
        return this.for;
    }
    
    public int byte() {
        switch (this.for) {
            case 1: {
                return (this.do < 5000) ? 5000 : this.do;
            }
            case 2: {
                return this.do;
            }
            default: {
                return 10000;
            }
        }
    }
    
    public boolean try() {
        return this.do() == 0;
    }
    
    public boolean int() {
        return !this.try();
    }
    
    public boolean if() {
        return this.do() == 1;
    }
    
    public boolean for() {
        return this.do() == 2;
    }
    
    public boolean a() {
        return !this.for() && "SAXOBANK".equals(this.new());
    }
}
