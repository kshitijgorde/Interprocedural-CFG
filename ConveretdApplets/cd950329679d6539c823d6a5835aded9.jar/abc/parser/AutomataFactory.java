// 
// Decompiled by Procyon v0.5.30
// 

package abc.parser;

import abc.parser.def.DefinitionFactory;
import scanner.FinaleStateAutomata;
import scanner.TokenType;
import java.util.Vector;

class AutomataFactory
{
    public static Vector m_allPreviouslyCreatedAutomatas;
    
    public static FinaleStateAutomata getAutomata(final TokenType abcTokenType) {
        return new FinaleStateAutomata(DefinitionFactory.getDefinition(abcTokenType));
    }
    
    public static FinaleStateAutomata getAutomata(final TokenType[] tokenTypes) {
        return new FinaleStateAutomata(DefinitionFactory.getDefinition(tokenTypes));
    }
    
    static {
        AutomataFactory.m_allPreviouslyCreatedAutomatas = new Vector();
    }
}
