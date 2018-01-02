// 
// Decompiled by Procyon v0.5.30
// 

package abc.parser.def;

import scanner.Transition;
import scanner.TokenType;
import scanner.State;
import abc.parser.AbcTokenType;
import scanner.AutomataDefinition;

public class MultiNoteEndDefinition extends AutomataDefinition
{
    public MultiNoteEndDefinition() {
        final State state = new State(AbcTokenType.MULTI_NOTE_END, true);
        this.getStartingState().addTransition(new Transition(state, ']'));
    }
}
