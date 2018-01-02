import java.net.URLConnection;
import java.io.IOException;
import java.net.MalformedURLException;
import java.io.DataInputStream;
import java.net.URLEncoder;
import java.io.DataOutputStream;
import netscape.javascript.JSObject;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.Image;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Button;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class aDraw extends Applet implements ActionListener
{
    private boolean _$11452;
    static String sR;
    static String ver;
    cDraw cv;
    cTools ct;
    private String _$11457;
    private int _$11458;
    
    public aDraw() {
        this._$11452 = false;
        this._$11457 = "aDraw.asp";
        this._$11458 = 0;
    }
    
    public void init() {
        int bkColor = 12632256;
        this.setFont(new Font("Helvetica", 0, 14));
        this.setLayout(null);
        final String parameter = this.getParameter("url");
        if (parameter != null) {
            this._$11457 = new String(parameter);
        }
        final String parameter2 = this.getParameter("bgcolor");
        if (parameter2 != null) {
            if (parameter2.charAt(0) == '#') {
                bkColor = Integer.parseInt(parameter2.substring(1), 16);
            }
            else {
                bkColor = Integer.parseInt(parameter2);
            }
        }
        this.setBackground(new Color(bkColor));
        this.ct = new cTools();
        this.cv = new cDraw(this.ct);
        this.ct.addcToolsListener(this.cv);
        this.ct.setBkColor(bkColor);
        if (!this._$11452) {
            (this.ct.b1 = new Button("About")).addActionListener(this);
        }
        final String parameter3 = this.getParameter("save");
        if (parameter3 != null) {
            (this.ct.b2 = new Button(parameter3)).addActionListener(this);
        }
        String parameter4 = this.getParameter("bouton");
        if (parameter4 == null) {
            parameter4 = "bouton.gif";
        }
        final MediaTracker mediaTracker = new MediaTracker(this);
        final Class<? extends aDraw> class1 = this.getClass();
        Image image = this.getImage(class1.getResource("/".concat(String.valueOf(String.valueOf(parameter4)))));
        if (image == null) {
            image = this.getImage(this.getCodeBase(), parameter4);
        }
        mediaTracker.addImage(image, 0);
        try {
            mediaTracker.waitForAll();
        }
        catch (InterruptedException ex) {}
        this.ct.setImage(image);
        final String parameter5 = this.getParameter("image");
        if (parameter5 != null) {
            Image image2 = this.getImage(class1.getResource("/".concat(String.valueOf(String.valueOf(parameter5)))));
            if (image2 == null) {
                image2 = this.getImage(this.getCodeBase(), parameter5);
            }
            if (image2 != null) {
                mediaTracker.addImage(image2, 0);
                try {
                    mediaTracker.waitForAll();
                }
                catch (InterruptedException ex2) {}
                this.cv.setBkImage(image2, parameter5, 0);
            }
        }
        final String parameter6 = this.getParameter("symbol");
        this.add(this.ct);
        this.ct.setBounds(0, 0, this.getSize().width, 90);
        if (parameter6 != null) {
            final Image[] array = new Image[20];
            for (int i = 0; i < 10; ++i) {
                array[i] = this.getImage(class1.getResource(String.valueOf(String.valueOf(new StringBuffer("/").append(parameter6).append(i).append(".gif")))));
                array[10 + i] = this.getImage(class1.getResource(String.valueOf(String.valueOf(new StringBuffer("/").append(parameter6).append(i).append("s.gif")))));
                if (array[i] == null) {
                    array[i] = this.getImage(this.getCodeBase(), String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(parameter6))).append(i).append(".gif"))));
                }
                if (array[10 + i] == null) {
                    array[10 + i] = this.getImage(this.getCodeBase(), String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(parameter6))).append(i).append("s.gif"))));
                }
                if (array[i] != null) {
                    mediaTracker.addImage(array[i], 0);
                }
                if (array[10 + i] != null) {
                    mediaTracker.addImage(array[10 + i], 0);
                }
            }
            try {
                mediaTracker.waitForAll();
            }
            catch (InterruptedException ex3) {}
            for (int j = 0; j < 10; ++j) {
                this.cv.setBkImage(array[j], String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(parameter6))).append(j).append(".gif"))), j + 1);
            }
            for (int k = 10; k < 20; ++k) {
                this.ct.setSymbol(array[k], k - 10);
            }
        }
        int n = this.getSize().width;
        int int1 = this.getSize().height - 90;
        final String parameter7 = this.getParameter("sizeW");
        if (parameter7 != null) {
            n = Integer.parseInt(parameter7);
        }
        final String parameter8 = this.getParameter("sizeH");
        if (parameter8 != null) {
            int1 = Integer.parseInt(parameter8);
        }
        this.add(this.cv);
        this.cv.setBounds((this.getSize().width - n) / 2, 91 + (this.getSize().height - 90 - int1) / 2, n, int1);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        try {
            if (actionEvent.getSource() == this.ct.b2) {
                if (this._$11457.startsWith("javascript")) {
                    this._$11485(this._$11457);
                }
                else {
                    this.postData(this._$11457);
                }
            }
            else {
                this.getAppletContext().showDocument(new URL(aDraw.sR), "_blank");
            }
        }
        catch (Exception ex) {}
    }
    
    public String getAppletInfo() {
        return String.valueOf(String.valueOf(new StringBuffer("Name: ").append(aDraw.ver).append("\r\n").append("Author: R. BERTHOU\r\n").append("E-Mail : rbl@berthou.com\r\n").append("URL : ").append(aDraw.sR)));
    }
    
    private void _$11485(final String s) {
        try {
            JSObject.getWindow((Applet)this).eval(s);
        }
        catch (Exception ex) {}
    }
    
    public String getData() {
        return this.cv.getData();
    }
    
    public boolean postData(final String s) {
        try {
            URL url;
            if (s.startsWith("http")) {
                url = new URL(s);
            }
            else {
                url = new URL(String.valueOf(String.valueOf(this.getCodeBase().toString())).concat(String.valueOf(String.valueOf(s))));
            }
            final URLConnection openConnection = url.openConnection();
            openConnection.setDoInput(true);
            openConnection.setDoOutput(true);
            openConnection.setUseCaches(false);
            openConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            final DataOutputStream dataOutputStream = new DataOutputStream(openConnection.getOutputStream());
            dataOutputStream.writeBytes(String.valueOf(String.valueOf(new StringBuffer("w=").append(URLEncoder.encode("".concat(String.valueOf(String.valueOf(this.cv.size.width))))).append("&h=").append(URLEncoder.encode("".concat(String.valueOf(String.valueOf(this.cv.size.height))))).append("&data=").append(URLEncoder.encode(this.cv.getData())))));
            dataOutputStream.flush();
            dataOutputStream.close();
            final DataInputStream dataInputStream = new DataInputStream(openConnection.getInputStream());
            String line;
            while ((line = dataInputStream.readLine()) != null) {
                System.out.println(line);
            }
            dataInputStream.close();
        }
        catch (MalformedURLException ex) {
            System.err.println("MalformedURLException: ".concat(String.valueOf(String.valueOf(ex))));
        }
        catch (IOException ex2) {
            System.err.println("IOException: ".concat(String.valueOf(String.valueOf(ex2.getMessage()))));
        }
        return true;
    }
    
    static {
        aDraw.sR = "http://www.javaside.com/";
        aDraw.ver = "aDraw V 2.00";
    }
}
