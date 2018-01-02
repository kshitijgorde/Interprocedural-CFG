// 
// Decompiled by Procyon v0.5.30
// 

package pclient.shd;

import java.net.MalformedURLException;
import java.awt.Toolkit;
import java.awt.MediaTracker;
import java.awt.Component;
import com.pchat.sc.GenericResponse;
import java.awt.Image;
import java.applet.Applet;
import java.util.Hashtable;
import java.net.URL;

public class IconShop
{
    public static final String HISTORY = "history.png";
    public static final String ADMIN = "admin.png";
    public static final String MOD = "mod.png";
    public static final String AVATAR = "avatar.png";
    public static final String ERASE = "erase.png";
    public static final String LOG_ON = "log-on.png";
    public static final String LOG_OFF = "log-off.png";
    public static final String SEC_BAD = "secbad.png";
    public static final String SECURE = "secure.png";
    public static final String BUSY = "busy.png";
    public static final String LOCK = "lock.png";
    public static final String SHOW_VIDEO = "showvid.png";
    public static final String SHOW_AUDIO = "showaud.png";
    public static final String SHOW_V_Q = "showvq.png";
    public static final String ROOMS = "rooms.png";
    public static final String USERS = "users.png";
    public static final String BLANK = "blank.gif";
    public static final String BOLD_RAISED = "b16.png";
    public static final String BOLD_RAISED_BG = "b16-bg.png";
    public static final String BOLD_PRESSED = "b16p.png";
    public static final String BOLD_PRESSED_BG = "b16p-bg.png";
    public static final String ITALIC_RAISED = "i16.png";
    public static final String ITALIC_RAISED_BG = "i16-bg.png";
    public static final String ITALIC_PRESSED = "i16p.png";
    public static final String ITALIC_PRESSED_BG = "i16p-bg.png";
    public static final String COLOR_RAISED = "select-color.png";
    public static final String COLOR_RAISED_BG = "select-color-bg.png";
    public static final String COLOR_PRESSED = "color16p.gif";
    public static final String COLOR_OVER = "color16p.gif";
    public static final String SMILE_RAISED = "smiley.png";
    public static final String SMILE_RAISED_BG = "smiley-bg.png";
    public static final String SMILE_PRESSED = "smilebutton16p.gif";
    public static final String SMILE_OVER = "smilebutton16p.gif";
    public static final String SOUND_RAISED = "sound.png";
    public static final String SOUND_RAISED_BG = "sound-bg.png";
    public static final String SOUND_PRESSED = "sound20p.gif";
    public static final String SOUND_OVER = "sound20p.gif";
    public static final String ARROW_RAISED = "arrow16.png";
    public static final String ARROW_RAISED_BG = "arrow16-bg.png";
    public static final String ARROW_PRESSED = "arrow16p.gif";
    public static final String ARROW_OVER = "arrow16p.gif";
    public static final String CLOSE_ARROW_RAISED = "closearrow16.png";
    public static final String CLOSE_ARROW_RAISED_BG = "closearrow16-bg.png";
    public static final String CLOSE_ARROW_PRESSED = "closearrow16p.gif";
    public static final String TOOL_RAISED = "tool16.gif";
    public static final String TOOL_PRESSED = "tool16p.gif";
    public static final String TOOL_OVER = "tool16p.gif";
    public static final String SAVE_RAISED = "save16.gif";
    public static final String SAVE_PRESSED = "save16p.gif";
    public static final String STATUS_RAISED = "status20.png";
    public static final String STATUS_RAISED_BG = "status20-bg.png";
    public static final String STATUS_PRESSED = "status20p.gif";
    public static final String STATUS_OVER = "status20p.gif";
    public static final String BUSY_RAISED = "statusbusy.png";
    public static final String BUSY_RAISED_BG = "statusbusy-bg.png";
    public static final String QUESTION_RAISED = "question16.gif";
    public static final String QUESTION_PRESSED = "question16p.gif";
    public static final String QUESTION_OVER = "question16p.gif";
    public static final String LOG_RAISED = "log-on.png";
    public static final String LOG_RAISED_BG = "log-on-bg.png";
    public static final String LOG_PRESSED = "logon20p.gif";
    public static final String LOG_OVER = "logon20p.gif";
    public static final String LEAVE_RAISED = "log-off.png";
    public static final String LEAVE_RAISED_BG = "log-off-bg.png";
    public static final String LEAVE_PRESSED = "logoff20p.gif";
    public static final String LEAVE_OVER = "logoff20p.gif";
    public static final String AV_RAISED = "showvid.png";
    public static final String AV_RAISED_BG = "showvid-bg.png";
    public static final String AUD_RAISED = "showaud.png";
    public static final String AUD_RAISED_BG = "showaud-bg.png";
    public static final String BT_ACCEPT = "bt-accept.gif";
    public static final String BT_ACCEPT_P = "bt-accept-p.gif";
    public static final String BT_ACCEPT_G = "bt-accept-g.gif";
    public static final String BT_ASK = "bt-ask.gif";
    public static final String BT_ASK_P = "bt-ask-p.gif";
    public static final String BT_ASK_G = "bt-ask-g.gif";
    public static final String BT_CONNECT = "bt-connect.gif";
    public static final String BT_CONNECT_P = "bt-connect-p.gif";
    public static final String BT_CONNECT_G = "bt-connect-g.gif";
    public static final String BT_ENTER = "bt-enter.gif";
    public static final String BT_ENTER_P = "bt-enter-p.gif";
    public static final String BT_ENTER_G = "bt-enter-g.gif";
    public static final String BT_IGNORE = "bt-ignore.gif";
    public static final String BT_IGNORE_P = "bt-ignore-p.gif";
    public static final String BT_IGNORE_G = "bt-ignore-g.gif";
    public static final String BT_INFO = "bt-info.gif";
    public static final String BT_INFO_P = "bt-info-p.gif";
    public static final String BT_INFO_G = "bt-info-g.gif";
    public static final String BT_LOGOFF = "bt-logoff.gif";
    public static final String BT_LOGOFF_P = "bt-logoff-p.gif";
    public static final String BT_LOGOFF_G = "bt-logoff-g.gif";
    public static final String BT_MORE = "bt-more.gif";
    public static final String BT_MORE_P = "bt-more-p.gif";
    public static final String BT_MORE_G = "bt-more-g.gif";
    public static final String BT_PRIVATE = "bt-private.gif";
    public static final String BT_PRIVATE_P = "bt-private-p.gif";
    public static final String BT_PRIVATE_G = "bt-private-g.gif";
    public static final String BT_REFRESH = "bt-refresh.gif";
    public static final String BT_REFRESH_P = "bt-refresh-p.gif";
    public static final String BT_REFRESH_G = "bt-refresh-g.gif";
    public static final String BT_ROOMS = "bt-rooms.gif";
    public static final String BT_ROOMS_P = "bt-rooms-p.gif";
    public static final String BT_ROOMS_G = "bt-rooms-g.gif";
    public static final String BT_SEND = "bt-send.gif";
    public static final String BT_SEND_P = "bt-send-p.gif";
    public static final String BT_SEND_G = "bt-send-g.gif";
    public static final String BT_USERS = "bt-users.gif";
    public static final String BT_USERS_P = "bt-users-p.gif";
    public static final String BT_USERS_G = "bt-users-g.gif";
    public static final String BT_RM_PLUS = "roamplus.png";
    public static final String BT_RM_GLASS = "roamglass.png";
    private URL classCodebase;
    private Hashtable iconTable;
    private int downloadTimeout;
    private Config paraConf;
    private Applet chatApplet;
    
    public IconShop(final Applet chatApplet, final Config paraConf) {
        this.classCodebase = null;
        this.iconTable = null;
        this.downloadTimeout = 14;
        this.chatApplet = chatApplet;
        this.paraConf = paraConf;
        this.classCodebase = chatApplet.getCodeBase();
        this.iconTable = new Hashtable(16);
    }
    
    public Image getImage(final String s, final String s2) {
        if (s2 == null) {
            return null;
        }
        final URL constructURL = this.constructURL(s, s2);
        if (constructURL == null) {
            return null;
        }
        if (this.iconTable.size() > 2000) {
            this.iconTable = new Hashtable();
        }
        final String string = constructURL.toString();
        final GenericResponse genericResponse = this.iconTable.get(string);
        if (genericResponse != null) {
            this.paraConf.printer().print("found in cache," + string);
            return (Image)genericResponse.handle;
        }
        final Image downloadImage = this.downloadImage(constructURL, this.chatApplet);
        final GenericResponse genericResponse2 = new GenericResponse();
        genericResponse2.handle = downloadImage;
        this.iconTable.put(string, genericResponse2);
        return downloadImage;
    }
    
    private Image downloadImage(final URL url, final Component component) {
        final MediaTracker mediaTracker = new MediaTracker(component);
        final Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        this.paraConf.printer().print("downloading image URL," + url);
        if (url == null) {
            return null;
        }
        final Image image = defaultToolkit.getImage(url);
        final int n = 2;
        if (image == null) {
            System.err.println("Err392. image was not downloaded.");
            return null;
        }
        mediaTracker.addImage(image, n);
        this.paraConf.printer().print("to wait for clip");
        boolean waitForID;
        try {
            waitForID = mediaTracker.waitForID(n, this.downloadTimeout * 1000);
        }
        catch (InterruptedException ex) {
            waitForID = false;
        }
        if (!waitForID) {
            System.err.println("image failed," + url + "," + this.downloadTimeout);
        }
        return image;
    }
    
    private URL constructURL(final String s, final String s2) {
        URL url;
        try {
            url = new URL(this.classCodebase, s + "/" + s2);
        }
        catch (MalformedURLException ex) {
            ex.printStackTrace();
            url = null;
        }
        return url;
    }
}
