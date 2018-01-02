import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class ColorThread extends Thread
{
    int R;
    int G;
    int B;
    int R2;
    int G2;
    int B2;
    
    public void run() {
        while (Pacmen.alive) {
            Pacmen.itColor = new Color(this.R, this.G, this.B);
            Pacmen.itColor2 = new Color(this.B, this.G, this.R);
            try {
                Thread.sleep(5L);
            }
            catch (Exception ex) {}
            if (Pacmen.toYellow) {
                if (this.G > 0) {
                    --this.G;
                }
                else {
                    ++this.G;
                    Pacmen.toYellow = false;
                }
            }
            else if (this.G < 255) {
                ++this.G;
            }
            else {
                --this.G;
                Pacmen.toYellow = true;
            }
        }
    }
    
    ColorThread() {
        this.R = 255;
        this.G = 255;
        this.G2 = 255;
        this.B2 = 255;
    }
}
