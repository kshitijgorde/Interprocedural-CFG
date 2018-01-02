// 
// Decompiled by Procyon v0.5.30
// 

package pclient.bsx;

import java.util.StringTokenizer;
import com.pchat.sc.StringUtil;
import java.awt.Panel;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.Component;
import com.pchat.sc.WindowUtil;
import java.awt.List;
import java.awt.Button;
import pclient.bsc.BaseChat;
import pclient.bsc.CommonInter;
import java.awt.event.ActionListener;
import java.awt.Frame;

public class SoundPop extends Frame implements ActionListener, CommonInter
{
    private BaseChat pChat;
    private Button testButton;
    private Button sendButton;
    private List soundList;
    
    public void process(final int n, final String[] array) {
    }
    
    public void setPara(final BaseChat pChat) {
        this.pChat = pChat;
        this.setTitle(this.pChat.paraConf.title());
        this.setSize(180, 420);
        this.buildGUI();
        this.enableEvents(64L);
        WindowUtil.center(this);
    }
    
    public void restart() {
        this.show();
        this.toFront();
        this.show();
    }
    
    public void destroy() {
        this.setVisible(false);
        this.dispose();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String selectedItem = this.soundList.getSelectedItem();
        if (actionEvent.getSource() == this.testButton) {
            if (selectedItem != null) {
                this.pChat.paraConf.play(selectedItem);
            }
            return;
        }
        if (actionEvent.getSource() == this.sendButton) {
            if (selectedItem != null) {
                this.pChat.chatModel.cmAudio(selectedItem);
                this.setVisible(false);
                final int selectedIndex = this.soundList.getSelectedIndex();
                if (selectedIndex >= 0) {
                    this.soundList.deselect(selectedIndex);
                }
            }
        }
    }
    
    public void processWindowEvent(final WindowEvent windowEvent) {
        if (windowEvent.getID() == 201) {
            this.setVisible(false);
        }
    }
    
    private void buildGUI() {
        this.setLayout(new BorderLayout());
        this.soundList = this.buildList();
        final Label label = new Label(" ");
        (this.testButton = new Button(this.pChat.paraConf.get("Bt.Au.Test", "Test"))).addActionListener(this);
        (this.sendButton = new Button(this.pChat.paraConf.get("Bt.Au.Send", "Send"))).addActionListener(this);
        final Panel panel = new Panel(new FlowLayout(1));
        panel.add(this.testButton);
        panel.add(this.sendButton);
        this.add("North", label);
        this.add("Center", this.soundList);
        this.add("South", panel);
    }
    
    private List buildList() {
        final List list = new List(16);
        String s = this.pChat.paraConf.get("Cf.Sounds");
        if (s == null) {
            return list;
        }
        if (StringUtil.isTrimmedEmpty(s)) {
            s = this.pChat.paraConf.serverConf("Cf.Sounds", "");
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ", ");
        while (stringTokenizer.hasMoreTokens()) {
            list.add(stringTokenizer.nextToken());
        }
        return list;
    }
}
