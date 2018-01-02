import java.awt.Graphics;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class Food
{
    static final int radius = 5;
    int x;
    int y;
    Color col;
    boolean eaten;
    char type;
    int level;
    
    Food(final int n, final int n2, final char type, final int level) {
        this.col = Color.white;
        this.eaten = false;
        this.x = n + 10;
        this.y = n2 + 10;
        this.type = type;
        this.level = level;
        if (this.type == 'm') {
            this.col = Color.magenta;
        }
        this.draw(Pacman.gfx);
    }
    
    public void draw(final Graphics graphics) {
        graphics.setColor(this.col);
        graphics.fillRect(this.x, this.y, 5, 5);
    }
    
    public void eat(final boolean b) {
        this.eaten = true;
        Pacman.gfx.setColor(Color.black);
        Pacman.gfx.fillRect(this.x, this.y, 5, 5);
        if (b) {
            if (this.type == 'a') {
                final Pacmen p = Pacman.p;
                p.ammo += 3;
            }
            else {
                final Pacmen p2 = Pacman.p;
                ++p2.mines;
            }
        }
        else if (this.type == 'a') {
            final Pacmen p3 = Pacman.p2;
            p3.ammo += 3;
        }
        else {
            final Pacmen p4 = Pacman.p2;
            ++p4.mines;
        }
        final SleepThread sleepThread = new SleepThread();
        sleepThread.init(this);
        sleepThread.start();
        if (Pacman.level == 4) {
            Pacman.sleepThreadVector.addElement(sleepThread);
        }
    }
}
