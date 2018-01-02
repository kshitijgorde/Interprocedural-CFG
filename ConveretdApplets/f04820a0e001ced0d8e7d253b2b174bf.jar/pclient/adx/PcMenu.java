// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adx;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuBar;
import java.awt.event.ActionListener;
import pclient.shd.Config;

public class PcMenu
{
    private Config paraConf;
    private ActionListener actListener;
    private JMenuBar menuBar;
    protected JCheckBoxMenuItem checkIgnore;
    private JCheckBoxMenuItem checkDouble;
    private JCheckBoxMenuItem checkSep;
    protected JCheckBoxMenuItem checkBeep;
    
    public PcMenu(final Config paraConf, final ActionListener actListener) {
        this.actListener = actListener;
        this.paraConf = paraConf;
    }
    
    public JMenuBar getBar() {
        return this.menuBar;
    }
    
    private void createMenu() {
    }
}
