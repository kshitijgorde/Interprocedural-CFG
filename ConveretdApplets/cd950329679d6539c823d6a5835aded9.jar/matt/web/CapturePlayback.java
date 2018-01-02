// 
// Decompiled by Procyon v0.5.30
// 

package matt.web;

import java.awt.font.TextLayout;
import java.awt.font.FontRenderContext;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Shape;
import java.awt.font.LineBreakMeasurer;
import java.text.AttributedCharacterIterator;
import java.awt.font.TextAttribute;
import java.text.AttributedString;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.geom.Line2D;
import java.awt.Color;
import java.awt.Font;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Line;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import javax.swing.JFrame;
import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.JFileChooser;
import javax.sound.sampled.AudioFileFormat;
import java.awt.event.ActionEvent;
import javax.swing.border.CompoundBorder;
import java.awt.Component;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.border.Border;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.EmptyBorder;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.sound.sampled.AudioFormat;
import java.util.Vector;
import java.io.File;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JApplet;
import javax.sound.sampled.AudioInputStream;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class CapturePlayback extends JPanel implements ActionListener, ControlContext
{
    final int bufSize = 16384;
    Capture capture;
    Playback playback;
    AudioInputStream audioInputStream;
    SamplingGraph samplingGraph;
    private JApplet applet;
    JButton playB;
    JButton captB;
    JButton pausB;
    JButton loadB;
    JButton waveB;
    JTextField textField;
    String fileName;
    String errStr;
    double duration;
    double seconds;
    File file;
    Vector lines;
    AudioFormat format;
    
    public CapturePlayback() {
        this.capture = new Capture();
        this.playback = new Playback();
        this.fileName = "untitled";
        this.lines = new Vector();
        this.format = null;
        this.format = new AudioFormat(44100.0f, 16, 1, true, false);
        this.setLayout(new BorderLayout());
        EmptyBorder eb = new EmptyBorder(5, 5, 5, 5);
        final SoftBevelBorder sbb = new SoftBevelBorder(1);
        this.setBorder(new EmptyBorder(5, 5, 5, 5));
        final JPanel p1 = new JPanel();
        p1.setLayout(new BoxLayout(p1, 0));
        final JPanel p2 = new JPanel();
        p2.setBorder(sbb);
        p2.setLayout(new BoxLayout(p2, 1));
        final JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBorder(new EmptyBorder(10, 0, 5, 0));
        this.playB = this.addButton("Play", buttonsPanel, false);
        this.captB = this.addButton("Record", buttonsPanel, true);
        this.pausB = this.addButton("Pause", buttonsPanel, false);
        this.waveB = this.addButton("Submit", buttonsPanel, false);
        p2.add(buttonsPanel);
        final JPanel samplingPanel = new JPanel(new BorderLayout());
        eb = new EmptyBorder(10, 20, 20, 20);
        samplingPanel.setBorder(new CompoundBorder(eb, sbb));
        samplingPanel.add(this.samplingGraph = new SamplingGraph());
        p2.add(samplingPanel);
        p1.add(p2);
        this.add(p1);
    }
    
    public void open() {
    }
    
    public void close() {
        if (this.playback.thread != null) {
            this.playB.doClick(0);
        }
        if (this.capture.thread != null) {
            this.captB.doClick(0);
        }
    }
    
    private JButton addButton(final String name, final JPanel p, final boolean state) {
        final JButton b = new JButton(name);
        b.addActionListener(this);
        b.setEnabled(state);
        p.add(b);
        return b;
    }
    
    public void actionPerformed(final ActionEvent e) {
        final Object obj = e.getSource();
        if (obj.equals(this.waveB)) {
            this.saveToFile(AudioFileFormat.Type.WAVE);
        }
        else if (obj.equals(this.playB)) {
            if (this.playB.getText().startsWith("Play")) {
                this.playback.start();
                this.samplingGraph.start();
                this.captB.setEnabled(false);
                this.pausB.setEnabled(true);
                this.playB.setText("Stop");
            }
            else {
                this.playback.stop();
                this.samplingGraph.stop();
                this.captB.setEnabled(true);
                this.pausB.setEnabled(false);
                this.playB.setText("Play");
            }
        }
        else if (obj.equals(this.captB)) {
            if (this.captB.getText().startsWith("Record")) {
                this.file = null;
                this.capture.start();
                this.fileName = "untitled";
                this.samplingGraph.start();
                this.playB.setEnabled(false);
                this.pausB.setEnabled(true);
                this.captB.setText("Stop");
            }
            else {
                this.lines.removeAllElements();
                this.capture.stop();
                this.samplingGraph.stop();
                this.playB.setEnabled(true);
                this.pausB.setEnabled(false);
                this.waveB.setEnabled(true);
                this.captB.setText("Record");
            }
        }
        else if (obj.equals(this.pausB)) {
            if (this.pausB.getText().startsWith("Pause")) {
                if (this.capture.thread != null) {
                    this.capture.line.stop();
                }
                else if (this.playback.thread != null) {
                    this.playback.line.stop();
                }
                this.pausB.setText("Resume");
            }
            else {
                if (this.capture.thread != null) {
                    this.capture.line.start();
                }
                else if (this.playback.thread != null) {
                    this.playback.line.start();
                }
                this.pausB.setText("Pause");
            }
        }
        else if (obj.equals(this.loadB)) {
            try {
                final File file = new File(System.getProperty("user.dir"));
                final JFileChooser fc = new JFileChooser(file);
                fc.setFileFilter(new FileFilter() {
                    public boolean accept(final File f) {
                        if (f.isDirectory()) {
                            return true;
                        }
                        final String name = f.getName();
                        return name.endsWith(".au") || name.endsWith(".wav") || name.endsWith(".aiff") || name.endsWith(".aif");
                    }
                    
                    public String getDescription() {
                        return ".au, .wav, .aif";
                    }
                });
                if (fc.showOpenDialog(null) == 0) {
                    this.createAudioInputStream(fc.getSelectedFile(), true);
                }
            }
            catch (SecurityException ex) {
                showInfoDialog();
                ex.printStackTrace();
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
            }
        }
    }
    
    public static void showInfoDialog() {
        final String msg = "When running the Java Sound demo as an applet these permissions\nare necessary in order to load/save files and record audio :  \n\ngrant { \n  permission java.io.FilePermission \"<<ALL FILES>>\", \"read, write\";\n  permission javax.sound.sampled.AudioPermission \"record\"; \n  permission java.util.PropertyPermission \"user.dir\", \"read\";\n}; \n\nThe permissions need to be added to the .java.policy file.";
        new Thread(new Runnable() {
            public void run() {
                JOptionPane.showMessageDialog(null, "When running the Java Sound demo as an applet these permissions\nare necessary in order to load/save files and record audio :  \n\ngrant { \n  permission java.io.FilePermission \"<<ALL FILES>>\", \"read, write\";\n  permission javax.sound.sampled.AudioPermission \"record\"; \n  permission java.util.PropertyPermission \"user.dir\", \"read\";\n}; \n\nThe permissions need to be added to the .java.policy file.", "Applet Info", 1);
            }
        }).start();
    }
    
    public void createAudioInputStream(final File file, final boolean updateComponents) {
        if (file != null && file.isFile()) {
            try {
                this.file = file;
                this.errStr = null;
                this.audioInputStream = AudioSystem.getAudioInputStream(file);
                this.playB.setEnabled(true);
                this.fileName = file.getName();
                final long milliseconds = (long)(this.audioInputStream.getFrameLength() * 1000L / this.audioInputStream.getFormat().getFrameRate());
                this.duration = milliseconds / 1000.0;
                this.waveB.setEnabled(true);
                if (updateComponents) {
                    this.samplingGraph.createWaveForm(null);
                }
            }
            catch (Exception ex) {
                this.reportStatus(ex.toString());
                ex.printStackTrace();
            }
        }
        else {
            this.reportStatus("Audio file required.");
        }
    }
    
    public void saveToFile(final AudioFileFormat.Type fileType) {
        if (this.audioInputStream == null) {
            this.reportStatus("No loaded audio to save");
            return;
        }
        if (this.file != null) {
            this.createAudioInputStream(this.file, false);
        }
        try {
            this.audioInputStream.reset();
        }
        catch (Exception e) {
            e.printStackTrace();
            this.reportStatus("Unable to reset stream " + e);
            return;
        }
        try {
            final Upload upload = new Upload();
            final int id = upload.submit(this.audioInputStream, fileType);
            String url = "" + this.applet.getDocumentBase();
            url = url.substring(0, url.lastIndexOf("/"));
            url = url + "/searching.php?id=" + id;
            System.out.println(url);
            this.applet.getAppletContext().showDocument(new URL(url), "resultsFrame");
        }
        catch (Exception ex) {
            this.reportStatus(ex.toString());
            ex.printStackTrace();
        }
        this.samplingGraph.repaint();
    }
    
    private void reportStatus(final String msg) {
        this.errStr = msg;
        if (msg != null) {
            System.out.println(this.errStr);
            this.samplingGraph.repaint();
        }
    }
    
    public JApplet getApplet() {
        return this.applet;
    }
    
    public void setApplet(final JApplet applet) {
        this.applet = applet;
    }
    
    public static void main(final String[] s) {
        final CapturePlayback capturePlayback = new CapturePlayback();
        capturePlayback.open();
        final JFrame f = new JFrame("Tunometer");
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent e) {
                System.exit(0);
            }
        });
        f.getContentPane().add("Center", capturePlayback);
        f.pack();
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final int w = 720;
        final int h = 340;
        f.setLocation(screenSize.width / 2 - w / 2, screenSize.height / 2 - h / 2);
        f.setSize(w, h);
        f.show();
    }
    
    public class Playback implements Runnable
    {
        SourceDataLine line;
        Thread thread;
        
        public void start() {
            CapturePlayback.this.errStr = null;
            (this.thread = new Thread(this)).setName("Playback");
            this.thread.start();
        }
        
        public void stop() {
            this.thread = null;
        }
        
        private void shutDown(final String message) {
            CapturePlayback.this.errStr = message;
            if (message != null) {
                System.err.println(CapturePlayback.this.errStr);
                CapturePlayback.this.samplingGraph.repaint();
            }
            if (this.thread != null) {
                this.thread = null;
                CapturePlayback.this.samplingGraph.stop();
                CapturePlayback.this.captB.setEnabled(true);
                CapturePlayback.this.pausB.setEnabled(false);
                CapturePlayback.this.playB.setText("Play");
            }
        }
        
        public void run() {
            if (CapturePlayback.this.file != null) {
                CapturePlayback.this.createAudioInputStream(CapturePlayback.this.file, false);
            }
            if (CapturePlayback.this.audioInputStream == null) {
                this.shutDown("No loaded audio to play back");
                return;
            }
            try {
                CapturePlayback.this.audioInputStream.reset();
            }
            catch (Exception e) {
                e.printStackTrace();
                this.shutDown("Unable to reset the stream\n" + e);
                return;
            }
            final AudioInputStream playbackInputStream = AudioSystem.getAudioInputStream(CapturePlayback.this.format, CapturePlayback.this.audioInputStream);
            if (playbackInputStream == null) {
                this.shutDown("Unable to convert stream of format " + CapturePlayback.this.audioInputStream + " to format " + CapturePlayback.this.format);
                return;
            }
            final DataLine.Info info = new DataLine.Info(SourceDataLine.class, CapturePlayback.this.format);
            if (!AudioSystem.isLineSupported(info)) {
                this.shutDown("Line matching " + info + " not supported.");
                return;
            }
            try {
                (this.line = (SourceDataLine)AudioSystem.getLine(info)).open(CapturePlayback.this.format, 16384);
            }
            catch (LineUnavailableException ex) {
                this.shutDown("Unable to open the line: " + ex);
                return;
            }
            final int frameSizeInBytes = CapturePlayback.this.format.getFrameSize();
            final int bufferLengthInFrames = this.line.getBufferSize() / 8;
            final int bufferLengthInBytes = bufferLengthInFrames * frameSizeInBytes;
            final byte[] data = new byte[bufferLengthInBytes];
            int numBytesRead = 0;
            this.line.start();
            while (this.thread != null) {
                try {
                    if ((numBytesRead = playbackInputStream.read(data)) != -1) {
                        for (int numBytesRemaining = numBytesRead; numBytesRemaining > 0; numBytesRemaining -= this.line.write(data, 0, numBytesRemaining)) {}
                        continue;
                    }
                }
                catch (Exception e2) {
                    this.shutDown("Error during playback: " + e2);
                }
                break;
            }
            if (this.thread != null) {
                this.line.drain();
            }
            this.line.stop();
            this.line.close();
            this.line = null;
            this.shutDown(null);
        }
    }
    
    class Capture implements Runnable
    {
        TargetDataLine line;
        Thread thread;
        
        public void start() {
            CapturePlayback.this.errStr = null;
            (this.thread = new Thread(this)).setName("Capture");
            this.thread.start();
        }
        
        public void stop() {
            this.thread = null;
        }
        
        private void shutDown(final String message) {
            CapturePlayback.this.errStr = message;
            if (message != null && this.thread != null) {
                this.thread = null;
                CapturePlayback.this.samplingGraph.stop();
                CapturePlayback.this.playB.setEnabled(true);
                CapturePlayback.this.pausB.setEnabled(false);
                CapturePlayback.this.waveB.setEnabled(true);
                CapturePlayback.this.captB.setText("Record");
                System.err.println(CapturePlayback.this.errStr);
                CapturePlayback.this.samplingGraph.repaint();
            }
        }
        
        public void run() {
            CapturePlayback.this.duration = 0.0;
            CapturePlayback.this.audioInputStream = null;
            final DataLine.Info info = new DataLine.Info(TargetDataLine.class, CapturePlayback.this.format);
            if (!AudioSystem.isLineSupported(info)) {
                this.shutDown("Line matching " + info + " not supported.");
                return;
            }
            try {
                (this.line = (TargetDataLine)AudioSystem.getLine(info)).open(CapturePlayback.this.format, this.line.getBufferSize());
            }
            catch (LineUnavailableException ex) {
                this.shutDown("Unable to open the line: " + ex);
                return;
            }
            catch (SecurityException ex2) {
                this.shutDown(ex2.toString());
                CapturePlayback.showInfoDialog();
                return;
            }
            catch (Exception ex3) {
                this.shutDown(ex3.toString());
                return;
            }
            final ByteArrayOutputStream out = new ByteArrayOutputStream();
            final int frameSizeInBytes = CapturePlayback.this.format.getFrameSize();
            final int bufferLengthInFrames = this.line.getBufferSize() / 8;
            final int bufferLengthInBytes = bufferLengthInFrames * frameSizeInBytes;
            final byte[] data = new byte[bufferLengthInBytes];
            this.line.start();
            int numBytesRead;
            while (this.thread != null && (numBytesRead = this.line.read(data, 0, bufferLengthInBytes)) != -1) {
                out.write(data, 0, numBytesRead);
            }
            this.line.stop();
            this.line.close();
            this.line = null;
            try {
                out.flush();
                out.close();
            }
            catch (IOException ex4) {
                ex4.printStackTrace();
            }
            final byte[] audioBytes = out.toByteArray();
            final ByteArrayInputStream bais = new ByteArrayInputStream(audioBytes);
            CapturePlayback.this.audioInputStream = new AudioInputStream(bais, CapturePlayback.this.format, audioBytes.length / frameSizeInBytes);
            final long milliseconds = (long)(CapturePlayback.this.audioInputStream.getFrameLength() * 1000L / CapturePlayback.this.format.getFrameRate());
            CapturePlayback.this.duration = milliseconds / 1000.0;
            try {
                CapturePlayback.this.audioInputStream.reset();
            }
            catch (Exception ex5) {
                ex5.printStackTrace();
                return;
            }
            CapturePlayback.this.samplingGraph.createWaveForm(audioBytes);
        }
    }
    
    class SamplingGraph extends JPanel implements Runnable
    {
        private Thread thread;
        private Font font10;
        private Font font12;
        Color jfcBlue;
        Color pink;
        
        public SamplingGraph() {
            this.font10 = new Font("serif", 0, 10);
            this.font12 = new Font("serif", 0, 12);
            this.jfcBlue = new Color(204, 204, 255);
            this.pink = new Color(255, 175, 175);
            this.setBackground(new Color(20, 20, 20));
        }
        
        public void createWaveForm(byte[] audioBytes) {
            CapturePlayback.this.lines.removeAllElements();
            final AudioFormat format = CapturePlayback.this.audioInputStream.getFormat();
            if (audioBytes == null) {
                try {
                    audioBytes = new byte[(int)(CapturePlayback.this.audioInputStream.getFrameLength() * format.getFrameSize())];
                    CapturePlayback.this.audioInputStream.read(audioBytes);
                }
                catch (Exception ex) {
                    CapturePlayback.this.reportStatus(ex.toString());
                    return;
                }
            }
            final Dimension d = this.getSize();
            final int w = d.width;
            final int h = d.height - 15;
            int[] audioData = null;
            if (format.getSampleSizeInBits() == 16) {
                final int nlengthInSamples = audioBytes.length / 2;
                audioData = new int[nlengthInSamples];
                if (format.isBigEndian()) {
                    for (int i = 0; i < nlengthInSamples; ++i) {
                        final int MSB = audioBytes[2 * i];
                        final int LSB = audioBytes[2 * i + 1];
                        audioData[i] = (MSB << 8 | (0xFF & LSB));
                    }
                }
                else {
                    for (int i = 0; i < nlengthInSamples; ++i) {
                        final int LSB2 = audioBytes[2 * i];
                        final int MSB2 = audioBytes[2 * i + 1];
                        audioData[i] = (MSB2 << 8 | (0xFF & LSB2));
                    }
                }
            }
            else if (format.getSampleSizeInBits() == 8) {
                final int nlengthInSamples = audioBytes.length;
                audioData = new int[nlengthInSamples];
                if (format.getEncoding().toString().startsWith("PCM_SIGN")) {
                    for (int i = 0; i < audioBytes.length; ++i) {
                        audioData[i] = audioBytes[i];
                    }
                }
                else {
                    for (int i = 0; i < audioBytes.length; ++i) {
                        audioData[i] = audioBytes[i] - 128;
                    }
                }
            }
            final int frames_per_pixel = audioBytes.length / format.getFrameSize() / w;
            byte my_byte = 0;
            double y_last = 0.0;
            final int numChannels = format.getChannels();
            for (double x = 0.0; x < w && audioData != null; ++x) {
                final int idx = (int)(frames_per_pixel * numChannels * x);
                if (format.getSampleSizeInBits() == 8) {
                    my_byte = (byte)audioData[idx];
                }
                else {
                    my_byte = (byte)(128 * audioData[idx] / 32768);
                }
                final double y_new = h * (128 - my_byte) / 256;
                CapturePlayback.this.lines.add(new Line2D.Double(x, y_last, x, y_new));
                y_last = y_new;
            }
            this.repaint();
        }
        
        public void paint(final Graphics g) {
            final Dimension d = this.getSize();
            final int w = d.width;
            final int h = d.height;
            final int INFOPAD = 15;
            final Graphics2D g2 = (Graphics2D)g;
            g2.setBackground(this.getBackground());
            g2.clearRect(0, 0, w, h);
            g2.setColor(Color.white);
            g2.fillRect(0, h - INFOPAD, w, INFOPAD);
            if (CapturePlayback.this.errStr != null) {
                g2.setColor(this.jfcBlue);
                g2.setFont(new Font("serif", 1, 18));
                g2.drawString("ERROR", 5, 20);
                final AttributedString as = new AttributedString(CapturePlayback.this.errStr);
                as.addAttribute(TextAttribute.FONT, this.font12, 0, CapturePlayback.this.errStr.length());
                final AttributedCharacterIterator aci = as.getIterator();
                final FontRenderContext frc = g2.getFontRenderContext();
                final LineBreakMeasurer lbm = new LineBreakMeasurer(aci, frc);
                float x = 5.0f;
                float y = 25.0f;
                lbm.setPosition(0);
                while (lbm.getPosition() < CapturePlayback.this.errStr.length()) {
                    final TextLayout tl = lbm.nextLayout(w - x - 5.0f);
                    if (!tl.isLeftToRight()) {
                        x = w - tl.getAdvance();
                    }
                    tl.draw(g2, x, y += tl.getAscent());
                    y += tl.getDescent() + tl.getLeading();
                }
            }
            else if (CapturePlayback.this.capture.thread != null) {
                g2.setColor(Color.black);
                g2.setFont(this.font12);
                g2.drawString("Length: " + String.valueOf(CapturePlayback.this.seconds), 3, h - 4);
            }
            else {
                g2.setColor(Color.black);
                g2.setFont(this.font12);
                g2.drawString("File: " + CapturePlayback.this.fileName + "  Length: " + String.valueOf(CapturePlayback.this.duration) + "  Position: " + String.valueOf(CapturePlayback.this.seconds), 3, h - 4);
                if (CapturePlayback.this.audioInputStream != null) {
                    g2.setColor(this.jfcBlue);
                    for (int i = 1; i < CapturePlayback.this.lines.size(); ++i) {
                        g2.draw(CapturePlayback.this.lines.get(i));
                    }
                    if (CapturePlayback.this.seconds != 0.0) {
                        final double loc = CapturePlayback.this.seconds / CapturePlayback.this.duration * w;
                        g2.setColor(this.pink);
                        g2.setStroke(new BasicStroke(3.0f));
                        g2.draw(new Line2D.Double(loc, 0.0, loc, h - INFOPAD - 2));
                    }
                }
            }
        }
        
        public void start() {
            (this.thread = new Thread(this)).setName("SamplingGraph");
            this.thread.start();
            CapturePlayback.this.seconds = 0.0;
        }
        
        public void stop() {
            if (this.thread != null) {
                this.thread.interrupt();
            }
            this.thread = null;
        }
        
        public void run() {
            CapturePlayback.this.seconds = 0.0;
        Label_0248:
            while (this.thread != null) {
                if (CapturePlayback.this.playback.line != null && CapturePlayback.this.playback.line.isOpen()) {
                    final long milliseconds = CapturePlayback.this.playback.line.getMicrosecondPosition() / 1000L;
                    CapturePlayback.this.seconds = milliseconds / 1000.0;
                }
                else if (CapturePlayback.this.capture.line != null && CapturePlayback.this.capture.line.isActive()) {
                    final long milliseconds = CapturePlayback.this.capture.line.getMicrosecondPosition() / 1000L;
                    CapturePlayback.this.seconds = milliseconds / 1000.0;
                }
                try {
                    final Thread thread = this.thread;
                    Thread.sleep(100L);
                }
                catch (Exception e) {
                    break;
                }
                this.repaint();
                while (true) {
                    if (CapturePlayback.this.capture.line == null || CapturePlayback.this.capture.line.isActive()) {
                        if (CapturePlayback.this.playback.line == null || CapturePlayback.this.playback.line.isOpen()) {
                            break;
                        }
                    }
                    try {
                        final Thread thread2 = this.thread;
                        Thread.sleep(10L);
                        continue;
                    }
                    catch (Exception e) {
                        break;
                    }
                    break Label_0248;
                }
            }
            CapturePlayback.this.seconds = 0.0;
            this.repaint();
        }
    }
}
