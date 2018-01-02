// 
// Decompiled by Procyon v0.5.30
// 

package com.objectplanet;

import java.awt.Event;
import java.net.MalformedURLException;
import java.applet.AppletContext;
import java.io.StringBufferInputStream;
import java.net.URL;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.awt.Rectangle;
import java.util.StringTokenizer;
import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Image;
import java.util.Vector;
import java.awt.Color;
import java.awt.Font;
import java.applet.Applet;

public class NewsTicker extends Applet implements Runnable
{
    private String header;
    private Font headerFont;
    private Color headerColor;
    private int headerAlignment;
    private Font newsHeaderFont;
    private Color newsHeaderColor;
    private Font newsFont;
    private Color newsColor;
    private String moreLinkText;
    private Color moreLinkColor;
    private String target;
    private String newsXMLTag;
    private String newsXMLTagEnd;
    private String newsHeaderXMLTag;
    private String newsHeaderXMLTagEnd;
    private String newsBodyXMLTag;
    private String newsBodyXMLTagEnd;
    private String newsLinkXMLTag;
    private String newsLinkXMLTagEnd;
    private int scrollSpeed;
    private int border;
    private Color borderColor;
    private static final int BORDER_NONE = 0;
    private static final int BORDER_SOLID = 1;
    private static final int BORDER_DOWN = 2;
    private static final int BORDER_UP = 3;
    private static final int BORDER_GROOVED = 4;
    private static final int BORDER_EMBOSSED = 5;
    private int wrapSpace;
    private Vector newsItems;
    private static final int STATE_NEWS = 0;
    private static final int STATE_HEADER = 1;
    private static final int STATE_BODY = 2;
    private static final int STATE_LINK = 3;
    private static final int STATE_END = 4;
    private static final int STATE_ERROR = 5;
    private static final int WORD = -3;
    private String parserError;
    private int offset;
    private static final int OFFSET_START = 50;
    private int lastYPos;
    private Image offscreen;
    private Thread scroller;
    private boolean firstStarted;
    private Vector linkPositions;
    private int lastLinkSelection;
    private Point mousePosition;
    private Object CURSOR_DEFAULT;
    private Object CURSOR_LINK;
    private Object currentCursor;
    
    public void stop() {
        if (this.scroller != null) {
            this.scroller.stop();
            this.scroller = null;
        }
    }
    
    public NewsTicker() {
        this.headerFont = new Font("Arial", 1, 16);
        this.headerColor = Color.black;
        this.headerAlignment = -1;
        this.newsHeaderFont = new Font("Arial", 1, 11);
        this.newsHeaderColor = Color.black;
        this.newsFont = new Font("Arial", 0, 11);
        this.newsColor = Color.black;
        this.moreLinkText = "more";
        this.moreLinkColor = Color.red;
        this.target = "_self";
        this.newsXMLTag = "<news>";
        this.newsXMLTagEnd = "</news>";
        this.newsHeaderXMLTag = "<header>";
        this.newsHeaderXMLTagEnd = "</header>";
        this.newsBodyXMLTag = "<body>";
        this.newsBodyXMLTagEnd = "</body>";
        this.newsLinkXMLTag = "<link>";
        this.newsLinkXMLTagEnd = "</link>";
        this.scrollSpeed = 5;
        this.border = 0;
        this.wrapSpace = -1;
        this.newsItems = new Vector();
        this.offset = 50;
        this.firstStarted = true;
        this.linkPositions = new Vector();
        this.lastLinkSelection = -1;
        this.mousePosition = new Point(0, 0);
        this.setBackground(Color.white);
        try {
            this.CURSOR_LINK = new Cursor(12);
            this.CURSOR_DEFAULT = new Cursor(0);
            if (this.CURSOR_DEFAULT != null) {
                this.setCursor((Cursor)this.CURSOR_DEFAULT);
            }
            this.currentCursor = this.CURSOR_DEFAULT;
        }
        catch (NoSuchMethodError noSuchMethodError) {
            System.out.println("Could not set the cursor");
            this.CURSOR_LINK = null;
            this.CURSOR_DEFAULT = null;
        }
        catch (NoClassDefFoundError noClassDefFoundError) {
            System.out.println("Could not find Cursor class");
        }
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.size();
        if (this.offscreen == null || this.offscreen.getWidth(this) < size.width || this.offscreen.getHeight(this) < size.height) {
            this.offscreen = this.createImage(size.width, size.height);
        }
        Graphics graphics2 = graphics;
        if (this.offscreen != null) {
            graphics2 = this.offscreen.getGraphics();
        }
        graphics2.setColor(this.getBackground());
        graphics2.fillRect(0, 0, size.width, size.height);
        int n = 5;
        int height = 5;
        if (this.header != null && this.header.length() > 0) {
            graphics2.setFont(this.headerFont);
            graphics2.setColor(this.headerColor);
            final FontMetrics fontMetrics = graphics2.getFontMetrics();
            if (this.headerAlignment == 0) {
                n = size.width / 2 - fontMetrics.stringWidth(this.header) / 2;
            }
            else if (this.headerAlignment == 1) {
                n = size.width - fontMetrics.stringWidth(this.header) - 5;
            }
            height = fontMetrics.getHeight();
            graphics2.drawString(this.header, n, height);
        }
        final Graphics create = graphics2.create();
        if (this.border == 0) {
            create.clipRect(0, height + 10, 10000, 10000);
        }
        else {
            create.clipRect(0, height + 10, 10000, size.height - (height + 15));
        }
        this.paintNewsItems(create);
        create.dispose();
        this.paintBorder(graphics2, height + 5);
        if (graphics2 != graphics) {
            graphics.drawImage(this.offscreen, 0, 0, this);
            graphics2.dispose();
        }
    }
    
    private int paintNewsItem(final Graphics graphics, final int n, int n2) {
        final String[] array = this.newsItems.elementAt(n);
        if (array == null || array.length != 3) {
            return n2;
        }
        int height = 12;
        if (array[0] != null && array[0].trim().length() > 0) {
            graphics.setFont(this.newsHeaderFont);
            graphics.setColor(this.newsHeaderColor);
            graphics.drawString(array[0], 10, n2);
            n2 += graphics.getFontMetrics().getHeight();
        }
        int x = 10;
        final Dimension size = this.size();
        if (array[1] != null && array[1].trim().length() > 0) {
            graphics.setFont(this.newsFont);
            graphics.setColor(this.newsColor);
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            height = fontMetrics.getHeight();
            final StringTokenizer stringTokenizer = new StringTokenizer(array[1]);
            while (stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                final int stringWidth = fontMetrics.stringWidth(nextToken);
                if (x + stringWidth < size.width) {
                    graphics.drawString(nextToken, x, n2);
                    x += stringWidth + fontMetrics.stringWidth(" ");
                }
                else {
                    final int n3 = 10;
                    n2 += height;
                    graphics.drawString(nextToken, n3, n2);
                    x = n3 + (stringWidth + fontMetrics.stringWidth(" "));
                }
            }
        }
        if (array[2] != null && array[2].trim().length() > 0) {
            graphics.setFont(this.newsFont);
            final FontMetrics fontMetrics2 = graphics.getFontMetrics();
            final int stringWidth2 = fontMetrics2.stringWidth(this.moreLinkText);
            if (x + stringWidth2 + 10 > size.width) {
                x = 10;
                n2 += fontMetrics2.getHeight();
            }
            graphics.setColor(this.moreLinkColor);
            graphics.drawString(this.moreLinkText, x, n2);
            final Rectangle rectangle = this.linkPositions.elementAt(n);
            rectangle.x = x;
            rectangle.y = n2 - fontMetrics2.getHeight();
            rectangle.width = stringWidth2;
            rectangle.height = fontMetrics2.getHeight();
            this.checkCursor(this.mousePosition.x, this.mousePosition.y);
        }
        return n2 += height * 2;
    }
    
    private int processNews(final StreamTokenizer streamTokenizer, final int n, final int n2) throws IOException {
        if (streamTokenizer == null) {
            throw new IllegalArgumentException("xml stream tokenizer is NULL");
        }
        if (n != 0) {
            throw new IllegalArgumentException("XML parser not in STATE_NEWS");
        }
        if (streamTokenizer.nextToken() == -3) {
            if (streamTokenizer.sval.equalsIgnoreCase(this.newsXMLTag)) {
                this.newsItems.addElement(new String[3]);
                this.linkPositions.addElement(new Rectangle(0, 0, 0, 0));
                return 1;
            }
        }
        else if (streamTokenizer.ttype == -1) {
            return 4;
        }
        this.parserError = "expecting " + this.newsXMLTag;
        return 5;
    }
    
    private int processHeader(final StreamTokenizer streamTokenizer, final int n, final int n2) throws IOException {
        if (streamTokenizer == null) {
            throw new IllegalArgumentException("xml stream tokenizer is NULL");
        }
        if (n != 1) {
            throw new IllegalArgumentException("XML parser not in STATE_HEADER");
        }
        String string = "";
        if (streamTokenizer.nextToken() != -3 || !streamTokenizer.sval.equalsIgnoreCase(this.newsHeaderXMLTag)) {
            this.parserError = "expecting " + this.newsHeaderXMLTag;
            return 5;
        }
        while (true) {
            streamTokenizer.nextToken();
            if (streamTokenizer.ttype != -3) {
                this.parserError = "expecting header word";
                return 5;
            }
            if (streamTokenizer.sval.equalsIgnoreCase(this.newsHeaderXMLTagEnd)) {
                ((String[])this.newsItems.elementAt(n2))[0] = string.trim();
                return 2;
            }
            string = string + streamTokenizer.sval + " ";
        }
    }
    
    private int processBody(final StreamTokenizer streamTokenizer, final int n, final int n2) throws IOException {
        if (streamTokenizer == null) {
            throw new IllegalArgumentException("xml stream tokenizer is NULL");
        }
        if (n != 2) {
            throw new IllegalArgumentException("XML parser not in STATE_BODY");
        }
        String string = "";
        if (streamTokenizer.nextToken() != -3 || !streamTokenizer.sval.equalsIgnoreCase(this.newsBodyXMLTag)) {
            this.parserError = "expecting " + this.newsBodyXMLTag;
            return 5;
        }
        while (true) {
            streamTokenizer.nextToken();
            if (streamTokenizer.ttype != -3) {
                this.parserError = "expecting body or " + this.newsBodyXMLTagEnd;
                return 5;
            }
            if (streamTokenizer.sval.equalsIgnoreCase(this.newsBodyXMLTagEnd)) {
                ((String[])this.newsItems.elementAt(n2))[1] = string.trim();
                return 3;
            }
            string = string + streamTokenizer.sval + " ";
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    private static Color createColor(String lowerCase) {
        if (lowerCase == null || lowerCase.length() == 0) {
            return Color.black;
        }
        if (lowerCase.indexOf(",") >= 0) {
            final StringTokenizer stringTokenizer = new StringTokenizer(lowerCase, ", ");
            try {
                return new Color(Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()));
            }
            catch (Exception ex) {
                System.out.println("Invalid RGB color: " + lowerCase);
                return Color.black;
            }
        }
        if (lowerCase.startsWith("#")) {
            try {
                return new Color(Integer.parseInt(lowerCase.substring(1, 3), 16), Integer.parseInt(lowerCase.substring(3, 5), 16), Integer.parseInt(lowerCase.substring(5, 7), 16));
            }
            catch (Exception ex2) {
                System.out.println("Invalid hex color: " + lowerCase);
                return Color.black;
            }
        }
        lowerCase = lowerCase.toLowerCase();
        if (lowerCase.equals("black")) {
            return Color.black;
        }
        if (lowerCase.equals("blue")) {
            return Color.blue;
        }
        if (lowerCase.equals("cyan")) {
            return Color.cyan;
        }
        if (lowerCase.equals("darkgray")) {
            return Color.darkGray;
        }
        if (lowerCase.equals("gray")) {
            return Color.gray;
        }
        if (lowerCase.equals("green")) {
            return Color.green;
        }
        if (lowerCase.equals("lightgray")) {
            return Color.lightGray;
        }
        if (lowerCase.equals("magenta")) {
            return Color.magenta;
        }
        if (lowerCase.equals("orange")) {
            return Color.orange;
        }
        if (lowerCase.equals("pink")) {
            return Color.pink;
        }
        if (lowerCase.equals("red")) {
            return Color.red;
        }
        if (lowerCase.equals("white")) {
            return Color.white;
        }
        if (lowerCase.equals("yellow")) {
            return Color.yellow;
        }
        System.out.println("Invalid color name: " + lowerCase);
        return Color.black;
    }
    
    private void checkCursor(final int n, final int n2) {
        final int size = this.linkPositions.size();
        this.lastLinkSelection = -1;
        Rectangle rectangle = null;
        for (int i = 0; i < size; ++i) {
            rectangle = (Rectangle)this.linkPositions.elementAt(i);
            if (rectangle.inside(n, n2)) {
                this.lastLinkSelection = i;
                break;
            }
            rectangle = null;
        }
        if (rectangle != null && rectangle.inside(n, n2)) {
            if (this.CURSOR_LINK != null && this.currentCursor != this.CURSOR_LINK) {
                this.setCursor((Cursor)this.CURSOR_LINK);
                this.currentCursor = this.CURSOR_LINK;
            }
        }
        else if (this.CURSOR_DEFAULT != null && this.currentCursor != this.CURSOR_DEFAULT) {
            this.setCursor((Cursor)this.CURSOR_DEFAULT);
            this.currentCursor = this.CURSOR_DEFAULT;
        }
    }
    
    private void loadNews(final String s) {
        if (s == null || s.trim().length() == 0) {
            return;
        }
        final URL url = this.createURL(s);
        if (url != null) {
            try {
                final InputStream openStream = url.openStream();
                final DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(openStream));
                final StringBuffer sb = new StringBuffer();
                String line;
                while ((line = dataInputStream.readLine()) != null) {
                    sb.append(line);
                    sb.append("\n");
                }
                dataInputStream.close();
                openStream.close();
                this.parseNews(sb.toString());
            }
            catch (IOException ex2) {
                System.out.println("Could not read newsURL: " + url.toExternalForm());
            }
            catch (SecurityException ex) {
                System.out.println("Error reading from the URL resource: " + url.toExternalForm());
                ex.printStackTrace();
            }
        }
    }
    
    public void start() {
        if (this.scroller == null) {
            (this.scroller = new Thread(this)).start();
        }
    }
    
    private void parseNews(final String s) throws IOException {
        if (s == null) {
            return;
        }
        final StreamTokenizer streamTokenizer = new StreamTokenizer(new StringBufferInputStream(s));
        streamTokenizer.resetSyntax();
        streamTokenizer.wordChars(33, 255);
        streamTokenizer.whitespaceChars(0, 32);
        int i = 0;
        int n = -1;
        while (i != 4) {
            try {
                switch (i) {
                    case 0: {
                        ++n;
                        i = this.processNews(streamTokenizer, i, n);
                        continue;
                    }
                    case 1: {
                        i = this.processHeader(streamTokenizer, i, n);
                        continue;
                    }
                    case 2: {
                        i = this.processBody(streamTokenizer, i, n);
                        continue;
                    }
                    case 3: {
                        i = this.processLink(streamTokenizer, i, n);
                        continue;
                    }
                    case 5: {
                        System.out.println("XML ERROR: " + this.parserError);
                        i = 4;
                        continue;
                    }
                }
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    private void paintBorder(final Graphics graphics, final int n) {
        final Dimension size = this.size();
        switch (this.border) {
            case 1: {
                graphics.setColor((this.borderColor != null) ? this.borderColor : Color.black);
                graphics.drawRect(0, 0, size.width - 1, size.height - 1);
                graphics.drawLine(0, n, size.width, n);
                break;
            }
            case 3: {
                final Color color = (this.borderColor != null) ? this.borderColor : this.getBackground();
                graphics.setColor(color);
                graphics.draw3DRect(0, 0, size.width - 1, size.height - 1, true);
                graphics.setColor(color.darker());
                graphics.drawLine(1, n, size.width, n);
                graphics.setColor(color.brighter());
                graphics.drawLine(1, n + 1, size.width - 2, n + 1);
                break;
            }
            case 2: {
                final Color color2 = (this.borderColor != null) ? this.borderColor : this.getBackground();
                graphics.setColor(color2);
                graphics.draw3DRect(0, 0, size.width - 1, size.height - 1, false);
                graphics.setColor(color2.brighter());
                graphics.drawLine(1, n, size.width, n);
                graphics.setColor(color2.darker());
                graphics.drawLine(1, n + 1, size.width - 2, n + 1);
                break;
            }
            case 4: {
                final Color color3 = (this.borderColor != null) ? this.borderColor : this.getBackground();
                graphics.setColor(color3);
                graphics.draw3DRect(0, 0, size.width - 1, size.height - 1, false);
                graphics.draw3DRect(1, 1, size.width - 3, size.height - 3, true);
                graphics.setColor(color3.darker());
                graphics.drawLine(1, n, size.width - 3, n);
                graphics.setColor(color3.brighter());
                graphics.drawLine(1, n + 1, size.width - 3, n + 1);
                break;
            }
            case 5: {
                final Color color4 = (this.borderColor != null) ? this.borderColor : this.getBackground();
                graphics.setColor(color4);
                graphics.draw3DRect(0, 0, size.width - 1, size.height - 1, true);
                graphics.draw3DRect(1, 1, size.width - 3, size.height - 3, false);
                graphics.setColor(color4.brighter());
                graphics.drawLine(1, n, size.width - 3, n);
                graphics.setColor(color4.darker());
                graphics.drawLine(1, n + 1, size.width - 3, n + 1);
                break;
            }
        }
    }
    
    private void openURL(final int n) {
        if (n < 0 || n >= this.newsItems.size()) {
            return;
        }
        final String s = ((String[])this.newsItems.elementAt(n))[2];
        if (s != null && s.trim().length() > 0) {
            final URL url = this.createURL(s);
            if (url != null) {
                final AppletContext appletContext = this.getAppletContext();
                if (appletContext != null) {
                    appletContext.showDocument(url, this.target);
                }
            }
        }
    }
    
    public void setParameter(String trim, String target) {
        if (trim == null) {
            return;
        }
        trim = trim.toLowerCase().trim();
        if (trim.equals("background")) {
            if (target != null && target.trim().length() > 0) {
                this.setBackground(createColor(target));
            }
            else {
                this.setBackground(Color.white);
            }
        }
        else if (trim.equals("header")) {
            this.header = target;
        }
        else if (trim.equals("headerfont")) {
            this.headerFont = createFont(target, "Arial", 1, 16);
        }
        else if (trim.equals("headercolor")) {
            if (target != null && target.trim().length() > 0) {
                this.headerColor = createColor(target);
            }
            else {
                this.headerColor = Color.black;
            }
        }
        else if (trim.equals("headeralignment")) {
            this.headerAlignment = 0;
            if (target != null && target.trim().length() > 0) {
                target = target.trim().toLowerCase();
                if (target.equals("left")) {
                    this.headerAlignment = -1;
                }
                else if (target.equals("center")) {
                    this.headerAlignment = 0;
                }
                else if (target.equals("right")) {
                    this.headerAlignment = 1;
                }
                else {
                    System.out.println("invalid header alignment: " + trim + " " + target + " (left,center,right)");
                }
            }
        }
        else if (trim.equals("newsheaderfont")) {
            this.newsHeaderFont = createFont(target, "Arial", 1, 11);
        }
        else if (trim.equals("newsheadercolor")) {
            if (target != null && target.trim().length() > 0) {
                this.newsHeaderColor = createColor(target);
            }
            else {
                this.newsHeaderColor = Color.black;
            }
        }
        else if (trim.equals("newsfont")) {
            this.newsFont = createFont(target, "Arial", 0, 11);
        }
        else if (trim.equals("newscolor")) {
            if (target != null && target.trim().length() > 0) {
                this.newsColor = createColor(target);
            }
            else {
                this.newsColor = Color.black;
            }
        }
        else if (trim.equals("scrollspeed")) {
            this.scrollSpeed = 5;
            if (target != null && target.trim().length() > 0) {
                try {
                    this.scrollSpeed = Integer.parseInt(target);
                    this.scrollSpeed = Math.min(10, this.scrollSpeed);
                    this.scrollSpeed = Math.max(0, this.scrollSpeed);
                }
                catch (NumberFormatException ex2) {
                    System.out.println("invalid scrollspeed: " + target);
                }
            }
            if (this.scrollSpeed > 0 && (this.scroller == null || !this.scroller.isAlive())) {
                (this.scroller = new Thread(this)).start();
            }
        }
        else if (trim.equals("morelinktext")) {
            if (target != null && target.trim().length() > 0) {
                this.moreLinkText = target;
            }
            else {
                this.moreLinkText = "more";
            }
        }
        else if (trim.equals("morelinkcolor")) {
            this.moreLinkColor = Color.red;
            if (target != null && target.trim().length() > 0) {
                this.moreLinkColor = createColor(target);
            }
        }
        else if (trim.equals("target")) {
            if (target != null && target.trim().length() > 0) {
                this.target = target;
            }
            else {
                this.target = "_self";
            }
        }
        else if (trim.equals("newsxmltag")) {
            if (target != null && target.trim().length() > 0) {
                this.newsXMLTag = "<" + target + ">";
                this.newsXMLTagEnd = "</" + target + ">";
            }
            else {
                this.newsXMLTag = "<news>";
                this.newsXMLTagEnd = "</news>";
            }
        }
        else if (trim.equals("newsheaderxmltag")) {
            if (target != null && target.trim().length() > 0) {
                this.newsHeaderXMLTag = "<" + target + ">";
                this.newsHeaderXMLTagEnd = "</" + target + ">";
            }
            else {
                this.newsHeaderXMLTag = "<header>";
                this.newsHeaderXMLTagEnd = "</header>";
            }
        }
        else if (trim.equals("newsbodyxmltag")) {
            if (target != null && target.trim().length() > 0) {
                this.newsBodyXMLTag = "<" + target + ">";
                this.newsBodyXMLTagEnd = "</" + target + ">";
            }
            else {
                this.newsBodyXMLTag = "<body>";
                this.newsBodyXMLTagEnd = "</body>";
            }
        }
        else if (trim.equals("newslinkxmltag")) {
            if (target != null && target.trim().length() > 0) {
                this.newsLinkXMLTag = "<" + target + ">";
                this.newsLinkXMLTagEnd = "</" + target + ">";
            }
            else {
                this.newsLinkXMLTag = "<link>";
                this.newsLinkXMLTagEnd = "</link>";
            }
        }
        else if (trim.equals("news")) {
            try {
                this.parseNews(target);
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        else if (trim.equals("newsurl")) {
            this.loadNews(target);
        }
        else if (trim.equals("border")) {
            this.border = 0;
            this.borderColor = null;
            if (target != null) {
                target = target.trim().toLowerCase();
                final int index = target.indexOf(",");
                if (index > 0 && index < target.length() - 1) {
                    this.borderColor = createColor(target.substring(index + 1).trim());
                }
                if (target.startsWith("solid")) {
                    this.border = 1;
                }
                else if (target.startsWith("up")) {
                    this.border = 3;
                }
                else if (target.startsWith("down")) {
                    this.border = 2;
                }
                else if (target.startsWith("grooved")) {
                    this.border = 4;
                }
                else if (target.startsWith("embossed")) {
                    this.border = 5;
                }
            }
        }
        else if (trim.equals("wrapspace")) {
            this.wrapSpace = -1;
            if (target != null && target.trim().length() > 0) {
                try {
                    this.wrapSpace = Integer.parseInt(target);
                }
                catch (NumberFormatException ex3) {
                    System.out.println("Invalid wrap space: " + target);
                }
            }
        }
        else {
            System.out.println("unkown parameter: " + trim + " " + target);
        }
    }
    
    private URL createURL(final String s) {
        if (s == null) {
            return null;
        }
        URL url = null;
        try {
            url = new URL(s);
        }
        catch (MalformedURLException ex) {
            final URL documentBase = this.getDocumentBase();
            if (documentBase == null) {
                return null;
            }
            String s2 = documentBase.toExternalForm();
            final int lastIndex = s2.lastIndexOf("?");
            if (lastIndex > -1) {
                s2 = s2.substring(0, lastIndex);
            }
            if (s.startsWith("/")) {
                final String file = documentBase.getFile();
                if (file != null && file.trim().length() > 0) {
                    final int lastIndex2 = s2.lastIndexOf(file);
                    if (lastIndex2 >= 0) {
                        s2 = s2.substring(0, lastIndex2);
                    }
                }
            }
            else if (!s2.endsWith("/") && !s.startsWith("#")) {
                s2 = s2.substring(0, s2.lastIndexOf("/") + 1);
            }
            if (s2.startsWith("file:/\\")) {
                s2 = "file://" + s2.substring(8);
            }
            try {
                url = new URL(s2 + s);
            }
            catch (MalformedURLException ex2) {
                System.out.println("Invalid URL: " + s2);
            }
        }
        return url;
    }
    
    public void run() {
        if (this.firstStarted) {
            try {
                Thread.sleep(2000L);
            }
            catch (InterruptedException ex) {}
            finally {
                this.firstStarted = false;
            }
        }
        if (this.scrollSpeed > 0) {
            while (this.scrollSpeed > 0) {
                --this.offset;
                this.repaint();
                try {
                    Thread.sleep(220 - this.scrollSpeed * 20);
                }
                catch (InterruptedException ex2) {}
            }
        }
    }
    
    public void init() {
        this.setParameter("background", this.getParameter("background"));
        this.setParameter("header", this.getParameter("header"));
        this.setParameter("headerFont", this.getParameter("headerFont"));
        this.setParameter("headerColor", this.getParameter("headerColor"));
        this.setParameter("headerAlignment", this.getParameter("headerAlignment"));
        this.setParameter("newsHeaderFont", this.getParameter("newsHeaderFont"));
        this.setParameter("newsHeaderColor", this.getParameter("newsHeaderColor"));
        this.setParameter("newsFont", this.getParameter("newsFont"));
        this.setParameter("newsColor", this.getParameter("newsColor"));
        this.setParameter("moreLinkText", this.getParameter("moreLinkText"));
        this.setParameter("moreLinkColor", this.getParameter("moreLinkColor"));
        this.setParameter("target", this.getParameter("target"));
        this.setParameter("newsXMLTag", this.getParameter("newsXMLTag"));
        this.setParameter("newsHeaderXMLTag", this.getParameter("newsHeaderXMLTag"));
        this.setParameter("newsBodyXMLTag", this.getParameter("newsBodyXMLTag"));
        this.setParameter("newsLinkXMLTag", this.getParameter("newsLinkXMLTag"));
        this.setParameter("newsURL", this.getParameter("newsURL"));
        this.setParameter("news", this.getParameter("news"));
        this.setParameter("scrollSpeed", this.getParameter("scrollSpeed"));
        this.setParameter("border", this.getParameter("border"));
        this.setParameter("wrapSpace", this.getParameter("wrapSpace"));
        this.firstStarted = true;
    }
    
    private static Font createFont(String s, final String s2, final int n, final int n2) {
        s = ((s != null) ? s : "");
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        String nextToken = s2;
        int n3 = Math.max(0, n2);
        int n4 = n;
        if (stringTokenizer.hasMoreTokens()) {
            nextToken = stringTokenizer.nextToken();
        }
        if (stringTokenizer.hasMoreTokens()) {
            final String trim = stringTokenizer.nextToken().toLowerCase().trim();
            if (trim.equals("bold")) {
                n4 = 1;
            }
            else if (trim.equals("italic")) {
                n4 = 2;
            }
            else if (trim.equals("bolditalic") || trim.equals("italicbold")) {
                n4 = 3;
            }
            else {
                n4 = 0;
            }
        }
        if (stringTokenizer.hasMoreTokens()) {
            try {
                n3 = Integer.parseInt(stringTokenizer.nextToken().trim());
            }
            catch (NumberFormatException ex) {
                n3 = 12;
            }
        }
        return new Font(nextToken, n4, n3);
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 501: {
                this.lastYPos = event.y;
                if (this.scroller != null) {
                    this.scroller.stop();
                }
                return true;
            }
            case 506: {
                this.offset -= this.lastYPos - event.y;
                this.lastYPos = event.y;
                this.repaint();
                return true;
            }
            case 502: {
                if (this.lastLinkSelection >= 0) {
                    this.openURL(this.lastLinkSelection);
                }
                if (this.scrollSpeed > 0) {
                    (this.scroller = new Thread(this)).start();
                }
                return true;
            }
            case 503: {
                this.mousePosition.x = event.x;
                this.mousePosition.y = event.y;
                this.checkCursor(event.x, event.y);
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    private void paintNewsItems(final Graphics graphics) {
        final Dimension size = this.size();
        int i = this.offset;
        if (i > size.height + 30) {
            i = (this.offset = 50);
        }
        final int size2 = this.newsItems.size();
        int n = 0;
        int n2 = 0;
        while (i < size.height + 20) {
            if (n == 0 && i < 0 && n2 > 0) {
                this.offset = i;
            }
            i = this.paintNewsItem(graphics, n, i);
            if (n == size2 - 1) {
                if (this.wrapSpace <= 0) {
                    break;
                }
                i += this.wrapSpace;
            }
            if (++n >= size2) {
                n = 0;
                ++n2;
            }
        }
        if (i < 20) {
            this.offset = size.height + 20;
        }
    }
    
    private int processLink(final StreamTokenizer streamTokenizer, final int n, final int n2) throws IOException {
        if (streamTokenizer == null) {
            throw new IllegalArgumentException("xml stream tokenizer is NULL");
        }
        if (n != 3) {
            throw new IllegalArgumentException("XML parser not in STATE_LINK");
        }
        String string = "";
        streamTokenizer.nextToken();
        if (streamTokenizer.ttype != -3 || !streamTokenizer.sval.equalsIgnoreCase(this.newsLinkXMLTag)) {
            this.parserError = "expecting " + this.newsLinkXMLTag;
            return 5;
        }
        while (true) {
            streamTokenizer.nextToken();
            if (streamTokenizer.ttype == -3 && streamTokenizer.sval.equalsIgnoreCase(this.newsLinkXMLTagEnd)) {
                break;
            }
            string = string + streamTokenizer.sval + " ";
        }
        ((String[])this.newsItems.elementAt(n2))[2] = string.trim();
        streamTokenizer.nextToken();
        if (streamTokenizer.ttype == -3 && streamTokenizer.sval.equalsIgnoreCase(this.newsXMLTagEnd)) {
            return 0;
        }
        this.parserError = "expecting " + this.newsXMLTagEnd;
        return 5;
    }
}
