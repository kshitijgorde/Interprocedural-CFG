// 
// Decompiled by Procyon v0.5.30
// 

package abc.parser.def;

import scanner.Transition;
import scanner.TokenType;
import scanner.State;
import abc.parser.AbcTokenType;
import scanner.AutomataDefinition;

public class ComaDefinition extends AutomataDefinition
{
    public ComaDefinition() {
        final State state = new State(AbcTokenType.COMA, true);
        final char[] chars = { ':' };
        final Transition trans = new Transition(state, chars);
        this.getStartingState().addTransition(trans);
    }
}
