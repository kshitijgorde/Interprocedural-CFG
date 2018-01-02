// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.editorpanel;

import java.awt.Component;
import java.util.Hashtable;
import java.awt.Panel;

public class ToolHost extends Panel
{
    private static final boolean DEBUGGING = false;
    private ToolTip tip;
    private Hashtable tools;
    public static final String TOOL_COLORMAP = "ColorMap";
    public static final String TOOL_TIP = "Tip";
    private ColorList colorList;
    
    public ToolHost() {
        this.colorList = null;
        this.tools = new Hashtable();
    }
    
    public ToolTip getToolTip() {
        return (ToolTip)this.getTool("Tip");
    }
    
    public Component getTool(final String s) {
        return this.tools.get(s);
    }
    
    public void addTool(final String s, final Component component) {
        this.tools.put(s, component);
        component.hide();
        this.add(component);
    }
}
