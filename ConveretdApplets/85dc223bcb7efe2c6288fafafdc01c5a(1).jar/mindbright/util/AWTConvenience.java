// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.util;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.AWTEvent;
import java.awt.event.WindowEvent;
import java.awt.Button;
import java.awt.event.WindowAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.Component;
import java.awt.List;
import java.awt.TextField;
import java.awt.Choice;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Dialog;

public abstract class AWTConvenience
{
    public static final void placeDialog(final Dialog diag) {
        final Dimension sDim = Toolkit.getDefaultToolkit().getScreenSize();
        final Dimension mDim = diag.getSize();
        final int x = sDim.width / 2 - mDim.width / 2;
        final int y = sDim.height / 2 - mDim.height / 2;
        diag.setLocation(x, y);
    }
    
    public static final void setBackgroundOfChildren(final Container container) {
        final Component[] children = container.getComponents();
        container.setBackground(SystemColor.menu);
        for (int i = 0; i < children.length; ++i) {
            if (!(children[i] instanceof Choice)) {
                children[i].setBackground(SystemColor.menu);
                if (children[i] instanceof Container) {
                    setBackgroundOfChildren((Container)children[i]);
                }
                else if (!(children[i] instanceof Choice)) {
                    if (children[i] instanceof TextField || children[i] instanceof List) {
                        children[i].setBackground(SystemColor.text);
                    }
                    else {
                        children[i].setBackground(SystemColor.menu);
                    }
                }
            }
        }
    }
    
    public static final void setKeyListenerOfChildren(final Container container, final KeyListener listener, final Class typeOfChild) {
        final Component[] children = container.getComponents();
        for (int i = 0; i < children.length; ++i) {
            if (!(children[i] instanceof Choice)) {
                if (children[i] instanceof Container) {
                    setKeyListenerOfChildren((Container)children[i], listener, typeOfChild);
                }
                else if (children[i] != null && (typeOfChild == null || typeOfChild.isInstance(children[i]))) {
                    children[i].addKeyListener(listener);
                }
            }
        }
    }
    
    public static class CloseAction implements ActionListener
    {
        Dialog dialog;
        
        public CloseAction(final Dialog dialog) {
            this.dialog = dialog;
        }
        
        public void actionPerformed(final ActionEvent e) {
            this.dialog.setVisible(false);
        }
    }
    
    public static class CloseAdapter extends WindowAdapter
    {
        Button b;
        
        public CloseAdapter(final Button b) {
            this.b = b;
        }
        
        public void windowClosing(final WindowEvent e) {
            this.b.dispatchEvent(new ActionEvent(this.b, 1001, this.b.getActionCommand()));
        }
    }
    
    public static class OKCancelAdapter extends KeyAdapter
    {
        protected static boolean isMRJ;
        Button butOK;
        Button butCancel;
        
        public OKCancelAdapter(final Button ok, final Button cancel) {
            this.butOK = ok;
            this.butCancel = cancel;
        }
        
        protected void pushButton(final Button target) {
            if (OKCancelAdapter.isMRJ) {
                target.dispatchEvent(new KeyEvent(target, 401, System.currentTimeMillis(), 0, 10, '\n'));
            }
            else {
                target.dispatchEvent(new ActionEvent(target, 1001, target.getActionCommand()));
            }
        }
        
        public void keyReleased(final KeyEvent e) {
            switch (e.getKeyCode()) {
                case 10: {
                    if (this.butOK != null) {
                        this.pushButton(this.butOK);
                        break;
                    }
                    break;
                }
                case 27: {
                    if (this.butCancel != null) {
                        this.pushButton(this.butCancel);
                        break;
                    }
                    break;
                }
            }
        }
        
        static {
            OKCancelAdapter.isMRJ = false;
            try {
                OKCancelAdapter.isMRJ = (System.getProperty("mrj.version") != null);
            }
            catch (RuntimeException ex) {}
        }
    }
}
