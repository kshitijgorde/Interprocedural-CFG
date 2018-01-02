// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.browser;

import org.eclipse.swt.internal.mozilla.nsIFile;
import java.util.Vector;
import org.eclipse.swt.internal.mozilla.nsIDirectoryServiceProvider2;
import org.eclipse.swt.internal.mozilla.nsIDirectoryServiceProvider;
import org.eclipse.swt.internal.C;
import org.eclipse.swt.internal.mozilla.nsISupports;
import org.eclipse.swt.internal.mozilla.nsID;
import org.eclipse.swt.internal.mozilla.nsILocalFile;
import org.eclipse.swt.internal.mozilla.XPCOM;
import org.eclipse.swt.internal.mozilla.nsEmbedString;
import org.eclipse.swt.internal.Compatibility;
import org.eclipse.swt.internal.mozilla.XPCOMObject;

class AppFileLocProvider
{
    XPCOMObject supports;
    XPCOMObject directoryServiceProvider;
    XPCOMObject directoryServiceProvider2;
    int refCount;
    String mozillaPath;
    String profilePath;
    String[] pluginDirs;
    boolean isXULRunner;
    static final String SEPARATOR_OS;
    static final String CHROME_DIR = "chrome";
    static final String COMPONENTS_DIR = "components";
    static final String HISTORY_FILE = "history.dat";
    static final String LOCALSTORE_FILE = "localstore.rdf";
    static final String MIMETYPES_FILE = "mimeTypes.rdf";
    static final String PLUGINS_DIR = "plugins";
    static final String USER_PLUGINS_DIR;
    static final String PREFERENCES_FILE = "prefs.js";
    static boolean IsSparc;
    
    static {
        SEPARATOR_OS = System.getProperty("file.separator");
        USER_PLUGINS_DIR = ".mozilla" + AppFileLocProvider.SEPARATOR_OS + "plugins";
        final String lowerCase = System.getProperty("os.name").toLowerCase();
        final String lowerCase2 = System.getProperty("os.arch").toLowerCase();
        AppFileLocProvider.IsSparc = ((lowerCase.startsWith("sunos") || lowerCase.startsWith("solaris")) && lowerCase2.startsWith("sparc"));
    }
    
    AppFileLocProvider(final String s, final String s2, final boolean isXULRunner) {
        this.refCount = 0;
        this.mozillaPath = String.valueOf(s) + AppFileLocProvider.SEPARATOR_OS;
        this.profilePath = String.valueOf(s2) + AppFileLocProvider.SEPARATOR_OS;
        this.isXULRunner = isXULRunner;
        if (!Compatibility.fileExists(s2, "")) {
            final int[] array = { 0 };
            final nsEmbedString nsEmbedString = new nsEmbedString(s2);
            final int ns_NewLocalFile = XPCOM.NS_NewLocalFile(nsEmbedString.getAddress(), 1, array);
            if (ns_NewLocalFile != 0) {
                Mozilla.error(ns_NewLocalFile);
            }
            if (array[0] == 0) {
                Mozilla.error(-2147467261);
            }
            nsEmbedString.dispose();
            final nsILocalFile nsILocalFile = new nsILocalFile(array[0]);
            final int create = nsILocalFile.Create(1, 448);
            if (create != 0) {
                Mozilla.error(create);
            }
            nsILocalFile.Release();
        }
        this.createCOMInterfaces();
    }
    
    int AddRef() {
        return ++this.refCount;
    }
    
    void createCOMInterfaces() {
        this.supports = new XPCOMObject(new int[] { 2, 0, 0 }) {
            public int method0(final int[] array) {
                return AppFileLocProvider.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return AppFileLocProvider.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return AppFileLocProvider.this.Release();
            }
        };
        this.directoryServiceProvider = new XPCOMObject(new int[] { 2, 0, 0, 3 }) {
            public int method0(final int[] array) {
                return AppFileLocProvider.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return AppFileLocProvider.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return AppFileLocProvider.this.Release();
            }
            
            public int method3(final int[] array) {
                return AppFileLocProvider.this.getFile(array[0], array[1], array[2]);
            }
        };
        this.directoryServiceProvider2 = new XPCOMObject(new int[] { 2, 0, 0, 3, 2 }) {
            public int method0(final int[] array) {
                return AppFileLocProvider.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return AppFileLocProvider.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return AppFileLocProvider.this.Release();
            }
            
            public int method3(final int[] array) {
                return AppFileLocProvider.this.getFile(array[0], array[1], array[2]);
            }
            
            public int method4(final int[] array) {
                return AppFileLocProvider.this.getFiles(array[0], array[1]);
            }
        };
    }
    
    void disposeCOMInterfaces() {
        if (this.supports != null) {
            this.supports.dispose();
            this.supports = null;
        }
        if (this.directoryServiceProvider != null) {
            this.directoryServiceProvider.dispose();
            this.directoryServiceProvider = null;
        }
        if (this.directoryServiceProvider2 != null) {
            this.directoryServiceProvider2.dispose();
            this.directoryServiceProvider2 = null;
        }
    }
    
    int getAddress() {
        return this.directoryServiceProvider.getAddress();
    }
    
    int QueryInterface(final int n, final int n2) {
        if (n == 0 || n2 == 0) {
            return -2147467262;
        }
        final nsID nsID = new nsID();
        XPCOM.memmove(nsID, n, 16);
        if (nsID.Equals(nsISupports.NS_ISUPPORTS_IID)) {
            C.memmove(n2, new int[] { this.supports.getAddress() }, C.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        if (nsID.Equals(nsIDirectoryServiceProvider.NS_IDIRECTORYSERVICEPROVIDER_IID)) {
            C.memmove(n2, new int[] { this.directoryServiceProvider.getAddress() }, C.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        if (nsID.Equals(nsIDirectoryServiceProvider2.NS_IDIRECTORYSERVICEPROVIDER2_IID)) {
            C.memmove(n2, new int[] { this.directoryServiceProvider2.getAddress() }, C.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        C.memmove(n2, new int[1], C.PTR_SIZEOF);
        return -2147467262;
    }
    
    int Release() {
        --this.refCount;
        if (this.refCount == 0) {
            this.disposeCOMInterfaces();
        }
        return this.refCount;
    }
    
    int getFiles(final int n, final int n2) {
        final int strlen = C.strlen(n);
        final byte[] array = new byte[strlen];
        C.memmove(array, n, strlen);
        final String s = new String(MozillaDelegate.mbcsToWcs(null, array));
        String[] pluginDirs = null;
        if (s.equals("APluginsDL")) {
            if (this.pluginDirs == null) {
                int i = 0;
                final int getenv = C.getenv(MozillaDelegate.wcsToMbcs(null, "MOZ_PLUGIN_PATH", true));
                if (getenv != 0) {
                    final int strlen2 = C.strlen(getenv);
                    final byte[] array2 = new byte[strlen2];
                    C.memmove(array2, getenv, strlen2);
                    final String s2 = new String(MozillaDelegate.mbcsToWcs(null, array2));
                    if (s2.length() > 0) {
                        final String property = System.getProperty("path.separator");
                        final Vector vector = new Vector<String>();
                        int j = -1;
                        do {
                            final int n3 = j + 1;
                            j = s2.indexOf(property, n3);
                            String s3;
                            if (j == -1) {
                                s3 = s2.substring(n3);
                            }
                            else {
                                s3 = s2.substring(n3, j);
                            }
                            if (s3.length() > 0) {
                                vector.addElement(s3);
                            }
                        } while (j != -1);
                        final int size = vector.size();
                        this.pluginDirs = new String[size + (AppFileLocProvider.IsSparc ? 1 : 2)];
                        for (i = 0; i < size; ++i) {
                            this.pluginDirs[i] = vector.elementAt(i);
                        }
                    }
                }
                if (this.pluginDirs == null) {
                    this.pluginDirs = new String[AppFileLocProvider.IsSparc ? 1 : 2];
                }
                if (!AppFileLocProvider.IsSparc) {
                    this.pluginDirs[i++] = String.valueOf(this.mozillaPath) + "plugins";
                }
                this.pluginDirs[i++] = String.valueOf(System.getProperty("user.home")) + AppFileLocProvider.SEPARATOR_OS + AppFileLocProvider.USER_PLUGINS_DIR;
            }
            pluginDirs = this.pluginDirs;
        }
        C.memmove(n2, new int[1], C.PTR_SIZEOF);
        if (pluginDirs != null) {
            final int[] array3 = { 0 };
            nsISupports[] array4 = new nsISupports[pluginDirs.length];
            int n4 = 0;
            for (int k = 0; k < pluginDirs.length; ++k) {
                final nsEmbedString nsEmbedString = new nsEmbedString(pluginDirs[k]);
                final int ns_NewLocalFile = XPCOM.NS_NewLocalFile(nsEmbedString.getAddress(), 1, array3);
                if (ns_NewLocalFile != -2142109695) {
                    if (ns_NewLocalFile != 0) {
                        Mozilla.error(ns_NewLocalFile);
                    }
                    if (array3[0] == 0) {
                        Mozilla.error(-2147467261);
                    }
                    final nsILocalFile nsILocalFile = new nsILocalFile(array3[0]);
                    array3[0] = 0;
                    final int queryInterface = nsILocalFile.QueryInterface(nsIFile.NS_IFILE_IID, array3);
                    if (queryInterface != 0) {
                        Mozilla.error(queryInterface);
                    }
                    if (array3[0] == 0) {
                        Mozilla.error(-2147467262);
                    }
                    nsILocalFile.Release();
                    array4[n4++] = new nsIFile(array3[0]);
                }
                nsEmbedString.dispose();
                array3[0] = 0;
            }
            if (n4 < pluginDirs.length) {
                final nsISupports[] array5 = new nsISupports[n4];
                System.arraycopy(array4, 0, array5, 0, n4);
                array4 = array5;
            }
            final SimpleEnumerator simpleEnumerator = new SimpleEnumerator(array4);
            simpleEnumerator.AddRef();
            C.memmove(n2, new int[] { simpleEnumerator.getAddress() }, C.PTR_SIZEOF);
            return 0;
        }
        return -2147467259;
    }
    
    int getFile(final int n, final int n2, final int n3) {
        final int strlen = C.strlen(n);
        final byte[] array = new byte[strlen];
        C.memmove(array, n, strlen);
        final String s = new String(MozillaDelegate.mbcsToWcs(null, array));
        String s2 = null;
        if (s.equals("UHist")) {
            s2 = String.valueOf(this.profilePath) + "history.dat";
        }
        else if (s.equals("UMimTyp")) {
            s2 = String.valueOf(this.profilePath) + "mimeTypes.rdf";
        }
        else if (s.equals("PrefF")) {
            s2 = String.valueOf(this.profilePath) + "prefs.js";
        }
        else if (s.equals("PrefD")) {
            s2 = this.profilePath;
        }
        else if (s.equals("UChrm")) {
            s2 = String.valueOf(this.profilePath) + "chrome";
        }
        else if (s.equals("ProfD")) {
            s2 = this.profilePath;
        }
        else if (s.equals("LclSt")) {
            s2 = String.valueOf(this.profilePath) + "localstore.rdf";
        }
        else if (s.equals("cachePDir")) {
            s2 = this.profilePath;
        }
        else if (s.equals("Home")) {
            s2 = System.getProperty("user.home");
        }
        else if (s.equals("TmpD")) {
            s2 = System.getProperty("java.io.tmpdir");
        }
        else if (s.equals("GreD")) {
            s2 = this.mozillaPath;
        }
        else if (s.equals("GreComsD")) {
            s2 = String.valueOf(this.profilePath) + "components";
        }
        else if (s.equals("MozBinD")) {
            s2 = this.mozillaPath;
        }
        else if (s.equals("CurProcD")) {
            s2 = this.mozillaPath;
        }
        else if (s.equals("ComsD")) {
            s2 = String.valueOf(this.mozillaPath) + "components";
        }
        else if (s.equals("XCurProcD")) {
            s2 = this.mozillaPath;
        }
        else if (s.equals("PrfDef") && this.isXULRunner) {
            s2 = this.profilePath;
        }
        C.memmove(n2, new int[] { 1 }, 4);
        C.memmove(n3, new int[1], C.PTR_SIZEOF);
        if (s2 != null && s2.length() > 0) {
            final int[] array2 = { 0 };
            final nsEmbedString nsEmbedString = new nsEmbedString(s2);
            final int ns_NewLocalFile = XPCOM.NS_NewLocalFile(nsEmbedString.getAddress(), 1, array2);
            if (ns_NewLocalFile != 0) {
                Mozilla.error(ns_NewLocalFile);
            }
            if (array2[0] == 0) {
                Mozilla.error(-2147467261);
            }
            nsEmbedString.dispose();
            final nsILocalFile nsILocalFile = new nsILocalFile(array2[0]);
            array2[0] = 0;
            final int queryInterface = nsILocalFile.QueryInterface(nsIFile.NS_IFILE_IID, array2);
            if (queryInterface != 0) {
                Mozilla.error(queryInterface);
            }
            if (array2[0] == 0) {
                Mozilla.error(-2147467262);
            }
            C.memmove(n3, new int[] { array2[0] }, C.PTR_SIZEOF);
            nsILocalFile.Release();
            return 0;
        }
        return -2147467259;
    }
}
