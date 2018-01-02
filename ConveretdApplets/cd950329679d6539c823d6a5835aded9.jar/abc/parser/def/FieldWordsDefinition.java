// 
// Decompiled by Procyon v0.5.30
// 

package abc.parser.def;

import scanner.TokenType;
import scanner.Transition;
import scanner.State;
import abc.parser.AbcTokenType;
import scanner.AutomataDefinition;

public class FieldWordsDefinition extends AutomataDefinition
{
    public FieldWordsDefinition() {
        final State state = new State(AbcTokenType.UNKNOWN, false);
        final Transition trans = new Transition(state, 'W');
        this.getStartingState().addTransition(trans);
        state.addTransition(new IsColonTransition(new State(AbcTokenType.FIELD_WORDS, true)));
    }
}