// 
// Decompiled by Procyon v0.5.30
// 

package scanner;

import java.io.Reader;
import java.io.BufferedReader;

class Scanner2 extends Scanner
{
    private String m_theLine;
    private int m_theLineIndex;
    private boolean endOfStreamReached;
    
    public Scanner2(final BufferedReader stream) {
        super(stream);
        this.m_theLine = null;
        this.m_theLineIndex = -1;
        this.endOfStreamReached = false;
    }
    
    public Scanner2() {
        this.m_theLine = null;
        this.m_theLineIndex = -1;
        this.endOfStreamReached = false;
    }
    
    public Token nextToken() throws NoSuchTokenException {
        String token = null;
        while (token == null && !this.endOfStreamReached) {
            try {
                this.m_currentChar[0] = this.nextChar();
                if (this.m_currentChar[0] == '§') {
                    this.endOfStreamReached = true;
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
                        this.previousChar();
                        this.m_position.setPosition(this.m_previousPosition);
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
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (this.endOfStreamReached) {
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
    
    public char nextChar() {
        char c = '§';
        try {
            if (this.m_theLine == null) {
                this.m_theLine = ((BufferedReader)this.m_charStream).readLine();
            }
            if (this.m_theLineIndex == -2) {
                ++this.m_theLineIndex;
                c = '\n';
            }
            else if (this.m_theLineIndex == this.m_theLine.length() - 1) {
                this.m_theLine = ((BufferedReader)this.m_charStream).readLine();
                if (this.m_theLine != null) {
                    this.m_theLineIndex = -1;
                    c = '\n';
                }
            }
            else if (this.m_theLine != null) {
                ++this.m_theLineIndex;
                c = this.m_theLine.charAt(this.m_theLineIndex);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if (this.m_theLine == null && c == '§') {
            this.endOfStreamReached = true;
        }
        return c;
    }
    
    public void previousChar() {
        if (this.m_theLine == null || this.m_theLineIndex == -2) {
            throw new IllegalStateException("No previous character : nothing has been started !");
        }
        --this.m_theLineIndex;
    }
    
    public boolean hasNext() {
        return !this.endOfStreamReached;
    }
}
