// 
// Decompiled by Procyon v0.5.30
// 

public class AppletRotator
{
    float ct;
    float st;
    float cp;
    float sp;
    float cg;
    float sg;
    
    public AppletRotator() {
        this.ct = 1.0f;
        this.cp = 1.0f;
        this.cg = 1.0f;
    }
    
    public void setRotation(final float n, final float n2, final float n3) {
        this.ct = (float)Math.cos(n);
        this.st = (float)Math.sin(n);
        this.cp = (float)Math.cos(n2);
        this.sp = (float)Math.sin(n2);
        this.cg = (float)Math.cos(n3);
        this.sg = (float)Math.sin(n3);
    }
    
    public void setRotation(final int n, final int n2, final int n3) {
        this.ct = (float)Math.cos(n * 0.017453292519943295);
        this.st = (float)Math.sin(n * 0.017453292519943295);
        this.cp = (float)Math.cos(n2 * 0.017453292519943295);
        this.sp = (float)Math.sin(n2 * 0.017453292519943295);
        this.cg = (float)Math.cos(n3 * 0.017453292519943295);
        this.sg = (float)Math.sin(n3 * 0.017453292519943295);
    }
    
    public int[] rotatePoints(final int[] array) {
        array[0] = Math.round(array[0] * (this.cp * this.cg - this.st * this.sp * this.sg) + array[1] * (this.cp * this.sg + this.st * this.sp * this.cg) + array[2] * -(this.ct * this.sp));
        array[1] = Math.round(array[0] * -(this.ct * this.sg) + array[1] * (this.ct * this.cg) + array[2] * this.st);
        array[2] = Math.round(array[0] * (this.sp * this.cg + this.st * this.cp * this.sg) + array[1] * (this.sp * this.sg - this.st * this.cp * this.cg) + array[2] * (this.ct * this.cp));
        return array;
    }
}
