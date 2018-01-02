// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.F.A.A;

import java.util.zip.ZipEntry;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import jmaster.util.B.C;
import jmaster.util.log.B;

public class A implements jmaster.util.F.A.A
{
    protected jmaster.util.log.A A;
    
    public A() {
        this.A = B.getInstance().getLog(this.getClass());
    }
    
    public void A(final String s, final String s2, final C c) throws IOException {
        final jmaster.util.F.A.A.A.A a = new jmaster.util.F.A.A.A.A(new BufferedOutputStream(new FileOutputStream(s2)));
        this.A("", s, a, c);
        a.flush();
        a.close();
    }
    
    private void B(final String s, final String s2, final jmaster.util.F.A.A.A.A a, final C c) throws IOException {
        if (this.A.B()) {
            this.A.D("Zipping file: " + s2);
        }
        final File file = new File(s2);
        if (file.isDirectory()) {
            this.A(s, s2, a, c);
        }
        else {
            final byte[] array = new byte[1024];
            final BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(s2));
            final ZipEntry zipEntry = new ZipEntry(((s == null || s.trim().length() == 0) ? "" : (s + "/")) + file.getName());
            zipEntry.setSize(new File(s2).length());
            a.putNextEntry(zipEntry);
            int read;
            while ((read = bufferedInputStream.read(array)) > 0) {
                a.write(array, 0, read);
            }
            if (c != null) {
                c.A(c.A() + file.length());
            }
        }
        if (this.A.B()) {
            this.A.D("Finished zipping file: " + s2);
        }
    }
    
    private void A(final String s, final String s2, final jmaster.util.F.A.A.A.A a, final C c) throws IOException {
        final File file = new File(s2);
        final String[] list = file.list();
        for (int n = 0; list != null && n < list.length; ++n) {
            this.B(((s == null || s.trim().length() == 0) ? "" : (s + "/")) + file.getName(), s2 + "/" + list[n], a, c);
        }
    }
    
    public static void A(final String[] array) {
        try {
            new A().A("C:\\temp\\x", "C:\\temp\\x.zip", null);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
