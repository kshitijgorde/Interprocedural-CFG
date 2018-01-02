import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.Insets;
import java.awt.Image;
import java.awt.Label;
import java.awt.Button;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class PictureGame extends Applet
{
    private boolean laidOut;
    private boolean WordUpdate;
    private int TotalLetters;
    private int TotalPictures;
    private int CurrentPicture;
    private int PreviousPicture;
    private int counter;
    private int offsettop;
    private int offsetleft;
    private Button cmdDone;
    private Button cmdReset;
    private Button cmdHelp;
    private Button[] cmdLetter;
    private Label lblWordTyped;
    private String[] ThingsName;
    private String msgWon;
    private String msgLost;
    private Image[] Things;
    
    public void init() {
        final PictureGame pictureGame = new PictureGame();
        final Insets insets = pictureGame.insets();
        this.TotalPictures = 35;
        this.CurrentPicture = this.Random();
        this.Things = new Image[this.TotalPictures];
        this.counter = 1;
        while (this.counter <= this.TotalPictures) {
            this.showStatus("Loading images/T" + this.counter + ".gif");
            this.Things[this.counter - 1] = this.getImage(this.getCodeBase(), "images/T" + this.counter + ".gif");
            ++this.counter;
        }
        (this.ThingsName = new String[this.TotalPictures])[0] = "PICTURE";
        this.ThingsName[1] = "BOOK";
        this.ThingsName[2] = "BOX";
        this.ThingsName[3] = "LOCK";
        this.ThingsName[4] = "SUN";
        this.ThingsName[5] = "CAMERA";
        this.ThingsName[6] = "DOG";
        this.ThingsName[7] = "DOOR";
        this.ThingsName[8] = "EARTH";
        this.ThingsName[9] = "EIGHT";
        this.ThingsName[10] = "EYE";
        this.ThingsName[11] = "FIRE";
        this.ThingsName[12] = "FIVE";
        this.ThingsName[13] = "FOUR";
        this.ThingsName[14] = "HAND";
        this.ThingsName[15] = "HOUSE";
        this.ThingsName[16] = "KEY";
        this.ThingsName[17] = "TV";
        this.ThingsName[18] = "TREE";
        this.ThingsName[19] = "LIGHTBULB";
        this.ThingsName[20] = "MAILBOX";
        this.ThingsName[21] = "NINE";
        this.ThingsName[22] = "ONE";
        this.ThingsName[23] = "PAINT";
        this.ThingsName[24] = "PHONE";
        this.ThingsName[25] = "SEVEN";
        this.ThingsName[26] = "SIX";
        this.ThingsName[27] = "SPIDER";
        this.ThingsName[28] = "STAR";
        this.ThingsName[29] = "COMPUTER";
        this.ThingsName[30] = "THREE";
        this.ThingsName[31] = "CASTLE";
        this.ThingsName[32] = "TWO";
        this.ThingsName[33] = "MONEY";
        this.ThingsName[34] = "NEWSPAPER";
        pictureGame.resize(400 + insets.left + insets.right, 300 + insets.top + insets.bottom);
        this.offsettop = insets.top;
        this.offsetleft = insets.left;
        pictureGame.show();
    }
    
    public PictureGame() {
        this.laidOut = false;
        this.WordUpdate = true;
        this.msgWon = "You Won!";
        this.msgLost = "Try again.";
        this.setLayout(null);
        this.TotalLetters = 26;
        this.cmdLetter = new Button[this.TotalLetters];
        (this.cmdDone = new Button("Done")).setFont(new Font("Helvetica", 1, 30));
        this.add(this.cmdDone);
        (this.cmdReset = new Button("Reset")).setFont(new Font("Helvetica", 1, 30));
        this.add(this.cmdReset);
        (this.cmdHelp = new Button("Help")).setFont(new Font("Helvetica", 1, 30));
        this.cmdHelp.disable();
        this.add(this.cmdHelp);
        (this.lblWordTyped = new Label()).setFont(new Font("Helvetica", 0, 20));
        this.add(this.lblWordTyped);
        this.setFont(new Font("Helvetica", 0, 20));
        this.cmdLetter[0] = new Button("A");
        this.cmdLetter[1] = new Button("B");
        this.cmdLetter[2] = new Button("C");
        this.cmdLetter[3] = new Button("D");
        this.cmdLetter[4] = new Button("E");
        this.cmdLetter[5] = new Button("F");
        this.cmdLetter[6] = new Button("G");
        this.cmdLetter[7] = new Button("H");
        this.cmdLetter[8] = new Button("I");
        this.cmdLetter[9] = new Button("J");
        this.cmdLetter[10] = new Button("K");
        this.cmdLetter[11] = new Button("L");
        this.cmdLetter[12] = new Button("M");
        this.cmdLetter[13] = new Button("N");
        this.cmdLetter[14] = new Button("O");
        this.cmdLetter[15] = new Button("P");
        this.cmdLetter[16] = new Button("Q");
        this.cmdLetter[17] = new Button("R");
        this.cmdLetter[18] = new Button("S");
        this.cmdLetter[19] = new Button("T");
        this.cmdLetter[20] = new Button("U");
        this.cmdLetter[21] = new Button("V");
        this.cmdLetter[22] = new Button("W");
        this.cmdLetter[23] = new Button("X");
        this.cmdLetter[24] = new Button("Y");
        this.cmdLetter[25] = new Button("Z");
        this.counter = 0;
        while (this.counter < this.TotalLetters) {
            this.add(this.cmdLetter[this.counter]);
            ++this.counter;
        }
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(Color.magenta);
        graphics.fill3DRect(this.offsetleft, this.offsettop, 600, 350, true);
        graphics.drawImage(this.Things[this.CurrentPicture], 275 + this.offsetleft, 210 + this.offsettop, this);
        if (!this.laidOut) {
            this.insets();
            this.insets();
            this.cmdReset.reshape(130 + this.offsetleft, 300 + this.offsettop, 100, 30);
            this.cmdDone.reshape(375 + this.offsetleft, 300 + this.offsettop, 100, 30);
            this.cmdHelp.reshape(250 + this.offsetleft, 300 + this.offsettop, 100, 30);
            this.counter = 0;
            while (this.counter < 8) {
                this.cmdLetter[this.counter].reshape(160 + this.counter * 35, 20, 35, 30);
                ++this.counter;
            }
            while (this.counter < 18) {
                this.cmdLetter[this.counter].reshape(130 + (this.counter - 8) * 35, 80, 35, 30);
                ++this.counter;
            }
            while (this.counter < this.TotalLetters) {
                this.cmdLetter[this.counter].reshape(160 + (this.counter - 18) * 35, 140, 35, 30);
                ++this.counter;
            }
            this.laidOut = true;
        }
        if (!this.WordUpdate) {
            graphics.setColor(Color.black);
            graphics.drawString(this.lblWordTyped.getText(), 250 + this.offsetleft, 200 + this.offsettop);
            graphics.setColor(Color.magenta);
        }
    }
    
    public boolean action(final Event event, final Object o) {
        if (this.lblWordTyped.getText() == this.msgWon || this.lblWordTyped.getText() == this.msgLost) {
            this.Reset();
        }
        if ("A".equals(o)) {
            this.lblWordTyped.setText(this.lblWordTyped.getText() + "A");
        }
        if ("B".equals(o)) {
            this.lblWordTyped.setText(this.lblWordTyped.getText() + "B");
        }
        if ("C".equals(o)) {
            this.lblWordTyped.setText(this.lblWordTyped.getText() + "C");
        }
        if ("D".equals(o)) {
            this.lblWordTyped.setText(this.lblWordTyped.getText() + "D");
        }
        if ("E".equals(o)) {
            this.lblWordTyped.setText(this.lblWordTyped.getText() + "E");
        }
        if ("F".equals(o)) {
            this.lblWordTyped.setText(this.lblWordTyped.getText() + "F");
        }
        if ("G".equals(o)) {
            this.lblWordTyped.setText(this.lblWordTyped.getText() + "G");
        }
        if ("H".equals(o)) {
            this.lblWordTyped.setText(this.lblWordTyped.getText() + "H");
        }
        if ("I".equals(o)) {
            this.lblWordTyped.setText(this.lblWordTyped.getText() + "I");
        }
        if ("J".equals(o)) {
            this.lblWordTyped.setText(this.lblWordTyped.getText() + "J");
        }
        if ("K".equals(o)) {
            this.lblWordTyped.setText(this.lblWordTyped.getText() + "K");
        }
        if ("L".equals(o)) {
            this.lblWordTyped.setText(this.lblWordTyped.getText() + "L");
        }
        if ("M".equals(o)) {
            this.lblWordTyped.setText(this.lblWordTyped.getText() + "M");
        }
        if ("N".equals(o)) {
            this.lblWordTyped.setText(this.lblWordTyped.getText() + "N");
        }
        if ("O".equals(o)) {
            this.lblWordTyped.setText(this.lblWordTyped.getText() + "O");
        }
        if ("P".equals(o)) {
            this.lblWordTyped.setText(this.lblWordTyped.getText() + "P");
        }
        if ("Q".equals(o)) {
            this.lblWordTyped.setText(this.lblWordTyped.getText() + "Q");
        }
        if ("R".equals(o)) {
            this.lblWordTyped.setText(this.lblWordTyped.getText() + "R");
        }
        if ("S".equals(o)) {
            this.lblWordTyped.setText(this.lblWordTyped.getText() + "S");
        }
        if ("T".equals(o)) {
            this.lblWordTyped.setText(this.lblWordTyped.getText() + "T");
        }
        if ("U".equals(o)) {
            this.lblWordTyped.setText(this.lblWordTyped.getText() + "U");
        }
        if ("V".equals(o)) {
            this.lblWordTyped.setText(this.lblWordTyped.getText() + "V");
        }
        if ("W".equals(o)) {
            this.lblWordTyped.setText(this.lblWordTyped.getText() + "W");
        }
        if ("X".equals(o)) {
            this.lblWordTyped.setText(this.lblWordTyped.getText() + "X");
        }
        if ("Y".equals(o)) {
            this.lblWordTyped.setText(this.lblWordTyped.getText() + "Y");
        }
        if ("Z".equals(o)) {
            this.lblWordTyped.setText(this.lblWordTyped.getText() + "Z");
        }
        if ("Reset".equals(o)) {
            this.Reset();
        }
        if ("Help".equals(o)) {
            this.lblWordTyped.setText(this.ThingsName[this.CurrentPicture]);
        }
        if ("Done".equals(o)) {
            if (!this.CheckWord()) {
                this.TooBad();
            }
            else {
                this.Celebrate();
                this.showStatus("Finding Next Picture.");
                this.CurrentPicture = this.Random();
                this.showStatus("Picture Found, Good Luck!");
            }
        }
        this.WordUpdate = false;
        this.repaint();
        return true;
    }
    
    boolean CheckWord() {
        return this.ThingsName[this.CurrentPicture].compareTo(this.lblWordTyped.getText()) == 0;
    }
    
    void Celebrate() {
        this.lblWordTyped.setText(this.msgWon);
    }
    
    void TooBad() {
        this.cmdHelp.enable();
        this.lblWordTyped.setText(this.msgLost);
    }
    
    void Reset() {
        this.lblWordTyped.setText("");
    }
    
    int Random() {
        int previousPicture = -1;
        while (previousPicture < 0 || previousPicture >= this.TotalPictures || previousPicture == this.PreviousPicture) {
            previousPicture = (int)(Math.random() * this.TotalPictures);
            this.showStatus("Looking for random number");
        }
        this.PreviousPicture = previousPicture;
        this.cmdHelp.disable();
        return previousPicture;
    }
}
