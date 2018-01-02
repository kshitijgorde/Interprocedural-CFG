// 
// Decompiled by Procyon v0.5.30
// 

package abc.parser;

public interface AbcFileParserListenerInterface extends TuneParserListenerInterface
{
    void fileBegin();
    
    void lineProcessed(final String p0);
    
    void fileEnd();
}
