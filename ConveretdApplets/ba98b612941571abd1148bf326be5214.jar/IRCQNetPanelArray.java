import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class IRCQNetPanelArray
{
    private IRCQNet theApp;
    public Vector List;
    
    public IRCQNetPanelArray(final IRCQNet theApp) {
        this.theApp = theApp;
        this.List = new Vector(5, 5);
    }
    
    public void DelAll() {
        Object element = null;
        for (int i = 0; i < this.List.size(); ++i) {
            if (((IRCQNetPanel)this.List.elementAt(i)).getLabel().equalsIgnoreCase("!STATUS!")) {
                element = this.List.elementAt(i);
            }
        }
        for (int j = this.List.size() - 1; j > 0; --j) {
            ((IRCQNetPanel)this.List.elementAt(j)).hide();
            this.theApp.MPanel.delCard(((IRCQNetPanel)this.List.elementAt(j)).getLabel());
        }
        this.List.removeAllElements();
        if (element != null) {
            this.List.addElement(element);
        }
    }
    
    public boolean Add(final IRCQNetPanel ircqNetPanel) {
        this.List.addElement(ircqNetPanel);
        return true;
    }
    
    public boolean Del(final String s) {
        for (int i = 0; i < this.List.size(); ++i) {
            if (((IRCQNetPanel)this.List.elementAt(i)).getLabel().equalsIgnoreCase(s)) {
                this.List.removeElementAt(i);
                return true;
            }
        }
        return false;
    }
    
    public boolean isSet(final String s) {
        for (int i = 0; i < this.List.size(); ++i) {
            if (((IRCQNetPanel)this.List.elementAt(i)).getLabel().equalsIgnoreCase(s)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean Print(final String s, final String s2) {
        for (int i = 0; i < this.List.size(); ++i) {
            if (((IRCQNetPanel)this.List.elementAt(i)).getLabel().equalsIgnoreCase(s)) {
                ((IRCQNetPanel)this.List.elementAt(i)).Print(s2);
                return true;
            }
        }
        return false;
    }
    
    public IRCQNetPanel Get(final String s) {
        for (int i = 0; i < this.List.size(); ++i) {
            if (((IRCQNetPanel)this.List.elementAt(i)).getLabel().equalsIgnoreCase(s)) {
                return (IRCQNetPanel)this.List.elementAt(i);
            }
        }
        return null;
    }
    
    public IRCQNetPanel Get(final int n) {
        if (n < this.List.size()) {
            return this.List.elementAt(n);
        }
        return null;
    }
}
