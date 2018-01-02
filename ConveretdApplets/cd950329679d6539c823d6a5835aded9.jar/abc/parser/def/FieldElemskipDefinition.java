// 
// Decompiled by Procyon v0.5.30
// 

package abc.parser.def;

import scanner.Transition;
import scanner.State;
import abc.parser.AbcTokenType;
import scanner.AutomataDefinition;

public class FieldElemskipDefinition extends AutomataDefinition
{
    public FieldElemskipDefinition() {
        this.buildDefinition();
    }
    
    protected void buildDefinition() {
        final State startingState = new State(AbcTokenType.UNKNOWN, false);
        final Transition trans = new Transition(startingState, 'E');
        this.getStartingState().addTransition(trans);
    }
}
