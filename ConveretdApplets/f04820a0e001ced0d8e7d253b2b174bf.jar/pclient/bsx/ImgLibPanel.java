// 
// Decompiled by Procyon v0.5.30
// 

package pclient.bsx;

import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import pclient.bsc.BaseChat;
import java.awt.event.ActionListener;
import java.awt.Panel;

public class ImgLibPanel extends Panel
{
    private ActionListener theListener;
    private BaseChat pChat;
    
    public ImgLibPanel(final BaseChat pChat, final ActionListener theListener) {
        this.pChat = pChat;
        this.theListener = theListener;
        this.buildLayout();
        this.pChat.paraConf.printer().print("image panel layout is done.");
        this.doLayout();
    }
    
    private void buildLayout() {
        this.setLayout(new BorderLayout(3, 3));
        final Panel buildGrid = this.buildGrid();
        final Label label = new Label();
        label.setText(" ");
        this.add("North", label);
        this.add("Center", buildGrid);
    }
    
    private Panel buildGrid() {
        final int n = 10;
        final String[] names = this.pChat.paraConf.getSmiley().getNames();
        final int length = names.length;
        int n2 = length / n;
        if (length % n != 0) {
            ++n2;
        }
        final Panel panel = new Panel();
        panel.setLayout(new GridLayout(n2, n, 5, 5));
        for (int i = 0; i < length; ++i) {
            panel.add(this.getButton(names[i]));
        }
        final Panel panel2 = new Panel();
        panel2.setLayout(new FlowLayout(1));
        panel2.add(panel);
        return panel2;
    }
    
    private ImageButton getButton(final String id) {
        final ImageButton imageButton = new ImageButton();
        final Image image = this.pChat.paraConf.getSmiley().getImage(id);
        boolean prepareImage = false;
        if (image != null) {
            prepareImage = this.prepareImage(image, this);
        }
        if (prepareImage) {
            this.pChat.paraConf.printer().print("prepared:" + id);
        }
        else {
            this.pChat.paraConf.printer().print("not prepared:" + id);
        }
        imageButton.setStaticImage(image);
        imageButton.setID(id);
        imageButton.addActionListener(this.theListener);
        return imageButton;
    }
}
