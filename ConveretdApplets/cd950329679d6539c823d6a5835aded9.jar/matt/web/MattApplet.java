// 
// Decompiled by Procyon v0.5.30
// 

package matt.web;

import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Line;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;
import abc.notation.Tune;
import abc.parser.TuneBook;
import matt.STFTTranscriber;
import java.util.TimerTask;
import java.net.MalformedURLException;
import matt.Logger;
import java.net.URL;
import java.net.URLEncoder;
import matt.MattABCTools;
import javax.swing.LayoutStyle;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.GroupLayout;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.EventQueue;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager;
import abc.midi.TunePlayerListenerInterface;
import abc.midi.PlayerStateChangeEvent;
import abc.midi.TunePlayerAdapter;
import matt.MattProperties;
import java.awt.Component;
import java.awt.Color;
import matt.Graph;
import javax.swing.JTextArea;
import javax.swing.JSlider;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.util.Timer;
import abc.midi.TunePlayer;
import matt.ODCFTranscriber;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import matt.GUI;
import javax.swing.JApplet;

public class MattApplet extends JApplet implements GUI
{
    public static MattApplet _instance;
    Capture capture;
    Playback playback;
    AudioInputStream audioInputStream;
    String errStr;
    AudioFormat format;
    final int bufSize = 16384;
    double duration;
    double seconds;
    ODCFTranscriber transcriber;
    float[] signal;
    private TunePlayer tunePlayer;
    private int sampleRate;
    private int numSamples;
    byte[] audioData;
    public boolean countdown;
    public boolean recording;
    public Timer cdtimer;
    public CheckList theCorpusList;
    public String corpString;
    public String typeString;
    private JButton TuneBookBtn;
    private JButton btnFind;
    private JButton btnFind1;
    private JButton btnPlay;
    private JButton btnRecord;
    private JButton btnTranscribe;
    private JComboBox cmbFundamental;
    private JComboBox cmbTranscriber;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JScrollPane jScrollPane2;
    private JButton playABCbtn;
    private JProgressBar progressBar;
    private JSlider slSilence;
    private JTextArea txtABC;
    private JLabel txtStatus;
    private Graph signalGraph;
    
    public MattApplet() {
        this.capture = new Capture();
        this.playback = new Playback();
        this.format = null;
        this.transcriber = new ODCFTranscriber();
        this.tunePlayer = new TunePlayer();
        this.countdown = false;
        this.recording = false;
        this.theCorpusList = new CheckList(new String[] { "All", "thesession.org", "Norbeck", "O'Neill's 1001", "Ceol Rince na h\u00c9ireann 1", "Ceol Rince na h\u00c9ireann 2", "Ceol Rince na h\u00c9ireann 3", "Ceol Rince na h\u00c9ireann 4", "Johnny O'Leary", "Nigel Gatherer", "The Microphone Rambles", "John Tose", "Jack Campin", "Fife and Drum", "Nottingham Database", "Aird's Airs" });
        this.corpString = "";
        this.typeString = "";
        MattApplet._instance = this;
    }
    
    private void myInit() {
        this.signalGraph.setBounds(10, 10, this.getBounds().width - 20, 80);
        this.setBackground(new Color(249, 249, 249));
        this.getContentPane().add(this.signalGraph);
        this.signalGraph.setBackground(new Color(176, 210, 13));
        this.format = new AudioFormat(44100.0f, 16, 1, true, false);
        this.transcriber.setGui(this);
        this.theCorpusList.setGui(this);
        MattProperties.instance(false).setProperty("drawFFTGraphs", "false");
        MattProperties.instance(false).setProperty("drawODFGraphs", "false");
        MattProperties.instance(false).setProperty("tansey", "false");
        MattProperties.instance(false).setProperty("applet", "true");
        MattApplet._instance = this;
        this.tunePlayer.addListener(new TunePlayerAdapter() {
            public void playEnd(final PlayerStateChangeEvent e) {
            }
        });
        final JComboBox cmbFundamental = this.cmbFundamental;
        MattProperties.instance();
        cmbFundamental.setSelectedItem(MattProperties.getString("fundamentalNote"));
    }
    
    public void init() {
        MattProperties.instance(true);
        this.setBackground(new Color(249, 249, 249));
        this.signalGraph = new Graph();
        this.tunePlayer.start();
        try {
            EventQueue.invokeAndWait(new Runnable() {
                public void run() {
                    try {
                        for (final UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                            if ("Nimbus".equals(info.getName())) {
                                UIManager.setLookAndFeel(info.getClassName());
                                break;
                            }
                        }
                    }
                    catch (UnsupportedLookAndFeelException e) {}
                    catch (ClassNotFoundException e2) {}
                    catch (InstantiationException e3) {}
                    catch (IllegalAccessException ex) {}
                    MattApplet.this.initComponents();
                    MattApplet.this.myInit();
                }
            });
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void windowClosing(final WindowEvent e) {
    }
    
    private void initComponents() {
        this.btnFind = new JButton();
        this.jPanel1 = new JPanel();
        this.btnPlay = new JButton();
        this.btnRecord = new JButton();
        this.jLabel6 = new JLabel();
        this.slSilence = new JSlider();
        this.jLabel1 = new JLabel();
        this.cmbFundamental = new JComboBox();
        this.btnTranscribe = new JButton();
        this.txtStatus = new JLabel();
        this.TuneBookBtn = new JButton();
        this.playABCbtn = new JButton();
        this.progressBar = new JProgressBar();
        this.jPanel2 = new JPanel();
        this.jLabel2 = new JLabel();
        this.jScrollPane2 = new JScrollPane();
        this.txtABC = new JTextArea();
        this.btnFind1 = new JButton();
        this.jLabel5 = new JLabel();
        this.cmbTranscriber = new JComboBox();
        this.btnFind.setText("Search!");
        this.btnFind.setMaximumSize(new Dimension(32767, 32767));
        this.btnFind.setMinimumSize(new Dimension(73, 18));
        this.btnFind.setPreferredSize(new Dimension(78, 20));
        this.btnFind.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                MattApplet.this.btnFindActionPerformed(evt);
            }
        });
        this.setBackground(new Color(249, 249, 249));
        this.jPanel1.setFocusable(false);
        this.jPanel1.setOpaque(false);
        this.btnPlay.setText("Playback");
        this.btnPlay.setEnabled(false);
        this.btnPlay.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                MattApplet.this.btnPlayActionPerformed(evt);
            }
        });
        this.btnRecord.setText("Record");
        this.btnRecord.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                MattApplet.this.btnRecordActionPerformed(evt);
            }
        });
        this.jLabel6.setText("Silence threshold:");
        this.slSilence.setMaximum(6000);
        this.slSilence.setValue(1500);
        this.jLabel1.setText("Fundamental:");
        this.cmbFundamental.setModel(new DefaultComboBoxModel<String>(new String[] { "Bb", "C", "D", "Eb", "F", "G" }));
        this.cmbFundamental.setName("cmbFundamental");
        this.cmbFundamental.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent evt) {
                MattApplet.this.cmbFundamentalItemStateChanged(evt);
            }
        });
        this.cmbFundamental.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                MattApplet.this.cmbFundamentalActionPerformed(evt);
            }
        });
        this.btnTranscribe.setText("Transcribe");
        this.btnTranscribe.setEnabled(false);
        this.btnTranscribe.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                MattApplet.this.btnTranscribeActionPerformed(evt);
            }
        });
        this.txtStatus.setText("<Press record to begin!>");
        this.txtStatus.setMaximumSize(new Dimension(68, 14));
        this.txtStatus.setMinimumSize(new Dimension(68, 14));
        this.txtStatus.setPreferredSize(new Dimension(68, 20));
        this.TuneBookBtn.setText("Tune Books: ...");
        this.TuneBookBtn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent evt) {
                MattApplet.this.TuneBookBtnMouseClicked(evt);
            }
        });
        this.playABCbtn.setText("Play ABC");
        this.playABCbtn.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                MattApplet.this.playABCbtnActionPerformed(evt);
            }
        });
        final GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
        this.jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.txtStatus, -1, 198, 32767).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.jLabel6).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.slSilence, -1, 98, 32767)).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(this.btnTranscribe, GroupLayout.Alignment.LEADING, -1, 85, 32767).addComponent(this.btnRecord, GroupLayout.Alignment.LEADING, -1, 85, 32767).addComponent(this.jLabel1, GroupLayout.Alignment.LEADING, -1, 85, 32767)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.btnPlay, -1, 96, 32767).addComponent(this.cmbFundamental, 0, 96, 32767)).addComponent(this.playABCbtn, -2, 96, -2)))).addGap(10, 10, 10)).addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup().addComponent(this.TuneBookBtn, -1, 198, 32767).addContainerGap()).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.progressBar, -1, 198, 32767).addContainerGap()))));
        jPanel1Layout.linkSize(0, this.btnPlay, this.btnRecord, this.btnTranscribe, this.jLabel1, this.jLabel6, this.playABCbtn);
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(this.txtStatus, -2, 13, -2).addGap(12, 12, 12).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(this.jLabel6).addComponent(this.slSilence, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel1).addComponent(this.cmbFundamental, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGap(29, 29, 29).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.btnTranscribe).addComponent(this.playABCbtn))).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.btnRecord).addComponent(this.btnPlay))).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.TuneBookBtn).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.progressBar, -2, -1, -2).addGap(14, 14, 14)));
        jPanel1Layout.linkSize(1, this.btnPlay, this.btnRecord, this.btnTranscribe, this.cmbFundamental, this.playABCbtn);
        this.jPanel2.setFocusable(false);
        this.jPanel2.setOpaque(false);
        this.jLabel2.setText("ABC to search for:");
        this.txtABC.setColumns(20);
        this.txtABC.setLineWrap(true);
        this.txtABC.setRows(5);
        this.jScrollPane2.setViewportView(this.txtABC);
        this.btnFind1.setText("Search!");
        this.btnFind1.setMaximumSize(new Dimension(32767, 32767));
        this.btnFind1.setMinimumSize(new Dimension(73, 18));
        this.btnFind1.setPreferredSize(new Dimension(78, 20));
        this.btnFind1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                MattApplet.this.btnFind1ActionPerformed(evt);
            }
        });
        final GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
        this.jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jScrollPane2, -1, 125, 32767).addComponent(this.jLabel2).addComponent(this.btnFind1, GroupLayout.Alignment.TRAILING, -1, 125, 32767)).addContainerGap()));
        jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addComponent(this.jLabel2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jScrollPane2, -2, 116, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.btnFind1, -1, 50, 32767).addContainerGap()));
        this.cmbTranscriber.setModel(new DefaultComboBoxModel<String>(new String[] { "Method 1", "Method 2" }));
        this.cmbTranscriber.setOpaque(false);
        this.cmbTranscriber.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent evt) {
                MattApplet.this.cmbTranscriberItemStateChanged(evt);
            }
        });
        this.cmbTranscriber.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                MattApplet.this.cmbTranscriberActionPerformed(evt);
            }
        });
        final GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel1, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jPanel2, -2, -1, -2).addContainerGap(1206, 32767)).addGroup(layout.createSequentialGroup().addGap(86, 86, 86).addComponent(this.jLabel5).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.cmbTranscriber, 0, 87, 32767).addGap(1408, 1408, 1408)));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(56, 56, 56).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.cmbTranscriber, -2, -1, -2).addComponent(this.jLabel5)).addGap(18, 18, 18).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.jPanel2, -1, -1, 32767).addComponent(this.jPanel1, -1, -1, 32767)).addContainerGap(141, 32767)));
    }
    
    private void btnFindActionPerformed(final ActionEvent evt) {
        String toFind = this.txtABC.getText();
        toFind = MattABCTools.expandLongNotes(toFind);
        toFind = MattABCTools.stripWhiteSpace(toFind);
        toFind = MattABCTools.stripBarDivisions(toFind);
        toFind = toFind.toUpperCase();
        final String docBase = "" + this.getDocumentBase();
        final int li = docBase.lastIndexOf("/");
        String url = docBase.substring(0, li) + "/search8.jsp?version=1.4&q=" + URLEncoder.encode(toFind);
        System.out.println(url);
        url = url + "&silence=" + this.slSilence.getValue();
        url = url + "&method=" + this.cmbTranscriber.getSelectedItem();
        System.out.println("URL: " + url);
        try {
            this.getAppletContext().showDocument(new URL(url), "results");
        }
        catch (MalformedURLException ex) {
            Logger.log(ex.toString());
        }
    }
    
    private void btnRecordActionPerformed(final ActionEvent evt) {
        if (this.btnRecord.getText().equals("Record")) {
            this.cdtimer = new Timer();
            this.countdown = true;
            this.btnRecord.setText("Stop");
            this.cdtimer.schedule(new RemindTask(), 0L, 1000L);
        }
        else if (this.countdown || this.recording) {
            this.txtStatus.setText("<Press record to begin!>");
            this.cdtimer.cancel();
            this.cdtimer.purge();
            this.cdtimer = null;
            this.countdown = false;
            this.recording = false;
            this.getProgressBar().setValue(0);
            this.btnRecord.setText("Record");
        }
        else {
            this.countdown = false;
            this.recording = false;
            this.cdtimer.cancel();
            this.cdtimer.purge();
            this.cdtimer = null;
            this.getProgressBar().setValue(0);
            this.txtStatus.setText("<Press record to begin!>");
            this.cdtimer.cancel();
            this.record();
        }
    }
    
    private void record() {
        if (this.btnRecord.getText().equals("Record")) {
            this.signalGraph.getDefaultSeries().clearLines();
            this.signalGraph.getDefaultSeries().clear();
            this.txtABC.setText("");
            this.capture.start();
            this.btnRecord.setText("Stop");
        }
        else {
            this.capture.stop();
            this.btnRecord.setText("Record");
            try {
                synchronized (this.capture) {
                    this.capture.wait();
                }
                final AudioFormat format = this.audioInputStream.getFormat();
                this.numSamples = (int)this.audioInputStream.getFrameLength();
                this.audioData = new byte[this.numSamples * 2];
                this.signal = new float[this.numSamples];
                this.audioInputStream.read(this.audioData, 0, this.numSamples * 2);
                this.sampleRate = (int)format.getSampleRate();
                this.transcriber.setSampleRate(this.sampleRate);
                final boolean bigEndian = format.isBigEndian();
                this.getProgressBar().setValue(0);
                this.getProgressBar().setMaximum(this.numSamples);
                for (int signalIndex = 0; signalIndex < this.numSamples; ++signalIndex) {
                    this.signal[signalIndex] = (this.audioData[signalIndex * 2 + 1] << 8) + this.audioData[signalIndex * 2];
                    this.getProgressBar().setValue(signalIndex);
                }
                Logger.log("Removing silence at the start...");
                this.transcriber.setSignal(this.signal);
                this.transcriber.setSilenceThreshold(this.slSilence.getValue());
                this.transcriber.removeSilence();
                this.signal = this.transcriber.getSignal();
                Logger.log("Graphing...");
                if (Boolean.parseBoolean("" + MattProperties.getString("drawSignalGraphs"))) {
                    this.signalGraph.getDefaultSeries().setData(this.signal);
                    this.signalGraph.getDefaultSeries().setGraphType(0);
                    this.signalGraph.repaint();
                }
                this.getProgressBar().setValue(0);
                Logger.log("Done.");
                this.btnPlay.setEnabled(true);
                this.btnTranscribe.setEnabled(true);
            }
            catch (Exception e) {
                e.printStackTrace();
                Logger.log("Could not plot audio: " + e.getMessage());
                Logger.log("Could not hear the melody.");
                this.getProgressBar().setValue(0);
            }
        }
    }
    
    private void btnPlayActionPerformed(final ActionEvent evt) {
        if (this.btnPlay.getText().equals("Playback")) {
            this.playback.start();
            this.btnPlay.setText("Stop");
        }
        else {
            this.playback.stop();
            this.btnPlay.setText("Playback");
        }
    }
    
    private void btnTranscribeActionPerformed(final ActionEvent evt) {
        try {
            this.txtABC.setText("");
            this.transcriber.setInputFile("");
            MattProperties.setString("fundamentalNote", "" + this.cmbFundamental.getSelectedItem());
            this.transcriber.transcribea();
            this.playABCbtn.setEnabled(true);
        }
        catch (Exception ex) {
            Logger.log(ex.toString());
            ex.printStackTrace();
        }
    }
    
    private void cmbFundamentalActionPerformed(final ActionEvent evt) {
    }
    
    private void cmbFundamentalItemStateChanged(final ItemEvent evt) {
        MattProperties.setString("fundamentalNote", "" + this.cmbFundamental.getSelectedItem());
    }
    
    private void cmbTranscriberActionPerformed(final ActionEvent evt) {
    }
    
    private void cmbTranscriberItemStateChanged(final ItemEvent evt) {
        if (this.cmbTranscriber.getSelectedItem().equals("Method 1")) {
            this.transcriber = new ODCFTranscriber();
        }
        else {
            this.transcriber = new STFTTranscriber();
        }
        this.transcriber.setGui(this);
        this.transcriber.setSampleRate(this.sampleRate);
        if (this.signal != null) {
            this.transcriber.setSignal(this.signal);
        }
    }
    
    private void btnFind1ActionPerformed(final ActionEvent evt) {
        String toFind = this.txtABC.getText();
        toFind = MattABCTools.expandLongNotes(toFind);
        toFind = MattABCTools.stripWhiteSpace(toFind);
        toFind = MattABCTools.stripBarDivisions(toFind);
        toFind = toFind.toUpperCase();
        final String docBase = "" + this.getDocumentBase();
        final int li = docBase.lastIndexOf("/");
        String url = docBase.substring(0, li) + "/search8.jsp?version=1.4&q=" + URLEncoder.encode(toFind);
        url += "&sources=";
        url += this.theCorpusList.getVals();
        url = url + "&silence=" + this.slSilence.getValue();
        url = url + "&method=" + this.cmbTranscriber.getSelectedItem();
        System.out.println("URL: " + url);
        try {
            this.getAppletContext().showDocument(new URL(url), "results");
        }
        catch (MalformedURLException ex) {
            Logger.log(ex.toString());
        }
    }
    
    private void TuneBookBtnMouseClicked(final MouseEvent evt) {
        this.theCorpusList.setVisible(true);
        this.theCorpusList.setLocationRelativeTo(null);
    }
    
    private void playABCbtnActionPerformed(final ActionEvent evt) {
        if (this.tunePlayer.isPlaying()) {
            this.tunePlayer.stopPlaying();
            return;
        }
        try {
            final StringBuffer tuneText = new StringBuffer();
            tuneText.append("X:1\r\n");
            tuneText.append("T:Temp\r\n");
            tuneText.append("R:Reel\r\n");
            tuneText.append("M:C|\r\n");
            tuneText.append("L:1/8\r\n");
            tuneText.append("K:D\r\n");
            tuneText.append(this.getTxtABC().getText());
            final TuneBook book = new TuneBook();
            book.putTune(tuneText.toString());
            final Tune aTune = book.getTune(1);
            this.tunePlayer.play(aTune);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void clearGraphs() {
        this.signalGraph.clear();
    }
    
    public Graph getSignalGraph() {
        return this.signalGraph;
    }
    
    public Graph getOdfGraph() {
        return null;
    }
    
    public JProgressBar getProgressBar() {
        return this.progressBar;
    }
    
    public void setTitle(final String t) {
    }
    
    public void enableButtons(final boolean b) {
        this.btnRecord.setEnabled(b);
        this.playABCbtn.setEnabled(b);
        this.btnFind.setEnabled(b);
        this.btnPlay.setEnabled(b);
        this.btnTranscribe.setEnabled(b);
        this.cmbFundamental.setEnabled(b);
        this.txtABC.setEnabled(b);
        this.slSilence.setEnabled(b);
        this.cmbTranscriber.setEnabled(b);
    }
    
    public void clearFFTGraphs() {
    }
    
    public Graph getFrameGraph() {
        return null;
    }
    
    public JTextArea getTxtABC() {
        return this.txtABC;
    }
    
    public static void setStatus(final String msg) {
        MattApplet._instance.txtStatus.setText(msg);
    }
    
    public void setBns() {
        if (this.theCorpusList.getWhat().length() > 15) {
            this.TuneBookBtn.setText("Tune Books: " + this.theCorpusList.getWhat().substring(0, 5) + "..." + this.theCorpusList.getWhat().substring(this.theCorpusList.getWhat().length() - 5));
        }
        else {
            this.TuneBookBtn.setText("Tune Books: " + this.theCorpusList.getWhat());
        }
    }
    
    class RemindTask extends TimerTask
    {
        int secleft;
        int countup;
        
        RemindTask() {
            this.secleft = 15;
            this.countup = 1;
        }
        
        public void run() {
            if (this.secleft > 12) {
                MattApplet.this.txtStatus.setText("Recording in " + (this.secleft - 12) + "...");
                MattApplet.this.btnRecord.setText("Stop");
                --this.secleft;
            }
            else if (this.secleft == 12) {
                MattApplet.this.btnRecord.setText("Recording in 0...");
                MattApplet.this.getProgressBar().setValue(0);
                MattApplet.this.getProgressBar().setMaximum(12);
                MattApplet.this.countdown = false;
                MattApplet.this.recording = true;
                --this.secleft;
                ++this.countup;
                MattApplet.this.btnRecord.setText("Record");
                MattApplet.this.record();
            }
            else if (this.secleft > 0) {
                MattApplet.this.txtStatus.setText("Recording!");
                MattApplet.this.getProgressBar().setValue(this.countup);
                --this.secleft;
                ++this.countup;
            }
            else {
                MattApplet.this.countdown = false;
                MattApplet.this.recording = false;
                MattApplet.this.record();
                this.cancel();
            }
        }
    }
    
    public class Playback implements Runnable
    {
        SourceDataLine line;
        Thread thread;
        
        public void start() {
            (this.thread = new Thread(this)).setName("Playback");
            this.thread.start();
        }
        
        public void stop() {
            this.thread = null;
        }
        
        private void shutDown(final String message) {
            MattApplet.this.errStr = message;
            if (message != null) {
                System.err.println(MattApplet.this.errStr);
                MattApplet.this.signalGraph.repaint();
            }
            if (this.thread != null) {
                this.thread = null;
                MattApplet.this.btnPlay.setText("Playback");
            }
        }
        
        public void run() {
            if (MattApplet.this.audioInputStream == null) {
                this.shutDown("No loaded audio to play back");
                return;
            }
            try {
                MattApplet.this.audioInputStream.reset();
            }
            catch (Exception e) {
                e.printStackTrace();
                this.shutDown("Unable to reset the stream\n" + e);
                return;
            }
            final AudioInputStream playbackInputStream = AudioSystem.getAudioInputStream(MattApplet.this.format, MattApplet.this.audioInputStream);
            if (playbackInputStream == null) {
                this.shutDown("Unable to convert stream of format " + MattApplet.this.audioInputStream + " to format " + MattApplet.this.format);
                return;
            }
            final DataLine.Info info = new DataLine.Info(SourceDataLine.class, MattApplet.this.format);
            if (!AudioSystem.isLineSupported(info)) {
                this.shutDown("Line matching " + info + " not supported.");
                return;
            }
            try {
                (this.line = (SourceDataLine)AudioSystem.getLine(info)).open(MattApplet.this.format, 16384);
            }
            catch (LineUnavailableException ex) {
                this.shutDown("Unable to open the line: " + ex);
                return;
            }
            final int frameSizeInBytes = MattApplet.this.format.getFrameSize();
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
            MattApplet.this.errStr = null;
            (this.thread = new Thread(this)).setName("Capture");
            this.thread.start();
        }
        
        public void stop() {
            this.thread = null;
        }
        
        private void shutDown(final String message) {
            System.out.println(message);
            MattApplet.this.errStr = message;
            if (message != null && this.thread != null) {
                this.thread = null;
            }
        }
        
        public void run() {
            MattApplet.this.duration = 0.0;
            MattApplet.this.audioInputStream = null;
            System.out.println("0");
            final DataLine.Info info = new DataLine.Info(TargetDataLine.class, MattApplet.this.format);
            System.out.println("1");
            if (!AudioSystem.isLineSupported(info)) {
                this.shutDown("Line matching " + info + " not supported.");
                return;
            }
            System.out.println("2");
            try {
                (this.line = (TargetDataLine)AudioSystem.getLine(info)).open(MattApplet.this.format, this.line.getBufferSize());
            }
            catch (LineUnavailableException ex) {
                this.shutDown("Unable to open the line: " + ex);
                return;
            }
            catch (SecurityException ex2) {
                this.shutDown(ex2.toString());
                return;
            }
            catch (Exception ex3) {
                this.shutDown(ex3.toString());
                return;
            }
            System.out.println("3");
            final ByteArrayOutputStream out = new ByteArrayOutputStream();
            final int frameSizeInBytes = MattApplet.this.format.getFrameSize();
            final int bufferLengthInFrames = this.line.getBufferSize() / 8;
            final int bufferLengthInBytes = bufferLengthInFrames * frameSizeInBytes;
            final byte[] data = new byte[bufferLengthInBytes];
            this.line.start();
            System.out.println("4");
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
            MattApplet.this.audioInputStream = new AudioInputStream(bais, MattApplet.this.format, audioBytes.length / frameSizeInBytes);
            final long milliseconds = (long)(MattApplet.this.audioInputStream.getFrameLength() * 1000L / MattApplet.this.format.getFrameRate());
            MattApplet.this.duration = milliseconds / 1000.0;
            try {
                MattApplet.this.audioInputStream.reset();
            }
            catch (Exception ex5) {
                ex5.printStackTrace();
                return;
            }
            synchronized (this) {
                this.notify();
            }
        }
    }
}
