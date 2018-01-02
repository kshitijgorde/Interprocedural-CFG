// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.util;

import java.util.Date;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Properties;
import java.text.SimpleDateFormat;

public class H implements I
{
    private boolean a;
    private boolean b;
    private static final SimpleDateFormat c;
    
    public boolean a(final Properties properties) {
        try {
            System.out.println("Using ISO8859");
            System.setOut(new PrintStream(System.out, true, "ISO8859-1"));
        }
        catch (Exception ex) {}
        this.a = Boolean.valueOf(properties.getProperty("log.print_date", "false"));
        this.b = Boolean.valueOf(properties.getProperty("log.print_level", "false"));
        return true;
    }
    
    public synchronized void a(final int n, final String s) {
        if (this.a && t.a() != null) {
            final String a = t.a().a();
            if (a != null) {
                System.out.print(a);
            }
            else {
                System.out.print(H.c.format(new Date()));
            }
            System.out.print(' ');
        }
        else if (this.a) {
            System.out.print(H.c.format(new Date()));
            System.out.print(' ');
        }
        if (this.b) {
            System.out.print(F.a[n]);
            System.out.print(":\t");
        }
        System.out.println(s);
    }
    
    static {
        c = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
    }
}
