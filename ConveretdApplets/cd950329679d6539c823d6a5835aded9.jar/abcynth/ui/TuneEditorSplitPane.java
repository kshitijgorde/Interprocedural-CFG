// 
// Decompiled by Procyon v0.5.30
// 

package abcynth.ui;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.Component;
import javax.swing.JScrollPane;
import javax.swing.JList;
import abc.ui.swing.TuneEditorPane;
import javax.swing.JSplitPane;

public class TuneEditorSplitPane extends JSplitPane
{
    private TuneEditorPane m_tunePane;
    private JList m_errorsList;
    
    public TuneEditorSplitPane() {
        super(0);
        this.m_tunePane = null;
        this.m_errorsList = null;
        this.setOneTouchExpandable(true);
        this.m_tunePane = new TuneEditorPane();
        this.m_errorsList = new ErrorsList(this.m_tunePane.getParser());
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
        this.setBottomComponent(new JScrollPane(this.m_errorsList, 20, 30));
    }
    
    public TuneEditorPane getTuneEditorPane() {
        return this.m_tunePane;
    }
}
