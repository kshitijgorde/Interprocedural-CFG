// 
// Decompiled by Procyon v0.5.30
// 

package scanner;

public class IsDigitTransition extends Transition
{
    private static char[] chars;
    
    public IsDigitTransition(final State state) {
        super(state, IsDigitTransition.chars);
    }
    
    static {
        IsDigitTransition.chars = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
    }
}
