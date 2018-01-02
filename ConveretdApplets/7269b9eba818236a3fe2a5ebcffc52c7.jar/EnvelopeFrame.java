import javax.sound.sampled.Line;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.AudioFormat;
import java.awt.event.ItemEvent;
import java.awt.Event;
import java.awt.event.AdjustmentEvent;
import java.awt.event.ComponentEvent;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Label;
import java.awt.Component;
import java.awt.LayoutManager;
import javax.sound.sampled.SourceDataLine;
import java.awt.Scrollbar;
import java.awt.Choice;
import java.awt.Checkbox;
import java.awt.Dimension;
import java.awt.event.ItemListener;
import java.awt.event.AdjustmentListener;
import java.awt.event.ComponentListener;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class EnvelopeFrame extends Frame implements ComponentListener, AdjustmentListener, ItemListener, Runnable
{
    Dimension winSize;
    Checkbox soundCheck;
    Choice setupChooser;
    Scrollbar stepLengthBar;
    Scrollbar[] stepCountBars;
    Scrollbar[] stepSizeBars;
    Scrollbar pulseWidthBar;
    static final double pi = 3.141592653589793;
    int[] freqMap;
    Thread engine;
    EnvelopeCanvas cv;
    Envelope applet;
    int freq;
    int phase;
    int steps;
    int skew;
    SourceDataLine line;
    final int bufferSize = 512;
    final int rate = 8000;
    static /* synthetic */ Class class$javax$sound$sampled$SourceDataLine;
    
    public String getAppletInfo() {
        return "Envelope by Paul Falstad";
    }
    
    EnvelopeFrame(final Envelope applet) {
        super("Envelope Applet v1.1");
        this.engine = null;
        this.line = null;
        this.applet = applet;
    }
    
    public void init() {
        this.setLayout(new EnvelopeLayout());
        (this.cv = new EnvelopeCanvas(this)).addComponentListener(this);
        this.add(this.cv);
        (this.soundCheck = new Checkbox("Sound on", false)).addItemListener(this);
        this.add(this.soundCheck);
        this.setupChooser = new Choice();
        for (int i = 0; i != 23; ++i) {
            this.setupChooser.add("Preset #" + (i + 1));
        }
        this.setupChooser.addItemListener(this);
        this.add(this.setupChooser);
        this.add(new Label("Step Length", 1));
        this.add(this.stepLengthBar = new Scrollbar(0, 1, 1, 1, 128));
        this.stepLengthBar.addAdjustmentListener(this);
        this.stepCountBars = new Scrollbar[3];
        this.stepSizeBars = new Scrollbar[3];
        for (int j = 0; j != 3; ++j) {
            this.add(new Label("Step Count " + (j + 1), 1));
            this.add(this.stepCountBars[j] = new Scrollbar(0, 48, 1, 0, 256));
            this.stepCountBars[j].addAdjustmentListener(this);
            this.add(new Label("Step Size " + (j + 1), 1));
            this.add(this.stepSizeBars[j] = new Scrollbar(0, 48, 1, 0, 256));
            this.stepSizeBars[j].addAdjustmentListener(this);
        }
        this.add(new Label("Pulse Width", 1));
        this.add(this.pulseWidthBar = new Scrollbar(0, 128, 1, 1, 255));
        this.pulseWidthBar.addAdjustmentListener(this);
        this.freqMap = new int[256];
        for (int k = 0; k != 256; ++k) {
            this.freqMap[k] = (int)(Math.exp(0.6931471805599453 * (k - 89) / 48.0) * 263.0);
        }
        this.cv.setBackground(Color.black);
        this.cv.setForeground(Color.lightGray);
        this.doSetup();
        this.resize(350, 450);
        this.handleResize();
        this.show();
    }
    
    void handleResize() {
        this.winSize = this.cv.getSize();
        if (this.winSize.width == 0) {
            return;
        }
    }
    
    void centerString(final Graphics graphics, final String s, final int n) {
        graphics.drawString(s, (this.winSize.width - graphics.getFontMetrics().stringWidth(s)) / 2, n);
    }
    
    public void paint(final Graphics graphics) {
        this.cv.repaint();
    }
    
    public void updateEnvelope(final Graphics graphics) {
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, this.winSize.width, this.winSize.height);
        graphics.setColor(Color.black);
        int n = 20;
        this.centerString(graphics, "Step length: " + this.stepLengthBar.getValue() * 10 + " ms", n);
        for (int i = 0; i != 3; ++i) {
            final String string = "Step count " + (i + 1) + ": " + this.stepCountBars[i].getValue();
            n += 20;
            this.centerString(graphics, string, n);
            final String string2 = "Step size " + (i + 1) + ": " + (this.stepSizeBars[i].getValue() - 128);
            n += 20;
            this.centerString(graphics, string2, n);
        }
        final String string3 = "Pulse width: " + this.pulseWidthBar.getValue();
        n += 20;
        this.centerString(graphics, string3, n);
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
        this.cv.repaint();
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        System.out.print(((Scrollbar)adjustmentEvent.getSource()).getValue() + "\n");
        this.cv.repaint();
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.soundCheck.setState(false);
            this.applet.destroyFrame();
            return true;
        }
        return super.handleEvent(event);
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getItemSelectable() == this.soundCheck && this.soundCheck.getState()) {
            this.doPlay();
        }
        if (itemEvent.getItemSelectable() == this.setupChooser) {
            this.doSetup();
        }
    }
    
    void setBars(final int value, final int value2, final int n, final int value3, final int n2, final int value4, final int n3) {
        this.stepLengthBar.setValue(value);
        this.stepCountBars[0].setValue(value2);
        this.stepCountBars[1].setValue(value3);
        this.stepCountBars[2].setValue(value4);
        this.stepSizeBars[0].setValue(n + 128);
        this.stepSizeBars[1].setValue(n2 + 128);
        this.stepSizeBars[2].setValue(n3 + 128);
    }
    
    void doSetup() {
        switch (this.setupChooser.getSelectedIndex()) {
            case 0: {
                this.setBars(4, 1, 12, 1, -10, 0, 0);
                break;
            }
            case 1: {
                this.setBars(1, 1, -2, 8, 32, 0, 0);
                break;
            }
            case 2: {
                this.setBars(1, 4, 1, 6, -1, 0, 0);
                break;
            }
            case 3: {
                this.setBars(2, 1, -76, 1, -38, 0, 0);
                break;
            }
            case 4: {
                this.setBars(2, 16, -72, 0, 0, 0, 0);
                break;
            }
            case 5: {
                this.setBars(2, 11, -64, 1, -27, 0, 0);
                break;
            }
            case 6: {
                this.setBars(1, 9, -32, 4, 0, 0, 0);
                break;
            }
            case 7: {
                this.setBars(1, 16, -32, 1, 1, 0, 0);
                break;
            }
            case 8: {
                this.setBars(1, 3, -49, 0, 0, 0, 0);
                break;
            }
            case 9: {
                this.setBars(1, 86, -53, 30, 32, 0, 0);
                break;
            }
            case 10: {
                this.setBars(1, 253, -19, 22, 8, 22, -8);
                break;
            }
            case 11: {
                this.setBars(1, 116, -91, 11, -92, 0, 0);
                break;
            }
            case 12: {
                this.setBars(6, 1, -49, 0, 0, 0, 0);
                break;
            }
            case 13: {
                this.setBars(8, 1, -14, 0, 0, 0, 0);
                break;
            }
            case 14: {
                this.setBars(2, 16, -14, 18, 12, 0, 0);
                break;
            }
            case 15: {
                this.setBars(8, 16, 48, 1, 13, 0, 0);
                break;
            }
            case 16: {
                this.setBars(2, 1, 48, 0, 0, 0, 0);
                break;
            }
            case 17: {
                this.setBars(3, 2, 48, 3, -48, 0, 0);
                break;
            }
            case 18: {
                this.setBars(3, 2, 48, 2, -48, 0, 0);
                break;
            }
            case 19: {
                this.setBars(3, 2, 48, 4, -18, 0, 0);
                break;
            }
            case 20: {
                this.setBars(4, 1, 48, 1, -96, 0, 0);
                break;
            }
            case 21: {
                this.setBars(4, 3, 48, 1, -97, 0, 0);
                break;
            }
            case 22: {
                this.setBars(2, 3, 48, 1, 111, 0, 0);
                break;
            }
            default: {
                this.setBars(20, 1, 1, 1, 1, 1, 1);
                break;
            }
        }
        this.freq = 89;
        this.cv.repaint();
    }
    
    void doPlay() {
        this.skew = 0;
        final AudioFormat audioFormat = new AudioFormat(8000.0f, 8, 1, true, false);
        final DataLine.Info info = new DataLine.Info((EnvelopeFrame.class$javax$sound$sampled$SourceDataLine == null) ? (EnvelopeFrame.class$javax$sound$sampled$SourceDataLine = class$("javax.sound.sampled.SourceDataLine")) : EnvelopeFrame.class$javax$sound$sampled$SourceDataLine, audioFormat);
        if (this.line == null) {
            try {
                (this.line = (SourceDataLine)AudioSystem.getLine(info)).open(audioFormat, 512);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            this.line.start();
        }
        this.freq = 89;
        final boolean b = false;
        this.steps = (b ? 1 : 0);
        this.phase = (b ? 1 : 0);
        this.start();
    }
    
    public void start() {
        if (this.engine == null) {
            (this.engine = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.engine != null && this.engine.isAlive()) {
            this.engine.stop();
        }
        this.engine = null;
    }
    
    public void run() {
        try {
            byte[] array = { 0 };
            while (this.soundCheck.getState()) {
                final int n = 80 * this.stepLengthBar.getValue();
                if (array.length != n) {
                    array = new byte[n];
                }
                final double n2 = 256.0 * this.freqMap[this.freq] / 8000.0;
                final int value = this.pulseWidthBar.getValue();
                for (int i = 0; i != n; ++i) {
                    array[i] = (byte)((((int)((i + this.skew) * n2) & 0xFF) < value) ? 127 : -127);
                }
                this.skew += n;
                this.freq = (this.freq + this.stepSizeBars[this.phase].getValue() - 128 & 0xFF);
                if (--this.steps <= 0) {
                    for (int j = 3; j > 0; --j) {
                        this.phase = (this.phase + 1) % 3;
                        this.steps = this.stepCountBars[this.phase].getValue();
                        if (this.steps > 0) {
                            break;
                        }
                    }
                }
                this.line.write(array, 0, n);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        this.engine = null;
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}
