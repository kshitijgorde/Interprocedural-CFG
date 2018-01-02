import java.awt.Event;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class IRCQNetChannelWnd extends IRCQNetPanel
{
    private IRCQNetUsersList Users;
    private RichEditCtrl Chat;
    private Panel ThePanel;
    private String Tmp;
    private IRCQNetSFrame Header;
    private GridBagConstraints c;
    private GridBagLayout Grid;
    private IRCQNet theApp;
    private String LastNick;
    private int width;
    private int height;
    private IRCQNetActionsPanel ActionPanel;
    private int newLineAt;
    private int lineAt;
    
    public IRCQNetChannelWnd(final String label, final IRCQNet theApp) {
        super(theApp);
        this.ThePanel = new Panel();
        this.Tmp = new String();
        this.c = new GridBagConstraints();
        this.Grid = new GridBagLayout();
        this.LastNick = "";
        this.theApp = theApp;
        this.setLabel(label);
        this.initControls();
        this.initWindow();
    }
    
    public void Print(final String s) {
        this.Chat.appendText(s);
    }
    
    public void initControls() {
        this.Users = new IRCQNetUsersList(this);
        this.Header = new IRCQNetSFrame(this.theApp);
        this.Chat = new RichEditCtrl(this);
        this.ActionPanel = new IRCQNetActionsPanel(this.theApp, super.Label);
    }
    
    public void initWindow() {
        this.Users.setBackground(IRCQNetColors.controlColor);
        this.ThePanel.setBackground(IRCQNetColors.controlColor);
        this.ThePanel.setLayout(new BorderLayout());
        this.ThePanel.add("Center", this.ActionPanel);
        this.setLayout(this.Grid);
        this.Users.setMultipleSelections(false);
        this.c.weightx = 30.0;
        this.c.weighty = 0.0;
        this.c.gridx = 0;
        this.c.gridwidth = 0;
        this.c.anchor = 11;
        this.Grid.setConstraints(this.Header, this.c);
        this.add(this.Header);
        this.c.gridwidth = 2;
        this.c.gridheight = -1;
        this.c.fill = 1;
        this.c.weightx = 30.0;
        this.c.weighty = 30.0;
        this.c.anchor = 10;
        this.Grid.setConstraints(this.Chat, this.c);
        this.add(this.Chat);
        this.c.weightx = 0.0;
        this.c.weighty = 0.0;
        this.c.gridx = 2;
        this.c.anchor = 13;
        this.c.gridwidth = 0;
        this.Grid.setConstraints(this.Users, this.c);
        this.add(this.Users);
        this.c.weightx = 30.0;
        this.c.weighty = 0.0;
        this.c.gridx = 0;
        this.c.anchor = 15;
        this.c.gridwidth = 0;
        this.Grid.setConstraints(this.ThePanel, this.c);
        this.add(this.ThePanel);
        this.Users.show();
        this.Chat.show();
        this.Header.show();
        this.Header.setLabel(super.Label);
        this.ThePanel.show();
        this.ActionPanel.show();
        this.validate();
    }
    
    public char cast(final char c) {
        final char lowerCase = Character.toLowerCase(c);
        if (lowerCase == '@') {
            return '\u0001';
        }
        if (lowerCase == '+') {
            return '\u0002';
        }
        if (lowerCase >= 'a' && lowerCase <= 'z') {
            return (char)(lowerCase + ' ');
        }
        if (lowerCase >= '0' && lowerCase <= '9') {
            return (char)(lowerCase + '\u00c8');
        }
        return lowerCase;
    }
    
    public int Compare(final String s, final String s2) {
        final int length = s.length();
        final int length2 = s2.length();
        int n;
        if (length < length2) {
            n = length;
        }
        else {
            n = length2;
        }
        for (int i = 0; i < n; ++i) {
            final char cast = this.cast(s.charAt(i));
            final char cast2 = this.cast(s2.charAt(i));
            if (cast < cast2) {
                return 1;
            }
            if (cast > cast2) {
                return -1;
            }
        }
        if (length < length2) {
            return 1;
        }
        if (length <= length2) {
            return 0;
        }
        return -1;
    }
    
    public void addUser(final String s) {
        if (this.Users.countItems() == 0) {
            this.Users.addItem(s);
            return;
        }
        for (int i = 0; i < this.Users.countItems(); ++i) {
            if (this.Compare(s, this.Users.getItem(i)) > 0) {
                this.Users.addItem(s, i);
                return;
            }
        }
        this.Users.addItem(s);
    }
    
    public String getUser(final String s) {
        for (int countItems = this.Users.countItems(), i = 0; i < countItems; ++i) {
            if (s.equalsIgnoreCase(this.RealNick(this.Users.getItem(i)))) {
                return this.Users.getItem(i);
            }
        }
        return null;
    }
    
    public boolean ChangeUser(final String s, final String s2) {
        final int countItems = this.Users.countItems();
        int i = 0;
        while (i < countItems) {
            if (s.equalsIgnoreCase(this.RealNick(this.Users.getItem(i)))) {
                if (this.Users.getItem(i).startsWith("@")) {
                    this.Users.delItem(i);
                    this.addUser("@" + s2);
                    return true;
                }
                if (this.Users.getItem(i).startsWith("+")) {
                    this.Users.delItem(i);
                    this.addUser("+" + s2);
                    return true;
                }
                this.Users.delItem(i);
                this.addUser(s2);
                return true;
            }
            else {
                ++i;
            }
        }
        return false;
    }
    
    public boolean RemoveUser(final String s) {
        for (int countItems = this.Users.countItems(), i = 0; i < countItems; ++i) {
            if (s.equalsIgnoreCase(this.RealNick(this.Users.getItem(i)))) {
                this.Users.delItem(i);
                return true;
            }
        }
        return false;
    }
    
    public String RealNick(final String s) {
        if (s.startsWith("+")) {
            return s.substring(1);
        }
        if (s.startsWith("@")) {
            return s.substring(1);
        }
        return s;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.target == this.Users) {
            if (event.key == 10) {
                if (!this.RealNick(this.Users.getSelectedItem()).equalsIgnoreCase(this.theApp.MPanel.getParams().nick)) {
                    this.theApp.MPanel.NewPrivChatCard(this.RealNick(this.Users.getSelectedItem()), true);
                }
                else {
                    this.theApp.MPanel.Print(super.Label, "\u00032You don't want to chat with yourself, right?");
                }
                return true;
            }
            if (event.id == 1001 && this.Users.getSelectedItem() != null) {
                if (!this.RealNick(this.Users.getSelectedItem()).equalsIgnoreCase(this.theApp.MPanel.getParams().nick)) {
                    this.theApp.MPanel.NewPrivChatCard(this.RealNick(this.Users.getSelectedItem()), true);
                }
                else {
                    this.theApp.MPanel.Print(super.Label, "\u00032You don't want to chat with yourself, right?");
                }
                return true;
            }
        }
        switch (event.id) {
            case 10018: {
                if (event.target == this) {
                    this.Header.setTopic((String)event.arg);
                    return true;
                }
                break;
            }
            case 1004: {
                this.ActionPanel.initWriteMode();
                return true;
            }
        }
        return super.handleEvent(event);
    }
    
    public void reshape(final int n, final int n2, final int width, final int height) {
        super.reshape(n, n2, width, height);
        this.width = width;
        this.height = height;
    }
}
