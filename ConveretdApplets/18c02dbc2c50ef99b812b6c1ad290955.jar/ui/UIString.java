// 
// Decompiled by Procyon v0.5.30
// 

package ui;

import action.FileStationHandler;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Locale;
import java.applet.Applet;
import java.util.HashMap;

public class UIString
{
    static String _country;
    static String _locale;
    String localFileName;
    HashMap<String, String> _StringMap;
    static String _OKText;
    static String _UploadText;
    
    UIString(final Applet applet) {
        this._StringMap = new HashMap<String, String>();
        this.readLanguageFile(applet, this.localFileName = "/texts/" + this.setLanguageFile());
    }
    
    public void initStirng() {
        UIString._OKText = this.getStringMap().get("common.ok");
        UIString._UploadText = this.getStringMap().get("filetable.filetable_upload");
    }
    
    public HashMap<String, String> getStringMap() {
        return this._StringMap;
    }
    
    String setLanguageFile() {
        if (UIString._locale.equalsIgnoreCase(new Locale("cs", "", "").getLanguage())) {
            this.localFileName = "csy";
        }
        else if (UIString._locale.equalsIgnoreCase(new Locale("da", "", "").getLanguage())) {
            this.localFileName = "dan";
        }
        else if (UIString._locale.equalsIgnoreCase(new Locale("nl", "", "").getLanguage())) {
            this.localFileName = "nld";
        }
        else if (UIString._locale.equalsIgnoreCase(new Locale("fr", "", "").getLanguage())) {
            this.localFileName = "fre";
        }
        else if (UIString._locale.equalsIgnoreCase(new Locale("de", "", "").getLanguage())) {
            this.localFileName = "ger";
        }
        else if (UIString._locale.equalsIgnoreCase(new Locale("hu", "", "").getLanguage())) {
            this.localFileName = "hun";
        }
        else if (UIString._locale.equalsIgnoreCase(new Locale("it", "", "").getLanguage())) {
            this.localFileName = "ita";
        }
        else if (UIString._locale.equalsIgnoreCase(new Locale("ja", "", "").getLanguage())) {
            this.localFileName = "jpn";
        }
        else if (UIString._locale.equalsIgnoreCase(new Locale("ko", "", "").getLanguage())) {
            this.localFileName = "krn";
        }
        else if (UIString._locale.equalsIgnoreCase(new Locale("no", "", "").getLanguage()) || UIString._locale.equalsIgnoreCase(new Locale("nn", "", "").getLanguage()) || UIString._locale.equalsIgnoreCase(new Locale("nb", "", "").getLanguage())) {
            this.localFileName = "nor";
        }
        else if (UIString._locale.equalsIgnoreCase(new Locale("pl", "", "").getLanguage())) {
            this.localFileName = "plk";
        }
        else if (UIString._locale.equalsIgnoreCase(new Locale("pt", "", "").getLanguage())) {
            if (UIString._country.equalsIgnoreCase("br")) {
                this.localFileName = "ptb";
            }
            else {
                this.localFileName = "ptg";
            }
        }
        else if (UIString._locale.equalsIgnoreCase(new Locale("ru", "", "").getLanguage())) {
            this.localFileName = "rus";
        }
        else if (UIString._locale.equalsIgnoreCase(new Locale("es", "", "").getLanguage())) {
            this.localFileName = "spn";
        }
        else if (UIString._locale.equalsIgnoreCase(new Locale("sv", "", "").getLanguage())) {
            this.localFileName = "sve";
        }
        else if (UIString._locale.equalsIgnoreCase(new Locale("tr", "", "").getLanguage())) {
            this.localFileName = "trk";
        }
        else if (UIString._locale.equalsIgnoreCase(new Locale("zh", "", "").getLanguage())) {
            if (UIString._country.equalsIgnoreCase("cn") || UIString._country.equalsIgnoreCase("sg")) {
                this.localFileName = "chs";
            }
            else {
                this.localFileName = "cht";
            }
        }
        else {
            this.localFileName = "enu";
        }
        return this.localFileName;
    }
    
    private void readLanguageFile(final Applet applet, final String s) {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(applet.getClass().getResourceAsStream(s), "UTF8"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.startsWith("#")) {
                    continue;
                }
                final int index = line.indexOf("=");
                if (index <= 0) {
                    continue;
                }
                this._StringMap.put(line.substring(0, index).trim().toLowerCase(), line.substring(index + 1));
            }
        }
        catch (Exception ex) {
            FileStationHandler.log(ex);
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            }
            catch (Exception ex2) {
                FileStationHandler.log(ex2);
            }
        }
        finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            }
            catch (Exception ex3) {
                FileStationHandler.log(ex3);
            }
        }
    }
    
    static {
        UIString._country = Locale.getDefault().getCountry();
        UIString._locale = Locale.getDefault().getLanguage();
    }
}
