// 
// Decompiled by Procyon v0.5.30
// 

package v1com.daeja.v1.v1base;

import ji.io.h;
import java.awt.Toolkit;
import ji.font.d1;
import ji.res.g8;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.PixelGrabber;
import java.awt.Color;
import ji.util.d;
import ji.v1event.af;
import ji.font.ct;
import ji.font.j;
import ji.util.i;
import java.awt.Component;
import java.awt.Dimension;
import java.io.FileInputStream;
import java.awt.Font;

public class jiP86
{
    private static boolean booLibraryTested;
    private static boolean booLoaded;
    private boolean printDialog;
    private boolean doImageText;
    private int[] textData;
    private byte[] textMonoData;
    private int textSize;
    private int monoTextSize;
    private int textWidth;
    private int textHeight;
    private Font font;
    private double fontScalingFactor;
    private static final int BLACK_VALUE = -16777216;
    private static final int WHITE_VALUE = -1;
    
    private static native int jiExtCheck();
    
    private static native boolean jiIsActive();
    
    private static native boolean jiIsDocStarted();
    
    private static native boolean jiIsReady();
    
    private static native int jiExtGetPrintJob(final String p0, final int p1, final int p2, final boolean p3, final int p4, final boolean p5, final String p6, final String p7, final int p8, final int p9, final boolean p10);
    
    private static native void jiExtDrawString(final String p0, final int p1);
    
    private static native void jiExtDrawStringImage(final int[] p0, final int p1, final int p2, final int p3, final int p4, final boolean p5);
    
    private static native void jiExtDrawMonoStringImage(final byte[] p0, final int p1, final int p2, final int p3, final int p4, final boolean p5);
    
    private static native boolean jiExtIsTopDown();
    
    private static native boolean jiExtBanding();
    
    private static native int jiExtGetPageWidth();
    
    private static native int jiExtGetPageHeight();
    
    private static native int jiExtGetXRes();
    
    private static native int jiExtGetYRes();
    
    private static native int jiExtGetLineHeight();
    
    private static native int jiSetPrinter(final String p0);
    
    private static native int jiExtGetCopies();
    
    private static native boolean jiStartDoc(final String p0, final boolean p1);
    
    private static native boolean jiSetDebug(final boolean p0);
    
    private static native boolean jiSetMessages(final boolean p0);
    
    private static native void jiInitTopWindow();
    
    private static native void jiReverseMonoColors();
    
    private static native void jiForgetPrintSettings();
    
    private static native boolean jiEndDoc();
    
    private static native boolean jiAbortDoc();
    
    private static native boolean jiStartPage(final String p0, final String p1);
    
    private static native boolean jiStartPageImage(final int[] p0, final int p1, final int p2, final int p3, final int[] p4, final int p5, final int p6, final int p7);
    
    private static native boolean jiStartMonoPageImage(final byte[] p0, final int p1, final int p2, final int p3, final byte[] p4, final int p5, final int p6, final int p7);
    
    private static native boolean jiStartPageTrailer(final int[] p0, final int p1, final int p2, final int p3, final int[] p4, final int p5, final int p6, final int p7);
    
    private static native boolean jiStartMonoPageTrailer(final byte[] p0, final int p1, final int p2, final int p3, final byte[] p4, final int p5, final int p6, final int p7);
    
    private static native boolean jiEndPage();
    
    private static native boolean jiDrawImage(final int[] p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6, final int p7, final boolean p8, final boolean p9, final int p10, final boolean p11, final int p12, final boolean p13);
    
    private static native boolean jiDrawMonoImage(final byte[] p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6, final int p7, final byte p8, final byte p9, final byte p10, final byte p11, final boolean p12, final boolean p13, final int p14, final boolean p15, final int p16, final boolean p17);
    
    private static native String jiExtReleaseResources();
    
    private static native int jiExtGetFromPage();
    
    private static native int jiExtGetToPage();
    
    private static native int jiExtGetMinPage();
    
    private static native int jiExtGetMaxPage();
    
    private static native void jiExtSetOriginalSize(final boolean p0, final int p1, final int p2);
    
    private static native boolean jiExtIsImageLargerThanPage(final int p0, final int p1, final int p2, final int p3);
    
    private static native void jiExtSetEndOfLastImage(final int p0);
    
    private static native int jiExtGetEndOfLastImage();
    
    private static native void jiSetImagesPerPage(final int p0);
    
    private static native void jiExtSetFontScalingFactor(final double p0);
    
    private static native void jiExtEndNoteAnnotationsPage();
    
    private static native void jiExtPrintNoteAnnotationText(final int[] p0, final int p1, final int p2, final int p3);
    
    private static native void jiExtPrintMonoNoteAnnotationText(final byte[] p0, final int p1, final int p2, final int p3);
    
    public jiP86() {
        this.printDialog = true;
        this.doImageText = true;
        this.textData = null;
        this.textMonoData = null;
        this.textSize = 0;
        this.monoTextSize = 0;
        this.textWidth = 0;
        this.textHeight = 0;
        this.font = null;
        this.fontScalingFactor = 1.0;
    }
    
    public void load() {
        try {
            jiExtCheck();
            jiP86.booLoaded = true;
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
    }
    
    public void initTopWindow() {
        jiInitTopWindow();
    }
    
    public void reverseMonoColors() {
        jiReverseMonoColors();
    }
    
    public void forgetPrintSettings() {
        jiForgetPrintSettings();
    }
    
    public void setDebug(final boolean b) {
        jiSetDebug(b);
    }
    
    public void setMessages(final boolean b) {
        jiSetMessages(b);
    }
    
    public boolean isLoaded() {
        return jiP86.booLoaded;
    }
    
    public boolean isTopDown() {
        return jiP86.booLoaded && jiExtIsTopDown();
    }
    
    private static final String getJavaVendor() {
        String s;
        try {
            s = System.getProperty("java.vendor");
        }
        catch (Exception ex) {
            s = ex.toString();
        }
        return s;
    }
    
    private final boolean isNetscape() {
        boolean b = false;
        try {
            final String javaVendor = getJavaVendor();
            if (javaVendor != null && javaVendor.toLowerCase().indexOf("netscape") >= 0) {
                b = true;
            }
        }
        catch (Exception ex) {}
        return b;
    }
    
    private String getLastPrinter(final String s, final boolean b) {
        String s2 = "";
        try {
            final FileInputStream fileInputStream = new FileInputStream(s);
            if (fileInputStream != null) {
                final byte[] array = new byte[fileInputStream.available()];
                fileInputStream.read(array);
                fileInputStream.close();
                s2 = new String(array);
            }
        }
        catch (Exception ex) {}
        return s2;
    }
    
    public boolean getPrintJob(final String s, final int n, final int n2, final boolean b, final int n3, final boolean printDialog, final String s2, final boolean b2, final int n4, final int n5, final boolean b3) {
        boolean b4 = false;
        this.printDialog = printDialog;
        if (jiP86.booLoaded) {
            try {
                jiExtGetPrintJob(s, n, n2, b, n3, printDialog, this.getLastPrinter(s2, b2), s2, n4, n5, b3);
                while (jiIsActive()) {
                    Thread.sleep(100L);
                }
                if (jiIsReady()) {
                    b4 = true;
                }
            }
            catch (Throwable t) {
                jiP86.booLoaded = false;
                t.printStackTrace();
            }
        }
        return b4;
    }
    
    public final int getFromPage() {
        if (!this.printDialog) {
            return -1;
        }
        return jiExtGetFromPage();
    }
    
    public final int getToPage() {
        if (!this.printDialog) {
            return -1;
        }
        return jiExtGetToPage();
    }
    
    public final Dimension getPageDimension() {
        if (jiP86.booLoaded) {
            return new Dimension(jiExtGetPageWidth(), jiExtGetPageHeight());
        }
        return null;
    }
    
    public final int getCopies() {
        if (jiP86.booLoaded) {
            return jiExtGetCopies();
        }
        return 0;
    }
    
    public final void setPrinter(final String s) {
        if (jiP86.booLoaded) {
            jiSetPrinter(s);
        }
    }
    
    public final int getXResolution() {
        if (jiP86.booLoaded) {
            return jiExtGetXRes();
        }
        return 0;
    }
    
    public final int getYResolution() {
        if (jiP86.booLoaded) {
            return jiExtGetYRes();
        }
        return 0;
    }
    
    public final int getPageLineHeight() {
        int jiExtGetLineHeight = 0;
        if (jiP86.booLoaded) {
            jiExtGetLineHeight = jiExtGetLineHeight();
        }
        return jiExtGetLineHeight;
    }
    
    public final int getEndOfLastImage() {
        int jiExtGetEndOfLastImage = 0;
        if (jiP86.booLoaded) {
            jiExtGetEndOfLastImage = jiExtGetEndOfLastImage();
        }
        return jiExtGetEndOfLastImage;
    }
    
    public final void setImagesPerPage(final int n) {
        if (jiP86.booLoaded) {
            jiSetImagesPerPage(n);
        }
    }
    
    public final void setEndOfLastImage(final int n) {
        if (jiP86.booLoaded) {
            jiExtSetEndOfLastImage(n);
        }
    }
    
    private final int getImageTextHeight(final String s, final Component component) {
        int n = 0;
        if (jiP86.booLoaded && s != null && s.length() > 0) {
            final int fontHeight = this.getFontHeight(component, this.getFont(component));
            int n2 = 1;
            final char[] charArray = s.toCharArray();
            for (int i = 0; i < charArray.length; ++i) {
                if (charArray[i] == '\n' || charArray[i] == '\r') {
                    ++n2;
                }
            }
            n = fontHeight * n2;
        }
        return n;
    }
    
    public final int getLineHeight(final String s, final Component component) {
        int n = 0;
        if (jiP86.booLoaded && s != null && s.length() > 0) {
            int jiExtGetLineHeight;
            if (this.doImageText) {
                if (i.c(65)) {
                    try {
                        this.fontScalingFactor = j.m() / j.p * 3.2;
                        n = j.a(component, null, "jip86", new ct(j.k(), j.p, j.l(), false, true), 1.0).h();
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                else {
                    n = this.getFontHeight(component, this.getFont(component));
                    this.fontScalingFactor = 1.0;
                }
                jiExtGetLineHeight = (int)(n * (jiExtGetYRes() / 240.0 * this.fontScalingFactor));
            }
            else {
                jiExtGetLineHeight = jiExtGetLineHeight();
            }
            int n2 = 1;
            final char[] charArray = s.toCharArray();
            for (int i = 0; i < charArray.length; ++i) {
                if (charArray[i] == '\n' || charArray[i] == '\r') {
                    ++n2;
                }
            }
            n = jiExtGetLineHeight * n2;
        }
        return n;
    }
    
    public final boolean startDoc(final String s, final boolean b) {
        if (jiP86.booLoaded) {
            if (b) {
                jiStartDoc(s, true);
                try {
                    while (jiIsActive()) {
                        Thread.sleep(100L);
                    }
                    if (!jiIsDocStarted()) {
                        jiStartDoc(s, false);
                    }
                }
                catch (InterruptedException ex) {}
            }
            else {
                jiStartDoc(s, false);
            }
            return jiIsDocStarted();
        }
        return false;
    }
    
    public final boolean endDoc() {
        return jiP86.booLoaded && jiEndDoc();
    }
    
    public final boolean abortDoc() {
        return jiP86.booLoaded && jiAbortDoc();
    }
    
    public final boolean startPage(final String s, final String s2, final Component component, final boolean b) {
        if (!jiP86.booLoaded) {
            return false;
        }
        if (!this.doImageText) {
            return jiStartPage(s, s2);
        }
        int[] textData = null;
        byte[] textMonoData = null;
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        int[] textData2 = null;
        byte[] textMonoData2 = null;
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        if (s != null) {
            if (i.c(65)) {
                this.getTextImageWithFont(component, "jip86", s, false, j.k(), j.m(), j.l(), false);
            }
            else {
                this.getTextImage(s, component, false);
            }
            if (i.c(71)) {
                textData = this.textData;
                n = this.textSize;
                n2 = this.textWidth;
                n3 = this.textHeight;
            }
            else {
                textMonoData = this.textMonoData;
                n = this.monoTextSize;
                n2 = this.textWidth;
                n3 = this.textHeight;
            }
        }
        if (s2 != null) {
            if (i.c(65)) {
                this.getTextImageWithFont(component, "jip86", s2, b, j.k(), j.m(), j.l(), false);
            }
            else {
                this.getTextImage(s2, component, b);
            }
            if (i.c(71)) {
                textData2 = this.textData;
                n4 = this.textSize;
                n5 = this.textWidth;
                n6 = this.textHeight;
            }
            else {
                textMonoData2 = this.textMonoData;
                n4 = this.monoTextSize;
                n5 = this.textWidth;
                n6 = this.textHeight;
            }
        }
        if (i.c(71)) {
            if (textData == null) {
                n = 0;
            }
            if (textData2 == null) {
                n4 = 0;
            }
            return jiStartPageImage(textData, n, n2, n3, textData2, n4, n5, n6);
        }
        if (textMonoData == null) {
            n = 0;
        }
        if (textMonoData2 == null) {
            n4 = 0;
        }
        return jiStartMonoPageImage(textMonoData, n, n2, n3, textMonoData2, n4, n5, n6);
    }
    
    public final void endNoteAnnotationPages() {
        jiExtEndNoteAnnotationsPage();
    }
    
    public final void startNoteAnnotationPages(final Component component, final String s) {
        logPrintText(s, "Starting note annotation text printing");
        final String ce = d.ce();
        String s2;
        if (ce == null) {
            s2 = d.b(1085, s);
        }
        else {
            s2 = d.b(ce, "<N>", "\n");
        }
        logPrintText(s, "Note annotation title:".concat(String.valueOf(String.valueOf(s2))));
        if (i.c(75)) {
            int o = j.o();
            if (o < 0) {
                o = j.n() * 2;
            }
            logPrintText(s, String.valueOf(String.valueOf(new StringBuffer("Creating title text image with font:").append(j.p()).append(" ").append(o))));
            this.getTextImageWithFont(component, s, s2, false, j.p(), o, false, false);
        }
        else {
            logPrintText(s, "Creating title text image using standard font");
            this.getTextImage(s2, component, false);
        }
        if (i.c(71)) {
            logPrintText(s, String.valueOf(String.valueOf(new StringBuffer("Printing color title:").append(this.textWidth).append(" ").append(this.textHeight).append(" ").append(this.textSize))));
            jiExtPrintNoteAnnotationText(this.textData, this.textSize, this.textWidth, this.textHeight);
        }
        else {
            logPrintText(s, String.valueOf(String.valueOf(new StringBuffer("Printing mono title:").append(this.textWidth).append(" ").append(this.textHeight).append(" ").append(this.monoTextSize))));
            jiExtPrintMonoNoteAnnotationText(this.textMonoData, this.monoTextSize, this.textWidth, this.textHeight);
        }
    }
    
    public final void printNoteAnnotationPageText(final Component component, final int n, final String[] array, final String[] array2, final String s, final int n2) {
        logPrintText(s, String.valueOf(String.valueOf(new StringBuffer("Printing note text for page").append(n).append(" ").append(array.length))));
        this.printNoteAnnotationPageHeader(component, s, n, n2);
        if (i.c(75)) {
            logPrintText(s, String.valueOf(String.valueOf(new StringBuffer("Getting note text with font:").append(j.p()).append(" ").append(j.n()))));
            this.getTextImageWithFont(component, s, "W", false, j.p(), j.n(), false, false);
        }
        else {
            logPrintText(s, "Getting note text with standard font");
            this.getTextImage("W", component, false);
        }
        final int n3 = (int)(this.getPageDimension().width / (this.textWidth * (jiExtGetYRes() / 240.0) * this.fontScalingFactor));
        for (int i = 0; i < array2.length; ++i) {
            final StringBuffer formatNoteAnnotationTextForPrinting = this.formatNoteAnnotationTextForPrinting(s, n3, array2[i], array[i]);
            logPrintText(s, formatNoteAnnotationTextForPrinting.toString());
            String substring = new String(formatNoteAnnotationTextForPrinting);
            int n4 = 0;
            while (substring.length() > 0 || n4 == 0) {
                String substring2;
                if (substring.length() == 0) {
                    n4 = 1;
                    substring2 = " \n";
                }
                else {
                    int n5 = substring.indexOf(10);
                    if (n5 == -1) {
                        n5 = substring.indexOf(13);
                    }
                    if (n5 > -1) {
                        if (n5 == 0) {
                            substring2 = " \n";
                        }
                        else {
                            substring2 = substring.substring(0, n5);
                        }
                        substring = substring.substring(n5 + 1);
                    }
                    else {
                        substring2 = substring;
                        substring = "";
                    }
                }
                this.printAnnotationText(component, s, substring2);
            }
        }
    }
    
    private void printNoteAnnotationPageHeader(final Component component, final String s, final int n, final int n2) {
        final StringBuffer sb = new StringBuffer("\n\n");
        final String cf = d.cf();
        String s2;
        if (cf == null) {
            s2 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(d.b(302, s)))).append(": ").append(n)));
        }
        else {
            s2 = d.b(d.b(d.b(d.b(d.b(d.b(cf, "##", "".concat(String.valueOf(String.valueOf(n2)))), "$pages", "".concat(String.valueOf(String.valueOf(d.b(377, s))))), "$page", "".concat(String.valueOf(String.valueOf(d.b(373, s))))), "$of", "".concat(String.valueOf(String.valueOf(d.b(380, s))))), "#", "".concat(String.valueOf(String.valueOf(n)))), "<N>", "\n");
        }
        sb.append(s2);
        sb.append("\n\n");
        this.printAnnotationText(component, s, sb.toString());
        logPrintText(s, "Page title:".concat(String.valueOf(String.valueOf(s2))));
    }
    
    private StringBuffer formatNoteAnnotationTextForPrinting(final String s, final int n, final String s2, final String s3) {
        final StringBuffer sb = new StringBuffer(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(d.b(1086, s)))).append(": ").append(s3).append("\n"))));
        if (s2.length() > n) {
            StringBuffer sb2 = new StringBuffer();
            int n2 = 0;
            try {
                for (int i = 0; i < s2.length(); ++i) {
                    if (s2.charAt(i) == '\n') {
                        sb.append(sb2.toString());
                        sb.append("\n");
                        sb2 = new StringBuffer();
                        n2 = 0;
                    }
                    else if (n2 > n) {
                        n2 = 0;
                        if (s2.charAt(i) == ' ') {
                            sb.append(sb2.toString());
                            sb.append("\n");
                            sb2 = new StringBuffer();
                        }
                        else if (sb2.length() == 0) {
                            sb.append("\n");
                            sb2.append(s2.charAt(i));
                        }
                        else if (sb2.length() >= n) {
                            sb.append(sb2.toString());
                            sb.append("\n");
                            sb2 = new StringBuffer();
                            sb2.append(s2.charAt(i));
                            ++n2;
                        }
                        else {
                            sb.append("\n");
                            sb2.append(s2.charAt(i));
                        }
                    }
                    else if (s2.charAt(i) == ' ') {
                        sb.append(sb2.toString());
                        sb.append(' ');
                        sb2 = new StringBuffer();
                        ++n2;
                    }
                    else {
                        sb2.append(s2.charAt(i));
                        ++n2;
                    }
                }
            }
            catch (Exception ex) {
                d.a(ex);
            }
            sb.append(sb2.toString());
        }
        else {
            sb.append(s2);
        }
        return sb;
    }
    
    private void printAnnotationText(final Component component, final String s, final String s2) {
        if (i.c(75)) {
            this.getTextImageWithFont(component, s, s2, false, j.p(), j.n(), false, false, false);
        }
        else {
            this.getTextImage(s2, component, false);
        }
        if (i.c(71)) {
            logPrintText(s, String.valueOf(String.valueOf(new StringBuffer("Printing color note text:").append(this.textWidth).append(" ").append(this.textHeight).append(" ").append(this.textSize))));
            jiExtPrintNoteAnnotationText(this.textData, this.textSize, this.textWidth, this.textHeight);
        }
        else {
            logPrintText(s, String.valueOf(String.valueOf(new StringBuffer("Printing mono note text:").append(this.textWidth).append(" ").append(this.textHeight).append(" ").append(this.monoTextSize))));
            jiExtPrintMonoNoteAnnotationText(this.textMonoData, this.monoTextSize, this.textWidth, this.textHeight);
        }
    }
    
    public final void startPageTrailer(final String s, final String s2, final Component component, final boolean b) {
        if (jiP86.booLoaded && this.doImageText) {
            int[] textData = null;
            byte[] textMonoData = null;
            int n = 0;
            int n2 = 0;
            int n3 = 0;
            int[] textData2 = null;
            byte[] textMonoData2 = null;
            int n4 = 0;
            int n5 = 0;
            int n6 = 0;
            if (s != null) {
                if (i.c(65)) {
                    this.getTextImageWithFont(component, "jip86", s, false, j.k(), j.m(), j.l(), false);
                }
                else {
                    this.getTextImage(s, component, false);
                }
                if (i.c(71)) {
                    textData = this.textData;
                    n = this.textSize;
                    n2 = this.textWidth;
                    n3 = this.textHeight;
                }
                else {
                    textMonoData = this.textMonoData;
                    n = this.monoTextSize;
                    n2 = this.textWidth;
                    n3 = this.textHeight;
                }
            }
            if (s2 != null) {
                if (i.c(65)) {
                    this.getTextImageWithFont(component, "jip86", s2, b, j.k(), j.m(), j.l(), false);
                }
                else {
                    this.getTextImage(s2, component, b);
                }
                if (i.c(71)) {
                    textData2 = this.textData;
                    n4 = this.textSize;
                    n5 = this.textWidth;
                    n6 = this.textHeight;
                }
                else {
                    textMonoData2 = this.textMonoData;
                    n4 = this.monoTextSize;
                    n5 = this.textWidth;
                    n6 = this.textHeight;
                }
            }
            if (i.c(71)) {
                jiStartPageTrailer(textData, n, n2, n3, textData2, n4, n5, n6);
            }
            else {
                jiStartMonoPageTrailer(textMonoData, n, n2, n3, textMonoData2, n4, n5, n6);
            }
        }
    }
    
    private final void getTextImage(final String s, final Component component, final boolean b) {
        jiExtSetFontScalingFactor(this.fontScalingFactor = 1.0);
        final Font font = this.getFont(component);
        this.textData = null;
        this.textSize = 0;
        this.textWidth = 0;
        this.textHeight = 0;
        try {
            if (s != null && s.length() > 0) {
                this.textHeight = this.getImageTextHeight(s, component);
                if (this.textHeight > 0) {
                    final int fontHeight = this.getFontHeight(component, font);
                    this.getLineHeight("W", component);
                    this.textWidth = this.getStringWidth(component, s, font) + 4;
                    if (b) {
                        this.textHeight += 3;
                        this.textWidth += 3;
                    }
                    this.textSize = this.textWidth * this.textHeight;
                    this.textData = new int[this.textSize];
                    final Image image = component.createImage(this.textWidth, this.textHeight);
                    final Graphics graphics = image.getGraphics();
                    graphics.setColor(Color.white);
                    graphics.fillRect(0, 0, this.textWidth, this.textHeight);
                    graphics.setColor(Color.black);
                    int n = 0;
                    if (b) {
                        graphics.drawRect(0, 0, this.textWidth - 1, this.textHeight - 1);
                        n = 3;
                    }
                    if (font != null) {
                        graphics.setFont(font);
                    }
                    int n2 = 80 * fontHeight / 100 - 2;
                    String concat = "";
                    final char[] charArray = s.toCharArray();
                    for (int i = 0; i < charArray.length; ++i) {
                        if (charArray[i] == '\n' || charArray[i] == '\r') {
                            if (concat.length() > 0) {
                                graphics.drawString(concat, n, n2);
                            }
                            n2 += fontHeight;
                            concat = "";
                        }
                        else {
                            concat = String.valueOf(String.valueOf(concat)).concat(String.valueOf(String.valueOf(charArray[i])));
                        }
                    }
                    if (concat.length() > 0) {
                        graphics.drawString(concat, 0, n2);
                    }
                    final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, this.textWidth, this.textHeight, this.textData, 0, this.textWidth);
                    try {
                        pixelGrabber.grabPixels();
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    if (!i.c(71)) {
                        this.textMonoData = d.a(this.textData, this.textWidth, this.textHeight, -16777216, true);
                        this.monoTextSize = this.textMonoData.length;
                    }
                }
            }
        }
        catch (Exception ex2) {}
    }
    
    private final int getStringWidth(final Component component, final String s, final Font font) {
        String concat = "";
        String s2 = "";
        final char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; ++i) {
            if (charArray[i] == '\n' || charArray[i] == '\r') {
                if (concat.length() > s2.length()) {
                    s2 = concat;
                    concat = "";
                }
            }
            else {
                concat = String.valueOf(String.valueOf(concat)).concat(String.valueOf(String.valueOf(charArray[i])));
            }
        }
        if (concat.length() > 0 && concat.length() > s2.length()) {
            s2 = concat;
        }
        final Graphics create = component.getGraphics().create();
        if (font != null) {
            create.setFont(font);
        }
        return create.getFontMetrics().stringWidth(s2);
    }
    
    private final Font getFont(final Component component) {
        if (this.font == null) {
            this.font = this.getPlainFont(component, 24, null);
        }
        return this.font;
    }
    
    private final void getTextImageWithFont(final Component component, final String s, final String s2, final boolean b, final String s3, final int n, final boolean b2, final boolean b3) {
        this.getTextImageWithFont(component, s, s2, b, s3, n, b2, b3, true);
    }
    
    private final void getTextImageWithFont(final Component component, final String s, final String s2, final boolean b, final String s3, final int n, final boolean b2, final boolean b3, final boolean b4) {
        this.textData = null;
        this.textMonoData = null;
        this.textSize = 0;
        this.monoTextSize = 0;
        this.textWidth = 0;
        this.textHeight = 0;
        try {
            jiExtSetFontScalingFactor(this.fontScalingFactor = n / j.p * 3.2);
            final d1 a = j.a(component, null, s, new ct(s3, j.p, b2, b3, true), 1.0);
            logPrintText(s, "Get text image with font:".concat(String.valueOf(String.valueOf(a))));
            char[] array;
            if (!i.c(269)) {
                array = s2.toCharArray();
            }
            else {
                array = g8.a(s2, s, i.c(40));
            }
            int n2 = 1;
            int textWidth = 0;
            int n3 = 0;
            int n4 = 0;
            int n5 = 0;
            for (int i = 0; i < array.length; ++i) {
                if (array[i] == '\n' || array[i] == '\r') {
                    ++n2;
                    if (n3 > textWidth) {
                        textWidth = n3;
                    }
                    if (n4 > n5) {
                        n5 = n4;
                    }
                    n3 = 0;
                    n4 = 0;
                }
                else {
                    n3 += a.a(component, "".concat(String.valueOf(String.valueOf(array[i]))), false, null);
                    ++n4;
                }
            }
            if (b4) {
                ++n2;
            }
            if (n3 > textWidth) {
                textWidth = n3;
            }
            if (n4 > n5) {
                n5 = n4;
            }
            final int h = a.h();
            int textHeight = h * n2;
            if (b) {
                textHeight += 4;
                textWidth += 4;
            }
            this.textData = new int[textWidth * textHeight];
            final int length = this.textData.length;
            for (int j = 0; j < length; ++j) {
                this.textData[j] = -1;
            }
            final int length2 = array.length;
            int n6 = 0;
            final byte[][] array2 = new byte[n5][];
            final int[] array3 = new int[n5];
            int n7 = 0;
            if (b) {
                for (int k = 0; k < textWidth; ++k) {
                    this.textData[k] = -16777216;
                }
                n7 = textWidth;
                for (int l = 1; l < textHeight - 1; ++l) {
                    this.textData[l * textWidth] = -16777216;
                    this.textData[(l + 1) * textWidth - 1] = -16777216;
                }
                for (int n8 = textWidth * (textHeight - 1); n8 < length; ++n8) {
                    this.textData[n8] = -16777216;
                }
            }
            for (int n9 = 0; n9 < n2; ++n9) {
                int n10 = 0;
                for (int n11 = 0, n12 = n6; n12 < length2 && n11 == 0; ++n12) {
                    char c;
                    if (n12 >= length2) {
                        c = ' ';
                    }
                    else {
                        c = array[n12];
                    }
                    if (c == '\n' || c == '\r') {
                        n11 = 1;
                    }
                    else {
                        array3[n10] = a.a(component, "".concat(String.valueOf(String.valueOf(c))), false, null);
                        n3 += array3[n10];
                        array2[n10] = a.a(component, "".concat(String.valueOf(String.valueOf(c))), false, false, false, false, null).b;
                        ++n10;
                    }
                    ++n6;
                }
                int n13 = 0;
                final int n14 = n9 * h * textWidth + n7;
                int n15 = 0;
                if (b) {
                    n15 = 2;
                }
                for (int n16 = 0; n16 < n10; ++n16) {
                    for (int n17 = 0; n17 < h; ++n17) {
                        final int n18 = n14 + n17 * textWidth;
                        final int n19 = n17 * array3[n16];
                        for (int n20 = 0; n20 < array3[n16]; ++n20) {
                            if (array2[n16][n19 + n20] == 1) {
                                this.textData[n13 + n20 + n18 + n15] = -16777216;
                            }
                            else {
                                this.textData[n13 + n20 + n18 + n15] = -1;
                            }
                        }
                    }
                    n13 += array3[n16];
                }
            }
            if (!i.c(71)) {
                this.textMonoData = d.a(this.textData, textWidth, textHeight, -16777216, true);
                this.monoTextSize = this.textMonoData.length;
            }
            this.textWidth = textWidth;
            this.textHeight = textHeight;
            this.textSize = length;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private final Font getPlainFont(final Component component, final int n, final String s) {
        Font font = component.getFont();
        Font font2 = null;
        Font font3 = null;
        boolean b = false;
        final String[] fontList = Toolkit.getDefaultToolkit().getFontList();
        if (fontList != null) {
            for (int i = 0; i < fontList.length; ++i) {
                if (s != null && fontList[i].toLowerCase().indexOf(s) >= 0) {
                    font = new Font(fontList[i], 1, n);
                    b = true;
                    break;
                }
                if (font2 == null && fontList[i].toLowerCase().indexOf("mono") >= 0) {
                    font2 = new Font(fontList[i], 1, n);
                }
                else if (font3 == null && fontList[i].toLowerCase().indexOf("courier") >= 0) {
                    font3 = new Font(fontList[i], 1, n);
                }
            }
        }
        if (!b && font2 != null) {
            return font2;
        }
        if (!b && font3 != null) {
            return font3;
        }
        return font;
    }
    
    private final int getFontHeight(final Component component, final Font font) {
        final Graphics create = component.getGraphics().create();
        if (font != null) {
            create.setFont(font);
        }
        return create.getFontMetrics().getHeight();
    }
    
    public final boolean endPage() {
        return jiP86.booLoaded && jiEndPage();
    }
    
    public boolean isImageLargerThanPage(final double n, final double n2, final int n3, final int n4) {
        return jiP86.booLoaded && jiExtIsImageLargerThanPage((int)n, (int)n2, n3, n4);
    }
    
    public void drawImage(final int[] array, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final boolean b, final boolean b2, final int n8, final boolean b3, final int n9, final boolean b4) {
        if (jiP86.booLoaded) {
            try {
                jiDrawImage(array, n, n2, n3, n4, n5, n6, n7, b, b2, Math.max(n8, 0), b3, n9, b4);
            }
            catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }
    
    public void drawMonoImage(final byte[] array, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final byte b, final byte b2, final byte b3, final byte b4, final boolean b5, final boolean b6, final boolean b7, final int n7, final boolean b8, final int n8, final boolean b9) {
        if (jiP86.booLoaded) {
            try {
                if (b7) {
                    jiDrawMonoImage(array, array.length, n, n2, n3, n4, n5, n6, b, b2, b3, b4, false, b6, Math.max(n7, 0), b8, n8, b9);
                }
                else {
                    jiDrawMonoImage(array, array.length, n, n2, n3, n4, n5, n6, b, b2, b3, b4, b5, b6, Math.max(n7, 0), b8, n8, b9);
                }
            }
            catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }
    
    public final void drawString(final String s, final int n, final Component component, final boolean b, final boolean b2) {
        if (jiP86.booLoaded && s != null) {
            try {
                if (this.doImageText) {
                    int[] textData = null;
                    int textSize = 0;
                    int textWidth = 0;
                    int textHeight = 0;
                    if (s != null) {
                        if (i.c(65)) {
                            this.getTextImageWithFont(component, "jip86", s, b2 && i.c(43), j.k(), j.m(), j.l(), false);
                        }
                        else {
                            this.getTextImage(s, component, b2 && i.c(43));
                        }
                        textData = this.textData;
                        textSize = this.textSize;
                        textWidth = this.textWidth;
                        textHeight = this.textHeight;
                    }
                    if (i.c(71)) {
                        jiExtDrawStringImage(textData, textSize, textWidth, textHeight, n, b);
                    }
                    else {
                        jiExtDrawMonoStringImage(this.textMonoData, this.monoTextSize, textWidth, textHeight, n, b);
                    }
                }
                else {
                    jiExtDrawString(s, n);
                }
            }
            catch (Throwable t) {}
        }
    }
    
    public final void setOriginalSize(final boolean b, final double n, final double n2) {
        if (jiP86.booLoaded) {
            try {
                jiExtSetOriginalSize(b, (int)n, (int)n2);
            }
            catch (Throwable t) {}
        }
    }
    
    public final void releaseResources() {
        if (jiP86.booLoaded) {
            try {
                jiExtReleaseResources();
            }
            catch (Throwable t) {}
            jiP86.booLoaded = false;
        }
    }
    
    public void finalize() {
        if (d.cz()) {
            System.out.println("Finalize p86 ".concat(String.valueOf(String.valueOf(this))));
        }
    }
    
    public void releasePrintingResources() {
        this.textData = null;
        this.textMonoData = null;
    }
    
    private static void logPrintText(final String s, final String s2) {
        if (i.c(40)) {
            h.d(s, s2);
        }
    }
    
    static {
        jiP86.booLibraryTested = false;
        jiP86.booLoaded = false;
    }
}
