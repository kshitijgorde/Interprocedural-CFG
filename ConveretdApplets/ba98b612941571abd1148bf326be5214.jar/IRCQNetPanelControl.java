// 
// Decompiled by Procyon v0.5.30
// 

class IRCQNetPanelControl
{
    private IRCQNetPanelArray ChanArray;
    private IRCQNet theApp;
    private IRCQNetPanel IRCQNetMainPanelCards;
    
    public IRCQNetPanelControl(final IRCQNetPanel ircqNetMainPanelCards, final IRCQNet theApp) {
        this.IRCQNetMainPanelCards = ircqNetMainPanelCards;
        this.theApp = theApp;
        this.ChanArray = new IRCQNetPanelArray(theApp);
    }
    
    public IRCQNetChannelWnd NewChannelWnd(final String label) {
        if (this.ChanArray.isSet(label)) {
            return null;
        }
        final IRCQNetChannelWnd ircqNetChannelWnd = new IRCQNetChannelWnd(label, this.theApp);
        ircqNetChannelWnd.setLabel(label);
        this.ChanArray.Add(ircqNetChannelWnd);
        ircqNetChannelWnd.show();
        return ircqNetChannelWnd;
    }
    
    public IRCQNetPrivChatWnd NewPrivChatWnd(final String label) {
        if (this.ChanArray.isSet(label)) {
            return null;
        }
        final IRCQNetPrivChatWnd ircqNetPrivChatWnd = new IRCQNetPrivChatWnd(label, this.theApp);
        ircqNetPrivChatWnd.setLabel(label);
        this.ChanArray.Add(ircqNetPrivChatWnd);
        ircqNetPrivChatWnd.show();
        return ircqNetPrivChatWnd;
    }
    
    public boolean DelChannelWnd(final String s) {
        if (!this.ChanArray.isSet(s)) {
            return false;
        }
        this.ChanArray.Del(s);
        return true;
    }
    
    public boolean DelPrivChatWnd(final String s) {
        if (!this.ChanArray.isSet(s)) {
            return false;
        }
        this.ChanArray.Del(s);
        return true;
    }
    
    public boolean Print(final String s, final String s2) {
        try {
            if (s.startsWith("#")) {
                if (!this.ChanArray.isSet(s)) {
                    this.theApp.MPanel.NewChannelCard(s);
                }
                this.ChanArray.Print(s, s2);
            }
            else {
                if (!this.ChanArray.isSet(s)) {
                    this.theApp.MPanel.NewPrivChatCard(s, false);
                }
                this.ChanArray.Print(s, s2);
            }
        }
        catch (NullPointerException ex) {}
        return true;
    }
    
    public IRCQNetChannelWnd GetChannelWnd(final String s) {
        return (IRCQNetChannelWnd)this.ChanArray.Get(s);
    }
    
    public IRCQNetPrivChatWnd GetPrivChatWnd(final String s) {
        return (IRCQNetPrivChatWnd)this.ChanArray.Get(s);
    }
    
    public IRCQNetPanel getWnd(final String s) {
        return this.ChanArray.Get(s);
    }
    
    public void AddUser(final String s, final String s2) {
        final IRCQNetChannelWnd getChannelWnd = this.GetChannelWnd(s);
        if (getChannelWnd != null) {
            getChannelWnd.addUser(s2);
        }
    }
    
    public String getUser(final String s, final String s2) {
        final IRCQNetChannelWnd getChannelWnd = this.GetChannelWnd(s);
        if (getChannelWnd != null) {
            return getChannelWnd.getUser(s2);
        }
        return null;
    }
    
    public boolean RemoveUserQ(final String s, final String s2) {
        if (this.ChanArray == null) {
            return false;
        }
        for (int size = this.ChanArray.List.size(), i = 0; i < size; ++i) {
            if (this.ChanArray.Get(i).getLabel().startsWith("#") && ((IRCQNetChannelWnd)this.ChanArray.Get(i)).getUser(s) != null) {
                ((IRCQNetChannelWnd)this.ChanArray.Get(i)).RemoveUser(s);
            }
        }
        this.theApp.MPanel.PrintQuits(s2);
        return true;
    }
    
    public void RemoveUser(final String s, final String s2) {
        final IRCQNetChannelWnd getChannelWnd = this.GetChannelWnd(s);
        if (getChannelWnd != null) {
            getChannelWnd.RemoveUser(s2);
        }
    }
    
    public void ChangeUser(final String s, final String s2) {
        IRCQNetPanel get;
        for (int n = 0; (get = this.ChanArray.Get(n)) != null; ++n) {
            if (get.Label.startsWith("#")) {
                ((IRCQNetChannelWnd)get).ChangeUser(s, s2);
            }
        }
    }
    
    public void DelAllWindows() {
        this.theApp.MPanel.DelChannelCard();
        this.ChanArray.DelAll();
        System.gc();
    }
}
