// 
// Decompiled by Procyon v0.5.30
// 

package com.screencastomatic.play.a;

import javax.sound.sampled.AudioFormat;
import com.screencastomatic.play.stream.e;
import com.screencastomatic.play.stream.r;

class m implements Runnable
{
    final /* synthetic */ b a;
    
    m(final b a) {
        this.a = a;
    }
    
    public void run() {
        this.a.a.l = true;
        this.a.a.c.a();
    }
}
