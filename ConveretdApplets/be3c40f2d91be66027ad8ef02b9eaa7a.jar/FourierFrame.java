import java.lang.reflect.Method;
import java.awt.Rectangle;
import java.awt.Event;
import java.awt.event.AdjustmentEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Label;
import java.awt.LayoutManager;
import java.util.StringTokenizer;
import java.awt.Component;
import java.util.Hashtable;
import java.awt.Scrollbar;
import java.awt.Checkbox;
import java.awt.Button;
import java.awt.Container;
import java.text.NumberFormat;
import java.util.Random;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.event.ItemListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.AdjustmentListener;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class FourierFrame extends Frame implements ComponentListener, ActionListener, AdjustmentListener, MouseMotionListener, MouseListener, ItemListener
{
    PlayThread playThread;
    Dimension winSize;
    Image dbimage;
    Random random;
    public static final int sampleCount = 1024;
    public static final int halfSampleCount = 512;
    public static final double halfSampleCountFloat = 512.0;
    final int rate = 22050;
    final int playSampleCount = 16384;
    Fourier applet;
    NumberFormat showFormat;
    public boolean useFrame;
    Container main;
    Button sineButton;
    Button cosineButton;
    Button rectButton;
    Button fullRectButton;
    Button triangleButton;
    Button sawtoothButton;
    Button squareButton;
    Button noiseButton;
    Button blankButton;
    Button phaseButton;
    Button clipButton;
    Button resampleButton;
    Button quantizeButton;
    Button highPassButton;
    Checkbox magPhaseCheck;
    Checkbox soundCheck;
    Checkbox logCheck;
    Scrollbar termBar;
    Scrollbar freqBar;
    double[] magcoef;
    double[] phasecoef;
    boolean[] mutes;
    boolean[] solos;
    boolean hasSolo;
    static final double pi = 3.141592653589793;
    static final double step = 0.006135923151542565;
    double[] func;
    int maxTerms;
    int selectedCoef;
    static final int SEL_NONE = 0;
    static final int SEL_FUNC = 1;
    static final int SEL_MAG = 2;
    static final int SEL_PHASE = 3;
    static final int SEL_MUTES = 4;
    static final int SEL_SOLOS = 5;
    int selection;
    int dragX;
    int dragY;
    int quantizeCount;
    int resampleCount;
    boolean dragging;
    boolean freqAdjusted;
    View viewFunc;
    View viewMag;
    View viewPhase;
    View viewMutes;
    View viewSolos;
    FFT fft;
    FourierCanvas cv;
    boolean java2;
    Hashtable showTable;
    boolean shown;
    double[] origFunc;
    int dfreq0;
    static /* synthetic */ Class class$java$lang$Class;
    
    public String getAppletInfo() {
        return "Fourier Series by Paul Falstad";
    }
    
    FourierFrame(final Fourier applet) {
        super("Fourier Series Applet v1.6b");
        this.maxTerms = 160;
        this.shown = false;
        this.applet = applet;
        this.useFrame = true;
    }
    
    int getrand(final int n) {
        int nextInt = this.random.nextInt();
        if (nextInt < 0) {
            nextInt = -nextInt;
        }
        return nextInt % n;
    }
    
    boolean mustShow(final String s) {
        return this.showTable == null || this.showTable.containsKey(s);
    }
    
    Button doButton(final String s) {
        final Button button = new Button(s);
        if (this.mustShow(s)) {
            this.main.add(button);
        }
        button.addActionListener(this);
        return button;
    }
    
    Checkbox doCheckbox(final String s) {
        final Checkbox checkbox = new Checkbox(s);
        if (this.mustShow(s)) {
            this.main.add(checkbox);
        }
        try {
            final String parameter = this.applet.getParameter(s);
            if (parameter != null && parameter.equalsIgnoreCase("true")) {
                checkbox.setState(true);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        checkbox.addItemListener(this);
        return checkbox;
    }
    
    public void init() {
        if (new Double(System.getProperty("java.class.version")) >= 48.0) {
            this.java2 = true;
        }
        String parameter = "";
        try {
            final String parameter2 = this.applet.getParameter("useFrame");
            if (parameter2 != null && parameter2.equalsIgnoreCase("false")) {
                this.useFrame = false;
            }
            final String parameter3 = this.applet.getParameter("show");
            if (parameter3 != null) {
                this.showTable = new Hashtable(10);
                final StringTokenizer stringTokenizer = new StringTokenizer(parameter3, ",");
                while (stringTokenizer.hasMoreTokens()) {
                    this.showTable.put(stringTokenizer.nextToken(), "");
                }
                this.showTable.put("Sound", "");
            }
            parameter = this.applet.getParameter("state");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        if (this.useFrame) {
            this.main = this;
        }
        else {
            this.main = this.applet;
        }
        this.selectedCoef = -1;
        this.magcoef = new double[this.maxTerms];
        this.phasecoef = new double[this.maxTerms];
        this.mutes = new boolean[this.maxTerms];
        this.solos = new boolean[this.maxTerms];
        this.func = new double[1025];
        this.random = new Random();
        this.fft = new FFT(1024);
        this.main.setLayout(new FourierLayout());
        (this.cv = new FourierCanvas(this)).addComponentListener(this);
        this.cv.addMouseMotionListener(this);
        this.cv.addMouseListener(this);
        this.main.add(this.cv);
        this.sineButton = this.doButton("Sine");
        this.cosineButton = this.doButton("Cosine");
        this.triangleButton = this.doButton("Triangle");
        this.sawtoothButton = this.doButton("Sawtooth");
        this.squareButton = this.doButton("Square");
        this.noiseButton = this.doButton("Noise");
        this.phaseButton = this.doButton("Phase Shift");
        this.clipButton = this.doButton("Clip");
        this.resampleButton = this.doButton("Resample");
        this.quantizeButton = this.doButton("Quantize");
        this.rectButton = this.doButton("Rectify");
        this.fullRectButton = this.doButton("Full Rectify");
        this.highPassButton = this.doButton("High-Pass Filter");
        this.blankButton = this.doButton("Clear");
        this.soundCheck = this.doCheckbox("Sound");
        if (!this.java2) {
            this.remove(this.soundCheck);
        }
        this.magPhaseCheck = this.doCheckbox("Mag/Phase View");
        (this.logCheck = this.doCheckbox("Log View")).disable();
        if (this.mustShow("Terms")) {
            this.main.add(new Label("Number of Terms", 1));
        }
        (this.termBar = new Scrollbar(0, 50, 1, 1, this.maxTerms)).addAdjustmentListener(this);
        if (this.mustShow("Terms")) {
            this.main.add(this.termBar);
        }
        if (this.java2) {
            this.main.add(new Label("Playing Frequency", 1));
        }
        (this.freqBar = new Scrollbar(0, 251, 1, -100, 500)).addAdjustmentListener(this);
        if (this.java2) {
            this.main.add(this.freqBar);
        }
        this.main.add(new Label("http://www.falstad.com"));
        this.cv.setBackground(Color.black);
        this.cv.setForeground(Color.lightGray);
        (this.showFormat = NumberFormat.getInstance()).setMaximumFractionDigits(5);
        if (parameter.equalsIgnoreCase("square")) {
            this.doSquare();
        }
        else if (parameter.equalsIgnoreCase("sine")) {
            this.doSine();
        }
        else if (parameter.equalsIgnoreCase("triangle")) {
            this.doTriangle();
        }
        else if (parameter.equalsIgnoreCase("noise")) {
            this.doNoise();
        }
        else if (parameter.equalsIgnoreCase("quant")) {
            this.doSine();
            this.doQuantize();
        }
        else if (parameter.equalsIgnoreCase("resample")) {
            this.doSine();
            this.doResample();
        }
        else if (parameter.equalsIgnoreCase("clip")) {
            this.doSine();
            this.doClip();
        }
        else if (parameter.equalsIgnoreCase("rect")) {
            this.doSine();
            this.doRect();
        }
        else if (parameter.equalsIgnoreCase("fullrect")) {
            this.doSine();
            this.doFullRect();
        }
        else if (parameter.equalsIgnoreCase("fullsaw")) {
            this.doSawtooth();
            this.doFullRect();
        }
        else if (parameter.equalsIgnoreCase("beats")) {
            this.doBeats();
        }
        else if (parameter.equalsIgnoreCase("loudsoft")) {
            this.doLoudSoft();
        }
        else {
            this.doSawtooth();
        }
        if (this.useFrame) {
            this.resize(800, 640);
            this.handleResize();
            final Dimension size = this.getSize();
            final Dimension screenSize = this.getToolkit().getScreenSize();
            this.setLocation((screenSize.width - size.width) / 2, (screenSize.height - size.height) / 2);
            this.show();
        }
        else {
            this.hide();
            this.handleResize();
            this.applet.validate();
        }
        this.main.requestFocus();
    }
    
    void handleResize() {
        final Dimension size = this.cv.getSize();
        this.winSize = size;
        final Dimension dimension = size;
        if (this.winSize.width == 0) {
            return;
        }
        this.dbimage = this.cv.createImage(dimension.width, dimension.height);
        final int n = 20;
        final int n2 = (dimension.height - n * 2) / 3;
        this.viewFunc = new View(0, 0, dimension.width, n2);
        final int n3 = n2 + n * 2;
        this.viewMag = new View(0, n3, dimension.width, n2);
        if (this.magPhaseCheck.getState()) {
            final View viewMag = this.viewMag;
            viewMag.ymult *= 1.6;
            final View viewMag2 = this.viewMag;
            viewMag2.midy += (int)this.viewMag.ymult / 2;
            this.logCheck.enable();
        }
        else {
            this.logCheck.disable();
            this.logCheck.setState(false);
        }
        this.viewPhase = new View(0, n3 + n2, dimension.width, n2);
        final int n4 = this.viewPhase.midy + (int)this.viewPhase.ymult + 10;
        final int n5 = (dimension.height - n4) / 2;
        this.viewMutes = new View(0, n4, dimension.width, n5);
        this.viewSolos = new View(0, n4 + n5, dimension.width, n5);
    }
    
    public void triggerShow() {
        if (!this.shown) {
            this.show();
        }
        this.shown = true;
    }
    
    void doBeats() {
        for (int i = 0; i != 1024; ++i) {
            final double n = (i - 512) * 0.006135923151542565;
            this.func[i] = 0.5 * (Math.cos(n * 20.0) + Math.cos(n * 21.0));
        }
        this.func[1024] = this.func[0];
        this.transform();
        this.freqBar.setValue(-100);
    }
    
    void doLoudSoft() {
        for (int i = 0; i != 1024; ++i) {
            final double n = (i - 512) * 0.006135923151542565;
            this.func[i] = Math.cos(n) + 0.05 * Math.cos(n * 10.0);
        }
        this.func[1024] = this.func[0];
        this.transform();
    }
    
    void doSawtooth() {
        for (int i = 0; i != 1024; ++i) {
            this.func[i] = (i - 512) / 512.0;
        }
        this.func[1024] = this.func[0];
        this.transform();
    }
    
    void doTriangle() {
        for (int i = 0; i != 512; ++i) {
            this.func[i] = (i * 2 - 512) / 512.0;
            this.func[i + 512] = ((512 - i) * 2 - 512) / 512.0;
        }
        this.func[1024] = this.func[0];
        this.transform();
    }
    
    void doSine() {
        for (int i = 0; i != 1024; ++i) {
            this.func[i] = Math.sin((i - 512) * 0.006135923151542565);
        }
        this.func[1024] = this.func[0];
        this.transform();
    }
    
    void doCosine() {
        for (int i = 0; i != 1024; ++i) {
            this.func[i] = Math.cos((i - 512) * 0.006135923151542565);
        }
        this.func[1024] = this.func[0];
        this.transform();
    }
    
    void doRect() {
        for (int i = 0; i != 1024; ++i) {
            if (this.func[i] < 0.0) {
                this.func[i] = 0.0;
            }
        }
        this.func[1024] = this.func[0];
        this.transform();
    }
    
    void doFullRect() {
        for (int i = 0; i != 1024; ++i) {
            if (this.func[i] < 0.0) {
                this.func[i] = -this.func[i];
            }
        }
        this.func[1024] = this.func[0];
        this.transform();
    }
    
    void doHighPass() {
        for (int value = this.termBar.getValue(), i = 0; i != value; ++i) {
            if (this.magcoef[i] != 0.0) {
                this.magcoef[i] = 0.0;
                break;
            }
        }
        this.doSetFunc();
    }
    
    void doSquare() {
        for (int i = 0; i != 512; ++i) {
            this.func[i] = -1.0;
            this.func[i + 512] = 1.0;
        }
        this.func[1024] = this.func[0];
        this.transform();
    }
    
    void doNoise() {
        for (int n = 3, i = 0; i != 1024 / n; ++i) {
            final double n2 = Math.random() * 2.0 - 1.0;
            for (int j = 0; j != n; ++j) {
                this.func[i * n + j] = n2;
            }
        }
        this.func[1024] = this.func[0];
        this.transform();
    }
    
    void doPhaseShift() {
        final int n = 51;
        final double[] array = new double[n];
        for (int i = 0; i != n; ++i) {
            array[i] = this.func[i];
        }
        for (int j = 0; j != 1024 - n; ++j) {
            this.func[j] = this.func[j + n];
        }
        for (int k = 0; k != n; ++k) {
            this.func[1024 - n + k] = array[k];
        }
        this.func[1024] = this.func[0];
        this.transform();
    }
    
    void doBlank() {
        for (int i = 0; i <= 1024; ++i) {
            this.func[i] = 0.0;
        }
        for (int j = 0; j != this.termBar.getValue(); ++j) {
            this.mutes[j] = (this.solos[j] = false);
        }
        this.transform();
    }
    
    void doSetFunc() {
        final double[] array = new double[2048];
        for (int value = this.termBar.getValue(), i = 0; i != value; ++i) {
            final int n = ((i & 0x1) == 0x1) ? -1 : 1;
            array[i * 2] = n * this.magcoef[i] * Math.cos(this.phasecoef[i]);
            array[i * 2 + 1] = -n * this.magcoef[i] * Math.sin(this.phasecoef[i]);
        }
        this.fft.transform(array, true);
        for (int j = 0; j != 1024; ++j) {
            this.func[j] = array[j * 2];
        }
        this.func[1024] = this.func[0];
        this.updateSound();
    }
    
    void updateSound() {
        if (this.playThread != null) {
            this.playThread.soundChanged();
        }
    }
    
    void doClip() {
        final double n = 1.2;
        for (int i = 0; i != 1024; ++i) {
            final double[] func = this.func;
            final int n2 = i;
            func[n2] *= n;
            if (this.func[i] > 1.0) {
                this.func[i] = 1.0;
            }
            if (this.func[i] < -1.0) {
                this.func[i] = -1.0;
            }
        }
        this.func[1024] = this.func[0];
        this.transform();
    }
    
    void doResample() {
        if (this.resampleCount == 0) {
            this.resampleCount = 32;
        }
        if (this.resampleCount == 1024) {
            return;
        }
        for (int i = 0; i != 1024; i += this.resampleCount) {
            for (int j = 1; j != this.resampleCount; ++j) {
                this.func[i + j] = this.func[i];
            }
        }
        this.func[1024] = this.func[0];
        this.transform();
        this.resampleCount *= 2;
    }
    
    void doQuantize() {
        if (this.quantizeCount == 0) {
            this.quantizeCount = 8;
            this.origFunc = new double[1024];
            System.arraycopy(this.func, 0, this.origFunc, 0, 1024);
        }
        for (int i = 0; i != 1024; ++i) {
            this.func[i] = Math.round(this.origFunc[i] * this.quantizeCount) / this.quantizeCount;
        }
        this.func[1024] = this.func[0];
        this.transform();
        this.quantizeCount /= 2;
    }
    
    double getFreq() {
        this.dfreq0 = (int)(27.5 * Math.exp(this.freqBar.getValue() * 0.004158883084 * 2.0) * 16384.0 / 22050.0) * 2;
        return 22050 * this.dfreq0 / 32768.0;
    }
    
    void transform() {
        final double[] array = new double[2048];
        for (int i = 0; i != 1024; ++i) {
            array[i * 2] = this.func[i];
        }
        this.fft.transform(array, false);
        final double n = 1.0E-5;
        final double n2 = 0.001953125;
        for (int j = 0; j != this.maxTerms; ++j) {
            double n3 = array[j * 2] * n2;
            double n4 = -array[j * 2 + 1] * n2;
            if ((j & 0x1) == 0x1) {
                n3 = -n3;
            }
            else {
                n4 = -n4;
            }
            if (n3 < n && n3 > -n) {
                n3 = 0.0;
            }
            if (n4 < n && n4 > -n) {
                n4 = 0.0;
            }
            if (j == 0) {
                this.magcoef[0] = n3 / 2.0;
                this.phasecoef[0] = 0.0;
            }
            else {
                this.magcoef[j] = Math.sqrt(n3 * n3 + n4 * n4);
                this.phasecoef[j] = Math.atan2(-n4, n3);
            }
        }
        this.updateSound();
    }
    
    void centerString(final Graphics graphics, final String s, final int n) {
        graphics.drawString(s, (this.winSize.width - graphics.getFontMetrics().stringWidth(s)) / 2, n);
    }
    
    public void paint(final Graphics graphics) {
        this.cv.repaint();
    }
    
    public void updateFourier(final Graphics graphics) {
        if (this.winSize == null || this.winSize.width == 0 || this.dbimage == null) {
            return;
        }
        final Graphics graphics2 = this.dbimage.getGraphics();
        final Color color = new Color(76, 76, 76);
        final Color color2 = new Color(127, 127, 127);
        graphics2.setColor(this.cv.getBackground());
        graphics2.fillRect(0, 0, this.winSize.width, this.winSize.height);
        graphics2.setColor(this.cv.getForeground());
        int n = -1;
        int n2 = -1;
        final int midy = this.viewFunc.midy;
        final int periodWidth = this.viewFunc.periodWidth;
        final double ymult = this.viewFunc.ymult;
        for (int i = -1; i <= 1; ++i) {
            graphics2.setColor((i == 0) ? color2 : color);
            graphics2.drawLine(0, midy + i * (int)ymult, this.winSize.width, midy + i * (int)ymult);
        }
        for (int j = 2; j <= 4; ++j) {
            graphics2.setColor((j == 3) ? color2 : color);
            graphics2.drawLine(periodWidth * j / 2, midy - (int)ymult, periodWidth * j / 2, midy + (int)ymult);
        }
        graphics2.setColor(Color.white);
        if (!this.dragging || this.selection == 1) {
            for (int k = 0; k != 1025; ++k) {
                final int n3 = periodWidth * k / 1024;
                final int n4 = midy - (int)(ymult * this.func[k]);
                if (n != -1) {
                    graphics2.drawLine(n, n2, n3, n4);
                    graphics2.drawLine(n + periodWidth, n2, n3 + periodWidth, n4);
                    graphics2.drawLine(n + periodWidth * 2, n2, n3 + periodWidth * 2, n4);
                }
                n = n3;
                n2 = n4;
            }
        }
        final int value = this.termBar.getValue();
        if (!this.dragging || this.selection != 1) {
            graphics2.setColor(Color.red);
            int n5 = -1;
            for (int l = 0; l != 1025; ++l) {
                final int n6 = periodWidth * l / 1024;
                double n7 = 0.0;
                for (int n8 = 0; n8 != value; ++n8) {
                    n7 += this.magcoef[n8] * Math.cos(0.006135923151542565 * (l - 512) * n8 + this.phasecoef[n8]);
                }
                final int n9 = midy - (int)(ymult * n7);
                if (n5 != -1) {
                    graphics2.drawLine(n5, n2, n6, n9);
                    graphics2.drawLine(n5 + periodWidth, n2, n6 + periodWidth, n9);
                    graphics2.drawLine(n5 + periodWidth * 2, n2, n6 + periodWidth * 2, n9);
                }
                n5 = n6;
                n2 = n9;
            }
        }
        final int n10 = this.viewFunc.height + 10;
        if (this.selectedCoef != -1) {
            graphics2.setColor(Color.yellow);
            int n11 = -1;
            double n12 = this.phasecoef[this.selectedCoef];
            final double n13 = this.selectedCoef * 2 * 3.141592653589793 / periodWidth;
            double n14 = this.magcoef[this.selectedCoef];
            if (!this.magPhaseCheck.getState()) {
                if (this.selection == 2) {
                    n14 *= -Math.sin(n12);
                    n12 = -1.5707963267948966;
                }
                else {
                    n14 *= Math.cos(n12);
                    n12 = 0.0;
                }
            }
            final double n15 = ymult * n14;
            if (!this.dragging) {
                for (int n16 = 0; n16 != 1025; ++n16) {
                    final int n17 = periodWidth * n16 / 1024;
                    final int n18 = midy - (int)(n15 * Math.cos(0.006135923151542565 * (n16 - 512) * this.selectedCoef + n12));
                    if (n11 != -1) {
                        graphics2.drawLine(n11, n2, n17, n18);
                        graphics2.drawLine(n11 + periodWidth, n2, n17 + periodWidth, n18);
                        graphics2.drawLine(n11 + periodWidth * 2, n2, n17 + periodWidth * 2, n18);
                    }
                    n11 = n17;
                    n2 = n18;
                }
            }
            if (this.selectedCoef > 0 && this.java2) {
                final int n19 = (int)(this.getFreq() * this.selectedCoef);
                this.centerString(graphics2, n19 + ((n19 > 11025) ? " Hz (filtered)" : " Hz"), n10);
            }
            if (this.selectedCoef != -1) {
                String s;
                if (this.selectedCoef == 0) {
                    s = this.showFormat.format(n14) + "";
                }
                else {
                    String s2 = "cos";
                    if (!this.magPhaseCheck.getState() && this.selection == 2) {
                        s2 = "sin";
                    }
                    String s3;
                    if (this.selectedCoef == 1) {
                        s3 = this.showFormat.format(n14) + " " + s2 + "(x";
                    }
                    else {
                        s3 = this.showFormat.format(n14) + " " + s2 + "(" + this.selectedCoef + "x";
                    }
                    if (!this.magPhaseCheck.getState() || n12 == 0.0) {
                        s = s3 + ")";
                    }
                    else {
                        s = s3 + ((n12 < 0.0) ? " - " : " + ") + this.showFormat.format(Math.abs(n12)) + ")";
                    }
                    if (this.logCheck.getState()) {
                        this.showFormat.setMaximumFractionDigits(2);
                        s = s + "   (" + this.showFormat.format(20.0 * Math.log(n14) / Math.log(10.0)) + " dB)";
                        this.showFormat.setMaximumFractionDigits(5);
                    }
                }
                this.centerString(graphics2, s, n10 + 15);
            }
        }
        if (this.selectedCoef == -1 && this.freqAdjusted && this.java2) {
            final int n20 = (int)this.getFreq();
            graphics2.setColor(Color.yellow);
            this.centerString(graphics2, n20 + " Hz", n10);
        }
        this.freqAdjusted = false;
        final int termWidth = this.getTermWidth();
        final double ymult2 = this.viewMag.ymult;
        final int midy2 = this.viewMag.midy;
        graphics2.setColor(Color.white);
        if (this.magPhaseCheck.getState()) {
            this.centerString(graphics2, "Magnitudes", this.viewMag.labely);
            this.centerString(graphics2, "Phases", this.viewPhase.labely);
            graphics2.setColor(color2);
            graphics2.drawLine(0, midy2, this.winSize.width, midy2);
            graphics2.setColor(color);
            graphics2.drawLine(0, midy2 - (int)ymult2, this.winSize.width, midy2 - (int)ymult2);
            final int n21 = termWidth - 3;
            for (int n22 = 0; n22 != value; ++n22) {
                final int n23 = termWidth * n22 + termWidth / 2;
                final int n24 = midy2 - (int)(this.showMag(n22) * ymult2);
                graphics2.setColor((n22 == this.selectedCoef) ? Color.yellow : Color.white);
                graphics2.drawLine(n23, midy2, n23, n24);
                graphics2.fillOval(n23 - n21 / 2, n24 - n21 / 2, n21, n21);
            }
            final double ymult3 = this.viewPhase.ymult;
            final int midy3 = this.viewPhase.midy;
            for (int n25 = -2; n25 <= 2; ++n25) {
                graphics2.setColor((n25 == 0) ? color2 : color);
                graphics2.drawLine(0, midy3 + n25 * (int)ymult3 / 2, this.winSize.width, midy3 + n25 * (int)ymult3 / 2);
            }
            final double n26 = ymult3 / 3.141592653589793;
            for (int n27 = 0; n27 != value; ++n27) {
                final int n28 = termWidth * n27 + termWidth / 2;
                final int n29 = midy3 - (int)(this.phasecoef[n27] * n26);
                graphics2.setColor((n27 == this.selectedCoef) ? Color.yellow : Color.white);
                graphics2.drawLine(n28, midy3, n28, n29);
                graphics2.fillOval(n28 - n21 / 2, n29 - n21 / 2, n21, n21);
            }
        }
        else {
            this.centerString(graphics2, "Sines", this.viewMag.labely);
            this.centerString(graphics2, "Cosines", this.viewPhase.labely);
            graphics2.setColor(color2);
            graphics2.drawLine(0, midy2, this.winSize.width, midy2);
            graphics2.setColor(color);
            graphics2.drawLine(0, midy2 - (int)ymult2, this.winSize.width, midy2 - (int)ymult2);
            graphics2.drawLine(0, midy2 + (int)ymult2, this.winSize.width, midy2 + (int)ymult2);
            final int n30 = termWidth - 3;
            for (int n31 = 1; n31 != value; ++n31) {
                final int n32 = termWidth * n31 + termWidth / 2;
                final int n33 = midy2 + (int)(this.magcoef[n31] * Math.sin(this.phasecoef[n31]) * ymult2);
                graphics2.setColor((n31 == this.selectedCoef) ? Color.yellow : Color.white);
                graphics2.drawLine(n32, midy2, n32, n33);
                graphics2.fillOval(n32 - n30 / 2, n33 - n30 / 2, n30, n30);
            }
            final double ymult4 = this.viewPhase.ymult;
            final int midy4 = this.viewPhase.midy;
            for (int n34 = -2; n34 <= 2; n34 += 2) {
                graphics2.setColor((n34 == 0) ? color2 : color);
                graphics2.drawLine(0, midy4 + n34 * (int)ymult4 / 2, this.winSize.width, midy4 + n34 * (int)ymult4 / 2);
            }
            for (int n35 = 0; n35 != value; ++n35) {
                final int n36 = termWidth * n35 + termWidth / 2;
                final int n37 = midy4 - (int)(this.magcoef[n35] * Math.cos(this.phasecoef[n35]) * ymult4);
                graphics2.setColor((n35 == this.selectedCoef) ? Color.yellow : Color.white);
                graphics2.drawLine(n36, midy4, n36, n37);
                graphics2.fillOval(n36 - n30 / 2, n37 - n30 / 2, n30, n30);
            }
        }
        final double freq = this.getFreq();
        if (this.viewMutes.height > 8) {
            graphics2.setFont(new Font("SansSerif", 0, this.viewMutes.height));
            final FontMetrics fontMetrics = graphics2.getFontMetrics();
            for (int n38 = 1; n38 != value; ++n38) {
                if (freq * n38 > 11025.0) {
                    break;
                }
                final int n39 = termWidth * n38 + termWidth / 2;
                final int n40 = this.viewMutes.y + fontMetrics.getAscent();
                graphics2.setColor((n38 == this.selectedCoef) ? Color.yellow : Color.white);
                if (this.hasSolo && !this.solos[n38]) {
                    graphics2.setColor(Color.gray);
                }
                String s4 = "-";
                if (this.mutes[n38]) {
                    s4 = "M";
                }
                graphics2.drawString(s4, n39 - fontMetrics.stringWidth(s4) / 2, n40);
                final int n41 = this.viewSolos.y + fontMetrics.getAscent();
                String s5 = "-";
                if (this.solos[n38]) {
                    s5 = "S";
                }
                graphics2.drawString(s5, n39 - fontMetrics.stringWidth(s5) / 2, n41);
            }
        }
        graphics.drawImage(this.dbimage, 0, 0, this);
    }
    
    double showMag(final int n) {
        final double n2 = this.magcoef[n];
        if (!this.logCheck.getState() || n == 0) {
            return n2;
        }
        final double n3 = Math.log(n2) / 6.0 + 1.0;
        return (n3 < 0.0) ? 0.0 : n3;
    }
    
    double getMagValue(final double n) {
        if (!this.logCheck.getState()) {
            return n;
        }
        if (n == 0.0) {
            return 0.0;
        }
        return Math.exp(6.0 * (n - 1.0));
    }
    
    int getTermWidth() {
        int n = this.winSize.width / this.termBar.getValue();
        final int n2 = this.winSize.width / 30;
        if (n > n2) {
            n = n2;
        }
        if (n > 12) {
            n = 12;
        }
        return n & 0xFFFFFFFE;
    }
    
    void edit(final MouseEvent mouseEvent) {
        if (this.selection == 0) {
            return;
        }
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        switch (this.selection) {
            case 2: {
                this.editMag(x, y);
                break;
            }
            case 1: {
                this.editFunc(x, y);
                break;
            }
            case 3: {
                this.editPhase(x, y);
                break;
            }
            case 4: {
                this.editMutes(mouseEvent, x, y);
                break;
            }
            case 5: {
                this.editSolos(mouseEvent, x, y);
                break;
            }
        }
        final boolean b = false;
        this.resampleCount = (b ? 1 : 0);
        this.quantizeCount = (b ? 1 : 0);
    }
    
    void editMag(final int n, final int n2) {
        if (this.selectedCoef == -1) {
            return;
        }
        double magValue = -(n2 - this.viewMag.midy) / this.viewMag.ymult;
        if (this.magPhaseCheck.getState()) {
            if (this.selectedCoef > 0) {
                if (magValue < 0.0) {
                    magValue = 0.0;
                }
                magValue = this.getMagValue(magValue);
            }
            else if (magValue < -1.0) {
                magValue = -1.0;
            }
            if (magValue > 1.0) {
                magValue = 1.0;
            }
            if (this.magcoef[this.selectedCoef] == magValue) {
                return;
            }
            this.magcoef[this.selectedCoef] = magValue;
        }
        else {
            final int selectedCoef = this.selectedCoef;
            if (selectedCoef == 0) {
                return;
            }
            final double n3 = this.magcoef[selectedCoef] * Math.cos(this.phasecoef[selectedCoef]);
            if (magValue > 1.0) {
                magValue = 1.0;
            }
            if (magValue < -1.0) {
                magValue = -1.0;
            }
            final double n4 = magValue;
            this.magcoef[selectedCoef] = Math.sqrt(n4 * n4 + n3 * n3);
            this.phasecoef[selectedCoef] = Math.atan2(-n4, n3);
        }
        this.updateSound();
        this.cv.repaint();
    }
    
    void editFunc(int i, int n) {
        if (this.dragX == i) {
            this.editFuncPoint(i, n);
            this.dragY = n;
        }
        else {
            final int n2 = (i < this.dragX) ? i : this.dragX;
            final int n3 = (i < this.dragX) ? n : this.dragY;
            final int n4 = (i > this.dragX) ? i : this.dragX;
            final int n5 = (i > this.dragX) ? n : this.dragY;
            this.dragX = i;
            this.dragY = n;
            for (i = n2; i <= n4; ++i) {
                n = n3 + (n5 - n3) * (i - n2) / (n4 - n2);
                this.editFuncPoint(i, n);
            }
        }
    }
    
    void editFuncPoint(final int n, final int n2) {
        final int midy = this.viewFunc.midy;
        final int periodWidth = this.viewFunc.periodWidth;
        final double ymult = this.viewFunc.ymult;
        int i = n % periodWidth * 1024 / periodWidth;
        final int n3 = (n % periodWidth + 1) * 1024 / periodWidth - 1;
        double n4 = (midy - n2) / ymult;
        if (n4 > 1.0) {
            n4 = 1.0;
        }
        if (n4 < -1.0) {
            n4 = -1.0;
        }
        while (i <= n3) {
            this.func[i] = n4;
            ++i;
        }
        this.func[1024] = this.func[0];
        this.cv.repaint();
    }
    
    void editPhase(final int n, final int n2) {
        if (this.selectedCoef == -1) {
            return;
        }
        double n3 = -(n2 - this.viewPhase.midy) / this.viewPhase.ymult;
        if (this.magPhaseCheck.getState()) {
            double n4 = n3 * 3.141592653589793;
            if (n4 < -3.141592653589793) {
                n4 = -3.141592653589793;
            }
            if (n4 > 3.141592653589793) {
                n4 = 3.141592653589793;
            }
            if (this.phasecoef[this.selectedCoef] == n4) {
                return;
            }
            this.phasecoef[this.selectedCoef] = n4;
        }
        else {
            final int selectedCoef = this.selectedCoef;
            final double n5 = -this.magcoef[selectedCoef] * Math.sin(this.phasecoef[selectedCoef]);
            if (n3 > 1.0) {
                n3 = 1.0;
            }
            if (n3 < -1.0) {
                n3 = -1.0;
            }
            final double n6 = n3;
            this.magcoef[selectedCoef] = Math.sqrt(n5 * n5 + n6 * n6);
            this.phasecoef[selectedCoef] = Math.atan2(-n5, n6);
            this.updateSound();
        }
        this.cv.repaint();
    }
    
    void editMutes(final MouseEvent mouseEvent, final int n, final int n2) {
        if (mouseEvent.getID() != 501) {
            return;
        }
        if (this.selectedCoef == -1) {
            return;
        }
        this.mutes[this.selectedCoef] = !this.mutes[this.selectedCoef];
        this.cv.repaint();
    }
    
    void editSolos(final MouseEvent mouseEvent, final int n, final int n2) {
        if (mouseEvent.getID() != 501) {
            return;
        }
        if (this.selectedCoef == -1) {
            return;
        }
        this.solos[this.selectedCoef] = !this.solos[this.selectedCoef];
        final int value = this.termBar.getValue();
        this.hasSolo = false;
        for (int i = 0; i != value; ++i) {
            if (this.solos[i]) {
                this.hasSolo = true;
                break;
            }
        }
        this.cv.repaint();
    }
    
    public void componentHidden(final ComponentEvent componentEvent) {
    }
    
    public void componentMoved(final ComponentEvent componentEvent) {
    }
    
    public void componentShown(final ComponentEvent componentEvent) {
        this.cv.repaint();
    }
    
    public void componentResized(final ComponentEvent componentEvent) {
        this.handleResize();
        this.cv.repaint(100L);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.pressButton(actionEvent.getSource());
    }
    
    void pressButton(final Object o) {
        if (o == this.triangleButton) {
            this.doTriangle();
            this.cv.repaint();
        }
        if (o == this.sineButton) {
            this.doSine();
            this.cv.repaint();
        }
        if (o == this.cosineButton) {
            this.doCosine();
            this.cv.repaint();
        }
        if (o == this.rectButton) {
            this.doRect();
            this.cv.repaint();
        }
        if (o == this.fullRectButton) {
            this.doFullRect();
            this.cv.repaint();
        }
        if (o == this.squareButton) {
            this.doSquare();
            this.cv.repaint();
        }
        if (o == this.highPassButton) {
            this.doHighPass();
            this.cv.repaint();
        }
        if (o == this.noiseButton) {
            this.doNoise();
            this.cv.repaint();
        }
        if (o == this.phaseButton) {
            this.doPhaseShift();
            this.cv.repaint();
        }
        if (o == this.blankButton) {
            this.doBlank();
            this.cv.repaint();
        }
        if (o == this.sawtoothButton) {
            this.doSawtooth();
            this.cv.repaint();
        }
        if (o == this.clipButton) {
            this.doClip();
            this.cv.repaint();
        }
        if (o == this.quantizeButton) {
            this.doQuantize();
            this.cv.repaint();
        }
        else {
            this.quantizeCount = 0;
        }
        if (o == this.resampleButton) {
            this.doResample();
            this.cv.repaint();
        }
        else {
            this.resampleCount = 0;
        }
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getSource() == this.soundCheck && this.soundCheck.getState() && this.playThread == null) {
            (this.playThread = new PlayThread()).start();
        }
        if (itemEvent.getSource() == this.magPhaseCheck) {
            this.handleResize();
        }
        this.cv.repaint();
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        System.out.print(((Scrollbar)adjustmentEvent.getSource()).getValue() + "\n");
        if (adjustmentEvent.getSource() == this.termBar) {
            this.updateSound();
            this.cv.repaint();
        }
        if (adjustmentEvent.getSource() == this.freqBar) {
            this.freqAdjusted = true;
            this.updateSound();
            this.cv.repaint();
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.dragging = true;
        this.edit(mouseEvent);
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        this.dragX = x;
        this.dragY = y;
        final int selectedCoef = this.selectedCoef;
        this.selectedCoef = -1;
        this.selection = 0;
        final int selection = this.selection;
        if (this.viewFunc.contains(x, y)) {
            this.selection = 1;
        }
        else {
            this.selectedCoef = x / this.getTermWidth();
            if (this.selectedCoef > this.termBar.getValue()) {
                this.selectedCoef = -1;
            }
            if (this.selectedCoef != -1) {
                if (this.viewMag.contains(x, y)) {
                    this.selection = 2;
                }
                else if (this.viewMutes.contains(x, y)) {
                    this.selection = 4;
                }
                else if (this.viewSolos.contains(x, y)) {
                    this.selection = 5;
                }
                else if (this.viewPhase.contains(x, y)) {
                    this.selection = 3;
                }
            }
        }
        if (this.selectedCoef != selectedCoef || selection != this.selection) {
            this.cv.repaint();
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2 && this.selectedCoef != -1 && this.selection != 4 && this.selection != 5) {
            for (int i = 0; i != this.termBar.getValue(); ++i) {
                this.phasecoef[i] = 0.0;
                if (this.selectedCoef != i) {
                    this.magcoef[i] = 0.0;
                }
            }
            this.magcoef[this.selectedCoef] = 1.0;
            if (!this.magPhaseCheck.getState()) {
                this.phasecoef[this.selectedCoef] = ((this.selection == 2) ? -1.5707963267948966 : 0.0);
            }
            this.doSetFunc();
            this.cv.repaint();
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.mouseMoved(mouseEvent);
        if ((mouseEvent.getModifiers() & 0x4) != 0x0 && this.selectedCoef != -1) {
            this.termBar.setValue(this.selectedCoef + 1);
            this.cv.repaint();
        }
        if ((mouseEvent.getModifiers() & 0x10) == 0x0) {
            return;
        }
        this.dragging = true;
        this.edit(mouseEvent);
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiers() & 0x10) == 0x0) {
            return;
        }
        this.dragging = false;
        if (this.selection == 1) {
            this.transform();
        }
        else if (this.selection != 0) {
            this.doSetFunc();
        }
        this.cv.repaint();
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.applet.destroyFrame();
            return true;
        }
        return super.handleEvent(event);
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    class View extends Rectangle
    {
        int midy;
        int labely;
        double ymult;
        int periodWidth;
        
        View(final int n, final int n2, final int n3, final int n4) {
            super(n, n2, n3, n4);
            this.midy = n2 + n4 / 2;
            this.ymult = 0.6 * n4 / 2.0;
            this.periodWidth = n3 / 3;
            this.labely = this.midy - 5 - n4 * 3 / 8;
        }
    }
    
    class PlayThread extends Thread
    {
        boolean changed;
        
        public void soundChanged() {
            this.changed = true;
        }
        
        public void run() {
            Object invoke;
            Method method;
            try {
                final Class<?> forName = Class.forName("javax.sound.sampled.AudioFormat");
                final Object instance = forName.getConstructor(Float.TYPE, Integer.TYPE, Integer.TYPE, Boolean.TYPE, Boolean.TYPE).newInstance(new Float(22050.0f), new Integer(16), new Integer(1), new Boolean(true), new Boolean(true));
                final Class<?> forName2 = Class.forName("javax.sound.sampled.DataLine$Info");
                final Class<?> forName3 = Class.forName("javax.sound.sampled.SourceDataLine");
                invoke = Class.forName("javax.sound.sampled.AudioSystem").getMethod("getLine", Class.forName("javax.sound.sampled.Line$Info")).invoke(null, forName2.getConstructor((FourierFrame.class$java$lang$Class == null) ? (FourierFrame.class$java$lang$Class = FourierFrame.class$("java.lang.Class")) : FourierFrame.class$java$lang$Class, forName).newInstance(forName3, instance));
                forName3.getMethod("open", forName, Integer.TYPE).invoke(invoke, instance, new Integer(4096));
                forName3.getMethod("start", (Class[])null).invoke(invoke, (Object[])null);
                method = forName3.getMethod("write", new byte[] { 0 }.getClass(), Integer.TYPE, Integer.TYPE);
            }
            catch (Exception ex) {
                ex.printStackTrace();
                FourierFrame.this.playThread = null;
                return;
            }
            final FFT fft = new FFT(16384);
            double[] array = null;
            Object o = null;
            int n = 0;
            while (FourierFrame.this.soundCheck.getState() && FourierFrame.this.applet.ogf != null) {
                if (array == null || this.changed) {
                    array = new double[32768];
                    final int value = FourierFrame.this.termBar.getValue();
                    final double n2 = 6.283185307179586 * FourierFrame.this.getFreq() / 22050.0;
                    double n3 = 0.2;
                    this.changed = false;
                    for (int i = 1; i != value; ++i) {
                        if (!FourierFrame.this.hasSolo || FourierFrame.this.solos[i]) {
                            if (!FourierFrame.this.mutes[i]) {
                                final int n4 = FourierFrame.this.dfreq0 * i;
                                if (n4 >= 16384) {
                                    break;
                                }
                                final int n5 = ((i & 0x1) == 0x1) ? -1 : 1;
                                array[n4] = n5 * FourierFrame.this.magcoef[i] * Math.cos(FourierFrame.this.phasecoef[i]);
                                array[n4 + 1] = -n5 * FourierFrame.this.magcoef[i] * Math.sin(FourierFrame.this.phasecoef[i]);
                            }
                        }
                    }
                    fft.transform(array, true);
                    for (int j = 0; j != 16384; ++j) {
                        final double n6 = array[j * 2];
                        if (n6 > n3) {
                            n3 = n6;
                        }
                        if (n6 < -n3) {
                            n3 = -n6;
                        }
                    }
                    o = new byte[32768];
                    final double n7 = 32767.0 / n3;
                    for (int k = 0; k != 16384; ++k) {
                        final short n8 = (short)(array[k * 2] * n7);
                        o[k * 2] = (byte)(n8 / 256);
                        o[k * 2 + 1] = (byte)(n8 & 0xFF);
                    }
                }
                try {
                    final int n9 = 4096;
                    if (n >= o.length) {
                        n = 0;
                    }
                    method.invoke(invoke, o, new Integer(n), new Integer(n9));
                    n += n9;
                    continue;
                }
                catch (Exception ex2) {
                    ex2.printStackTrace();
                }
                break;
            }
            FourierFrame.this.playThread = null;
        }
    }
}
