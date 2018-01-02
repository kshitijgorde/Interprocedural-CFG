// 
// Decompiled by Procyon v0.5.30
// 

package teletext;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseAdapter;

public abstract class TeletextTool implements TeletextToolInterface
{
    TeletextPageCanvas pageCanvas;
    MouseAdapter mouseAdapter;
    MouseMotionAdapter mouseMotionAdapter;
    
    public TeletextTool() {
        this.mouseAdapter = this.createMouseAdapter();
        this.mouseMotionAdapter = this.createMouseMotionAdapter();
    }
    
    public void install(final TeletextPageCanvas pageCanvas) {
        (this.pageCanvas = pageCanvas).addMouseListener(this.mouseAdapter);
        this.pageCanvas.addMouseMotionListener(this.mouseMotionAdapter);
    }
    
    public void uninstall() {
        this.pageCanvas.removeMouseListener(this.mouseAdapter);
        this.pageCanvas.removeMouseMotionListener(this.mouseMotionAdapter);
        this.pageCanvas = null;
    }
    
    public boolean isDisposable() {
        return false;
    }
    
    public abstract void paint(final Graphics p0);
    
    public TeletextPage getPage() {
        return this.pageCanvas.getPage();
    }
    
    public TeletextPageCanvas getPageCanvas() {
        return this.pageCanvas;
    }
    
    MouseAdapter createMouseAdapter() {
        return new MouseAdapter() {
            public void mousePressed(final MouseEvent mouseEvent) {
                TeletextTool.this.implementMousePressed(mouseEvent);
            }
            
            public void mouseReleased(final MouseEvent mouseEvent) {
                TeletextTool.this.implementMouseReleased(mouseEvent);
            }
            
            public void mouseClicked(final MouseEvent mouseEvent) {
                TeletextTool.this.implementMouseClicked(mouseEvent);
            }
            
            public void mouseEntered(final MouseEvent mouseEvent) {
                TeletextTool.this.implementMouseEntered(mouseEvent);
            }
            
            public void mouseExited(final MouseEvent mouseEvent) {
                TeletextTool.this.implementMouseExited(mouseEvent);
            }
        };
    }
    
    MouseMotionAdapter createMouseMotionAdapter() {
        return new MouseMotionAdapter() {
            public void mouseDragged(final MouseEvent mouseEvent) {
                TeletextTool.this.implementMouseDragged(mouseEvent);
            }
            
            public void mouseMoved(final MouseEvent mouseEvent) {
                TeletextTool.this.implementMouseMoved(mouseEvent);
            }
        };
    }
    
    public void implementMousePressed(final MouseEvent mouseEvent) {
    }
    
    public void implementMouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void implementMouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void implementMouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void implementMouseExited(final MouseEvent mouseEvent) {
    }
    
    public void implementMouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void implementMouseMoved(final MouseEvent mouseEvent) {
    }
    
    public boolean implementKeyPressed(final KeyEvent keyEvent) {
        return false;
    }
    
    public boolean implementKeyReleased(final KeyEvent keyEvent) {
        return false;
    }
    
    public boolean implementKeyTyped(final KeyEvent keyEvent) {
        return false;
    }
    
    public boolean invokeMethod(final String s) {
        return false;
    }
    
    public void handleEditorEvent(final TeletextPageCanvasEvent teletextPageCanvasEvent) {
    }
    
    public void renderCanvas() {
        this.getPageCanvas().render();
    }
    
    public void repaintCanvas() {
        this.getPageCanvas().repaint();
    }
    
    void drawMessage(final Graphics graphics, final int n, final int n2, final String s) {
        graphics.setColor(Color.black);
        for (int i = -1; i < 2; ++i) {
            for (int j = -1; j < 2; ++j) {
                graphics.drawString(s, n + i, n2 + j);
            }
        }
        graphics.setColor(Color.white);
        graphics.drawString(s, n, n2);
    }
}
