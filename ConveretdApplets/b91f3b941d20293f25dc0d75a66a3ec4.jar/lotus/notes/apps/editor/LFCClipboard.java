// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.editor;

import java.io.IOException;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.datatransfer.StringSelection;
import netscape.security.PrivilegeManager;
import java.awt.Toolkit;
import com.ms.security.PolicyEngine;
import com.ms.security.PermissionID;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Clipboard;

public class LFCClipboard extends Clipboard implements ClipboardOwner
{
    private static LFCClipboard cbm;
    private Clipboard systemClipboard;
    private Transferable privateClipContents;
    private ClipboardOwner privateClipOwner;
    private String privateClipStringForm;
    private static String clipName;
    private static String BrowserVendor;
    private static boolean isNS;
    private static boolean isMSJVM;
    public static DataFlavor HTMLFlavor;
    private static char[] crlf;
    private static String CRLF;
    static /* synthetic */ Class class$lotus$notes$apps$editor$LFCClipboard;
    
    public static LFCClipboard getLFCClipboard() {
        if (LFCClipboard.cbm == null) {
            Class class$;
            Class class$lotus$notes$apps$editor$LFCClipboard;
            if (LFCClipboard.class$lotus$notes$apps$editor$LFCClipboard == null) {
                class$lotus$notes$apps$editor$LFCClipboard = (LFCClipboard.class$lotus$notes$apps$editor$LFCClipboard = (class$ = class$("lotus.notes.apps.editor.LFCClipboard")));
            }
            else {
                class$ = (class$lotus$notes$apps$editor$LFCClipboard = LFCClipboard.class$lotus$notes$apps$editor$LFCClipboard);
            }
            final Class clazz = class$lotus$notes$apps$editor$LFCClipboard;
            synchronized (class$) {
                if (LFCClipboard.cbm == null) {
                    LFCClipboard.cbm = new LFCClipboard(LFCClipboard.clipName);
                }
            }
            LFCClipboard.BrowserVendor = System.getProperty("browser");
            if (LFCClipboard.BrowserVendor != null && LFCClipboard.BrowserVendor.length() > 0) {
                LFCClipboard.BrowserVendor = LFCClipboard.BrowserVendor.toLowerCase();
                if (LFCClipboard.BrowserVendor.indexOf("netscape") != -1) {
                    LFCClipboard.isNS = true;
                }
            }
            final String property = System.getProperty("java.vendor");
            if (property != null && property.length() > 0 && property.toLowerCase().indexOf("microsoft") != -1) {
                LFCClipboard.isMSJVM = true;
            }
        }
        return LFCClipboard.cbm;
    }
    
    private LFCClipboard() {
        this(LFCClipboard.clipName);
    }
    
    private LFCClipboard(final String s) {
        super(s);
        this.systemClipboard = null;
        this.privateClipContents = null;
        this.privateClipOwner = null;
        this.privateClipStringForm = null;
    }
    
    private Clipboard getSystemClipboard() {
        if (this.systemClipboard == null) {
            boolean b = false;
            if (LFCClipboard.isMSJVM) {
                try {
                    PolicyEngine.assertPermission(PermissionID.UI);
                    this.systemClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    PolicyEngine.denyPermission(PermissionID.UI);
                    b = true;
                }
                catch (Exception ex) {
                    b = false;
                }
            }
            else if (LFCClipboard.isNS) {
                try {
                    PrivilegeManager.enablePrivilege("UniversalSystemClipboardAccess");
                    this.systemClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    PrivilegeManager.disablePrivilege("UniversalSystemClipboardAccess");
                    b = true;
                }
                catch (Exception ex2) {
                    b = false;
                }
            }
            if (!b) {
                try {
                    if (this.systemClipboard == null) {
                        this.systemClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    }
                }
                catch (Exception ex3) {
                    System.out.println("Unable to obain System clipboard, External cut/copy/paste not available");
                }
            }
        }
        return this.systemClipboard;
    }
    
    public boolean isDataAvailable() {
        boolean b = this.privateClipContents != null;
        if (!b) {
            final Clipboard systemClipboard = this.getSystemClipboard();
            if (systemClipboard != null) {
                b = (systemClipboard.getContents(this) != null);
            }
        }
        return b;
    }
    
    public void lostOwnership(final Clipboard clipboard, final Transferable transferable) {
    }
    
    public synchronized void setContents(final Transferable privateClipContents, final ClipboardOwner privateClipOwner) {
        if (this.privateClipOwner != null && this.privateClipContents != null) {
            this.privateClipOwner.lostOwnership(this, this.privateClipContents);
        }
        this.privateClipContents = privateClipContents;
        this.privateClipOwner = privateClipOwner;
        try {
            this.privateClipStringForm = (String)privateClipContents.getTransferData(DataFlavor.stringFlavor);
        }
        catch (Exception ex) {
            this.privateClipStringForm = "";
        }
        final Clipboard systemClipboard = this.getSystemClipboard();
        if (systemClipboard != null) {
            systemClipboard.setContents(new StringSelection(this.privateClipStringForm), this);
        }
    }
    
    public String getName() {
        return LFCClipboard.clipName;
    }
    
    public synchronized Transferable getContents(final Object o) {
        final Clipboard systemClipboard = this.getSystemClipboard();
        if (systemClipboard == null) {
            return this.privateClipContents;
        }
        final Transferable contents = systemClipboard.getContents(this);
        if (contents == null) {
            return null;
        }
        String s = null;
        String privateClipStringForm = null;
        try {
            s = (String)contents.getTransferData(DataFlavor.stringFlavor);
            if (this.privateClipContents != null) {
                privateClipStringForm = this.privateClipStringForm;
            }
        }
        catch (UnsupportedFlavorException ex) {}
        catch (IOException ex2) {}
        if (s != null && !this.compareClipStrings(s, privateClipStringForm)) {
            return contents;
        }
        return this.privateClipContents;
    }
    
    private boolean compareClipStrings(final String s, final String s2) {
        if (LFCClipboard.isMSJVM && s2 != null && s2.indexOf(LFCClipboard.CRLF) != -1) {
            final int length = s2.length();
            final char[] array = new char[length];
            s2.getChars(0, length, array, 0);
            int n = 0;
            final char[] array2 = new char[length];
            for (int i = 0; i < length; ++i) {
                if (i >= length - 1 || array[i] != '\r' || array[i + 1] != '\n') {
                    array2[n++] = array[i];
                }
            }
            return s.equals(new String(array2, 0, n));
        }
        return s.equals(s2);
    }
    
    public boolean isFlavorSupported(final DataFlavor[] array) {
        final Transferable contents = this.getContents(this);
        if (contents == null) {
            return false;
        }
        final DataFlavor[] transferDataFlavors = contents.getTransferDataFlavors();
        for (int i = 0; i < array.length; ++i) {
            for (int j = 0; j < transferDataFlavors.length; ++j) {
                if (array[i].equals(transferDataFlavors[j])) {
                    return true;
                }
            }
        }
        return false;
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        LFCClipboard.cbm = null;
        LFCClipboard.clipName = "LFCClipboard";
        LFCClipboard.BrowserVendor = "";
        LFCClipboard.isNS = false;
        LFCClipboard.isMSJVM = false;
        LFCClipboard.HTMLFlavor = new DataFlavor("text/html", "Lotus HTML");
        LFCClipboard.crlf = new char[] { '\r', '\n' };
        LFCClipboard.CRLF = new String(LFCClipboard.crlf);
    }
}
