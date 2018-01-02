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

public class NumberDefinition extends AutomataDefinition
{
    public NumberDefinition() {
        this.buildDefinition();
    }
    
    protected void buildDefinition() {
        final State stateNUMBER = new State(AbcTokenType.NUMBER, true);
        Transition trans = new IsDigitTransition(stateNUMBER);
        this.getStartingState().addTransition(trans);
        trans = new IsDigitTransition(stateNUMBER);
        stateNUMBER.addTransition(trans);
    }
}
