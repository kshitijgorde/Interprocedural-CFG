// 
// Decompiled by Procyon v0.5.30
// 

package abc.parser.def;

import scanner.Transition;
import scanner.TokenType;
import scanner.State;
import abc.parser.AbcTokenType;
import scanner.AutomataDefinition;

public class GracingEndDefinition extends AutomataDefinition
{
    public GracingEndDefinition() {
        final State state = new State(AbcTokenType.GRACING_END, true);
        this.getStartingState().addTransition(new Transition(state, '}'));
    }
}
