import java.awt.Event;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Choice;
import java.awt.Button;
import java.applet.AudioClip;
import java.awt.Image;
import java.awt.event.MouseMotionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Xylophone extends Applet implements MouseMotionListener
{
    static Image xyloImage;
    static Image malletImage;
    static Image mdImage;
    static Image curImage;
    static Image trebleClefImage;
    static Image eighthImage;
    static Image eighthImage2;
    static AudioClip[] acArray;
    static int mx;
    static int my;
    static int curFloor;
    static int curCeiling;
    static int curNote;
    static SongSaver ss;
    static Button playButton;
    static Button stopButton;
    static Button deleteButton;
    static Button deleteLastButton;
    static Choice rhythmChoice;
    static Choice tempoChoice;
    static XyloListener ml;
    static boolean up;
    
    public void init() {
        this.setCursor(new Cursor(1));
        Xylophone.acArray = new AudioClip[15];
        Xylophone.xyloImage = this.getImage(this.getCodeBase(), "xylo.jpg");
        Xylophone.malletImage = this.getImage(this.getCodeBase(), "mallet.gif");
        Xylophone.mdImage = this.getImage(this.getCodeBase(), "mallet_down.gif");
        Xylophone.trebleClefImage = this.getImage(this.getCodeBase(), "treble_clef.gif");
        Xylophone.eighthImage = this.getImage(this.getCodeBase(), "eighth_note.gif");
        Xylophone.eighthImage2 = this.getImage(this.getCodeBase(), "eighth_note2.gif");
        Xylophone.curImage = Xylophone.malletImage;
        Xylophone.ss = new SongSaver();
        Xylophone.playButton = new Button("Play");
        Xylophone.stopButton = new Button("Stop");
        Xylophone.deleteButton = new Button("Delete All");
        Xylophone.deleteLastButton = new Button("Delete Last Note");
        (Xylophone.rhythmChoice = new Choice()).addItem("Quarter Note");
        Xylophone.rhythmChoice.addItem("Eighth Note");
        Xylophone.rhythmChoice.addItem("Sixteenth Note");
        (Xylophone.tempoChoice = new Choice()).addItem("100 beats per minute");
        Xylophone.tempoChoice.addItem("80 beats per minute");
        Xylophone.tempoChoice.addItem("60 beats per minute");
        this.add(Xylophone.playButton);
        this.add(Xylophone.stopButton);
        this.add(Xylophone.deleteLastButton);
        this.add(Xylophone.deleteButton);
        this.add(Xylophone.rhythmChoice);
        this.add(Xylophone.tempoChoice);
        try {
            Xylophone.acArray[0] = this.getAudioClip(this.getCodeBase(), "x0.au");
            Xylophone.acArray[1] = this.getAudioClip(this.getCodeBase(), "x1.au");
            Xylophone.acArray[2] = this.getAudioClip(this.getCodeBase(), "x2.au");
            Xylophone.acArray[3] = this.getAudioClip(this.getCodeBase(), "x3.au");
            Xylophone.acArray[4] = this.getAudioClip(this.getCodeBase(), "x4.au");
            Xylophone.acArray[5] = this.getAudioClip(this.getCodeBase(), "x5.au");
            Xylophone.acArray[6] = this.getAudioClip(this.getCodeBase(), "x6.au");
            Xylophone.acArray[7] = this.getAudioClip(this.getCodeBase(), "x7.au");
            Xylophone.acArray[8] = this.getAudioClip(this.getCodeBase(), "x8.au");
            Xylophone.acArray[9] = this.getAudioClip(this.getCodeBase(), "x9.au");
            Xylophone.acArray[10] = this.getAudioClip(this.getCodeBase(), "x10.au");
            Xylophone.acArray[11] = this.getAudioClip(this.getCodeBase(), "x11.au");
            Xylophone.acArray[12] = this.getAudioClip(this.getCodeBase(), "x12.au");
            Xylophone.acArray[13] = this.getAudioClip(this.getCodeBase(), "x13.au");
            Xylophone.acArray[14] = this.getAudioClip(this.getCodeBase(), "x14.au");
        }
        catch (Exception ex) {
            System.out.println("ALL FILES MUST BE IN THE SAME DIRECTORY");
        }
        this.addMouseMotionListener(this);
        this.addMouseListener(Xylophone.ml = new XyloListener(this));
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(Xylophone.xyloImage, 0, 0, this);
        graphics.drawImage(Xylophone.trebleClefImage, 0, 250, this);
        graphics.setColor(Color.black);
        graphics.drawLine(0, 258, 575, 258);
        graphics.drawLine(0, 268, 575, 268);
        graphics.drawLine(0, 278, 575, 278);
        graphics.drawLine(0, 288, 575, 288);
        graphics.drawLine(0, 298, 575, 298);
        for (int i = 0; i < 20; ++i) {
            if (Xylophone.ss.noteList[i] != 0) {
                graphics.fillOval(50 + 25 * i, 303 - 5 * (Xylophone.ss.noteList[i] - 1) + 1, 12, 9);
                if (Xylophone.ss.noteList[i] == 1) {
                    graphics.drawLine(50 + 25 * i - 3, 308, 50 + 25 * i + 15, 308);
                }
                else if (Xylophone.ss.noteList[i] > 12) {
                    graphics.drawLine(50 + 25 * i - 3, 248, 50 + 25 * i + 15, 248);
                    if (Xylophone.ss.noteList[i] == 15) {
                        graphics.drawLine(50 + 25 * i - 3, 238, 50 + 25 * i + 15, 238);
                    }
                }
                if (Xylophone.ss.noteList[i] < 7) {
                    graphics.drawLine(62 + 25 * i, 303 - 5 * (Xylophone.ss.noteList[i] - 1) + 5, 62 + 25 * i, 303 - 5 * (Xylophone.ss.noteList[i] - 1) - 28);
                    switch (Xylophone.ss.rhythmList[i]) {
                        case 2: {
                            graphics.drawImage(Xylophone.eighthImage, 62 + 25 * i, 303 - 5 * (Xylophone.ss.noteList[i] - 1) - 20, this);
                        }
                        case 1: {
                            graphics.drawImage(Xylophone.eighthImage, 62 + 25 * i, 303 - 5 * (Xylophone.ss.noteList[i] - 1) - 28, this);
                            break;
                        }
                    }
                }
                else {
                    graphics.drawLine(50 + 25 * i, 303 - 5 * (Xylophone.ss.noteList[i] - 1) + 5, 50 + 25 * i, 303 - 5 * (Xylophone.ss.noteList[i] - 1) + 38);
                    switch (Xylophone.ss.rhythmList[i]) {
                        case 2: {
                            graphics.drawImage(Xylophone.eighthImage2, 50 + 25 * i - 6, 303 - 5 * (Xylophone.ss.noteList[i] - 1) + 12, this);
                        }
                        case 1: {
                            graphics.drawImage(Xylophone.eighthImage2, 50 + 25 * i - 6, 303 - 5 * (Xylophone.ss.noteList[i] - 1) + 20, this);
                            break;
                        }
                    }
                }
            }
        }
        if (Xylophone.up) {
            graphics.drawImage(Xylophone.curImage, Xylophone.mx, Xylophone.my, this);
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        if (y > 233) {
            if (Xylophone.up) {
                Xylophone.up = false;
                this.setCursor(new Cursor(12));
                this.repaint();
            }
        }
        else if (!Xylophone.up) {
            Xylophone.up = true;
            this.setCursor(new Cursor(1));
        }
        int my;
        if (y - 15 - Xylophone.my > 0) {
            my = Xylophone.my;
        }
        else {
            my = y - 16;
        }
        if (y < 233) {
            if (x - Xylophone.mx > 18) {
                this.repaint(Xylophone.mx, my, x - Xylophone.mx + 20, 290);
            }
            else {
                this.repaint(x - 18, my, 54 + Xylophone.mx - x, 290);
            }
        }
        Xylophone.mx = x - 18;
        Xylophone.my = y - 15;
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        if (y > 233) {
            if (Xylophone.up) {
                Xylophone.up = false;
                this.setCursor(new Cursor(12));
                this.repaint();
            }
        }
        else if (!Xylophone.up) {
            Xylophone.up = true;
            this.setCursor(new Cursor(1));
        }
        int my;
        if (y - 15 - Xylophone.my > 0) {
            my = Xylophone.my;
        }
        else {
            my = y - 16;
        }
        if (y < 233) {
            if (x - Xylophone.mx > 18) {
                this.repaint(Xylophone.mx, my, x - Xylophone.mx + 20, 290);
            }
            else {
                this.repaint(x - 18, my, 54 + Xylophone.mx - x, 290);
            }
        }
        Xylophone.mx = x - 18;
        Xylophone.my = y - 15;
        if ((mouseEvent.getX() > Xylophone.curCeiling || mouseEvent.getX() < Xylophone.curFloor) && mouseEvent.getY() < 233) {
            Xylophone.ml.mousePressed(mouseEvent);
        }
    }
    
    public void destroy() {
        this.removeMouseMotionListener(this);
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target instanceof Button) {
            if (((Button)event.target).equals(Xylophone.playButton)) {
                if (Xylophone.playButton.getLabel().equals(new String("Play"))) {
                    Xylophone.playButton.setLabel("Pause");
                    Xylophone.ss.play();
                }
                else {
                    Xylophone.ss.pause();
                }
            }
            else if (((Button)event.target).equals(Xylophone.stopButton)) {
                Xylophone.ss.stop();
            }
            else if (((Button)event.target).equals(Xylophone.deleteButton)) {
                Xylophone.ss.stop();
                Xylophone.ss.erase();
                this.repaint(0, 233, 600, 150);
            }
            else if (((Button)event.target).equals(Xylophone.deleteLastButton)) {
                Xylophone.ss.stop();
                Xylophone.ss.eraseLast();
                this.repaint(0, 233, 600, 150);
            }
        }
        return super.action(event, o);
    }
    
    static {
        Xylophone.up = true;
    }
}
