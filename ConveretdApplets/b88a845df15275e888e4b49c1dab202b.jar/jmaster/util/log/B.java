// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.log;

import jmaster.util.C.A;

public abstract class B
{
    private static final String B = "LogFactory.properties";
    protected static B A;
    protected A C;
    static /* synthetic */ Class class$jmaster$util$log$C;
    
    public B() {
        this.C = new A((jmaster.util.log.B.class$jmaster$util$log$C == null) ? (jmaster.util.log.B.class$jmaster$util$log$C = class$("jmaster.util.log.C")) : jmaster.util.log.B.class$jmaster$util$log$C);
    }
    
    public static synchronized B getInstance() {
        if (jmaster.util.log.B.A == null) {
            final String property = jmaster.util.property.B.C().G("LogFactory.properties").getProperty("className");
            try {
                jmaster.util.log.B.A = (B)Class.forName(property).newInstance();
            }
            catch (Exception ex) {
                if (ex instanceof RuntimeException) {
                    throw (RuntimeException)ex;
                }
                throw new RuntimeException(ex);
            }
        }
        return jmaster.util.log.B.A;
    }
    
    public void addListener(final C c) {
        this.C.C(c);
    }
    
    public void removeListener(final C c) {
        this.C.A(c);
    }
    
    protected void A(final jmaster.util.log.A a) {
        for (int i = this.C.C() - 1; i >= 0; --i) {
            ((C)this.C.A(i)).A(this, a);
        }
    }
    
    public abstract jmaster.util.log.A getLog(final String p0);
    
    public abstract jmaster.util.log.A getLog(final Class p0);
    
    public jmaster.util.log.A getLog(final Object o) {
        return this.getLog(o.getClass());
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
        jmaster.util.log.B.A = null;
    }
}
