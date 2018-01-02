import com.dreamfabric.jac64.DirEntry;
import java.util.ArrayList;
import java.io.InputStream;
import java.net.URLConnection;
import java.io.IOException;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import com.dreamfabric.jac64.VICConstants;
import java.util.StringTokenizer;
import java.io.Reader;
import java.io.LineNumberReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.applet.AudioClip;
import java.awt.event.KeyListener;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import com.dreamfabric.jac64.C64Chips;
import com.dreamfabric.jac64.Loader;
import com.dreamfabric.jac64.SELoader;
import com.dreamfabric.jac64.SIDMixer;
import com.dreamfabric.jac64.DefaultIMon;
import java.awt.Color;
import com.dreamfabric.jac64.IMonitor;
import java.util.Vector;
import com.dreamfabric.jac64.C64Canvas;
import com.dreamfabric.jac64.C64Screen;
import com.dreamfabric.jac64.C64Reader;
import com.dreamfabric.jac64.CPU;
import com.dreamfabric.jac64.PatchListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class C64Applet extends Applet implements Runnable, PatchListener
{
    private CPU cpu;
    private int[] memory;
    private boolean started;
    private boolean stopping;
    private C64Reader reader;
    private String currentDisk;
    private String loadFile;
    private boolean stick;
    private C64Screen screen;
    private C64Canvas canvas;
    private Vector files;
    private boolean require1541;
    private IMonitor imon;
    private static Color lblue;
    private Thread thread;
    private String autostartDisk;
    private String autostartProgram;
    private String autoText;
    private int autostartID;
    private int defaultStick;
    private int soundOn;
    private int doubleScreen;
    
    public C64Applet() {
        this.started = false;
        this.stopping = false;
        this.stick = true;
        this.require1541 = false;
        this.imon = new DefaultIMon();
        this.autostartID = -1;
        this.defaultStick = 0;
        this.soundOn = 0;
        this.doubleScreen = 0;
    }
    
    public void init() {
        this.started = false;
        this.stopping = false;
        this.currentDisk = null;
        if (this.cpu == null) {
            SIDMixer.DL_BUFFER_SIZE = 16384;
            System.out.println("starting CPU");
            this.cpu = new CPU(this.imon, this.getCodeBase().toString(), new SELoader());
            System.out.println("Status: initializing");
            this.doubleScreen = this.getParameterAsInt("doubleScreen", 0);
            this.screen = new C64Screen(this.imon, this.doubleScreen > 0);
            this.cpu.init(this.screen);
            this.screen.init(this.cpu);
            this.memory = this.cpu.getMemory();
            this.setLayout(new BorderLayout());
            this.setBackground(Color.black);
            this.setForeground(C64Applet.lblue);
            (this.reader = new C64Reader()).setCPU(this.cpu);
            this.add(this.canvas = (C64Canvas)this.screen.getScreen(), "Center");
            this.repaint();
            this.validate();
            this.addKeyListener(this.canvas);
            this.canvas.requestFocus();
            this.cpu.getDrive().setReader(this.reader);
            AudioClip audioClip = null;
            AudioClip audioClip2 = null;
            final URL resource = this.getClass().getResource("sounds/track.wav");
            System.out.println("Audio URL:" + resource);
            if (resource != null) {
                audioClip = Applet.newAudioClip(resource);
            }
            final URL resource2 = this.getClass().getResource("sounds/motor.wav");
            if (resource2 != null) {
                audioClip2 = Applet.newAudioClip(resource2);
            }
            this.screen.setSounds(audioClip, audioClip2);
        }
    }
    
    public void start() {
        System.out.println("Starting applet...");
        if (this.thread == null) {
            (this.thread = new Thread(this)).start();
        }
        this.setColorSet(this.getParameterAsInt("colorset", 0));
        this.require1541 = (this.getParameterAsInt("require1541", 0) == 1);
        for (int i = 0; i < 12; ++i) {
            final String parameter = this.getParameter("hotkey-f" + (i + 1));
            if (parameter != null && parameter.length() > 0) {
                this.screen.registerHotKey(i, parameter, this);
            }
        }
        new Thread(new Runnable() {
            public void run() {
                C64Applet.this.autoText = C64Applet.this.getParameter("autostartCode");
                C64Applet.this.autostartDisk = C64Applet.this.getParameter("autostartDisk");
                if (C64Applet.this.autostartDisk != null) {
                    C64Applet.this.autostartProgram = C64Applet.this.getParameter("autostartPGM");
                    if (C64Applet.this.autostartProgram == null) {
                        C64Applet.this.autostartProgram = C64Applet.this.getParameter("autostartProgram");
                    }
                }
                else {
                    C64Applet.this.autostartProgram = C64Applet.this.getParameter("autostartPGM");
                    C64Applet.this.autostartID = C64Applet.this.getParameterAsInt("autostartProgram", -1);
                }
                C64Applet.this.defaultStick = C64Applet.this.getParameterAsInt("joystick", 0);
                C64Applet.this.soundOn = C64Applet.this.getParameterAsInt("soundOn", 1);
                if (C64Applet.this.getParameterAsInt("extendedKeyboard", 0) != 0) {
                    C64Applet.this.screen.setKeyboardEmulation(true);
                    System.out.println("Extended keyboard emulation on!");
                }
                C64Applet.this.loadGamesList();
                C64Applet.this.screen.setSoundOn(C64Applet.this.soundOn == 1);
                C64Applet.this.screen.setStick(C64Applet.this.defaultStick == 0);
                if (C64Applet.this.autostartDisk != null) {
                    if (C64Applet.this.autostartProgram != null) {
                        C64Applet.this.loadGame(C64Applet.this.autostartDisk, C64Applet.this.autostartProgram);
                    }
                    else {
                        C64Applet.this.insertDisk(C64Applet.this.autostartDisk);
                        C64Applet.this.resetAndWait();
                        C64Applet.this.enterText(C64Applet.this.autoText);
                    }
                }
                else if (C64Applet.this.autostartProgram != null) {
                    if (C64Applet.this.autostartProgram.equals("random")) {
                        C64Applet.this.loadGame((int)(Math.random() * (C64Applet.this.files.size() / 2)));
                    }
                    else {
                        C64Applet.this.loadPGM(C64Applet.this.autostartProgram);
                    }
                }
                if (C64Applet.this.autostartID != -1) {
                    System.out.println("AutostartID: " + C64Applet.this.autostartID);
                    C64Applet.this.loadGame(C64Applet.this.autostartID);
                }
            }
        }).start();
    }
    
    private int getParameterAsInt(final String s, final int n) {
        final String parameter = this.getParameter(s);
        System.out.println(s + " = " + parameter);
        if (parameter != null) {
            try {
                return Integer.parseInt(parameter);
            }
            catch (Exception ex) {
                System.out.println("Can not parse value: " + parameter);
            }
        }
        return n;
    }
    
    private void loadGamesList() {
        System.out.println("Trying to load games list");
        try {
            final LineNumberReader lineNumberReader = new LineNumberReader(new InputStreamReader(this.getResource("games.txt").openConnection().getInputStream()));
            this.files = new Vector();
            String line;
            while ((line = lineNumberReader.readLine()) != null) {
                final String trim = line.trim();
                if (trim.toLowerCase().endsWith(".prg") || trim.toLowerCase().endsWith(".p00")) {
                    this.files.addElement(trim);
                    this.files.addElement(trim);
                }
                else {
                    final String line2 = lineNumberReader.readLine();
                    if (line2 == null) {
                        continue;
                    }
                    final StringTokenizer stringTokenizer = new StringTokenizer(line2.trim(), ",");
                    while (stringTokenizer.hasMoreElements()) {
                        final String nextToken = stringTokenizer.nextToken();
                        this.files.addElement(trim);
                        this.files.addElement(nextToken);
                        System.out.println("Adding: " + nextToken);
                    }
                }
            }
        }
        catch (Exception ex) {
            System.out.println("Can not load games..." + ex);
            ex.printStackTrace();
            System.out.println("No games to load...");
        }
    }
    
    public void setColorSet(int colorSet) {
        colorSet %= VICConstants.COLOR_SETS.length;
        this.screen.setColorSet(colorSet);
    }
    
    public void run() {
        if (this.started && !this.stopping) {
            System.out.println("Status: running");
            this.cpu.start();
        }
        else {
            this.cpu.start();
        }
        this.stopping = false;
        this.thread = null;
    }
    
    public void stop() {
        System.out.println("Status: stopping");
        this.stopping = true;
        this.cpu.stop();
        this.screen.motorSound(false);
    }
    
    private void loadProgram(final int n) {
        final String s = this.files.elementAt(n * 2);
        final String s2 = this.files.elementAt(n * 2 + 1);
        System.out.println("Index:" + n + " -> " + s + " " + s2);
        this.loadProgram(s, s2);
    }
    
    private boolean loadProgram(String currentDisk, String string) {
        boolean require1541 = this.require1541;
        if (currentDisk.startsWith("@")) {
            currentDisk = currentDisk.substring(1);
            require1541 = true;
        }
        if (currentDisk != this.currentDisk) {
            final URL resource = this.getResource(currentDisk);
            this.currentDisk = currentDisk;
            currentDisk = currentDisk.toLowerCase();
            if (currentDisk.endsWith(".d64")) {
                if (!this.reader.readDiskFromURL(resource)) {
                    System.out.println("Status: problem while loading disk");
                }
            }
            else if (currentDisk.endsWith(".t64")) {
                if (!this.reader.readTapeFromURL(resource)) {
                    System.out.println("Status: problem while loading tape");
                }
            }
            else if (currentDisk.endsWith(".prg") || currentDisk.endsWith(".p00")) {
                if (!this.reader.readPGM(resource, -1)) {
                    System.out.println("Status: problem while loading pgm");
                }
                else {
                    System.out.println("Status: loaded " + currentDisk);
                }
                return false;
            }
        }
        if (require1541) {
            System.out.println("Loading with C1541 emulation...");
            this.enterText("load \"" + string + "\",8~");
            this.enterText("run~");
            return true;
        }
        for (int i = string.length(); i < 16; ++i) {
            string += " ";
        }
        if (this.reader.readFile(string) != null) {
            System.out.println("Status: loaded " + string);
        }
        else {
            System.out.println("Status: error while loading " + string);
        }
        return false;
    }
    
    public void loadPGM(final String s) {
        this.resetAndWait();
        if (!this.reader.readPGM(this.getResource(s), -1)) {
            System.out.println("Status: problem while loading pgm");
        }
        this.cpu.runBasic();
        this.canvas.requestFocus();
    }
    
    public void insertDisk(String substring) {
        if (substring.startsWith("@")) {
            substring = substring.substring(1);
        }
        final URL resource = this.getResource(substring);
        if (!this.reader.readDiskFromURL(resource)) {
            System.out.println("Status: problem while inserting disk: " + resource);
        }
    }
    
    public void enterText(final String s) {
        this.cpu.enterText(s);
    }
    
    public void loadPGM(final String s, final String s2) {
        this.loadGame(s, s2);
    }
    
    public void loadGame(final String s, final String s2) {
        this.resetAndWait();
        System.out.println("Loading " + s2 + " from " + s);
        if (!this.loadProgram(s, s2)) {
            this.cpu.runBasic();
        }
        this.canvas.requestFocus();
    }
    
    private void resetAndWait() {
        this.cpu.reset();
        while (!this.screen.ready()) {
            try {
                Thread.sleep(100L);
            }
            catch (Exception ex) {
                System.out.println("Exception while sleeping... C64Applet");
            }
        }
    }
    
    public void loadGame(final int n) {
        this.resetAndWait();
        this.loadProgram(n);
        this.cpu.runBasic();
        this.canvas.requestFocus();
    }
    
    public void setStick(final int n) {
        System.out.println("Setting stick: one ? " + (n == 0));
        this.screen.setStick(n == 0);
        this.canvas.requestFocus();
    }
    
    public void setSoundOn(final boolean soundOn) {
        this.screen.setSoundOn(soundOn);
    }
    
    public void setScanRate(final int n) {
        this.screen.setScanRate(n);
    }
    
    public void reset() {
        System.out.println("Reset - no kill");
        this.cpu.reset();
        this.canvas.requestFocus();
    }
    
    private URL getResource(final String s) {
        URL resource = this.getClass().getResource(s);
        if (resource == null) {
            try {
                resource = new URL(this.getCodeBase().toString() + s);
            }
            catch (Exception ex) {}
        }
        return resource;
    }
    
    public void setEffect(final int efx) {
        this.screen.getMixer().setEFX(efx);
    }
    
    public void saveFile(final String s, final String s2, String s3) {
        if (s3 == null) {
            s3 = "";
        }
        final String string = "name=" + URLEncoder.encode(s) + "&description=" + URLEncoder.encode(s3) + "&author=" + URLEncoder.encode(s2) + "&file=" + this.reader.saveFile();
        System.out.println("Saving file: " + string);
        try {
            final URLConnection openConnection = this.getResource("prgup.php").openConnection();
            openConnection.setDoOutput(true);
            openConnection.setUseCaches(false);
            final HttpURLConnection httpURLConnection = (HttpURLConnection)openConnection;
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            final DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            dataOutputStream.writeBytes(string);
            dataOutputStream.flush();
            dataOutputStream.close();
            final InputStream inputStream = httpURLConnection.getInputStream();
            System.out.println("Read back:");
            int read;
            while ((read = inputStream.read()) != -1) {
                System.out.print((char)read);
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public boolean readFile(String s, final int n) {
        s = s.trim();
        System.out.println("Should load: \"" + s + "\"");
        if ("$".equals(s)) {
            System.out.println("Entering basic data");
            final ArrayList dirNames = this.reader.getDirNames();
            int n2 = 2048;
            this.memory[n2++] = 0;
            for (int i = 0; i < dirNames.size(); ++i) {
                final DirEntry dirEntry = this.reader.getDirEntry(dirNames.get(i));
                final String name = dirEntry.name;
                final int n3 = n2 + 5 + name.length();
                System.out.println("Name:  " + name + " " + name.length());
                System.out.println("Next:  " + n3);
                System.out.println("Pos:  " + n2);
                this.memory[n2++] = (n3 & 0xFF);
                this.memory[n2++] = n3 >> 8;
                this.memory[n2++] = (dirEntry.size & 0xFF);
                this.memory[n2++] = dirEntry.size >> 8;
                for (int j = 0; j < name.length(); ++j) {
                    this.memory[n2++] = name.charAt(j);
                }
                this.memory[n2++] = 0;
            }
            return true;
        }
        for (int k = s.length(); k < 16; ++k) {
            s += " ";
        }
        return this.reader.readFile(s, n) != null;
    }
    
    static {
        C64Applet.lblue = new Color(VICConstants.COLOR_SETS[0][14]);
    }
}
