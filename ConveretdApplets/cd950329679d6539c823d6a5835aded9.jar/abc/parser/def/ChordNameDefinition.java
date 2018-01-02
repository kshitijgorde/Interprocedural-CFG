// 
// Decompiled by Procyon v0.5.30
// 

package abc.parser.def;

import scanner.Transition;
import scanner.TokenType;
import scanner.State;
import abc.parser.AbcTokenType;
import scanner.AutomataDefinition;

public class ChordNameDefinition extends AutomataDefinition
{
    private static char[] chars;
    
    public ChordNameDefinition() {
        final State stateTEXT_CHAR = new State(AbcTokenType.CHORD_NAME, true);
        final Transition trans = new Transition(stateTEXT_CHAR, ChordNameDefinition.chars);
        this.getStartingState().addTransition(trans);
        stateTEXT_CHAR.addTransition(new Transition(stateTEXT_CHAR, ChordNameDefinition.chars));
    }
    
    static {
        ChordNameDefinition.chars = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ' ', '\t', '!', '#', '$', '&', '\'', '(', ')', '*', '+', ',', '-', '.', '/', ':', ';', '<', '=', '>', '?', '@', '[', '\\', ']', '^', '_', '`', '{', '|', '}', '~', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
    }
}
