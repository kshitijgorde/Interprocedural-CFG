// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.clock;

import java.util.EventListener;

interface TimerListener extends EventListener
{
    void timeChanged(final TimerEvent p0);
}
