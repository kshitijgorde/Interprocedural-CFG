import java.net.MalformedURLException;
import java.net.URLConnection;
import java.io.IOException;
import java.io.Reader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.FontMetrics;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

public class a implements Runnable
{
    private URL urlData;
    URL urlScripURL;
    private DataInputStream objDataInput1;
    private BufferedReader p;
    private String strDataURL;
    private String sfileUrl;
    private String strAddThis;
    private b objB;
    private bse_ticker objBseTicker;
    private Font fntTextFont;
    private Font fntTextFont2;
    private FontMetrics fntMatrix;
    private int intImgHeight;
    private String strErrorMessage;
    int intEVal;
    public boolean o;
    public boolean j;
    
    public a(final bse_ticker objBseTicker, final b objB, final String strDataURL) {
        this.strErrorMessage = new String(" Unknown error. ");
        this.o = false;
        this.objBseTicker = objBseTicker;
        this.objB = objB;
        this.strDataURL = strDataURL;
        this.fntTextFont2 = new Font("Arial", 1, 12);
    }
    
    private void _mthif() {
        try {
            if (this.p != null) {
                this.p.close();
                this.p = null;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    synchronized c a(final Image flddo, final int n, final URL a, final int fldfor, final int fldint) {
        if (this.j) {
            this.intEVal = 0;
        }
        this.intEVal += n;
        final c c = new c();
        final Dimension dimension = new Dimension(flddo.getWidth(null), flddo.getHeight(null));
        c._flddo = flddo;
        c._fldif = new Rectangle(dimension);
        c._fldtry = this.intEVal;
        c.a = a;
        c._fldfor = fldfor;
        c._fldint = fldint;
        c.strScripCode = this.sfileUrl;
        return c;
    }
    
    public synchronized void _mthif(final Image flddo, final int n, final URL url, final int fldfor, final int fldnew) {
        final Dimension dimension = new Dimension(flddo.getWidth(null), flddo.getHeight(null));
        this.objB.q._flddo = flddo;
        this.objB.q._fldif = new Rectangle(dimension);
        this.objB.q._fldtry = 0;
        this.objB.q.a = null;
        this.objB.q._fldfor = fldfor;
        this.objB.q._fldnew = fldnew;
        this.objB._flddo = this.objB.q._fldif.width;
    }
    
    public void _mthint() {
        this.fntMatrix = this.objB.getGraphics().getFontMetrics();
        try {
            final Dimension dimension = new Dimension(this.fntMatrix.stringWidth(this.strErrorMessage), 46);
            final Image image = this.objB.createImage(dimension.width, dimension.height);
            final Graphics graphics = image.getGraphics();
            graphics.setColor(this.objBseTicker._fldgoto);
            graphics.fillRect(0, 0, dimension.width, dimension.height);
            graphics.setFont(this.fntTextFont);
            graphics.setColor(this.objBseTicker.q);
            graphics.drawString(this.strErrorMessage, 2, dimension.height / 2);
            this.objB.a(this.a(image, dimension.width, null, 0, 0));
            this.o = true;
            final Dimension dimension2 = new Dimension(1, 46);
            final Image image2 = this.objB.createImage(dimension2.width, dimension2.height);
            final Graphics graphics2 = image2.getGraphics();
            graphics2.setColor(this.objBseTicker._fldgoto);
            graphics2.fillRect(0, 0, dimension2.width, dimension2.height);
            graphics2.setFont(this.fntTextFont);
            graphics2.setColor(this.objBseTicker.q);
            graphics2.drawString("Not available", 2, dimension2.height / 2);
            this._mthif(image2, 0, null, 0, 0);
            this.objDataInput1 = null;
            this.objB._mthfor();
            Thread.sleep(this.objBseTicker._flddo);
            System.out.println("tickerApplet.thread_sleeptime   " + this.objBseTicker._flddo);
            this._mthdo();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public boolean _mthfor() {
        this.objB.a();
        return this.a();
    }
    
    public synchronized void _mthdo() {
        while (this.o) {
            try {
                this.wait();
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public boolean a() {
        try {
            final int[] array = { 63, 56, 56, 58, 105, 41, 108, 102, 41, 109, 123, 106, 53, 113, 109, 99 };
            final char[] array2 = new char[array.length];
            for (int i = 0; i < array.length; ++i) {
                array2[i] = (char)(array[i] ^ array.length / 2);
            }
            final String string = String.valueOf(new StringBuffer(new String(array2)).reverse().toString()) + "&datafile=" + this.strDataURL;
            final URLConnection openConnection = this.urlData.openConnection();
            openConnection.setDoInput(true);
            openConnection.setDoOutput(true);
            openConnection.setUseCaches(false);
            openConnection.setDefaultUseCaches(false);
            openConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            openConnection.getOutputStream().write(string.getBytes());
            this.objDataInput1 = new DataInputStream(openConnection.getInputStream());
            this.p = new BufferedReader(new InputStreamReader(this.objDataInput1));
        }
        catch (IOException ex) {
            this.strErrorMessage = " Error reading file from the server.File not found Exception. ";
            if (this.a()) {
                return true;
            }
            this._mthint();
            return false;
        }
        return true;
    }
    
    public boolean _mthnew() {
        try {
            final String dataURL = this.objBseTicker.getDataURL();
            if (dataURL.startsWith("http")) {
                this.urlData = new URL(dataURL);
            }
            else {
                this.urlData = new URL(String.valueOf(this.objBseTicker.getCodeBase()) + dataURL);
            }
            return true;
        }
        catch (MalformedURLException ex) {
            this.objBseTicker.a(ex);
            return false;
        }
    }
    
    private int a(final int n, final int n2) {
        int n3;
        if (n > n2) {
            n3 = n;
        }
        else {
            n3 = n2;
        }
        return n3;
    }
    
    private void _mthbyte() {
        this._mthif();
        this.strErrorMessage = " Error reading file from the server.Connection could not be established. ";
        this.run();
        this._mthint();
    }
    
    public void run() {
        System.gc();
        System.runFinalization();
        this.objB.getGraphics().setFont(this.fntTextFont2);
        this.fntMatrix = this.objB.getGraphics().getFontMetrics();
        if (!this._mthnew()) {
            this._mthint();
            return;
        }
        while (true) {
            if (this._mthfor()) {
                try {
                    final String s = "© BSEIndia.com";
                    final Dimension dimension = new Dimension(this.fntMatrix.stringWidth(s) + 30, 46);
                    final Image image = this.objB.createImage(dimension.width, dimension.height);
                    final Graphics graphics = image.getGraphics();
                    graphics.setColor(this.objBseTicker._fldgoto);
                    graphics.fillRect(0, 0, dimension.width, dimension.height);
                    graphics.setFont(this.fntTextFont2);
                    graphics.setColor(this.objBseTicker.strWaterColor);
                    graphics.drawString(s, 5, dimension.height - 29);
                    this.objB.a(this.a(image, dimension.width, null, 0, 0));
                    final int width = this.objBseTicker.c[6]._fldif.width;
                    this.j = true;
                    final String line = this.p.readLine();
                    System.out.println("S 12: " + line);
                    if (line == null) {
                        this._mthbyte();
                        return;
                    }
                    final String substring = line.trim().substring(1);
                    System.out.println("S : " + substring);
                    final Dimension dimension2 = new Dimension(this.fntMatrix.stringWidth(substring) + 30, 46);
                    final Image image2 = this.objB.createImage(dimension2.width, dimension2.height);
                    final Graphics graphics2 = image2.getGraphics();
                    graphics2.setColor(this.objBseTicker._fldgoto);
                    graphics2.fillRect(0, 0, dimension2.width, dimension2.height);
                    graphics2.setFont(this.fntTextFont2);
                    graphics2.setColor(this.objBseTicker.q);
                    graphics2.drawString("Latest Datetime", 5, dimension2.height - 29);
                    graphics2.setFont(this.fntTextFont);
                    graphics2.drawString(substring, 5, dimension2.height - 12);
                    this.objB.a(this.a(image2, dimension2.width, null, 0, 0));
                    this.j = false;
                    final String s2 = "";
                    final String s3 = "";
                    final String s4 = "";
                    final int a = this.a(this.fntMatrix.stringWidth(""), this.fntMatrix.stringWidth(s3) + 15);
                    final Dimension dimension3 = new Dimension(5, 46);
                    final Image image3 = this.objB.createImage(dimension3.width, dimension3.height);
                    final Graphics graphics3 = image3.getGraphics();
                    graphics3.setColor(this.objBseTicker._fldgoto);
                    graphics3.fillRect(0, 0, dimension3.width, dimension3.height);
                    graphics3.setFont(this.fntTextFont2);
                    graphics3.setColor(this.objBseTicker.q);
                    graphics3.setFont(this.fntTextFont);
                    graphics3.drawString(s2, a + 10, dimension3.height - 29);
                    graphics3.drawString(s3, 15, dimension3.height - 12);
                    graphics3.drawString(s4, a + 10, dimension3.height - 12);
                    this._mthif(image3, dimension3.width, null, this.fntMatrix.stringWidth(s3), this.fntMatrix.stringWidth(s2));
                    image3.flush();
                    graphics3.dispose();
                    if (!this.strDataURL.equals("1") && !this.strDataURL.equals("2")) {
                        this.setStringToAdd(this.strAddThis = new String(this.p.readLine().trim().getBytes(), "ISO-8859-1"));
                    }
                    String line2;
                    while ((line2 = this.p.readLine()) != null) {
                        String trim = line2.trim();
                        if (trim.equals("TECK") && this.objBseTicker.d == 1) {
                            trim = new String("TEC");
                        }
                        if (trim.length() == 0) {
                            break;
                        }
                        if (!this.strDataURL.equals("1") && !this.strDataURL.equals("2")) {
                            final String line3;
                            if ((line3 = this.p.readLine()) == null) {
                                this._mthbyte();
                                break;
                            }
                            if (line3 != null) {
                                final String trim2 = line3.trim();
                                this.sfileUrl = trim2.trim().substring(1);
                                if (trim2.substring(0, 1).equalsIgnoreCase("@")) {
                                    this.a(this.objBseTicker.getCodeBase(), this.strAddThis, trim2.substring(0));
                                }
                            }
                        }
                        final String line4;
                        if ((line4 = this.p.readLine()) == null) {
                            this._mthbyte();
                            break;
                        }
                        String s5 = line4.trim();
                        if (s5.startsWith("$")) {
                            s5 = s5.substring(1);
                        }
                        final String line5;
                        if ((line5 = this.p.readLine()) == null) {
                            this._mthbyte();
                            break;
                        }
                        String s6 = line5.trim();
                        final int lastIndex = s6.lastIndexOf("%");
                        final String line6;
                        if ((line6 = this.p.readLine()) == null) {
                            this._mthbyte();
                            break;
                        }
                        String trim3;
                        if (line6.equals("$")) {
                            trim3 = "";
                        }
                        else {
                            trim3 = line6.trim();
                        }
                        int n = 0;
                        if (s5 == null) {
                            continue;
                        }
                        int n2;
                        if (this.fntMatrix.stringWidth(trim) > this.fntMatrix.stringWidth(String.valueOf(s5) + width)) {
                            n2 = this.fntMatrix.stringWidth(trim);
                        }
                        else {
                            n2 = this.fntMatrix.stringWidth(String.valueOf(s5) + width);
                        }
                        final Dimension dimension4 = new Dimension(n2 + 80, 46);
                        final Image image4 = this.objB.createImage(dimension4.width, dimension4.height);
                        final Graphics graphics4 = image4.getGraphics();
                        graphics4.setColor(this.objBseTicker._fldgoto);
                        graphics4.fillRect(0, 0, dimension4.width, dimension4.height);
                        graphics4.setFont(this.fntTextFont2);
                        int n3;
                        if (trim.startsWith("*")) {
                            graphics4.setColor(this.objBseTicker.b);
                            graphics4.drawString(trim.substring(1), 0, dimension4.height - 29);
                            this.intImgHeight = 1;
                            n3 = this.fntMatrix.stringWidth(trim);
                        }
                        else if (trim.equals("TEC") && this.objBseTicker.d == 1) {
                            graphics4.setColor(this.objBseTicker.q);
                            graphics4.drawString(trim, 0, dimension4.height - 29);
                            final int stringWidth = graphics4.getFontMetrics().stringWidth(trim);
                            final Font font = graphics4.getFont();
                            graphics4.setFont(new Font("SERIF", 2, 12));
                            graphics4.drawString("k", stringWidth, dimension4.height - 29);
                            n3 = stringWidth + graphics4.getFontMetrics().stringWidth("k");
                            graphics4.setFont(font);
                        }
                        else {
                            graphics4.setColor(this.objBseTicker.q);
                            if (this.objBseTicker.d == 3) {
                                n = graphics4.getFontMetrics().stringWidth(trim) / 2;
                            }
                            graphics4.drawString(trim, 0, dimension4.height - 29);
                            this.intImgHeight = 0;
                            n3 = this.fntMatrix.stringWidth(trim);
                        }
                        graphics4.setColor(this.objBseTicker.q);
                        graphics4.setFont(this.fntTextFont);
                        if (s5.endsWith("+")) {
                            graphics4.drawImage(this.objBseTicker.c[18]._flddo, n, dimension4.height - 21, this.objBseTicker);
                        }
                        else if (s5.endsWith("-")) {
                            graphics4.drawImage(this.objBseTicker.c[19]._flddo, n, dimension4.height - 21, this.objBseTicker);
                        }
                        else if (!s5.endsWith("~")) {
                            graphics4.drawImage(this.objBseTicker.c[20]._flddo, n, dimension4.height - 21, this.objBseTicker);
                        }
                        final int index = s5.indexOf("+");
                        final int index2 = s5.indexOf("-");
                        if (s5.endsWith("-")) {
                            s5 = s5.substring(0, s5.length() - 1);
                        }
                        else if (s5.endsWith("+")) {
                            s5 = s5.substring(0, s5.length() - 1);
                        }
                        else if (s5.endsWith("~")) {
                            s5 = s5.substring(0, s5.length() - 2);
                        }
                        final int n4 = s5.lastIndexOf("") - s5.indexOf(".");
                        if (index != -1) {
                            if (n4 < 3) {
                                new StringBuffer(String.valueOf(s5)).append("0").toString();
                            }
                        }
                        String s7;
                        if (index2 != -1) {
                            if (n4 >= 3) {
                                s7 = s5;
                            }
                            else {
                                s7 = String.valueOf(s5) + "0";
                            }
                        }
                        else if (n4 >= 3) {
                            s7 = s5;
                        }
                        else {
                            s7 = String.valueOf(s5) + "0";
                        }
                        graphics4.setColor(this.objBseTicker.q);
                        if (lastIndex != -1) {
                            s6 = " YTM " + s6;
                        }
                        if (this.objBseTicker.d == 3) {
                            graphics4.drawString(s6, dimension4.width - graphics4.getFontMetrics().stringWidth(s6) - 15, dimension4.height - 12);
                            graphics4.drawString(s7, dimension4.width - graphics4.getFontMetrics().stringWidth(s7) - 15, dimension4.height - 29);
                        }
                        else {
                            graphics4.drawString(s6, 14, dimension4.height - 12);
                            graphics4.drawString(s7, n2 + width, dimension4.height - 29);
                        }
                        graphics4.drawString(trim3, n2 + width, dimension4.height - 12);
                        this.objB.a(this.a(image4, dimension4.width, this.urlScripURL, n3, this.intImgHeight));
                        image4.flush();
                        graphics4.dispose();
                        System.gc();
                        System.runFinalization();
                    }
                    this.o = true;
                    this._mthif();
                    this.objB._mthfor();
                    try {
                        Thread.sleep(this.objBseTicker._flddo);
                        System.out.println("sleep time " + this.objBseTicker._flddo + "milliseconds");
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    this._mthdo();
                    System.gc();
                    System.runFinalization();
                }
                catch (IOException ex2) {
                    this.objBseTicker.a(ex2);
                }
            }
        }
    }
    
    public void a(final Font fntTextFont) {
        this.fntTextFont = fntTextFont;
    }
    
    public void a(final URL url, final String s, String substring) {
        try {
            substring = substring.trim().substring(1);
            final String value = String.valueOf(url);
            this.urlScripURL = new URL(value.substring(0, value.lastIndexOf(this.objBseTicker.getStrIndexOf())));
        }
        catch (MalformedURLException ex) {}
    }
    
    public synchronized void _mthcase() {
        this.o = false;
        this.notifyAll();
    }
    
    private void setStringToAdd(final String stoAdd) {
        this.objBseTicker.setStoAdd(stoAdd);
    }
}
