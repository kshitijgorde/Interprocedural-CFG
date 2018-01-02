// 
// Decompiled by Procyon v0.5.30
// 

package pclient.bsx;

import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Insets;
import java.util.Vector;
import java.awt.Panel;

public class TabbedPanel extends Panel
{
    private Vector captions;
    private Vector panels;
    private int[] tabWidths;
    private String selectedPanel;
    private Insets insets;
    private int tabHeight;
    private int tabMargin;
    
    public Insets insets() {
        return this.insets;
    }
    
    public TabbedPanel() {
        this.captions = new Vector();
        this.panels = new Vector();
        this.tabHeight = 21;
        this.tabMargin = 13;
        this.insets = new Insets(14 + this.tabHeight, 14, 14, 14);
        this.setLayout(new BorderLayout());
    }
    
    public void addTabPanel(final Panel panel, final String s) {
        this.panels.addElement(panel);
        this.captions.addElement(s);
        this.add(panel);
        panel.reshape(1, 22, this.getSize().width - 3, this.getSize().height - 3 - this.tabHeight);
        panel.setVisible(false);
        if (this.panels.size() == 1) {
            this.showTab(s);
        }
    }
    
    public void removePanel(final String s) {
        final int index = this.captions.indexOf(s);
        if (index == -1) {
            System.out.println("tab. Err2964");
            return;
        }
        this.captions.removeElementAt(index);
        this.panels.removeElementAt(index);
        if (s.equals(this.selectedPanel)) {
            if (this.captions.size() > 0) {
                this.showTab(this.captions.elementAt(0));
            }
            else {
                this.selectedPanel = null;
                this.repaint();
            }
        }
    }
    
    public void showTab(final String selectedPanel) {
        final int index = this.captions.indexOf(selectedPanel);
        if (index == -1) {
            System.out.println("W843. not a registered tab," + selectedPanel);
            return;
        }
        this.selectedPanel = selectedPanel;
        final Panel panel = this.panels.elementAt(index);
        for (int i = 0; i < this.captions.size(); ++i) {
            if (i != index) {
                ((Panel)this.panels.elementAt(i)).hide();
            }
        }
        panel.reshape(this.insets().left, this.insets().top, this.getSize().width - this.insets().left - this.insets().right, this.getSize().height - this.insets().top - this.insets().bottom);
        panel.validate();
        panel.layout();
        panel.show();
        this.postEvent(new Event(this, 1001, null));
    }
    
    public void layout() {
        super.layout();
        this.getSelectedPanel().reshape(this.insets().left, this.insets().top, this.getSize().width - this.insets().left - this.insets().right, this.getSize().height - this.insets().top - this.insets().bottom);
        this.getSelectedPanel().layout();
    }
    
    public Panel getSelectedPanel() {
        return this.panels.elementAt(this.captions.indexOf(this.selectedPanel));
    }
    
    public void paint(final Graphics graphics) {
        super.paint(graphics);
        final int n = this.tabHeight - 1;
        final int n2 = 0;
        final int n3 = this.getSize().height - 1;
        final int n4 = n2 + this.getSize().width - 1;
        graphics.setColor(Color.darkGray);
        graphics.drawLine(n2, n3, n4 - 1, n3);
        graphics.drawLine(n4, n, n4, n3);
        graphics.setColor(Color.gray);
        graphics.drawLine(n2 + 1, n3 - 1, n4 - 2, n3 - 1);
        graphics.drawLine(n4 - 1, n + 1, n4 - 1, n3 - 1);
        graphics.setColor(Color.white);
        graphics.drawLine(n2, n, n2, n3 - 1);
        this.tabWidths = new int[this.captions.size()];
        int n5 = -1;
        int n6 = 0;
        int n7 = 2;
        for (int i = 0; i < this.captions.size(); ++i) {
            final String s = this.captions.elementAt(i);
            this.tabWidths[i] = graphics.getFontMetrics(this.getFont()).stringWidth(s) + this.tabMargin;
            if (this.captions.elementAt(i).equals(this.selectedPanel)) {
                n5 = i;
                n6 = n7;
            }
            else {
                this.paintTab(graphics, false, n7, 0, this.tabWidths[i], n, s);
            }
            n7 += this.tabWidths[i] - 1;
        }
        if (n5 > -1) {
            this.paintTab(graphics, true, n6, 0, this.tabWidths[n5], n, this.captions.elementAt(n5));
            graphics.setColor(Color.white);
            graphics.drawLine(n2, n, n6 - 2, n);
            graphics.drawLine(n6 + this.tabWidths[n5] + 2, n, n4 - 1, n);
        }
        else {
            graphics.setColor(Color.white);
            graphics.drawLine(n2, n, n4 - 1, n);
        }
    }
    
    private void paintTab(final Graphics graphics, final boolean b, final int n, final int n2, final int n3, int n4, final String s) {
        int n5 = n;
        int n6 = n2 + 2;
        int n7 = n + n3 - 1;
        int n8 = n2 + n4 - 1;
        n4 -= 2;
        if (b) {
            n6 -= 2;
            n5 -= 2;
            n7 += 2;
            ++n8;
        }
        graphics.setColor(Color.darkGray);
        graphics.drawLine(n7 - 1, n6 + 2, n7 - 1, n8);
        graphics.drawRect(n7 - 2, n6 + 1, 0, 0);
        graphics.setColor(Color.gray);
        graphics.drawLine(n7 - 2, n6 + 2, n7 - 2, n8);
        graphics.setColor(Color.white);
        graphics.drawLine(n5, n6 + 2, n5, n8);
        graphics.drawLine(n5 + 2, n6, n7 - 3, n6);
        graphics.drawRect(n5 + 1, n6 + 1, 0, 0);
        graphics.setColor(Color.black);
        final FontMetrics fontMetrics = graphics.getFontMetrics(this.getFont());
        graphics.drawString(s, n + (n3 - fontMetrics.stringWidth(s)) / 2, fontMetrics.getHeight() - fontMetrics.getDescent() + n6 + 3);
        if (b) {
            graphics.setColor(this.getBackground());
            graphics.drawLine(n5 + 1, n8, n7 - 3, n8);
            graphics.drawLine(n5 + 1, n6 + 3, n5 + 1, n8);
        }
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (n2 < this.tabHeight) {
            int n3 = 0;
            for (int i = 0; i < this.captions.size(); ++i) {
                n3 += this.tabWidths[i];
                if (n < n3) {
                    this.showTab((String)this.captions.elementAt(i));
                    this.repaint();
                    return true;
                }
            }
        }
        return super.mouseDown(event, n, n2);
    }
}
