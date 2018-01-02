// 
// Decompiled by Procyon v0.5.30
// 

package abc.parser.def;

import scanner.TokenType;
import scanner.Transition;
import scanner.State;
import abc.parser.AbcTokenType;
import scanner.AutomataDefinition;

public class FieldDiscographyDefinition extends AutomataDefinition
{
    public FieldDiscographyDefinition() {
        this.buildDefinition();
    }
    
    protected void buildDefinition() {
        final State areaState = new State(AbcTokenType.UNKNOWN, false);
        final Transition trans = new Transition(areaState, 'D');
        this.getStartingState().addTransition(trans);
        areaState.addTransition(new IsColonTransition(new State(AbcTokenType.FIELD_DISCOGRAPHY, true)));
    }
}
