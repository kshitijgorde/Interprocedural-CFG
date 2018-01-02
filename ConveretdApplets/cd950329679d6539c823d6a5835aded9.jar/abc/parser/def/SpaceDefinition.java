// 
// Decompiled by Procyon v0.5.30
// 

package abc.parser.def;

import scanner.Transition;
import scanner.TokenType;
import scanner.State;
import abc.parser.AbcTokenType;
import scanner.AutomataDefinition;

public class SpaceDefinition extends AutomataDefinition
{
    public SpaceDefinition() {
        final State state = new State(AbcTokenType.SPACE, true);
        final char[] chars = { ' ', '\t' };
        final Transition trans = new Transition(state, chars);
        this.getStartingState().addTransition(trans);
    }
}
