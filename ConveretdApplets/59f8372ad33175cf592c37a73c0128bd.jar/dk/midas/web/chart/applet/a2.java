// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import java.util.EventObject;

public class a2 extends EventObject
{
    public static final int for = 1;
    public static final int try = 2;
    public static final int char = 3;
    public static final int int = 4;
    public static final int else = 5;
    public static final int goto = 6;
    private int do;
    private boolean if;
    private boolean new;
    private String case;
    private Object byte;
    private Object a;
    
    public a2(final DataSource dataSource, final int do1) {
        super(dataSource);
        this.if = false;
        this.new = false;
        this.do = do1;
    }
    
    public a2(final DataSource dataSource, final int do1, final boolean if1, final boolean new1) {
        super(dataSource);
        this.if = false;
        this.new = false;
        this.do = do1;
        this.if = if1;
        this.new = new1;
    }
    
    public a2(final DataSource dataSource, final String s, final Object byte1, final Object a) {
        super(dataSource);
        this.if = false;
        this.new = false;
        this.do = 6;
        this.byte = byte1;
        this.a = a;
    }
    
    public int a() {
        return this.do;
    }
    
    public DataSource try() {
        return (DataSource)this.getSource();
    }
    
    public boolean if() {
        return this.new;
    }
    
    public boolean new() {
        return this.if;
    }
    
    public Object do() {
        return this.a;
    }
    
    public Object int() {
        return this.byte;
    }
    
    public String for() {
        return this.case;
    }
    
    public String toString() {
        return "DatsSourceEvent[source=" + this.try() + ", type=" + this.do + ", isFirstTime=" + this.if + ", isNewBar=" + this.new + "]";
    }
}
