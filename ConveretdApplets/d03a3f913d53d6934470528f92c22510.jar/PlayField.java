import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.applet.Applet;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyListener;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

final class PlayField extends Canvas implements Runnable, KeyListener
{
    protected Invaders inv;
    protected Data data;
    protected Thread mainThread;
    protected boolean runFlag;
    protected boolean threadAlive;
    protected int keyCode;
    protected int delay;
    protected boolean shoot;
    protected int a;
    private Image buffer;
    protected boolean starting;
    private Graphics bufferGraphics;
    protected boolean gameOver;
    protected Font textfont;
    protected int bbcount;
    protected int loop;
    protected long time;
    protected boolean takeTime;
    protected int fps;
    
    public PlayField(final Invaders inv, final Data data) {
        this.runFlag = false;
        this.threadAlive = true;
        this.a = 0;
        this.starting = false;
        this.textfont = new Font("Helvetica", 1, 12);
        this.loop = 0;
        this.takeTime = true;
        this.delay = 22;
        this.inv = inv;
        this.data = data;
        this.buffer = inv.getEmptyImage(400, 300);
        this.bufferGraphics = this.buffer.getGraphics();
        (this.mainThread = new Thread(this)).start();
        this.setBackground(data.bgColor);
        this.addKeyListener(this);
        this.requestFocus();
        this.starting = true;
    }
    
    private void checkBonusCollision() {
        for (int i = 0; i < 8; ++i) {
            if (this.data.playerFire[i].isAlive() && !this.data.bonus.isExploding() && this.data.bonus.isAlive() && this.data.bonus.getXco() < this.data.playerFire[i].getXco() && this.data.bonus.getXco() + 20 > this.data.playerFire[i].getXco() && this.data.bonus.getYco() > this.data.playerFire[i].getYco() && this.data.bonus.getYco() - 20 < this.data.playerFire[i].getYco()) {
                this.data.bonus.explode(30);
                this.data.playerFire[i].disable();
                this.data.bonusKilled();
                this.inv.explodeS.play();
            }
        }
    }
    
    private void checkBulletBug() {
        if (this.data.getBulletsLeft() == 0) {
            ++this.bbcount;
        }
        else {
            this.bbcount = 0;
        }
        if (this.bbcount > 20) {
            this.data.addToBullets(1);
        }
    }
    
    private void checkCollision() {
        for (int i = 0; i < 8; ++i) {
            if (this.data.playerFire[i].isAlive()) {
                for (int j = 0; j < this.data.getEnemyNumber(); ++j) {
                    if (!this.data.enemies[j].isExploding() && this.data.enemies[j].isAlive()) {
                        if (this.data.enemies[j].getXco() < this.data.playerFire[i].getXco() && this.data.enemies[j].getXco() + 20 > this.data.playerFire[i].getXco() && !this.data.bossMode && this.data.enemies[j].getYco() > this.data.playerFire[i].getYco() && this.data.enemies[j].getYco() - 20 < this.data.playerFire[i].getYco()) {
                            this.data.enemies[j].explode(10);
                            if (!this.data.superBullets) {
                                this.data.playerFire[i].disable();
                            }
                            this.data.enemyKilled();
                            this.inv.dieS.play();
                            if (this.data.isMode(1) && !this.data.superBullets) {
                                this.data.addToBullets(1);
                            }
                            else if (!this.data.superBullets) {
                                this.data.addToBullets(1, i);
                            }
                        }
                        if (this.data.bossMode && this.data.enemies[j].getXco() < this.data.playerFire[i].getXco() && this.data.enemies[j].getXco() + 60 > this.data.playerFire[i].getXco() && this.data.enemies[j].getYco() > this.data.playerFire[i].getYco() && this.data.enemies[j].getYco() - 60 < this.data.playerFire[i].getYco()) {
                            final Data data = this.data;
                            ++data.bossHits;
                            if (this.data.bossHits > this.data.getLevel() * 2) {
                                this.data.enemies[j].explode(10);
                                this.data.enemyKilled();
                            }
                            if (!this.data.superBullets) {
                                this.data.playerFire[i].disable();
                            }
                            this.inv.dieS.play();
                            if (this.data.isMode(1) && !this.data.superBullets) {
                                this.data.addToBullets(1);
                            }
                            else {
                                if (i > 3 && !this.data.playerFire[i - 4].isAlive()) {
                                    this.data.addToBullets(1);
                                }
                                if (i < 4 && !this.data.playerFire[i + 4].isAlive()) {
                                    this.data.addToBullets(1);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    private void checkPlayerCollision() {
        for (int i = 0; i < this.data.getEnemyFire(); ++i) {
            if (this.data.enemyFire[i].isAlive() && !this.data.player.isExploding() && this.data.player.isAlive() && this.data.player.getXco() < this.data.enemyFire[i].getXco() && this.data.player.getXco() + 20 > this.data.enemyFire[i].getXco() && this.data.player.getYco() > this.data.enemyFire[i].getYco() && this.data.player.getYco() - 20 < this.data.enemyFire[i].getYco()) {
                this.data.player.explode(40);
                this.data.enemyFire[i].disable();
                this.data.playerKilled();
                this.inv.explodeS.play();
            }
        }
    }
    
    private void checkRespawn() {
        if (!this.data.player.isAlive() && this.data.getShipsLeft() > 0) {
            this.data.player.enable();
        }
        else if (!this.data.player.isAlive() && this.data.getShipsLeft() == 0) {
            this.gameOver();
        }
    }
    
    private void doHighScores(final int score, final int level) {
        final HighScoreTool highScoreTool = new HighScoreTool(this.inv);
        highScoreTool.setScore(score);
        highScoreTool.setLevel(level);
        highScoreTool.setFile(this.inv.file);
        highScoreTool.setDir(this.inv.dir);
        highScoreTool.doHigh();
    }
    
    private void doPaint() {
        this.repaint();
    }
    
    private void drawBasicField(final Graphics graphics) {
        this.buffer.flush();
        this.bufferGraphics.setFont(this.textfont);
        this.bufferGraphics.setColor(this.data.bgColor);
        this.bufferGraphics.clearRect(0, 0, 400, 300);
        this.bufferGraphics.fillRect(0, 0, 400, 300);
        this.bufferGraphics.setColor(this.data.fgColor);
        this.bufferGraphics.fillRect(0, 280, this.data.getLimitBlock(), 20);
        this.bufferGraphics.fillRect(400 - this.data.getLimitBlock(), 280, this.data.getLimitBlock(), 20);
        this.bufferGraphics.drawRect(1, 1, 398, 298);
        this.bufferGraphics.setColor(this.data.bgColor);
        this.bufferGraphics.fillRect(5, 290, 4 * this.data.getBulletsLeft(), 5);
        this.bufferGraphics.setColor(this.data.fgColor);
    }
    
    private void drawBullets(final Graphics graphics) {
        for (int i = 0; i < 8; ++i) {
            if (this.data.playerFire[i].isAlive()) {
                this.bufferGraphics.drawImage(this.data.playerFire[i].getFace(), this.data.playerFire[i].getXco(), this.data.playerFire[i].getYco(), this);
            }
        }
        for (int j = 0; j < this.data.getEnemyFire(); ++j) {
            if (this.data.enemyFire[j].isAlive()) {
                this.bufferGraphics.drawImage(this.data.enemyFire[j].getFace(), this.data.enemyFire[j].getXco(), this.data.enemyFire[j].getYco(), this);
            }
        }
    }
    
    private void drawEnemies(final Graphics graphics) {
        for (int i = 0; i < this.data.getEnemyNumber(); ++i) {
            if (this.data.enemies[i].isAlive()) {
                if (this.data.bossMode) {
                    final int n = (int)(this.data.bossHits / (this.data.getLevel() * 2) * 100.0);
                    this.bufferGraphics.drawRect(this.data.enemies[i].getXco(), this.data.enemies[i].getYco() - 20, 50, 5);
                    this.bufferGraphics.fillRect(this.data.enemies[i].getXco(), this.data.enemies[i].getYco() - 20, n / 2, 5);
                    this.bufferGraphics.drawImage(this.data.enemies[i].getFace(), this.data.enemies[i].getXco(), this.data.enemies[i].getYco(), 60, 60, this);
                }
                else {
                    this.bufferGraphics.drawImage(this.data.enemies[i].getFace(), this.data.enemies[i].getXco(), this.data.enemies[i].getYco(), this);
                }
            }
        }
    }
    
    private void drawMessages(final Graphics graphics) {
        if (this.data.isWaiting() || this.starting) {
            this.bufferGraphics.setColor(Color.gray);
            this.bufferGraphics.fillRect(82, 82, 240, 140);
            this.bufferGraphics.setColor(this.data.bgColor);
            this.bufferGraphics.fillRect(80, 80, 240, 140);
            this.bufferGraphics.setColor(this.data.fgColor);
            this.bufferGraphics.drawRect(81, 81, 238, 138);
            if (this.starting) {
                this.bufferGraphics.drawString("Press Enter to Start", 100, 190);
                this.bufferGraphics.drawString("Save The Earth!", 100, 100);
            }
            else {
                this.bufferGraphics.drawString("Level " + this.data.getLevel() + " completed!", 100, 160);
                this.bufferGraphics.drawString("Score: " + this.data.getScore(), 100, 130);
                if (!this.gameOver) {
                    this.bufferGraphics.drawString("Press Enter for next level", 100, 190);
                    this.bufferGraphics.drawString(this.data.getMesg(this.data.getLevel()), 100, 100);
                }
                else {
                    if (this.inv.doHighScore) {
                        this.bufferGraphics.drawString("Press Enter to view highscores", 100, 190);
                    }
                    else {
                        this.bufferGraphics.drawString("Press Enter to Restart", 100, 190);
                    }
                    if (this.data.getLevel() < 5) {
                        this.bufferGraphics.drawString("You had to shoot those moving things", 100, 100);
                    }
                    else if (this.data.getLevel() < 10) {
                        this.bufferGraphics.drawString("No No No! Not good!", 100, 100);
                    }
                    else if (this.data.getLevel() < 15) {
                        this.bufferGraphics.drawString("Could've been worse...", 100, 100);
                    }
                    else {
                        this.bufferGraphics.drawString("Excellent play!", 100, 100);
                    }
                }
            }
        }
    }
    
    private void enemyAttack() {
        for (int i = 0; i < this.data.getEnemyNumber(); ++i) {
            if (this.data.enemies[i].isAlive() && !this.data.enemies[i].isExploding()) {
                this.data.enemies[i].randAttack();
            }
        }
    }
    
    public void gameOver() {
        this.inv.endS.play();
        this.gameOver = true;
        this.data.waitMode(true);
        this.runFlag = false;
    }
    
    private void generateBonus() {
        if (this.data.bonus.isAlive()) {
            this.data.bonus.calcMove();
        }
        this.data.generateBonus();
    }
    
    private int getKey() {
        return this.keyCode;
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        if (this.data.isWaiting() || this.starting) {
            if (keyEvent.getKeyCode() == 10 || keyEvent.getKeyChar() == '\n') {
                if (this.starting) {
                    this.starting = false;
                }
                else if (!this.gameOver) {
                    this.loadnext();
                }
                else {
                    this.data.gameOver();
                    if (this.inv.doHighScore) {
                        this.doHighScores(this.data.getScore(), this.data.getLevel());
                    }
                }
                this.data.waitMode(false);
            }
        }
        else if (this.data.player.isAlive()) {
            if (keyEvent.getKeyCode() != 39 && keyEvent.getKeyCode() != 37) {
                this.data.player.randAttack();
            }
            else if (keyEvent.getKeyCode() == 37 || keyEvent.getKeyCode() == 39) {
                this.keyCode = keyEvent.getKeyCode();
            }
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() != 39 && keyEvent.getKeyCode() != 37) {}
        if (this.keyCode == keyEvent.getKeyCode()) {
            this.keyCode = 0;
        }
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void kill() {
        this.threadAlive = false;
    }
    
    private void loadnext() {
        this.data.nextLevel();
    }
    
    private void moveBullets() {
        for (int i = 0; i < 8; ++i) {
            if (this.data.playerFire[i].isAlive()) {
                this.data.playerFire[i].calcMove(6, i);
            }
        }
        for (int j = 0; j < this.data.getEnemyFire(); ++j) {
            if (this.data.enemyFire[j].isAlive()) {
                this.data.enemyFire[j].calcMove();
            }
        }
    }
    
    private void moveShips() {
        for (int i = 0; i < this.data.getEnemyNumber(); ++i) {
            if (this.data.enemies[i].isAlive() && !this.data.enemies[i].isExploding()) {
                this.data.enemies[i].calcMove();
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        this.drawBasicField(graphics);
        if (!this.inv.registered) {
            this.bufferGraphics.drawString("Unregistered Version", 10, 20);
            this.bufferGraphics.drawString("Http://www.realapplets.com", 230, 20);
        }
        if (this.data.player.isAlive()) {
            this.bufferGraphics.drawImage(this.data.player.getFace(), this.data.player.getXco(), this.data.player.getYco(), this);
        }
        if (this.data.bonus.isAlive()) {
            this.bufferGraphics.drawImage(this.data.bonus.getFace(), this.data.bonus.getXco(), this.data.bonus.getYco(), this);
        }
        this.drawEnemies(graphics);
        this.drawBullets(graphics);
        this.drawMessages(graphics);
        graphics.drawImage(this.buffer, 0, 0, this);
    }
    
    public void processEvent(final int n) {
        if (n == 37) {
            this.data.player.calcMove(-5);
        }
        if (n == 39) {
            this.data.player.calcMove(5);
        }
    }
    
    public void run() {
        while (this.threadAlive) {
            while (this.runFlag) {
                this.requestFocus();
                try {
                    Thread.sleep(this.delay);
                }
                catch (Exception ex) {}
                if (!this.starting) {
                    this.processEvent(this.getKey());
                    this.moveShips();
                    this.moveBullets();
                    this.enemyAttack();
                    this.checkCollision();
                    this.checkPlayerCollision();
                    this.checkRespawn();
                    this.generateBonus();
                    this.checkBonusCollision();
                    this.checkBulletBug();
                    this.doPaint();
                }
            }
            try {
                if (this.gameOver) {
                    Thread.sleep(2000L);
                }
                Thread.sleep(500L);
            }
            catch (Exception ex2) {}
        }
        System.out.println("Thread has stopped");
    }
    
    public void startActions() {
        if ((this.data.getLevel() - 1) % 5 == 0) {
            this.inv.startS.play();
        }
        this.runFlag = true;
        this.inv.info.refresh();
    }
    
    public void stopActions() {
        this.runFlag = false;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
