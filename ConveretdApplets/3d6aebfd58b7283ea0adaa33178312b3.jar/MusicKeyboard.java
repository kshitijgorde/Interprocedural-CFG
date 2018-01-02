import java.awt.Event;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.net.MalformedURLException;
import java.awt.Font;
import java.awt.Color;
import java.net.URL;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.util.Hashtable;
import java.awt.Image;
import java.awt.Label;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class MusicKeyboard extends Applet
{
    private Label readyLabel;
    private Image authorUrlImage;
    private Hashtable notes;
    private String keys;
    private Image keyboardImage;
    private final String instruction = "Click on the keyboard image to activate.";
    private final String myWebPage = "http://members.aol.com/djrant/programming.html";
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.keyboardImage, 24, 25, this);
        graphics.drawImage(this.authorUrlImage, 75, this.bounds().height - 21, this);
        super.paint(graphics);
    }
    
    private void play(final int n) {
        final Note note = this.notes.get(new Integer(n));
        if (note == null) {
            return;
        }
        if (!note.isPlaying()) {
            note.play();
        }
    }
    
    private void silence(final int n) {
        final Note note = this.notes.get(new Integer(n));
        if (note == null) {
            return;
        }
        note.stop();
    }
    
    private void silenceAll() {
        for (int length = this.keys.length(), i = 0; i < length; ++i) {
            this.silence(this.keys.charAt(i));
        }
    }
    
    private void loadSounds() {
        final URL codeBase = this.getCodeBase();
        for (int length = this.keys.length(), i = 0; i < length; ++i) {
            final char char1 = this.keys.charAt(i);
            final Note note = new Note(this.getAudioClip(codeBase, "sounds/" + this.getFileNameForKey(char1)));
            this.notes.put(new Integer(char1), note);
            note.play();
            note.stop();
        }
    }
    
    private Image makeAuthorUrlImage() {
        final Image image = this.createImage(300, 17);
        final Graphics graphics = image.getGraphics();
        graphics.setColor(this.getBackground());
        graphics.fillRect(0, 0, 300, 17);
        graphics.setColor(Color.blue);
        graphics.drawRect(0, 0, 299, 16);
        graphics.setFont(new Font("Courier", 0, 10));
        graphics.drawString("http://members.aol.com/djrant/programming.html", 5, 11);
        return image;
    }
    
    private Image makeKeyboard() {
        final Image image = this.createImage(400, 100);
        final Graphics graphics = image.getGraphics();
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, 400, 100);
        graphics.setColor(Color.white);
        for (int i = 0; i < 12; ++i) {
            graphics.fillRect(3 + i * 33, 2, 31, 96);
        }
        graphics.setColor(Color.black);
        for (int j = 0; j < 11; ++j) {
            switch (j) {
                case 0:
                case 1:
                case 3:
                case 4:
                case 5:
                case 7:
                case 8:
                case 10: {
                    graphics.fillRect(24 + j * 33, 2, 22, 65);
                    break;
                }
            }
        }
        graphics.setFont(new Font("Courier", 1, 24));
        int k = 0;
        int n = 0;
        while (k < 20) {
            switch (this.keys.charAt(k)) {
                case 'E':
                case 'I':
                case 'O':
                case 'P':
                case 'Q':
                case 'R':
                case 'T':
                case 'U':
                case 'W':
                case 'Y':
                case '[':
                case ']': {
                    graphics.setColor(Color.black);
                    graphics.drawString(this.keys.substring(k, k + 1), 9 + n * 33, 90);
                    ++n;
                    break;
                }
                case '0':
                case '2':
                case '3':
                case '5':
                case '6':
                case '7':
                case '9':
                case '=': {
                    graphics.setColor(Color.white);
                    graphics.drawString(this.keys.substring(k, k + 1), 28 + (n - 1) * 33, 60);
                    break;
                }
            }
            ++k;
        }
        return image;
    }
    
    private String getFileNameForKey(final int n) {
        switch (n) {
            case 81:
            case 113: {
                return "c2.au";
            }
            case 87:
            case 119: {
                return "d2.au";
            }
            case 69:
            case 101: {
                return "e2.au";
            }
            case 82:
            case 114: {
                return "f2.au";
            }
            case 84:
            case 116: {
                return "g2.au";
            }
            case 89:
            case 121: {
                return "a2.au";
            }
            case 85:
            case 117: {
                return "b2.au";
            }
            case 73:
            case 105: {
                return "c3.au";
            }
            case 79:
            case 111: {
                return "d3.au";
            }
            case 80:
            case 112: {
                return "e3.au";
            }
            case 91: {
                return "f3.au";
            }
            case 93: {
                return "g3.au";
            }
            case 50: {
                return "csharp2.au";
            }
            case 51: {
                return "dsharp2.au";
            }
            case 53: {
                return "fsharp2.au";
            }
            case 54: {
                return "gsharp2.au";
            }
            case 55: {
                return "asharp2.au";
            }
            case 57: {
                return "csharp3.au";
            }
            case 48: {
                return "dsharp3.au";
            }
            case 61: {
                return "fsharp3.au";
            }
            default: {
                return null;
            }
        }
    }
    
    private void visitAuthor() {
        try {
            this.getAppletContext().showDocument(new URL("http://members.aol.com/djrant/programming.html"));
        }
        catch (MalformedURLException ex) {}
    }
    
    public void init() {
        this.setLayout(new BorderLayout());
        this.keyboardImage = this.makeKeyboard();
        this.authorUrlImage = this.makeAuthorUrlImage();
        this.notes = new Hashtable(127);
        this.loadSounds();
        this.add("North", this.readyLabel = new Label("", 1));
        this.readyLabel.setText("Click on the keyboard image to activate.");
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 401) {
            this.play(event.key);
            return true;
        }
        if (event.id == 402) {
            this.silence(event.key);
            return true;
        }
        if (event.id == 501) {
            if (event.x >= 75 && event.x <= 75 + this.authorUrlImage.getWidth(this) && event.y >= this.bounds().height - 21 && event.y <= this.bounds().height - 21 + this.authorUrlImage.getHeight(this)) {
                this.visitAuthor();
                return true;
            }
        }
        else if (event.id == 1005) {
            this.silenceAll();
            return true;
        }
        return super.handleEvent(event);
    }
    
    public MusicKeyboard() {
        this.keys = "Q2W3ER5T6Y7UI9O0P[=]qwertyuiop";
    }
}
