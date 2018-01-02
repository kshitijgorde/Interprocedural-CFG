import java.awt.LayoutManager;
import java.io.DataInputStream;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.image.ImageObserver;
import netscape.javascript.JSObject;
import java.util.StringTokenizer;
import java.util.Enumeration;
import java.awt.Font;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;
import java.net.URL;
import java.awt.Component;
import java.awt.MediaTracker;
import java.util.Vector;
import java.util.Hashtable;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Event;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class diputab extends Applet implements Runnable
{
    private boolean iconsReady;
    private static final String optionsHex = " backgroundcolor foregroundcolor highcolor lowcolor shadecolor textcolor selectedcolor mouseovercolor underlinecolor tipcolor tipbackgroundcolor ";
    private static final String optionsInt = " mouseover underline marginleft marginright baseline tabheight indent overlap connectlineleft connectlineright textup textdown textleft textright imageleft tipwait executelinks showstatus scrollbuttonsize unitdivider blockdivider scrollspeed scrollbarhorizontaldivider asyncload externalhandlers ";
    private boolean imagesLoaded;
    private int scrollSlideToCoord;
    private Thread scrollThread;
    private int scrollHandler;
    private int scrollHorValue;
    private volatile int scrollHorMax;
    private boolean initialized;
    private int appletWidth;
    private int appletHeight;
    private volatile Thread tipThread;
    private String tipKey;
    private Event tipEvent;
    private boolean tablesLoaded;
    private String recordSeparator;
    private String fieldSeparator;
    private volatile Image buf2xImage;
    private volatile Graphics buf2x;
    private volatile Object dbLock;
    private final Hashtable db;
    private volatile String entrySelected;
    private volatile String entryMouseOver;
    private volatile Image bgImage;
    private volatile Graphics bg;
    private volatile Graphics buf;
    private volatile Image bufImage;
    
    private Vector DBGetRecord(final String s, final String s2) {
        try {
            final Vector<Vector> vector = this.db.get(s);
            for (int i = 0; i < vector.size(); ++i) {
                final Vector<Object> vector2 = vector.elementAt(i);
                if (vector2.elementAt(0).equals(s2)) {
                    return vector2;
                }
            }
            return null;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public void stop() {
        this.graphicsRelease();
    }
    
    private void graphicsRelease() {
        try {
            this.bg.dispose();
        }
        catch (Exception ex) {}
        try {
            this.buf.dispose();
        }
        catch (Exception ex2) {}
        try {
            this.buf2x.dispose();
        }
        catch (Exception ex3) {}
        this.bgImage = null;
        this.bg = null;
        this.bufImage = null;
        this.buf = null;
        this.buf2xImage = null;
        this.buf2x = null;
    }
    
    public void graphicsCreate() {
        final int intValue = (int)this.DBGet("options", "marginleft");
        final int intValue2 = (int)this.DBGet("options", "marginright");
        try {
            this.bufImage = this.createImage(this.appletWidth - (intValue + intValue2), this.appletHeight);
            this.buf = this.bufImage.getGraphics();
            this.buf2xImage = this.createImage(this.appletWidth, this.appletHeight);
            this.buf2x = this.buf2xImage.getGraphics();
            this.bgImage = this.createImage(this.appletWidth, this.appletHeight);
            this.bg = this.bgImage.getGraphics();
        }
        catch (Exception ex) {
            this.graphicsRelease();
        }
    }
    
    private boolean DBSet(final String s, final String s2, final int n, final Object o) {
        try {
            this.db.get(s).get(s2).setElementAt(o, n);
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    private void DBSet(final String s, final Object o, final Object o2) {
        try {
            this.db.get(s).put(o, o2);
        }
        catch (Exception ex) {}
    }
    
    protected void postProcess(final String s, final String s2) {
        if (s.equals("images")) {
            final Vector stringToTable = this.parseStringToTable(s2, this.recordSeparator, this.fieldSeparator, 2);
            if (stringToTable.size() == 0) {
                return;
            }
            final MediaTracker mediaTracker = new MediaTracker(this);
            final Hashtable<String, Image> hashtable = this.db.get("imagesraw");
            for (int i = 0; i < stringToTable.size(); ++i) {
                final String s3 = stringToTable.elementAt(i).elementAt(0);
                final String s4 = stringToTable.elementAt(i).elementAt(1);
                try {
                    final String string = new URL(this.getCodeBase(), s4).toString();
                    if (!hashtable.containsKey(string)) {
                        final Image image = this.getImage(new URL(string));
                        hashtable.put(string, image);
                        mediaTracker.addImage(image, 0);
                    }
                }
                catch (Exception ex) {
                    this.printMessage("error while parsing images");
                    this.printMessage("key: " + s3);
                    this.printMessage("url: " + s4);
                    this.printMessage(ex.toString());
                }
            }
            try {
                mediaTracker.waitForAll();
            }
            catch (Exception ex8) {}
            final Object[] errorsAny = mediaTracker.getErrorsAny();
            if (errorsAny != null) {
                for (int j = 0; j < errorsAny.length; ++j) {
                    final Image image2 = (Image)errorsAny[j];
                    final Enumeration<String> keys = hashtable.keys();
                    while (keys.hasMoreElements()) {
                        final String s5 = keys.nextElement();
                        if (hashtable.get(s5).equals(image2)) {
                            hashtable.remove(s5);
                            this.printMessage("error loading image " + s5);
                        }
                    }
                }
            }
            final MediaTracker mediaTracker2 = new MediaTracker(this);
            final Hashtable<String, Image> hashtable2 = new Hashtable<String, Image>();
            for (int k = 0; k < stringToTable.size(); ++k) {
                final Vector<String> vector = stringToTable.elementAt(k);
                final String s6 = vector.elementAt(0);
                final String s7 = vector.elementAt(1);
                try {
                    final Image image3 = (Image)this.DBGet("imagesraw", new URL(this.getCodeBase(), s7).toString());
                    if (image3 != null) {
                        if (vector.size() > 2) {
                            final Image image4 = this.createImage(new FilteredImageSource(image3.getSource(), new CropImageFilter(Integer.parseInt(vector.elementAt(2)), Integer.parseInt(vector.elementAt(3)), Integer.parseInt(vector.elementAt(4)), Integer.parseInt(vector.elementAt(5)))));
                            mediaTracker2.addImage(image4, 0);
                            hashtable2.put(s6, image4);
                        }
                        else {
                            hashtable2.put(s6, image3);
                        }
                    }
                }
                catch (Exception ex2) {
                    this.printMessage("error while parsing images");
                    this.printMessage("key: " + s6);
                    this.printMessage("url: " + s7);
                    this.printMessage(ex2.toString());
                }
            }
            try {
                mediaTracker2.waitForAll();
            }
            catch (Exception ex3) {
                this.printMessage("error while parsing images");
                this.printMessage(ex3.toString());
            }
            this.db.put("images", hashtable2);
        }
        if (s.equals("fonts")) {
            final Vector stringToTable2 = this.parseStringToTable(s2, this.recordSeparator, this.fieldSeparator, 4);
            if (stringToTable2.size() == 0) {
                return;
            }
            for (int l = 0; l < stringToTable2.size(); ++l) {
                final String s8 = stringToTable2.elementAt(l).elementAt(0);
                try {
                    this.DBSet("fonts", s8, new Font(stringToTable2.elementAt(l).elementAt(1).trim(), Integer.parseInt(stringToTable2.elementAt(l).elementAt(2)), Integer.parseInt(stringToTable2.elementAt(l).elementAt(3))));
                }
                catch (Exception ex4) {
                    this.printMessage("error while parsing fonts");
                    this.printMessage("key: " + s8);
                    this.printMessage(ex4.toString());
                }
            }
        }
        if (s.equals("options")) {
            final Vector stringToTable3 = this.parseStringToTable(s2, this.recordSeparator, this.fieldSeparator, 2);
            if (stringToTable3.size() == 0) {
                return;
            }
            for (int n = 0; n < stringToTable3.size(); ++n) {
                final String s9 = stringToTable3.elementAt(n).elementAt(0);
                String s10 = stringToTable3.elementAt(n).elementAt(1);
                try {
                    if (" backgroundcolor foregroundcolor highcolor lowcolor shadecolor textcolor selectedcolor mouseovercolor underlinecolor tipcolor tipbackgroundcolor ".indexOf(" " + s9 + " ") != -1) {
                        s10 = s10.substring(s10.lastIndexOf("x") + 1);
                        s10 = s10.substring(s10.lastIndexOf("X") + 1);
                        this.DBSet("options", s9, new Integer(Integer.parseInt(s10, 16)));
                    }
                    else if (" mouseover underline marginleft marginright baseline tabheight indent overlap connectlineleft connectlineright textup textdown textleft textright imageleft tipwait executelinks showstatus scrollbuttonsize unitdivider blockdivider scrollspeed scrollbarhorizontaldivider asyncload externalhandlers ".indexOf(" " + s9 + " ") != -1) {
                        this.DBSet("options", s9, new Integer(Integer.parseInt(s10)));
                    }
                    else {
                        this.DBSet("options", s9, s10);
                    }
                }
                catch (Exception ex5) {
                    this.printMessage("error while parsing options");
                    this.printMessage("key: " + s9);
                    this.printMessage("value: " + s10);
                    this.printMessage(ex5.toString());
                }
            }
        }
        if (s.equals("entries")) {
            final Vector stringToTable4 = this.parseStringToTable(s2, this.recordSeparator, this.fieldSeparator, 9);
            if (stringToTable4.size() == 0) {
                return;
            }
            for (int n2 = 0; n2 < stringToTable4.size(); ++n2) {
                try {
                    final Vector<String> vector2 = stringToTable4.elementAt(n2);
                    final String s11 = vector2.elementAt(0);
                    for (int n3 = 1; n3 < vector2.size(); ++n3) {
                        if (vector2.elementAt(n3) == null) {
                            vector2.setElementAt("", n3);
                        }
                    }
                    final String trim = vector2.elementAt(3).trim();
                    final String trim2 = vector2.elementAt(4).trim();
                    String trim3 = vector2.elementAt(5).trim();
                    if (trim3.length() < 1) {
                        trim3 = "defaultfont";
                    }
                    vector2.setElementAt(s11, 0);
                    vector2.setElementAt(trim, 3);
                    vector2.setElementAt(trim2, 4);
                    vector2.setElementAt(trim3, 5);
                }
                catch (Exception ex9) {}
            }
            this.DBAdd("entries", stringToTable4);
        }
        if (s.equals("internalhandlers")) {
            final Vector stringToTable5 = this.parseStringToTable(s2, this.recordSeparator, this.fieldSeparator, 4);
            if (stringToTable5.size() == 0) {
                return;
            }
            for (int n4 = stringToTable5.size() - 1; n4 >= 0; --n4) {
                try {
                    final Vector<String> vector3 = stringToTable5.elementAt(n4);
                    String lowerCase = vector3.elementAt(1).trim().toLowerCase();
                    if (lowerCase.length() < 1) {
                        lowerCase = "0";
                    }
                    final Integer n5 = new Integer(Integer.parseInt(lowerCase));
                    final String trim4 = vector3.elementAt(2).trim();
                    vector3.setElementAt((String)n5, 1);
                    vector3.setElementAt(trim4, 2);
                }
                catch (Exception ex6) {
                    stringToTable5.removeElementAt(n4);
                    this.printMessage("error while parsing internal handlers");
                    this.printMessage(ex6.toString());
                }
            }
            this.DBAdd("internalhandlers", stringToTable5);
        }
        if (s.equals("links")) {
            final Vector stringToTable6 = this.parseStringToTable(s2, this.recordSeparator, this.fieldSeparator, 3);
            if (stringToTable6.size() == 0) {
                return;
            }
            this.DBAdd("links", stringToTable6);
        }
        if (s.equals("selected") || s.equals("expanded") || s.equals("contracted") || s.equals("externalhandlers")) {
            final Vector stringToTable7 = this.parseStringToTable(s2, this.recordSeparator, this.fieldSeparator, 2);
            if (stringToTable7.size() == 0) {
                return;
            }
            for (int n6 = 0; n6 < stringToTable7.size(); ++n6) {
                final Vector<String> vector4 = stringToTable7.elementAt(n6);
                try {
                    this.DBSet(s, vector4.elementAt(0), vector4.elementAt(1).trim());
                }
                catch (Exception ex7) {
                    this.printMessage("error while parsing " + s2);
                    this.printMessage(ex7.toString());
                }
            }
        }
    }
    
    protected void externalHandlersEnable() {
        if ((int)this.DBGet("options", "externalhandlers") == 0) {
            return;
        }
        try {
            Class.forName("netscape.javascript.JSObject");
            this.DBSet("options", "externalhandlers", new Integer(1));
        }
        catch (Exception ex) {
            this.DBSet("options", "externalhandlers", new Integer(0));
        }
    }
    
    protected void handler(final String s, final String s2) {
        String substring = (String)this.DBGet(s2, s);
        if (substring != null) {
            if (substring.startsWith("++")) {
                substring = substring.substring(2);
            }
            else {
                this.db.get(s2).remove(s);
            }
            final StringTokenizer stringTokenizer = new StringTokenizer(substring.trim(), " \t\n\r", false);
            if (stringTokenizer.hasMoreElements()) {
                this.tabsCreateImage();
                this.repaint();
            }
            while (stringTokenizer.hasMoreElements()) {
                final String trim = ((String)stringTokenizer.nextElement()).trim();
                if (trim.length() > 0) {
                    if ((int)this.DBGet("options", "externalhandlers") == 1 && this.DBContainsKey("externalhandlers", trim)) {
                        final String s3 = (String)this.DBGet("externalhandlers", trim);
                        try {
                            JSObject.getWindow((Applet)this).eval(s3);
                        }
                        catch (Exception ex) {
                            this.printMessage("error during external handler [" + trim + "]");
                            this.printMessage(ex.toString());
                        }
                    }
                    else if (!this.DBContainsKey("internalhandlers", trim)) {
                        this.printMessage("error, internal handler [" + trim + "] does not exist");
                    }
                    else {
                        final int intValue = (int)this.DBGet("internalhandlers", trim, 1);
                        final String s4 = (String)this.DBGet("internalhandlers", trim, 2);
                        try {
                            if (intValue == 0) {
                                this.addRecords(s4, "url:" + (String)this.DBGet("internalhandlers", trim, 3));
                            }
                            if (intValue == 1) {
                                String s5 = this.recordSeparator;
                                final Vector<Object> vector = this.db.get("internalhandlers").get(trim);
                                for (int i = 3; i < vector.size(); ++i) {
                                    s5 += vector.elementAt(i);
                                    if (i < vector.size() - 1) {
                                        s5 += this.fieldSeparator;
                                    }
                                }
                                this.addRecords(s4, s5);
                            }
                            if (intValue == 2) {
                                final String s6 = (String)this.DBGet("internalhandlers", trim, 3);
                                final String s7 = (String)this.DBGet("internalhandlers", trim, 4);
                                final String s8 = (String)this.DBGet("internalhandlers", trim, 5);
                                int int1;
                                try {
                                    int1 = Integer.parseInt(s8);
                                }
                                catch (Exception ex3) {
                                    int1 = 1;
                                }
                                this.setField(s4, s6, int1, s7);
                            }
                            if (intValue == 3) {
                                this.removeRecords(s4, this.recordSeparator + (String)this.DBGet("internalhandlers", trim, 3));
                            }
                            if (intValue != 4) {
                                continue;
                            }
                            this.setSelected((String)this.DBGet("internalhandlers", trim, 3));
                        }
                        catch (Exception ex2) {
                            this.printMessage("error during internal handler [" + trim + "]");
                            this.printMessage(ex2.toString());
                        }
                    }
                }
            }
        }
    }
    
    protected void startAsync() {
        if (!this.imagesLoaded) {
            final String parameter = this.getParameter("images");
            if (parameter != null) {
                this.tablesLoad("images", parameter);
            }
            this.imagesLoaded = true;
        }
        this.backgroundUpdate();
        this.tabsCreate();
        this.tabsCreateImage();
        this.externalHandlersEnable();
        this.initialized = true;
        this.repaint();
    }
    
    private void popupThread() {
        final int intValue = (int)this.DBGet("options", "tipwait");
        final Event tipEvent = this.tipEvent;
        final Event tipEvent2 = this.tipEvent;
        Event tipEvent3;
        Event tipEvent4;
        do {
            tipEvent3 = this.tipEvent;
            try {
                Thread.sleep(intValue);
            }
            catch (Exception ex) {
                return;
            }
            tipEvent4 = this.tipEvent;
        } while (tipEvent3.x != tipEvent4.x || tipEvent3.y != tipEvent4.y);
        if (tipEvent4.x == -1) {
            this.tabsCreateImage();
            this.repaint();
            return;
        }
        final String tipKey = this.tipKey;
        try {
            final String s = new String();
            String s2;
            if (tipKey.equals(this.entrySelected)) {
                s2 = (String)this.DBGet("entries", tipKey, 7);
            }
            else {
                s2 = (String)this.DBGet("entries", tipKey, 6);
            }
            if (s2.length() < 1) {
                return;
            }
            if (s2.equals("auto") && !tipKey.equals(this.entrySelected)) {
                this.entrySelected = tipKey;
                this.tabsCreateImage();
                this.repaint();
                return;
            }
            this.tabsCreateImage();
            final int width = this.bufImage.getWidth(this);
            final int height = this.bufImage.getHeight(this);
            final Font font = (Font)this.DBGet("fonts", (String)this.DBGet("options", "tipfontprofile"));
            int size = font.getSize();
            this.buf.setFont(font);
            FontMetrics fontMetrics = this.buf.getFontMetrics();
            final int n = fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent();
            int n2 = fontMetrics.stringWidth(s2);
            final int intValue2 = (int)this.DBGet("options", "scrollbuttonsize");
            while (width - 6 < n2) {
                --size;
                this.buf.setFont(new Font(font.getName(), font.getStyle(), size));
                fontMetrics = this.buf.getFontMetrics();
                n2 = fontMetrics.stringWidth(s2);
                if (size >= 8) {
                    continue;
                }
                break;
            }
            int n3 = tipEvent4.x + 15;
            final int width2 = this.bufImage.getWidth(this);
            final int intValue3 = (int)this.DBGet("options", "scrollbarhorizontaldivider");
            int n4 = (width2 / intValue3 < intValue2 * 3) ? (intValue2 * 3) : (width2 / intValue3);
            if (intValue3 == 1) {
                n4 = 0;
            }
            if (n3 + n2 > width - n4) {
                n3 = width - n2 - n4 - 5;
            }
            int i;
            for (i = tipEvent4.y; i > height - n - 2; --i) {}
            final int maxAscent = fontMetrics.getMaxAscent();
            this.buf.setColor(new Color((int)this.DBGet("options", "tipbackgroundcolor")));
            this.buf.fillRect(n3 - 2, i, n2 + 4, n);
            this.buf.setColor(new Color(0));
            this.buf.drawRect(n3 - 2, i, n2 + 4, n);
            this.buf.setColor(new Color((int)this.DBGet("options", "tipcolor")));
            this.buf.drawString(s2, n3, i + maxAscent);
            final int intValue4 = (int)this.DBGet("options", "marginleft");
            this.buf2x.drawImage(this.bgImage, 0, 0, this);
            this.buf2x.drawImage(this.bufImage, intValue4, 0, this);
            this.scrollPaint();
            this.repaint();
        }
        catch (Exception ex2) {}
    }
    
    private boolean scrollUpdate() {
        boolean b = false;
        (int)this.DBGet("options", "marginleft");
        final int intValue = (int)this.DBGet("options", "scrollbuttonsize");
        final int width = this.getImage("horizontalbubble").getWidth(this);
        (int)this.DBGet("options", "baseline");
        final String s = (String)this.DBGet("options", "position");
        final int width2 = this.bufImage.getWidth(this);
        this.bufImage.getHeight(this);
        final int intValue2 = (int)this.DBGet("options", "scrollbarhorizontaldivider");
        final int n = ((width2 / intValue2 < intValue * 3) ? (intValue * 3) : (width2 / intValue2)) - intValue * 2;
        if (!s.equals("top")) {}
        if (this.scrollHorMax != 0) {
            final int n2 = this.scrollHorValue * (n - width) / this.scrollHorMax;
        }
        final int intValue3 = (int)this.DBGet("options", "unitdivider");
        final int intValue4 = (int)this.DBGet("options", "blockdivider");
        int n3 = width2 / intValue3;
        int n4 = width2 / intValue4;
        if (n3 == 0) {
            n3 = 1;
        }
        if (n4 == 0) {
            n4 = 1;
        }
        switch (this.scrollHandler) {
            case 20: {
                this.scrollHorValue -= n3;
                break;
            }
            case 22: {
                this.scrollHorValue += n3;
                break;
            }
        }
        if (this.scrollHandler == 21) {
            final int n5 = this.scrollHorMax * (this.scrollSlideToCoord / (n - width));
            if (n5 == this.scrollHorValue) {
                return true;
            }
            if (n5 > this.scrollHorValue) {
                this.scrollHorValue += n4;
                if (n5 < this.scrollHorValue) {
                    this.scrollHorValue = n5;
                    b = true;
                }
            }
            else {
                this.scrollHorValue -= n4;
                if (n5 > this.scrollHorValue) {
                    this.scrollHorValue = n5;
                    b = true;
                }
            }
        }
        if (this.scrollHorValue > this.scrollHorMax) {
            this.scrollHorValue = this.scrollHorMax;
        }
        if (this.scrollHorValue < 0) {
            this.scrollHorValue = 0;
        }
        this.tabsCreateImage();
        this.repaint();
        return b;
    }
    
    private void scrollThread() {
        final int intValue = (int)this.DBGet("options", "scrollspeed");
        final int scrollHandler = this.scrollHandler;
        this.scrollUpdate();
        while (true) {
            try {
                Thread.sleep(intValue);
            }
            catch (Exception ex) {
                return;
            }
            if (scrollHandler != this.scrollHandler) {
                if (this.scrollHandler == 0) {
                    this.scrollUpdate();
                }
                return;
            }
            if (this.scrollUpdate()) {
                return;
            }
        }
    }
    
    private void scrollPaint() {
        final int intValue = (int)this.DBGet("options", "marginleft");
        final int intValue2 = (int)this.DBGet("options", "scrollbuttonsize");
        final int width = this.getImage("horizontalbubble").getWidth(this);
        final int intValue3 = (int)this.DBGet("options", "baseline");
        final String s = (String)this.DBGet("options", "position");
        final int width2 = this.bufImage.getWidth(this);
        final int height = this.bufImage.getHeight(this);
        final int intValue4 = (int)this.DBGet("options", "scrollbarhorizontaldivider");
        final int n = (width2 / intValue4 < intValue2 * 3) ? (intValue2 * 3) : (width2 / intValue4);
        final int n2 = n - intValue2 * 2;
        final int n3 = intValue + width2 - n;
        int n4 = intValue3 - intValue2;
        if (!s.equals("top")) {
            n4 = height - intValue3 + 2;
        }
        final int n5 = (this.scrollHorMax == 0) ? 0 : (this.scrollHorValue * (n2 - width) / this.scrollHorMax);
        if (this.scrollHorMax > 0) {
            this.buf2x.setColor(new Color(12632256));
            this.buf2x.fillRect(n3, n4, n, intValue2);
            if (this.scrollHandler == 20) {
                this.buf2x.drawImage(this.getImage("horizontalunitincpushed"), n3, n4, this);
            }
            else {
                this.buf2x.drawImage(this.getImage("horizontalunitinc"), n3, n4, this);
            }
            this.buf2x.drawImage(this.getImage("horizontalbubble"), n3 + intValue2 + n5, n4, this);
            if (this.scrollHandler == 22) {
                this.buf2x.drawImage(this.getImage("horizontalunitdecpushed"), n3 + n - intValue2, n4, this);
                return;
            }
            this.buf2x.drawImage(this.getImage("horizontalunitdec"), n3 + n - intValue2, n4, this);
        }
    }
    
    private void tabsCreateImage() {
        if (this.bg == null || this.buf == null || this.buf2x == null) {
            return;
        }
        try {
            this.buf.drawImage(this.bgImage, 0, 0, this);
            this.bufImage.getWidth(this);
            this.bufImage.getHeight(this);
            final Image image = this.getImage("tableft");
            final Image image2 = this.getImage("tabmiddle");
            final Image image3 = this.getImage("tabright");
            final Image image4 = this.getImage("tabline");
            final Image image5 = this.getImage("selectedtableft");
            final Image image6 = this.getImage("selectedtabmiddle");
            final Image image7 = this.getImage("selectedtabright");
            final int intValue = (int)this.DBGet("options", "overlap");
            final String s = (String)this.DBGet("options", "position");
            final int intValue2 = (int)this.DBGet("options", "connectlineleft");
            final int intValue3 = (int)this.DBGet("options", "connectlineright");
            final int intValue4 = (int)this.DBGet("options", "underline");
            final int intValue5 = (int)this.DBGet("options", "mouseover");
            final Color color = new Color((int)this.DBGet("options", "textcolor"));
            new Color((int)this.DBGet("options", "selectedcolor"));
            final Color color2 = new Color((int)this.DBGet("options", "mouseovercolor"));
            final Color color3 = new Color((int)this.DBGet("options", "underlinecolor"));
            if (this.DBSize("tabs") == 0) {
                return;
            }
            final Vector dbSubSet = this.DBSubSet("tabs");
            if (dbSubSet.indexOf(this.entrySelected) != -1) {
                dbSubSet.removeElementAt(dbSubSet.indexOf(this.entrySelected));
                dbSubSet.addElement(this.entrySelected);
            }
            for (int i = 0; i < dbSubSet.size(); ++i) {
                final String s2 = dbSubSet.elementAt(i);
                final Font font = (Font)this.DBGet("tabs", s2, 5);
                final Image image8 = (Image)this.DBGet("tabs", s2, 6);
                final String s3 = (String)this.DBGet("entries", s2, 1);
                int n = (int)this.DBGet("tabs", s2, 1) - this.scrollHorValue;
                int n2 = (int)this.DBGet("tabs", s2, 2) - this.scrollHorValue;
                int n3 = (int)this.DBGet("tabs", s2, 3) - this.scrollHorValue;
                int n4 = (int)this.DBGet("tabs", s2, 4) - this.scrollHorValue;
                final int intValue6 = (int)this.DBGet("tabs", s2, 7);
                final int intValue7 = (int)this.DBGet("tabs", s2, 8);
                final int intValue8 = (int)this.DBGet("tabs", s2, 9);
                final int intValue9 = (int)this.DBGet("tabs", s2, 10);
                if (n4 >= 0) {
                    if (intValue8 == 0) {
                        for (int j = 0; j < n; ++j) {
                            this.buf.drawImage(image4, j, 0, this);
                        }
                    }
                    Image image9 = image;
                    Image image10 = image2;
                    Image image11 = image3;
                    if (s2.equals(this.entrySelected)) {
                        n -= intValue;
                        if (n + this.scrollHorValue < 0) {
                            n = -this.scrollHorValue;
                        }
                        n2 -= intValue;
                        n3 -= intValue;
                        n4 += intValue;
                        image9 = image5;
                        image10 = image6;
                        image11 = image7;
                    }
                    this.buf.drawImage(image9, n, 0, this);
                    for (int k = n + image.getWidth(this); k < n4 - image3.getWidth(this); ++k) {
                        this.buf.drawImage(image10, k, 0, this);
                    }
                    this.buf.drawImage(image11, n4 - image3.getWidth(this), 0, this);
                    Color color4 = color;
                    if (s2.equals(this.entryMouseOver)) {
                        if (intValue4 != 0) {
                            this.buf.setColor(color3);
                            this.buf.drawLine(n3, intValue7 + 2, n3 + intValue9, intValue7 + 2);
                        }
                        if (intValue5 != 0) {
                            color4 = color2;
                        }
                    }
                    if (image8 != null) {
                        if (s.equals("top")) {
                            this.buf.drawImage(image8, n2, intValue6 + 1, this);
                        }
                        else {
                            this.buf.drawImage(image8, n2, intValue6, this);
                        }
                    }
                    this.buf.setFont(font);
                    this.buf.setColor(color4);
                    this.buf.drawString(s3, n3, intValue7);
                    if (intValue8 == 2) {
                        for (int l = n4; l < this.buf2xImage.getWidth(this); ++l) {
                            this.buf.drawImage(image4, l, 0, this);
                        }
                    }
                }
            }
            final Color color5 = new Color((int)this.DBGet("options", "highcolor"));
            final Color color6 = new Color((int)this.DBGet("options", "lowcolor"));
            final int intValue10 = (int)this.DBGet("options", "baseline");
            if (s.equals("top")) {
                if (intValue2 == 1) {
                    this.buf.setColor(color5);
                    this.buf.drawLine(0, intValue10, 0, this.bufImage.getHeight(this));
                }
                if (intValue3 == 1) {
                    this.buf.setColor(color6);
                    this.buf.drawLine(this.bufImage.getWidth(this) - 1, intValue10, this.bufImage.getWidth(this) - 1, this.bufImage.getHeight(this));
                }
            }
            else {
                if (intValue2 == 1) {
                    this.buf.setColor(color6);
                    this.buf.drawLine(0, 0, 0, this.bufImage.getHeight(this) - intValue10);
                }
                if (intValue3 == 1) {
                    this.buf.setColor(color5);
                    this.buf.drawLine(this.bufImage.getWidth(this) - 1, 0, this.bufImage.getWidth(this) - 1, this.bufImage.getHeight(this) - intValue10);
                }
            }
            final int intValue11 = (int)this.DBGet("options", "marginleft");
            this.buf2x.drawImage(this.bgImage, 0, 0, this);
            this.buf2x.drawImage(this.bufImage, intValue11, 0, this);
            this.scrollPaint();
        }
        catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
    
    protected void printMessage(final String s) {
        System.out.println("=============== message from diputree ==============================");
        System.out.println(s);
        System.out.println("====================================================================");
    }
    
    private Image getImage(final String s) {
        final Image image = (Image)this.DBGet("images", s);
        if (image == null) {
            return (Image)this.DBGet("imagesdefault", s);
        }
        return image;
    }
    
    private Vector DBSubSet(final String s, final Object o, final int n) {
        final Vector<String> vector = new Vector<String>();
        final Vector<Vector> value = this.db.get(s);
        try {
            if (value instanceof Hashtable) {
                final Enumeration<String> keys = ((Hashtable<String, V>)value).keys();
                while (keys.hasMoreElements()) {
                    final Vector<Object> vector2 = ((Hashtable<K, Vector>)value).get(keys.nextElement());
                    if (vector2.elementAt(n).equals(o)) {
                        vector.addElement((String)vector2.elementAt(0));
                    }
                }
            }
            if (value instanceof Vector) {
                for (int i = 0; i < value.size(); ++i) {
                    final Vector<String> vector3 = value.elementAt(i);
                    if (vector3.elementAt(n).equals(o)) {
                        vector.addElement(vector3.elementAt(0));
                    }
                }
            }
        }
        catch (Exception ex) {}
        return vector;
    }
    
    private Vector DBSubSet(final String s) {
        final Vector<String> vector = new Vector<String>();
        final Vector<Vector> value = this.db.get(s);
        try {
            if (value instanceof Hashtable) {
                final Enumeration<String> keys = ((Hashtable<String, V>)value).keys();
                while (keys.hasMoreElements()) {
                    vector.addElement(keys.nextElement());
                }
            }
            if (value instanceof Vector) {
                for (int i = 0; i < value.size(); ++i) {
                    vector.addElement(((Vector<String>)value.elementAt(i)).elementAt(0));
                }
            }
        }
        catch (Exception ex) {}
        return vector;
    }
    
    private int DBSize(final String s) {
        final Vector value = this.db.get(s);
        if (value instanceof Hashtable) {
            return ((Hashtable)value).size();
        }
        if (value instanceof Vector) {
            return value.size();
        }
        return 0;
    }
    
    private boolean DBRemove(final String s, final String s2) {
        try {
            final Vector<Vector> value = this.db.get(s);
            if (value instanceof Hashtable) {
                ((Hashtable)value).remove(s2);
                return true;
            }
            if (value instanceof Vector) {
                for (int i = 0; i < value.size(); ++i) {
                    if (((Vector<Object>)value.elementAt(i)).elementAt(0).equals(s2)) {
                        value.removeElementAt(i);
                        return true;
                    }
                }
            }
        }
        catch (Exception ex) {
            return false;
        }
        return false;
    }
    
    private String DBGetS(final String s, final String s2) {
        return (String)this.DBGet(s, s2);
    }
    
    private String DBGetS(final String s, final String s2, final int n) {
        return (String)this.DBGet(s, s2, n);
    }
    
    private int DBGetI(final String s, final String s2, final int n) {
        return (int)this.DBGet(s, s2, n);
    }
    
    private int DBGetI(final String s, final String s2) {
        return (int)this.DBGet(s, s2);
    }
    
    private Object DBGet(final String s, final String s2, final int n) {
        try {
            if (this.db.get(s) instanceof Vector) {
                final Vector<Vector> vector = this.db.get(s);
                for (int i = 0; i < vector.size(); ++i) {
                    final Vector<Object> vector2 = vector.elementAt(i);
                    if (vector2.elementAt(0).equals(s2)) {
                        return vector2.elementAt(n);
                    }
                }
                return null;
            }
            return this.db.get(s).get(s2).elementAt(n);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    private Object DBGet(final String s, final String s2) {
        try {
            return this.db.get(s).get(s2);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    private boolean DBContainsKey(final String s, final String s2) {
        if (this.db.get(s) instanceof Hashtable) {
            return this.db.get(s).containsKey(s2);
        }
        if (this.db.get(s) instanceof Vector) {
            final Vector<Vector> vector = this.db.get(s);
            for (int i = 0; i < vector.size(); ++i) {
                if (vector.elementAt(i).elementAt(0).equals(s2)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private void DBAdd(final String s, final Vector vector) {
        if (this.db.get(s) instanceof Hashtable) {
            final Hashtable<String, Vector<String>> hashtable = this.db.get(s);
            for (int i = 0; i < vector.size(); ++i) {
                final Vector<String> vector2 = vector.elementAt(i);
                hashtable.put(vector2.elementAt(0), vector2);
            }
        }
        if (this.db.get(s) instanceof Vector) {
            try {
                final Vector<Vector<String>> vector3 = this.db.get(s);
                for (int j = 0; j < vector.size(); ++j) {
                    final Vector<String> vector4 = vector.elementAt(j);
                    final String s2 = vector4.elementAt(0);
                    boolean b = false;
                    for (int k = 0; k < vector3.size(); ++k) {
                        if (vector3.elementAt(k).elementAt(0).equals(s2)) {
                            b = true;
                            vector3.setElementAt(vector4, k);
                            break;
                        }
                    }
                    if (!b) {
                        vector3.addElement(vector4);
                    }
                }
            }
            catch (Exception ex) {}
        }
    }
    
    public diputab() {
        this.db = new Hashtable();
        this.dbLock = new Object();
        this.db.put("entries", new Vector());
        this.db.put("options", new Hashtable<String, Hashtable>());
        this.db.put("links", new Hashtable<String, Hashtable>());
        this.db.put("fonts", new Hashtable<String, Hashtable>());
        this.db.put("images", new Hashtable<String, Hashtable>());
        this.db.put("imagesraw", new Hashtable<String, Hashtable>());
        this.db.put("imagesdefault", new Hashtable<String, Hashtable>());
        this.db.put("internalhandlers", new Hashtable<String, Hashtable>());
        this.db.put("externalhandlers", new Hashtable<String, Hashtable>());
        this.db.put("selected", new Hashtable<String, Hashtable>());
        this.db.put("tabs", new Vector());
        this.entryMouseOver = "";
        this.entrySelected = "";
        this.DBSet("options", "backgroundcolor", new Integer(12632256));
        this.DBSet("options", "foregroundcolor", new Integer(12632256));
        this.DBSet("options", "highcolor", new Integer(16777215));
        this.DBSet("options", "lowcolor", new Integer(0));
        this.DBSet("options", "shadecolor", new Integer(8421504));
        this.DBSet("options", "textcolor", new Integer(0));
        this.DBSet("options", "selectedcolor", new Integer(4210752));
        this.DBSet("options", "mouseovercolor", new Integer(16777215));
        this.DBSet("options", "tipcolor", new Integer(0));
        this.DBSet("options", "tipbackgroundcolor", new Integer(16777088));
        this.DBSet("options", "underlinecolor", new Integer(128));
        this.DBSet("options", "underline", new Integer(0));
        this.DBSet("options", "mouseover", new Integer(1));
        this.DBSet("options", "marginleft", new Integer(0));
        this.DBSet("options", "marginright", new Integer(0));
        this.DBSet("options", "baseline", new Integer(30));
        this.DBSet("options", "tabheight", new Integer(25));
        this.DBSet("options", "indent", new Integer(0));
        this.DBSet("options", "overlap", new Integer(2));
        this.DBSet("options", "connectlineleft", new Integer(0));
        this.DBSet("options", "connectlineright", new Integer(0));
        this.DBSet("options", "position", "top");
        this.DBSet("options", "textup", new Integer(3));
        this.DBSet("options", "textdown", new Integer(3));
        this.DBSet("options", "textleft", new Integer(5));
        this.DBSet("options", "textright", new Integer(5));
        this.DBSet("options", "imageleft", new Integer(5));
        this.DBSet("options", "frame", "_blank");
        this.DBSet("options", "fontprofile", "defaultfont");
        this.DBSet("options", "tipwait", new Integer(1500));
        this.DBSet("options", "tipfontprofile", "tipdefaultfont");
        this.DBSet("options", "showstatus", new Integer(1));
        this.DBSet("options", "scrollbuttonsize", new Integer(14));
        this.DBSet("options", "unitdivider", new Integer(10));
        this.DBSet("options", "blockdivider", new Integer(2));
        this.DBSet("options", "scrollspeed", new Integer(100));
        this.DBSet("options", "scrollbarhorizontaldivider", new Integer(5));
        this.DBSet("options", "asyncload", new Integer(1));
        this.DBSet("options", "externalhandlers", new Integer(0));
        this.DBSet("fonts", "defaultfont", new Font("SansSerif", 0, 15));
        this.DBSet("fonts", "tipdefaultfont", new Font("SansSerif", 0, 13));
    }
    
    private void tabsCreate() {
        if (this.bg == null || this.buf == null || this.buf2x == null) {
            return;
        }
        final Vector stringToTable = this.parseStringToTable("^(c)dipu|(c) dipu|(c)dipu||||click here for more info on dipuproducts||click here for more info on dipu products", "^", "|", 9);
        final Vector stringToTable2 = this.parseStringToTable("^(c)dipu|http://www.dipu.com|_blank", "^", "|", 3);
        this.DBAdd("entries", stringToTable);
        this.DBAdd("links", stringToTable2);
        try {
            final int intValue = (int)this.DBGet("options", "textup");
            final int intValue2 = (int)this.DBGet("options", "textleft");
            final int intValue3 = (int)this.DBGet("options", "textdown");
            final int intValue4 = (int)this.DBGet("options", "textright");
            final int intValue5 = (int)this.DBGet("options", "imageleft");
            final int intValue6 = (int)this.DBGet("options", "tabheight");
            final int i = intValue6 - intValue - intValue3;
            final int intValue7 = (int)this.DBGet("options", "indent");
            final int intValue8 = (int)this.DBGet("options", "baseline");
            final String s = (String)this.DBGet("options", "position");
            int n = intValue7;
            final Vector<Vector<String>> vector = new Vector<Vector<String>>();
            final Vector dbSubSet = this.DBSubSet("entries");
            for (int j = 0; j < dbSubSet.size(); ++j) {
                final String s2 = dbSubSet.elementAt(j);
                final Vector<String> vector2 = new Vector<String>();
                final String s3 = (String)this.DBGet("entries", s2, 1);
                String s4 = (String)this.DBGet("entries", s2, 3);
                if (s2.equals(this.entrySelected)) {
                    s4 = (String)this.DBGet("entries", s2, 4);
                }
                final Image image = this.getImage(s4);
                Font font = (Font)this.DBGet("fonts", (String)this.DBGet("entries", s2, 5));
                if (font == null) {
                    font = (Font)this.DBGet("fonts", "defaultfont");
                }
                this.buf.setFont(font);
                FontMetrics fontMetrics = this.buf.getFontMetrics();
                int n2 = fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent();
                final String name = font.getName();
                int size = font.getSize();
                final int style = font.getStyle();
                while (i < n2) {
                    --size;
                    font = new Font(name, style, size);
                    this.buf.setFont(font);
                    fontMetrics = this.buf.getFontMetrics();
                    n2 = fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent();
                    if (size >= 8) {
                        continue;
                    }
                    break;
                }
                final int stringWidth = fontMetrics.stringWidth(s3);
                final int ascent = fontMetrics.getAscent();
                vector2.addElement(s2);
                vector2.addElement((String)new Integer(n));
                if (image != null) {
                    final int n3 = n + intValue5;
                    vector2.addElement((String)new Integer(n3));
                    n = n3 + image.getWidth(this);
                }
                else {
                    vector2.addElement((String)new Integer(n));
                }
                final int n4 = n + intValue2;
                vector2.addElement((String)new Integer(n4));
                n = n4 + intValue4 + stringWidth;
                vector2.addElement((String)new Integer(n));
                vector2.addElement((String)font);
                vector2.addElement((String)image);
                int n5 = 0;
                int n6;
                if (s.equals("top")) {
                    if (image != null) {
                        if (s3.length() > 0) {
                            n5 = intValue8 - intValue6 + intValue + ascent - image.getHeight(this) + 2;
                        }
                        else {
                            n5 = intValue8 - 1 - 2 - image.getHeight(this);
                        }
                    }
                    n6 = intValue8 - intValue6 + intValue + ascent;
                }
                else {
                    if (image != null) {
                        if (s3.length() > 0) {
                            n5 = this.bufImage.getHeight(this) - intValue8 + intValue + ascent + 2 - image.getHeight(this) + 2;
                        }
                        else {
                            n5 = this.bufImage.getHeight(this) - intValue8 + 1;
                        }
                    }
                    n6 = this.bufImage.getHeight(this) - intValue8 + intValue + ascent + 2;
                }
                vector2.addElement((String)new Integer(n5));
                vector2.addElement((String)new Integer(n6));
                int n7 = 1;
                if (j == 0) {
                    n7 = 0;
                }
                if (j == dbSubSet.size() - 1) {
                    n7 = 2;
                }
                vector2.addElement((String)new Integer(n7));
                vector2.addElement((String)new Integer(stringWidth));
                vector.addElement(vector2);
            }
            this.db.put("tabs", vector);
            final int width = this.bufImage.getWidth(this);
            final int intValue9 = (int)this.DBGet("options", "scrollbarhorizontaldivider");
            final int intValue10 = (int)this.DBGet("options", "scrollbuttonsize");
            this.scrollHorMax = n + intValue7 - this.bufImage.getWidth(this) + ((width / intValue9 < intValue10 * 3) ? (intValue10 * 3) : (width / intValue9));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void createDefaultIcons() {
        if (this.bg == null || this.buf == null || this.buf2x == null) {
            return;
        }
        try {
            this.iconsReady = false;
            final Color color = new Color((int)this.DBGet("options", "backgroundcolor"));
            final Color color2 = new Color((int)this.DBGet("options", "foregroundcolor"));
            final Color color3 = new Color((int)this.DBGet("options", "highcolor"));
            final Color color4 = new Color((int)this.DBGet("options", "lowcolor"));
            final Color color5 = new Color((int)this.DBGet("options", "shadecolor"));
            final int intValue = (int)this.DBGet("options", "baseline");
            final int n = this.buf2xImage.getHeight(this) - intValue;
            final int intValue2 = (int)this.DBGet("options", "tabheight");
            final String s = (String)this.DBGet("options", "position");
            final Image image = this.createImage(2, this.buf2xImage.getHeight(this));
            final Graphics graphics = image.getGraphics();
            graphics.setColor(color);
            graphics.fillRect(0, 0, 2, this.buf2xImage.getHeight(this));
            if (s.equals("top")) {
                graphics.setColor(color2);
                graphics.fillRect(0, intValue - intValue2 + 2 + 2, 2, this.buf2xImage.getHeight(this));
                graphics.setColor(color3);
                graphics.fillRect(0, intValue - intValue2 + 2 + 2, 1, intValue2 - 2 - 2);
                graphics.fillRect(1, intValue - intValue2 + 1 + 2, 1, 1);
                graphics.fillRect(0, intValue, 2, 1);
            }
            else {
                graphics.setColor(color2);
                graphics.fillRect(0, 0, 2, 1 + n + intValue2 - 2 - 2);
                graphics.setColor(color3);
                graphics.fillRect(0, n, 1, 1 + intValue2 - 2 - 2);
                graphics.setColor(color4);
                graphics.fillRect(1, n + intValue2 - 2 - 1, 1, 1);
                graphics.setColor(color5);
                graphics.fillRect(0, n, 2, 1);
                graphics.setColor(color4);
                graphics.fillRect(0, n + 1, 2, 1);
            }
            this.DBSet("imagesdefault", "tableft", image);
            final Image image2 = this.createImage(1, this.buf2xImage.getHeight(this));
            final Graphics graphics2 = image2.getGraphics();
            graphics2.setColor(color);
            graphics2.fillRect(0, 0, 1, this.buf2xImage.getHeight(this));
            if (s.equals("top")) {
                graphics2.setColor(color2);
                graphics2.fillRect(0, intValue - intValue2 + 1 + 2, 1, this.buf2xImage.getHeight(this));
                graphics2.setColor(color3);
                graphics2.fillRect(0, intValue - intValue2 + 2, 1, 1);
                graphics2.fillRect(0, intValue, 2, 1);
            }
            else {
                graphics2.setColor(color2);
                graphics2.fillRect(0, 0, 1, 1 + n + intValue2 - 2);
                graphics2.setColor(color5);
                graphics2.fillRect(0, n + intValue2 - 2 - 1, 1, 1);
                graphics2.setColor(color4);
                graphics2.fillRect(0, n + intValue2 - 2, 1, 1);
                graphics2.setColor(color5);
                graphics2.fillRect(0, n, 2, 1);
                graphics2.setColor(color4);
                graphics2.fillRect(0, n + 1, 2, 1);
            }
            this.DBSet("imagesdefault", "tabmiddle", image2);
            final Image image3 = this.createImage(2, this.buf2xImage.getHeight(this));
            final Graphics graphics3 = image3.getGraphics();
            graphics3.setColor(color);
            graphics3.fillRect(0, 0, 2, this.buf2xImage.getHeight(this));
            if (s.equals("top")) {
                graphics3.setColor(color2);
                graphics3.fillRect(0, intValue - intValue2 + 2 + 1 + 2, 2, this.buf2xImage.getHeight(this));
                graphics3.setColor(color5);
                graphics3.fillRect(0, intValue - intValue2 + 1 + 2, 1, intValue2 - 1 - 2);
                graphics3.setColor(color4);
                graphics3.fillRect(1, intValue - intValue2 + 2 + 2, 1, intValue2 - 2 - 2);
                graphics3.setColor(color3);
                graphics3.fillRect(0, intValue, 2, 1);
            }
            else {
                graphics3.setColor(color2);
                graphics3.fillRect(0, 0, 2, 1 + n + intValue2 - 2 - 2);
                graphics3.setColor(color5);
                graphics3.fillRect(0, n, 1, 1 + intValue2 - 1 - 2);
                graphics3.setColor(color4);
                graphics3.fillRect(0, n + intValue2 - 1 - 2, 1, 1);
                graphics3.fillRect(1, n, 1, 1 + intValue2 - 2 - 2);
                graphics3.setColor(color5);
                graphics3.fillRect(0, n, 2, 1);
                graphics3.setColor(color4);
                graphics3.fillRect(0, n + 1, 2, 1);
            }
            this.DBSet("imagesdefault", "tabright", image3);
            final Image image4 = this.createImage(1, this.buf2xImage.getHeight(this));
            final Graphics graphics4 = image4.getGraphics();
            graphics4.setColor(color);
            graphics4.fillRect(0, 0, 2, this.buf2xImage.getHeight(this));
            if (s.equals("top")) {
                graphics4.setColor(color2);
                graphics4.fillRect(0, intValue, 1, this.buf2xImage.getHeight(this));
                graphics4.setColor(color3);
                graphics4.fillRect(0, intValue, 1, 1);
            }
            else {
                graphics4.setColor(color2);
                graphics4.fillRect(0, 0, 1, 1 + n);
                graphics4.setColor(color5);
                graphics4.fillRect(0, n, 1, 1);
                graphics4.setColor(color4);
                graphics4.fillRect(0, n + 1, 1, 1);
            }
            this.DBSet("imagesdefault", "tabline", image4);
            final Image image5 = this.createImage(2, this.buf2xImage.getHeight(this));
            final Graphics graphics5 = image5.getGraphics();
            graphics5.setColor(color);
            graphics5.fillRect(0, 0, 2, this.buf2xImage.getHeight(this));
            if (s.equals("top")) {
                graphics5.setColor(color2);
                graphics5.fillRect(0, intValue - intValue2 + 2, 2, this.buf2xImage.getHeight(this));
                graphics5.setColor(color3);
                graphics5.fillRect(0, intValue - intValue2 + 2, 1, intValue2 - 2 + 1);
                graphics5.fillRect(1, intValue - intValue2 + 1, 1, 1);
            }
            else {
                graphics5.setColor(color2);
                graphics5.fillRect(0, 0, 2, 1 + n + intValue2 - 2);
                graphics5.setColor(color3);
                graphics5.fillRect(0, n, 1, 1 + intValue2 - 2);
                graphics5.setColor(color4);
                graphics5.fillRect(1, n + intValue2 - 1, 1, 1);
            }
            this.DBSet("imagesdefault", "selectedtableft", image5);
            final Image image6 = this.createImage(1, this.buf2xImage.getHeight(this));
            final Graphics graphics6 = image6.getGraphics();
            graphics6.setColor(color);
            graphics6.fillRect(0, 0, 1, this.buf2xImage.getHeight(this));
            if (s.equals("top")) {
                graphics6.setColor(color2);
                graphics6.fillRect(0, intValue - intValue2 + 1, 1, this.buf2xImage.getHeight(this));
                graphics6.setColor(color3);
                graphics6.fillRect(0, intValue - intValue2, 1, 1);
            }
            else {
                graphics6.setColor(color2);
                graphics6.fillRect(0, 0, 1, 1 + n + intValue2);
                graphics6.setColor(color5);
                graphics6.fillRect(0, n + intValue2 - 1, 1, 1);
                graphics6.setColor(color4);
                graphics6.fillRect(0, n + intValue2, 1, 1);
            }
            this.DBSet("imagesdefault", "selectedtabmiddle", image6);
            final Image image7 = this.createImage(2, this.buf2xImage.getHeight(this));
            final Graphics graphics7 = image7.getGraphics();
            graphics7.setColor(color);
            graphics7.fillRect(0, 0, 2, this.buf2xImage.getHeight(this));
            if (s.equals("top")) {
                graphics7.setColor(color2);
                graphics7.fillRect(0, intValue - intValue2 + 2 + 1, 2, this.buf2xImage.getHeight(this));
                graphics7.setColor(color5);
                graphics7.fillRect(0, intValue - intValue2 + 1, 1, intValue2 - 1 + 1);
                graphics7.setColor(color4);
                graphics7.fillRect(1, intValue - intValue2 + 2, 1, intValue2 - 2 + 1);
            }
            else {
                graphics7.setColor(color2);
                graphics7.fillRect(0, 0, 2, 1 + n + intValue2 - 2);
                graphics7.setColor(color5);
                graphics7.fillRect(0, n, 1, 1 + intValue2 - 1);
                graphics7.setColor(color4);
                graphics7.fillRect(0, n + intValue2 - 1, 1, 1);
                graphics7.fillRect(1, n, 1, 1 + intValue2 - 2);
            }
            this.DBSet("imagesdefault", "selectedtabright", image7);
            final Image image8 = this.createImage(14, 14);
            final Graphics graphics8 = image8.getGraphics();
            graphics8.setColor(new Color(12632256));
            graphics8.fill3DRect(0, 0, 14, 14, true);
            graphics8.setColor(new Color(0));
            graphics8.fillRect(5, 6, 1, 1);
            graphics8.fillRect(6, 5, 1, 3);
            graphics8.fillRect(7, 4, 1, 5);
            graphics8.fillRect(8, 3, 1, 7);
            this.DBSet("imagesdefault", "horizontalunitinc", image8);
            final Image image9 = this.createImage(14, 14);
            final Graphics graphics9 = image9.getGraphics();
            graphics9.drawImage(image8, 1, 1, this);
            graphics9.setColor(new Color(12632256));
            graphics9.drawRect(1, 1, 13, 13);
            graphics9.drawRect(0, 0, 14, 14);
            this.DBSet("imagesdefault", "horizontalunitincpushed", image9);
            final Image image10 = this.createImage(14, 14);
            final Graphics graphics10 = image10.getGraphics();
            graphics10.setColor(new Color(12632256));
            graphics10.fill3DRect(0, 0, 14, 14, true);
            graphics10.setColor(new Color(0));
            graphics10.fillRect(8, 6, 1, 1);
            graphics10.fillRect(7, 5, 1, 3);
            graphics10.fillRect(6, 4, 1, 5);
            graphics10.fillRect(5, 3, 1, 7);
            this.DBSet("imagesdefault", "horizontalunitdec", image10);
            final Image image11 = this.createImage(14, 14);
            final Graphics graphics11 = image11.getGraphics();
            graphics11.drawImage(image10, 1, 1, this);
            graphics11.setColor(new Color(12632256));
            graphics11.drawRect(1, 1, 13, 13);
            graphics11.drawRect(0, 0, 14, 14);
            this.DBSet("imagesdefault", "horizontalunitdecpushed", image11);
            final Image image12 = this.createImage(5, 14);
            final Graphics graphics12 = image12.getGraphics();
            graphics12.setColor(new Color(12632256));
            graphics12.fill3DRect(0, 0, 5, 14, true);
            this.DBSet("imagesdefault", "horizontalbubble", image12);
            this.iconsReady = true;
            graphics.dispose();
            graphics2.dispose();
            graphics3.dispose();
            graphics4.dispose();
            graphics5.dispose();
            graphics6.dispose();
            graphics7.dispose();
            graphics8.dispose();
            graphics9.dispose();
            graphics10.dispose();
            graphics11.dispose();
            graphics12.dispose();
        }
        catch (Exception ex) {}
    }
    
    private void backgroundUpdate() {
        if (this.bg == null || this.buf == null || this.buf2x == null) {
            return;
        }
        this.bg.setColor(new Color((int)this.DBGet("options", "backgroundcolor")));
        this.bg.fillRect(0, 0, this.appletWidth, this.appletHeight);
        final Image image = this.getImage("background");
        if (image != null) {
            final int width = this.bgImage.getWidth(this);
            final int height = this.bgImage.getHeight(this);
            final int width2 = image.getWidth(this);
            final int height2 = image.getHeight(this);
            for (int i = 0; i <= width; i += width2) {
                for (int j = 0; j <= height; j += height2) {
                    this.bg.drawImage(image, i, j, this);
                }
            }
        }
    }
    
    protected void waitForInit() {
        while (!this.initialized) {
            try {
                Thread.currentThread();
                Thread.sleep(500L);
            }
            catch (Exception ex) {}
        }
    }
    
    protected Vector parseStringToTable(String s, final String s2, final String s3, final int size) {
        final Vector<Vector<String>> vector = new Vector<Vector<String>>();
        final Vector vector2 = new Vector();
        while (true) {
            s = s.trim();
            final String s4 = new String();
            if (s.length() <= 0) {
                return vector;
            }
            if (!s.startsWith(s2)) {
                this.printMessage("No record separator found at ");
                this.printMessage("the beginning of [" + s + "]");
                return vector;
            }
            s = s.substring(1);
            final int index = s.indexOf(s2);
            String substring;
            if (index == -1) {
                substring = new String(s);
                s = "";
            }
            else {
                substring = new String(s.substring(0, index));
                s = s.substring(index);
            }
            final Vector vector3 = new Vector<String>();
            while (true) {
                final String trim = substring.trim();
                final String s5 = new String();
                final boolean b = true;
                if (trim.length() <= 0) {
                    break;
                }
                final int index2 = trim.indexOf(s3);
                if (index2 == -1) {
                    String trim2 = new String(trim);
                    if (b) {
                        trim2 = trim2.trim();
                    }
                    vector3.addElement(new String(trim2));
                    substring = "";
                }
                else {
                    String s6 = trim.substring(0, index2);
                    if (b) {
                        s6 = s6.trim();
                    }
                    vector3.addElement(new String(s6));
                    substring = trim.substring(index2 + 1);
                }
            }
            if (size != -1 && vector3.size() < size) {
                vector3.setSize(size);
            }
            vector.addElement(vector3);
        }
    }
    
    protected void tablesLoad(final String s, String string) {
        try {
            if (string.length() >= 4 && string.substring(0, 4).toLowerCase().equals("url:")) {
                final URL url = new URL(this.getCodeBase(), string.substring(4));
                string = new String();
                final String s2 = new String();
                final DataInputStream dataInputStream = new DataInputStream(url.openStream());
                String line;
                while ((line = dataInputStream.readLine()) != null) {
                    string += line;
                }
                dataInputStream.close();
            }
        }
        catch (Exception ex) {
            this.printMessage("Error while loading " + s + ".");
            this.printMessage(ex.toString());
        }
        this.postProcess(s, string);
    }
    
    public void update(final Graphics graphics) {
        if (this.appletWidth < 1 || this.appletHeight < 1) {
            this.appletWidth = this.size().width;
            this.appletHeight = this.size().height;
            this.graphicsRelease();
        }
        if (this.bgImage == null || this.bufImage == null || this.buf2xImage == null) {
            this.graphicsCreate();
            this.backgroundUpdate();
            this.createDefaultIcons();
            this.tabsCreate();
            this.tabsCreateImage();
            this.repaint();
        }
        if (!this.iconsReady) {
            this.createDefaultIcons();
        }
        if (this.bg == null || this.buf == null || this.buf2x == null) {
            this.repaint();
            return;
        }
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (!this.initialized) {
            graphics.setColor(new Color((int)this.DBGet("options", "backgroundcolor")));
            graphics.fillRect(0, 0, this.appletWidth, this.appletHeight);
            graphics.setFont(new Font("SanSerif", 0, 13));
            graphics.setColor(new Color((int)this.DBGet("options", "textcolor")));
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            graphics.drawString("loading, please wait.", (this.size().width - fontMetrics.stringWidth("loading, please wait.")) / 2, (this.size().height - (fontMetrics.getAscent() + fontMetrics.getDescent())) / 2 + fontMetrics.getAscent());
        }
        if (this.initialized) {
            if (this.DBSize("tabs") > 0) {
                graphics.drawImage(this.buf2xImage, 0, 0, this);
                return;
            }
            graphics.drawImage(this.bgImage, 0, 0, this);
        }
    }
    
    protected void executeLinks(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(((String)this.DBGet("entries", s, 2)).trim(), " \t\n\r", false);
        while (stringTokenizer.hasMoreElements()) {
            final String trim = ((String)stringTokenizer.nextElement()).trim();
            if (trim.length() > 0) {
                final String s2 = (String)this.DBGet("links", trim, 1);
                String s3 = (String)this.DBGet("links", trim, 2);
                if (s3 == null) {
                    s3 = (String)this.DBGet("options", "frame");
                }
                try {
                    URL codeBase = this.getCodeBase();
                    if (this.DBGet("options", "baseurl") != null) {
                        codeBase = new URL(codeBase, (String)this.DBGet("options", "baseurl"));
                    }
                    this.getAppletContext().showDocument(new URL(codeBase, s2), s3);
                }
                catch (Exception ex) {
                    this.printMessage("error while showing page " + s2 + " in frame " + s3);
                }
            }
        }
    }
    
    public boolean handleEvent(final Event tipEvent) {
        try {
            if (!this.initialized || this.DBSize("tabs") < 1) {
                return true;
            }
            if (this.scrollHorMax > 0 && (tipEvent.id == 501 || tipEvent.id == 506 || tipEvent.id == 502 || tipEvent.id == 503)) {
                final int intValue = (int)this.DBGet("options", "marginleft");
                final int intValue2 = (int)this.DBGet("options", "scrollbuttonsize");
                final int width = this.getImage("horizontalbubble").getWidth(this);
                final int intValue3 = (int)this.DBGet("options", "baseline");
                final String s = (String)this.DBGet("options", "position");
                final int width2 = this.bufImage.getWidth(this);
                final int height = this.bufImage.getHeight(this);
                final int intValue4 = (int)this.DBGet("options", "scrollbarhorizontaldivider");
                final int n = (width2 / intValue4 < intValue2 * 3) ? (intValue2 * 3) : (width2 / intValue4);
                final int n2 = n - intValue2 * 2;
                final int n3 = intValue + width2 - n;
                int n4 = intValue3 - intValue2;
                if (!s.equals("top")) {
                    n4 = height - intValue3 + 2;
                }
                if (this.scrollHorMax != 0) {
                    final int n5 = this.scrollHorValue * (n2 - width) / this.scrollHorMax;
                }
                final int x = tipEvent.x;
                final int y = tipEvent.y;
                if (n3 <= x && x < n3 + n && n4 <= y && y < n4 + intValue2) {
                    if (tipEvent.id == 503 || tipEvent.id == 502) {
                        if (this.scrollHandler != 0) {
                            this.scrollHandler = 0;
                            this.scrollPaint();
                            this.repaint();
                        }
                        return true;
                    }
                    if (tipEvent.id == 501 || tipEvent.id == 506) {
                        int scrollHandler = 22;
                        if (x < n3 + intValue2) {
                            scrollHandler = 20;
                        }
                        if (n3 + intValue2 <= x && x < n3 + n - intValue2) {
                            scrollHandler = 21;
                            this.scrollSlideToCoord = x - n3 - intValue2;
                        }
                        if (scrollHandler == this.scrollHandler && this.scrollThread.isAlive()) {
                            return true;
                        }
                        if (tipEvent.id == 506 && scrollHandler != this.scrollHandler) {
                            return true;
                        }
                        if (this.scrollThread.isAlive()) {
                            this.scrollThread.stop();
                        }
                        this.scrollHandler = scrollHandler;
                        (this.scrollThread = new Thread(this, "scroll")).start();
                    }
                    return true;
                }
            }
            if (this.scrollHandler != 0) {
                this.scrollHandler = 0;
            }
            if (tipEvent.id == 505) {
                this.tipEvent = tipEvent;
                this.tipEvent.x = -1;
                this.entryMouseOver = "";
                this.tabsCreateImage();
                this.repaint();
                return true;
            }
            if (tipEvent.id == 501 || tipEvent.id == 503) {
                final int n6 = tipEvent.x - (int)this.DBGet("options", "marginleft") + this.scrollHorValue;
                final int y2 = tipEvent.y;
                String tipKey = "";
                final int intValue5 = (int)this.DBGet("options", "tabheight");
                final int intValue6 = (int)this.DBGet("options", "baseline");
                final String s2 = (String)this.DBGet("options", "position");
                boolean b = true;
                if (s2.equals("top")) {
                    if (y2 > intValue6 - intValue5 && y2 < intValue6) {
                        b = false;
                    }
                }
                else {
                    final int n7 = this.bufImage.getHeight(this) - intValue6;
                    if (y2 > n7 && y2 <= n7 + intValue5 - 1) {
                        b = false;
                    }
                }
                if (b) {
                    this.tipEvent = tipEvent;
                    this.tipEvent.x = -1;
                    this.entryMouseOver = "";
                    this.tabsCreateImage();
                    this.repaint();
                    return true;
                }
                final Vector dbSubSet = this.DBSubSet("tabs");
                for (int size = dbSubSet.size(), i = 0; i < size; ++i) {
                    final String s3 = dbSubSet.elementAt(i);
                    final int intValue7 = (int)this.DBGet("tabs", s3, 1);
                    final int intValue8 = (int)this.DBGet("tabs", s3, 4);
                    if (intValue7 <= n6 && n6 < intValue8) {
                        tipKey = s3;
                    }
                }
                if (tipEvent.id == 501 && tipKey.length() > 0) {
                    this.executeLinks(this.entrySelected = tipKey);
                    this.handler(this.entryMouseOver, "selected");
                    this.tabsCreate();
                    this.tabsCreateImage();
                    this.repaint();
                }
                if (tipEvent.id == 503) {
                    if (!this.entryMouseOver.equals(tipKey)) {
                        this.entryMouseOver = tipKey;
                        this.tabsCreateImage();
                        final String s4 = (String)this.DBGet("entries", tipKey, 8);
                        if ((int)this.DBGet("options", "showstatus") == 1) {
                            this.getAppletContext().showStatus(s4);
                        }
                        this.repaint();
                    }
                    this.tipEvent = tipEvent;
                    this.tipKey = tipKey;
                    if (!this.tipThread.isAlive()) {
                        (this.tipThread = new Thread(this, "popup")).start();
                    }
                }
            }
        }
        catch (Exception ex) {
            this.printMessage(ex.toString());
        }
        return true;
    }
    
    public String getKeys(final String s, final String s2) {
        try {
            this.waitForInit();
            String s3 = new String();
            synchronized (this.dbLock) {
                final Vector dbSubSet = this.DBSubSet(s);
                for (int i = 0; i < dbSubSet.size(); ++i) {
                    if (i != 0) {
                        s3 += s2;
                    }
                    s3 += dbSubSet.elementAt(i);
                }
            }
            // monitorexit(this.dbLock)
            return s3;
        }
        catch (Exception ex) {
            this.printMessage("error during 'getKeys (" + s + ", " + s2 + ")");
            this.printMessage(ex.toString());
            return null;
        }
    }
    
    public boolean setField(final String s, final String s2, final int n, final String s3) {
        try {
            this.waitForInit();
            synchronized (this.dbLock) {
                if (!this.DBContainsKey(s, s2)) {
                    // monitorexit(this.dbLock)
                    return false;
                }
                Vector<String> dbGetRecord = new Vector<String>();
                if (s.equals("images")) {
                    this.printMessage("warning, images can only be altered through the addRecords method");
                    // monitorexit(this.dbLock)
                    return false;
                }
                if (s.equals("links")) {
                    dbGetRecord = this.db.get(s).get(s2);
                    dbGetRecord.setElementAt(s3, n);
                }
                else if (s.equals("entries")) {
                    dbGetRecord = (Vector<String>)this.DBGetRecord(s, s2);
                    dbGetRecord.setElementAt(s3, n);
                }
                else if (s.equals("fonts")) {
                    final Font font = (Font)this.DBGet("fonts", s2);
                    dbGetRecord.addElement(s2);
                    dbGetRecord.addElement(font.getName());
                    dbGetRecord.addElement(new Integer(font.getStyle()).toString());
                    dbGetRecord.addElement(new Integer(font.getSize()).toString());
                    dbGetRecord.setElementAt(s3, n);
                }
                else {
                    dbGetRecord.addElement(s2);
                    dbGetRecord.addElement(s3);
                }
                String s4 = this.recordSeparator;
                for (int i = 0; i < dbGetRecord.size(); ++i) {
                    if (dbGetRecord.elementAt(i) != null) {
                        s4 += dbGetRecord.elementAt(i);
                    }
                    if (i != dbGetRecord.size() - 1) {
                        s4 += this.fieldSeparator;
                    }
                }
                this.postProcess(s, s4);
                if (s.equals("options")) {
                    this.createDefaultIcons();
                }
                if (s.equals("images") || s.equals("options")) {
                    this.backgroundUpdate();
                }
                this.tabsCreate();
                this.tabsCreateImage();
            }
            // monitorexit(this.dbLock)
            this.repaint();
            return true;
        }
        catch (Exception ex) {
            this.printMessage("error during 'setField (" + s + ", " + s2 + ", " + n + ", " + s3 + ")");
            this.printMessage(ex.toString());
            return false;
        }
    }
    
    public String getField(final String s, final String s2, final int n) {
        try {
            this.waitForInit();
            synchronized (this.dbLock) {
                // monitorexit(this.dbLock)
                return (String)this.DBGet(s, s2, n);
            }
        }
        catch (Exception ex) {
            this.printMessage("error during 'getField (" + s + ", " + s2 + ", " + n + ")");
            this.printMessage(ex.toString());
            return null;
        }
    }
    
    public boolean setSelected(final String entrySelected) {
        try {
            this.waitForInit();
            this.entrySelected = entrySelected;
            synchronized (this.dbLock) {
                this.tabsCreate();
                this.tabsCreateImage();
            }
            // monitorexit(this.dbLock)
            this.repaint();
            return true;
        }
        catch (Exception ex) {
            this.printMessage("error during 'setSelected (" + entrySelected + ")");
            this.printMessage(ex.toString());
            return false;
        }
    }
    
    public String getSelected() {
        this.waitForInit();
        return this.entrySelected;
    }
    
    public void removeRecords(final String s, final String s2) {
        try {
            this.waitForInit();
            final Vector stringToTable = this.parseStringToTable(s2, this.recordSeparator, this.fieldSeparator, -1);
            if (s.equals("entries")) {
                synchronized (this.dbLock) {
                    for (int i = 0; i < stringToTable.size(); ++i) {
                        this.DBRemove(s, stringToTable.elementAt(i).elementAt(0));
                    }
                    this.tabsCreate();
                    this.tabsCreateImage();
                }
                // monitorexit(this.dbLock)
                this.repaint();
            }
            if (s.equals("images")) {
                synchronized (this.dbLock) {
                    for (int j = 0; j < stringToTable.size(); ++j) {
                        this.DBRemove(s, stringToTable.elementAt(j).elementAt(0));
                    }
                    this.backgroundUpdate();
                    this.tabsCreate();
                    this.tabsCreateImage();
                }
                // monitorexit(this.dbLock)
                this.repaint();
            }
            if (s.equals("internalhandlers") || s.equals("externalhandlers") || s.equals("selected")) {
                synchronized (this.dbLock) {
                    for (int k = 0; k < stringToTable.size(); ++k) {
                        this.DBRemove(s, stringToTable.elementAt(k).elementAt(0));
                    }
                }
                // monitorexit(this.dbLock)
            }
        }
        catch (Exception ex) {
            this.printMessage("error during 'removeRecords (" + s + ", " + s2 + ")");
            this.printMessage(ex.toString());
        }
    }
    
    public void addRecords(final String s, final String s2) {
        try {
            this.waitForInit();
            synchronized (this.dbLock) {
                this.tablesLoad(s, s2);
                if (s.equals("options")) {
                    this.createDefaultIcons();
                }
                if (s.equals("images") || s.equals("options")) {
                    this.backgroundUpdate();
                }
                this.tabsCreate();
                this.tabsCreateImage();
            }
            // monitorexit(this.dbLock)
            this.repaint();
        }
        catch (Exception ex) {
            this.printMessage("error during 'addRecords (" + s + ", " + s2 + ")");
            this.printMessage(ex.toString());
        }
    }
    
    public void run() {
        if (Thread.currentThread().getName().equals("popup")) {
            this.popupThread();
        }
        if (Thread.currentThread().getName().equals("scroll")) {
            this.scrollThread();
        }
        if (Thread.currentThread().getName().equals("startasync")) {
            this.startAsync();
        }
    }
    
    public void start() {
        this.setLayout(null);
        this.appletWidth = this.size().width;
        this.appletHeight = this.size().height;
        this.fieldSeparator = this.getParameter("fieldseparator");
        this.recordSeparator = this.getParameter("recordseparator");
        if (this.fieldSeparator == null) {
            this.fieldSeparator = "|";
        }
        if (this.recordSeparator == null) {
            this.recordSeparator = "^";
        }
        if (!this.tablesLoaded) {
            final String[] array = { "options", "entries", "fonts", "links", "selected", "internalhandlers", "externalhandlers" };
            for (int i = 0; i < array.length; ++i) {
                final String s = array[i];
                final String parameter = this.getParameter(s);
                if (parameter != null) {
                    this.tablesLoad(s, parameter);
                }
            }
            if (this.DBGet("options", "selected") != null) {
                this.entrySelected = (String)this.DBGet("options", "selected");
            }
            this.tablesLoaded = true;
        }
        this.graphicsCreate();
        this.scrollHorValue = 0;
        this.createDefaultIcons();
        (this.tipThread = new Thread(this, "popup")).stop();
        (this.scrollThread = new Thread(this, "scroll")).stop();
        if ((int)this.DBGet("options", "asyncload") == 1) {
            new Thread(this, "startasync").start();
            return;
        }
        this.startAsync();
    }
}
