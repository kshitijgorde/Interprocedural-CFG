// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.net.MalformedURLException;
import java.io.IOException;
import com.hw.client.util.a;
import java.net.URL;
import java.io.File;

public class aK
{
    dA a;
    private final String b;
    private final String c;
    private final String d;
    private File e;
    private File f;
    private File g;
    private URL h;
    private URL i;
    
    public aK(final dA a) {
        this.a = a;
        this.b = a.a("door_name");
        this.c = a.a("door_version");
        this.d = a.a("door_repository");
    }
    
    private File a(final boolean b) {
        if (!bj.d() && this.e != null) {
            return this.e;
        }
        if (bj.c()) {
            final String a = this.a(b, System.getenv("WIMBA_DOORPATH"), "WIMBA_DOORPATH", false, false);
            if (a == null) {
                return this.e;
            }
            com.hw.client.util.a.c(a);
            String s = System.getenv("LOCALAPPDATA");
            if (s != null && s.length() > 5 && bj.d() && (bj.f() || b) && s.substring(s.length() - 5).equals("Local")) {
                s += "Low";
            }
            final String a2 = this.a(b, s, "LOCALAPPDATA", false, false);
            if (a2 == null) {
                return this.e;
            }
            com.hw.client.util.a.c(a2);
            final String a3 = this.a(b, System.getenv("APPDATA"), "APPDATA", false, false);
            if (a3 == null) {
                return this.e;
            }
            com.hw.client.util.a.c(a3);
        }
        String s2 = this.a(b, System.getProperty("user.home"), "user.home", true, true);
        if (s2 == null) {
            return this.e;
        }
        com.hw.client.util.a.c(s2);
        if (bj.c()) {
            final String string = System.getenv("HOMEDRIVE") + System.getenv("HOMEPATH");
            final String a4 = this.a(b, string, "HOMEDRIVE + HOMEPATH", false, true);
            if (a4 == null) {
                return this.e;
            }
            com.hw.client.util.a.c(a4);
            s2 = this.a(b, string, "user.home", false, true);
            if (s2 == null) {
                return this.e;
            }
            com.hw.client.util.a.c(s2);
        }
        throw new IOException("Unable to get base directory.  " + s2);
    }
    
    private String a(final boolean b, String s, final String s2, final boolean b2, final boolean b3) {
        com.hw.client.util.a.c("Trying to get base directory.  { path=[" + s + "], pathRetrievalMethod=[" + s2 + "], checkCanWriteOnPath=[" + b2 + "] }");
        if (s == null) {
            return "Base path not specified.  { retrievalMethod=[" + s2 + "] }";
        }
        a(s);
        final File file;
        if (!(file = new File(s)).exists()) {
            return "Base path does not exist.  { path=[" + s + "], retrievalMethod=[" + s2 + "] }";
        }
        if (b2 && !file.canWrite()) {
            return "Unable to write to path.  { path=[" + s + ", retrievalMethod=[" + s2 + "] }";
        }
        if (!s.endsWith(File.separator)) {
            s += File.separator;
        }
        if (bj.c()) {
            if (b3) {
                if (bj.d()) {
                    a(s = s + "AppData" + File.separator);
                    if (bj.f() || b) {
                        a(s = s + "LocalLow" + File.separator);
                    }
                    else {
                        a(s = s + "Local" + File.separator);
                    }
                }
                else {
                    a(s = s + "Application Data" + File.separator);
                }
            }
            a(s = s + "HorizonWimba" + File.separator);
        }
        else if (bj.b()) {
            if (b3) {
                a(s = s + "Library" + File.separator);
                a(s = s + "Application Support" + File.separator);
            }
            a(s = s + "HorizonWimba" + File.separator);
        }
        else {
            a(s = s + ".horizonwimba" + File.separator);
        }
        a(s += "JSecureDoor");
        final File e;
        if (!(e = new File(s)).exists() && !e.mkdirs()) {
            return "Unable to create base directory.  { path=[" + s + ", retrievalMethod=[" + s2 + "] }";
        }
        if (!b2 && !e.canWrite()) {
            return "Unable to write to path.  { path=[" + s + ", retrievalMethod=[" + s2 + "] }";
        }
        this.e = e;
        com.hw.client.util.a.c("Successfully created base directory.  { path=[" + s + ", retrievalMethod=[" + s2 + "] }");
        return null;
    }
    
    private static void a(final String s) {
        final File file = new File(s);
        a.c("Checking path exists and is writable.  { path=[" + s + ", exists=[" + file.exists() + "], canWrite=[" + file.canWrite() + "] }");
    }
    
    public final File a() {
        final String string = this.b + ".log";
        File b;
        try {
            b = this.b();
        }
        catch (IOException ex) {
            com.hw.client.util.a.b("unable to get log file's base dir", ex);
            throw ex;
        }
        return new File(b, "logs" + File.separator + string);
    }
    
    public final File b() {
        if (this.g != null) {
            return this.g;
        }
        File a;
        try {
            a = this.a(false);
        }
        catch (IOException ex) {
            com.hw.client.util.a.a("unable to get base directory", ex);
            throw (IOException)ex.fillInStackTrace();
        }
        final StringBuffer sb;
        (sb = new StringBuffer()).append(a.getAbsolutePath());
        sb.append(File.separator);
        sb.append(this.b);
        sb.append("_");
        sb.append(this.c);
        final File g;
        if (!(g = new File(new String(sb))).exists() && !g.mkdirs()) {
            throw new IOException("unable to create door directory, path => " + (Object)sb);
        }
        com.hw.client.util.a.c("successfully created door directory, f => " + g);
        return this.g = g;
    }
    
    public final URL c() {
        if (this.h != null) {
            return this.h;
        }
        URL url;
        try {
            url = new URL(this.d);
        }
        catch (MalformedURLException ex) {
            com.hw.client.util.a.b("unable to create repository url", ex);
            throw ex;
        }
        com.hw.client.util.a.b("repository_url => " + url);
        URL h;
        try {
            h = new URL(url, this.m());
        }
        catch (MalformedURLException ex2) {
            com.hw.client.util.a.b("unable to create archive url", ex2);
            throw ex2;
        }
        com.hw.client.util.a.c("successfully created archive url, archive_url => " + h);
        return this.h = h;
    }
    
    public final URL d() {
        if (this.i != null) {
            return this.i;
        }
        URL url;
        try {
            url = new URL(this.d);
        }
        catch (MalformedURLException ex) {
            com.hw.client.util.a.b("unable to create repository url", ex);
            throw ex;
        }
        com.hw.client.util.a.b("repository_url => " + url);
        URL i;
        try {
            i = new URL(url, this.k());
        }
        catch (MalformedURLException ex2) {
            com.hw.client.util.a.b("unable to create door url", ex2);
            throw ex2;
        }
        com.hw.client.util.a.c("successfully created archive signature url, signature_url => " + i);
        return this.i = i;
    }
    
    public final File e() {
        return new File(this.f());
    }
    
    public final String f() {
        final StringBuffer append = new StringBuffer().append(this.b().getAbsolutePath()).append(File.separator).append("data").append(File.separator);
        String s = null;
        if (bj.c()) {
            if (bj.f() && this.a.a() && this.g()) {
                s = "wimbasecproxy-low.exe";
            }
            else {
                s = this.b + ".exe";
            }
        }
        else if (bj.b()) {
            s = this.b + "";
        }
        else if (bj.a()) {
            s = this.b + "";
        }
        return append.append(s).toString();
    }
    
    public final boolean g() {
        File file;
        try {
            file = new File(this.b().getAbsolutePath() + File.separator + "data" + File.separator + "wimbasecproxy-low.exe");
        }
        catch (IOException ex) {
            return false;
        }
        return file.exists();
    }
    
    public final Vector h() {
        final Vector<File> vector = new Vector<File>();
        final String[] list = this.f.list();
        for (int i = 0; i < list.length; ++i) {
            if (list[i].endsWith(".exe")) {
                vector.add(new File(this.f.getAbsolutePath() + File.separator + list[i]));
            }
        }
        return vector;
    }
    
    public final File i() {
        if (this.f != null) {
            return this.f;
        }
        File a;
        try {
            a = this.a(bj.d());
        }
        catch (IOException ex) {
            com.hw.client.util.a.a("unable to get base directory", ex);
            throw (IOException)ex.fillInStackTrace();
        }
        final File f;
        if (!(f = new File(a.getAbsolutePath() + File.separator + "archives")).exists() && !f.mkdirs()) {
            throw new IOException("unable to create archive directory, f => " + f);
        }
        com.hw.client.util.a.c("successfully created door directory, f => " + f);
        return this.f = f;
    }
    
    public final File j() {
        return new File(this.i().getAbsolutePath() + File.separator + this.m());
    }
    
    private String m() {
        final StringBuffer sb;
        (sb = new StringBuffer()).append(this.b);
        sb.append("_");
        sb.append(this.c.replace('.', '_'));
        sb.append(".");
        if (bj.c()) {
            sb.append("win");
        }
        else if (bj.b()) {
            sb.append("mac");
        }
        else if (bj.a()) {
            sb.append("lnx");
        }
        else {
            sb.append("unk");
        }
        sb.append(".zip");
        return new String(sb);
    }
    
    public final String k() {
        return this.m() + ".sig";
    }
    
    public final File l() {
        File a;
        try {
            a = this.a(false);
        }
        catch (IOException ex) {
            com.hw.client.util.a.a("DoorPaths.getExtractionDirectory(), unable to get base directory", ex);
            throw (IOException)ex.fillInStackTrace();
        }
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd_hhmmss_SSS");
        final StringBuffer sb;
        (sb = new StringBuffer()).append(a.getAbsolutePath()).append(File.separator);
        sb.append("temp").append(File.separator);
        sb.append(simpleDateFormat.format(new Date()));
        final File file;
        if (!(file = new File(new String(sb))).exists() && !file.mkdirs()) {
            throw new IOException("unable to create temp directory, path => " + (Object)sb);
        }
        com.hw.client.util.a.c("successfully created temp directory, f => " + file);
        return file;
    }
    
    public aK() {
    }
    
    public static int a(final float n, final float[] array, final int n2) {
        float n3 = 0.0f;
        int n4 = 0;
        for (int i = 0; i < n2; ++i) {
            final float n5 = n - array[i];
            final float n6 = n5 * n5;
            if (i == 0 || n6 < n3) {
                n3 = n6;
                n4 = i;
            }
        }
        return n4;
    }
}
