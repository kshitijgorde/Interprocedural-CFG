// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.e;

import java.lang.reflect.Method;

public class c implements b
{
    Object a;
    Method b;
    Object[] c;
    
    public c() {
    }
    
    public c(final Object a, final Method b, final Object[] c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    public void a() {
        try {
            this.b.invoke(this.a, this.c);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
