// 
// Decompiled by Procyon v0.5.30
// 

package jap;

import anon.AnonServerDescription;
import java.awt.event.ActionListener;
import gui.JAPDll;
import java.awt.Component;
import gui.AWTUpdateQueue;
import javax.swing.JFrame;

public abstract class AbstractJAPMainView extends JFrame implements IJAPMainView
{
    protected String m_Title;
    protected JAPController m_Controller;
    private boolean m_bChangingTitle;
    private final Object SYNC_TITLE;
    private final Object SYNC_PACK;
    private final AWTUpdateQueue AWT_UPDATE_QUEUE;
    
    public AbstractJAPMainView(final String s, final JAPController controller) {
        super(s);
        this.m_bChangingTitle = false;
        this.SYNC_TITLE = new Object();
        this.SYNC_PACK = new Object();
        this.AWT_UPDATE_QUEUE = new AWTUpdateQueue(new Runnable() {
            public void run() {
                synchronized (AbstractJAPMainView.this.SYNC_PACK) {
                    AbstractJAPMainView.this.onUpdateValues();
                }
            }
        });
        this.setName(s);
        this.m_Controller = controller;
        this.m_Title = s;
        this.setDefaultCloseOperation(0);
    }
    
    public void pack() {
        synchronized (this.SYNC_PACK) {
            super.pack();
        }
    }
    
    public abstract void saveWindowPositions();
    
    public Component getCurrentView() {
        return this.getContentPane();
    }
    
    public void setTitle(final String s) {
        this.setName(s);
        super.setTitle(s);
    }
    
    public abstract void showIconifiedView();
    
    public void setVisible(final boolean visible) {
        synchronized (this.SYNC_PACK) {
            if (visible) {
                final JAPViewIconified viewIconified = this.getViewIconified();
                if (viewIconified != null) {
                    viewIconified.setVisible(false);
                }
            }
            super.setVisible(visible);
        }
    }
    
    public void showConfigDialog(final String s, final Object o) {
    }
    
    public final void showConfigDialog() {
        this.showConfigDialog(null, null);
    }
    
    public void packetMixed(final long n) {
    }
    
    public final boolean isChangingTitle() {
        return this.m_bChangingTitle;
    }
    
    public boolean hideWindowInTaskbar() {
        synchronized (this.SYNC_TITLE) {
            this.m_bChangingTitle = true;
            this.setTitle(Double.toString(Math.random()));
            final boolean hideWindowInTaskbar = JAPDll.hideWindowInTaskbar(this.getTitle());
            if (hideWindowInTaskbar) {
                this.setVisible(false);
            }
            this.setTitle(this.m_Title);
            this.m_bChangingTitle = false;
            return hideWindowInTaskbar;
        }
    }
    
    public void updateValues(final boolean b) {
        this.AWT_UPDATE_QUEUE.update(b);
    }
    
    public abstract void onUpdateValues();
    
    public abstract void disableSetAnonMode();
    
    public abstract void doClickOnCascadeChooser();
    
    public abstract JAPViewIconified getViewIconified();
    
    public abstract void registerViewIconified(final JAPViewIconified p0);
    
    public abstract void create(final boolean p0);
    
    public abstract void transferedBytes(final long p0, final int p1);
    
    public abstract void channelsChanged(final int p0);
    
    public abstract void removeStatusMsg(final int p0);
    
    public abstract int addStatusMsg(final String p0, final int p1, final boolean p2, final ActionListener p3);
    
    public abstract int addStatusMsg(final String p0, final int p1, final boolean p2);
    
    public abstract void dataChainErrorSignaled();
    
    public abstract void connectionEstablished(final AnonServerDescription p0);
    
    public abstract void connecting(final AnonServerDescription p0);
    
    public abstract void disconnected();
    
    public abstract void connectionError();
}
