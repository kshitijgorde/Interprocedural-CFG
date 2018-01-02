// 
// Decompiled by Procyon v0.5.30
// 

package com.mobius.applet;

import java.awt.event.KeyEvent;
import java.awt.Window;
import java.awt.event.WindowEvent;
import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.util.Stack;
import java.applet.Applet;
import netscape.javascript.JSObject;
import java.awt.EventQueue;

public class MobiusAppletEventQueue extends EventQueue
{
    private JSObject win;
    private Applet parentApplet;
    private Stack focusedWindowStack;
    private boolean isIEBrowser;
    
    public MobiusAppletEventQueue(final Applet parentApplet, final boolean isIEBrowser) {
        this.isIEBrowser = false;
        this.parentApplet = parentApplet;
        this.focusedWindowStack = new Stack();
        Toolkit.getDefaultToolkit().getSystemEventQueue().push(this);
        this.isIEBrowser = isIEBrowser;
    }
    
    public void pop() {
        super.pop();
    }
    
    protected void dispatchEvent(final AWTEvent awtEvent) {
        try {
            this.interceptEvent(awtEvent);
        }
        catch (Exception ex) {}
        super.dispatchEvent(awtEvent);
    }
    
    private void interceptEvent(final AWTEvent awtEvent) {
        if (!this.isIEBrowser) {
            if (awtEvent.getID() == 205) {
                final Window window = ((WindowEvent)awtEvent).getWindow();
                if (this.focusedWindowStack.empty() || this.focusedWindowStack.peek() != window) {
                    this.focusedWindowStack.push(window);
                }
            }
            if (awtEvent.getID() == 206) {
                if (this.focusedWindowStack.empty()) {
                    return;
                }
                if (this.focusedWindowStack.peek() == ((WindowEvent)awtEvent).getWindow()) {
                    this.focusedWindowStack.pop();
                }
                while (!this.focusedWindowStack.empty()) {
                    final Window window2 = this.focusedWindowStack.peek();
                    if (window2.isShowing()) {
                        window2.requestFocus();
                        break;
                    }
                    this.focusedWindowStack.pop();
                }
            }
        }
        if (awtEvent.getID() == 401) {
            final KeyEvent keyEvent = (KeyEvent)awtEvent;
            if ((keyEvent.isControlDown() || keyEvent.isAltDown()) && keyEvent.getKeyCode() != 17 && keyEvent.getKeyCode() != 16 && keyEvent.getKeyCode() != 18) {
                if (this.win == null) {
                    this.win = JSObject.getWindow(this.parentApplet);
                }
                new JavaScriptKeyPressedRunner(keyEvent).start();
            }
        }
    }
    
    private class JavaScriptKeyPressedRunner extends Thread
    {
        KeyEvent keyEvent;
        
        JavaScriptKeyPressedRunner(final KeyEvent keyEvent) {
            this.keyEvent = keyEvent;
        }
        
        public void run() {
            try {
                MobiusAppletEventQueue.this.win.call("javaKeyHandler", new Object[] { new Integer(this.keyEvent.getID() - 400), new Integer(this.keyEvent.getKeyCode()), new Integer(this.keyEvent.getKeyChar()), new Boolean(this.keyEvent.isAltDown()), new Boolean(this.keyEvent.isControlDown()), new Boolean(this.keyEvent.isShiftDown()) });
            }
            catch (Exception ex) {}
        }
    }
}
