import java.awt.Color;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

final class Data
{
    protected Invaders inv;
    Player player;
    private Image player1G;
    private Image player2G;
    private Image alien1aG;
    private Image alien1bG;
    private Image explode1G;
    private Image explode2G;
    private Image bulletG;
    private Image bullet2G;
    private Image alien4aG;
    private Image alien4bG;
    private Image bonus1G;
    private Image bonus2G;
    private Image alien2aG;
    private Image alien2bG;
    private Image alien3aG;
    private Image alien3bG;
    Image bt1a;
    Image bt1b;
    Image bt1c;
    Image bt2a;
    Image bt2b;
    Image bt2c;
    Image bt3a;
    Image bt3b;
    Image bt3c;
    Image real1;
    Image real2;
    Image real3;
    Image bgimage;
    Image logoG;
    private int score;
    private int level;
    private int ships;
    private int bullets;
    Color bgColor;
    Color fgColor;
    Color[] bgCol;
    Enemy[] enemies;
    Bullet[] playerFire;
    Bullet[] enemyFire;
    int idOld;
    boolean firstRun;
    int limitBlock;
    int numberOfEnemies;
    int enemyKilled;
    int numberOfEnemyFire;
    int speed;
    int randomMod;
    boolean waiting;
    String[] mesg;
    private int bonusTime;
    Bonus bonus;
    int bonusCount;
    int mode;
    boolean superBullets;
    boolean bossMode;
    int bossHits;
    
    public Data(final Invaders inv) {
        this.bgCol = new Color[5];
        this.playerFire = new Bullet[8];
        this.firstRun = false;
        this.limitBlock = 30;
        this.mesg = new String[20];
        this.bonus = new Bonus(this);
        this.inv = inv;
        this.bgColor = new Color(0, 0, 0);
        this.fgColor = new Color(255, 255, 255);
        this.score = 0;
    }
    
    public void addToBullets(final int n) {
        if (n == 0) {
            this.bullets = 4;
        }
        else {
            this.bullets += n;
        }
    }
    
    public void addToBullets(final int n, final int n2) {
        if (n2 < 4 && !this.playerFire[n2 + 4].isAlive()) {
            this.addToBullets(1);
        }
        else if (n2 > 3 && !this.playerFire[n2 - 4].isAlive()) {
            this.addToBullets(1);
        }
    }
    
    public void addToLevel(final int n) {
        if (n == 0) {
            this.level = 1;
        }
        else {
            this.level += n;
        }
    }
    
    public void addToLimit() {
        this.limitBlock += 2;
    }
    
    public void addToScore(final int n) {
        this.score += n;
        this.inv.info.refresh();
    }
    
    public void addToShips(final int n) {
        if (n == 0) {
            this.ships = 4;
        }
        else {
            this.ships += n;
        }
    }
    
    public void bonusKilled() {
        this.addToScore(100);
        if (this.mode < 3) {
            ++this.mode;
        }
        else {
            this.superBullets = true;
            this.addToShips(this.mode = 1);
            this.inv.info.refresh();
        }
    }
    
    public void bulletsUp() {
        this.randomMod -= 5;
    }
    
    public void enemyKilled() {
        this.addToScore(5);
        ++this.enemyKilled;
        if (this.enemyKilled == 8 && this.getLevel() % 3 == 1) {
            this.inv.endS.play();
        }
        if (this.enemyKilled == this.getEnemyNumber()) {
            this.waitMode(true);
            this.inv.field.stopActions();
        }
    }
    
    public void gameOver() {
        this.inv.gameOver();
    }
    
    public void generateBonus() {
        ++this.bonusCount;
        if (this.bonusCount == this.bonusTime) {
            this.bonus.reset();
            this.bonus.enable();
        }
    }
    
    public int getBulletsLeft() {
        return this.bullets;
    }
    
    public int getEnemyFire() {
        return this.numberOfEnemyFire;
    }
    
    public int getEnemyNumber() {
        return this.numberOfEnemies;
    }
    
    public Image getGraphic(final String s) {
        if (s.equals("player1G")) {
            return this.player1G;
        }
        if (s.equals("player2G")) {
            return this.player2G;
        }
        if (s.equals("alien1aG")) {
            return this.alien1aG;
        }
        if (s.equals("alien1bG")) {
            return this.alien1bG;
        }
        if (s.equals("alien2aG")) {
            return this.alien2aG;
        }
        if (s.equals("alien2bG")) {
            return this.alien2bG;
        }
        if (s.equals("alien3aG")) {
            return this.alien3aG;
        }
        if (s.equals("alien3bG")) {
            return this.alien3bG;
        }
        if (s.equals("alien4aG")) {
            return this.alien4aG;
        }
        if (s.equals("alien4bG")) {
            return this.alien4bG;
        }
        if (s.equals("bullet2G")) {
            return this.bullet2G;
        }
        if (s.equals("bulletG")) {
            return this.bulletG;
        }
        if (s.equals("explode1G")) {
            return this.explode1G;
        }
        if (s.equals("explode2G")) {
            return this.explode2G;
        }
        if (s.equals("bonus1G")) {
            return this.bonus1G;
        }
        if (s.equals("bonus2G")) {
            return this.bonus2G;
        }
        if (s.equals("logoG")) {
            return this.logoG;
        }
        return null;
    }
    
    public int getLevel() {
        return this.level;
    }
    
    public int getLimitBlock() {
        return this.limitBlock;
    }
    
    public String getMesg(final int n) {
        if (n < 20) {
            return this.mesg[n];
        }
        return "No Comments, Sir.";
    }
    
    public int getRandomMod() {
        return this.randomMod;
    }
    
    public int getScore() {
        return this.score;
    }
    
    public int getShipsLeft() {
        return this.ships;
    }
    
    public int getSpeed() {
        return this.speed;
    }
    
    public boolean inOrder() {
        return this.firstRun;
    }
    
    public void initData() {
        this.superBullets = false;
        this.mode = 1;
        this.bossMode = false;
        this.bgCol[0] = Color.cyan;
        this.bgCol[1] = Color.blue;
        this.bgCol[2] = Color.yellow;
        this.bgCol[3] = Color.red;
        this.bgCol[4] = Color.green;
        this.fgColor = this.bgCol[this.getLevel() % 5];
        this.loadMesg();
        this.randomMod = 150;
        this.speedUp();
        this.speedUp();
        this.setEnemies(10, 5);
        this.addToScore(0);
        this.addToShips(0);
        this.addToBullets(0);
        this.addToLevel(0);
        this.bonusTime = (int)(Math.random() * 500.0);
        this.bonusCount = 0;
        (this.player = new Player(this)).enable();
        this.setInOrder(false);
        this.enemies = new Enemy[this.getEnemyNumber()];
        this.enemyFire = new Bullet[this.getEnemyFire()];
        for (int i = 0; i < this.getEnemyNumber(); ++i) {
            (this.enemies[i] = new Enemy(this, i)).enable();
        }
        for (int j = 0; j < 8; ++j) {
            (this.playerFire[j] = new Bullet(this)).setType(2);
        }
        for (int k = 0; k < 5; ++k) {
            this.enemyFire[k] = new Bullet(this);
        }
    }
    
    public boolean isMode(final int n) {
        return n == this.mode;
    }
    
    public boolean isWaiting() {
        return this.waiting;
    }
    
    public void loadGraphics(final int n) {
        switch (n) {
            case 1: {
                this.player1G = this.inv.loadImage("player1G.gif");
                break;
            }
            case 2: {
                this.player2G = this.inv.loadImage("player2G.gif");
                break;
            }
            case 3: {
                this.bulletG = this.inv.loadImage("bulletG.gif");
                break;
            }
            case 4: {
                this.alien1aG = this.inv.loadImage("alien1aG.gif");
                break;
            }
            case 5: {
                this.alien1bG = this.inv.loadImage("alien1bG.gif");
                break;
            }
            case 6: {
                this.explode2G = this.inv.loadImage("explode1G.gif");
                break;
            }
            case 7: {
                this.explode1G = this.inv.loadImage("explode2G.gif");
                break;
            }
            case 8: {
                this.logoG = this.inv.loadImage("logo.gif");
                break;
            }
            case 9: {
                this.alien2aG = this.inv.loadImage("alien2aG.gif");
                break;
            }
            case 10: {
                this.alien2bG = this.inv.loadImage("alien2bG.gif");
                break;
            }
            case 11: {
                this.alien3aG = this.inv.loadImage("alien3aG.gif");
                break;
            }
            case 12: {
                this.alien3bG = this.inv.loadImage("alien3bG.gif");
                break;
            }
            case 13: {
                this.alien4aG = this.inv.loadImage("alien4aG.gif");
                break;
            }
            case 14: {
                this.alien4bG = this.inv.loadImage("alien4bG.gif");
                break;
            }
            case 15: {
                this.bgimage = this.inv.loadImage("invbg.gif");
                break;
            }
            case 16: {
                this.logoG = this.inv.loadImage("logoG.gif");
                break;
            }
            case 17: {
                this.bonus1G = this.inv.loadImage("bonus1G.gif");
                break;
            }
            case 18: {
                this.bonus2G = this.inv.loadImage("bonus2G.gif");
                break;
            }
            case 19: {
                this.bullet2G = this.inv.loadImage("bullet2G.gif");
                break;
            }
            case 20: {
                this.bt1a = this.inv.loadImage("bt1a.gif");
                break;
            }
            case 21: {
                this.bt1b = this.inv.loadImage("bt1b.gif");
                break;
            }
            case 22: {
                this.bt1c = this.inv.loadImage("bt1c.gif");
                break;
            }
            case 23: {
                this.bt2a = this.inv.loadImage("bt2a.gif");
                break;
            }
            case 24: {
                this.bt2b = this.inv.loadImage("bt2b.gif");
                break;
            }
            case 25: {
                this.bt2c = this.inv.loadImage("bt2c.gif");
                break;
            }
            case 26: {
                this.bt3a = this.inv.loadImage("bt3a.gif");
                break;
            }
            case 27: {
                this.bt3b = this.inv.loadImage("bt3b.gif");
                break;
            }
            case 28: {
                this.bt3c = this.inv.loadImage("bt3c.gif");
                break;
            }
            case 29: {
                this.real1 = this.inv.loadImage("real1.gif");
                break;
            }
            case 30: {
                this.real2 = this.inv.loadImage("real2.gif");
                break;
            }
            case 31: {
                this.real3 = this.inv.loadImage("real3.gif");
                break;
            }
        }
        this.inv.repaint();
    }
    
    private void loadMesg() {
        this.mesg[1] = "So you found the shoot button, huh?";
        this.mesg[2] = "This is just the beginning...";
        this.mesg[3] = "Still warming up.";
        this.mesg[4] = "That was easy";
        this.mesg[5] = "Stress? What is that?";
        this.mesg[6] = "Ooooooh you made it";
        this.mesg[7] = "You think your really something, wait.";
        this.mesg[8] = "Agression comes back to you";
        this.mesg[9] = "Still relaxed?";
        this.mesg[10] = "Even my grandmother got this far";
        this.mesg[11] = "Alright, try this then";
        this.mesg[12] = "Still alive? Better than I thought";
        this.mesg[13] = "Try this";
        this.mesg[14] = "That woke you, didn't it?";
        this.mesg[15] = "It's time to lose";
        this.mesg[16] = "Stop playing, this is bad for you";
        this.mesg[17] = "You are good!";
        this.mesg[18] = "Breaking records...";
        this.mesg[19] = "That's it, I'm out of here";
    }
    
    public void makeEnemies() {
        for (int i = 0; i < this.getEnemyNumber(); ++i) {
            (this.enemies[i] = new Enemy(this, i)).enable();
            if (this.getLevel() < 3) {
                if (i % 4 == 0) {
                    this.enemies[i].setType(2);
                }
                else {
                    this.enemies[i].setType(1);
                }
            }
            if (this.getLevel() > 2 && this.getLevel() < 5) {
                if (i < 5) {
                    this.enemies[i].setType(2);
                }
                else {
                    this.enemies[i].setType(1);
                }
                if (i == 2 || i == 3) {
                    this.enemies[i].setType(3);
                }
            }
            if (this.getLevel() > 4 && this.getLevel() < 8) {
                if (i < 5) {
                    this.enemies[i].setType(3);
                }
                else if (i < 10) {
                    this.enemies[i].setType(2);
                }
                else {
                    this.enemies[i].setType(1);
                }
            }
            if (this.getLevel() > 7 && this.getLevel() < 11) {
                if (i < 10) {
                    this.enemies[i].setType(2);
                }
                else {
                    this.enemies[i].setType(3);
                }
                if (i == 2 || i == 3) {
                    this.enemies[i].setType(4);
                }
            }
            if (this.getLevel() > 10 && this.getLevel() < 16) {
                if (i < 10) {
                    this.enemies[i].setType(3);
                }
                else {
                    this.enemies[i].setType(4);
                }
                if (i == 2 || i == 3) {
                    this.enemies[i].setType(2);
                }
            }
            if (this.getLevel() > 15) {
                if (i < 10) {
                    this.enemies[i].setType(4);
                }
                else {
                    this.enemies[i].setType(3);
                }
                if (i == 2 || i == 3) {
                    this.enemies[i].setType(2);
                }
            }
            if (this.bossMode) {
                if (this.getLevel() == 5) {
                    this.enemies[i].setType(1);
                }
                if (this.getLevel() == 10) {
                    this.enemies[i].setType(2);
                }
                if (this.getLevel() == 15) {
                    this.enemies[i].setType(3);
                }
                if (this.getLevel() > 15) {
                    this.enemies[i].setType(4);
                }
            }
        }
    }
    
    public void nextLevel() {
        this.fgColor = this.bgCol[this.getLevel() % 5];
        this.bossMode = false;
        this.bossHits = 0;
        this.bonusTime = (int)(Math.random() * 500.0);
        this.bonusCount = 0;
        this.enemyKilled = 0;
        this.addToLevel(1);
        this.addToLimit();
        this.setEnemies(10 + this.getLevel() - 1, 1 + this.getLevel());
        if (this.getLevel() % 5 == 0) {
            this.setEnemies(1, 10);
            this.bossMode = true;
        }
        this.addToScore(100);
        this.addToBullets(0);
        (this.player = new Player(this)).enable();
        this.setInOrder(false);
        this.enemyFire = new Bullet[this.getEnemyFire()];
        for (int i = 0; i < this.getEnemyFire(); ++i) {
            this.enemyFire[i] = new Bullet(this);
        }
        this.enemies = new Enemy[this.getEnemyNumber()];
        this.makeEnemies();
        for (int j = 0; j < 8; ++j) {
            (this.playerFire[j] = new Bullet(this)).setType(2);
        }
        this.inv.field.startActions();
    }
    
    public void playerKilled() {
        this.addToShips(-1);
        this.mode = 1;
        this.superBullets = false;
        this.inv.info.refresh();
    }
    
    public void reset() {
        this.limitBlock = 30;
        this.firstRun = false;
        this.score = 0;
        this.enemyKilled = 0;
        this.superBullets = false;
        this.mode = 1;
        this.bossMode = false;
    }
    
    public void setEnemies(final int numberOfEnemies, final int numberOfEnemyFire) {
        this.numberOfEnemyFire = numberOfEnemyFire;
        if (numberOfEnemies < 16) {
            this.numberOfEnemies = numberOfEnemies;
        }
        else {
            this.speedUp();
            this.numberOfEnemies = 15;
        }
    }
    
    public void setInOrder(final boolean firstRun) {
        this.firstRun = firstRun;
    }
    
    public void speedUp() {
        if (this.speed < 5) {
            ++this.speed;
        }
        else {
            this.bulletsUp();
        }
    }
    
    public void waitMode(final boolean waiting) {
        this.waiting = waiting;
    }
}
