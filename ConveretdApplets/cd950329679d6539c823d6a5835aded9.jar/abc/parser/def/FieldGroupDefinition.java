// 
// Decompiled by Procyon v0.5.30
// 

package abc.parser.def;

import scanner.TokenType;
import scanner.Transition;
import scanner.State;
import abc.parser.AbcTokenType;
import scanner.AutomataDefinition;

public class FieldGroupDefinition extends AutomataDefinition
{
    public FieldGroupDefinition() {
        this.buildDefinition();
    }
    
    protected void buildDefinition() {
        final State startingState = new State(AbcTokenType.UNKNOWN, false);
        final Transition trans = new Transition(startingState, 'G');
        this.getStartingState().addTransition(trans);
        startingState.addTransition(new IsColonTransition(new State(AbcTokenType.FIELD_GROUP, true)));
    }
}
