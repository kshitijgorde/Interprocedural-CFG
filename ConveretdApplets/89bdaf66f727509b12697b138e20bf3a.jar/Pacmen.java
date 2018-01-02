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
    Food f;
    int x;
    int y;
    int stage;
    boolean closing;
    static boolean toYellow;
    static boolean toGreen;
    static final short delay = 6;
    static boolean alive;
    static Font itFont;
    boolean waitingUp;
    boolean waitingDown;
    boolean waitingRight;
    boolean waitingLeft;
    int ammo;
    int mines;
    int health;
    static final int ammoDamage = 1;
    static final int mineDamage = 5;
    static final int pacWidth = 25;
    static final int pacHeight = 25;
    
    Pacmen(final int x, final int y, final boolean p3) {
        this.dir = 'r';
        this.stage = 44;
        this.closing = true;
        this.waitingUp = false;
        this.waitingDown = false;
        this.waitingRight = false;
        this.waitingLeft = false;
        this.ammo = 25;
        this.mines = 5;
        this.health = 30;
        this.x = x;
        this.y = y;
        this.p = p3;
    }
    
    public void run() {
        while (Pacmen.alive && !Pacman.ended) {
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
            for (int i = 0; i < Pacman.foodArray[Pacman.level - 1].size(); ++i) {
                this.f = (Food)Pacman.foodArray[Pacman.level - 1].elementAt(i);
                if (this.x + 25 > this.f.x && this.y < this.f.y + 5 && this.y + 25 > this.f.y && this.x < this.f.x + 5 && !this.f.eaten) {
                    this.f.eat(this.p);
                    this.f.eaten = true;
                    Pacman.updateStatusBar();
                }
            }
            if (this.dir == 'r') {
                for (int j = 0; j < 2; ++j) {
                    Pacmen.g.setColor(Color.black);
                    Pacmen.g.fillRect(this.x, this.y, 25, 25);
                    this.draw();
                    if (j < 1) {
                        try {
                            Thread.sleep(6L);
                        }
                        catch (Exception ex) {}
                    }
                }
                try {
                    Thread.sleep(6L);
                }
                catch (Exception ex2) {}
                Pacmen.g.setColor(Color.black);
                Pacmen.g.fillRect(this.x, this.y, 25, 25);
                if (this.checkRight()) {
                    ++this.x;
                    this.checkMineProxy();
                    this.checkMineHit();
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
                }
            }
            if (this.dir == 'u') {
                for (int k = 0; k < 2; ++k) {
                    Pacmen.g.setColor(Color.black);
                    Pacmen.g.fillRect(this.x, this.y, 25, 25);
                    this.draw();
                    if (k < 1) {
                        try {
                            Thread.sleep(6L);
                        }
                        catch (Exception ex3) {}
                    }
                }
                try {
                    Thread.sleep(6L);
                }
                catch (Exception ex4) {}
                Pacmen.g.setColor(Color.black);
                Pacmen.g.fillRect(this.x, this.y, 25, 25);
                if (this.checkUp()) {
                    --this.y;
                    this.checkMineProxy();
                    this.checkMineHit();
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
                }
            }
            if (this.dir == 'l') {
                for (int l = 0; l < 2; ++l) {
                    Pacmen.g.setColor(Color.black);
                    Pacmen.g.fillRect(this.x, this.y, 25, 25);
                    this.draw();
                    if (l < 1) {
                        try {
                            Thread.sleep(6L);
                        }
                        catch (Exception ex5) {}
                    }
                }
                try {
                    Thread.sleep(6L);
                }
                catch (Exception ex6) {}
                Pacmen.g.setColor(Color.black);
                Pacmen.g.fillRect(this.x, this.y, 25, 25);
                if (this.checkLeft()) {
                    --this.x;
                    this.checkMineProxy();
                    this.checkMineHit();
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
                }
            }
            if (this.dir == 'd') {
                for (int n = 0; n < 2; ++n) {
                    Pacmen.g.setColor(Color.black);
                    Pacmen.g.fillRect(this.x, this.y, 25, 25);
                    this.draw();
                    if (n < 1) {
                        try {
                            Thread.sleep(6L);
                        }
                        catch (Exception ex7) {}
                    }
                }
                try {
                    Thread.sleep(6L);
                }
                catch (Exception ex8) {}
                Pacmen.g.setColor(Color.black);
                Pacmen.g.fillRect(this.x, this.y, 25, 25);
                if (!this.checkDown()) {
                    continue;
                }
                if (this.y >= 375) {
                    Pacmen.g.setColor(Color.black);
                    Pacmen.g.fillRect(this.x, 375, 25, 25);
                    this.y = -25;
                }
                ++this.y;
                this.checkMineProxy();
                this.checkMineHit();
                if (this.waitingRight && this.checkRight()) {
                    this.dir = 'r';
                }
                if (this.waitingLeft && this.checkLeft()) {
                    this.dir = 'l';
                }
                if (!this.waitingUp || !this.checkUp()) {
                    continue;
                }
                this.dir = 'u';
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
    
    public void checkMineProxy() {
        for (int i = 0; i < Pacman.mineVector.size(); ++i) {
            final mine mine = Pacman.mineVector.elementAt(i);
            if (mine.x + 30 > this.x && mine.x < this.x + 30 && mine.y + 30 > this.y && mine.y < this.y + 30) {
                mine.draw();
            }
        }
    }
    
    public void checkMineHit() {
        for (int i = 0; i < Pacman.mineVector.size(); ++i) {
            final mine mine = Pacman.mineVector.elementAt(i);
            if (mine.x + 25 > this.x && mine.x < this.x + 25 && mine.y + 25 > this.y && mine.y < this.y + 25) {
                this.health -= 5;
                mine.explode(this.dir, false);
                if (this.dir == 'r') {
                    for (int j = 0; j < 36; ++j) {
                        try {
                            Thread.sleep(2L);
                        }
                        catch (Exception ex) {
                            System.out.println("HELLLOOOEOEOOEOEOEE");
                        }
                        Pacmen.g.setColor(Color.black);
                        Pacmen.g.fillRect(this.x, this.y, 25, 25);
                        if (this.checkLeft()) {
                            if (this.x > -25) {
                                --this.x;
                            }
                            else {
                                this.x = 424;
                            }
                        }
                        this.draw();
                    }
                }
                else if (this.dir == 'l') {
                    for (int k = 0; k < 36; ++k) {
                        try {
                            Thread.sleep(2L);
                        }
                        catch (Exception ex2) {}
                        Pacmen.g.setColor(Color.black);
                        Pacmen.g.fillRect(this.x, this.y, 25, 25);
                        if (this.checkRight()) {
                            if (this.x < 425) {
                                ++this.x;
                            }
                            else {
                                this.x = -24;
                            }
                        }
                        this.draw();
                    }
                }
                else if (this.dir == 'u') {
                    for (int l = 0; l < 36; ++l) {
                        try {
                            Thread.sleep(2L);
                        }
                        catch (Exception ex3) {
                            System.out.println("HELLLOOOEOEOOEOEOEE");
                        }
                        Pacmen.g.setColor(Color.black);
                        Pacmen.g.fillRect(this.x, this.y, 25, 25);
                        if (this.checkDown()) {
                            if (this.y < 375) {
                                ++this.y;
                            }
                            else {
                                this.y = -24;
                            }
                        }
                        this.draw();
                    }
                }
                else if (this.dir == 'd') {
                    for (int n = 0; n < 36; ++n) {
                        try {
                            Thread.sleep(2L);
                        }
                        catch (Exception ex4) {}
                        Pacmen.g.setColor(Color.black);
                        Pacmen.g.fillRect(this.x, this.y, 25, 25);
                        if (this.checkUp()) {
                            if (this.y > -25) {
                                --this.y;
                            }
                            else {
                                this.y = 375;
                            }
                        }
                        this.draw();
                    }
                }
                if (this.health <= 0) {
                    this.health = 0;
                }
                Pacman.updateStatusBar();
                if (this.health == 0) {
                    Pacman.endGame(this.p);
                }
            }
        }
    }
    
    public synchronized void shoot() {
        final bullet bullet = new bullet(this.x, this.y, this.p, this.dir);
        if ((bullet.dir == 'r' && bullet.checkRight()) || (bullet.dir == 'l' && bullet.checkLeft()) || (bullet.dir == 'u' && bullet.checkUp()) || (bullet.dir == 'd' && bullet.checkDown())) {
            bullet.start();
            bullet.serial = Pacman.bulletVector.size();
            Pacman.bulletVector.addElement(bullet);
            --this.ammo;
            Pacman.updateStatusBar();
            Pacman.playSound('g');
        }
    }
    
    public void mine() {
        if (this.checkMine()) {
            final mine mine = new mine(this.x, this.y, this.p, this.dir);
            --this.mines;
            mine.serial = Pacman.mineVector.size();
            Pacman.mineVector.addElement(mine);
        }
    }
    
    public boolean checkMine() {
        boolean b = true;
        if (Pacman.level == 1) {
            for (int i = 0; i < Pacman.level1.size(); ++i) {
                final Wall wall = Pacman.level1.elementAt(i);
                if (this.dir == 'd') {
                    if (!wall.vertical) {
                        if (wall.y1 < this.y && this.y < wall.y1 + 50 && this.x + 25 > wall.x1 && this.x < wall.x1 + wall.length) {
                            b = false;
                        }
                    }
                    else if (wall.y1 < this.y && this.y < wall.y1 + wall.length + 25 && this.x + 25 > wall.x1 && this.x < wall.x1 + 25) {
                        b = false;
                    }
                }
                if (this.dir == 'u') {
                    if (this.y > 350) {
                        b = false;
                    }
                    if (!wall.vertical) {
                        if (wall.y1 - 25 < this.y && this.y + 25 < wall.y1 + 25 && this.x + 25 > wall.x1 && this.x < wall.x1 + wall.length) {
                            b = false;
                        }
                    }
                    else if (wall.y1 - 25 < this.y + 25 && this.y + 25 < wall.y1 + wall.length && this.x + 25 > wall.x1 && this.x < wall.x1 + 25) {
                        b = false;
                    }
                }
                if (this.dir == 'l') {
                    if (!wall.vertical) {
                        if (wall.x1 - 25 < this.x + 25 && this.x + 25 < wall.x1 + wall.length && this.y + 25 > wall.y1 && this.y < wall.y1 + 25) {
                            b = false;
                        }
                    }
                    else if (wall.x1 - 25 < this.x + 25 && this.x + 25 < wall.x1 + 25 && this.y + 25 > wall.y1 && this.y < wall.y1 + wall.length) {
                        b = false;
                    }
                }
                if (this.dir == 'r') {
                    if (!wall.vertical) {
                        if (wall.x1 < this.x && this.x < wall.x1 + wall.length + 25 && this.y + 25 > wall.y1 && this.y < wall.y1 + 25) {
                            b = false;
                        }
                    }
                    else if (wall.x1 < this.x && this.x < wall.x1 + 50 && this.y + 25 > wall.y1 && this.y < wall.y1 + wall.length) {
                        b = false;
                    }
                }
            }
        }
        else if (Pacman.level == 2) {
            for (int j = 0; j < Pacman.level2.size(); ++j) {
                final Wall wall2 = Pacman.level2.elementAt(j);
                if (this.dir == 'd') {
                    if (!wall2.vertical) {
                        if (wall2.y1 < this.y && this.y < wall2.y1 + 50 && this.x + 25 > wall2.x1 && this.x < wall2.x1 + wall2.length) {
                            b = false;
                        }
                    }
                    else if (wall2.y1 < this.y && this.y < wall2.y1 + wall2.length + 25 && this.x + 25 > wall2.x1 && this.x < wall2.x1 + 25) {
                        b = false;
                    }
                }
                if (this.dir == 'u') {
                    if (this.y > 350) {
                        b = false;
                    }
                    if (!wall2.vertical) {
                        if (wall2.y1 - 25 < this.y + 25 && this.y + 25 < wall2.y1 + 25 && this.x + 25 > wall2.x1 && this.x < wall2.x1 + wall2.length) {
                            b = false;
                        }
                    }
                    else if (wall2.y1 - 25 < this.y + 25 && this.y + 25 < wall2.y1 + wall2.length && this.x + 25 > wall2.x1 && this.x < wall2.x1 + 25) {
                        b = false;
                    }
                }
                if (this.dir == 'l') {
                    if (!wall2.vertical) {
                        if (wall2.x1 - 25 < this.x + 25 && this.x + 25 < wall2.x1 + wall2.length && this.y + 25 > wall2.y1 && this.y < wall2.y1 + 25) {
                            b = false;
                        }
                    }
                    else if (wall2.x1 - 25 < this.x + 25 && this.x + 25 < wall2.x1 + 25 && this.y + 25 > wall2.y1 && this.y < wall2.y1 + wall2.length) {
                        b = false;
                    }
                }
                if (this.dir == 'r') {
                    if (!wall2.vertical) {
                        if (wall2.x1 < this.x && this.x < wall2.x1 + wall2.length + 25 && this.y + 25 > wall2.y1 && this.y < wall2.y1 + 25) {
                            b = false;
                        }
                    }
                    else if (wall2.x1 < this.x && this.x < wall2.x1 + 50 && this.y + 25 > wall2.y1 && this.y < wall2.y1 + wall2.length) {
                        b = false;
                    }
                }
            }
        }
        else if (Pacman.level == 3) {
            for (int k = 0; k < Pacman.level3.size(); ++k) {
                final Wall wall3 = Pacman.level3.elementAt(k);
                if (this.dir == 'd') {
                    if (!wall3.vertical) {
                        if (wall3.y1 < this.y && this.y < wall3.y1 + 50 && this.x + 25 > wall3.x1 && this.x < wall3.x1 + wall3.length) {
                            b = false;
                        }
                    }
                    else if (wall3.y1 < this.y && this.y < wall3.y1 + wall3.length + 25 && this.x + 25 > wall3.x1 && this.x < wall3.x1 + 25) {
                        b = false;
                    }
                }
                if (this.dir == 'u') {
                    if (this.y > 350) {
                        b = false;
                    }
                    if (!wall3.vertical) {
                        if (wall3.y1 - 25 < this.y && this.y + 25 < wall3.y1 + 25 && this.x + 25 > wall3.x1 && this.x < wall3.x1 + wall3.length) {
                            b = false;
                        }
                    }
                    else if (wall3.y1 - 25 < this.y + 25 && this.y + 25 < wall3.y1 + wall3.length && this.x + 25 > wall3.x1 && this.x < wall3.x1 + 25) {
                        b = false;
                    }
                }
                if (this.dir == 'l') {
                    if (!wall3.vertical) {
                        if (wall3.x1 - 25 < this.x + 25 && this.x + 25 < wall3.x1 + wall3.length && this.y + 25 > wall3.y1 && this.y < wall3.y1 + 25) {
                            b = false;
                        }
                    }
                    else if (wall3.x1 - 25 < this.x + 25 && this.x + 25 < wall3.x1 + 25 && this.y + 25 > wall3.y1 && this.y < wall3.y1 + wall3.length) {
                        b = false;
                    }
                }
                if (this.dir == 'r') {
                    if (!wall3.vertical) {
                        if (wall3.x1 < this.x && this.x < wall3.x1 + wall3.length + 25 && this.y + 25 > wall3.y1 && this.y < wall3.y1 + 25) {
                            b = false;
                        }
                    }
                    else if (wall3.x1 < this.x && this.x < wall3.x1 + 50 && this.y + 25 > wall3.y1 && this.y < wall3.y1 + wall3.length) {
                        b = false;
                    }
                }
            }
        }
        else if (Pacman.level == 4) {
            for (int l = 0; l < Pacman.levelCustom.size(); ++l) {
                final Wall wall4 = Pacman.levelCustom.elementAt(l);
                if (this.dir == 'd') {
                    if (!wall4.vertical) {
                        if (wall4.y1 < this.y && this.y < wall4.y1 + 50 && this.x + 25 > wall4.x1 && this.x < wall4.x1 + wall4.length) {
                            b = false;
                        }
                    }
                    else if (wall4.y1 < this.y && this.y < wall4.y1 + wall4.length + 25 && this.x + 25 > wall4.x1 && this.x < wall4.x1 + 25) {
                        b = false;
                    }
                }
                if (this.dir == 'u') {
                    if (this.y > 350) {
                        b = false;
                    }
                    if (!wall4.vertical) {
                        if (wall4.y1 - 25 < this.y && this.y + 25 < wall4.y1 + 25 && this.x + 25 > wall4.x1 && this.x < wall4.x1 + wall4.length) {
                            b = false;
                        }
                    }
                    else if (wall4.y1 - 25 < this.y + 25 && this.y + 25 < wall4.y1 + wall4.length && this.x + 25 > wall4.x1 && this.x < wall4.x1 + 25) {
                        b = false;
                    }
                }
                if (this.dir == 'l') {
                    if (!wall4.vertical) {
                        if (wall4.x1 - 25 < this.x + 25 && this.x + 25 < wall4.x1 + wall4.length && this.y + 25 > wall4.y1 && this.y < wall4.y1 + 25) {
                            b = false;
                        }
                    }
                    else if (wall4.x1 - 25 < this.x + 25 && this.x + 25 < wall4.x1 + 25 && this.y + 25 > wall4.y1 && this.y < wall4.y1 + wall4.length) {
                        b = false;
                    }
                }
                if (this.dir == 'r') {
                    if (!wall4.vertical) {
                        if (wall4.x1 < this.x && this.x < wall4.x1 + wall4.length + 25 && this.y + 25 > wall4.y1 && this.y < wall4.y1 + 25) {
                            b = false;
                        }
                    }
                    else if (wall4.x1 < this.x && this.x < wall4.x1 + 50 && this.y + 25 > wall4.y1 && this.y < wall4.y1 + wall4.length) {
                        b = false;
                    }
                }
            }
        }
        return b;
    }
    
    public void draw() {
        if (this.dir == 'd') {
            if (this.health < 5) {
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
            return;
        }
        if (this.dir == 'u') {
            if (this.health < 5) {
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
            return;
        }
        if (this.dir == 'l') {
            if (this.health < 5) {
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
            return;
        }
        if (this.dir == 'r') {
            if (this.health < 5) {
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
        }
    }
    
    static {
        Pacmen.itColor = new Color(255, 255, 0);
        Pacmen.itColor2 = new Color(0, 0, 255);
        Pacmen.toYellow = true;
        Pacmen.alive = true;
        Pacmen.itFont = new Font("Geneva", 0, 10);
    }
}
