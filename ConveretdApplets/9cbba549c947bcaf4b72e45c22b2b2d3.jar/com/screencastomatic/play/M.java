// 
// Decompiled by Procyon v0.5.30
// 

package com.screencastomatic.play;

import com.screencastomatic.play.stream.i;

class M implements Runnable
{
    final /* synthetic */ i a;
    final /* synthetic */ Display b;
    
    M(final Display b, final i a) {
        this.b = b;
        this.a = a;
    }
    
    public void run() {
        this.b.m_webcam = this.a;
        this.b.h();
    }
}
