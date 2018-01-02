import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.applet.Applet;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class JTree extends Panel
{
    JTreeView m_TreeView;
    
    public JTree(final Applet applet) {
        this.m_TreeView = null;
        this.m_TreeView = new JTreeView(applet);
        this.setLayout(new BorderLayout());
        this.add("Center", this.m_TreeView);
    }
}
