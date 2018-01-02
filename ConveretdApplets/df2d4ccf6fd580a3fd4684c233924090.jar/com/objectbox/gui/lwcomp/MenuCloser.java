// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.gui.lwcomp;

import com.objectbox.runner.util.JBLogger;

public class MenuCloser extends Thread
{
    private JBPopupMenu pm;
    protected boolean isRunning;
    protected boolean isFresh;
    
    public MenuCloser(final JBPopupMenu pm) {
        this.pm = null;
        this.isRunning = true;
        this.isFresh = true;
        this.pm = pm;
    }
    
    public MenuCloser(final Runnable runnable) {
        super(runnable);
        this.pm = null;
        this.isRunning = true;
        this.isFresh = true;
    }
    
    public MenuCloser(final Runnable runnable, final String s) {
        super(runnable, s);
        this.pm = null;
        this.isRunning = true;
        this.isFresh = true;
    }
    
    public MenuCloser(final String s) {
        super(s);
        this.pm = null;
        this.isRunning = true;
        this.isFresh = true;
    }
    
    public MenuCloser(final ThreadGroup threadGroup, final Runnable runnable) {
        super(threadGroup, runnable);
        this.pm = null;
        this.isRunning = true;
        this.isFresh = true;
    }
    
    public MenuCloser(final ThreadGroup threadGroup, final Runnable runnable, final String s) {
        super(threadGroup, runnable, s);
        this.pm = null;
        this.isRunning = true;
        this.isFresh = true;
    }
    
    public MenuCloser(final ThreadGroup threadGroup, final String s) {
        super(threadGroup, s);
        this.pm = null;
        this.isRunning = true;
        this.isFresh = true;
    }
    
    public void run() {
        while (this.isRunning) {
            try {
                Thread.sleep(50L);
            }
            catch (Exception ex) {
                JBLogger.log("Ex" + ex);
            }
            boolean b = true;
            while (this.pm.parent != null) {
                this.pm = this.pm.parent;
            }
            this.isFresh = true;
            if (this.pm.focus) {
                b = false;
            }
            for (JBPopupMenu jbPopupMenu = this.pm; jbPopupMenu != null; jbPopupMenu = jbPopupMenu.opensubmenu) {
                if (jbPopupMenu.focus) {
                    b = false;
                    break;
                }
            }
            try {
                Thread.sleep(50L);
            }
            catch (Exception ex2) {
                JBLogger.log("Ex in MenuCloser" + ex2);
            }
            if (b && this.isFresh) {
                this.pm.setVisible(false);
                this.isRunning = false;
            }
        }
    }
}
