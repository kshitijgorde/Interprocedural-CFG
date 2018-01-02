import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

class JuliaSet implements Runnable
{
    ComplexNumber c;
    int num_points;
    ComplexNumber[] points;
    JuliaCanvas canvas;
    Graphics g;
    
    JuliaSet(final int num_points, final ComplexNumber[] points, final JuliaCanvas canvas, final Graphics g) {
        this.num_points = num_points;
        this.points = points;
        this.canvas = canvas;
        this.g = g;
    }
    
    public void run() {
        this.points[0] = new ComplexNumber(Math.random(), Math.random());
        for (int i = 0; i < this.num_points - 1; ++i) {
            this.points[i + 1] = this.fc((int)(2.0 * Math.random()), this.points[i]);
            this.canvas.plot(this.points[i]);
        }
    }
    
    ComplexNumber fc(final int n, final ComplexNumber complexNumber) {
        if (n == 0) {
            return complexNumber.minus(this.c).sqrt();
        }
        return complexNumber.minus(this.c).sqrt().ominus();
    }
}
