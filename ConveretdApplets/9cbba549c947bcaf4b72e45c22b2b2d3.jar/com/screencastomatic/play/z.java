// 
// Decompiled by Procyon v0.5.30
// 

package com.screencastomatic.play;

class z implements Runnable
{
    final /* synthetic */ Display a;
    
    z(final Display a) {
        this.a = a;
    }
    
    public void run() {
        System.out.println("Calling stop.");
        this.a.c();
    }
}
