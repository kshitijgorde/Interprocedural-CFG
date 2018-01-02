// 
// Decompiled by Procyon v0.5.30
// 

package ytdfriends;

import java.awt.Component;
import java.net.Authenticator;
import javax.swing.JFrame;

public class FriendsFrame extends JFrame
{
    private static final long serialVersionUID = 1341567691228810777L;
    public FriendsPanel fPanel;
    public static String uid;
    
    public void init() {
        Authenticator.setDefault(new MyFrameAuthenticator());
        FriendsFrame.uid = "fcf20f17671dcebcafe5147b18394a48";
        (this.fPanel = new FriendsPanel(new FriendsApplet())).setSize(800, 800);
        this.fPanel.setVisible(true);
        this.getContentPane().add(this.fPanel);
        this.getContentPane().setSize(800, 800);
        this.pack();
        this.setVisible(true);
    }
    
    public static void main(final String[] argv) {
        new FriendsFrame();
    }
    
    public FriendsFrame() {
        super("YatedoFriends");
        this.init();
    }
}
