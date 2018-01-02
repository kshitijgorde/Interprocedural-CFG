// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.system;

import java.io.File;
import java.net.URL;
import java.util.Locale;
import java.io.IOException;
import jmaster.util.property.C;
import jmaster.util.log.B;
import jmaster.util.log.A;

public class SystemHelper
{
    public static final String PROPERTY_FILE_NAME = "SystemHelper.properties";
    public static final String PREFIX = "systemHelper";
    private static final int B = -1;
    private static final int C = 0;
    private static final int A = 1;
    private static final int H = 2;
    private static SystemHelper K;
    protected A E;
    protected int D;
    protected String J;
    protected String F;
    protected String G;
    protected String I;
    
    protected SystemHelper() {
        this.E = jmaster.util.log.B.getInstance().getLog(this.getClass());
        jmaster.util.property.C.A().A(this, jmaster.util.property.B.C().G("SystemHelper.properties"), "systemHelper");
        try {
            final String property = System.getProperty("os.name");
            if (property == null) {
                throw new IOException("os.name not found");
            }
            final String lowerCase = property.toLowerCase(Locale.ENGLISH);
            if (lowerCase.indexOf("windows") != -1) {
                this.D = 1;
            }
            else if (lowerCase.indexOf("linux") != -1 || lowerCase.indexOf("sun os") != -1 || lowerCase.indexOf("sunos") != -1 || lowerCase.indexOf("solaris") != -1 || lowerCase.indexOf("mpe/ix") != -1 || lowerCase.indexOf("hp-ux") != -1 || lowerCase.indexOf("aix") != -1 || lowerCase.indexOf("freebsd") != -1 || lowerCase.indexOf("irix") != -1 || lowerCase.indexOf("digital unix") != -1 || lowerCase.indexOf("unix") != -1 || lowerCase.indexOf("mac os x") != -1) {
                this.D = 2;
            }
            else {
                this.D = 0;
            }
        }
        catch (Exception ex) {
            this.D = -1;
        }
    }
    
    public static synchronized SystemHelper getInstance() {
        if (SystemHelper.K == null) {
            SystemHelper.K = new SystemHelper();
        }
        return SystemHelper.K;
    }
    
    public String getOpenFileWindowsCmd() {
        return this.J;
    }
    
    public void setOpenFileWindowsCmd(final String j) {
        this.J = j;
    }
    
    public String getOpenFolderWindowsCmd() {
        return this.F;
    }
    
    public void setOpenFolderWindowsCmd(final String f) {
        this.F = f;
    }
    
    public String getOpenFileUnixCmd() {
        return this.G;
    }
    
    public void setOpenFileUnixCmd(final String g) {
        this.G = g;
    }
    
    public String getOpenFolderUnixCmd() {
        return this.I;
    }
    
    public void setOpenFolderUnixCmd(final String i) {
        this.I = i;
    }
    
    private Process C(final String s) {
        Process exec = null;
        if (this.E.B()) {
            this.E.D("SystemHelper.executeProcess( " + s + " )");
        }
        try {
            exec = Runtime.getRuntime().exec(s);
        }
        catch (Exception ex) {
            this.E.E("SystemHelper.executeProcess( " + s + " ) failed", ex);
        }
        return exec;
    }
    
    private String A(final String s, final String s2) throws Exception {
        File file = null;
        URL url;
        try {
            url = new URL(s2);
        }
        catch (Exception ex) {
            file = new File(s2);
            url = file.toURI().toURL();
        }
        String s3 = url.toExternalForm();
        if (s3.toLowerCase().startsWith("file:/") && !s3.toLowerCase().startsWith("file:///")) {
            s3 = "file:///" + s3.substring("file:/".length());
        }
        String s4 = s.replaceAll("\\$\\{fileUrl\\}", s3.replaceAll("\\\\", "\\\\\\\\"));
        if (file != null) {
            s4 = s4.replaceAll("\\$\\{filePath\\}", file.getAbsolutePath().replaceAll("\\\\", "\\\\\\\\"));
        }
        return s4;
    }
    
    private Process B(final String s) {
        Process c = null;
        try {
            c = this.C(this.A(this.J, s));
        }
        catch (Exception ex) {
            this.E.E("openFileWindows( " + s + " ) failed", ex);
        }
        return c;
    }
    
    private Process A(final String s) {
        Process c = null;
        try {
            c = this.C(this.A(this.F, s));
        }
        catch (Exception ex) {
            this.E.E("openFolderWindowsCmd( " + s + " ) failed", ex);
        }
        return c;
    }
    
    private Process E(final String s) {
        Process c = null;
        try {
            c = this.C(this.A(this.G, s));
        }
        catch (Exception ex) {
            this.E.E("openFileUnix( " + s + " ) failed", ex);
        }
        return c;
    }
    
    private Process D(final String s) {
        Process c = null;
        try {
            c = this.C(this.A(this.I, s));
        }
        catch (Exception ex) {
            this.E.E("openFolderUnixCmd( " + s + " ) failed", ex);
        }
        return c;
    }
    
    public Process openFile(final String s) {
        Process process = null;
        switch (this.D) {
            case 1: {
                process = this.B(s);
                break;
            }
            case 2: {
                process = this.E(s);
                break;
            }
            default: {
                this.E.C("openFile() not implemented for OS: " + this.D);
                break;
            }
        }
        return process;
    }
    
    public Process openFolder(final String s) {
        Process process = null;
        switch (this.D) {
            case 1: {
                process = this.A(s);
                break;
            }
            case 2: {
                process = this.D(s);
                break;
            }
            default: {
                this.E.C("openFolder() not implemented for OS: " + this.D);
                break;
            }
        }
        return process;
    }
    
    public long getMaxMemory() {
        return Runtime.getRuntime().maxMemory();
    }
    
    public long getFreeMemory() {
        return Runtime.getRuntime().freeMemory();
    }
    
    public long getTotalMemory() {
        return Runtime.getRuntime().totalMemory();
    }
}
