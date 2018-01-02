// 
// Decompiled by Procyon v0.5.30
// 

package abc.parser.def;

import scanner.TokenType;
import scanner.Transition;
import scanner.State;
import abc.parser.AbcTokenType;
import scanner.AutomataDefinition;

public class KeyHPDefinition extends AutomataDefinition
{
    public KeyHPDefinition() {
        final State state = new State(AbcTokenType.UNKNOWN, false);
        Transition trans = new Transition(state, 'H');
        this.getStartingState().addTransition(trans);
        final State state2 = new State(AbcTokenType.KEY_HP, true);
        final char[] chars = { 'p', 'P' };
        trans = new Transition(state2, chars);
        state.addTransition(trans);
    }
}
