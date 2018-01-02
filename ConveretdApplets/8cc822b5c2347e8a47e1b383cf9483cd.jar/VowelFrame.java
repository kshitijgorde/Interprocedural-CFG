import java.awt.Point;
import java.awt.TextArea;
import java.awt.Dialog;
import java.awt.Insets;
import java.awt.Container;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Line;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.SourceDataLine;
import java.awt.Rectangle;
import java.util.StringTokenizer;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.awt.Event;
import java.awt.event.AdjustmentEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.Component;
import java.awt.LayoutManager;
import java.text.NumberFormat;
import java.awt.Label;
import java.awt.Scrollbar;
import java.awt.Choice;
import java.awt.MenuItem;
import java.awt.CheckboxMenuItem;
import java.awt.Button;
import java.awt.Checkbox;
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

class VowelFrame extends Frame implements ComponentListener, ActionListener, AdjustmentListener, MouseMotionListener, MouseListener, ItemListener
{
    Dimension winSize;
    Image dbimage;
    View respView;
    View impulseView;
    View phaseView;
    View pipeView;
    View stepView;
    View spectrumView;
    View waveformView;
    View poleInfoView;
    View polesView;
    Random random;
    int maxSampleCount;
    int sampleCountR;
    int sampleCountTh;
    int modeCountR;
    int modeCountTh;
    int maxDispRModes;
    int maxDispThModes;
    public static final double epsilon = 1.0E-5;
    public static final double epsilon2 = 0.003;
    public static final double log10 = 2.302585092994046;
    public static int WINDOW_KAISER;
    public static final double soundSpeed = 35396.0;
    Checkbox soundCheck;
    Checkbox displayCheck;
    Checkbox compressCheck;
    Checkbox attenuationCheck;
    Checkbox envelopeCheck;
    Button exportButton;
    ImportDialog impDialog;
    CheckboxMenuItem freqCheckItem;
    CheckboxMenuItem phaseCheckItem;
    CheckboxMenuItem spectrumCheckItem;
    CheckboxMenuItem impulseCheckItem;
    CheckboxMenuItem stepCheckItem;
    CheckboxMenuItem waveformCheckItem;
    CheckboxMenuItem logFreqCheckItem;
    CheckboxMenuItem linRespCheckItem;
    CheckboxMenuItem allWaveformCheckItem;
    CheckboxMenuItem ferrisCheckItem;
    MenuItem exitItem;
    Choice filterChooser;
    int selection;
    final int SELECT_RESPONSE = 1;
    final int SELECT_SPECTRUM = 2;
    final int SELECT_PIPE = 3;
    int filterSelection;
    Choice inputChooser;
    Choice windowChooser;
    Choice rateChooser;
    Scrollbar[] auxBars;
    Label[] auxLabels;
    Label inputLabel;
    Scrollbar inputBar;
    Label kaiserLabel;
    Scrollbar kaiserBar;
    boolean editingFunc;
    boolean dragStop;
    double inputW;
    static final double pi = 3.141592653589793;
    double step;
    double waveGain;
    double outputGain;
    int sampleRate;
    int[] xpoints;
    int[] ypoints;
    int dragX;
    int dragY;
    int dragStartX;
    int dragStartY;
    int mouseX;
    int mouseY;
    int selectedPole;
    int selectedZero;
    int lastPoleCount;
    int lastZeroCount;
    boolean dragSet;
    boolean dragClear;
    boolean dragging;
    boolean unstable;
    double[] pipeRadius;
    double pipeLen;
    double t;
    int pause;
    PlayThread playThread;
    Filter curFilter;
    FilterType filterType;
    double[] spectrumBuf;
    FFT spectrumFFT;
    Waveform wformInfo;
    PhaseColor[] phaseColors;
    static final int phaseColorCount = 400;
    boolean filterChanged;
    VowelCanvas cv;
    Vowel applet;
    NumberFormat showFormat;
    boolean java2;
    String[] mp3List;
    String mp3Error;
    long lastTime;
    double minlog;
    double logrange;
    double[] uresp;
    static /* synthetic */ Class class$javax$sound$sampled$SourceDataLine;
    
    public String getAppletInfo() {
        return "Vowel Series by Paul Falstad";
    }
    
    int getrand(final int n) {
        int nextInt = this.random.nextInt();
        if (nextInt < 0) {
            nextInt = -nextInt;
        }
        return nextInt % n;
    }
    
    VowelFrame(final Vowel applet) {
        super("Vowel Applet v1.0");
        this.maxSampleCount = 70;
        this.maxDispRModes = 5;
        this.maxDispThModes = 5;
        this.waveGain = 1.52587890625E-5;
        this.outputGain = 1.0;
        this.xpoints = new int[4];
        this.ypoints = new int[4];
        this.lastPoleCount = 2;
        this.lastZeroCount = 2;
        this.java2 = false;
        this.applet = applet;
    }
    
    public void init() {
        this.mp3List = new String[20];
        try {
            final String parameter = this.applet.getParameter("PAUSE");
            if (parameter != null) {
                this.pause = Integer.parseInt(parameter);
            }
        }
        catch (Exception ex) {}
        if (new Double(System.getProperty("java.class.version")) >= 48.0) {
            this.java2 = true;
        }
        final int n = 50;
        this.phaseColors = new PhaseColor[400];
        for (int i = 0; i != 8; ++i) {
            for (int j = 0; j != n; ++j) {
                this.phaseColors[i * n + j] = this.genPhaseColor(i, Math.atan(j / n));
            }
        }
        this.pipeRadius = new double[200];
        int k;
        for (k = 0; k != 10; ++k) {
            this.pipeRadius[k] = 2.828427;
        }
        while (k != 20) {
            this.pipeRadius[k] = 1.0;
            ++k;
        }
        this.setLayout(new VowelLayout());
        (this.cv = new VowelCanvas(this)).addComponentListener(this);
        this.cv.addMouseMotionListener(this);
        this.cv.addMouseListener(this);
        this.add(this.cv);
        final MenuBar menuBar = new MenuBar();
        final Menu menu = new Menu("File");
        menuBar.add(menu);
        menu.add(this.exitItem = this.getMenuItem("Exit"));
        final Menu menu2 = new Menu("View");
        menuBar.add(menu2);
        menu2.add(this.freqCheckItem = this.getCheckItem("Frequency Response", true));
        this.phaseCheckItem = this.getCheckItem("Phase Response", false);
        menu2.add(this.spectrumCheckItem = this.getCheckItem("Spectrum", true));
        menu2.add(this.waveformCheckItem = this.getCheckItem("Waveform", false));
        menu2.add(this.impulseCheckItem = this.getCheckItem("Impulse Response", false));
        menu2.add(this.stepCheckItem = this.getCheckItem("Step Response", false));
        menu2.addSeparator();
        menu2.add(this.logFreqCheckItem = this.getCheckItem("Log Frequency Scale", false));
        menu2.add(this.allWaveformCheckItem = this.getCheckItem("Show Entire Waveform", false));
        menu2.add(this.ferrisCheckItem = this.getCheckItem("Ferris Plot", false));
        menu2.add(this.linRespCheckItem = this.getCheckItem("Linear Response Scale", false));
        this.setMenuBar(menuBar);
        this.soundCheck = new Checkbox("Sound On");
        if (this.java2) {
            this.soundCheck.setState(true);
        }
        else {
            this.soundCheck.disable();
        }
        this.soundCheck.addItemListener(this);
        this.add(this.soundCheck);
        (this.displayCheck = new Checkbox("Stop Display")).addItemListener(this);
        (this.compressCheck = new Checkbox("Compress")).setState(true);
        this.compressCheck.addItemListener(this);
        (this.attenuationCheck = new Checkbox("Attenuation")).setState(true);
        this.attenuationCheck.addItemListener(this);
        this.add(this.attenuationCheck);
        (this.envelopeCheck = new Checkbox("Envelope")).setState(true);
        this.envelopeCheck.addItemListener(this);
        this.add(this.envelopeCheck);
        this.add(this.exportButton = new Button("Import/Export"));
        this.exportButton.addActionListener(this);
        this.add(this.inputChooser = new Choice());
        this.inputChooser.add("Input = Noise");
        this.inputChooser.add("Input = Vocal");
        this.inputChooser.add("Input = Sawtooth");
        this.inputChooser.add("Input = Periodic Noise");
        this.inputChooser.add("Input = Triangle Wave");
        this.inputChooser.add("Input = Square Wave");
        this.inputChooser.add("Input = Sine Wave");
        this.inputChooser.add("Input = Sweep");
        this.inputChooser.add("Input = Impulses");
        for (int n2 = 0; this.mp3List[n2] != null; ++n2) {
            this.inputChooser.add("Input = " + this.mp3List[n2]);
        }
        this.inputChooser.select(1);
        this.inputChooser.addItemListener(this);
        this.add(this.filterChooser = new Choice());
        this.filterChooser.add("Filter = ah");
        this.filterChooser.add("Filter = oh");
        this.filterChooser.add("Filter = oo");
        this.filterChooser.add("Filter = ee");
        this.filterChooser.add("Filter = eh");
        this.filterChooser.add("Filter = barred-I (Russian vowel)");
        this.filterChooser.add("Filter = ah (simple)");
        this.filterChooser.add("Filter = ee (simple)");
        this.filterChooser.add("Filter = a as in bad (simple)");
        this.filterChooser.add("Filter = ih (simple)");
        this.filterChooser.add("Filter = oo (simple)");
        this.filterChooser.add("Filter = French u (simple)");
        this.filterChooser.add("Filter = open tube");
        this.filterChooser.add("Filter = custom");
        this.filterChooser.add("Filter = none");
        this.filterChooser.addItemListener(this);
        this.filterSelection = -1;
        (this.windowChooser = new Choice()).add("Window = Rectangular");
        this.windowChooser.add("Window = Hamming");
        this.windowChooser.add("Window = Hann");
        this.windowChooser.add("Window = Blackman");
        this.windowChooser.add("Window = Kaiser");
        this.windowChooser.add("Window = Bartlett");
        this.windowChooser.add("Window = Welch");
        this.windowChooser.addItemListener(this);
        this.windowChooser.select(1);
        this.add(this.rateChooser = new Choice());
        this.rateChooser.add("Sampling Rate = 8000");
        this.rateChooser.add("Sampling Rate = 11025");
        this.rateChooser.add("Sampling Rate = 16000");
        this.rateChooser.add("Sampling Rate = 22050");
        this.rateChooser.select(1);
        this.sampleRate = 11025;
        this.rateChooser.addItemListener(this);
        this.auxLabels = new Label[5];
        this.auxBars = new Scrollbar[5];
        for (int l = 0; l != 5; ++l) {
            this.add(this.auxLabels[l] = new Label("", 1));
            this.add(this.auxBars[l] = new Scrollbar(0, 25, 1, 1, 999));
            this.auxBars[l].addAdjustmentListener(this);
        }
        this.add(this.inputLabel = new Label("Input Frequency", 1));
        this.add(this.inputBar = new Scrollbar(0, 400, 1, 1, 999));
        this.inputBar.addAdjustmentListener(this);
        this.add(this.kaiserLabel = new Label("Kaiser Parameter", 1));
        this.add(this.kaiserBar = new Scrollbar(0, 500, 1, 1, 999));
        this.kaiserBar.addAdjustmentListener(this);
        this.random = new Random();
        this.setInputLabel();
        this.reinit();
        this.cv.setBackground(Color.black);
        this.cv.setForeground(Color.lightGray);
        (this.showFormat = NumberFormat.getInstance()).setMaximumFractionDigits(2);
        this.resize(640, 640);
        this.handleResize();
        final Dimension size = this.getSize();
        final Dimension screenSize = this.getToolkit().getScreenSize();
        this.setLocation((screenSize.width - size.width) / 2, (screenSize.height - size.height) / 2);
        this.show();
    }
    
    void reinit() {
        this.setupFilter();
        this.setInputW();
    }
    
    MenuItem getMenuItem(final String s) {
        final MenuItem menuItem = new MenuItem(s);
        menuItem.addActionListener(this);
        return menuItem;
    }
    
    CheckboxMenuItem getCheckItem(final String s, final boolean state) {
        final CheckboxMenuItem checkboxMenuItem = new CheckboxMenuItem(s);
        checkboxMenuItem.setState(state);
        checkboxMenuItem.addItemListener(this);
        return checkboxMenuItem;
    }
    
    int getPower2(final int n) {
        int i;
        for (i = 2; i < n; i *= 2) {}
        return i;
    }
    
    PhaseColor genPhaseColor(final int n, double n2) {
        n2 += n * 3.141592653589793 / 4.0;
        n2 *= 0.954929658551372;
        final int n3 = (int)n2;
        final double n4 = n2 % 1.0;
        final double n5 = 1.0 - n4;
        PhaseColor phaseColor = null;
        switch (n3) {
            case 0:
            case 6: {
                phaseColor = new PhaseColor(1.0, n4, 0.0);
                break;
            }
            case 1: {
                phaseColor = new PhaseColor(n5, 1.0, 0.0);
                break;
            }
            case 2: {
                phaseColor = new PhaseColor(0.0, 1.0, n4);
                break;
            }
            case 3: {
                phaseColor = new PhaseColor(0.0, n5, 1.0);
                break;
            }
            case 4: {
                phaseColor = new PhaseColor(n4, 0.0, 1.0);
                break;
            }
            case 5: {
                phaseColor = new PhaseColor(1.0, 0.0, n5);
                break;
            }
        }
        return phaseColor;
    }
    
    void handleResize() {
        final Dimension size = this.cv.getSize();
        this.winSize = size;
        final Dimension dimension = size;
        if (this.winSize.width == 0) {
            return;
        }
        int n = 1;
        final View respView = null;
        this.pipeView = respView;
        this.waveformView = respView;
        this.stepView = respView;
        this.phaseView = respView;
        this.impulseView = respView;
        this.spectrumView = respView;
        this.respView = respView;
        if (this.freqCheckItem.getState()) {
            ++n;
        }
        if (this.phaseCheckItem.getState()) {
            ++n;
        }
        if (this.spectrumCheckItem.getState()) {
            ++n;
        }
        if (this.waveformCheckItem.getState()) {
            ++n;
        }
        if (this.impulseCheckItem.getState()) {
            ++n;
        }
        if (this.stepCheckItem.getState()) {
            ++n;
        }
        ++n;
        final int n2 = dimension.height / n;
        this.dbimage = this.createImage(dimension.width, dimension.height);
        int n3 = 0;
        if (this.freqCheckItem.getState()) {
            this.respView = this.getView(n3++, n);
        }
        if (this.phaseCheckItem.getState()) {
            this.phaseView = this.getView(n3++, n);
        }
        if (this.spectrumCheckItem.getState()) {
            this.spectrumView = this.getView(n3++, n);
        }
        if (this.waveformCheckItem.getState()) {
            this.waveformView = this.getView(n3++, n);
        }
        if (this.impulseCheckItem.getState()) {
            this.impulseView = this.getView(n3++, n);
        }
        if (this.stepCheckItem.getState()) {
            this.stepView = this.getView(n3++, n);
        }
        this.pipeView = this.getView(n3++, n);
        this.poleInfoView = this.getView(n3++, n);
        if (this.poleInfoView.height > 200) {
            this.poleInfoView.height = 200;
        }
        this.polesView = new View(this.poleInfoView.x, this.poleInfoView.y, this.poleInfoView.height, this.poleInfoView.height);
    }
    
    View getView(final int n, final int n2) {
        final int n3 = this.winSize.height / n2;
        final int n4 = 5;
        final int n5 = 15;
        return new View(n4, n4 + n * n3 + n5, this.winSize.width - n4 * 2, n3 - n4 * 2 - n5);
    }
    
    void centerString(final Graphics graphics, final String s, final int n) {
        graphics.drawString(s, (this.winSize.width - graphics.getFontMetrics().stringWidth(s)) / 2, n);
    }
    
    public void paint(final Graphics graphics) {
        this.cv.repaint();
    }
    
    public void updateVowel(final Graphics graphics) {
        final Graphics graphics2 = this.dbimage.getGraphics();
        if (this.winSize == null || this.winSize.width == 0 || this.dbimage == null) {
            return;
        }
        final long currentTimeMillis = System.currentTimeMillis();
        if (this.lastTime == 0L) {
            this.lastTime = currentTimeMillis;
        }
        this.t += (currentTimeMillis - this.lastTime) * 0.008;
        this.lastTime = currentTimeMillis;
        if (this.curFilter == null) {
            final Filter genFilter = this.filterType.genFilter();
            this.curFilter = genFilter;
            if (this.playThread != null) {
                this.playThread.setFilter(genFilter);
            }
            this.filterChanged = true;
            this.unstable = false;
        }
        if (this.playThread == null && !this.unstable && this.soundCheck.getState()) {
            (this.playThread = new PlayThread()).start();
        }
        if (this.displayCheck.getState()) {
            return;
        }
        graphics2.setColor(this.cv.getBackground());
        graphics2.fillRect(0, 0, this.winSize.width, this.winSize.height);
        graphics2.setColor(this.cv.getForeground());
        this.minlog = Math.log(40.0 / this.sampleRate);
        this.logrange = Math.log(0.5) - this.minlog;
        final Complex complex = new Complex();
        if (this.respView != null) {
            this.respView.drawLabel(graphics2, "Frequency Response");
            graphics2.setColor(Color.darkGray);
            graphics2.fillRect(this.respView.x, this.respView.y, this.respView.width, this.respView.height);
            graphics2.setColor(Color.black);
            final double n = 0.069;
            int n2 = 0;
            while (true) {
                final double n3 = n * n2;
                if (n3 > 1.0) {
                    break;
                }
                final int n4 = this.respView.y + (int)(n3 * this.respView.height);
                graphics2.drawLine(this.respView.x, n4, this.respView.right, n4);
                n2 += 2;
            }
            int n5 = 1;
            while (true) {
                final double n6 = this.logrange - n5 * Math.log(2.0);
                int n7;
                if (this.logFreqCheckItem.getState()) {
                    n7 = (int)(n6 * this.respView.width / this.logrange);
                }
                else {
                    n7 = this.respView.width / (1 << n5);
                }
                if (n7 <= 0) {
                    break;
                }
                final int n8 = n7 + this.respView.x;
                graphics2.drawLine(n8, this.respView.y, n8, this.respView.bottom);
                ++n5;
            }
            graphics2.setColor(Color.white);
            int n9 = -1;
            int n10 = -1;
            for (int i = 0; i != this.respView.width; ++i) {
                double n11;
                if (!this.logFreqCheckItem.getState()) {
                    n11 = 3.141592653589793 * i / this.respView.width;
                }
                else {
                    n11 = 6.283185307179586 * Math.exp(this.minlog + i * this.logrange / this.respView.width);
                }
                this.filterType.getResponse(n11, complex);
                final double magSquared = complex.magSquared();
                double n12 = -n * Math.log(magSquared * magSquared) / 2.302585092994046;
                if (this.linRespCheckItem.getState()) {
                    n12 = 1.0 - complex.mag;
                }
                final int n13 = i + this.respView.x;
                if (n12 > 1.0) {
                    if (n9 != -1) {
                        graphics2.drawLine(n9, n10, n9, this.respView.bottom);
                    }
                    n9 = -1;
                }
                else {
                    final int n14 = this.respView.y + (int)(this.respView.height * n12);
                    if (n9 != -1) {
                        graphics2.drawLine(n9, n10, n13, n14);
                    }
                    else if (n13 > this.respView.x) {
                        graphics2.drawLine(n13, this.respView.bottom, n13, n14);
                    }
                    n9 = n13;
                    n10 = n14;
                }
            }
        }
        graphics2.setColor(Color.white);
        if (this.phaseView != null) {
            this.phaseView.drawLabel(graphics2, "Phase Response");
            graphics2.setColor(Color.darkGray);
            graphics2.fillRect(this.phaseView.x, this.phaseView.y, this.phaseView.width, this.phaseView.height);
            graphics2.setColor(Color.black);
            for (int j = 0; j < 5; ++j) {
                final int n15 = this.phaseView.y + (int)(j * 0.25 * this.phaseView.height);
                graphics2.drawLine(this.phaseView.x, n15, this.phaseView.right, n15);
            }
            int n16 = 1;
            while (true) {
                final double n17 = this.logrange - n16 * Math.log(2.0);
                int n18;
                if (this.logFreqCheckItem.getState()) {
                    n18 = (int)(n17 * this.phaseView.width / this.logrange);
                }
                else {
                    n18 = this.phaseView.width / (1 << n16);
                }
                if (n18 <= 0) {
                    break;
                }
                final int n19 = n18 + this.phaseView.x;
                graphics2.drawLine(n19, this.phaseView.y, n19, this.phaseView.bottom);
                ++n16;
            }
            graphics2.setColor(Color.white);
            int n20 = -1;
            int n21 = -1;
            for (int k = 0; k != this.phaseView.width; ++k) {
                double n22;
                if (!this.logFreqCheckItem.getState()) {
                    n22 = 3.141592653589793 * k / this.phaseView.width;
                }
                else {
                    n22 = 6.283185307179586 * Math.exp(this.minlog + k * this.logrange / this.phaseView.width);
                }
                this.filterType.getResponse(n22, complex);
                final int n23 = this.phaseView.y + (int)(this.phaseView.height * (0.5 - complex.phase / 6.283185307179586));
                final int n24 = k + this.phaseView.x;
                if (n20 != -1) {
                    graphics2.drawLine(n20, n21, n24, n23);
                }
                else if (n24 > this.phaseView.x) {
                    graphics2.drawLine(n24, this.phaseView.bottom, n24, n23);
                }
                n20 = n24;
                n21 = n23;
            }
        }
        if (this.pipeView != null && this.filterType instanceof PipeFIRFilter) {
            this.pipeView.drawLabel(graphics2, "Cross Section");
            graphics2.setColor(Color.darkGray);
            graphics2.fillRect(this.pipeView.x, this.pipeView.y, this.pipeView.width, this.pipeView.height);
            graphics2.setColor(Color.black);
            this.pipeView.mult = 0.14285714285714285;
            for (int l = 0; l != 10; ++l) {
                final double n25 = this.pipeView.y + this.pipeView.height * (0.5 - l * this.pipeView.mult);
                if (n25 < this.pipeView.y) {
                    break;
                }
                graphics2.drawLine(0, (int)n25, this.winSize.width - 1, (int)n25);
                final double n26 = this.pipeView.y + this.pipeView.height * (0.5 + l * this.pipeView.mult);
                graphics2.drawLine(0, (int)n26, this.winSize.width - 1, (int)n26);
            }
            for (int n27 = 0; n27 * 0.01 <= this.pipeLen; ++n27) {
                final int n28 = this.pipeView.x + (int)(this.pipeView.width * n27 * 0.01 / this.pipeLen);
                graphics2.drawLine(n28, this.pipeView.y, n28, this.pipeView.y + this.pipeView.height);
            }
            double n29 = 0.0;
            Complex[] array = null;
            graphics2.setColor(Color.white);
            if ((this.respView != null && this.respView.contains(this.mouseX, this.mouseY)) || (this.spectrumView != null && this.spectrumView.contains(this.mouseX, this.mouseY))) {
                n29 = this.getFreqFromX(this.mouseX, this.respView) * this.sampleRate;
                array = new Complex[this.pipeRadius.length + 1];
                this.calcWave(n29, array);
                final double n30 = 6.283185307179586 * n29 / 35396.0 * (this.pipeLen * 100.0 / this.pipeRadius.length);
            }
            for (int n31 = 0; n31 != this.pipeRadius.length; ++n31) {
                if (n29 > 0.0) {
                    array[n31].rotate(this.t);
                    int n32 = (int)(128.0 + 127.0 * (array[n31].re / 2.0));
                    if (n32 < 0) {
                        n32 = 0;
                    }
                    if (n32 > 255) {
                        n32 = 255;
                    }
                    graphics2.setColor(new Color(n32, n32, n32));
                }
                final int n33 = this.pipeView.width * n31 / this.pipeRadius.length + this.pipeView.x;
                final int n34 = this.pipeView.width * (n31 + 1) / this.pipeRadius.length + this.pipeView.x;
                final int n35 = this.pipeView.y + (int)(this.pipeView.height * (0.5 - this.pipeRadius[n31] * this.pipeView.mult));
                graphics2.fillRect(n33, n35, n34 - n33, this.pipeView.y + (int)(this.pipeView.height * (0.5 + this.pipeRadius[n31] * this.pipeView.mult)) - n35);
            }
        }
        final int poleCount = this.filterType.getPoleCount();
        final int zeroCount = this.filterType.getZeroCount();
        int n36 = 10;
        int n37 = 0;
        int n38 = 0;
        int n39 = 0;
        int n40 = 0;
        if (this.poleInfoView != null && (poleCount > 0 || zeroCount > 0 || this.ferrisCheckItem.getState())) {
            n37 = (n38 = this.polesView.height / 2);
            n39 = this.polesView.x + n38;
            n40 = this.polesView.y + n37;
            n36 = n39 + n38 + 10;
            if (!this.ferrisCheckItem.getState()) {
                graphics2.setColor(Color.white);
                final FontMetrics fontMetrics = graphics2.getFontMetrics();
                final String s = "Poles/Zeros";
                graphics2.drawString(s, n39 - fontMetrics.stringWidth(s) / 2, this.polesView.y - 5);
                graphics2.drawOval(n39 - n38, n40 - n37, n38 * 2, n37 * 2);
                graphics2.drawLine(n39, n40 - n37, n39, n40 + n37);
                graphics2.drawLine(n39 - n37, n40, n39 + n37, n40);
                final Complex complex2 = new Complex();
                for (int n41 = 0; n41 != poleCount; ++n41) {
                    this.filterType.getPole(n41, complex2);
                    graphics2.setColor((n41 == this.selectedPole) ? Color.yellow : Color.white);
                    final int n42 = n39 + (int)(n38 * complex2.re);
                    final int n43 = n40 - (int)(n37 * complex2.im);
                    graphics2.drawLine(n42 - 3, n43 - 3, n42 + 3, n43 + 3);
                    graphics2.drawLine(n42 - 3, n43 + 3, n42 + 3, n43 - 3);
                }
                for (int n44 = 0; n44 != zeroCount; ++n44) {
                    this.filterType.getZero(n44, complex2);
                    graphics2.setColor((n44 == this.selectedZero) ? Color.yellow : Color.white);
                    graphics2.drawOval(n39 + (int)(n38 * complex2.re) - 3, n40 - (int)(n37 * complex2.im) - 3, 6, 6);
                }
            }
        }
        if (this.poleInfoView != null) {
            graphics2.setColor(Color.white);
            final String[] array2 = new String[10];
            this.filterType.getInfo(array2);
            int n45;
            for (n45 = 0; n45 != 10 && array2[n45] != null; ++n45) {}
            if (this.wformInfo.needsFrequency()) {
                array2[n45++] = "Input Freq = " + (int)(this.inputW * this.sampleRate / 6.283185307179586) + " Hz";
            }
            int n46;
            for (n46 = 0; n46 != 10 && array2[n46] != null; ++n46) {
                graphics2.drawString(array2[n46], n36, this.poleInfoView.y + 5 + 20 * n46);
            }
            if ((this.respView != null && this.respView.contains(this.mouseX, this.mouseY)) || (this.spectrumView != null && this.spectrumView.contains(this.mouseX, this.mouseY))) {
                final double freqFromX = this.getFreqFromX(this.mouseX, this.respView);
                if (freqFromX >= 0.0) {
                    final double n47 = 6.283185307179586 * freqFromX;
                    final double n48 = freqFromX * this.sampleRate;
                    graphics2.setColor(Color.yellow);
                    String s2 = "f = " + (int)n48;
                    if (this.respView.contains(this.mouseX, this.mouseY)) {
                        this.filterType.getResponse(n47, complex);
                        final double magSquared2 = complex.magSquared();
                        s2 = s2 + " Hz, " + this.showFormat.format(10.0 * (Math.log(magSquared2 * magSquared2) / 4.605170185988092)) + " dB, \u03bb = " + this.showFormat.format(35396.0 / n48) + " cm";
                    }
                    graphics2.drawString(s2, n36, this.poleInfoView.y + 5 + 20 * n46);
                    if (n37 > 0) {
                        final int n49 = n39 + (int)(n38 * Math.cos(n47));
                        final int n50 = n40 - (int)(n38 * Math.sin(n47));
                        if (this.ferrisCheckItem.getState()) {
                            graphics2.setColor(Color.black);
                            graphics2.fillOval(n49 - 3, n50 - 3, 7, 7);
                        }
                        graphics2.setColor(Color.yellow);
                        graphics2.fillOval(n49 - 2, n50 - 2, 5, 5);
                    }
                }
            }
        }
        if (this.impulseView != null) {
            this.impulseView.drawLabel(graphics2, "Impulse Response");
            graphics2.setColor(Color.darkGray);
            graphics2.fillRect(this.impulseView.x, this.impulseView.y, this.impulseView.width, this.impulseView.height);
            graphics2.setColor(Color.black);
            graphics2.drawLine(this.impulseView.x, this.impulseView.y + this.impulseView.height / 2, this.impulseView.x + this.impulseView.width - 1, this.impulseView.y + this.impulseView.height / 2);
            graphics2.setColor(Color.white);
            final int impulseOffset = this.curFilter.getImpulseOffset();
            final double[] impulseResponse = this.curFilter.getImpulseResponse(impulseOffset);
            int impulseLen = this.curFilter.getImpulseLen(impulseOffset, impulseResponse);
            int n51 = -1;
            int n52 = -1;
            final double n53 = 0.5 / this.max(impulseResponse);
            final int n54 = (impulseLen < 50) ? 50 : impulseLen;
            if (impulseLen < n54 && n54 < impulseResponse.length - impulseOffset) {
                impulseLen = n54;
            }
            for (int n55 = 0; n55 != impulseLen; ++n55) {
                final int n56 = this.impulseView.y + (int)(this.impulseView.height * (0.5 - impulseResponse[impulseOffset + n55] * n53));
                final int n57 = this.impulseView.x + this.impulseView.width * n55 / n54;
                if (impulseLen < 100) {
                    graphics2.drawLine(n57, this.impulseView.y + this.impulseView.height / 2, n57, n56);
                    graphics2.fillOval(n57 - 2, n56 - 2, 5, 5);
                }
                else {
                    if (n51 != -1) {
                        graphics2.drawLine(n51, n52, n57, n56);
                    }
                    n51 = n57;
                    n52 = n56;
                }
            }
        }
        if (this.stepView != null) {
            this.stepView.drawLabel(graphics2, "Step Response");
            graphics2.setColor(Color.darkGray);
            graphics2.fillRect(this.stepView.x, this.stepView.y, this.stepView.width, this.stepView.height);
            graphics2.setColor(Color.black);
            graphics2.drawLine(this.stepView.x, this.stepView.y + this.stepView.height / 2, this.stepView.x + this.stepView.width - 1, this.stepView.y + this.stepView.height / 2);
            graphics2.setColor(Color.white);
            final int stepOffset = this.curFilter.getStepOffset();
            final double[] stepResponse = this.curFilter.getStepResponse(stepOffset);
            int stepLen = this.curFilter.getStepLen(stepOffset, stepResponse);
            int n58 = -1;
            int n59 = -1;
            final double n60 = 0.5 / this.max(stepResponse);
            final int n61 = (stepLen < 50) ? 50 : stepLen;
            if (stepLen < n61 && n61 < stepResponse.length - stepOffset) {
                stepLen = n61;
            }
            for (int n62 = 0; n62 != stepLen; ++n62) {
                final int n63 = this.stepView.y + (int)(this.stepView.height * (0.5 - stepResponse[stepOffset + n62] * n60));
                final int n64 = this.stepView.x + this.stepView.width * n62 / n61;
                if (stepLen < 100) {
                    graphics2.drawLine(n64, this.stepView.y + this.stepView.height / 2, n64, n63);
                    graphics2.fillOval(n64 - 2, n63 - 2, 5, 5);
                }
                else {
                    if (n58 != -1) {
                        graphics2.drawLine(n58, n59, n64, n63);
                    }
                    n58 = n64;
                    n59 = n63;
                }
            }
        }
        if (this.playThread != null) {
            final int spectrumLen = this.playThread.spectrumLen;
            if (this.spectrumBuf == null || this.spectrumBuf.length != spectrumLen * 2) {
                this.spectrumBuf = new double[spectrumLen * 2];
            }
            final int spectrumOffset = this.playThread.spectrumOffset;
            final int fbufmask = this.playThread.fbufmask;
            int n66;
            for (int n65 = n66 = 0; n66 != spectrumLen; ++n66, n65 += 2) {
                final int n67 = fbufmask & spectrumOffset + n66;
                this.spectrumBuf[n65] = this.playThread.fbufLo[n67] + this.playThread.fbufRo[n67];
                this.spectrumBuf[n65 + 1] = 0.0;
            }
        }
        else {
            this.spectrumBuf = null;
        }
        if (this.waveformView != null && this.spectrumBuf != null) {
            this.waveformView.drawLabel(graphics2, "Waveform");
            graphics2.setColor(Color.darkGray);
            graphics2.fillRect(this.waveformView.x, this.waveformView.y, this.waveformView.width, this.waveformView.height);
            graphics2.setColor(Color.black);
            graphics2.drawLine(this.waveformView.x, this.waveformView.y + this.waveformView.height / 2, this.waveformView.x + this.waveformView.width - 1, this.waveformView.y + this.waveformView.height / 2);
            graphics2.setColor(Color.white);
            int n68 = -1;
            int n69 = -1;
            if (this.waveGain < 0.1) {
                this.waveGain = 0.1;
            }
            double n70 = 0.0;
            for (int n71 = 0; n71 != this.spectrumBuf.length; n71 += 2) {
                if (this.spectrumBuf[n71] > n70) {
                    n70 = this.spectrumBuf[n71];
                }
                if (this.spectrumBuf[n71] < -n70) {
                    n70 = -this.spectrumBuf[n71];
                }
            }
            if (this.waveGain > 1.0 / n70) {
                this.waveGain = 1.0 / n70;
            }
            else if (this.waveGain * 1.05 < 1.0 / n70) {
                this.waveGain *= 1.05;
            }
            final double n72 = 0.5 * this.waveGain;
            int n73 = this.waveformView.width;
            if (n73 > this.spectrumBuf.length || this.allWaveformCheckItem.getState()) {
                n73 = this.spectrumBuf.length;
            }
            for (int n74 = 0; n74 < n73; n74 += 2) {
                final int n75 = (int)(this.waveformView.height * (0.5 - this.spectrumBuf[n74] * n72));
                if (n75 > this.waveformView.height) {
                    n68 = -1;
                }
                else {
                    final int n76 = this.waveformView.y + n75;
                    final int n77 = this.waveformView.x + n74 * this.waveformView.width / n73;
                    if (n68 != -1) {
                        graphics2.drawLine(n68, n69, n77, n76);
                    }
                    n68 = n77;
                    n69 = n76;
                }
            }
        }
        if (this.spectrumView != null && this.spectrumBuf != null) {
            this.spectrumView.drawLabel(graphics2, "Spectrum");
            graphics2.setColor(Color.darkGray);
            graphics2.fillRect(this.spectrumView.x, this.spectrumView.y, this.spectrumView.width, this.spectrumView.height);
            graphics2.setColor(Color.black);
            final double n78 = 0.138;
            int n79 = 0;
            while (true) {
                final double n80 = n78 * n79;
                if (n80 > 1.0) {
                    break;
                }
                final int n81 = this.spectrumView.y + (int)(n80 * this.spectrumView.height);
                graphics2.drawLine(this.spectrumView.x, n81, this.spectrumView.x + this.spectrumView.width, n81);
                ++n79;
            }
            int n82 = 1;
            while (true) {
                final double n83 = this.logrange - n82 * Math.log(2.0);
                int n84;
                if (this.logFreqCheckItem.getState()) {
                    n84 = (int)(n83 * this.spectrumView.width / this.logrange);
                }
                else {
                    n84 = this.spectrumView.width / (1 << n82);
                }
                if (n84 <= 0) {
                    break;
                }
                final int n85 = n84 + this.spectrumView.x;
                graphics2.drawLine(n85, this.spectrumView.y, n85, this.spectrumView.bottom);
                ++n82;
            }
            graphics2.setColor(Color.white);
            final int n86 = this.spectrumBuf.length / 2;
            final double n87 = 6.283185307179586 / (this.spectrumBuf.length - 2);
            for (int n88 = 0; n88 != this.spectrumBuf.length; n88 += 2) {
                final double n89 = 0.54 - 0.46 * Math.cos(n88 * n87);
                final double[] spectrumBuf = this.spectrumBuf;
                final int n90 = n88;
                spectrumBuf[n90] *= n89;
            }
            if (this.spectrumFFT == null || this.spectrumFFT.size != this.spectrumBuf.length / 2) {
                this.spectrumFFT = new FFT(this.spectrumBuf.length / 2);
            }
            this.spectrumFFT.transform(this.spectrumBuf, false);
            final double n91 = this.spectrumView.width / Math.log(this.spectrumBuf.length / 2 + 1);
            final double n92 = 1.0 / (this.spectrumBuf.length / 2) / 65536.0;
            final double n93 = n92 * n92;
            final double[] array3 = new double[this.spectrumView.width];
            if (this.logFreqCheckItem.getState()) {
                for (int n94 = 0; n94 != this.spectrumBuf.length / 2; n94 += 2) {
                    final int n95 = (int)(array3.length * (Math.log(n94 / this.spectrumBuf.length) - this.minlog) / this.logrange);
                    if (n95 >= 0) {
                        final double[] array4 = array3;
                        final int n96 = n95;
                        array4[n96] += this.spectrumBuf[n94] * this.spectrumBuf[n94] + this.spectrumBuf[n94 + 1] * this.spectrumBuf[n94 + 1];
                    }
                }
            }
            else {
                for (int n97 = 0; n97 != this.spectrumBuf.length / 2; n97 += 2) {
                    final int n98 = array3.length * n97 * 2 / this.spectrumBuf.length;
                    final double[] array5 = array3;
                    final int n99 = n98;
                    array5[n99] += this.spectrumBuf[n97] * this.spectrumBuf[n97] + this.spectrumBuf[n97 + 1] * this.spectrumBuf[n97 + 1];
                }
            }
            final int length = array3.length;
            for (int n100 = 0; n100 != this.spectrumView.width; ++n100) {
                final int n101 = (int)(this.spectrumView.height * (-n78 * Math.log(array3[n100] * n93) / 2.302585092994046));
                if (n101 <= this.spectrumView.height) {
                    final int n102 = this.spectrumView.y + n101;
                    final int n103 = this.spectrumView.x + n100 * this.spectrumView.width / length;
                    graphics2.drawLine(n103, n102, n103, this.spectrumView.y + this.spectrumView.height - 1);
                }
            }
        }
        if (this.spectrumView != null && !this.java2) {
            graphics2.setColor(Color.white);
            this.centerString(graphics2, "Need java 2 for sound", this.spectrumView.y + this.spectrumView.height / 2);
        }
        if (this.unstable) {
            graphics2.setColor(Color.red);
            this.centerString(graphics2, "Filter is unstable", this.winSize.height / 2);
        }
        if (this.mp3Error != null) {
            graphics2.setColor(Color.red);
            this.centerString(graphics2, this.mp3Error, this.winSize.height / 2 + 20);
        }
        if (this.respView != null && this.respView.contains(this.mouseX, this.mouseY)) {
            graphics2.setColor(Color.yellow);
            graphics2.drawLine(this.mouseX, this.respView.y, this.mouseX, this.respView.y + this.respView.height - 1);
        }
        if (this.spectrumView != null && this.spectrumView.contains(this.mouseX, this.mouseY)) {
            graphics2.setColor(Color.yellow);
            graphics2.drawLine(this.mouseX, this.spectrumView.y, this.mouseX, this.spectrumView.y + this.spectrumView.height - 1);
        }
        this.filterChanged = false;
        graphics.drawImage(this.dbimage, 0, 0, this);
    }
    
    void calcWave(final double n, final Complex[] array) {
        final int[] array2 = new int[this.pipeRadius.length + 1];
        for (int i = 0; i != array2.length; ++i) {
            array2[i] = i;
        }
        this.genPipeResponse(this.pipeRadius, n * 2.0, array2, new double[2], array);
        final double n2 = 1.0 / array[0].mag;
        for (int j = 0; j != array.length; ++j) {
            array[j].mult(n2);
        }
    }
    
    void setCutoff(final double n) {
    }
    
    int countPoints(final double[] array, final int n) {
        final int length = array.length;
        double n2 = 0.0;
        int n3 = 0;
        double n4 = 123.0;
        for (int i = n; i < length; ++i) {
            final double abs = Math.abs(array[i]);
            if (abs > n2) {
                n2 = abs;
            }
            if (Math.abs(abs - n4) > n2 * 0.003) {
                n3 = i - n + 1;
            }
            n4 = abs;
        }
        return n3;
    }
    
    double max(final double[] array) {
        double n = 0.0;
        for (int i = 0; i != array.length; ++i) {
            final double abs = Math.abs(array[i]);
            if (abs > n) {
                n = abs;
            }
        }
        return n;
    }
    
    double getFreqFromX(final int n, final View view) {
        final double n2 = 0.5 * (n - view.x) / view.width;
        if (n2 <= 0.0 || n2 >= 0.5) {
            return -1.0;
        }
        if (this.logFreqCheckItem.getState()) {
            return Math.exp(this.minlog + 2.0 * n2 * this.logrange);
        }
        return n2;
    }
    
    void setupFilter() {
        final int selectedIndex = this.filterChooser.getSelectedIndex();
        switch (selectedIndex) {
            case 0: {
                this.filterType = new AVowelFilter();
                break;
            }
            case 1: {
                this.filterType = new OVowelFilter();
                break;
            }
            case 2: {
                this.filterType = new UVowelFilter();
                break;
            }
            case 3: {
                this.filterType = new IVowelFilter();
                break;
            }
            case 4: {
                this.filterType = new EVowelFilter();
                break;
            }
            case 5: {
                this.filterType = new IBarVowelFilter();
                break;
            }
            case 6: {
                this.filterType = new AVowelFilterSimple();
                break;
            }
            case 7: {
                this.filterType = new IVowelFilterSimple();
                break;
            }
            case 8: {
                this.filterType = new AEVowelFilterSimple();
                break;
            }
            case 9: {
                this.filterType = new IhVowelFilterSimple();
                break;
            }
            case 10: {
                this.filterType = new OoVowelFilterSimple();
                break;
            }
            case 11: {
                this.filterType = new YVowelFilterSimple();
                break;
            }
            case 12: {
                this.filterType = new OpenTubeFilter();
                break;
            }
            case 13: {
                this.filterType = new CustomFilter();
                break;
            }
            case 14: {
                this.filterType = new NoFilter();
                break;
            }
        }
        if (this.filterSelection != selectedIndex) {
            this.filterSelection = selectedIndex;
            for (int i = 0; i != this.auxBars.length; ++i) {
                this.auxBars[i].setMaximum(999);
            }
            final int select = this.filterType.select();
            for (int j = 0; j != select; ++j) {
                this.auxLabels[j].show();
                this.auxBars[j].show();
            }
            for (int k = select; k != this.auxBars.length; ++k) {
                this.auxLabels[k].hide();
                this.auxBars[k].hide();
            }
            if (this.filterType.needsWindow()) {
                this.windowChooser.show();
                this.setWindow();
            }
            else {
                this.windowChooser.hide();
                this.setWindow();
            }
            this.validate();
        }
        this.filterType.setup();
        this.curFilter = null;
    }
    
    void setInputLabel() {
        this.wformInfo = this.getWaveformObject();
        final String inputText = this.wformInfo.getInputText();
        if (inputText == null) {
            this.inputLabel.hide();
            this.inputBar.hide();
        }
        else {
            this.inputLabel.setText(inputText);
            this.inputLabel.show();
            this.inputBar.show();
        }
        this.validate();
    }
    
    Waveform getWaveformObject() {
        Waveform waveform = null;
        switch (this.inputChooser.getSelectedIndex()) {
            case 0: {
                waveform = new NoiseWaveform();
                break;
            }
            case 1: {
                waveform = new VocalWaveform();
                break;
            }
            case 2: {
                waveform = new SawtoothWaveform();
                break;
            }
            case 3: {
                waveform = new PeriodicNoiseWaveform();
                break;
            }
            case 4: {
                waveform = new TriangleWaveform();
                break;
            }
            case 5: {
                waveform = new SquareWaveform();
                break;
            }
            case 6: {
                waveform = new SineWaveform();
                break;
            }
            case 7: {
                waveform = new SweepWaveform();
                break;
            }
            case 8: {
                waveform = new ImpulseWaveform();
                break;
            }
            default: {
                waveform = new NoiseWaveform();
                break;
            }
        }
        return waveform;
    }
    
    public void componentHidden(final ComponentEvent componentEvent) {
    }
    
    public void componentMoved(final ComponentEvent componentEvent) {
    }
    
    public void componentShown(final ComponentEvent componentEvent) {
        this.cv.repaint(this.pause);
    }
    
    public void componentResized(final ComponentEvent componentEvent) {
        this.handleResize();
        this.cv.repaint(this.pause);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.exitItem) {
            this.destroyFrame();
            return;
        }
        if (actionEvent.getSource() == this.exportButton) {
            this.doImport();
        }
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        if (adjustmentEvent.getSource() != this.inputBar) {
            this.setupFilter();
        }
        System.out.print(((Scrollbar)adjustmentEvent.getSource()).getValue() + "\n");
        if (adjustmentEvent.getSource() == this.inputBar) {
            this.setInputW();
        }
        this.cv.repaint(this.pause);
    }
    
    void setInputW() {
        this.inputW = 3.141592653589793 * this.inputBar.getValue() / 1000.0;
        this.inputW /= 20.0;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.destroyFrame();
            return true;
        }
        return super.handleEvent(event);
    }
    
    void destroyFrame() {
        if (this.playThread != null) {
            this.playThread.requestShutdown();
        }
        if (this.applet == null) {
            this.dispose();
        }
        else {
            this.applet.destroyFrame();
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.mouseX = mouseEvent.getX();
        this.mouseY = mouseEvent.getY();
        this.edit(mouseEvent);
        this.cv.repaint(this.pause);
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        this.mouseX = x;
        this.dragX = x;
        final int y = mouseEvent.getY();
        this.mouseY = y;
        this.dragY = y;
        this.cv.repaint(this.pause);
        if (this.respView != null && this.respView.contains(mouseEvent.getX(), mouseEvent.getY())) {
            this.selection = 1;
        }
        if (this.spectrumView != null && this.spectrumView.contains(mouseEvent.getX(), mouseEvent.getY())) {
            this.selection = 2;
        }
        if (this.pipeView != null && this.pipeView.contains(mouseEvent.getX(), mouseEvent.getY())) {
            this.selection = 3;
        }
    }
    
    void selectPoleZero(final int n, final int n2) {
        final int n3 = -1;
        this.selectedZero = n3;
        this.selectedPole = n3;
        final int n5;
        final int n4 = n5 = this.polesView.height / 2;
        final int n6 = this.polesView.x + n5;
        final int n7 = this.polesView.y + n4;
        final Complex complex = new Complex();
        final int poleCount = this.filterType.getPoleCount();
        final int zeroCount = this.filterType.getZeroCount();
        int n8 = 10000;
        for (int i = 0; i != poleCount; ++i) {
            this.filterType.getPole(i, complex);
            final int distanceSq = this.distanceSq(n6 + (int)(n5 * complex.re), n7 - (int)(n4 * complex.im), n, n2);
            if (distanceSq <= n8) {
                n8 = distanceSq;
                this.selectedPole = i;
                this.selectedZero = -1;
            }
        }
        for (int j = 0; j != zeroCount; ++j) {
            this.filterType.getZero(j, complex);
            final int distanceSq2 = this.distanceSq(n6 + (int)(n5 * complex.re), n7 - (int)(n4 * complex.im), n, n2);
            if (distanceSq2 < n8) {
                n8 = distanceSq2;
                this.selectedPole = -1;
                this.selectedZero = j;
            }
        }
    }
    
    int distanceSq(final int n, final int n2, final int n3, final int n4) {
        return (n - n3) * (n - n3) + (n2 - n4) * (n2 - n4);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.mouseMoved(mouseEvent);
        this.edit(mouseEvent);
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    void edit(final MouseEvent mouseEvent) {
        if (this.selection == 2) {
            if (!this.wformInfo.needsFrequency()) {
                return;
            }
            final double freqFromX = this.getFreqFromX(mouseEvent.getX(), this.spectrumView);
            if (freqFromX < 0.0) {
                return;
            }
            this.inputW = 6.283185307179586 * freqFromX;
            this.inputBar.setValue((int)(2000.0 * freqFromX));
        }
        if (this.selection == 3) {
            this.filterChooser.select(13);
            this.editPipe(mouseEvent);
        }
    }
    
    void editPipe(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        if (this.dragX == x) {
            this.editPipePoint(x, y);
            this.dragY = y;
        }
        else {
            final int n = (x < this.dragX) ? x : this.dragX;
            final int n2 = (x < this.dragX) ? y : this.dragY;
            final int n3 = (x > this.dragX) ? x : this.dragX;
            final int n4 = (x > this.dragX) ? y : this.dragY;
            this.dragX = x;
            this.dragY = y;
            for (int i = n; i <= n3; ++i) {
                this.editPipePoint(i, n2 + (n4 - n2) * (i - n) / (n3 - n));
            }
        }
        this.setupFilter();
    }
    
    void editPipePoint(final int n, final int n2) {
        final int n3 = (n - this.pipeView.x) * this.pipeRadius.length / this.pipeView.width;
        if (n3 < 0 || n3 >= this.pipeRadius.length) {
            return;
        }
        this.pipeRadius[n3] = Math.abs((0.5 - (n2 - this.pipeView.y) / this.pipeView.height) / this.pipeView.mult);
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        this.filterChanged = true;
        if (itemEvent.getSource() == this.displayCheck) {
            this.cv.repaint(this.pause);
            return;
        }
        if (itemEvent.getSource() == this.inputChooser) {
            if (this.playThread != null) {
                this.playThread.requestShutdown();
            }
            this.setInputLabel();
        }
        if (itemEvent.getSource() == this.rateChooser) {
            if (this.playThread != null) {
                this.playThread.requestShutdown();
            }
            this.inputW *= this.sampleRate;
            switch (this.rateChooser.getSelectedIndex()) {
                case 0: {
                    this.sampleRate = 8000;
                    break;
                }
                case 1: {
                    this.sampleRate = 11025;
                    break;
                }
                case 2: {
                    this.sampleRate = 16000;
                    break;
                }
                case 3: {
                    this.sampleRate = 22050;
                    break;
                }
                case 4: {
                    this.sampleRate = 32000;
                    break;
                }
                case 5: {
                    this.sampleRate = 44100;
                    break;
                }
            }
            this.inputW /= this.sampleRate;
        }
        if (itemEvent.getSource() == this.windowChooser) {
            this.setWindow();
        }
        if (itemEvent.getSource() instanceof CheckboxMenuItem) {
            this.handleResize();
        }
        else {
            this.setupFilter();
        }
        this.cv.repaint(this.pause);
    }
    
    void setWindow() {
        if (this.windowChooser.getSelectedIndex() == VowelFrame.WINDOW_KAISER && this.filterType.needsWindow()) {
            this.kaiserLabel.show();
            this.kaiserBar.show();
        }
        else {
            this.kaiserLabel.hide();
            this.kaiserBar.hide();
        }
        this.validate();
    }
    
    void setSampleRate(final int sampleRate) {
        int n = 0;
        switch (sampleRate) {
            case 8000: {
                n = 0;
                break;
            }
            case 11025: {
                n = 1;
                break;
            }
            case 16000: {
                n = 2;
                break;
            }
            case 22050: {
                n = 3;
                break;
            }
            case 32000: {
                n = 4;
                break;
            }
            case 44100: {
                n = 5;
                break;
            }
        }
        this.rateChooser.select(n);
        this.sampleRate = sampleRate;
    }
    
    String getOmegaText(final double n) {
        return (int)(n * this.sampleRate / 6.283185307179586) + " Hz";
    }
    
    double bessi0(final double n) {
        final double abs;
        double n4;
        if ((abs = Math.abs(n)) < 3.75) {
            final double n2 = n / 3.75;
            final double n3 = n2 * n2;
            n4 = 1.0 + n3 * (3.5156229 + n3 * (3.0899424 + n3 * (1.2067492 + n3 * (0.2659732 + n3 * (0.0360768 + n3 * 0.0045813)))));
        }
        else {
            final double n5 = 3.75 / abs;
            n4 = Math.exp(abs) / Math.sqrt(abs) * (0.39894228 + n5 * (0.01328592 + n5 * (0.00225319 + n5 * (-0.00157565 + n5 * (0.00916281 + n5 * (-0.02057706 + n5 * (0.02635537 + n5 * (-0.01647633 + n5 * 0.00392377))))))));
        }
        return n4;
    }
    
    void genPipeResponse(final double[] array, final double n, final int[] array2, final double[] array3, final Complex[] array4) {
        final int n2 = 1 + array.length;
        final double n3 = 40.3;
        final double n4 = this.pipeLen / array2[array2.length - 1] * 100.0;
        final double[] array5 = new double[n2 + 1];
        array5[0] = 200.0;
        for (int i = 0; i != array.length; ++i) {
            array5[i + 1] = n3 / (3.141592653589793 * array[i] * array[i]);
        }
        final Complex[][] array6 = new Complex[n2 * 2][4];
        final Complex[] array7 = new Complex[n2 * 2];
        for (int j = 1; j != array3.length; ++j) {
            final double n5 = n * j / array3.length;
            final double n6 = n5 * 2.0 * 3.141592653589793 / 35396.0;
            array5[array.length + 1] = n3 * n6 * n6 / 12.566370614359172;
            array5[array.length + 1] = array5[array.length] / 4.0;
            for (int k = 0; k != n2; ++k) {
                final int n7 = k * 2;
                final double n8 = array2[k] * n4;
                final int n9 = n7 + 1;
                double n10 = (k == n2 - 1) ? 0.0 : (0.007 * Math.sqrt(1.0 / (array[k] * array[k])) * (0.5 + n5 / 4000.0));
                if (!this.attenuationCheck.getState()) {
                    n10 = 0.0;
                }
                array6[n7][0] = this.iexp(-n6 * n8, -n10 * n8, 1.0);
                array6[n7][1] = this.iexp(n6 * n8, n10 * n8, 1.0);
                array6[n7][2] = this.iexp(-n6 * n8, -n10 * n8, -1.0);
                array6[n7][3] = this.iexp(n6 * n8, n10 * n8, -1.0);
                array6[n9][0] = this.iexp(-n6 * n8, -n10 * n8, 1.0 / array5[k]);
                array6[n9][1] = this.iexp(n6 * n8, n10 * n8, -1.0 / array5[k]);
                array6[n9][2] = this.iexp(-n6 * n8, -n10 * n8, -1.0 / array5[k + 1]);
                array6[n9][3] = this.iexp(n6 * n8, n10 * n8, 1.0 / array5[k + 1]);
            }
            final double n11 = n5 / 100.0;
            array3[j] = this.solve(array6, n2 * 2, array4) * (this.envelopeCheck.getState() ? (n11 / (1.0 + n11 * n11)) : 1.0);
        }
        array3[0] = array3[1];
    }
    
    double solve(final Complex[][] array, final int n, final Complex[] array2) {
        final Complex complex = new Complex(1.0, 0.0);
        final Complex complex2 = new Complex(0.0, 0.0);
        final Complex complex3 = new Complex();
        final Complex complex4 = new Complex();
        final Complex complex5 = new Complex();
        for (int i = n - 2; i >= 0; i -= 2) {
            complex4.set(0.0);
            complex4.addMult(-1.0, array[i][2], complex);
            complex4.addMult(-1.0, array[i][3], complex2);
            complex5.set(0.0);
            complex5.addMult(-1.0, array[i + 1][2], complex);
            complex5.addMult(-1.0, array[i + 1][3], complex2);
            complex3.set(0.0);
            complex3.addMult(1.0, array[i][0], array[i + 1][1]);
            complex3.addMult(-1.0, array[i][1], array[i + 1][0]);
            complex.set(0.0);
            complex.addMult(-1.0, array[i][1], complex5);
            complex.addMult(1.0, array[i + 1][1], complex4);
            complex.divide(complex3);
            complex2.set(0.0);
            complex2.addMult(1.0, array[i][0], complex5);
            complex2.addMult(-1.0, array[i + 1][0], complex4);
            complex2.divide(complex3);
            if (array2 != null) {
                final int n2 = i / 2;
                final Complex complex6 = new Complex(complex);
                array2[n2] = complex6;
                final Complex complex7 = complex6;
                complex7.mult(array[i][0]);
                final Complex complex8 = new Complex(complex2);
                complex8.mult(array[i][1]);
                complex7.add(complex8);
            }
        }
        return 1.0 / complex2.mag;
    }
    
    Complex iexp(final double n, final double n2, final double n3) {
        final Complex complex = new Complex();
        complex.setMagPhase(Math.exp(n2), n);
        complex.mult(n3);
        return complex;
    }
    
    void doImport() {
        if (this.impDialog != null) {
            this.requestFocus();
            this.impDialog.setVisible(false);
            this.impDialog = null;
        }
        String s = "$ 0 " + this.pipeLen + " " + this.pipeRadius.length + "\n";
        for (int i = 0; i != this.pipeRadius.length; ++i) {
            s = s + "p " + this.pipeRadius[i] + "\n";
        }
        (this.impDialog = new ImportDialog(this, s)).show();
    }
    
    void readImport(final String s) {
        final byte[] bytes = s.getBytes();
        final int length = s.length();
        int n = 0;
        this.filterChooser.select(13);
        int j;
        for (int i = 0; i < length; i += j) {
            int n2 = 0;
            j = 0;
            while (j != length - i) {
                if (bytes[j + i] == 10 || bytes[j + i] == 13) {
                    n2 = j++;
                    if (j + i < bytes.length && bytes[j + i] == 10) {
                        ++j;
                        break;
                    }
                    break;
                }
                else {
                    ++j;
                }
            }
            final StringTokenizer stringTokenizer = new StringTokenizer(new String(bytes, i, n2));
            if (stringTokenizer.hasMoreTokens()) {
                final char char1 = stringTokenizer.nextToken().charAt(0);
                try {
                    if (char1 == '$') {
                        new Integer(stringTokenizer.nextToken());
                        this.pipeLen = new Double(stringTokenizer.nextToken());
                        new Integer(stringTokenizer.nextToken());
                    }
                    else if (char1 == 'p') {
                        this.pipeRadius[n++] = new Double(stringTokenizer.nextToken());
                    }
                    else {
                        System.out.println("unknown type!");
                    }
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        this.setupFilter();
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        VowelFrame.WINDOW_KAISER = 4;
    }
    
    class View extends Rectangle
    {
        public double mult;
        int right;
        int bottom;
        
        View(final Dimension dimension) {
            super(dimension);
        }
        
        View(final int n, final int n2, final int n3, final int n4) {
            super(n, n2, n3, n4);
            this.right = n + n3 - 1;
            this.bottom = n2 + n4 - 1;
        }
        
        void drawLabel(final Graphics graphics, final String s) {
            graphics.setColor(Color.white);
            VowelFrame.this.centerString(graphics, s, this.y - 5);
        }
    }
    
    class PhaseColor
    {
        public double r;
        public double g;
        public double b;
        
        PhaseColor(final double r, final double g, final double b) {
            this.r = r;
            this.g = g;
            this.b = b;
        }
    }
    
    class FFT
    {
        double[] wtabf;
        double[] wtabi;
        int size;
        
        FFT(final int size) {
            this.size = size;
            if ((this.size & this.size - 1) != 0x0) {
                System.out.println("size must be power of two!");
            }
            this.calcWTable();
        }
        
        void calcWTable() {
            this.wtabf = new double[this.size];
            this.wtabi = new double[this.size];
            for (int i = 0; i != this.size; i += 2) {
                final double n = 3.1415926535 * i / this.size;
                this.wtabf[i] = Math.cos(n);
                this.wtabf[i + 1] = Math.sin(n);
                this.wtabi[i] = this.wtabf[i];
                this.wtabi[i + 1] = -this.wtabf[i + 1];
            }
        }
        
        void transform(final double[] array, final boolean b) {
            int n = 0;
            final int n2 = this.size * 2;
            if ((this.size & this.size - 1) != 0x0) {
                System.out.println("size must be power of two!");
            }
            for (int i = 0; i != n2; i += 2) {
                if (i > n) {
                    final double n3 = array[i];
                    array[i] = array[n];
                    array[n] = n3;
                    final double n4 = array[i + 1];
                    array[i + 1] = array[n + 1];
                    array[n + 1] = n4;
                }
                int size;
                for (size = this.size; (size & n) != 0x0; n &= ~size, size >>= 1) {}
                n |= size;
            }
            final int n5 = this.size << 1;
            final double[] array2 = b ? this.wtabi : this.wtabf;
            for (int j = 0; j != n2; j += 4) {
                final double n6 = array[j];
                final double n7 = array[j + 1];
                final double n8 = array[j + 2];
                final double n9 = array[j + 3];
                array[j] = n6 + n8;
                array[j + 1] = n7 + n9;
                array[j + 2] = n6 - n8;
                array[j + 3] = n7 - n9;
            }
            int n10 = n5 >> 1;
            final int n11 = b ? -1 : 1;
            if (n2 >= 8) {
                for (int k = 0; k != n2; k += 8) {
                    final double n12 = array[k];
                    final double n13 = array[k + 1];
                    final double n14 = array[k + 4];
                    final double n15 = array[k + 5];
                    array[k] = n12 + n14;
                    array[k + 1] = n13 + n15;
                    array[k + 4] = n12 - n14;
                    array[k + 5] = n13 - n15;
                    final double n16 = array[k + 2];
                    final double n17 = array[k + 3];
                    final double n18 = array[k + 6] * n11;
                    final double n19 = array[k + 7] * n11;
                    array[k + 2] = n16 - n19;
                    array[k + 3] = n17 + n18;
                    array[k + 6] = n16 + n19;
                    array[k + 7] = n17 - n18;
                }
                n10 >>= 1;
            }
            for (int l = 16; l <= n2; l <<= 1) {
                final int n20 = l >> 1;
                n10 >>= 1;
                for (int n21 = 0; n21 != 1000; ++n21) {}
                for (int n22 = 0; n22 < n2; n22 += l) {
                    for (int n23 = 0, n24 = n22; n24 != n22 + n20; n24 += 2, n23 += n10) {
                        final double n25 = array2[n23];
                        final double n26 = array2[n23 + 1];
                        final double n27 = array[n24];
                        final double n28 = array[n24 + 1];
                        final int n29 = n24 + n20;
                        final double n30 = array[n29];
                        final double n31 = array[n29 + 1];
                        final double n32 = n30 * n25 - n31 * n26;
                        final double n33 = n30 * n26 + n31 * n25;
                        array[n24] = n27 + n32;
                        array[n24 + 1] = n28 + n33;
                        array[n29] = n27 - n32;
                        array[n29 + 1] = n28 - n33;
                    }
                }
            }
        }
    }
    
    abstract class Waveform
    {
        short[] buffer;
        
        boolean start() {
            return true;
        }
        
        abstract int getData();
        
        int getChannels() {
            return 2;
        }
        
        void getBuffer() {
            this.buffer = new short[VowelFrame.this.getPower2(VowelFrame.this.sampleRate / 12) * this.getChannels()];
        }
        
        String getInputText() {
            return "Input Frequency";
        }
        
        boolean needsFrequency() {
            return true;
        }
    }
    
    class NoiseWaveform extends Waveform
    {
        boolean start() {
            this.getBuffer();
            return true;
        }
        
        int getData() {
            for (int i = 0; i != this.buffer.length; ++i) {
                this.buffer[i] = (short)VowelFrame.this.random.nextInt();
            }
            return this.buffer.length;
        }
        
        String getInputText() {
            return null;
        }
        
        boolean needsFrequency() {
            return false;
        }
    }
    
    class PeriodicNoiseWaveform extends Waveform
    {
        short[] smbuf;
        int ix;
        
        int getChannels() {
            return 1;
        }
        
        boolean start() {
            this.getBuffer();
            this.smbuf = new short[1];
            this.ix = 0;
            return true;
        }
        
        int getData() {
            final int n = (int)(6.283185307179586 / VowelFrame.this.inputW);
            if (n != this.smbuf.length) {
                this.smbuf = new short[n];
                for (int i = 0; i != n; ++i) {
                    this.smbuf[i] = (short)VowelFrame.this.random.nextInt();
                }
            }
            for (int j = 0; j != this.buffer.length; ++j, ++this.ix) {
                if (this.ix >= n) {
                    this.ix = 0;
                }
                this.buffer[j] = this.smbuf[this.ix];
            }
            return this.buffer.length;
        }
    }
    
    class SineWaveform extends Waveform
    {
        int ix;
        
        int getChannels() {
            return 1;
        }
        
        boolean start() {
            this.getBuffer();
            this.ix = 0;
            return true;
        }
        
        int getData() {
            for (int i = 0; i != this.buffer.length; ++i) {
                ++this.ix;
                this.buffer[i] = (short)(Math.sin(this.ix * VowelFrame.this.inputW) * 32000.0);
            }
            return this.buffer.length;
        }
    }
    
    class TriangleWaveform extends Waveform
    {
        int ix;
        short[] smbuf;
        
        int getChannels() {
            return 1;
        }
        
        boolean start() {
            this.getBuffer();
            this.ix = 0;
            this.smbuf = new short[1];
            return true;
        }
        
        int getData() {
            final int n = (int)(6.283185307179586 / VowelFrame.this.inputW);
            if (n != this.smbuf.length) {
                this.smbuf = new short[n];
                double n2;
                int i;
                for (n2 = n / 2.0, i = 0; i < n2; ++i) {
                    this.smbuf[i] = (short)(i / n2 * 64000.0 - 32000.0);
                }
                while (i != n) {
                    this.smbuf[i] = (short)((2.0 - i / n2) * 64000.0 - 32000.0);
                    ++i;
                }
            }
            for (int j = 0; j != this.buffer.length; ++j, ++this.ix) {
                if (this.ix >= n) {
                    this.ix = 0;
                }
                this.buffer[j] = this.smbuf[this.ix];
            }
            return this.buffer.length;
        }
    }
    
    class SawtoothWaveform extends Waveform
    {
        int ix;
        short[] smbuf;
        
        int getChannels() {
            return 1;
        }
        
        boolean start() {
            this.getBuffer();
            this.ix = 0;
            this.smbuf = new short[1];
            return true;
        }
        
        int getData() {
            final int n = (int)(6.283185307179586 / VowelFrame.this.inputW);
            if (n != this.smbuf.length) {
                this.smbuf = new short[n];
                final double n2 = n / 2.0;
                for (int i = 0; i != n; ++i) {
                    this.smbuf[i] = (short)((i / n2 - 1.0) * 32000.0);
                }
            }
            for (int j = 0; j != this.buffer.length; ++j, ++this.ix) {
                if (this.ix >= n) {
                    this.ix = 0;
                }
                this.buffer[j] = this.smbuf[this.ix];
            }
            return this.buffer.length;
        }
    }
    
    class VocalWaveform extends Waveform
    {
        int ix;
        int period;
        double p2;
        
        VocalWaveform() {
            this.period = 0;
            this.p2 = 0.0;
        }
        
        int getChannels() {
            return 1;
        }
        
        boolean start() {
            this.getBuffer();
            this.ix = 0;
            return true;
        }
        
        int getData() {
            for (int i = 0; i != this.buffer.length; ++i, ++this.ix) {
                if (this.ix >= this.period) {
                    this.ix = 0;
                    this.period = (int)(6.283185307179586 / VowelFrame.this.inputW);
                    this.period += VowelFrame.this.getrand(3) - 1;
                    this.p2 = this.period / 2;
                }
                this.buffer[i] = (short)((this.ix / this.p2 - 1.0) * 32000.0);
            }
            return this.buffer.length;
        }
    }
    
    class SquareWaveform extends Waveform
    {
        int ix;
        double omega;
        short[] smbuf;
        
        int getChannels() {
            return 1;
        }
        
        boolean start() {
            this.getBuffer();
            this.ix = 0;
            this.smbuf = new short[1];
            return true;
        }
        
        int getData() {
            final int n = (int)(6.283185307179586 / VowelFrame.this.inputW);
            if (n != this.smbuf.length) {
                this.smbuf = new short[n];
                int i;
                for (i = 0; i != n / 2; ++i) {
                    this.smbuf[i] = 32000;
                }
                if ((n & 0x1) > 0) {
                    this.smbuf[i++] = 0;
                }
                while (i != n) {
                    this.smbuf[i] = -32000;
                    ++i;
                }
            }
            for (int j = 0; j != this.buffer.length; ++j, ++this.ix) {
                if (this.ix >= n) {
                    this.ix = 0;
                }
                this.buffer[j] = this.smbuf[this.ix];
            }
            return this.buffer.length;
        }
    }
    
    class SweepWaveform extends Waveform
    {
        int ix;
        double omega;
        double nextOmega;
        double t;
        double startOmega;
        
        int getChannels() {
            return 1;
        }
        
        boolean start() {
            this.getBuffer();
            this.ix = 0;
            final double startOmega = 251.32741228718345 / VowelFrame.this.sampleRate;
            this.omega = startOmega;
            this.nextOmega = startOmega;
            this.startOmega = startOmega;
            this.t = 0.0;
            return true;
        }
        
        int getData() {
            double pow = 1.0;
            double n = 0.0;
            final double n2 = 1.0 / (0.66 * VowelFrame.this.sampleRate);
            final double n3 = 1 / (VowelFrame.this.sampleRate * 16);
            if (VowelFrame.this.logFreqCheckItem.getState()) {
                pow = Math.pow(6.283185307179586 / this.startOmega, 2.0 * (n3 + (n2 - n3) * VowelFrame.this.inputBar.getValue() / 1000.0));
            }
            else {
                n = (6.283185307179586 - this.startOmega) * (n3 + (n2 - n3) * VowelFrame.this.inputBar.getValue() / 1000.0);
            }
            for (int i = 0; i != this.buffer.length; ++i) {
                ++this.ix;
                this.t += this.omega;
                if (this.t > 6.283185307179586) {
                    this.t -= 6.283185307179586;
                    this.omega = this.nextOmega;
                    if (this.nextOmega > 3.141592653589793) {
                        final double startOmega = this.startOmega;
                        this.nextOmega = startOmega;
                        this.omega = startOmega;
                    }
                }
                this.buffer[i] = (short)(Math.sin(this.t) * 32000.0);
                this.nextOmega = this.nextOmega * pow + n;
            }
            return this.buffer.length;
        }
        
        String getInputText() {
            return "Sweep Speed";
        }
        
        boolean needsFrequency() {
            return false;
        }
    }
    
    class ImpulseWaveform extends Waveform
    {
        int ix;
        
        int getChannels() {
            return 1;
        }
        
        boolean start() {
            this.getBuffer();
            this.ix = 0;
            return true;
        }
        
        int getData() {
            final int n = 10000 / (VowelFrame.this.inputBar.getValue() / 51 + 1);
            for (int i = 0; i != this.buffer.length; ++i) {
                short n2 = 0;
                if (this.ix % n == 0) {
                    n2 = 32767;
                }
                ++this.ix;
                this.buffer[i] = n2;
            }
            return this.buffer.length;
        }
        
        String getInputText() {
            return "Impulse Frequency";
        }
        
        boolean needsFrequency() {
            return false;
        }
    }
    
    class PlayThread extends Thread
    {
        SourceDataLine line;
        Waveform wform;
        boolean shutdownRequested;
        boolean stereo;
        Filter filt;
        Filter newFilter;
        double[] fbufLi;
        double[] fbufRi;
        double[] fbufLo;
        double[] fbufRo;
        double[] stateL;
        double[] stateR;
        int fbufmask;
        int fbufsize;
        int spectrumOffset;
        int spectrumLen;
        int inbp;
        int outbp;
        int spectCt;
        double[] impulseBuf;
        double[] convolveBuf;
        int convBufPtr;
        FFT convFFT;
        byte[] ob;
        
        PlayThread() {
            this.shutdownRequested = false;
        }
        
        void requestShutdown() {
            this.shutdownRequested = true;
        }
        
        void setFilter(final Filter newFilter) {
            this.newFilter = newFilter;
        }
        
        void openLine() {
            try {
                this.stereo = (this.wform.getChannels() == 2);
                final AudioFormat audioFormat = new AudioFormat(VowelFrame.this.sampleRate, 16, 2, true, false);
                final DataLine.Info info = new DataLine.Info((VowelFrame.class$javax$sound$sampled$SourceDataLine == null) ? (VowelFrame.class$javax$sound$sampled$SourceDataLine = VowelFrame.class$("javax.sound.sampled.SourceDataLine")) : VowelFrame.class$javax$sound$sampled$SourceDataLine, audioFormat);
                if (!AudioSystem.isLineSupported(info)) {
                    throw new LineUnavailableException("sorry, the sound format cannot be played");
                }
                (this.line = (SourceDataLine)AudioSystem.getLine(info)).open(audioFormat, VowelFrame.this.getPower2(VowelFrame.this.sampleRate / 4));
                this.line.start();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        
        public void run() {
            try {
                this.doRun();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            this.line.close();
            VowelFrame.this.cv.repaint();
            VowelFrame.this.playThread = null;
        }
        
        void doRun() {
            VowelFrame.this.rateChooser.enable();
            this.wform = VowelFrame.this.getWaveformObject();
            VowelFrame.this.mp3Error = null;
            VowelFrame.this.unstable = false;
            if (!this.wform.start()) {
                VowelFrame.this.cv.repaint();
                try {
                    Thread.sleep(1000L);
                }
                catch (Exception ex) {}
                return;
            }
            this.fbufsize = 32768;
            this.fbufmask = this.fbufsize - 1;
            this.fbufLi = new double[this.fbufsize];
            this.fbufRi = new double[this.fbufsize];
            this.fbufLo = new double[this.fbufsize];
            this.fbufRo = new double[this.fbufsize];
            this.openLine();
            final boolean inbp = false;
            this.spectCt = (inbp ? 1 : 0);
            this.outbp = (inbp ? 1 : 0);
            this.inbp = (inbp ? 1 : 0);
            final int n = this.stereo ? 2 : 1;
            VowelFrame.this.outputGain = 1.0;
            final Filter curFilter = VowelFrame.this.curFilter;
            this.filt = curFilter;
            this.newFilter = curFilter;
            this.spectrumLen = VowelFrame.this.getPower2(VowelFrame.this.sampleRate / 12);
            int n2 = 0;
            boolean b = true;
            boolean useConvolve = false;
            this.ob = new byte[16384];
            while (!this.shutdownRequested && VowelFrame.this.soundCheck.getState()) {
                if (VowelFrame.this.applet != null) {
                    final Vowel applet = VowelFrame.this.applet;
                    if (Vowel.ogf == null) {
                        break;
                    }
                }
                if (this.newFilter != null) {
                    n2 = 0;
                    b = true;
                    if (this.wform instanceof SweepWaveform || this.wform instanceof SineWaveform) {
                        b = false;
                    }
                    VowelFrame.this.outputGain = 1.0;
                    if (this.filt == null || this.filt.getLength() != this.newFilter.getLength()) {
                        final boolean b2 = false;
                        this.spectCt = (b2 ? 1 : 0);
                        this.outbp = (b2 ? 1 : 0);
                        this.inbp = (b2 ? 1 : 0);
                        this.convBufPtr = (b2 ? 1 : 0);
                    }
                    this.filt = this.newFilter;
                    this.newFilter = null;
                    this.impulseBuf = null;
                    useConvolve = this.filt.useConvolve();
                    this.stateL = this.filt.createState();
                    this.stateR = this.filt.createState();
                }
                final int data = this.wform.getData();
                if (data == 0) {
                    break;
                }
                final short[] buffer = this.wform.buffer;
                int inbp2 = this.inbp;
                for (int i = 0; i < data; i += n) {
                    this.fbufLi[inbp2] = buffer[i];
                    inbp2 = (inbp2 + 1 & this.fbufmask);
                }
                int inbp3 = this.inbp;
                if (this.stereo) {
                    for (int j = 0; j < data; j += 2) {
                        this.fbufRi[inbp3] = buffer[j + 1];
                        inbp3 = (inbp3 + 1 & this.fbufmask);
                    }
                }
                else {
                    for (int k = 0; k < data; ++k) {
                        this.fbufRi[inbp3] = this.fbufLi[inbp3];
                        inbp3 = (inbp3 + 1 & this.fbufmask);
                    }
                }
                final int n3 = data / n;
                if (useConvolve) {
                    this.doConvolveFilter(n3, b);
                }
                else {
                    this.doFilter(n3);
                    if (VowelFrame.this.unstable) {
                        break;
                    }
                    this.doOutput(n3 * 4, b);
                }
                if (VowelFrame.this.unstable) {
                    break;
                }
                if (this.spectCt >= this.spectrumLen) {
                    this.spectrumOffset = (this.outbp - this.spectrumLen & this.fbufmask);
                    this.spectCt -= this.spectrumLen;
                    VowelFrame.this.cv.repaint();
                }
                n2 += n3;
                if (!b || n2 < VowelFrame.this.sampleRate) {
                    continue;
                }
                n2 = 0;
                b = false;
            }
            if (this.shutdownRequested || VowelFrame.this.unstable || !VowelFrame.this.soundCheck.getState()) {
                this.line.flush();
            }
            else {
                this.line.drain();
            }
            VowelFrame.this.cv.repaint();
        }
        
        void doFilter(final int n) {
            this.filt.run(this.fbufLi, this.fbufLo, this.inbp, this.fbufmask, n, this.stateL);
            this.filt.run(this.fbufRi, this.fbufRo, this.inbp, this.fbufmask, n, this.stateR);
            this.inbp = (this.inbp + n & this.fbufmask);
            final double n2 = this.fbufLo[this.inbp - 1 & this.fbufmask];
            if (Double.isNaN(n2) || Double.isInfinite(n2)) {
                VowelFrame.this.unstable = true;
            }
        }
        
        void doConvolveFilter(final int n, final boolean b) {
            int inbp = this.inbp;
            final double[] aList = ((DirectFilter)this.filt).aList;
            final int power2 = VowelFrame.this.getPower2(512 + aList.length * 2);
            if (this.convolveBuf == null || this.convolveBuf.length != power2) {
                this.convolveBuf = new double[power2];
            }
            if (this.impulseBuf == null) {
                this.impulseBuf = new double[power2];
                for (int i = 0; i != aList.length; ++i) {
                    this.impulseBuf[i * 2] = aList[i];
                }
                (this.convFFT = new FFT(this.convolveBuf.length / 2)).transform(this.impulseBuf, false);
            }
            int convBufPtr = this.convBufPtr;
            final int n2 = this.convolveBuf.length + 2 - 2 * aList.length;
            for (int j = 0; j != n; ++j, ++inbp) {
                final int n3 = inbp & this.fbufmask;
                this.convolveBuf[convBufPtr] = this.fbufLi[n3];
                this.convolveBuf[convBufPtr + 1] = this.fbufRi[n3];
                convBufPtr += 2;
                if (convBufPtr == n2) {
                    this.convFFT.transform(this.convolveBuf, false);
                    final double n4 = 2.0 / power2;
                    for (int k = 0; k != power2; k += 2) {
                        final double n5 = this.convolveBuf[k] * this.impulseBuf[k] - this.convolveBuf[k + 1] * this.impulseBuf[k + 1];
                        final double n6 = this.convolveBuf[k] * this.impulseBuf[k + 1] + this.convolveBuf[k + 1] * this.impulseBuf[k];
                        this.convolveBuf[k] = n5 * n4;
                        this.convolveBuf[k + 1] = n6 * n4;
                    }
                    this.convFFT.transform(this.convolveBuf, true);
                    int outbp;
                    int n7;
                    int l;
                    for (outbp = this.outbp, n7 = power2 - n2, l = 0; l != n7; l += 2, ++outbp) {
                        final int n8 = outbp & this.fbufmask;
                        final double[] fbufLo = this.fbufLo;
                        final int n9 = n8;
                        fbufLo[n9] += this.convolveBuf[l];
                        final double[] fbufRo = this.fbufRo;
                        final int n10 = n8;
                        fbufRo[n10] += this.convolveBuf[l + 1];
                    }
                    while (l != power2) {
                        final int n11 = outbp & this.fbufmask;
                        this.fbufLo[n11] = this.convolveBuf[l];
                        this.fbufRo[n11] = this.convolveBuf[l + 1];
                        l += 2;
                        ++outbp;
                    }
                    convBufPtr = 0;
                    this.doOutput(n2 * 2, b);
                    for (int n12 = 0; n12 != power2; ++n12) {
                        this.convolveBuf[n12] = 0.0;
                    }
                }
            }
            this.inbp = (inbp & this.fbufmask);
            this.convBufPtr = convBufPtr;
        }
        
        void doOutput(final int n, final boolean b) {
            if (this.ob.length < n) {
                this.ob = new byte[n];
            }
            int outbp2;
            while (true) {
                int n2 = 0;
                int outbp = this.outbp;
                for (int i = 0; i < n; i += 4) {
                    final int n3 = (int)(this.fbufLo[outbp] * VowelFrame.this.outputGain);
                    if (n3 > n2) {
                        n2 = n3;
                    }
                    if (n3 < -n2) {
                        n2 = -n3;
                    }
                    this.ob[i + 1] = (byte)(n3 >> 8);
                    this.ob[i] = (byte)n3;
                    outbp = (outbp + 1 & this.fbufmask);
                }
                outbp2 = this.outbp;
                for (int j = 2; j < n; j += 4) {
                    final int n4 = (int)(this.fbufRo[outbp2] * VowelFrame.this.outputGain);
                    if (n4 > n2) {
                        n2 = n4;
                    }
                    if (n4 < -n2) {
                        n2 = -n4;
                    }
                    this.ob[j + 1] = (byte)(n4 >> 8);
                    this.ob[j] = (byte)n4;
                    outbp2 = (outbp2 + 1 & this.fbufmask);
                }
                if (n2 > 32767) {
                    final VowelFrame this$0 = VowelFrame.this;
                    this$0.outputGain *= 30000.0 / n2;
                    if (VowelFrame.this.outputGain < 1.0E-8 || Double.isInfinite(VowelFrame.this.outputGain)) {
                        VowelFrame.this.unstable = true;
                        break;
                    }
                    continue;
                }
                else {
                    if (!b || n2 >= 24000) {
                        break;
                    }
                    if (n2 == 0) {
                        if (VowelFrame.this.outputGain == 1.0) {
                            break;
                        }
                        VowelFrame.this.outputGain = 1.0;
                    }
                    else {
                        final VowelFrame this$2 = VowelFrame.this;
                        this$2.outputGain *= 30000.0 / n2;
                    }
                }
            }
            if (VowelFrame.this.unstable) {
                return;
            }
            final int outbp3 = this.outbp;
            this.outbp = outbp2;
            this.line.write(this.ob, 0, n);
            this.spectCt += n / 4;
        }
    }
    
    class Complex
    {
        public double re;
        public double im;
        public double mag;
        public double phase;
        
        Complex() {
            final double n = 0.0;
            this.phase = n;
            this.mag = n;
            this.im = n;
            this.re = n;
        }
        
        Complex(final double n, final double n2) {
            this.set(n, n2);
        }
        
        Complex(final Complex complex) {
            this.set(complex.re, complex.im);
        }
        
        double magSquared() {
            return this.mag * this.mag;
        }
        
        String asString() {
            return this.re + "+" + this.im + "i";
        }
        
        void set(final double re, final double im) {
            this.re = re;
            this.im = im;
            this.setMagPhase();
        }
        
        void set(final double re) {
            this.re = re;
            this.im = 0.0;
            this.setMagPhase();
        }
        
        void set(final Complex complex) {
            this.re = complex.re;
            this.im = complex.im;
            this.mag = complex.mag;
            this.phase = complex.phase;
        }
        
        void add(final double n) {
            this.re += n;
            this.setMagPhase();
        }
        
        void add(final double n, final double n2) {
            this.re += n;
            this.im += n2;
            this.setMagPhase();
        }
        
        void add(final Complex complex) {
            this.re += complex.re;
            this.im += complex.im;
            this.setMagPhase();
        }
        
        void subtract(final Complex complex) {
            this.re -= complex.re;
            this.im -= complex.im;
            this.setMagPhase();
        }
        
        void addMult(final double n, final Complex complex) {
            this.re += complex.re * n;
            this.im += complex.im * n;
            this.setMagPhase();
        }
        
        void addMult(final double n, final Complex complex, final Complex complex2) {
            this.re += n * (complex.re * complex2.re - complex.im * complex2.im);
            this.im += n * (complex.re * complex2.im + complex.im * complex2.re);
            this.setMagPhase();
        }
        
        void square() {
            this.set(this.re * this.re - this.im * this.im, 2.0 * this.re * this.im);
        }
        
        void sqrt() {
            this.setMagPhase(Math.sqrt(this.mag), this.phase * 0.5);
        }
        
        void mult(final double n, final double n2) {
            this.set(this.re * n - this.im * n2, this.re * n2 + this.im * n);
        }
        
        void mult(final double n) {
            this.re *= n;
            this.im *= n;
            this.mag *= n;
        }
        
        void mult(final Complex complex) {
            this.mult(complex.re, complex.im);
        }
        
        void setMagPhase() {
            this.mag = Math.sqrt(this.re * this.re + this.im * this.im);
            this.phase = Math.atan2(this.im, this.re);
        }
        
        void setMagPhase(final double mag, final double phase) {
            this.mag = mag;
            this.phase = phase;
            this.re = mag * Math.cos(phase);
            this.im = mag * Math.sin(phase);
        }
        
        void recip() {
            final double n = this.re * this.re + this.im * this.im;
            this.set(this.re / n, -this.im / n);
        }
        
        void divide(final Complex complex) {
            final double n = complex.re * complex.re + complex.im * complex.im;
            this.mult(complex.re / n, -complex.im / n);
        }
        
        void rotate(final double n) {
            this.setMagPhase(this.mag, (this.phase + n) % 6.283185307179586);
        }
        
        void conjugate() {
            this.im = -this.im;
            this.phase = -this.phase;
        }
        
        void pow(final double n) {
            Math.atan2(this.im, this.re);
            this.phase *= n;
            this.setMagPhase(Math.pow(this.re * this.re + this.im * this.im, n * 0.5), this.phase);
        }
    }
    
    abstract class Filter
    {
        abstract void run(final double[] p0, final double[] p1, final int p2, final int p3, final int p4, final double[] p5);
        
        abstract void evalTransfer(final Complex p0);
        
        abstract int getImpulseOffset();
        
        abstract int getStepOffset();
        
        abstract int getLength();
        
        boolean useConvolve() {
            return false;
        }
        
        double[] getImpulseResponse(final int n) {
            final int n2 = 1000;
            final double[] array = new double[n + n2];
            final double[] array2 = new double[n + n2];
            array[n] = 1.0;
            this.run(array, array2, n, -1, n2, this.createState());
            return array2;
        }
        
        double[] getStepResponse(final int n) {
            final int n2 = 1000;
            final double[] array = new double[n + n2];
            final double[] array2 = new double[n + n2];
            for (int i = n; i != array.length; ++i) {
                array[i] = 1.0;
            }
            this.run(array, array2, n, -1, n2, this.createState());
            return array2;
        }
        
        int getImpulseLen(final int n, final double[] array) {
            return VowelFrame.this.countPoints(array, n);
        }
        
        int getStepLen(final int n, final double[] array) {
            return VowelFrame.this.countPoints(array, n);
        }
        
        double[] createState() {
            return null;
        }
    }
    
    class DirectFilter extends Filter
    {
        double[] aList;
        double[] bList;
        int[] nList;
        Complex czn;
        Complex top;
        Complex bottom;
        
        DirectFilter() {
            this.aList = new double[] { 1.0 };
            this.bList = null;
            this.nList = new int[] { 0 };
        }
        
        int getLength() {
            return this.aList.length;
        }
        
        boolean useConvolve() {
            return this.bList == null && this.aList.length > 25;
        }
        
        void dump() {
            System.out.print("a ");
            this.dump(this.aList);
            if (this.bList != null) {
                System.out.print("b ");
                this.dump(this.bList);
            }
        }
        
        void dump(final double[] array) {
            for (int i = 0; i != array.length; ++i) {
                System.out.print(array[i] + " ");
            }
            System.out.println("");
        }
        
        void evalTransfer(final Complex complex) {
            if (this.czn == null) {
                this.czn = new Complex();
                this.top = new Complex();
                this.bottom = new Complex();
            }
            this.czn.set(1.0);
            this.top.set(0.0);
            this.bottom.set(0.0);
            int i = 0;
            for (int j = 0; j != this.aList.length; ++j) {
                for (int n = this.nList[j]; i < n; ++i) {
                    if (i + 3 < n) {
                        this.czn.set(complex);
                        this.czn.pow(-n);
                        i = n;
                        break;
                    }
                    this.czn.divide(complex);
                }
                this.top.addMult(this.aList[j], this.czn);
                if (this.bList != null) {
                    this.bottom.addMult(this.bList[j], this.czn);
                }
            }
            if (this.bList != null) {
                this.top.divide(this.bottom);
            }
            complex.set(this.top);
        }
        
        void run(final double[] array, final double[] array2, final int n, final int n2, final int n3, final double[] array3) {
            for (int i = 0; i != n3; ++i) {
                final int n4 = n + i;
                final int n5 = n4 & n2;
                double n6 = array[n5] * this.aList[0];
                if (this.bList == null) {
                    for (int j = 1; j < this.aList.length; ++j) {
                        n6 += array[n4 - this.nList[j] & n2] * this.aList[j];
                    }
                }
                else {
                    for (int k = 1; k < this.aList.length; ++k) {
                        final int n7 = n4 - this.nList[k] & n2;
                        n6 += array[n7] * this.aList[k] - array2[n7] * this.bList[k];
                    }
                }
                array2[n5] = n6;
            }
        }
        
        boolean isSimpleAList() {
            return this.bList == null && this.nList[this.nList.length - 1] == this.nList.length - 1;
        }
        
        int getImpulseOffset() {
            if (this.isSimpleAList()) {
                return 0;
            }
            return this.getStepOffset();
        }
        
        int getStepOffset() {
            int n = 0;
            for (int i = 0; i != this.aList.length; ++i) {
                if (this.nList[i] > n) {
                    n = this.nList[i];
                }
            }
            return n;
        }
        
        double[] getImpulseResponse(final int n) {
            if (this.isSimpleAList()) {
                return this.aList;
            }
            return super.getImpulseResponse(n);
        }
        
        int getImpulseLen(final int n, final double[] array) {
            if (this.isSimpleAList()) {
                return this.aList.length;
            }
            return VowelFrame.this.countPoints(array, n);
        }
    }
    
    class CascadeFilter extends Filter
    {
        double[] a1;
        double[] a2;
        double[] b0;
        double[] b1;
        double[] b2;
        int size;
        Complex cm2;
        Complex cm1;
        Complex top;
        Complex bottom;
        
        CascadeFilter(final int size) {
            this.size = size;
            this.a1 = new double[size];
            this.a2 = new double[size];
            this.b0 = new double[size];
            this.b1 = new double[size];
            this.b2 = new double[size];
            for (int i = 0; i != size; ++i) {
                this.b0[i] = 1.0;
            }
        }
        
        double[] createState() {
            return new double[this.size * 3];
        }
        
        void setAStage(final double n, final double n2) {
            for (int i = 0; i != this.size; ++i) {
                if (this.a1[i] == 0.0 && this.a2[i] == 0.0) {
                    this.a1[i] = n;
                    this.a2[i] = n2;
                    return;
                }
                if (this.a2[i] == 0.0 && n2 == 0.0) {
                    this.a2[i] = -this.a1[i] * n;
                    final double[] a1 = this.a1;
                    final int n3 = i;
                    a1[n3] += n;
                    return;
                }
            }
            System.out.println("setAStage failed");
        }
        
        void setBStage(final double n, final double n2, final double n3) {
            for (int i = 0; i != this.size; ++i) {
                if (this.b1[i] == 0.0 && this.b2[i] == 0.0) {
                    this.b0[i] = n;
                    this.b1[i] = n2;
                    this.b2[i] = n3;
                    return;
                }
                if (this.b2[i] == 0.0 && n3 == 0.0) {
                    this.b2[i] = this.b1[i] * n2;
                    this.b1[i] = this.b1[i] * n + this.b0[i] * n2;
                    final double[] b0 = this.b0;
                    final int n4 = i;
                    b0[n4] *= n;
                    return;
                }
            }
            System.out.println("setBStage failed");
        }
        
        void run(final double[] array, final double[] array2, final int n, final int n2, final int n3, final double[] array3) {
            for (int i = 0; i != n3; ++i) {
                final int n4 = n + i & n2;
                double n5 = array[n4];
                for (int j = 0; j != this.size; ++j) {
                    final int n6 = j * 3;
                    final int n7 = n6 + 2;
                    final double n8 = array3[n6 + 1];
                    array3[n7] = n8;
                    final double n9 = n8;
                    final int n10 = n6 + 1;
                    final double n11 = array3[n6];
                    array3[n10] = n11;
                    final double n12 = n11;
                    final int n13 = n6;
                    final double n14 = n5 + this.a1[j] * n12 + this.a2[j] * n9;
                    array3[n13] = n14;
                    n5 = this.b0[j] * n14 + this.b1[j] * n12 + this.b2[j] * n9;
                }
                array2[n4] = n5;
            }
        }
        
        void evalTransfer(final Complex complex) {
            if (this.cm1 == null) {
                this.cm1 = new Complex();
                this.cm2 = new Complex();
                this.top = new Complex();
                this.bottom = new Complex();
            }
            this.cm1.set(complex);
            this.cm1.recip();
            this.cm2.set(this.cm1);
            this.cm2.square();
            complex.set(1.0);
            for (int i = 0; i != this.size; ++i) {
                this.top.set(this.b0[i]);
                this.top.addMult(this.b1[i], this.cm1);
                this.top.addMult(this.b2[i], this.cm2);
                this.bottom.set(1.0);
                this.bottom.addMult(-this.a1[i], this.cm1);
                this.bottom.addMult(-this.a2[i], this.cm2);
                complex.mult(this.top);
                complex.divide(this.bottom);
            }
        }
        
        int getImpulseOffset() {
            return 0;
        }
        
        int getStepOffset() {
            return 0;
        }
        
        int getLength() {
            return 1;
        }
    }
    
    abstract class FilterType
    {
        int select() {
            return 0;
        }
        
        void setup() {
        }
        
        abstract void getResponse(final double p0, final Complex p1);
        
        int getPoleCount() {
            return 0;
        }
        
        int getZeroCount() {
            return 0;
        }
        
        void getPole(final int n, final Complex complex) {
            complex.set(0.0);
        }
        
        void getZero(final int n, final Complex complex) {
            complex.set(0.0);
        }
        
        abstract Filter genFilter();
        
        void getInfo(final String[] array) {
        }
        
        boolean needsWindow() {
            return false;
        }
        
        void setCutoff(final double n) {
            VowelFrame.this.auxBars[0].setValue((int)(2000.0 * n));
        }
    }
    
    abstract class FIRFilterType extends FilterType
    {
        double[] response;
        
        void getResponse(final double n, final Complex complex) {
            if (this.response == null) {
                complex.set(0.0);
                return;
            }
            int n2 = (int)(this.response.length * n / 6.283185307179586) & 0xFFFFFFFE;
            if (n2 < 0) {
                n2 = 0;
            }
            if (n2 >= this.response.length) {
                n2 = this.response.length - 2;
            }
            complex.set(this.response[n2], this.response[n2 + 1]);
        }
        
        double getWindow(final int n, final int n2) {
            if (n2 == 1) {
                return 1.0;
            }
            final double n3 = 6.283185307179586 * n / (n2 - 1);
            final double n4 = n2 / 2;
            switch (VowelFrame.this.windowChooser.getSelectedIndex()) {
                case 0: {
                    return 1.0;
                }
                case 1: {
                    return 0.54 - 0.46 * Math.cos(n3);
                }
                case 2: {
                    return 0.5 - 0.5 * Math.cos(n3);
                }
                case 3: {
                    return 0.42 - 0.5 * Math.cos(n3) + 0.08 * Math.cos(2.0 * n3);
                }
                case 4: {
                    final double n5 = VowelFrame.this.kaiserBar.getValue() * 3.141592653589793 / 120.0;
                    final double n6 = 2 * n / n2 - 1.0;
                    return VowelFrame.this.bessi0(n5 * Math.sqrt(1.0 - n6 * n6));
                }
                case 5: {
                    return (n < n4) ? (n / n4) : (2.0 - n / n4);
                }
                case 6: {
                    final double n7 = (n - n4) / n4;
                    return 1.0 - n7 * n7;
                }
                default: {
                    return 0.0;
                }
            }
        }
        
        void setResponse(final DirectFilter directFilter) {
            this.setResponse(directFilter, 0.0);
        }
        
        void setResponse(final DirectFilter directFilter, final double n) {
            this.response = new double[8192];
            if (directFilter.nList.length != directFilter.aList.length) {
                directFilter.nList = new int[directFilter.aList.length];
                for (int i = 0; i != directFilter.aList.length; ++i) {
                    directFilter.nList[i] = i;
                }
            }
            for (int j = 0; j != directFilter.aList.length; ++j) {
                this.response[directFilter.nList[j] * 2] = directFilter.aList[j];
            }
            new FFT(this.response.length / 2).transform(this.response, false);
            double n2 = 0.0;
            final Complex complex = new Complex();
            for (int k = 0; k != this.response.length; k += 2) {
                complex.set(this.response[k], this.response[k + 1]);
                complex.rotate(-n * 2.0 * 3.141592653589793 * k / this.response.length);
                final double magSquared = complex.magSquared();
                if (n2 < magSquared) {
                    n2 = magSquared;
                }
                this.response[k] = complex.re;
                this.response[k + 1] = complex.im;
            }
            final double sqrt = Math.sqrt(n2);
            for (int l = 0; l != this.response.length; ++l) {
                final double[] response = this.response;
                final int n3 = l;
                response[n3] /= sqrt;
            }
            for (int n4 = 0; n4 != directFilter.aList.length; ++n4) {
                final double[] aList = directFilter.aList;
                final int n5 = n4;
                aList[n5] /= sqrt;
            }
        }
    }
    
    class PipeFIRFilter extends FIRFilterType
    {
        int select() {
            VowelFrame.this.auxLabels[0].setText("Total Length");
            VowelFrame.this.auxBars[0].setValue(176);
            VowelFrame.this.auxBars[0].setMaximum(1000);
            return 1;
        }
        
        void compressedPipeResponse(final double[] array, final double[] array2) {
            double n = -1.0;
            int i;
            int n2;
            for (n2 = (i = 0); i != array.length; ++i) {
                if (array[i] != n) {
                    ++n2;
                }
                n = array[i];
            }
            final double[] array3 = new double[n2];
            final int[] array4 = new int[n2 + 1];
            double n3 = -1.0;
            array4[0] = 0;
            int j;
            for (int n4 = j = 0; j != array.length; ++j) {
                if (array[j] != n3) {
                    final double[] array5 = array3;
                    final int n5 = n4++;
                    final double n6 = array[j];
                    array5[n5] = n6;
                    n3 = n6;
                }
                array4[n4] = j;
            }
            VowelFrame.this.genPipeResponse(array3, VowelFrame.this.sampleRate / 2, array4, array2, null);
        }
        
        Filter genFilter() {
            int i;
            int n;
            for (i = 16, n = 256; i < n; i *= 2) {}
            final double[] array = new double[i];
            VowelFrame.this.pipeLen = VowelFrame.this.auxBars[0].getValue() / 1000.0;
            if (VowelFrame.this.compressCheck.getState()) {
                this.compressedPipeResponse(VowelFrame.this.pipeRadius, array);
            }
            else {
                final int[] array2 = new int[VowelFrame.this.pipeRadius.length + 1];
                for (int j = 0; j != array2.length; ++j) {
                    array2[j] = j;
                }
                VowelFrame.this.genPipeResponse(VowelFrame.this.pipeRadius, VowelFrame.this.sampleRate / 2, array2, array, null);
            }
            final int n2 = array.length * 4;
            final double[] array3 = new double[n2];
            final int n3 = n2 / 2;
            for (int n4 = n3 / 2, k = 0; k != n4; ++k) {
                final double n5 = array[k] / n3;
                array3[k * 2] = n5;
                if (k > 0) {
                    array3[n2 - k * 2] = n5;
                }
            }
            new FFT(n3).transform(array3, true);
            final DirectFilter directFilter = new DirectFilter();
            directFilter.aList = new double[n];
            directFilter.nList = new int[n];
            for (int l = 0; l != n; ++l) {
                directFilter.aList[l] = array3[(l - n / 2) * 2 & n2 - 1] * this.getWindow(l, n);
                directFilter.nList[l] = l;
            }
            this.setResponse(directFilter, n / 2);
            return directFilter;
        }
        
        void getInfo(final String[] array) {
            array[0] = "Length: " + VowelFrame.this.auxBars[0].getValue() + " mm";
        }
        
        double areaToRadius(final double n) {
            return Math.sqrt(n / 3.141592653589793);
        }
        
        boolean needsWindow() {
            return true;
        }
    }
    
    class IBarVowelFilter extends PipeFIRFilter
    {
        double[] data;
        
        IBarVowelFilter() {
            this.data = new double[] { 6.5, 6.5, 6.5, 6.5, 6.5, 6.5, 6.5, 6.5, 6.5, 6.5, 6.5, 6.42857, 2.83929, 2.73214, 3.13393, 3.29464, 3.45536, 3.45536, 4.58036, 5.49107, 6.45536, 6.96429, 7.33929, 7.66071, 7.84821, 8.03571, 8.19643, 8.30357, 8.38393, 8.41071, 8.38393, 8.35714, 8.25, 8.14286, 8.14286, 7.98214, 7.82143, 7.58036, 7.3125, 6.75, 6.02679, 5.22321, 4.6875, 4.28571, 3.96429, 3.66964, 3.40179, 3.13393, 2.91964, 2.75893, 2.625, 2.625, 2.51786, 2.4375, 2.35714, 2.27679, 2.19643, 2.11607, 2.0625, 2.00893, 1.95536, 1.875, 1.79464, 1.71429, 1.66071, 1.60714, 1.52679, 1.52679, 1.47321, 1.41964, 1.36607, 1.3125, 1.25893, 1.20536, 1.15179, 1.125, 1.09821, 1.07143, 1.04464, 1.04464, 1.01786, 1.01786, 1.01786, 1.01786, 1.01786, 1.04464, 1.09821, 1.15179, 1.17857, 1.23214, 1.28571, 1.39286, 1.47321, 1.60714, 1.76786, 1.95536, 2.16964, 2.35714, 2.46429, 2.51786, 2.35714, 2.35714, 2.0625, 1.79464, 1.71429, 1.79464, 2.86607, 3.375, 3.85714, 4.125, 4.33929, 4.55357, 4.76786, 4.92857, 5.11607, 5.30357, 5.46429, 5.46429, 5.59821, 5.75893, 5.89286, 6.10714, 6.26786, 6.42857, 6.61607, 6.75, 6.96429, 7.125, 7.25893, 7.47321, 7.6875, 7.875, 8.08929, 8.33036, 8.33036, 8.625, 8.83929, 9.13393, 9.375, 9.58929, 9.80357, 10.0179, 10.1786, 10.3125, 10.4196, 10.5, 10.6071, 10.6607, 10.7143, 10.7679, 10.8482, 10.8482, 10.875, 10.9018, 10.9286, 10.9821, 10.9821, 11.0089, 11.0089, 11.0357, 11.0357, 11.0625, 11.0357, 11.0625, 11.0625, 11.0625, 11.0625, 11.0625, 11.0625, 11.0893, 11.0893, 11.0893, 11.0893, 11.0625, 11.0625, 11.1161, 11.0893, 11.0625, 11.0625, 11.0625, 10.9018, 3.58929, 3.375, 3.21429, 3.21429, 3.13393, 3.08036, 3.05357, 3.02679, 3.02679, 3.02679, 3.0, 3.02679, 3.02679, 3.0, 3.0, 3.0, 2.97321, 2.94643, 2.97321 };
        }
        
        int select() {
            final int select = super.select();
            for (int i = 0; i != VowelFrame.this.pipeRadius.length; ++i) {
                VowelFrame.this.pipeRadius[VowelFrame.this.pipeRadius.length - 1 - i] = this.areaToRadius(this.data[i]);
            }
            VowelFrame.this.auxBars[select - 1].setValue(190);
            return select;
        }
    }
    
    class IVowelFilter extends PipeFIRFilter
    {
        double[] data;
        
        IVowelFilter() {
            this.data = new double[] { 8.74254, 8.74254, 7.71642, 7.18284, 6.64925, 5.99254, 4.96642, 4.63806, 4.55597, 4.43284, 4.02239, 2.95522, 2.87313, 2.83209, 2.83209, 2.83209, 2.83209, 2.79104, 2.25746, 1.76493, 1.76493, 1.76493, 1.68284, 1.64179, 1.60075, 1.5597, 1.47761, 1.35448, 1.31343, 1.1903, 1.10821, 1.10821, 1.02612, 0.985075, 0.94403, 0.86194, 0.779851, 0.738806, 0.656716, 0.656716, 0.656716, 0.656716, 0.574627, 0.574627, 0.574627, 0.574627, 0.574627, 0.574627, 0.574627, 0.574627, 0.574627, 0.574627, 0.615672, 0.615672, 0.615672, 0.615672, 0.574627, 0.574627, 0.574627, 0.574627, 0.615672, 0.656716, 0.615672, 0.615672, 0.656716, 0.697761, 0.697761, 0.697761, 0.697761, 0.738806, 0.779851, 0.779851, 0.820896, 0.779851, 0.779851, 0.902985, 0.902985, 0.985075, 1.10821, 1.23134, 1.39552, 1.51866, 1.72388, 1.84701, 2.05224, 2.17537, 2.42164, 2.66791, 2.87313, 3.20149, 3.5709, 3.65299, 4.06343, 4.39179, 4.72015, 5.37687, 5.66418, 6.15672, 6.85448, 7.01866, 7.34701, 7.4291, 7.55224, 7.71642, 7.79851, 7.79851, 7.75746, 7.71642, 7.59328, 7.47015, 7.30597, 7.22388, 7.34701, 7.79851, 9.11194, 10.0149, 10.2612, 10.5075, 10.8358, 10.8769, 10.9179, 10.959, 11.0, 11.041, 11.0, 11.041, 11.041, 11.041, 11.041, 11.041, 11.041, 11.041, 11.041, 11.041, 11.041, 11.0, 10.959, 10.9179, 10.8358, 10.7948, 10.7127, 10.6306, 10.5075, 10.4664, 10.3433, 10.2612, 10.1381, 10.056, 10.056, 9.85075, 9.72761, 9.64552, 9.52239, 9.39925, 9.35821, 9.27612, 9.19403, 9.11194, 9.02985, 8.94776, 8.86567, 8.78358, 8.74254, 8.66045, 8.66045, 8.53731, 8.45522, 8.37313, 8.33209, 8.29104, 8.25, 8.20896, 8.20896, 8.20896, 8.16791, 8.16791, 8.12687, 8.08582, 8.04478, 7.83955, 2.87313, 2.62687, 2.54478, 2.54478, 2.46269, 2.42164, 2.46269, 2.46269, 2.46269, 2.54478, 2.58582, 2.66791, 2.66791, 2.75, 2.79104, 2.83209, 2.87313, 2.91418, 2.99627, 3.03731 };
        }
        
        int select() {
            final int select = super.select();
            for (int i = 0; i != VowelFrame.this.pipeRadius.length; ++i) {
                VowelFrame.this.pipeRadius[VowelFrame.this.pipeRadius.length - 1 - i] = this.areaToRadius(this.data[i]);
            }
            VowelFrame.this.auxBars[select - 1].setValue(170);
            return select;
        }
    }
    
    class EVowelFilter extends PipeFIRFilter
    {
        double[] data;
        
        EVowelFilter() {
            this.data = new double[] { 10.2, 10.2, 10.2, 9.31174, 8.98785, 8.70445, 8.54251, 8.34008, 7.85425, 5.91093, 5.34413, 5.26316, 5.18219, 5.06073, 4.97976, 4.93927, 4.93927, 4.97976, 5.06073, 5.10121, 5.10121, 5.06073, 5.06073, 4.89879, 4.69636, 4.49393, 4.21053, 4.0081, 3.80567, 3.64372, 3.48178, 3.15789, 2.99595, 2.67206, 2.46964, 2.38866, 2.26721, 2.14575, 2.14575, 2.10526, 2.10526, 2.18623, 2.18623, 2.30769, 2.34818, 2.42915, 2.46964, 2.55061, 2.63158, 2.67206, 2.75304, 2.83401, 2.95547, 2.99595, 3.07692, 3.19838, 3.23887, 3.23887, 3.36032, 3.4413, 3.56275, 3.56275, 3.64372, 3.64372, 3.7247, 3.80567, 3.84615, 3.92713, 4.0081, 4.04858, 4.12955, 4.17004, 4.21053, 4.33198, 4.41296, 4.49393, 4.49393, 4.5749, 4.65587, 4.69636, 4.77733, 4.8583, 4.89879, 5.06073, 5.26316, 5.30364, 5.4251, 5.66802, 5.78947, 6.03239, 6.11336, 6.31579, 6.51822, 6.63968, 6.80162, 6.92308, 7.12551, 7.24696, 7.36842, 7.40891, 7.40891, 7.40891, 7.32794, 7.20648, 7.04453, 6.80162, 6.72065, 6.80162, 6.92308, 7.32794, 7.85425, 8.25911, 8.8664, 9.06883, 9.4332, 9.55466, 9.67611, 9.79757, 9.79757, 9.87854, 9.91903, 9.95951, 9.95951, 10.0, 9.95951, 9.95951, 9.95951, 9.95951, 9.91903, 9.87854, 9.83806, 9.79757, 9.75709, 9.67611, 9.63563, 9.55466, 9.47368, 9.39271, 9.35223, 9.23077, 9.19028, 9.1498, 8.98785, 8.82591, 8.70445, 8.66397, 8.54251, 8.34008, 8.13765, 7.93522, 7.85425, 7.69231, 7.65182, 7.53036, 7.36842, 7.16599, 7.08502, 6.96356, 6.84211, 6.72065, 6.43725, 6.35628, 6.23482, 6.19433, 6.07287, 6.03239, 5.9919, 5.9919, 5.95142, 5.91093, 5.95142, 5.91093, 5.91093, 5.95142, 5.95142, 5.95142, 5.54656, 1.53846, 1.417, 1.417, 1.417, 1.417, 1.417, 1.45749, 1.53846, 1.61943, 1.61943, 1.65992, 1.78138, 1.78138, 1.94332, 2.06478, 2.14575, 2.22672, 2.30769, 2.38866, 2.51012, 2.59109, 2.63158, 2.75304 };
        }
        
        int select() {
            final int select = super.select();
            for (int i = 0; i != VowelFrame.this.pipeRadius.length; ++i) {
                VowelFrame.this.pipeRadius[VowelFrame.this.pipeRadius.length - 1 - i] = this.areaToRadius(this.data[i]);
            }
            VowelFrame.this.auxBars[select - 1].setValue(170);
            return select;
        }
    }
    
    class OpenTubeFilter extends PipeFIRFilter
    {
        int select() {
            final int select = super.select();
            for (int i = 0; i != VowelFrame.this.pipeRadius.length; ++i) {
                VowelFrame.this.pipeRadius[i] = this.areaToRadius(6.5);
            }
            return select;
        }
    }
    
    class CustomFilter extends PipeFIRFilter
    {
        int select() {
            final int select = super.select();
            VowelFrame.this.auxBars[0].setValue((int)(VowelFrame.this.pipeLen * 1000.0));
            return select;
        }
    }
    
    class AVowelFilter extends PipeFIRFilter
    {
        double[] data;
        
        AVowelFilter() {
            this.data = new double[] { 6.08276, 6.08276, 6.08276, 6.08276, 6.08276, 6.05379, 5.93793, 5.59034, 5.18483, 5.01103, 4.92414, 4.89517, 4.86621, 4.86621, 4.86621, 4.86621, 4.86621, 4.89517, 4.9531, 5.04, 5.18483, 5.38759, 5.67724, 5.93793, 6.14069, 6.31448, 6.48828, 6.6331, 6.74897, 6.89379, 7.00966, 7.09655, 7.24138, 7.35724, 7.44414, 7.53103, 7.6469, 7.73379, 7.79172, 7.87862, 7.93655, 7.96552, 8.02345, 8.05241, 8.11034, 8.13931, 8.19724, 8.19724, 8.25517, 8.28414, 8.34207, 8.37103, 8.37103, 8.4, 8.42897, 8.4, 8.4, 8.4, 8.34207, 8.3131, 8.28414, 8.22621, 8.16828, 8.13931, 8.05241, 7.99448, 7.93655, 7.84966, 7.79172, 7.70483, 7.6469, 7.50207, 7.38621, 7.27034, 7.12552, 6.98069, 6.89379, 6.77793, 6.60414, 6.43034, 6.25655, 6.08276, 5.93793, 5.73517, 5.56138, 5.38759, 5.21379, 5.09793, 4.89517, 4.69241, 4.54759, 4.31586, 4.02621, 3.82345, 3.64966, 3.36, 3.09931, 2.86759, 2.54897, 2.17241, 1.85379, 1.73793, 1.68, 1.73793, 1.96966, 2.11448, 2.57793, 2.75172, 2.95448, 3.07034, 3.04138, 3.04138, 2.98345, 2.89655, 2.78069, 2.66483, 2.52, 2.37517, 2.28828, 2.17241, 2.02759, 1.96966, 1.88276, 1.79586, 1.68, 1.65103, 1.53517, 1.47724, 1.39034, 1.30345, 1.21655, 1.12966, 1.04276, 1.04276, 0.984828, 0.955862, 0.926897, 0.926897, 0.868966, 0.868966, 0.811034, 0.811034, 0.724138, 0.782069, 0.811034, 0.84, 0.811034, 0.868966, 0.868966, 0.84, 0.897931, 0.926897, 0.926897, 0.984828, 1.04276, 1.07172, 1.15862, 1.24552, 1.30345, 1.36138, 1.50621, 1.5931, 1.7669, 1.82483, 2.02759, 2.17241, 2.4331, 2.54897, 2.80966, 3.04138, 3.36, 3.50483, 3.67862, 3.85241, 4.02621, 4.08414, 4.05517, 3.99724, 3.09931, 1.44828, 1.36138, 1.33241, 1.36138, 1.36138, 1.44828, 1.47724, 1.53517, 1.56414, 1.65103, 1.73793, 1.82483, 1.91172, 1.99862, 2.11448, 2.17241, 2.25931, 2.37517, 2.46207, 2.54897, 2.63586 };
        }
        
        int select() {
            final int select = super.select();
            VowelFrame.this.auxBars[select - 1].setValue(190);
            VowelFrame.this.auxLabels[select].setText("Pharynx Width");
            VowelFrame.this.auxBars[select].setValue(100);
            VowelFrame.this.auxBars[select].setMaximum(200);
            VowelFrame.this.auxLabels[select + 1].setText("Mouth Width");
            VowelFrame.this.auxBars[select + 1].setValue(100);
            VowelFrame.this.auxBars[select + 1].setMaximum(200);
            return select + 2;
        }
        
        Filter genFilter() {
            final double n = VowelFrame.this.auxBars[1].getValue() / 100.0;
            final double n2 = VowelFrame.this.auxBars[2].getValue() / 100.0;
            int i;
            for (i = 0; i != 100; ++i) {
                VowelFrame.this.pipeRadius[VowelFrame.this.pipeRadius.length - 1 - i] = this.areaToRadius(this.data[i]) * n2;
            }
            while (i != VowelFrame.this.pipeRadius.length) {
                VowelFrame.this.pipeRadius[VowelFrame.this.pipeRadius.length - 1 - i] = this.areaToRadius(this.data[i]) * n;
                ++i;
            }
            return super.genFilter();
        }
    }
    
    class AVowelFilterSimple extends PipeFIRFilter
    {
        int select() {
            super.select();
            VowelFrame.this.auxLabels[1].setText("1st Section Length");
            VowelFrame.this.auxBars[1].setValue(109);
            VowelFrame.this.auxBars[1].setMaximum(200);
            return 2;
        }
        
        Filter genFilter() {
            int i;
            for (i = 0; i < VowelFrame.this.auxBars[1].getValue(); ++i) {
                VowelFrame.this.pipeRadius[i] = this.areaToRadius(1.0);
            }
            while (i != VowelFrame.this.pipeRadius.length) {
                VowelFrame.this.pipeRadius[i] = this.areaToRadius(8.0);
                ++i;
            }
            return super.genFilter();
        }
    }
    
    class AEVowelFilterSimple extends AVowelFilterSimple
    {
        int select() {
            super.select();
            VowelFrame.this.auxBars[1].setValue(66);
            return 2;
        }
    }
    
    class OVowelFilter extends PipeFIRFilter
    {
        double[] data;
        
        OVowelFilter() {
            this.data = new double[] { 3.8, 3.8, 3.8, 3.86077, 3.53659, 3.4187, 3.35976, 3.30081, 3.30081, 3.24187, 3.33028, 3.38923, 3.59553, 3.89024, 4.15549, 4.30285, 4.56809, 4.8628, 5.03963, 6.07114, 7.39736, 8.4878, 9.75508, 10.6687, 11.1402, 11.8476, 12.6138, 13.3506, 13.4685, 13.7632, 13.9695, 14.1169, 14.2053, 14.3526, 14.4116, 14.5, 14.5, 14.5, 14.5, 14.4411, 14.3526, 14.2348, 13.999, 13.2033, 12.4959, 11.9949, 11.5528, 11.1697, 10.9929, 10.6982, 10.4329, 10.2856, 10.0793, 9.93191, 9.78455, 9.60772, 9.46037, 9.25407, 9.13618, 8.98882, 8.87093, 8.72358, 8.57622, 8.42886, 8.2815, 8.10467, 7.92785, 7.78049, 7.63313, 7.54472, 7.33841, 7.22053, 7.10264, 6.98476, 6.80793, 6.6311, 6.4248, 6.30691, 6.18902, 6.04167, 5.89431, 5.77642, 5.59959, 5.42276, 5.33435, 5.18699, 5.06911, 4.95122, 4.83333, 4.68598, 4.56809, 4.4502, 4.33232, 4.21443, 4.06707, 3.89024, 3.74289, 3.50711, 3.38923, 3.15346, 3.03557, 2.77033, 2.44614, 2.21037, 1.91565, 1.76829, 1.73882, 1.79776, 2.18089, 2.74085, 2.88821, 2.91768, 2.91768, 2.82927, 2.74085, 2.41667, 2.18089, 1.85671, 1.70935, 1.47358, 1.35569, 1.26728, 1.2378, 1.20833, 1.17886, 1.17886, 1.14939, 1.17886, 1.14939, 1.14939, 1.17886, 1.14939, 1.17886, 1.17886, 1.17886, 1.17886, 1.17886, 1.17886, 1.20833, 1.20833, 1.2378, 1.26728, 1.29675, 1.35569, 1.38516, 1.47358, 1.50305, 1.56199, 1.62093, 1.70935, 1.79776, 1.85671, 1.94512, 2.06301, 2.06301, 2.18089, 2.26931, 2.3872, 2.50508, 2.5935, 2.74085, 2.88821, 3.06504, 3.24187, 3.38923, 3.56606, 3.74289, 3.97866, 4.09654, 4.21443, 4.36179, 4.59756, 4.74492, 4.89228, 5.06911, 5.18699, 5.27541, 5.48171, 5.54065, 5.57012, 5.54065, 5.09858, 2.56402, 1.88618, 1.70935, 1.73882, 1.70935, 1.73882, 1.76829, 1.79776, 1.82724, 1.85671, 1.94512, 2.00407, 2.06301, 2.15142, 2.23984, 2.29878, 2.35772, 2.53455 };
        }
        
        int select() {
            super.select();
            for (int i = 0; i != VowelFrame.this.pipeRadius.length; ++i) {
                VowelFrame.this.pipeRadius[VowelFrame.this.pipeRadius.length - 1 - i] = this.areaToRadius(this.data[i]);
            }
            VowelFrame.this.auxBars[1].setValue(190);
            return 2;
        }
    }
    
    class UVowelFilter extends PipeFIRFilter
    {
        double[] data;
        
        UVowelFilter() {
            this.data = new double[] { 0.975787, 1.57385, 0.94431, 0.661017, 0.535109, 0.440678, 0.440678, 0.377724, 0.346247, 0.31477, 0.346247, 0.409201, 0.472155, 0.62954, 0.849879, 1.07022, 1.32203, 1.66828, 2.14044, 2.48668, 2.83293, 3.1477, 3.52542, 3.93462, 4.31235, 5.50847, 7.77482, 9.8523, 10.6392, 11.1429, 11.4262, 11.7409, 12.0872, 12.276, 12.4649, 12.5908, 12.6852, 12.7797, 12.8426, 12.8741, 12.9685, 13.0315, 13.063, 13.063, 13.063, 13.063, 13.0, 12.937, 12.8741, 12.6538, 12.3705, 12.0872, 11.4576, 10.6077, 9.75787, 9.19128, 8.78208, 8.49879, 8.05811, 7.77482, 7.49153, 7.23971, 6.95642, 6.67312, 6.42131, 6.23245, 5.94915, 5.69734, 5.50847, 5.31961, 5.13075, 4.75303, 4.62712, 4.40678, 4.12349, 3.93462, 3.80872, 3.65133, 3.36804, 3.21065, 3.08475, 2.92736, 2.83293, 2.70702, 2.61259, 2.54964, 2.45521, 2.42373, 2.39225, 2.3293, 2.3293, 2.29782, 2.29782, 2.3293, 2.3293, 2.3293, 2.3293, 2.29782, 2.26634, 2.20339, 2.10896, 1.95157, 1.79419, 1.47942, 1.29056, 1.29056, 1.44794, 2.046, 2.42373, 2.58111, 2.64407, 2.64407, 2.54964, 2.42373, 2.29782, 2.14044, 1.95157, 1.85714, 1.76271, 1.66828, 1.57385, 1.47942, 1.41646, 1.35351, 1.29056, 1.29056, 1.25908, 1.2276, 1.2276, 1.2276, 1.25908, 1.32203, 1.32203, 1.35351, 1.38499, 1.47942, 1.5109, 1.57385, 1.66828, 1.79419, 1.88862, 2.01453, 2.17191, 2.39225, 2.58111, 2.86441, 3.1477, 3.52542, 3.99758, 4.46973, 4.97337, 5.50847, 5.98063, 6.32688, 6.76755, 7.08232, 7.36562, 7.64891, 7.90073, 8.15254, 8.3414, 8.43584, 8.56174, 8.65617, 8.75061, 8.81356, 8.87651, 8.97094, 9.00242, 9.0339, 9.06538, 9.09685, 9.12833, 9.12833, 9.15981, 9.15981, 9.15981, 9.15981, 9.15981, 9.12833, 7.49153, 3.1477, 2.76998, 2.61259, 2.54964, 2.51816, 2.51816, 2.54964, 2.58111, 2.61259, 2.67554, 2.70702, 2.7385, 2.80145, 2.86441, 2.92736, 2.95884, 2.99031, 3.05327, 3.08475 };
        }
        
        int select() {
            super.select();
            for (int i = 0; i != VowelFrame.this.pipeRadius.length; ++i) {
                VowelFrame.this.pipeRadius[VowelFrame.this.pipeRadius.length - 1 - i] = this.areaToRadius(this.data[i]);
            }
            VowelFrame.this.auxBars[1].setValue(190);
            return 2;
        }
    }
    
    class YVowelFilterSimple extends PipeFIRFilter
    {
        int select() {
            super.select();
            VowelFrame.this.auxLabels[1].setText("1st Section Length");
            VowelFrame.this.auxBars[1].setValue(100);
            VowelFrame.this.auxBars[1].setMaximum(200);
            return 2;
        }
        
        Filter genFilter() {
            int i;
            for (i = 0; i < VowelFrame.this.auxBars[1].getValue(); ++i) {
                VowelFrame.this.pipeRadius[i] = this.areaToRadius(8.0);
            }
            while (i != VowelFrame.this.pipeRadius.length) {
                VowelFrame.this.pipeRadius[i] = this.areaToRadius(1.0);
                ++i;
            }
            return super.genFilter();
        }
    }
    
    class IVowelFilterSimple extends YVowelFilterSimple
    {
        int select() {
            super.select();
            VowelFrame.this.auxBars[0].setValue(145);
            VowelFrame.this.auxBars[1].setValue(120);
            return 2;
        }
    }
    
    class UrVowelFilterSimple extends YVowelFilterSimple
    {
        int select() {
            super.select();
            VowelFrame.this.auxBars[1].setValue(177);
            return 2;
        }
    }
    
    class IhVowelFilterSimple extends PipeFIRFilter
    {
        int select() {
            super.select();
            VowelFrame.this.auxBars[0].setValue(160);
            return 1;
        }
        
        Filter genFilter() {
            int i;
            for (i = 0; i < 37; ++i) {
                VowelFrame.this.pipeRadius[i] = this.areaToRadius(1.0);
            }
            while (i < 112) {
                VowelFrame.this.pipeRadius[i] = this.areaToRadius(7.0);
                ++i;
            }
            while (i != VowelFrame.this.pipeRadius.length) {
                VowelFrame.this.pipeRadius[i] = this.areaToRadius(1.0);
                ++i;
            }
            return super.genFilter();
        }
    }
    
    class OoVowelFilterSimple extends PipeFIRFilter
    {
        int select() {
            super.select();
            VowelFrame.this.auxBars[0].setValue(180);
            return 1;
        }
        
        Filter genFilter() {
            int i;
            for (i = 0; i < 33; ++i) {
                VowelFrame.this.pipeRadius[i] = this.areaToRadius(1.0);
            }
            while (i < 88) {
                VowelFrame.this.pipeRadius[i] = this.areaToRadius(7.0);
                ++i;
            }
            while (i < 122) {
                VowelFrame.this.pipeRadius[i] = this.areaToRadius(1.0);
                ++i;
            }
            while (i < 177) {
                VowelFrame.this.pipeRadius[i] = this.areaToRadius(7.0);
                ++i;
            }
            while (i != VowelFrame.this.pipeRadius.length) {
                VowelFrame.this.pipeRadius[i] = this.areaToRadius(1.0);
                ++i;
            }
            return super.genFilter();
        }
    }
    
    class NoFilter extends FilterType
    {
        void getResponse(final double n, final Complex complex) {
            complex.set(1.0);
        }
        
        Filter genFilter() {
            final DirectFilter directFilter = new DirectFilter();
            (directFilter.aList = new double[1])[0] = 1.0;
            return directFilter;
        }
    }
    
    class ImportDialogLayout implements LayoutManager
    {
        public void addLayoutComponent(final String s, final Component component) {
        }
        
        public void removeLayoutComponent(final Component component) {
        }
        
        public Dimension preferredLayoutSize(final Container container) {
            return new Dimension(500, 500);
        }
        
        public Dimension minimumLayoutSize(final Container container) {
            return new Dimension(100, 100);
        }
        
        public void layoutContainer(final Container container) {
            final Insets insets = container.insets();
            final int n = container.size().width - insets.left - insets.right;
            final int n2 = container.size().height - (insets.top + insets.bottom);
            if (container.getComponentCount() == 0) {
                return;
            }
            final Dimension preferredSize = container.getComponent(container.getComponentCount() - 1).getPreferredSize();
            container.getComponent(0).move(insets.left, insets.top);
            final int n3 = container.size().width - insets.left - insets.right;
            final int n4 = container.size().height - insets.top - insets.bottom - preferredSize.height;
            container.getComponent(0).resize(n3, n4);
            final int n5 = n4 + insets.top;
            int n6 = 0;
            for (int i = 1; i < container.getComponentCount(); ++i) {
                final Component component = container.getComponent(i);
                if (component.isVisible()) {
                    final Dimension preferredSize2 = component.getPreferredSize();
                    component.move(insets.left + n6, n5);
                    component.resize(preferredSize2.width, preferredSize2.height);
                    n6 += preferredSize2.width;
                }
            }
        }
    }
    
    class ImportDialog extends Dialog implements ActionListener
    {
        VowelFrame rframe;
        Button importButton;
        Button clearButton;
        Button closeButton;
        TextArea text;
        
        ImportDialog(final VowelFrame rframe, final String s) {
            super(rframe, (s.length() > 0) ? "Export" : "Import", false);
            this.rframe = rframe;
            this.setLayout(new ImportDialogLayout());
            this.add(this.text = new TextArea(s, 10, 60, 0));
            this.add(this.importButton = new Button("Import"));
            this.importButton.addActionListener(this);
            this.add(this.clearButton = new Button("Clear"));
            this.clearButton.addActionListener(this);
            this.add(this.closeButton = new Button("Close"));
            this.closeButton.addActionListener(this);
            final Point locationOnScreen = this.rframe.getLocationOnScreen();
            this.resize(400, 300);
            final Dimension size = this.getSize();
            this.setLocation(locationOnScreen.x + (VowelFrame.this.winSize.width - size.width) / 2, locationOnScreen.y + (VowelFrame.this.winSize.height - size.height) / 2);
            this.show();
            if (s.length() > 0) {
                this.text.selectAll();
            }
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            final Object source = actionEvent.getSource();
            if (source == this.importButton) {
                this.rframe.readImport(this.text.getText());
                this.setVisible(false);
            }
            if (source == this.closeButton) {
                this.setVisible(false);
            }
            if (source == this.clearButton) {
                this.text.setText("");
            }
        }
        
        public boolean handleEvent(final Event event) {
            if (event.id == 201) {
                this.rframe.requestFocus();
                this.setVisible(false);
                return true;
            }
            return super.handleEvent(event);
        }
    }
}
