// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adx;

import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;
import com.pchat.sc.WindowUtil;
import pclient.adv.AppletSpice;
import pclient.adv.ComInter;
import javax.swing.JFrame;

public class PopAbout extends JFrame implements ComInter
{
    private AppletSpice paraApplet;
    
    public void setPara(final AppletSpice paraApplet) {
        this.paraApplet = paraApplet;
        this.setTitle(this.paraApplet.paraConf.title());
        this.setSize(480, 300);
        this.setDefaultCloseOperation(1);
        this.buildGUI();
        WindowUtil.center(this);
    }
    
    public void process(final int n, final String[] array) {
    }
    
    public void restart() {
        this.setVisible(true);
    }
    
    public void destroy() {
        this.setVisible(false);
        this.dispose();
    }
    
    private void buildGUI() {
        this.getContentPane().setLayout(new BorderLayout());
        final AboutBox aboutBox = new AboutBox(this.paraApplet.paraConf, false);
        aboutBox.setMinimumSize(new Dimension(400, 240));
        this.getContentPane().add("North", new JLabel(" "));
        this.getContentPane().add("Center", aboutBox);
    }
}
