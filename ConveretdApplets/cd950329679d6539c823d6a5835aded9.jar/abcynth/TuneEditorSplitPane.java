// 
// Decompiled by Procyon v0.5.30
// 

package abcynth;

import java.awt.Dimension;
import java.awt.LayoutManager;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.Component;
import javax.swing.JScrollPane;
import abc.parser.TuneParserListenerInterface;
import abc.notation.Tune;
import abc.parser.TuneParserAdapter;
import abcynth.ui.ErrorsList;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import abc.ui.swing.JScoreComponent;
import abcynth.ui.ParsingEventsList;
import javax.swing.JList;
import abc.ui.swing.TuneEditorPane;
import javax.swing.JSplitPane;

public class TuneEditorSplitPane extends JSplitPane
{
    private TuneEditorPane m_tunePane;
    private JList m_errorsList;
    private ParsingEventsList m_tokensList;
    private JScoreComponent m_score;
    private JTextArea m_tuneHeaderComments;
    private JTabbedPane m_tabbedPane;
    private static final String HEADER_TAB_NAME = "Header";
    private static final String ERRORS_TAB_NAME = "Errors";
    private static final String TOKENS_TAB_NAME = "Tokens";
    private static final String SCORE_TAB_NAME = "Score";
    private static final boolean DISPLAY_TOKEN_TAB = false;
    private static final int SCORE_SIZE = 38;
    
    public TuneEditorSplitPane() {
        super(0);
        this.m_tunePane = null;
        this.m_errorsList = null;
        this.m_tokensList = null;
        this.m_score = null;
        this.m_tuneHeaderComments = null;
        this.m_tabbedPane = null;
        this.setOneTouchExpandable(true);
        this.m_tunePane = new TuneEditorPane();
        this.m_errorsList = new ErrorsList(this.m_tunePane.getParser());
        (this.m_score = new JScoreComponent()).setJustification(true);
        this.m_score.setSize(38.0f);
        this.m_tunePane.getParser().addListener(new TuneParserAdapter() {
            public void tuneEnd(final Tune tune) {
                TuneEditorSplitPane.this.m_score.setTune(tune);
            }
        });
        this.setTopComponent(new JScrollPane(this.m_tunePane, 20, 30));
        this.m_errorsList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(final ListSelectionEvent e) {
                final ErrorsList.Error err = TuneEditorSplitPane.this.m_errorsList.getModel().getElementAt(TuneEditorSplitPane.this.m_errorsList.getSelectedIndex());
                try {
                    TuneEditorSplitPane.this.m_tunePane.setCaretPosition(err.getBeginOffset());
                    TuneEditorSplitPane.this.m_tunePane.moveCaretPosition(err.getEndoffset());
                    TuneEditorSplitPane.this.m_tunePane.getCaret().setSelectionVisible(true);
                    TuneEditorSplitPane.this.m_tunePane.repaint();
                }
                catch (IllegalArgumentException ex) {}
            }
        });
        this.m_tabbedPane = new JTabbedPane(3);
        (this.m_tuneHeaderComments = new JTextArea()).setBackground(Color.lightGray);
        this.m_tuneHeaderComments.setEditable(false);
        this.m_tabbedPane.addTab("Score", new JScrollPane(this.m_score, 20, 30));
        this.m_tabbedPane.addTab("Errors", new JScrollPane(this.m_errorsList, 20, 30));
        this.m_tabbedPane.addTab("Header", new JScrollPane(this.m_tuneHeaderComments, 20, 30));
        final JPanel downerPanel = new JPanel(new BorderLayout());
        downerPanel.add(this.m_tabbedPane, "Center");
        this.setBottomComponent(downerPanel);
        this.setPreferredSize(new Dimension(200, 100));
    }
    
    public TuneEditorPane getTuneEditorPane() {
        return this.m_tunePane;
    }
    
    public JScoreComponent getScore() {
        return this.m_score;
    }
}
