import java.util.Hashtable;
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.util.Properties;
import java.net.URL;
import java.io.FileNotFoundException;
import java.io.DataInputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class Mpget001 extends Thread
{
    InfoW \u010d;
    String \u014e;
    final int \u013d = 1000;
    boolean \u0138;
    int \u014f;
    int \u0150;
    String \u0140;
    String \u0141;
    int \u0151;
    int \u0152;
    int \u0153;
    int \u0154;
    int \u0155;
    int \u0156;
    int \u0157;
    int \u0158;
    String \u013e;
    String \u013c;
    String \u0159;
    String \u015a;
    String[] \u015b;
    boolean \u015c;
    DataInputStream \u0148;
    
    Mpget001(final String \u013c, final InfoW \u010d) {
        this.\u014e = "/nytimes-settogether/multiplayerservletny";
        this.\u0138 = false;
        this.\u0151 = 50;
        this.\u013e = "0";
        this.\u0159 = "00000000000000";
        this.\u015a = "00000000000000";
        this.\u015c = false;
        this.\u010d = \u010d;
        this.\u013c = \u013c;
        this.\u015b = new String[this.\u0151];
        for (int i = 0; i < this.\u0151; ++i) {
            this.\u015b[i] = new String("");
        }
        this.reset();
    }
    
    private void reset() {
        this.\u014f = 0;
        this.\u0156 = 0;
        this.\u0157 = 0;
        this.\u0158 = 0;
    }
    
    public void setStream(final DataInputStream \u0148) {
        this.\u015c = true;
        this.\u0148 = \u0148;
    }
    
    public void run() {
        while (true) {
            if (this.\u0138) {
                this.\u014e(this.getNextPacket());
            }
            else {
                try {
                    Thread.sleep(5000L);
                }
                catch (InterruptedException ex) {}
            }
            try {
                Thread.sleep(1000L);
            }
            catch (InterruptedException ex2) {}
        }
    }
    
    public void startThread(final String \u013e) {
        this.\u013e = \u013e;
        this.clearBuffer();
        this.\u0138 = true;
        this.\u0155 = 0;
        this.\u0156 = 0;
    }
    
    public void stopThread() {
        this.clearBuffer();
        this.reset();
        this.\u0138 = false;
    }
    
    public void pauseThread() {
        this.\u0138 = false;
    }
    
    public void resumeThread() {
        this.\u0138 = true;
    }
    
    private void \u014e(final String s) {
        if (!s.equals("null")) {
            final int \u0155 = this.\u0154;
            ++this.\u0154;
            if (this.\u0154 == this.\u0151) {
                this.\u0154 = 0;
            }
            if (this.\u0154 == this.\u0153) {
                if (this.\u0154 == 0) {
                    this.\u0154 = this.\u0151 - 1;
                    return;
                }
                --this.\u0154;
            }
            else {
                this.\u015b[\u0155] = s;
                ++this.\u0152;
            }
        }
    }
    
    public boolean emptyBuffer() {
        return this.\u0152 == 0;
    }
    
    public String getBuffer() {
        if (this.\u0152 > 0) {
            final int \u0153 = this.\u0153;
            ++this.\u0153;
            if (this.\u0153 == this.\u0151) {
                this.\u0153 = 0;
            }
            --this.\u0152;
            final String s = this.\u015b[\u0153];
            this.\u015b[\u0153] = "OLD <" + this.\u015b[\u0153] + ">";
            return s;
        }
        return "[NULL]";
    }
    
    public String getNextPacket() {
        if (this.\u015c) {
            return this.getNextSocketPacket();
        }
        String s;
        try {
            s = this.getNextHTTPPacket();
        }
        catch (FileNotFoundException ex) {
            this.sendToInfoWindow("Mpget001:7", "[Exeception Special" + ex + "]");
            s = "[ERROR][BIG EXCEPTION][SPECIAL REQUESTS][" + this.\u0150 + "]";
            this.sendToInfoWindow("Mpget001:7", "[getProperties][messagetype][info][roomID]" + this.\u013e + "][userID][" + this.\u0140 + "][lastMoveReceived][" + this.\u0156 + "][lastChatReceived][" + this.\u0157 + "][lastUserlistReceived][" + this.\u0158 + "]");
            this.\u0138 = false;
        }
        return s;
    }
    
    public String getNextHTTPPacket() {
        String s = null;
        DataInputStream dataInputStream = null;
        try {
            final HttpM001 httpM001 = new HttpM001(new URL("http", this.\u013c, 80, this.\u014e));
            this.sendToInfoWindow("Mpget001:3", "[getProperties2][messagetype][info][roomID]" + this.\u013e + "][userID][" + this.\u0140 + "][lastMoveReceived][" + this.\u0156 + "][lastChatReceived][" + this.\u0157 + "][lastUserlistReceived][" + this.\u0158 + "]");
            final Properties properties = new Properties();
            ((Hashtable<String, String>)properties).put("messagetype", "info");
            ((Hashtable<String, String>)properties).put("roomID", this.\u013e);
            ((Hashtable<String, String>)properties).put("userID", this.\u0140);
            ((Hashtable<String, String>)properties).put("uniqueUserNo", this.\u0141);
            ((Hashtable<String, String>)properties).put("LMR", String.valueOf(this.\u0156));
            ((Hashtable<String, String>)properties).put("LCR", String.valueOf(this.\u0157));
            ((Hashtable<String, String>)properties).put("LUR", String.valueOf(this.\u0158));
            ((Hashtable<String, String>)properties).put("packetCheck", String.valueOf(this.\u0156));
            final InputStream sendGetMessage = httpM001.sendGetMessage(properties);
            ++this.\u014f;
            dataInputStream = new DataInputStream(new BufferedInputStream(sendGetMessage));
            s = dataInputStream.readLine();
            this.sendToInfoWindow("Mpget001:NEXTMESSAGE", "<" + this.\u014f + "><" + s + ">");
            if (s.startsWith("[OK][M]") || s.startsWith("[M]")) {
                final String substring = s.substring(s.indexOf("[LMR]", 0) + 5);
                this.\u0156 = Integer.parseInt(substring.substring(1, substring.indexOf(93)));
                this.sendToInfoWindow("Mpget001:LASTMOVERECEIVED", "<" + this.\u0156 + ">");
            }
            if (s.startsWith("chat") || s.startsWith("[OK][CHAT]")) {
                final int index = s.indexOf("[LCR]", 0);
                final String substring2 = s.substring(index + 5);
                this.\u0157 = Integer.parseInt(substring2.substring(1, substring2.indexOf(93)));
                s = s.substring(0, index);
                this.sendToInfoWindow("Mpget001:LASTCHATRECEIVED", "<" + this.\u0157 + ">");
            }
            if (s.startsWith("uout") || s.startsWith("[OK][LUR]")) {
                final String substring3 = s.substring(9);
                final String substring4 = substring3.substring(substring3.indexOf("[LUR]", 1) + 5);
                this.\u0158 = Integer.parseInt(substring4.substring(1, substring4.indexOf(93)));
                this.sendToInfoWindow("Mpget001:LASTUSERLISTRECEIVED", "<" + this.\u0158 + ">");
            }
            if (s.startsWith("[OUTOFROOM]")) {
                this.sendToInfoWindow("Mpget001:OUTOFROOMRECEIVED", "<0>");
            }
            ++this.\u0155;
        }
        catch (IOException ex) {
            s = "[ERROR][MPGET][1][SPECIAL REQUEST][" + this.\u0150 + "][" + ex + "]";
            ++this.\u0150;
            throw new FileNotFoundException();
        }
        catch (Exception ex2) {
            s = "[ERROR][MPGET][2][SPECIAL REQUEST]" + this.\u0150 + "][" + ex2 + "]";
            ++this.\u0150;
        }
        finally {
            if (dataInputStream != null) {
                try {
                    dataInputStream.close();
                }
                catch (IOException ex3) {
                    s = "[ERROR][MPGET][3][SPECIAL REQUEST]" + this.\u0150 + "][" + ex3 + "]";
                }
            }
        }
        this.sendToInfoWindow("Mpget001:NEXTMESSAGE", "<" + this.\u014f + "><" + s + ">");
        return s;
    }
    
    public void sendToInfoWindow(final String s, final String s2) {
        if (this.\u010d != null) {
            this.\u010d.add(s, s2);
        }
    }
    
    public void setRoomID(final String \u013e) {
        this.\u013e = \u013e;
    }
    
    public void setUserID(final int n) {
        this.\u0140 = String.valueOf(n);
    }
    
    public void setUniqueUserNo(final int n) {
        this.\u0141 = String.valueOf(n);
    }
    
    public void setLastMove(final int \u0157) {
        this.\u0156 = \u0157;
    }
    
    public void setLastChat(final int \u0157) {
        this.\u0157 = \u0157;
    }
    
    public void clearBuffer() {
        for (int i = 0; i < this.\u0151; ++i) {
            this.\u015b[i] = "";
        }
        this.\u0152 = 0;
        this.\u0154 = 0;
        this.\u0153 = 0;
    }
    
    public String getNextSocketPacket() {
        if (this.\u0148 == null) {
            System.out.println("Stream is null");
        }
        String line = null;
        while (line == null) {
            try {
                line = this.\u0148.readLine();
            }
            catch (Exception ex) {
                System.out.println("General exception: " + ex.getClass().getName() + ": " + ex.getMessage());
                try {
                    Thread.sleep(1000L);
                }
                catch (InterruptedException ex2) {}
            }
        }
        return line;
    }
}
