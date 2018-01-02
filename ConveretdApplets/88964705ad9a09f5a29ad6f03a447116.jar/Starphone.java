import java.util.Date;
import java.io.Writer;
import java.io.FileWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.StringTokenizer;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Vector;
import java.awt.Container;
import java.applet.Applet;
import netscape.javascript.JSObject;
import java.io.BufferedWriter;
import java.io.File;
import javax.swing.JApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Starphone extends JApplet
{
    public static final String VERSION = "0.51";
    public static final String host = "asterisk.liveonthenet.com";
    public static String username;
    public static String secret;
    public static networkThread nt;
    public static jsListener mw;
    public static regThread rt;
    public static int iaxDebug;
    public static boolean promptForConfig;
    private static Thread trt;
    private static String toCall;
    private static String lineSep;
    private static String fileSep;
    private static String confFile;
    private static String logFile;
    private static File pathToClientHome;
    private static File pathToClientConf;
    private static File lockFile;
    private static boolean enableLogging;
    private static boolean logStarted;
    private static boolean consoleDebug;
    private static BufferedWriter logWriter;
    public static JSObject win;
    public static short JSscno;
    public static short callNumber;
    public static Boolean isCallActive;
    public static String chatName;
    
    public void init() {
        super.init();
        Starphone.chatName = this.getParameter("chatName");
        Starphone.win = JSObject.getWindow((Applet)this);
        initializePlatform();
        if (Starphone.username == null || Starphone.username.equals("")) {
            Starphone.username = "embedUser";
            Starphone.secret = "embedPass";
        }
        final Vector<IAXCall> v = IAXCall.getCallarray();
        v.add(new IAXCall());
        Starphone.mw = new jsListener(Starphone.chatName);
        Starphone.nt = new networkThread(Starphone.mw);
        new Thread(Starphone.nt).start();
        Starphone.rt = new regThread("asterisk.liveonthenet.com", Starphone.username);
        (Starphone.trt = new Thread(Starphone.rt)).start();
        final schedThread st = new schedThread();
        new Thread(st).start();
        System.out.println("inited");
        this.setContentPane(Starphone.mw);
        st.callTarget = "presence:" + Starphone.chatName;
        genCall("presence:" + Starphone.chatName);
    }
    
    public void destroy() {
    }
    
    public static short genCall(final String callednumber) {
        if (Starphone.username == null || Starphone.username.equals("")) {
            return -1;
        }
        return IAXCall.Dial("asterisk.liveonthenet.com", callednumber);
    }
    
    public static void setCallActive(final Boolean ca) {
        Starphone.isCallActive = ca;
    }
    
    public static void loginUser(final String user, final String password) {
        Starphone.username = user;
        Starphone.secret = password;
        try {
            AccessController.doPrivileged((PrivilegedAction<Object>)new PrivilegedAction() {
                public Object run() {
                    final regThread rt = Starphone.rt;
                    regThread.setRegUsername(Starphone.username);
                    return 1;
                }
            });
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public short javaGenCall(final String cn) {
        Starphone.JSscno = 0;
        if (Starphone.callNumber != -1) {
            userHangup(Starphone.callNumber, 16, "Normal Clearing");
            Starphone.callNumber = -1;
            Starphone.isCallActive = false;
        }
        final String callednumber = new String(cn);
        if (Starphone.username == null || Starphone.username.equals("") || Starphone.username.equals("default")) {
            return -1;
        }
        try {
            AccessController.doPrivileged((PrivilegedAction<Object>)new PrivilegedAction() {
                public Object run() {
                    if (callednumber != null && !callednumber.equals("")) {
                        Starphone.JSscno = IAXCall.Dial("asterisk.liveonthenet.com", callednumber);
                        return Starphone.JSscno;
                    }
                    return -1;
                }
            });
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        Starphone.callNumber = Starphone.JSscno;
        Starphone.isCallActive = true;
        return Starphone.JSscno;
    }
    
    public static void javaGetHome() {
        String user = "";
        try {
            user = AccessController.doPrivileged((PrivilegedAction<String>)new PrivilegedAction() {
                public Object run() {
                    return System.getProperty("user.home");
                }
            });
            System.out.println(user);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        reportCall(user);
    }
    
    public void setText(final String s) {
        reportCall(s);
    }
    
    public static void reportCall(final String reportText) {
        try {
            Starphone.win.call("updateText", (Object[])new String[] { reportText });
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void userHangup(final int scno, final int causecode, final String cause) {
        try {
            AccessController.doPrivileged((PrivilegedAction<Object>)new PrivilegedAction() {
                public Object run() {
                    final networkThread nt = Starphone.nt;
                    final int idx = networkThread.getCallIndex((short)scno, 0);
                    final networkThread nt2 = Starphone.nt;
                    networkThread.sendHangup(idx, causecode, cause);
                    schedThread.nowPlayingTones = false;
                    return 1;
                }
            });
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void userAnswer(final short scno) {
        try {
            AccessController.doPrivileged((PrivilegedAction<Object>)new PrivilegedAction() {
                public Object run() {
                    final networkThread nt = Starphone.nt;
                    final IAXCall ic = networkThread.getCall(scno, 0);
                    final networkThread nt2 = Starphone.nt;
                    final int idx = networkThread.getCallIndex(scno, 0);
                    if (ic.getCallState() == IAXCall.STATE.RINGING) {
                        ic.setCallState(IAXCall.STATE.UP);
                        schedThread.nowPlayingTones = false;
                        Starphone.nt.sendAnswer(idx);
                    }
                    return scno;
                }
            });
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void callAlert(final short num) {
        final String numtext = String.valueOf(num);
        System.out.println("Num inside callAlert =" + num);
        System.out.println("NumText inside callAlert =" + numtext);
        try {
            Starphone.win.call("callAlert", (Object[])new String[] { numtext });
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void userHold(final short scno) {
        try {
            AccessController.doPrivileged((PrivilegedAction<Object>)new PrivilegedAction() {
                public Object run() {
                    final networkThread nt = Starphone.nt;
                    final IAXCall ic = networkThread.getCall(scno, 0);
                    final networkThread nt2 = Starphone.nt;
                    final int idx = networkThread.getCallIndex(scno, 0);
                    if (ic.getCallState() == IAXCall.STATE.UP) {
                        ic.setCallState(IAXCall.STATE.HOLD);
                        Starphone.nt.sendQuelch(idx);
                    }
                    else if (ic.getCallState() == IAXCall.STATE.HOLD) {
                        ic.setCallState(IAXCall.STATE.UP);
                        Starphone.nt.sendUnquelch(idx);
                    }
                    return 1;
                }
            });
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void sendDTMF(final String s, final short scno) {
        Starphone.nt.sendDTMF(s, scno);
    }
    
    public static void writeConf(String field, final String value) {
        boolean valueWritten = false;
        if (field.equals("Password")) {
            field = "secret";
        }
        String toWrite = "";
        try {
            final FileInputStream fstream = new FileInputStream(Starphone.pathToClientConf);
            final BufferedReader in = new BufferedReader(new InputStreamReader(fstream));
            String s = null;
            StringTokenizer st = null;
            while (in.ready()) {
                st = new StringTokenizer(in.readLine());
                while (st.hasMoreTokens()) {
                    s = st.nextToken();
                    toWrite += s;
                    if (s.equalsIgnoreCase(field)) {
                        toWrite = toWrite + " = " + value;
                        valueWritten = true;
                        break;
                    }
                    toWrite += " ";
                }
                toWrite += Starphone.lineSep;
            }
            if (!valueWritten) {
                toWrite = toWrite + field + " = " + value + Starphone.lineSep;
            }
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
        try {
            final FileOutputStream fos = new FileOutputStream(Starphone.pathToClientConf);
            log("Writing configuration file " + Starphone.pathToClientConf);
            fos.write(toWrite.getBytes());
            fos.flush();
            fos.close();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
        readConf(Starphone.pathToClientConf);
    }
    
    public static String readConf(final String field) {
        if (Starphone.pathToClientConf == null) {
            return null;
        }
        String ret = "";
        try {
            final FileInputStream fstream = new FileInputStream(Starphone.pathToClientConf);
            final BufferedReader in = new BufferedReader(new InputStreamReader(fstream));
            String s = null;
            StringTokenizer st = null;
            while (in.ready()) {
                st = new StringTokenizer(in.readLine());
                s = st.nextToken();
                if (s.equals(field)) {
                    st.nextToken();
                    ret = st.nextToken();
                    break;
                }
            }
            in.close();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return ret;
    }
    
    private static void readConf(final File f) {
        try {
            final FileInputStream fstream = new FileInputStream(f);
            final BufferedReader in = new BufferedReader(new InputStreamReader(fstream));
            String s = null;
            StringTokenizer st = null;
            while (in.ready()) {
                st = new StringTokenizer(in.readLine());
                s = st.nextToken();
                if (s.equals("username")) {
                    st.nextToken();
                    s = st.nextToken();
                    if (!s.equals(Starphone.username) && Starphone.rt != null) {
                        final regThread rt = Starphone.rt;
                        regThread.setRegUsername(s);
                    }
                    Starphone.username = s;
                    if (!Starphone.username.equals("default")) {
                        continue;
                    }
                    Starphone.promptForConfig = true;
                }
                else {
                    if (!s.equals("secret")) {
                        continue;
                    }
                    st.nextToken();
                    Starphone.secret = st.nextToken();
                    if (!Starphone.secret.equals("default")) {
                        continue;
                    }
                    Starphone.promptForConfig = true;
                }
            }
            in.close();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    
    public static void sendSearch(final String s, final short scno) {
        Starphone.nt.sendText(s, scno);
    }
    
    public static void parseOpts(final String[] args) {
        if (args == null) {
            return;
        }
        for (int i = 0; i < args.length; ++i) {
            if (args[i].equals("-c")) {
                Starphone.consoleDebug = true;
            }
            if (args[i].equals("-d")) {
                Starphone.iaxDebug = 2;
            }
            if (args[i].equals("-f")) {
                if (i + 1 <= args.length) {
                    readSPHFile(args[i + 1]);
                }
                else {
                    System.out.println("Must specify a file when using -f");
                    doExit();
                }
            }
            if (args[i].equals("-h")) {
                printHelp();
                doExit();
            }
            if (args[i].equals("-l")) {
                Starphone.enableLogging = true;
            }
            if (args[i].equals("-v")) {
                System.out.println("Starphone Version 0.51");
                doExit();
            }
        }
    }
    
    public static void setSpeaker(final int callno, final int value) {
        final networkThread nt = Starphone.nt;
        networkThread.setSpeaker(callno, value);
    }
    
    public static void setMic(final int callno, final int value) {
        final networkThread nt = Starphone.nt;
        networkThread.setMic(callno, value);
    }
    
    public static void setMute(final int callno, final boolean state) {
        final networkThread nt = Starphone.nt;
        networkThread.setMute(callno, state);
    }
    
    public static void toggleMute(final int callno) {
        final networkThread nt = Starphone.nt;
        networkThread.toggleMute(callno);
    }
    
    public static void log(final String s, final int level) {
        if (level > 0) {
            if (Starphone.consoleDebug) {
                System.out.println(s);
            }
            if (Starphone.enableLogging) {
                if (!Starphone.logStarted) {
                    initLog();
                }
                try {
                    Starphone.logWriter.write(s + Starphone.lineSep);
                    Starphone.logWriter.flush();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public static void log(final String s) {
        log(s, 1);
    }
    
    private static void initLog() {
        try {
            Starphone.logWriter = new BufferedWriter(new FileWriter(new File(Starphone.pathToClientHome + Starphone.fileSep + Starphone.logFile), true));
            Starphone.logStarted = true;
            log("********************************");
            log("New log started at " + new Date(System.currentTimeMillis()));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void doExit() {
        if (regThread.exiting) {
            return;
        }
        regThread.exiting = true;
        networkThread.allCallHangup();
        regThread.releaseReg();
        if (Starphone.enableLogging) {
            log("");
            try {
                Starphone.logWriter.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        deleteLock();
    }
    
    private static void printHelp() {
        System.out.println("Starphone Beta Version 1.0, Copyright (C) 2006, Live on the Net, Inc.");
        System.out.println("Valid options:");
        System.out.println("\t-c\t\tEnable console debugging");
        System.out.println("\t-d\t\tEnable explicit IAX message debugging");
        System.out.println("\t-f\t\tLaunch the phone using the specified .sph file to automatically generate a call");
        System.out.println("\t-h\t\tPrint this help screen and exit");
        System.out.println("\t-l\t\tEnable logging to a file");
        System.out.println("\t-h\t\tPrint version information and exit");
    }
    
    private static void initializePlatform() {
        Starphone.lineSep = System.getProperty("line.separator");
        final String userHome = System.getProperty("user.home");
        final String osName = System.getProperty("os.name");
        if (osName.indexOf("Windows") > -1) {
            Starphone.pathToClientHome = new File(userHome + Starphone.fileSep + "Application Data" + Starphone.fileSep + ".starphone");
        }
        else if (osName.indexOf("Mac") > -1) {
            Starphone.pathToClientHome = new File(userHome + Starphone.fileSep + ".starphone");
        }
        else if (osName.indexOf("Linux") > -1) {
            Starphone.pathToClientHome = new File(userHome + Starphone.fileSep + ".starphone");
        }
        else {
            System.out.println("ERROR: Your operating system " + osName + " is not supported by this version of Starphone. Exiting...");
            doExit();
        }
        if (!Starphone.pathToClientHome.isDirectory()) {
            System.out.println("~/.starphone directory does not exist, attempting to create it.");
            if (Starphone.pathToClientHome.mkdir()) {
                System.out.println("Directory created: " + Starphone.pathToClientHome.getAbsolutePath());
            }
            else {
                System.out.println("Failed to create directory: " + Starphone.pathToClientHome.getAbsolutePath() + ". Exiting...");
                doExit();
            }
        }
        if (Starphone.enableLogging) {
            initLog();
        }
        Starphone.pathToClientConf = new File(Starphone.pathToClientHome + Starphone.fileSep + Starphone.confFile);
        if (!Starphone.pathToClientConf.isFile()) {
            Starphone.promptForConfig = true;
            log("Creating file: " + Starphone.pathToClientConf);
            try {
                final BufferedWriter out = new BufferedWriter(new FileWriter(Starphone.pathToClientConf));
                out.write("username = default" + Starphone.lineSep);
                out.flush();
                out.write("secret = default" + Starphone.lineSep);
                out.flush();
                out.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        log("Starphone path: " + Starphone.pathToClientConf);
        log("Operating System: " + osName);
    }
    
    public static void wakeupRegThread() {
        Starphone.trt.interrupt();
    }
    
    public static void setSecret(final String s) {
        if (s == null || s.equals("default")) {
            return;
        }
        writeConf("Password", Starphone.secret = s);
    }
    
    public static void setUsername(final String s) {
        if (s == null || s.equals("default")) {
            return;
        }
        writeConf("username", Starphone.username = s);
    }
    
    private static void readSPHFile(final String s) {
        log("Reading .sph file " + s);
        final File f = new File(s);
        try {
            final FileInputStream fstream = new FileInputStream(f);
            final BufferedReader in = new BufferedReader(new InputStreamReader(fstream));
            Starphone.toCall = in.readLine();
            in.close();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    
    public static File getClientHomePath() {
        return Starphone.pathToClientHome;
    }
    
    private static void writeLock() {
        try {
            (Starphone.lockFile = new File(System.getProperty("java.io.tmpdir") + File.separator + "starphone.lock")).createNewFile();
            System.out.println("Creating lockfile " + Starphone.lockFile.getAbsolutePath());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if (!Starphone.lockFile.exists()) {
            System.out.println("Lockfile could not be created... continuing anyway.");
        }
    }
    
    private static void deleteLock() {
        System.out.println("Deleting lockfile " + Starphone.lockFile.getAbsolutePath());
        Starphone.lockFile.delete();
    }
    
    static {
        Starphone.username = null;
        Starphone.secret = null;
        Starphone.rt = null;
        Starphone.iaxDebug = 0;
        Starphone.promptForConfig = false;
        Starphone.trt = null;
        Starphone.toCall = "";
        Starphone.lineSep = "";
        Starphone.fileSep = File.separator;
        Starphone.confFile = "starphone.conf";
        Starphone.logFile = "starphone.log";
        Starphone.pathToClientHome = null;
        Starphone.pathToClientConf = null;
        Starphone.lockFile = null;
        Starphone.enableLogging = true;
        Starphone.logStarted = false;
        Starphone.consoleDebug = true;
        Starphone.callNumber = -1;
        Starphone.isCallActive = false;
    }
}
