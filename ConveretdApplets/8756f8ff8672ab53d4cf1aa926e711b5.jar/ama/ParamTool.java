// 
// Decompiled by Procyon v0.5.30
// 

package ama;

import java.util.Enumeration;
import java.net.URLEncoder;
import java.util.Hashtable;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.IOException;

public class ParamTool
{
    private static String defaultURL;
    private static ParamTool tool;
    private static String defaultEncoding;
    private String url;
    
    public static void setDefaultURL(final String defaultURL) {
        ParamTool.defaultURL = defaultURL;
        ParamTool.tool = new ParamTool(defaultURL);
    }
    
    public static ParamTool getDefaultParamTool() {
        return ParamTool.tool;
    }
    
    private ParamTool(final String url) {
        this.url = url;
    }
    
    public String addGroup(final String s, final String s2) throws IOException, TooManyGroupsException {
        return this.addGroup(s, s2, "");
    }
    
    public String addGroup(final String s, final String s2, final String s3) throws IOException, TooManyGroupsException {
        URL url;
        try {
            url = new URL(this.url + "?action=add&group=" + s + "&template=" + s2 + s3);
        }
        catch (MalformedURLException ex) {
            ex.printStackTrace(System.err);
            return null;
        }
        BufferedReader bufferedReader = null;
        String substring = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
            for (String s4 = bufferedReader.readLine(); s4 != null; s4 = bufferedReader.readLine()) {
                if (s4.indexOf(" OK") == -1) {
                    throw new TooManyGroupsException("Maximum number of " + s + " groups has been reached.");
                }
                substring = s4.substring(0, s4.indexOf(" OK"));
            }
        }
        finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
        return substring;
    }
    
    public Hashtable getGroups(final String s) throws IOException {
        URL url;
        try {
            url = new URL(this.url + "?action=list&group=" + s);
        }
        catch (MalformedURLException ex) {
            ex.printStackTrace(System.err);
            return null;
        }
        final Hashtable<String, Hashtable<String, String>> hashtable = new Hashtable<String, Hashtable<String, String>>();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
            String s2 = null;
            Hashtable<String, String> hashtable2 = null;
            String s3 = bufferedReader.readLine();
            int n = -1;
            if (s3 != null) {
                n = s3.indexOf(61);
            }
            while (s3 != null && n != -1) {
                final String substring = s3.substring(0, n);
                final String substring2 = s3.substring(n + 1);
                final String substring3 = substring.substring(0, substring.lastIndexOf(46));
                if (s2 == null || !s2.equals(substring3)) {
                    if (s2 != null) {
                        hashtable.put(s2, hashtable2);
                    }
                    s2 = substring3;
                    hashtable2 = new Hashtable<String, String>();
                }
                hashtable2.put(substring, substring2);
                s3 = bufferedReader.readLine();
                if (s3 != null) {
                    n = s3.indexOf(61);
                }
            }
            if (s2 != null) {
                hashtable.put(s2, hashtable2);
            }
        }
        finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
        return hashtable;
    }
    
    public Hashtable getGroup(final String s) throws IOException {
        URL url;
        try {
            url = new URL(this.url + "?action=list&group=" + s);
        }
        catch (MalformedURLException ex) {
            ex.printStackTrace(System.err);
            return null;
        }
        final Hashtable<String, String> hashtable = new Hashtable<String, String>();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
            String s2 = bufferedReader.readLine();
            int n = -1;
            if (s2 != null) {
                n = s2.indexOf(61);
            }
            while (s2 != null && n != -1) {
                hashtable.put(s2.substring(0, n), s2.substring(n + 1));
                s2 = bufferedReader.readLine();
                if (s2 != null) {
                    n = s2.indexOf(61);
                }
            }
        }
        finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
        return hashtable;
    }
    
    public void update(final Hashtable hashtable) throws IOException {
        String string = "?action=update";
        final Enumeration<String> keys = hashtable.keys();
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            string = string + "&" + s + "=" + URLEncoder.encode(hashtable.get(s).toString(), ParamTool.defaultEncoding);
        }
        URL url;
        try {
            url = new URL(this.url + string);
        }
        catch (MalformedURLException ex) {
            ex.printStackTrace(System.err);
            return;
        }
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
            final String line = bufferedReader.readLine();
            if (!line.equals("OK")) {
                System.err.println("OOPS!!!: " + line);
            }
        }
        finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
    }
    
    public void removeGroup(final String s) throws IOException {
        URL url;
        try {
            url = new URL(this.url + "?action=remove&group=" + s);
        }
        catch (MalformedURLException ex) {
            ex.printStackTrace(System.err);
            return;
        }
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
            final String line = bufferedReader.readLine();
            if (!line.equals("OK")) {
                System.err.println("ParamTool OOPS: " + line);
            }
        }
        finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
    }
    
    static {
        ParamTool.defaultEncoding = new InputStreamReader(System.in).getEncoding();
    }
    
    public class TooManyGroupsException extends Exception
    {
        public TooManyGroupsException(final String s) {
            super(s);
        }
    }
}
