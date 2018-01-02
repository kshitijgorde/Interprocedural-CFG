// 
// Decompiled by Procyon v0.5.30
// 

package jchatbox.client;

import jchatbox.client.util.Debug;
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
import java.awt.LayoutManager;
import jchatbox.client.util.Conf;
import jchatbox.client.local.ChatroomDesc;
import java.util.Vector;
import java.awt.Container;
import java.awt.Button;
import java.awt.Choice;
import java.awt.TextField;
import jchatbox.client.gui.jcbLabel;
import jchatbox.client.gui.jcbPanel;

public class LoginPanel extends jcbPanel
{
    jcbLabel label_username;
    jcbLabel label_chatrooms;
    jcbLabel label_name;
    jcbLabel label_subject;
    jcbLabel label_date;
    jcbLabel label_welcome;
    jcbLabel label_error;
    TextField textfield_username;
    Choice choice_chatrooms;
    Button button_login;
    private Container _$8460;
    private ChatPanel _$8482;
    private Vector _$8483;
    private ChatroomDesc _$8461;
    
    public LoginPanel(final Container $8460) {
        this.label_username = new jcbLabel(Conf.LOGINPANEL_USERNAME_LABEL_PICTURE);
        this.label_chatrooms = new jcbLabel(Conf.LOGINPANEL_CHATROOMS_LABEL_PICTURE);
        this.label_name = new jcbLabel();
        this.label_subject = new jcbLabel();
        this.label_date = new jcbLabel();
        this.label_welcome = new jcbLabel();
        this.label_error = new jcbLabel();
        this.textfield_username = new TextField();
        this.choice_chatrooms = new Choice();
        this.button_login = new Button();
        this._$8460 = null;
        this._$8482 = null;
        this._$8483 = null;
        this._$8461 = null;
        this._$8482 = null;
        this._$8460 = $8460;
        try {
            this.jbInit();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    void jbInit() throws Exception {
        this.setLayout(null);
        this.setBackground(new Color(Integer.parseInt(Conf.LOGINPANEL_COLOR, 16)));
        this.setImage(Conf.LOGINPANEL_PICTURE);
        this.setFont(new Font(Conf.LOGINPANEL_FONTNAME, Conf.LOGINPANEL_FONTSTYLE, Conf.LOGINPANEL_FONTSIZE));
        this.label_username.setForeground(new Color(Integer.parseInt(Conf.LOGINPANEL_USERNAME_LABEL_COLOR, 16)));
        this.label_username.setText(Conf.LOGINPANEL_USERNAME_LABEL);
        this.label_username.setBounds(new Rectangle(Conf.LOGINPANEL_USERNAME_LABEL_X, Conf.LOGINPANEL_USERNAME_LABEL_Y, Conf.LOGINPANEL_USERNAME_LABEL_W, Conf.LOGINPANEL_USERNAME_LABEL_H));
        this.label_chatrooms.setForeground(new Color(Integer.parseInt(Conf.LOGINPANEL_CHATROOMS_LABEL_COLOR, 16)));
        this.label_chatrooms.setText(Conf.LOGINPANEL_CHATROOMS_LABEL);
        this.label_chatrooms.setBounds(new Rectangle(Conf.LOGINPANEL_CHATROOMS_LABEL_X, Conf.LOGINPANEL_CHATROOMS_LABEL_Y, Conf.LOGINPANEL_CHATROOMS_LABEL_W, Conf.LOGINPANEL_CHATROOMS_LABEL_H));
        this.label_name.setForeground(new Color(Integer.parseInt(Conf.LOGINPANEL_NAME_LABEL_COLOR, 16)));
        this.label_name.setText(Conf.LOGINPANEL_NAME_LABEL);
        this.label_name.setBounds(new Rectangle(Conf.LOGINPANEL_NAME_LABEL_X, Conf.LOGINPANEL_NAME_LABEL_Y, Conf.LOGINPANEL_NAME_LABEL_W, Conf.LOGINPANEL_NAME_LABEL_H));
        this.label_subject.setForeground(new Color(Integer.parseInt(Conf.LOGINPANEL_SUBJECT_LABEL_COLOR, 16)));
        this.label_subject.setText(Conf.LOGINPANEL_SUBJECT_LABEL);
        this.label_subject.setBounds(new Rectangle(Conf.LOGINPANEL_SUBJECT_LABEL_X, Conf.LOGINPANEL_SUBJECT_LABEL_Y, Conf.LOGINPANEL_SUBJECT_LABEL_W, Conf.LOGINPANEL_SUBJECT_LABEL_H));
        this.label_date.setForeground(new Color(Integer.parseInt(Conf.LOGINPANEL_DATE_LABEL_COLOR, 16)));
        this.label_date.setText(Conf.LOGINPANEL_DATE_LABEL);
        this.label_date.setBounds(new Rectangle(Conf.LOGINPANEL_DATE_LABEL_X, Conf.LOGINPANEL_DATE_LABEL_Y, Conf.LOGINPANEL_DATE_LABEL_W, Conf.LOGINPANEL_DATE_LABEL_H));
        this.label_welcome.setForeground(new Color(Integer.parseInt(Conf.LOGINPANEL_WELCOME_LABEL_COLOR, 16)));
        this.label_welcome.setText(Conf.LOGINPANEL_WELCOME_LABEL);
        this.label_welcome.setBounds(new Rectangle(Conf.LOGINPANEL_WELCOME_LABEL_X, Conf.LOGINPANEL_WELCOME_LABEL_Y, Conf.LOGINPANEL_WELCOME_LABEL_W, Conf.LOGINPANEL_WELCOME_LABEL_H));
        this.label_error.setForeground(new Color(Integer.parseInt(Conf.LOGINPANEL_ERROR_LABEL_COLOR, 16)));
        this.label_error.setText(Conf.LOGINPANEL_ERROR_LABEL);
        this.label_error.setBounds(new Rectangle(Conf.LOGINPANEL_ERROR_LABEL_X, Conf.LOGINPANEL_ERROR_LABEL_Y, Conf.LOGINPANEL_ERROR_LABEL_W, Conf.LOGINPANEL_ERROR_LABEL_H));
        this.textfield_username.setBackground(new Color(Integer.parseInt(Conf.LOGINPANEL_USERNAME_TEXTFIELD_BGCOLOR, 16)));
        this.textfield_username.setForeground(new Color(Integer.parseInt(Conf.LOGINPANEL_USERNAME_TEXTFIELD_COLOR, 16)));
        this.textfield_username.setBounds(new Rectangle(Conf.LOGINPANEL_USERNAME_TEXTFIELD_X, Conf.LOGINPANEL_USERNAME_TEXTFIELD_Y, Conf.LOGINPANEL_USERNAME_TEXTFIELD_W, Conf.LOGINPANEL_USERNAME_TEXTFIELD_H));
        this.textfield_username.addKeyListener(new KeyAdapter() {
            public void keyPressed(final KeyEvent keyEvent) {
                LoginPanel.this.textfield_username_keyPressed(keyEvent);
            }
        });
        this.choice_chatrooms.setBackground(new Color(Integer.parseInt(Conf.LOGINPANEL_CHATROOMS_CHOICE_BGCOLOR, 16)));
        this.choice_chatrooms.setForeground(new Color(Integer.parseInt(Conf.LOGINPANEL_CHATROOMS_CHOICE_COLOR, 16)));
        this.choice_chatrooms.setBounds(new Rectangle(Conf.LOGINPANEL_CHATROOMS_CHOICE_X, Conf.LOGINPANEL_CHATROOMS_CHOICE_Y, Conf.LOGINPANEL_CHATROOMS_CHOICE_W, Conf.LOGINPANEL_CHATROOMS_CHOICE_H));
        this.choice_chatrooms.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent itemEvent) {
                LoginPanel.this.choice_chatrooms_itemStateChanged(itemEvent);
            }
        });
        this.button_login.setBackground(new Color(Integer.parseInt(Conf.LOGINPANEL_LOGIN_BUTTON_BGCOLOR, 16)));
        this.button_login.setForeground(new Color(Integer.parseInt(Conf.LOGINPANEL_LOGIN_BUTTON_COLOR, 16)));
        this.button_login.setLabel(Conf.LOGINPANEL_LOGIN_BUTTON_LABEL);
        this.button_login.setBounds(new Rectangle(Conf.LOGINPANEL_LOGIN_BUTTON_X, Conf.LOGINPANEL_LOGIN_BUTTON_Y, Conf.LOGINPANEL_LOGIN_BUTTON_W, Conf.LOGINPANEL_LOGIN_BUTTON_H));
        this.button_login.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                LoginPanel.this.button_login_actionPerformed(actionEvent);
            }
        });
        this.add(this.label_subject, null);
        this.add(this.label_name, null);
        this.add(this.label_date, null);
        this.add(this.label_username, null);
        this.add(this.textfield_username, null);
        this.add(this.button_login, null);
        this.add(this.choice_chatrooms, null);
        this.add(this.label_chatrooms, null);
        this.add(this.label_error, null);
        this.add(this.label_welcome, null);
    }
    
    void choice_chatrooms_itemStateChanged(final ItemEvent itemEvent) {
        final String s = (String)itemEvent.getItem();
        this._$8461 = null;
        for (int i = 0; i < this._$8483.size(); ++i) {
            final ChatroomDesc $8461 = this._$8483.elementAt(i);
            if ($8461.getName().equals(s)) {
                this._$8461 = $8461;
                break;
            }
        }
        if (this._$8461 != null) {
            Conf.loadResources(this._$8461.getLanguage());
            this.label_username.setText(Conf.LOGINPANEL_USERNAME_LABEL);
            this.label_chatrooms.setText(Conf.LOGINPANEL_CHATROOMS_LABEL);
            this.button_login.setLabel(Conf.LOGINPANEL_LOGIN_BUTTON_LABEL);
            this.label_name.setText(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(Conf.LOGINPANEL_NAME_LABEL))).append(this._$8461.getName()).append(" (").append(this._$8461.getTotalUsers()).append("/").append(this._$8461.getMaxUsers()).append(")"))));
            this.label_subject.setText(String.valueOf(String.valueOf(Conf.LOGINPANEL_SUBJECT_LABEL)).concat(String.valueOf(String.valueOf(this._$8461.getSubject()))));
            this.label_date.setText(String.valueOf(String.valueOf(Conf.LOGINPANEL_DATE_LABEL)).concat(String.valueOf(String.valueOf(this._$8461.getDate()))));
            final int selectedIndex = this.choice_chatrooms.getSelectedIndex();
            this.choice_chatrooms.remove(0);
            this.choice_chatrooms.insert(Conf.LOGINPANEL_CHATROOMS_CHOICE_FIRSTITEM, 0);
            this.choice_chatrooms.select(selectedIndex);
        }
        else {
            this.label_name.setText(Conf.LOGINPANEL_NAME_LABEL);
            this.label_subject.setText(Conf.LOGINPANEL_SUBJECT_LABEL);
            this.label_date.setText(Conf.LOGINPANEL_DATE_LABEL);
        }
    }
    
    void button_login_actionPerformed(final ActionEvent actionEvent) {
        this._$8487();
    }
    
    void textfield_username_keyPressed(final KeyEvent keyEvent) {
        if (KeyEvent.getKeyText(keyEvent.getKeyCode()).equals("Enter")) {
            this._$8487();
        }
    }
    
    private void _$8487() {
        this.label_error.setText("");
        final String text = this.textfield_username.getText();
        if (text != null && !text.equals("") && this._$8461 != null && this._$8461.getID() != null && !this._$8461.getID().equals("")) {
            final Communication instance = Communication.getInstance(Conf.URL);
            try {
                final ChatroomDesc doLogin = instance.doLogin(this._$8461, text);
                this._$619(10, this.getClass().getName(), "ChatroomDesc : ".concat(String.valueOf(String.valueOf(doLogin))));
                this._$8482 = new ChatPanel(this._$8460, doLogin);
                this._$8460.remove(this);
                this._$8460.add(this._$8482);
                this._$8482.setSize(Conf.APPLET_WIDTH, Conf.APPLET_HEIGHT);
                this._$8482.updateUserList(doLogin.getUserList());
                this._$8482.updateMsgs(doLogin.getMsgs());
                this._$8482.activateRefresh(Integer.parseInt(doLogin.getRefresh()));
            }
            catch (ChatException ex) {
                this._$619(10, this.getClass().getName(), "Login error : ".concat(String.valueOf(String.valueOf(ex.getMessage()))));
                this.label_error.setText(ex.getMessage());
            }
            catch (Exception ex2) {
                this._$619(1, this.getClass().getName(), "Login error : ".concat(String.valueOf(String.valueOf(ex2.getMessage()))));
                this.label_error.setText("Server not available");
            }
        }
    }
    
    public void updateChatRooms() {
        this.choice_chatrooms.addItem(Conf.LOGINPANEL_CHATROOMS_CHOICE_FIRSTITEM);
        for (int i = 0; i < this._$8483.size(); ++i) {
            this.choice_chatrooms.addItem(((ChatroomDesc)this._$8483.elementAt(i)).getName());
        }
    }
    
    public void setRooms(final Vector $8483) {
        this._$8483 = $8483;
    }
    
    public void setErrorMsg(final String text) {
        if (this.label_error != null) {
            this.label_error.setText(text);
        }
    }
    
    public ChatPanel getChatPanel() {
        return this._$8482;
    }
    
    private void _$619(final int n, final String s, final String s2) {
        Debug.log(n, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append(":").append(s2))));
    }
}
