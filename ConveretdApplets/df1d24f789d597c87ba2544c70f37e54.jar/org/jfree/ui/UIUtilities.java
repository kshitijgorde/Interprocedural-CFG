// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.ui;

import javax.swing.UIDefaults;
import javax.swing.border.Border;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.BorderUIResource;
import javax.swing.UIManager;

public class UIUtilities
{
    public static void setupUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        final UIDefaults defaults = UIManager.getDefaults();
        defaults.put("PopupMenu.border", new BorderUIResource.EtchedBorderUIResource(0, defaults.getColor("controlShadow"), defaults.getColor("controlLtHighlight")));
        final BorderUIResource.CompoundBorderUIResource compoundBorderUIResource = new BorderUIResource.CompoundBorderUIResource(new MatteBorder(2, 2, 2, 2, defaults.getColor("control")), new MatteBorder(1, 1, 1, 1, Color.black));
        final BorderUIResource.EmptyBorderUIResource emptyBorderUIResource = new BorderUIResource.EmptyBorderUIResource(0, 0, 0, 0);
        defaults.put("SplitPane.border", emptyBorderUIResource);
        defaults.put("Table.scrollPaneBorder", emptyBorderUIResource);
        defaults.put("ComboBox.border", compoundBorderUIResource);
        defaults.put("TextField.border", compoundBorderUIResource);
        defaults.put("TextArea.border", compoundBorderUIResource);
        defaults.put("CheckBox.border", compoundBorderUIResource);
        defaults.put("ScrollPane.border", emptyBorderUIResource);
    }
}
