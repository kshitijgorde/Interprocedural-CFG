// 
// Decompiled by Procyon v0.5.30
// 

public class Particle
{
    float velocityX;
    float velocityY;
    float x;
    float y;
    
    public void initialize(final int n, final int n2) {
        this.velocityX = (float)(Math.random() * 0.5 - 0.25);
        this.velocityY = (float)(Math.random() * 0.5 - 0.25);
        this.x = n + this.velocityX;
        this.y = n2 + this.velocityY;
    }
    
    public void update(final float[] array, final float[] array2, final float[] array3, final float[] array4, final float[] array5, final int n, final boolean b) {
        this.velocityX += array[0] + array2[0] + array3[0] + array4[0] + array5[0];
        this.velocityY += array[1] + array2[1] + array3[1] + array4[1] + array5[1];
        this.x += this.velocityX;
        this.y += this.velocityY;
        if (n == 0 && !b) {
            this.x += this.velocityX * 1.6f;
            this.y += this.velocityY * 1.6f;
        }
        if (Math.abs(this.velocityX) > 5.0f) {
            this.velocityX = this.velocityX / Math.abs(this.velocityX) * 5.0f;
        }
        if (Math.abs(this.velocityY) > 5.0f) {
            this.velocityY = this.velocityY / Math.abs(this.velocityY) * 5.0f;
        }
    }
}
