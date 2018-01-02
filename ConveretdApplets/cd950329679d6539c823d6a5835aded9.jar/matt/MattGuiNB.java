// 
// Decompiled by Procyon v0.5.30
// 

package matt;

import java.util.Hashtable;
import java.awt.Point;
import java.awt.GraphicsEnvironment;
import javax.swing.DefaultComboBoxModel;
import java.awt.EventQueue;
import abc.notation.Tune;
import abc.parser.TuneBook;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.File;
import javax.swing.filechooser.FileFilter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.awt.LayoutManager;
import org.jdesktop.layout.GroupLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import abc.midi.TunePlayer;
import java.util.Vector;
import javax.swing.JFrame;

public class MattGuiNB extends JFrame implements GUI
{
    private ODCFTranscriber transcriber;
    private BatchJob batchJob;
    private Matt matt;
    private static MattGuiNB _instance;
    private Graph frameGraph;
    private Graph fftGraph;
    private Graph signalGraph;
    private Graph odfGraph;
    private Vector tuneMatches;
    private Vector<Graph> fftGraphs;
    private TunePlayer tunePlayer;
    ABCFinder finder;
    ABCMatch best;
    AudioCapture audioCapture;
    private JButton btnAbout;
    private JButton btnAnalysed;
    private JButton btnBatch;
    private JButton btnBest;
    private JButton btnChooseFile;
    private JButton btnClearLog;
    private JButton btnFind;
    private JButton btnLiveQuery;
    private JButton btnPlayFound;
    private JButton btnPlayOriginal;
    private JButton btnPlayTranscription;
    private JButton btnQuit;
    private JButton btnReindex;
    private JButton btnTranscribe;
    private JComboBox cbSelectFFT;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel6;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JProgressBar progressBar;
    private JScrollPane spLog;
    private JTable tblMatches;
    private JTextArea txtABC;
    private JLabel txtBest;
    private JTextArea txtLog;
    
    private MattGuiNB() {
        this.transcriber = null;
        this.batchJob = null;
        this.frameGraph = new Graph();
        this.fftGraph = new Graph();
        this.signalGraph = new Graph();
        this.odfGraph = new Graph();
        this.tuneMatches = new Vector();
        this.fftGraphs = new Vector<Graph>();
        this.tunePlayer = new TunePlayer();
        this.finder = null;
        this.best = null;
        this.audioCapture = new AudioCapture();
        this.initComponents();
        this.setTitle("MATT2 - Machine Annotation of Traditional Tunes - by Bryan Duggan");
        this.frameGraph.setBounds(10, 10, 380, 120);
        this.fftGraph.setBounds(400, 10, 380, 120);
        this.signalGraph.setBounds(10, 140, 770, 120);
        this.odfGraph.setBounds(10, 270, 770, 120);
        this.getContentPane().add(this.frameGraph);
        this.getContentPane().add(this.fftGraph);
        this.getContentPane().add(this.signalGraph);
        this.getContentPane().add(this.odfGraph);
        this.frameGraph.setBackground(Color.CYAN);
        this.signalGraph.setBackground(Color.GREEN);
        this.odfGraph.setBackground(Color.YELLOW);
        this.fftGraph.setBackground(Color.WHITE);
        center(this, 800, 700);
        this.tunePlayer.start();
        MattProperties.instance();
    }
    
    private void initComponents() {
        this.spLog = new JScrollPane();
        this.txtLog = new JTextArea();
        this.btnPlayOriginal = new JButton();
        this.btnPlayTranscription = new JButton();
        this.btnChooseFile = new JButton();
        this.jLabel2 = new JLabel();
        this.btnClearLog = new JButton();
        this.jScrollPane1 = new JScrollPane();
        this.txtABC = new JTextArea();
        this.jLabel3 = new JLabel();
        this.btnTranscribe = new JButton();
        this.btnFind = new JButton();
        this.btnPlayFound = new JButton();
        this.jLabel4 = new JLabel();
        this.jScrollPane2 = new JScrollPane();
        this.tblMatches = new JTable();
        this.btnQuit = new JButton();
        this.btnAnalysed = new JButton();
        this.jLabel6 = new JLabel();
        this.txtBest = new JLabel();
        this.btnBest = new JButton();
        this.btnReindex = new JButton();
        this.btnBatch = new JButton();
        this.progressBar = new JProgressBar();
        this.btnLiveQuery = new JButton();
        this.cbSelectFFT = new JComboBox();
        this.jLabel1 = new JLabel();
        this.btnAbout = new JButton();
        this.setDefaultCloseOperation(3);
        this.setBounds(new Rectangle(0, 0, 1, 1));
        this.spLog.setAutoscrolls(true);
        this.txtLog.setColumns(20);
        this.txtLog.setLineWrap(true);
        this.txtLog.setRows(5);
        this.txtLog.setWrapStyleWord(true);
        this.spLog.setViewportView(this.txtLog);
        this.btnPlayOriginal.setText("Original");
        this.btnPlayOriginal.setName("btnPlayOriginal");
        this.btnPlayOriginal.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                MattGuiNB.this.btnPlayOriginalActionPerformed(evt);
            }
        });
        this.btnPlayTranscription.setText("Transcribed");
        this.btnPlayTranscription.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                MattGuiNB.this.btnPlayTranscriptionActionPerformed(evt);
            }
        });
        this.btnChooseFile.setText("Choose File");
        this.btnChooseFile.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                MattGuiNB.this.btnChooseFileActionPerformed(evt);
            }
        });
        this.jLabel2.setText("Log:");
        this.btnClearLog.setText("Clear Log");
        this.btnClearLog.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                MattGuiNB.this.btnClearLogActionPerformed(evt);
            }
        });
        this.txtABC.setColumns(20);
        this.txtABC.setLineWrap(true);
        this.txtABC.setRows(5);
        this.jScrollPane1.setViewportView(this.txtABC);
        this.jLabel3.setText("ABC:");
        this.btnTranscribe.setText("Transcribe");
        this.btnTranscribe.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                MattGuiNB.this.btnTranscribeActionPerformed(evt);
            }
        });
        this.btnFind.setText("Find");
        this.btnFind.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                MattGuiNB.this.btnFindActionPerformed(evt);
            }
        });
        this.btnPlayFound.setText("Found");
        this.btnPlayFound.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                MattGuiNB.this.btnPlayFoundActionPerformed(evt);
            }
        });
        this.jLabel4.setText("Matches:");
        this.jScrollPane2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent evt) {
                MattGuiNB.this.jScrollPane2MouseClicked(evt);
            }
        });
        this.tblMatches.setModel(new DefaultTableModel(new Object[0][], new String[] { "Title", "Matched", "ED" }) {
            boolean[] canEdit = { false, false, false };
            
            public boolean isCellEditable(final int rowIndex, final int columnIndex) {
                return this.canEdit[columnIndex];
            }
        });
        this.tblMatches.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent evt) {
                MattGuiNB.this.tblMatchesMouseClicked(evt);
            }
        });
        this.jScrollPane2.setViewportView(this.tblMatches);
        this.btnQuit.setText("Quit");
        this.btnQuit.setName("btnQuit");
        this.btnQuit.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                MattGuiNB.this.btnQuitActionPerformed(evt);
            }
        });
        this.btnAnalysed.setText("Analysed");
        this.btnAnalysed.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                MattGuiNB.this.btnAnalysedActionPerformed(evt);
            }
        });
        this.txtBest.setText("<Start searcing!>");
        this.btnBest.setText("Best");
        this.btnBest.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                MattGuiNB.this.btnBestActionPerformed(evt);
            }
        });
        this.btnReindex.setText("Reindex");
        this.btnReindex.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                MattGuiNB.this.btnReindexActionPerformed(evt);
            }
        });
        this.btnBatch.setText("Batch");
        this.btnBatch.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                MattGuiNB.this.btnBatchActionPerformed(evt);
            }
        });
        this.btnLiveQuery.setText("Live Query");
        this.btnLiveQuery.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                MattGuiNB.this.btnLiveQueryActionPerformed(evt);
            }
        });
        this.cbSelectFFT.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent evt) {
                MattGuiNB.this.cbSelectFFTItemStateChanged(evt);
            }
        });
        this.cbSelectFFT.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                MattGuiNB.this.cbSelectFFTActionPerformed(evt);
            }
        });
        this.jLabel1.setText("Graph:");
        this.btnAbout.setText("About");
        this.btnAbout.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                MattGuiNB.this.btnAboutActionPerformed(evt);
            }
        });
        final GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout((LayoutManager)layout);
        layout.setHorizontalGroup((GroupLayout.Group)layout.createParallelGroup(1).add(2, (GroupLayout.Group)layout.createSequentialGroup().addContainerGap().add((GroupLayout.Group)layout.createParallelGroup(1).add((GroupLayout.Group)layout.createSequentialGroup().add((GroupLayout.Group)layout.createParallelGroup(1).add((Component)this.jLabel2).add((Component)this.spLog, -1, 454, 32767).add(2, (GroupLayout.Group)layout.createSequentialGroup().add((Component)this.progressBar, -1, 371, 32767).addPreferredGap(0).add((Component)this.jLabel1).add(18, 18, 18).add((Component)this.cbSelectFFT, -2, -1, -2))).addPreferredGap(0).add((GroupLayout.Group)layout.createParallelGroup(1).add((Component)this.jScrollPane1, -2, 125, -2).add((Component)this.jLabel3)).addPreferredGap(0).add((GroupLayout.Group)layout.createParallelGroup(1).add((Component)this.jLabel4).add((Component)this.jScrollPane2, -2, 183, -2)).add(103, 103, 103)).add((GroupLayout.Group)layout.createSequentialGroup().add((GroupLayout.Group)layout.createParallelGroup(1).add((Component)this.btnLiveQuery, -1, 107, 32767).add(2, (Component)this.btnBatch, -1, 107, 32767)).addPreferredGap(0).add((GroupLayout.Group)layout.createParallelGroup(2).add((Component)this.btnChooseFile, -1, 96, 32767).add((Component)this.btnTranscribe, -1, 96, 32767)).addPreferredGap(0).add((GroupLayout.Group)layout.createParallelGroup(1, false).add((Component)this.btnQuit, -1, -1, 32767).add((Component)this.btnFind, -2, 78, -2)).addPreferredGap(0).add((GroupLayout.Group)layout.createParallelGroup(1, false).add((Component)this.btnAnalysed, -1, -1, 32767).add((Component)this.btnPlayOriginal, -2, 85, -2)).addPreferredGap(0).add((GroupLayout.Group)layout.createParallelGroup(1).add((Component)this.btnPlayFound).add((Component)this.btnPlayTranscription)).addPreferredGap(0).add((GroupLayout.Group)layout.createParallelGroup(1, false).add((Component)this.btnClearLog, -1, -1, 32767).add((Component)this.btnBest, -2, 81, -2)).add(3, 3, 3).add((GroupLayout.Group)layout.createParallelGroup(1).add((GroupLayout.Group)layout.createSequentialGroup().add(4, 4, 4).add((Component)this.btnReindex).addPreferredGap(0).add((Component)this.btnAbout)).add(2, (GroupLayout.Group)layout.createSequentialGroup().add(19, 19, 19).add((Component)this.txtBest, -2, 253, -2))))).add(42, 42, 42).add((Component)this.jLabel6).add(501, 501, 501)));
        layout.linkSize(new Component[] { this.btnChooseFile, this.btnFind, this.btnPlayFound, this.btnPlayOriginal, this.btnPlayTranscription, this.btnTranscribe }, 1);
        layout.setVerticalGroup((GroupLayout.Group)layout.createParallelGroup(1).add(2, (GroupLayout.Group)layout.createSequentialGroup().addContainerGap(644, 32767).add((Component)this.jLabel6).add(186, 186, 186)).add(2, (GroupLayout.Group)layout.createSequentialGroup().add(392, 392, 392).add((GroupLayout.Group)layout.createParallelGroup(2).add((Component)this.jLabel2).add((GroupLayout.Group)layout.createParallelGroup(3).add((Component)this.jLabel3).add((Component)this.jLabel4))).addPreferredGap(0).add((GroupLayout.Group)layout.createParallelGroup(1).add((Component)this.jScrollPane1, -2, 186, -2).add((Component)this.jScrollPane2, 0, 0, 32767).add(2, (GroupLayout.Group)layout.createSequentialGroup().add((Component)this.spLog, -2, 163, -2).addPreferredGap(0).add((GroupLayout.Group)layout.createParallelGroup(2).add((Component)this.progressBar, -1, 20, 32767).add((GroupLayout.Group)layout.createParallelGroup(3).add((Component)this.cbSelectFFT, -2, -1, -2).add((Component)this.jLabel1))))).addPreferredGap(0).add((GroupLayout.Group)layout.createParallelGroup(1).add((GroupLayout.Group)layout.createSequentialGroup().add((GroupLayout.Group)layout.createParallelGroup(3).add((Component)this.btnFind).add((Component)this.btnPlayOriginal).add((Component)this.btnChooseFile, -2, 23, -2).add((Component)this.btnPlayFound).add((Component)this.btnClearLog).add((Component)this.btnBatch)).addPreferredGap(0).add((GroupLayout.Group)layout.createParallelGroup(3).add((Component)this.btnQuit).add((Component)this.btnTranscribe).add((Component)this.btnAnalysed).add((Component)this.btnPlayTranscription, -2, 23, -2).add((Component)this.btnBest).add((Component)this.btnReindex).add((Component)this.btnLiveQuery).add((Component)this.btnAbout))).add((Component)this.txtBest)).add(171, 171, 171)));
        this.pack();
    }
    
    private void btnPlayFoundActionPerformed(final ActionEvent evt) {
        final int row = this.tblMatches.getSelectedRow();
        final ABCMatch match = this.tuneMatches.elementAt(row);
        try {
            if (!MIDITools.instance().isFinished()) {
                MIDITools.instance().setFinished(true);
            }
            else {
                MIDITools.instance().playMidiFile(match.getCorpusEntry().getMidiFileName());
            }
        }
        catch (Exception e) {
            System.out.println("Could not play tune");
            e.printStackTrace();
        }
    }
    
    private void tblMatchesMouseClicked(final MouseEvent evt) {
        if (evt.getClickCount() == 2) {
            final JTable target = (JTable)evt.getSource();
            final int row = target.getSelectedRow();
            final int column = target.getSelectedColumn();
            final ABCMatch match = this.tuneMatches.elementAt(row);
            JOptionPane.showMessageDialog(this, match.getNotation(), match.getFileName(), -1);
        }
    }
    
    private void jScrollPane2MouseClicked(final MouseEvent evt) {
    }
    
    private void btnFindActionPerformed(final ActionEvent evt) {
        if (this.finder != null && this.finder.isRunning()) {
            this.finder.setRunning(false);
        }
        else {
            (this.finder = new ABCFinder()).setTranscribedNotes(this.transcriber.getTranscribedNotes());
            this.finder.setSearchString(this.getTxtABC().getText());
            this.finder.setStartIn(((Hashtable<K, Object>)MattProperties.instance()).get("SearchCorpus").toString());
            this.finder.finda();
        }
    }
    
    private void btnTranscribeActionPerformed(final ActionEvent evt) {
        this.transcriber.transcribea();
    }
    
    private void btnClearLogActionPerformed(final ActionEvent evt) {
        this.txtLog.setText("");
        this.getTxtABC().setText("");
    }
    
    private void btnChooseFileActionPerformed(final ActionEvent evt) {
        final JFileChooser fc = new JFileChooser();
        fc.setFileFilter(new WavFilter());
        System.out.println(this.transcriber.getInputFile());
        fc.setSelectedFile(new File("" + ((Hashtable<K, Object>)MattProperties.instance()).get("BatchPath")));
        final int returnVal = fc.showOpenDialog(this);
        if (returnVal == 0) {
            this.clearGraphs();
            this.transcriber.setInputFile(fc.getSelectedFile().toString());
            this.transcriber.loadAudio();
        }
    }
    
    private void btnPlayOriginalActionPerformed(final ActionEvent evt) {
        this.transcriber.playOriginal();
    }
    
    private void btnPlayTranscriptionActionPerformed(final ActionEvent evt) {
        if (this.tunePlayer.isPlaying()) {
            this.tunePlayer.stopPlaying();
            return;
        }
        try {
            final FileWriter outFile = new FileWriter("temp.abc");
            final PrintWriter out = new PrintWriter(outFile);
            out.println("X:1");
            out.println("T:Temp");
            out.println("R:Reel");
            out.println("M:C|");
            out.println("L:1/8");
            out.println("K:D");
            out.println(this.getTxtABC().getText());
            out.close();
            final File abcFile = new File("temp.abc");
            final TuneBook book = new TuneBook(abcFile);
            final Tune aTune = book.getTune(1);
            this.tunePlayer.play(aTune);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void btnQuitActionPerformed(final ActionEvent evt) {
        System.exit(0);
    }
    
    private void btnAnalysedActionPerformed(final ActionEvent evt) {
        if (this.transcriber.isIsPlaying()) {
            this.transcriber.setIsPlaying(false);
        }
        else {
            new Thread() {
                public void run() {
                    MattGuiNB.this.transcriber.playTranscription();
                }
            }.start();
        }
    }
    
    private void btnBestActionPerformed(final ActionEvent evt) {
        if (this.best == null) {
            return;
        }
        try {
            if (!MIDITools.instance().isFinished()) {
                MIDITools.instance().setFinished(true);
            }
            else {
                MIDITools.instance().playMidiFile(this.best.getCorpusEntry().getMidiFileName());
            }
        }
        catch (Exception e) {
            System.out.println("Could not play tune");
            e.printStackTrace();
        }
    }
    
    private void btnReindexActionPerformed(final ActionEvent evt) {
        new Thread() {
            public void run() {
                CorpusIndex.instance().reindex();
            }
        }.start();
    }
    
    private void btnBatchActionPerformed(final ActionEvent evt) {
        if (this.batchJob != null && this.batchJob.isRunning()) {
            this.batchJob.setRunning(false);
        }
        else {
            this.batchJob = new BatchJob();
            if (this.batchJob.chooseFolder()) {
                this.batchJob.start();
            }
        }
    }
    
    private void cbSelectFFTActionPerformed(final ActionEvent evt) {
    }
    
    private void cbSelectFFTItemStateChanged(final ItemEvent evt) {
        final int i = this.cbSelectFFT.getSelectedIndex();
        if (i == -1 || this.fftGraphs.size() == 0) {
            return;
        }
        this.getContentPane().remove(this.fftGraph);
        if (i < this.fftGraphs.size()) {
            (this.fftGraph = this.fftGraphs.elementAt(i)).setBounds(400, 10, 380, 120);
            this.getContentPane().add(this.fftGraph);
            this.fftGraph.repaint();
        }
    }
    
    private void btnLiveQueryActionPerformed(final ActionEvent evt) {
        if (this.btnLiveQuery.getText().equals("Live Query")) {
            this.btnLiveQuery.setText("Recording...");
            this.audioCapture.start();
        }
        else {
            this.audioCapture.stop();
            this.btnLiveQuery.setText("Live Query");
            try {
                Thread.sleep(1000L);
            }
            catch (Exception ex) {}
            this.transcriber.setInputFile(this.audioCapture.getFileName());
            this.transcriber.loadAudio();
        }
    }
    
    private void btnAboutActionPerformed(final ActionEvent evt) {
        About.instance().setVisible(true);
    }
    
    public static void main(final String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MattGuiNB((MattGuiNB$1)null).setVisible(true);
            }
        });
    }
    
    public void addFFTGraph(final Graph graph, final String title) {
        final DefaultComboBoxModel model = (DefaultComboBoxModel)this.cbSelectFFT.getModel();
        model.addElement("" + title);
        this.fftGraphs.add(graph);
        this.cbSelectFFT.setSelectedIndex(model.getSize() - 1);
        this.getContentPane().remove(this.fftGraph);
        graph.setBounds(400, 10, 380, 120);
        graph.setBackground(Color.WHITE);
        this.fftGraph = graph;
        this.getContentPane().add(graph);
        graph.repaint();
    }
    
    public void clearFFTGraphs() {
        final DefaultComboBoxModel model = (DefaultComboBoxModel)this.cbSelectFFT.getModel();
        model.removeAllElements();
        this.fftGraphs.removeAllElements();
        this.fftGraph.getDefaultSeries().clear();
    }
    
    public void enableButtons(final boolean enabled) {
    }
    
    public void clearGraphs() {
        this.frameGraph.clear();
        this.signalGraph.clear();
        this.odfGraph.clear();
        this.fftGraph.clear();
        this.clearMatches();
        this.getFftGraph().removeAll();
        final DefaultComboBoxModel model = (DefaultComboBoxModel)this.cbSelectFFT.getModel();
        model.removeAllElements();
        this.fftGraphs.removeAllElements();
        this.txtABC.setText("");
        this.txtLog.setText("");
        this.txtBest.setText("");
    }
    
    public static void center(final JFrame frame, final int w, final int h) {
        final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        final Point center = ge.getCenterPoint();
        final Rectangle bounds = ge.getMaximumWindowBounds();
        final int x = center.x - w / 2;
        final int y = center.y - h / 2;
        frame.setBounds(x, y, w, h);
        if (w == bounds.width && h == bounds.height) {
            frame.setExtendedState(6);
        }
        frame.validate();
    }
    
    public ODCFTranscriber getTranscriber() {
        return this.transcriber;
    }
    
    public void setTranscriber(final ODCFTranscriber transcriber) {
        this.transcriber = transcriber;
    }
    
    public Matt getMatt() {
        return this.matt;
    }
    
    public void setMatt(final Matt matt) {
        this.matt = matt;
    }
    
    public Graph getFrameGraph() {
        return this.frameGraph;
    }
    
    public Graph getSignalGraph() {
        return this.signalGraph;
    }
    
    public Graph getOdfGraph() {
        return this.odfGraph;
    }
    
    public static void log(final Object s) {
        instance().getTxtLog().append(s + System.getProperty("line.separator"));
        instance().getTxtLog().setCaretPosition(MattGuiNB._instance.txtLog.getText().length());
    }
    
    public static MattGuiNB instance() {
        if (MattGuiNB._instance == null) {
            MattGuiNB._instance = new MattGuiNB();
        }
        return MattGuiNB._instance;
    }
    
    public void addMatch(final ABCMatch match) {
        final DefaultTableModel model = (DefaultTableModel)this.tblMatches.getModel();
        final Vector row = new Vector();
        row.add(match.getTitle());
        row.add(match.getLine());
        row.add(new Double(match.getEditDistance()));
        model.addRow(row);
        this.tuneMatches.add(match);
    }
    
    public void clearMatches() {
        final DefaultTableModel model = (DefaultTableModel)this.tblMatches.getModel();
        model.setRowCount(0);
        this.tuneMatches.clear();
    }
    
    public JTextArea getTxtABC() {
        return this.txtABC;
    }
    
    public void setTxtABC(final JTextArea txtABC) {
        this.txtABC = txtABC;
    }
    
    public synchronized void setBestSoFar(final ABCMatch match) {
        this.best = match;
        this.txtBest.setText("Title: " + match.getTitle() + " ED:" + match.getEditDistance());
    }
    
    public JProgressBar getProgressBar() {
        return this.progressBar;
    }
    
    public void setProgressBar(final JProgressBar progressBar) {
        this.progressBar = progressBar;
    }
    
    public JTextArea getTxtLog() {
        return this.txtLog;
    }
    
    public void setTxtLog(final JTextArea txtLog) {
        this.txtLog = txtLog;
    }
    
    public Graph getFftGraph() {
        return this.fftGraph;
    }
    
    public void setFftGraph(final Graph fftGraph) {
        this.fftGraph = fftGraph;
    }
    
    public Vector<Graph> getFftGraphs() {
        return this.fftGraphs;
    }
    
    public void setFftGraphs(final Vector<Graph> fftGraphs) {
        this.fftGraphs = fftGraphs;
    }
    
    public void setBns() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
