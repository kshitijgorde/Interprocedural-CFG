// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adv;

import javax.swing.Icon;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.JLabel;
import javax.swing.JComponent;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.SwingUtilities;
import java.awt.event.ActionEvent;
import pclient.shd.ClientUtil;
import java.awt.Component;
import com.pchat.sc.WindowUtil;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class ReconnectList extends JFrame implements ActionListener, Runnable
{
    private AppletSpice appletChat;
    private SimpleBankQueue itemQueue;
    protected JList historyList;
    private DefaultListModel listModel;
    private JButton cancelButton;
    private JOptionPane optionPane;
    private String COUNT_DOWN;
    protected boolean cancelClicked;
    private boolean firstTime;
    
    public ReconnectList(final AppletSpice appletChat) {
        this.COUNT_DOWN = "Your Internet connection to the chat room has dropped. The seconds remaining until the chat room attempts to automatically reconnect you are:";
        this.firstTime = true;
        this.appletChat = appletChat;
        this.itemQueue = new SimpleBankQueue();
        this.initData();
    }
    
    public void run() {
        try {
            this.doChanges();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void initData() {
        this.setTitle(this.appletChat.paraConf.title());
        this.setSize(320, 340);
        this.setDefaultCloseOperation(1);
        this.buildGUI();
    }
    
    public void tryReconnect() {
        this.addMsg(new Date().toString());
        this.setVisible(true);
        if (this.firstTime) {
            try {
                WindowUtil.center(this.appletChat.paraConf.getApplet(), this);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            this.firstTime = false;
        }
        this.cancelButton.setEnabled(true);
        this.cancelClicked = false;
        for (int i = 20; i >= 0; --i) {
            this.optionPane.setMessage(this.createText(this.COUNT_DOWN + " " + i));
            ClientUtil.doze(1000);
            if (this.cancelClicked) {
                return;
            }
        }
        this.cancelButton.setEnabled(false);
        if (this.appletChat.chatModel.cmIsConnected()) {
            return;
        }
        this.appletChat.reconnectAuto();
    }
    
    private void addMsg(final String s) {
        this.clearOld();
        this.listModel.addElement(s);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (this.appletChat.BTN_CANCEL.equals(actionEvent.getActionCommand())) {
            this.cancelClicked = true;
        }
    }
    
    private void clearOld() {
        if (this.listModel.getSize() < 1000) {
            return;
        }
        for (int i = 0; i < 100; ++i) {
            if (this.listModel.getSize() > 0) {
                this.listModel.remove(0);
            }
        }
    }
    
    private void queue(final int type, final Object obj) {
        final SimpleQueueItem simpleQueueItem = new SimpleQueueItem();
        simpleQueueItem.type = type;
        simpleQueueItem.obj = obj;
        this.itemQueue.add(simpleQueueItem);
        SwingUtilities.invokeLater(this);
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
                    this.optionPane.setMessage(this.createText((String)simpleQueueItem.obj));
                    break;
                }
                default: {
                    System.err.println("Err3229," + simpleQueueItem);
                    break;
                }
            }
        }
    }
    
    private void buildGUI() {
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(this.getCount(), "North");
        this.getContentPane().add(this.getList(), "Center");
    }
    
    private JComponent getList() {
        final JLabel label = new JLabel(this.appletChat.paraConf.get("Lb.Rec.Hs", "Reconnection Attempts History"));
        this.listModel = new DefaultListModel();
        this.historyList = new JList(this.listModel);
        final JScrollPane scrollPane = new JScrollPane(this.historyList);
        scrollPane.getViewport().setScrollMode(2);
        this.historyList.setSelectionMode(0);
        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(label, "North");
        panel.add(scrollPane, "Center");
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return panel;
    }
    
    private JComponent getCount() {
        this.COUNT_DOWN = this.appletChat.paraConf.get("Lb.Rec.Cd", this.COUNT_DOWN);
        final JButton cancelButton = new JButton(this.appletChat.BTN_CANCEL);
        cancelButton.setActionCommand(this.appletChat.BTN_CANCEL);
        cancelButton.addActionListener(this);
        this.optionPane = new JOptionPane(this.createText(this.COUNT_DOWN), 1, 2, null, new Object[] { cancelButton }, null);
        this.cancelButton = cancelButton;
        return this.optionPane;
    }
    
    private String createText(final String s) {
        return "<html><p>" + s + "</p></html>";
    }
}
