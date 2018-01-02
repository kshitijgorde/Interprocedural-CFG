import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.Random;
import java.awt.Button;
import java.awt.TextField;
import java.awt.TextArea;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Hag extends Applet implements ActionListener, KeyListener
{
    TextArea ta;
    TextField tf;
    Button button;
    static Random rnd;
    int l;
    int r;
    boolean win;
    String[] kw;
    String[] kw2;
    String[] r1;
    String[] r2;
    String[] r3;
    
    static {
        Hag.rnd = new Random();
    }
    
    public Hag() {
        this.ta = new TextArea("", 19, 80, 1);
        this.tf = new TextField(80);
        this.button = new Button("Another debate");
        this.l = 8;
        this.r = 0;
        this.win = false;
        this.kw = new String[] { " you", " agree ", " correct" };
        this.kw2 = new String[] { " i " };
        this.r1 = new String[] { "You're a shameless liar!", "I think I'm going to puke!", "Go stick your head in a bucket!", "If I wanted your opinion, I'd ask for it!", "Bullshit.", "Nazi!", "Communist!", "You don't really believe that, do you?", "Ha ha ha ha ha!  Get real.", "What a load of crap.", "Have you ever considered seeing a psychiatrist?", "How can you say that and keep a straight face?", "You're wrong!  Completely and totally wrong!", "I know slugs that are smarter than you.", "You're a jerk.", "You don't know what you're talking about.", "Don't insult my intelligence.", "Shut up.", "Is that the best you can come up with?", "Limp!  Limp!", "Where do you get that stuff?", "Don't give me that crap.", "You don't expect semi-intelligent people to believe that, do you?", "Give up.  You don't have a case.", "How can you be so ignorant?", "What an incredibly flimsy argument.", "Even an imbecile couldn't believe that.", "You have absolutely no credibility whatsoever.", "You don't know a thing about debating.", "Drop dead.", "You disgust me.", "You stink.", "Redneck!", "Pinko!", "Asshole.", "Screw you.", "You display an appalling ignorance of the topic you are debating." };
        this.r2 = new String[] { "I am infallibly correct and morally superior!", "Communist!", "Nazi!", "Drop dead.", "You don't have the brains of a mosquito.", "Stick to the facts!  Have you no understanding of proper debating?", "Screw you.", "You are full of it, aren't you?" };
        this.r3 = new String[] { "You are self-centered, egocentric scum.", "Don't think so highly of yourself.", "You are a dogmatic, brain-damaged moron.", "You are an egotistic, self-righteous jerk -- a complete asshole.", "Your feeble attempt at self-glorification is pathetic." };
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.reset();
        this.tf.requestFocus();
    }
    
    public void done() {
        this.ta.append("HAG: The debate is over.  I have won.");
        this.win = true;
        this.button.setEnabled(true);
    }
    
    public void handleInput() {
        ++this.r;
        if (this.win) {
            return;
        }
        final String text = this.tf.getText();
        this.ta.append("Human: " + text + "\n");
        this.tf.setText("");
        if (this.r == this.l) {
            this.done();
        }
        else if (this.r == 1) {
            this.ta.append("HAG: Fine.  You may begin.\n");
        }
        else {
            final StringBuffer sb = new StringBuffer(text.toLowerCase());
            for (int i = 0; i < sb.length(); ++i) {
                if (sb.charAt(i) > 'z' || sb.charAt(i) < 'a') {
                    sb.setCharAt(i, ' ');
                }
            }
            String s = sb.toString();
            if (!s.startsWith(" ")) {
                s = " " + s;
            }
            if (!s.endsWith(" ")) {
                s = String.valueOf(s) + " ";
            }
            String s2 = null;
            if (s.equals(" ") && this.r > 1) {
                this.ta.append("HAG: I assume your silence means that you concede.\n");
                this.done();
                return;
            }
            if (s2 == null) {
                int j = 0;
                boolean b = false;
                while (j < this.kw.length) {
                    if (s.indexOf(this.kw[j]) > -1) {
                        b = true;
                        break;
                    }
                    ++j;
                }
                if (b) {
                    s2 = this.r2[this.nextInt(this.r2.length)];
                }
            }
            if (s2 == null) {
                int k = 0;
                boolean b2 = false;
                while (k < this.kw2.length) {
                    if (s.indexOf(this.kw2[k]) > -1) {
                        b2 = true;
                        break;
                    }
                    ++k;
                }
                if (b2) {
                    s2 = this.r3[this.nextInt(this.r3.length)];
                }
            }
            if (s2 == null) {
                s2 = this.r1[this.nextInt(this.r1.length)];
            }
            this.ta.append("HAG: " + s2 + "\n");
        }
    }
    
    public void init() {
        this.ta.setEditable(false);
        this.add("Center", this.ta);
        this.tf.addKeyListener(this);
        this.add("South", this.tf);
        this.button.addActionListener(this);
        this.add("South", this.button);
        this.setSize(this.getPreferredSize());
        this.reset();
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 10) {
            keyEvent.consume();
            this.handleInput();
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public static void main(final String[] array) {
        final Frame frame = new Frame("Human Argument Generator");
        final Hag hag = new Hag();
        hag.init();
        frame.add("Center", hag);
        frame.pack();
        frame.show();
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        hag.start();
    }
    
    public int nextInt(final int n) {
        return (int)(Hag.rnd.nextDouble() * n);
    }
    
    public void reset() {
        this.ta.setText("Human Argument Generator, Copyright 1987,2000 Jim W. Lai\n\nHAG: What topic shall we discuss?\n");
        this.tf.setText("");
        this.win = false;
        this.r = 0;
        this.button.setEnabled(false);
    }
    
    public void start() {
        super.start();
        this.tf.requestFocus();
    }
}
