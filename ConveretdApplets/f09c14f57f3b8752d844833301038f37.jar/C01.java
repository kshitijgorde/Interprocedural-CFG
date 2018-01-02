import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import netscape.security.PrivilegeManager;
import java.awt.Event;
import java.applet.Applet;
import java.net.URL;
import java.awt.FileDialog;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class C01 extends Frame
{
    FileDialog i;
    static boolean k;
    URL l;
    static boolean m;
    boolean n;
    URL o;
    Applet p;
    int q;
    String r;
    C51 s;
    
    public void show() {
        if (this.i == null) {
            super.show();
            this.resize(Math.max(this.preferredSize().width, this.q), Math.max(this.preferredSize().height, 450));
            this.validate();
            this.layout();
        }
        else {
            System.out.println("TRYING TO LOAD POLYMARKER WIDGET");
            try {
                Class.forName("PolymarkerWidget");
                System.out.println("Loaded polymarker");
            }
            catch (ClassNotFoundException ex) {
                System.out.println("e=" + ex);
            }
            try {
                this.h();
            }
            catch (Throwable t) {
                System.out.println("Error 839443 " + t);
                System.out.println("Loading a new drawing from the file system, requires IE4.0 or Communicator 4.0 or above");
                t.printStackTrace();
            }
            if (this.r == null) {
                this.i.show();
            }
            System.out.println("TRYING TO LOAD POLYMARKER WIDGET");
            try {
                Class.forName("PolymarkerWidget");
                System.out.println("Loaded polymarker");
            }
            catch (ClassNotFoundException ex2) {
                System.out.println("e=" + ex2);
            }
            if (this.i.getFile() != null) {
                this.d();
            }
        }
    }
    
    public boolean action(final Event event, final Object o) {
        return true;
    }
    
    static {
        C01.m = true;
        C01.k = false;
    }
    
    public C01(final Applet applet, final String s) {
        this.n = false;
        this.r = null;
    }
    
    public URL c() {
        return this.l;
    }
    
    public C01(final Applet applet) {
        this.n = false;
        this.r = null;
    }
    
    public C01(final Applet applet, final boolean b) {
        this.n = false;
        this.r = null;
    }
    
    private void d() {
        final String directory = this.i.getDirectory();
        final String file = this.i.getFile();
        if (directory == null || file == null) {
            System.out.println("Error 33423 " + directory + " " + file);
            return;
        }
        final String string = directory + file;
        System.out.println("f=" + string);
        this.i.setDirectory(this.o.toString());
        System.out.println("TRYING TO LOAD POLYMARKER WIDGET");
        try {
            Class.forName("PolymarkerWidget");
            System.out.println("Loaded polymarker");
        }
        catch (ClassNotFoundException ex) {
            System.out.println("e=" + ex);
        }
        if (C01.m) {
            try {
                PrivilegeManager.enablePrivilege("UniversalFileRead");
            }
            catch (Throwable t) {
                if (t instanceof ClassNotFoundException) {
                    C01.m = false;
                    System.out.println("IE SELECTED");
                }
                System.out.println("deny " + t);
            }
        }
        try {
            final FileInputStream fileInputStream = new FileInputStream(string);
            if (this.s != null) {
                this.s.a(fileInputStream, this.i.getFile());
            }
        }
        catch (IOException ex2) {
            System.out.println("Error loading " + string + "" + ex2.getMessage());
        }
    }
    
    public boolean isShowing() {
        if (this.i != null) {
            return this.i.isShowing();
        }
        return super.isShowing();
    }
    
    public void g() throws IOException {
        if (this.p == null) {
            System.out.println("Error #443443");
            return;
        }
        final String string = this.p.getDocumentBase().toString();
        final int lastIndex = string.lastIndexOf("/");
        if (lastIndex != -1) {
            string.substring(0, lastIndex + 1);
        }
    }
    
    private void h() throws Exception {
        if (C01.k) {
            return;
        }
        if (C01.m) {
            try {
                PrivilegeManager.enablePrivilege("UniversalFileRead");
                PrivilegeManager.enablePrivilege("UniversalPropertyRead");
            }
            catch (Throwable t) {
                if (t instanceof ClassNotFoundException) {
                    C01.m = false;
                    System.out.println("IE SELECTED");
                }
                System.out.println("deny " + t);
            }
        }
        File file = new File((this.o == null) ? "dummy" : this.o.getFile());
        if (!file.isDirectory() && new File(file.getAbsolutePath()).getParent() != null) {
            file = new File(new File(file.getAbsolutePath()).getParent());
        }
        final String[] list = file.list();
        for (int n = 0; list != null && n < list.length; ++n) {
            if (list[n].endsWith(".class")) {
                try {
                    Class.forName(list[n].substring(0, list[n].length() - 6));
                }
                catch (Exception ex) {
                    System.out.println("Error 99883 " + ex);
                    ex.printStackTrace();
                }
            }
        }
        C01.k = true;
    }
}
