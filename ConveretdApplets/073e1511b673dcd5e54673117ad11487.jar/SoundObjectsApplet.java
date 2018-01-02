import symantec.itools.awt.Slider;
import java.io.InputStream;
import java.awt.Font;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Event;
import symantec.itools.awt.VerticalSlider;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Button;
import java.awt.Label;
import java.awt.CheckboxGroup;
import java.awt.Checkbox;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioDataStream;
import java.awt.Graphics;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class SoundObjectsApplet extends Applet implements Runnable
{
    private static final double SAMPLING_RATE = 8012.0;
    private static final int STRING = 0;
    private static final int CPLATEC = 1;
    private static final int BARCC = 2;
    private static final int BARCF = 3;
    private static final int CMEMBRANE = 4;
    private static final int PSEUDOSTRING = 5;
    private static final int FILESOB = 6;
    private static final int BANG = 0;
    private static final int SCRAPE = 1;
    private static final int PLUCK = 2;
    private static final int BANG2 = 3;
    private static final int WHITENOISE = 4;
    private static final int GAUSSIANNOISE = 5;
    private static final double MAXMAT = 10000.0;
    private static final double MINMAT = 1.0;
    private static final double MAXFREQ = 1000.0;
    private static final double MINFREQ = 1.0;
    private static final int MINGRIDPOINTS = 1;
    private static final int MAXGRIDPOINTS = 10000;
    private static final double MAXSAMPLETIME = 60.0;
    private static final double MINSAMPLETIME = 1.0E-4;
    private static final double MINPLUCKTIME = 1.0E-4;
    private static final double MINSCRAPEDUR = 0.01;
    private static final double MAXSCRAPEDUR = 1.0;
    private static final double MINMALLETHARDNESS = 10.0;
    private static final double MAXMALLETHARDNESS = 100000.0;
    private static final double MIN_SHORT_VOL = 500.0;
    private static final double MAX_SHORT_VOL = 20000.0;
    private int n_sobjects;
    private int n_samples;
    private int xl;
    private int xr;
    private int yt;
    private int yb;
    private Color barColor;
    private Color stringColor;
    private Graphics g;
    private double stringThinness;
    private double materialHardness;
    private double lowestFrequency;
    private double loudNess;
    private double overallDecayRate;
    private double maximumForce;
    private double malletHardness;
    private double soundLength;
    private double samplingRate;
    private double pluckTime;
    private double bangBangProb;
    private double relativeScrapeDuration;
    private double max_short_volume;
    private int initial_volume;
    private AudioDataStream audioDataStream;
    private AudioPlayer audioPlayer;
    private AudioData[] audioData;
    private int forceType;
    private int objectType;
    private int scrapeForceStatistics;
    private boolean isStopped;
    private Thread synthesizeThread;
    private LineSObject sob;
    private int centerx;
    private int centery;
    private int rimsize;
    private int radius;
    private Color plateColor;
    private Color cmembraneColor;
    private Color pseudoStringColor;
    Checkbox check1;
    CheckboxGroup group1;
    Checkbox check2;
    Checkbox check3;
    Checkbox check4;
    Label label1;
    Checkbox check6;
    CheckboxGroup group2;
    Checkbox check7;
    Label label2;
    Button button1;
    Button button2;
    TextField edit2;
    TextField edit3;
    Label label4;
    Label label5;
    Label label7;
    Label label8;
    TextArea edit7;
    Checkbox check8;
    Checkbox check9;
    Checkbox check10;
    Checkbox check11;
    Checkbox check5;
    TextField edit8;
    TextField edit6;
    Label label3;
    TextField edit1;
    TextField edit4;
    Label label6;
    TextField edit5;
    Label label9;
    VerticalSlider verticalSlider1;
    Label label10;
    
    void verticalSlider1_Action(final Event event) {
        ((Slider)this.verticalSlider1).setValue(((Slider)this.verticalSlider1).getValue());
        this.max_short_volume = -2166.6666666666665 * ((Slider)this.verticalSlider1).getValue() + 22166.666666666668;
        this.start();
    }
    
    public void run() {
        this.computeSounds();
    }
    
    private void computeSounds() {
        this.audioData = new AudioData[this.n_samples];
        this.audioPlayer = AudioPlayer.player;
        if (this.objectType == 0) {
            this.sob = new LineSObject(this.overallDecayRate, 1.0 / this.materialHardness, this.lowestFrequency, this.n_samples, this.loudNess, this.max_short_volume);
        }
        else if (this.objectType == 1) {
            this.sob = new CPlateCSObject(this.overallDecayRate, 1.0 / this.materialHardness, this.lowestFrequency, this.n_samples, this.loudNess, this.max_short_volume);
        }
        else if (this.objectType == 2) {
            this.sob = new BarCCSObject(this.overallDecayRate, 1.0 / this.materialHardness, this.lowestFrequency, this.n_samples, this.loudNess, this.max_short_volume);
        }
        else if (this.objectType == 3) {
            this.sob = new BarCFSObject(this.overallDecayRate, 1.0 / this.materialHardness, this.lowestFrequency, this.n_samples, this.loudNess, this.max_short_volume);
        }
        else if (this.objectType == 4) {
            this.sob = new CMembraneSObject(this.overallDecayRate, 1.0 / this.materialHardness, this.lowestFrequency, this.n_samples, this.loudNess, this.max_short_volume);
        }
        else if (this.objectType == 5) {
            this.sob = new PseudoStringSObject(this.overallDecayRate, 1.0 / this.materialHardness, this.lowestFrequency, this.n_samples, this.loudNess, this.max_short_volume);
        }
        else {
            System.out.println("computeSounds(): Error in objectType");
        }
        double[] array = new double[2];
        if (this.forceType == 0) {
            final BangForce bangForce = new BangForce(this.samplingRate, this.soundLength);
            bangForce.makeForce(this.maximumForce, 1.0 / this.malletHardness);
            array = bangForce.force;
        }
        else if (this.forceType == 1) {
            if (this.scrapeForceStatistics == 4) {
                final ScrapeForce scrapeForce = new ScrapeForce(this.samplingRate, this.soundLength);
                scrapeForce.makeForce(this.maximumForce, this.soundLength * this.relativeScrapeDuration);
                array = scrapeForce.force;
            }
            else if (this.scrapeForceStatistics == 5) {
                final GaussianScrapeForce gaussianScrapeForce = new GaussianScrapeForce(this.samplingRate, this.soundLength);
                gaussianScrapeForce.makeForce(this.maximumForce, this.soundLength * this.relativeScrapeDuration);
                array = gaussianScrapeForce.force;
            }
            else {
                System.out.println("computeSounds(): Error in scrapeForceStatistics");
            }
        }
        else if (this.forceType == 2) {
            final PluckForce pluckForce = new PluckForce(this.samplingRate, this.soundLength);
            pluckForce.makeForce(this.maximumForce, this.pluckTime);
            array = pluckForce.force;
        }
        else if (this.forceType == 3) {
            final Bang2Force bang2Force = new Bang2Force(this.samplingRate, this.soundLength);
            bang2Force.makeForce(this.maximumForce, 1.0 / this.malletHardness);
            array = bang2Force.force;
        }
        else {
            System.out.println("computeSounds(): Error in forceType");
        }
        for (int i = 0; i < this.n_samples; ++i) {
            this.audioData[i] = new AudioData(this.sob.writeSound(this.samplingRate, array, this.sob.centerOfSound(i), this.soundLength, 0.0));
            final double n = 100.0 * (i + 1) / this.n_samples;
            this.edit7.setText("");
            this.edit7.appendText("Computed " + n + "%\n");
        }
        this.edit7.setText("");
        this.edit7.appendText("Ready\n");
    }
    
    private void paintString(final Graphics graphics) {
        graphics.setColor(this.stringColor);
        graphics.fill3DRect(this.xl, this.yt, this.xr - this.xl, (int)((this.yb - this.yt) * this.stringThinness), true);
        graphics.setColor(Color.gray);
        final int n = this.yb - this.yt;
        graphics.fill3DRect(this.xl - 3 * n, this.yt - n, 3 * n, 3 * n, true);
        graphics.fill3DRect(this.xr, this.yt - n, 3 * n, 3 * n, true);
    }
    
    private void paintPseudoString(final Graphics graphics) {
        graphics.setColor(this.pseudoStringColor);
        graphics.fill3DRect(this.xl, this.yt, this.xr - this.xl, (int)((this.yb - this.yt) * this.stringThinness), true);
        graphics.setColor(Color.gray);
        final int n = this.yb - this.yt;
        graphics.fill3DRect(this.xl - 3 * n, this.yt - n, 3 * n, 3 * n, true);
        graphics.fill3DRect(this.xr, this.yt - n, 3 * n, 3 * n, true);
    }
    
    private void paintBarCC(final Graphics graphics) {
        graphics.setColor(this.barColor);
        graphics.fill3DRect(this.xl, this.yt, this.xr - this.xl, this.yb - this.yt, true);
        graphics.setColor(Color.gray);
        final int n = this.yb - this.yt;
        graphics.fill3DRect(this.xl - 3 * n, this.yt - n, 3 * n, 3 * n, true);
        graphics.fill3DRect(this.xr, this.yt - n, 3 * n, 3 * n, true);
    }
    
    private void paintBarCF(final Graphics graphics) {
        graphics.setColor(this.barColor);
        graphics.fill3DRect(this.xl, this.yt, this.xr - this.xl, this.yb - this.yt, true);
        graphics.setColor(Color.gray);
        final int n = this.yb - this.yt;
        graphics.fill3DRect(this.xl - 3 * n, this.yt - n, 3 * n, 3 * n, true);
    }
    
    private void paintCPlate(final Graphics graphics) {
        final int radius = this.radius;
        final int n = this.radius - this.rimsize;
        final int n2 = 2 * radius;
        final int n3 = 2 * radius;
        final int n4 = this.centerx - radius;
        final int n5 = this.centery - radius;
        graphics.setColor(Color.gray);
        graphics.fillOval(n4, n5, n2, n3);
        final int n6 = 2 * n;
        final int n7 = 2 * n;
        final int n8 = this.centerx - n;
        final int n9 = this.centery - n;
        graphics.setColor(this.plateColor);
        graphics.fillOval(n8, n9, n6, n7);
        graphics.setColor(Color.yellow);
        graphics.drawOval(n8, n9, n6, n7);
        graphics.setColor(Color.white);
        graphics.drawOval(n4, n5, n2, n3);
    }
    
    private void paintCMembrane(final Graphics graphics) {
        final int radius = this.radius;
        final int n = this.radius - this.rimsize;
        final int n2 = 2 * radius;
        final int n3 = 2 * radius;
        final int n4 = this.centerx - radius;
        final int n5 = this.centery - radius;
        graphics.setColor(Color.gray);
        graphics.fillOval(n4, n5, n2, n3);
        final int n6 = 2 * n;
        final int n7 = 2 * n;
        final int n8 = this.centerx - n;
        final int n9 = this.centery - n;
        graphics.setColor(this.cmembraneColor);
        graphics.fillOval(n8, n9, n6, n7);
        graphics.setColor(Color.yellow);
        graphics.drawOval(n8, n9, n6, n7);
        graphics.setColor(Color.white);
        graphics.drawOval(n4, n5, n2, n3);
    }
    
    public void paint(final Graphics graphics) {
        if (this.objectType == 0) {
            this.paintString(graphics);
            return;
        }
        if (this.objectType == 1) {
            this.paintCPlate(graphics);
            return;
        }
        if (this.objectType == 2) {
            this.paintBarCC(graphics);
            return;
        }
        if (this.objectType == 3) {
            this.paintBarCF(graphics);
            return;
        }
        if (this.objectType == 4) {
            this.paintCMembrane(graphics);
            return;
        }
        if (this.objectType == 5) {
            this.paintPseudoString(graphics);
        }
    }
    
    private void readParameters() {
        final String parameter = this.getParameter("left");
        final String parameter2 = this.getParameter("right");
        final String parameter3 = this.getParameter("top");
        final String parameter4 = this.getParameter("bottom");
        final String parameter5 = this.getParameter("nsounds");
        final String parameter6 = this.getParameter("stringcolor");
        this.getParameter("materialHardness");
        this.getParameter("volume");
        try {
            this.materialHardness = Double.valueOf(this.getParameter("materialHardness"));
            this.malletHardness = Double.valueOf(this.getParameter("malletHardness"));
            this.soundLength = Double.valueOf(this.getParameter("soundLength"));
            this.stringThinness = Double.valueOf(this.getParameter("stringThinness"));
            this.lowestFrequency = Double.valueOf(this.getParameter("lowestFrequency"));
            this.pluckTime = Double.valueOf(this.getParameter("pluckTime"));
            this.relativeScrapeDuration = Double.valueOf(this.getParameter("relativeScrapeDuration"));
            this.initial_volume = (int)(double)Double.valueOf(this.getParameter("volume"));
            this.max_short_volume = 22166.666666666668 + -2166.6666666666665 * this.initial_volume;
            final String parameter7 = this.getParameter("forceType");
            if (parameter7.equals("bang")) {
                this.forceType = 0;
            }
            else if (parameter7.equals("scrape")) {
                this.forceType = 1;
            }
            else if (parameter7.equals("pluck")) {
                this.forceType = 2;
            }
            else if (parameter7.equals("bang2")) {
                this.forceType = 3;
            }
            else {
                System.out.println("forceType = bang | scrape | pluck | bang2 ");
                System.exit(1);
            }
            final String parameter8 = this.getParameter("scrapeForceStatistics");
            if (parameter8.equals("whiteNoise")) {
                this.scrapeForceStatistics = 4;
            }
            else if (parameter8.equals("gaussianNoise")) {
                this.scrapeForceStatistics = 5;
            }
            else {
                System.out.println("scrapeForceStatistics  = whiteNoise | gaussianNoise ");
                System.exit(1);
            }
            final String parameter9 = this.getParameter("objectType");
            if (parameter9.equals("cplatec")) {
                this.objectType = 1;
            }
            else if (parameter9.equals("string")) {
                this.objectType = 0;
            }
            else if (parameter9.equals("barcc")) {
                this.objectType = 2;
            }
            else if (parameter9.equals("barcf")) {
                this.objectType = 3;
            }
            else if (parameter9.equals("cmembrane")) {
                this.objectType = 4;
            }
            else {
                System.out.println("objectType = string | cplatec | barcc | barcf | cmembrane");
                System.exit(1);
            }
            this.xl = Integer.parseInt(parameter);
            this.xr = Integer.parseInt(parameter2);
            this.yt = Integer.parseInt(parameter3);
            this.yb = Integer.parseInt(parameter4);
            this.n_samples = Integer.parseInt(parameter5);
            this.stringColor = new Color(Integer.parseInt(parameter6, 16));
        }
        catch (NumberFormatException ex) {
            System.out.println("Error1 in applet parameters");
            System.exit(1);
        }
        final String parameter10 = this.getParameter("centerx");
        final String parameter11 = this.getParameter("centery");
        final String parameter12 = this.getParameter("rimsize");
        final String parameter13 = this.getParameter("radius");
        final String parameter14 = this.getParameter("platecolor");
        try {
            this.centerx = Integer.parseInt(parameter10);
            this.centery = Integer.parseInt(parameter11);
            this.rimsize = Integer.parseInt(parameter12);
            this.radius = Integer.parseInt(parameter13);
            this.plateColor = new Color(Integer.parseInt(parameter14, 16));
        }
        catch (NumberFormatException ex2) {
            System.out.println("Error2 in applet parameters");
            System.exit(1);
        }
        final String parameter15 = this.getParameter("barcolor");
        try {
            this.barColor = new Color(Integer.parseInt(parameter15, 16));
        }
        catch (NumberFormatException ex3) {
            System.out.println("Error3 in applet parameters");
            System.exit(1);
        }
        final String parameter16 = this.getParameter("cmembranecolor");
        try {
            this.cmembraneColor = new Color(Integer.parseInt(parameter16, 16));
        }
        catch (NumberFormatException ex4) {
            System.out.println("Error4 in applet parameters");
            System.exit(1);
        }
        final String parameter17 = this.getParameter("pseudostringcolor");
        try {
            this.pseudoStringColor = new Color(Integer.parseInt(parameter17, 16));
        }
        catch (NumberFormatException ex5) {
            System.out.println("Error5 in applet parameters");
            System.exit(1);
        }
    }
    
    private void getUIvalues() {
        double n = 200.0;
        int n_samples = 1;
        try {
            n = this.materialHardness;
            this.materialHardness = Double.valueOf(this.edit1.getText());
            if (this.materialHardness < 1.0 || this.materialHardness > 10000.0) {
                throw new NumberFormatException("material error");
            }
        }
        catch (NumberFormatException ex) {
            this.edit7.appendText("Hardness must be " + 1.0 + " - " + 10000.0 + "\n");
            this.materialHardness = n;
            this.edit1.setText("" + n);
        }
        try {
            n = this.lowestFrequency;
            this.lowestFrequency = Double.valueOf(this.edit2.getText());
            if (this.lowestFrequency < 1.0 || this.lowestFrequency > 1000.0) {
                throw new NumberFormatException("Frequency ");
            }
        }
        catch (NumberFormatException ex2) {
            this.edit7.appendText("Frequency must be " + 1.0 + " - " + 1000.0 + "\n");
            this.lowestFrequency = n;
            this.edit2.setText("" + n);
        }
        try {
            n_samples = this.n_samples;
            this.n_samples = Integer.valueOf(this.edit3.getText());
            if (this.n_samples < 1 || this.n_samples > 10000) {
                throw new NumberFormatException(" error");
            }
        }
        catch (NumberFormatException ex3) {
            this.edit7.appendText("Grid size must be " + 1 + " - " + 10000 + "\n");
            this.n_samples = n_samples;
            this.edit3.setText("" + n_samples);
        }
        try {
            n = this.soundLength;
            this.soundLength = Double.valueOf(this.edit4.getText());
            if (this.soundLength < 1.0E-4 || this.soundLength > 60.0) {
                throw new NumberFormatException("SoundLength ");
            }
        }
        catch (NumberFormatException ex4) {
            this.edit7.appendText("Duration must be " + 1.0E-4 + " - " + 60.0 + "\n");
            this.soundLength = n;
            this.edit4.setText("" + n);
        }
        try {
            n = this.relativeScrapeDuration;
            this.relativeScrapeDuration = Double.valueOf(this.edit5.getText());
            if (this.relativeScrapeDuration < 0.01 || this.relativeScrapeDuration > 1.0) {
                throw new NumberFormatException("relativeScrapeDuration");
            }
        }
        catch (NumberFormatException ex5) {
            this.edit7.appendText("Relative scrape duration must be " + 0.01 + " - " + 1.0 + "\n");
            this.relativeScrapeDuration = n;
            this.edit5.setText("" + n);
        }
        try {
            n = this.pluckTime;
            final double soundLength = this.soundLength;
            this.pluckTime = Double.valueOf(this.edit6.getText());
            if (this.pluckTime < 1.0E-4 || this.pluckTime > soundLength) {
                throw new NumberFormatException("pluckTime");
            }
        }
        catch (NumberFormatException ex6) {
            this.edit7.appendText("Absolute pluck duration must be " + 1.0E-4 + " - " + "sound lenght\n");
            this.pluckTime = n;
            this.edit6.setText("" + n);
        }
        try {
            n = this.malletHardness;
            this.malletHardness = Double.valueOf(this.edit8.getText());
            if (this.malletHardness < 10.0 || this.malletHardness > 100000.0) {
                throw new NumberFormatException("malletHardness");
            }
        }
        catch (NumberFormatException ex7) {
            this.edit7.appendText("Hammer hardness must be " + 10.0 + " - " + 100000.0 + "\n");
            this.malletHardness = n;
            this.edit8.setText("" + n);
        }
    }
    
    public void stop() {
        this.isStopped = true;
        if (this.synthesizeThread != null && this.synthesizeThread.isAlive()) {
            this.synthesizeThread.stop();
            this.synthesizeThread = null;
        }
        this.edit7.setText("");
        this.edit7.appendText("Stopped\n");
        this.edit7.appendText("Set options and press Build\n");
    }
    
    public void start() {
        this.isStopped = false;
        this.repaint();
        this.getUIvalues();
        if (this.synthesizeThread != null && this.synthesizeThread.isAlive()) {
            this.synthesizeThread.stop();
        }
        (this.synthesizeThread = new Thread(this)).start();
        this.edit7.appendText("Running... (press Stop to end)\n");
    }
    
    public void init() {
        this.g = this.getGraphics();
        this.readParameters();
        this.setLayout(null);
        this.addNotify();
        this.resize(715, 535);
        this.setBackground(new Color(12632256));
        this.group1 = new CheckboxGroup();
        (this.check1 = new Checkbox("Hit 1", this.group1, false)).reshape(24, 192, 144, 24);
        this.add(this.check1);
        (this.check2 = new Checkbox("Pluck", this.group1, false)).reshape(24, 240, 144, 24);
        this.add(this.check2);
        (this.check3 = new Checkbox("Scrape (white noise)", this.group1, false)).reshape(24, 264, 180, 24);
        this.add(this.check3);
        (this.check4 = new Checkbox("Scrape (Gaussian noise)", this.group1, true)).reshape(24, 288, 198, 24);
        this.add(this.check4);
        (this.label1 = new Label("Force")).reshape(36, 144, 90, 32);
        this.label1.setFont(new Font("Courier", 1, 20));
        this.add(this.label1);
        this.group2 = new CheckboxGroup();
        (this.check6 = new Checkbox("Bar 1", this.group2, false)).reshape(228, 216, 108, 24);
        this.add(this.check6);
        (this.check7 = new Checkbox("Circular Plate", this.group2, false)).reshape(228, 264, 120, 24);
        this.add(this.check7);
        (this.label2 = new Label("Object")).reshape(240, 144, 90, 32);
        this.label2.setFont(new Font("Courier", 1, 20));
        this.add(this.label2);
        (this.button1 = new Button("Build")).reshape(360, 168, 54, 40);
        this.add(this.button1);
        (this.button2 = new Button("Stop")).reshape(360, 216, 54, 40);
        this.add(this.button2);
        (this.edit2 = new TextField(10)).reshape(576, 192, 108, 32);
        this.add(this.edit2);
        (this.edit3 = new TextField(10)).reshape(576, 228, 108, 32);
        this.add(this.edit3);
        (this.label4 = new Label("Base freq.")).reshape(432, 192, 90, 24);
        this.add(this.label4);
        (this.label5 = new Label("Grid size")).reshape(432, 228, 90, 24);
        this.add(this.label5);
        (this.label7 = new Label("Scrape time")).reshape(432, 306, 108, 24);
        this.add(this.label7);
        (this.label8 = new Label("Pluck time")).reshape(432, 342, 86, 24);
        this.add(this.label8);
        (this.edit7 = new TextArea(4, 32)).reshape(24, 342, 324, 88);
        this.edit7.setBackground(new Color(16777215));
        this.add(this.edit7);
        (this.check8 = new Checkbox("Bar 2", this.group2, false)).reshape(228, 240, 108, 24);
        this.add(this.check8);
        (this.check9 = new Checkbox("Circular Drum", this.group2, false)).reshape(228, 288, 144, 24);
        this.add(this.check9);
        (this.check10 = new Checkbox("Hit 2", this.group1, false)).reshape(24, 216, 144, 24);
        this.add(this.check10);
        (this.check11 = new Checkbox("Pseudo String", this.group2, false)).reshape(228, 312, 144, 24);
        this.add(this.check11);
        (this.check5 = new Checkbox("String", this.group2, true)).reshape(228, 192, 120, 24);
        this.add(this.check5);
        (this.edit8 = new TextField(10)).reshape(576, 372, 108, 32);
        this.add(this.edit8);
        (this.edit6 = new TextField(10)).reshape(576, 336, 108, 32);
        this.add(this.edit6);
        (this.label3 = new Label("Hardness")).reshape(432, 156, 90, 24);
        this.add(this.label3);
        (this.edit1 = new TextField(10)).reshape(576, 156, 108, 32);
        this.add(this.edit1);
        (this.edit4 = new TextField(10)).reshape(576, 264, 108, 32);
        this.add(this.edit4);
        (this.label6 = new Label("Duration")).reshape(432, 264, 90, 24);
        this.add(this.label6);
        (this.edit5 = new TextField(10)).reshape(576, 300, 108, 32);
        this.add(this.edit5);
        (this.label9 = new Label("Hammer hardness")).reshape(432, 378, 141, 24);
        this.add(this.label9);
        (this.verticalSlider1 = new VerticalSlider()).reshape(372, 270, 48, 138);
        this.add((Component)this.verticalSlider1);
        (this.label10 = new Label("Volume")).reshape(372, 414, 60, 24);
        this.add(this.label10);
        ((Slider)this.verticalSlider1).setValue(11 - this.initial_volume);
        this.edit1.setText("" + this.materialHardness);
        this.edit2.setText("" + this.lowestFrequency);
        this.edit3.setText("" + this.n_samples);
        this.edit4.setText("" + this.soundLength);
        this.edit5.setText("" + this.relativeScrapeDuration);
        this.edit6.setText("" + this.pluckTime);
        this.edit8.setText("" + this.malletHardness);
    }
    
    public void destroy() {
    }
    
    private void uimess(final String s) {
        this.edit7.appendText(s);
    }
    
    private void clearUimess() {
        this.edit7.setText("");
    }
    
    private boolean mouseDownBarCC(final Event event, final int n, final int n2) {
        return this.mouseDownString(event, n, n2);
    }
    
    private boolean mouseDownPseudoString(final Event event, final int n, final int n2) {
        return this.mouseDownString(event, n, n2);
    }
    
    private boolean mouseDownBarCF(final Event event, final int n, final int n2) {
        if (n2 < this.yb && n2 > this.yt && n < this.xr && n > this.xl) {
            this.g.setXORMode(Color.white);
            this.g.fillOval(n - 2, n2 - 2, 5, 5);
            int n3 = (n - this.xl) / (this.xr - this.xl) * this.n_samples;
            if (n3 >= this.n_samples) {
                n3 = this.n_samples - 1;
            }
            if (!this.isStopped && (this.synthesizeThread == null || !this.synthesizeThread.isAlive())) {
                this.playSound(n3);
            }
            this.g.fillOval(n - 2, n2 - 2, 5, 5);
        }
        return true;
    }
    
    private boolean mouseDownString(final Event event, final int n, final int n2) {
        if (n2 < this.yb && n2 > this.yt && n < this.xr && n > this.xl) {
            this.g.setXORMode(Color.white);
            this.g.fillOval(n - 2, n2 - 2, 5, 5);
            int n3;
            if (n <= (this.xl + this.xr) / 2) {
                n3 = n - this.xl;
            }
            else {
                n3 = this.xr - n;
            }
            int n4 = n3 / ((this.xr - this.xl) / 2) * this.n_samples;
            if (n4 >= this.n_samples) {
                n4 = this.n_samples - 1;
            }
            if (!this.isStopped && (this.synthesizeThread == null || !this.synthesizeThread.isAlive())) {
                this.playSound(n4);
            }
            this.g.fillOval(n - 2, n2 - 2, 5, 5);
        }
        return true;
    }
    
    private boolean mouseDownCMembrane(final Event event, final int n, final int n2) {
        return this.mouseDownCPlate(event, n, n2);
    }
    
    private boolean mouseDownCPlate(final Event event, final int n, final int n2) {
        final int n3 = (n - this.centerx) * (n - this.centerx) + (n2 - this.centery) * (n2 - this.centery);
        final int n4 = (this.radius - this.rimsize) * (this.radius - this.rimsize);
        if (n3 <= n4) {
            this.g.setXORMode(Color.white);
            this.g.fillOval(n - 2, n2 - 2, 5, 5);
            int n5 = (int)(Math.sqrt(n3 / n4) * this.n_samples);
            if (n5 >= this.n_samples) {
                n5 = this.n_samples - 1;
            }
            if (!this.isStopped && (this.synthesizeThread == null || !this.synthesizeThread.isAlive())) {
                this.playSound(n5);
            }
            this.g.fillOval(n - 2, n2 - 2, 5, 5);
        }
        return true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.objectType == 0) {
            return this.mouseDownString(event, n, n2);
        }
        if (this.objectType == 1) {
            return this.mouseDownCPlate(event, n, n2);
        }
        if (this.objectType == 2) {
            return this.mouseDownString(event, n, n2);
        }
        if (this.objectType == 3) {
            return this.mouseDownBarCF(event, n, n2);
        }
        if (this.objectType == 4) {
            return this.mouseDownCPlate(event, n, n2);
        }
        return this.objectType == 5 && this.mouseDownString(event, n, n2);
    }
    
    private void playSound(final int n) {
        final AudioDataStream audioDataStream = new AudioDataStream(this.audioData[n]);
        if (!this.isStopped) {
            this.audioPlayer.start(audioDataStream);
            return;
        }
        System.out.println("Stopped");
    }
    
    public void clickedCheck1() {
        this.forceType = 0;
        this.start();
    }
    
    public void clickedCheck10() {
        this.forceType = 3;
        this.start();
    }
    
    public void clickedCheck2() {
        this.forceType = 2;
        this.start();
    }
    
    public void clickedCheck3() {
        this.scrapeForceStatistics = 4;
        this.forceType = 1;
        this.start();
    }
    
    public void clickedCheck4() {
        this.scrapeForceStatistics = 5;
        this.forceType = 1;
        this.start();
    }
    
    public void clickedCheck5() {
        this.objectType = 0;
        this.start();
    }
    
    public void clickedCheck7() {
        this.objectType = 1;
        this.start();
    }
    
    public void clickedCheck6() {
        this.objectType = 2;
        this.start();
    }
    
    public void clickedCheck8() {
        this.objectType = 3;
        this.start();
    }
    
    public void clickedCheck9() {
        this.objectType = 4;
        this.start();
    }
    
    public void clickedCheck11() {
        this.objectType = 5;
        this.start();
    }
    
    public void clickedCheck12() {
        this.objectType = 6;
        this.start();
    }
    
    public void clickedButton1() {
        this.start();
    }
    
    public void clickedButton2() {
        this.stop();
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 1001 && event.target == this.check11) {
            this.clickedCheck11();
            return true;
        }
        if (event.id == 1001 && event.target == this.check10) {
            this.clickedCheck10();
            return true;
        }
        if (event.id == 1004 && event.target == this.edit8) {
            this.gotFocusEdit8(event);
            return true;
        }
        if (event.id == 1004 && event.target == this.edit6) {
            this.gotFocusEdit6(event);
            return true;
        }
        if (event.id == 1004 && event.target == this.edit5) {
            this.gotFocusEdit5(event);
            return true;
        }
        if (event.id == 1004 && event.target == this.edit4) {
            this.gotFocusEdit4(event);
            return true;
        }
        if (event.id == 1004 && event.target == this.edit3) {
            this.gotFocusEdit3(event);
            return true;
        }
        if (event.id == 1004 && event.target == this.edit2) {
            this.gotFocusEdit2(event);
            return true;
        }
        if (event.id == 1001 && event.target == this.check9) {
            this.clickedCheck9();
            return true;
        }
        if (event.id == 1001 && event.target == this.check8) {
            this.clickedCheck8();
            return true;
        }
        if (event.id == 1001 && event.target == this.check6) {
            this.clickedCheck6();
            return true;
        }
        if (event.id == 1001 && event.target == this.check7) {
            this.clickedCheck7();
            return true;
        }
        if (event.id == 1004 && event.target == this.edit1) {
            this.gotFocusEdit1(event);
            return true;
        }
        if (event.id == 1001 && event.target == this.button2) {
            this.clickedButton2();
            return true;
        }
        if (event.id == 1001 && event.target == this.button1) {
            this.clickedButton1();
            return true;
        }
        if (event.id == 1001 && event.target == this.check5) {
            this.clickedCheck5();
            return true;
        }
        if (event.id == 1001 && event.target == this.check4) {
            this.clickedCheck4();
            return true;
        }
        if (event.id == 1001 && event.target == this.check3) {
            this.clickedCheck3();
            return true;
        }
        if (event.id == 1001 && event.target == this.check2) {
            this.clickedCheck2();
            return true;
        }
        if (event.id == 1001 && event.target == this.check1) {
            this.clickedCheck1();
            return true;
        }
        if (event.target == this.verticalSlider1 && event.id == 1001) {
            this.verticalSlider1_Action(event);
            return true;
        }
        return super.handleEvent(event);
    }
    
    public void gotFocusEdit1(final Event event) {
        this.edit7.setText("");
        this.edit7.appendText(1.0 + " - " + 10000.0 + ". Represents material hardness for\n");
        this.edit7.appendText("solids, represents damping behaviour for strings\n");
        this.edit7.appendText("and membranes.\n");
    }
    
    public void gotFocusEdit2(final Event event) {
        this.edit7.setText("");
        this.edit7.appendText(1.0 + " - " + 1000.0 + ". Frequency of lowest partial.\n");
        this.edit7.appendText("Lower sounds need more computation.\n");
    }
    
    public void gotFocusEdit3(final Event event) {
        this.edit7.setText("");
        this.edit7.appendText(1 + " - " + 10000 + ". Number of different\n");
        this.edit7.appendText("sounds mapped onto object.\n");
    }
    
    public void gotFocusEdit4(final Event event) {
        this.edit7.setText("");
        this.edit7.appendText(1.0E-4 + " - " + 60.0 + ". Length in seconds\n");
        this.edit7.appendText("of sound response to mouse click.\n");
    }
    
    public void gotFocusEdit5(final Event event) {
        this.edit7.setText("");
        this.edit7.appendText(0.01 + " - " + 1.0 + ". For scraping force. Fraction of\n");
        this.edit7.appendText("the sample time that the objects is scraped.\n");
        this.edit7.appendText("The rest of the time it is ringing.\n");
    }
    
    public void gotFocusEdit6(final Event event) {
        this.edit7.setText("");
        this.edit7.appendText(1.0E-4 + " - " + this.soundLength + ". For plucking force. Length of\n");
        this.edit7.appendText("time (in seconds) that the objects is pulled\n");
        this.edit7.appendText("before release.\n");
    }
    
    public void gotFocusEdit8(final Event event) {
        this.edit7.setText("");
        this.edit7.appendText(10.0 + " - " + 100000.0 + ". For hitting force. Reciprocal of\n");
        this.edit7.appendText("the length of time (in seconds) that the object\n");
        this.edit7.appendText("is in contact with the hammer.\n");
    }
    
    public SoundObjectsApplet() {
        this.stringThinness = 1.0;
        this.materialHardness = 100.0;
        this.lowestFrequency = 415.0;
        this.loudNess = 1.0;
        this.maximumForce = 1.0;
        this.malletHardness = 10000.0;
        this.soundLength = 1.0;
        this.samplingRate = 8012.0;
        this.pluckTime = 0.30000000000000004;
        this.bangBangProb = 0.01;
        this.relativeScrapeDuration = 0.5;
        this.max_short_volume = 1000.0;
        this.scrapeForceStatistics = 4;
        this.isStopped = false;
    }
}
