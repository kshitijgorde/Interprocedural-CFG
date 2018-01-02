// 
// Decompiled by Procyon v0.5.30
// 

package abc.parser.def;

import scanner.Transition;
import scanner.TokenType;
import scanner.State;
import abc.parser.AbcTokenType;
import scanner.AutomataDefinition;

public class BaseNoteDefinition extends AutomataDefinition
{
    private static char[] chars;
    
    public BaseNoteDefinition() {
        this.buildDefinition();
    }
    
    protected void buildDefinition() {
        final State state = new State(AbcTokenType.BASE_NOTE, true);
        final Transition trans = new Transition(state, BaseNoteDefinition.chars);
        this.getStartingState().addTransition(trans);
    }
    
    static {
        BaseNoteDefinition.chars = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'a', 'b', 'c', 'd', 'e', 'f', 'g' };
    }
}
