// 
// Decompiled by Procyon v0.5.30
// 

package sexy.gui;

import java.lang.reflect.Constructor;
import sexy.util.MD5;
import java.awt.FontMetrics;
import java.awt.Color;
import java.lang.reflect.Method;
import java.io.InputStream;
import java.net.URLConnection;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.awt.Component;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
import sexy.util.AsyncJSCall;
import netscape.javascript.JSObject;
import java.awt.Graphics;
import java.util.Enumeration;
import java.awt.image.ImageObserver;
import java.awt.Point;
import java.awt.Event;
import java.awt.image.ImageProducer;
import sexy.res.PropertiesParser;
import java.util.Hashtable;
import java.awt.Font;
import java.awt.Image;
import java.util.Vector;
import sexy.res.ResLoader;
import java.applet.Applet;

public class SexyApplet extends Applet implements Runnable
{
    public int mVersion;
    public String mVariation;
    public String mBuildDate;
    public ResLoader mResLoader;
    public String mProdName;
    public Vector mDebugLines;
    public boolean mAccentedChars;
    public WidgetManager mWidgetManager;
    public int mWidth;
    public int mHeight;
    public boolean mShutdown;
    public int mFrameTime;
    String mClipboard;
    Vector mWidgetManagers;
    boolean mIsSigned;
    int mDirtyCount;
    Image mFMImage;
    boolean mInitialized;
    int mCreateImageCount;
    public boolean mDebug;
    Vector mCreatedImageVector;
    public Object mJSObject;
    boolean mStopped;
    boolean mDispFatalError;
    boolean mHasError;
    boolean mIsRunning;
    String mFatalError;
    String mFatalDetails;
    Font mFatalFont;
    Font mSmFatalFont;
    long mStopTick;
    long mLastTime;
    long mLoadedTime;
    public boolean mAppPaused;
    public boolean mPendingAppPaused;
    public boolean mNeedSendGameStart;
    public int mScoreBroadcastTime;
    public int mScoreBroadcastCounter;
    Hashtable mImageGetHash;
    Hashtable mMemoryImageContextHash;
    Vector mDuplicateImageContextVector;
    Hashtable mSpareImageHash;
    boolean mIsMac;
    int[] mFontSizeConvTable;
    int[] CORRECT_FONT_WIDTHS;
    public Hashtable mSexyFontHash;
    boolean mInStatsGroup;
    String mStatsString;
    public Hashtable mProperties;
    private static /* synthetic */ Class class$java$lang$String;
    
    public void LoadProperties() {
        final PropertiesParser propertiesParser = new PropertiesParser(this);
        propertiesParser.ParsePropertiesString(this.mResLoader.GetTextFile("properties/default.xml"));
        String parameter = this.getParameter("language");
        if (parameter == null) {
            parameter = "en";
        }
        String parameter2 = this.getParameter("country");
        if (parameter2 == null) {
            parameter2 = "US";
        }
        String parameter3 = this.getParameter("lookandfeel");
        if (parameter3 == null) {
            parameter3 = "NORMAL";
        }
        this.mAccentedChars = true;
        if (parameter.compareTo("en") != 0 || parameter2.compareTo("US") != 0 || parameter3.compareTo("NORMAL") != 0) {
            propertiesParser.ParsePropertiesString(this.mResLoader.GetTextFile("properties/prop_" + parameter + "-" + parameter2 + "_" + parameter3 + ".xml"));
        }
    }
    
    public Image createImage(final int n, final int n2) {
        ++this.mCreateImageCount;
        final Image image = super.createImage(n, n2);
        this.mCreatedImageVector.addElement(image);
        return image;
    }
    
    public Image createImage(final ImageProducer imageProducer) {
        ++this.mCreateImageCount;
        final Image image = super.createImage(imageProducer);
        this.mCreatedImageVector.addElement(image);
        return image;
    }
    
    public synchronized boolean gotFocus(final Event event, final Object o) {
        try {
            this.mWidgetManager.GotFocus();
        }
        catch (Throwable t) {
            this.CaughtThrowable(t);
        }
        return super.gotFocus(event, o);
    }
    
    public synchronized boolean lostFocus(final Event event, final Object o) {
        try {
            this.mWidgetManager.LostFocus();
        }
        catch (Throwable t) {
            this.CaughtThrowable(t);
        }
        return super.lostFocus(event, o);
    }
    
    public synchronized void Shutdown() {
        if (!this.mShutdown) {
            this.mShutdown = true;
            if (this.mResLoader != null) {
                this.mResLoader.Shutdown();
            }
            for (int i = 0; i < this.mCreatedImageVector.size(); ++i) {
                ((Image)this.mCreatedImageVector.elementAt(i)).flush();
            }
            this.mCreatedImageVector.removeAllElements();
        }
    }
    
    public SexyFont GetSexyFont(final Font font) {
        SexyFont sexyFont = this.mSexyFontHash.get(font);
        if (sexyFont == null) {
            sexyFont = new SexyFont(this, font);
            this.mSexyFontHash.put(font, sexyFont);
        }
        return sexyFont;
    }
    
    public synchronized Image CreateNewImage(final int n, final int n2) {
        final Point point = new Point(n, n2);
        final Image image = this.mSpareImageHash.get(point);
        if (image == null) {
            return this.createImage(n, n2);
        }
        this.mSpareImageHash.remove(point);
        return image;
    }
    
    public synchronized Image FindSpareImage(final int n, final int n2) {
        final Point point = new Point(n, n2);
        final Image image = this.mSpareImageHash.get(point);
        if (image == null) {
            return null;
        }
        this.mSpareImageHash.remove(point);
        return image;
    }
    
    public synchronized void FreeImage(final Image image) {
        this.mSpareImageHash.put(new Point(image.getWidth(null), image.getHeight(null)), image);
    }
    
    public boolean GetParamBoolean(final String s, final boolean b) {
        final String parameter = this.getParameter(s);
        if (parameter == null) {
            return b;
        }
        return !parameter.equalsIgnoreCase("0") && !parameter.equalsIgnoreCase("off") && !parameter.equalsIgnoreCase("no") && !parameter.equalsIgnoreCase("false");
    }
    
    public Image GetImage(final String s, final int n) {
        System.out.println("Getting image " + s + " (" + n + ")");
        final Image image = this.getImage(this.getCodeBase(), s);
        this.mImageGetHash.put(image, new Object[] { s, new Integer(n) });
        return image;
    }
    
    public Image GetImage(final String s) {
        return this.GetImage(s, 0);
    }
    
    public void SendGameStartNotification(final String s) {
        this.mNeedSendGameStart = false;
        this.SendNotification("pc_gamestart", s);
    }
    
    public void SyncShutdown() {
        this.Shutdown();
        while (this.mIsRunning) {
            try {
                Thread.sleep(20L);
            }
            catch (Exception ex) {}
        }
    }
    
    void Resize(final int mWidth, final int mHeight) {
        this.mWidth = mWidth;
        this.mHeight = mHeight;
        this.mWidgetManager.Resize(this.mWidth, this.mHeight);
        this.ResizeComponents();
        this.mWidgetManager.MarkAllDirty();
    }
    
    public void ResetUpdateTimer() {
        this.mLastTime = System.currentTimeMillis();
    }
    
    public void RequiredResoucesLoaded() {
        this.TrackerResourcesLoaded();
    }
    
    public void TrackerAppletLoaded() {
        try {
            final Applet getTrackerApplet = this.GetTrackerApplet();
            getTrackerApplet.getClass().getMethod("AppletLoaded", (Class<?>[])null).invoke(getTrackerApplet, (Object[])null);
        }
        catch (Exception ex) {}
    }
    
    public String GetString(final String s) {
        return this.mProperties.get(s);
    }
    
    public synchronized void reshape(final int n, final int n2, final int mWidth, final int mHeight) {
        try {
            super.reshape(n, n2, mWidth, mHeight);
            if (this.mInitialized) {
                this.Resize(mWidth, mHeight);
                return;
            }
            this.mWidth = mWidth;
            this.mHeight = mHeight;
        }
        catch (Throwable t) {
            this.CaughtThrowable(t);
        }
    }
    
    public String[] GetStringArray(final String s) {
        return this.mProperties.get(s);
    }
    
    public synchronized boolean mouseExit(final Event event, final int n, final int n2) {
        if (!this.mInitialized) {
            return true;
        }
        try {
            return this.mWidgetManager.mouseExit(n, n2);
        }
        catch (Throwable t) {
            this.CaughtThrowable(t);
            return true;
        }
    }
    
    public void StatsGroupEnd() {
        if (!this.mInStatsGroup) {
            this.FatalError("StatsFail", "Not in group");
            return;
        }
        this.mInStatsGroup = false;
    }
    
    Applet GetTrackerApplet() {
        final Enumeration<Applet> applets = this.getAppletContext().getApplets();
        while (applets.hasMoreElements()) {
            final Applet applet = applets.nextElement();
            if (applet.getClass().getName().equals("SexyUserTracker")) {
                return applet;
            }
        }
        return null;
    }
    
    public synchronized boolean mouseUp(final Event event, final int n, final int n2) {
        if (!this.mInitialized) {
            return true;
        }
        try {
            if ((event.modifiers & 0x4) != 0x0) {
                return this.mWidgetManager.mouseUp(n, n2, -1);
            }
            return this.mWidgetManager.mouseUp(n, n2, 1);
        }
        catch (Throwable t) {
            this.CaughtThrowable(t);
            return true;
        }
    }
    
    public void destroy() {
        System.out.println("Destroy At: " + System.currentTimeMillis());
        try {
            this.Shutdown();
        }
        catch (Throwable t) {
            this.CaughtThrowable(t);
        }
        super.destroy();
    }
    
    public void ButtonMouseEnter(final int n) {
    }
    
    public void ResizeComponents() {
    }
    
    public Font CreateSysFont(final String s, final int n, int n2) {
        if (this.mFontSizeConvTable == null) {
            final Graphics graphics = this.mFMImage.getGraphics();
            this.mFontSizeConvTable = new int[this.CORRECT_FONT_WIDTHS.length];
            int n3 = 0;
            int i = 0;
            for (int j = 0; j < this.CORRECT_FONT_WIDTHS.length; ++j) {
                while (i <= this.CORRECT_FONT_WIDTHS[j]) {
                    ++n3;
                    graphics.setFont(new Font("SansSerif", 1, n3));
                    i = graphics.getFontMetrics().stringWidth("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz ");
                }
                this.mFontSizeConvTable[j] = n3 - 1;
            }
            graphics.dispose();
        }
        if (n2 >= 0 && n2 < this.CORRECT_FONT_WIDTHS.length) {
            n2 = this.mFontSizeConvTable[n2];
        }
        return new Font(s, n, n2);
    }
    
    public SexyFont CreateFont(final String s, final int n, final int n2) {
        return this.GetSexyFont(this.CreateSysFont(s, n, n2));
    }
    
    public void StartThread() {
        if (this.mInitialized) {
            return;
        }
        this.Resize(this.mWidth, this.mHeight);
        this.mInitialized = true;
        this.mIsRunning = true;
        new Thread(this).start();
    }
    
    public synchronized boolean keyUp(final Event event, final int n) {
        try {
            return this.mWidgetManager.keyUp(n, event.shiftDown(), event.controlDown());
        }
        catch (Throwable t) {
            this.CaughtThrowable(t);
            return true;
        }
    }
    
    public synchronized void FatalError(final String mFatalError, final String mFatalDetails) {
        if (!this.mShutdown) {
            this.mHasError = true;
            this.mFatalError = mFatalError;
            this.mFatalDetails = mFatalDetails;
            this.mDispFatalError = true;
            this.repaint();
            System.out.println("FATAL ERROR: " + mFatalError);
            System.out.println("    DETAILS: " + mFatalDetails);
            this.Shutdown();
        }
    }
    
    public String ToHex16(final short n) {
        final char[] array = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        return "" + array[n >> 12 & 0xF] + array[n >> 8 & 0xF] + array[n >> 4 & 0xF] + array[n & 0xF];
    }
    
    public void FatalError(final String s) {
        this.FatalError(s, null);
    }
    
    private static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public void AppPausedHook() {
    }
    
    public void SendNotification(final String s, final String s2) {
        try {
            final String parameter = this.getParameter("JSNotify");
            final JSObject window = JSObject.getWindow((Applet)this);
            if (parameter != null && window != null) {
                window.call(parameter, new Object[] { s, (s2 != null) ? s2 : "" });
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void SendScoreBroadcastNotification() {
        final String getScoreHook = this.GetScoreHook();
        if (getScoreHook != null) {
            this.SendNotification("pc_scorebroadcast", getScoreHook);
        }
    }
    
    void StatsHeader(final String s, final char c) {
        if (!this.mInStatsGroup) {
            this.FatalError("StatsFail", "Not in group");
            return;
        }
        this.mStatsString = this.mStatsString + this.ToHex16((short)s.length()) + s;
        this.mStatsString += c;
    }
    
    public synchronized boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this.mInitialized) {
            return true;
        }
        try {
            int clickCount = event.clickCount;
            if ((event.modifiers & 0x4) != 0x0) {
                clickCount = -clickCount;
            }
            return this.mWidgetManager.mouseDown(n, n2, clickCount);
        }
        catch (Throwable t) {
            this.CaughtThrowable(t);
            return true;
        }
    }
    
    public void run() {
        try {
            this.ThreadInit();
            int n = 0;
            int n2 = 0;
            if (!this.mShutdown) {
                this.mLastTime = System.currentTimeMillis();
                int n3 = 0;
                while (!this.mShutdown) {
                    ++n;
                    final long currentTimeMillis = System.currentTimeMillis();
                    if (currentTimeMillis - this.mLastTime >= this.mFrameTime - 5 && ++n3 < 10) {
                        if (!this.mStopped) {
                            this.UpdateFrames();
                        }
                        this.mLastTime += this.mFrameTime;
                        if (currentTimeMillis - this.mLastTime > 1000L) {
                            this.mLastTime = currentTimeMillis - 1000L;
                        }
                    }
                    else {
                        n3 = 0;
                        this.DrawDirtyStuff();
                        final long n4 = System.currentTimeMillis() - this.mLastTime;
                        if (n4 < this.mFrameTime) {
                            try {
                                ++n2;
                                Thread.currentThread();
                                Thread.sleep(this.mFrameTime - n4);
                            }
                            catch (InterruptedException ex) {}
                        }
                    }
                    if (this.mStopped && System.currentTimeMillis() - this.mStopTick >= 10000L) {
                        this.Shutdown();
                    }
                }
            }
            if (this.mWidgetManagers.size() != 0) {
                System.out.println("Unfreed Widget Managers: " + this.mWidgetManagers.size());
                while (this.mWidgetManagers.size() > 0) {
                    this.RemoveWidgetManager(this.mWidgetManagers.elementAt(0));
                }
            }
        }
        catch (Throwable t) {
            this.CaughtThrowable(t);
        }
        try {
            if (this.mHasError) {
                if (!this.GetParamBoolean("noErrorPage", false)) {
                    this.RegisterErrorInfo();
                }
            }
            else if (this.GetParamBoolean("StatsUpload", false)) {
                this.StatsUpload();
            }
            this.ThreadLeave();
        }
        catch (Throwable t2) {
            t2.printStackTrace();
        }
        System.out.println("Applet thread shut down");
        this.mIsRunning = false;
    }
    
    public void init() {
        this.mProperties.put("DIALOG_BUTTON_YES", "Yes");
        this.mProperties.put("DIALOG_BUTTON_NO", "No");
        try {
            this.mScoreBroadcastTime = new Integer(this.getParameter("pc_scorebroadcast"));
            if (this.mScoreBroadcastTime > 0) {
                this.mScoreBroadcastTime *= 1000;
            }
        }
        catch (Exception ex) {}
        new AsyncJSCall(this, "AppletLoaded", null);
        try {
            this.Initialize();
            if (this.mProdName == null) {
                System.out.println("WARNING: mProdName not set");
            }
            if (this.mVersion == 0) {
                System.out.println("WARNING: mVersion not set");
            }
            if (this.mBuildDate == null) {
                System.out.println("WARNING: mBuildDate not set");
            }
            this.StartThread();
        }
        catch (Throwable t) {
            this.CaughtThrowable(t);
        }
    }
    
    public void CaughtThrowable(final Throwable t) {
        t.printStackTrace();
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        t.printStackTrace(new PrintStream(byteArrayOutputStream));
        this.FatalError("exception", new String(byteArrayOutputStream.toByteArray()));
    }
    
    public void SendGameBreakNotification(final int n, final boolean b, final String s) {
        this.SendScoreBroadcastNotification();
        if (b) {
            this.SendGameOverNotification();
        }
        String s2 = "" + n;
        if (b) {
            s2 += ",GameOver";
        }
        if (s != null && s.length() > 0) {
            s2 = s2 + "," + s;
        }
        this.SendNotification("pc_gamebreak", s2);
    }
    
    public synchronized boolean mouseMove(final Event event, final int n, final int n2) {
        if (!this.mInitialized) {
            return true;
        }
        try {
            return this.mWidgetManager.mouseMove(n, n2);
        }
        catch (Throwable t) {
            this.CaughtThrowable(t);
            return true;
        }
    }
    
    public void stop() {
        this.mStopped = true;
        this.mStopTick = System.currentTimeMillis();
    }
    
    public void SendGameOverNotification() {
        this.SendNotification("pc_gameover", this.GetScoreHook());
    }
    
    public void Initialize() {
        this.TrackerAppletLoaded();
        this.mFMImage = this.createImage(1, 1);
        this.AddWidgetManager(this.mWidgetManager = new WidgetManager(this, this));
        this.requestFocus();
    }
    
    synchronized void DrawDirtyStuff() {
        final Enumeration<WidgetManager> elements = this.mWidgetManagers.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().DrawScreen();
        }
    }
    
    public void TrackerResourcesLoaded() {
        try {
            final Applet getTrackerApplet = this.GetTrackerApplet();
            getTrackerApplet.getClass().getMethod("ResourcesLoaded", (Class<?>[])null).invoke(getTrackerApplet, (Object[])null);
        }
        catch (Exception ex) {}
    }
    
    public String URLEncode(final String s) {
        String string = s;
        for (int i = 0; i < string.length(); ++i) {
            final String s2 = " %?&+\n\r\t";
            final char[] array = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
            final char char1 = string.charAt(i);
            if (char1 <= ' ' || s2.indexOf(char1) != -1) {
                string = string.substring(0, i) + "%" + array[char1 / '\u0010'] + "" + array[char1 % '\u0010'] + string.substring(i + 1);
                i += 2;
            }
        }
        return string;
    }
    
    public synchronized void AddWidgetManager(final WidgetManager widgetManager) {
        this.mWidgetManagers.addElement(widgetManager);
    }
    
    public synchronized void UpdateFrames() {
        if (this.mPendingAppPaused != this.mAppPaused) {
            this.DoAppPause(this.mPendingAppPaused);
        }
        if (!this.mAppPaused) {
            final Enumeration<WidgetManager> elements = this.mWidgetManagers.elements();
            while (elements.hasMoreElements()) {
                elements.nextElement().UpdateFrame();
            }
            if (this.mScoreBroadcastTime > 0) {
                this.mScoreBroadcastCounter += this.mFrameTime;
                if (this.mScoreBroadcastCounter >= this.mScoreBroadcastTime) {
                    this.mScoreBroadcastCounter -= this.mScoreBroadcastTime;
                    this.SendScoreBroadcastNotification();
                }
            }
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void StatsUpload() {
        System.out.println("Raw Stats Size: " + this.mStatsString.length());
        System.out.println("Stats: " + this.mStatsString);
        try {
            final URLConnection openConnection = new URL("http://www.popcap.com/record_java_stats.php").openConnection();
            openConnection.setDoOutput(true);
            openConnection.setDoInput(true);
            openConnection.setUseCaches(false);
            final OutputStream outputStream = openConnection.getOutputStream();
            outputStream.write(("prod=" + this.URLEncode(this.mProdName)).getBytes());
            outputStream.write(("&ver=" + this.mVersion).getBytes());
            if (this.mVariation != null) {
                outputStream.write(("&variation=" + this.mVariation).getBytes());
            }
            final String parameter = this.getParameter("RandID");
            if (parameter != null) {
                outputStream.write(("&randid=" + parameter).getBytes());
            }
            outputStream.write(("&secsloaded=" + (int)(System.currentTimeMillis() - this.mLoadedTime) / 1000).getBytes());
            outputStream.write(("&stats=" + this.URLEncode(this.mStatsString)).getBytes());
            outputStream.flush();
            outputStream.close();
            final InputStream inputStream = openConnection.getInputStream();
            String line;
            while ((line = new BufferedReader(new InputStreamReader(inputStream)).readLine()) != null) {
                System.out.println("S> " + line);
            }
            inputStream.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void DrawFromStrip(final SexyGraphics sexyGraphics, final int n, final int n2, final SexyImage sexyImage, final int n3, final int n4) {
        final int getHeight = sexyImage.GetHeight();
        final SexyGraphics create = sexyGraphics.create();
        create.ClipRect(n, n2, n4, getHeight);
        create.DrawImage(sexyImage, n - n3, n2);
    }
    
    public void TrackerRecordValue(final String s, final String s2) {
        try {
            final Class[] array = { (SexyApplet.class$java$lang$String != null) ? SexyApplet.class$java$lang$String : (SexyApplet.class$java$lang$String = class$("java.lang.String")), (SexyApplet.class$java$lang$String != null) ? SexyApplet.class$java$lang$String : (SexyApplet.class$java$lang$String = class$("java.lang.String")) };
            final Applet getTrackerApplet = this.GetTrackerApplet();
            getTrackerApplet.getClass().getMethod("RecordValue", (Class<?>[])array).invoke(getTrackerApplet, s, s2);
        }
        catch (Exception ex) {}
    }
    
    public void start() {
        this.mStopped = false;
    }
    
    public String GetClipboard() {
        if (!this.mIsSigned) {
            return this.mClipboard;
        }
        try {
            final Method method = Class.forName("java.awt.Toolkit").getMethod("getSystemClipboard", (Class<?>[])null);
            final Method method2 = Class.forName("java.awt.datatransfer.Clipboard").getMethod("getContents", Class.forName("java.lang.Object"));
            final Class<?> forName = Class.forName("java.awt.datatransfer.DataFlavor");
            return (String)Class.forName("java.awt.datatransfer.Transferable").getMethod("getTransferData", forName).invoke(method2.invoke(method.invoke(this.getToolkit(), (Object[])null), this), forName.getField("stringFlavor").get(null));
        }
        catch (Throwable t) {
            return this.mClipboard;
        }
    }
    
    public synchronized void RemoveWidgetManager(final WidgetManager widgetManager) {
        if (this.mWidgetManager.mWidgets.size() > 0) {
            System.out.println("Unfreed Widgets in Manager: " + this.mWidgetManager.mWidgets.size());
        }
        this.mWidgetManagers.removeElement(widgetManager);
        widgetManager.FreeResources();
    }
    
    public Image ReGetImage(final Image image) {
        final Object[] array = this.mImageGetHash.get(image);
        final String s = (String)array[0];
        final int intValue = (int)array[1];
        if (intValue >= 3) {
            System.out.println("Failed to load image after 3 tries: " + s);
            return null;
        }
        return this.GetImage(s, intValue + 1);
    }
    
    public String GetScoreHook() {
        return null;
    }
    
    public void StatsGroupBegin(final String s) {
        if (this.mInStatsGroup) {
            this.FatalError("StatsFail", "StatsGroupBegin reentry");
            return;
        }
        this.mInStatsGroup = true;
        this.mStatsString = this.mStatsString + this.ToHex16((short)s.length()) + s;
        this.mStatsString += "G";
    }
    
    public void ThreadLeave() {
    }
    
    public synchronized boolean keyDown(final Event event, final int n) {
        try {
            return this.mWidgetManager.keyDown(n, event.shiftDown(), event.controlDown());
        }
        catch (Throwable t) {
            this.CaughtThrowable(t);
            return true;
        }
    }
    
    public void ButtonMouseLeave(final int n) {
    }
    
    public String ToHex32(final int n) {
        final char[] array = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        return "" + array[n >> 28 & 0xF] + array[n >> 24 & 0xF] + array[n >> 20 & 0xF] + array[n >> 16 & 0xF] + array[n >> 12 & 0xF] + array[n >> 8 & 0xF] + array[n >> 4 & 0xF] + array[n & 0xF];
    }
    
    public SexyApplet() {
        this.mDebugLines = new Vector();
        this.mShutdown = false;
        this.mFrameTime = 20;
        this.mClipboard = "";
        this.mWidgetManagers = new Vector();
        this.mIsSigned = false;
        this.mCreatedImageVector = new Vector();
        this.mAppPaused = false;
        this.mPendingAppPaused = false;
        this.mNeedSendGameStart = false;
        this.mScoreBroadcastTime = 0;
        this.mScoreBroadcastCounter = 0;
        this.mImageGetHash = new Hashtable();
        this.mMemoryImageContextHash = new Hashtable();
        this.mDuplicateImageContextVector = new Vector();
        this.mSpareImageHash = new Hashtable();
        this.CORRECT_FONT_WIDTHS = new int[] { 0, 60, 60, 101, 123, 163, 194, 222, 254, 300, 327, 353, 372, 418, 456, 475, 518, 526, 586, 620, 632, 669, 698, 743, 770, 798, 844, 883, 901, 934, 964, 987, 1018, 1056, 1095, 1107, 1153, 1200, 1216, 1253, 1273, 1319, 1351, 1381, 1415, 1436, 1469, 1512, 1546, 1574, 1607, 1631, 1675, 1694, 1732, 1772, 1797, 1833, 1856, 1893, 1930, 1953, 1988, 2019, 2060, 2089, 2114, 2149, 2186, 2216, 2253, 2271, 2314 };
        this.mSexyFontHash = new Hashtable();
        this.mStatsString = "";
        this.mProperties = new Hashtable();
        this.mLoadedTime = System.currentTimeMillis();
        this.mSmFatalFont = new Font("SansSerif", 0, 14);
        this.mFatalFont = new Font("SansSerif", 0, 24);
        try {
            this.mDebug = (new Integer(this.getParameter("debug")) != 0);
        }
        catch (Exception ex) {}
    }
    
    public void ThreadInit() {
    }
    
    public void paint(final Graphics graphics) {
        synchronized (this) {
            if (this.mDispFatalError) {
                try {
                    graphics.setColor(new Color(255, 0, 0));
                    graphics.fillRect(0, 0, this.mWidth, this.mHeight);
                    graphics.setColor(new Color(255, 255, 255));
                    graphics.fillRect(4, 4, this.mWidth - 8, this.mHeight - 8);
                    boolean b = false;
                    graphics.setFont(this.mFatalFont);
                    final FontMetrics fontMetrics = graphics.getFontMetrics();
                    String mFatalError;
                    String mFatalDetails;
                    if (this.mFatalError.equalsIgnoreCase("loading")) {
                        mFatalError = "Loading Error";
                        mFatalDetails = "Not all required resources could be loaded";
                    }
                    else if (this.mFatalError.equalsIgnoreCase("exception")) {
                        mFatalError = "Fatal Error";
                        mFatalDetails = "An unrecoverable error has occured";
                    }
                    else if (this.mFatalError.equalsIgnoreCase("version")) {
                        mFatalError = "Invalid Version";
                        mFatalDetails = "A newer version of this applet is required";
                        b = true;
                    }
                    else {
                        mFatalError = this.mFatalError;
                        mFatalDetails = this.mFatalDetails;
                    }
                    graphics.setColor(new Color(128, 0, 0));
                    graphics.drawString(mFatalError, Math.max((this.mWidth - fontMetrics.stringWidth(mFatalError)) / 2, 6), 64);
                    graphics.setFont(this.mSmFatalFont);
                    final FontMetrics fontMetrics2 = graphics.getFontMetrics();
                    graphics.drawString(mFatalDetails, Math.max((this.mWidth - fontMetrics2.stringWidth(mFatalDetails)) / 2, 6), this.mHeight / 2);
                    String s;
                    if (b) {
                        s = "Please close all browser windows before trying again";
                    }
                    else {
                        s = "Please refresh the page and try again";
                    }
                    graphics.drawString(s, (this.mWidth - fontMetrics2.stringWidth(s)) / 2, this.mHeight - 48);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            else {
                this.mWidgetManager.Draw(graphics);
            }
        }
    }
    
    void RegisterErrorInfo() {
        try {
            String parameter = this.getParameter("errorURL");
            if (parameter == null) {
                parameter = "http://www.popcap.com/err_main.php";
            }
            String s;
            if (parameter.indexOf(63) == -1) {
                s = parameter + "?";
            }
            else {
                s = parameter + "&";
            }
            String s2 = "err=" + this.URLEncode(this.mFatalError);
            if (this.mProdName != null) {
                s2 = s2 + "&prod=" + this.URLEncode(this.mProdName);
            }
            if (this.mVersion != 0) {
                s2 = s2 + "&ver=" + this.mVersion;
            }
            this.getAppletContext().showDocument(new URL(s + s2));
            final URLConnection openConnection = new URL("http://www.popcap.com/err_record.php").openConnection();
            openConnection.setDoOutput(true);
            openConnection.setDoInput(true);
            openConnection.setUseCaches(false);
            final OutputStream outputStream = openConnection.getOutputStream();
            outputStream.write(s2.getBytes());
            if (this.mBuildDate != null) {
                outputStream.write(("&buildDate=" + this.URLEncode(this.mBuildDate)).getBytes());
            }
            if (this.mFatalDetails != null) {
                outputStream.write(("&det=" + this.URLEncode(this.mFatalDetails)).getBytes());
            }
            String concat = "";
            for (int i = 0; i < this.mDebugLines.size(); ++i) {
                concat = concat.concat((String)this.mDebugLines.elementAt(i) + "\r\n");
            }
            outputStream.write(("&debugLines=" + this.URLEncode(concat)).getBytes());
            outputStream.flush();
            outputStream.close();
            final InputStream inputStream = openConnection.getInputStream();
            String line;
            while ((line = new BufferedReader(new InputStreamReader(inputStream)).readLine()) != null) {
                System.out.println("> " + line);
            }
            inputStream.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void DoAppPause(final boolean mAppPaused) {
        if (this.mAppPaused == mAppPaused) {
            return;
        }
        if (!(this.mAppPaused = mAppPaused)) {}
        this.AppPausedHook();
    }
    
    public void DrawYRot(final SexyGraphics sexyGraphics, final int n, final int n2, final int n3, final int n4, final int[] array, final int[] array2, final int n5, final int n6, final double n7) {
        final SexyImage sexyImage = new SexyImage();
        sexyImage.Create(n3, n4);
        sexyImage.SetImageMode(true, true);
        final int[] getBits = sexyImage.GetBits();
        for (int i = 0; i < n3 * n4; ++i) {
            getBits[i] = 0;
        }
        for (int j = 0; j < n3; ++j) {
            double cos = Math.cos(n7);
            if (Math.abs(cos) < 1.0E-5) {
                cos = 1.0E-5;
            }
            double n8 = (j - n3 / 2) / cos;
            final int n9 = n6 + (int)(Math.sin(n7) * n8 / 4.0);
            int n10;
            if ((n6 & 0x1) == 0x0) {
                n10 = (n9 & 0xFFFFFFFE);
            }
            else {
                n10 = (n9 | 0x1);
            }
            int[] array3 = array;
            if (cos < 0.0) {
                array3 = array2;
                n8 = -n8;
            }
            final double n11 = n8 + n5 / 2;
            if (n11 >= 0.0 && n11 < n5) {
                int n12 = (n4 - n10 - 1) / 2;
                int n13 = n12 + n10 - 1;
                float n14 = 0.05f;
                final float n15 = n6 / n10;
                if (n12 < 0) {
                    n14 -= n12 * n15;
                    n12 = 0;
                }
                if (n13 >= n4) {
                    n13 = n4 - 1;
                }
                final int n16 = (int)n11;
                for (int k = n12; k <= n13; ++k) {
                    getBits[k * n3 + j] = array3[(int)n14 * n5 + n16];
                    n14 += n15;
                }
            }
        }
        sexyImage.BitsChanged();
        sexyGraphics.DrawImage(sexyImage, n, n2);
    }
    
    public void WriteDebug(final String s) {
        System.out.println(s);
        this.mDebugLines.addElement(s);
    }
    
    public void PopcapNotify(final String s, final String s2) {
        if (s.equals("pc_pause")) {
            if (s2.equals("1")) {
                this.mPendingAppPaused = true;
                return;
            }
            if (s2.equals("0")) {
                this.mPendingAppPaused = false;
            }
        }
    }
    
    protected void finalize() throws Throwable {
        System.out.println("Applet finalized");
    }
    
    public boolean CheckHost() {
        boolean b = false;
        boolean b2 = true;
        final String parameter = this.getParameter("hosterr");
        final String parameter2 = this.getParameter("hosterrhash");
        if (parameter != null && parameter2 != null && parameter2.equals(new MD5(parameter + "SACHH").GetDigestString())) {
            b2 = false;
            final String lowerCase = this.getDocumentBase().getHost().toLowerCase();
            int n = -1;
            while (true) {
                String s = "";
                int length = -1;
                boolean b3 = false;
                for (int i = 0; i <= n; ++i) {
                    final int index = lowerCase.indexOf(".", length + 1);
                    if (index != -1) {
                        if (i != n) {
                            s = s + lowerCase.substring(length + 1, index) + ".";
                        }
                        length = index;
                    }
                    else {
                        if (length == lowerCase.length()) {
                            b3 = true;
                        }
                        length = lowerCase.length();
                    }
                }
                if (b3) {
                    break;
                }
                if (n != -1) {
                    s += "*";
                }
                if (length != lowerCase.length()) {
                    if (n != -1) {
                        s += ".";
                    }
                    s += lowerCase.substring(length + 1);
                }
                final MD5 md5 = new MD5(s + "SACHH");
                int n2 = 1;
                String parameter3;
                do {
                    parameter3 = this.getParameter("host" + n2++);
                    if (parameter3 != null && parameter3.equals(md5.GetDigestString())) {
                        b = true;
                        break;
                    }
                } while (parameter3 != null);
                ++n;
            }
            if (!b) {
                System.out.println("HOST NOT VALID: " + lowerCase);
                try {
                    this.getAppletContext().showDocument(new URL(parameter), "_top");
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        if (b2) {
            try {
                this.getAppletContext().showDocument(new URL("http://www.popcap.com"), "_top");
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
            }
            System.out.println("INVALID HOST VALIDATOR ERROR URL");
        }
        if (!b) {
            this.Shutdown();
        }
        return b;
    }
    
    public void StatsValue(final String s) {
        this.StatsHeader(s, 'E');
    }
    
    public void StatsValue(final String s, final boolean b) {
        this.StatsHeader(s, 'B');
        if (b) {
            this.mStatsString += "1";
            return;
        }
        this.mStatsString += "0";
    }
    
    public void StatsValue(final String s, final int n) {
        this.StatsHeader(s, 'I');
        this.mStatsString += this.ToHex32(n);
    }
    
    public void StatsValue(final String s, final String s2) {
        this.StatsHeader(s, 'S');
        this.mStatsString = this.mStatsString + this.ToHex16((short)s2.length()) + s2;
    }
    
    public synchronized boolean mouseDrag(final Event event, final int n, final int n2) {
        if (!this.mInitialized) {
            return true;
        }
        try {
            return this.mWidgetManager.mouseDrag(n, n2);
        }
        catch (Throwable t) {
            this.CaughtThrowable(t);
            return true;
        }
    }
    
    public FontMetrics GetFontMetrics(final Font font) {
        final Graphics graphics = this.mFMImage.getGraphics();
        graphics.setFont(font);
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        graphics.dispose();
        return fontMetrics;
    }
    
    public void CopyToClipboard(final String mClipboard) {
        this.mClipboard = mClipboard;
        if (!this.mIsSigned) {
            return;
        }
        try {
            final Method method = Class.forName("java.awt.Toolkit").getMethod("getSystemClipboard", (Class<?>[])null);
            final Constructor<?> constructor = Class.forName("java.awt.datatransfer.StringSelection").getConstructor(Class.forName("java.lang.String"));
            final Method method2 = Class.forName("java.awt.datatransfer.Clipboard").getMethod("setContents", Class.forName("java.awt.datatransfer.Transferable"), Class.forName("java.awt.datatransfer.ClipboardOwner"));
            final Object invoke = method.invoke(this.getToolkit(), (Object[])null);
            final Object instance = constructor.newInstance(mClipboard);
            method2.invoke(invoke, instance, instance);
        }
        catch (Throwable t) {}
    }
}
