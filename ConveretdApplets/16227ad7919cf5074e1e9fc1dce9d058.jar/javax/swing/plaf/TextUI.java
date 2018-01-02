// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.plaf;

import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.text.View;
import javax.swing.text.BadLocationException;
import javax.swing.text.EditorKit;
import javax.swing.text.Position;
import javax.swing.text.JTextComponent;

public abstract class TextUI extends ComponentUI
{
    public abstract void damageRange(final JTextComponent p0, final int p1, final int p2);
    
    public abstract void damageRange(final JTextComponent p0, final int p1, final int p2, final Position.Bias p3, final Position.Bias p4);
    
    public abstract EditorKit getEditorKit(final JTextComponent p0);
    
    public abstract int getNextVisualPositionFrom(final JTextComponent p0, final int p1, final Position.Bias p2, final int p3, final Position.Bias[] p4) throws BadLocationException;
    
    public abstract View getRootView(final JTextComponent p0);
    
    public abstract Rectangle modelToView(final JTextComponent p0, final int p1) throws BadLocationException;
    
    public abstract Rectangle modelToView(final JTextComponent p0, final int p1, final Position.Bias p2) throws BadLocationException;
    
    public abstract int viewToModel(final JTextComponent p0, final Point p1);
    
    public abstract int viewToModel(final JTextComponent p0, final Point p1, final Position.Bias[] p2);
}
