import java.awt.Event;
import java.net.URL;
import java.awt.image.ImageObserver;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Label;
import java.awt.Image;
import java.awt.Button;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public final class Concentration2ech extends Applet
{
    AppletUtil3 aut;
    AudioThread2 sound_solved;
    AudioThread2 sound_correct;
    AudioThread2 sound_incorrect;
    AudioThread2 sound_pick;
    boolean[] card_removed;
    Button solve;
    Button scramble;
    ConCanvas2 canvas;
    Image background;
    Image mask;
    Image facedown;
    Image[] cards;
    int cw;
    int ch;
    int card_count;
    int width;
    int height;
    int rows;
    int columns;
    int first_picked;
    int second_picked;
    int pairs;
    int attempts;
    int[] card_order;
    Label attempts_label;
    Label score;
    MediaTracker mt;
    String param;
    String delims;
    
    public void init() {
        this.aut = new AppletUtil3(this);
        this.mt = new MediaTracker(this);
        this.width = this.size().width;
        this.height = this.size().height;
        this.setLayout(new BorderLayout(0, 0));
        this.setBackground(this.aut.makeColor(this.getParameter("BGCOLOR"), Color.lightGray));
        this.setForeground(this.aut.makeColor(this.getParameter("FGCOLOR"), Color.black));
        this.setFont(this.aut.getFont());
        final Panel panel = new Panel();
        panel.setLayout(new GridLayout(0, 4, 0, 0));
        panel.add(this.attempts_label);
        panel.add(this.scramble);
        panel.add(this.solve);
        panel.add(this.score);
        this.rows = ((this.getParameter("ROWS") != null) ? Integer.parseInt(this.getParameter("ROWS")) : 4);
        this.columns = ((this.getParameter("COLUMNS") != null) ? Integer.parseInt(this.getParameter("COLUMNS")) : 4);
        this.card_count = this.rows * this.columns / 2;
        this.cards = new Image[this.card_count];
        this.card_removed = new boolean[this.card_count * 2];
        this.card_order = new int[this.card_count * 2];
        this.showStatus("Loading MASK image.");
        this.mask = this.aut.getImage(this.getParameter("IMAGE.MASK"));
        this.showStatus("Loading FACEDOWN image.");
        this.facedown = this.aut.getImage(this.getParameter("IMAGE.FACEDOWN"));
        try {
            for (int i = 0; i < this.card_count; ++i) {
                this.cards[i] = this.getImage(this.getDocumentBase(), this.getParameter("IMAGE." + i));
                if (this.cards[i] != null) {
                    this.mt.addImage(this.cards[i], i);
                }
                this.showStatus("Loading CARD #" + (i + 1) + " (out of " + this.card_count + ")");
                this.mt.waitForID(i);
            }
        }
        catch (Exception ex2) {}
        this.cw = this.facedown.getWidth(this);
        this.ch = this.facedown.getHeight(this);
        try {
            this.background = this.getImage(this.getDocumentBase(), this.getParameter("BACKGROUND"));
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        this.setupBoard();
        (this.canvas = new ConCanvas2(this, this.columns * this.cw, this.rows * this.ch, this.background, this.facedown, this.mask)).repaint();
        if (this.getParameter("AUTHOR").equals("Eric Harshbarger, http://www.ericharshbarger.org") && this.getParameter("COPYRIGHT").equals("Concentration applet, Copyright 1998, Eric Harshbarger")) {
            this.add("North", this.canvas);
            this.add("Center", panel);
            return;
        }
        System.out.println("AUTHOR & COPYRIGHT parameters are incorrect.");
    }
    
    public void start() {
        this.sound_pick = this.getSound(this.getParameter("SOUND.PICK"));
        this.sound_correct = this.getSound(this.getParameter("SOUND.CORRECT"));
        this.sound_incorrect = this.getSound(this.getParameter("SOUND.INCORRECT"));
        this.sound_solved = this.getSound(this.getParameter("SOUND.SOLVED"));
    }
    
    private AudioThread2 getSound(final String s) {
        AudioThread2 audioThread2 = null;
        try {
            audioThread2 = new AudioThread2(this, new URL(this.getDocumentBase(), s));
        }
        catch (Exception ex) {}
        return audioThread2;
    }
    
    public void setupBoard() {
        this.attempts = 0;
        this.attempts_label.setText("Attempts: " + this.attempts);
        this.pairs = 0;
        this.score.setText("Score: " + this.pairs + "/" + this.card_count);
        final int n = -1;
        this.second_picked = n;
        this.first_picked = n;
        final int[] array = new int[this.card_count * 2];
        for (int i = 0; i < array.length; ++i) {
            array[i] = i;
        }
        for (int j = 0; j < this.card_count * 2; ++j) {
            this.card_removed[j] = false;
            final int n2 = (int)(Math.random() * (this.card_count * 2 - j));
            this.card_order[j] = array[n2];
            array[n2] = array[this.card_count * 2 - j - 1];
        }
        if (this.canvas != null) {
            this.canvas.repaint();
        }
    }
    
    public void cardPicked(final int n, final int n2) {
        if (this.pairs < this.card_count) {
            final int first_picked = this.columns * (n2 / this.ch) + n / this.cw;
            if (this.second_picked > -1) {
                if (this.card_order[this.first_picked] % this.card_count == this.card_order[this.second_picked] % this.card_count) {
                    this.card_removed[this.first_picked] = (this.card_removed[this.second_picked] = true);
                    ++this.pairs;
                    this.score.setText("Score: " + this.pairs + "/" + this.card_count);
                    if (this.pairs == this.card_count) {
                        if (this.sound_solved != null) {
                            this.sound_solved.playAudio();
                        }
                        this.showStatus("GOOD JOB!!!... mouse click in the image to start over.");
                    }
                }
                this.second_picked = -1;
                this.first_picked = first_picked;
                if (this.sound_pick != null) {
                    this.sound_pick.playAudio();
                }
            }
            else if (this.first_picked > -1) {
                if (this.first_picked != first_picked) {
                    this.second_picked = first_picked;
                    if (this.card_order[this.first_picked] % this.card_count == this.card_order[this.second_picked] % this.card_count) {
                        if (this.sound_correct != null && this.pairs != this.card_count) {
                            this.sound_correct.playAudio();
                        }
                        else if (this.sound_pick != null) {
                            this.sound_pick.playAudio();
                        }
                    }
                    else if (this.sound_incorrect != null) {
                        this.sound_incorrect.playAudio();
                    }
                    else if (this.sound_pick != null) {
                        this.sound_pick.playAudio();
                    }
                    ++this.attempts;
                    this.attempts_label.setText("Attempts: " + this.attempts);
                }
            }
            else {
                this.first_picked = first_picked;
                if (this.sound_pick != null) {
                    this.sound_pick.playAudio();
                }
            }
            this.canvas.repaint();
            return;
        }
        this.setupBoard();
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 1001) {
            if (event.target == this.solve) {
                for (int i = 0; i < this.card_count * 2; ++i) {
                    this.card_removed[i] = true;
                }
                this.pairs = this.card_count;
                this.canvas.repaint();
            }
            else if (event.target == this.scramble) {
                this.setupBoard();
            }
        }
        return true;
    }
    
    public int getFirstPicked() {
        return this.first_picked;
    }
    
    public int getSecondPicked() {
        return this.second_picked;
    }
    
    public int getRows() {
        return this.rows;
    }
    
    public int getColumns() {
        return this.columns;
    }
    
    public int[] getCardOrder() {
        return this.card_order;
    }
    
    public boolean[] getRemoved() {
        return this.card_removed;
    }
    
    public Image getCard(final int n) {
        return this.cards[n];
    }
    
    public Concentration2ech() {
        this.solve = new Button("SOLVE");
        this.scramble = new Button("SCRAMBLE");
        this.first_picked = -1;
        this.second_picked = -1;
        this.attempts_label = new Label("Attempts: ------");
        this.score = new Label("Score: ---------", 2);
        this.delims = " \t\n\r,.|xX";
    }
}
