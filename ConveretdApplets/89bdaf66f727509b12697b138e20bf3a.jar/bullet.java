import java.awt.Graphics;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class bullet extends Thread
{
    int x;
    int y;
    int serial;
    final int radius = 5;
    Color col;
    char dir;
    Graphics g;
    boolean stopped;
    static int delay;
    
    bullet(final int n, final int n2, final boolean b, final char dir) {
        this.stopped = false;
        this.dir = dir;
        if (!b) {
            this.col = Color.blue;
        }
        else if (b) {
            this.col = Color.yellow;
        }
        else {
            this.col = Color.red;
        }
        if (dir == 'r') {
            this.x = n + 25;
            this.y = n2 + 10;
        }
        else if (dir == 'u') {
            this.x = n + 10;
            this.y = n2 - 5;
        }
        else if (dir == 'l') {
            this.x = n - 5;
            this.y = n2 + 10;
        }
        else if (dir == 'd') {
            this.x = n + 10;
            this.y = n2 + 25;
        }
        this.g = Pacman.gfx;
    }
    
    public void run() {
        while (!this.stopped) {
            this.g.setColor(Color.black);
            this.g.fillOval(this.x, this.y, 5, 5);
            if (this.dir == 'r') {
                if (this.checkRight()) {
                    if (this.x < 425) {
                        ++this.x;
                    }
                    else {
                        this.x = 0;
                    }
                }
                else {
                    this.stopped = true;
                    Pacman.bulletVector.removeElementAt(this.serial);
                    for (int i = 0; i < Pacman.bulletVector.size(); ++i) {
                        ((bullet)Pacman.bulletVector.elementAt(i)).serial = i;
                    }
                }
            }
            else if (this.dir == 'l') {
                if (this.checkLeft()) {
                    if (this.x > -3) {
                        --this.x;
                    }
                    else {
                        this.x = 424;
                    }
                }
                else {
                    this.stopped = true;
                    Pacman.bulletVector.removeElementAt(this.serial);
                    for (int j = 0; j < Pacman.bulletVector.size(); ++j) {
                        ((bullet)Pacman.bulletVector.elementAt(j)).serial = j;
                    }
                }
            }
            else if (this.dir == 'u') {
                if (this.checkUp()) {
                    if (this.y > -3) {
                        --this.y;
                    }
                    else {
                        this.y = 395;
                    }
                }
                else {
                    this.stopped = true;
                    Pacman.bulletVector.removeElementAt(this.serial);
                    for (int k = 0; k < Pacman.bulletVector.size(); ++k) {
                        ((bullet)Pacman.bulletVector.elementAt(k)).serial = k;
                    }
                }
            }
            else if (this.dir == 'd') {
                if (this.checkDown()) {
                    if (this.y < 395) {
                        ++this.y;
                    }
                    else {
                        this.y = 0;
                    }
                }
                else {
                    this.stopped = true;
                    Pacman.bulletVector.removeElementAt(this.serial);
                    for (int l = 0; l < Pacman.bulletVector.size(); ++l) {
                        ((bullet)Pacman.bulletVector.elementAt(l)).serial = l;
                    }
                }
            }
            final char checkHit = this.checkHit();
            if (checkHit == '1') {
                this.stopped = true;
                final Pacmen p = Pacman.p;
                --p.health;
                Pacman.updateStatusBar();
                if (Pacman.p.health == 0) {
                    Pacman.endGame(true);
                }
                this.stop();
            }
            else if (checkHit == '2') {
                this.stopped = true;
                final Pacmen p2 = Pacman.p2;
                --p2.health;
                Pacman.updateStatusBar();
                if (Pacman.p2.health == 0) {
                    Pacman.endGame(false);
                }
                this.stop();
            }
            else {
                this.g.setColor(this.col);
                this.g.fillOval(this.x, this.y, 5, 5);
            }
            this.checkMine();
            try {
                Thread.sleep(bullet.delay);
            }
            catch (Exception ex) {}
            if (this.stopped) {
                this.g.setColor(Color.black);
                this.g.fillOval(this.x, this.y, 5, 5);
            }
        }
    }
    
    public char checkHit() {
        char c = 'n';
        if (this.x + 5 > Pacman.p.x && this.x < Pacman.p.x + 25 && this.y + 5 > Pacman.p.y && this.y < Pacman.p.y + 25 && !this.stopped) {
            c = '1';
            this.stopped = true;
            Pacman.bulletVector.removeElementAt(this.serial);
            for (int i = 0; i < Pacman.bulletVector.size(); ++i) {
                ((bullet)Pacman.bulletVector.elementAt(i)).serial = i;
            }
        }
        else if (this.x + 5 > Pacman.p2.x && this.x < Pacman.p2.x + 25 && this.y + 5 > Pacman.p2.y && this.y < Pacman.p2.y + 25 && !this.stopped) {
            c = '2';
            this.stopped = true;
            Pacman.bulletVector.removeElementAt(this.serial);
            for (int j = 0; j < Pacman.bulletVector.size(); ++j) {
                ((bullet)Pacman.bulletVector.elementAt(j)).serial = j;
            }
        }
        return c;
    }
    
    public boolean checkRight() {
        boolean b = true;
        if (Pacman.level == 1) {
            for (int i = 0; i < Pacman.level1.size(); ++i) {
                final Wall wall = Pacman.level1.elementAt(i);
                if (wall.vertical) {
                    if (this.x + 5 >= wall.x1 && this.y + 10 > wall.y1 && this.y < wall.y1 + wall.length && this.x <= wall.x1) {
                        b = false;
                    }
                }
                else if (this.x + 5 >= wall.x1 && this.y + 10 > wall.y1 && this.y < wall.y1 + 25 && this.x <= wall.x1) {
                    b = false;
                }
            }
        }
        else if (Pacman.level == 2) {
            for (int j = 0; j < Pacman.level2.size(); ++j) {
                final Wall wall2 = Pacman.level2.elementAt(j);
                if (wall2.vertical) {
                    if (this.x + 5 >= wall2.x1 && this.y + 10 > wall2.y1 && this.y < wall2.y1 + wall2.length && this.x <= wall2.x1) {
                        b = false;
                    }
                }
                else if (this.x + 5 >= wall2.x1 && this.y + 10 > wall2.y1 && this.y < wall2.y1 + 25 && this.x <= wall2.x1) {
                    b = false;
                }
            }
        }
        else if (Pacman.level == 3) {
            for (int k = 0; k < Pacman.level3.size(); ++k) {
                final Wall wall3 = Pacman.level3.elementAt(k);
                if (wall3.vertical) {
                    if (this.x + 5 >= wall3.x1 && this.y + 10 > wall3.y1 && this.y < wall3.y1 + wall3.length && this.x <= wall3.x1) {
                        b = false;
                    }
                }
                else if (this.x + 5 >= wall3.x1 && this.y + 10 > wall3.y1 && this.y < wall3.y1 + 25 && this.x <= wall3.x1) {
                    b = false;
                }
            }
            if (this.x >= 400) {
                for (int l = 0; l < Pacman.level3.size(); ++l) {
                    final Wall wall4 = Pacman.level3.elementAt(l);
                    if (wall4.x1 == 0) {
                        if (wall4.vertical) {
                            if (wall4.y1 < this.y + 10 && wall4.y1 + wall4.length > this.y) {
                                b = false;
                            }
                        }
                        else if (wall4.y1 < this.y + 10 && wall4.y1 + 25 > this.y) {
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
                    if (this.x + 5 >= wall5.x1 && this.y + 10 > wall5.y1 && this.y < wall5.y1 + wall5.length && this.x <= wall5.x1) {
                        b = false;
                    }
                }
                else if (this.x + 5 >= wall5.x1 && this.y + 10 > wall5.y1 && this.y < wall5.y1 + 25 && this.x <= wall5.x1) {
                    b = false;
                }
            }
            if (this.x >= 400) {
                for (int n2 = 0; n2 < Pacman.levelCustom.size(); ++n2) {
                    final Wall wall6 = Pacman.levelCustom.elementAt(n2);
                    if (wall6.x1 == 0) {
                        if (wall6.vertical) {
                            if (wall6.y1 < this.y + 10 && wall6.y1 + wall6.length > this.y) {
                                b = false;
                            }
                        }
                        else if (wall6.y1 < this.y + 10 && wall6.y1 + 25 > this.y) {
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
                    if (this.y <= wall.y1 + 25 && this.x + 10 > wall.x1 && this.x < wall.x1 + wall.length && this.y >= wall.y1) {
                        b = false;
                    }
                }
                else if (this.y <= wall.y1 + wall.length && this.x + 10 > wall.x1 && this.x < wall.x1 + 25 && this.y >= wall.y1) {
                    b = false;
                }
            }
        }
        else if (Pacman.level == 2) {
            for (int j = 0; j < Pacman.level2.size(); ++j) {
                final Wall wall2 = Pacman.level2.elementAt(j);
                if (!wall2.vertical) {
                    if (this.y <= wall2.y1 + 25 && this.x + 10 > wall2.x1 && this.x < wall2.x1 + wall2.length && this.y >= wall2.y1) {
                        b = false;
                    }
                }
                else if (this.y <= wall2.y1 + wall2.length && this.x + 10 > wall2.x1 && this.x < wall2.x1 + 25 && this.y >= wall2.y1) {
                    b = false;
                }
            }
        }
        else if (Pacman.level == 3) {
            for (int k = 0; k < Pacman.level3.size(); ++k) {
                final Wall wall3 = Pacman.level3.elementAt(k);
                if (!wall3.vertical) {
                    if (this.y <= wall3.y1 + 25 && this.x + 10 > wall3.x1 && this.x < wall3.x1 + wall3.length && this.y >= wall3.y1) {
                        b = false;
                    }
                }
                else if (this.y <= wall3.y1 + wall3.length && this.x + 10 > wall3.x1 && this.x < wall3.x1 + 25 && this.y >= wall3.y1) {
                    b = false;
                }
            }
            if (this.y <= 0) {
                for (int l = 0; l < Pacman.level3.size(); ++l) {
                    final Wall wall4 = Pacman.level3.elementAt(l);
                    if (wall4.y1 == 375 || (wall4.y1 + wall4.length == 400 && wall4.vertical)) {
                        if (wall4.vertical) {
                            if (this.x < wall4.x1 + 25 && this.x + 10 > wall4.x1) {
                                b = false;
                            }
                        }
                        else if (this.x < wall4.x1 + wall4.length && this.x + 10 > wall4.x1) {
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
                    if (this.y <= wall5.y1 + 25 && this.x + 10 > wall5.x1 && this.x < wall5.x1 + wall5.length && this.y >= wall5.y1) {
                        b = false;
                    }
                }
                else if (this.y <= wall5.y1 + wall5.length && this.x + 10 > wall5.x1 && this.x < wall5.x1 + 25 && this.y >= wall5.y1) {
                    b = false;
                }
            }
            if (this.y <= 0) {
                for (int n2 = 0; n2 < Pacman.levelCustom.size(); ++n2) {
                    final Wall wall6 = Pacman.levelCustom.elementAt(n2);
                    if (wall6.y1 == 375) {
                        if (wall6.vertical) {
                            if (this.x < wall6.x1 + 25 && this.x + 10 > wall6.x1) {
                                b = false;
                            }
                        }
                        else if (this.x < wall6.x1 + wall6.length && this.x + 10 > wall6.x1) {
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
                    if (this.x <= wall.x1 + 25 && this.y + 10 > wall.y1 && this.y < wall.y1 + wall.length && this.x >= wall.x1) {
                        b = false;
                    }
                }
                else if (this.x <= wall.x1 + wall.length && this.y + 10 > wall.y1 && this.y < wall.y1 + 25 && this.x >= wall.x1) {
                    b = false;
                }
            }
        }
        else if (Pacman.level == 2) {
            for (int j = 0; j < Pacman.level2.size(); ++j) {
                final Wall wall2 = Pacman.level2.elementAt(j);
                if (wall2.vertical) {
                    if (this.x <= wall2.x1 + 25 && this.y + 10 > wall2.y1 && this.y < wall2.y1 + wall2.length && this.x >= wall2.x1) {
                        b = false;
                    }
                }
                else if (this.x <= wall2.x1 + wall2.length && this.y + 10 > wall2.y1 && this.y < wall2.y1 + 25 && this.x >= wall2.x1) {
                    b = false;
                }
            }
        }
        else if (Pacman.level == 3) {
            for (int k = 0; k < Pacman.level3.size(); ++k) {
                final Wall wall3 = Pacman.level3.elementAt(k);
                if (wall3.vertical) {
                    if (this.x <= wall3.x1 + 25 && this.y + 10 > wall3.y1 && this.y < wall3.y1 + wall3.length && this.x >= wall3.x1) {
                        b = false;
                    }
                }
                else if (this.x <= wall3.x1 + wall3.length && this.y + 10 > wall3.y1 && this.y < wall3.y1 + 25 && this.x >= wall3.x1) {
                    b = false;
                }
            }
            if (this.x <= 0) {
                for (int l = 0; l < Pacman.level3.size(); ++l) {
                    final Wall wall4 = Pacman.level3.elementAt(l);
                    if (wall4.x1 == 400 || (wall4.x1 + wall4.length == 425 && !wall4.vertical)) {
                        if (wall4.vertical) {
                            if (wall4.y1 < this.y + 10 && wall4.y1 + wall4.length > this.y) {
                                b = false;
                            }
                        }
                        else if (wall4.y1 < this.y + 10 && wall4.y1 + 25 > this.y) {
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
                    if (this.x <= wall5.x1 + 25 && this.y + 10 > wall5.y1 && this.y < wall5.y1 + wall5.length && this.x >= wall5.x1) {
                        b = false;
                    }
                }
                else if (this.x <= wall5.x1 + wall5.length && this.y + 10 > wall5.y1 && this.y < wall5.y1 + 25 && this.x >= wall5.x1) {
                    b = false;
                }
            }
            if (this.x <= 0) {
                for (int n2 = 0; n2 < Pacman.levelCustom.size(); ++n2) {
                    final Wall wall6 = Pacman.levelCustom.elementAt(n2);
                    if (wall6.x1 == 400 || wall6.x1 + wall6.length == 425) {
                        if (wall6.vertical) {
                            if (wall6.y1 < this.y + 10 && wall6.y1 + wall6.length > this.y) {
                                b = false;
                            }
                        }
                        else if (wall6.y1 < this.y + 10 && wall6.y1 + 25 > this.y) {
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
                    if (this.y + 5 >= wall.y1 && this.x + 10 > wall.x1 && this.x < wall.x1 + wall.length && this.y <= wall.y1) {
                        b = false;
                    }
                }
                else if (this.y + 5 >= wall.y1 && this.x + 10 > wall.x1 && this.x < wall.x1 + 25 && this.y <= wall.y1) {
                    b = false;
                }
            }
        }
        else if (Pacman.level == 2) {
            for (int j = 0; j < Pacman.level2.size(); ++j) {
                final Wall wall2 = Pacman.level2.elementAt(j);
                if (!wall2.vertical) {
                    if (this.y + 5 >= wall2.y1 && this.x + 10 > wall2.x1 && this.x < wall2.x1 + wall2.length && this.y <= wall2.y1) {
                        b = false;
                    }
                }
                else if (this.y + 5 >= wall2.y1 && this.x + 10 > wall2.x1 && this.x < wall2.x1 + 25 && this.y <= wall2.y1) {
                    b = false;
                }
            }
        }
        else if (Pacman.level == 3) {
            for (int k = 0; k < Pacman.level3.size(); ++k) {
                final Wall wall3 = Pacman.level3.elementAt(k);
                if (!wall3.vertical) {
                    if (this.y + 5 >= wall3.y1 && this.x + 10 > wall3.x1 && this.x < wall3.x1 + wall3.length && this.y <= wall3.y1) {
                        b = false;
                    }
                }
                else if (this.y + 5 >= wall3.y1 && this.x + 10 > wall3.x1 && this.x < wall3.x1 + 25 && this.y <= wall3.y1) {
                    b = false;
                }
            }
            if (this.y >= 375) {
                for (int l = 0; l < Pacman.level3.size(); ++l) {
                    final Wall wall4 = Pacman.level3.elementAt(l);
                    if (wall4.y1 == 0) {
                        if (wall4.vertical) {
                            if (this.x < wall4.x1 + 25 && this.x + 10 > wall4.x1) {
                                b = false;
                            }
                        }
                        else if (this.x < wall4.x1 + wall4.length && this.x + 10 > wall4.x1) {
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
                    if (this.y + 5 >= wall5.y1 && this.x + 10 > wall5.x1 && this.x < wall5.x1 + wall5.length && this.y <= wall5.y1) {
                        b = false;
                    }
                }
                else if (this.y + 5 >= wall5.y1 && this.x + 10 > wall5.x1 && this.x < wall5.x1 + 25 && this.y <= wall5.y1) {
                    b = false;
                }
            }
            if (this.y >= 375) {
                for (int n2 = 0; n2 < Pacman.levelCustom.size(); ++n2) {
                    final Wall wall6 = Pacman.levelCustom.elementAt(n2);
                    if (wall6.y1 == 0) {
                        if (wall6.vertical) {
                            if (this.x < wall6.x1 + 25 && this.x + 10 > wall6.x1) {
                                b = false;
                            }
                        }
                        else if (this.x < wall6.x1 + wall6.length && this.x + 10 > wall6.x1) {
                            b = false;
                        }
                    }
                }
            }
        }
        return b;
    }
    
    public void checkMine() {
        for (int i = 0; i < Pacman.mineVector.size(); ++i) {
            final mine mine = Pacman.mineVector.elementAt(i);
            if (mine.x + 25 > this.x && mine.x < this.x + 5 && mine.y + 25 > this.y && mine.y < this.y + 5 && mine.alive) {
                this.g.setColor(Color.black);
                this.g.fillOval(this.x, this.y, 5, 5);
                this.stopped = true;
                mine.explode(this.dir, true);
            }
        }
    }
    
    static {
        bullet.delay = 3;
    }
}
