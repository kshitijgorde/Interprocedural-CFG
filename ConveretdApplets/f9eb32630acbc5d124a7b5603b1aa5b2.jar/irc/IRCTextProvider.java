// 
// Decompiled by Procyon v0.5.30
// 

package irc;

public interface IRCTextProvider
{
    public static final int INTERPRETOR_NOT_ON_CHANNEL = 1;
    public static final int INTERPRETOR_UNKNOWN_DCC = 2;
    public static final int INTERPRETOR_INSUFFICIENT_PARAMETERS = 3;
    public static final int INTERPRETOR_BAD_CONTEXT = 4;
    public static final int INTERPRETOR_CANNOT_CTCP_IN_DCCCHAT = 5;
    public static final int INTERPRETOR_UNKNOWN_CONFIG = 6;
    public static final int INTERPRETOR_TIMESTAMP_ON = 7;
    public static final int INTERPRETOR_TIMESTAMP_OFF = 8;
    public static final int INTERPRETOR_SMILEYS_ON = 9;
    public static final int INTERPRETOR_SMILEYS_OFF = 10;
    public static final int INTERPRETOR_IGNORE_ON = 11;
    public static final int INTERPRETOR_IGNORE_OFF = 12;
    public static final int INTERPRETOR_MULTISERVER_DISABLED = 13;
    public static final int DCC_WAITING_INCOMING = 257;
    public static final int DCC_UNABLE_TO_OPEN_CONNECTION = 258;
    public static final int DCC_CONNECTION_ESTABLISHED = 259;
    public static final int DCC_CONNECTION_CLOSED = 260;
    public static final int DCC_ERROR = 261;
    public static final int DCC_UNABLE_TO_SEND_TO = 262;
    public static final int DCC_BAD_CONTEXT = 263;
    public static final int DCC_NOT_CONNECTED = 264;
    public static final int DCC_UNABLE_PASSIVE_MODE = 265;
    public static final int CTCP_PING_REPLY = 266;
    public static final int DCC_STREAM_CLOSED = 267;
    public static final int IDENT_FAILED_LAUNCH = 513;
    public static final int IDENT_REQUEST = 514;
    public static final int IDENT_ERROR = 515;
    public static final int IDENT_REPLIED = 516;
    public static final int IDENT_DEFAULT_USER = 517;
    public static final int IDENT_NO_USER = 518;
    public static final int IDENT_RUNNING_ON_PORT = 519;
    public static final int IDENT_LEAVING = 520;
    public static final int IDENT_NONE = 521;
    public static final int IDENT_UNKNOWN = 522;
    public static final int IDENT_UNDEFINED = 523;
    public static final int FILE_SAVEAS = 769;
    public static final int ABOUT_ABOUT = 1025;
    public static final int ABOUT_PROGRAMMING = 1026;
    public static final int ABOUT_DESIGN = 1027;
    public static final int ABOUT_THANKS = 1028;
    public static final int ABOUT_SUPPORT = 1029;
    public static final int ABOUT_GPL = 1030;
    public static final int SERVER_UNABLE_TO_CONNECT = 1281;
    public static final int SERVER_UNABLE_TO_CONNECT_STILL = 1282;
    public static final int SERVER_DISCONNECTING = 1283;
    public static final int SERVER_CONNECTING = 1284;
    public static final int SERVER_NOT_CONNECTED = 1285;
    public static final int SERVER_LOGIN = 1286;
    public static final int SERVER_DISCONNECTED = 1287;
    public static final int SERVER_ERROR = 1288;
    public static final int SERVER_AUTOREJOIN_ATTEMPT = 1289;
    public static final int SERVER_AUTOREJOIN_FAILED = 1290;
    public static final int GUI_CHANGE_NICK = 1818;
    public static final int GUI_COPY_WINDOW = 1819;
    public static final int GUI_DCC_CHAT_WARNING_TITLE = 1820;
    public static final int GUI_DCC_CHAT_WARNING_TEXT = 1821;
    public static final int ASL_MALE = 2049;
    public static final int ASL_FEMALE = 2050;
    public static final int ASL_UNKNOWN = 2051;
    public static final int REPLY_IDLE = 2305;
    public static final int REPLY_SIGNON = 2306;
}
