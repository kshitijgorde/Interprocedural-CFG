import java.awt.Event;
import java.util.StringTokenizer;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;

// 
// Decompiled by Procyon v0.5.30
// 

class IRCQNetChannelListCtrl extends IRCQNetPanel
{
    private int securityLevel;
    private IRCQNetSFrame Header;
    public IRCQNet theApp;
    private int count;
    private int height;
    private int width;
    private IRCQNetScrollBar sBar;
    private IRCQNetList View;
    private int newLineAt;
    private int lineAt;
    
    public IRCQNetChannelListCtrl(final IRCQNet theApp) {
        super(theApp);
        this.securityLevel = 5;
        this.theApp = theApp;
        this.setLabel("!CHANNEL!");
        this.initControls();
        this.initLayout();
    }
    
    public void initControls() {
        this.sBar = new IRCQNetScrollBar();
        this.View = new IRCQNetList(this.sBar, this);
        this.Header = new IRCQNetSFrame(this.theApp);
        this.SetHeaderTopic();
        this.Header.setLabel("!CHANNEL!");
        this.Header.ChangeOrder(true);
    }
    
    public void initLayout() {
        this.setLayout(new BorderLayout());
        this.add("Center", this.View);
        this.add("East", this.sBar);
        this.add("North", this.Header);
    }
    
    public void SetHeaderTopic() {
        this.Header.setTopic("#" + this.View.GetListSize());
    }
    
    public void Print(final String s, final String s2, final String s3) {
        final StringTokenizer stringTokenizer = new StringTokenizer(this.theApp.MPanel.getParams().iFilter5, ";");
        for (int countTokens = stringTokenizer.countTokens(), i = 0; i < countTokens; ++i) {
            final String s4 = new String(stringTokenizer.nextToken(";"));
            if (s.toLowerCase().indexOf(s4.toLowerCase()) >= 0) {
                return;
            }
            if (s2.toLowerCase().indexOf(s4.toLowerCase()) >= 0) {
                return;
            }
            if (s3.toLowerCase().indexOf(s4.toLowerCase()) >= 0) {
                return;
            }
        }
        this.View.appendLine(s, s2, s3);
        this.SetHeaderTopic();
    }
    
    public void setSearchMode(final boolean searchMode) {
        this.View.searchMode = searchMode;
    }
    
    public void reshape(final int n, final int n2, final int width, final int height) {
        super.reshape(n, n2, width, height);
        this.width = width;
        this.height = height;
    }
    
    public boolean cleanUp() {
        return this.View != null && this.View.cleanUp();
    }
    
    public boolean modeWait(final boolean b) {
        return this.View != null && this.View.modeWait(b);
    }
    
    public boolean keyDown(final Event event, final int n) {
        switch (n) {
            case 1004: {
                return this.View.lineUp();
            }
            case 1005: {
                return this.View.lineDown();
            }
            case 1002: {
                return this.View.pageUp();
            }
            case 1003: {
                return this.View.pageDown();
            }
            default: {
                return false;
            }
        }
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 501: {
                this.newLineAt = this.View.whichLine(event.x, event.y);
                if (event.clickCount >= 2 && this.newLineAt == this.lineAt) {
                    this.View.openChannel(this.lineAt);
                }
                if (this.newLineAt != this.lineAt) {
                    this.lineAt = this.newLineAt;
                }
                return true;
            }
            case 602: {
                final IRCQNetList view = this.View;
                ++view.onLine;
                this.View.repaint();
                break;
            }
            case 601: {
                final IRCQNetList view2 = this.View;
                --view2.onLine;
                this.View.repaint();
                break;
            }
            case 603:
            case 604:
            case 605: {
                this.View.SBarMove();
                return true;
            }
        }
        return super.handleEvent(event);
    }
}
