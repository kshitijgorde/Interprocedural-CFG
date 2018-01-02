import java.util.Hashtable;
import java.util.StringTokenizer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.io.PrintStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class OmegaSystem
{
    public static final int versio_java;
    public static final String line_separator;
    public static PrintStream err;
    public static int contador1;
    public static int contador2;
    public static String code_base;
    public static String document_base;
    public static AppInterface context;
    private static Properties redirection;
    public static boolean dual;
    public static final boolean windowsOS;
    public static final boolean macOS;
    
    public OmegaSystem(final PrintStream err) {
        OmegaSystem.err = err;
    }
    
    public static final String get_url_base() {
        if (OmegaSystem.code_base == null) {
            return null;
        }
        String code_base = OmegaSystem.code_base;
        try {
            if (new URL(OmegaSystem.code_base).getProtocol().equals("file")) {
                code_base = "http://www.wiris.com/wiris/wiris-codebase";
            }
        }
        catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
        int n = code_base.length() - 1;
        if (code_base.endsWith("/")) {
            --n;
        }
        final int lastIndex = code_base.lastIndexOf("/", n);
        if (lastIndex > 0) {
            return code_base.substring(0, lastIndex);
        }
        return code_base;
    }
    
    public static final String translateURL(final String s) {
        String s2 = OmegaSystem.redirection.getProperty(s);
        try {
            if (s2 == null) {
                final HttpURLConnection httpURLConnection = (HttpURLConnection)new URL(s).openConnection();
                httpURLConnection.setRequestMethod("HEAD");
                final int responseCode = httpURLConnection.getResponseCode();
                s2 = httpURLConnection.getURL().toString();
                if (!s2.equals(s)) {
                    System.err.println("Redirection(" + responseCode + "): " + s2);
                }
                ((Hashtable<String, String>)OmegaSystem.redirection).put(s, s2);
            }
            return s2;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return s;
        }
    }
    
    static {
        OmegaSystem.contador1 = 0;
        OmegaSystem.contador2 = 0;
        OmegaSystem.redirection = new Properties();
        OmegaSystem.dual = false;
        boolean startsWith = false;
        boolean startsWith2 = false;
        try {
            startsWith = System.getProperty("os.name").toUpperCase().startsWith("WINDOWS");
        }
        catch (Exception ex2) {}
        windowsOS = startsWith;
        try {
            startsWith2 = System.getProperty("os.name").toUpperCase().startsWith("MAC");
        }
        catch (Exception ex3) {}
        macOS = startsWith2;
        int versio_java2 = 0;
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(System.getProperty("java.version"), ".");
            final int int1 = Integer.parseInt(stringTokenizer.nextToken());
            if (int1 == 0) {
                versio_java2 = 0;
            }
            else if (int1 == 1) {
                switch (Integer.parseInt(stringTokenizer.nextToken())) {
                    case 1: {
                        versio_java2 = 0;
                        break;
                    }
                    case 2: {
                        versio_java2 = 1;
                        break;
                    }
                    case 3: {
                        versio_java2 = 2;
                        break;
                    }
                    case 4: {
                        versio_java2 = 3;
                        break;
                    }
                    default: {
                        versio_java2 = 4;
                        break;
                    }
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        versio_java = versio_java2;
        line_separator = "\n";
    }
}
