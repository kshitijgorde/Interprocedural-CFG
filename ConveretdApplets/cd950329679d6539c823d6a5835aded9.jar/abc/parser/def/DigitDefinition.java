// 
// Decompiled by Procyon v0.5.30
// 

package abc.parser.def;

import scanner.Transition;
import scanner.IsDigitTransition;
import scanner.TokenType;
import scanner.State;
import abc.parser.AbcTokenType;
import scanner.AutomataDefinition;

public class DigitDefinition extends AutomataDefinition
{
    public DigitDefinition() {
        final State state = new State(AbcTokenType.DIGIT, true);
        this.getStartingState().addTransition(new IsDigitTransition(state));
    }
}
