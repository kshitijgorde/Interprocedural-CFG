// 
// Decompiled by Procyon v0.5.30
// 

package ytdfriends.util;

import edu.berkeley.guir.prefuse.ItemRegistry;
import java.io.File;
import ytdfriends.FriendsLib;
import edu.berkeley.guir.prefuse.graph.io.XMLGraphWriter;
import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.swing.JFileChooser;
import ytdfriends.FriendsPanel;
import javax.swing.AbstractAction;

public class SaveVisibleNetworkAction extends AbstractAction
{
    private FriendsPanel vizster;
    private JFileChooser chooser;
    
    public SaveVisibleNetworkAction(final FriendsPanel vizster) {
        this.vizster = vizster;
        this.chooser = new JFileChooser();
    }
    
    public void actionPerformed(final ActionEvent arg0) {
        File f = null;
        final int rval = this.chooser.showOpenDialog(this.vizster);
        if (rval == 0) {
            f = this.chooser.getSelectedFile();
            final ItemRegistry registry = this.vizster.getItemRegistry();
            final XMLGraphWriter gw = new XMLGraphWriter();
            try {
                gw.writeGraph(registry.getFilteredGraph(), f);
            }
            catch (Exception e) {
                e.printStackTrace();
                FriendsLib.defaultError(this.vizster, "Error saving file!");
            }
        }
    }
}
