import java.net.URL;
import java.io.InputStream;
import java.io.FileReader;
import java.awt.FileDialog;
import java.io.File;
import java.awt.Frame;
import java.applet.AppletContext;
import java.applet.Applet;
import java.util.Hashtable;
import java.applet.AppletStub;

// 
// Decompiled by Procyon v0.5.30
// 

public class ptstub implements AppletStub
{
    private Hashtable _properties;
    private String _sproperties;
    private Applet _applet;
    private ptviewer _ptviewer;
    private AppletContext _context;
    private String _path;
    
    public ptstub(final ptviewer ptviewer, final String sproperties) {
        this._ptviewer = ptviewer;
        this._sproperties = sproperties;
    }
    
    public ptstub(final Applet applet, final Frame frame) {
        this._applet = applet;
        this._properties = new Hashtable();
        this._context = new ptcontext(frame);
    }
    
    public ptstub(String file, final Frame frame) {
        this._properties = new Hashtable();
        this._context = new ptcontext(frame);
        try {
            String substring = file;
            final int lastIndex = file.lastIndexOf(File.separatorChar);
            if (lastIndex >= 0) {
                substring = file.substring(lastIndex + 1);
            }
            final Class<?> forName = Class.forName("ptviewer");
            forName.getResource(substring);
            this._path = System.getProperty("user.dir");
            if (substring.toLowerCase().endsWith(".jpeg") || substring.toLowerCase().endsWith(".jpg") || substring.toLowerCase().endsWith(".jpa") || substring.toLowerCase().endsWith(".jpb") || substring.toLowerCase().endsWith(".jpc") || substring.toLowerCase().endsWith(".mov")) {
                this._properties.put("file", substring);
                return;
            }
            final InputStream resourceAsStream = forName.getResourceAsStream(substring);
            final byte[] file2 = this.readFile(resourceAsStream, 0);
            resourceAsStream.close();
            this.ParseHTML(new String(file2));
        }
        catch (Exception ex) {
            File file3 = new File(file);
            if (!file3.exists()) {
                final FileDialog fileDialog = new FileDialog(frame, "Load Panorama...");
                fileDialog.show();
                file = fileDialog.getFile();
                if (file != null) {
                    file3 = new File(String.valueOf(fileDialog.getDirectory()) + file);
                }
                else {
                    System.exit(0);
                }
            }
            if (!file3.exists()) {
                System.exit(0);
            }
            try {
                this._path = new File(file3.getCanonicalPath()).getParent();
            }
            catch (Exception ex2) {}
            if (file3.getName().toLowerCase().endsWith(".jpeg") || file3.getName().toLowerCase().endsWith(".jpg") || file3.getName().toLowerCase().endsWith(".jpa") || file3.getName().toLowerCase().endsWith(".jpb") || file3.getName().toLowerCase().endsWith(".jpc") || file3.getName().toLowerCase().endsWith(".mov")) {
                this._properties.put("file", file3.getName());
                return;
            }
            try {
                final FileReader fileReader = new FileReader(file3);
                final char[] array = new char[(int)file3.length()];
                fileReader.read(array, 0, (int)file3.length());
                fileReader.close();
                this.ParseHTML(new String(array));
            }
            catch (Exception ex3) {
                System.exit(0);
            }
        }
    }
    
    public void appletResize(final int n, final int n2) {
        if (this._applet != null) {
            this._applet.resize(n, n2);
        }
    }
    
    public AppletContext getAppletContext() {
        return this._context;
    }
    
    public URL getCodeBase() {
        if (this._ptviewer != null) {
            return this._ptviewer.getCodeBase();
        }
        URL url = null;
        try {
            final String string = "file:" + this._path;
            final StringBuffer sb = new StringBuffer();
            for (int i = 0; i < string.length(); ++i) {
                if (string.charAt(i) != ' ') {
                    sb.append(string.charAt(i));
                }
                else {
                    sb.append("%20");
                }
            }
            sb.append("/");
            url = new URL(sb.toString());
        }
        catch (Exception ex) {}
        return url;
    }
    
    public URL getDocumentBase() {
        if (this._ptviewer != null) {
            return this._ptviewer.getDocumentBase();
        }
        return this.getCodeBase();
    }
    
    public String getParameter(final String s) {
        if (this._ptviewer == null) {
            return this._properties.get(s);
        }
        return this._ptviewer.myGetParameter(this._sproperties, s);
    }
    
    public boolean isActive() {
        return true;
    }
    
    private void ParseHTML(final String s) {
        int n = 0;
        String substring = null;
        int n2 = 0;
        while (n2 < s.length() && substring == null) {
            if (s.regionMatches(true, n2, "<applet", 0, 7)) {
                n2 += 7;
                for (n = n2; n < s.length() && s.charAt(n) != '>'; ++n) {}
                substring = s.substring(n2, n);
            }
            else {
                ++n2;
            }
        }
        if (substring == null) {
            return;
        }
        int i = 0;
        while (i < substring.length()) {
            while (Character.isWhitespace(substring.charAt(i)) && i < substring.length()) {
                ++i;
            }
            if (i < substring.length()) {
                final String substring2 = substring.substring(i, substring.substring(i).indexOf(61) + i);
                int n3 = i + (substring.substring(i).indexOf(61) + 1);
                String s2;
                if (substring.charAt(n3) == '\"') {
                    ++n3;
                    s2 = substring.substring(n3, substring.substring(n3).indexOf(34) + n3);
                    i = n3 + (substring.substring(n3).indexOf(34) + 1);
                }
                else {
                    final int index = substring.substring(n3).indexOf(32);
                    if (index >= 0) {
                        s2 = substring.substring(n3, index + n3);
                        i = n3 + (index + 1);
                    }
                    else {
                        s2 = substring.substring(n3);
                        i = substring.length();
                    }
                }
                this._properties.put(substring2, s2);
            }
        }
        String substring3 = null;
        final int n4 = ++n;
        while (n < s.length() && substring3 == null) {
            if (s.regionMatches(true, n, "</applet>", 0, 9)) {
                substring3 = s.substring(n4, n);
            }
            else {
                ++n;
            }
        }
        if (substring3 == null) {
            return;
        }
        int j = 0;
        while (j < substring3.length()) {
            while (j < substring3.length() && !substring3.regionMatches(true, j, "<param", 0, 6)) {
                ++j;
            }
            if (j >= substring3.length()) {
                return;
            }
            j += 6;
            int n5;
            for (n5 = j; n5 < substring3.length() && substring3.charAt(n5) != '>'; ++n5) {}
            if (substring3.charAt(n5) != '>') {
                return;
            }
            final String substring4 = substring3.substring(j, n5);
            j = n5 + 1;
            int n6;
            for (n6 = 0; n6 < substring4.length() && !substring4.regionMatches(true, n6, "name=", 0, 5); ++n6) {}
            if (n6 >= substring4.length()) {
                System.out.println("Error in parameter tag: " + substring4 + "  No name tag");
                return;
            }
            n6 += 5;
            final int n7 = n6;
            while (n6 < substring4.length() && !Character.isWhitespace(substring4.charAt(n6))) {
                ++n6;
            }
            if (n6 >= substring4.length()) {
                System.out.println("Error in parameter tag: " + substring4 + "  No value tag");
                return;
            }
            final String substring5 = substring4.substring(n7, n6);
            while (n6 < substring4.length() && !substring4.regionMatches(true, n6, "value=", 0, 6)) {
                ++n6;
            }
            if (n6 >= substring4.length()) {
                System.out.println("Error in parameter tag: " + substring4 + "  No value tag");
                return;
            }
            n6 += 6;
            String s3;
            if (substring4.charAt(n6) == '\"') {
                ++n6;
                s3 = substring4.substring(n6, substring4.substring(n6).indexOf(34) + n6);
                final int n8 = n6 + (substring4.substring(n6).indexOf(34) + 1);
            }
            else {
                final int index2 = substring4.substring(n6).indexOf(32);
                if (index2 >= 0) {
                    s3 = substring4.substring(n6, index2 + n6);
                }
                else {
                    s3 = substring4.substring(n6);
                }
            }
            this._properties.put(substring5, s3);
        }
    }
    
    byte[] readFile(final InputStream inputStream, final int n) {
        int n2 = 0;
        int i = 0;
        final int n3 = (n > 0) ? (n / 10 + 1) : 50000;
        byte[] array = new byte[(n > 0) ? n : 50000];
        try {
            while (i != -1) {
                int n4 = 0;
                if (array.length < n2 + n3) {
                    final byte[] array2 = new byte[n2 + n3];
                    System.arraycopy(array, 0, array2, 0, n2);
                    array = array2;
                }
                while (n4 < n3 && (i = inputStream.read(array, n2, n3 - n4)) != -1) {
                    n4 += i;
                    n2 += i;
                }
            }
            if (array.length > n2) {
                final byte[] array3 = new byte[n2];
                System.arraycopy(array, 0, array3, 0, n2);
                array = array3;
            }
        }
        catch (Exception ex) {
            return null;
        }
        return array;
    }
}
