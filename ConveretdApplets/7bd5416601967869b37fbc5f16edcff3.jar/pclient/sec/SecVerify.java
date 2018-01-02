// 
// Decompiled by Procyon v0.5.30
// 

package pclient.sec;

import javax.swing.JOptionPane;
import java.security.cert.X509Certificate;
import java.security.cert.Certificate;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.CertificateExpiredException;
import java.io.IOException;
import javax.net.ssl.SSLSession;
import com.pchat.sc.MsgOptions;
import pclient.shd.Config;
import pclient.adv.AppletSpice;
import pclient.adv.ComInter;

public class SecVerify implements ComInter
{
    private AppletSpice paraApplet;
    
    public void setPara(final AppletSpice paraApplet) {
        this.paraApplet = paraApplet;
    }
    
    public void process(final int n, final String[] array) {
    }
    
    public void restart() {
        this.runVeri();
    }
    
    public void destroy() {
    }
    
    private void runVeri() {
        if (!this.checkSocket(this.paraApplet.paraConf, this.paraApplet)) {
            return;
        }
        if (this.paraApplet.chatModel.cmIsTunnel() || !this.checkName(this.paraApplet.paraConf, this.paraApplet)) {}
        if (!this.checkDownload(this.paraApplet.paraConf, this.paraApplet)) {}
        if (!this.checkDoc(this.paraApplet.paraConf, this.paraApplet)) {}
        if (this.paraApplet.chatModel.cmIsTunnel() || !this.checkDate(this.paraApplet.paraConf, this.paraApplet)) {}
    }
    
    private boolean checkSocket(final Config config, final AppletSpice appletSpice) {
        if (appletSpice.chatModel.cmSecConnected()) {
            final String value = config.get("Msg.Sec.Good", "You are connected via secure connection.");
            if (config.getBool("Op.Sec.Good", true)) {
                appletSpice.mainChat.chatRender.showLocalAlert(value, null);
            }
            return true;
        }
        final String value2 = config.get("Msg.Sec.Bad", "Secure connection failed.");
        if (config.getBool("Op.Sec.Bad", true)) {
            this.showWarn(value2, this.paraApplet.paraConf);
        }
        return false;
    }
    
    private boolean checkName(final Config config, final AppletSpice appletSpice) {
        if (!config.getBool("Ctrl.Sec.Match", true)) {
            return true;
        }
        final boolean bool = config.getBool("Ctrl.Sec.DomainOnly", false);
        final String serverConf = config.serverConf("Net.Sec.Host", "");
        final String host = config.getApplet().getCodeBase().getHost();
        final String common = this.getCommon(config, appletSpice);
        config.printer().print("domainOnly=" + bool + " matchHost=" + serverConf + " codehost=" + host + " cname=" + common);
        if (common == null) {
            this.log("SEC 79238.");
            this.showWarn(config.get("Msg.Cer.Bad", "Certificate Error. You should disconnect this session."), this.paraApplet.paraConf);
            return false;
        }
        final String s = "Certificate Error. The correct domain name is";
        if (!bool) {
            if (!common.equalsIgnoreCase(host)) {
                this.log("Cert Err. Cerfiticate's host is " + common + ". But codebase's host is " + host);
                this.showWarn("SC63. " + config.get("Msg.Cer.Dom", s) + " " + common, this.paraApplet.paraConf);
            }
            return false;
        }
        if (!host.toLowerCase().endsWith(serverConf.toLowerCase())) {
            this.log("Cert Error. Wanted domain is " + serverConf + ". But codebase's host is " + host);
            this.showWarn("SC78. " + config.get("Msg.Cer.Dom", s) + " " + serverConf, this.paraApplet.paraConf);
            return false;
        }
        if (!common.toLowerCase().endsWith(serverConf.toLowerCase())) {
            this.log("Cert Err. Wanted domain is " + serverConf + ". But cert's host is " + host);
            this.showWarn("SC92. " + config.get("Msg.Cer.Dom", s) + " " + serverConf, this.paraApplet.paraConf);
            return false;
        }
        return true;
    }
    
    private boolean checkDownload(final Config config, final AppletSpice appletSpice) {
        if (!config.getBool("Ctrl.Sec.Down", true)) {
            return true;
        }
        final String protocol = config.getApplet().getCodeBase().getProtocol();
        this.log("proto=" + protocol);
        if (!"HTTPS".equalsIgnoreCase(protocol)) {
            this.showWarn("SC45. " + config.get("Msg.Hs.Bas", "Not HTTPS codebase"), config);
            return false;
        }
        return true;
    }
    
    private boolean checkDoc(final Config config, final AppletSpice appletSpice) {
        if (!config.getBool("Ctrl.Sec.Doc", true)) {
            return true;
        }
        final String protocol = config.getApplet().getDocumentBase().getProtocol();
        this.log("doc=" + protocol);
        if (!"HTTPS".equalsIgnoreCase(protocol)) {
            this.showWarn("SC26. " + config.get("Msg.Hs.Doc", "Not HTTPS page"), config);
            return false;
        }
        return true;
    }
    
    private boolean checkDate(final Config config, final AppletSpice appletSpice) {
        Certificate first;
        try {
            first = SecCert.getFirst((SSLSession)appletSpice.chatModel.cmSecSession());
        }
        catch (IOException ex) {
            ex.printStackTrace();
            first = null;
        }
        if (first == null) {
            this.showWarn("Certificate Error, no info. You may disconnect.", this.paraApplet.paraConf);
            return false;
        }
        final X509Certificate cast509 = SecCert.cast509(first);
        if (cast509 == null) {
            this.showWarn("Certificate Error. no info. You may disconnect.", this.paraApplet.paraConf);
            return false;
        }
        try {
            cast509.checkValidity();
        }
        catch (CertificateExpiredException ex2) {
            this.showWarn("Certificate Date Error. cert expired. You may disconnect.", this.paraApplet.paraConf);
            return false;
        }
        catch (CertificateNotYetValidException ex3) {
            this.showWarn("Certificate Date Error. Not yet valid. You may disconnect.", this.paraApplet.paraConf);
            return false;
        }
        return true;
    }
    
    private String getCommon(final Config config, final AppletSpice appletSpice) {
        CertGeneral cert;
        try {
            cert = SecCert.getCert((SSLSession)appletSpice.chatModel.cmSecSession());
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return cert.cn;
    }
    
    private void showWarn(final String s, final Config config) {
        System.out.println(s);
        JOptionPane.showMessageDialog(this.paraApplet.mainChat.chatRender.getComp(), s, config.title(), 2);
    }
    
    private void showInfo(final String s, final Config config) {
        System.out.println(s);
        JOptionPane.showMessageDialog(this.paraApplet.mainChat.chatRender.getComp(), s, config.title(), 1);
    }
    
    private void log(final String s) {
        System.out.println(s + " CHAT.");
    }
}
