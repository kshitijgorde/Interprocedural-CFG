// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class Study
{
    private int int;
    private int a;
    private String case;
    private String if;
    private ag byte;
    private Class new;
    private Method for;
    private Constructor try;
    private Constructor do;
    static /* synthetic */ Class array$Ljava$lang$Object;
    static /* synthetic */ Class class$dk$midas$web$chart$applet$Analyse;
    static /* synthetic */ Class class$dk$midas$web$chart$applet$ChartManager;
    static /* synthetic */ Class class$dk$midas$web$chart$applet$Study;
    static /* synthetic */ Class class$dk$midas$web$chart$applet$DataSource;
    static /* synthetic */ Class class$dk$midas$web$chart$applet$CompositeAnalysis;
    static /* synthetic */ Class class$dk$midas$web$chart$applet$ChartBody;
    
    public Study(final int int1, final int a, final String case1, final String if1) {
        this.int = int1;
        this.a = a;
        this.case = case1;
        this.if = if1;
        this.byte = null;
        this.if();
    }
    
    private void if() {
        try {
            this.new = this.getClass().getClassLoader().loadClass(this.byte());
            this.for = this.new.getMethod("createNameSuffix", (Study.array$Ljava$lang$Object == null) ? (Study.array$Ljava$lang$Object = class$("[Ljava.lang.Object;")) : Study.array$Ljava$lang$Object);
            if (((Study.class$dk$midas$web$chart$applet$Analyse == null) ? (Study.class$dk$midas$web$chart$applet$Analyse = class$("dk.midas.web.chart.applet.Analyse")) : Study.class$dk$midas$web$chart$applet$Analyse).isAssignableFrom(this.new)) {
                this.try = this.new.getConstructor((Study.class$dk$midas$web$chart$applet$ChartManager == null) ? (Study.class$dk$midas$web$chart$applet$ChartManager = class$("dk.midas.web.chart.applet.ChartManager")) : Study.class$dk$midas$web$chart$applet$ChartManager, (Study.class$dk$midas$web$chart$applet$Study == null) ? (Study.class$dk$midas$web$chart$applet$Study = class$("dk.midas.web.chart.applet.Study")) : Study.class$dk$midas$web$chart$applet$Study, (Study.class$dk$midas$web$chart$applet$DataSource == null) ? (Study.class$dk$midas$web$chart$applet$DataSource = class$("dk.midas.web.chart.applet.DataSource")) : Study.class$dk$midas$web$chart$applet$DataSource);
            }
            if (((Study.class$dk$midas$web$chart$applet$CompositeAnalysis == null) ? (Study.class$dk$midas$web$chart$applet$CompositeAnalysis = class$("dk.midas.web.chart.applet.CompositeAnalysis")) : Study.class$dk$midas$web$chart$applet$CompositeAnalysis).isAssignableFrom(this.new)) {
                this.do = this.new.getConstructor((Study.class$dk$midas$web$chart$applet$Study == null) ? (Study.class$dk$midas$web$chart$applet$Study = class$("dk.midas.web.chart.applet.Study")) : Study.class$dk$midas$web$chart$applet$Study, (Study.class$dk$midas$web$chart$applet$ChartBody == null) ? (Study.class$dk$midas$web$chart$applet$ChartBody = class$("dk.midas.web.chart.applet.ChartBody")) : Study.class$dk$midas$web$chart$applet$ChartBody);
            }
        }
        catch (SecurityException ex) {
            ex.printStackTrace();
        }
        catch (ClassNotFoundException ex2) {}
        catch (NoSuchMethodException ex3) {}
    }
    
    public boolean equals(final Object o) {
        return this.int == ((Study)o).int;
    }
    
    public int hashCode() {
        return this.int;
    }
    
    public ag try() {
        return this.byte;
    }
    
    public void a(final ag byte1) {
        this.byte = byte1;
    }
    
    public int new() {
        return this.int;
    }
    
    public int a() {
        return this.a;
    }
    
    public String int() {
        return this.case;
    }
    
    public String byte() {
        return this.if;
    }
    
    public String if(final Object[] array) {
        try {
            final boolean b = false;
            if (this.for != null && (this.for.getModifiers() & (b ? 1 : 0)) == (b ? 1 : 0)) {
                return this.int() + " " + (String)this.for.invoke(null, array);
            }
        }
        catch (SecurityException ex) {}
        catch (IllegalArgumentException ex2) {}
        catch (IllegalAccessException ex3) {}
        catch (InvocationTargetException ex4) {}
        return this.int();
    }
    
    public q a(final ChartManager chartManager, final DataSource dataSource) {
        try {
            if (this.try != null) {
                return this.try.newInstance(chartManager, this, dataSource);
            }
        }
        catch (SecurityException ex) {
            ex.printStackTrace();
        }
        catch (IllegalArgumentException ex2) {}
        catch (InstantiationException ex3) {}
        catch (IllegalAccessException ex4) {}
        catch (InvocationTargetException ex5) {}
        return null;
    }
    
    public q a(final ChartBody chartBody) {
        try {
            if (this.do != null) {
                return this.do.newInstance(this, chartBody);
            }
        }
        catch (SecurityException ex) {
            ex.printStackTrace();
        }
        catch (IllegalArgumentException ex2) {}
        catch (InstantiationException ex3) {}
        catch (IllegalAccessException ex4) {}
        catch (InvocationTargetException ex5) {}
        return null;
    }
    
    public boolean for() {
        return this.try != null;
    }
    
    public boolean do() {
        return this.do != null;
    }
    
    public static int a(final Object[] array) {
        return a(array, 0, 10);
    }
    
    public static int a(final Object[] array, final int n, final int n2) {
        if (array == null || n >= array.length) {
            return n2;
        }
        try {
            if (array[n] instanceof String) {
                return new Integer((String)array[n]);
            }
            if (array[n] instanceof Integer) {
                return (int)array[n];
            }
            if (array[n] instanceof Float) {
                return (int)array[n];
            }
        }
        catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        return n2;
    }
    
    public static float a(final Object[] array, final int n, final float n2) {
        if (array == null || n >= array.length) {
            return n2;
        }
        try {
            if (array[n] instanceof String) {
                return new Float((String)array[n]);
            }
            if (array[n] instanceof Integer) {
                return (float)array[n];
            }
            if (array[n] instanceof Float) {
                return (float)array[n];
            }
        }
        catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        return n2;
    }
    
    public static boolean a(final Object[] array, final int n, final boolean b) {
        if (array == null || n >= array.length) {
            return b;
        }
        if (array[n] instanceof String) {
            return Boolean.valueOf((String)array[n]);
        }
        return b;
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}
