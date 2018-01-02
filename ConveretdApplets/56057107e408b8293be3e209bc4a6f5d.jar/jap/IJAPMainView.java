// 
// Decompiled by Procyon v0.5.30
// 

package jap;

import anon.AnonServiceEventListener;
import gui.IStatusLine;

public interface IJAPMainView extends JAPObserver, IStatusLine, AnonServiceEventListener
{
    void create(final boolean p0);
    
    void setVisible(final boolean p0);
    
    void registerViewIconified(final JAPViewIconified p0);
    
    JAPViewIconified getViewIconified();
    
    void showConfigDialog();
    
    void showConfigDialog(final String p0, final Object p1);
    
    void doClickOnCascadeChooser();
    
    void disableSetAnonMode();
    
    void onUpdateValues();
}
