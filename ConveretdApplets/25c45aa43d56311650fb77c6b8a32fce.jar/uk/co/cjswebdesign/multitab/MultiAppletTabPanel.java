// 
// Decompiled by Procyon v0.5.30
// 

package uk.co.cjswebdesign.multitab;

import javax.swing.Icon;
import java.awt.Component;
import javax.swing.JScrollPane;
import java.awt.LayoutManager;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JTabbedPane;

class MultiAppletTabPanel extends JTabbedPane
{
    public MultiAppletTabPanel(final String tabplace, final String policy, final String bg, final String fg) {
        this.setAppletTabPlacement(tabplace);
        this.setTabPolicy(policy);
        this.setTabBackgroundColor(bg);
        this.setTabForegroundColor(fg);
    }
    
    public MultiAppletTabPanel(final String tabplace, final String policy, final String bg, final String fg, final MultitabEditorPane[] editors) {
        this.setAppletTabPlacement(tabplace);
        this.setTabPolicy(policy);
        this.setTabBackgroundColor(bg);
        this.setTabForegroundColor(fg);
        for (int i = 1; i < editors.length; ++i) {
            this.addMultitabEditorPane(editors[i]);
        }
    }
    
    public MultiAppletTabPanel(final MultitabEditorPane[] editors) {
        for (int i = 1; i < editors.length; ++i) {
            this.addMultitabEditorPane(editors[i]);
        }
    }
    
    void setAppletTabPlacement(final String placement) {
        if (placement.equalsIgnoreCase("top")) {
            this.setTabPlacement(1);
        }
        else if (placement.equalsIgnoreCase("right")) {
            this.setTabPlacement(4);
        }
        else if (placement.equalsIgnoreCase("bottom")) {
            this.setTabPlacement(3);
        }
        else if (placement.equalsIgnoreCase("left")) {
            this.setTabPlacement(2);
        }
        else {
            this.setTabPlacement(1);
        }
    }
    
    void setTabBackgroundColor(final String nm) {
        Color bgColor;
        try {
            bgColor = Color.decode(nm);
        }
        catch (NumberFormatException nfx) {
            bgColor = Color.GRAY;
        }
        this.setBackground(bgColor);
    }
    
    void setTabForegroundColor(final String nm) {
        Color fgColor;
        try {
            fgColor = Color.decode(nm);
        }
        catch (NumberFormatException nfx) {
            fgColor = Color.BLACK;
        }
        this.setForeground(fgColor);
    }
    
    void setTabPolicy(final String policy) {
        if (policy.equalsIgnoreCase("wrap")) {
            this.setTabLayoutPolicy(0);
        }
        else if (policy.equalsIgnoreCase("scroll")) {
            this.setTabLayoutPolicy(1);
        }
        else {
            this.setTabLayoutPolicy(1);
        }
    }
    
    void addMultitabEditorPane(final MultitabEditorPane editor) {
        final JPanel panel = new JPanel(new BorderLayout());
        final JScrollPane scroll = new JScrollPane(editor);
        panel.add(scroll, "Center");
        this.addTab(editor.getTitle(), null, panel, editor.getDescription());
    }
}
