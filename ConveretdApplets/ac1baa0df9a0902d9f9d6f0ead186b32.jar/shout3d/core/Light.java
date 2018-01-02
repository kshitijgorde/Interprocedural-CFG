// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

public abstract class Light extends Node
{
    public final float[] defaultColor;
    public final StringArrayField affectedGroups;
    public final FloatArrayField color;
    public final FloatField intensity;
    public final BooleanField on;
    
    public Light() {
        this.defaultColor = new float[] { 1.0f, 1.0f, 1.0f };
        this.affectedGroups = new StringArrayField(this, "affectedGroups", 0, null);
        this.color = new FloatArrayField(this, "color", 2, this.defaultColor);
        this.intensity = new FloatField(this, "intensity", 18, 1.0f);
        this.on = new BooleanField(this, "on", 0, true);
    }
}
