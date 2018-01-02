// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adv;

import javax.swing.JOptionPane;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComponent;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import com.pchat.sc.MsgOptions;
import javax.swing.SwingUtilities;
import com.pchat.sc.StringUtil;
import pclient.shd.PlaybackMsg;
import java.awt.Component;
import com.pchat.sc.WindowUtil;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class PlayFrame extends JFrame implements ActionListener, Runnable
{
    private static final String AT_DW = "dw";
    private AppletSpice appletChat;
    private StageBox chatRender;
    private SimpleBankQueue itemQueue;
    private long lastRetClick;
    
    public PlayFrame(final AppletSpice para) {
        this.lastRetClick = 0L;
        this.itemQueue = new SimpleBankQueue();
        this.setPara(para);
    }
    
    public void run() {
        try {
            this.doChanges();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void setPara(final AppletSpice appletChat) {
        this.appletChat = appletChat;
        this.setTitle(this.appletChat.paraConf.title());
        this.setSize(480, 300);
        this.setDefaultCloseOperation(1);
        this.buildGUI();
        WindowUtil.center(this);
    }
    
    public void retrieve() {
        final long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.lastRetClick < 1500L) {
            return;
        }
        this.lastRetClick = currentTimeMillis;
        this.appletChat.chatModel.cmPlayback();
    }
    
    public void destroy() {
        this.setVisible(false);
        this.dispose();
    }
    
    public void clear() {
        this.chatRender.clearText();
    }
    
    public void addMsg(final PlaybackMsg playbackMsg) {
        if (StringUtil.isEmpty(playbackMsg.sender)) {
            this.appletChat.paraConf.printer().print("no more PB");
            final String value = this.appletChat.paraConf.get("Msg.NoMore", "No More Messages");
            final SimpleQueueItem simpleQueueItem = new SimpleQueueItem();
            simpleQueueItem.type = 8;
            simpleQueueItem.obj = value;
            this.itemQueue.add(simpleQueueItem);
            SwingUtilities.invokeLater(this);
            return;
        }
        this.chatRender.prependPublic(playbackMsg.sender, playbackMsg.msg, null, playbackMsg.date);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if ("dw".equals(actionEvent.getActionCommand())) {
            this.retrieve();
        }
    }
    
    private void buildGUI() {
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(this.getChatBox(), "Center");
        this.getContentPane().add(this.getButton(), "South");
    }
    
    private JComponent getChatBox() {
        (this.chatRender = new StageBox(this.appletChat)).setChoice(this.appletChat.userChoice);
        this.chatRender.clickPrivate(false);
        this.chatRender.enableTime(false);
        return (JComponent)this.chatRender.getComp();
    }
    
    private JComponent getButton() {
        final JButton button = new JButton(this.appletChat.paraConf.get("Bt.Download", "Display More Messages"));
        button.setActionCommand("dw");
        button.addActionListener(this);
        button.setAlignmentX(0.5f);
        final JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(button);
        return panel;
    }
    
    private void doChanges() {
        final SimpleQueueItem[] dequeueAll = this.itemQueue.dequeueAll();
        if (dequeueAll == null) {
            return;
        }
        for (int i = 0; i < dequeueAll.length; ++i) {
            final SimpleQueueItem simpleQueueItem = dequeueAll[i];
            switch (simpleQueueItem.type) {
                case 8: {
                    JOptionPane.showMessageDialog(this, simpleQueueItem.obj);
                    break;
                }
                default: {
                    System.err.println("Err7932," + simpleQueueItem);
                    break;
                }
            }
        }
    }
}
