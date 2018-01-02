// 
// Decompiled by Procyon v0.5.30
// 

package irc.gui;

import irc.Source;

public interface GUISource
{
    void setFieldText(final String p0);
    
    String getFieldText();
    
    void validateText();
    
    Source getSource();
    
    void requestFocus();
    
    String getTitle();
}
