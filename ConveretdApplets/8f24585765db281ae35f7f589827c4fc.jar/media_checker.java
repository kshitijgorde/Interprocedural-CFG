import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

class media_checker extends Thread
{
    Graphics myG;
    puzzle puz;
    
    public media_checker(final Graphics myG, final puzzle puz) {
        super("mediachecker");
        this.myG = myG;
        this.puz = puz;
    }
    
    public void run() {
        boolean b = false;
        while (!b) {
            int n;
            for (n = 0; n <= 15 && !this.puz.pieces[n].img_thread.isAlive(); ++n) {}
            if (n > 15) {
                this.puz.images_loaded = true;
                if (this.puz.user_waiting) {
                    final String s = "Image loading complete!!";
                    this.myG.setColor(Color.pink);
                    this.myG.fillRect(0, 251, 300, 300);
                    this.myG.setFont(new Font("Times", 1, 8));
                    this.myG.setColor(Color.black);
                    this.myG.drawString(s, 10, 275);
                }
                b = true;
            }
            else {
                try {
                    Thread.sleep(1000L);
                }
                catch (InterruptedException ex) {}
            }
        }
    }
}
