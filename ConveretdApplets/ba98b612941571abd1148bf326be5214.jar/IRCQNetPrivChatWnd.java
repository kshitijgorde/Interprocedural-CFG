import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Event;
import java.awt.Panel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

// 
// Decompiled by Procyon v0.5.30
// 

public class IRCQNetPrivChatWnd extends IRCQNetPanel
{
    private RichEditCtrl Chat;
    private GridBagConstraints c;
    private GridBagLayout Grid;
    private IRCQNet theApp;
    private IRCQNetSFrame Header;
    private IRCQNetActionsPanel ActionPanel;
    private Panel ThePanel;
    
    public IRCQNetPrivChatWnd(final String label, final IRCQNet theApp) {
        super(theApp);
        this.c = new GridBagConstraints();
        this.Grid = new GridBagLayout();
        this.ThePanel = new Panel();
        this.theApp = theApp;
        this.setLabel(label);
        this.initControls();
        this.initWindow();
    }
    
    public void initControls() {
        this.Header = new IRCQNetSFrame(this.theApp);
        this.ActionPanel = new IRCQNetActionsPanel(this.theApp, super.Label);
        this.Chat = new RichEditCtrl(this);
    }
    
    public void Print(final String s) {
        this.Chat.appendText(s);
    }
    
    public boolean handleEvent(final Event event) {
        if (!super.Label.equalsIgnoreCase("!STATUS!")) {
            switch (event.id) {
            }
        }
        else {
            switch (event.id) {
            }
        }
        return super.handleEvent(event);
    }
    
    public void initWindow() {
        this.ThePanel.setBackground(IRCQNetColors.controlColor);
        this.ThePanel.setLayout(new BorderLayout());
        this.ThePanel.add("Center", this.ActionPanel);
        this.setLayout(this.Grid);
        if (!super.Label.equalsIgnoreCase("!STATUS!")) {
            this.c.weightx = 30.0;
            this.c.weighty = 0.0;
            this.c.gridx = 0;
            this.c.gridwidth = 0;
            this.c.anchor = 11;
            this.Grid.setConstraints(this.Header, this.c);
            this.add(this.Header);
        }
        this.c.gridwidth = 2;
        this.c.gridheight = -1;
        this.c.fill = 1;
        this.c.weightx = 30.0;
        this.c.weighty = 30.0;
        this.c.anchor = 10;
        this.Grid.setConstraints(this.Chat, this.c);
        this.add(this.Chat);
        this.c.weightx = 30.0;
        this.c.weighty = 0.0;
        this.c.gridx = 0;
        this.c.anchor = 15;
        this.c.gridwidth = 0;
        this.Grid.setConstraints(this.ThePanel, this.c);
        this.add(this.ThePanel);
        this.Chat.show();
        if (!super.Label.equalsIgnoreCase("!STATUS!")) {
            this.Header.show();
            this.Header.setLabel(super.Label);
            this.ActionPanel.show();
        }
    }
    
    public void reshape(final int n, final int n2, final int n3, final int n4) {
        super.reshape(n, n2, n3, n4);
    }
}
