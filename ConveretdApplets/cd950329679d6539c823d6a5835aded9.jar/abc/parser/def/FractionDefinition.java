// 
// Decompiled by Procyon v0.5.30
// 

package abc.parser.def;

import scanner.Transition;
import scanner.TokenType;
import scanner.State;
import abc.parser.AbcTokenType;
import scanner.AutomataDefinition;

public class FractionDefinition extends AutomataDefinition
{
    public FractionDefinition() {
        final State state = new State(AbcTokenType.FRACTION, true);
        final char[] chars = { '/' };
        final Transition trans = new Transition(state, chars);
        this.getStartingState().addTransition(trans);
    }
}
