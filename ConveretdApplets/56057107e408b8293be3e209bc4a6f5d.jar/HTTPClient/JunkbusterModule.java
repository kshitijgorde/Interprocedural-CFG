// 
// Decompiled by Procyon v0.5.30
// 

package HTTPClient;

import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

public class JunkbusterModule implements HTTPClientModule, GlobalConstants
{
    private static String bl_file;
    private static String[] bl_lines;
    private static String[] bl_hosts;
    private static int[] bl_ports;
    private static String[] bl_paths;
    private static boolean[] bl_block;
    private static boolean remove_from;
    private static boolean remove_ua;
    private static boolean remove_referer;
    
    public int requestHandler(final Request request, final Response[] array) {
        final String blocked;
        if ((blocked = isBlocked(request)) != null) {
            array[0] = new Response("HTTP/1.1", 403, "Forbidden", new NVPair[] { new NVPair("Content-type", "text/plain") }, ("JunkbusterModule: this url was blocked by the rule '" + blocked + "'").getBytes(), null, 0);
            if (GlobalConstants.DebugMods) {
                Util.logLine("JBM:   '" + request.getConnection() + request.getRequestURI() + "' blocked by " + "rule '" + blocked + "'");
            }
            return 4;
        }
        NVPair[] headers = request.getHeaders();
        if (JunkbusterModule.remove_from) {
            headers = Util.removeAllValues(headers, "From");
        }
        if (JunkbusterModule.remove_ua) {
            headers = Util.removeAllValues(headers, "User-Agent");
        }
        if (JunkbusterModule.remove_referer) {
            headers = Util.removeAllValues(headers, "Referer");
        }
        request.setHeaders(headers);
        return 0;
    }
    
    public void responsePhase1Handler(final Response response, final RoRequest roRequest) throws IOException, ModuleException {
    }
    
    public int responsePhase2Handler(final Response response, final Request request) throws IOException {
        return 10;
    }
    
    public void responsePhase3Handler(final Response response, final RoRequest roRequest) {
    }
    
    public void trailerHandler(final Response response, final RoRequest roRequest) {
    }
    
    public static void removeFrom(final boolean remove_from) {
        JunkbusterModule.remove_from = remove_from;
    }
    
    public static void removeUserAgent(final boolean remove_ua) {
        JunkbusterModule.remove_ua = remove_ua;
    }
    
    public static void removeReferer(final boolean remove_referer) {
        JunkbusterModule.remove_referer = remove_referer;
    }
    
    private static String isBlocked(final RoRequest roRequest) {
        final String host = roRequest.getConnection().getHost();
        final int port = roRequest.getConnection().getPort();
        final String path = Util.getPath(roRequest.getRequestURI());
        boolean b = false;
        String s = null;
        for (int i = 0; i < JunkbusterModule.bl_hosts.length; ++i) {
            final String s2 = JunkbusterModule.bl_hosts[i];
            final String s3 = JunkbusterModule.bl_paths[i];
            final int n = JunkbusterModule.bl_ports[i];
            if ((s2 == null || s2.equals(host) || (s2.length() < host.length() && host.endsWith(s2) && host.charAt(host.length() - s2.length() - 1) == '.')) && (n == -1 || n == port) && (s3 == null || path.startsWith(s3))) {
                b = JunkbusterModule.bl_block[i];
                s = JunkbusterModule.bl_lines[i];
            }
        }
        return b ? s : null;
    }
    
    private static synchronized void readBlocklist(final String s) {
        if (s == null) {
            return;
        }
        try {
            final BufferedReader bufferedReader = new BufferedReader(new FileReader(s));
            JunkbusterModule.bl_lines = new String[100];
            JunkbusterModule.bl_hosts = new String[100];
            JunkbusterModule.bl_ports = new int[100];
            JunkbusterModule.bl_paths = new String[100];
            JunkbusterModule.bl_block = new boolean[100];
            int n = 0;
            String s2;
            while ((s2 = bufferedReader.readLine()) != null) {
                if (n == JunkbusterModule.bl_hosts.length) {
                    JunkbusterModule.bl_lines = Util.resizeArray(JunkbusterModule.bl_lines, n + 100);
                    JunkbusterModule.bl_hosts = Util.resizeArray(JunkbusterModule.bl_hosts, n + 100);
                    JunkbusterModule.bl_ports = Util.resizeArray(JunkbusterModule.bl_ports, n + 100);
                    JunkbusterModule.bl_paths = Util.resizeArray(JunkbusterModule.bl_paths, n + 100);
                    JunkbusterModule.bl_block = Util.resizeArray(JunkbusterModule.bl_block, n + 100);
                }
                final int index = s2.indexOf(35);
                if (index != -1) {
                    s2 = s2.substring(0, index);
                }
                final String trim = s2.trim();
                if (trim.length() == 0) {
                    continue;
                }
                JunkbusterModule.bl_lines[n] = trim;
                int n2 = 0;
                if (trim.charAt(0) == '~') {
                    JunkbusterModule.bl_block[n] = false;
                    n2 = 1;
                }
                else {
                    JunkbusterModule.bl_block[n] = true;
                }
                if (trim.charAt(n2) != '/') {
                    int n3 = trim.indexOf(47);
                    if (n3 == -1) {
                        n3 = trim.length();
                    }
                    int index2 = trim.indexOf(58);
                    if (index2 > n3) {
                        index2 = -1;
                    }
                    if (index2 != -1) {
                        if (index2 > n2) {
                            JunkbusterModule.bl_hosts[n] = trim.substring(n2, index2);
                        }
                        JunkbusterModule.bl_ports[n] = Integer.parseInt(trim.substring(index2 + 1, n3));
                    }
                    else {
                        JunkbusterModule.bl_hosts[n] = trim.substring(n2, n3);
                        JunkbusterModule.bl_ports[n] = -1;
                    }
                    n2 = n3;
                }
                else {
                    JunkbusterModule.bl_ports[n] = -1;
                }
                if (n2 < trim.length()) {
                    JunkbusterModule.bl_paths[n] = trim.substring(n2);
                }
                ++n;
            }
            JunkbusterModule.bl_lines = Util.resizeArray(JunkbusterModule.bl_lines, n);
            JunkbusterModule.bl_hosts = Util.resizeArray(JunkbusterModule.bl_hosts, n);
            JunkbusterModule.bl_ports = Util.resizeArray(JunkbusterModule.bl_ports, n);
            JunkbusterModule.bl_paths = Util.resizeArray(JunkbusterModule.bl_paths, n);
            JunkbusterModule.bl_block = Util.resizeArray(JunkbusterModule.bl_block, n);
        }
        catch (Exception ex) {
            JunkbusterModule.bl_lines = new String[0];
            JunkbusterModule.bl_hosts = new String[0];
            JunkbusterModule.bl_ports = new int[0];
            JunkbusterModule.bl_paths = new String[0];
            JunkbusterModule.bl_block = new boolean[0];
            if (GlobalConstants.DebugMods) {
                Util.logLine("JBM:   Error reading `" + JunkbusterModule.bl_file + "': " + ex);
            }
        }
    }
    
    static {
        try {
            JunkbusterModule.remove_from = Boolean.getBoolean("HTTPClient.junkbuster.remove_from");
        }
        catch (Exception ex) {
            JunkbusterModule.remove_from = false;
        }
        try {
            JunkbusterModule.remove_ua = Boolean.getBoolean("HTTPClient.junkbuster.remove_useragent");
        }
        catch (Exception ex2) {
            JunkbusterModule.remove_ua = false;
        }
        try {
            JunkbusterModule.remove_referer = Boolean.getBoolean("HTTPClient.junkbuster.remove_referer");
        }
        catch (Exception ex3) {
            JunkbusterModule.remove_referer = false;
        }
        try {
            JunkbusterModule.bl_file = System.getProperty("HTTPClient.junkbuster.blockfile");
        }
        catch (Exception ex4) {
            JunkbusterModule.bl_file = null;
        }
        if (GlobalConstants.DebugMods && JunkbusterModule.bl_file != null) {
            Util.logLine("JBM:   reading blockfile " + JunkbusterModule.bl_file);
        }
        readBlocklist(JunkbusterModule.bl_file);
    }
}
