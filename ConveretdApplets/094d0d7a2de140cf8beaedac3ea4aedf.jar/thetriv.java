import java.awt.MediaTracker;
import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.net.URLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.BufferedInputStream;
import java.awt.Dimension;
import java.net.MalformedURLException;
import java.awt.Panel;
import java.applet.AudioClip;
import java.util.Vector;
import java.awt.Image;
import java.net.URL;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class thetriv extends Applet implements Animation
{
    public static boolean start;
    public static boolean trueReg;
    public static String currentQ;
    public static String introText;
    public static String copyRight;
    public static URL theURL;
    public static int qTotal;
    public static int i;
    public static int buttonMode;
    public static int qCorrect;
    public static int qIncorrect;
    public static int currentAnswer;
    public static int timerSpeed;
    public static int introInc;
    public static Image qPicture;
    public static Image bPicture;
    public static Image aPicture;
    public static Image bufImage;
    public static int w;
    public static int h;
    public static boolean inPlay;
    public static questionCanvas qPage;
    public static statusCanvas sPage;
    public static buttonCanvas bPage;
    public static Vector questionSet;
    public static AudioClip buzz;
    public static AudioClip ding;
    private Panel aPan;
    private int wid;
    private int hei;
    AnimationTimer timer;
    
    public void init() {
        Thread.currentThread().setPriority(10);
        this.timer.delay = thetriv.timerSpeed;
        final String parameter = this.getParameter("text");
        try {
            thetriv.theURL = new URL(this.getCodeBase(), parameter);
        }
        catch (MalformedURLException ex) {
            System.out.println("Bad URL: " + thetriv.theURL);
        }
        this.readQuestions();
        this.setupSounds();
        this.setupGraphics();
        reset();
        questionUser();
        final Dimension size = this.size();
        this.wid = size.width;
        this.hei = size.height;
        this.timer.start_animation();
    }
    
    public void animate() {
        if (thetriv.start) {
            if (!thetriv.trueReg && thetriv.qTotal > 5) {
                thetriv.qPage.repaint();
                this.timer.pause_animation();
                return;
            }
            if (thetriv.introInc <= 40) {
                thetriv.qPage.repaint();
                ++thetriv.introInc;
            }
        }
        else {
            thetriv.bPage.repaint();
        }
    }
    
    public void start() {
        this.timer.start_animation();
    }
    
    public void stop() {
        this.timer.pause_animation();
    }
    
    public void destroy() {
        this.timer.stop();
        reset();
        thetriv.i = 0;
        thetriv.currentQ = "";
        thetriv.qTotal = 0;
        thetriv.start = true;
        thetriv.introInc = 0;
        thetriv.questionSet.removeAllElements();
    }
    
    public void readQuestions() {
        new StringBuffer();
        try {
            final URLConnection openConnection = thetriv.theURL.openConnection();
            openConnection.connect();
            System.out.println("Connection opened...");
            final DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(openConnection.getInputStream()));
            System.out.println("Reading data...");
            int n = -1;
            String s = "";
            final String[] array = new String[4];
            String line;
            while ((line = dataInputStream.readLine()) != null) {
                if (++n == 0) {
                    s = line;
                }
                if (n > 0 && n < 5) {
                    array[n - 1] = line;
                }
                if (n == 5) {
                    thetriv.questionSet.addElement(new trivQuestion(s, array[0], array[1], array[2], array[3], Integer.parseInt(line)));
                    n = -1;
                    ++thetriv.qTotal;
                }
            }
            dataInputStream.close();
        }
        catch (IOException ex) {
            System.out.println("IO Error:" + ex.getMessage());
        }
    }
    
    public void setupSounds() {
        try {
            thetriv.buzz = this.getAudioClip(this.getCodeBase(), "buzz.au");
            thetriv.ding = this.getAudioClip(this.getCodeBase(), "ding.au");
        }
        catch (Exception ex) {}
        thetriv.buzz.play();
        thetriv.ding.play();
    }
    
    public void setupGraphics() {
        this.processImage("pal.jpg", "button.jpg", "pal2.jpg");
        thetriv.bufImage = this.createImage(thetriv.w, thetriv.h);
        (thetriv.qPage = new questionCanvas()).setSize(thetriv.w, thetriv.h);
        thetriv.qPage.addMouseListener(new boxCheckListener());
        thetriv.sPage = new statusCanvas();
        (thetriv.bPage = new buttonCanvas()).setSize(thetriv.w, thetriv.h / 6);
        thetriv.bPage.addMouseListener(new butListener());
        thetriv.bPage.addMouseMotionListener(new butMoveListener());
        (this.aPan = new Panel()).setLayout(new GridLayout(2, 1));
        this.aPan.add(thetriv.bPage);
        this.aPan.add(thetriv.sPage);
        this.add(thetriv.qPage, "North");
        this.add(this.aPan, "South");
        this.setBackground(Color.blue);
        this.aPan.setBackground(Color.blue);
    }
    
    public static void questionUser() {
        if (thetriv.i < thetriv.qTotal) {
            thetriv.currentQ = thetriv.questionSet.elementAt(thetriv.i).getQuestion();
            thetriv.qPage.repaint();
        }
    }
    
    public static void reset() {
        thetriv.i = 0;
        thetriv.qCorrect = 0;
        thetriv.qIncorrect = 0;
        thetriv.inPlay = true;
        thetriv.sPage.repaint();
        questionUser();
        thetriv.bPage.resetTimer();
    }
    
    public void processImage(final String s, final String s2, final String s3) {
        thetriv.qPicture = this.getImage(this.getDocumentBase(), s);
        thetriv.bPicture = this.getImage(this.getDocumentBase(), s2);
        thetriv.aPicture = this.getImage(this.getDocumentBase(), s3);
        final MediaTracker mediaTracker = new MediaTracker(this);
        mediaTracker.addImage(thetriv.qPicture, 0);
        mediaTracker.addImage(thetriv.bPicture, 1);
        mediaTracker.addImage(thetriv.aPicture, 2);
        try {
            mediaTracker.waitForAll();
            final boolean b = !mediaTracker.isErrorAny();
        }
        catch (InterruptedException ex) {}
    }
    
    public static void checkAnswer() {
        if (thetriv.i < thetriv.qTotal) {
            boolean b = false;
            if (thetriv.questionSet.elementAt(thetriv.i).getCorrectAnswer() == thetriv.currentAnswer) {
                b = true;
            }
            if (b) {
                ++thetriv.qCorrect;
                thetriv.ding.play();
                return;
            }
            ++thetriv.qIncorrect;
            thetriv.buzz.play();
        }
    }
    
    public thetriv() {
        this.timer = new AnimationTimer(this, 30);
    }
    
    static {
        thetriv.start = true;
        thetriv.introText = "JAKES TRIVIA";
        thetriv.copyRight = "(Copyright 1999 by Jake W. Holmes)";
        thetriv.timerSpeed = 50;
        thetriv.w = 310;
        thetriv.h = 120;
        thetriv.inPlay = true;
        thetriv.questionSet = new Vector(1, 1);
    }
}
