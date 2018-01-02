// 
// Decompiled by Procyon v0.5.30
// 

package ytdfriends;

import edu.berkeley.guir.prefuse.VisualItem;
import javax.swing.UIManager;
import java.io.IOException;
import java.net.MalformedURLException;
import edu.berkeley.guir.prefuse.graph.io.XMLGraphReader;
import edu.berkeley.guir.prefuse.graph.Graph;
import java.net.URL;
import javax.swing.JOptionPane;
import java.awt.Component;
import java.util.Timer;

public class FriendsLib
{
    public static final int DEFAULT_ERROR = 0;
    public static final int PROFILE_ERROR = 1;
    private static Timer s_timer;
    
    public static final void errexit(final Exception e, final Component c, final String msg) {
        errexit(0, e, c, msg);
    }
    
    public static final void errexit(final int type, final Exception e, final Component c, final String msg) {
        if (e != null) {
            e.printStackTrace();
        }
        switch (type) {
            case 1: {
                profileLoadError(c, msg);
                break;
            }
            default: {
                defaultError(c, msg);
                break;
            }
        }
        System.exit(1);
    }
    
    public static final void profileLoadError(final Component c, final String uid) {
        JOptionPane.showMessageDialog(c, "Error loading profile: " + uid, "Error Loading Profile", 0);
    }
    
    public static final void defaultError(final Component c, final String msg) {
        JOptionPane.showMessageDialog(c, msg);
    }
    
    public static final Graph loadGraph(final URL graphUrl) throws MalformedURLException, IOException {
        final XMLGraphReader gl = new XMLGraphReader();
        final Graph loadedGraph = gl.loadGraph(graphUrl);
        return loadedGraph;
    }
    
    public static final void setLookAndFeel() {
        try {
            final String laf = UIManager.getSystemLookAndFeelClassName();
            UIManager.setLookAndFeel(laf);
        }
        catch (Exception ex) {}
    }
    
    public static final void setHighlightValue(final VisualItem item, final int val) {
        if (item == null) {
            return;
        }
        int[] value = (int[])item.getVizAttribute("highlightValue");
        if (value == null) {
            value = new int[] { 0 };
            item.setVizAttribute("highlightValue", (Object)value);
        }
        value[0] = val;
    }
    
    public static final int getHighlightValue(final VisualItem item) {
        if (item == null) {
            return -1;
        }
        final int[] val = (int[])item.getVizAttribute("highlightValue");
        return (val == null) ? 0 : val[0];
    }
    
    public static final Timer getTimer() {
        if (FriendsLib.s_timer == null) {
            FriendsLib.s_timer = new Timer() {
                public void cancel() {
                }
            };
        }
        return FriendsLib.s_timer;
    }
}
