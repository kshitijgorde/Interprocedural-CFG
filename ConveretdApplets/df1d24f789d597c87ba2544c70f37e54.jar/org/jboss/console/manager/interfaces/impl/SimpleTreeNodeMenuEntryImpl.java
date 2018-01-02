// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.console.manager.interfaces.impl;

import org.jboss.console.manager.interfaces.TreeAction;
import org.jboss.console.manager.interfaces.SimpleTreeNodeMenuEntry;

public class SimpleTreeNodeMenuEntryImpl implements SimpleTreeNodeMenuEntry
{
    protected TreeAction action;
    protected String text;
    
    public SimpleTreeNodeMenuEntryImpl() {
        this.action = null;
        this.text = null;
    }
    
    public SimpleTreeNodeMenuEntryImpl(final String text, final TreeAction action) {
        this.action = null;
        this.text = null;
        this.action = action;
        this.text = text;
    }
    
    public TreeAction getAction() {
        return this.action;
    }
    
    public String getText() {
        return this.text;
    }
}
