import java.awt.Frame;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Event;
import java.awt.MediaTracker;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Button;
import java.awt.Font;
import java.awt.Label;
import java.awt.Image;
import java.applet.AudioClip;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class jaman extends Applet
{
    public static final String shortTitle = "Jaman";
    public static final String title = "JAMAN - version Beta";
    public static final String JamanMediaDirector = "JmAudio//";
    public static AudioClip[] audioClip;
    private Image jamanImg;
    private Label proLabel;
    private Label cprLabel;
    private Label urlLabel;
    private Label blankLabel;
    private Label blank2Label;
    private Font proFont;
    private Button hideButton;
    private JamanFrame jaman;
    private static final String[][] defaultMapStr;
    private static final String[] defaultMapName;
    private static final int MAX_MAP = 100;
    private static final int MAX_MAP_LINE = 50;
    private String[][] mapString;
    private String[] mapName;
    private int numMap;
    private static final char CHAR_COMMENT = ';';
    private static final char CHAR_MAP_NAME = '!';
    
    public jaman() {
        this.proLabel = new Label("JAMAN - version Beta");
        this.cprLabel = new Label("Copyright by Pham Hong Nguyen, 2000");
        this.urlLabel = new Label("http://www.geocities.com/SiliconValley/Grid/6544");
        this.blankLabel = new Label(" ");
        this.blank2Label = new Label(" ");
        this.proFont = new Font("SanSerif", 1, 14);
        this.hideButton = new Button("Play Game");
        this.jaman = new JamanFrame();
        this.mapString = new String[100][];
        this.mapName = new String[100];
        this.numMap = 0;
    }
    
    public void init() {
        if (!this.loadImages()) {
            System.exit(0);
        }
        this.getParameter();
        this.proLabel.setBackground(Color.white);
        this.proLabel.setFont(this.proFont);
        this.proLabel.setAlignment(1);
        this.cprLabel.setBackground(Color.white);
        this.cprLabel.setAlignment(1);
        this.urlLabel.setBackground(Color.white);
        this.urlLabel.setAlignment(1);
        this.setBackground(Color.white);
        final Panel panel = new Panel(new BorderLayout());
        panel.setBackground(Color.white);
        panel.add("North", this.proLabel);
        panel.add("Center", this.cprLabel);
        final Panel panel2 = new Panel(new GridLayout(1, 3));
        panel2.setBackground(Color.white);
        panel2.add(this.blankLabel);
        panel2.add(this.hideButton);
        panel2.add(this.blank2Label);
        this.setLayout(new BorderLayout());
        this.add("North", panel);
        this.add("Center", panel2);
        this.add("South", this.urlLabel);
        this.setSize(300, 120);
        this.loadAudioClip();
        Runner.setImage(this.jamanImg, 10, 5);
        this.jaman.init(this.hideButton);
    }
    
    public void destroy() {
        this.stopSound();
    }
    
    private boolean loadImages() {
        this.showStatus("Loading images...");
        final MediaTracker mediaTracker = new MediaTracker(this);
        mediaTracker.addImage(this.jamanImg = this.getImage(this.getCodeBase(), "jaman.gif"), 0);
        try {
            mediaTracker.waitForID(0);
        }
        catch (InterruptedException ex) {}
        if (mediaTracker.isErrorID(0)) {
            this.showStatus("Failed to load image");
            return false;
        }
        this.showStatus("Image loaded");
        return true;
    }
    
    public boolean action(final Event event, final Object o) {
        if (o.equals("Play Game") || o.equals("Hide Game")) {
            if (this.jaman.theGame.isHideGame()) {
                this.jaman.setVisible(true);
                this.jaman.theGame.setHideGame(false);
                this.hideButton.setLabel("Hide Game");
                this.jaman.theGame.setSound();
            }
            else {
                this.jaman.setVisible(false);
                this.jaman.theGame.setHideGame(true);
                this.hideButton.setLabel("Play Game");
            }
        }
        return true;
    }
    
    private void initMap() {
        for (int i = 0; i < 100; ++i) {
            this.mapString[i] = null;
            this.mapName[i] = null;
        }
        int j;
        for (j = 0; j < jaman.defaultMapStr.length; ++j) {
            this.mapString[j] = jaman.defaultMapStr[j];
            this.mapName[j] = jaman.defaultMapName[j];
        }
        this.numMap = j;
        this.readMap();
        this.jaman.theGame.mapStr = new String[this.numMap][];
        for (int k = 0; k < this.numMap; ++k) {
            this.jaman.theGame.mapStr[k] = this.mapString[k];
        }
        this.jaman.theGame.mapName = this.mapName;
    }
    
    private void readMap() {
        final String[] array = new String[50];
        for (int n = 0; n < 100 && (n == 0 || this.mapString[n - 1] != null); ++n) {
            final String string = "map" + (n + 1) + ".txt";
            int n2 = 0;
            URL url;
            try {
                url = new URL(this.getCodeBase() + string);
            }
            catch (MalformedURLException ex) {
                continue;
            }
            try {
                final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
                String line;
                while (n2 < 50 && (line = bufferedReader.readLine()) != null) {
                    if (line.length() > 0 && line.charAt(0) == ';') {
                        continue;
                    }
                    if (line.length() > 0 && line.charAt(0) == '!') {
                        this.mapName[n] = line.substring(1).trim();
                    }
                    else {
                        array[n2] = line;
                        ++n2;
                    }
                }
            }
            catch (IOException ex2) {
                continue;
            }
            if (n2 > 0) {
                final String[] array2 = new String[n2];
                for (int i = 0; i < n2; ++i) {
                    array2[i] = array[i];
                }
                this.mapString[n] = array2;
                this.numMap = Math.max(this.numMap, n + 1);
            }
        }
    }
    
    private boolean getOnOff(final String s, final boolean b) {
        final String parameter = this.getParameter(s);
        if (parameter == null || parameter.length() == 0) {
            return b;
        }
        return 0 == parameter.compareTo("1") || 0 == parameter.compareTo("on");
    }
    
    private int getNumber(final String s, final int n) {
        final String parameter = this.getParameter(s);
        if (parameter == null || parameter.length() == 0) {
            return n;
        }
        int intValue;
        try {
            intValue = Integer.decode(parameter);
        }
        catch (NumberFormatException ex) {
            return n;
        }
        return intValue;
    }
    
    private void getParameter() {
        this.jaman.theGame.superjaman = this.getOnOff("superjaman", this.jaman.theGame.superjaman);
        this.jaman.theGame.numJaman = this.getNumber("numberjaman", this.jaman.theGame.numJaman);
        this.jaman.theGame.numDevil = this.getNumber("numberdevil", this.jaman.theGame.numDevil);
        if (this.jaman.theGame.numDevil > 4) {
            this.jaman.theGame.numDevil = 4;
        }
        this.jaman.theGame.soundCheckbox.setState(this.getOnOff("sound", true));
        this.jaman.theGame.levelbymap = this.getOnOff("levelbymap", this.jaman.theGame.levelbymap);
        this.initMap();
    }
    
    private void loadAudioClip() {
        final String[] array = { "bkg.au", "nut.au", "org.au", "dev.au", "die.au", "vic.au", "los.au", "bon.au", "new.au" };
        jaman.audioClip = new AudioClip[array.length];
        for (int i = 0; i < array.length; ++i) {
            jaman.audioClip[i] = this.getAudioClip(this.getCodeBase(), "JmAudio//" + array[i]);
        }
    }
    
    private void stopSound() {
        for (int i = 0; i < jaman.audioClip.length; ++i) {
            if (jaman.audioClip[i] != null) {
                jaman.audioClip[i].stop();
            }
        }
    }
    
    public static void main(final String[] array) {
        final Frame frame = new Frame("jaman");
        final jaman jaman = new jaman();
        jaman.init();
        frame.add(jaman);
        frame.resize(300, 200);
        frame.show();
    }
    
    static {
        defaultMapStr = new String[][] { { "                *              ", " ++++  $+++     *     +++$++++ ", " +  +  +  +     *     +  +   + ", " +  ++++  +     *     +  +   + ", " +  +     +     .     +  +   + ", " +  +     +     .     +  +   + ", " +  ++++++++++++++++++++++++++ ", " +     +  +     .        +   + ", " +     +  +     .        +   + ", " +  ++++  +.........  ++++   + ", " +  +  +  +  .     .  +  +   + ", " ++++  ++++  . ### ...+  +++++ ", " +     .     . ####.     +     ", " +     @     % ####.     +     ", " ++++  ++++  . ### ...+  +++++ ", " +  +  +  +  .     .  +  +   + ", " +  ++++  +.........  ++++   + ", " +     +  +     .        +   + ", " +     +  +     .        +   + ", " +  ++++++++++++++++++++++++++ ", " +  +     +     .     +  +   + ", " +  +     +     .     +  +   + ", " +  ++++  +     *     +  +   + ", " +  +  +  +     *     +  +   + ", " ++++  $+++     *     +++++$++ ", "Traditional map *              " }, { "++$+++++++++++++++++@++++++++++++++++++", "      +                         +     +", "+++++++ +++++++++++++++++++++++ +++++++", "      + +          +          + +     +", "++$++++ + +++++++++++++++++++ + +++++++", "      + + +                 + + +     +", "+++++++ + + +++++++++++++++ + + +++++++", "      + + + +      $      + + + +     +", "++$++++ + + + +++++++++++ + + + +++++++", "      + + + + +         + + + + +     +", "+++++++ + + + + +++++++ + + + + +++++++", "      + + + + + +     + + + + + +     +", "++$++++ + + + + + ### + + + + + +++++++", "      +++ +$+ +$+ ### +$+ +$+ +++     +", "+++++++ + + + + + ### + + + + + +++++++", "      + + + + + +  #  + + + + + +     +", "++$++++ + + + + +++++++ + + + + +++++++", "      + + + + +         + + + + +     +", "+++++++ + + + +++++++++++ + + + +++++++", "      + + + +      $      + + + +     +", "++$++++ + + +++++++++++++++ + + +++++++", "      + + +                 + + +     +", "+++++++ + +++++++++++++++++++ + +++++++", "      + +          +          + +     +", "++$++++ +++++++++++++++++++++++ +++++++", "      +                         +     +", "+++++++++++++++++++++++++++++++++++++++", "      +                         +     +" } };
        defaultMapName = new String[] { "Tradition map", "The cirles" };
    }
}
