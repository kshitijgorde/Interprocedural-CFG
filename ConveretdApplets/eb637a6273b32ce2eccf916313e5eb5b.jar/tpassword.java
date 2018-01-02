import java.util.Enumeration;
import java.io.DataInputStream;
import java.net.URL;
import java.util.Vector;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class tpassword extends Applet
{
    public Vector dList;
    private char cSep;
    c_pa mess;
    int bgcolor;
    int bgcolor2;
    int fgcolor;
    int fgcolor2;
    int iCur;
    private String m_t;
    public String m_s;
    
    public void login() {
        if (this.mess == null) {
            this.mess = new c_pa(this, "Login...");
        }
        if (!this.mess.isShowing()) {
            this.mess.show();
        }
        this.mess.toFront();
    }
    
    public boolean verb(final String s, final String s2) {
        final String verif = this.verif(s, s2);
        if (verif != null) {
            try {
                if (verif.charAt(0) == '.') {
                    this.getAppletContext().showDocument(new URL(this.getCodeBase(), verif), this.m_t);
                }
                else {
                    this.getAppletContext().showDocument(new URL(verif), this.m_t);
                }
            }
            catch (Exception ex) {
                System.out.println("Erreur Open URL : " + verif + " err=" + ex);
            }
        }
        return verif != null;
    }
    
    public void readFile(final String s) {
        this.dList.removeAllElements();
        DataInputStream dataInputStream;
        try {
            dataInputStream = new DataInputStream(new URL(this.getCodeBase(), s).openStream());
        }
        catch (Exception ex) {
            dataInputStream = null;
        }
        while (true) {
            final r_pa r_pa = new r_pa();
            String line;
            try {
                line = dataInputStream.readLine();
            }
            catch (Exception ex2) {
                return;
            }
            if (!r_pa.get(r_pa, line, this.cSep)) {
                break;
            }
            if (r_pa.sURL == null) {
                continue;
            }
            this.dList.addElement(r_pa);
        }
    }
    
    public tpassword() {
        this.dList = new Vector(10, 5);
        this.cSep = '§';
        this.bgcolor = 13693183;
        this.bgcolor2 = 4210864;
        this.fgcolor = 2105504;
        this.fgcolor2 = 13693183;
        this.m_t = "_self";
        this.m_s = "JavaSide.com";
    }
    
    public String verif(final String s, final String s2) {
        int n = -1;
        int n2 = 0;
        final Enumeration<r_pa> elements = (Enumeration<r_pa>)this.dList.elements();
        while (elements.hasMoreElements()) {
            if (elements.nextElement().verif(s, s2)) {
                n = n2;
                break;
            }
            ++n2;
        }
        String surl = null;
        if (n > -1) {
            if (this.mess != null && this.mess.isShowing()) {
                this.mess.hide();
            }
            surl = this.dList.elementAt(n).sURL;
        }
        return surl;
    }
    
    public void init() {
        final String parameter = this.getParameter("bgcolor");
        if (parameter != null) {
            this.bgcolor = Integer.parseInt(parameter);
        }
        final String parameter2 = this.getParameter("fgcolor");
        if (parameter2 != null) {
            this.fgcolor = Integer.parseInt(parameter2);
        }
        final String parameter3 = this.getParameter("bgcolor2");
        if (parameter3 != null) {
            this.bgcolor2 = Integer.parseInt(parameter3);
        }
        final String parameter4 = this.getParameter("fgcolor2");
        if (parameter4 != null) {
            this.fgcolor2 = Integer.parseInt(parameter4);
        }
        final String parameter5 = this.getParameter("title");
        if (parameter5 != null) {
            this.m_s = new String(parameter5);
        }
        final String parameter6 = this.getParameter("target");
        if (parameter6 != null) {
            this.m_t = new String(parameter6);
        }
        final String parameter7 = this.getParameter("t1");
        if (parameter7 != null) {
            this.readFile(parameter7 + "pass.txt");
        }
        else {
            this.readFile("./pass.txt");
        }
        final String parameter8 = this.getParameter("auto");
        if (parameter8 != null && parameter8.equalsIgnoreCase("YES")) {
            this.login();
        }
    }
}
