// 
// Decompiled by Procyon v0.5.30
// 

package scanner;

import java.io.IOException;
import java.io.StringReader;
import java.util.Vector;
import java.io.Reader;

public class Scanner
{
    protected Reader m_charStream;
    protected FinaleStateAutomata FSA;
    protected Vector m_listeners;
    protected CharStreamPosition m_previousPosition;
    protected CharStreamPosition m_position;
    protected StringBuffer m_currentLine;
    protected char[] m_currentChar;
    
    public Scanner(final String charStreamValue) {
        this(new StringReader(charStreamValue));
    }
    
    public Scanner(final Reader stream) {
        this();
        this.m_listeners = new Vector();
        this.init(stream);
    }
    
    public Scanner() {
        this.m_charStream = null;
        this.FSA = null;
        this.m_listeners = null;
        this.m_previousPosition = null;
        this.m_position = null;
        this.m_currentLine = null;
        this.m_currentChar = null;
        this.m_listeners = new Vector();
        this.m_currentChar = new char[1];
    }
    
    public void init(final String charStreamValue) {
        this.init(new StringReader(charStreamValue));
    }
    
    public void init(final Reader readerStream) {
        this.m_charStream = readerStream;
        this.FSA = null;
        this.m_currentLine = new StringBuffer();
        this.m_previousPosition = new CharStreamPosition();
        this.m_position = new CharStreamPosition(0, 1, -1);
    }
    
    public void addListener(final ScannerListenerInterface listener) {
        this.m_listeners.addElement(listener);
    }
    
    public void removeListener(final ScannerListenerInterface listener) {
        this.m_listeners.removeElement(listener);
    }
    
    public Token nextToken() throws NoSuchTokenException {
        String token = null;
        boolean endOfStreamReached = false;
        while (token == null && !endOfStreamReached) {
            try {
                this.m_charStream.mark(1);
                if (this.m_charStream.read(this.m_currentChar) == -1) {
                    endOfStreamReached = true;
                }
                else {
                    this.m_previousPosition.setPosition(this.m_position);
                    this.m_position.setColumn(this.m_position.getColumn() + 1);
                    this.m_position.setCharactersOffset(this.m_position.getCharactersOffset() + 1);
                    if (this.FSA.getTransitionFor(this.m_currentChar[0]) != null) {
                        this.FSA.sendChar(this.m_currentChar[0]);
                        this.m_currentLine.append(this.m_currentChar);
                        if (this.m_currentChar[0] != '\n') {
                            continue;
                        }
                        this.notifyListenersForLineScanned(new String(this.m_currentLine));
                        this.m_currentLine = new StringBuffer();
                    }
                    else if (this.FSA.getCurrentState().isTokenState()) {
                        try {
                            this.m_charStream.reset();
                            this.m_position.setPosition(this.m_previousPosition);
                        }
                        catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        token = this.FSA.getReceivedCharacters();
                    }
                    else {
                        this.m_currentLine.append(this.m_currentChar);
                        this.notifyListenersForInvalidCharacter(this.m_currentChar[0], (CharStreamPosition)this.m_position.clone());
                        this.FSA.initialize();
                        if (this.m_currentChar[0] != '\n') {
                            continue;
                        }
                        this.notifyListenersForLineScanned(new String(this.m_currentLine));
                        this.m_currentLine = new StringBuffer();
                        this.m_position.setColumn(0);
                        this.m_position.setLine(this.m_position.getLine() + 1);
                    }
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (endOfStreamReached) {
            if (!this.FSA.getCurrentState().isTokenState()) {
                this.FSA.initialize();
                throw new NoSuchTokenException();
            }
            token = this.FSA.getReceivedCharacters();
        }
        final TokenType currentState = this.FSA.getCurrentState().getType();
        this.FSA.initialize();
        final Token tok = new Token(token, currentState, new CharStreamPosition(this.m_position.getColumn() - (token.length() - 1), this.m_position.getLine(), this.m_position.getCharactersOffset() - (token.length() - 1)));
        this.notifyListenersForToken(tok);
        if (token.indexOf(10) != -1) {
            this.m_position.setColumn(0);
            this.m_position.setLine(this.m_position.getLine() + 1);
        }
        return tok;
    }
    
    public boolean hasNext() {
        int hasNext = -1;
        try {
            this.m_charStream.mark(0);
            hasNext = this.m_charStream.read(new char[1]);
            this.m_charStream.reset();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return hasNext != -1;
    }
    
    public FinaleStateAutomata getFinaleStateAutomata() {
        return this.FSA;
    }
    
    public void setFinaleStateAutomata(final FinaleStateAutomata fsa) {
        this.FSA = fsa;
    }
    
    public CharStreamPosition getPosition() {
        return this.m_position;
    }
    
    public String getCurrentLine() {
        return new String(this.m_currentLine);
    }
    
    protected void notifyListenersForToken(final Token token) {
        final TokenEvent evt = new TokenEvent(this, token);
        for (int i = 0; i < this.m_listeners.size(); ++i) {
            this.m_listeners.elementAt(i).tokenGenerated(evt);
        }
    }
    
    protected void notifyListenersForInvalidCharacter(final char character, final CharStreamPosition pos) {
        final InvalidCharacterEvent evt = new InvalidCharacterEvent(this, character, pos);
        for (int i = 0; i < this.m_listeners.size(); ++i) {
            this.m_listeners.elementAt(i).invalidCharacter(evt);
        }
    }
    
    protected void notifyListenersForLineScanned(final String line) {
        for (int i = 0; i < this.m_listeners.size(); ++i) {
            this.m_listeners.elementAt(i).lineProcessed(line);
        }
    }
}
