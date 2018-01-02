// 
// Decompiled by Procyon v0.5.30
// 

package jap;

import java.awt.Dimension;
import java.awt.image.ImageObserver;
import anon.util.JAPMessages;
import java.awt.Cursor;
import java.awt.Graphics;
import logging.LogHolder;
import logging.LogType;
import java.awt.LayoutManager;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import gui.GUIUtils;
import java.awt.Image;
import javax.swing.JLabel;
import java.util.Random;
import gui.IStatusLine;
import javax.swing.JPanel;

public class StatusPanel extends JPanel implements Runnable, IStatusLine
{
    private static final String MSG_CLICK_HERE;
    private final Object SYNC_MSG;
    private Random m_Random;
    private JLabel m_button;
    private static final int ICON_HEIGHT = 15;
    private static final int ICON_WIDTH = 16;
    private Image m_imageError;
    private Image m_imageInformation;
    private Image m_imageWarning;
    private MessagesListNode m_firstMessage;
    private volatile boolean m_bRun;
    private volatile int m_aktY;
    private Thread m_Thread;
    static /* synthetic */ Class class$jap$StatusPanel;
    
    public StatusPanel(final JLabel button) {
        this.SYNC_MSG = new Object();
        this.m_imageInformation = GUIUtils.loadImageIcon("information.gif", true, false).getImage();
        this.m_imageError = GUIUtils.loadImageIcon("error.gif", true, false).getImage();
        this.m_imageWarning = GUIUtils.loadImageIcon("warning.gif", true, false).getImage();
        this.m_button = button;
        if (this.m_button != null) {
            this.m_button.addMouseListener(new MouseAdapter() {
                public void mouseClicked(final MouseEvent mouseEvent) {
                    final boolean b = false;
                    ActionListener buttonAction = null;
                    if (b) {
                        return;
                    }
                    synchronized (StatusPanel.this.SYNC_MSG) {
                        final MessagesListNode access$100 = StatusPanel.this.m_firstMessage;
                        if (access$100 != null && access$100.buttonAction != null) {
                            buttonAction = access$100.buttonAction;
                        }
                    }
                    if (buttonAction != null) {
                        buttonAction.actionPerformed(new ActionEvent(StatusPanel.this, mouseEvent.getID(), "mouseClicked"));
                        StatusPanel.this.repaint();
                    }
                }
            });
        }
        this.addMouseListener(new MouseAdapter() {
            boolean m_bClicked = false;
            
            public void mouseClicked(final MouseEvent mouseEvent) {
                ActionListener listener = null;
                if (this.m_bClicked) {
                    return;
                }
                this.m_bClicked = true;
                synchronized (StatusPanel.this.SYNC_MSG) {
                    final MessagesListNode access$100 = StatusPanel.this.m_firstMessage;
                    if (access$100 != null) {
                        listener = access$100.listener;
                    }
                }
                if (listener != null) {
                    listener.actionPerformed(new ActionEvent(StatusPanel.this, mouseEvent.getID(), "mouseClicked"));
                    StatusPanel.this.repaint();
                }
                this.m_bClicked = false;
            }
        });
        this.m_Random = new Random();
        this.setLayout(null);
        this.m_firstMessage = null;
        (this.m_Thread = new Thread(this, "StatusPanel")).setDaemon(true);
        this.m_bRun = true;
        this.m_Thread.start();
    }
    
    public void finalize() {
        this.m_bRun = false;
        try {
            this.m_Thread.interrupt();
            this.m_Thread.join();
        }
        catch (Exception ex) {}
    }
    
    public int addStatusMsg(final String s, final int n, final boolean b) {
        return this.addStatusMsg(s, n, b, null, null);
    }
    
    public int addStatusMsg(final String s, final int n, final boolean b, final ActionListener actionListener) {
        return this.addStatusMsg(s, n, b, actionListener, null);
    }
    
    public int addStatusMsg(final String msg, final int n, final boolean b, final ActionListener listener, final ButtonListener buttonAction) {
        MessagesListNode next = null;
        synchronized (this.SYNC_MSG) {
            next = new MessagesListNode();
            next.listener = listener;
            next.buttonAction = buttonAction;
            next.m_Msg = msg;
            next.m_Id = Math.abs(this.m_Random.nextInt());
            if (b) {
                next.m_DisplayCount = 2;
            }
            if (n == 2) {
                next.m_Icon = this.m_imageWarning;
            }
            else if (n == 1) {
                next.m_Icon = this.m_imageInformation;
            }
            else if (n == 0) {
                next.m_Icon = this.m_imageError;
            }
            if (this.m_firstMessage == null) {
                this.m_firstMessage = next;
                next.m_Next = next;
                this.m_aktY = 15;
            }
            else {
                next.m_Next = this.m_firstMessage.m_Next;
                this.m_firstMessage.m_Next = next;
            }
            this.m_Thread.interrupt();
        }
        return next.m_Id;
    }
    
    public void removeStatusMsg(final int n) {
        synchronized (this.SYNC_MSG) {
            if (this.m_firstMessage == null) {
                LogHolder.log(7, LogType.PAY, "Could not remove message with id of " + n + " since there are no messages at all");
                this.m_aktY = 15;
                return;
            }
            if (this.m_firstMessage.m_Id == n && this.m_firstMessage.m_Next == this.m_firstMessage) {
                this.m_firstMessage = null;
                this.m_aktY = 15;
                this.m_Thread.interrupt();
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex) {}
                this.m_Thread.interrupt();
            }
            else {
                MessagesListNode messagesListNode = this.m_firstMessage;
                MessagesListNode messagesListNode2 = null;
                while (messagesListNode != null) {
                    if (messagesListNode.m_Next.m_Id == n) {
                        messagesListNode2 = messagesListNode;
                        messagesListNode = messagesListNode.m_Next;
                        break;
                    }
                    messagesListNode = messagesListNode.m_Next;
                    if (messagesListNode == this.m_firstMessage) {
                        return;
                    }
                }
                if (messagesListNode == this.m_firstMessage) {
                    this.m_firstMessage = messagesListNode.m_Next;
                    this.m_aktY = 15;
                    this.m_Thread.interrupt();
                    try {
                        Thread.sleep(100L);
                    }
                    catch (InterruptedException ex2) {}
                    this.m_Thread.interrupt();
                }
                messagesListNode2.m_Next = messagesListNode.m_Next;
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        if (graphics == null) {
            return;
        }
        super.paint(graphics);
        synchronized (this.SYNC_MSG) {
            if (this.m_firstMessage != null) {
                String s = this.m_firstMessage.m_Msg;
                if (this.m_firstMessage.buttonAction != null && !this.m_button.isVisible()) {
                    this.m_button.setVisible(this.m_firstMessage.buttonAction.isButtonShown());
                }
                else if (this.m_firstMessage.buttonAction == null && this.m_button.isVisible()) {
                    this.m_button.setVisible(false);
                }
                if (this.m_firstMessage.listener != null) {
                    this.setCursor(Cursor.getPredefinedCursor(12));
                    s = s + " (" + JAPMessages.getString(StatusPanel.MSG_CLICK_HERE) + ")";
                    this.setToolTipText(JAPMessages.getString(StatusPanel.MSG_CLICK_HERE));
                }
                else {
                    this.setToolTipText(null);
                    this.setCursor(Cursor.getDefaultCursor());
                }
                graphics.drawString(s, 18, graphics.getFont().getSize() - this.m_aktY);
                if (this.m_firstMessage.m_Icon != null) {
                    graphics.drawImage(this.m_firstMessage.m_Icon, 0, (this.getSize().height - this.m_firstMessage.m_Icon.getHeight(this)) / 2 - this.m_aktY, this);
                }
            }
            else {
                this.setToolTipText(null);
                this.setCursor(Cursor.getDefaultCursor());
                this.m_button.setVisible(false);
            }
        }
    }
    
    public Dimension getPreferredSize() {
        if (this.m_button != null) {
            return new Dimension(100, Math.max(18, this.m_button.getSize().height));
        }
        return new Dimension(100, 18);
    }
    
    public Dimension getMinimumSize() {
        return this.getPreferredSize();
    }
    
    public void run() {
        try {
            while (this.m_bRun) {
                MessagesListNode firstMessage = null;
                try {
                    Thread.sleep(10000L);
                }
                catch (InterruptedException ex) {
                    if (!this.m_bRun) {
                        return;
                    }
                }
                synchronized (this.SYNC_MSG) {
                    if (this.m_firstMessage != null && this.m_firstMessage.m_DisplayCount == 0) {
                        this.removeStatusMsg(this.m_firstMessage.m_Id);
                    }
                    if (this.m_firstMessage == null) {
                        this.repaint();
                        continue;
                    }
                    if (this.m_firstMessage.m_DisplayCount > 0) {
                        firstMessage = this.m_firstMessage;
                        final MessagesListNode firstMessage2 = this.m_firstMessage;
                        --firstMessage2.m_DisplayCount;
                    }
                    if (this.m_firstMessage == null) {
                        this.m_aktY = 15;
                        this.repaint();
                        continue;
                    }
                    if (this.m_firstMessage.m_Next == this.m_firstMessage && this.m_firstMessage.listener != null && this.m_aktY == 0) {
                        this.repaint();
                        continue;
                    }
                    this.m_firstMessage = this.m_firstMessage.m_Next;
                    this.m_aktY = 15;
                }
                for (int n = 0; n < 15 && this.m_bRun; ++n) {
                    try {
                        Thread.sleep(100L);
                        --this.m_aktY;
                        this.repaint();
                    }
                    catch (InterruptedException ex2) {
                        synchronized (this.SYNC_MSG) {
                            if (this.m_firstMessage != null) {
                                if (this.m_firstMessage.m_DisplayCount >= 0 && this.m_firstMessage == firstMessage) {
                                    final MessagesListNode firstMessage3 = this.m_firstMessage;
                                    ++firstMessage3.m_DisplayCount;
                                }
                                this.m_aktY = 15;
                                n = -1;
                                this.m_firstMessage = this.m_firstMessage.m_Next;
                            }
                        }
                    }
                }
            }
        }
        catch (Exception ex3) {}
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        MSG_CLICK_HERE = ((StatusPanel.class$jap$StatusPanel == null) ? (StatusPanel.class$jap$StatusPanel = class$("jap.StatusPanel")) : StatusPanel.class$jap$StatusPanel).getName() + "_clickHere";
    }
    
    public abstract static class ButtonListener implements ActionListener
    {
        public boolean isButtonShown() {
            return true;
        }
        
        public abstract void actionPerformed(final ActionEvent p0);
    }
    
    private final class MessagesListNode
    {
        ActionListener listener;
        ButtonListener buttonAction;
        String m_Msg;
        Image m_Icon;
        int m_Id;
        MessagesListNode m_Next;
        int m_DisplayCount;
        
        private MessagesListNode() {
            this.m_DisplayCount = -1;
        }
    }
}
