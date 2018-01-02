// 
// Decompiled by Procyon v0.5.30
// 

package ji.v1event;

import java.awt.event.KeyEvent;
import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import ji.util.e;
import java.awt.Component;

public class jiJava2KeyHandler implements jiJava2KeyInterface
{
    static zi keyInterceptor;
    static bu keyManager;
    static boolean interceptorAdded;
    
    public void setKeyManager(final bu keyManager) {
        jiJava2KeyHandler.keyManager = keyManager;
    }
    
    public boolean addJavaKeyManager(final Component component) {
        boolean b = false;
        try {
            if (e.av()) {
                if (jiJava2KeyHandler.keyInterceptor == null) {
                    jiJava2KeyHandler.keyInterceptor = new zi();
                }
                if (!jiJava2KeyHandler.interceptorAdded) {
                    component.getToolkit().addAWTEventListener(jiJava2KeyHandler.keyInterceptor, 8L);
                    jiJava2KeyHandler.interceptorAdded = true;
                }
                b = true;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return b;
    }
    
    public boolean removeJavaKeyManager() {
        boolean b = false;
        try {
            if (e.av()) {
                if (jiJava2KeyHandler.interceptorAdded && jiJava2KeyHandler.keyInterceptor != null) {
                    Toolkit.getDefaultToolkit().removeAWTEventListener(jiJava2KeyHandler.keyInterceptor);
                    jiJava2KeyHandler.interceptorAdded = false;
                }
                jiJava2KeyHandler.keyInterceptor = null;
                b = true;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return b;
    }
    
    public final void releaseResources() {
        jiJava2KeyHandler.keyManager = null;
        jiJava2KeyHandler.interceptorAdded = false;
    }
    
    static {
        jiJava2KeyHandler.keyInterceptor = null;
        jiJava2KeyHandler.keyManager = null;
        jiJava2KeyHandler.interceptorAdded = false;
    }
    
    class zi implements AWTEventListener
    {
        zi(final jiJava2KeyHandler jiJava2KeyHandler) {
        }
        
        public void eventDispatched(final AWTEvent awtEvent) {
            if (jiJava2KeyHandler.keyManager != null && awtEvent instanceof KeyEvent) {
                final KeyEvent keyEvent = (KeyEvent)awtEvent;
                final KeyEvent keyEvent2 = new KeyEvent((Component)keyEvent.getSource(), keyEvent.getID(), keyEvent.getWhen(), keyEvent.getModifiers(), keyEvent.getKeyCode(), keyEvent.getKeyChar());
                switch (awtEvent.getID()) {
                    case 401: {
                        jiJava2KeyHandler.keyManager.keyPressed(keyEvent2);
                        break;
                    }
                    case 402: {
                        jiJava2KeyHandler.keyManager.keyReleased(keyEvent2);
                        break;
                    }
                    case 400: {
                        jiJava2KeyHandler.keyManager.keyTyped(keyEvent2);
                        break;
                    }
                }
            }
        }
    }
}
