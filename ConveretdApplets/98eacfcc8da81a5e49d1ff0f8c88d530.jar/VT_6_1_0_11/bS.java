// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.util.zip.ZipEntry;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.util.zip.ZipInputStream;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.Vector;
import com.hw.client.util.f;
import com.hw.client.util.e;
import java.io.IOException;
import com.hw.client.util.a;
import java.io.File;

public final class bS
{
    private static final aU a;
    private final aK b;
    private File c;
    private File d;
    private boolean e;
    
    public bS(final aK b) {
        this.e = false;
        this.b = b;
        this.e();
    }
    
    private boolean c() {
        if (this.e) {
            return false;
        }
        if (this.c == null) {
            com.hw.client.util.a.c("DoorArchive.installNeeded(), archive file does not exist");
            return true;
        }
        try {
            if (!this.b.e().exists()) {
                com.hw.client.util.a.c("DoorArchive.installNeeded(), agent does not exist");
                return true;
            }
        }
        catch (IOException ex) {
            com.hw.client.util.a.a("error getting agent file object", ex);
            return true;
        }
        return false;
    }
    
    public final boolean a() {
        boolean b = true;
        final String a;
        if ((a = this.b.a.a("DOWNLOAD")) == null) {
            b = false;
            com.hw.client.util.a.d("unable to get DOWNLOAD parameter");
        }
        else if (a.equals("always")) {
            com.hw.client.util.a.c("attempting to remove archive and agent");
            if (!this.c.delete()) {
                com.hw.client.util.a.d("unable to remove archive : " + this.c.getPath());
                b = false;
            }
            try {
                final File e;
                if (!(e = this.b.e()).delete()) {
                    com.hw.client.util.a.d("unable to remove agent : " + e.getPath());
                    b = false;
                }
            }
            catch (IOException ex) {
                com.hw.client.util.a.d("unable to get agent file ");
            }
        }
        else {
            b = false;
        }
        return b;
    }
    
    public final boolean b() {
        if (!this.e) {
            this.e = this.d();
        }
        if (this.e) {
            bS.a.a(new v(bS.a, v.g, "door archive installed", 100), true);
        }
        return this.e;
    }
    
    private boolean d() {
        if (this.c()) {
            if (this.c == null) {
                throw new IllegalStateException("invalid archive file");
            }
            com.hw.client.util.a.c("checking if archive is already downloaded");
            if (!this.c.exists() || !this.d.exists()) {
                com.hw.client.util.a.c("attempting to download archive");
                bS.a.a(new v(bS.a, v.a, "download started", 0), true);
                if (!this.f()) {
                    com.hw.client.util.a.d("unable to download archive");
                    cx.a(142);
                    return false;
                }
                com.hw.client.util.a.c("successfully downloaded archive");
                com.hw.client.util.a.c("attempting to download archive signature");
                if (!this.g()) {
                    com.hw.client.util.a.d("unable to download archive signature");
                    cx.a(144);
                    return false;
                }
                com.hw.client.util.a.c("successfully downloaded archive signature");
                bS.a.a(new v(bS.a, v.c, "download ended", 100), true);
            }
            else {
                com.hw.client.util.a.c("archive is already downloaded");
            }
            com.hw.client.util.a.c("checking if archive is valid");
            if (!ce.a(this.c, this.d)) {
                com.hw.client.util.a.d("unable to validate archive");
                cx.a(160);
                return false;
            }
            com.hw.client.util.a.c("successfully checked archive validity");
            com.hw.client.util.a.c("attempting to extact archive");
            if (!this.h()) {
                com.hw.client.util.a.d("unable to extract archive");
                cx.a(150);
                return false;
            }
            com.hw.client.util.a.c("successfully extracted the archive");
        }
        try {
            if (bj.c()) {
                final Vector h = this.b.h();
                for (int i = 0; i < h.size(); ++i) {
                    bj.a(h.elementAt(i));
                }
            }
            else {
                bj.a(this.b.e());
            }
        }
        catch (IOException ex) {
            com.hw.client.util.a.a("DoorArchive.install: error in setFileExecutable", ex);
            cx.a(134);
            return false;
        }
        return true;
    }
    
    private boolean e() {
        if (this.c != null && this.d != null) {
            return true;
        }
        try {
            this.c = this.b.j();
        }
        catch (IOException ex) {
            com.hw.client.util.a.a("unable to get archive file object", ex);
            cx.a(132, ex);
            return false;
        }
        try {
            final aK b = this.b;
            this.d = new File(b.i().getAbsolutePath() + File.separator + b.k());
        }
        catch (IOException ex2) {
            com.hw.client.util.a.a("unable to get archive signature file object", ex2);
            cx.a(133, ex2);
            return false;
        }
        return true;
    }
    
    private boolean f() {
        URL c;
        try {
            c = this.b.c();
            com.hw.client.util.a.b("got archive url object, archive_url => " + c);
        }
        catch (MalformedURLException ex) {
            com.hw.client.util.a.d("unable to get archive url");
            return false;
        }
        return new aZ(c, this.c, new do(new v(bS.a, v.b, "download progress", 0), 1, 90)).a();
    }
    
    private boolean g() {
        URL d;
        try {
            d = this.b.d();
            com.hw.client.util.a.b("got archive signature url object, signature_url => " + d);
        }
        catch (MalformedURLException ex) {
            com.hw.client.util.a.d("unable to get archive signature url");
            return false;
        }
        return new aZ(d, this.d, new do(new v(bS.a, v.b, "download progress", 0), 91, 99)).a();
    }
    
    private boolean h() {
        if (!this.c.exists()) {
            com.hw.client.util.a.d("archive file does not exist");
            return false;
        }
        com.hw.client.util.a.b("start archive extraction");
        bS.a.a(new v(bS.a, v.d, "extraction started", 0), true);
        File b;
        File l;
        try {
            b = this.b.b();
            l = this.b.l();
        }
        catch (IOException ex) {
            com.hw.client.util.a.a("unable to get the destination directory", ex);
            cx.a(151);
            return false;
        }
        a(5);
        ZipInputStream zipInputStream;
        try {
            com.hw.client.util.a.b("creating zip input stream, m_archive_file => " + this.c);
            zipInputStream = new ZipInputStream(new BufferedInputStream(new FileInputStream(this.c)));
        }
        catch (FileNotFoundException ex2) {
            com.hw.client.util.a.a("unable to find archive file", ex2);
            cx.a(152);
            return false;
        }
        a(10);
        ZipEntry nextEntry = null;
        final byte[] array = new byte[2048];
        com.hw.client.util.a.b("starting to extract files from archive, archive => " + this.c);
        int n = 10;
        while (true) {
            n += 5;
            a(n);
            try {
                if (nextEntry != null) {
                    zipInputStream.closeEntry();
                }
                if ((nextEntry = zipInputStream.getNextEntry()) == null) {
                    com.hw.client.util.a.b("finished extracting files from archive, archive => " + this.c);
                    break;
                }
            }
            catch (IOException ex3) {
                com.hw.client.util.a.a("exception getting next entry from archive file", ex3);
                cx.a(153);
                return false;
            }
            final File file;
            if (!(file = new File(l, nextEntry.getName())).exists()) {
                if (nextEntry.isDirectory()) {
                    com.hw.client.util.a.c("extracting directory, lf => " + file);
                    file.mkdirs();
                }
                else {
                    if (file.getParentFile().exists() || file.getParentFile().mkdirs()) {
                        final int n2 = (int)nextEntry.getSize();
                        int n3 = 0;
                        try {
                            com.hw.client.util.a.b("attempting to write entry to file, lf => " + file);
                            final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
                            int read;
                            while ((read = zipInputStream.read(array, 0, 2048)) != -1) {
                                bufferedOutputStream.write(array, 0, read);
                                n3 += read;
                            }
                            com.hw.client.util.a.b("successfully wrote entry to file, entry_size => " + n2 + ", total_bytes_read => " + n3);
                            bufferedOutputStream.flush();
                            bufferedOutputStream.close();
                            continue;
                        }
                        catch (Exception ex4) {
                            com.hw.client.util.a.a("exception while extracting file from archive", ex4);
                            cx.a(154);
                            return false;
                        }
                        break;
                    }
                    com.hw.client.util.a.d("unable to make parent directory, lf => " + file.getParent());
                }
            }
        }
        a(80);
        try {
            com.hw.client.util.a.b("closing the zip input stream, m_archive_file => " + this.c);
            zipInputStream.close();
        }
        catch (IOException ex5) {
            com.hw.client.util.a.a("unable to close zip input stream", ex5);
            cx.a(155);
            return false;
        }
        a(90);
        com.hw.client.util.a.b("attempting to rename temp directory, temp_dir => " + l.toString() + ", destination_dir => " + b.toString());
        try {
            final File file2 = new File(l, "data");
            final File file3;
            if ((file3 = new File(b, "data")).exists()) {
                this.a(file3);
            }
            if (!file2.renameTo(file3)) {
                throw new Exception("unable to rename data dir");
            }
            final File file4 = new File(l, "pids");
            final File file5;
            if ((file5 = new File(b, "pids")).exists()) {
                this.a(file5);
            }
            if (!file4.renameTo(file5)) {
                com.hw.client.util.a.d("unable to rename pids dir");
            }
            final File file6 = new File(l, "scripts");
            final File file7;
            if ((file7 = new File(b, "scripts")).exists()) {
                this.a(file7);
            }
            if (!file6.renameTo(file7)) {
                com.hw.client.util.a.d("unable to rename scripts dir");
            }
            final File file8 = new File(l, "BUILD.txt");
            final File file9;
            if ((file9 = new File(b, "BUILD.txt")).exists()) {
                this.a(file9);
            }
            if (!file8.renameTo(file9)) {
                com.hw.client.util.a.d("unable to rename build file");
            }
            if (!new File(l, "logs").delete()) {
                com.hw.client.util.a.d("unable to delete logs dir");
            }
            if (!l.delete()) {
                com.hw.client.util.a.d("unable to delete logs dir");
            }
        }
        catch (Exception ex6) {
            com.hw.client.util.a.a("exception renaming temp directory", ex6);
            cx.a(156);
            return false;
        }
        com.hw.client.util.a.b("successfully renamed temp directory");
        com.hw.client.util.a.b("end archive extraction");
        bS.a.a(new v(bS.a, v.f, "extraction ended", 100), true);
        return true;
    }
    
    private static void a(final int n) {
        bS.a.a(new v(bS.a, v.e, "extraction progress", n), true);
    }
    
    private boolean a(final File file) {
        boolean delete = true;
        try {
            if (file.isFile()) {
                com.hw.client.util.a.b("deleteFile(), deleting file, source => " + file);
                delete = file.delete();
            }
            else {
                final File[] listFiles = file.listFiles();
                for (int i = 0; i < listFiles.length; ++i) {
                    if (!this.a(listFiles[i])) {
                        delete = false;
                    }
                }
                com.hw.client.util.a.b("deleteFile(), deleting directory, source => " + file);
                if (!file.delete()) {
                    delete = false;
                }
            }
        }
        catch (Exception ex) {
            com.hw.client.util.a.a("unable to recursivley delete directory, source => " + file, ex);
            return false;
        }
        return delete;
    }
    
    static {
        a = new aU();
    }
}
