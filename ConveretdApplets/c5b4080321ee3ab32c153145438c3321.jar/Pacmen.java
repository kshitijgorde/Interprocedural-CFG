import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class Pacmen extends Thread
{
    static Color itColor;
    static Color itColor2;
    static Graphics g;
    char dir;
    boolean p;
    int x;
    int y;
    int stage;
    boolean closing;
    static boolean toYellow;
    static boolean toGreen;
    static final int escapeDelay = 8;
    static final int itDelay = 6;
    static boolean alive;
    boolean it;
    static Font itFont;
    boolean waitingUp;
    boolean waitingDown;
    boolean waitingRight;
    boolean waitingLeft;
    short delay;
    static final int pacWidth = 25;
    static final int pacHeight = 25;
    
    Pacmen(final int x, final int y, final boolean it, final boolean p4) {
        this.dir = 'r';
        this.stage = 44;
        this.closing = true;
        this.waitingUp = false;
        this.waitingDown = false;
        this.waitingRight = false;
        this.waitingLeft = false;
        this.x = x;
        this.y = y;
        this.it = it;
        this.p = p4;
        if (it) {
            this.delay = 6;
            return;
        }
        this.delay = 8;
    }
    
    public void run() {
        while (Pacmen.alive) {
            if (this.closing) {
                if (this.stage > 0) {
                    this.stage -= 2;
                }
                else {
                    this.stage += 2;
                    this.closing = false;
                }
            }
            else if (this.stage < 44) {
                this.stage += 2;
            }
            else {
                this.stage -= 2;
                this.closing = true;
            }
            if (this.dir == 'r') {
                if (this.it) {
                    if (this.p) {
                        Pacmen.g.setColor(Pacmen.itColor);
                    }
                    else {
                        Pacmen.g.setColor(Pacmen.itColor2);
                    }
                }
                else if (this.p) {
                    Pacmen.g.setColor(Color.yellow);
                }
                else {
                    Pacmen.g.setColor(Color.blue);
                }
                Pacmen.g.fillArc(this.x, this.y, 25, 25, this.stage, 360 - this.stage * 2);
                Pacmen.g.setColor(Color.black);
                Pacmen.g.drawOval(this.x + 7, this.y + 3, 5, 5);
                Pacmen.g.fillOval(this.x + 10, this.y + 5, 3, 3);
                if (this.it) {
                    Pacmen.g.setColor(Color.black);
                    Pacmen.g.setFont(Pacmen.itFont);
                    Pacmen.g.drawString("IT", this.x + 9, this.y + 17);
                }
                try {
                    Thread.sleep(this.delay);
                }
                catch (Exception ex) {}
                Pacmen.g.setColor(Color.black);
                Pacmen.g.fillRect(this.x - 1, this.y, 26, 25);
                if (this.checkRight()) {
                    ++this.x;
                    if (this.x >= 425) {
                        this.x = 0;
                    }
                    if (this.waitingDown && this.checkDown()) {
                        this.dir = 'd';
                    }
                    if (this.waitingLeft && this.checkLeft()) {
                        this.dir = 'l';
                    }
                    if (this.waitingUp && this.checkUp()) {
                        this.dir = 'u';
                    }
                    if (this == Pacman.p) {
                        if (this.x + 25 > Pacman.p2.x && this.x < Pacman.p2.x + 25 && this.y + 25 > Pacman.p2.y && this.y < Pacman.p2.y + 25) {
                            Pacman.tag(this.x, this.y);
                        }
                    }
                    else if (this.x + 25 > Pacman.p.x && this.x < Pacman.p.x + 25 && this.y + 25 > Pacman.p.y && this.y < Pacman.p.y + 25) {
                        Pacman.tag(this.x, this.y);
                    }
                }
            }
            if (this.dir == 'u') {
                if (this.it) {
                    if (this.p) {
                        Pacmen.g.setColor(Pacmen.itColor);
                    }
                    else {
                        Pacmen.g.setColor(Pacmen.itColor2);
                    }
                }
                else if (this.p) {
                    Pacmen.g.setColor(Color.yellow);
                }
                else {
                    Pacmen.g.setColor(Color.blue);
                }
                Pacmen.g.fillArc(this.x, this.y, 25, 25, 90 + this.stage, 360 - this.stage * 2);
                Pacmen.g.setColor(Color.black);
                Pacmen.g.drawOval(this.x + 3, this.y + 14, 5, 5);
                Pacmen.g.fillOval(this.x + 5, this.y + 14, 3, 3);
                if (this.it) {
                    Pacmen.g.setColor(Color.black);
                    Pacmen.g.setFont(Pacmen.itFont);
                    Pacmen.g.drawString("IT", this.x + 9, this.y + 17);
                }
                try {
                    Thread.sleep(this.delay);
                }
                catch (Exception ex2) {}
                Pacmen.g.setColor(Color.black);
                Pacmen.g.fillRect(this.x, this.y, 25, 26);
                if (this.checkUp()) {
                    --this.y;
                    if (this.y <= -25) {
                        this.y = 375;
                    }
                    if (this.waitingRight && this.checkRight()) {
                        this.dir = 'r';
                    }
                    if (this.waitingLeft && this.checkLeft()) {
                        this.dir = 'l';
                    }
                    if (this.waitingDown && this.checkDown()) {
                        this.dir = 'd';
                    }
                    if (this == Pacman.p) {
                        if (this.x + 25 > Pacman.p2.x && this.x < Pacman.p2.x + 25 && this.y + 25 > Pacman.p2.y && this.y < Pacman.p2.y + 25) {
                            Pacman.tag(this.x, this.y);
                        }
                    }
                    else if (this.x + 25 > Pacman.p.x && this.x < Pacman.p.x + 25 && this.y + 25 > Pacman.p.y && this.y < Pacman.p.y + 25) {
                        Pacman.tag(this.x, this.y);
                    }
                }
            }
            if (this.dir == 'l') {
                if (this.it) {
                    if (this.p) {
                        Pacmen.g.setColor(Pacmen.itColor);
                    }
                    else {
                        Pacmen.g.setColor(Pacmen.itColor2);
                    }
                }
                else if (this.p) {
                    Pacmen.g.setColor(Color.yellow);
                }
                else {
                    Pacmen.g.setColor(Color.blue);
                }
                Pacmen.g.fillArc(this.x, this.y, 25, 25, 180 + this.stage, 360 - this.stage * 2);
                Pacmen.g.setColor(Color.black);
                Pacmen.g.drawOval(this.x + 12, this.y + 3, 5, 5);
                Pacmen.g.fillOval(this.x + 12, this.y + 5, 3, 3);
                if (this.it) {
                    Pacmen.g.setColor(Color.black);
                    Pacmen.g.setFont(Pacmen.itFont);
                    Pacmen.g.drawString("IT", this.x + 9, this.y + 17);
                }
                try {
                    Thread.sleep(this.delay);
                }
                catch (Exception ex3) {}
                Pacmen.g.setColor(Color.black);
                Pacmen.g.fillRect(this.x, this.y, 26, 25);
                if (this.checkLeft()) {
                    --this.x;
                    if (this.x <= -25) {
                        this.x = 400;
                    }
                    if (this.waitingRight && this.checkRight()) {
                        this.dir = 'r';
                    }
                    if (this.waitingDown && this.checkDown()) {
                        this.dir = 'd';
                    }
                    if (this.waitingUp && this.checkUp()) {
                        this.dir = 'u';
                    }
                    if (this == Pacman.p) {
                        if (this.x + 25 > Pacman.p2.x && this.x < Pacman.p2.x + 25 && this.y + 25 > Pacman.p2.y && this.y < Pacman.p2.y + 25) {
                            Pacman.tag(this.x, this.y);
                        }
                    }
                    else if (this.x + 25 > Pacman.p.x && this.x < Pacman.p.x + 25 && this.y + 25 > Pacman.p.y && this.y < Pacman.p.y + 25) {
                        Pacman.tag(this.x, this.y);
                    }
                }
            }
            if (this.dir == 'd') {
                if (this.it) {
                    if (this.p) {
                        Pacmen.g.setColor(Pacmen.itColor);
                    }
                    else {
                        Pacmen.g.setColor(Pacmen.itColor2);
                    }
                }
                else if (this.p) {
                    Pacmen.g.setColor(Color.yellow);
                }
                else {
                    Pacmen.g.setColor(Color.blue);
                }
                Pacmen.g.fillArc(this.x, this.y, 25, 25, 270 + this.stage, 360 - this.stage * 2);
                Pacmen.g.setColor(Color.black);
                Pacmen.g.drawOval(this.x + 18, this.y + 11, 5, 5);
                Pacmen.g.fillOval(this.x + 19, this.y + 14, 3, 3);
                if (this.it) {
                    Pacmen.g.setColor(Color.black);
                    Pacmen.g.setFont(Pacmen.itFont);
                    Pacmen.g.drawString("IT", this.x + 9, this.y + 17);
                }
                try {
                    Thread.sleep(this.delay);
                }
                catch (Exception ex4) {}
                Pacmen.g.setColor(Color.black);
                Pacmen.g.fillRect(this.x, this.y - 1, 25, 26);
                if (!this.checkDown()) {
                    continue;
                }
                if (this.y >= 375) {
                    Pacmen.g.setColor(Color.black);
                    Pacmen.g.fillRect(this.x, 375, 25, 25);
                    this.y = -25;
                }
                ++this.y;
                if (this.waitingRight && this.checkRight()) {
                    this.dir = 'r';
                }
                if (this.waitingLeft && this.checkLeft()) {
                    this.dir = 'l';
                }
                if (this.waitingUp && this.checkUp()) {
                    this.dir = 'u';
                }
                if (this == Pacman.p) {
                    if (this.x + 25 <= Pacman.p2.x || this.x >= Pacman.p2.x + 25 || this.y + 25 <= Pacman.p2.y || this.y >= Pacman.p2.y + 25) {
                        continue;
                    }
                    Pacman.tag(this.x, this.y);
                }
                else {
                    if (this.x + 25 <= Pacman.p.x || this.x >= Pacman.p.x + 25 || this.y + 25 <= Pacman.p.y || this.y >= Pacman.p.y + 25) {
                        continue;
                    }
                    Pacman.tag(this.x, this.y);
                }
            }
        }
    }
    
    public boolean checkRight() {
        boolean b = true;
        if (Pacman.level == 1) {
            for (int i = 0; i < Pacman.level1.size(); ++i) {
                final Wall wall = Pacman.level1.elementAt(i);
                if (wall.vertical) {
                    if (this.x + 25 >= wall.x1 && this.y + 25 > wall.y1 && this.y < wall.y1 + wall.length && this.x + 25 <= wall.x1) {
                        b = false;
                    }
                }
                else if (this.x + 25 >= wall.x1 && this.y + 25 > wall.y1 && this.y < wall.y1 + 25 && this.x + 25 <= wall.x1) {
                    b = false;
                }
            }
        }
        else if (Pacman.level == 2) {
            for (int j = 0; j < Pacman.level2.size(); ++j) {
                final Wall wall2 = Pacman.level2.elementAt(j);
                if (wall2.vertical) {
                    if (this.x + 25 >= wall2.x1 && this.y + 25 > wall2.y1 && this.y < wall2.y1 + wall2.length && this.x + 25 <= wall2.x1) {
                        b = false;
                    }
                }
                else if (this.x + 25 >= wall2.x1 && this.y + 25 > wall2.y1 && this.y < wall2.y1 + 25 && this.x + 25 <= wall2.x1) {
                    b = false;
                }
            }
        }
        else if (Pacman.level == 3) {
            for (int k = 0; k < Pacman.level3.size(); ++k) {
                final Wall wall3 = Pacman.level3.elementAt(k);
                if (wall3.vertical) {
                    if (this.x + 25 >= wall3.x1 && this.y + 25 > wall3.y1 && this.y < wall3.y1 + wall3.length && this.x + 25 <= wall3.x1) {
                        b = false;
                    }
                }
                else if (this.x + 25 >= wall3.x1 && this.y + 25 > wall3.y1 && this.y < wall3.y1 + 25 && this.x + 25 <= wall3.x1) {
                    b = false;
                }
            }
            if (this.x >= 400) {
                for (int l = 0; l < Pacman.level3.size(); ++l) {
                    final Wall wall4 = Pacman.level3.elementAt(l);
                    if (wall4.x1 == 0) {
                        if (wall4.vertical) {
                            if (wall4.y1 < this.y + 25 && wall4.y1 + wall4.length > this.y) {
                                b = false;
                            }
                        }
                        else if (wall4.y1 < this.y + 25 && wall4.y1 + 25 > this.y) {
                            b = false;
                        }
                    }
                }
            }
        }
        else if (Pacman.level == 4) {
            for (int n = 0; n < Pacman.levelCustom.size(); ++n) {
                final Wall wall5 = Pacman.levelCustom.elementAt(n);
                if (wall5.vertical) {
                    if (this.x + 25 >= wall5.x1 && this.y + 25 > wall5.y1 && this.y < wall5.y1 + wall5.length && this.x + 25 <= wall5.x1) {
                        b = false;
                    }
                }
                else if (this.x + 25 >= wall5.x1 && this.y + 25 > wall5.y1 && this.y < wall5.y1 + 25 && this.x + 25 <= wall5.x1) {
                    b = false;
                }
            }
            if (this.x >= 400) {
                for (int n2 = 0; n2 < Pacman.levelCustom.size(); ++n2) {
                    final Wall wall6 = Pacman.levelCustom.elementAt(n2);
                    if (wall6.x1 == 0) {
                        if (wall6.vertical) {
                            if (wall6.y1 < this.y + 25 && wall6.y1 + wall6.length > this.y) {
                                b = false;
                            }
                        }
                        else if (wall6.y1 < this.y + 25 && wall6.y1 + 25 > this.y) {
                            b = false;
                        }
                    }
                }
            }
        }
        return b;
    }
    
    public boolean checkUp() {
        boolean b = true;
        if (Pacman.level == 1) {
            for (int i = 0; i < Pacman.level1.size(); ++i) {
                final Wall wall = Pacman.level1.elementAt(i);
                if (!wall.vertical) {
                    if (this.y <= wall.y1 + 25 && this.x + 25 > wall.x1 && this.x < wall.x1 + wall.length && this.y >= wall.y1) {
                        b = false;
                    }
                }
                else if (this.y <= wall.y1 + wall.length && this.x + 25 > wall.x1 && this.x < wall.x1 + 25 && this.y >= wall.y1) {
                    b = false;
                }
            }
        }
        else if (Pacman.level == 2) {
            for (int j = 0; j < Pacman.level2.size(); ++j) {
                final Wall wall2 = Pacman.level2.elementAt(j);
                if (!wall2.vertical) {
                    if (this.y <= wall2.y1 + 25 && this.x + 25 > wall2.x1 && this.x < wall2.x1 + wall2.length && this.y >= wall2.y1) {
                        b = false;
                    }
                }
                else if (this.y <= wall2.y1 + wall2.length && this.x + 25 > wall2.x1 && this.x < wall2.x1 + 25 && this.y >= wall2.y1) {
                    b = false;
                }
            }
        }
        else if (Pacman.level == 3) {
            for (int k = 0; k < Pacman.level3.size(); ++k) {
                final Wall wall3 = Pacman.level3.elementAt(k);
                if (!wall3.vertical) {
                    if (this.y <= wall3.y1 + 25 && this.x + 25 > wall3.x1 && this.x < wall3.x1 + wall3.length && this.y >= wall3.y1) {
                        b = false;
                    }
                }
                else if (this.y <= wall3.y1 + wall3.length && this.x + 25 > wall3.x1 && this.x < wall3.x1 + 25 && this.y >= wall3.y1) {
                    b = false;
                }
            }
            if (this.y <= 0) {
                for (int l = 0; l < Pacman.level3.size(); ++l) {
                    final Wall wall4 = Pacman.level3.elementAt(l);
                    if (wall4.y1 == 375 || (wall4.y1 + wall4.length == 400 && wall4.vertical)) {
                        if (wall4.vertical) {
                            if (this.x < wall4.x1 + 25 && this.x + 25 > wall4.x1) {
                                b = false;
                            }
                        }
                        else if (this.x < wall4.x1 + wall4.length && this.x + 25 > wall4.x1) {
                            b = false;
                        }
                    }
                }
            }
        }
        else if (Pacman.level == 4) {
            for (int n = 0; n < Pacman.levelCustom.size(); ++n) {
                final Wall wall5 = Pacman.levelCustom.elementAt(n);
                if (!wall5.vertical) {
                    if (this.y <= wall5.y1 + 25 && this.x + 25 > wall5.x1 && this.x < wall5.x1 + wall5.length && this.y >= wall5.y1) {
                        b = false;
                    }
                }
                else if (this.y <= wall5.y1 + wall5.length && this.x + 25 > wall5.x1 && this.x < wall5.x1 + 25 && this.y >= wall5.y1) {
                    b = false;
                }
            }
            if (this.y <= 0) {
                for (int n2 = 0; n2 < Pacman.levelCustom.size(); ++n2) {
                    final Wall wall6 = Pacman.levelCustom.elementAt(n2);
                    if (wall6.y1 == 375) {
                        if (wall6.vertical) {
                            if (this.x < wall6.x1 + 25 && this.x + 25 > wall6.x1) {
                                b = false;
                            }
                        }
                        else if (this.x < wall6.x1 + wall6.length && this.x + 25 > wall6.x1) {
                            b = false;
                        }
                    }
                }
            }
        }
        return b;
    }
    
    public boolean checkLeft() {
        boolean b = true;
        if (Pacman.level == 1) {
            for (int i = 0; i < Pacman.level1.size(); ++i) {
                final Wall wall = Pacman.level1.elementAt(i);
                if (wall.vertical) {
                    if (this.x <= wall.x1 + 25 && this.y + 25 > wall.y1 && this.y < wall.y1 + wall.length && this.x >= wall.x1) {
                        b = false;
                    }
                }
                else if (this.x <= wall.x1 + wall.length && this.y + 25 > wall.y1 && this.y < wall.y1 + 25 && this.x >= wall.x1) {
                    b = false;
                }
            }
        }
        else if (Pacman.level == 2) {
            for (int j = 0; j < Pacman.level2.size(); ++j) {
                final Wall wall2 = Pacman.level2.elementAt(j);
                if (wall2.vertical) {
                    if (this.x <= wall2.x1 + 25 && this.y + 25 > wall2.y1 && this.y < wall2.y1 + wall2.length && this.x >= wall2.x1) {
                        b = false;
                    }
                }
                else if (this.x <= wall2.x1 + wall2.length && this.y + 25 > wall2.y1 && this.y < wall2.y1 + 25 && this.x >= wall2.x1) {
                    b = false;
                }
            }
        }
        else if (Pacman.level == 3) {
            for (int k = 0; k < Pacman.level3.size(); ++k) {
                final Wall wall3 = Pacman.level3.elementAt(k);
                if (wall3.vertical) {
                    if (this.x <= wall3.x1 + 25 && this.y + 25 > wall3.y1 && this.y < wall3.y1 + wall3.length && this.x >= wall3.x1) {
                        b = false;
                    }
                }
                else if (this.x <= wall3.x1 + wall3.length && this.y + 25 > wall3.y1 && this.y < wall3.y1 + 25 && this.x >= wall3.x1) {
                    b = false;
                }
            }
            if (this.x <= 0) {
                for (int l = 0; l < Pacman.level3.size(); ++l) {
                    final Wall wall4 = Pacman.level3.elementAt(l);
                    if (wall4.x1 == 400 || (wall4.x1 + wall4.length == 425 && !wall4.vertical)) {
                        if (wall4.vertical) {
                            if (wall4.y1 < this.y + 25 && wall4.y1 + wall4.length > this.y) {
                                b = false;
                            }
                        }
                        else if (wall4.y1 < this.y + 25 && wall4.y1 + 25 > this.y) {
                            b = false;
                        }
                    }
                }
            }
        }
        else if (Pacman.level == 4) {
            for (int n = 0; n < Pacman.levelCustom.size(); ++n) {
                final Wall wall5 = Pacman.levelCustom.elementAt(n);
                if (wall5.vertical) {
                    if (this.x <= wall5.x1 + 25 && this.y + 25 > wall5.y1 && this.y < wall5.y1 + wall5.length && this.x >= wall5.x1) {
                        b = false;
                    }
                }
                else if (this.x <= wall5.x1 + wall5.length && this.y + 25 > wall5.y1 && this.y < wall5.y1 + 25 && this.x >= wall5.x1) {
                    b = false;
                }
            }
            if (this.x <= 0) {
                for (int n2 = 0; n2 < Pacman.levelCustom.size(); ++n2) {
                    final Wall wall6 = Pacman.levelCustom.elementAt(n2);
                    if (wall6.x1 == 400 || wall6.x1 + wall6.length == 425) {
                        if (wall6.vertical) {
                            if (wall6.y1 < this.y + 25 && wall6.y1 + wall6.length > this.y) {
                                b = false;
                            }
                        }
                        else if (wall6.y1 < this.y + 25 && wall6.y1 + 25 > this.y) {
                            b = false;
                        }
                    }
                }
            }
        }
        return b;
    }
    
    public boolean checkDown() {
        boolean b = true;
        if (Pacman.level == 1) {
            for (int i = 0; i < Pacman.level1.size(); ++i) {
                final Wall wall = Pacman.level1.elementAt(i);
                if (!wall.vertical) {
                    if (this.y + 25 >= wall.y1 && this.x + 25 > wall.x1 && this.x < wall.x1 + wall.length && this.y <= wall.y1) {
                        b = false;
                    }
                }
                else if (this.y + 25 >= wall.y1 && this.x + 25 > wall.x1 && this.x < wall.x1 + 25 && this.y <= wall.y1) {
                    b = false;
                }
            }
        }
        else if (Pacman.level == 2) {
            for (int j = 0; j < Pacman.level2.size(); ++j) {
                final Wall wall2 = Pacman.level2.elementAt(j);
                if (!wall2.vertical) {
                    if (this.y + 25 >= wall2.y1 && this.x + 25 > wall2.x1 && this.x < wall2.x1 + wall2.length && this.y <= wall2.y1) {
                        b = false;
                    }
                }
                else if (this.y + 25 >= wall2.y1 && this.x + 25 > wall2.x1 && this.x < wall2.x1 + 25 && this.y <= wall2.y1) {
                    b = false;
                }
            }
        }
        else if (Pacman.level == 3) {
            for (int k = 0; k < Pacman.level3.size(); ++k) {
                final Wall wall3 = Pacman.level3.elementAt(k);
                if (!wall3.vertical) {
                    if (this.y + 25 >= wall3.y1 && this.x + 25 > wall3.x1 && this.x < wall3.x1 + wall3.length && this.y <= wall3.y1) {
                        b = false;
                    }
                }
                else if (this.y + 25 >= wall3.y1 && this.x + 25 > wall3.x1 && this.x < wall3.x1 + 25 && this.y <= wall3.y1) {
                    b = false;
                }
            }
            if (this.y >= 375) {
                for (int l = 0; l < Pacman.level3.size(); ++l) {
                    final Wall wall4 = Pacman.level3.elementAt(l);
                    if (wall4.y1 == 0) {
                        if (wall4.vertical) {
                            if (this.x < wall4.x1 + 25 && this.x + 25 > wall4.x1) {
                                b = false;
                            }
                        }
                        else if (this.x < wall4.x1 + wall4.length && this.x + 25 > wall4.x1) {
                            b = false;
                        }
                    }
                }
            }
        }
        else if (Pacman.level == 4) {
            for (int n = 0; n < Pacman.levelCustom.size(); ++n) {
                final Wall wall5 = Pacman.levelCustom.elementAt(n);
                if (!wall5.vertical) {
                    if (this.y + 25 >= wall5.y1 && this.x + 25 > wall5.x1 && this.x < wall5.x1 + wall5.length && this.y <= wall5.y1) {
                        b = false;
                    }
                }
                else if (this.y + 25 >= wall5.y1 && this.x + 25 > wall5.x1 && this.x < wall5.x1 + 25 && this.y <= wall5.y1) {
                    b = false;
                }
            }
            if (this.y >= 375) {
                for (int n2 = 0; n2 < Pacman.levelCustom.size(); ++n2) {
                    final Wall wall6 = Pacman.levelCustom.elementAt(n2);
                    if (wall6.y1 == 0) {
                        if (wall6.vertical) {
                            if (this.x < wall6.x1 + 25 && this.x + 25 > wall6.x1) {
                                b = false;
                            }
                        }
                        else if (this.x < wall6.x1 + wall6.length && this.x + 25 > wall6.x1) {
                            b = false;
                        }
                    }
                }
            }
        }
        return b;
    }
    
    static {
        Pacmen.itColor = new Color(255, 255, 0);
        Pacmen.itColor2 = new Color(0, 0, 255);
        Pacmen.toYellow = true;
        Pacmen.alive = true;
        Pacmen.itFont = new Font("Geneva", 0, 10);
    }
}
