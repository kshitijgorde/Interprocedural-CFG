import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class IRCQNet extends Applet
{
    public IRCQNetMainPanel MPanel;
    public IRCQNetNickNamePanel MNickPanel;
    private IRCQNetFrame tFrame;
    private BorderLayout borderLayout;
    public boolean mode;
    public boolean appletStart;
    private int height;
    private int width;
    private IRCQNetParam Params;
    private boolean doneInit;
    
    public IRCQNet() {
        this.borderLayout = new BorderLayout();
        this.mode = false;
        this.appletStart = false;
        this.doneInit = false;
        this.Params = new IRCQNetParam(this);
        this.MPanel = new IRCQNetMainPanel("IRCQNet Ver 1.0b", this);
    }
    
    public IRCQNetParam getParams() {
        return this.Params;
    }
    
    public void send(final String s) {
        if (this.MPanel != null) {
            this.MPanel.Send(s);
        }
    }
    
    public void closeWindow() {
        if (this.MPanel.isModeFrame()) {
            this.tFrame.dispose();
        }
    }
    
    public Dimension preferredSize() {
        return new Dimension(300, 400);
    }
    
    public Dimension minimumSize() {
        return new Dimension(200, 200);
    }
    
    public boolean initNoneFrame() {
        if (this.tFrame != null) {
            this.tFrame.hide();
            this.tFrame.dispose();
        }
        this.setLayout(this.borderLayout);
        this.add("Center", this.MPanel);
        this.MPanel.setModeFrame(false);
        this.MPanel.resize(this.width, this.height);
        this.show();
        this.MPanel.show();
        this.validate();
        return true;
    }
    
    public boolean initFrame() {
        this.remove(this.MPanel);
        (this.tFrame = new IRCQNetFrame("IRCQNet Ver 1.0b", this.MPanel)).add("Center", this.MPanel);
        this.MPanel.setModeFrame(true);
        final Dimension screenSize = this.MPanel.getToolkit().getScreenSize();
        final int n = (int)(screenSize.width / 1.3);
        final int n2 = (int)(screenSize.height / 1.3);
        this.MPanel.resize(this.width, this.height);
        this.tFrame.resize(n, n2);
        this.show();
        this.tFrame.show();
        this.MPanel.show();
        this.tFrame.setMenuBar(this.MPanel.menuBar);
        this.validate();
        this.MPanel.validate();
        this.tFrame.validate();
        return true;
    }
    
    public void init() {
        this.doneInit = true;
        this.Params.init();
        this.MPanel.init();
        this.initNoneFrame();
        this.MPanel.OpenNickNameTab("Please choose a Nickname", this.Params.nick, false);
    }
    
    public void connect() {
        this.appletStart = true;
        this.MPanel.ConnectToServer();
    }
    
    public void start() {
        try {
            if (this.MPanel == null) {
                super.start();
            }
        }
        catch (NullPointerException ex) {}
    }
    
    public void stop() {
        try {
            if (this.MPanel == null) {
                super.stop();
            }
        }
        catch (NullPointerException ex) {}
    }
    
    public void reshape(final int n, final int n2, final int width, final int height) {
        super.reshape(n, n2, width, height);
        this.width = width;
        this.height = height;
    }
    
    public void destroy() {
        try {
            if (this.MPanel != null) {
                if (this.MPanel.isModeFrame()) {
                    this.MPanel.disableEmbed();
                    return;
                }
                this.MPanel.Disconnected(false);
                System.exit(0);
            }
        }
        catch (NullPointerException ex) {}
    }
}
