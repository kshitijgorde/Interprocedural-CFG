// 
// Decompiled by Procyon v0.5.30
// 

package dk.scanmaps.ditkort;

import java.io.InputStream;
import java.io.IOException;
import java.awt.Component;
import javax.swing.JOptionPane;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class Login
{
    String loginName;
    String loginPasswd;
    String ticketURL;
    String ticket;
    private CResourceManager myResource;
    
    public Login() {
        this.loginName = "preview";
        this.loginPasswd = "looking";
        this.ticketURL = "";
        this.myResource = CResourceManager.instance();
        this.init(this.loginName, this.loginPasswd);
    }
    
    public Login(final String login, final String password) {
        this.loginName = "preview";
        this.loginPasswd = "looking";
        this.ticketURL = "";
        this.myResource = CResourceManager.instance();
        this.init(login, password);
    }
    
    public void init(final String login, final String password) {
        this.ticketURL = "http://kortforsyningen.kms.dk/service?request=GetTicket&login=" + login + "&password=" + password;
        URL url = null;
        InputStream in = null;
        if (Constant.debugMode) {
            System.out.println("ticketURL is: " + this.ticketURL);
        }
        try {
            url = new URL(this.ticketURL);
            in = url.openStream();
            final BufferedReader br = new BufferedReader(new InputStreamReader(in, "iso-8859-1"));
            this.setTicket(br.readLine());
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(null, this.myResource.getResource("login.failed"));
            if (Constant.debugMode) {
                System.err.println("Couldn't get ticket!");
                System.err.println(e.getMessage());
            }
        }
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            }
            catch (IOException ex) {}
        }
        try {
            if (in != null) {
                in.close();
            }
        }
        catch (IOException ex2) {}
    }
    
    private void setTicket(final String t) {
        this.ticket = t;
    }
    
    public String getTicket() {
        final String str = this.ticket;
        return str;
    }
}
