// 
// Decompiled by Procyon v0.5.30
// 

package com.dreamfabric.jac64;

public class DefaultIMon implements IMonitor
{
    int level;
    String prefix;
    
    public DefaultIMon() {
        this.level = 0;
        this.prefix = "";
    }
    
    public void init(final MOS6510Core mos6510Core) {
    }
    
    public void setEnabled(final boolean b) {
    }
    
    public boolean isEnabled() {
        return false;
    }
    
    public void info(final Object o) {
        this.output((String)o);
    }
    
    public void setLevel(final int level) {
        this.level = level;
    }
    
    public int getLevel() {
        return this.level;
    }
    
    public void warning(final Object o) {
        this.output((String)o);
    }
    
    public void error(final Object o) {
        this.output((String)o);
    }
    
    private void output(final String s) {
        if (this.prefix != null) {
            if (s.startsWith(this.prefix)) {
                System.out.println(s);
            }
        }
        else {
            System.out.println(s);
        }
    }
    
    public void disAssemble(final int[] array, final int n, final int n2, final int n3, final int n4, final byte b, final int n5, final int n6) {
    }
}
