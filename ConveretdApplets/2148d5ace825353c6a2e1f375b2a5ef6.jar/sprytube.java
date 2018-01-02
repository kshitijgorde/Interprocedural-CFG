import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class sprytube extends Applet
{
    String sURL;
    String sLinks;
    
    public sprytube() {
        this.sURL = "http://www.youtube.com/get_video_info?&video_id=";
        this.sLinks = "INIT";
    }
    
    public void init() {
        this.setSize(0, 0);
        try {
            final String sDominio = this.getCodeBase().toString().toLowerCase() + "";
            final Pattern p = Pattern.compile("^http://[a-z]*\\.*descargatusvideos.com");
            final Matcher m = p.matcher(sDominio);
            if (m.find()) {
                final String sID = this.getParameter("VID");
                this.loadUrl(sID);
            }
            else {
                this.sLinks = "NOVALID";
            }
        }
        catch (Exception es) {
            this.sLinks = "FALSE";
        }
    }
    
    public void loadUrl(final String sID) {
        String sTexto = "";
        try {
            final URL sUrl = new URL(this.sURL + sID);
            final BufferedReader entrada = new BufferedReader(new InputStreamReader(sUrl.openStream()));
            while (entrada.read() != -1) {
                sTexto += entrada.readLine();
            }
            entrada.close();
            final String[] vars = sTexto.split("&");
            String text = "";
            int i;
            String[] pair;
            for (i = 0, i = 0; i < vars.length; ++i) {
                pair = vars[i].split("=");
                if (pair[0].toString().equals("fmt_url_map")) {
                    text += pair[1].toString();
                    break;
                }
            }
            sTexto = text;
        }
        catch (Exception es) {
            sTexto = "FALSE";
        }
        this.sLinks = sTexto;
    }
    
    public String getData() {
        return this.sLinks;
    }
}
