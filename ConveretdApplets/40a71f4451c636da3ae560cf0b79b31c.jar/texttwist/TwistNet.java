// 
// Decompiled by Procyon v0.5.30
// 

package texttwist;

import java.util.Vector;
import inknet.User;

public interface TwistNet
{
    void initialize(final User p0);
    
    Vector getWordList(final String p0);
    
    void end();
    
    String getRandomLetters();
}
