import java.awt.Event;
import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.net.URL;
import java.applet.AudioClip;
import java.awt.Font;
import java.awt.Choice;
import java.awt.Graphics;
import java.util.Vector;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Pacman extends Applet
{
    static final int appletWidth = 425;
    static final int appletHeight = 440;
    static Pacmen p;
    static Pacmen p2;
    Wall w;
    Food f;
    static int level;
    static int score;
    static int lives;
    int size;
    boolean started;
    static Vector level1;
    static Vector level2;
    static Vector level3;
    static Vector levelCustom;
    static Vector food1;
    static Vector food2;
    static Vector food3;
    static Vector foodCustom;
    static Vector sleepThreadVector;
    static Graphics gfx;
    static boolean[][] customArray;
    static boolean mapEditorOn;
    Choice mapChoice;
    Choice instructions;
    Choice colorChoice;
    Choice editorInstructions;
    Choice playerNumberChoice;
    Choice bulletChoice;
    setPanel choicePanel;
    ColorThread ct;
    static Vector[] foodArray;
    static char[][] foodCustomArray;
    static Vector bulletVector;
    static Vector mineVector;
    static Font normalFont;
    static Font endFont;
    static AudioClip wc;
    static AudioClip gc;
    static AudioClip ac;
    static AudioClip ec;
    static URL baseURL;
    static boolean ended;
    static char editorSet;
    static boolean soundsLoaded;
    
    public void init() {
        Pacmen.g = this.getGraphics();
        Pacman.level1 = new Vector();
        Pacman.level2 = new Vector();
        Pacman.level3 = new Vector();
        Pacman.levelCustom = new Vector();
        Pacman.sleepThreadVector = new Vector();
        Pacman.gfx = this.getGraphics();
        this.initLevel1();
        Pacman.level = 0;
        this.choicePanel = new setPanel(425, 82);
        this.setLayout(new BorderLayout(4, 4));
        this.add("South", this.choicePanel);
        (this.mapChoice = new Choice()).addItem("Map 1: The Fork");
        this.mapChoice.addItem("Map 2: Garden Maze");
        this.mapChoice.addItem("Map 3: Ode to Tetris");
        this.mapChoice.addItem("Map 4: Custom");
        this.mapChoice.addItem("Level Editor");
        this.choicePanel.add(this.mapChoice);
        (this.instructions = new Choice()).addItem("INSTRUCTIONS (pull down)");
        this.instructions.addItem("The object of the game is to");
        this.instructions.addItem("kill the other Pac-person.");
        this.instructions.addItem("Player one moves with W, A, S, D");
        this.instructions.addItem("shoots with space, and lays mines with B.");
        this.instructions.addItem("Player two moves with the arrow");
        this.instructions.addItem("keys, shoots with return, and");
        this.instructions.addItem("lays mines with the apostrophe key.");
        this.instructions.addItem("Copyright © 1999 David Kaplan");
        this.instructions.addItem("davkapl@aol.com");
        this.instructions.addItem("http://members.aol.com/davkapl");
        this.choicePanel.add(this.instructions);
        this.colorChoice = new Choice();
        this.editorInstructions = new Choice();
        this.colorChoice.addItem("Color List (for editor)");
        this.colorChoice.addItem("Random");
        this.colorChoice.addItem("Red");
        this.colorChoice.addItem("Green");
        this.colorChoice.addItem("Blue");
        this.colorChoice.addItem("Magenta");
        this.colorChoice.addItem("Yellow");
        this.colorChoice.addItem("Orange");
        this.colorChoice.addItem("White");
        this.choicePanel.add(this.colorChoice);
        this.editorInstructions.addItem("Level Editor Instructions");
        this.editorInstructions.addItem("Click once to draw a wall,");
        this.editorInstructions.addItem("and click again to erase it.");
        this.editorInstructions.addItem("To place an ammo pack, hold");
        this.editorInstructions.addItem("down the 'a' key and click.");
        this.editorInstructions.addItem("To place mine ammo, hold down");
        this.editorInstructions.addItem("the 'm' key and click.  To erase");
        this.editorInstructions.addItem("an ammo pack, hold down 'a' and");
        this.editorInstructions.addItem("click again; hold down 'm' and");
        this.editorInstructions.addItem("click to erase mine ammo.");
        this.editorInstructions.addItem("Select wall color using the");
        this.editorInstructions.addItem("pull-down.  Then select the");
        this.editorInstructions.addItem("'Custom' map and enjoy!");
        this.choicePanel.add(this.editorInstructions);
        Pacman.customArray = new boolean[17][16];
        Pacman.foodCustomArray = new char[17][16];
        this.ct = new ColorThread();
        (this.playerNumberChoice = new Choice()).addItem("2 Players");
        this.playerNumberChoice.addItem("3 Players");
        this.playerNumberChoice.addItem("4 Players");
        (this.bulletChoice = new Choice()).addItem("Bullet Speed");
        this.bulletChoice.addItem("6x");
        this.bulletChoice.addItem("4x (default)");
        this.bulletChoice.addItem("3x");
        this.bulletChoice.addItem("2x");
        this.choicePanel.add(this.bulletChoice);
        Pacman.food1 = new Vector();
        Pacman.food2 = new Vector();
        Pacman.food3 = new Vector();
        Pacman.foodCustom = new Vector();
        this.initFood1();
        this.initFood2();
        this.initFood3();
        (Pacman.foodArray = new Vector[4])[0] = Pacman.food1;
        Pacman.foodArray[1] = Pacman.food2;
        Pacman.foodArray[2] = Pacman.food3;
        Pacman.foodArray[3] = Pacman.foodCustom;
        this.initFoodCustom();
        Pacman.bulletVector = new Vector();
        Pacman.mineVector = new Vector();
        Pacman.normalFont = new Font("Geneva", 0, 10);
        Pacman.endFont = new Font("Geneva", 1, 30);
        Pacman.gfx.setFont(Pacman.normalFont);
        Pacman.baseURL = this.getCodeBase();
        Pacman.ended = false;
        Pacman.editorSet = 'w';
        Pacman.p = new Pacmen(25, 25, true);
        Pacman.p2 = new Pacmen(375, 350, false);
    }
    
    public void loadSounds() {
        try {
            Pacman.wc = this.getAudioClip(new URL("http://members.aol.com/davkapl/PacWars/winner.au"));
        }
        catch (Exception ex) {}
        try {
            Pacman.gc = this.getAudioClip(new URL("http://www.best.com/~jfn/gunshot.au"));
        }
        catch (Exception ex2) {}
        try {
            Pacman.ac = this.getAudioClip(new URL("http://www.best.com/~jfn/applause.au"));
        }
        catch (Exception ex3) {}
        try {
            Pacman.ec = this.getAudioClip(new URL("http://www.best.com/~jfn/explosion.au"));
        }
        catch (Exception ex4) {}
        try {
            this.play(new URL("http://www.best.com/~jfn/letsrock.au"));
        }
        catch (Exception ex5) {}
        Pacman.soundsLoaded = true;
    }
    
    public void start() {
        this.repaint();
        this.loadSounds();
        this.repaint();
        if (this.started) {
            Pacman.p.start();
            Pacman.p2.start();
        }
    }
    
    public void stop() {
        Pacman.p.stop();
        Pacman.p2.stop();
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, 425, 440);
        if (!Pacman.ended) {
            if (Pacman.level == 1) {
                for (int i = 0; i < Pacman.level1.size(); ++i) {
                    (this.w = (Wall)Pacman.level1.elementAt(i)).draw(graphics);
                }
                for (int j = 0; j < Pacman.foodArray[0].size(); ++j) {
                    ((Food)Pacman.foodArray[0].elementAt(j)).draw(graphics);
                }
            }
            else if (Pacman.level == 2) {
                for (int k = 0; k < Pacman.level2.size(); ++k) {
                    (this.w = (Wall)Pacman.level2.elementAt(k)).draw(graphics);
                }
                for (int l = 0; l < Pacman.foodArray[1].size(); ++l) {
                    ((Food)Pacman.foodArray[1].elementAt(l)).draw(graphics);
                }
            }
            else if (Pacman.level == 3) {
                for (int n = 0; n < Pacman.level3.size(); ++n) {
                    (this.w = (Wall)Pacman.level3.elementAt(n)).draw(graphics);
                }
                for (int n2 = 0; n2 < Pacman.foodArray[2].size(); ++n2) {
                    ((Food)Pacman.foodArray[2].elementAt(n2)).draw(graphics);
                }
            }
            else if (Pacman.level == 4) {
                if (this.colorChoice.getSelectedIndex() == 2) {
                    Wall.wallColor = Color.red;
                    Wall.bricks = true;
                }
                else if (this.colorChoice.getSelectedIndex() == 3) {
                    Wall.wallColor = Color.green;
                    Wall.bricks = true;
                }
                else if (this.colorChoice.getSelectedIndex() == 4) {
                    Wall.wallColor = Color.blue;
                    Wall.bricks = true;
                }
                else if (this.colorChoice.getSelectedIndex() == 5) {
                    Wall.wallColor = Color.magenta;
                    Wall.bricks = true;
                }
                else if (this.colorChoice.getSelectedIndex() == 6) {
                    Wall.wallColor = Color.yellow;
                    Wall.bricks = true;
                }
                else if (this.colorChoice.getSelectedIndex() == 7) {
                    Wall.wallColor = Color.orange;
                    Wall.bricks = true;
                }
                else if (this.colorChoice.getSelectedIndex() == 8) {
                    Wall.wallColor = Color.white;
                    Wall.bricks = true;
                }
                for (int n3 = 0; n3 < Pacman.levelCustom.size(); ++n3) {
                    if (this.colorChoice.getSelectedIndex() == 1) {
                        Wall.wallColor = new Color((int)(Math.random() * 225.0) + 30, (int)(Math.random() * 195.0) + 60, (int)(Math.random() * 165.0) + 90);
                        Wall.bricks = false;
                    }
                    (this.w = (Wall)Pacman.levelCustom.elementAt(n3)).draw(graphics);
                }
                for (int n4 = 0; n4 < Pacman.foodArray[3].size(); ++n4) {
                    ((Food)Pacman.foodArray[3].elementAt(n4)).draw(graphics);
                }
            }
        }
        else if (Pacman.level == 5) {
            graphics.setColor(Color.red);
            for (int n5 = 0; n5 < 17; ++n5) {
                for (int n6 = 0; n6 < 16; ++n6) {
                    if (Pacman.customArray[n5][n6]) {
                        graphics.fillRect(25 * n5, 25 * n6, 25, 25);
                    }
                }
            }
            graphics.drawString("P1", 25, 35);
            graphics.drawString("P2", 375, 360);
            graphics.drawString("start", 25, 45);
            graphics.drawString("start", 375, 370);
            for (int n7 = 0; n7 < 17; ++n7) {
                for (int n8 = 0; n8 < 16; ++n8) {
                    if (Pacman.foodCustomArray[n7][n8] == 'a' || Pacman.foodCustomArray[n7][n8] == 'm') {
                        new Food(25 * n7, 25 * n8, Pacman.foodCustomArray[n7][n8], 4);
                    }
                }
            }
        }
        if (!Pacman.soundsLoaded) {
            graphics.setColor(Color.green);
            graphics.drawString("Please wait for the sounds to load...", 200, 200);
            graphics.drawString("This should take ~32 seconds on a 33.6 modem", 200, 215);
        }
        else if (!this.started) {
            graphics.setColor(Color.green);
            graphics.drawString("Click to begin", 200, 200);
        }
        graphics.setColor(Color.white);
        graphics.fillRect(0, 400, 425, 40);
        graphics.setColor(Color.black);
        if (Pacman.level > 0) {
            updateStatusBar();
        }
        if (Pacman.level > 0 && this.started && (Pacman.p.health == 0 || Pacman.p2.health == 0)) {
            final boolean b = Pacman.p.health == 0;
            graphics.setFont(Pacman.endFont);
            graphics.setColor(b ? Color.blue : Color.yellow);
            graphics.drawString(b ? "Player Two is the WINNER!" : "Player One is the WINNER!", 10, 200);
            graphics.setFont(Pacman.normalFont);
        }
    }
    
    public boolean keyDown(final Event event, final int n) {
        if (n == 1004) {
            if (Pacman.p2.checkUp()) {
                Pacman.p2.dir = 'u';
            }
            else {
                Pacman.p2.waitingUp = true;
            }
        }
        if (n == 1005) {
            if (Pacman.p2.checkDown()) {
                Pacman.p2.dir = 'd';
            }
            else {
                Pacman.p2.waitingDown = true;
            }
        }
        if (n == 1006) {
            if (Pacman.p2.checkLeft()) {
                Pacman.p2.dir = 'l';
            }
            else {
                Pacman.p2.waitingLeft = true;
            }
        }
        if (n == 1007) {
            if (Pacman.p2.checkRight()) {
                Pacman.p2.dir = 'r';
            }
            else {
                Pacman.p2.waitingRight = true;
            }
        }
        if (n == 119) {
            if (Pacman.p.checkUp()) {
                Pacman.p.dir = 'u';
            }
            else {
                Pacman.p.waitingUp = true;
            }
        }
        if (n == 115) {
            if (Pacman.p.checkDown()) {
                Pacman.p.dir = 'd';
            }
            else {
                Pacman.p.waitingDown = true;
            }
        }
        if (n == 97) {
            Pacman.editorSet = 'a';
            if (Pacman.p.checkLeft()) {
                Pacman.p.dir = 'l';
            }
            else {
                Pacman.p.waitingLeft = true;
            }
        }
        if (n == 100) {
            if (Pacman.p.checkRight()) {
                Pacman.p.dir = 'r';
            }
            else {
                Pacman.p.waitingRight = true;
            }
        }
        if (n == 109) {
            Pacman.editorSet = 'm';
        }
        if (!Pacman.ended) {
            if (n == 10 && Pacman.p2.ammo > 0) {
                Pacman.p2.shoot();
            }
            if (n == 39 && Pacman.p2.mines > 0) {
                Pacman.p2.mine();
            }
            if (n == 32 && Pacman.p.ammo > 0) {
                Pacman.p.shoot();
            }
            if (n == 98 && Pacman.p.mines > 0) {
                Pacman.p.mine();
            }
        }
        updateStatusBar();
        return super.keyDown(event, n);
    }
    
    public boolean keyUp(final Event event, final int n) {
        if (n == 1004) {
            Pacman.p2.waitingUp = false;
        }
        if (n == 1005) {
            Pacman.p2.waitingDown = false;
        }
        if (n == 1006) {
            Pacman.p2.waitingLeft = false;
        }
        if (n == 1007) {
            Pacman.p2.waitingRight = false;
        }
        if (n == 119) {
            Pacman.p.waitingUp = false;
        }
        if (n == 115) {
            Pacman.p.waitingDown = false;
        }
        if (n == 97) {
            if (Pacman.editorSet != 'm') {
                Pacman.editorSet = 'w';
            }
            Pacman.p.waitingLeft = false;
        }
        if (n == 100) {
            Pacman.p.waitingRight = false;
        }
        if (n == 109 && Pacman.editorSet != 'a') {
            Pacman.editorSet = 'w';
        }
        return super.keyDown(event, n);
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this.started) {
            this.started = true;
            this.repaint();
            Pacman.p.start();
            Pacman.p2.start();
            this.ct.start();
            Pacman.level = 1;
        }
        if (Pacman.mapEditorOn) {
            if (Pacman.editorSet == 'w') {
                final int n3 = n / 25;
                final int n4 = n2 / 25;
                if ((n3 != 1 || n4 != 1) && (n3 != 15 || n4 != 14)) {
                    Pacman.customArray[n3][n4] = !Pacman.customArray[n3][n4];
                    this.repaint();
                }
            }
            else {
                final int n5 = n / 25;
                final int n6 = n2 / 25;
                if ((n5 != 1 || n6 != 1) && (n5 != 15 || n6 != 14)) {
                    if (Pacman.foodCustomArray[n5][n6] != Pacman.editorSet) {
                        Pacman.foodCustomArray[n5][n6] = Pacman.editorSet;
                    }
                    else {
                        Pacman.foodCustomArray[n5][n6] = ' ';
                    }
                    this.repaint();
                }
            }
        }
        return super.mouseDown(event, n, n2);
    }
    
    public static void updateStatusBar() {
        Pacman.gfx.setColor(Color.white);
        Pacman.gfx.fillRect(0, 400, 425, 40);
        Pacman.gfx.setColor(Color.black);
        Pacman.gfx.drawString("P1: ammo=" + Pacman.p.ammo + " mines=" + Pacman.p.mines + " health=" + Pacman.p.health, 0, 410);
        Pacman.gfx.drawString("P2: ammo=" + Pacman.p2.ammo + " mines=" + Pacman.p2.mines + " health=" + Pacman.p2.health, 225, 410);
        Pacman.gfx.setColor(Color.red);
        Pacman.gfx.drawLine(10, 412, 70, 412);
        Pacman.gfx.drawLine(10, 419, 70, 419);
        Pacman.gfx.drawLine(355, 412, 415, 412);
        Pacman.gfx.drawLine(355, 419, 415, 419);
        Pacman.gfx.drawLine(70, 412, 70, 419);
        Pacman.gfx.drawLine(415, 412, 415, 419);
        Pacman.gfx.fillRect(10, 412, Pacman.p.health * 2, 7);
        Pacman.gfx.fillRect(355, 412, Pacman.p2.health * 2, 7);
    }
    
    public void initLevel1() {
        this.w = new Wall(0, 0, 425, false);
        Pacman.level1.addElement(this.w);
        this.w = new Wall(0, 375, 425, false);
        Pacman.level1.addElement(this.w);
        this.w = new Wall(0, 25, 150, true);
        Pacman.level1.addElement(this.w);
        this.w = new Wall(0, 200, 175, true);
        Pacman.level1.addElement(this.w);
        this.w = new Wall(400, 25, 150, true);
        Pacman.level1.addElement(this.w);
        this.w = new Wall(400, 200, 175, true);
        Pacman.level1.addElement(this.w);
        this.w = new Wall(100, 275, 50, false);
        Pacman.level1.addElement(this.w);
        this.w = new Wall(175, 275, 75, false);
        Pacman.level1.addElement(this.w);
        this.w = new Wall(275, 275, 50, false);
        Pacman.level1.addElement(this.w);
        this.w = new Wall(50, 325, 50, false);
        Pacman.level1.addElement(this.w);
        this.w = new Wall(325, 325, 50, false);
        Pacman.level1.addElement(this.w);
        this.w = new Wall(125, 300, 50, true);
        Pacman.level1.addElement(this.w);
        this.w = new Wall(275, 300, 50, true);
        Pacman.level1.addElement(this.w);
        this.w = new Wall(150, 325, 25, false);
        Pacman.level1.addElement(this.w);
        this.w = new Wall(250, 325, 25, false);
        Pacman.level1.addElement(this.w);
        this.w = new Wall(200, 325, 50, true);
        Pacman.level1.addElement(this.w);
        this.w = new Wall(50, 200, 50, true);
        Pacman.level1.addElement(this.w);
        this.w = new Wall(25, 275, 50, false);
        Pacman.level1.addElement(this.w);
        this.w = new Wall(350, 200, 50, true);
        Pacman.level1.addElement(this.w);
        this.w = new Wall(350, 275, 50, false);
        Pacman.level1.addElement(this.w);
        this.w = new Wall(50, 100, 75, true);
        Pacman.level1.addElement(this.w);
        this.w = new Wall(50, 50, 50, false);
        Pacman.level1.addElement(this.w);
        this.w = new Wall(350, 100, 75, true);
        Pacman.level1.addElement(this.w);
        this.w = new Wall(325, 50, 50, false);
        Pacman.level1.addElement(this.w);
        this.w = new Wall(100, 225, 225, false);
        Pacman.level1.addElement(this.w);
        this.w = new Wall(100, 100, 100, false);
        Pacman.level1.addElement(this.w);
        this.w = new Wall(225, 100, 100, false);
        Pacman.level1.addElement(this.w);
        this.w = new Wall(100, 150, 75, true);
        Pacman.level1.addElement(this.w);
        this.w = new Wall(125, 50, 50, true);
        Pacman.level1.addElement(this.w);
        this.w = new Wall(150, 125, 75, true);
        Pacman.level1.addElement(this.w);
        this.w = new Wall(200, 150, 125, true);
        Pacman.level1.addElement(this.w);
        this.w = new Wall(250, 125, 75, true);
        Pacman.level1.addElement(this.w);
        this.w = new Wall(275, 50, 50, true);
        Pacman.level1.addElement(this.w);
        this.w = new Wall(300, 150, 75, true);
        Pacman.level1.addElement(this.w);
        this.w = new Wall(175, 50, 75, false);
        Pacman.level1.addElement(this.w);
        this.w = new Wall(200, 25, 25, true);
        Pacman.level1.addElement(this.w);
    }
    
    public void initLevel2() {
        this.w = new Wall(0, 0, 175, false);
        Pacman.level2.addElement(this.w);
        this.w = new Wall(250, 0, 175, false);
        Pacman.level2.addElement(this.w);
        this.w = new Wall(0, 0, 175, true);
        Pacman.level2.addElement(this.w);
        this.w = new Wall(0, 225, 175, true);
        Pacman.level2.addElement(this.w);
        this.w = new Wall(0, 375, 175, false);
        Pacman.level2.addElement(this.w);
        this.w = new Wall(250, 375, 175, false);
        Pacman.level2.addElement(this.w);
        this.w = new Wall(400, 0, 175, true);
        Pacman.level2.addElement(this.w);
        this.w = new Wall(400, 225, 175, true);
        Pacman.level2.addElement(this.w);
        this.w = new Wall(50, 50, 325, false);
        Pacman.level2.addElement(this.w);
        this.w = new Wall(50, 325, 325, false);
        Pacman.level2.addElement(this.w);
        this.w = new Wall(75, 75, 100, true);
        Pacman.level2.addElement(this.w);
        this.w = new Wall(75, 225, 100, true);
        Pacman.level2.addElement(this.w);
        this.w = new Wall(325, 75, 100, true);
        Pacman.level2.addElement(this.w);
        this.w = new Wall(325, 225, 100, true);
        Pacman.level2.addElement(this.w);
        this.w = new Wall(325, 225, 75, true);
        Pacman.level2.addElement(this.w);
        this.w = new Wall(150, 125, 150, true);
        Pacman.level2.addElement(this.w);
        this.w = new Wall(175, 125, 25, true);
        Pacman.level2.addElement(this.w);
        this.w = new Wall(175, 250, 25, true);
        Pacman.level2.addElement(this.w);
        this.w = new Wall(225, 125, 25, true);
        Pacman.level2.addElement(this.w);
        this.w = new Wall(225, 250, 25, true);
        Pacman.level2.addElement(this.w);
        this.w = new Wall(25, 100, 25, true);
        Pacman.level2.addElement(this.w);
        this.w = new Wall(25, 275, 25, true);
        Pacman.level2.addElement(this.w);
        this.w = new Wall(375, 100, 25, true);
        Pacman.level2.addElement(this.w);
        this.w = new Wall(375, 275, 25, true);
        Pacman.level2.addElement(this.w);
        this.w = new Wall(250, 125, 150, true);
        Pacman.level2.addElement(this.w);
    }
    
    public void initLevel3() {
        this.w = new Wall(0, 0, 100, false);
        Pacman.level3.addElement(this.w);
        this.w = new Wall(325, 375, 100, false);
        Pacman.level3.addElement(this.w);
        this.w = new Wall(50, 50, 50, false);
        Pacman.level3.addElement(this.w);
        this.w = new Wall(50, 75, 50, false);
        Pacman.level3.addElement(this.w);
        this.w = new Wall(325, 300, 50, false);
        Pacman.level3.addElement(this.w);
        this.w = new Wall(325, 325, 50, false);
        Pacman.level3.addElement(this.w);
        this.w = new Wall(150, 25, 50, false);
        Pacman.level3.addElement(this.w);
        this.w = new Wall(150, 50, 50, false);
        Pacman.level3.addElement(this.w);
        this.w = new Wall(225, 325, 50, false);
        Pacman.level3.addElement(this.w);
        this.w = new Wall(225, 350, 50, false);
        Pacman.level3.addElement(this.w);
        this.w = new Wall(0, 150, 100, true);
        Pacman.level3.addElement(this.w);
        this.w = new Wall(0, 325, 75, true);
        Pacman.level3.addElement(this.w);
        this.w = new Wall(25, 175, 25, true);
        Pacman.level3.addElement(this.w);
        this.w = new Wall(25, 350, 25, true);
        Pacman.level3.addElement(this.w);
        this.w = new Wall(25, 275, 25, true);
        Pacman.level3.addElement(this.w);
        this.w = new Wall(50, 125, 75, true);
        Pacman.level3.addElement(this.w);
        this.w = new Wall(50, 250, 75, true);
        Pacman.level3.addElement(this.w);
        this.w = new Wall(100, 125, 50, false);
        Pacman.level3.addElement(this.w);
        this.w = new Wall(100, 175, 100, true);
        Pacman.level3.addElement(this.w);
        this.w = new Wall(100, 325, 75, false);
        Pacman.level3.addElement(this.w);
        this.w = new Wall(125, 275, 75, false);
        Pacman.level3.addElement(this.w);
        this.w = new Wall(125, 100, 50, false);
        Pacman.level3.addElement(this.w);
        this.w = new Wall(150, 175, 50, true);
        Pacman.level3.addElement(this.w);
        this.w = new Wall(150, 350, 25, true);
        Pacman.level3.addElement(this.w);
        this.w = new Wall(175, 150, 50, true);
        Pacman.level3.addElement(this.w);
        this.w = new Wall(175, 250, 50, true);
        Pacman.level3.addElement(this.w);
        this.w = new Wall(225, 100, 75, false);
        Pacman.level3.addElement(this.w);
        this.w = new Wall(225, 125, 25, true);
        Pacman.level3.addElement(this.w);
        this.w = new Wall(225, 200, 50, true);
        Pacman.level3.addElement(this.w);
        this.w = new Wall(250, 25, 25, true);
        Pacman.level3.addElement(this.w);
        this.w = new Wall(250, 50, 75, false);
        Pacman.level3.addElement(this.w);
        this.w = new Wall(250, 175, 50, true);
        Pacman.level3.addElement(this.w);
        this.w = new Wall(250, 275, 50, false);
        Pacman.level3.addElement(this.w);
        this.w = new Wall(275, 250, 50, false);
        Pacman.level3.addElement(this.w);
        this.w = new Wall(300, 125, 100, true);
        Pacman.level3.addElement(this.w);
        this.w = new Wall(350, 75, 75, true);
        Pacman.level3.addElement(this.w);
        this.w = new Wall(350, 200, 75, true);
        Pacman.level3.addElement(this.w);
        this.w = new Wall(375, 100, 25, false);
        Pacman.level3.addElement(this.w);
        this.w = new Wall(375, 25, 25, true);
        Pacman.level3.addElement(this.w);
        this.w = new Wall(375, 200, 25, false);
        Pacman.level3.addElement(this.w);
        this.w = new Wall(400, 0, 75, true);
        Pacman.level3.addElement(this.w);
        this.w = new Wall(400, 150, 100, true);
        Pacman.level3.addElement(this.w);
    }
    
    public void initLevelCustom() {
        Pacman.levelCustom = new Vector();
        for (int i = 0; i < 17; ++i) {
            for (int j = 0; j < 16; ++j) {
                if (Pacman.customArray[i][j]) {
                    Pacman.levelCustom.addElement(new Wall(i * 25, j * 25, 25, true));
                }
            }
        }
    }
    
    public void initFood1() {
        this.f = new Food(25, 400, 'a', 1);
        Pacman.food1.addElement(this.f);
        this.f = new Food(425, 25, 'a', 1);
        Pacman.food1.addElement(this.f);
        this.f = new Food(0, 175, 'a', 1);
        Pacman.food1.addElement(this.f);
        this.f = new Food(400, 175, 'a', 1);
        Pacman.food1.addElement(this.f);
        this.f = new Food(200, 300, 'm', 1);
        Pacman.food1.addElement(this.f);
        this.f = new Food(200, 125, 'm', 1);
        Pacman.food1.addElement(this.f);
    }
    
    public void initFood2() {
        this.f = new Food(200, 125, 'a', 2);
        Pacman.food2.addElement(this.f);
        this.f = new Food(200, 250, 'a', 2);
        Pacman.food2.addElement(this.f);
        this.f = new Food(100, 75, 'a', 2);
        Pacman.food2.addElement(this.f);
        this.f = new Food(300, 75, 'm', 2);
        Pacman.food2.addElement(this.f);
        this.f = new Food(100, 300, 'm', 2);
        Pacman.food2.addElement(this.f);
        this.f = new Food(300, 300, 'a', 2);
        Pacman.food2.addElement(this.f);
    }
    
    public void initFood3() {
        this.f = new Food(200, 175, 'm', 3);
        Pacman.food3.addElement(this.f);
        this.f = new Food(25, 325, 'a', 3);
        Pacman.food3.addElement(this.f);
        this.f = new Food(375, 50, 'a', 3);
        Pacman.food3.addElement(this.f);
        this.f = new Food(25, 150, 'm', 3);
        Pacman.food3.addElement(this.f);
        this.f = new Food(375, 225, 'm', 3);
        Pacman.food3.addElement(this.f);
    }
    
    public void initFoodCustom() {
        Pacman.foodArray[3] = new Vector();
        for (int i = 0; i < 17; ++i) {
            for (int j = 0; j < 16; ++j) {
                if (Pacman.foodCustomArray[i][j] == 'a' || Pacman.foodCustomArray[i][j] == 'm') {
                    Pacman.foodArray[3].addElement(new Food(i * 25, j * 25, Pacman.foodCustomArray[i][j], 4));
                }
                else {
                    Pacman.foodCustomArray[i][j] = ' ';
                }
            }
        }
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.mapChoice) {
            for (int i = 0; i < Pacman.mineVector.size(); ++i) {
                ((mine)Pacman.mineVector.elementAt(i)).alive = false;
            }
            for (int j = 0; j < Pacman.sleepThreadVector.size(); ++j) {
                ((SleepThread)Pacman.sleepThreadVector.elementAt(j)).stop();
            }
            Pacman.mineVector = new Vector();
            final int selectedIndex = this.mapChoice.getSelectedIndex();
            Pacman.level = selectedIndex + 1;
            if (selectedIndex == 0) {
                this.initLevel1();
                Pacman.mapEditorOn = false;
                Pacman.level = 1;
                Wall.wallColor = Color.red;
                Wall.bricks = true;
            }
            else if (selectedIndex == 1) {
                this.initLevel2();
                Pacman.mapEditorOn = false;
                Pacman.level = 2;
                Wall.wallColor = Color.green;
                Wall.bricks = true;
            }
            else if (selectedIndex == 2) {
                this.initLevel3();
                Pacman.mapEditorOn = false;
                Pacman.level = 3;
                Wall.wallColor = Color.blue;
                Wall.bricks = false;
            }
            else if (selectedIndex == 3) {
                this.initLevelCustom();
                this.initFoodCustom();
                Pacman.mapEditorOn = false;
                Pacman.level = 4;
                Wall.wallColor = Color.magenta;
                Wall.bricks = false;
            }
            else if (selectedIndex == 4) {
                this.initLevelCustom();
                Pacman.mapEditorOn = true;
                Pacman.level = 5;
                Pacman.p.stop();
                Pacman.p2.stop();
                Pacman.gfx.setColor(Color.black);
                Pacman.gfx.fillRect(0, 0, 425, 400);
                Pacman.gfx.setColor(Color.red);
                Pacman.gfx.drawString("P1", 25, 35);
                Pacman.gfx.drawString("P2", 375, 360);
                Pacman.gfx.drawString("start", 25, 45);
                Pacman.gfx.drawString("start", 375, 370);
                Pacman.p.health = 30;
                Pacman.p2.health = 30;
                this.repaint();
            }
            if (selectedIndex < 4) {
                this.newGame();
            }
            else {
                Pacman.ended = true;
            }
        }
        if (event.target == this.instructions && this.instructions.getSelectedIndex() == 10) {
            try {
                this.getAppletContext().showDocument(new URL("http://members.aol.com/davkapl"));
            }
            catch (Exception ex) {}
        }
        if (event.target == this.bulletChoice) {
            if (this.bulletChoice.getSelectedIndex() == 1) {
                bullet.delay = 2;
            }
            else if (this.bulletChoice.getSelectedIndex() == 2) {
                bullet.delay = 3;
            }
            else if (this.bulletChoice.getSelectedIndex() == 3) {
                bullet.delay = 4;
            }
            else if (this.bulletChoice.getSelectedIndex() == 4) {
                bullet.delay = 6;
            }
        }
        return super.action(event, o);
    }
    
    public void newGame() {
        Pacman.ended = false;
        Pacman.p.stop();
        Pacman.p2.stop();
        Pacman.p = new Pacmen(25, 25, true);
        Pacman.p2 = new Pacmen(375, 350, false);
        Pacman.p.start();
        Pacman.p2.start();
        for (int i = 0; i < Pacman.bulletVector.size(); ++i) {
            ((bullet)Pacman.bulletVector.elementAt(i)).stop();
        }
        Pacman.bulletVector = new Vector();
        for (int j = 0; j < Pacman.foodArray[Pacman.level - 1].size(); ++j) {
            this.f = (Food)Pacman.foodArray[Pacman.level - 1].elementAt(j);
            this.f.eaten = false;
        }
        this.repaint();
        updateStatusBar();
    }
    
    public void destroy() {
        if (Pacman.p != null) {
            Pacman.p.stop();
        }
        if (Pacman.p2 != null) {
            Pacman.p2.stop();
        }
        for (int i = 0; i < Pacman.bulletVector.size(); ++i) {
            ((bullet)Pacman.bulletVector.elementAt(i)).stop();
        }
        for (int j = 0; j < Pacman.mineVector.size(); ++j) {
            final mine mine = Pacman.mineVector.elementAt(j);
        }
    }
    
    public static void endGame(final boolean b) {
        Pacman.ended = true;
        for (int i = 0; i < Pacman.bulletVector.size(); ++i) {
            ((bullet)Pacman.bulletVector.elementAt(i)).stop();
        }
        Pacman.gfx.setColor(Color.black);
        Pacman.gfx.fillRect(0, 0, 425, 400);
        Pacman.gfx.setFont(Pacman.endFont);
        Pacman.gfx.setColor(b ? Color.blue : Color.yellow);
        Pacman.gfx.drawString(b ? "Player Two is the WINNER!" : "Player One is the WINNER!", 10, 200);
        Pacman.gfx.setFont(Pacman.normalFont);
        playSound('w');
        Pacman.p.stop();
        Pacman.p2.stop();
    }
    
    public void playGunShot() {
        Pacman.gc.play();
    }
    
    public static void playSound(final char c) {
        if (c == 'g') {
            new Pacman().playGunShot();
            return;
        }
        if (c == 'w') {
            new Pacman().playWinnerClip();
            return;
        }
        new Pacman().playExplosion();
    }
    
    public void playWinnerClip() {
        Pacman.wc.play();
        try {
            Thread.sleep(1600L);
        }
        catch (Exception ex) {}
        Pacman.ac.play();
    }
    
    public void playExplosion() {
        Pacman.ec.play();
    }
    
    public Pacman() {
        this.started = false;
    }
    
    static {
        Pacman.p = null;
        Pacman.p2 = null;
        Pacman.wc = null;
        Pacman.gc = null;
        Pacman.ac = null;
        Pacman.ec = null;
    }
}
