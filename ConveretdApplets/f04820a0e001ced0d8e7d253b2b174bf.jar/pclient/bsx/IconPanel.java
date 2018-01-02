// 
// Decompiled by Procyon v0.5.30
// 

package pclient.bsx;

import java.awt.Label;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import pclient.bsc.BaseChat;
import java.awt.event.ActionListener;
import java.awt.Panel;

public class IconPanel extends Panel implements ActionListener
{
    private BaseChat pChat;
    
    public IconPanel(final BaseChat pChat) {
        this.pChat = pChat;
        this.buildGUI();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (!(actionEvent.getSource() instanceof ImageButton)) {
            this.pChat.paraConf.printer().print("not an image button. actin ignored.");
            return;
        }
        final String id = ((ImageButton)actionEvent.getSource()).getID();
        this.pChat.paraConf.printer().print("an image button," + id);
        this.pChat.insertImage(id);
    }
    
    private void buildGUI() {
        final Panel buildTop = this.buildTop();
        final Panel buildImages = this.buildImages();
        this.setLayout(new BorderLayout(2, 2));
        this.add("North", buildTop);
        this.add("Center", buildImages);
    }
    
    private Panel buildTop() {
        final Label label = new Label(" ");
        return new Panel();
    }
    
    private Panel buildImages() {
        return new ImgLibPanel(this.pChat, this);
    }
}
