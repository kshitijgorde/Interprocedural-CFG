// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

public class Material extends Node
{
    public final float[] defaultDiffuseColor;
    public final float[] defaultEmissiveColor;
    public final FloatArrayField diffuseColor;
    public final FloatArrayField emissiveColor;
    public final FloatField transparency;
    
    public Material() {
        this.defaultDiffuseColor = new float[] { 0.8f, 0.8f, 0.8f };
        this.defaultEmissiveColor = new float[3];
        this.diffuseColor = new FloatArrayField(this, "diffuseColor", 2, this.defaultDiffuseColor);
        this.emissiveColor = new FloatArrayField(this, "emissiveColor", 2, this.defaultEmissiveColor);
        this.transparency = new FloatField(this, "transparency", 15, 0.0f);
    }
    
    public void b(final g g) {
        final Appearance bc = g.bc;
        bc.a = true;
        bc.b = this.diffuseColor.a;
        bc.c = this.emissiveColor.a;
        bc.e = this.transparency.a;
    }
}
