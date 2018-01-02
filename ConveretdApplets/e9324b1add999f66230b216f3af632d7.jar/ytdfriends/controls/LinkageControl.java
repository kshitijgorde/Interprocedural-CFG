// 
// Decompiled by Procyon v0.5.30
// 

package ytdfriends.controls;

import java.util.Iterator;
import edu.berkeley.guir.prefuse.FocusManager;
import edu.berkeley.guir.prefuse.graph.Entity;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import edu.berkeley.guir.prefuse.VisualItem;
import edu.berkeley.guir.prefuse.ItemRegistry;
import edu.berkeley.guir.prefuse.focus.FocusSet;
import edu.berkeley.guir.prefuse.NodeItem;
import edu.berkeley.guir.prefuse.graph.Node;
import java.util.ArrayList;
import ytdfriends.FriendsPanel;
import edu.berkeley.guir.prefuse.event.ControlAdapter;

public class LinkageControl extends ControlAdapter
{
    private FriendsPanel fPanel;
    private ArrayList<Node> fixedList;
    public boolean isEnabled;
    private boolean persist;
    private long down;
    private long thresh;
    private NodeItem tempFocus;
    private FocusSet focusSet;
    
    public LinkageControl(final FriendsPanel fPanel) {
        this.fixedList = new ArrayList<Node>();
        this.isEnabled = false;
        this.persist = false;
        this.thresh = 500L;
        this.tempFocus = null;
        this.fPanel = fPanel;
        final ItemRegistry registry = fPanel.getItemRegistry();
        this.focusSet = registry.getFocusManager().getFocusSet((Object)"clicked");
    }
    
    public void itemKeyPressed(final VisualItem item, final KeyEvent e) {
        if (this.isSpaceBar(e)) {
            if (!this.isEnabled) {
                this.isEnabled = true;
                this.down = System.currentTimeMillis();
                if (item.getItemClass().equals("node")) {
                    this.tempFocus = (NodeItem)item;
                    this.focusSet.add(this.tempFocus.getEntity());
                    this.fPanel.setReferrer(this.tempFocus);
                }
                if (!this.setFixed(this.fPanel.getItemRegistry(), true)) {
                    this.isEnabled = false;
                    return;
                }
                this.showLinkage();
            }
            else if (this.persist) {
                this.down = System.currentTimeMillis();
            }
        }
    }
    
    public void itemKeyReleased(final VisualItem item, final KeyEvent e) {
        this.keyReleased(e);
    }
    
    public void keyPressed(final KeyEvent e) {
        if (this.isSpaceBar(e)) {
            if (!this.isEnabled) {
                this.isEnabled = true;
                this.down = System.currentTimeMillis();
                if (!this.setFixed(this.fPanel.getItemRegistry(), true)) {
                    this.isEnabled = false;
                    return;
                }
                this.showLinkage();
            }
            else if (this.persist) {
                this.down = System.currentTimeMillis();
            }
        }
    }
    
    public void keyReleased(final KeyEvent e) {
        if (this.isSpaceBar(e) && this.isEnabled) {
            final long up = System.currentTimeMillis();
            if (this.persist || up - this.down > this.thresh) {
                this.isEnabled = false;
                this.persist = false;
                if (this.tempFocus != null) {
                    this.focusSet.remove(this.tempFocus.getEntity());
                }
                this.hideLinkage();
                this.setFixed(this.fPanel.getItemRegistry(), false);
                this.tempFocus = null;
            }
            else {
                this.persist = true;
            }
        }
    }
    
    public void mouseClicked(final MouseEvent e) {
        if (this.isEnabled) {
            this.isEnabled = false;
            this.persist = false;
            if (this.tempFocus != null) {
                this.focusSet.remove(this.tempFocus.getEntity());
            }
            this.hideLinkage();
            this.setFixed(this.fPanel.getItemRegistry(), false);
        }
    }
    
    private boolean isSpaceBar(final KeyEvent e) {
        return true;
    }
    
    private void showLinkage() {
        this.fPanel.setLinkageMode(true);
    }
    
    private void hideLinkage() {
        this.fPanel.setLinkageMode(false);
    }
    
    private boolean setFixed(final ItemRegistry registry, final boolean state) {
        synchronized (registry) {
            final FocusManager fman = registry.getFocusManager();
            final FocusSet click = fman.getFocusSet((Object)"clicked");
            Iterator<Node> nodeIter;
            if (state) {
                if (click.size() < 2) {
                    // monitorexit(registry)
                    return false;
                }
                nodeIter = (Iterator<Node>)click.iterator();
            }
            else {
                if (this.fixedList.isEmpty()) {
                    // monitorexit(registry)
                    return false;
                }
                click.clear();
                nodeIter = this.fixedList.iterator();
            }
            while (nodeIter.hasNext()) {
                final Node n = nodeIter.next();
                final NodeItem nitem = registry.getNodeItem(n);
                nitem.setFixed(state);
                nitem.setWasFixed(state);
                if (state) {
                    this.fixedList.add(n);
                }
                else {
                    if (state || nitem == this.tempFocus) {
                        continue;
                    }
                    click.add((Entity)n);
                }
            }
            if (!state) {
                final FocusSet mouse = fman.getFocusSet((Object)"moused");
                nodeIter = (Iterator<Node>)mouse.iterator();
                while (nodeIter.hasNext()) {
                    final Node n2 = nodeIter.next();
                    final NodeItem nitem2 = registry.getNodeItem(n2);
                    nitem2.setFixed(false);
                    nitem2.setWasFixed(false);
                    nitem2.setFixed(true);
                }
            }
        }
        if (!state) {
            this.fixedList.clear();
        }
        return true;
    }
}
