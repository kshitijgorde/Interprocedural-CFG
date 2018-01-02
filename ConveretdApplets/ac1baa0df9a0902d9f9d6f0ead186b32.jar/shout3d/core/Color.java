// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

public class Color extends Node
{
    public final FloatArrayField color;
    
    public Color() {
        this.color = new FloatArrayField(this, "color", 5, null);
    }
}
