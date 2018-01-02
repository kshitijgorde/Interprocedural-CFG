import java.awt.Toolkit;
import java.applet.AppletContext;
import java.awt.Event;
import netscape.javascript.JSObject;
import java.awt.FontMetrics;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.awt.Font;
import java.util.StringTokenizer;
import java.lang.reflect.Field;
import java.awt.Rectangle;
import java.awt.Component;
import java.lang.reflect.Method;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.io.InputStream;
import java.awt.image.ImageObserver;
import java.net.URL;
import java.awt.MediaTracker;
import java.awt.Container;
import java.util.Vector;
import java.awt.Point;
import java.awt.Frame;
import java.awt.Color;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class BtnPlayer extends Applet implements Runnable
{
    int[] m_iBtnImages;
    Image[] m_iPreRendered;
    int[] m_iBtnInfo;
    Object m_oPlaying;
    Object[] m_oBtnAudio;
    Color[] m_cBtnColors;
    String[] m_sBtnStrings;
    String[] m_sFonts;
    int m_iReady;
    boolean m_fRegistered;
    boolean m_fLocal;
    int m_iPaintMode;
    boolean m_fMac;
    boolean m_fMicrosoft;
    boolean m_fExplorer4;
    boolean m_fNetscape4;
    boolean m_fNetscape6;
    boolean m_fOneSound;
    String m_sBitmapFolder;
    String m_sAudioFolder;
    String m_sStartAction;
    Frame m_Frame;
    Image m_iBuffer;
    Image m_iFlusher;
    boolean m_fFlushing;
    int m_iWidth;
    int m_iHeight;
    int m_iSelect;
    int m_iResizing;
    Point m_ptOffset;
    int m_iButtons;
    int m_iOver;
    int m_iBtnGrab;
    Color m_cBgnd;
    int m_iBgndMode;
    int m_iBgnd;
    Point m_ptBgndOffset;
    String m_sToken;
    int m_iToken;
    String m_sBgnd;
    Vector m_vBitmapNames;
    Vector m_vBitmapImages;
    Vector m_vStatics;
    Vector m_vAudioNames;
    Vector m_vAudioObjects;
    Vector m_vJSEvals;
    boolean fJDK11;
    boolean fJDK2;
    final int ISZ = 50;
    final int SSZ = 19;
    final int CSZ = 15;
    
    public BtnPlayer() {
        this.m_oPlaying = null;
        this.m_sFonts = this.getFontList();
        this.m_iReady = -1;
        this.m_fRegistered = true;
        this.m_fLocal = true;
        this.m_iPaintMode = 0;
        this.m_iSelect = -1;
        this.m_iResizing = -1;
        this.m_ptOffset = null;
        this.m_iOver = -1;
        this.m_iBtnGrab = -1;
        this.m_vBitmapNames = new Vector();
        this.m_vBitmapImages = new Vector();
        this.m_vStatics = new Vector();
        this.m_vAudioNames = new Vector();
        this.m_vAudioObjects = new Vector();
        this.m_vJSEvals = new Vector();
    }
    
    public void init() {
        try {
            this.m_fLocal = this.getCodeBase().getProtocol().equals("file");
            this.fJDK11 = false;
            this.fJDK2 = false;
            this.m_fMac = false;
            final String property = System.getProperty("java.version");
            final boolean equals = property.equals("1.0.2");
            this.fJDK11 = !property.startsWith("1.0");
            this.fJDK2 = (!property.startsWith("1.1") && !property.startsWith("1.0"));
            final boolean startsWith = System.getProperty("os.version").startsWith("3.1");
            final String lowerCase = System.getProperty("os.name").toLowerCase();
            final boolean b = lowerCase.indexOf("windows") != -1 && !startsWith;
            this.m_fMac = (lowerCase.indexOf("mac") != -1);
            this.m_fMicrosoft = (System.getProperty("java.vendor").toLowerCase().indexOf("microsoft") != -1);
            final boolean b2 = !this.m_fMicrosoft;
            this.m_fExplorer4 = ((this.m_fMicrosoft || (this.m_fMac && !b2)) && this.fJDK11);
            this.m_fNetscape4 = (b2 && this.fJDK11 && b);
            this.m_fNetscape6 = (b2 && this.fJDK2);
            this.m_iWidth = this.size().width;
            this.m_iHeight = this.size().height;
            if (this.m_iBuffer == null) {
                this.m_iBuffer = this.createImage(this.m_iWidth, this.m_iHeight);
            }
            if (this.m_iFlusher == null) {
                this.m_iFlusher = this.createImage(this.m_iWidth, this.m_iHeight);
            }
            for (Container container = this.getParent(); container != null; container = container.getParent()) {
                if (container instanceof Frame) {
                    this.m_Frame = (Frame)container;
                }
            }
            final String parameter = this.getParameter("p");
            this.m_sToken = parameter;
            this.m_iToken = 0;
            this.m_iButtons = this.getInt();
            this.getInt();
            this.getInt();
            this.setBackground(this.m_cBgnd = this.getColor(this.getToken()));
            this.show();
            this.paintAll();
            this.m_iBgndMode = this.getInt();
            this.m_sBgnd = this.getString();
            this.m_ptBgndOffset = new Point(this.getInt(), this.getInt());
            this.m_fOneSound = (this.getInt() == 1);
            this.m_sBitmapFolder = this.getString();
            this.m_sAudioFolder = this.getString();
            this.m_sStartAction = this.getString();
            int n;
            int calcRegCode = n = this.calcRegCode(parameter, 1114221141);
            this.m_iBtnInfo = new int[this.m_iButtons * 50];
            this.m_iBtnImages = new int[this.m_iButtons * 3];
            this.m_iPreRendered = new Image[this.m_iButtons * 6];
            this.m_oBtnAudio = new Object[this.m_iButtons * 9];
            this.m_sBtnStrings = new String[this.m_iButtons * 19];
            this.m_cBtnColors = new Color[this.m_iButtons * 15];
            final int[] iBtnInfo = this.m_iBtnInfo;
            final String[] array = new String[3];
            for (int i = 0; i < this.m_iButtons; ++i) {
                final String parameter2 = this.getParameter("b".concat(String.valueOf(String.valueOf(i))));
                this.m_sToken = parameter2;
                this.m_iToken = 0;
                final int n2 = i * 50;
                int j;
                for (j = 0; j < 8; ++j) {
                    iBtnInfo[n2 + j] = this.getInt();
                }
                for (int k = 0; k < 13; ++k) {
                    this.getGroup(array);
                    for (int l = 0; l < 3; ++l, ++j) {
                        iBtnInfo[n2 + j] = this.toInt(array[l]);
                    }
                }
                iBtnInfo[n2 + 50 - 1] = ((iBtnInfo[n2 + 5] == 1) ? 2 : 0);
                iBtnInfo[n2 + 50 - 2] = 100;
                if (this.m_iSelect < 0 && iBtnInfo[n2 + 7] == 1 && iBtnInfo[n2 + 5] != 0) {
                    this.m_iSelect = i;
                }
                final String substring = this.m_sToken.substring(0, this.m_iToken);
                this.getGroup(array);
                for (int n3 = 0; n3 < 3; ++n3) {
                    this.m_sBtnStrings[i * 19 + 13 + n3] = array[n3];
                    this.m_iBtnImages[i * 3 + n3] = -1;
                }
                int n4 = i * 19;
                for (int n5 = 0; n5 < 3; ++n5) {
                    this.getGroup(array);
                    for (int n6 = 0; n6 < 3; ++n6, ++n4) {
                        this.m_sBtnStrings[n4] = array[n6];
                    }
                }
                for (int n7 = 0; n7 < 4; ++n7, ++n4) {
                    this.m_sBtnStrings[n4] = this.getString();
                }
                final int iToken = this.m_iToken;
                int n8 = i * 15;
                for (int n9 = 0; n9 < 3; ++n9) {
                    this.getGroup(array);
                    for (int n10 = 0; n10 < 3; ++n10, ++n8) {
                        this.m_cBtnColors[n8] = this.getColor(array[n10]);
                    }
                }
                final String concat = String.valueOf(String.valueOf(substring)).concat(String.valueOf(String.valueOf(this.m_sToken.substring(iToken, this.m_iToken - 1))));
                int n11 = i * 19 + 16;
                this.getGroup(array);
                for (int n12 = 0; n12 < 3; ++n12, ++n11) {
                    this.m_sBtnStrings[n11] = array[n12];
                }
                calcRegCode = this.calcRegCode(parameter2, calcRegCode);
                n = this.calcRegCode(concat, n);
            }
            final int n13 = calcRegCode & Integer.MAX_VALUE;
            final int n14 = n & Integer.MAX_VALUE;
            final int hexParam = this.getHexParam("r");
            final int hexParam2 = this.getHexParam("c");
            if (n13 != hexParam && n14 != hexParam2 && !this.m_fLocal) {
                this.m_fRegistered = false;
            }
            this.m_iReady = 0;
            if ((b && equals) || this.fJDK11) {
                new Thread(this).start();
            }
            else {
                this.run();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private String a(final String s) {
        final StringBuffer sb = new StringBuffer(s);
        final StringBuffer sb2 = new StringBuffer();
        sb2.setLength(s.length());
        int n = 0;
        for (int i = 0; i < sb.length(); ++i) {
            final char char1 = sb.charAt(i);
            if (this.fJDK11) {
                if (!Character.isWhitespace(sb.charAt(i))) {
                    sb2.setCharAt(n++, char1);
                }
            }
            else if (Character.isSpace(char1) || char1 == '\t' || char1 == '\n' || char1 == '\r') {
                sb2.setCharAt(n++, char1);
            }
        }
        return sb2.toString();
    }
    
    int calcRegCode(final String s, int n) {
        final char[] charArray = this.a(s).toCharArray();
        for (int i = 0; i < charArray.length; ++i) {
            final char c = charArray[i];
            n ^= (c << 24 | c << 16 | c << 8 | c);
            final char c2 = (char)(c & '\u001f');
            n = ((n & -1) << c2 | n >>> ' ' - c2);
        }
        return n;
    }
    
    int getHexParam(final String s) {
        final String parameter = this.getParameter(s);
        return (parameter != null && parameter.length() > 0) ? Integer.parseInt(parameter, 16) : Integer.MIN_VALUE;
    }
    
    String getToken() {
        String trim = "";
        if (this.m_iToken < this.m_sToken.length()) {
            int n = this.m_sToken.indexOf(44, this.m_iToken);
            if (n < 0) {
                n = this.m_sToken.length();
            }
            trim = this.m_sToken.substring(this.m_iToken, n).trim();
            this.m_iToken = n + 1;
        }
        return trim;
    }
    
    void getGroup(final String[] array) {
        final String token = this.getToken();
        if (token.startsWith("^")) {
            final String fixString = this.fixString(token.substring(1, token.length()));
            for (int i = 0; i < 3; ++i) {
                array[i] = fixString;
            }
        }
        else {
            array[0] = this.fixString(token);
            for (int j = 1; j < 3; ++j) {
                array[j] = this.getString();
            }
        }
    }
    
    String fixString(String replace) {
        replace = replace.trim().replace('|', ',').replace('`', '\"');
        return replace.equals(".") ? null : replace;
    }
    
    String getString() {
        return this.fixString(this.getToken());
    }
    
    int toInt(String substring) {
        if (substring.startsWith("+")) {
            substring = substring.substring(1);
        }
        int int1 = 0;
        try {
            int1 = Integer.parseInt(substring);
        }
        catch (Exception ex) {}
        return int1;
    }
    
    int getInt() {
        return this.toInt(this.getToken());
    }
    
    Color getColor(final String s) {
        int int1 = 0;
        try {
            int1 = Integer.parseInt(s.trim(), 16);
        }
        catch (Exception ex) {}
        return new Color(int1);
    }
    
    Image imageAt(final Vector vector, final int n) {
        return (n < 0 || n >= vector.size()) ? null : vector.elementAt(n);
    }
    
    int getImg(final String s, final int n, final int n2, final boolean b, final boolean b2, final MediaTracker mediaTracker) {
        if (s == null) {
            return -1;
        }
        for (int i = this.m_vBitmapNames.size() - 1; i >= 0; --i) {
            if (((String)this.m_vBitmapNames.elementAt(i)).equals(s)) {
                return i;
            }
        }
        int size = -1;
        try {
            Image image = null;
            Label_0137: {
                if (!this.m_fNetscape4) {
                    if (!this.m_fExplorer4) {
                        break Label_0137;
                    }
                }
                try {
                    final InputStream resourceAsStream = this.getClass().getResourceAsStream(s);
                    if (resourceAsStream != null) {
                        final byte[] array = new byte[resourceAsStream.available()];
                        resourceAsStream.read(array);
                        image = this.getToolkit().createImage(array);
                    }
                }
                catch (Exception ex) {
                    System.err.println("inner catch: ".concat(String.valueOf(String.valueOf(ex))));
                }
            }
            if (image == null) {
                String value = s;
                if (this.m_sBitmapFolder != null && !this.m_fLocal) {
                    value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.m_sBitmapFolder))).append("/").append(s)));
                }
                try {
                    image = this.getImage(new URL(this.getDocumentBase(), value));
                }
                catch (Exception ex2) {
                    System.err.println(ex2);
                }
            }
            image.getWidth(this);
            this.checkImage(image, this);
            final Graphics graphics = this.m_iFlusher.getGraphics();
            graphics.drawImage(image, 0, 0, this);
            graphics.dispose();
            size = this.m_vBitmapImages.size();
            this.checkImage(image, this);
            this.m_vBitmapNames.addElement(s);
            this.m_vBitmapImages.addElement(image);
            this.m_vStatics.addElement(null);
        }
        catch (Exception ex3) {
            System.err.println("outer catch: ".concat(String.valueOf(String.valueOf(ex3))));
        }
        return size;
    }
    
    Object getAudio(final String s) {
        if (s == null) {
            return null;
        }
        for (int i = this.m_vAudioNames.size() - 1; i >= 0; --i) {
            if (((String)this.m_vAudioNames.elementAt(i)).equals(s)) {
                return this.m_vAudioObjects.elementAt(i);
            }
        }
        Object o = null;
        Object resourceAsStream = null;
        try {
            if (this.m_fNetscape4) {
                resourceAsStream = this.getClass().getResourceAsStream(s);
            }
            if (resourceAsStream == null || this.m_fNetscape6) {
                String value = s;
                if (this.m_sAudioFolder != null && !this.m_fLocal) {
                    value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.m_sAudioFolder))).append("/").append(s)));
                }
                final AudioClip audioClip = (AudioClip)(o = this.getAudioClip(this.getDocumentBase(), value));
                if (this.m_fNetscape4) {
                    audioClip.play();
                    audioClip.stop();
                }
            }
            else {
                final Object instance = Class.forName("sun.audio.AudioStream").getConstructor(Class.forName("java.io.InputStream")).newInstance(resourceAsStream);
                final Method[] methods = instance.getClass().getMethods();
                Method method = null;
                for (int j = 0; j < methods.length; ++j) {
                    if (methods[j].getName().compareTo("getData") == 0) {
                        method = methods[j];
                    }
                }
                o = method.invoke(instance, (Object[])null);
            }
        }
        catch (Exception ex) {
            System.err.println(ex);
        }
        if (o != null) {
            this.m_vAudioNames.addElement(s);
            this.m_vAudioObjects.addElement(o);
        }
        return o;
    }
    
    void resetImage(final int n) {
        final int n2 = n * 9 + 3;
        for (int i = 0; i < 3; ++i) {
            this.m_oBtnAudio[n2 + i] = this.stopSound(this.m_oBtnAudio[n2 + i]);
        }
        final int[] iBtnInfo = this.m_iBtnInfo;
        final int n3 = n * 50;
        if ((iBtnInfo[n3 + 6] & 0x4) != 0x0) {
            final int n4 = this.m_iBtnImages[n * 3 + iBtnInfo[n3 + 50 - 1]];
            final Image image = this.imageAt(this.m_vBitmapImages, n4);
            final Image image2 = this.imageAt(this.m_vStatics, n4);
            if (image != null && image2 == image && (this.m_fExplorer4 || (this.checkImage(image, this) & 0x20) != 0x0)) {
                this.m_fFlushing = true;
                image.flush();
                image.getWidth(this);
                final MediaTracker mediaTracker = new MediaTracker(this);
                mediaTracker.addImage(image, 0);
                try {
                    mediaTracker.waitForAll();
                }
                catch (Exception ex) {}
                this.checkImage(image, this);
                this.m_fFlushing = false;
            }
        }
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (!this.m_fFlushing) {
            final boolean b = (n & 0x10) != 0x0;
            final boolean b2 = (n & 0x20) != 0x0;
            if (b && !b2) {
                final int index = this.m_vBitmapImages.indexOf(image);
                if (index >= 0) {
                    final Image image2 = this.imageAt(this.m_vStatics, index);
                    if (image2 == null) {
                        this.m_vStatics.setElementAt(this.m_iBuffer, index);
                    }
                    else if (image2 == this.m_iBuffer) {
                        this.m_vStatics.setElementAt(image, index);
                    }
                }
            }
            if (b || b2) {
                final Vector<Rectangle> vector = new Vector<Rectangle>();
                this.buildImage(image, vector, null);
                for (int i = 0; i < vector.size(); ++i) {
                    this.paintRect(vector.elementAt(i));
                }
            }
        }
        return (n & 0xA0) == 0x0;
    }
    
    Object stopSound(final Object o) {
        if (o != null) {
            try {
                if (o instanceof AudioClip) {
                    ((AudioClip)o).stop();
                }
                else {
                    Class<?> forName = null;
                    try {
                        forName = Class.forName("sun.audio.AudioData");
                    }
                    catch (Exception ex2) {}
                    if (forName != null && forName.isInstance(o)) {
                        final Class<?> forName2 = Class.forName("sun.audio.AudioPlayer");
                        final Object value = forName2.getField("player").get(forName2.newInstance());
                        final Method[] methods = value.getClass().getMethods();
                        Method method = null;
                        for (int i = 0; i < methods.length; ++i) {
                            if (methods[i].getName().compareTo("stop") == 0) {
                                method = methods[i];
                            }
                        }
                        method.invoke(value, o);
                    }
                }
            }
            catch (Exception ex) {
                System.err.println("From stopSound(): ".concat(String.valueOf(String.valueOf(ex))));
            }
        }
        return null;
    }
    
    void playSound(final int n, final int n2) {
        final int n3 = n * 9 + n2;
        Object oPlaying = this.m_oBtnAudio[n3];
        final int n4 = this.m_iBtnInfo[n * 50 + 35 + n2] >> 2;
        if (oPlaying != null) {
            if (this.m_fOneSound) {
                this.m_oPlaying = this.stopSound(this.m_oPlaying);
            }
            try {
                if (oPlaying instanceof AudioClip) {
                    final AudioClip audioClip = (AudioClip)oPlaying;
                    if ((n4 & 0x2) != 0x0) {
                        audioClip.loop();
                    }
                    else {
                        audioClip.play();
                    }
                }
                else {
                    Class<?> forName = null;
                    try {
                        forName = Class.forName("sun.audio.AudioData");
                    }
                    catch (Exception ex2) {}
                    if (forName != null && forName.isInstance(oPlaying)) {
                        final Class<?> forName2 = Class.forName("sun.audio.AudioPlayer");
                        this.m_oBtnAudio[n3 + 6] = this.stopSound(this.m_oBtnAudio[n3 + 6]);
                        InputStream inputStream;
                        if ((n4 & 0x2) != 0x0) {
                            inputStream = (InputStream)Class.forName("sun.audio.ContinuousAudioDataStream").getConstructor(Class.forName("sun.audio.AudioData")).newInstance(oPlaying);
                        }
                        else {
                            inputStream = (InputStream)Class.forName("sun.audio.AudioDataStream").getConstructor(Class.forName("sun.audio.AudioData")).newInstance(oPlaying);
                        }
                        final Field field = forName2.getField("player");
                        field.getType();
                        final Object value = field.get(null);
                        final Method[] methods = value.getClass().getMethods();
                        Method method = null;
                        for (int i = 0; i < methods.length; ++i) {
                            if (methods[i].getName().compareTo("start") == 0) {
                                method = methods[i];
                            }
                        }
                        method.invoke(value, inputStream);
                        oPlaying = inputStream;
                    }
                }
            }
            catch (Exception ex) {
                System.err.println("From playSound(): ".concat(String.valueOf(String.valueOf(ex))));
            }
            if (this.m_fOneSound) {
                this.m_oPlaying = oPlaying;
            }
            this.m_oBtnAudio[n3 + 6] = oPlaying;
            if (n4 != 0) {
                this.m_oBtnAudio[n3 + 3] = oPlaying;
            }
        }
    }
    
    void playSound(final String s, final boolean b) {
        Object audio = this.getAudio(s);
        if (audio != null) {
            if (this.m_fOneSound) {
                this.m_oPlaying = this.stopSound(this.m_oPlaying);
            }
            try {
                if (audio instanceof AudioClip) {
                    final AudioClip audioClip = (AudioClip)audio;
                    if (b) {
                        audioClip.loop();
                    }
                    else {
                        audioClip.play();
                    }
                }
                else {
                    Class<?> forName = null;
                    try {
                        forName = Class.forName("sun.audio.AudioData");
                    }
                    catch (Exception ex2) {}
                    if (forName != null && forName.isInstance(audio)) {
                        final Class<?> forName2 = Class.forName("sun.audio.AudioPlayer");
                        InputStream inputStream;
                        if (b) {
                            inputStream = (InputStream)Class.forName("sun.audio.ContinuousAudioDataStream").getConstructor(Class.forName("sun.audio.AudioData")).newInstance(audio);
                        }
                        else {
                            inputStream = (InputStream)Class.forName("sun.audio.AudioDataStream").getConstructor(Class.forName("sun.audio.AudioData")).newInstance(audio);
                        }
                        final Field field = forName2.getField("player");
                        field.getType();
                        final Object value = field.get(null);
                        final Method[] methods = value.getClass().getMethods();
                        Method method = null;
                        for (int i = 0; i < methods.length; ++i) {
                            if (methods[i].getName().compareTo("start") == 0) {
                                method = methods[i];
                            }
                        }
                        method.invoke(value, inputStream);
                        audio = inputStream;
                    }
                }
            }
            catch (Exception ex) {
                System.err.println("From playSound(): ".concat(String.valueOf(String.valueOf(ex))));
            }
            if (this.m_fOneSound) {
                this.m_oPlaying = audio;
            }
        }
    }
    
    Rectangle safeUnion(final Rectangle rectangle, final Rectangle rectangle2) {
        return (rectangle == null) ? rectangle2 : rectangle.union(rectangle2);
    }
    
    void waitMS(final long n) {
        if (n > 0) {
            try {
                Thread.sleep(n);
            }
            catch (Exception ex) {}
        }
    }
    
    void getCoord(final StringTokenizer stringTokenizer, final int[] array) {
        String s = stringTokenizer.nextToken().trim();
        if (s.endsWith("+")) {
            array[0] = 1;
        }
        else if (s.endsWith("-")) {
            array[0] = -1;
        }
        if (array[0] != 0) {
            s = s.substring(0, s.length() - 1);
        }
        array[1] = this.toInt(s);
    }
    
    Rectangle performAction(final String s, Rectangle rectangle) {
        if (s != null) {
            final int[] iBtnInfo = this.m_iBtnInfo;
            final StringTokenizer stringTokenizer = new StringTokenizer(s, ";");
            for (int countTokens = stringTokenizer.countTokens(), i = 0; i < countTokens; ++i) {
                final String trim = stringTokenizer.nextToken().trim();
                int index = trim.indexOf(32);
                if (index >= 0) {
                    String[] array;
                    String substring;
                    int n;
                    for (array = new String[] { "show", "hide", "up", "over", "down", "wait", "call", "move", "size", "xform", "settext", "play", "fade" }, substring = trim.substring(0, index), n = array.length - 1; n >= 0 && !substring.equalsIgnoreCase(array[n]); --n) {}
                    if (n >= 0) {
                        while (trim.charAt(index) == ' ' && index < trim.length()) {
                            ++index;
                        }
                        if (index < trim.length()) {
                            final String substring2 = trim.substring(index);
                            switch (n) {
                                case 5: {
                                    this.paintRect(rectangle);
                                    this.waitMS(this.toInt(substring2));
                                    break;
                                }
                                case 6: {
                                    this.callJS(substring2);
                                    break;
                                }
                                case 7:
                                case 8:
                                case 9:
                                case 12: {
                                    final int index2 = substring2.indexOf(":");
                                    if (index2 >= 0) {
                                        final String trim2 = substring2.substring(0, index2).trim();
                                        final StringTokenizer stringTokenizer2 = new StringTokenizer(substring2.substring(index2 + 1).trim(), ", ");
                                        final int[] array2 = { 0, 0 };
                                        final int[] array3 = { 0, 0 };
                                        final int[] array4 = { 0, 0 };
                                        final int[] array5 = { 0, 0 };
                                        final int[] array6 = { 0, 0 };
                                        if (n == 7 || n == 9) {
                                            this.getCoord(stringTokenizer2, array2);
                                            this.getCoord(stringTokenizer2, array3);
                                        }
                                        if (n == 8 || n == 9) {
                                            this.getCoord(stringTokenizer2, array4);
                                            this.getCoord(stringTokenizer2, array5);
                                        }
                                        if (n == 12) {
                                            this.getCoord(stringTokenizer2, array6);
                                        }
                                        int int1 = 0;
                                        if (stringTokenizer2.hasMoreTokens()) {
                                            int1 = this.toInt(stringTokenizer2.nextToken().trim());
                                        }
                                        int int2 = 0;
                                        if (stringTokenizer2.hasMoreTokens()) {
                                            int2 = this.toInt(stringTokenizer2.nextToken().trim());
                                        }
                                        for (int j = this.m_iButtons - 1; j >= 0; --j) {
                                            if (this.m_sBtnStrings[j * 19 + 11].equals(trim2)) {
                                                rectangle = this.safeUnion(rectangle, this.getBtnRect(j));
                                                final int n2 = j * 50;
                                                final Rectangle rectangle2 = new Rectangle(iBtnInfo[n2], iBtnInfo[n2 + 1], iBtnInfo[n2 + 2], iBtnInfo[n2 + 3]);
                                                final Rectangle union = rectangle2.union(rectangle2);
                                                final int n3 = iBtnInfo[n2 + 50 - 2];
                                                int n4 = iBtnInfo[n2 + 50 - 2];
                                                if (n == 7 || n == 9) {
                                                    union.x = ((array2[0] == 0) ? array2[1] : (rectangle2.x + array2[0] * array2[1]));
                                                    union.y = ((array3[0] == 0) ? array3[1] : (rectangle2.y + array3[0] * array3[1]));
                                                }
                                                if (n == 8 || n == 9) {
                                                    union.width = ((array4[0] == 0) ? array4[1] : (rectangle2.width + array4[0] * array4[1]));
                                                    union.height = ((array5[0] == 0) ? array5[1] : (rectangle2.height + array5[0] * array5[1]));
                                                    this.reRender(this.m_iResizing = j);
                                                }
                                                if (n == 12) {
                                                    n4 = ((array6[0] == 0) ? array6[1] : (n3 + array6[0] * array6[1]));
                                                    this.reRender(this.m_iResizing = j);
                                                }
                                                if (int1 == 0) {
                                                    this.setBtnRect(j, union);
                                                    rectangle = this.safeUnion(rectangle, this.getBtnRect(j));
                                                }
                                                else {
                                                    this.m_iPaintMode |= 0x2;
                                                    this.paintRect(rectangle);
                                                    int max = int1;
                                                    if (this.m_fNetscape6 && int2 == 0) {
                                                        max = Math.max(max / 5, 1);
                                                    }
                                                    for (int k = 1; k <= max; ++k) {
                                                        final long currentTimeMillis = System.currentTimeMillis();
                                                        final int n5 = max - k;
                                                        final Rectangle btnRect = this.getBtnRect(j);
                                                        final Rectangle rectangle3 = new Rectangle((n5 * rectangle2.x + k * union.x) / max, (n5 * rectangle2.y + k * union.y) / max, (n5 * rectangle2.width + k * union.width) / max, (n5 * rectangle2.height + k * union.height) / max);
                                                        iBtnInfo[n2 + 50 - 2] = (n5 * n3 + k * n4) / int1;
                                                        this.setBtnRect(j, rectangle3);
                                                        this.paintRect(btnRect.union(this.getBtnRect(j)));
                                                        if (k < max) {
                                                            this.waitMS(int2 - (System.currentTimeMillis() - currentTimeMillis));
                                                        }
                                                    }
                                                    this.m_iPaintMode &= 0xFFFFFFFD;
                                                }
                                                this.m_iResizing = -1;
                                                break;
                                            }
                                        }
                                        break;
                                    }
                                    break;
                                }
                                case 10: {
                                    final int index3 = substring2.indexOf(44);
                                    if (index3 < 0) {
                                        break;
                                    }
                                    final int index4 = substring2.indexOf(58, index3);
                                    if (index4 < 0) {
                                        break;
                                    }
                                    final String trim3 = substring2.substring(0, index3).trim();
                                    final String lowerCase = substring2.substring(index3 + 1, index4).trim().toLowerCase();
                                    final String substring3 = substring2.substring(index4 + 1);
                                    int n6 = -1;
                                    if (lowerCase.equals("up")) {
                                        n6 = 0;
                                    }
                                    else if (lowerCase.equals("over")) {
                                        n6 = 1;
                                    }
                                    else if (lowerCase.equals("down")) {
                                        n6 = 2;
                                    }
                                    else if (lowerCase.equals("all")) {
                                        n6 = 3;
                                    }
                                    if (n6 == 3) {
                                        rectangle = this.setStringAttrib(trim3, 3, substring3, rectangle, true);
                                        rectangle = this.setStringAttrib(trim3, 4, substring3, rectangle, true);
                                        rectangle = this.setStringAttrib(trim3, 5, substring3, rectangle, true);
                                        break;
                                    }
                                    if (n6 >= 0) {
                                        rectangle = this.setStringAttrib(trim3, 3 + n6, substring3, rectangle, true);
                                        break;
                                    }
                                    break;
                                }
                                case 11: {
                                    final int index5 = substring2.indexOf(44);
                                    boolean b = false;
                                    String trim4 = substring2;
                                    if (index5 >= 0) {
                                        trim4 = substring2.substring(0, index5).trim();
                                        if (!substring2.substring(index5 + 1).trim().equals("0")) {
                                            b = true;
                                        }
                                    }
                                    this.playSound(trim4, b);
                                    break;
                                }
                                default: {
                                    if (n < 2) {
                                        rectangle = this.setAttrib(substring2, 4, 1 - n, rectangle, false);
                                        break;
                                    }
                                    rectangle = this.setAttrib(substring2, 49, n - 2, rectangle, false);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        return rectangle;
    }
    
    Rectangle updateActionRect(final int n, final Rectangle rectangle, final int n2) {
        return this.performAction(this.m_sBtnStrings[n * 19 + ((n2 >= 3) ? (13 + n2) : (6 + n2))], this.safeUnion(rectangle, this.getBtnRect(n)));
    }
    
    void setBtnRect(final int n, final Rectangle rectangle) {
        final int n2 = n * 50;
        final int[] iBtnInfo = this.m_iBtnInfo;
        iBtnInfo[n2] = rectangle.x;
        iBtnInfo[n2 + 1] = rectangle.y;
        iBtnInfo[n2 + 2] = rectangle.width;
        iBtnInfo[n2 + 3] = rectangle.height;
    }
    
    public Rectangle getBtnRect(final int n) {
        final int n2 = n * 50;
        int n3 = 0;
        final int[] iBtnInfo = this.m_iBtnInfo;
        for (int i = 0; i < 3; ++i) {
            final int n4 = iBtnInfo[n2 + 38 + i];
            if (n4 > n3) {
                n3 = n4;
            }
        }
        return new Rectangle(iBtnInfo[n2] - n3, iBtnInfo[n2 + 1] - n3, iBtnInfo[n2 + 2] + n3 * 2, iBtnInfo[n2 + 3] + n3 * 2);
    }
    
    public void paintRect(final Rectangle rectangle) {
        final Graphics graphics = this.getGraphics();
        if (graphics != null && rectangle != null) {
            graphics.clipRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
            this.privatePaint(graphics);
        }
    }
    
    public void paintAll() {
        this.paintRect(new Rectangle(0, 0, this.m_iWidth, this.m_iHeight));
    }
    
    public void paintBtn(final int n) {
        this.paintRect(this.getBtnRect(n));
    }
    
    void drawHLine(final boolean b, final Graphics graphics, final int[] array, final int n, final int n2, final int n3, final int n4, final Color color) {
        if (b) {
            graphics.setColor(color);
            graphics.drawLine(n3, n2, n4, n2);
        }
        else {
            final int n5 = color.getRGB() | 0xFF000000;
            final int n6 = n2 * n;
            int i = n6 + n3;
            do {
                array[i++] = n5;
            } while (i != n6 + n4 + 1);
        }
    }
    
    void drawVLine(final boolean b, final Graphics graphics, final int[] array, final int n, final int n2, final int n3, final int n4, final Color color) {
        if (b) {
            graphics.setColor(color);
            graphics.drawLine(n2, n3, n2, n4);
        }
        else {
            final int n5 = color.getRGB() | 0xFF000000;
            int i = n3 * n + n2;
            do {
                array[i] = n5;
                i += n;
            } while (i != (n4 + 1) * n + n2);
        }
    }
    
    public void drawBtnImage(final Graphics graphics, final int n, final int n2, final int n3, final Image image, final Vector vector) {
        try {
            final boolean b = n == this.m_iResizing;
            final int n4 = n * 6 + ((n3 == 0) ? 0 : 3) + n2;
            final Image[] iPreRendered = this.m_iPreRendered;
            if (n3 == 1 || b || iPreRendered[n4] == null) {
                final int[] iBtnInfo = this.m_iBtnInfo;
                final int n5 = n * 50;
                final int n6 = iBtnInfo[n5 + 2];
                final int n7 = iBtnInfo[n5 + 3];
                final Color color = this.m_cBtnColors[n * 15 + n2];
                final int n8 = iBtnInfo[n5 + 14 + n2];
                final boolean b2 = (n8 & 0x100) != 0x0;
                final int n9 = n8 & 0xFF;
                final int n10 = iBtnInfo[n5 + 11 + n2];
                final int red = color.getRed();
                final int green = color.getGreen();
                final int blue = color.getBlue();
                final int min = Math.min(red + n9, 255);
                final int min2 = Math.min(green + n9, 255);
                final int min3 = Math.min(blue + n9, 255);
                final int max = Math.max(red - n9, 0);
                final int max2 = Math.max(green - n9, 0);
                final int max3 = Math.max(blue - n9, 0);
                final Color color2 = new Color(min, min2, min3);
                final Color color3 = new Color(max, max2, max3);
                final int n11 = iBtnInfo[n5 + 8 + n2];
                final int n12 = (n11 > 1) ? n10 : 0;
                final int n13 = (n11 == 3 || n11 == 5) ? 2 : 0;
                final int n14 = n6 * n7;
                int[] array = null;
                if (n3 == 0) {
                    if (n11 == 0 || vector != null) {
                        return;
                    }
                    if (!b) {
                        if (n14 <= 0) {
                            return;
                        }
                        array = new int[n14];
                    }
                    if (n11 == 1 || n11 > 3) {
                        if (b) {
                            graphics.setColor(color);
                            graphics.fillRect(0, 0, n6, n7);
                        }
                        else {
                            final int n15 = color.getRGB() | 0xFF000000;
                            for (int i = 0; i < n6; ++i) {
                                array[i] = n15;
                            }
                            for (int n16 = 0, j = 0; j < n7 - 1; ++j, n16 += n6) {
                                System.arraycopy(array, n16, array, n16 + n6, n6);
                            }
                        }
                    }
                    if (n10 > 0 && n11 > 1) {
                        for (int k = 0; k < n10; ++k) {
                            final int n17 = n10 - k;
                            final Color color4 = b2 ? color2 : new Color(red + (min - red) * n17 / n10, green + (min2 - green) * n17 / n10, blue + (min3 - blue) * n17 / n10);
                            final Color color5 = b2 ? color3 : new Color(red + (max - red) * n17 / n10, green + (max2 - green) * n17 / n10, blue + (max3 - blue) * n17 / n10);
                            final Color color6 = (n11 == 3 || n11 == 5) ? color5 : color4;
                            this.drawHLine(b, graphics, array, n6, k, k, n6 - 1 - k, color6);
                            this.drawVLine(b, graphics, array, n6, k, k, n7 - 1 - k, color6);
                            final Color color7 = (n11 == 3 || n11 == 5) ? color4 : color5;
                            this.drawVLine(b, graphics, array, n6, n6 - 1 - k, k, n7 - 1 - k, color7);
                            this.drawHLine(b, graphics, array, n6, n7 - 1 - k, k, n6 - 1 - k, color7);
                        }
                    }
                }
                else {
                    if (n3 == 1) {
                        final Image image2 = this.imageAt(this.m_vBitmapImages, this.m_iBtnImages[n * 3 + n2]);
                        if (image2 != null) {
                            this.checkImage(image2, this);
                            final int width = image2.getWidth(this);
                            final int height = image2.getHeight(this);
                            if (width != -1 && height != -1) {
                                if (vector != null && image == image2) {
                                    vector.addElement(new Rectangle(iBtnInfo[n5], iBtnInfo[n5 + 1], iBtnInfo[n5 + 2], iBtnInfo[n5 + 3]));
                                }
                                else {
                                    final int n18 = (n7 - height) / 2 + n13;
                                    final int n19 = iBtnInfo[n5 + 35 + n2];
                                    switch (((n19 & 0x10) != 0x0) ? 4 : (n19 & 0x3)) {
                                        case 0: {
                                            graphics.drawImage(image2, n12 + n13, n18, this);
                                            break;
                                        }
                                        default: {
                                            graphics.drawImage(image2, (n6 - width) / 2 + n13, n18, this);
                                            break;
                                        }
                                        case 2: {
                                            graphics.drawImage(image2, n6 - width - n12 + n13, n18, this);
                                            break;
                                        }
                                        case 3: {
                                            graphics.drawImage(image2, n12, n12, n6 - 2 * n12, n7 - 2 * n12, this);
                                            break;
                                        }
                                        case 4: {
                                            graphics.drawImage(image2, iBtnInfo[n5 + 41 + n2] - width / 2, iBtnInfo[n5 + 44 + n2] - height / 2, this);
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        return;
                    }
                    if (n3 == 2) {
                        final String s = this.m_sBtnStrings[n * 19 + 3 + n2];
                        if (s == null || vector != null) {
                            return;
                        }
                        Image image3 = null;
                        final Color color8 = this.m_cBtnColors[n * 15 + 3 + n2];
                        Graphics graphics2;
                        if (b) {
                            graphics2 = graphics;
                            graphics2.setColor(color8);
                        }
                        else {
                            if (n14 <= 0) {
                                return;
                            }
                            array = new int[n14];
                            image3 = this.createImage(n6, n7);
                            graphics2 = image3.getGraphics();
                        }
                        final int n20 = iBtnInfo[n5 + 23 + n2];
                        final Font font = new Font(this.m_sFonts[iBtnInfo[n5 + 17 + n2]], n20 & 0x3, iBtnInfo[n5 + 20 + n2]);
                        graphics2.setFont(font);
                        final FontMetrics fontMetrics = graphics2.getFontMetrics(font);
                        int height2 = fontMetrics.getHeight();
                        int ascent = fontMetrics.getAscent();
                        if (this.m_fNetscape4) {
                            height2 = (height2 * 17 + 10) / 20;
                            ascent = (ascent * 17 + 10) / 20;
                        }
                        int index = -2;
                        int n21 = 1;
                        while ((index = s.indexOf("\\n", index + 2)) != -1) {
                            ++n21;
                        }
                        final int n22 = height2 * n21;
                        final int n23 = (iBtnInfo[n5 + 26 + n2] == 3) ? iBtnInfo[n5 + 32 + n2] : ((n7 - n22) / 2 + ascent);
                        final int n24 = n20 & 0x18;
                        final int n25 = (n24 == 8) ? 1 : -1;
                        final int[] array2 = { -1, 1, 0 };
                        final Color[] array3 = { color2, color3, color8 };
                        for (int l = (n24 == 0) ? 2 : 0; l < 3; ++l) {
                            final Color color9 = array3[l];
                            final int n26 = array2[l] * n25;
                            if (!b) {
                                graphics2.setColor(Color.black);
                                graphics2.fillRect(0, 0, n6, n7);
                            }
                            graphics2.setColor(b ? color9 : Color.white);
                            int index2 = 0;
                            for (int n27 = n23, n28 = 0; n28 < n21; ++n28, n27 += height2) {
                                final int n29 = index2;
                                index2 = s.indexOf("\\n", n29);
                                this.drawStringLine((index2 == -1) ? s.substring(n29) : s.substring(n29, index2), graphics2, n27 + n13, n26, iBtnInfo, n5, n2, fontMetrics, n6, n13, n12, n20);
                                index2 += 2;
                            }
                            if (!b) {
                                final int[] array4 = new int[n14];
                                final PixelGrabber pixelGrabber = new PixelGrabber(image3, 0, 0, n6, n7, array4, 0, n6);
                                try {
                                    pixelGrabber.grabPixels();
                                }
                                catch (Exception ex) {}
                                final int n30 = color9.getRGB() | 0xFF000000;
                                for (int n31 = 0; n31 < n14; ++n31) {
                                    if ((array4[n31] & 0xFF) > 127) {
                                        array[n31] = n30;
                                    }
                                }
                            }
                        }
                        if (!b) {
                            graphics2.dispose();
                            image3.flush();
                        }
                    }
                }
                if (b) {
                    return;
                }
                iPreRendered[n4] = this.createImage(new MemoryImageSource(n6, n7, array, 0, n6));
            }
            if (graphics != null) {
                graphics.drawImage(iPreRendered[n4], 0, 0, this);
            }
        }
        catch (Exception ex2) {}
    }
    
    void drawStringLine(final String s, final Graphics graphics, final int n, final int n2, final int[] array, final int n3, final int n4, final FontMetrics fontMetrics, final int n5, final int n6, final int n7, final int n8) {
        final int stringWidth = fontMetrics.stringWidth(s);
        int n9 = 0;
        switch (array[n3 + 26 + n4]) {
            case 0: {
                n9 = n6 + n7;
                break;
            }
            default: {
                n9 = (n5 - stringWidth) / 2 + n6;
                break;
            }
            case 2: {
                n9 = n5 - stringWidth - n7 + n6;
                break;
            }
            case 3: {
                n9 = array[n3 + 29 + n4];
                break;
            }
        }
        this.drawText(graphics, s, n9 + n2, n + n2, stringWidth, n8, array[n3 + 20 + n4]);
    }
    
    void drawText(final Graphics graphics, final String s, final int n, final int n2, final int n3, final int n4, final int n5) {
        graphics.drawString(s, n, n2);
        if ((n4 & 0x4) != 0x0) {
            final int n6 = n2 + n5 / 6;
            graphics.drawLine(n, n6, n + n3, n6);
        }
    }
    
    void privatePaint(final Graphics graphics) {
        this.buildImage(null, null, graphics.getClipRect());
        graphics.drawImage(this.m_iBuffer, 0, 0, this);
        graphics.dispose();
    }
    
    public void paint(final Graphics graphics) {
        this.privatePaint(graphics);
        this.m_iPaintMode |= 0x1;
    }
    
    public void buildImage(final Image image, final Vector vector, final Rectangle rectangle) {
        final int iWidth = this.m_iWidth;
        final int iHeight = this.m_iHeight;
        final Graphics graphics = this.m_iBuffer.getGraphics();
        graphics.setColor(this.m_cBgnd);
        if (rectangle == null) {
            graphics.fillRect(0, 0, iWidth, iHeight);
        }
        else {
            graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        }
        final Image image2 = this.imageAt(this.m_vBitmapImages, this.m_iBgnd);
        if (image2 != null) {
            final int width = image2.getWidth(this);
            final int height = image2.getHeight(this);
            if (width != -1 && height != -1) {
                if (vector != null && image != null && image2 == image) {
                    vector.addElement(new Rectangle(0, 0, iWidth, iHeight));
                }
                else {
                    switch (this.m_iBgndMode) {
                        case 0: {
                            graphics.drawImage(image2, (iWidth - width) / 2, (iHeight - height) / 2, this);
                            break;
                        }
                        case 1: {
                            graphics.drawImage(image2, 0, 0, iWidth, iHeight, this);
                            break;
                        }
                        case 2: {
                            for (int i = this.m_ptBgndOffset.y; i < iHeight; i += height) {
                                for (int j = this.m_ptBgndOffset.x; j < iWidth; j += width) {
                                    graphics.drawImage(image2, j, i, width, height, this);
                                }
                            }
                            break;
                        }
                    }
                }
            }
        }
        if (this.m_iReady >= 0) {
            final int[] iBtnInfo = this.m_iBtnInfo;
            for (int k = this.m_iButtons - 1; k >= 0; --k) {
                final int n = k * 50;
                if (iBtnInfo[n + 4] != 0 && (rectangle == null || rectangle.intersects(this.getBtnRect(k)))) {
                    final int n2 = iBtnInfo[n + 50 - 1];
                    final int n3 = iBtnInfo[n + 38 + n2];
                    final int n4 = iBtnInfo[n + 2];
                    final int n5 = iBtnInfo[n + 3];
                    final Graphics graphics2 = this.m_iBuffer.getGraphics();
                    graphics2.translate(iBtnInfo[n], iBtnInfo[n + 1]);
                    graphics2.clipRect(-n3, -n3, n4 + n3 * 2, n5 + n3 * 2);
                    for (int l = 0; l < 3; ++l) {
                        this.drawBtnImage(graphics2, k, n2, l, image, vector);
                    }
                    if (n3 > 0) {
                        graphics2.setColor(this.m_cBtnColors[k * 15 + 6 + n2]);
                        for (int n6 = 1; n6 <= n3; ++n6) {
                            graphics2.drawRect(-n6, -n6, n4 + n6 * 2 - 1, n5 + n6 * 2 - 1);
                        }
                    }
                    if (n3 > 0) {
                        graphics2.setColor(this.m_cBtnColors[k * 15 + 6 + n2]);
                        for (int n7 = 1; n7 <= n3; ++n7) {
                            graphics2.drawRect(-n7, -n7, n4 + n7 * 2 - 1, n5 + n7 * 2 - 1);
                        }
                    }
                    graphics2.dispose();
                }
            }
        }
        if (!this.m_fRegistered) {
            final String s = "UNREGISTERED";
            final int stringWidth = this.getFontMetrics(graphics.getFont()).stringWidth(s);
            graphics.setColor(Color.black);
            graphics.fillRect((iWidth - stringWidth) / 2 - 10, iHeight / 2 - 15, stringWidth + 20, 30);
            graphics.setColor(Color.white);
            graphics.drawRect((iWidth - stringWidth) / 2 - 11, iHeight / 2 - 16, stringWidth + 21, 31);
            graphics.drawString(s, (iWidth - stringWidth) / 2, iHeight / 2 + 4);
        }
        graphics.dispose();
    }
    
    public String getJS() {
        if (this.m_vJSEvals.size() <= 0) {
            return null;
        }
        final String s = this.m_vJSEvals.elementAt(0);
        this.m_vJSEvals.removeElementAt(0);
        return s;
    }
    
    public boolean callJSObject(final String s) {
        boolean b = false;
        try {
            final Class<?> forName = Class.forName("netscape.javascript.JSObject");
            final Method[] methods = forName.getMethods();
            Method method = null;
            for (int i = 0; i < methods.length; ++i) {
                if (methods[i].getName().compareTo("getWindow") == 0) {
                    method = methods[i];
                }
            }
            final Object[] array = { this };
            final Object[] array2 = { s };
            Method method2 = null;
            final Object invoke = method.invoke(forName, array);
            final Method[] methods2 = invoke.getClass().getMethods();
            for (int j = 0; j < methods2.length; ++j) {
                if (methods2[j].getName().compareTo("eval") == 0) {
                    method2 = methods2[j];
                }
            }
            method2.invoke(invoke, array2);
            b = true;
        }
        catch (Exception ex) {}
        if (!b) {
            try {
                String nextToken = "";
                Object[] array3 = new Object[0];
                final StringTokenizer stringTokenizer = new StringTokenizer(s, "(");
                if (stringTokenizer.hasMoreTokens()) {
                    nextToken = stringTokenizer.nextToken();
                }
                if (stringTokenizer.hasMoreTokens()) {
                    String s2 = stringTokenizer.nextToken();
                    final int lastIndex = s2.lastIndexOf(41);
                    if (lastIndex >= 0) {
                        s2 = s2.substring(0, lastIndex - 1);
                    }
                    final StringTokenizer stringTokenizer2 = new StringTokenizer(s2, ",");
                    final int countTokens = stringTokenizer2.countTokens();
                    final String[] array4 = new String[countTokens];
                    int n = 0;
                    while (stringTokenizer2.hasMoreTokens()) {
                        array4[n] = stringTokenizer2.nextToken();
                        ++n;
                    }
                    final String[] array5 = new String[countTokens];
                    int n2 = 0;
                    for (int k = 0; k < array4.length; ++k) {
                        final String trim = array4[k].trim();
                        if (trim.length() == 0) {
                            array5[n2] = "";
                            ++n2;
                        }
                        else {
                            final char char1 = trim.charAt(0);
                            final char char2 = trim.charAt(trim.length() - 1);
                            if ((char1 == '\"' && char2 == '\"') || (char1 == '\'' && char2 == '\'')) {
                                array5[n2] = trim.substring(1, trim.length() - 1);
                                ++n2;
                            }
                            else if (char1 != '\"' && char1 != '\'') {
                                array5[n2] = trim;
                                ++n2;
                            }
                            else if (k == array4.length - 1) {
                                array5[n2] = trim.substring(1);
                                ++n2;
                            }
                            else {
                                array4[k + 1] = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(array4[k]))).append(",").append(array4[k + 1])));
                            }
                        }
                    }
                    array3 = new Object[n2];
                    for (int l = 0; l < n2; ++l) {
                        array3[l] = array5[l];
                    }
                }
                JSObject.getWindow((Applet)this).call(nextToken, array3);
                b = true;
            }
            catch (Exception ex2) {}
        }
        return b;
    }
    
    public void callJS(String concat) {
        if (!concat.endsWith(";")) {
            concat = String.valueOf(String.valueOf(concat)).concat(";");
        }
        if ((this.m_fMicrosoft && !this.m_fExplorer4) || !this.callJSObject(concat)) {
            this.m_vJSEvals.addElement(concat);
        }
    }
    
    int whichButton(final int n, final int n2) {
        final int[] iBtnInfo = this.m_iBtnInfo;
        for (int i = 0; i < this.m_iButtons; ++i) {
            final int n3 = i * 50;
            if (iBtnInfo[n3 + 4] != 0) {
                if (this.m_ptOffset != null) {
                    if (i == this.m_iBtnGrab) {
                        continue;
                    }
                    if ((iBtnInfo[n3 + 6] & 0x1) != 0x0) {
                        continue;
                    }
                }
                final Rectangle btnRect = this.getBtnRect(i);
                final int n4 = n - btnRect.x;
                final int n5 = n2 - btnRect.y;
                if (n4 >= 0 && n4 < btnRect.width && n5 >= 0 && n5 < btnRect.height) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.m_iReady >= 0) {
            this.mouseMove(event, n, n2);
            Rectangle updateActionRect = null;
            final int whichButton = this.whichButton(n, n2);
            final int[] iBtnInfo = this.m_iBtnInfo;
            if (whichButton != -1 && (this.m_iBtnGrab == -1 || this.m_iBtnGrab == whichButton)) {
                final int n3 = whichButton * 50;
                if (iBtnInfo[n3 + 50 - 1] != 2) {
                    iBtnInfo[n3 + 50 - 1] = 2;
                    this.resetImage(whichButton);
                    this.playSound(whichButton, 2);
                    this.m_ptOffset = (((iBtnInfo[n3 + 6] & 0x1) != 0x0) ? new Point(n - iBtnInfo[n3], n2 - iBtnInfo[n3 + 1]) : null);
                    updateActionRect = this.updateActionRect(whichButton, updateActionRect, 2);
                }
                this.m_iBtnGrab = whichButton;
            }
            if (updateActionRect != null) {
                this.paintRect(updateActionRect);
            }
        }
        this.m_iOver = this.whichButton(n, n2);
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.m_iReady >= 0) {
            this.mouseDrag(event, n, n2);
            boolean b = false;
            final int whichButton = this.whichButton(n, n2);
            if (this.m_iBtnGrab >= 0) {
                final int[] iBtnInfo = this.m_iBtnInfo;
                final int n3 = this.m_iBtnGrab * 50;
                final int n4 = iBtnInfo[n3 + 4];
                iBtnInfo[n3 + 4] = 1;
                final int whichButton2 = this.whichButton(n, n2);
                iBtnInfo[n3 + 4] = n4;
                Rectangle rectangle = this.updateActionRect(this.m_iBtnGrab, null, 3);
                if (whichButton == this.m_iBtnGrab || this.m_ptOffset != null) {
                    if (this.m_ptOffset != null && this.m_iOver >= 0) {
                        iBtnInfo[n3 + 4] = 0;
                        rectangle = this.getBtnRect(this.m_iOver);
                        iBtnInfo[this.m_iOver * 50 + 50 - 1] = 1;
                        this.resetImage(this.m_iOver);
                        this.playSound(this.m_iBtnGrab, 0);
                        b = true;
                    }
                    else {
                        switch (iBtnInfo[n3 + 7]) {
                            default: {
                                iBtnInfo[n3 + 50 - 1] = 1;
                                this.m_iOver = this.m_iBtnGrab;
                                this.resetImage(this.m_iBtnGrab);
                                if (this.m_ptOffset == null) {
                                    this.playSound(this.m_iBtnGrab, 0);
                                    break;
                                }
                                break;
                            }
                            case 0: {
                                iBtnInfo[n3 + 5] = 1 - iBtnInfo[n3 + 5];
                                iBtnInfo[n3 + 50 - 1] = ((iBtnInfo[n3 + 5] == 1) ? 2 : 1);
                                rectangle = this.updateActionRect(this.m_iBtnGrab, rectangle, 4 + ((iBtnInfo[n3 + 5] != 1) ? 1 : 0));
                                this.resetImage(this.m_iBtnGrab);
                                if (iBtnInfo[n3 + 5] == 0) {
                                    this.playSound(whichButton, 0);
                                    this.m_iOver = this.m_iBtnGrab;
                                    break;
                                }
                                break;
                            }
                            case 1: {
                                if (this.m_iSelect != this.m_iBtnGrab) {
                                    if (this.m_iSelect >= 0) {
                                        final int n5 = this.m_iSelect * 50;
                                        iBtnInfo[n5 + 50 - 1] = 0;
                                        this.resetImage(this.m_iSelect);
                                        iBtnInfo[n5 + 5] = 0;
                                        rectangle = this.updateActionRect(this.m_iSelect, this.updateActionRect(this.m_iSelect, rectangle, 5), 0);
                                    }
                                    this.m_iSelect = this.m_iBtnGrab;
                                    iBtnInfo[n3 + 5] = 1;
                                    rectangle = this.updateActionRect(this.m_iSelect, rectangle, 4);
                                    break;
                                }
                                break;
                            }
                        }
                    }
                    final Rectangle updateActionRect = this.updateActionRect(this.m_iBtnGrab, rectangle, iBtnInfo[n3 + 50 - 1]);
                    if (updateActionRect != null) {
                        this.paintRect(updateActionRect);
                    }
                }
                if (b || whichButton2 == this.m_iBtnGrab) {
                    final int n6 = this.m_iBtnGrab * 19;
                    final String s = this.m_sBtnStrings[n6 + 9];
                    final String s2 = this.m_sBtnStrings[n6 + 10];
                    if (s != null) {
                        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
                        final StringTokenizer stringTokenizer2 = (s2 != null) ? new StringTokenizer(s2, ",") : null;
                        while (stringTokenizer.hasMoreTokens()) {
                            String s3 = stringTokenizer.nextToken().trim();
                            if (s3.toLowerCase().startsWith("javascript:")) {
                                this.callJS(s3.substring(s3.indexOf(58) + 1).trim());
                            }
                            else {
                                final int index;
                                if (this.m_fMicrosoft && !this.m_fExplorer4 && (index = s3.indexOf(35)) >= 0) {
                                    s3 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3.substring(0, index + 1)))).append("#").append(s3.substring(index + 1))));
                                }
                                URL url = null;
                                try {
                                    url = new URL(this.getDocumentBase(), s3);
                                }
                                catch (Exception ex) {}
                                if (url == null) {
                                    continue;
                                }
                                final AppletContext appletContext = this.getAppletContext();
                                if (stringTokenizer2 != null && stringTokenizer2.hasMoreTokens()) {
                                    appletContext.showDocument(url, stringTokenizer2.nextToken().trim());
                                }
                                else {
                                    if (s3.indexOf(".htm") != -1) {
                                        this.SetCursor(3);
                                    }
                                    appletContext.showDocument(url);
                                }
                            }
                        }
                    }
                }
            }
            this.m_ptOffset = null;
            this.m_iBtnGrab = -1;
        }
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        if (this.m_iReady >= 0 && this.m_iBtnGrab != -1) {
            Rectangle updateActionRect = null;
            int n3 = this.m_iBtnGrab * 50;
            final int[] iBtnInfo = this.m_iBtnInfo;
            if (this.m_ptOffset != null) {
                final int n4 = iBtnInfo[n3 + 38 + iBtnInfo[n3 + 50 - 1]];
                final Rectangle rectangle = new Rectangle(iBtnInfo[n3] - n4, iBtnInfo[n3 + 1] - n4, iBtnInfo[n3 + 2] + n4 + n4, iBtnInfo[n3 + 3] + n4 + n4);
                iBtnInfo[n3] = n - this.m_ptOffset.x;
                iBtnInfo[n3 + 1] = n2 - this.m_ptOffset.y;
                Rectangle rectangle2 = new Rectangle(iBtnInfo[n3] - n4, iBtnInfo[n3 + 1] - n4, iBtnInfo[n3 + 2] + n4 + n4, iBtnInfo[n3 + 3] + n4 + n4).union(rectangle);
                final int whichButton = this.whichButton(n, n2);
                if (this.m_iOver >= 0 && this.m_iOver != whichButton && whichButton != this.m_iBtnGrab) {
                    n3 = this.m_iOver * 50;
                    if (iBtnInfo[n3 + 50 - 1] == 2 && iBtnInfo[n3 + 5] == 0) {
                        iBtnInfo[n3 + 50 - 1] = 0;
                        this.resetImage(this.m_iOver);
                        rectangle2 = rectangle2.union(this.getBtnRect(this.m_iOver));
                    }
                }
                if (whichButton != this.m_iOver && whichButton != -1) {
                    n3 = whichButton * 50;
                    if (iBtnInfo[n3 + 50 - 1] != 2) {
                        iBtnInfo[n3 + 50 - 1] = 2;
                        this.resetImage(whichButton);
                        rectangle2 = rectangle2.union(this.getBtnRect(whichButton));
                        this.playSound(whichButton, 2);
                    }
                }
                this.m_iOver = whichButton;
                this.paintRect(rectangle2);
            }
            else {
                final int whichButton2 = this.whichButton(n, n2);
                this.mouseDown(event, n, n2);
                if (whichButton2 != this.m_iBtnGrab && iBtnInfo[n3 + 5] == 0 && iBtnInfo[n3 + 50 - 1] == 2 && this.m_iBtnGrab != this.m_iSelect) {
                    iBtnInfo[n3 + 50 - 1] = 0;
                    this.resetImage(this.m_iBtnGrab);
                    updateActionRect = this.updateActionRect(this.m_iBtnGrab, updateActionRect, 0);
                }
            }
            if (updateActionRect != null) {
                this.paintRect(updateActionRect);
            }
            if ((this.m_fExplorer4 || this.m_fNetscape4) && (iBtnInfo[n3 + 6] & 0x2) == 0x0) {
                this.SetCursor((this.m_iOver < 0) ? 0 : 12);
            }
        }
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        if (this.m_iReady >= 0) {
            Rectangle rectangle = null;
            final int whichButton = this.whichButton(n, n2);
            final int[] iBtnInfo = this.m_iBtnInfo;
            if (this.m_iOver != -1 && this.m_iOver != whichButton) {
                final int n3 = this.m_iOver * 50;
                final int n4 = n3 + 50 - 1;
                final int n5 = iBtnInfo[n4];
                if (iBtnInfo[n4] == 1 && iBtnInfo[n3 + 5] == 0) {
                    iBtnInfo[n4] = 0;
                    this.resetImage(this.m_iOver);
                    rectangle = this.updateActionRect(this.m_iOver, rectangle, 0);
                }
                if ((this.m_fExplorer4 || this.m_fNetscape4) && (iBtnInfo[n3 + 6] & 0x2) == 0x0) {
                    this.SetCursor(0);
                }
                this.m_iOver = -1;
            }
            if (whichButton < 0) {
                this.m_iOver = whichButton;
                this.showStatus("");
            }
            else if (whichButton != this.m_iOver) {
                final int n6 = whichButton * 50;
                final int n7 = n6 + 50 - 1;
                final int n8 = iBtnInfo[n7];
                final String s = this.m_sBtnStrings[whichButton * 19 + 12];
                this.showStatus((s != null) ? s : "");
                if (iBtnInfo[n7] == 0 && iBtnInfo[n6 + 5] == 0) {
                    iBtnInfo[n7] = 1;
                    this.resetImage(whichButton);
                    this.playSound(whichButton, 1);
                    rectangle = this.updateActionRect(whichButton, rectangle, 1);
                }
                if ((this.m_fExplorer4 || this.m_fNetscape4) && (iBtnInfo[n6 + 6] & 0x2) == 0x0) {
                    this.SetCursor(12);
                }
                this.m_iOver = whichButton;
            }
            if (rectangle != null) {
                this.paintRect(rectangle);
            }
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.mouseMove(event, Integer.MIN_VALUE, Integer.MIN_VALUE);
        return true;
    }
    
    public void SetCursor(final int cursor) {
        if (this.m_Frame != null) {
            this.m_Frame.setCursor(cursor);
        }
    }
    
    public void start() {
        this.paintAll();
        this.SetCursor(0);
    }
    
    public void stop() {
        System.gc();
    }
    
    public void destroy() {
        this.m_iBtnImages = null;
        this.m_iPreRendered = null;
        this.m_iBtnInfo = null;
        this.m_oPlaying = null;
        this.m_oBtnAudio = null;
        this.m_cBtnColors = null;
        this.m_sBtnStrings = null;
        this.m_sFonts = null;
        this.m_sBitmapFolder = null;
        this.m_sAudioFolder = null;
        this.m_sStartAction = null;
        this.m_Frame = null;
        this.m_iBuffer = null;
        this.m_iFlusher = null;
        this.m_ptOffset = null;
        this.m_cBgnd = null;
        this.m_ptBgndOffset = null;
        this.m_sToken = null;
        this.m_sBgnd = null;
        this.m_vBitmapNames = null;
        this.m_vBitmapImages = null;
        this.m_vStatics = null;
        this.m_vAudioNames = null;
        this.m_vAudioObjects = null;
        this.m_vJSEvals = null;
        System.gc();
    }
    
    public void run() {
        final int[] iBtnInfo = this.m_iBtnInfo;
        final MediaTracker mediaTracker = new MediaTracker(this);
        this.m_iBgnd = ((this.m_iBgndMode >= 0) ? this.getImg(this.m_sBgnd, this.m_iWidth, this.m_iHeight, true, this.m_iBgndMode == 3, mediaTracker) : -1);
        for (int i = 0; i < this.m_iButtons; ++i) {
            final int n = i * 50;
            for (int j = 0; j < 3; ++j) {
                final int n2 = iBtnInfo[n + 8 + j];
                final int n3 = iBtnInfo[n + 11 + j];
                final int n4 = (n2 > 1) ? n3 : false;
                this.m_iBtnImages[i * 3 + j] = this.getImg(this.m_sBtnStrings[i * 19 + 13 + j], iBtnInfo[n + 2] - 2 * n4, iBtnInfo[n + 3] - 2 * n4, j != 0 || iBtnInfo[n + 4] == 0, (iBtnInfo[n + 35 + j] & 0x3) == 0x3, mediaTracker);
            }
        }
        this.m_iReady = 1;
        this.paintAll();
        for (int k = 0; k < this.m_iButtons; ++k) {
            for (int l = 0; l < 3; ++l) {
                this.drawBtnImage(null, k, l, 0, null, null);
                this.drawBtnImage(null, k, l, 2, null, null);
            }
        }
        this.performAction(this.m_sStartAction);
        for (int n5 = 0; n5 < this.m_iButtons; ++n5) {
            for (int n6 = 0; n6 < 3; ++n6) {
                this.m_oBtnAudio[n5 * 9 + n6] = this.getAudio(this.m_sBtnStrings[n5 * 19 + n6]);
            }
        }
        this.paintAll();
        try {
            mediaTracker.waitForAll();
        }
        catch (Exception ex) {}
        this.paintAll();
        while (this.m_fExplorer4) {
            try {
                Thread.sleep(1000L);
            }
            catch (Exception ex2) {}
            if (this.m_iPaintMode == 1) {
                this.paintAll();
                this.m_iPaintMode = 0;
            }
        }
    }
    
    void reRender(final int n) {
        final int n2 = n * 6;
        for (int i = 0; i < 6; ++i) {
            if (this.m_iPreRendered[n2 + i] != null) {
                this.m_iPreRendered[n2 + i].flush();
                this.m_iPreRendered[n2 + i] = null;
            }
        }
    }
    
    Rectangle setAttrib(final String s, final int n, final int n2, final Rectangle rectangle, final boolean b) {
        Rectangle rectangle2 = rectangle;
        final int index = s.indexOf(42);
        for (int i = 0; i < this.m_iButtons; ++i) {
            final String s2 = this.m_sBtnStrings[i * 19 + 11];
            if ((index >= 0 && s2.regionMatches(0, s, 0, index)) || s2.equals(s)) {
                rectangle2 = this.safeUnion(rectangle2, this.getBtnRect(i));
                this.m_iBtnInfo[i * 50 + n] = n2;
                if (b) {
                    this.reRender(i);
                }
                this.resetImage(i);
                if (n == 49) {
                    final int[] iBtnInfo = this.m_iBtnInfo;
                    final int n3 = i * 50;
                    if (iBtnInfo[n3 + 7] == 0) {
                        iBtnInfo[n3 + 5] = ((n2 == 2) ? 1 : 0);
                    }
                    else if (iBtnInfo[n3 + 7] == 1) {
                        if ((n2 == 2 && this.m_iSelect >= 0 && this.m_iSelect != i) || (n2 == 0 && i == this.m_iSelect)) {
                            final int n4 = this.m_iSelect * 50;
                            iBtnInfo[n4 + 50 - 1] = 0;
                            this.resetImage(this.m_iSelect);
                            iBtnInfo[n4 + 5] = 0;
                            rectangle2 = rectangle2.union(this.getBtnRect(this.m_iSelect));
                            this.m_iSelect = -1;
                        }
                        if (n2 == 2) {
                            iBtnInfo[n3 + 5] = 1;
                            this.m_iSelect = i;
                        }
                    }
                }
            }
        }
        if (rectangle == null) {
            this.paintRect(rectangle2);
        }
        return rectangle2;
    }
    
    Rectangle setStringAttrib(final String s, final int n, final String s2, final Rectangle rectangle, final boolean b) {
        Rectangle safeUnion = rectangle;
        final int index = s.indexOf(42);
        for (int i = 0; i < this.m_iButtons; ++i) {
            final String s3 = this.m_sBtnStrings[i * 19 + 11];
            if ((index >= 0 && s3.regionMatches(0, s, 0, index)) || s3.equals(s)) {
                safeUnion = this.safeUnion(safeUnion, this.getBtnRect(i));
                this.m_sBtnStrings[i * 19 + n] = s2;
                if (b) {
                    this.reRender(i);
                }
                this.resetImage(i);
            }
        }
        if (rectangle == null) {
            this.paintRect(safeUnion);
        }
        return safeUnion;
    }
    
    public void setState(final String s, final int n) {
        this.setAttrib(s, 49, n, null, true);
    }
    
    public void setText(final String s, final int n, final String s2) {
        this.setStringAttrib(s, 3 + n, s2, null, true);
    }
    
    public void setVisible(final String s, final int n) {
        this.setAttrib(s, 4, n, null, true);
    }
    
    public int getAttrib(final String s, final int n) {
        for (int i = 0; i < this.m_iButtons; ++i) {
            if (s.equals(this.m_sBtnStrings[i * 19 + 11])) {
                return this.m_iBtnInfo[i * 50 + n];
            }
        }
        return -1;
    }
    
    public int getState(final String s) {
        return this.getAttrib(s, 49);
    }
    
    public int getVisible(final String s) {
        return this.getAttrib(s, 4);
    }
    
    public void setRect(final String s, final int n, final int n2, final int n3, final int n4) {
        for (int i = 0; i < this.m_iButtons; ++i) {
            if (s.equals(this.m_sBtnStrings[i * 19 + 11])) {
                final Rectangle btnRect = this.getBtnRect(i);
                final int[] iBtnInfo = this.m_iBtnInfo;
                final int n5 = i * 50;
                iBtnInfo[n5] = n;
                iBtnInfo[n5 + 1] = n2;
                iBtnInfo[n5 + 2] = n3;
                iBtnInfo[n5 + 3] = n4;
                this.reRender(i);
                this.paintRect(btnRect.union(this.getBtnRect(i)));
                break;
            }
        }
    }
    
    public void performAction(final String s) {
        this.paintRect(this.performAction(s, null));
    }
    
    String[] getFontList() {
        final String[] fontList = Toolkit.getDefaultToolkit().getFontList();
        final String[] array = new String[fontList.length + 1];
        for (int i = 0; i < fontList.length; ++i) {
            array[i] = fontList[i];
        }
        array[array.length - 1] = "Arial";
        return array;
    }
}
