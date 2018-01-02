import java.net.URL;
import java.awt.Event;
import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
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
    static int level;
    static int score;
    static int lives;
    int size;
    boolean started;
    static Vector level1;
    static Vector level2;
    static Vector level3;
    static Vector levelCustom;
    static Graphics gfx;
    static boolean[][] customArray;
    static boolean mapEditorOn;
    Choice mapChoice;
    Choice instructions;
    Choice colorChoice;
    Choice editorInstructions;
    setPanel choicePanel;
    ColorThread ct;
    
    public void init() {
        Pacmen.g = this.getGraphics();
        Pacman.level1 = new Vector();
        Pacman.level2 = new Vector();
        Pacman.level3 = new Vector();
        Pacman.levelCustom = new Vector();
        Pacman.gfx = this.getGraphics();
        this.initLevel1();
        Pacman.level = 0;
        this.choicePanel = new setPanel(425, 58);
        this.setLayout(new BorderLayout(4, 4));
        this.add("South", this.choicePanel);
        (this.mapChoice = new Choice()).addItem("Map 1: The Fork");
        this.mapChoice.addItem("Map 2: Garden Maze");
        this.mapChoice.addItem("Map 3: Ode to Tetris");
        this.mapChoice.addItem("Map 4: Custom");
        this.mapChoice.addItem("Level Editor");
        this.choicePanel.add(this.mapChoice);
        (this.instructions = new Choice()).addItem("INSTRUCTIONS (pull down)");
        this.instructions.addItem("The object of the game is for the");
        this.instructions.addItem("IT pac-person to tag the other one.");
        this.instructions.addItem("Player one starts being IT, and they");
        this.instructions.addItem("use the W, A, S, and D keys to move.");
        this.instructions.addItem("Player two uses the arrow keys");
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
        this.editorInstructions.addItem("Select wall color using the");
        this.editorInstructions.addItem("pull-down.  Then select the");
        this.editorInstructions.addItem("'Custom' map and enjoy!");
        this.choicePanel.add(this.editorInstructions);
        Pacman.customArray = new boolean[17][16];
        this.ct = new ColorThread();
        Pacman.p = new Pacmen(375, 350, true, true);
        Pacman.p2 = new Pacmen(25, 25, false, false);
    }
    
    public void start() {
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
        if (Pacman.level == 1) {
            for (int i = 0; i < Pacman.level1.size(); ++i) {
                (this.w = (Wall)Pacman.level1.elementAt(i)).draw(graphics);
            }
        }
        else if (Pacman.level == 2) {
            for (int j = 0; j < Pacman.level2.size(); ++j) {
                (this.w = (Wall)Pacman.level2.elementAt(j)).draw(graphics);
            }
        }
        else if (Pacman.level == 3) {
            for (int k = 0; k < Pacman.level3.size(); ++k) {
                (this.w = (Wall)Pacman.level3.elementAt(k)).draw(graphics);
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
            for (int l = 0; l < Pacman.levelCustom.size(); ++l) {
                if (this.colorChoice.getSelectedIndex() == 1) {
                    Wall.wallColor = new Color((int)(Math.random() * 225.0) + 30, (int)(Math.random() * 195.0) + 60, (int)(Math.random() * 165.0) + 90);
                    Wall.bricks = false;
                }
                (this.w = (Wall)Pacman.levelCustom.elementAt(l)).draw(graphics);
            }
        }
        else if (Pacman.level == 5) {
            graphics.setColor(Color.red);
            for (int n = 0; n < 17; ++n) {
                for (int n2 = 0; n2 < 16; ++n2) {
                    if (Pacman.customArray[n][n2]) {
                        graphics.fillRect(25 * n, 25 * n2, 25, 25);
                    }
                }
            }
            graphics.drawString("P1", 25, 35);
            graphics.drawString("P2", 375, 360);
            graphics.drawString("start", 25, 45);
            graphics.drawString("start", 375, 370);
        }
        if (!this.started) {
            graphics.setColor(Color.green);
            graphics.drawString("Click to begin", 200, 200);
        }
        graphics.setColor(Color.white);
        graphics.fillRect(0, 400, 425, 40);
        graphics.setColor(Color.black);
        if (Pacman.level > 0) {
            updateStatusBar();
        }
    }
    
    public boolean keyDown(final Event event, final int n) {
        if (n == 1004) {
            if (Pacman.p.checkUp()) {
                Pacman.p.dir = 'u';
            }
            else {
                Pacman.p.waitingUp = true;
            }
        }
        if (n == 1005) {
            if (Pacman.p.checkDown()) {
                Pacman.p.dir = 'd';
            }
            else {
                Pacman.p.waitingDown = true;
            }
        }
        if (n == 1006) {
            if (Pacman.p.checkLeft()) {
                Pacman.p.dir = 'l';
            }
            else {
                Pacman.p.waitingLeft = true;
            }
        }
        if (n == 1007) {
            if (Pacman.p.checkRight()) {
                Pacman.p.dir = 'r';
            }
            else {
                Pacman.p.waitingRight = true;
            }
        }
        if (n == 119) {
            if (Pacman.p2.checkUp()) {
                Pacman.p2.dir = 'u';
            }
            else {
                Pacman.p2.waitingUp = true;
            }
        }
        if (n == 115) {
            if (Pacman.p2.checkDown()) {
                Pacman.p2.dir = 'd';
            }
            else {
                Pacman.p2.waitingDown = true;
            }
        }
        if (n == 97) {
            if (Pacman.p2.checkLeft()) {
                Pacman.p2.dir = 'l';
            }
            else {
                Pacman.p2.waitingLeft = true;
            }
        }
        if (n == 100) {
            if (Pacman.p2.checkRight()) {
                Pacman.p2.dir = 'r';
            }
            else {
                Pacman.p2.waitingRight = true;
            }
        }
        return super.keyDown(event, n);
    }
    
    public boolean keyUp(final Event event, final int n) {
        if (n == 1004) {
            Pacman.p.waitingUp = false;
        }
        if (n == 1005) {
            Pacman.p.waitingDown = false;
        }
        if (n == 1006) {
            Pacman.p.waitingLeft = false;
        }
        if (n == 1007) {
            Pacman.p.waitingRight = false;
        }
        if (n == 119) {
            Pacman.p2.waitingUp = false;
        }
        if (n == 115) {
            Pacman.p2.waitingDown = false;
        }
        if (n == 97) {
            Pacman.p2.waitingLeft = false;
        }
        if (n == 100) {
            Pacman.p2.waitingRight = false;
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
            final int n3 = n / 25;
            final int n4 = n2 / 25;
            if ((n3 != 1 || n4 != 1) && (n3 != 15 || n4 != 14)) {
                Pacman.customArray[n3][n4] = !Pacman.customArray[n3][n4];
                this.repaint();
            }
        }
        return super.mouseDown(event, n, n2);
    }
    
    public static void updateStatusBar() {
        Pacman.gfx.setColor(Color.white);
        Pacman.gfx.fillRect(0, 400, 400, 40);
        Pacman.gfx.setColor(Color.black);
        if (!Pacman.p.it) {
            Pacman.gfx.drawString("Player One: You're it!  Tag player two!", 100, 412);
            Pacman.gfx.drawString("Player Two: Escape!  Don't let player one get you!", 100, 423);
            return;
        }
        Pacman.gfx.drawString("Player One: Escape!  Don't let player two get you!", 100, 412);
        Pacman.gfx.drawString("Player Two: You're it!  Tag player one!", 100, 423);
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
        this.w = new Wall(75, 75, 75, true);
        Pacman.level2.addElement(this.w);
        this.w = new Wall(75, 225, 100, true);
        Pacman.level2.addElement(this.w);
        this.w = new Wall(325, 75, 75, true);
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
     