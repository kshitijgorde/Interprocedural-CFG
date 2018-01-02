// 
// Decompiled by Procyon v0.5.30
// 

package abc.parser.def;

import scanner.Transition;
import scanner.TokenType;
import scanner.State;
import abc.parser.AbcTokenType;
import scanner.AutomataDefinition;

public class SlurEndDefinition extends AutomataDefinition
{
    public SlurEndDefinition() {
        final State state = new State(AbcTokenType.END_SLUR, true);
        this.getStartingState().addTransition(new Transition(state, ')'));
    }
}
