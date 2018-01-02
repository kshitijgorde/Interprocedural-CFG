import java.util.Hashtable;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.io.FileNotFoundException;
import java.net.SocketException;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.net.URL;
import java.util.Properties;
import java.io.PrintStream;
import java.io.DataInputStream;
import java.net.Socket;

// 
// Decompiled by Procyon v0.5.30
// 

public class Mpexe002 extends Thread
{
    InfoW \u010d;
    boolean \u0138;
    Commu003 \u0139;
    Mpput001 \u013a;
    Mpget001 \u013b;
    String \u013c;
    int \u013d;
    String \u013e;
    String \u013f;
    String \u00d4;
    int \u0140;
    int \u0141;
    int \u0142;
    final int \u0143 = 10000;
    long \u0144;
    boolean \u0145;
    boolean \u0146;
    String \u0115;
    Socket \u0147;
    DataInputStream \u0148;
    PrintStream \u0149;
    int \u014a;
    boolean \u014b;
    int \u014c;
    int \u014d;
    
    Mpexe002(final String \u013c, final Commu003 \u013a, final Mpput001 \u013a2, final Mpget001 \u013c2, final InfoW \u010d) {
        this.\u0138 = false;
        this.\u0142 = 1;
        this.\u0145 = false;
        this.\u014a = 8085;
        this.\u014b = false;
        this.\u010d = \u010d;
        this.\u013c = \u013c;
        this.\u013a = \u013a2;
        this.\u013b = \u013c2;
        this.\u0139 = \u013a;
    }
    
    public void run() {
        while (true) {
            if (this.\u0138) {
                this.\u0138();
            }
            else {
                try {
                    Thread.sleep(5000L);
                }
                catch (InterruptedException ex) {}
            }
            try {
                Thread.sleep(this.\u013d);
            }
            catch (InterruptedException ex2) {}
        }
    }
    
    public void startThread() {
        ++this.\u014d;
        this.\u013a.clearBuffer();
        this.\u0144 = System.currentTimeMillis() + 10000L;
        this.\u0138 = true;
    }
    
    public void stopThread() {
        ++this.\u014c;
        this.\u013a.clearBuffer();
        this.\u0138 = false;
    }
    
    public void sendToInfoWindow(final String s, final String s2) {
        if (this.\u010d != null) {
            this.\u010d.add(s, s2);
        }
    }
    
    private synchronized void \u0138() {
        if (this.\u0139()) {
            if (!this.\u013b.emptyBuffer()) {
                this.sendToInfoWindow("Mpexe002:4", "[RECEIVEBUFFER][OK]");
                this.sendToInfoWindow("Mpexe002:4", "[TESTING PROCESS RECEIVEBUFFER]");
                if (this.process("receivePacket")) {
                    this.sendToInfoWindow("Mpexe002:4", "[PROCESS RECEIVEBUFFER][OK]");
                    this.\u0145 = true;
                }
                else {
                    this.sendToInfoWindow("Mpexe002:4", "[PROCESS RECEIVEBUFFER][FAILED]");
                }
            }
            if (!this.\u013a.emptyBuffer()) {
                this.sendToInfoWindow("Mpexe002:4", "[SENDBUFFER][OK]");
                this.sendToInfoWindow("Mpexe002:4", "[TESTING PROCESS SENDBUFFER]");
                if (this.process("sendPacket")) {
                    this.sendToInfoWindow("Mpexe002:4", "[PROCESS SENDBUFFER][OK]");
                    this.\u0145 = true;
                }
                else {
                    this.sendToInfoWindow("Mpexe002:4", "[PROCESS SENDBUFFER][FAILED]");
                }
            }
            if (this.\u0146) {
                if (this.process("replyToAwakeMessage")) {
                    this.\u0145 = true;
                }
                this.\u0146 = false;
            }
        }
        else {
            this.sendToInfoWindow("Mpexe002:4", "[CONNECTION][FAILED]");
        }
    }
    
    private boolean \u0139() {
        if (System.currentTimeMillis() <= this.\u0144) {
            return true;
        }
        if (this.\u0145) {
            this.\u0144 = System.currentTimeMillis() + 10000L;
            this.\u0145 = false;
            return true;
        }
        if (!this.process("testConnection")) {
            this.\u0144 = System.currentTimeMillis() + 10000L;
            this.\u0139.reportConnectionLost("1202");
            return false;
        }
        this.\u0144 = System.currentTimeMillis() + 10000L;
        this.\u0145 = false;
        return true;
    }
    
    public boolean process(final String s) {
        String s2 = "[ERROR][Process not defined]";
        if (s.equals("sendPacket")) {
            s2 = this.processPacket(this.sendPacket("post", this.\u013a.getBuffer()));
        }
        if (s.equals("receivePacket")) {
            s2 = this.processPacket(this.\u013b.getBuffer());
        }
        if (s.equals("replyToAwakeMessage")) {
            s2 = this.processPacket(this.sendPacket("wake", ""));
        }
        if (s.equals("testConnection")) {
            s2 = this.processPacket(this.sendPacket("test", ""));
        }
        return !s2.startsWith("[ERROR]");
    }
    
    public String sendPacket(final String s, final String s2) {
        this.sendToInfoWindow("Mpexe002", "[sending][messageType][" + s + "][packet][" + s2 + "]");
        final Properties properties = new Properties();
        ((Hashtable<String, String>)properties).put("messagetype", s);
        ((Hashtable<String, String>)properties).put("username", this.\u00d4);
        ((Hashtable<String, String>)properties).put("roomID", this.\u013e);
        ((Hashtable<String, String>)properties).put("userID", String.valueOf(this.\u0140));
        ((Hashtable<String, String>)properties).put("uniqueUserNo", String.valueOf(this.\u0141));
        ((Hashtable<String, String>)properties).put("message", s2);
        return this.sendData(properties);
    }
    
    public String sendSpecialPacket(final String s, final String s2) {
        this.sendToInfoWindow("Mpexe002", "[sending][messageType][" + s + "][packet][" + s2 + "]");
        final Properties properties = new Properties();
        ((Hashtable<String, String>)properties).put("messagetype", s);
        ((Hashtable<String, String>)properties).put("username", this.\u00d4);
        ((Hashtable<String, String>)properties).put("userID", String.valueOf(this.\u0140));
        ((Hashtable<String, String>)properties).put("uniqueUserNo", String.valueOf(this.\u0141));
        ((Hashtable<String, String>)properties).put("roomname", this.\u013f);
        ((Hashtable<String, String>)properties).put("roomID", this.\u013e);
        ((Hashtable<String, String>)properties).put("message", s2);
        return this.sendData(properties);
    }
    
    public String processGetRoomID(final int n, final int n2, final String s, final String s2) {
        final Properties properties = new Properties();
        ((Hashtable<String, String>)properties).put("messagetype", "login");
        ((Hashtable<String, String>)properties).put("username", s);
        ((Hashtable<String, String>)properties).put("roomID", String.valueOf(n));
        ((Hashtable<String, String>)properties).put("uniqueRoomNo", String.valueOf(n2));
        if (s2 != null) {
            ((Hashtable<String, String>)properties).put("password", s2);
        }
        String sendData = this.sendData(properties);
        if (sendData.startsWith("[ERROR][MPEXE]")) {
            sendData = "[ERROR]Er kon geen verbinding worden gemaakt met de server, probeer het later nog eens.";
        }
        return sendData;
    }
    
    public String processGetRoomIDCreate(final String s, final String s2, final String s3, final String s4) {
        final Properties properties = new Properties();
        ((Hashtable<String, String>)properties).put("messagetype", "createroom");
        ((Hashtable<String, String>)properties).put("username", s);
        ((Hashtable<String, String>)properties).put("roomname", s2);
        ((Hashtable<String, String>)properties).put("puzzeltype", s3);
        if (s4 != null && s4.trim().length() > 0) {
            ((Hashtable<String, String>)properties).put("password", s4.trim());
        }
        String sendData = this.sendData(properties);
        if (sendData.startsWith("[ERROR][MPEXE]")) {
            sendData = "[ERROR]Er kon geen verbinding worden gemaakt met de server, probeer het later nog eens.";
        }
        return sendData;
    }
    
    public String processGetWaitingRoomID(final String s, final String s2) {
        final Properties properties = new Properties();
        ((Hashtable<String, String>)properties).put("messagetype", "waitingroom");
        ((Hashtable<String, String>)properties).put("username", s);
        ((Hashtable<String, String>)properties).put("puzzeltype", s2);
        String sendData = this.sendData(properties);
        if (sendData.startsWith("[ERROR][MPEXE]")) {
            sendData = "[ERROR]Er kon geen verbinding worden gemaakt met de server, probeer het later nog eens.";
        }
        return sendData;
    }
    
    public String processGetWaitingRoomID2(final int n, final int n2, final String s) {
        final Properties properties = new Properties();
        ((Hashtable<String, String>)properties).put("messagetype", "enterwaitingroom");
        ((Hashtable<String, String>)properties).put("username", s);
        ((Hashtable<String, String>)properties).put("roomID", String.valueOf(n));
        ((Hashtable<String, String>)properties).put("uniqueRoomNo", String.valueOf(n2));
        String sendData = this.sendData(properties);
        if (sendData.startsWith("[ERROR][MPEXE]")) {
            sendData = "[ERROR]Er kon geen verbinding worden gemaakt met de server, probeer het later nog eens.";
        }
        return sendData;
    }
    
    public String loadPuzzle(final int n, final int n2) {
        int n3 = 0;
        final Properties properties = new Properties();
        ((Hashtable<String, String>)properties).put("messagetype", "loadPuzzle");
        ((Hashtable<String, String>)properties).put("roomID", String.valueOf(n));
        ((Hashtable<String, String>)properties).put("puzzlenumber", String.valueOf(n2));
        String s = this.sendData(properties);
        while (s.startsWith("error") && n3 < 3) {
            s = this.sendData(properties);
            if (++n3 > 3) {
                this.\u0138 = false;
            }
        }
        return s.trim();
    }
    
    public String processGetRoomUsersList() {
        int n = 0;
        final Properties properties = new Properties();
        ((Hashtable<String, String>)properties).put("messagetype", "userlist");
        ((Hashtable<String, String>)properties).put("roomID", this.\u013e);
        String s = this.sendData(properties);
        while (s.startsWith("error") && n < 3) {
            s = this.sendData(properties);
            if (++n > 3) {
                this.\u0138 = false;
            }
        }
        return s.substring(5).trim();
    }
    
    public void processLeaveRoom() {
        int n = 0;
        final Properties properties = new Properties();
        ((Hashtable<String, String>)properties).put("messagetype", "logout");
        ((Hashtable<String, String>)properties).put("username", this.\u00d4);
        ((Hashtable<String, String>)properties).put("roomID", this.\u013e);
        String s = this.sendData(properties);
        while (s.startsWith("error") && n < 3) {
            s = this.sendData(properties);
            if (++n > 3) {
                this.\u0138 = false;
            }
        }
    }
    
    public String datePuzzle() {
        int n = 0;
        final Properties properties = new Properties();
        ((Hashtable<String, String>)properties).put("messagetype", "updatepuzzle");
        ((Hashtable<String, String>)properties).put("roomID", this.\u013e);
        String s = this.sendData(properties);
        while (s.startsWith("error") && n < 3) {
            s = this.sendData(properties);
            if (++n > 3) {
                this.\u0138 = false;
            }
        }
        return s;
    }
    
    public String sendData(final Properties properties) {
        this.\u013b.pauseThread();
        String s;
        try {
            final DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new HttpM001(new URL("http", this.\u013c, 80, "/nytimes-settogether/multiplayerservletny")).sendGetMessage(properties)));
            s = dataInputStream.readLine();
            dataInputStream.close();
        }
        catch (SocketException ex) {
            s = "[ERROR][MPEXE][" + ex + "]";
        }
        catch (FileNotFoundException ex2) {
            s = "[ERROR][MPEXE][" + ex2 + "]";
        }
        catch (Exception ex3) {
            s = "[ERROR][MPEXE][" + ex3 + "]";
        }
        this.\u013b.resumeThread();
        return s;
    }
    
    public String processPacket(String s) {
        this.sendToInfoWindow("Mpexe002:2", "processPacket[" + s + "]");
        if (!s.startsWith("[ERROR]")) {
            if (s.startsWith("[OK]")) {
                s = s.substring(4);
            }
            s = s.concat("               ");
            if (s.startsWith("list")) {
                this.sendToInfoWindow("Mpexe002:UPDATEUSERLIST", "<" + s.substring(5).trim() + ">");
                this.\u0139.updateUserList(s.substring(5).trim());
            }
            if (s.startsWith("enter")) {
                this.sendToInfoWindow("Mpexe002:ENTERWAITINGROOM", "<" + s.substring(6).trim() + ">");
                this.\u0139.puzzlerInWaitingRoom(s.substring(6));
            }
            if (s.startsWith("[CHAT]")) {
                s = s.substring(6);
            }
            if (s.startsWith("chatX")) {
                final String trim = s.substring(5).trim();
                this.sendToInfoWindow("Mpexe002:PROCESSCHATMESSAGE", "<" + trim + ">");
                this.\u0139.processChatMessage(trim);
            }
            if (s.startsWith("[M]")) {
                this.sendToInfoWindow("Mpexe002:PROCESSMOVES", "<" + s + ">");
                this.processMoves(s);
                this.\u0139.setSolutions(this.getCurrentSolutions(s));
            }
            if (s.startsWith("setmove")) {
                this.\u0139.processSetMove(s);
            }
            s.startsWith("wake");
            if (s.startsWith("uout")) {
                this.\u0139.updateUserList(this.processGetRoomUsersList());
            }
            if (s.startsWith("[LUR]")) {
                this.\u0139.updateUserList(this.processGetRoomUsersList());
            }
            if (s.startsWith("[OUTOFROOM]")) {
                this.\u0139.reportOutOfRoom();
            }
            return "[OK]";
        }
        return s;
    }
    
    public String processGetMovesDone() {
        int n = 0;
        final Properties properties = new Properties();
        ((Hashtable<String, String>)properties).put("messagetype", "updatemoves");
        ((Hashtable<String, String>)properties).put("roomID", this.\u013e);
        String s = this.sendData(properties);
        while (s.startsWith("error") && n < 3) {
            s = this.sendData(properties);
            if (++n > 3) {
                this.\u0138 = false;
            }
        }
        if (s.startsWith("[OK][M]") || s.startsWith("[M]")) {
            final String substring = s.substring(s.indexOf("[LMR]", 0) + 5);
            final int int1 = Integer.parseInt(substring.substring(1, substring.indexOf(93)));
            this.\u013b.setLastMove(int1);
            this.sendToInfoWindow("Mpexe002:LASTMOVERECEIVED", "<" + int1 + ">");
            return s.substring(4);
        }
        return s;
    }
    
    public String processUpdatePuzzle() {
        int n = 0;
        final Properties properties = new Properties();
        ((Hashtable<String, String>)properties).put("messagetype", "updatepuzzle");
        ((Hashtable<String, String>)properties).put("roomID", this.\u013e);
        String s = this.sendData(properties);
        while (s.startsWith("error") && n < 3) {
            s = this.sendData(properties);
            if (++n > 3) {
                this.\u0138 = false;
            }
        }
        return s;
    }
    
    public void processMoves(final String s) {
        String s2 = s.substring(4);
        String concat = "";
        for (String substring = ""; !substring.startsWith("]"); substring = s2.substring(0, 1), s2 = s2.substring(1)) {
            concat = concat.concat(substring);
        }
        final int int1 = Integer.parseInt(concat);
        String s3 = s2.substring(1);
        for (int i = 0; i < int1; ++i) {
            final PuzzleMove puzzleMove = new PuzzleMove(s3.substring(0, 10));
            this.\u0139.processPuzzleMove(puzzleMove.getToXValue(), puzzleMove.getToYValue(), puzzleMove.getCodeValue(), puzzleMove.getUserIDValue());
            s3 = s3.substring(10);
        }
    }
    
    public boolean enterRoom(final int n, final int n2, final String \u00f4, final String s) {
        final String processGetRoomID = this.processGetRoomID(n, n2, \u00f4, s);
        if (processGetRoomID.startsWith("[OK]")) {
            this.\u00d4 = \u00f4;
            this.\u013f = "not used anymore";
            this.sendToInfoWindow("Mpexe002:3", "[systemMessage][" + processGetRoomID + "]");
            this.\u013e = this.getStringValue(processGetRoomID, "ROOMID");
            this.\u0140 = this.getIntegerValue(processGetRoomID, "USERID");
            this.\u0141 = this.getIntegerValue(processGetRoomID, "UNIQUEUSERNO");
            this.\u0142 = this.getIntegerValue(processGetRoomID, "PUZZLETYPE");
            final int integerValue = this.getIntegerValue(processGetRoomID, "LCR");
            final int integerValue2 = this.getIntegerValue(processGetRoomID, "LMR");
            if (this.\u014b) {
                this.\u013b.setStream(this.\u0148);
            }
            this.\u013b.setRoomID(this.\u013e);
            this.sendToInfoWindow("Mpexe002:8", "<CALLINGSETUSERID>" + this.\u0140 + "></CALLINGSETUSERID>");
            this.\u013b.setUserID(this.\u0140);
            this.\u013b.setUniqueUserNo(this.\u0141);
            this.\u013b.setLastMove(integerValue2);
            this.\u013b.setLastChat(integerValue);
            this.\u0139.updateUserList(this.processGetRoomUsersList());
            if (integerValue2 > 0) {
                this.processMoves(this.processGetMovesDone());
            }
            return true;
        }
        this.\u0115 = processGetRoomID.substring(7);
        return false;
    }
    
    public boolean createRoom(final String \u00f4, final String \u0140, final String s, final String s2) {
        String s3;
        if (this.\u014b) {
            if (this.createSocketConnection()) {
                s3 = this.processGetRoomIDSocket(\u00f4, \u0140, s);
            }
            else {
                s3 = "error";
            }
        }
        else {
            s3 = this.processGetRoomIDCreate(\u00f4, \u0140, s, s2);
        }
        if (s3.startsWith("[OK]")) {
            this.\u00d4 = \u00f4;
            this.\u013f = \u0140;
            this.sendToInfoWindow("Mpexe002:3", "[systemMessage][" + s3 + "]");
            this.\u013e = this.getStringValue(s3, "ROOMID");
            this.\u0140 = this.getIntegerValue(s3, "USERID");
            this.\u0141 = this.getIntegerValue(s3, "UNIQUEUSERNO");
            final int integerValue = this.getIntegerValue(s3, "LCR");
            final int integerValue2 = this.getIntegerValue(s3, "LMR");
            if (this.\u014b) {
                this.\u013b.setStream(this.\u0148);
            }
            this.\u013b.setRoomID(this.\u013e);
            this.sendToInfoWindow("Mpexe002:8", "<CALLINGSETUSERID>" + this.\u0140 + "></CALLINGSETUSERID>");
            this.\u013b.setUserID(this.\u0140);
            this.\u013b.setUniqueUserNo(this.\u0141);
            this.\u013b.setLastMove(integerValue2);
            this.\u013b.setLastChat(integerValue);
            this.\u0139.updateUserList(this.processGetRoomUsersList());
            if (integerValue2 > 0) {
                this.processMoves(this.processGetMovesDone());
            }
            return true;
        }
        this.\u0115 = s3.substring(7);
        return false;
    }
    
    public String getRoomName() {
        return this.\u013f;
    }
    
    public boolean enterWaitingRoom(final String \u00f4, final String s) {
        final String processGetWaitingRoomID = this.processGetWaitingRoomID(\u00f4, s);
        if (processGetWaitingRoomID.startsWith("[OK]")) {
            this.\u00d4 = \u00f4;
            this.\u013f = this.getStringValue(processGetWaitingRoomID, "ROOMNAME");
            this.sendToInfoWindow("Mpexe002:3", "[systemMessage][" + processGetWaitingRoomID + "]");
            this.\u013e = this.getStringValue(processGetWaitingRoomID, "ROOMID");
            this.\u0140 = this.getIntegerValue(processGetWaitingRoomID, "USERID");
            this.\u0141 = this.getIntegerValue(processGetWaitingRoomID, "UNIQUEUSERNO");
            final int lastChat = 0;
            final int lastMove = 0;
            if (this.\u014b) {
                this.\u013b.setStream(this.\u0148);
            }
            this.\u013b.setRoomID(this.\u013e);
            this.sendToInfoWindow("Mpexe002:8", "<CALLINGSETUSERID>" + this.\u0140 + "></CALLINGSETUSERID>");
            this.\u013b.setUserID(this.\u0140);
            this.\u013b.setUniqueUserNo(this.\u0141);
            this.\u013b.setLastMove(lastMove);
            this.\u013b.setLastChat(lastChat);
            if (lastMove > 0) {
                this.processMoves(this.processGetMovesDone());
            }
            return true;
        }
        this.\u0115 = processGetWaitingRoomID.substring(7);
        return false;
    }
    
    public boolean enterWaitingRoom2(final int n, final int n2, final String \u00f4) {
        final String processGetWaitingRoomID2 = this.processGetWaitingRoomID2(n, n2, \u00f4);
        if (processGetWaitingRoomID2.startsWith("[OK]")) {
            this.\u00d4 = \u00f4;
            this.\u013f = this.getStringValue(processGetWaitingRoomID2, "ROOMNAME");
            this.sendToInfoWindow("Mpexe002:3", "[systemMessage][" + processGetWaitingRoomID2 + "]");
            this.\u013e = this.getStringValue(processGetWaitingRoomID2, "ROOMID");
            this.\u0140 = this.getIntegerValue(processGetWaitingRoomID2, "USERID");
            this.\u0141 = this.getIntegerValue(processGetWaitingRoomID2, "UNIQUEUSERNO");
            this.\u0142 = this.getIntegerValue(processGetWaitingRoomID2, "PUZZLETYPE");
            final int lastChat = 0;
            final int lastMove = 0;
            if (this.\u014b) {
                this.\u013b.setStream(this.\u0148);
            }
            this.\u013b.setRoomID(this.\u013e);
            this.sendToInfoWindow("Mpexe002:8", "<CALLINGSETUSERID>" + this.\u0140 + "></CALLINGSETUSERID>");
            this.\u013b.setUserID(this.\u0140);
            this.\u013b.setUniqueUserNo(this.\u0141);
            this.\u013b.setLastMove(lastMove);
            this.\u013b.setLastChat(lastChat);
            this.\u0139.updateUserList(this.processGetRoomUsersList());
            if (lastMove > 0) {
                this.processMoves(this.processGetMovesDone());
            }
            return true;
        }
        this.\u0115 = processGetWaitingRoomID2.substring(7);
        return false;
    }
    
    public int getIntegerValue(final String s, final String s2) {
        final int n = s.indexOf("<" + s2 + ">", 0) + s2.length() + 2;
        final int index = s.indexOf("</" + s2 + ">", 0);
        this.sendToInfoWindow("Mpexe002:7", "[indexBegin]" + n);
        this.sendToInfoWindow("Mpexe002:7", "[indexEnd]" + index);
        if (n > 0 & index > 0) {
            this.sendToInfoWindow("Mpexe002:7", "<" + s2 + ">" + s.substring(n, index) + "</" + s2 + ">");
            return Integer.parseInt(s.substring(n, index));
        }
        this.sendToInfoWindow("Mpexe002:7", "<" + s2 + ">NOT FOUND</" + s2 + ">");
        return 0;
    }
    
    public int getPuzzleType() {
        return this.\u0142;
    }
    
    public SetSolutionList getCurrentSolutions() {
        final SetSolutionList list = new SetSolutionList();
        int n = 0;
        final Properties properties = new Properties();
        ((Hashtable<String, String>)properties).put("messagetype", "getcurrentsolutions");
        ((Hashtable<String, String>)properties).put("roomID", this.\u013e);
        String s = this.sendData(properties);
        while (s.startsWith("error") && n < 3) {
            s = this.sendData(properties);
            if (++n > 3) {
                this.\u0138 = false;
            }
        }
        if (s.startsWith("[OK]")) {
            String s2 = this.getTagValue(s, "<SOLUTIONGRID>");
            if (s2.length() == 0) {
                return null;
            }
            int n2 = s2.indexOf("setmove[");
            int n3 = s2.indexOf("]]");
            while (n2 >= 0 && n3 >= 0) {
                final String substring = s2.substring(n2, n3);
                final int int1 = Integer.parseInt(substring.substring(8, substring.indexOf("]")).trim());
                final String trim = substring.substring(substring.indexOf("][") + 2).trim();
                final int int2 = Integer.parseInt(trim.substring(0, trim.indexOf("]")).trim());
                final String trim2 = trim.substring(trim.indexOf("][") + 2).trim();
                final int int3 = Integer.parseInt(trim2.substring(0, trim2.indexOf("]")).trim());
                final String trim3 = trim2.substring(trim2.indexOf("][") + 2).trim();
                s2 = s2.substring(n3 + 1);
                n2 = s2.indexOf("setmove[");
                n3 = s2.indexOf("]]");
                if (trim3.length() > 0) {
                    list.setSolution(new SetSolution(int1, int2, int3, trim3));
                }
                else {
                    list.setSolution(new SetSolution(int1, int2, int3));
                }
            }
        }
        return list;
    }
    
    public SetSolutionList getCurrentSolutions(final String s) {
        final SetSolutionList list = new SetSolutionList();
        if (s.startsWith("[OK]")) {
            String s2 = this.getTagValue(s, "<SOLUTIONGRID>");
            if (s2.length() == 0) {
                return null;
            }
            int n = s2.indexOf("setmove[");
            int n2 = s2.indexOf("]]");
            while (n >= 0 && n2 >= 0) {
                final String substring = s2.substring(n, n2);
                final int int1 = Integer.parseInt(substring.substring(8, substring.indexOf("]")).trim());
                final String trim = substring.substring(substring.indexOf("][") + 2).trim();
                final int int2 = Integer.parseInt(trim.substring(0, trim.indexOf("]")).trim());
                final String trim2 = trim.substring(trim.indexOf("][") + 2).trim();
                final int int3 = Integer.parseInt(trim2.substring(0, trim2.indexOf("]")).trim());
                final String trim3 = trim2.substring(trim2.indexOf("][") + 2).trim();
                s2 = s2.substring(n2 + 1);
                n = s2.indexOf("setmove[");
                n2 = s2.indexOf("]]");
                if (trim3.length() > 0) {
                    list.setSolution(new SetSolution(int1, int2, int3, trim3));
                }
                else {
                    list.setSolution(new SetSolution(int1, int2, int3));
                }
            }
        }
        return list;
    }
    
    public String getTagValue(final String s, final String s2) {
        final String lowerCase = s.toLowerCase();
        final String lowerCase2 = s2.toLowerCase();
        final int n = lowerCase.indexOf(lowerCase2, 0) + lowerCase2.length();
        final int index = lowerCase.indexOf("</" + lowerCase2.substring(1, lowerCase2.length()), 0);
        if (n >= 0 & index > 0) {
            return s.substring(n, index);
        }
        return "";
    }
    
    public int getTagValueInt(final String s, final String s2, final String s3) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, String.valueOf(s2) + s3);
        String nextToken;
        try {
            if (stringTokenizer.hasMoreTokens()) {
                nextToken = stringTokenizer.nextToken();
            }
            else {
                nextToken = "0";
            }
        }
        catch (NoSuchElementException ex) {
            nextToken = "0";
        }
        int int1;
        try {
            int1 = Integer.parseInt(nextToken);
        }
        catch (Exception ex2) {
            int1 = 0;
        }
        return int1;
    }
    
    public String getStringValue(final String s, final String s2) {
        final int n = s.indexOf("<" + s2 + ">", 0) + s2.length() + 2;
        final int index = s.indexOf("</" + s2 + ">", 0);
        this.sendToInfoWindow("Mpexe002:7", "[indexBegin]" + n);
        this.sendToInfoWindow("Mpexe002:7", "[indexEnd]" + index);
        if (n > 0 & index > 0) {
            this.sendToInfoWindow("Mpexe002:7", "<" + s2 + ">" + s.substring(n, index) + "</" + s2 + ">");
            return s.substring(n, index);
        }
        this.sendToInfoWindow("Mpexe002:7", "<" + s2 + ">NOT FOUND</" + s2 + ">");
        return "";
    }
    
    public void leaveRoom() {
        this.processLeaveRoom();
    }
    
    public int getUserID() {
        return this.\u0140;
    }
    
    public int getUniqueNo() {
        return this.\u0141;
    }
    
    public String getRoomID() {
        return this.\u013e;
    }
    
    public String getErrorMessage() {
        return this.\u0115;
    }
    
    public int reconnect() {
        final String sendSpecialPacket = this.sendSpecialPacket("reconnect", "");
        if (sendSpecialPacket.startsWith("[ERROR]")) {
            return -1;
        }
        if (sendSpecialPacket.startsWith("[OK]")) {
            return 0;
        }
        if (sendSpecialPacket.startsWith("[RELOGIN]")) {
            this.\u013a(sendSpecialPacket);
            return 1;
        }
        if (sendSpecialPacket.startsWith("[NOROOM]")) {
            return 2;
        }
        if (sendSpecialPacket.startsWith("[USERNAMEEXISTS]")) {
            return 3;
        }
        return -1;
    }
    
    void \u013a(final String s) {
        this.\u0140 = this.getIntegerValue(s, "USERID");
        this.\u0141 = this.getIntegerValue(s, "UNIQUEUSERNO");
        final int integerValue = this.getIntegerValue(s, "LCR");
        final int integerValue2 = this.getIntegerValue(s, "LMR");
        this.sendToInfoWindow("Mpexe002:8", "<CALLINGSETUSERID>" + this.\u0140 + "></CALLINGSETUSERID>");
        this.\u013b.setUserID(this.\u0140);
        this.\u013b.setUniqueUserNo(this.\u0141);
        this.\u013b.setLastMove(integerValue2);
        this.\u013b.setLastChat(integerValue);
        this.\u0139.updateUserList(this.processGetRoomUsersList());
        this.\u0139.updatePuzzle(this.processUpdatePuzzle());
    }
    
    public String processGetRoomIDSocket(final String s, final String s2, final String s3) {
        final String s4 = "";
        int n = 0;
        String socketData;
        if (this.sendSocketData(String.valueOf(new StringBuffer(String.valueOf(new StringBuffer(String.valueOf(new StringBuffer(String.valueOf(new StringBuffer(String.valueOf(new StringBuffer(String.valueOf(s4)).append("messagetype||login||username||").toString())).append(s).toString())).append("||roomname||").toString())).append(s2).toString())).append("||puzzeltype||").toString()) + s3)) {
            socketData = this.getSocketData();
        }
        else {
            socketData = "error";
        }
        while (socketData.startsWith("error") && n < 3) {
            n = 4;
            if (n > 3) {
                this.\u0138 = false;
            }
        }
        return socketData;
    }
    
    public boolean createSocketConnection() {
        try {
            this.\u0147 = new Socket(this.\u013c, this.\u014a);
            if (this.\u0148 == null) {
                this.\u0148 = new DataInputStream(new BufferedInputStream(this.\u0147.getInputStream()));
            }
            if (this.\u0149 == null) {
                this.\u0149 = new PrintStream(this.\u0147.getOutputStream());
            }
        }
        catch (SocketException ex2) {
            this.\u0148 = null;
            this.\u0149 = null;
            return false;
        }
        catch (Exception ex) {
            System.out.println("General exception: " + ex.getClass().getName() + ": " + ex.getMessage());
            return false;
        }
        return true;
    }
    
    public String getSocketData() {
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
    
    public boolean sendSocketData(final String s) {
        try {
            this.\u0149.println(s);
        }
        catch (Exception ex) {
            return false;
        }
        return true;
    }
}
