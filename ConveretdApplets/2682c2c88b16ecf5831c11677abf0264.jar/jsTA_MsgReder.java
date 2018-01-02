import java.io.IOException;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.BufferedInputStream;
import java.net.MalformedURLException;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

class jsTA_MsgReder extends Thread
{
    public static final int BA_ACTION_RL = 0;
    public static final int BA_ACTION_LR = 1;
    public static final int BA_ACTION_TB = 2;
    public static final int BA_ACTION_BT = 4;
    public static final int BA_ACTION_ERR = 5;
    public static final int EA_ACTION_RL = 0;
    public static final int EA_ACTION_CRL = 1;
    public static final int EA_ACTION_LR = 2;
    public static final int EA_ACTION_TB = 3;
    public static final int EA_ACTION_BT = 4;
    public static final int EA_ACTION_ERR = 5;
    String m_aFileName;
    int m_nMsgNum;
    int m_nNumOfMsgRead;
    String[] m_aMsgList;
    String[] m_aColorList;
    int[] m_aBeginingActionList;
    int[] m_aEndingActionList;
    URL m_SourceURL;
    boolean m_debug;
    
    private void Debug(final String s) {
        if (this.m_debug) {
            System.out.println(s);
        }
    }
    
    public jsTA_MsgReder(final String aFileName) {
        this.m_debug = false;
        this.Debug(">jaNamiMsgRead(" + aFileName + ")");
        this.m_nMsgNum = 0;
        this.m_nNumOfMsgRead = 0;
        this.m_aFileName = aFileName;
        this.Debug("<jaNamiMsgRead()");
    }
    
    public boolean startFileRead(final String aFileName) {
        this.m_aFileName = aFileName;
        try {
            this.m_SourceURL = new URL(this.m_aFileName);
        }
        catch (MalformedURLException ex) {
            ex.printStackTrace();
            return false;
        }
        try {
            final InputStream openStream = this.m_SourceURL.openStream();
            final DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(openStream, 2048));
            int n = 0;
            final String line;
            if ((line = dataInputStream.readLine()) != null) {
                final int intValue = Integer.valueOf(line);
                if (intValue > 0 && intValue < 100) {
                    this.m_nMsgNum = intValue;
                    this.m_aMsgList = new String[intValue];
                    this.m_aColorList = new String[intValue];
                    this.m_aEndingActionList = new int[intValue];
                    this.m_aBeginingActionList = new int[intValue];
                    String s;
                    while ((s = dataInputStream.readLine()) != null && intValue > n) {
                        if ((s.length() & 0x1) == 0x1) {
                            s += " ";
                        }
                        this.m_aMsgList[n] = s;
                        final String line2;
                        if ((line2 = dataInputStream.readLine()) == null) {
                            break;
                        }
                        this.m_aColorList[n] = line2;
                        final String line3;
                        if ((line3 = dataInputStream.readLine()) == null) {
                            break;
                        }
                        this.m_aEndingActionList[n] = this.parsEndingAction(line3);
                        this.m_aBeginingActionList[n] = this.parsBeginingAction(line3);
                        ++n;
                        ++this.m_nNumOfMsgRead;
                    }
                }
            }
            openStream.close();
            return true;
        }
        catch (IOException ex2) {
            ex2.printStackTrace();
            this.m_nNumOfMsgRead = 0;
            return false;
        }
    }
    
    public int getNumberOfMessage() {
        return this.m_nMsgNum;
    }
    
    public int getNumberOfMessageRead() {
        return this.m_nNumOfMsgRead;
    }
    
    public String getMessage(final int n) {
        if (n < this.m_nNumOfMsgRead) {
            return this.m_aMsgList[n];
        }
        return "";
    }
    
    public String getColorList(final int n) {
        if (n < this.m_nNumOfMsgRead) {
            return this.m_aColorList[n];
        }
        return "";
    }
    
    public int getEndingActionrList(final int n) {
        if (n < this.m_nNumOfMsgRead) {
            return this.m_aEndingActionList[n];
        }
        return -1;
    }
    
    public int getBeginigActionrList(final int n) {
        if (n < this.m_nNumOfMsgRead) {
            return this.m_aBeginingActionList[n];
        }
        return -1;
    }
    
    int parsBeginingAction(final String s) {
        if (s.length() < 5) {
            return -1;
        }
        final String substring = s.substring(0, 2);
        if (substring.equals("<<")) {
            return 0;
        }
        if (substring.equals(">>")) {
            return 1;
        }
        if (substring.equals("VV")) {
            return 2;
        }
        if (substring.equals("^^")) {
            return 4;
        }
        return -1;
    }
    
    int parsEndingAction(final String s) {
        if (s.length() < 5) {
            return -1;
        }
        final String substring = s.substring(3, 5);
        if (substring.equals("<<")) {
            return 0;
        }
        if (substring.equals("<>")) {
            return 1;
        }
        if (substring.equals(">>")) {
            return 2;
        }
        if (substring.equals("VV")) {
            return 3;
        }
        if (substring.equals("^^")) {
            return 4;
        }
        return -1;
    }
    
    public void run() {
        if (this.m_aFileName != null) {
            this.startFileRead(this.m_aFileName);
        }
    }
}
