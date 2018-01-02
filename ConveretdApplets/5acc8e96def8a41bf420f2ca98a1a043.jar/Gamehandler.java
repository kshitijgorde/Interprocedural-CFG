import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.applet.AudioClip;

// 
// Decompiled by Procyon v0.5.30
// 

class Gamehandler extends Asteroids
{
    Ship[] ships;
    Rocks boulder;
    AudioClip[] sounds;
    Font f;
    Color col;
    int width;
    int height;
    int level;
    int lives;
    int points;
    int astnr;
    int which_ship;
    String[] ld;
    boolean gameover;
    boolean nextlevel;
    boolean sound_on;
    boolean filled;
    
    public void paint(final Graphics graphics) {
        graphics.setColor(Color.lightGray);
        graphics.setFont(this.f);
        graphics.drawString("Mission: " + this.ld[this.level], 10, 15);
        graphics.drawString("Torpedo: ", 10, 30);
        graphics.drawString("   Hull: ", 10, 45);
        graphics.drawString("Targets: " + this.boulder.remain, 10, 60);
        graphics.drawString("  Score: " + this.points, 10, 75);
        graphics.setColor(Color.darkGray);
        graphics.fillRect(73, 22, 100, 8);
        graphics.fillRect(73, 37, 100, 8);
        graphics.setColor(Color.green);
        graphics.fillRect(73, 22, this.ships[0].rounds.ammo, 8);
        graphics.fillRect(73, 37, this.ships[0].hull, 8);
        this.boulder.paint(graphics);
        for (int i = 0; i < this.ships.length; ++i) {
            this.ships[i].paint(graphics);
        }
    }
    
    public void solid(final boolean filled) {
        this.boulder.solid(filled);
        for (int i = 0; i < this.ships.length; ++i) {
            this.ships[i].solid(filled);
        }
        this.filled = filled;
    }
    
    public void play(final boolean b, final boolean b2, final boolean b3, final boolean b4) {
        this.ships[0].checkKey(b, b2, b3, b4);
        this.ships[0].move();
        for (int i = 1; i < this.ships.length; ++i) {
            this.ships[i].checkAuto(this.ships[0]);
            if (this.ships[i].check(this.ships[0])) {
                this.points += 250;
            }
            this.ships[0].check(this.ships[i]);
            this.ships[i].move();
        }
        if (!this.ships[0].alive && this.ships[0].fade < 0.5) {
            this.gameover = true;
        }
        this.boulder.move();
        if (this.ships[0].check(this.boulder)) {
            this.points += this.boulder.p;
        }
        if (this.boulder.remain == 0) {
            if (++this.level > 9) {
                this.level = 0;
            }
            for (int j = 0; j < this.ships.length; ++j) {
                this.ships[j].newlevel();
            }
            this.boulder = new Rocks(this.width, this.height, ++this.astnr, this.sounds);
            this.soundControl(this.sound_on);
            this.solid(this.filled);
            this.nextlevel = true;
        }
    }
    
    public Gamehandler(final int width, final int height, final int level, final AudioClip[] sounds) {
        this.ships = new Ship[4];
        this.level = -1;
        this.astnr = 2;
        this.ld = new String[] { "Solar eclipse", "Distant battle", "USA alert", "Blue nebula", "Stargazer", "Red devil", "Supernova", "The light", "Martian madness", "Homecoming" };
        this.nextlevel = true;
        this.sounds = sounds;
        this.width = width;
        this.height = height;
        this.level = level;
        this.points = 0;
        for (int i = 0; i < this.ships.length; ++i) {
            this.ships[i] = new Ship(this.width, this.height, 2, i, this.sounds);
        }
        this.boulder = new Rocks(this.width, this.height, this.astnr + this.level, this.sounds);
        this.f = new Font("Courier", 0, 12);
    }
    
    public void soundControl(final boolean sound_on) {
        for (int i = 0; i < this.ships.length; ++i) {
            this.ships[i].sound_on = sound_on;
        }
        this.boulder.sound_on = sound_on;
        this.sound_on = sound_on;
    }
}
