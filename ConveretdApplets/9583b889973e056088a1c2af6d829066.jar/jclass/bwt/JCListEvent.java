// 
// Decompiled by Procyon v0.5.30
// 

package jclass.bwt;

import java.awt.Event;

public class JCListEvent extends JCItemEvent
{
    public static final int INITIAL = 0;
    public static final int MODIFICATION = 1;
    public static final int ADDITION = 2;
    Event event;
    int row;
    int type;
    boolean doit;
    
    public int getRow() {
        return this.row;
    }
    
    public int getType() {
        return this.type;
    }
    
    public boolean getAllowSelection() {
        return this.doit;
    }
    
    public void setAllowSelection(final boolean doit) {
        this.doit = doit;
    }
    
    public Event getSourceEvent() {
        return this.event;
    }
    
    public JCListEvent(final JCListInterface jcListInterface, final Event event, final int row, final int type, final int n) {
        super(jcListInterface, 701, jcListInterface.getItem(row), n);
        this.doit = true;
        this.event = event;
        this.row = row;
        this.type = type;
    }
}
