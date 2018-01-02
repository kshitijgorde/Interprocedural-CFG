// 
// Decompiled by Procyon v0.5.30
// 

package teletext;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.Graphics;

public interface TeletextToolInterface
{
    void install(final TeletextPageCanvas p0);
    
    void uninstall();
    
    boolean isDisposable();
    
    void paint(final Graphics p0);
    
    TeletextPage getPage();
    
    TeletextPageCanvas getPageCanvas();
    
    void implementMousePressed(final MouseEvent p0);
    
    void implementMouseReleased(final MouseEvent p0);
    
    void implementMouseClicked(final MouseEvent p0);
    
    void implementMouseEntered(final MouseEvent p0);
    
    void implementMouseExited(final MouseEvent p0);
    
    void implementMouseDragged(final MouseEvent p0);
    
    void implementMouseMoved(final MouseEvent p0);
    
    boolean implementKeyPressed(final KeyEvent p0);
    
    boolean implementKeyReleased(final KeyEvent p0);
    
    boolean implementKeyTyped(final KeyEvent p0);
}
