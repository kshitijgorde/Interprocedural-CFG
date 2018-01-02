import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Image;
import java.util.Date;
import java.awt.Color;
import java.awt.Button;
import java.awt.Font;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Worm extends Applet implements Runnable
{
    BoardMap map;
    Food wormFood;
    Thread artist;
    Font bigFont;
    Font boldFont;
    Font smallFont;
    Button myButton;
    Color Grey;
    Color lightGreen;
    Color darkGreen;
    Color foodColor;
    Date date;
    private int seconds;
    private boolean oops;
    private boolean frozen;
    private boolean startNewGame;
    private boolean weJustStartedGame;
    private int width;
    private int height;
    private int visibleWidth;
    private int visibleHeight;
    private final int direction = 4;
    private int score;
    Integer intScore;
    private int foodPieces;
    Integer intFoodPieces;
    Integer intTimeLeft;
    private int delay;
    private int scoreStep;
    private final int diameter = 4;
    private int x;
    private int y;
    private int xOld;
    private int yOld;
    private int xDirection;
    private int yDirection;
    private int lastX;
    private int lastY;
    private int lastXOld;
    private int lastYOld;
    private int lastXDir;
    private int lastYDir;
    private byte contents;
    private final byte FreeSpace = 99;
    private final byte FoodSpace = 70;
    private final byte WormSpace = 40;
    private final byte LeftTurn = 50;
    private final byte RightTurn = 60;
    private final byte BonusSpace = 30;
    Image[] myImages;
    private int loadedBonus;
    private boolean bonusEaten;
    private int bonusAliveUntil;
    private boolean AlreadyRestored;
    
    public void init() {
        this.resize(400, 400);
        this.bigFont = new Font("TimesRoman", 0, 18);
        this.boldFont = new Font("TimesRoman", 1, 16);
        this.smallFont = new Font("TimesRoman", 1, 12);
        this.myButton = new Button("Start a New Game");
        this.Grey = new Color(192, 192, 192);
        this.lightGreen = new Color(165, 250, 165);
        this.darkGreen = new Color(0, 100, 0);
        this.intScore = new Integer(0);
        this.score = 0;
        this.scoreStep = 10;
        this.foodPieces = 0;
        this.intFoodPieces = new Integer(0);
        this.intTimeLeft = new Integer(0);
        (this.myImages = new Image[6])[0] = this.getImage(this.getDocumentBase(), "apple.gif");
        this.myImages[1] = this.getImage(this.getDocumentBase(), "banana.gif");
        this.myImages[2] = this.getImage(this.getDocumentBase(), "cherries.gif");
        this.myImages[3] = this.getImage(this.getDocumentBase(), "chili.gif");
        this.myImages[4] = this.getImage(this.getDocumentBase(), "pineappl.gif");
        this.myImages[5] = this.getImage(this.getDocumentBase(), "taz.jpg");
        this.weJustStartedGame = true;
        this.startNewGame = false;
        this.bonusEaten = true;
        this.AlreadyRestored = false;
        final Dimension size = this.size();
        this.height = size.height;
        this.width = size.width;
        this.visibleHeight = this.height - 40;
        this.visibleWidth = this.width - 40;
        this.delay = 175;
        this.bonusAliveUntil = 0;
        this.map = new BoardMap();
        this.date = new Date();
        this.seconds = this.date.getSeconds();
        this.add(this.myButton);
        this.validate();
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
        if (this.weJustStartedGame || this.startNewGame) {
            this.x = 44;
            this.y = 44;
            this.xOld = 1000;
            this.yOld = 1000;
            this.xDirection = 4;
            this.yDirection = 0;
            this.lastX = this.x;
            this.lastY = this.y;
            this.lastXOld = this.lastX;
            this.lastYOld = this.lastY;
            this.lastXDir = this.xDirection;
            this.lastYDir = this.yDirection;
            this.score = 0;
            this.foodPieces = 0;
            this.scoreStep = 10;
            this.delay = 175;
            this.bonusAliveUntil = 0;
            this.bonusEaten = true;
            (this.wormFood = new Food()).createBonus(graphics, this.map, this.myImages[0]);
            this.wormFood.createBonus(graphics, this.map, this.myImages[1]);
            this.wormFood.createBonus(graphics, this.map, this.myImages[2]);
            this.wormFood.createBonus(graphics, this.map, this.myImages[3]);
            this.wormFood.createBonus(graphics, this.map, this.myImages[4]);
            graphics.drawImage(this.myImages[5], this.width / 2 - 35, this.height / 2 - 120, this);
            this.map.clearBoard();
        }
        graphics.setColor(this.Grey);
        graphics.fill3DRect(0, 0, this.width, this.height, true);
        graphics.fill3DRect(39, 39, this.width - 78, this.height - 78, false);
        graphics.setColor(Color.white);
        graphics.fillRect(40, 40, this.width - 80, this.height - 80);
        graphics.setColor(Color.red);
        graphics.setFont(this.boldFont);
        graphics.drawString("Score              : ", 40, this.height - 25);
        graphics.drawString("Pieces Eaten  : ", 40, this.height - 8);
        graphics.setColor(this.darkGreen);
        graphics.setFont(this.smallFont);
        graphics.drawString(Integer.toString(this.score), 150, this.height - 25);
        graphics.drawString(Integer.toString(this.foodPieces), 150, this.height - 8);
        if (this.weJustStartedGame) {
            this.weJustStartedGame = false;
            this.wormFood = new Food(graphics, this.map);
        }
        else {
            for (int i = 0; i < 80; ++i) {
                for (int j = 0; j < 80; ++j) {
                    if (this.map.getFood(i, j) == 40 || this.map.getFood(i, j) == 50 || this.map.getFood(i, j) == 60) {
                        graphics.setColor(Color.red);
                        graphics.fillOval(i * 4 + 40, j * 4 + 40, 4, 4);
                    }
                    else if (this.map.getFood(i, j) == 70) {
                        graphics.setColor(this.foodColor = new Color(j + 100, i + 100, j * i % 220));
                        graphics.fillRect(i * 4 + 40, j * 4 + 40, 4, 4);
                    }
                    else if (this.map.getFood(i, j) < 30 && !this.AlreadyRestored) {
                        this.AlreadyRestored = true;
                        graphics.drawImage(this.myImages[this.loadedBonus], i * 4 + 40, j * 4 + 40, 24, 20, null);
                        graphics.setColor(Color.blue);
                        graphics.setFont(this.smallFont);
                        graphics.drawString("Time left for bonus", 245, this.height - 22);
                    }
                }
            }
        }
        this.AlreadyRestored = false;
        this.update(graphics);
    }
    
    public void update(final Graphics graphics) {
        if (this.startNewGame) {
            this.startNewGame = false;
            this.weJustStartedGame = true;
            this.oops = false;
            this.paint(graphics);
            return;
        }
        if (!this.oops && !this.weJustStartedGame) {
            if (this.bonusAliveUntil > 0) {
                if (--this.bonusAliveUntil == 0) {
                    final int n = this.wormFood.getBonusX() * 4 + 40;
                    final int n2 = this.wormFood.getBonusY() * 4 + 40;
                    graphics.setColor(Color.white);
                    graphics.fillRect(n, n2, 24, 20);
                    for (int i = 0; i < 5; ++i) {
                        for (int j = 0; j < 6; ++j) {
                            this.map.putWay(n + 4 * j, n2 + 4 * i, (byte)99);
                        }
                    }
                    graphics.setColor(this.Grey);
                    graphics.fillRect(220, this.height - 35, 150, 30);
                    this.bonusEaten = true;
                }
                if (!this.bonusEaten) {
                    graphics.setColor(this.Grey);
                    graphics.fillRect(280, this.height - 20, 30, 16);
                    graphics.setFont(this.smallFont);
                    graphics.setColor(Color.black);
                    graphics.drawString(Integer.toString(this.bonusAliveUntil), 285, this.height - 7);
                }
            }
            if (this.x != this.xOld || this.y != this.yOld) {
                this.matliErase(graphics, this.map);
                this.matliDraw(graphics, this.map);
                this.contents = this.map.getWay(this.x, this.y);
                if (this.contents == 70) {
                    this.map.putWay(this.x, this.y, (byte)40);
                    graphics.setColor(Color.white);
                    graphics.fillRect(this.x, this.y, 4, 4);
                    this.matliDraw(graphics);
                    this.score += this.scoreStep;
                    ++this.foodPieces;
                    if (this.score > 250000) {
                        this.giveMessage(graphics);
                    }
                    if (this.foodPieces >= 100) {
                        if (this.foodPieces < 250) {
                            this.scoreStep = 25;
                        }
                        else if (this.foodPieces < 500) {
                            this.scoreStep = 50;
                        }
                        else if (this.foodPieces < 1000) {
                            this.scoreStep = 100;
                        }
                        else if (this.foodPieces < 2000) {
                            this.scoreStep = 200;
                        }
                        else {
                            this.scoreStep = 500;
                        }
                    }
                    if (this.delay > 90 && this.foodPieces % 5 == 0) {
                        --this.delay;
                    }
                    graphics.setColor(this.Grey);
                    graphics.fillRect(150, this.height - 35, 70, 30);
                    graphics.setColor(this.darkGreen);
                    graphics.setFont(this.smallFont);
                    graphics.drawString(Integer.toString(this.score), 150, this.height - 25);
                    graphics.drawString(Integer.toString(this.foodPieces), 150, this.height - 8);
                    this.lastX -= this.lastXDir;
                    this.lastY -= this.lastYDir;
                    if (!this.frozen) {
                        this.wormFood.createFood(graphics, this.map);
                    }
                    this.seconds += this.score;
                    this.seconds = (int)(Math.random() * this.seconds) % 100;
                    if (this.bonusEaten && !this.frozen) {
                        if (this.seconds > 94) {
                            this.bonusEaten = false;
                            this.loadedBonus = 0;
                            this.bonusAliveUntil = 125;
                            this.wormFood.createBonus(graphics, this.map, this.myImages[0]);
                            graphics.setColor(Color.blue);
                            graphics.setFont(this.smallFont);
                            graphics.drawString("Time left for bonus", 245, this.height - 22);
                        }
                        else if (this.seconds > 90) {
                            this.bonusEaten = false;
                            this.loadedBonus = 1;
                            this.bonusAliveUntil = 125;
                            this.wormFood.createBonus(graphics, this.map, this.myImages[1]);
                            graphics.setColor(Color.blue);
                            graphics.setFont(this.smallFont);
                            graphics.drawString("Time left for bonus", 245, this.height - 22);
                        }
                        else if (this.seconds > 87) {
                            this.bonusEaten = false;
                            this.loadedBonus = 2;
                            this.bonusAliveUntil = 125;
                            this.wormFood.createBonus(graphics, this.map, this.myImages[2]);
                            graphics.setColor(Color.blue);
                            graphics.setFont(this.smallFont);
                            graphics.drawString("Time left for bonus", 245, this.height - 22);
                        }
                        else if (this.seconds > 85) {
                            this.bonusEaten = false;
                            this.loadedBonus = 3;
                            this.bonusAliveUntil = 125;
                            this.wormFood.createBonus(graphics, this.map, this.myImages[3]);
                            graphics.setColor(Color.blue);
                            graphics.setFont(this.smallFont);
                            graphics.drawString("Time left for bonus", 245, this.height - 22);
                        }
                        else if (this.seconds == 16) {
                            this.bonusEaten = false;
                            this.loadedBonus = 4;
                            this.bonusAliveUntil = 125;
                            this.wormFood.createBonus(graphics, this.map, this.myImages[4]);
                            graphics.setColor(Color.blue);
                            graphics.setFont(this.smallFont);
                            graphics.drawString("Time left for bonus", 245, this.height - 22);
                        }
                    }
                }
                else if (this.contents < 30) {
                    this.bonusAliveUntil = 0;
                    switch (this.loadedBonus) {
                        case 0: {
                            this.score += 250;
                            this.foodPieces += 5;
                            break;
                        }
                        case 1: {
                            this.score += 500;
                            this.foodPieces += 10;
                            break;
                        }
                        case 2: {
                            this.score += 1000;
                            this.foodPieces += 25;
                            break;
                        }
                        case 3: {
                            this.score += 5000;
                            this.foodPieces += 50;
                            break;
                        }
                        case 4: {
                            this.score += 10000;
                            this.foodPieces += 100;
                            break;
                        }
                    }
                    this.bonusEaten = true;
                    graphics.setColor(this.Grey);
                    graphics.fillRect(150, this.height - 35, 220, 30);
                    graphics.setColor(this.darkGreen);
                    graphics.setFont(this.smallFont);
                    graphics.drawString(Integer.toString(this.score), 150, this.height - 25);
                    graphics.drawString(Integer.toString(this.foodPieces), 150, this.height - 8);
                    this.map.putWay(this.x, this.y, (byte)40);
                    final int n3 = this.x - this.contents % 6 * 4;
                    final int n4 = this.y - this.contents / 6 * 4;
                    graphics.setColor(Color.white);
                    graphics.fillRect(n3, n4, 24, 20);
                    for (byte b = 0; b < 5; ++b) {
                        for (byte b2 = 0; b2 < 6; ++b2) {
                            if (this.contents / 6 != b || this.contents % 6 != b2) {
                                this.map.putWay(n3 + 4 * b2, n4 + 4 * b, (byte)99);
                            }
                        }
                    }
                    this.matliDraw(graphics);
                    if (this.score > 250000) {
                        this.giveMessage(graphics);
                    }
                }
                else if (this.contents == 50) {
                    if (this.xDirection == 4) {
                        this.xDirection = 0;
                        this.yDirection = -4;
                    }
                    else if (this.xDirection == -4) {
                        this.xDirection = 0;
                        this.yDirection = 4;
                    }
                    else if (this.yDirection == 4) {
                        this.yDirection = 0;
                        this.xDirection = 4;
                    }
                    else {
                        this.yDirection = 0;
                        this.xDirection = -4;
                    }
                }
                else if (this.contents == 60) {
                    if (this.xDirection == 4) {
                        this.xDirection = 0;
                        this.yDirection = 4;
                    }
                    else if (this.xDirection == -4) {
                        this.xDirection = 0;
                        this.yDirection = -4;
                    }
                    else if (this.yDirection == 4) {
                        this.yDirection = 0;
                        this.xDirection = -4;
                    }
                    else {
                        this.yDirection = 0;
                        this.xDirection = 4;
                    }
                }
                if (this.map.getWay(this.lastX, this.lastY) == 50) {
                    if (this.lastXDir == 4) {
                        this.lastXDir = 0;
                        this.lastYDir = -4;
                    }
                    else if (this.lastXDir == -4) {
                        this.lastXDir = 0;
                        this.lastYDir = 4;
                    }
                    else if (this.lastYDir == 4) {
                        this.lastYDir = 0;
                        this.lastXDir = 4;
                    }
                    else {
                        this.lastYDir = 0;
                        this.lastXDir = -4;
                    }
                }
                else if (this.map.getWay(this.lastX, this.lastY) == 60) {
                    if (this.lastXDir == 4) {
                        this.lastXDir = 0;
                        this.lastYDir = 4;
                    }
                    else if (this.lastXDir == -4) {
                        this.lastXDir = 0;
                        this.lastYDir = -4;
                    }
                    else if (this.lastYDir == 4) {
                        this.lastYDir = 0;
                        this.lastXDir = -4;
                    }
                    else {
                        this.lastYDir = 0;
                        this.lastXDir = 4;
                    }
                }
                this.xOld = this.x;
                this.yOld = this.y;
                this.x += this.xDirection;
                this.y += this.yDirection;
                this.lastXOld = this.lastX;
                this.lastYOld = this.lastY;
                this.lastX += this.lastXDir;
                this.lastY += this.lastYDir;
                if (this.x >= this.visibleWidth || this.x < 40 || this.y >= this.visibleHeight || this.y < 40 || this.map.getWay(this.x, this.y) == 50 || this.map.getWay(this.x, this.y) == 60 || this.map.getWay(this.x, this.y) == 40) {
                    this.giveMessage(graphics);
                }
            }
        }
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.frozen) {
            this.frozen = false;
            this.start();
        }
        else {
            this.frozen = true;
            this.stop();
        }
        return true;
    }
    
    public void start() {
        this.requestFocus();
        if (!this.frozen && this.artist == null) {
            (this.artist = new Thread(this)).start();
        }
    }
    
    public void stop() {
        this.artist = null;
    }
    
    public void run() {
        while (this.artist != null) {
            try {
                Thread.sleep(this.delay);
            }
            catch (InterruptedException ex) {}
            this.repaint();
        }
        this.artist = null;
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.myButton) {
            this.startNewGame = true;
            if (this.oops || this.frozen) {
                this.frozen = false;
                this.start();
            }
            else {
                this.repaint();
            }
        }
        return false;
    }
    
    public boolean keyDown(final Event event, final int n) {
        if (!this.oops) {
            if (n == 1006 && this.map.getFood((this.x - 40) / 4, (this.y - 40) / 4) == 99) {
                this.map.putWay(this.x, this.y, (byte)50);
                this.repaint();
                return true;
            }
            if (n == 1007 && this.map.getFood((this.x - 40) / 4, (this.y - 40) / 4) == 99) {
                this.map.putWay(this.x, this.y, (byte)60);
                this.repaint();
                return true;
            }
        }
        return false;
    }
    
    public void matliErase(final Graphics graphics, final BoardMap boardMap) {
        boardMap.putWay(this.lastXOld, this.lastYOld, (byte)99);
        graphics.setColor(Color.white);
        graphics.fillOval(this.lastXOld, this.lastYOld, 4, 4);
    }
    
    public void matliDraw(final Graphics graphics, final BoardMap boardMap) {
        if (boardMap.getWay(this.x, this.y) == 99) {
            boardMap.putWay(this.x, this.y, (byte)40);
        }
        graphics.setColor(Color.red);
        graphics.fillOval(this.x, this.y, 4, 4);
    }
    
    public void matliDraw(final Graphics graphics) {
        graphics.setColor(Color.red);
        graphics.fillOval(this.x, this.y, 4, 4);
    }
    
    public void giveMessage(final Graphics graphics) {
        this.oops = true;
        this.frozen = true;
        this.stop();
        for (int i = 1; i < 80; ++i) {
            try {
                Thread.sleep(5L);
            }
            catch (InterruptedException ex) {}
            graphics.setColor(Color.black);
            graphics.drawRect(this.width / 2 - i, this.height / 2 - i / 4, i + i + 8, i / 2 + 8);
            graphics.setColor(this.lightGreen);
            graphics.fillRect(this.width / 2 - i + 1, this.height / 2 - i / 4 + 1, i + i + 7, i / 2 + 7);
        }
        graphics.setColor(Color.black);
        graphics.setFont(this.bigFont);
        if (this.score > 250000) {
            graphics.drawString("Very good job!!!", this.width / 2 - 55, this.height / 2 + 8);
            graphics.drawImage(this.myImages[5], this.width / 2 - 50, this.height / 2 - 120, this);
            return;
        }
        graphics.drawString("Oops, try again!", this.width / 2 - 55, this.height / 2 + 8);
    }
    
    public Worm() {
        this.oops = false;
        this.frozen = false;
    }
}
