// 
// Decompiled by Procyon v0.5.30
// 

package abc.parser.def;

import scanner.Transition;
import scanner.TokenType;
import scanner.State;
import abc.parser.AbcTokenType;
import scanner.AutomataDefinition;

public class GracingDefinition extends AutomataDefinition
{
    public GracingDefinition() {
        final State state = new State(AbcTokenType.GRACING, true);
        final char[] chars = { '~', '.', 'v', 'u' };
        this.getStartingState().addTransition(new Transition(state, chars));
    }
}
