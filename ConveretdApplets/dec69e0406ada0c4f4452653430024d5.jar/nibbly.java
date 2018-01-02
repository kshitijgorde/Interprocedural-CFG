import java.awt.event.ItemEvent;
import java.net.URL;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.event.ItemListener;
import java.awt.TextField;
import java.awt.Label;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.Button;
import java.applet.AudioClip;
import java.awt.Image;
import java.awt.Choice;
import java.awt.event.KeyListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public final class nibbly extends Applet implements KeyListener
{
    String userName;
    int id;
    final int FACTOR = 3;
    final int MAPS = 5;
    final int SKAERMFARVE = 255;
    final int SCREENX = 402;
    final int SCREENY = 350;
    final int OBJEKTSFARVE = 255;
    int highscore;
    boolean paintInfo;
    String text;
    boolean gameRunning;
    int score;
    Choice input;
    worm w;
    map m;
    int[][] currentMap;
    Image head;
    Image led;
    Image wall;
    Image wood;
    Image wall1;
    Image wood1;
    Image apple;
    static AudioClip click;
    AudioClip loose;
    AudioClip win;
    Button startGame;
    Panel inputPanel;
    Image workspace;
    Graphics offscreen;
    Font inputF;
    Font inputF2;
    Font adress;
    Font normalText;
    Panel panel2;
    Label label1;
    TextField textField1;
    Button button1;
    
    public nibbly() {
        this.userName = "";
        this.id = 100;
        this.highscore = 1;
        this.paintInfo = true;
        this.text = "Press START";
        this.gameRunning = false;
        this.score = 0;
        this.w = new worm();
        this.m = new map();
        this.currentMap = this.m.makeMap(1);
        this.startGame = new Button();
        this.inputPanel = new Panel();
        this.inputF = new Font("Helvetica", 0, 11);
        this.inputF2 = new Font("Helvetica", 1, 18);
        this.adress = new Font("Helvetica", 1, 13);
        this.normalText = new Font("Helvetica", 1, 12);
        this.panel2 = new Panel();
        this.label1 = new Label();
        this.textField1 = new TextField();
        this.button1 = new Button();
    }
    
    public void init() {
        final String parameter = this.getParameter("highScoreID");
        if (parameter != null) {
            this.id = Integer.parseInt(parameter);
        }
        final String parameter2 = this.getParameter("highScore");
        if (parameter2 != null) {
            this.highscore = Integer.parseInt(parameter2);
        }
        (this.input = new Choice()).addItemListener(new inputChoiseHandler());
        for (int i = 1; i < 6; ++i) {
            this.input.add("Level " + i);
        }
        nibbly.click = this.getAudioClip(this.getCodeBase(), "click.au");
        this.loose = this.getAudioClip(this.getCodeBase(), "loose.au");
        this.win = this.getAudioClip(this.getCodeBase(), "win.au");
        nibbly.click.play();
        final MediaTracker mediaTracker = new MediaTracker(this);
        this.showStatus("Loading Graphics");
        mediaTracker.addImage(this.head = this.getImage(this.getDocumentBase(), "head.gif"), 0);
        mediaTracker.addImage(this.led = this.getImage(this.getDocumentBase(), "led.gif"), 0);
        mediaTracker.addImage(this.wall = this.getImage(this.getDocumentBase(), "wall.gif"), 0);
        mediaTracker.addImage(this.wall1 = this.getImage(this.getDocumentBase(), "wall1.gif"), 0);
        mediaTracker.addImage(this.apple = this.getImage(this.getDocumentBase(), "apple.gif"), 0);
        mediaTracker.addImage(this.wood = this.getImage(this.getDocumentBase(), "wood.gif"), 0);
        mediaTracker.addImage(this.wood1 = this.getImage(this.getDocumentBase(), "wood1.gif"), 0);
        try {
            mediaTracker.waitForID(0);
        }
        catch (InterruptedException ex) {}
        this.setLayout(null);
        this.add(this.inputPanel, null);
        this.inputPanel.setBounds(138, 10, 150, 50);
        this.inputPanel.setLayout(null);
        this.inputPanel.setBackground(Color.black);
        this.inputPanel.add(this.startGame);
        this.inputPanel.add(this.input);
        this.startGame.setBounds(2, 0, 126, 20);
        this.startGame.setLabel("Start Game");
        this.startGame.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                nibbly.this.startGame_actionPerformed(actionEvent);
            }
        });
        this.input.setBounds(25, 25, 80, 20);
        this.workspace = this.createImage(402, 350);
        this.offscreen = this.workspace.getGraphics();
        this.addKeyListener(this);
        this.setLayout(null);
        this.setSize(426, 129);
        this.panel2.setLayout(null);
        this.add(this.panel2);
        this.panel2.setBackground(Color.lightGray);
        this.panel2.setBounds(100, 100, 200, 100);
        this.label1.setText("YOU MADE A HIGHSCORE!");
        this.panel2.add(this.label1);
        this.label1.setBounds(27, 10, 160, 24);
        this.panel2.add(this.textField1);
        this.textField1.setBounds(20, 40, 160, 20);
        this.button1.setLabel("Done");
        this.panel2.add(this.button1);
        this.button1.setBackground(Color.lightGray);
        this.button1.setBounds(37, 70, 125, 24);
        this.panel2.setVisible(false);
        this.button1.addActionListener(new SymAction());
        this.requestFocus();
        this.repaint();
    }
    
    public void startGame_actionPerformed(final ActionEvent actionEvent) {
        if (this.input.getSelectedItem().compareTo("Completed!") != 0) {
            this.w = new worm();
            this.m = new map();
            this.currentMap = this.m.makeMap(this.input.getSelectedIndex() + 1);
            this.gameRunning = true;
            this.requestFocus();
            this.startGame.setEnabled(false);
            this.input.setEnabled(false);
            this.repaint();
        }
        this.text = "Go Get'em !!";
    }
    
    public void paint(final Graphics graphics) {
        this.offscreen.setColor(Color.black);
        this.offscreen.fillRect(0, 0, 402, 350);
        this.offscreen.setFont(this.inputF);
        int n = 0;
        int n2 = 80;
        int n3 = 0;
        int n4 = 0;
        for (int i = 0; i < 220; ++i) {
            if (n >= 400) {
                n = 0;
                n2 += 20;
            }
            if (this.currentMap[n3][n4] == 0) {
                this.offscreen.drawImage(this.wall, n, n2, this);
            }
            else if (this.currentMap[n3][n4] == 5) {
                this.offscreen.drawImage(this.wall1, n, n2, this);
            }
            if (this.currentMap[n3][n4] == 6) {
                this.offscreen.drawImage(this.wood, n, n2, this);
            }
            else if (this.currentMap[n3][n4] == 7) {
                this.offscreen.drawImage(this.wood1, n, n2, this);
            }
            else if (this.currentMap[n3][n4] == 1) {
                this.offscreen.drawImage(this.apple, n, n2, this);
            }
            n += 20;
            if (n3 == 19) {
                n3 = 0;
                ++n4;
            }
            else {
                ++n3;
            }
        }
        this.offscreen.drawImage(this.head, this.w.getX() * 20, this.w.getY() * 20 + 80, this);
        this.offscreen.setColor(Color.black);
        this.offscreen.drawPolyline(new int[] { this.w.getX() * 20 + 6, this.w.getX() * 20 + 8, this.w.getX() * 20 + 11, this.w.getX() * 20 + 16 }, new int[] { this.w.getY() * 20 + 93, this.w.getY() * 20 + 95, this.w.getY() * 20 + 95, this.w.getY() * 20 + 93 }, 4);
        worm worm = this.w.getNext();
        this.offscreen.setColor(Color.green);
        while (worm != null) {
            this.offscreen.drawImage(this.led, worm.getX() * 20, worm.getY() * 20 + 80, this);
            worm = worm.getNext();
        }
        this.offscreen.setColor(Color.lightGray);
        this.offscreen.drawString("Apples left: " + this.m.left(), 10, 74);
        this.offscreen.drawLine(0, 79, 400, 79);
        this.offscreen.drawLine(0, 302, 400, 302);
        this.offscreen.setColor(Color.white);
        this.offscreen.drawString("" + this.text, 170, 74);
        this.offscreen.setFont(this.adress);
        this.offscreen.drawString("www.G5.dk", 320, 320);
        this.offscreen.drawString("" + this.userName, 10, 320);
        this.offscreen.drawString("" + this.score, 360, 74);
        if (!this.gameRunning && this.paintInfo) {
            this.offscreen.setColor(Color.black);
            this.offscreen.fillRect(81, 100, 240, 130);
            this.offscreen.setColor(Color.red);
            this.offscreen.drawRect(81, 103, 238, 124);
            this.offscreen.setFont(this.inputF2);
            this.offscreen.setColor(Color.white);
            this.offscreen.drawString("Nibbly", 170, 135);
            this.offscreen.setFont(this.normalText);
            this.offscreen.drawString("Remember this good old 64 game?", 103, 155);
            this.offscreen.drawString("Use arrow keys to control the worm", 100, 175);
            this.offscreen.setFont(this.inputF);
            this.offscreen.drawString("Coded by", 115, 213);
            this.offscreen.drawString("Karsten Mandrup Nielsen", 85, 224);
            this.offscreen.drawString("www.G5.dk", 255, 224);
        }
        graphics.drawImage(this.workspace, 0, 0, this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        if (this.gameRunning) {
            new StringBuffer().append("keyP: ").append(KeyEvent.getKeyText(keyEvent.getKeyCode())).toString();
            if (keyEvent.getKeyCode() == 38) {
                this.moveUp();
            }
            else if (keyEvent.getKeyCode() == 40) {
                this.moveDown();
            }
            else if (keyEvent.getKeyCode() == 37) {
                this.moveLeft();
            }
            else if (keyEvent.getKeyCode() == 39) {
                this.moveRight();
            }
            if (this.w.isDead()) {
                this.endGame();
            }
            if (this.m.left() == 0) {
                this.winGame();
            }
        }
        this.repaint();
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void moveUp() {
        boolean b = false;
        if (this.currentMap[this.w.getX()][this.w.getY() - 1] == 2) {
            if (this.w.getNext() != null) {
                if (this.w.getNext().getY() != this.w.getY() - 1) {
                    this.w.moveU(false);
                }
            }
            else {
                this.w.moveU(false);
            }
            b = true;
        }
        if (this.currentMap[this.w.getX()][this.w.getY() - 1] == 1 && !b) {
            if (this.w.getNext() != null) {
                if (this.w.getNext().getY() != this.w.getY() - 1) {
                    if (this.m.left() % 3 == 0) {
                        this.w.moveU(false);
                    }
                    else {
                        this.w.moveU(true);
                    }
                    this.currentMap[this.w.getX()][this.w.getY()] = 2;
                    this.m.eat();
                }
            }
            else {
                this.w.moveU(true);
                this.currentMap[this.w.getX()][this.w.getY()] = 2;
                this.m.eat();
            }
            nibbly.click.play();
            ++this.score;
        }
    }
    
    public void moveDown() {
        boolean b = false;
        if (this.currentMap[this.w.getX()][this.w.getY() + 1] == 2) {
            if (this.w.getNext() != null) {
                if (this.w.getNext().getY() != this.w.getY() + 1) {
                    this.w.moveD(false);
                }
            }
            else {
                this.w.moveD(false);
            }
            b = true;
        }
        if (this.currentMap[this.w.getX()][this.w.getY() + 1] == 1 && !b) {
            if (this.w.getNext() != null) {
                if (this.w.getNext().getY() != this.w.getY() + 1) {
                    if (this.m.left() % 3 == 0) {
                        this.w.moveD(false);
                    }
                    else {
                        this.w.moveD(true);
                    }
                    this.currentMap[this.w.getX()][this.w.getY()] = 2;
                    this.m.eat();
                }
            }
            else {
                this.w.moveD(true);
                this.currentMap[this.w.getX()][this.w.getY()] = 2;
                this.m.eat();
            }
            nibbly.click.play();
            ++this.score;
        }
    }
    
    public void moveLeft() {
        boolean b = false;
        if (this.currentMap[this.w.getX() - 1][this.w.getY()] == 2) {
            if (this.w.getNext() != null) {
                if (this.w.getNext().getX() != this.w.getX() - 1) {
                    this.w.moveL(false);
                }
            }
            else {
                this.w.moveL(false);
            }
            b = true;
        }
        if (this.currentMap[this.w.getX() - 1][this.w.getY()] == 1 && !b) {
            if (this.w.getNext() != null) {
                if (this.w.getNext().getX() != this.w.getX() - 1) {
                    if (this.m.left() % 3 == 0) {
                        this.w.moveL(false);
                    }
                    else {
                        this.w.moveL(true);
                    }
                    this.currentMap[this.w.getX()][this.w.getY()] = 2;
                    this.m.eat();
                }
            }
            else {
                if (this.m.left() % 3 == 0) {
                    this.w.moveL(false);
                }
                else {
                    this.w.moveL(true);
                }
                this.currentMap[this.w.getX()][this.w.getY()] = 2;
                this.m.eat();
            }
            nibbly.click.play();
            ++this.score;
        }
    }
    
    public void moveRight() {
        boolean b = false;
        if (this.currentMap[this.w.getX() + 1][this.w.getY()] == 2) {
            if (this.w.getNext() != null) {
                if (this.w.getNext().getX() != this.w.getX() + 1) {
                    this.w.moveR(false);
                }
            }
            else {
                this.w.moveR(false);
            }
            b = true;
        }
        if (this.currentMap[this.w.getX() + 1][this.w.getY()] == 1 && !b) {
            if (this.w.getNext() != null) {
                if (this.w.getNext().getX() != this.w.getX() + 1) {
                    if (this.m.left() % 3 == 0) {
                        this.w.moveR(false);
                    }
                    else {
                        this.w.moveR(true);
                    }
                    this.currentMap[this.w.getX()][this.w.getY()] = 2;
                    this.m.eat();
                }
            }
            else {
                if (this.m.left() % 3 == 0) {
                    this.w.moveR(false);
                }
                else {
                    this.w.moveR(true);
                }
                this.currentMap[this.w.getX()][this.w.getY()] = 2;
                this.m.eat();
            }
            nibbly.click.play();
            ++this.score;
        }
    }
    
    public void indtastNavn() {
        this.paintInfo = false;
        this.repaint();
        this.textField1.setColumns(15);
        this.startGame.setEnabled(false);
        this.input.setEnabled(false);
        this.panel2.setVisible(true);
        this.paint(this.offscreen);
        this.textField1.requestFocus();
    }
    
    void button1_ActionPerformed(final ActionEvent actionEvent) {
        this.userName = this.textField1.getText();
        if (this.userName.length() > 14) {
            this.userName = this.userName.substring(0, 14);
        }
        if (this.userName.length() == 0) {
            this.userName = "Mr. X";
        }
        this.panel2.setVisible(false);
        this.sendHighscore();
        this.gameRunning = false;
        this.paintInfo = true;
        this.repaint();
    }
    
    private void endGame() {
        this.text = "GAME OVER";
        this.loose.play();
        this.gameRunning = false;
        this.startGame.setEnabled(true);
        this.input.setEnabled(true);
        this.score = 0;
    }
    
    private void winGame() {
        this.win.play();
        this.w = new worm();
        this.input.remove(this.m.mapNumber() - 1);
        this.input.insert("Completed!", this.m.mapNumber() - 1);
        this.currentMap = this.m.makeMap(this.m.mapNumber());
        this.gameRunning = false;
        this.startGame.setEnabled(true);
        this.input.setEnabled(true);
        boolean b = true;
        for (int i = 0; i < 5; ++i) {
            if (this.input.getItem(i).compareTo("Completed!") != 0) {
                b = false;
            }
        }
        if (b) {
            this.text = "HIGHSCORE!";
            this.indtastNavn();
            this.loose.play();
            this.gameRunning = false;
        }
        this.repaint();
    }
    
    public void sendHighscore() {
        if (this.highscore == 1 && this.id < 200 && this.id > 99) {
            try {
                final String string = this.getDocumentBase().toString();
                this.getAppletContext().showDocument(new URL("http://www.G5.dk/highscore/score.asp" + "?n=" + this.userName + "&s=" + this.score + "&i=" + this.id + "&c=" + ((this.score * 3 + 3) * 2 + (this.id + 5)) + "&c2=" + (this.userName.length() + string.length()) + "&u=" + string), "_blank");
            }
            catch (Exception ex) {}
        }
    }
    
    class SymAction implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            if (actionEvent.getSource() == nibbly.this.button1) {
                nibbly.this.button1_ActionPerformed(actionEvent);
            }
        }
    }
    
    class inputChoiseHandler implements ItemListener
    {
        public void itemStateChanged(final ItemEvent itemEvent) {
        }
    }
}
