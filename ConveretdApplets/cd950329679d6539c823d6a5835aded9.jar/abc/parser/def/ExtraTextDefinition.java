// 
// Decompiled by Procyon v0.5.30
// 

package abc.parser.def;

import scanner.Transition;
import scanner.TokenType;
import scanner.State;
import abc.parser.AbcTokenType;
import scanner.AutomataDefinition;

public class ExtraTextDefinition extends AutomataDefinition
{
    private static char[] chars;
    
    public ExtraTextDefinition() {
        final State stateTEXT_CHAR = new State(AbcTokenType.TEXT, true);
        final Transition trans = new Transition(stateTEXT_CHAR, ExtraTextDefinition.chars);
        this.getStartingState().addTransition(trans);
        stateTEXT_CHAR.addTransition(new Transition(stateTEXT_CHAR, ExtraTextDefinition.chars));
    }
    
    static {
        ExtraTextDefinition.chars = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
    }
}
