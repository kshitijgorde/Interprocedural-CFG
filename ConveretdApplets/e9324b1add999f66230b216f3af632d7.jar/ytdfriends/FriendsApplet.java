// 
// Decompiled by Procyon v0.5.30
// 

package ytdfriends;

import java.awt.Component;
import javax.swing.JApplet;

public class FriendsApplet extends JApplet
{
    private static final long serialVersionUID = 1341567691228810777L;
    public FriendsPanel fPanel;
    public String uid;
    
    public void init() {
        this.uid = this.getParameter("uid");
        (this.fPanel = new FriendsPanel(this)).setVisible(true);
        this.add(this.fPanel);
    }
}
