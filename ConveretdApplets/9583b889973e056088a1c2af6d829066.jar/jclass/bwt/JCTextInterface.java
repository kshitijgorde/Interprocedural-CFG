// 
// Decompiled by Procyon v0.5.30
// 

package jclass.bwt;

import java.awt.Color;
import java.awt.Dimension;

public interface JCTextInterface
{
    void setText(final String p0);
    
    String getText();
    
    String getSelectedText();
    
    boolean isEditable();
    
    boolean getEditable();
    
    void setEditable(final boolean p0);
    
    int getSelectionStart();
    
    int getSelectionEnd();
    
    void setSelectionStart(final int p0);
    
    void setSelectionEnd(final int p0);
    
    void select(final int p0, final int p1);
    
    void selectAll();
    
    void insert(final String p0, final int p1);
    
    void append(final String p0);
    
    void replaceRange(final String p0, final int p1, final int p2);
    
    int getColumns();
    
    void setColumns(final int p0);
    
    void setAlignment(final int p0);
    
    void setMaximumLength(final int p0);
    
    void setStringCase(final int p0);
    
    void beep();
    
    boolean getChanged();
    
    Dimension getMinimumSize(final int p0);
    
    int[] getSelectionList();
    
    void setSelectionList(final int[] p0);
    
    Color getSelectedBackground();
    
    void setSelectedBackground(final Color p0);
    
    Color getSelectedForeground();
    
    void setSelectedForeground(final Color p0);
    
    int pointToPosition(final int p0, final int p1);
    
    void showPosition(final int p0);
    
    int getCursorPosition();
    
    void setCursorPosition(final int p0);
    
    void setOverstrike(final boolean p0);
    
    boolean getOverstrike();
    
    int getLastPosition();
    
    boolean getShowCursorPosition();
    
    void setShowCursorPosition(final boolean p0);
    
    void addTextListener(final JCTextListener p0);
    
    void addTextCursorListener(final JCTextCursorListener p0);
}
