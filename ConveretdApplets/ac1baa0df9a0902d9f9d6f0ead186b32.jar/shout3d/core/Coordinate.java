// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

public class Coordinate extends Node
{
    public final FloatArrayField point;
    
    public Coordinate() {
        this.point = new FloatArrayField(this, "point", 7, null);
    }
}
