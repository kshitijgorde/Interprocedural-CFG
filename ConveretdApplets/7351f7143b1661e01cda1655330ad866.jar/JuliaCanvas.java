import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class JuliaCanvas extends Canvas implements Runnable
{
    static ComplexNumber c;
    static final int num_points = 5000;
    static final double z_size = 4.0;
    static int pixel_size;
    static Thread juliaRunner;
    JuliaSet theSet;
    ComplexNumber[] points;
    
    JuliaCanvas(final int pixel_size) {
        this.points = new ComplexNumber[5000];
        this.theSet = new JuliaSet(5000, this.points, this, this.getGraphics());
        JuliaCanvas.pixel_size = pixel_size;
        this.setBackground(Color.white);
        this.setForeground(Color.black);
        this.setFont(new Font("TimesRoman", 0, 12));
        this.resize(JuliaCanvas.pixel_size, JuliaCanvas.pixel_size);
        this.show();
    }
    
    public void run() {
        this.theSet.c = JuliaCanvas.c;
        this.startJulia();
    }
    
    void startJulia() {
        if (JuliaCanvas.juliaRunner == null) {
            (JuliaCanvas.juliaRunner = new Thread(this.theSet)).setPriority(1);
            JuliaCanvas.juliaRunner.start();
        }
        if (!JuliaCanvas.juliaRunner.isAlive()) {
            JuliaCanvas.juliaRunner.stop();
            (JuliaCanvas.juliaRunner = new Thread(this.theSet)).setPriority(1);
            JuliaCanvas.juliaRunner.start();
        }
    }
    
    public void clear() {
        for (int i = 0; i < 5000; ++i) {
            this.points[i] = new ComplexNumber(5.0);
        }
        this.repaint();
    }
    
    void plot(final ComplexNumber complexNumber) {
        final Graphics graphics = this.getGraphics();
        final int n = (int)(JuliaCanvas.pixel_size / 4.0 * (complexNumber.a + 2.0));
        final int n2 = (int)(JuliaCanvas.pixel_size / 4.0 * (2.0 - complexNumber.b));
        graphics.drawLine(n, n2, n, n2);
    }
    
    public void paint(final Graphics graphics) {
        if (!JuliaCanvas.juliaRunner.isAlive() && JuliaCanvas.juliaRunner != null && this.theSet.points[1].a < 4.0) {
            for (int i = 0; i < 5000; ++i) {
                this.plot(this.theSet.points[i]);
            }
        }
    }
}
