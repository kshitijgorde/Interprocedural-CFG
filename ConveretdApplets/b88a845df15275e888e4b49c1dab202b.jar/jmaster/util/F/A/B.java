// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.F.A;

import jmaster.util.log.A;

public class B
{
    public static final String D = "zipUtilClassName";
    public static final String C;
    private static B A;
    protected A B;
    static /* synthetic */ Class class$jmaster$util$F$A$A$A;
    
    private B() {
        this.B = jmaster.util.log.B.getInstance().getLog(this.getClass());
    }
    
    public static synchronized B B() {
        if (B.A == null) {
            B.A = new B();
        }
        return B.A;
    }
    
    public jmaster.util.F.A.A A() {
        final String property = System.getProperty("zipUtilClassName", jmaster.util.F.A.B.C);
        jmaster.util.F.A.A a;
        try {
            if (this.B.B()) {
                this.B.D("About to instantiate zip util from class " + property);
            }
            a = (jmaster.util.F.A.A)Class.forName(property).newInstance();
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return a;
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError().initCause(ex);
        }
    }
    
    static {
        C = ((B.class$jmaster$util$F$A$A$A == null) ? (B.class$jmaster$util$F$A$A$A = class$("jmaster.util.F.A.A.A")) : B.class$jmaster$util$F$A$A$A).getName();
        B.A = null;
    }
}
