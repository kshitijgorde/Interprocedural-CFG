// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.applet;

class F implements Runnable
{
    final /* synthetic */ au a;
    
    F(final au a) {
        this.a = a;
    }
    
    public void run() {
        this.a.getContentPane().removeAll();
        this.a.getContentPane().validate();
    }
}
