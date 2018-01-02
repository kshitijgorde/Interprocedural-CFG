// 
// Decompiled by Procyon v0.5.30
// 

package scanner;

public class IsAlphaTransition extends Transition
{
    private static char[] chars;
    
    public IsAlphaTransition(final State state) {
        super(state, IsAlphaTransition.chars);
    }
    
    static {
        IsAlphaTransition.chars = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 'y', 'u', 'v', 'w', 'x', 'y', 'z' };
    }
}
