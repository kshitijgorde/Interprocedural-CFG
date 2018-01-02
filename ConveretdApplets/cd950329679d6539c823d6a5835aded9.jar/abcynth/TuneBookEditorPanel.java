// 
// Decompiled by Procyon v0.5.30
// 

package abcynth;

import abc.notation.Tune;
import abc.parser.TuneBook;
import abc.ui.swing.TuneEditorPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.LayoutManager;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JScrollPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import abc.midi.TunePlayer;
import abcynth.ui.TuneBookTable;
import javax.swing.JSplitPane;

public class TuneBookEditorPanel extends JSplitPane
{
    private TuneBookTable m_tuneBookTable;
    private int m_selectedTuneIndex;
    private TuneEditorSplitPane m_tuneEditPane;
    private boolean m_tuneHasChangedInEditor;
    private OwnParser m_parser;
    private TunePlayer player;
    private DocumentListener m_documentListener;
    private Object m_mutex;
    
    public TuneBookEditorPanel() {
        super(0);
        this.m_tuneBookTable = null;
        this.m_selectedTuneIndex = -1;
        this.m_tuneEditPane = null;
        this.m_tuneHasChangedInEditor = false;
        this.m_parser = new OwnParser();
        this.player = null;
        this.m_documentListener = null;
        this.m_mutex = new Object();
        this.m_parser.start();
        this.m_tuneEditPane = new TuneEditorSplitPane();
        this.m_documentListener = new DocumentListener() {
            public void changedUpdate(final DocumentEvent e) {
            }
            
            public void insertUpdate(final DocumentEvent e) {
                TuneBookEditorPanel.this.m_tuneHasChangedInEditor = true;
            }
            
            public void removeUpdate(final DocumentEvent e) {
                TuneBookEditorPanel.this.m_tuneHasChangedInEditor = true;
            }
        };
        this.m_tuneEditPane.getTuneEditorPane().getDocument().addDocumentListener(this.m_documentListener);
        (this.player = new TunePlayer()).start();
        this.m_tuneBookTable = new TuneBookTable();
        this.setDividerSize(10);
        this.setOneTouchExpandable(true);
        final JScrollPane tablePane = new JScrollPane(this.m_tuneBookTable, 20, 30);
        final JPanel upperPanel = new JPanel(new BorderLayout());
        upperPanel.add(tablePane, "Center");
        this.setTopComponent(upperPanel);
        final JPanel panel1 = new JPanel(new BorderLayout());
        panel1.add(this.m_tuneEditPane, "Center");
        final JPanel downerPanel = new JPanel(new BorderLayout());
        downerPanel.add(this.m_tuneEditPane, "Center");
        this.setBottomComponent(downerPanel);
        this.m_tuneBookTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(final ListSelectionEvent e) {
                int selectedIndex = -1;
                int viewColumnNumber = -1;
                try {
                    selectedIndex = TuneBookEditorPanel.this.m_tuneBookTable.getSelectionModel().getLeadSelectionIndex();
                    viewColumnNumber = TuneBookEditorPanel.this.m_tuneBookTable.convertColumnIndexToView(10);
                    if (TuneBookEditorPanel.this.m_tuneBookTable.getValueAt(selectedIndex, viewColumnNumber) != null) {
                        final int selectedTuneIndex = (int)TuneBookEditorPanel.this.m_tuneBookTable.getValueAt(selectedIndex, viewColumnNumber);
                        if (TuneBookEditorPanel.this.m_selectedTuneIndex != selectedTuneIndex) {
                            TuneBookEditorPanel.this.m_parser.parse(e);
                        }
                    }
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    System.out.println("INDEX :" + selectedIndex + " " + viewColumnNumber);
                    System.out.println("=" + TuneBookEditorPanel.this.m_tuneBookTable.getValueAt(selectedIndex, viewColumnNumber));
                }
            }
        });
    }
    
    public boolean isEditingTune() {
        return this.m_tuneHasChangedInEditor;
    }
    
    public TuneEditorPane getTuneEditArea() {
        return this.m_tuneEditPane.getTuneEditorPane();
    }
    
    public TuneBookTable getTuneBookTable() {
        return this.m_tuneBookTable;
    }
    
    public TuneEditorSplitPane getTuneEditSplitPane() {
        return this.m_tuneEditPane;
    }
    
    public void setTuneBook(final TuneBook tuneBook) {
        this.m_tuneBookTable.setTuneBook(tuneBook);
    }
    
    public void onTuneSelectedChange(final int newSelectedTuneReferenceNumber) {
        try {
            if (this.m_tuneHasChangedInEditor) {
                final String newNotation = this.m_tuneEditPane.getTuneEditorPane().getDocument().getText(0, this.m_tuneEditPane.getTuneEditorPane().getDocument().getLength());
                final Tune newTune = this.m_tuneBookTable.getTuneBook().putTune(newNotation);
                if (newTune.getReferenceNumber() != newSelectedTuneReferenceNumber) {
                    System.out.println("Changing reference number from " + this.m_selectedTuneIndex + " to " + newTune.getReferenceNumber());
                }
                else {
                    System.out.println("Saving new tune notation for tune " + this.m_selectedTuneIndex);
                }
            }
            this.m_tuneEditPane.getTuneEditorPane().getDocument().removeDocumentListener(this.m_documentListener);
            this.m_tuneEditPane.getTuneEditorPane().setText(this.m_tuneBookTable.getTuneBook().getTuneNotation(newSelectedTuneReferenceNumber));
            this.m_tuneHasChangedInEditor = false;
            this.m_tuneEditPane.getTuneEditorPane().getDocument().addDocumentListener(this.m_documentListener);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private class OwnParser extends Thread
    {
        private ListSelectionEvent m_selectionEvent;
        
        public OwnParser() {
            super("ABC-TuneBookEditor");
            this.m_selectionEvent = null;
        }
        
        public void parse(final ListSelectionEvent event) {
            this.m_selectionEvent = event;
            synchronized (TuneBookEditorPanel.this.m_mutex) {
                TuneBookEditorPanel.this.m_mutex.notify();
            }
        }
        
        public void run() {
            try {
                while (true) {
                    synchronized (TuneBookEditorPanel.this.m_mutex) {
                        TuneBookEditorPanel.this.m_mutex.wait();
                        final int selectedIndex = TuneBookEditorPanel.this.m_tuneBookTable.getSelectionModel().getLeadSelectionIndex();
                        final int viewColumnNumber = TuneBookEditorPanel.this.m_tuneBookTable.convertColumnIndexToView(10);
                        final int selectedTuneReferenceNumber = (int)TuneBookEditorPanel.this.m_tuneBookTable.getValueAt(selectedIndex, viewColumnNumber);
                        TuneBookEditorPanel.this.onTuneSelectedChange(selectedTuneReferenceNumber);
                        TuneBookEditorPanel.this.m_selectedTuneIndex = selectedTuneReferenceNumber;
                    }
                }
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
