// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.editor;

import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.event.MouseEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.util.Locale;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Canvas;

class CRTEdit extends Canvas
{
    private CRTEditor cEditor;
    private CParaStyleMgr cStyleMgr;
    private Image cDBImage;
    private Graphics cDBGraphics;
    private Locale cUILocale;
    private Locale cActiveLocale;
    private String cDefaultStyleName;
    private int cX;
    private int cY;
    private int cWidth;
    private int cHeight;
    private boolean cMouseDown;
    
    CRTEdit() {
        this.cMouseDown = false;
        this.cDefaultStyleName = "Default";
        this.cStyleMgr = new CParaStyleMgr(this);
        this.cEditor = new CRTEditor(this);
        final boolean b = false;
        this.cHeight = (b ? 1 : 0);
        this.cWidth = (b ? 1 : 0);
        this.cY = (b ? 1 : 0);
        this.cX = (b ? 1 : 0);
        this.enableEvents(60L);
    }
    
    void destroy() {
        this.cEditor.destroy();
        this.destroyImage();
    }
    
    private void destroyImage() {
        if (this.cDBImage != null) {
            this.cDBImage.flush();
            this.cDBImage = null;
        }
        if (this.cDBGraphics != null) {
            this.cDBGraphics.dispose();
            this.cDBGraphics = null;
        }
    }
    
    final CRTEditor getEditor() {
        return this.cEditor;
    }
    
    final CParaStyleMgr getParaStyleMgr() {
        return this.cStyleMgr;
    }
    
    final String getDefaultStyleName() {
        return this.cDefaultStyleName;
    }
    
    protected void processKeyEvent(final KeyEvent keyEvent) {
        super.processKeyEvent(keyEvent);
        if (!keyEvent.isConsumed() && !keyEvent.isAltDown()) {
            if (keyEvent.getID() == 400) {
                final char keyChar = keyEvent.getKeyChar();
                if (!Character.isISOControl(keyChar)) {
                    this.cEditor.keyTyped(keyChar);
                }
            }
            else if (keyEvent.getID() == 401) {
                this.cEditor.keyAction(keyEvent.getKeyCode(), keyEvent.isShiftDown(), keyEvent.isControlDown());
            }
        }
    }
    
    protected void processFocusEvent(final FocusEvent focusEvent) {
        super.processFocusEvent(focusEvent);
        if (focusEvent.getID() == 1004) {
            this.cEditor.setFocus(true);
        }
        else if (focusEvent.getID() == 1005) {
            this.cEditor.setFocus(false);
        }
    }
    
    protected void processMouseEvent(final MouseEvent mouseEvent) {
        super.processMouseEvent(mouseEvent);
        if (mouseEvent.getID() == 501) {
            if (!this.cMouseDown) {
                this.cMouseDown = true;
                this.cEditor.getParent().requestFocus();
                this.cEditor.mouseDown(mouseEvent.getX(), mouseEvent.getY(), mouseEvent.isShiftDown(), mouseEvent.getClickCount());
            }
        }
        else if (mouseEvent.getID() == 502 && this.cMouseDown) {
            this.cMouseDown = false;
            this.cEditor.mouseUp();
        }
    }
    
    protected void processMouseMotionEvent(final MouseEvent mouseEvent) {
        super.processMouseMotionEvent(mouseEvent);
        if (mouseEvent.getID() == 506) {
            this.cEditor.mouseDrag(mouseEvent.getX(), mouseEvent.getY());
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.cDBImage == null) {
            final Dimension size = this.getSize();
            if (size.width == 0 || size.height == 0) {
                return;
            }
            this.cDBImage = this.createImage(size.width, size.height);
            this.cDBGraphics = this.cDBImage.getGraphics();
        }
        this.cEditor.prePaint();
        this.cEditor.paint(this.cDBGraphics, graphics);
        if (this.cDBImage != null) {
            graphics.drawImage(this.cDBImage, 0, 0, this);
        }
        this.cEditor.postPaint(graphics);
    }
    
    public void setBounds(final int n, final int n2, final int n3, final int n4) {
        super.setBounds(n, n2, n3, n4);
        this.destroyImage();
        this.cEditor.beginChange();
        this.cEditor.setSize(n3, n4);
        this.cEditor.endChange(true);
    }
    
    void setUILocale(final Locale cuiLocale) {
        this.cUILocale = cuiLocale;
    }
    
    Locale getUILocale() {
        if (this.cUILocale == null) {
            this.cUILocale = Locale.getDefault();
        }
        return this.cUILocale;
    }
    
    void setActiveLocale(final Locale cActiveLocale) {
        this.cActiveLocale = cActiveLocale;
    }
    
    Locale getActiveLocale() {
        if (this.cActiveLocale == null) {
            this.cActiveLocale = Locale.getDefault();
        }
        return this.cActiveLocale;
    }
}
