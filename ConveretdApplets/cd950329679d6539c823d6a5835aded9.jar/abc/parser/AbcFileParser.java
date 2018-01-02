// 
// Decompiled by Procyon v0.5.30
// 

package abc.parser;

import abc.notation.Tune;
import scanner.TokenType;
import java.io.IOException;
import scanner.NoSuchTokenException;
import scanner.Set;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.File;
import scanner.InvalidCharacterEvent;
import scanner.TokenEvent;
import scanner.ScannerListenerInterface;

public class AbcFileParser extends AbcParserAbstract
{
    public AbcFileParser() {
        this.m_scanner.removeListener(this.m_scannerListener);
        this.m_scannerListener = new ScannerListenerInterface() {
            public void tokenGenerated(final TokenEvent evt) {
            }
            
            public void invalidCharacter(final InvalidCharacterEvent evt) {
                AbcFileParser.this.notifyListenersForInvalidCharacter(evt);
            }
            
            public void lineProcessed(final String line) {
                AbcFileParser.this.notifyListenersForLineProcessed(line);
            }
        };
        this.m_scanner.addListener(this.m_scannerListener);
    }
    
    public void addListener(final AbcFileParserListenerInterface listener) {
        super.addListener(listener);
    }
    
    public void removeListener(final AbcFileParserListenerInterface listener) {
        super.addListener(listener);
    }
    
    public void parseFile(final File abcFile) throws FileNotFoundException {
        this.parseFile(new BufferedReader(new InputStreamReader(new FileInputStream(abcFile))));
    }
    
    protected void notifyListenersForFileBegin() {
        for (int i = 0; i < this.m_listeners.size(); ++i) {
            this.m_listeners.elementAt(i).fileBegin();
        }
    }
    
    protected void notifyListenersForFileEnd() {
        for (int i = 0; i < this.m_listeners.size(); ++i) {
            this.m_listeners.elementAt(i).fileEnd();
        }
    }
    
    protected void notifyListenersForLineProcessed(final String line) {
        for (int i = 0; i < this.m_listeners.size(); ++i) {
            this.m_listeners.elementAt(i).lineProcessed(line);
        }
    }
    
    public void parseFile(final Reader abcCharStream) {
        try {
            final Set current = new Set();
            this.init();
            this.m_scanner.init(abcCharStream);
            this.notifyListenersForFileBegin();
            this.parseAbcFile(current);
            this.notifyListenersForFileEnd();
        }
        catch (NoSuchTokenException ex) {}
    }
    
    public void parseFileHeaders(final File abcFile) throws FileNotFoundException {
        this.parseFileHeaders(new BufferedReader(new InputStreamReader(new FileInputStream(abcFile))));
    }
    
    public void parseFileHeaders(final BufferedReader charStream) {
        Tune tune = null;
        try {
            this.m_scanner.init(charStream);
            this.notifyListenersForFileBegin();
            final Set current = AbcFileParser.FIRST_ABCTUNE.createUnion(AbcFileParser.FIRST_COMMENT).createUnion(AbcFileParser.FIRST_LINE_FEED);
            this.m_scanner.setFinaleStateAutomata(AutomataFactory.getAutomata(current.getTypes()));
            this.m_token = this.m_scanner.nextToken();
            this.m_tokenType = this.m_token.getType();
            while (this.m_token != null) {
                if (AbcFileParser.FIRST_ABCTUNE.contains(this.m_token.getType())) {
                    this.notifyListenersForTuneBegin();
                    tune = this.parseAbcHeader(current);
                    this.m_scanner.removeListener(this.m_scannerListener);
                    StringBuffer line = null;
                    while (this.m_token.getType() != AbcTokenType.LINE_FEED) {
                        line = new StringBuffer(this.m_scanner.getCurrentLine());
                        if (!AbcFileParser.FIRST_LINE_FEED.contains(this.m_token.getType())) {
                            try {
                                line.append(charStream.readLine());
                                line.append("\n");
                                this.notifyListenersForLineProcessed(new String(line));
                            }
                            catch (IOException e) {
                                e.printStackTrace();
                            }
                            this.m_scanner.init(charStream);
                            final Set newCurrent = AbcFileParser.FIRST_ABCTUNE.createUnion(AbcFileParser.FIRST_COMMENT).createUnion(AbcFileParser.FIRST_LINE_FEED).createUnion(this.FIRST_ABC_MUSIC);
                            this.m_scanner.setFinaleStateAutomata(AutomataFactory.getAutomata(newCurrent.getTypes()));
                            this.m_token = this.m_scanner.nextToken();
                            this.m_tokenType = this.m_token.getType();
                            if (!this.m_tokenType.equals(AbcTokenType.LINE_FEED)) {
                                continue;
                            }
                            this.notifyListenersForLineProcessed(new String("\n"));
                        }
                    }
                    this.notifyListenersForTuneEnd(tune);
                    this.m_scanner.addListener(this.m_scannerListener);
                }
                else if (AbcFileParser.FIRST_COMMENT.contains(this.m_token.getType())) {
                    this.parseComment(current);
                }
                else {
                    if (!AbcFileParser.FIRST_LINE_FEED.contains(this.m_token.getType())) {
                        continue;
                    }
                    this.accept(AbcTokenType.LINE_FEED, current, current);
                }
            }
        }
        catch (NoSuchTokenException e2) {
            this.notifyListenersForTuneEnd(tune);
        }
    }
}
