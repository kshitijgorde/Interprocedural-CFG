// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.log.console;

import jmaster.util.log.D;
import jmaster.util.property.C;
import jmaster.util.log.B;

public class ConsoleLogFactory extends B
{
    private static final String F = "ConsoleLogFactory.properties";
    protected String D;
    private A E;
    
    public ConsoleLogFactory() {
        jmaster.util.property.C.A().A(this, jmaster.util.property.B.C().G("ConsoleLogFactory.properties"), null);
    }
    
    public String getDefaultPriority() {
        return this.D;
    }
    
    public void setDefaultPriority(final String d) {
        this.D = d;
    }
    
    public synchronized jmaster.util.log.A getLog(final String s) {
        if (this.E == null) {
            this.A(this.E = new A(jmaster.util.log.D.A(this.D)));
        }
        return this.E;
    }
    
    public jmaster.util.log.A getLog(final Class clazz) {
        final A a = new A(jmaster.util.log.D.A(this.D));
        this.A(a);
        return a;
    }
}
