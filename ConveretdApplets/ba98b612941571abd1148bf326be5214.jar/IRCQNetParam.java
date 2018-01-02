import java.util.Random;
import java.util.StringTokenizer;
import java.awt.Color;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class IRCQNetParam
{
    private IRCQNet theApp;
    private int pingTimes;
    public String FirstJoin;
    public boolean Connected;
    public Vector colorVector;
    public int RtBG;
    public int HtBG;
    public int HtFG;
    public int Ht2FG;
    public int GtBG;
    public String Magics;
    public String iFilter1;
    public String iFilter2;
    public String iFilter3;
    public String iFilter4;
    public String iFilter5;
    public String channelList;
    public String emotionsList;
    public String sColorName;
    public String server;
    public String adsServer;
    public String altNick;
    public String user;
    public String uin;
    public String userInfo;
    public String firstJoin;
    public String sFont;
    public boolean startFrame;
    public int sBG;
    public int sFG1;
    public int sFG2;
    public boolean disableLineInput;
    public boolean mustLogin;
    public boolean skipStartup;
    public String iBeginWithTop;
    public String iBeginWithBottom;
    public String iContainToTop;
    public String iContainToBottom;
    public String iChannelToTop;
    public String iRoomSearch;
    public boolean showIP;
    public String nick;
    public boolean showOnlyChat;
    public boolean showQuits;
    public boolean showJoins;
    public boolean showNickChange;
    public boolean showUserModeChange;
    public boolean showButtonsPanel;
    public boolean showChannelsPanel;
    public boolean showSpecialsPanel;
    public boolean showEmotionsPanel;
    public boolean showColorsPanel;
    public Color sFxGradientBG;
    public Color sFxHighLightBG;
    public Color sFxHighLightFG;
    public Color sFxRandomBG;
    public int writeMode;
    public int specialNum;
    public String specialList;
    public Vector sColorVector;
    public String font;
    public Color bgColor;
    
    public IRCQNetParam() {
        this.FirstJoin = "#ICQ";
        this.Connected = false;
        this.colorVector = new Vector(16);
        this.HtFG = 10;
        this.Ht2FG = 11;
        this.Magics = "#Computers;44000305;#Entertainment;44000306;#Family;44000307;#Games;44000308;#HealthandMedicine;44000309;#Internet;44000310;#Lifestyles;44000311;#Local;44000312;#MoneyandBusiness;44000313;#Music;44000314;#Romance;44000315;#ScienceandTechnology;44000316;#Sports;44000317;#Students;44000318;#Travel;44000319;#Women;44000320";
        this.iFilter1 = "sex;fuck;dick;orgy;cum;teen;suck;adult";
        this.iFilter2 = "warez";
        this.iFilter3 = "";
        this.iFilter4 = "";
        this.iFilter5 = "sex;fuck;dick;orgy;cum;teen;suck;adult";
        this.channelList = "#ICQ;#Chatroom;#Computers;#Entertainment;#Family;#Games;#HealthandMedicine;#ICQHelp;#ICQChatMaster;#ICQGroupMaster;#ICQListMaster;#International;#Internet;#Lifestyles;#Local;#MoneyandBusiness;#Music;#Newbies;#Romance;#ScienceandTechnology;#Sports;#Students;#Travel;#Webmasters;#Women;";
        this.emotionsList = "grins;grins mischievously;drools;drools with desire;screams;opens wide eyes and screams excitedly;yawns;yawns showing deep boredom;coughs;coughs loudly trying to get some attention;kick;kicks and screams;hug;gives you a big warm hug;laugh;laughs hysterically, pounding fists and feet on the ground;kiss;blows you a kiss;ignore;puts nose in the air making a hmmpf sound, ignoring that remark;amazed;opens eyes wide with amazement;think;scratches head, deep in thought;flower;picks a flower and hands it to you;confused;moves eyebrows inward in puzzlement;jump;jumps up and down ecstatically;scold;points finger at you, scolding you for your actions;apolgy;begs for your forgiveness;brb;will be right back;excuse;asks to be excused and heads for the toilet;listen;gives you complete attention;";
        this.sColorName = "";
        this.server = "ircqnet.icq.com";
        this.adsServer = "irc.icq.com";
        this.altNick = "IRCQ-Net";
        this.user = "IRCQNet";
        this.uin = "";
        this.userInfo = "IRCQNet Java Enable";
        this.firstJoin = "#ICQ";
        this.sFont = "Helvetica";
        this.startFrame = false;
        this.sBG = 1;
        this.sFG1 = 10;
        this.sFG2 = 11;
        this.mustLogin = true;
        this.disableLineInput = false;
        this.skipStartup = true;
        this.iBeginWithTop = "";
        this.iBeginWithBottom = "";
        this.iContainToTop = "";
        this.iContainToBottom = "";
        this.iChannelToTop = "";
        this.iRoomSearch = "";
        this.showIP = true;
        this.nick = "IrCQNet";
        this.showOnlyChat = true;
        this.showQuits = true;
        this.showJoins = true;
        this.showNickChange = true;
        this.showUserModeChange = true;
        this.showButtonsPanel = true;
        this.showChannelsPanel = true;
        this.showSpecialsPanel = true;
        this.showEmotionsPanel = true;
        this.showColorsPanel = true;
        this.sFxGradientBG = Color.white;
        this.sFxHighLightBG = Color.white;
        this.sFxHighLightFG = Color.white;
        this.sFxRandomBG = Color.white;
        this.specialList = "black;1,9,14,15,14,9,1;green;3,4,7,6,7,4,3";
        this.sColorVector = new Vector(10, 10);
        this.initColorVector();
        this.font = "";
    }
    
    public IRCQNetParam(final IRCQNet theApp) {
        this.FirstJoin = "#ICQ";
        this.Connected = false;
        this.colorVector = new Vector(16);
        this.HtFG = 10;
        this.Ht2FG = 11;
        this.Magics = "#Computers;44000305;#Entertainment;44000306;#Family;44000307;#Games;44000308;#HealthandMedicine;44000309;#Internet;44000310;#Lifestyles;44000311;#Local;44000312;#MoneyandBusiness;44000313;#Music;44000314;#Romance;44000315;#ScienceandTechnology;44000316;#Sports;44000317;#Students;44000318;#Travel;44000319;#Women;44000320";
        this.iFilter1 = "sex;fuck;dick;orgy;cum;teen;suck;adult";
        this.iFilter2 = "warez";
        this.iFilter3 = "";
        this.iFilter4 = "";
        this.iFilter5 = "sex;fuck;dick;orgy;cum;teen;suck;adult";
        this.channelList = "#ICQ;#Chatroom;#Computers;#Entertainment;#Family;#Games;#HealthandMedicine;#ICQHelp;#ICQChatMaster;#ICQGroupMaster;#ICQListMaster;#International;#Internet;#Lifestyles;#Local;#MoneyandBusiness;#Music;#Newbies;#Romance;#ScienceandTechnology;#Sports;#Students;#Travel;#Webmasters;#Women;";
        this.emotionsList = "grins;grins mischievously;drools;drools with desire;screams;opens wide eyes and screams excitedly;yawns;yawns showing deep boredom;coughs;coughs loudly trying to get some attention;kick;kicks and screams;hug;gives you a big warm hug;laugh;laughs hysterically, pounding fists and feet on the ground;kiss;blows you a kiss;ignore;puts nose in the air making a hmmpf sound, ignoring that remark;amazed;opens eyes wide with amazement;think;scratches head, deep in thought;flower;picks a flower and hands it to you;confused;moves eyebrows inward in puzzlement;jump;jumps up and down ecstatically;scold;points finger at you, scolding you for your actions;apolgy;begs for your forgiveness;brb;will be right back;excuse;asks to be excused and heads for the toilet;listen;gives you complete attention;";
        this.sColorName = "";
        this.server = "irc.icq.com";
        this.adsServer = "irc.icq.com";
        this.altNick = "IRCQ-Net";
        this.user = "IRCQNet";
        this.uin = "";
        this.userInfo = "IRCQNet Java Enable";
        this.firstJoin = "#ICQ";
        this.sFont = "Helvetica";
        this.startFrame = false;
        this.sBG = 1;
        this.sFG1 = 10;
        this.sFG2 = 11;
        this.mustLogin = true;
        this.disableLineInput = false;
        this.skipStartup = true;
        this.iBeginWithTop = "";
        this.iBeginWithBottom = "";
        this.iContainToTop = "";
        this.iContainToBottom = "Z-";
        this.iChannelToTop = "";
        this.iRoomSearch = "";
        this.showIP = true;
        this.nick = "IrCQNet";
        this.showOnlyChat = true;
        this.showQuits = true;
        this.showJoins = true;
        this.showNickChange = true;
        this.showUserModeChange = true;
        this.showButtonsPanel = true;
        this.showChannelsPanel = true;
        this.showSpecialsPanel = true;
        this.showEmotionsPanel = true;
        this.showColorsPanel = true;
        this.sFxGradientBG = Color.white;
        this.sFxHighLightBG = Color.white;
        this.sFxHighLightFG = Color.white;
        this.sFxRandomBG = Color.white;
        this.specialList = "black;1,9,14,15,14,9,1;green;3,4,7,6,7,4,3";
        this.sColorVector = new Vector(10, 10);
        this.theApp = theApp;
        this.font = "";
    }
    
    public void init() {
        this.initColorVector();
        final String parameter = this.theApp.getParameter("channelList");
        if (parameter != null) {
            this.channelList = parameter;
        }
        final String parameter2 = this.theApp.getParameter("emotionsList");
        if (parameter2 != null) {
            this.emotionsList = parameter2;
        }
        final String parameter3 = this.theApp.getParameter("specialList");
        if (parameter3 != null) {
            this.specialList = parameter3;
        }
        final String parameter4 = this.theApp.getParameter("server");
        if (parameter4 != null) {
            this.server = parameter4;
            this.adsServer = parameter4;
        }
        else {
            this.server = "irc.icq.com";
            this.adsServer = "irc.icq.com";
        }
        final String parameter5 = this.theApp.getParameter("nick");
        if (parameter5 != null) {
            this.nick = parameter5;
        }
        final String parameter6 = this.theApp.getParameter("altNick");
        if (parameter6 != null) {
            this.altNick = parameter6;
        }
        final String parameter7 = this.theApp.getParameter("user");
        if (parameter7 != null) {
            this.user = parameter7;
        }
        final String parameter8 = this.theApp.getParameter("uin");
        if (parameter8 != null) {
            this.uin = parameter8;
        }
        final String parameter9 = this.theApp.getParameter("userInfo");
        if (parameter9 != null) {
            this.userInfo = parameter9;
        }
        final String parameter10 = this.theApp.getParameter("firstJoin");
        if (parameter10 != null) {
            if (parameter10.startsWith("#")) {
                this.firstJoin = parameter10;
            }
            else {
                this.firstJoin = "#" + parameter10;
            }
        }
        final String parameter11 = this.theApp.getParameter("join");
        if (parameter11 != null) {
            if (parameter11.startsWith("#")) {
                this.firstJoin = parameter11;
            }
            else {
                this.firstJoin = "#" + parameter11;
            }
        }
        final String parameter12 = this.theApp.getParameter("disableLineInput");
        if (parameter12 != null) {
            this.disableLineInput = new Boolean(parameter12);
        }
        final String parameter13 = this.theApp.getParameter("mustLogin");
        if (parameter13 != null) {
            this.mustLogin = new Boolean(parameter13);
        }
        final String parameter14 = this.theApp.getParameter("skipStartup");
        if (parameter14 != null) {
            this.skipStartup = new Boolean(parameter14);
        }
        final String parameter15 = this.theApp.getParameter("showIP");
        if (parameter15 != null) {
            this.showIP = new Boolean(parameter15);
        }
        final String parameter16 = this.theApp.getParameter("showQuits");
        if (parameter16 != null) {
            this.showQuits = new Boolean(parameter16);
        }
        final String parameter17 = this.theApp.getParameter("startFrame");
        if (parameter17 != null) {
            this.startFrame = new Boolean(parameter17);
        }
        final String parameter18 = this.theApp.getParameter("showButtonsPanel");
        if (parameter18 != null) {
            this.showButtonsPanel = new Boolean(parameter18);
        }
        final String parameter19 = this.theApp.getParameter("showChannelsPanel");
        if (parameter19 != null) {
            this.showChannelsPanel = new Boolean(parameter19);
        }
        final String parameter20 = this.theApp.getParameter("showSpecialsPanel");
        if (parameter20 != null) {
            this.showSpecialsPanel = new Boolean(parameter20);
        }
        final String parameter21 = this.theApp.getParameter("showEmotionsPanel");
        if (parameter21 != null) {
            this.showEmotionsPanel = new Boolean(parameter21);
        }
        final String parameter22 = this.theApp.getParameter("showColorsPanel");
        if (parameter22 != null) {
            this.showColorsPanel = new Boolean(parameter22);
        }
        final String parameter23 = this.theApp.getParameter("iBeginWithTop");
        if (parameter23 != null) {
            this.iBeginWithTop = parameter23;
        }
        final String parameter24 = this.theApp.getParameter("iBeginWithBottom");
        if (parameter24 != null) {
            this.iBeginWithBottom = parameter24;
        }
        final String parameter25 = this.theApp.getParameter("iContainToTop");
        if (parameter25 != null) {
            this.iContainToTop = parameter25;
        }
        final String parameter26 = this.theApp.getParameter("iContainToBottom");
        if (parameter26 != null) {
            this.iContainToBottom = parameter26;
        }
        final String parameter27 = this.theApp.getParameter("iChannelToTop");
        if (parameter27 != null) {
            this.iChannelToTop = parameter27;
        }
        final String parameter28 = this.theApp.getParameter("font");
        if (parameter28 != null) {
            this.font = parameter28;
        }
    }
    
    private void initColorVector() {
        this.colorVector.addElement(new Color(255, 255, 255));
        this.colorVector.addElement(new Color(0, 0, 0));
        this.colorVector.addElement(new Color(0, 0, 123));
        this.colorVector.addElement(new Color(0, 146, 0));
        this.colorVector.addElement(new Color(255, 0, 0));
        this.colorVector.addElement(new Color(123, 0, 0));
        this.colorVector.addElement(new Color(156, 0, 156));
        this.colorVector.addElement(new Color(255, 125, 0));
        this.colorVector.addElement(new Color(255, 255, 0));
        this.colorVector.addElement(new Color(0, 255, 0));
        this.colorVector.addElement(new Color(0, 146, 148));
        this.colorVector.addElement(new Color(0, 255, 255));
        this.colorVector.addElement(new Color(0, 0, 255));
        this.colorVector.addElement(new Color(255, 0, 255));
        this.colorVector.addElement(new Color(123, 125, 123));
        this.colorVector.addElement(new Color(214, 211, 214));
    }
    
    public void setSpecials(final int n) {
        final StringTokenizer stringTokenizer = new StringTokenizer(this.specialList, ";");
        if (stringTokenizer.countTokens() < (n + 1) * 2) {
            return;
        }
        for (int i = 0; i <= n * 2; ++i) {
            this.sColorName = stringTokenizer.nextToken();
        }
        final StringTokenizer stringTokenizer2 = new StringTokenizer(stringTokenizer.nextToken(), ",");
        final int countTokens = stringTokenizer2.countTokens();
        this.sColorVector.removeAllElements();
        for (int j = 0; j < countTokens; ++j) {
            this.sColorVector.addElement(new Integer(Integer.parseInt(stringTokenizer2.nextToken())));
        }
    }
    
    public String getMagic(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(this.Magics, ";");
        final int countTokens = stringTokenizer.countTokens();
        if (countTokens <= 0) {
            return null;
        }
        for (int i = 0; i < countTokens; i += 2) {
            if (stringTokenizer.nextToken().equalsIgnoreCase(s)) {
                return stringTokenizer.nextToken(";");
            }
            stringTokenizer.nextToken(";");
        }
        return null;
    }
    
    public int getAdsPort() {
        return 9000 + Math.abs(new Random().nextInt()) % 11;
    }
    
    public int serverPort() {
        return 6666 + Math.abs(new Random().nextInt()) % 4;
    }
    
    public boolean allowPing() {
        if (this.pingTimes < 3) {
            ++this.pingTimes;
            return true;
        }
        return false;
    }
    
    public void pingTimer() {
        if (this.pingTimes > 0) {
            --this.pingTimes;
        }
    }
}
