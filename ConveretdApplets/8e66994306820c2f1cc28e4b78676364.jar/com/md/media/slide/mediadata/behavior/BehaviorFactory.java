// 
// Decompiled by Procyon v0.5.30
// 

package com.md.media.slide.mediadata.behavior;

import a.b.a.b.d;
import a.b.a.b.b;
import a.b.a.b.a;

public class BehaviorFactory implements a
{
    private static BehaviorFactory a;
    
    public b a() {
        return new MovingBehavior();
    }
    
    public d b() {
        return new StaticBehavior();
    }
    
    static {
        BehaviorFactory.a = new BehaviorFactory();
    }
}
