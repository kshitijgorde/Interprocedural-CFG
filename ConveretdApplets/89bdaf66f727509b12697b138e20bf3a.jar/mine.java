import java.awt.Font;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class mine
{
    int x;
    int y;
    Color col;
    boolean p;
    boolean alive;
    int serial;
    
    mine(final int n, final int n2, final boolean p4, final char c) {
        if (c == 'r') {
            this.x = n - 25;
            this.y = n2;
        }
        else if (c == 'd') {
            this.x = n;
            this.y = n2 - 25;
        }
        else if (c == 'l') {
            this.x = n + 25;
            this.y = n2;
        }
        else if (c == 'u') {
            this.x = n;
            this.y = n2 + 25;
        }
        if (!(this.p = p4)) {
            this.col = Color.blue;
        }
        else {
            this.col = Color.yellow;
        }
        this.alive = true;
        this.draw();
    }
    
    public void draw() {
        Pacman.gfx.setColor(this.col);
        Pacman.gfx.fillOval(this.x, this.y, 25, 25);
        Pacman.gfx.setColor(Color.black);
        Pacman.gfx.drawString("MINE", this.x, this.y + 18);
        final MineThread mineThread = new MineThread();
        mineThread.init(this, Pacman.gfx, 900);
        mineThread.start();
    }
    
    public synchronized void explode(final char c, final boolean b) {
        try {
            Pacman.playSound('e');
        }
        catch (Exception ex) {
            System.out.println("NO SOUND EXPLODE");
        }
        Pacman.mineVector.removeElementAt(this.serial);
        this.alive = false;
        Pacman.gfx.setColor(this.col);
        Pacman.gfx.fillOval(this.x, this.y, 25, 25);
        Pacman.gfx.setColor(Color.black);
        Pacman.gfx.setFont(new Font("Times", 2, 7));
        Pacman.gfx.drawString("BOOM!", this.x, this.y + 18);
        Pacman.gfx.setFont(Pacman.normalFont);
        for (int i = 0; i < Pacman.mineVector.size(); ++i) {
            ((mine)Pacman.mineVector.elementAt(i)).serial = i;
        }
        if (b) {
            final bullet bullet = new bullet(this.x, this.y, this.p, 'u');
            if (c != 'd' && bullet.checkUp()) {
                bullet.serial = Pacman.bulletVector.size();
                bullet.start();
                Pacman.bulletVector.addElement(bullet);
            }
            final bullet bullet2 = new bullet(this.x, this.y, this.p, 'd');
            if (c != 'u' && bullet2.checkDown()) {
                bullet2.serial = Pacman.bulletVector.size();
                bullet2.start();
                Pacman.bulletVector.addElement(bullet2);
            }
            final bullet bullet3 = new bullet(this.x, this.y, this.p, 'r');
            if (c != 'l' && bullet3.checkRight()) {
                bullet3.serial = Pacman.bulletVector.size();
                bullet3.start();
                Pacman.bulletVector.addElement(bullet3);
            }
            final bullet bullet4 = new bullet(this.x, this.y, this.p, 'l');
            if (c != 'r' && bullet4.checkLeft()) {
                bullet4.serial = Pacman.bulletVector.size();
                bullet4.start();
                Pacman.bulletVector.addElement(bullet4);
            }
        }
        final MineThread mineThread = new MineThread();
        mineThread.init(this, Pacman.gfx, 500);
        mineThread.start();
    }
}
