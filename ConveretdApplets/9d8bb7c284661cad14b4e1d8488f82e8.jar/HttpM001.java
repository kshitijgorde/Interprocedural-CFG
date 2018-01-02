import java.net.URLEncoder;
import java.util.Enumeration;
import java.io.DataOutputStream;
import java.net.URLConnection;
import java.util.Properties;
import java.io.InputStream;
import java.util.Hashtable;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

public class HttpM001
{
    URL servlet;
    Hashtable \u011a;
    
    public HttpM001(final URL servlet) {
        this.servlet = servlet;
    }
    
    public InputStream sendGetMessage() {
        return this.sendGetMessage(null);
    }
    
    public InputStream sendGetMessage(final Properties properties) {
        String string = "";
        if (properties != null) {
            string = "?" + this.\u011b(properties);
        }
        final URLConnection openConnection = new URL(String.valueOf(this.servlet.toExternalForm()) + string).openConnection();
        openConnection.setUseCaches(false);
        this.\u011a(openConnection);
        return openConnection.getInputStream();
    }
    
    public InputStream sendPostMessage() {
        return this.sendPostMessage(null);
    }
    
    public InputStream sendPostMessage(final Properties properties) {
        String \u011b = "";
        if (properties != null) {
            \u011b = this.\u011b(properties);
        }
        final URLConnection openConnection = this.servlet.openConnection();
        openConnection.setDoInput(true);
        openConnection.setDoOutput(true);
        openConnection.setUseCaches(false);
        openConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        this.\u011a(openConnection);
        final DataOutputStream dataOutputStream = new DataOutputStream(openConnection.getOutputStream());
        dataOutputStream.writeBytes(\u011b);
        dataOutputStream.flush();
        dataOutputStream.close();
        return openConnection.getInputStream();
    }
    
    public void setHeader(final String s, final String s2) {
        if (this.\u011a == null) {
            this.\u011a = new Hashtable();
        }
        this.\u011a.put(s, s2);
    }
    
    private void \u011a(final URLConnection urlConnection) {
        if (this.\u011a != null) {
            final Enumeration<String> keys = this.\u011a.keys();
            while (keys.hasMoreElements()) {
                final String s = keys.nextElement();
                urlConnection.setRequestProperty(s, (String)this.\u011a.get(s));
            }
        }
    }
    
    public void setCookie(final String s, final String s2) {
        if (this.\u011a == null) {
            this.\u011a = new Hashtable();
        }
        final String s3 = this.\u011a.get("Cookie");
        if (s3 == null) {
            this.setHeader("Cookie", String.valueOf(s) + "=" + s2);
            return;
        }
        this.setHeader("Cookie", String.valueOf(s3) + "; " + s + "=" + s2);
    }
    
    private String \u011b(final Properties properties) {
        final StringBuffer sb = new StringBuffer();
        final Enumeration<?> propertyNames = properties.propertyNames();
        while (propertyNames.hasMoreElements()) {
            final String s = (String)propertyNames.nextElement();
            sb.append(String.valueOf(URLEncoder.encode(s)) + "=" + URLEncoder.encode(properties.getProperty(s)));
            if (propertyNames.hasMoreElements()) {
                sb.append("&");
            }
        }
        return sb.toString();
    }
}
