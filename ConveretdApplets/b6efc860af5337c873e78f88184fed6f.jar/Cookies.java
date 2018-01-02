import java.net.URLEncoder;
import netscape.javascript.JSException;
import java.util.Calendar;
import netscape.javascript.JSObject;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Cookies
{
    private Applet applet;
    
    public Cookies(final Applet applet) {
        this.applet = applet;
    }
    
    private String decode(final String encoded) {
        final int HEXADECIMAL = 16;
        String decoded = "";
        for (int i = 0; i < encoded.length(); ++i) {
            final char ch = encoded.charAt(i);
            if (ch == '%') {
                final int value = Character.digit(encoded.charAt(++i), 16) * 16 + Character.digit(encoded.charAt(++i), 16);
                decoded = String.valueOf(decoded) + (char)value;
            }
            else if (ch == '+') {
                decoded = String.valueOf(decoded) + ' ';
            }
            else {
                decoded = String.valueOf(decoded) + ch;
            }
        }
        return decoded;
    }
    
    public void deleteCookie(final String cookie) {
        try {
            final JSObject browser = JSObject.getWindow(this.applet);
            final JSObject document = (JSObject)browser.getMember("document");
            final Calendar date = Calendar.getInstance();
            date.add(2, -1);
            final String expires = "; expires=" + date.getTime().toString();
            document.setMember("cookie", (Object)(String.valueOf(cookie) + expires));
        }
        catch (JSException ex) {}
    }
    
    private String readAllCookies() {
        try {
            final JSObject browser = JSObject.getWindow(this.applet);
            final JSObject document = (JSObject)browser.getMember("document");
            final String cookie = (String)document.getMember("cookie");
            if (cookie.length() > 0) {
                return cookie;
            }
        }
        catch (Exception ex) {}
        return "";
    }
    
    public String readCookie(final String name) {
        final String cookies = this.readAllCookies();
        final String search = String.valueOf(name) + "=";
        if (cookies.length() > 0) {
            int offset = cookies.indexOf(search);
            if (offset != -1) {
                offset += search.length();
                int end = cookies.indexOf(";", offset);
                if (end == -1) {
                    end = cookies.length();
                }
                return this.decode(cookies.substring(offset, end));
            }
        }
        return "";
    }
    
    public boolean writeCookie(final String name, final String cookie) {
        try {
            final JSObject browser = JSObject.getWindow(this.applet);
            final JSObject document = (JSObject)browser.getMember("document");
            final Calendar date = Calendar.getInstance();
            date.add(2, 4);
            final String expires = "; expires=" + date.getTime().toString();
            document.setMember("cookie", (Object)(String.valueOf(name) + "=" + URLEncoder.encode(cookie) + expires));
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }
}
