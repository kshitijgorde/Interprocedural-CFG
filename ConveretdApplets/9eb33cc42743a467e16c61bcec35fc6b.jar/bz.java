import java.net.Socket;
import java.io.FileOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PipedOutputStream;
import java.io.PipedInputStream;
import java.io.File;

// 
// Decompiled by Procyon v0.5.30
// 

public class bz implements b_, bw
{
    public String la;
    public int k9;
    public b5 e9;
    public bo c;
    public b_ k8;
    public ad k7;
    public bo k6;
    public File k5;
    public boolean gv;
    public boolean k4;
    public String k3;
    public PipedInputStream k2;
    public PipedOutputStream k1;
    
    public bz(final String la, final int k9) {
        this.la = la;
        this.k9 = k9;
    }
    
    public final String gb() {
        return this.la;
    }
    
    public final int ga() {
        return this.k9;
    }
    
    public final String f8() {
        return "";
    }
    
    public final int f7() {
        return 0;
    }
    
    public final int f6() {
        return 0;
    }
    
    public final boolean f5() {
        return false;
    }
    
    public final boolean f4() {
        return false;
    }
    
    public bz(final String k3, final int n, final as as, final File k4, final boolean gv, final boolean k5) throws IOException {
        this(k3, n);
        this.k6 = new bn(this);
        this.e9 = new b5(as, this);
        this.k2 = new PipedInputStream();
        this.k1 = new PipedOutputStream(this.k2);
        this.k5 = k4;
        this.gv = gv;
        this.k4 = k5;
        this.k3 = k3;
        this.e9.lq(this);
        this.e9.lf = false;
    }
    
    public final void hk(final bo c) {
        this.c = c;
    }
    
    public final void kl(final b_ k8) {
        this.k8 = k8;
    }
    
    public final void kk(final ad k7) {
        this.k7 = k7;
    }
    
    public final void kj() {
        this.c = null;
        this.k7 = null;
        this.e9.lf();
    }
    
    public final void ki(final String s, String s2) throws IOException {
        File file = new File(s);
        if (!file.isAbsolute()) {
            file = new File(this.k5, s);
        }
        if (!file.exists()) {
            throw new IOException("File: " + s + " does not exist");
        }
        if (!file.isFile() && !file.isDirectory()) {
            throw new IOException("File: " + s + " is not a regular file or directory");
        }
        if (file.isDirectory() && !this.gv) {
            throw new IOException("File: " + s + " is a directory, use recursive mode");
        }
        if (s2 == null || s2.equals("")) {
            s2 = ".";
        }
        this.e9.jm("scp " + (file.isDirectory() ? "-d " : "") + "-t " + (this.gv ? "-r " : "") + (this.k4 ? "-v " : "") + s2, true, 0L);
        this.kc("After starting remote scp");
        this.kf(file);
        this.e9.lf();
    }
    
    public final void ki(final String[] array, String s) throws IOException {
        if (s == null || s.equals("")) {
            s = ".";
        }
        if (array.length == 1) {
            this.ki(array[0], s);
        }
        else {
            this.e9.jm("scp -d -t " + (this.gv ? "-r " : "") + (this.k4 ? "-v " : "") + s, true, 0L);
            this.kc("After starting remote scp");
            for (int i = 0; i < array.length; ++i) {
                File file = new File(array[i]);
                if (!file.isAbsolute()) {
                    file = new File(this.k5, array[i]);
                }
                if (!file.isFile() && !file.isDirectory()) {
                    this.h9("File: " + file.getName() + " is not a regular file or directory");
                }
                else {
                    this.kf(file);
                }
            }
            this.e9.lf();
        }
    }
    
    public final void kh(String s, final String s2) throws IOException {
        if (s == null || s.equals("")) {
            s = ".";
        }
        File file = new File(s);
        if (!file.isAbsolute()) {
            file = new File(this.k5, s);
        }
        if (file.exists() && !file.isFile() && !file.isDirectory()) {
            throw new IOException("File: " + s + " is not a regular file or directory");
        }
        this.e9.jm("scp -f " + (this.gv ? "-r " : "") + (this.k4 ? "-v " : "") + s2, true, 0L);
        this.ke(file);
        this.e9.lf();
    }
    
    public final boolean kg(final File file) throws IOException {
        if (!this.gv) {
            this.kb("File " + file.getName() + " is a directory, use recursive mode");
            return false;
        }
        this.jt("D0755 0 " + file.getName() + "\n");
        if (this.k7 != null) {
            this.k7.e9(file.getAbsolutePath());
        }
        this.kc("After sedning dirdata");
        final String[] list = file.list();
        for (int i = 0; i < list.length; ++i) {
            this.kf(new File(file, list[i]));
        }
        this.jt("E\n");
        if (this.k7 != null) {
            this.k7.e7();
        }
        return true;
    }
    
    public final void kf(final File file) throws IOException {
        if (file.isDirectory()) {
            if (!this.kg(file)) {
                return;
            }
        }
        else {
            if (!file.isFile()) {
                throw new IOException("Not ordinary file: " + file.getName());
            }
            this.jt("C0644 " + file.length() + " " + file.getName() + "\n");
            if (this.k7 != null) {
                this.k7.fa(file.getName(), (int)file.length());
            }
            this.kc("After sending filedata");
            this.j7(new FileInputStream(file), (int)file.length());
            this.j8(0);
            if (this.k7 != null) {
                this.k7.e8();
            }
        }
        this.kc("After writing file");
    }
    
    public final void ke(final File file) throws IOException {
        final String[] array = new String[3];
        this.j8(0);
        while (true) {
            String jw;
            try {
                jw = this.jw();
            }
            catch (EOFException ex) {
                return;
            }
            if (jw != null) {
                final char char1 = jw.charAt(0);
                switch (char1) {
                    case 69: {
                        this.j8(0);
                    }
                    case 84: {
                        System.out.println("(T)ime not supported: " + jw);
                        continue;
                    }
                    case 67:
                    case 68: {
                        String s = file.getAbsolutePath();
                        this.kd(jw, array);
                        if (file.isDirectory()) {
                            s = String.valueOf(s) + File.separator + array[2];
                        }
                        final File file2 = new File(s);
                        if (char1 != 'D') {
                            final FileOutputStream fileOutputStream = new FileOutputStream(file2);
                            this.j8(0);
                            final int int1 = Integer.parseInt(array[1]);
                            if (this.k7 != null) {
                                this.k7.fa(file2.getName(), int1);
                            }
                            this.j9(fileOutputStream, int1);
                            this.kc("After reading file");
                            if (this.k7 != null) {
                                this.k7.e8();
                            }
                            this.j8(0);
                            continue;
                        }
                        if (file2.exists()) {
                            if (!file2.isDirectory()) {
                                this.kb("Invalid target " + file2.getName() + ", must be a directory");
                            }
                        }
                        else if (!file2.mkdir()) {
                            this.kb("Could not create directory: " + file2.getName());
                        }
                        if (this.k7 != null) {
                            this.k7.e9(file2.getAbsolutePath());
                        }
                        this.ke(file2);
                        if (this.k7 != null) {
                            this.k7.e7();
                            continue;
                        }
                        continue;
                    }
                    default: {
                        this.kb("Unexpected cmd: " + jw);
                        throw new IOException("Unexpected cmd: " + jw);
                    }
                }
            }
        }
    }
    
    public final void kd(final String s, final String[] array) throws IOException {
        final int index = s.indexOf(32);
        final int index2 = s.indexOf(32, index + 1);
        if (index == -1 || index2 == -1) {
            this.kb("Syntax error in cmd");
            throw new IOException("Syntax error in cmd");
        }
        array[0] = s.substring(1, index);
        array[1] = s.substring(index + 1, index2);
        array[2] = s.substring(index2 + 1);
    }
    
    public final void kc(final String s) throws IOException {
        final int ka = this.ka();
        if (ka == 0) {
            return;
        }
        final String jw = this.jw();
        if (ka == 2) {
            throw new IOException(jw);
        }
        this.h9(jw);
    }
    
    public final void kb(final String s) throws IOException {
        this.j8(1);
        this.jt(s);
        this.h9(s);
    }
    
    public final int ka() throws IOException {
        return this.k2.read();
    }
    
    public final String jw() throws IOException {
        final byte[] array = new byte[2048];
        int n = 0;
        int ka;
        while ((ka = this.ka()) != 10 && ka >= 0) {
            array[n++] = (byte)ka;
        }
        if (ka == -1) {
            throw new EOFException();
        }
        if (array[0] == 10) {
            throw new IOException("Unexpected <NL>");
        }
        if (array[0] != 2 && array[0] != 1) {
            return new String(array, 0, n);
        }
        final String s = new String(array, 1, n - 1);
        if (array[0] == 2) {
            throw new IOException(s);
        }
        this.h9(s);
        return null;
    }
    
    public final void j9(final FileOutputStream fileOutputStream, final int n) throws IOException {
        final byte[] array = new byte[2048];
        int i = 0;
        while (i < n) {
            final int read = this.k2.read(array, 0, (n - i < 2048) ? (n - i) : 2048);
            if (read == -1) {
                this.h9("Premature EOF");
                throw new IOException("Premature EOF");
            }
            i += read;
            fileOutputStream.write(array, 0, read);
            if (this.k7 == null) {
                continue;
            }
            this.k7.e6(read);
        }
        fileOutputStream.close();
    }
    
    public final void j8(final int n) throws IOException {
        this.e9.kn(new byte[] { (byte)n });
    }
    
    public final void jt(final String s) throws IOException {
        this.e9.kn(s.getBytes());
    }
    
    public final void j7(final FileInputStream fileInputStream, final int n) throws IOException {
        final byte[] array = new byte[2048];
        int i = 0;
        while (i < n) {
            final int read = fileInputStream.read(array, 0, (n - i < 2048) ? (n - i) : 2048);
            if (read == -1) {
                throw new IOException("Premature EOF");
            }
            i += read;
            this.e9.kn(array, 0, read);
            if (this.k7 != null) {
                this.k7.e6(read);
            }
            Thread.yield();
        }
        fileInputStream.close();
    }
    
    public final void eu(final byte[] array) {
        try {
            this.k1.write(array);
        }
        catch (IOException ex) {
            try {
                this.k1.close();
            }
            catch (IOException ex2) {}
            this.h9("Error writing data to stdout-pipe");
        }
    }
    
    public final void et(final byte[] array) {
        if (this.k4) {
            this.h9("Remote warning/error: " + new String(array));
        }
    }
    
    public final h ev() {
        return null;
    }
    
    public final void es(final String s) {
    }
    
    public final void er(final String s) {
    }
    
    public final void eq(final b7 b7, final ci ci) {
    }
    
    public final void ep(final String s) {
    }
    
    public final boolean f3() {
        return false;
    }
    
    public final void i9(final b5 b5) {
        if (this.k7 != null) {
            this.k7.fb(this.k3);
        }
    }
    
    public final void i8(final b5 b5, final boolean b6) {
        try {
            this.k1.close();
        }
        catch (IOException ex) {}
    }
    
    public final void h9(final String s) {
        if (this.c != null) {
            this.c.h9(s);
        }
    }
    
    public final Socket f9() throws IOException {
        if (this.k8 != null) {
            return this.k8.f9();
        }
        return null;
    }
    
    public final bo f2() {
        return this.k6;
    }
}
