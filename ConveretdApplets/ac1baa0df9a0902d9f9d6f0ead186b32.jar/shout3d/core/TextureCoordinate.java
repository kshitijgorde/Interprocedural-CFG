// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

public class TextureCoordinate extends Node
{
    public final FloatArrayField point;
    
    public TextureCoordinate() {
        this.point = new FloatArrayField(this, "point", 26, null);
    }
}
