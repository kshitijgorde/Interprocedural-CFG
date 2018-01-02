// 
// Decompiled by Procyon v0.5.30
// 

package check;

import abc.notation.Tune;
import scanner.TokenEvent;
import java.awt.Component;
import abc.parser.InvalidTokenEvent;
import scanner.InvalidCharacterEvent;
import java.util.EventObject;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.util.Vector;
import java.awt.List;
import abc.ui.awt.TuneEditorArea;
import abc.parser.TuneParserListenerInterface;
import java.applet.Applet;

public class AbcCheck extends Applet implements TuneParserListenerInterface
{
    private TuneEditorArea m_textArea;
    private List m_errorList;
    private Vector m_errors;
    
    public AbcCheck() {
        this.m_textArea = null;
        this.m_errorList = null;
        this.m_errors = null;
        this.setLayout(new BorderLayout());
        this.m_textArea = new TuneEditorArea();
        this.m_textArea.getParser().addListener(this);
        this.m_textArea.setFont(new Font("Courier", 0, 15));
        (this.m_errorList = new List(10)).setForeground(Color.red);
        this.m_errorList.setFont(new Font("Courier", 1, 15));
        this.m_errorList.add("Errors are displayed here.");
        this.m_errorList.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent e) {
                if (e.getStateChange() == 1 && e.getItem() instanceof Integer) {
                    final EventObject evt = AbcCheck.this.m_errors.elementAt((int)e.getItem());
                    int beginOffset = 0;
                    int length = 0;
                    if (evt instanceof InvalidCharacterEvent) {
                        beginOffset = ((InvalidCharacterEvent)evt).getPosition().getCharactersOffset();
                        length = 1;
                    }
                    else if (evt instanceof InvalidTokenEvent) {
                        beginOffset = ((InvalidTokenEvent)evt).getPosition().getCharactersOffset();
                        if (((InvalidTokenEvent)evt).getToken() == null) {
                            length = 1;
                        }
                        else {
                            length = ((InvalidTokenEvent)evt).getToken().getValue().length();
                        }
                    }
                    AbcCheck.this.m_textArea.select(beginOffset, beginOffset + length);
                }
            }
        });
        this.m_errors = new Vector();
        this.add(this.m_textArea, "Center");
        this.add(this.m_errorList, "South");
    }
    
    public void tuneBegin() {
        System.out.println("Beginning to parse tune");
        this.m_errors.removeAllElements();
    }
    
    public void invalidToken(final InvalidTokenEvent evt) {
        this.m_errors.addElement(evt);
    }
    
    public void validToken(final TokenEvent event) {
    }
    
    public void invalidCharacter(final InvalidCharacterEvent event) {
        this.m_errors.addElement(event);
    }
    
    public void tuneEnd(final Tune tune) {
        this.m_errorList.removeAll();
        if (this.m_errors.isEmpty()) {
            this.m_errorList.add("No error detected !");
        }
        else {
            for (int i = 0; i < this.m_errors.size(); ++i) {
                final EventObject ev = this.m_errors.elementAt(i);
                String message = null;
                if (ev instanceof InvalidCharacterEvent) {
                    final InvalidCharacterEvent evt = (InvalidCharacterEvent)ev;
                    message = "Invalid Character at line " + evt.getPosition().getLine() + ", column " + evt.getPosition().getColumn();
                }
                else if (ev instanceof InvalidTokenEvent) {
                    final InvalidTokenEvent evt2 = (InvalidTokenEvent)ev;
                    message = ev.toString();
                    if (evt2.getToken() != null) {
                        message = "Expecting " + evt2.getExpectedTokenType().toString() + " at line " + evt2.getToken().getPosition().getLine() + ", column " + evt2.getToken().getPosition().getColumn() + " instead of " + evt2.getToken().getType().toString();
                    }
                    else {
                        message = "Expecting " + evt2.getExpectedTokenType().toString();
                    }
                }
                this.m_errorList.add(message);
            }
        }
        this.m_errorList.repaint();
        this.repaint();
    }
}
