// 
// Decompiled by Procyon v0.5.30
// 

package kiang.swing;

import java.util.EventObject;
import java.util.Iterator;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.swing.JList;

public class JClickableList extends JList
{
    private Set listClickedListeners;
    
    public JClickableList() {
        this.listClickedListeners = new LinkedHashSet();
        this.initEventListeners();
    }
    
    private void initEventListeners() {
        this.addMouseListener(new MouseAdapter() {
            public void mouseReleased(final MouseEvent e) {
                final int clickedIndex = JClickableList.this.locationToIndex(e.getPoint());
                if (clickedIndex > -1 && JClickableList.this.isSelectedIndex(clickedIndex)) {
                    JClickableList.this.notifyClickListeners();
                }
            }
        });
        this.addKeyListener(new KeyAdapter() {
            public void keyPressed(final KeyEvent e) {
                if (10 == e.getKeyCode()) {
                    final int selectedIndex = JClickableList.this.getSelectedIndex();
                    if (selectedIndex > -1) {
                        JClickableList.this.notifyClickListeners();
                    }
                }
            }
        });
    }
    
    private void notifyClickListeners() {
        synchronized (this.listClickedListeners) {
            for (final ListClickedListener nextListener : this.listClickedListeners) {
                nextListener.listClicked(new ListClickedEvent((ListClickedEvent)null));
            }
        }
        // monitorexit(this.listClickedListeners)
    }
    
    public void addListClickedListener(final ListClickedListener listener) {
        if (listener != null) {
            synchronized (this.listClickedListeners) {
                this.listClickedListeners.add(listener);
            }
            // monitorexit(this.listClickedListeners)
        }
    }
    
    public synchronized void removeListClickedListener(final ListClickedListener listener) {
        if (listener != null) {
            synchronized (this.listClickedListeners) {
                this.listClickedListeners.remove(listener);
            }
            // monitorexit(this.listClickedListeners)
        }
    }
    
    public class ListClickedEvent extends EventObject
    {
        private ListClickedEvent() {
            super(JClickableList.this);
        }
    }
    
    public interface ListClickedListener
    {
        void listClicked(final ListClickedEvent p0);
    }
}
