// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

public class Shape extends Node
{
    public final NodeField appearance;
    public final NodeField geometry;
    
    public Shape() {
        this.appearance = new NodeField(this, "appearance", 1, null);
        this.geometry = new NodeField(this, "geometry", 10, null);
    }
    
    public void b(final g g) {
        if (this.appearance.a != null) {
            this.appearance.a.b(g);
        }
        if (this.geometry.a != null) {
            this.geometry.a.b(g);
        }
    }
}
