// 
// Decompiled by Procyon v0.5.30
// 

package abc.parser.def;

import scanner.Transition;
import scanner.TokenType;
import scanner.State;
import abc.parser.AbcTokenType;
import scanner.AutomataDefinition;

public class TextDefinition extends AutomataDefinition
{
    private static char[] chars;
    
    public TextDefinition() {
        final State stateTEXT_CHAR = new State(AbcTokenType.TEXT, true);
        final Transition trans = new Transition(stateTEXT_CHAR, TextDefinition.chars);
        this.getStartingState().addTransition(trans);
        stateTEXT_CHAR.addTransition(new Transition(stateTEXT_CHAR, TextDefinition.chars));
    }
    
    static {
        TextDefinition.chars = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ' ', '\t', '\"', '!', '#', '$', '&', '\'', '(', ')', '*', '+', ',', '-', '.', '/', ':', ';', '<', '=', '>', '?', '@', '[', '\\', ']', '^', '_', '`', '{', '|', '}', '~', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '\u00e0', '\u00e1', '\u00e2', '\u00e3', '\u00e9', '\u00ea', '\u00ed', '\u00f3', '\u00f4', '\u00f5', '\u00fa', '\u00e7', '\u00c0', '\u00c1', '\u00c2', '\u00c3', '\u00c9', '\u00ca', '\u00cd', '\u00d3', '\u00d4', '\u00d5', '\u00da', '\u00c7', '\u00e8', '\u00f9' };
    }
}
