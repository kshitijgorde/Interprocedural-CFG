// 
// Decompiled by Procyon v0.5.30
// 

class SnowPoint
{
    int x;
    int y;
    int mx;
    int my;
    int degree;
    int wDegree;
    int size;
    float blur;
    
    SnowPoint(final int n, final int n2) {
        this.setSnow(n, n2, 0, 0, 0, 0, 0, 0.0f);
    }
    
    SnowPoint(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final float n8) {
        this.setSnow(n, n2, n3, n4, n5, n6, n7, n8);
    }
    
    void setSnow(final int x, final int y, final int mx, final int my, final int degree, final int wDegree, final int size, final float blur) {
        this.x = x;
        this.y = y;
        this.mx = mx;
        this.my = my;
        this.degree = degree;
        this.wDegree = wDegree;
        this.size = size;
        this.blur = blur;
    }
    
    void moveNext() {
        this.x += this.mx;
        this.y += this.my;
        this.degree += this.wDegree;
        if (this.degree < 0) {
            this.degree += 360;
        }
        else if (this.degree >= 360) {
            this.degree -= 360;
        }
    }
}
