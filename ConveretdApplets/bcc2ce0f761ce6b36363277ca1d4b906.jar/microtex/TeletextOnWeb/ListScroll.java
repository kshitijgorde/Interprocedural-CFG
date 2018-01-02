// 
// Decompiled by Procyon v0.5.30
// 

package microtex.TeletextOnWeb;

import java.awt.Event;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.Vector;
import java.awt.Dimension;
import java.awt.Scrollbar;
import java.awt.Panel;

public class ListScroll extends Panel
{
    ControlPanel m_parent;
    Scrollbar m_scrolVert;
    ScrollableCanvas m_canvas;
    Dimension m_preferredImageSize;
    Dimension m_prefSize;
    int m_nLineInc;
    
    public void init(final Vector pageList) {
        this.m_preferredImageSize = new Dimension(this.preferredSize().width, pageList.size() * pageList.elementAt(0).preferredSize().height + (pageList.size() + 2) * 5);
        this.m_nLineInc = pageList.elementAt(0).preferredSize().height + 5;
        this.m_canvas = new ScrollableCanvas(this.m_preferredImageSize);
        for (int i = 0; i < pageList.size(); ++i) {
            this.m_canvas.add(pageList.elementAt(i));
        }
        this.m_canvas.setBackground(new Color(95, 95, 103));
        this.m_canvas.validate();
        this.m_scrolVert = new Scrollbar(1);
        this.setLayout(new BorderLayout());
        this.add("Center", this.m_canvas);
        this.add("East", this.m_scrolVert);
        this.validate();
        this.m_scrolVert.setValues(0, this.preferredSize().height, 0, this.m_preferredImageSize.height);
        this.m_scrolVert.setLineIncrement(pageList.elementAt(0).preferredSize().height);
        this.m_scrolVert.setPageIncrement((int)(this.preferredSize().height * 0.5));
    }
    
    public boolean handleEvent(final Event evt) {
        switch (evt.id) {
            case 601:
            case 602:
            case 603:
            case 604:
            case 605: {
                if (evt.target == this.m_scrolVert) {
                    if (this.m_scrolVert.getLineIncrement() != this.m_nLineInc) {
                        this.m_scrolVert.setLineIncrement(this.m_nLineInc);
                        this.m_scrolVert.setValue(this.m_nLineInc);
                        this.m_canvas.m_nTy = this.m_nLineInc;
                    }
                    else {
                        this.m_canvas.m_nTy = (int)evt.arg;
                    }
                    this.m_canvas.scroll();
                    this.m_canvas.paintAll(this.m_canvas.getGraphics());
                    break;
                }
                break;
            }
        }
        return super.handleEvent(evt);
    }
    
    public void setPreferredSize(final Dimension prefSize) {
        this.m_prefSize = prefSize;
    }
    
    public Dimension minimumSize() {
        return this.m_prefSize;
    }
    
    public void paint() {
        this.m_scrolVert.setLineIncrement(this.m_nLineInc);
    }
    
    public Dimension preferredSize() {
        return this.m_prefSize;
    }
    
    public void resetScroll() {
        this.m_canvas.m_nOldTy = 0;
        this.m_canvas.m_nOldTx = 0;
        this.m_canvas.m_nTy = 0;
        this.m_canvas.m_nTx = 0;
        this.m_scrolVert.setValues(0, this.preferredSize().height, 0, this.m_preferredImageSize.height - this.preferredSize().height);
    }
    
    public ListScroll(final ControlPanel parent) {
        this.m_nLineInc = 25;
        this.m_parent = parent;
    }
}
