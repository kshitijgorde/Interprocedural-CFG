// 
// Decompiled by Procyon v0.5.30
// 

package abcynth;

import abc.notation.Tune;
import scanner.InvalidCharacterEvent;
import scanner.TokenEvent;
import abc.parser.InvalidTokenEvent;
import java.awt.Component;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import abc.parser.AbcFileParserListenerInterface;
import javax.swing.JFrame;

public class LogFrame extends JFrame implements AbcFileParserListenerInterface
{
    private JTextArea m_errorsArea;
    
    public LogFrame() {
        super("Logs...");
        this.m_errorsArea = null;
        this.m_errorsArea = new JTextArea();
        final JScrollPane logPane = new JScrollPane(this.m_errorsArea, 20, 30);
        this.getContentPane().add(logPane);
    }
    
    public JTextArea getErrorsArea() {
        return this.m_errorsArea;
    }
    
    public void tuneBegin() {
    }
    
    public void invalidToken(final InvalidTokenEvent event) {
        this.m_errorsArea.append("Invalid token " + event.getToken().getValue() + " at " + event.getPosition().toString() + "\n");
    }
    
    public void validToken(final TokenEvent event) {
    }
    
    public void invalidCharacter(final InvalidCharacterEvent event) {
        this.m_errorsArea.append("Invalid character " + event.getCharacter() + " at " + event.getPosition().toString() + "\n");
    }
    
    public void tuneEnd(final Tune tune) {
    }
    
    public void fileBegin() {
        this.m_errorsArea.setText("");
    }
    
    public void lineProcessed(final String line) {
    }
    
    public void fileEnd() {
    }
}
