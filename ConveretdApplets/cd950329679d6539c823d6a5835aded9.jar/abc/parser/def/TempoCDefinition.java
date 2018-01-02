// 
// Decompiled by Procyon v0.5.30
// 

package abc.parser.def;

import scanner.Transition;
import scanner.TokenType;
import scanner.State;
import abc.parser.AbcTokenType;
import scanner.AutomataDefinition;

public class TempoCDefinition extends AutomataDefinition
{
    public TempoCDefinition() {
        final State state = new State(AbcTokenType.C_TEMPO, true);
        final Transition trans = new Transition(state, 'C');
        this.getStartingState().addTransition(trans);
    }
}
