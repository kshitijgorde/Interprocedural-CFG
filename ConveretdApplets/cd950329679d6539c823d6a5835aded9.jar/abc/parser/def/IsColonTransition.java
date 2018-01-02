// 
// Decompiled by Procyon v0.5.30
// 

package abc.parser.def;

import scanner.State;
import scanner.Transition;

public class IsColonTransition extends Transition
{
    private static char[] chars;
    
    public IsColonTransition(final State state) {
        super(state, IsColonTransition.chars);
    }
    
    static {
        IsColonTransition.chars = new char[] { ':' };
    }
}
