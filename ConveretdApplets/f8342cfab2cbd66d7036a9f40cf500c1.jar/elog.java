import java.io.InputStream;
import java.net.URLConnection;
import java.net.URL;
import java.awt.image.ImageObserver;
import java.awt.Cursor;
import java.awt.Event;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Image;
import java.awt.Graphics;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class elog extends Applet implements Runnable
{
    Thread runner;
    Graphics dBuf;
    Image offScr;
    int init_log;
    int ac;
    String[] logMsg;
    String UID;
    String UPW;
    Font vFont;
    FontMetrics vMet;
    String bgcolor;
    String fgcolor;
    int fontsize;
    E_Menu im;
    int cycx_start;
    int cycx_now;
    int cycx_end;
    int cycx_skip;
    
    public elog() {
        this.logMsg = new String[] { "\u7cfb\u7d71\u672a\u767b\u5165,\u8acb\u6309\u9019\u91cd\u65b0\u767b\u5165...", "\u8981\u6c42\u7cfb\u7d71\u767b\u5165\u4e2d...\u8acb\u7a0d\u5f8c...", "\u7cfb\u7d71\u5df2\u767b\u5165,\u6b61\u8fce\u4f7f\u7528\u767c\u8ca1VIP\u770b\u76e4\u5ba4...", "\u5e33\u865f\u5df2\u5728\u4ed6\u8655\u767b\u5165\u4f7f\u7528,\u8acb\u6309\u9019\u91cd\u65b0\u767b\u5165..." };
        this.UID = "";
        this.UPW = "";
        this.runner = null;
        this.init_log = 0;
    }
    
    public void Login(final String id, final String pass) {
        this.UID = new String(id);
        this.UPW = new String(pass);
        this.init_log = 1;
        this.ac = 0;
        this.showStatus("\u8981\u6c42\u767b\u5165\u4e2d");
    }
    
    public void bpaint(final Graphics graphi1) {
        graphi1.setColor(Color.decode(this.bgcolor));
        graphi1.fillRect(0, 0, this.size().width, this.size().height);
        this.cycx_start = 0 - this.vMet.stringWidth(this.logMsg[this.init_log]);
        graphi1.setColor(Color.decode(this.fgcolor));
        this.cycx_now -= this.cycx_skip;
        if (this.cycx_now > this.cycx_start) {
            graphi1.drawString(String.valueOf(this.UID).concat(String.valueOf(this.logMsg[this.init_log])), this.cycx_now, this.vMet.getAscent() + 4);
        }
        else {
            this.cycx_now = this.size().width;
        }
    }
    
    public void chkLog() {
        switch (this.init_log) {
            case 1: {
                --this.ac;
                if (this.ac < 0) {
                    this.login();
                }
                break;
            }
            case 2: {
                --this.ac;
                if (this.ac < 0) {
                    this.login();
                }
                break;
            }
        }
    }
    
    public void init() {
        this.bgcolor = this.getParameter("bgcolor");
        this.fgcolor = this.getParameter("fgcolor");
        this.fontsize = Integer.parseInt(this.getParameter("fontsize"));
        this.setBackground(Color.decode(this.bgcolor));
        this.offScr = this.createImage(this.size().width, this.size().height);
        this.dBuf = this.offScr.getGraphics();
        this.vFont = new Font("\u7d30\u660e\u9ad4", 0, this.fontsize);
        this.vMet = this.getFontMetrics(this.vFont);
        this.repaint();
        this.cycx_start = 0;
        this.cycx_now = this.size().width;
        this.cycx_end = this.size().width;
        this.cycx_skip = 3;
        (this.im = (E_Menu)this.getAppletContext().getApplet("E_Menu")).disable();
    }
    
    public void login() {
        final int r = this.Request_url(String.valueOf(String.valueOf(String.valueOf("/VIP/StockDJ_chk_login.asp?id=").concat(String.valueOf(this.UID))).concat(String.valueOf("&pass="))).concat(String.valueOf(this.UPW)));
        this.im = (E_Menu)this.getAppletContext().getApplet("E_Menu");
        switch (r) {
            case 0: {
                this.init_log = 0;
                this.ac = 50;
                this.im.disable();
                this.Show_login_page();
                break;
            }
            case 1: {
                this.init_log = 2;
                this.ac = 300;
                this.im.enable();
                break;
            }
            case 2: {
                this.init_log = 3;
                this.ac = 300;
                this.im.disable();
                this.Show_login_page();
                break;
            }
        }
        this.showStatus(String.valueOf(Integer.toString(r)).concat(String.valueOf(Integer.toString(this.init_log))));
    }
    
    public int islogin() {
        if (this.init_log == 2) {
            return 1;
        }
        return 0;
    }
    
    public void logout() {
        final int int1 = this.Request_url(String.valueOf("/VIP/StockDJ_chk_logout.asp?id=").concat(String.valueOf(this.UID)));
    }
    
    public boolean mouseDown(final Event e, final int x, final int y) {
        if (this.init_log == 0 || this.init_log == 3) {
            this.Show_login_page();
        }
        return true;
    }
    
    public boolean mouseEnter(final Event e, final int x, final int y) {
        if (this.init_log == 0 || this.init_log == 3) {
            this.setCursor(new Cursor(12));
        }
        else {
            this.setCursor(new Cursor(0));
        }
        return true;
    }
    
    public void paint(final Graphics graphi1) {
        graphi1.drawImage(this.offScr, 0, 0, this);
    }
    
    int Request_url(final String aurl) {
        try {
            final URL uRL2 = new URL(this.getDocumentBase(), aurl);
            final URLConnection uRLCon3 = uRL2.openConnection();
            uRLCon3.setUseCaches(false);
            final InputStream inputS4 = uRLCon3.getInputStream();
            String msg = "";
            for (int i = 0; i < 12; ++i) {
                final int j = inputS4.read();
                if (j == -1) {
                    break;
                }
                msg = String.valueOf(msg).concat(String.valueOf((char)j));
            }
            inputS4.close();
            System.out.println(msg);
            return Integer.parseInt(msg);
        }
        catch (Exception Exception0) {
            return -1;
        }
    }
    
    public void run() {
        final Thread thread1 = Thread.currentThread();
        while (this.runner == thread1) {
            this.chkLog();
            this.bpaint(this.dBuf);
            this.repaint();
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void start() {
        if (this.runner == null) {
            (this.runner = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.runner != null) {
            this.runner = null;
            if (this.init_log == 2) {
                this.logout();
            }
        }
    }
    
    public void update(final Graphics graphi1) {
        this.paint(graphi1);
    }
    
    private void Show_login_page() {
        try {
            final URL uRL5 = new URL(this.getDocumentBase(), "/VIP/login.asp");
            this.getAppletContext().showDocument(uRL5, "vip_main");
        }
        catch (Exception except5) {
            except5.printStackTrace();
        }
    }
}
