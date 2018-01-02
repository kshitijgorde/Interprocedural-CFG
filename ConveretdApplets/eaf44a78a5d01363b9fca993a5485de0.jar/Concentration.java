import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;
import java.awt.image.ImageObserver;
import java.awt.Cursor;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.Component;
import java.awt.TextField;
import java.awt.Choice;
import java.awt.Button;
import java.awt.Panel;
import java.util.Random;
import java.util.Vector;
import java.awt.Font;
import java.awt.MediaTracker;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Concentration extends Applet implements Runnable, MouseListener, ActionListener
{
    Thread me;
    Image offI;
    Graphics offG;
    MediaTracker tracker;
    final int width = 430;
    final int height = 470;
    final Font bigFont;
    final Font mediumFont;
    final Font smallFont;
    final String selectStatement = ", select a card.";
    int stage;
    boolean flip;
    int[][] scores;
    int[][] values;
    int turn;
    int card;
    int imageCount;
    String message;
    boolean[][] flipped;
    int[][] flipCards;
    Vector shuffleCards;
    Random random;
    Image[] cardImages;
    Panel controlP;
    Button startB;
    Button continueB;
    Button newGameB;
    Button startOverB;
    Choice numPlayersC;
    TextField[] names;
    
    public final void init() {
        this.offI = this.createImage(430, 470);
        this.offG = this.offI.getGraphics();
        this.tracker = new MediaTracker(this);
        this.stage = 0;
        this.flip = false;
        this.scores = new int[4][2];
        this.turn = 0;
        this.card = 0;
        this.message = null;
        this.flipped = new boolean[6][4];
        this.values = new int[6][4];
        int n = 0;
        for (int i = 0; i < this.values.length; ++i) {
            for (int j = 0; j < this.values[i].length; ++j) {
                if (++n > 11) {
                    n = 0;
                }
                this.values[i][j] = n;
            }
        }
        this.flipCards = new int[2][3];
        this.flipCards[0][0] = -1;
        this.flipCards[1][0] = -1;
        this.shuffleCards = new Vector();
        this.random = new Random();
        this.cardImages = new Image[12];
        this.setLayout(null);
        (this.startB = new Button("Start")).setFont(this.mediumFont);
        this.startB.setBackground(Color.green);
        this.startB.setForeground(Color.black);
        this.startB.setBounds(225, 250, 80, 40);
        this.startB.setActionCommand("s");
        this.startB.addActionListener(this);
        (this.numPlayersC = new Choice()).addItem("One");
        this.numPlayersC.addItem("Two");
        this.numPlayersC.addItem("Three");
        this.numPlayersC.addItem("Four");
        this.numPlayersC.setFont(this.mediumFont);
        this.numPlayersC.setForeground(Color.black);
        this.numPlayersC.setBackground(Color.white);
        this.numPlayersC.setBounds(125, 255, 80, 30);
        this.names = new TextField[4];
        for (int k = 0; k < 4; ++k) {
            (this.names[k] = new TextField()).setFont(this.mediumFont);
            this.names[k].setForeground(Color.black);
            this.names[k].setBackground(Color.white);
            this.names[k].setBounds(225, 250 + k * 30, 120, 30);
            this.names[k].setText("Player " + (k + 1));
            this.names[k].setColumns(20);
        }
        (this.continueB = new Button("Continue")).setFont(this.mediumFont);
        this.continueB.setForeground(Color.black);
        this.continueB.setBackground(Color.yellow);
        this.continueB.setBounds(155, 420, 120, 40);
        this.continueB.setActionCommand("c");
        this.continueB.addActionListener(this);
        (this.newGameB = new Button("New Game")).setFont(this.mediumFont);
        this.newGameB.setForeground(Color.white);
        this.newGameB.setBackground(Color.blue);
        this.newGameB.setBounds(300, 370, 120, 40);
        this.newGameB.setActionCommand("n");
        this.newGameB.addActionListener(this);
        (this.startOverB = new Button("Start Over")).setFont(this.mediumFont);
        this.startOverB.setForeground(Color.white);
        this.startOverB.setBackground(Color.blue);
        this.startOverB.setBounds(300, 420, 120, 40);
        this.startOverB.setActionCommand("o");
        this.startOverB.addActionListener(this);
        this.setCursor(Cursor.getPredefinedCursor(12));
        this.addMouseListener(this);
    }
    
    public final void start() {
        if (this.me == null) {
            (this.me = new Thread(this)).start();
        }
    }
    
    public final void drawLoadScreen() {
        this.offG.setColor(Color.green);
        this.offG.drawRect(10, 10, 410, 20);
        this.offG.fillRect(10, 10, 410 * this.imageCount / (this.cardImages.length + 1), 20);
        this.drawShadowText("Loading Images...(" + this.imageCount * 100 / (this.cardImages.length + 1) + "%)", 0, 25, 1, Color.white, Color.black, true, this.smallFont);
        this.drawShadowText("www.javaplayground.com", 0, 235, 0, Color.white, Color.black, true, this.mediumFont);
    }
    
    public final void drawTitleScreen() {
        this.drawShadowText("Concentration", 0, 100, 4, Color.red, Color.white, true, this.bigFont);
        this.drawShadowText("by John Morris", 0, 120, 1, Color.blue, Color.white, true, this.smallFont);
        this.drawShadowText("www.javaplayground.com", 0, 140, 1, Color.orange, Color.white, true, this.smallFont);
        if (this.startB.isShowing()) {
            this.drawShadowText("Select the number of players", 0, 200, 2, Color.white, Color.gray, true, this.mediumFont);
            this.drawShadowText("and press Start.", 0, 220, 2, Color.white, Color.gray, true, this.mediumFont);
            return;
        }
        this.drawShadowText("Enter each player's name", 0, 200, 2, Color.white, Color.gray, true, this.mediumFont);
        this.drawShadowText("and press Continue.", 0, 220, 2, Color.white, Color.gray, true, this.mediumFont);
        for (int i = 0; i < this.numPlayersC.getSelectedIndex() + 1; ++i) {
            this.drawShadowText("Player " + (i + 1), 100, 270 + i * 30, 2, Color.gray, Color.white, false, this.mediumFont);
        }
    }
    
    public final void drawGameBoard() {
        this.offG.setColor(Color.white);
        for (int i = 0; i < this.values.length; ++i) {
            for (int j = 0; j < this.values[i].length; ++j) {
                if (this.flipCards[this.card][0] == i && this.flipCards[this.card][1] == j) {
                    if (this.flipped[i][j]) {
                        this.offG.drawImage(this.cardImages[this.values[i][j]], i * 70 + 10, j * 90 + 10 + (80 - this.flipCards[this.card][2]) / 2, 60, this.flipCards[this.card][2], null);
                    }
                    else {
                        this.offG.fillRect(i * 70 + 10, j * 90 + 10 + (80 - this.flipCards[this.card][2]) / 2, 60, this.flipCards[this.card][2]);
                    }
                }
                else if (this.flipped[i][j]) {
                    this.offG.drawImage(this.cardImages[this.values[i][j]], i * 70 + 10, j * 90 + 10, null);
                }
                else {
                    this.offG.fillRect(i * 70 + 10, j * 90 + 10, 60, 80);
                }
            }
        }
        this.drawShadowText("Player", 10, 380, 1, Color.white, Color.lightGray, false, this.smallFont);
        this.drawShadowText("Matches", 100, 380, 1, Color.white, Color.lightGray, false, this.smallFont);
        this.drawShadowText("Tries", 160, 380, 1, Color.white, Color.lightGray, false, this.smallFont);
        this.drawShadowText("Percentage", 220, 380, 1, Color.white, Color.lightGray, false, this.smallFont);
        for (int k = 0; k < this.numPlayersC.getSelectedIndex() + 1; ++k) {
            Color color;
            if (k == this.turn) {
                color = Color.red;
            }
            else {
                color = Color.gray;
            }
            if (this.names[k].getText().length() >= 10) {
                this.drawShadowText(this.names[k].getText().substring(0, 7), 10, 400 + k * 20, 1, color, Color.lightGray, false, this.smallFont);
            }
            else {
                this.drawShadowText(this.names[k].getText(), 10, 400 + k * 20, 1, color, Color.lightGray, false, this.smallFont);
            }
            this.drawShadowText(Integer.toString(this.scores[k][0]), 100, 400 + k * 20, 1, color, Color.lightGray, false, this.smallFont);
            this.drawShadowText(Integer.toString(this.scores[k][1]), 160, 400 + k * 20, 1, color, Color.lightGray, false, this.smallFont);
            if (this.scores[k][1] > 0) {
                this.drawShadowText(String.valueOf(Integer.toString(this.scores[k][0] * 100 / this.scores[k][1])) + "%", 220, 400 + k * 20, 1, color, Color.lightGray, false, this.smallFont);
            }
            else {
                this.drawShadowText("0%", 220, 400 + k * 20, 1, color, Color.white, false, this.smallFont);
            }
        }
        if (this.message != null) {
            this.drawShadowText(this.message, 0, 185, 2, Color.red, Color.black, true, this.mediumFont);
        }
    }
    
    public final void drawShadowText(final String s, final int n, final int n2, final int n3, final Color color, final Color color2, final boolean b, final Font font) {
        this.offG.setFont(font);
        this.offG.setColor(color2);
        if (b) {
            this.offG.drawString(s, (430 - this.offG.getFontMetrics().stringWidth(s)) / 2 + n3, n2 + n3);
        }
        else {
            this.offG.drawString(s, n + n3, n2 + n3);
        }
        this.offG.setColor(color);
        if (b) {
            this.offG.drawString(s, (430 - this.offG.getFontMetrics().stringWidth(s)) / 2, n2);
            return;
        }
        this.offG.drawString(s, n, n2);
    }
    
    public final void paint(final Graphics graphics) {
        this.offG.setColor(Color.black);
        this.offG.fillRect(0, 0, 430, 470);
        switch (this.stage) {
            case 0: {
                this.drawLoadScreen();
                break;
            }
            case 1: {
                this.drawTitleScreen();
                break;
            }
            case 2: {
                this.drawGameBoard();
                break;
            }
        }
        graphics.drawImage(this.offI, 0, 0, null);
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final void animate(final int n) {
        try {
            this.repaint();
            Thread.sleep(n);
        }
        catch (Exception ex) {
            this.stop();
        }
    }
    
    public final void flipCard() {
        this.flipCards[this.card][2] = 80;
        while (this.flipCards[this.card][2] > 0) {
            this.animate(50);
            final int[] array = this.flipCards[this.card];
            final int n = 2;
            array[n] -= 10;
        }
        this.flipped[this.flipCards[this.card][0]][this.flipCards[this.card][1]] = !this.flipped[this.flipCards[this.card][0]][this.flipCards[this.card][1]];
        this.flipCards[this.card][2] = 0;
        while (this.flipCards[this.card][2] < 80) {
            this.animate(50);
            final int[] array2 = this.flipCards[this.card];
            final int n2 = 2;
            array2[n2] += 10;
        }
    }
    
    public final void run() {
        try {
            this.imageCount = 0;
            final Image image = this.getImage(this.getDocumentBase(), "images/cards.gif");
            this.tracker.addImage(image, this.imageCount);
            this.tracker.waitForAll();
            ++this.imageCount;
            this.repaint();
            for (int i = 0; i < 12; ++i) {
                this.cardImages[i] = this.createImage(new FilteredImageSource(image.getSource(), new CropImageFilter(i / 6 * 60, i % 6 * 80, 60, 80)));
                this.tracker.addImage(this.cardImages[i], this.imageCount);
                this.tracker.waitForAll();
                ++this.imageCount;
                this.repaint();
            }
            this.stage = 1;
            this.add(this.numPlayersC);
            this.add(this.startB);
            while (true) {
                if (this.stage == 2) {
                    if (this.flip) {
                        if (this.card == 0) {
                            final int[] array = this.scores[this.turn];
                            final int n = 1;
                            ++array[n];
                        }
                        this.message = null;
                        this.flipCard();
                        ++this.card;
                        if (this.card == 2) {
                            this.card = 0;
                            if (this.values[this.flipCards[0][0]][this.flipCards[0][1]] == this.values[this.flipCards[1][0]][this.flipCards[1][1]]) {
                                final int[] array2 = this.scores[this.turn];
                                final int n2 = 0;
                                ++array2[n2];
                                this.message = "Match!";
                                this.animate(2000);
                            }
                            else {
                                this.message = "No Match!";
                                this.animate(2000);
                                this.card = 0;
                                while (this.card < 2) {
                                    this.flipCard();
                                    ++this.card;
                                }
                                this.card = 0;
                                ++this.turn;
                                if (this.turn > this.numPlayersC.getSelectedIndex()) {
                                    this.turn = 0;
                                }
                            }
                        }
                        this.flip = false;
                        this.message = String.valueOf(this.names[this.turn].getText()) + ", select a card.";
                    }
                    else {
                        boolean b = true;
                        int n3 = 0;
                        int n4 = 0;
                        while (b && n3 < this.flipped.length) {
                            b = this.flipped[n3][n4];
                            if (++n4 >= this.flipped[n3].length) {
                                n4 = 0;
                                ++n3;
                            }
                        }
                        if (b) {
                            int n5 = 0;
                            int n6 = 0;
                            for (int j = 0; j < this.scores.length; ++j) {
                                if (this.scores[j][0] > n6) {
                                    n5 = j;
                                    n6 = this.scores[j][0];
                                }
                            }
                            this.message = String.valueOf(this.names[n5].getText()) + " wins!";
                            this.animate(3000);
                            this.card = 0;
                            this.flipCards[0][0] = 0;
                            while (this.flipCards[0][0] < this.values.length) {
                                this.flipCards[0][1] = 0;
                                while (this.flipCards[0][1] < this.values[this.flipCards[0][0]].length) {
                                    this.flipCard();
                                    final int[] array3 = this.flipCards[0];
                                    final int n7 = 1;
                                    ++array3[n7];
                                }
                                final int[] array4 = this.flipCards[0];
                                final int n8 = 0;
                                ++array4[n8];
                            }
                            this.reset();
                        }
                        else {
                            this.animate(500);
                        }
                    }
                }
                else {
                    this.animate(500);
                }
            }
        }
        catch (Exception ex) {
            this.stop();
        }
    }
    
    public final void stop() {
        if (this.me != null) {
            (this.me = null).stop();
        }
    }
    
    public final void reset() {
        for (int i = 0; i < this.values.length; ++i) {
            for (int j = 0; j < this.values[i].length; ++j) {
                this.shuffleCards.addElement(new Integer(this.values[i][j]));
            }
        }
        for (int k = 0; k < this.values.length; ++k) {
            for (int l = 0; l < this.values[k].length; ++l) {
                final int abs = Math.abs(this.random.nextInt() % this.shuffleCards.size());
                this.values[k][l] = (int)this.shuffleCards.elementAt(abs);
                this.shuffleCards.removeElementAt(abs);
                this.flipped[k][l] = false;
            }
        }
        for (int n = 0; n < this.scores.length; ++n) {
            this.scores[n][0] = 0;
            this.scores[n][1] = 0;
        }
        this.flip = false;
        this.card = 0;
        for (int n2 = 0; n2 < this.flipCards.length; ++n2) {
            this.flipCards[n2][0] = -1;
            this.flipCards[n2][1] = 0;
            this.flipCards[n2][2] = 0;
        }
        this.turn = 0;
        this.message = String.valueOf(this.names[this.turn].getText()) + ", select a card.";
        this.repaint();
    }
    
    public final void showStart() {
        this.remove(this.startOverB);
        this.remove(this.newGameB);
        this.add(this.numPlayersC);
        this.add(this.startB);
        this.stage = 1;
        this.repaint();
    }
    
    public final void showNameEntry() {
        this.remove(this.startB);
        this.remove(this.numPlayersC);
        for (int i = 0; i < this.numPlayersC.getSelectedIndex() + 1; ++i) {
            this.add(this.names[i]);
            this.names[i].setCaretPosition(0);
        }
        this.add(this.continueB);
    }
    
    public final void showGameBoard() {
        this.stage = 2;
        for (int i = 0; i < this.numPlayersC.getSelectedIndex() + 1; ++i) {
            this.remove(this.names[i]);
        }
        this.remove(this.continueB);
        this.add(this.startOverB);
        this.add(this.newGameB);
        this.reset();
        this.repaint();
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        switch (actionEvent.getActionCommand().charAt(0)) {
            case 's': {
                this.showNameEntry();
            }
            case 'c': {
                this.showGameBoard();
            }
            case 'n': {
                if (!this.flip) {
                    this.reset();
                    return;
                }
                break;
            }
            case 'o': {
                this.showStart();
            }
        }
    }
    
    public final void mousePressed(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        if (this.stage == 2 && !this.flip && x % 70 > 10 && y % 90 > 10 && x / 70 < this.values.length && y / 90 < this.values[0].length && !this.flipped[x / 70][y / 90]) {
            this.flipCards[this.card][0] = x / 70;
            this.flipCards[this.card][1] = y / 90;
            this.flip = true;
        }
    }
    
    public final void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public final void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public final void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public Concentration() {
        this.bigFont = new Font("Verdana", 1, 50);
        this.mediumFont = new Font("Verdana", 1, 20);
        this.smallFont = new Font("Verdana", 1, 12);
    }
}
