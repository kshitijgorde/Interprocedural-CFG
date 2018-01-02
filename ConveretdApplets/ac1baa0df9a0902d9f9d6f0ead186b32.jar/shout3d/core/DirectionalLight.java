// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

import shout3d.math.MatUtil;

public class DirectionalLight extends Light
{
    static final String a = "STOP IMMEDIATELY!";
    static final String b = "Copyright (c) 1997-1999 Shout Interactive, Inc.";
    static final String c = "Contents of this file are property of Shout Interactive, Inc.";
    static final String d = "If you are reading this text, then you are in direct violation";
    static final String e = "of the terms of use and the terms of the license agreement.";
    static final String f = "YOU MUST CEASE YOUR EXAMINATION OF THIS FILE AND DESTROY THIS FILE IMMEDIATELY.";
    public final float[] defaultDirection;
    public final FloatArrayField direction;
    public boolean isHeadlight;
    float[] defaultColor;
    float[] g;
    float[] h;
    private Node i;
    float[] j;
    
    public DirectionalLight() {
        this.defaultDirection = new float[] { 0.0f, 0.0f, -1.0f };
        this.direction = new FloatArrayField(this, "direction", 8, this.defaultDirection);
        this.isHeadlight = false;
        this.defaultColor = new float[3];
        this.g = new float[3];
        this.h = new float[] { 0.0f, 0.0f, -1.0f };
        this.j = new float[9];
    }
    
    protected void a(final g g) {
        if (this.direction.a != null) {
            if (!this.isHeadlight) {
                System.arraycopy(this.direction.a, 0, this.h, 0, 3);
                for (int i = g.w() - 1; i >= 0; --i) {
                    this.i = g.a(i);
                    if (this.i instanceof Group) {
                        MatUtil.c(((Group)this.i).b(), this.h, this.h);
                    }
                }
                MatUtil.c(g.h(), this.h, this.h);
            }
            else {
                this.h[0] = this.direction.a[0];
                this.h[1] = this.direction.a[1];
                this.h[2] = this.direction.a[2];
            }
            MatUtil.normalize(this.h);
        }
        if (!super.on.g) {
            this.defaultColor[0] = 0.0f;
            this.defaultColor[1] = 0.0f;
            this.defaultColor[2] = 0.0f;
        }
        else {
            this.defaultColor[0] = super.intensity.a * super.color.a[0];
            this.defaultColor[1] = super.intensity.a * super.color.a[1];
            this.defaultColor[2] = super.intensity.a * super.color.a[2];
        }
        g.bd.addElement(this);
    }
    
    public void b(final g g) {
    }
}
