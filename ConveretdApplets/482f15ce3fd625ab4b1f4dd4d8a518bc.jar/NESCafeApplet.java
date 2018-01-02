import java.awt.event.FocusEvent;
import java.awt.Cursor;
import java.net.UnknownServiceException;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.io.ByteArrayOutputStream;
import java.awt.Toolkit;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Color;
import java.util.zip.ZipEntry;
import java.net.URLConnection;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.zip.ZipInputStream;
import java.io.PrintStream;
import java.net.URLEncoder;
import java.net.URL;
import java.io.DataInputStream;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.FocusListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class NESCafeApplet extends Applet implements Runnable, FocusListener
{
    private String a;
    public boolean forceDebugMode;
    public String loadStateOnStartup;
    public boolean loadStateOnStartupTrigger;
    private aE a;
    private au a;
    private Thread a;
    private String b;
    public Image nescafeImg;
    public String username;
    private int a;
    private int b;
    public String settingsfile;
    private Font a;
    private Font b;
    private Font c;
    public static final int BROADCAST_SUCCESS = 0;
    public static final int BROADCAST_FAILURE = 1;
    public static final int BROADCAST_SCRIPT_OLD = 2;
    public static final int BROADCAST_SCRIPT_HTML_RESPONSE = 3;
    public static final int BROADCAST_SCRIPT_NOTWEBSITE = 4;
    public String lastBroadcastError;
    private boolean a;
    private boolean b;
    public boolean hasFocus;
    private boolean c;
    
    private boolean a() {
        final String parameter;
        return (parameter = this.a.a.getParameter("LOADDATAURL")) != null && !parameter.equals("");
    }
    
    private boolean b() {
        final String parameter;
        return (parameter = this.a.a.getParameter("SAVEDATAURL")) != null && !parameter.equals("");
    }
    
    public boolean canAppletLoadState() {
        return this.a() && this.a.a.c && !this.b;
    }
    
    public boolean canAppletSaveState() {
        return this.b() && this.a.a.c && !this.a;
    }
    
    public boolean canAppletSaveRAM() {
        return this.b() && this.a.a.b && !this.a;
    }
    
    public boolean canAppletLoadRAM() {
        return this.a() && this.a.a.b && !this.b;
    }
    
    public boolean canAppletSaveAnimGif() {
        return this.b() && this.a.a.e && !this.a;
    }
    
    public boolean canAppletSaveSettings() {
        return this.b() && !this.a;
    }
    
    public boolean canAppletLoadSettings() {
        return this.a() && !this.b;
    }
    
    public boolean canAppletSaveShot() {
        return this.b() && this.a.a.d && !this.a;
    }
    
    public boolean saveAnimGif(final byte[] array) {
        return this.canAppletSaveAnimGif() && this.broadcastByteArray(this.a.a.getParameter("SAVEDATAURL"), array, "gif", "nescafe/movie") == 0;
    }
    
    public boolean saveSettings(final byte[] array) {
        return this.canAppletSaveSettings() && this.broadcastByteArray(this.a.a.getParameter("SAVEDATAURL"), array, "txt", "nescafe/settings") == 0;
    }
    
    public boolean AppletSaveState(final byte[] array, final String s) {
        if (this.canAppletSaveState()) {
            final String parameter;
            String s2;
            if ((parameter = this.a.a.getParameter("SAVEDATAURL")).indexOf("?") < 0) {
                s2 = parameter + "?saveslot=" + s;
            }
            else {
                s2 = parameter + "&saveslot=" + s;
            }
            this.a.a("Saving State to " + s2);
            if (this.broadcastByteArray(s2, array, "nss", "nescafe/state") == 0) {
                this.a.b("State Successfully Saved...");
                return true;
            }
        }
        return false;
    }
    
    public boolean nescafeLoadGameGenieCode(final String s, final int n) {
        if (this.a == null || this.a.a == null || this.a.a.a == null) {
            return false;
        }
        if (s == null || s.equals("")) {
            this.a.a.b = this.a.a.a.a(n);
            this.a.b("Game Genie Code cleared from slot " + n + "!");
            return true;
        }
        final boolean b = this.a.a.a.a(s, n) == 1;
        this.a.a.b = b;
        this.a.b("Game Genie Code activated in slot " + n + "!");
        return b;
    }
    
    public boolean saveScreenShot(final byte[] array) {
        if (!this.canAppletSaveShot()) {
            this.a.a("Applet not allowed to Save Data");
            return false;
        }
        final String s = "nescafe/";
        String s2;
        if (this.a.a.m) {
            s2 = s + "timetrial";
        }
        else {
            s2 = s + "screenshot";
        }
        final boolean b = this.broadcastByteArray(this.a.a.getParameter("SAVEDATAURL"), array, "gif", s2) == 0;
        this.a.a("Save Shot Result : " + b);
        if (this.a.a.m) {
            if (b) {
                final an an;
                (an = new an(11, this.a)).c = 14;
                an.a("Your screenshot has been sent to the website", "as proof of your progress in the game.");
                this.a.a(an);
            }
            return true;
        }
        return b;
    }
    
    public boolean AppletSaveSaveRAM(final byte[] array) {
        return this.canAppletSaveRAM() && this.broadcastByteArray(this.a.a.getParameter("SAVEDATAURL"), array, "sav", "nescafe/saveram") == 0;
    }
    
    public DataInputStream AppletLoadSaveState(final String s) {
        this.a.a("Loading from Slot " + s);
        if (!s.equals("00") && !this.canAppletLoadState()) {
            this.a.a("NESCafe is prevented from Load State files");
            return null;
        }
        boolean b = false;
        String s2 = this.loadStateOnStartup;
        this.loadStateOnStartupTrigger = false;
        final boolean b2 = s2 != null && s2.toUpperCase().endsWith(".ZIP");
        this.a.a("Loading State from Tag: " + s2);
        if (s.equals("00") && s2 != null) {
            b = true;
        }
        else if ((s2 = this.getParameter("LOADDATAURL")) == null) {
            this.a.a("LOADDATAURL is null");
            return null;
        }
        if (!s2.toUpperCase().startsWith("HTTP://") && !s2.toUpperCase().startsWith("FILE:") && !s2.toUpperCase().startsWith("HTTPS://")) {
            if (s2.startsWith("/")) {
                s2 = "http://" + this.getCodeBase().getHost() + s2;
            }
            else {
                s2 = this.getCodeBase() + (this.getCodeBase().toString().endsWith("/") ? "" : "/") + s2;
            }
        }
        if (!b) {
            if (s2.indexOf("?") < 0) {
                s2 = s2 + "?saveslot=" + s;
            }
            else {
                s2 = s2 + "&saveslot=" + s;
            }
        }
        this.a.a("About to Load State File from from URL " + s2);
        try {
            final URL url;
            final URLConnection openConnection = (url = new URL(s2)).openConnection();
            if (!b) {
                try {
                    final String encode = URLEncoder.encode(aK.a(this.a.a));
                    this.a.a("Sending Game  " + encode);
                    openConnection.setDoOutput(true);
                    openConnection.setUseCaches(false);
                    final PrintStream printStream;
                    (printStream = new PrintStream(openConnection.getOutputStream())).print("gamename=" + encode + "&contenttype=nescafe/state&ext=nss&");
                    printStream.print("crc=" + this.a.a.a.a + "&");
                    if (this.a != null) {
                        printStream.print("site=" + this.a + "&");
                        this.a.a("Sending Site=" + this.a);
                    }
                    this.a.a("Load Save State");
                    printStream.flush();
                    printStream.close();
                }
                catch (Exception ex) {
                    this.a.a("Error Occurred: " + ex.getMessage());
                }
            }
            try {
                if (!b2) {
                    return new DataInputStream(openConnection.getInputStream());
                }
                ZipInputStream zipInputStream;
                ZipEntry zipEntry;
                for (zipEntry = (zipInputStream = new ZipInputStream(openConnection.getInputStream())).getNextEntry(); zipEntry != null && (zipEntry.isDirectory() || !zipEntry.getName().toUpperCase().endsWith("NSS")); zipEntry = zipInputStream.getNextEntry()) {}
                if (zipEntry == null) {
                    zipInputStream.close();
                    return null;
                }
                return new DataInputStream(zipInputStream);
            }
            catch (FileNotFoundException ex3) {
                this.a.a("File not Found: " + s2);
                return null;
            }
            catch (IOException ex4) {
                return new DataInputStream(url.openConnection().getInputStream());
            }
        }
        catch (Exception ex2) {
            this.a.a("Error Occurred: " + ex2.getMessage());
            return null;
        }
    }
    
    public DataInputStream AppletLoadSaveRAM() {
        if (!this.a.a.a.d) {
            this.a.a("SaveRAM attempted to Load, but game doesn't support SaveRAM");
            return null;
        }
        if (!this.canAppletLoadRAM()) {
            this.a.a("SaveRAM attempted to Load, LOADDATAURL not defined");
            return null;
        }
        try {
            String s;
            if (!(s = this.a.a.getParameter("LOADDATAURL")).toUpperCase().startsWith("HTTP://") && !s.toUpperCase().startsWith("FILE:") && !s.toUpperCase().startsWith("HTTPS://")) {
                if (s.startsWith("/")) {
                    s = "http://" + this.getCodeBase().getHost() + s;
                }
                else {
                    s = this.getCodeBase() + (this.getCodeBase().toString().endsWith("/") ? "" : "/") + s;
                }
            }
            final URL url = new URL(s);
            this.a.a("Loading SaveRAM from " + s);
            final URLConnection openConnection = url.openConnection();
            try {
                final String encode = URLEncoder.encode(aK.a(this.a.a));
                openConnection.setDoOutput(true);
                openConnection.setUseCaches(false);
                final PrintStream printStream;
                (printStream = new PrintStream(openConnection.getOutputStream())).print("gamename=" + encode + "&contenttype=nescafe/saveram&ext=sav&");
                printStream.print("crc=" + this.a.a.a.a + "&");
                if (this.a != null) {
                    printStream.print("site=" + this.a + "&");
                    this.a.a("Sending Site=" + this.a);
                }
                this.a.a("Load Save RAM");
                printStream.flush();
                printStream.close();
                this.a.a("Sending Game Name : " + encode);
            }
            catch (Exception ex) {}
            return new DataInputStream(openConnection.getInputStream());
        }
        catch (Exception ex2) {
            return null;
        }
    }
    
    public DataInputStream AppletLoadSaveSettings() {
        if (!this.canAppletLoadSettings()) {
            return null;
        }
        try {
            String s;
            if (!(s = this.a.a.getParameter("LOADDATAURL")).toUpperCase().startsWith("HTTP://") && !s.toUpperCase().startsWith("FILE:") && !s.toUpperCase().startsWith("HTTPS://")) {
                if (s.startsWith("/")) {
                    s = "http://" + this.getCodeBase().getHost() + s;
                }
                else {
                    s = this.getCodeBase() + (this.getCodeBase().toString().endsWith("/") ? "" : "/") + s;
                }
            }
            final URL url = new URL(s);
            this.a.a("Loading Settings from " + s);
            final URLConnection openConnection = url.openConnection();
            try {
                openConnection.setDoOutput(true);
                openConnection.setUseCaches(false);
                final PrintStream printStream = new PrintStream(openConnection.getOutputStream());
                if (this.a != null) {
                    printStream.print("site=" + this.a + "&");
                    this.a.a("Sending Site=" + this.a);
                }
                this.a.a("Load Settings");
                printStream.flush();
                printStream.close();
            }
            catch (Exception ex) {}
            return new DataInputStream(openConnection.getInputStream());
        }
        catch (Exception ex2) {
            return null;
        }
    }
    
    public NESCafeApplet() {
        this.a = null;
        this.forceDebugMode = false;
        this.loadStateOnStartup = null;
        this.loadStateOnStartupTrigger = false;
        this.b = "";
        this.nescafeImg = null;
        this.username = "";
        this.a = 0;
        this.b = 0;
        this.settingsfile = "";
        this.a = new Font("Helvetica", 0, 10);
        this.b = new Font("Helvetica", 1, 12);
        this.c = new Font("Helvetica", 1, 10);
        this.lastBroadcastError = "";
        this.a = false;
        this.b = false;
        this.hasFocus = false;
        this.c = false;
        this.setBackground(Color.black);
    }
    
    public void init() {
    }
    
    public void start() {
        System.out.println("Starting NESCafe v1.02");
        (this.a = new Thread(this)).start();
    }
    
    public void stop() {
        if (this.a != null) {
            this.a.a();
        }
        if (this.a != null) {
            this.a.g();
        }
        if (this.a != null && this.a.a != null && this.a.a.a != null) {
            this.a.a.a.g();
        }
    }
    
    public void destroy() {
        if (this.a.a != null) {
            this.a.a.a();
        }
    }
    
    public final Image loadImage(final String s) throws Exception {
        final Image loadImageFromJar = this.loadImageFromJar(s);
        final MediaTracker mediaTracker;
        (mediaTracker = new MediaTracker(this)).addImage(loadImageFromJar, 0);
        mediaTracker.waitForID(0);
        if (mediaTracker.isErrorID(0)) {
            return null;
        }
        return loadImageFromJar;
    }
    
    public Image loadImageFromJar(final String s) {
        final Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        try {
            final aj aj = new aj(this.getClass().getResourceAsStream(s));
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final byte[] array = new byte[2048];
            int read;
            while ((read = aj.read(array, 0, 2048)) > -1) {
                byteArrayOutputStream.write(array, 0, read);
            }
            byteArrayOutputStream.close();
            final byte[] byteArray = byteArrayOutputStream.toByteArray();
            aj.close();
            return defaultToolkit.createImage(byteArray);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public final int getDispHeight() {
        try {
            if (this.a == 0) {
                this.a = this.getHeight();
            }
            return this.a;
        }
        catch (NoSuchMethodError noSuchMethodError) {
            return this.a = this.getSize().height;
        }
    }
    
    public final int getDispWidth() {
        try {
            if (this.b == 0) {
                this.b = this.getWidth();
            }
            return this.b;
        }
        catch (NoSuchMethodError noSuchMethodError) {
            return this.b = this.getSize().width;
        }
    }
    
    public void resize(final int b, final int a) {
        this.b = b;
        this.a = a;
        if (this.a != null && this.a.a != null) {
            this.a.a.c();
        }
    }
    
    public void paint(final Graphics graphics) {
        final int dispHeight = this.getDispHeight();
        final int dispWidth;
        final int n = (dispWidth = this.getDispWidth()) / 2 - 1 * aP.d / 2;
        final int n2 = dispHeight / 2 - 1 * aP.e / 2;
        if (this.a != null && this.a.a != null) {
            this.a.a.a(graphics);
        }
        if (!aK.a()) {
            final int n3 = dispHeight / 2 - 30;
            graphics.setColor(Color.black);
            graphics.fillRect(0, 0, dispWidth, dispHeight);
            graphics.setColor(Color.white);
            graphics.setFont(this.b);
            graphics.drawString("NESCafe v1.02", 20, n3);
            graphics.setColor(new Color(192, 192, 192));
            graphics.setFont(this.a);
            graphics.drawString("Please upgrade your Java Virtual Machine", 20, n3 + 15);
            graphics.drawString("in order to run the NESCafe emulator. You", 20, n3 + 30);
            graphics.drawString("can download one from the address below.", 20, n3 + 45);
            graphics.setFont(this.c);
            graphics.setColor(new Color(192, 192, 255));
            graphics.drawString("http://java.sun.com", 20, n3 + 80);
            return;
        }
        if (this.a == null) {
            graphics.setColor(Color.black);
            graphics.fillRect(0, 0, dispWidth, dispHeight);
            if (this.nescafeImg != null) {
                graphics.drawImage(this.nescafeImg, n, n2, Color.black, null);
            }
            return;
        }
        if (this.a != null && !this.a.a() && this.b != null && !this.b.equals("")) {
            this.a.a.a(true);
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public int broadcastByteArray(String s, final byte[] array, final String s2, final String s3) {
        this.lastBroadcastError = "";
        this.a.a("Called BroadcastByteArray for " + s);
        byte[] a;
        if (!"nescafe/state".equals(s3) && !"nescafe/saveram".equals(s3)) {
            a = aK.a(array);
        }
        else {
            a = array;
        }
        final boolean b = a.length > 2048;
        if (!s.toUpperCase().startsWith("HTTP://") && !s.toUpperCase().startsWith("HTTPS://")) {
            if (s.startsWith("/")) {
                s = "http://" + this.getCodeBase().getHost() + s;
            }
            else {
                s = this.getCodeBase() + (this.getCodeBase().toString().endsWith("/") ? "" : "/") + s;
            }
        }
        this.a.a("Broadcasting " + a.length + " bytes to " + s);
        while (true) {
            if (b) {
                final an an;
                (an = new an(21, this.a)).a("[Establishing Connection]");
                this.a.a.a(an);
                this.a.a.a.a(true);
                try {
                    final URLConnection openConnection;
                    (openConnection = new URL(s).openConnection()).setDoOutput(true);
                    openConnection.setUseCaches(false);
                    final PrintStream printStream = new PrintStream(openConnection.getOutputStream());
                    final String encode = URLEncoder.encode(aK.a(this.a.a));
                    printStream.print("ext=" + s2 + "&");
                    printStream.print("crc=" + this.a.a.a.a + "&");
                    printStream.print("contenttype=" + s3 + "&");
                    printStream.print("datalength=" + a.length * 2 + "&");
                    printStream.print("gamename=" + encode + "&");
                    printStream.print("nescafe=NESCAFE1.02&");
                    if (this.a != null) {
                        printStream.print("site=" + this.a + "&");
                        this.a.a("Sending Site=" + this.a);
                    }
                    this.a.a("Broadcast Byte Array");
                    if (this.a.a.m) {
                        printStream.print("time=" + (int)(this.a.a.k / 60.0) + "&");
                    }
                    int n = 0;
                    final String[] array2 = new String[a.length / 512 + 1];
                    if (b) {
                        if (this.a.a != null && this.a.a() == 21) {
                            this.a.a.a("[Preparing and Compressing Data]");
                        }
                        this.a.a.a.a(true);
                    }
                    for (int i = 0; i < array2.length; ++i) {
                        array2[i] = "";
                    }
                    for (int j = 0; j < a.length; ++j) {
                        if (b && j % 8192 == 0 && this.a.a != null && this.a.a() == 21) {
                            final int a2 = 50 * j / a.length;
                            this.a.a.a("[Preparing Data]");
                            this.a.a.a = a2;
                            this.a.a.a.a(true);
                        }
                        final StringBuffer sb = new StringBuffer();
                        final String[] array3 = array2;
                        final int n2 = n;
                        array3[n2] = sb.append(array3[n2]).append(aK.a(a[j] & 0xFF, 2)).toString();
                        if (array2[n].length() >= 1024) {
                            ++n;
                        }
                    }
                    if (b && this.a.a != null && this.a.a() == 21) {
                        this.a.a.a("[Sending Data]");
                    }
                    for (int k = 0; k < n + 1; ++k) {
                        printStream.print("data" + k + "=" + array2[k] + "&");
                        this.a.a("sending data " + k + " = " + array2[k]);
                        printStream.flush();
                        if (b && k % 8 == 0 && this.a.a != null && this.a.a() == 21) {
                            final int a3 = 50 + 50 * k / (n + 1);
                            this.a.a.a("[Sending Data]");
                            this.a.a.a = a3;
                            this.a.a.a.a(true);
                        }
                    }
                    printStream.close();
                    if (b && this.a.a != null && this.a.a() == 21) {
                        this.a.a.a("[Waiting for Response from Server]");
                        this.a.a.a = 100;
                    }
                    this.a.a.a.a(true);
                    final DataInputStream dataInputStream = new DataInputStream(openConnection.getInputStream());
                    if (b && this.a.a != null && this.a.a() == 21) {
                        this.a.a.a("");
                    }
                    String s4 = dataInputStream.readLine();
                    this.a.a("<: " + s4);
                    if (s4 == null) {
                        if (this.a.a != null && this.a.a() == 21) {
                            this.a.b();
                        }
                        dataInputStream.close();
                        this.lastBroadcastError = "Server did not response to request";
                        this.a.a("Server could not be connected - failure");
                        return 1;
                    }
                    if (s4.toUpperCase().startsWith("SUCCESS")) {
                        for (String s5 = dataInputStream.readLine(); s5 != null; s5 = dataInputStream.readLine()) {
                            this.a.a("<: " + s5);
                            if (s5.toUpperCase().startsWith("URL:")) {
                                String a4 = s5.substring(4).trim();
                                try {
                                    if (!a4.toUpperCase().startsWith("HTTP://") && !a4.toUpperCase().startsWith("FILE:") && !a4.toUpperCase().startsWith("HTTPS://")) {
                                        if (a4.startsWith("/")) {
                                            a4 = "http://" + this.getCodeBase().getHost() + a4;
                                        }
                                        else {
                                            a4 = this.getCodeBase() + (this.getCodeBase().toString().endsWith("/") ? "" : "/") + a4;
                                        }
                                    }
                                    this.a.a.a = a4;
                                }
                                catch (Exception ex2) {}
                            }
                            else {
                                this.a.a("Ignoring Unknown Command: " + s5);
                            }
                        }
                        if (this.a.a() == 21) {
                            this.a.b();
                        }
                        dataInputStream.close();
                        this.a.a("Server responded successfully");
                        return 0;
                    }
                    if (s4.toUpperCase().startsWith("ERROR: ") && s3.equals("nescafe/test")) {
                        this.lastBroadcastError = s4.substring(7);
                        return 2;
                    }
                    if (s4 != null && s4.indexOf("<") >= 0 && s4.indexOf(">") > 0) {
                        this.lastBroadcastError = "Server responded with HTML content";
                        this.a.a("Error: " + this.lastBroadcastError);
                    }
                    else if (s4 != null && s4.toUpperCase().startsWith("ERROR: ")) {
                        this.lastBroadcastError = s4.substring(7);
                        this.a.a("Error: " + this.lastBroadcastError);
                    }
                    while (s4 != null) {
                        this.a.a("Server responded unexpectedly: " + s4);
                        s4 = dataInputStream.readLine();
                    }
                    dataInputStream.close();
                    if (this.a.a() == 21) {
                        this.a.b();
                    }
                    return 3;
                }
                catch (UnknownServiceException ex3) {
                    this.lastBroadcastError = "The website could not be reached";
                    return 4;
                }
                catch (Exception ex) {
                    this.lastBroadcastError = ex.getMessage();
                    this.a.a("Error Occurred: " + ex.toString());
                }
                finally {
                    if (this.a.a() == 21) {
                        this.a.b();
                    }
                }
                return 1;
            }
            continue;
        }
    }
    
    public void run() {
        this.repaint();
        this.addFocusListener(this);
        if (!aK.a()) {
            return;
        }
        this.settingsfile = this.getParameter("SETTINGSFILE");
        if (this.settingsfile == null || this.settingsfile.trim().equals("")) {
            this.settingsfile = "nescafe-settings.txt";
        }
        this.a = new aE();
        this.a = new au();
        this.a.a = this;
        this.a.a(this.a);
        try {
            this.nescafeImg = this.loadImage("NESCafe.ddnb");
        }
        catch (Exception ex) {
            this.nescafeImg = null;
            this.a.a("Failed to load NESCafe.ddnb");
        }
        this.a.a(this.a);
        this.a.f();
        final R r = new R();
        while (!this.a.a.f) {
            this.repaint();
            try {
                r.a(10);
                r.a();
            }
            catch (Exception ex2) {}
        }
        if (this.getCodeBase().toString().indexOf("!") > 0) {
            this.b = "The URL for the JAR has an illegal character";
            this.a.a(this.b);
            this.hasFocus = true;
            final an an = new an(11, this.a);
            this.a.a(true);
            an.c = 5;
            an.a(this.b);
            this.a.a(an);
            this.repaint();
            this.a.e();
            return;
        }
        if (aK.b(this.getCodeBase().getHost())) {
            this.hasFocus = true;
            final an an2 = new an(11, this.a);
            this.a.a(true);
            an2.c = 5;
            an2.a("The Applet version of NESCafe will only run on ", "registered websites belonging to sponsors ", "of NESCafe. Please use the Hosted version instead,", "which is available for free at www.nescafeweb.com");
            this.a.a(an2);
            this.repaint();
            this.a.e();
            return;
        }
        this.loadStateOnStartup = this.getParameter("LOADSTATEONSTARTUP");
        if (this.loadStateOnStartup != null && !this.loadStateOnStartup.trim().equals("")) {
            this.a.a("Attempting to load State at Startup due to LOADSTATEONSTARTUP being set");
            this.a.a("LOADSTATE=" + this.loadStateOnStartup);
            this.a.a = true;
            this.a.b = false;
            this.loadStateOnStartupTrigger = true;
        }
        else {
            this.loadStateOnStartup = null;
            this.loadStateOnStartupTrigger = false;
        }
        this.a = this.getParameter("REFERER");
        if (this.a != null && this.a.equals("")) {
            this.a = null;
        }
        String parameter;
        if ((parameter = this.getParameter("ROMFILE")) == null || parameter.equals("")) {
            parameter = "";
            this.loadStateOnStartup = null;
            this.loadStateOnStartupTrigger = false;
        }
        if (parameter == null || parameter.equals("")) {
            this.a.a("Loading Default NESCafe ROM");
        }
        else {
            this.a.a("Loading ROM File '" + parameter + "'");
        }
        this.a.a.a(!this.hasFocus);
        if (!this.a.a(parameter)) {
            final an an3 = new an(11, this.a);
            this.a.a(true);
            an3.c = 1;
            an3.a("An error occurred whilst loading the NES game.", this.b);
            this.a.a(an3);
            this.a.a("Error Occurred when Loading ROM");
            this.a.a(this.b);
            this.repaint();
            this.a.e();
            return;
        }
        final int broadcastByteArray;
        if (this.getParameter("LOADDATAURL") != null && !this.getParameter("LOADDATAURL").equals("") && (broadcastByteArray = this.broadcastByteArray(this.getParameter("LOADDATAURL"), null, ".", "nescafe/test")) != 0) {
            final an an4 = new an(12, this.a);
            this.a.a(true);
            an4.c = 12;
            switch (broadcastByteArray) {
                case 2: {
                    an4.a("The LOADDATAURL parameter has been set, but", "the server-side script it points caused an error.", ((this.lastBroadcastError != "") ? "Error: " : "") + this.lastBroadcastError);
                    break;
                }
                case 3: {
                    an4.a("This Applet has the LOADDATAURL parameter set but", "the script that it is pointing to on your webserver", "is producing unexpected HTML output. Please check.");
                    break;
                }
                case 4: {
                    an4.a("This Applet has the LOADDATAURL parameter set ", "but this functionality is not supported when running", "Applets directly, rather than through a web-browser.");
                    break;
                }
                default: {
                    an4.a("This Applet has the LOADDATAURL parameter set", "but the script that it points to is not responding.", "Please check the parameter before proceeding.");
                    break;
                }
            }
            this.a.a(an4);
            this.repaint();
            this.b = true;
        }
        final int broadcastByteArray2;
        if (this.getParameter("SAVEDATAURL") != null && !this.getParameter("SAVEDATAURL").equals("") && (broadcastByteArray2 = this.broadcastByteArray(this.getParameter("SAVEDATAURL"), null, ".", "nescafe/test")) != 0) {
            final an an5 = new an(12, this.a);
            this.a.a(true);
            an5.c = 12;
            switch (broadcastByteArray2) {
                case 2: {
                    an5.a("The SAVEDATAURL parameter has been set, but", "the server-side script it points caused an error.", ((this.lastBroadcastError != "") ? "Error: " : "") + this.lastBroadcastError);
                    break;
                }
                case 3: {
                    an5.a("This Applet has the SAVEDATAURL parameter set but", "the script that it is pointing to on your webserver", "is producing unexpected HTML output. Please check.");
                    break;
                }
                case 4: {
                    an5.a("This Applet has the SAVEDATAURL parameter set ", "but this functionality is not supported when running", "Applets directly, rather than through a web-browser.");
                    break;
                }
                default: {
                    an5.a("This Applet has the SAVEDATAURL parameter set", "but the script that it points to is not responding.", "Please check the parameter before proceeding.");
                    break;
                }
            }
            this.a.a(an5);
            this.repaint();
            this.a = true;
        }
        if ("TRUE".equalsIgnoreCase(this.getParameter("TIMETRIAL"))) {
            final an an6 = new an(12, this.a);
            this.a.a(true);
            an6.c = 12;
            if (this.a) {
                an6.a("Time Trial mode has been set, however the", "SAVEDATAURL has been set incorrectly, which", "will prevent you saving your Time Trial.");
            }
            else if (!this.b() || !this.a.a.c) {
                an6.a("Time Trial mode has been set, but unfortunately", "the SAVEDATAURL has not been set, which will", "prevent you saving your Time Trial to the server.");
            }
            else {
                this.a.a.m = true;
                an6.c = 17;
                an6.a("Time Trial mode has been enabled, press ENTER", "to start playing, then when you wish to save", "your Time Trial back to the server press T.");
            }
            this.a.a(an6);
            this.repaint();
        }
        this.username = this.getParameter("USERNAME");
        final boolean equalsIgnoreCase = "TRUE".equalsIgnoreCase(this.getParameter("HOSTED"));
        if (this.username == null || this.username.trim().equals("")) {
            if (aK.a(this.getCodeBase().getHost()) && !equalsIgnoreCase) {
                final an an7;
                (an7 = new an(12, this.a)).a("Welcome to NESCafe Online! If you register with", "with this site then you will be able to save ", "your progress online whilst playing games.");
                this.a.a(an7);
            }
            this.username = "";
        }
        else {
            this.username = this.username.trim();
            this.a.a("Username = " + this.username);
        }
        this.a.a.g = "TRUE".equalsIgnoreCase(this.getParameter("LIGHTGUN"));
        if (this.a.a.g) {
            this.setCursor(new Cursor(13));
        }
        final String parameter2;
        if ((parameter2 = this.getParameter("SOUND")) != null && parameter2.equalsIgnoreCase("FALSE")) {
            this.a.a.g = false;
        }
        else {
            this.a.a.g = true;
        }
        final String parameter3;
        if ((parameter3 = this.getParameter("GAMEGENIE")) != null && !parameter3.equals("")) {
            this.a.a("Game Genie codes found");
            final String[] a = aK.a(parameter3, ',');
            for (int n = 0, n2 = 0; n2 < a.length && n < 4; ++n2) {
                if (this.a.a.a.a(a[n2], n) > 0) {
                    this.a.a.b = true;
                    ++n;
                }
                else {
                    this.a.a("Game Genie Code rejected: " + a[n2]);
                }
            }
            if (this.a.a.b) {
                this.a.a("Game Genie Codes Active");
                this.a.b("Game Genie Codes Active");
            }
        }
        if (this.a.a.l) {
            this.a.a("DOWNLOADROMPROMPT is set so show menu");
            final an an8 = new an(14, this.a);
            this.a.a(true);
            this.a.a(an8);
        }
        this.a.d();
        this.repaint();
    }
    
    public void focusGained(final FocusEvent focusEvent) {
        this.hasFocus = true;
        if (this.a != null) {
            if (this.a.a != null && this.a.a.s) {
                this.a.a("You are using reserved key '" + this.a.a.f + "' for your controls", true);
            }
            this.a.a.a(this.c);
        }
    }
    
    public void focusLost(final FocusEvent focusEvent) {
        this.hasFocus = false;
        if (this.a != null && this.a.a != null) {
            this.c = this.a.a.b;
            this.a.a.a(true);
        }
    }
}
