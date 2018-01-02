// 
// Decompiled by Procyon v0.5.30
// 

package pclient.bsx;

import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.Component;
import com.pchat.sc.WindowUtil;
import java.awt.Button;
import pclient.bsc.BaseChat;
import pclient.bsc.CommonInter;
import java.awt.event.ActionListener;
import java.awt.Frame;

public class IconPop extends Frame implements ActionListener, CommonInter
{
    private BaseChat pChat;
    private Button moreButton;
    
    public void process(final int n, final String[] array) {
    }
    
    public void setPara(final BaseChat pChat) {
        this.pChat = pChat;
        this.setTitle(this.pChat.paraConf.title());
        this.setSize(280, 200);
        this.buildGUI();
        this.enableEvents(64L);
        WindowUtil.center(this);
    }
    
    public void restart() {
        this.setVisible(true);
        this.toFront();
        this.setVisible(true);
    }
    
    public void destroy() {
        this.setVisible(false);
        this.dispose();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.moreButton) {
            this.pChat.fireControlPanel(12, null);
            return;
        }
        if (!(actionEvent.getSource() instanceof ImageButton)) {
            return;
        }
        final String id = ((ImageButton)actionEvent.getSource()).getID();
        this.setVisible(false);
        this.pChat.insertImage(id);
    }
    
    public void processWindowEvent(final WindowEvent windowEvent) {
        final int id = windowEvent.getID();
        if (id != 205) {
            if (id != 206) {
                if (id == 201) {
                    this.closeWindow();
                }
            }
        }
    }
    
    private void closeWindow() {
        this.setVisible(false);
    }
    
    private void buildGUI() {
        this.setLayout(new BorderLayout());
        final Panel buildGrid = this.buildGrid();
        final Label label = new Label(" ");
        (this.moreButton = new Button("More")).addActionListener(this);
        final Panel panel = new Panel(new FlowLayout(1));
        panel.add(this.moreButton);
        this.add("North", label);
        this.add("Center", buildGrid);
        this.add("South", panel);
    }
    
    private Panel buildGrid() {
        final int n = 5;
        final String[] array = { "angry", "biggrin", "cool", "laugh", "ninja", "ohmy", "smile", "tongue", "wacko", "wub" };
        final int length = array.length;
        int n2 = length / n;
        if (length % n != 0) {
            ++n2;
        }
        final Panel panel = new Panel();
        panel.setLayout(new GridLayout(n2, n, 5, 5));
        for (int i = 0; i < length; ++i) {
            panel.add(this.getButton(array[i]));
        }
        final Panel panel2 = new Panel();
        panel2.setLayout(new FlowLayout(1));
        panel2.add(panel);
        return panel2;
    }
    
    private ImageButton getButton(final String id) {
        final ImageButton imageButton = new ImageButton();
        final Image image = this.pChat.paraConf.getSmiley().getImage(id);
        if (image != null) {
            this.prepareImage(image, this);
        }
        imageButton.setStaticImage(image);
        imageButton.setID(id);
        imageButton.addActionListener(this);
        return imageButton;
    }
}
