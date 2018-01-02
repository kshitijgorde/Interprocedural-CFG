import java.io.InputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import java.security.CodeSource;
import java.io.File;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class SRLApplet extends Applet
{
    static final String VERSION = "4.4.24.0";
    public String SessionID;
    public String ReferrerID;
    public String UserID;
    public String Logging;
    public String Debug;
    public String Version;
    public String SysInfo;
    static final String INTEL_RELEASE_NAME = "intel";
    static final String CYRI_RELEASE_NAME = "cyri";
    static final String CLIENT_RELEASE_NAME = "client";
    static final String TEST_RELEASE_NAME = "test";
    static final String PARAM_LOGGING_LEVEL = "LOGGING";
    static final String PARAM_PRIVACY_LEVEL = "PRIVACY";
    static final String PARAM_SESSION_ID = "SESSION_ID";
    static final String PARAM_REFERRER_ID = "REFERRER_ID";
    static final String PARAM_APPLICATION_ID = "APPLICATION_ID";
    static final String PARAM_CLIENT_ID = "CLIENT_ID";
    static final String PARAM_IP = "IP";
    static final String PARAM_INCLUDE = "INCLUDE";
    static final String PARAM_EXCLUDE = "EXCLUDE";
    static final String INTEL_PROXY_FILE_NAME = "srlproxy_intel_4.4.24.0.dll";
    static final String CYRI_PROXY_FILE_NAME = "srlproxy_cyri_4.4.24.0.dll";
    static final String CLIENT_PROXY_FILE_NAME = "srlproxy_client_4.4.24.0.dll";
    static final String TEST_PROXY_FILE_NAME = "srlproxy_test_4.4.24.0.dll";
    static final String INTEL_MSI_FILE_NAME = "SystemRequirementsLab_intel_4.4.24.0.msi";
    static final String CYRI_MSI_FILE_NAME = "SystemRequirementsLab_cyri_4.4.24.0.msi";
    static final String CLIENT_MSI_FILE_NAME = "SystemRequirementsLab_client_4.4.24.0.msi";
    static final String TEST_MSI_FILE_NAME = "SystemRequirementsLab_test_4.4.24.0.msi";
    static final String JAR_INTEL_PROXY_FILE_NAME = "srlproxy_intel.dll";
    static final String JAR_CYRI_PROXY_FILE_NAME = "srlproxy_cyri.dll";
    static final String JAR_CLIENT_PROXY_FILE_NAME = "srlproxy_client.dll";
    static final String JAR_TEST_PROXY_FILE_NAME = "srlproxy_test.dll";
    static final String JAR_MSI_FILE_NAME = "SystemRequirementsLab.msi";
    String _jarMSIFileName;
    String _jarProxyFileName;
    String _msiFileName;
    String _proxyFileName;
    String _proxyDir;
    String _msiDir;
    String _msiPath;
    SRLProxy srlProxy;
    long error;
    String errorText;
    public static final int BUFFER_SIZE = 4096;
    boolean Inited;
    static /* synthetic */ Class class$SRLApplet;
    
    public SRLApplet() {
        this.SessionID = "";
        this.ReferrerID = "";
        this.UserID = "";
        this.Logging = "0";
        this.Debug = "";
        this.Version = "4.4.24.0";
        this.SysInfo = "";
        this._jarMSIFileName = "SystemRequirementsLab.msi";
        this.srlProxy = null;
        this.error = 0L;
        this.errorText = "";
        this.Inited = false;
    }
    
    public void init() {
        try {
            final String parameter = this.getParameter("LOGGING");
            if (parameter != null && parameter.length() > 0) {
                this.Logging = parameter;
            }
            final String parameter2 = this.getParameter("SESSION_ID");
            if (parameter2 != null && parameter2.length() > 0) {
                this.SessionID = parameter2;
            }
            final String parameter3 = this.getParameter("REFERRER_ID");
            if (parameter3 != null && parameter3.length() > 0) {
                this.ReferrerID = parameter3;
            }
            final String parameter4 = this.getParameter("CLIENT_ID");
            if (parameter4 != null && parameter4.length() > 0) {
                this.UserID = parameter4;
            }
            boolean b = false;
            boolean b2 = false;
            boolean b3 = false;
            boolean b4 = false;
            try {
                final CodeSource codeSource = this.getClass().getProtectionDomain().getCodeSource();
                if (codeSource != null) {
                    final String s = new String(codeSource.getLocation().toString());
                    if (s.indexOf("cyri") != -1) {
                        b2 = true;
                    }
                    if (s.indexOf("intel") != -1) {
                        b = true;
                    }
                    if (s.indexOf("client") != -1) {
                        b3 = true;
                    }
                    if (s.indexOf("test") != -1) {
                        b4 = true;
                    }
                }
                else {
                    System.out.println("unknown source, likely rt.jar");
                }
            }
            catch (Exception ex) {
                System.err.println("Unable to locate class on command line.");
                ex.printStackTrace();
            }
            if (b) {
                this._jarProxyFileName = "srlproxy_intel.dll";
                this._msiFileName = "SystemRequirementsLab_intel_4.4.24.0.msi";
                this._proxyFileName = "srlproxy_intel_4.4.24.0.dll";
            }
            if (b2) {
                this._jarProxyFileName = "srlproxy_cyri.dll";
                this._msiFileName = "SystemRequirementsLab_cyri_4.4.24.0.msi";
                this._proxyFileName = "srlproxy_cyri_4.4.24.0.dll";
            }
            if (b3) {
                this._jarProxyFileName = "srlproxy_client.dll";
                this._msiFileName = "SystemRequirementsLab_client_4.4.24.0.msi";
                this._proxyFileName = "srlproxy_client_4.4.24.0.dll";
            }
            if (b4) {
                this._jarProxyFileName = "srlproxy_test.dll";
                this._msiFileName = "SystemRequirementsLab_test_4.4.24.0.msi";
                this._proxyFileName = "srlproxy_test_4.4.24.0.dll";
            }
            this._proxyDir = System.getProperty("user.home");
            this._proxyDir += "\\Application Data";
            if (!new File(this._proxyDir).exists()) {
                this._proxyDir = System.getProperty("user.home");
            }
            this._proxyDir += "\\SystemRequirementsLab";
            this._msiDir = this._proxyDir;
            new File(this._proxyDir).mkdirs();
            this._msiPath = this._proxyDir + "\\" + this._msiFileName;
            this.GetFileFromJAR(this._jarMSIFileName, this._msiPath);
            if (null == this.srlProxy) {
                this.LoadProxy();
                this.srlProxy = new SRLProxy();
            }
        }
        catch (Exception ex2) {
            System.err.println("Unable to locate class on command line.");
            ex2.printStackTrace();
        }
    }
    
    private void InitSRL() {
        if (this.Inited) {
            return;
        }
        try {
            this.srlProxy.Install(this._msiPath);
            this.SendAttributes();
            this.GetAttributes();
            this.Inited = true;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("error: " + ex.getMessage());
        }
    }
    
    public void start() {
        try {
            if (null != this.srlProxy) {
                this.srlProxy.start();
            }
        }
        catch (Exception ex) {
            this.error = 1L;
            this.errorText = "error";
            System.out.println("error: " + ex.getMessage());
        }
    }
    
    public void stop() {
        try {
            if (null != this.srlProxy) {
                this.srlProxy.stop();
            }
        }
        catch (Exception ex) {
            System.out.println("error: " + ex.getMessage());
        }
    }
    
    public void destroy() {
        try {
            if (null != this.srlProxy) {
                this.srlProxy = null;
            }
        }
        catch (Exception ex) {
            System.out.println("error: " + ex.getMessage());
        }
    }
    
    private void GetFileFromJAR(final String s, final String s2) {
        try {
            if (new File(s2).exists()) {
                return;
            }
            final InputStream resourceAsStream = ((SRLApplet.class$SRLApplet == null) ? (SRLApplet.class$SRLApplet = class$("SRLApplet")) : SRLApplet.class$SRLApplet).getResourceAsStream(s);
            final byte[] array = new byte[4096];
            try {
                final FileOutputStream fileOutputStream = new FileOutputStream(s2);
                while (true) {
                    final int read = resourceAsStream.read(array, 0, array.length);
                    if (read == -1) {
                        break;
                    }
                    fileOutputStream.write(array, 0, read);
                }
                fileOutputStream.close();
                resourceAsStream.close();
            }
            catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        catch (Exception ex2) {
            System.out.println("error: " + ex2.getMessage());
        }
    }
    
    private void LoadProxy() {
        try {
            int i = 0;
            while (i < 4) {
                final StringBuffer sb = new StringBuffer("");
                sb.append(this._proxyDir + "\\" + this._proxyFileName);
                sb.insert(sb.length() - 4, (char)(65 + i));
                this.GetFileFromJAR(this._jarProxyFileName, sb.toString());
                try {
                    System.load(sb.toString());
                }
                catch (UnsatisfiedLinkError unsatisfiedLinkError) {
                    System.err.println("fail" + (Object)sb);
                    ++i;
                    continue;
                }
                break;
            }
        }
        catch (Exception ex) {
            System.out.println("errorx: " + ex.getMessage());
            System.err.println(ex.toString());
            this.error = 1L;
            this.errorText = "error";
        }
    }
    
    private void SendAttributes() {
        try {
            if (this.srlProxy != null) {
                if (this.ReferrerID.length() > 0) {
                    this.srlProxy.setReferrerID(this.ReferrerID);
                }
                if (this.SessionID.length() > 0) {
                    this.srlProxy.setSessionID(this.SessionID);
                }
                this.SessionID = this.srlProxy.getSessionID();
                if (this.UserID.length() > 0) {
                    this.srlProxy.setUserID(this.UserID);
                }
                if (this.Logging.length() > 0) {
                    this.srlProxy.setLogging(this.Logging);
                }
            }
        }
        catch (Exception ex) {
            System.out.println("error ");
        }
    }
    
    private void GetAttributes() {
        try {
            if (this.srlProxy != null) {
                this.ReferrerID = this.srlProxy.getReferrerID();
                this.SessionID = this.srlProxy.getSessionID();
                this.UserID = this.srlProxy.getUserID();
                this.Logging = this.srlProxy.getLogging();
                this.Version = this.srlProxy.getVersion();
            }
        }
        catch (Exception ex) {
            System.out.println("error ");
        }
    }
    
    public void Detect() {
        try {
            this.InitSRL();
            this.SendAttributes();
            this.srlProxy.Detect();
            this.SysInfo = this.System("");
        }
        catch (Exception ex) {
            System.out.println("error ");
        }
    }
    
    public void Configure(final String s) {
        try {
            this.InitSRL();
            this.SendAttributes();
            this.srlProxy.Configure(s);
        }
        catch (Exception ex) {
            System.out.println("error ");
        }
    }
    
    public String System(final String s) {
        String system = "";
        try {
            this.InitSRL();
            this.SendAttributes();
            system = this.srlProxy.getSystem(s);
        }
        catch (Exception ex) {
            System.out.println("error ");
        }
        return system;
    }
    
    public String Analysis(final String s, final String s2, final String s3) {
        String analysis = "";
        try {
            this.InitSRL();
            this.SendAttributes();
            analysis = this.srlProxy.getAnalysis(s, s2, s3);
        }
        catch (Exception ex) {
            System.out.println("error ");
        }
        return analysis;
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}
