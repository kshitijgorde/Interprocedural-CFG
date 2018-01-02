import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.util.Enumeration;
import java.util.Vector;
import java.io.InputStream;
import java.net.URLConnection;
import java.io.ObjectInputStream;
import java.net.URL;
import java.awt.Font;
import java.util.StringTokenizer;
import java.util.Hashtable;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ForeignExchangeApplet extends Applet implements Runnable, MouseListener
{
    boolean isStandalone;
    BorderLayout borderLayout1;
    private int count;
    int w;
    int h;
    String fs;
    int delay;
    Color bgColor;
    Color lineColor;
    private Image offimg;
    private Graphics gr;
    private final int divid = 16;
    private Hashtable[] rate;
    private Thread me;
    private boolean bStop;
    
    public String getParameter(final String s, final String s2) {
        return this.isStandalone ? System.getProperty(s, s2) : ((this.getParameter(s) == null) ? s2 : this.getParameter(s));
    }
    
    public ForeignExchangeApplet() {
        this.isStandalone = false;
        this.borderLayout1 = new BorderLayout();
        this.count = 0;
        this.rate = null;
        this.bStop = false;
    }
    
    public void init() {
        try {
            this.jbInit();
            this.addMouseListener(this);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void getWebParam() {
        this.w = this.getSize().width;
        this.h = this.getSize().height;
        this.fs = this.getParameter("fontsize");
        try {
            this.delay = Integer.parseInt(this.getParameter("delay"));
        }
        catch (Exception ex) {
            this.delay = 40;
        }
        final int[] array = new int[3];
        final StringTokenizer stringTokenizer = new StringTokenizer(this.getParameter("bgcolor"));
        if (stringTokenizer.countTokens() == 3) {
            int n = 0;
            while (stringTokenizer.hasMoreTokens()) {
                array[n] = Integer.parseInt(stringTokenizer.nextToken());
                ++n;
            }
        }
        else {
            for (int i = 0; i < array.length; ++i) {
                array[i] = 255;
            }
        }
        this.bgColor = new Color(array[0], array[1], array[2]);
        final StringTokenizer stringTokenizer2 = new StringTokenizer(this.getParameter("linecolor"));
        if (stringTokenizer2.countTokens() == 3) {
            int n2 = 0;
            while (stringTokenizer2.hasMoreTokens()) {
                array[n2] = Integer.parseInt(stringTokenizer2.nextToken());
                ++n2;
            }
        }
        else {
            for (int j = 0; j < array.length; ++j) {
                array[j] = 255;
            }
        }
        this.lineColor = new Color(array[0], array[1], array[2]);
    }
    
    private Font setFontType() {
        int int1;
        if (this.fs == null) {
            int1 = 12;
        }
        else {
            int1 = Integer.parseInt(this.fs);
        }
        return new Font("\u7d30\u660e\u9ad4", 0, int1);
    }
    
    private int findMaxLength(final int n, final String[][] array) {
        int length = 0;
        for (int i = 0; i < array.length; ++i) {
            if (array[i][n].length() > length) {
                length = array[i][n].length();
            }
        }
        return length;
    }
    
    private String[][] fillSpace(final int n, final int n2, final String[][] array) {
        for (int i = 0; i < array.length; ++i) {
            final int n3 = n2 - array[i][n].length();
            if (n3 > 0) {
                StringBuffer sb;
                if (n == 0) {
                    sb = new StringBuffer(array[i][n]);
                    for (int j = 0; j < n3; ++j) {
                        sb.append("\u3000");
                    }
                }
                else {
                    sb = new StringBuffer();
                    for (int k = 0; k < n3; ++k) {
                        sb.append(" ");
                    }
                    sb.append(array[i][n]);
                }
                array[i][n] = sb.toString();
            }
        }
        return array;
    }
    
    public String padString(final String s, final int n, final String s2, final boolean b) {
        if (s2.length() > 1) {
            return s;
        }
        if (s.length() > n) {
            return s.substring(0, n);
        }
        final StringBuffer sb = new StringBuffer(s);
        while (sb.length() < n) {
            if (b) {
                sb.insert(0, s2);
            }
            else {
                sb.append(s2);
            }
        }
        return sb.toString();
    }
    
    public void prepareRate() {
        try {
            final URLConnection openConnection = new URL("http://" + this.getCodeBase().getHost() + "/netbank/servlet/ForeignExchangeQuery").openConnection();
            System.out.println("connected !!");
            openConnection.setUseCaches(false);
            final InputStream inputStream = openConnection.getInputStream();
            final Hashtable hashtable = (Hashtable)new ObjectInputStream(inputStream).readObject();
            System.out.println("read data !!");
            inputStream.close();
            final String[][] array = hashtable.get("RTExtData");
            final String[][] array2 = hashtable.get("RTExtDataF");
            System.out.println("RTExtData !!" + array.length);
            System.out.println("RTExtDataF !!" + array2.length);
            this.rate = new Hashtable[array.length + array2.length];
            for (int i = 0; i < array.length; ++i) {
                (this.rate[i] = new Hashtable()).put("data", array[i]);
                this.rate[i].put("y", new Integer(String.valueOf(i * 16)));
            }
            for (int j = array.length; j < this.rate.length; ++j) {
                (this.rate[j] = new Hashtable()).put("data", array2[j - array.length]);
                this.rate[j].put("y", new Integer(String.valueOf(j * 16)));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private String[][] dumpUnitExchange(final String[][] array) {
        final Vector<Object> vector = new Vector<Object>(3);
        for (int i = 0; i < array.length; ++i) {
            if (array[i][0].indexOf("\u99ac\u4f86\u5e63") == -1 && array[i][0].indexOf("\u7f8e\u91d1\u4e00\u822c") == -1) {
                vector.addElement(array[i]);
            }
        }
        final String[][] array2 = new String[vector.size()][5];
        final Enumeration<String[]> elements = vector.elements();
        int n = 0;
        while (elements.hasMoreElements()) {
            array2[n] = elements.nextElement();
            ++n;
        }
        return array2;
    }
    
    private void jbInit() throws Exception {
        this.getWebParam();
        this.prepareRate();
        this.offimg = this.createImage(this.w, this.h);
        (this.gr = this.offimg.getGraphics()).setFont(this.setFontType());
    }
    
    public void start() {
        (this.me = new Thread(this)).start();
    }
    
    public void run() {
        while (true) {
            if (!this.bStop) {
                for (int i = 0; i < this.rate.length; ++i) {
                    final int intValue = this.rate[i].get("y");
                    if (intValue - 1 < 0) {
                        if (i == 0) {
                            this.rate[i].put("y", new Integer(String.valueOf(this.rate[this.rate.length - 1].get("y") + 16)));
                        }
                        else {
                            this.rate[i].put("y", new Integer(String.valueOf((int)this.rate[i - 1].get("y") + 16)));
                        }
                    }
                    else {
                        this.rate[i].put("y", new Integer(String.valueOf(intValue - 1)));
                    }
                }
                this.count += this.delay;
                if (this.count >= 600000) {
                    this.prepareRate();
                    this.count = 0;
                }
                this.repaint();
            }
            try {
                Thread.sleep(this.delay);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void update(final Graphics graphics) {
        this.gr.setColor(this.bgColor);
        this.gr.fillRect(0, 0, this.w, this.h);
        for (int i = 0; i < this.rate.length; ++i) {
            if (i % 2 == 0) {
                this.gr.setColor(this.lineColor);
                this.gr.fillRect(5, (int)this.rate[i].get("y") - 10, this.w, 12);
            }
            this.gr.setColor(Color.darkGray);
            String s;
            try {
                final String[] array = this.rate[i].get("data");
                if (array[0] == null || array[0].trim().equals("")) {
                    s = this.forString("-", 10) + this.padString("-", 9, " ", true) + " " + this.padString("-", 9, " ", true);
                }
                else {
                    s = this.forString(array[0], 10) + this.padString(array[3], 9, " ", true) + " " + this.padString(array[4], 9, " ", true);
                }
            }
            catch (Exception ex) {
                s = "X \u8cc7\u8a0a\u6e90\u6709\u8aa4 X";
            }
            this.gr.drawString(s, 5, this.rate[i].get("y"));
        }
        graphics.drawImage(this.offimg, 0, 0, this);
    }
    
    public String forString(String s, final int n) {
        s = ((s != null) ? s : "-");
        final byte[] bytes = s.getBytes();
        final byte[] array = new byte[n];
        for (int i = 0; i < n; ++i) {
            if (i >= bytes.length) {
                array[i] = " ".getBytes()[0];
            }
            else {
                array[i] = bytes[i];
            }
        }
        String s2 = new String(array);
        if (s2.getBytes().length != n) {
            for (int j = 0; j < n - 1; ++j) {
                if (j >= bytes.length) {
                    array[j] = " ".getBytes()[0];
                }
                else {
                    array[j] = bytes[j];
                }
            }
            array[n - 1] = " ".getBytes()[0];
            s2 = new String(array);
        }
        return s2;
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(Color.cyan);
        graphics.fillRect(0, 0, this.w, this.h);
    }
    
    public String getAppletInfo() {
        return "Applet Information";
    }
    
    public String[][] getParameterInfo() {
        return null;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        try {
            this.getAppletContext().showDocument(new URL(this.getCodeBase(), "https://ibank.hncb.com.tw/netbank/pages/jsp/ExtSel/RTExange.html"), "_blank");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.bStop = true;
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.bStop = false;
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
}
