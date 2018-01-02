// 
// Decompiled by Procyon v0.5.30
// 

package abcynth;

import abc.xml.Abc2xml;
import javax.sound.midi.Sequence;
import abc.midi.MidiConverterAbstract;
import java.io.IOException;
import abc.midi.BasicMidiConverter;
import javax.swing.JFileChooser;
import javax.swing.AbstractAction;
import java.awt.Dimension;
import java.awt.Toolkit;
import abc.midi.PlayerStateChangeEvent;
import abc.midi.TempoChangeEvent;
import java.awt.event.WindowEvent;
import javax.swing.JTable;
import java.io.OutputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import abc.parser.AbcFileParserListenerInterface;
import javax.sound.midi.Instrument;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.awt.event.MouseListener;
import abc.notation.MusicElement;
import abc.ui.swing.JScoreElement;
import abc.notation.NoteAbstract;
import scanner.PositionableInCharStream;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import abc.notation.Tune;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.MidiSystem;
import javax.swing.JCheckBoxMenuItem;
import abcynth.ui.RemoveTuneAction;
import abcynth.ui.AddTuneAction;
import javax.swing.KeyStroke;
import javax.swing.JMenuItem;
import java.awt.Component;
import javax.swing.JMenuBar;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import javax.swing.JPopupMenu;
import javax.swing.JMenu;
import javax.swing.Action;
import abcynth.ui.TuneBookActionAbstract;
import abc.midi.TunePlayer;
import abc.parser.TuneBook;
import java.io.File;
import java.awt.event.WindowListener;
import abc.midi.TunePlayerListenerInterface;
import javax.swing.JFrame;

public class PlayerApp extends JFrame implements TunePlayerListenerInterface, WindowListener
{
    private File m_file;
    private File lastDirectory;
    private TuneBook m_tuneBook;
    private TuneBookEditorPanel m_tuneBookEditorPanel;
    private TunePlayer m_player;
    private CircularBuffer m_lastOpenedFiles;
    private TuneBookActionAbstract m_addTuneAction;
    private TuneBookActionAbstract m_saveAction;
    private TuneBookActionAbstract m_saveAsAction;
    private Action m_tune2MidiExport;
    private Action m_tune2pngExport;
    private Action m_abc2xmlExport;
    private Action m_showHideLogAction;
    private Action m_enableColoringAction;
    private JMenu m_fileMenu;
    private JMenu m_tunebookMenu;
    private JMenu m_lastOpenedFilesMenu;
    private JMenu m_windowsMenu;
    private JMenu m_viewMenu;
    private PlayerToolBar m_playerToolBar;
    private JPopupMenu m_tunePopMenu;
    private LogFrame m_logFrame;
    
    public PlayerApp() {
        super("ABCynth");
        this.m_file = null;
        this.lastDirectory = null;
        this.m_tuneBook = null;
        this.m_tuneBookEditorPanel = null;
        this.m_player = null;
        this.m_lastOpenedFiles = null;
        this.m_saveAsAction = null;
        this.m_enableColoringAction = null;
        this.m_viewMenu = null;
        this.m_playerToolBar = null;
        this.m_tunePopMenu = null;
        this.m_logFrame = null;
        this.m_logFrame = new LogFrame();
        this.m_tuneBookEditorPanel = new TuneBookEditorPanel();
        this.m_lastOpenedFiles = new CircularBuffer(5);
        this.setDefaultCloseOperation(0);
        final File f = new File("config.dat");
        try {
            if (f.exists()) {
                final ObjectInputStream oos = new ObjectInputStream(new FileInputStream(f));
                final Object o = oos.readObject();
                this.m_lastOpenedFiles = (CircularBuffer)o;
                if (this.m_lastOpenedFiles.size() != 0) {
                    this.lastDirectory = this.m_lastOpenedFiles.lastElement();
                }
                oos.close();
            }
            else {
                this.lastDirectory = new File(".");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        final JMenuBar menuBar = new JMenuBar();
        JMenuItem menuItem = null;
        this.m_fileMenu = new JMenu("File");
        menuItem = new JMenuItem(new OpenAbcFileAction("Open...", "Opens a new ABC file", 79, this));
        menuItem.setAccelerator(KeyStroke.getKeyStroke(79, 2));
        this.m_fileMenu.add(menuItem);
        this.m_lastOpenedFilesMenu = new JMenu("Reopen");
        this.m_fileMenu.add(this.m_lastOpenedFilesMenu);
        for (int i = 0; i < this.m_lastOpenedFiles.size(); ++i) {
            final String name = this.m_lastOpenedFiles.elementAt(i).getAbsolutePath();
            this.m_lastOpenedFilesMenu.add(new OpenLastAction(name, "Opens the file " + name));
        }
        this.m_fileMenu.addSeparator();
        this.m_saveAction = new SaveAction("Save", "Saves tunebook updates", this);
        menuItem = new JMenuItem(this.m_saveAction);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(83, 2));
        this.m_fileMenu.add(menuItem);
        this.m_saveAsAction = new SaveToAbcFileAction("Save as...", "Saves the current tunebook to a file", 83, this);
        this.m_fileMenu.add(this.m_saveAsAction);
        this.m_tune2MidiExport = new Tune2MidiExport("Export to midi...", "Saves the selected tune to a midi file", this);
        this.m_fileMenu.add(this.m_tune2MidiExport);
        this.m_tune2pngExport = new Tune2PNGExport("Export to png...", "Saves the selected tune to a png image file", this);
        this.m_fileMenu.add(this.m_tune2pngExport);
        this.m_abc2xmlExport = new Tune2XMLExport("Export to MusicXML...", "Saves the selected tune to a musicXML file", this);
        this.m_fileMenu.add(this.m_abc2xmlExport);
        this.m_tunebookMenu = new JMenu("Tunebook");
        menuItem = new JMenuItem(new NewTuneBookAction("New", "Creates a new emty tunebook"));
        menuItem.setAccelerator(KeyStroke.getKeyStroke(78, 2));
        this.m_tunebookMenu.add(menuItem);
        this.m_addTuneAction = new AddTuneAction("Add Tune", "Adds a new tune to the opened tunebook", 65);
        this.m_tunebookMenu.add(this.m_addTuneAction);
        this.m_tunebookMenu.add(new RemoveTuneAction("Remove tune", "Removes the selected tune", 68, this.m_tuneBookEditorPanel.getTuneBookTable()));
        this.m_viewMenu = new JMenu("View");
        this.m_enableColoringAction = new EnableColoringAction("Enable tune coloring", "Differenciate tune parts with colors");
        this.m_viewMenu.add(new JCheckBoxMenuItem(this.m_enableColoringAction));
        this.m_fileMenu.addSeparator();
        menuItem = new JMenuItem(new ExitAction("Exit", "Exit ABCynth"));
        menuItem.setAccelerator(KeyStroke.getKeyStroke(81, 2));
        this.m_fileMenu.add(menuItem);
        final JMenu helpMenu = new JMenu("Help");
        helpMenu.add(new HelpAction("About ABCynth...", "About ABCynth", 72, this));
        menuBar.add(this.m_fileMenu);
        menuBar.add(this.m_tunebookMenu);
        menuBar.add(this.m_viewMenu);
        menuBar.add(helpMenu);
        this.setJMenuBar(menuBar);
        this.m_player = new TunePlayer();
        try {
            final Instrument[] allInstruments = MidiSystem.getSynthesizer().getAvailableInstruments();
            Instrument accordion = null;
            for (int j = 0; j < allInstruments.length && accordion == null; ++j) {
                if (allInstruments[j].getName().equalsIgnoreCase("accordion")) {
                    accordion = allInstruments[j];
                }
            }
            this.m_player.setInstrument(accordion);
        }
        catch (MidiUnavailableException e2) {
            e2.printStackTrace();
        }
        this.m_player.addListener(this);
        this.m_player.start();
        (this.m_playerToolBar = new PlayerToolBar(this.m_player)).setFloatable(false);
        this.m_playerToolBar.getPlayButton().addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                final Tune tune2play = PlayerApp.this.m_tuneBookEditorPanel.getTuneEditArea().getTune();
                if (tune2play != null) {
                    if (!PlayerApp.this.m_player.isPlaying()) {
                        PlayerApp.this.m_player.play(tune2play);
                    }
                    else {
                        PlayerApp.this.m_player.stopPlaying();
                    }
                }
            }
        });
        (this.m_tunePopMenu = new JPopupMenu("Tune")).add("Play");
        this.m_tunePopMenu.add("Export to midi...");
        this.getContentPane().add(this.m_playerToolBar, "North");
        this.getContentPane().add(this.m_tuneBookEditorPanel, "Center");
        this.addWindowListener(this);
        this.pack();
        this.setSize(600, 600);
        this.m_tuneBookEditorPanel.setDividerLocation(0.4);
        this.m_tuneBookEditorPanel.getTuneEditSplitPane().setDividerLocation(200);
        this.m_tuneBookEditorPanel.getTuneEditSplitPane().getScore().addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent e) {
                final JScoreElement sel = PlayerApp.this.m_tuneBookEditorPanel.getTuneEditSplitPane().getScore().getScoreElementAt(e.getPoint());
                if (sel != null) {
                    final MusicElement elmnt = sel.getMusicElement();
                    PlayerApp.this.m_tuneBookEditorPanel.getTuneEditSplitPane().getScore().setSelectedItem(sel);
                    if (elmnt != null && elmnt instanceof PositionableInCharStream) {
                        PlayerApp.this.m_tuneBookEditorPanel.getTuneEditArea().setSelectedItem((PositionableInCharStream)elmnt);
                        if (elmnt instanceof NoteAbstract) {
                            final NoteAbstract note = (NoteAbstract)elmnt;
                            System.out.println("properties for " + elmnt + " : slur?=" + note.isPartOfSlur() + " isLastOfGroup?=");
                            final String test = (note.getSlurDefinition() == null) ? "no slur" : ("start:" + note.getSlurDefinition().getStart() + " end:" + note.getSlurDefinition().getEnd());
                            System.out.println(test);
                        }
                    }
                }
            }
        });
        this.m_tuneBookEditorPanel.getTuneEditSplitPane().getTuneEditorPane().addCaretListener(new CaretListener() {
            public void caretUpdate(final CaretEvent e) {
                MusicElement elmnt = null;
                final Tune tune = PlayerApp.this.m_tuneBookEditorPanel.getTuneEditSplitPane().getTuneEditorPane().getTune();
                if (tune != null) {
                    elmnt = tune.getMusic().getElementAt(e.getDot());
                }
                PlayerApp.this.m_tuneBookEditorPanel.getTuneEditSplitPane().getScore().setSelectedItem(elmnt);
            }
        });
        this.setTuneBook(new TuneBook());
        this.addMouseListenerToHeaderInTable();
    }
    
    public void setFile(final File file) {
        try {
            this.m_file = file;
            final TuneBook tuneBook = new TuneBook(file, this.m_logFrame);
            this.setTuneBook(tuneBook);
            this.setTitle("ABCynth - " + file.getAbsolutePath() + " (" + this.m_tuneBook.size() + " tunes)");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void setTuneBook(final TuneBook tunebook) {
        this.m_tuneBook = tunebook;
        this.m_tuneBookEditorPanel.setTuneBook(this.m_tuneBook);
        this.m_addTuneAction.setTuneBook(this.m_tuneBook);
        this.m_saveAction.setTuneBook(this.m_tuneBook);
        this.m_saveAsAction.setTuneBook(this.m_tuneBook);
    }
    
    public void onExit() {
        final Object[] options = { "Yes", "No" };
        final int answer = JOptionPane.showOptionDialog(this, "Do you really want to exit ?", "Exit ?", 1, 3, null, options, options[1]);
        if (answer == 0) {
            try {
                final ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("config.dat"));
                oos.writeObject(this.m_lastOpenedFiles);
                oos.close();
                System.exit(0);
            }
            catch (Exception exc) {
                exc.printStackTrace();
            }
        }
    }
    
    private void addMouseListenerToHeaderInTable() {
        final MouseAdapter listMouseListener = new MouseAdapter() {
            public void mouseClicked(final MouseEvent e) {
                if (!PlayerApp.this.m_tunePopMenu.isVisible()) {
                    final JTable table = PlayerApp.this.m_tuneBookEditorPanel.getTuneBookTable();
                }
            }
            
            public void mousePressed(final MouseEvent e) {
            }
            
            public void mouseReleased(final MouseEvent e) {
                if (e.isPopupTrigger()) {
                    PlayerApp.this.m_tunePopMenu.show(PlayerApp.this.m_tuneBookEditorPanel, e.getX(), e.getY());
                }
            }
        };
        this.m_tuneBookEditorPanel.getTuneBookTable().addMouseListener(listMouseListener);
    }
    
    public void windowActivated(final WindowEvent e) {
    }
    
    public void windowClosed(final WindowEvent e) {
    }
    
    public void windowClosing(final WindowEvent e) {
        this.onExit();
    }
    
    public void windowDeactivated(final WindowEvent e) {
    }
    
    public void windowDeiconified(final WindowEvent e) {
    }
    
    public void windowIconified(final WindowEvent e) {
    }
    
    public void windowOpened(final WindowEvent e) {
    }
    
    TuneBookEditorPanel getTuneBookEditor() {
        return this.m_tuneBookEditorPanel;
    }
    
    TunePlayer getPlayer() {
        return this.m_player;
    }
    
    public void tempoChanged(final TempoChangeEvent e) {
    }
    
    public void playBegin(final PlayerStateChangeEvent e) {
    }
    
    public void playEnd(final PlayerStateChangeEvent e) {
        this.m_tuneBookEditorPanel.getTuneEditArea().setCaretPosition(0);
    }
    
    public void notePlayed(final NoteAbstract note) {
        if (this.m_player.getTune().equals(this.m_tuneBookEditorPanel.getTuneEditArea().getTune())) {
            final int begin = ((PositionableInCharStream)note).getPosition().getCharactersOffset();
            final int end = begin + ((PositionableInCharStream)note).getLength();
            try {
                this.m_tuneBookEditorPanel.getTuneEditArea().setCaretPosition(begin);
                this.m_tuneBookEditorPanel.getTuneEditArea().moveCaretPosition(end);
                this.m_tuneBookEditorPanel.getTuneEditArea().getCaret().setSelectionVisible(true);
                this.m_tuneBookEditorPanel.getTuneEditArea().repaint();
            }
            catch (IllegalArgumentException ex) {}
            this.m_tuneBookEditorPanel.getTuneEditSplitPane().getScore().setSelectedItem(note);
        }
    }
    
    public void partPlayed(final int begin, final int end) {
    }
    
    public static void main(final String[] arg) {
        final PlayerApp ui = new PlayerApp();
        if (arg.length != 0 && new File(arg[0]).exists()) {
            ui.setFile(new File(arg[0]));
        }
        final Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        ui.setLocation((int)(d.getWidth() - ui.getWidth()) / 2, (int)(d.getHeight() - ui.getHeight()) / 2);
        ui.setVisible(true);
    }
    
    public class Tune2MidiExport extends AbstractAction
    {
        private Component m_parent;
        
        public Tune2MidiExport(final String name, final String description, final Component parent) {
            this.m_parent = null;
            this.putValue("Name", name);
            this.putValue("ShortDescription", description);
            this.m_parent = parent;
        }
        
        public void actionPerformed(final ActionEvent e) {
            try {
                final Tune t = PlayerApp.this.m_tuneBookEditorPanel.getTuneEditArea().getTune();
                if (t != null) {
                    final JFileChooser chooser = new JFileChooser(PlayerApp.this.lastDirectory);
                    final int returnVal = chooser.showSaveDialog(this.m_parent);
                    final File file = chooser.getSelectedFile();
                    if (file != null) {
                        final MidiConverterAbstract conv = new BasicMidiConverter();
                        final Sequence s = conv.toMidiSequence(t);
                        final int[] types = MidiSystem.getMidiFileTypes(s);
                        if (types.length > 0) {
                            MidiSystem.write(s, types[0], file);
                        }
                    }
                }
            }
            catch (IOException excpt) {
                excpt.printStackTrace();
            }
        }
    }
    
    public class Tune2PNGExport extends AbstractAction
    {
        private Component m_parent;
        
        public Tune2PNGExport(final String name, final String description, final Component parent) {
            this.m_parent = null;
            this.putValue("Name", name);
            this.putValue("ShortDescription", description);
            this.m_parent = parent;
        }
        
        public void actionPerformed(final ActionEvent e) {
            try {
                final Tune t = PlayerApp.this.m_tuneBookEditorPanel.getTuneEditArea().getTune();
                if (t != null) {
                    final JFileChooser chooser = new JFileChooser(PlayerApp.this.lastDirectory);
                    final int returnVal = chooser.showSaveDialog(this.m_parent);
                    final File file = chooser.getSelectedFile();
                    if (file != null) {
                        PlayerApp.this.m_tuneBookEditorPanel.getTuneEditSplitPane().getScore().writeScoreTo(file);
                    }
                }
            }
            catch (IOException excpt) {
                excpt.printStackTrace();
            }
        }
    }
    
    public class Tune2XMLExport extends AbstractAction
    {
        private Component m_parent;
        
        public Tune2XMLExport(final String name, final String description, final Component parent) {
            this.m_parent = null;
            this.putValue("Name", name);
            this.putValue("ShortDescription", description);
            this.m_parent = parent;
        }
        
        public void actionPerformed(final ActionEvent e) {
            try {
                final Tune t = PlayerApp.this.m_tuneBookEditorPanel.getTuneEditArea().getTune();
                if (t != null) {
                    final JFileChooser chooser = new JFileChooser(PlayerApp.this.lastDirectory);
                    final int returnVal = chooser.showSaveDialog(this.m_parent);
                    final File file = chooser.getSelectedFile();
                    if (file != null) {
                        Abc2xml.writeAsMusicXML(t, file);
                    }
                }
            }
            catch (IOException excpt) {
                excpt.printStackTrace();
            }
        }
    }
    
    public class NewTuneBookAction extends AbstractAction
    {
        public NewTuneBookAction(final String name, final String description) {
            this.putValue("Name", name);
            this.putValue("ShortDescription", description);
        }
        
        public void actionPerformed(final ActionEvent e) {
            PlayerApp.this.setTuneBook(new TuneBook());
            PlayerApp.this.setTitle("ABCynth");
        }
    }
    
    public class ExitAction extends AbstractAction
    {
        public ExitAction(final String name, final String description) {
            this.putValue("Name", name);
            this.putValue("ShortDescription", description);
        }
        
        public void actionPerformed(final ActionEvent e) {
            PlayerApp.this.onExit();
        }
    }
    
    public class ShowHideLogAction extends AbstractAction
    {
        public ShowHideLogAction(final String name, final String description) {
            this.putValue("Name", name);
            this.putValue("ShortDescription", description);
        }
        
        public void actionPerformed(final ActionEvent e) {
            if (PlayerApp.this.m_logFrame.isVisible()) {
                PlayerApp.this.m_logFrame.setVisible(false);
            }
            else {
                PlayerApp.this.m_logFrame.setVisible(true);
            }
        }
    }
    
    public class EnableColoringAction extends AbstractAction
    {
        public EnableColoringAction(final String name, final String description) {
            this.putValue("Name", name);
            this.putValue("ShortDescription", description);
        }
        
        public void actionPerformed(final ActionEvent e) {
            if (PlayerApp.this.m_tuneBookEditorPanel.getTuneEditArea().isColoringEnabled()) {
                PlayerApp.this.m_tuneBookEditorPanel.getTuneEditArea().setColoringEnable(false);
            }
            else {
                PlayerApp.this.m_tuneBookEditorPanel.getTuneEditArea().setColoringEnable(true);
            }
        }
    }
    
    public class OpenAbcFileAction extends AbstractAction
    {
        private Component m_parent;
        
        public OpenAbcFileAction(final String name, final String description, final int shortcurt, final Component parent) {
            this.m_parent = null;
            this.putValue("Name", name);
            this.putValue("ShortDescription", description);
            this.putValue("MnemonicKey", new Integer(shortcurt));
            this.m_parent = parent;
        }
        
        public void actionPerformed(final ActionEvent e) {
            try {
                final JFileChooser chooser = new JFileChooser(PlayerApp.this.lastDirectory);
                final int returnVal = chooser.showOpenDialog(this.m_parent);
                if (returnVal == 0) {
                    final File file = chooser.getSelectedFile();
                    PlayerApp.this.lastDirectory = file;
                    final File abcFile = new File(file.getPath());
                    if (!PlayerApp.this.m_lastOpenedFiles.contains(abcFile)) {
                        PlayerApp.this.m_lastOpenedFiles.insertElementAt(abcFile, 0);
                        final Action a = new OpenLastAction(abcFile.getAbsolutePath(), "Last Opened file");
                        final JMenuItem m = new JMenuItem(a);
                        PlayerApp.this.m_lastOpenedFilesMenu.add(m, 0);
                    }
                    PlayerApp.this.setFile(abcFile);
                }
                chooser.setVisible(true);
                final ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("config.dat"));
                oos.writeObject(PlayerApp.this.lastDirectory);
                oos.close();
            }
            catch (Exception ex) {}
        }
    }
    
    public class SaveAction extends TuneBookActionAbstract
    {
        private Component m_parent;
        
        public SaveAction(final String name, final String description, final Component parent) {
            this.m_parent = null;
            this.putValue("Name", name);
            this.putValue("ShortDescription", description);
            this.m_parent = parent;
        }
        
        public void actionPerformed(final ActionEvent e) {
            try {
                if (PlayerApp.this.m_tuneBookEditorPanel.isEditingTune()) {
                    final String newNotation = PlayerApp.this.m_tuneBookEditorPanel.getTuneEditArea().getDocument().getText(0, PlayerApp.this.m_tuneBookEditorPanel.getTuneEditArea().getDocument().getLength());
                    PlayerApp.this.m_tuneBook.putTune(newNotation);
                }
                System.out.println("Saving changes for " + this.getTuneBook());
                this.getTuneBook().save();
                PlayerApp.this.setTitle("ABCynth - " + PlayerApp.this.m_tuneBook.getFile().getAbsolutePath() + "\\" + PlayerApp.this.m_tuneBook.getFile().getName() + " (" + PlayerApp.this.m_tuneBook.size() + " tunes)");
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public class SaveToAbcFileAction extends TuneBookActionAbstract
    {
        private Component m_parent;
        
        public SaveToAbcFileAction(final String name, final String description, final int shortcurt, final Component parent) {
            this.m_parent = null;
            this.putValue("Name", name);
            this.putValue("ShortDescription", description);
            this.putValue("MnemonicKey", new Integer(shortcurt));
            this.m_parent = parent;
        }
        
        public void actionPerformed(final ActionEvent e) {
            try {
                final JFileChooser chooser = new JFileChooser(PlayerApp.this.lastDirectory);
                final int returnVal = chooser.showSaveDialog(this.m_parent);
                final File abcFile = chooser.getSelectedFile();
                if (abcFile != null) {
                    PlayerApp.this.lastDirectory = abcFile;
                    chooser.setVisible(true);
                    this.getTuneBook().saveTo(abcFile);
                    if (!PlayerApp.this.m_lastOpenedFiles.contains(abcFile)) {
                        PlayerApp.this.m_lastOpenedFiles.insertElementAt(abcFile, 0);
                        final Action a = new OpenLastAction(abcFile.getAbsolutePath(), "Opens the file " + abcFile.getAbsolutePath());
                        final JMenuItem m = new JMenuItem(a);
                        PlayerApp.this.m_lastOpenedFilesMenu.add(m, 0);
                    }
                }
                PlayerApp.this.setTitle("ABCynth - " + abcFile.getAbsolutePath() + "\\" + abcFile.getName() + " (" + PlayerApp.this.m_tuneBook.size() + " tunes)");
            }
            catch (Exception ex) {}
        }
    }
    
    public class OpenLastAction extends AbstractAction
    {
        public OpenLastAction(final String name, final String description) {
            this.putValue("Name", name);
            this.putValue("ShortDescription", description);
        }
        
        public void actionPerformed(final ActionEvent e) {
            final File selectedFile = new File(((JMenuItem)e.getSource()).getText());
            PlayerApp.this.setFile(selectedFile);
            PlayerApp.this.m_lastOpenedFilesMenu.remove((JMenuItem)e.getSource());
            PlayerApp.this.m_lastOpenedFilesMenu.add((Component)e.getSource(), 0);
        }
    }
    
    class MyScoreSelectionListener
    {
    }
}
