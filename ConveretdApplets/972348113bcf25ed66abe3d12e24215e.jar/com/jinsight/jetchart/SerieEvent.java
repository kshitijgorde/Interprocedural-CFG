// 
// Decompiled by Procyon v0.5.30
// 

package com.jinsight.jetchart;

import java.util.EventObject;

public class SerieEvent extends EventObject
{
    AbstractSerie a;
    int b;
    int c;
    int d;
    boolean e;
    Graph f;
    
    public Graph getGraph() {
        return this.f;
    }
    
    public AbstractSerie getSerie() {
        return this.a;
    }
    
    public boolean isOverLegend() {
        return this.e;
    }
    
    public int getX() {
        return this.b;
    }
    
    public int getY() {
        return this.c;
    }
    
    public int getClickCount() {
        return this.d;
    }
    
    public SerieEvent(final Graph f, final AbstractSerie a, final boolean e, final int b, final int c, final int d) {
        super(f);
        this.f = f;
        this.a = a;
        this.e = e;
        this.b = b;
        this.c = c;
        this.d = d;
    }
}
