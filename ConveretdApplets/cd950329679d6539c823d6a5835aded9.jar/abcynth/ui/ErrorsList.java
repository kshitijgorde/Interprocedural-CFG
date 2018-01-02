// 
// Decompiled by Procyon v0.5.30
// 

package abcynth.ui;

import java.util.Vector;
import javax.swing.AbstractListModel;
import scanner.InvalidCharacterEvent;
import scanner.TokenEvent;
import abc.notation.Tune;
import abc.parser.InvalidTokenEvent;
import javax.swing.ListModel;
import abc.parser.TuneParser;
import abc.parser.TuneParserListenerInterface;
import javax.swing.JList;

public class ErrorsList extends JList implements TuneParserListenerInterface
{
    private TuneParser m_parser;
    
    public ErrorsList(final TuneParser parser) {
        super(new ErrorsListModel());
        this.m_parser = null;
        (this.m_parser = parser).addListener(this);
    }
    
    public void setParser(final TuneParser parser) {
        this.m_parser.removeListener(this);
        (this.m_parser = parser).addListener(this);
    }
    
    public void invalidToken(final InvalidTokenEvent evt) {
        try {
            String message = "Expecting " + evt.getExpectedTokenType().toString() + " at line " + evt.getPosition().getLine() + ", column " + evt.getPosition().getColumn();
            if (evt.getToken() != null) {
                message = message.concat(" instead of " + evt.getToken().getType());
                ((ErrorsListModel)this.getModel()).addError(new Error(message, evt.getToken().getPosition().getCharactersOffset(), evt.getToken().getPosition().getCharactersOffset() + evt.getToken().getValue().length()));
            }
            else {
                ((ErrorsListModel)this.getModel()).addError(new Error(message, evt.getPosition().getCharactersOffset(), evt.getPosition().getCharactersOffset() + 1));
            }
        }
        catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
    
    public void tuneBegin() {
        ((ErrorsListModel)this.getModel()).removeAllErrors();
    }
    
    public void tuneEnd(final Tune tune) {
    }
    
    public void validToken(final TokenEvent evt) {
    }
    
    public void invalidCharacter(final InvalidCharacterEvent evt) {
        final String message = "Invalid character '" + evt.getCharacter() + "' " + "at line " + evt.getPosition().getLine() + ", column " + evt.getPosition().getColumn();
        ((ErrorsListModel)this.getModel()).addError(new Error(message, evt.getPosition().getCharactersOffset(), evt.getPosition().getCharactersOffset() + 1));
    }
    
    private static class ErrorsListModel extends AbstractListModel
    {
        private Vector m_errors;
        
        public ErrorsListModel() {
            this.m_errors = null;
            this.m_errors = new Vector();
        }
        
        public Object getElementAt(final int index) {
            return this.m_errors.elementAt(index);
        }
        
        public int getSize() {
            return this.m_errors.size();
        }
        
        public void removeAllErrors() {
            this.m_errors.removeAllElements();
            this.fireContentsChanged(this, 0, 0);
        }
        
        public void addError(final Object o) {
            this.m_errors.addElement(o);
            this.fireContentsChanged(this, this.m_errors.size() - 1, this.m_errors.size() - 1);
        }
    }
    
    public class Error
    {
        private String m_message;
        private int m_beginOffset;
        private int m_endOffset;
        
        public Error(final String message, final int beginOffset, final int endOffset) {
            this.m_message = null;
            this.m_beginOffset = 0;
            this.m_endOffset = 0;
            this.m_message = message;
            this.m_beginOffset = beginOffset;
            this.m_endOffset = endOffset;
        }
        
        public int getBeginOffset() {
            return this.m_beginOffset;
        }
        
        public int getEndoffset() {
            return this.m_endOffset;
        }
        
        public String getMessage() {
            return this.m_message;
        }
        
        public String toString() {
            return this.m_message;
        }
    }
}
