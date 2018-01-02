// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.text;

import javax.swing.Action;
import javax.swing.KeyStroke;

public interface Keymap
{
    void addActionForKeyStroke(final KeyStroke p0, final Action p1);
    
    Action getAction(final KeyStroke p0);
    
    Action[] getBoundActions();
    
    KeyStroke[] getBoundKeyStrokes();
    
    Action getDefaultAction();
    
    KeyStroke[] getKeyStrokesForAction(final Action p0);
    
    String getName();
    
    Keymap getResolveParent();
    
    boolean isLocallyDefined(final KeyStroke p0);
    
    void removeBindings();
    
    void removeKeyStrokeBinding(final KeyStroke p0);
    
    void setDefaultAction(final Action p0);
    
    void setResolveParent(final Keymap p0);
}
