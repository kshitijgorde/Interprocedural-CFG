// 
// Decompiled by Procyon v0.5.30
// 

package abc.parser.def;

import scanner.Transition;
import scanner.TokenType;
import scanner.State;
import abc.parser.AbcTokenType;
import scanner.AutomataDefinition;

public class UserDefinedDefinition extends AutomataDefinition
{
    private static char[] chars;
    
    public UserDefinedDefinition() {
        final State state = new State(AbcTokenType.USER_DEFINED, true);
        final Transition trans = new Transition(state, UserDefinedDefinition.chars);
        this.getStartingState().addTransition(trans);
    }
    
    static {
        UserDefinedDefinition.chars = new char[] { 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
    }
}
