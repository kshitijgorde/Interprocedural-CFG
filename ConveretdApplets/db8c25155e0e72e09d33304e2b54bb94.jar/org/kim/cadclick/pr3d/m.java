// 
// Decompiled by Procyon v0.5.30
// 

package org.kim.cadclick.pr3d;

import java.util.Vector;

public class m
{
    protected String a;
    protected Stv_Button if;
    protected Vector do;
    
    public m(final String a) {
        this.a = a;
        this.if = null;
        this.do = new Vector();
    }
    
    protected void a(final Stv_Button if1, final boolean b) {
        if (b) {
            this.if = if1;
            if1.status = 0;
        }
        this.do.addElement(if1);
    }
}
