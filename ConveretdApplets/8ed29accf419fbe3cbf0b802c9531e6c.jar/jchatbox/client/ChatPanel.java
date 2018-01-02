// 
// Decompiled by Procyon v0.5.30
// 

package jchatbox.client;

import jchatbox.client.util.Debug;
import jchatbox.client.local.Msg;
import java.util.Vector;
import jchatbox.client.util.ChatException;
import jchatbox.client.local.Communication;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.Rectangle;
import java.awt.Font;
import java.awt.Color;
import jchatbox.client.util.Conf;
import java.awt.LayoutManager;
import jchatbox.client.util.RefreshThread;
import jchatbox.client.local.ChatroomDesc;
import java.awt.Container;
import java.awt.List;
import java.awt.TextArea;
import java.awt.Button;
import java.awt.TextField;
import jchatbox.client.gui.jcbLabel;
import jchatbox.client.gui.jcbPanel;

public class ChatPanel extends jcbPanel
{
    jcbLabel label_message;
    jcbLabel label_name;
    TextField textfield_message;
    Button button_send;
    TextArea textarea_chat;
    List list_users;
    Button button_refresh;
    Button button_logout;
    private String _$9018;
    private Container _$8460;
    private ChatroomDesc _$8461;
    private int _$495;
    private boolean _$9019;
    private RefreshThread _$9020;
    
    public ChatPanel(final Container $8460, final ChatroomDesc $8461) {
        this.label_message = new jcbLabel();
        this.label_name = new jcbLabel();
        this.textfield_message = new TextField();
        this.button_send = new Button();
        this.textarea_chat = new TextArea("*** jChatBox !\n*** http://www.javazoom.net\n[...]");
        this.list_users = new List();
        this.button_refresh = new Button();
        this.button_logout = new Button();
        this._$9018 = "ALL";
        this._$8460 = null;
        this._$8461 = null;
        this._$495 = -1;
        this._$9019 = true;
        this._$9020 = null;
        this._$8461 = $8461;
        this._$8460 = $8460;
        this._$495 = ((jcbApplet)$8460).getRefreshRate();
        if (this._$495 > 0) {
            this._$9019 = false;
        }
        try {
            this.jbInit();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    void jbInit() throws Exception {
        this.setLayout(null);
        this.setBackground(new Color(Integer.parseInt(Conf.CHATPANEL_COLOR, 16)));
        this.setImage(Conf.CHATPANEL_PICTURE);
        this.setFont(new Font(Conf.CHATPANEL_FONTNAME, Conf.CHATPANEL_FONTSTYLE, Conf.CHATPANEL_FONTSIZE));
        this.label_message.setForeground(new Color(Integer.parseInt(Conf.CHATPANEL_MESSAGE_LABEL_COLOR, 16)));
        this.label_message.setText(Conf.CHATPANEL_MESSAGE_LABEL);
        this.label_message.setBounds(new Rectangle(Conf.CHATPANEL_MESSAGE_LABEL_X, Conf.CHATPANEL_MESSAGE_LABEL_Y, Conf.CHATPANEL_MESSAGE_LABEL_W, Conf.CHATPANEL_MESSAGE_LABEL_H));
        this.label_name.setForeground(new Color(Integer.parseInt(Conf.CHATPANEL_NAME_LABEL_COLOR, 16)));
        this.label_name.setText(String.valueOf(String.valueOf(Conf.CHATPANEL_NAME_LABEL)).concat(String.valueOf(String.valueOf(this._$8461.getName()))));
        this.label_name.setBounds(new Rectangle(Conf.CHATPANEL_NAME_LABEL_X, Conf.CHATPANEL_NAME_LABEL_Y, Conf.CHATPANEL_NAME_LABEL_W, Conf.CHATPANEL_NAME_LABEL_H));
        this.textfield_message.setBackground(new Color(Integer.parseInt(Conf.CHATPANEL_MESSAGE_TEXTFIELD_BGCOLOR, 16)));
        this.textfield_message.setForeground(new Color(Integer.parseInt(Conf.CHATPANEL_MESSAGE_TEXTFIELD_COLOR, 16)));
        this.textfield_message.setBounds(new Rectangle(Conf.CHATPANEL_MESSAGE_TEXTFIELD_X, Conf.CHATPANEL_MESSAGE_TEXTFIELD_Y, Conf.CHATPANEL_MESSAGE_TEXTFIELD_W, Conf.CHATPANEL_MESSAGE_TEXTFIELD_H));
        this.textfield_message.addKeyListener(new KeyAdapter() {
            public void keyPressed(final KeyEvent keyEvent) {
                ChatPanel.this.textfield_message_keyPressed(keyEvent);
            }
        });
        this.textarea_chat.setEditable(false);
        this.textarea_chat.setBackground(new Color(Integer.parseInt(Conf.CHATPANEL_MESSAGES_TEXTAREA_BGCOLOR, 16)));
        this.textarea_chat.setForeground(new Color(Integer.parseInt(Conf.CHATPANEL_MESSAGES_TEXTAREA_COLOR, 16)));
        this.textarea_chat.setBounds(new Rectangle(Conf.CHATPANEL_MESSAGES_TEXTAREA_X, Conf.CHATPANEL_MESSAGES_TEXTAREA_Y, Conf.CHATPANEL_MESSAGES_TEXTAREA_W, Conf.CHATPANEL_MESSAGES_TEXTAREA_H));
        this.list_users.setBackground(new Color(Integer.parseInt(Conf.CHATPANEL_USERS_LIST_BGCOLOR, 16)));
        this.list_users.setForeground(new Color(Integer.parseInt(Conf.CHATPANEL_USERS_LIST_COLOR, 16)));
        this.list_users.setBounds(new Rectangle(Conf.CHATPANEL_USERS_LIST_X, Conf.CHATPANEL_USERS_LIST_Y, Conf.CHATPANEL_USERS_LIST_W, Conf.CHATPANEL_USERS_LIST_H));
        this.list_users.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent itemEvent) {
                ChatPanel.this.list_users_itemStateChanged(itemEvent);
            }
        });
        this.button_send.setBackground(new Color(Integer.parseInt(Conf.CHATPANEL_SEND_BUTTON_BGCOLOR, 16)));
        this.button_send.setForeground(new Color(Integer.parseInt(Conf.CHATPANEL_SEND_BUTTON_COLOR, 16)));
        this.button_send.setLabel(Conf.CHATPANEL_SEND_BUTTON_LABEL);
        this.button_send.setBounds(new Rectangle(Conf.CHATPANEL_SEND_BUTTON_X, Conf.CHATPANEL_SEND_BUTTON_Y, Conf.CHATPANEL_SEND_BUTTON_W, Conf.CHATPANEL_SEND_BUTTON_H));
        this.button_send.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                ChatPanel.this.button_send_actionPerformed(actionEvent);
            }
        });
        this.button_refresh.setBackground(new Color(Integer.parseInt(Conf.CHATPANEL_REFRESH_BUTTON_BGCOLOR, 16)));
        this.button_refresh.setForeground(new Color(Integer.parseInt(Conf.CHATPANEL_REFRESH_BUTTON_COLOR, 16)));
        this.button_refresh.setLabel(Conf.CHATPANEL_REFRESH_BUTTON_LABEL);
        this.button_refresh.setBounds(new Rectangle(Conf.CHATPANEL_REFRESH_BUTTON_X, Conf.CHATPANEL_REFRESH_BUTTON_Y, Conf.CHATPANEL_REFRESH_BUTTON_W, Conf.CHATPANEL_REFRESH_BUTTON_H));
        this.button_refresh.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                ChatPanel.this.button_refresh_actionPerformed(actionEvent);
            }
        });
        this.button_logout.setBackground(new Color(Integer.parseInt(Conf.CHATPANEL_LOGOUT_BUTTON_BGCOLOR, 16)));
        this.button_logout.setForeground(new Color(Integer.parseInt(Conf.CHATPANEL_LOGOUT_BUTTON_COLOR, 16)));
        this.button_logout.setLabel(Conf.CHATPANEL_LOGOUT_BUTTON_LABEL);
        this.button_logout.setBounds(new Rectangle(Conf.CHATPANEL_LOGOUT_BUTTON_X, Conf.CHATPANEL_LOGOUT_BUTTON_Y, Conf.CHATPANEL_LOGOUT_BUTTON_W, Conf.CHATPANEL_LOGOUT_BUTTON_H));
        this.button_logout.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                ChatPanel.this.button_logout_actionPerformed(actionEvent);
            }
        });
        this.add(this.textarea_chat, null);
        this.add(this.list_users, null);
        this.add(this.button_send, null);
        this.add(this.button_refresh, null);
        this.add(this.textfield_message, null);
        this.add(this.label_message, null);
        this.add(this.label_name, null);
        this.add(this.button_logout, null);
    }
    
    void button_send_actionPerformed(final ActionEvent actionEvent) {
        final String text = this.textfield_message.getText();
        final String s = "ALL";
        if (text != null && !text.equals("")) {
            this._$9023(text, s);
            this.textfield_message.setText("");
        }
    }
    
    void textfield_message_keyPressed(final KeyEvent keyEvent) {
        if (KeyEvent.getKeyText(keyEvent.getKeyCode()).equals("Enter")) {
            final String text = this.textfield_message.getText();
            if (text != null && !text.equals("")) {
                this._$9023(text, this._$9018);
                this.textfield_message.setText("");
            }
        }
    }
    
    private void _$9023(final String s, final String s2) {
        final Communication instance = Communication.getInstance(Conf.URL);
        try {
            this._$8461 = instance.doChat(this._$8461, s, s2);
            if (this._$9019) {
                this._$495 = Integer.parseInt(this._$8461.getRefresh());
            }
            this.updateUserList(this._$8461.getUserList());
            this.updateMsgs(this._$8461.getMsgs());
        }
        catch (ChatException ex) {
            this._$9020.disable();
            this._$619(1, this.getClass().getName(), "Send error : ".concat(String.valueOf(String.valueOf(ex.getMessage()))));
            this.textarea_chat.append("\n----- ".concat(String.valueOf(String.valueOf(ex.getMessage()))));
            this.textarea_chat.append("\n----- ".concat(String.valueOf(String.valueOf(Conf.PLEASELOGOUT))));
            this.textarea_chat.repaint();
        }
        catch (Exception ex2) {
            this._$9020.disable();
            this._$619(1, this.getClass().getName(), "Send error : ".concat(String.valueOf(String.valueOf(ex2.getMessage()))));
            this.textarea_chat.append("\n----- ".concat(String.valueOf(String.valueOf(Conf.INVALIDRESPONSE))));
            this.textarea_chat.append("\n----- ".concat(String.valueOf(String.valueOf(Conf.PLEASELOGOUT))));
            this.textarea_chat.repaint();
        }
    }
    
    void button_refresh_actionPerformed(final ActionEvent actionEvent) {
        this.performRefresh();
    }
    
    public void performRefresh() {
        final Communication instance = Communication.getInstance(Conf.URL);
        try {
            this._$8461 = instance.doRefresh(this._$8461);
            if (this._$9019) {
                this._$495 = Integer.parseInt(this._$8461.getRefresh());
            }
            this.updateUserList(this._$8461.getUserList());
            this.updateMsgs(this._$8461.getMsgs());
        }
        catch (ChatException ex) {
            this._$9020.disable();
            this._$619(1, this.getClass().getName(), "Refresh error : ".concat(String.valueOf(String.valueOf(ex.getMessage()))));
            this.textarea_chat.append("\n----- ".concat(String.valueOf(String.valueOf(ex.getMessage()))));
            this.textarea_chat.append("\n----- ".concat(String.valueOf(String.valueOf(Conf.PLEASELOGOUT))));
            this.textarea_chat.repaint();
        }
        catch (Exception ex2) {
            this._$9020.disable();
            this._$619(1, this.getClass().getName(), "Refresh error : ".concat(String.valueOf(String.valueOf(ex2.getMessage()))));
            this.textarea_chat.append("\n----- ".concat(String.valueOf(String.valueOf(Conf.INVALIDRESPONSE))));
            this.textarea_chat.append("\n----- ".concat(String.valueOf(String.valueOf(Conf.PLEASELOGOUT))));
            this.textarea_chat.repaint();
        }
    }
    
    void button_logout_actionPerformed(final ActionEvent actionEvent) {
        this.performLogout();
    }
    
    public void performLogout() {
        LoginPanel loginPanel = null;
        final Communication instance = Communication.getInstance(Conf.URL);
        try {
            loginPanel = new LoginPanel(this._$8460);
            this._$495 = -1;
            this._$9020.disable();
            loginPanel.setRooms(instance.doLogout());
            loginPanel.updateChatRooms();
        }
        catch (ChatException ex) {
            this._$619(1, this.getClass().getName(), "Logout error : ".concat(String.valueOf(String.valueOf(ex.getMessage()))));
        }
        catch (Exception ex2) {
            this._$619(1, this.getClass().getName(), "Logout error : ".concat(String.valueOf(String.valueOf(ex2.getMessage()))));
        }
        finally {
            this._$9020.disable();
            instance.resetSession();
            this._$8460.remove(this);
            this._$8460.add(loginPanel);
            loginPanel.setSize(Conf.APPLET_WIDTH, Conf.APPLET_HEIGHT);
        }
    }
    
    void list_users_itemStateChanged(final ItemEvent itemEvent) {
        final String selectedItem = this.list_users.getSelectedItem();
        if (selectedItem != null && !selectedItem.equals("") && !selectedItem.equals(Conf.CHATPANEL_USERS_LIST_SEPARATOR) && !selectedItem.equals(Conf.SEPARATOR2) && !selectedItem.equals(Conf.RIGHT)) {
            if (selectedItem.equals(Conf.CHATPANEL_USERS_LIST_TITLE)) {
                this.textarea_chat.append(String.valueOf(String.valueOf(Conf.PREPRIVMSG)).concat(String.valueOf(String.valueOf(Conf.CHATPANEL_MESSAGES_TEXTAREA_ALL))));
                this._$9018 = "ALL";
            }
            else {
                this.textarea_chat.append(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(Conf.PREPRIVMSG))).append(Conf.CHATPANEL_MESSAGES_TEXTAREA_PRIVATE).append(selectedItem))));
                this._$9018 = selectedItem;
            }
        }
    }
    
    public synchronized void updateUserList(final Vector vector) {
        final StringBuffer sb = new StringBuffer();
        this.list_users.removeAll();
        this.list_users.add(Conf.CHATPANEL_USERS_LIST_TITLE);
        this.list_users.add(Conf.CHATPANEL_USERS_LIST_SEPARATOR);
        for (int i = 0; i < vector.size(); ++i) {
            this.list_users.add(vector.elementAt(i));
        }
        this.list_users.add(Conf.SEPARATOR2);
        this.list_users.add(Conf.RIGHT);
    }
    
    public synchronized void updateMsgs(final Vector vector) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < vector.size(); ++i) {
            final Msg msg = vector.elementAt(i);
            String s;
            if (msg.getType() == 1) {
                s = String.valueOf(String.valueOf(new StringBuffer("*** ").append(msg.getMsg()).append(Conf.CHATPANEL_MESSAGES_TEXTAREA_JOIN)));
            }
            else if (msg.getType() == 2) {
                s = String.valueOf(String.valueOf(new StringBuffer("*** ").append(msg.getMsg()).append(Conf.CHATPANEL_MESSAGES_TEXTAREA_QUIT)));
            }
            else if (msg.getType() == 3) {
                s = String.valueOf(String.valueOf(new StringBuffer(">").append(msg.getFrom()).append("< ").append(msg.getMsg())));
            }
            else {
                s = String.valueOf(String.valueOf(new StringBuffer("<").append(msg.getFrom()).append("> ").append(msg.getMsg())));
            }
            this.textarea_chat.append("\n".concat(String.valueOf(String.valueOf(s))));
        }
    }
    
    public void setCurrentRoom(final ChatroomDesc $8461) {
        this._$8461 = $8461;
    }
    
    public int getRefreshRate() {
        return this._$495;
    }
    
    public void setRefreshRate(final int $495) {
        this._$495 = $495;
    }
    
    public void activateRefresh(final int $495) {
        if (this._$495 > 0) {
            (this._$9020 = new RefreshThread(this)).start();
            this._$619(5, this.getClass().getName(), "Refresh activated");
        }
        else if ($495 > 0) {
            this._$495 = $495;
            (this._$9020 = new RefreshThread(this)).start();
            this._$619(5, this.getClass().getName(), "Auto Refresh activated");
        }
        else {
            this._$619(5, this.getClass().getName(), "Refresh not activated");
        }
    }
    
    private void _$619(final int n, final String s, final String s2) {
        Debug.log(n, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append(":").append(s2))));
    }
}
