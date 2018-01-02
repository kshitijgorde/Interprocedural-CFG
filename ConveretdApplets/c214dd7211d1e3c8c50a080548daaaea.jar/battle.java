import java.awt.Event;
import java.awt.Graphics;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class battle extends Applet implements Runnable
{
    Color col;
    String message;
    String Status;
    String[] message2;
    int hits;
    int numDestroyed;
    int[] battlepos;
    int[] carrierpos;
    int[] subpos;
    int[] patrolpos;
    int[] destroyerpos;
    int guesses;
    int[][] player;
    int[][] guessed;
    int[][] computer;
    int battleshipHitCt;
    int destroyerHitCt;
    int subHitCt;
    int patrolHitCt;
    int carrierHitCt;
    boolean init;
    boolean ok;
    boolean battleOK;
    boolean patrolOK;
    boolean subOK;
    boolean destroyerOK;
    boolean carrierOK;
    boolean won;
    int o;
    
    public void getCarrier() {
        int i = (int)(10.0 * Math.random());
        int j = (int)(10.0 * Math.random());
        final int n = (int)(100.0 * Math.random() / 25.0);
        if (n == 0) {
            while (j < 4) {
                j = (int)(10.0 * Math.random());
            }
            for (int k = 0; k < 5; ++k) {
                this.computer[i][j - k] = 1;
                this.carrierpos[k] = i * 10 + j - k;
            }
        }
        if (n == 1) {
            while (i > 5) {
                i = (int)(10.0 * Math.random());
            }
            for (int l = 0; l < 5; ++l) {
                this.computer[i + l][j] = 1;
                this.carrierpos[l] = (i + l) * 10 + j;
            }
        }
        if (n == 2) {
            while (j > 5) {
                j = (int)(10.0 * Math.random());
            }
            for (int n2 = 0; n2 < 5; ++n2) {
                this.computer[i][j + n2] = 1;
                this.carrierpos[n2] = i * 10 + j + n2;
            }
        }
        if (n == 3) {
            while (i < 4) {
                i = (int)(10.0 * Math.random());
            }
            for (int n3 = 0; n3 < 5; ++n3) {
                this.computer[i - n3][j] = 1;
                this.carrierpos[n3] = (i - n3) * 10 + j;
            }
        }
    }
    
    public void getBattle() {
        int i = (int)(10.0 * Math.random());
        int j = (int)(10.0 * Math.random());
        int n = (int)(100.0 * Math.random() / 25.0);
        while (!this.ok) {
            this.ok = true;
            i = (int)(10.0 * Math.random());
            j = (int)(10.0 * Math.random());
            n = (int)(100.0 * Math.random() / 25.0);
            if (n == 0) {
                while (j < 3) {
                    j = (int)(10.0 * Math.random());
                }
                for (int k = 0; k < 4; ++k) {
                    this.o = this.computer[i][j - k];
                    if (this.o == 1) {
                        this.ok = false;
                    }
                }
            }
            if (n == 1) {
                while (i > 6) {
                    i = (int)(10.0 * Math.random());
                }
                for (int l = 0; l < 4; ++l) {
                    this.o = this.computer[i + l][j];
                    if (this.o == 1) {
                        this.ok = false;
                    }
                }
            }
            if (n == 2) {
                while (j > 6) {
                    j = (int)(10.0 * Math.random());
                }
                for (int n2 = 0; n2 < 4; ++n2) {
                    this.o = this.computer[i][j + n2];
                    if (this.o == 1) {
                        this.ok = false;
                    }
                }
            }
            if (n == 3) {
                while (i < 3) {
                    i = (int)(10.0 * Math.random());
                }
                for (int n3 = 0; n3 < 4; ++n3) {
                    this.o = this.computer[i - n3][j];
                    if (this.o == 1) {
                        this.ok = false;
                    }
                }
            }
        }
        if (n == 0) {
            for (int n4 = 0; n4 < 4; ++n4) {
                this.computer[i][j - n4] = 1;
                this.battlepos[n4] = i * 10 + j - n4;
            }
        }
        if (n == 1) {
            for (int n5 = 0; n5 < 4; ++n5) {
                this.computer[i + n5][j] = 1;
                this.battlepos[n5] = (i + n5) * 10 + j;
            }
        }
        if (n == 2) {
            for (int n6 = 0; n6 < 4; ++n6) {
                this.computer[i][j + n6] = 1;
                this.battlepos[n6] = i * 10 + j + n6;
            }
        }
        if (n == 3) {
            for (int n7 = 0; n7 < 4; ++n7) {
                this.computer[i - n7][j] = 1;
                this.battlepos[n7] = (i - n7) * 10 + j;
            }
        }
    }
    
    public void getSub() {
        int i = (int)(10.0 * Math.random());
        int j = (int)(10.0 * Math.random());
        int n = (int)(100.0 * Math.random() / 25.0);
        while (!this.ok) {
            this.ok = true;
            i = (int)(10.0 * Math.random());
            j = (int)(10.0 * Math.random());
            n = (int)(100.0 * Math.random() / 25.0);
            if (n == 0) {
                while (j < 2) {
                    j = (int)(10.0 * Math.random());
                }
                for (int k = 0; k < 3; ++k) {
                    this.o = this.computer[i][j - k];
                    if (this.o == 1) {
                        this.ok = false;
                    }
                }
            }
            if (n == 1) {
                while (i > 7) {
                    i = (int)(10.0 * Math.random());
                }
                for (int l = 0; l < 3; ++l) {
                    this.o = this.computer[i + l][j];
                    if (this.o == 1) {
                        this.ok = false;
                    }
                }
            }
            if (n == 2) {
                while (j > 7) {
                    j = (int)(10.0 * Math.random());
                }
                for (int n2 = 0; n2 < 3; ++n2) {
                    this.o = this.computer[i][j + n2];
                    if (this.o == 1) {
                        this.ok = false;
                    }
                }
            }
            if (n == 3) {
                while (i < 2) {
                    i = (int)(10.0 * Math.random());
                }
                for (int n3 = 0; n3 < 3; ++n3) {
                    this.o = this.computer[i - n3][j];
                    if (this.o == 1) {
                        this.ok = false;
                    }
                }
            }
        }
        if (n == 0) {
            for (int n4 = 0; n4 < 3; ++n4) {
                this.computer[i][j - n4] = 1;
                this.subpos[n4] = i * 10 + j - n4;
            }
        }
        if (n == 1) {
            for (int n5 = 0; n5 < 3; ++n5) {
                this.computer[i + n5][j] = 1;
                this.subpos[n5] = (i + n5) * 10 + j;
            }
        }
        if (n == 2) {
            for (int n6 = 0; n6 < 3; ++n6) {
                this.computer[i][j + n6] = 1;
                this.subpos[n6] = i * 10 + j + n6;
            }
        }
        if (n == 3) {
            for (int n7 = 0; n7 < 3; ++n7) {
                this.computer[i - n7][j] = 1;
                this.subpos[n7] = (i - n7) * 10 + j;
            }
        }
    }
    
    public void getDestroyer() {
        int i = (int)(10.0 * Math.random());
        int j = (int)(10.0 * Math.random());
        int n = (int)(100.0 * Math.random() / 25.0);
        while (!this.ok) {
            this.ok = true;
            i = (int)(10.0 * Math.random());
            j = (int)(10.0 * Math.random());
            n = (int)(100.0 * Math.random() / 25.0);
            if (n == 0) {
                while (j < 2) {
                    j = (int)(10.0 * Math.random());
                }
                for (int k = 0; k < 3; ++k) {
                    this.o = this.computer[i][j - k];
                    if (this.o == 1) {
                        this.ok = false;
                    }
                }
            }
            if (n == 1) {
                while (i > 7) {
                    i = (int)(10.0 * Math.random());
                }
                for (int l = 0; l < 3; ++l) {
                    this.o = this.computer[i + l][j];
                    if (this.o == 1) {
                        this.ok = false;
                    }
                }
            }
            if (n == 2) {
                while (j > 7) {
                    j = (int)(10.0 * Math.random());
                }
                for (int n2 = 0; n2 < 3; ++n2) {
                    this.o = this.computer[i][j + n2];
                    if (this.o == 1) {
                        this.ok = false;
                    }
                }
            }
            if (n == 3) {
                while (i < 2) {
                    i = (int)(10.0 * Math.random());
                }
                for (int n3 = 0; n3 < 3; ++n3) {
                    this.o = this.computer[i - n3][j];
                    if (this.o == 1) {
                        this.ok = false;
                    }
                }
            }
        }
        if (n == 0) {
            for (int n4 = 0; n4 < 3; ++n4) {
                this.computer[i][j - n4] = 1;
                this.destroyerpos[n4] = i * 10 + j - n4;
            }
        }
        if (n == 1) {
            for (int n5 = 0; n5 < 3; ++n5) {
                this.computer[i + n5][j] = 1;
                this.destroyerpos[n5] = (i + n5) * 10 + j;
            }
        }
        if (n == 2) {
            for (int n6 = 0; n6 < 3; ++n6) {
                this.computer[i][j + n6] = 1;
                this.destroyerpos[n6] = i * 10 + j + n6;
            }
        }
        if (n == 3) {
            for (int n7 = 0; n7 < 3; ++n7) {
                this.computer[i - n7][j] = 1;
                this.destroyerpos[n7] = (i - n7) * 10 + j;
            }
        }
    }
    
    public void getPatrol() {
        int i = (int)(10.0 * Math.random());
        int j = (int)(10.0 * Math.random());
        int n = (int)(100.0 * Math.random() / 25.0);
        while (!this.ok) {
            this.ok = true;
            i = (int)(10.0 * Math.random());
            j = (int)(10.0 * Math.random());
            n = (int)(100.0 * Math.random() / 25.0);
            if (n == 0) {
                while (j < 1) {
                    j = (int)(10.0 * Math.random());
                }
                for (int k = 0; k < 2; ++k) {
                    this.o = this.computer[i][j - k];
                    if (this.o == 1) {
                        this.ok = false;
                    }
                }
            }
            if (n == 1) {
                while (i > 8) {
                    i = (int)(10.0 * Math.random());
                }
                for (int l = 0; l < 2; ++l) {
                    this.o = this.computer[i + l][j];
                    if (this.o == 1) {
                        this.ok = false;
                    }
                }
            }
            if (n == 2) {
                while (j > 8) {
                    j = (int)(10.0 * Math.random());
                }
                for (int n2 = 0; n2 < 2; ++n2) {
                    this.o = this.computer[i][j + n2];
                    if (this.o == 1) {
                        this.ok = false;
                    }
                }
            }
            if (n == 3) {
                while (i < 1) {
                    i = (int)(10.0 * Math.random());
                }
                for (int n3 = 0; n3 < 2; ++n3) {
                    this.o = this.computer[i - n3][j];
                    if (this.o == 1) {
                        this.ok = false;
                    }
                }
            }
        }
        if (n == 0) {
            for (int n4 = 0; n4 < 2; ++n4) {
                this.computer[i][j - n4] = 1;
                this.patrolpos[n4] = i * 10 + j - n4;
            }
        }
        if (n == 1) {
            for (int n5 = 0; n5 < 2; ++n5) {
                this.computer[i + n5][j] = 1;
                this.patrolpos[n5] = (i + n5) * 10 + j;
            }
        }
        if (n == 2) {
            for (int n6 = 0; n6 < 2; ++n6) {
                this.computer[i][j + n6] = 1;
                this.patrolpos[n6] = i * 10 + j + n6;
            }
        }
        if (n == 3) {
            for (int n7 = 0; n7 < 2; ++n7) {
                this.computer[i - n7][j] = 1;
                this.patrolpos[n7] = (i - n7) * 10 + j;
            }
        }
    }
    
    public void run() {
    }
    
    public void init() {
        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < 10; ++j) {
                this.guessed[i][j] = 2;
            }
        }
        for (int k = 0; k < 2; ++k) {
            this.patrolpos[k] = 0;
        }
        for (int l = 0; l < 2; ++l) {
            this.destroyerpos[l] = 0;
        }
        for (int n = 0; n < 2; ++n) {
            this.subpos[n] = 0;
        }
        for (int n2 = 0; n2 < 2; ++n2) {
            this.carrierpos[n2] = 0;
        }
        for (int n3 = 0; n3 < 2; ++n3) {
            this.battlepos[n3] = 0;
        }
        this.battleshipHitCt = 0;
        this.destroyerHitCt = 0;
        this.subHitCt = 0;
        this.patrolHitCt = 0;
        this.carrierHitCt = 0;
        this.getCarrier();
        this.ok = false;
        this.getBattle();
        this.ok = false;
        this.getDestroyer();
        this.ok = false;
        this.getSub();
        this.ok = false;
        this.getPatrol();
    }
    
    public void paint(final Graphics graphics) {
        if (!this.init && !this.won) {
            graphics.setColor(Color.white);
            graphics.fillRect(0, 0, 600, 320);
            graphics.setColor(Color.black);
            graphics.fillRect(20, 20, 30, 25);
            graphics.setColor(Color.white);
            graphics.drawString("PLAY", 23, 30);
            graphics.setColor(Color.black);
            graphics.drawString("This is different that usual battleship, here the goal is", 23, 65);
            graphics.drawString("to find all of the computers ships in the least number of guesses.", 23, 85);
            graphics.drawString("<30   turns  --- You have too much luck!", 23, 105);
            graphics.drawString("31-50 turns  --- Excellent!", 23, 125);
            graphics.drawString(">=51  turns  --- You have no skills!", 23, 145);
            graphics.drawString("PRESS PLAY TO BEGIN", 23, 165);
        }
        if (this.init && !this.won) {
            graphics.setColor(Color.black);
            graphics.fillRect(0, 0, 600, 320);
            graphics.setColor(Color.white);
            graphics.drawString("# of Guesses = " + this.guesses, 500, 280);
            for (int i = 0; i < 11; ++i) {
                graphics.drawLine(20, 20 + i * 28, 300, 20 + i * 28);
            }
            for (int j = 0; j < 11; ++j) {
                graphics.drawLine(20 + j * 28, 20, 20 + j * 28, 300);
            }
            char c = 'A';
            for (int k = 0; k < 10; ++k) {
                graphics.drawString(String.valueOf(c) + " ", 30 + 28 * k, 13);
                ++c;
            }
            char c2 = '1';
            for (int l = 0; l < 9; ++l) {
                graphics.drawString(String.valueOf(c2) + " ", 10, 40 + 28 * l);
                ++c2;
            }
            graphics.drawString("10", 6, 290);
            graphics.drawString(this.message, 420, 40);
            for (int n = 0; n < this.numDestroyed; ++n) {
                graphics.drawString(this.message2[n], 305, 40 + (n + 1) * 20);
            }
            graphics.setColor(Color.black);
            for (int n2 = 0; n2 < 10; ++n2) {
                for (int n3 = 0; n3 < 10; ++n3) {
                    if (this.guessed[n2][n3] == 1) {
                        graphics.setColor(Color.red);
                        graphics.fillOval(n2 * 28 + 30, n3 * 28 + 30, 10, 10);
                    }
                    if (this.guessed[n2][n3] == 0) {
                        graphics.setColor(Color.white);
                        graphics.fillOval(n2 * 28 + 30, n3 * 28 + 30, 10, 10);
                    }
                }
            }
        }
    }
    
    public boolean seeifahit(final int n, final int n2) {
        return this.computer[n][n2] == 1;
    }
    
    public String getshiptype(final int n, final int n2) {
        for (int i = 0; i < 4; ++i) {
            if (this.battlepos[i] == n * 10 + n2) {
                ++this.battleshipHitCt;
                return "Battleship";
            }
        }
        for (int j = 0; j < 3; ++j) {
            if (this.subpos[j] == n * 10 + n2) {
                ++this.subHitCt;
                return "Submarine";
            }
        }
        for (int k = 0; k < 2; ++k) {
            if (this.patrolpos[k] == n * 10 + n2) {
                ++this.patrolHitCt;
                return "Patrol Boat";
            }
        }
        for (int l = 0; l < 5; ++l) {
            if (this.carrierpos[l] == n * 10 + n2) {
                ++this.carrierHitCt;
                return "Carrier";
            }
        }
        for (int n3 = 0; n3 < 3; ++n3) {
            if (this.destroyerpos[n3] == n * 10 + n2) {
                ++this.destroyerHitCt;
                return "Destroyer";
            }
        }
        return "  ";
    }
    
    public void gameOver() {
        if (!this.won) {
            final Graphics graphics = this.getGraphics();
            graphics.setColor(Color.black);
            graphics.fillRect(0, 0, 600, 320);
            graphics.setColor(Color.white);
            graphics.drawString("YOU WIN!!", 200, 100);
            graphics.drawString(new String("It took you " + this.guesses + " tries!"), 200, 120);
            graphics.drawString("Press refresh on your browser to play again", 200, 150);
            this.won = true;
        }
    }
    
    public boolean destroyed() {
        if (this.battleshipHitCt == 4 && this.battleOK) {
            this.battleOK = false;
            return true;
        }
        if (this.carrierHitCt == 5 && this.carrierOK) {
            this.carrierOK = false;
            return true;
        }
        if (this.subHitCt == 3 && this.subOK) {
            this.subOK = false;
            return true;
        }
        if (this.patrolHitCt == 2 && this.patrolOK) {
            this.patrolOK = false;
            return true;
        }
        if (this.destroyerHitCt == 3 && this.destroyerOK) {
            this.destroyerOK = false;
            return true;
        }
        return false;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        final String s = new String("");
        if (!this.won) {
            this.message = "";
            final Graphics graphics = this.getGraphics();
            graphics.setColor(Color.white);
            if (n > 20 && n2 > 20 && n2 < 300 && n < 300 && this.init) {
                ++this.guesses;
                graphics.setColor(Color.black);
                graphics.fillRect(490, 260, 550, 285);
                graphics.setColor(Color.white);
                graphics.drawString("# of Guesses = " + this.guesses, 500, 280);
                graphics.setColor(Color.black);
                graphics.fillRect(419, 30, 580, 95);
                graphics.setColor(Color.white);
                final int n3 = (n - 20) / 28;
                final int n4 = (n2 - 20) / 28;
                if (this.guessed[n3][n4] == 2) {
                    if (this.seeifahit(n3, n4)) {
                        final String getshiptype = this.getshiptype(n3, n4);
                        ++this.hits;
                        graphics.setColor(Color.white);
                        this.guessed[n3][n4] = 1;
                        if (!this.destroyed()) {
                            graphics.drawString(this.message = String.valueOf(getshiptype) + " Hit", 420, 40);
                        }
                        else {
                            graphics.setColor(Color.white);
                            this.message2[this.numDestroyed] = new String("");
                            this.message2[this.numDestroyed] = String.valueOf(getshiptype) + "Destroyed";
                            ++this.numDestroyed;
                            graphics.drawString(this.message2[this.numDestroyed - 1], 305, 40 + this.numDestroyed * 20);
                        }
                        graphics.setColor(Color.red);
                    }
                    else {
                        this.guessed[n3][n4] = 0;
                    }
                    graphics.fillOval(n3 * 28 + 30, n4 * 28 + 30, 10, 10);
                }
                else {
                    graphics.setColor(Color.white);
                    graphics.drawString(this.message = "That has already been guessed", 420, 40);
                }
            }
            if (n > 20 && n < 40 && n2 > 20 && n2 < 40 && !this.init) {
                this.init = true;
                this.paint(graphics);
            }
            if (this.hits >= 17) {
                this.gameOver();
                System.exit(0);
            }
        }
        return true;
    }
    
    public battle() {
        this.message = new String("");
        this.Status = new String("");
        this.message2 = new String[5];
        this.battlepos = new int[4];
        this.carrierpos = new int[5];
        this.subpos = new int[3];
        this.patrolpos = new int[2];
        this.destroyerpos = new int[3];
        this.player = new int[10][10];
        this.guessed = new int[10][10];
        this.computer = new int[10][10];
        this.init = false;
        this.ok = false;
        this.battleOK = true;
        this.patrolOK = true;
        this.subOK = true;
        this.destroyerOK = true;
        this.carrierOK = true;
        this.won = false;
    }
}
