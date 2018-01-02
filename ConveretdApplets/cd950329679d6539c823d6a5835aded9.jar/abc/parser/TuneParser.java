// 
// Decompiled by Procyon v0.5.30
// 

package abc.parser;

import scanner.NoSuchTokenException;
import abc.parser.def.DefinitionFactory;
import scanner.Set;
import java.io.Reader;
import java.io.StringReader;
import abc.notation.Tune;

public class TuneParser extends AbcParserAbstract
{
    public Tune parse(final String tune) {
        return this.parse(new StringReader(tune));
    }
    
    public Tune parse(final Reader charStream) {
        try {
            Set current = null;
            this.init();
            this.m_scanner.init(charStream);
            current = new Set().union(TuneParser.FIRST_ABCHEADER).union(TuneParser.FIRST_FIELD_KEY);
            this.m_automata.setDefinition(DefinitionFactory.getDefinition(current.getTypes()));
            this.m_scanner.setFinaleStateAutomata(this.m_automata);
            this.notifyListenersForTuneBegin();
            try {
                this.m_token = this.m_scanner.nextToken();
                this.m_tokenType = this.m_token.getType();
            }
            catch (NoSuchTokenException ex) {}
            this.parseAbcTune(current);
        }
        catch (NoSuchTokenException ex2) {}
        this.notifyListenersForTuneEnd(this.m_tune);
        return this.m_tune;
    }
    
    public Tune parseHeader(final String tune) {
        return this.parseHeader(new StringReader(tune));
    }
    
    public Tune parseHeader(final Reader charStream) {
        this.notifyListenersForTuneBegin();
        try {
            this.init();
            this.m_scanner.init(charStream);
            final Set current = new Set().union(TuneParser.FIRST_ABCHEADER).union(TuneParser.FIRST_FIELD_KEY);
            this.m_automata.setDefinition(DefinitionFactory.getDefinition(current.getTypes()));
            this.m_scanner.setFinaleStateAutomata(this.m_automata);
            try {
                this.m_token = this.m_scanner.nextToken();
                this.m_tokenType = this.m_token.getType();
            }
            catch (NoSuchTokenException ex) {}
            this.parseAbcHeader(current);
        }
        catch (NoSuchTokenException ex2) {}
        this.notifyListenersForTuneEnd(this.m_tune);
        return this.m_tune;
    }
    
    public void addListener(final TuneParserListenerInterface listener) {
        super.addListener(listener);
    }
    
    public void removeListener(final TuneParserListenerInterface listener) {
        super.removeListener(listener);
    }
}
