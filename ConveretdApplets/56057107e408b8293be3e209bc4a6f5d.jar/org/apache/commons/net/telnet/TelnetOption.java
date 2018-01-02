// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.net.telnet;

public class TelnetOption
{
    public static final int MAX_OPTION_VALUE = 255;
    public static int BINARY;
    public static int ECHO;
    public static int PREPARE_TO_RECONNECT;
    public static int SUPPRESS_GO_AHEAD;
    public static int APPROXIMATE_MESSAGE_SIZE;
    public static int STATUS;
    public static int TIMING_MARK;
    public static int REMOTE_CONTROLLED_TRANSMISSION;
    public static int NEGOTIATE_OUTPUT_LINE_WIDTH;
    public static int NEGOTIATE_OUTPUT_PAGE_SIZE;
    public static int NEGOTIATE_CARRIAGE_RETURN;
    public static int NEGOTIATE_HORIZONTAL_TAB_STOP;
    public static int NEGOTIATE_HORIZONTAL_TAB;
    public static int NEGOTIATE_FORMFEED;
    public static int NEGOTIATE_VERTICAL_TAB_STOP;
    public static int NEGOTIATE_VERTICAL_TAB;
    public static int NEGOTIATE_LINEFEED;
    public static int EXTENDED_ASCII;
    public static int FORCE_LOGOUT;
    public static int BYTE_MACRO;
    public static int DATA_ENTRY_TERMINAL;
    public static int SUPDUP;
    public static int SUPDUP_OUTPUT;
    public static int SEND_LOCATION;
    public static int TERMINAL_TYPE;
    public static int END_OF_RECORD;
    public static int TACACS_USER_IDENTIFICATION;
    public static int OUTPUT_MARKING;
    public static int TERMINAL_LOCATION_NUMBER;
    public static int REGIME_3270;
    public static int X3_PAD;
    public static int WINDOW_SIZE;
    public static int TERMINAL_SPEED;
    public static int REMOTE_FLOW_CONTROL;
    public static int LINEMODE;
    public static int X_DISPLAY_LOCATION;
    public static int OLD_ENVIRONMENT_VARIABLES;
    public static int AUTHENTICATION;
    public static int ENCRYPTION;
    public static int NEW_ENVIRONMENT_VARIABLES;
    public static int EXTENDED_OPTIONS_LIST;
    private static int __FIRST_OPTION;
    private static int __LAST_OPTION;
    private static final String[] __optionString;
    
    public static final String getOption(final int code) {
        if (TelnetOption.__optionString[code].length() == 0) {
            return "UNASSIGNED";
        }
        return TelnetOption.__optionString[code];
    }
    
    public static final boolean isValidOption(final int code) {
        return code <= TelnetOption.__LAST_OPTION;
    }
    
    static {
        TelnetOption.BINARY = 0;
        TelnetOption.ECHO = 1;
        TelnetOption.PREPARE_TO_RECONNECT = 2;
        TelnetOption.SUPPRESS_GO_AHEAD = 3;
        TelnetOption.APPROXIMATE_MESSAGE_SIZE = 4;
        TelnetOption.STATUS = 5;
        TelnetOption.TIMING_MARK = 6;
        TelnetOption.REMOTE_CONTROLLED_TRANSMISSION = 7;
        TelnetOption.NEGOTIATE_OUTPUT_LINE_WIDTH = 8;
        TelnetOption.NEGOTIATE_OUTPUT_PAGE_SIZE = 9;
        TelnetOption.NEGOTIATE_CARRIAGE_RETURN = 10;
        TelnetOption.NEGOTIATE_HORIZONTAL_TAB_STOP = 11;
        TelnetOption.NEGOTIATE_HORIZONTAL_TAB = 12;
        TelnetOption.NEGOTIATE_FORMFEED = 13;
        TelnetOption.NEGOTIATE_VERTICAL_TAB_STOP = 14;
        TelnetOption.NEGOTIATE_VERTICAL_TAB = 15;
        TelnetOption.NEGOTIATE_LINEFEED = 16;
        TelnetOption.EXTENDED_ASCII = 17;
        TelnetOption.FORCE_LOGOUT = 18;
        TelnetOption.BYTE_MACRO = 19;
        TelnetOption.DATA_ENTRY_TERMINAL = 20;
        TelnetOption.SUPDUP = 21;
        TelnetOption.SUPDUP_OUTPUT = 22;
        TelnetOption.SEND_LOCATION = 23;
        TelnetOption.TERMINAL_TYPE = 24;
        TelnetOption.END_OF_RECORD = 25;
        TelnetOption.TACACS_USER_IDENTIFICATION = 26;
        TelnetOption.OUTPUT_MARKING = 27;
        TelnetOption.TERMINAL_LOCATION_NUMBER = 28;
        TelnetOption.REGIME_3270 = 29;
        TelnetOption.X3_PAD = 30;
        TelnetOption.WINDOW_SIZE = 31;
        TelnetOption.TERMINAL_SPEED = 32;
        TelnetOption.REMOTE_FLOW_CONTROL = 33;
        TelnetOption.LINEMODE = 34;
        TelnetOption.X_DISPLAY_LOCATION = 35;
        TelnetOption.OLD_ENVIRONMENT_VARIABLES = 36;
        TelnetOption.AUTHENTICATION = 37;
        TelnetOption.ENCRYPTION = 38;
        TelnetOption.NEW_ENVIRONMENT_VARIABLES = 39;
        TelnetOption.EXTENDED_OPTIONS_LIST = 255;
        TelnetOption.__FIRST_OPTION = TelnetOption.BINARY;
        TelnetOption.__LAST_OPTION = TelnetOption.EXTENDED_OPTIONS_LIST;
        __optionString = new String[] { "BINARY", "ECHO", "RCP", "SUPPRESS GO AHEAD", "NAME", "STATUS", "TIMING MARK", "RCTE", "NAOL", "NAOP", "NAOCRD", "NAOHTS", "NAOHTD", "NAOFFD", "NAOVTS", "NAOVTD", "NAOLFD", "EXTEND ASCII", "LOGOUT", "BYTE MACRO", "DATA ENTRY TERMINAL", "SUPDUP", "SUPDUP OUTPUT", "SEND LOCATION", "TERMINAL TYPE", "END OF RECORD", "TACACS UID", "OUTPUT MARKING", "TTYLOC", "3270 REGIME", "X.3 PAD", "NAWS", "TSPEED", "LFLOW", "LINEMODE", "XDISPLOC", "OLD-ENVIRON", "AUTHENTICATION", "ENCRYPT", "NEW-ENVIRON", "TN3270E", "XAUTH", "CHARSET", "RSP", "Com Port Control", "Suppress Local Echo", "Start TLS", "KERMIT", "SEND-URL", "FORWARD_X", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "TELOPT PRAGMA LOGON", "TELOPT SSPI LOGON", "TELOPT PRAGMA HEARTBEAT", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "Extended-Options-List" };
    }
}
