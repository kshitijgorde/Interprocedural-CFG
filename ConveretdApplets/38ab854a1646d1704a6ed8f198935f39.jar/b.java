import java.awt.Component;
import javax.swing.JTabbedPane;

// 
// Decompiled by Procyon v0.5.30
// 

public class b extends JTabbedPane
{
    public b(final Component component) {
        this.addTab(component.toString(), component);
    }
}
