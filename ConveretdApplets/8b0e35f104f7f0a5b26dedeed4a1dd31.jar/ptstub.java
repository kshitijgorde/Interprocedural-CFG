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
    private Hashtable f1;
    private String f2;
    private Applet f3;
    private ptviewer f4;
    private AppletContext f5;
    private String f6;
    
    public ptstub(final ptviewer f4, final String f5) {
        this.f3 = null;
        this.f4 = null;
        this.f5 = null;
        this.f6 = null;
        this.f4 = f4;
        this.f2 = f5;
    }
    
    public ptstub(final Applet f3, final Frame frame) {
        this.f3 = null;
        this.f4 = null;
        this.f5 = null;
        this.f6 = null;
        this.f3 = f3;
        this.f1 = new Hashtable();
        this.f5 = new ptcontext(frame);
    }
    
    public ptstub(String file, final Frame frame) {
        this.f3 = null;
        this.f4 = null;
        this.f5 = null;
        this.f6 = null;
        this.f1 = new Hashtable();
        this.f5 = new ptcontext(frame);
        try {
            String substring = file;
            final int lastIndex;
            if ((lastIndex = file.lastIndexOf(File.separatorChar)) >= 0) {
                substring = file.substring(lastIndex + 1);
            }
            final Class forName;
            (forName = Class.forName("ptviewer")).getResource(substring);
            this.f6 = System.getProperty("user.dir");
            if (substring.toLowerCase().endsWith(".jpeg") || substring.toLowerCase().endsWith(".jpg") || substring.toLowerCase().endsWith(".jpa") || substring.toLowerCase().endsWith(".jpb") || substring.toLowerCase().endsWith(".jpc") || substring.toLowerCase().endsWith(".mov")) {
                this.f1.put("file", substring);
                return;
            }
            final InputStream resourceAsStream = forName.getResourceAsStream(substring);
            final byte[] file2 = this.readFile(resourceAsStream, 0);
            resourceAsStream.close();
            this.m1(new String(file2));
        }
        catch (Exception ex) {
            File file3;
            if (!(file3 = new File(file)).exists()) {
                final FileDialog fileDialog;
                (fileDialog = new FileDialog(frame, "Load Panorama...")).show();
                if ((file = fileDialog.getFile()) != null) {
                    file3 = new File(fileDialog.getDirectory() + file);
                }
                else {
                    System.exit(0);
                }
            }
            if (!file3.exists()) {
                System.exit(0);
            }
            try {
                this.f6 = new File(file3.getCanonicalPath()).getParent();
            }
            catch (Exception ex2) {}
            if (file3.getName().toLowerCase().endsWith(".jpeg") || file3.getName().toLowerCase().endsWith(".jpg") || file3.getName().toLowerCase().endsWith(".jpa") || file3.getName().toLowerCase().endsWith(".jpb") || file3.getName().toLowerCase().endsWith(".jpc") || file3.getName().toLowerCase().endsWith(".mov")) {
                this.f1.put("file", file3.getName());
                return;
            }
            try {
                final FileReader fileReader = new FileReader(file3);
                final char[] array = new char[(int)file3.length()];
                fileReader.read(array, 0, (int)file3.length());
                fileReader.close();
                this.m1(new String(array));
            }
            catch (Exception ex3) {
                System.exit(0);
            }
        }
    }
    
    public void appletResize(final int n, final int n2) {
        if (this.f3 != null) {
            this.f3.resize(n, n2);
        }
    }
    
    public AppletContext getAppletContext() {
        return this.f5;
    }
    
    public URL getCodeBase() {
        if (this.f4 != null) {
            return this.f4.getCodeBase();
        }
        URL url = null;
        try {
            final String string = "file:" + this.f6;
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
        if (this.f4 != null) {
            return this.f4.getDocumentBase();
        }
        return this.getCodeBase();
    }
    
    public String getParameter(final String s) {
        if (this.f4 == null) {
            return this.f1.get(s);
        }
        return this.f4.myGetParameter(this.f2, s);
    }
    
    public boolean isActive() {
        return true;
    }
    
    private void m1(final String s) {
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
        if (substring != null) {
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
                        final int index;
                        if ((index = substring.substring(n3).indexOf(32)) >= 0) {
                            s2 = substring.substring(n3, index + n3);
                            i = n3 + (index + 1);
                        }
                        else {
                            s2 = substring.substring(n3);
                            i = substring.length();
                        }
                    }
                    this.f1.put(substring2, s2);
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
            if (substring3 != null) {
                int j = 0;
                while (j < substring3.length()) {
                    while (j < substring3.length() && !substring3.regionMatches(true, j, "<param", 0, 6)) {
                        ++j;
                    }
                    if (j >= substring3.length()) {
                        break;
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
                        substring4.substring(n6).indexOf(34);
                    }
                    else {
                        final int index2;
                        if ((index2 = substring4.substring(n6).indexOf(32)) >= 0) {
                            s3 = substring4.substring(n6, index2 + n6);
                        }
                        else {
                            s3 = substring4.substring(n6);
                        }
                    }
                    this.f1.put(substring5, s3);
                }
            }
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
