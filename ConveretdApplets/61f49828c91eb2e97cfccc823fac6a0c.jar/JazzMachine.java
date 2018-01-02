import java.awt.image.ImageObserver;
import java.applet.AppletContext;
import java.net.URL;
import java.awt.event.MouseEvent;
import java.awt.event.AdjustmentEvent;
import sun.audio.ContinuousAudioDataStream;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.Panel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Canvas;
import java.awt.Label;
import java.awt.Scrollbar;
import java.io.InputStream;
import java.awt.event.MouseListener;
import java.awt.event.AdjustmentListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class JazzMachine extends Applet implements Runnable, AdjustmentListener, MouseListener
{
    static final String lsep;
    static final double rate = 8000.0;
    static final int minFreqValue = 1;
    static final int maxFreqValue = 200;
    static final int minAmplValue = 0;
    static final int maxAmplValue = 100;
    private boolean audioOn;
    private boolean changed;
    private int freqValue;
    private int amplValue;
    private int frequency;
    private int amplitude;
    private byte[] mu;
    private InputStream soundStream;
    private Scrollbar barVolume;
    private Scrollbar barFreq;
    private Label labelNS;
    private Label labelValueFreq;
    private Canvas canvas;
    private boolean showFreq;
    private int cw;
    private int ch;
    private Image img;
    private Graphics graph;
    private int ovalX;
    private int ovalY;
    private int ovalW;
    private int ovalH;
    private Color ovalColor;
    private Color inactiveLinkColor;
    private Color activeLinkColor;
    private Font inactiveLinkFont;
    private Font activeLinkFont;
    private long startTime;
    private long fixedTime;
    Thread runner;
    
    public JazzMachine() {
        this.audioOn = false;
        this.changed = true;
        this.freqValue = 1;
        this.amplValue = 70;
        this.showFreq = true;
        this.inactiveLinkColor = Color.yellow;
        this.activeLinkColor = Color.white;
        this.inactiveLinkFont = new Font("Dialog", 0, 16);
        this.activeLinkFont = new Font("Dialog", 1, 16);
        this.fixedTime = 10000L;
    }
    
    public void init() {
        this.setAppletParams();
        this.amplitude = (100 - this.amplValue) * 300;
        this.frequency = (int)Math.pow(1.2, (this.freqValue + 250) / 10.0);
        this.setLayout(new BorderLayout());
        this.setBackground(Color.darkGray);
        this.setForeground(Color.white);
        this.setFont(new Font("Dialog", 0, 14));
        final Label label = new Label("Volume");
        label.setAlignment(1);
        (this.barVolume = new Scrollbar(1, this.amplValue, 1, 0, 101)).addAdjustmentListener(this);
        final Panel panel = new Panel();
        panel.setLayout(null);
        panel.add(this.barVolume);
        this.barVolume.setSize(16, 90);
        panel.setSize(16, 90);
        final Label label2 = new Label("Frequency");
        label2.setAlignment(2);
        (this.barFreq = new Scrollbar(0, this.freqValue, 1, 1, 200)).addAdjustmentListener(this);
        final Panel panel2 = new Panel();
        panel2.setLayout(null);
        panel2.add(this.barFreq);
        this.barFreq.setSize(150, 18);
        panel2.setSize(150, 18);
        this.labelValueFreq = new Label();
        if (this.showFreq) {
            this.labelValueFreq.setText(String.valueOf(new Integer(this.frequency).toString()) + " Hz  ");
        }
        this.labelValueFreq.setAlignment(0);
        final Panel panel3 = new Panel();
        panel3.setLayout(new BorderLayout());
        panel3.add("North", label);
        final Panel panel4 = new Panel();
        panel4.add(panel);
        panel3.add("Center", panel4);
        final Panel panel5 = new Panel();
        final Panel panel6 = new Panel();
        panel6.setLayout(new FlowLayout(1));
        panel6.add(label2);
        panel6.add(panel2);
        panel6.add(this.labelValueFreq);
        panel5.add("South", panel6);
        final Panel panel7 = new Panel();
        panel7.setLayout(new FlowLayout(1, 0, 5));
        panel7.setFont(new Font("Dialog", 0, 16));
        panel7.setForeground(Color.yellow);
        final Cursor cursor = new Cursor(12);
        final Label label3 = new Label("The jazz machine ©");
        label3.setAlignment(2);
        label3.addMouseListener(this);
        label3.setCursor(cursor);
        (this.labelNS = new Label(" Neural Semantics   ")).setForeground(this.inactiveLinkColor);
        this.labelNS.setFont(this.inactiveLinkFont);
        this.labelNS.setAlignment(0);
        this.labelNS.addMouseListener(this);
        this.labelNS.setCursor(cursor);
        panel7.add(label3);
        panel7.add(this.labelNS);
        final Panel panel8 = new Panel();
        panel8.add(new Label("      "));
        this.add("South", panel5);
        this.add("East", panel3);
        this.add("North", panel7);
        this.add("West", panel8);
        this.add("Center", this.canvas = new Canvas());
    }
    
    private void switchAudio(final boolean b) {
        if (b && !this.audioOn) {
            try {
                AudioPlayer.player.start(this.soundStream);
            }
            catch (Exception ex) {}
            this.audioOn = true;
        }
        if (!b && this.audioOn) {
            try {
                AudioPlayer.player.stop(this.soundStream);
            }
            catch (Exception ex2) {}
            this.audioOn = false;
        }
    }
    
    private void getChanges() {
        this.mu = this.getWave(this.frequency, this.amplitude);
        if (this.showFreq) {
            this.labelValueFreq.setText(String.valueOf(new Integer(this.frequency).toString()) + " Hz");
        }
        this.switchAudio(false);
        try {
            this.soundStream = new ContinuousAudioDataStream(new AudioData(this.mu));
        }
        catch (Exception ex) {}
        this.switchAudio(true);
        final double n = this.freqValue / 200.0;
        this.ovalW = (int)(n * this.cw);
        this.ovalH = (int)(n * this.ch);
        this.ovalX = (this.cw - this.ovalW) / 2;
        this.ovalY = (this.ch - this.ovalH) / 2;
        this.ovalColor = new Color((int)(255.0 * n), (int)(511.0 * (0.5 - Math.abs(0.5 - n))), (int)(255.0 * (1.0 - n)));
        this.startTime = System.currentTimeMillis();
        this.changed = false;
    }
    
    public void start() {
        if (this.runner == null) {
            (this.runner = new Thread(this)).start();
        }
    }
    
    public void run() {
        while (true) {
            if (this.changed) {
                this.getChanges();
            }
            this.repaint();
            if (System.currentTimeMillis() - this.startTime > this.fixedTime) {
                this.switchAudio(false);
            }
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void stop() {
        this.cleanup();
    }
    
    public void destroy() {
        this.cleanup();
    }
    
    private synchronized void cleanup() {
        this.switchAudio(false);
        if (this.runner != null) {
            try {
                this.runner.stop();
                this.runner.join();
                this.runner = null;
            }
            catch (Exception ex) {}
        }
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        final Object source = adjustmentEvent.getSource();
        if (source == this.barVolume) {
            this.amplitude = (100 - this.barVolume.getValue()) * 300;
            this.changed = true;
            return;
        }
        if (source == this.barFreq) {
            this.freqValue = this.barFreq.getValue();
            this.frequency = (int)Math.pow(1.2, (this.freqValue + 250) / 10.0);
            this.changed = true;
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.labelNS.setForeground(this.activeLinkColor);
        this.labelNS.setFont(this.activeLinkFont);
        this.showStatus("Click to connect to Neural Semantics home page");
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.labelNS.setForeground(this.inactiveLinkColor);
        this.labelNS.setFont(this.inactiveLinkFont);
        this.showStatus("");
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        String s = this.getDocumentBase().getHost();
        if (s.length() > 4 && s.substring(0, 4).equals("www.")) {
            s = s.substring(4);
        }
        if (s != null && !s.startsWith("neuralsemantics.com")) {
            try {
                final URL url = new URL("http://www.neuralsemantics.com/");
                final AppletContext appletContext = this.getAppletContext();
                if (appletContext != null) {
                    appletContext.showDocument(url);
                }
            }
            catch (Exception ex) {}
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void update(final Graphics graphics) {
        final Graphics graphics2 = this.canvas.getGraphics();
        if (this.img == null) {
            this.cw = this.canvas.getSize().width;
            this.ch = this.canvas.getSize().height;
            this.img = this.createImage(this.cw, this.ch);
            this.graph = this.img.getGraphics();
        }
        this.graph.setColor(Color.black);
        this.graph.fillRect(0, 0, this.cw, this.ch);
        this.graph.setColor(this.ovalColor);
        this.graph.fillOval(this.ovalX, this.ovalY, this.ovalW, this.ovalH);
        if (graphics2 != null) {
            graphics2.drawImage(this.img, 0, 0, this.canvas);
            graphics2.dispose();
        }
    }
    
    private byte[] getWave(final int n, final int n2) {
        final int n3 = (int)(8000.0 / n);
        final byte[] array = new byte[n3];
        final double n4 = 6.283185307179586 * n / 8000.0;
        for (int i = 0; i < n3; ++i) {
            array[i] = linToMu((int)(Math.sin(n4 * i) * n2));
        }
        return array;
    }
    
    private static byte linToMu(int n) {
        int n2;
        if (n < 0) {
            n = -n;
            n2 = 127;
        }
        else {
            n2 = 255;
        }
        if (n < 32) {
            n = (0xF0 | 15 - n / 2);
        }
        else if (n < 96) {
            n = (0xE0 | 15 - (n - 32) / 4);
        }
        else if (n < 224) {
            n = (0xD0 | 15 - (n - 96) / 8);
        }
        else if (n < 480) {
            n = (0xC0 | 15 - (n - 224) / 16);
        }
        else if (n < 992) {
            n = (0xB0 | 15 - (n - 480) / 32);
        }
        else if (n < 2016) {
            n = (0xA0 | 15 - (n - 992) / 64);
        }
        else if (n < 4064) {
            n = (0x90 | 15 - (n - 2016) / 128);
        }
        else if (n < 8160) {
            n = (0x80 | 15 - (n - 4064) / 256);
        }
        else {
            n = 128;
        }
        return (byte)(n2 & n);
    }
    
    public String getAppletInfo() {
        return "The jazz machine" + JazzMachine.lsep + JazzMachine.lsep + "A music generator applet" + JazzMachine.lsep + "Copyright (c) Neural Semantics, 2000" + JazzMachine.lsep + JazzMachine.lsep + "Home page : http://www.neuralsemantics.com/";
    }
    
    private void setAppletParams() {
        final String parameter = this.getParameter("showfreq");
        if (parameter != null && parameter.toUpperCase().equals("OFF")) {
            this.showFreq = false;
        }
    }
    
    static {
        lsep = System.getProperty("line.separator");
    }
}
