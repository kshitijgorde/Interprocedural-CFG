// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.model.impl.upload;

import java.nio.channels.FileLock;
import java.nio.channels.FileChannel;
import java.io.RandomAccessFile;
import java.io.FilterInputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.security.MessageDigest;
import jmaster.jumploader.model.api.C.C;
import java.awt.image.BufferedImage;
import jmaster.jumploader.view.api.image.IImageView;
import jmaster.jumploader.view.api.main.IMainView;
import jmaster.jumploader.view.api.IGenericView;
import jmaster.util.system.SystemHelper;
import java.io.InputStream;
import jmaster.jumploader.model.api.file.IFile;
import jmaster.jumploader.model.api.common.ITransferProgress;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Pattern;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.File;
import jmaster.jumploader.model.api.upload.IUploadFile;
import java.util.StringTokenizer;
import jmaster.jumploader.app.JumpLoaderMain;
import jmaster.util.log.A;

public class F implements Runnable
{
    static final String K = "nocrc";
    static final String Q = "removeDocumentWhileEditing";
    static final String H = "readOnly";
    static final String R = "autoUpload";
    static final String I = "editImage";
    protected A J;
    protected JumpLoaderMain F;
    private E M;
    private Thread B;
    private String S;
    private B D;
    private Exception N;
    private boolean E;
    protected String C;
    protected boolean P;
    protected boolean L;
    protected boolean O;
    protected boolean G;
    protected boolean A;
    
    public F(final JumpLoaderMain f, final String s) {
        this.J = jmaster.util.log.B.getInstance().getLog(this.getClass());
        this.F = f;
        this.M = (E)f.getModel().D();
        this.S = s;
    }
    
    public String toString() {
        return "documentProcessingThread " + this.S + ", current file: " + this.D;
    }
    
    public boolean C() {
        return this.E;
    }
    
    public void A(final boolean e) {
        this.E = e;
    }
    
    public String I() {
        return this.C;
    }
    
    public void C(final String c) {
        this.C = c;
        if (c != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(c, ";");
            while (stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                if (!jmaster.util.B.A.C(nextToken)) {
                    final StringTokenizer stringTokenizer2 = new StringTokenizer(nextToken, "=");
                    Object nextToken2 = null;
                    if (stringTokenizer2.hasMoreTokens()) {
                        nextToken2 = stringTokenizer2.nextToken();
                    }
                    if (stringTokenizer2.hasMoreTokens()) {
                        stringTokenizer2.nextToken();
                    }
                    if ("nocrc".equals(nextToken2)) {
                        this.P = true;
                    }
                    if ("removeDocumentWhileEditing".equals(nextToken2)) {
                        this.L = true;
                    }
                    if ("readOnly".equals(nextToken2)) {
                        this.O = true;
                    }
                    if ("editImage".equals(nextToken2)) {
                        this.G = true;
                    }
                    if (!"autoUpload".equals(nextToken2)) {
                        continue;
                    }
                    this.A = true;
                }
            }
        }
    }
    
    public void run() {
        try {
            if (!this.E) {
                this.E();
            }
            if (this.D()) {
                this.H();
            }
        }
        catch (Exception ex) {
            this.J.E("Error in " + this, ex);
            this.D.A(ex);
            this.M.updateFileStatus(this.D, 3);
        }
    }
    
    public void A(final B d) {
        if (this.J.B()) {
            this.J.D("startDocumentProcessing, downloadLocation=" + d.D() + ", " + "uploadLocation=" + d.F() + ", " + "name=" + d.getName() + ", " + "key=" + d.getKey() + ", " + "options=" + this.C);
            this.J.D("Processing options=" + this.C);
        }
        this.D = d;
        final jmaster.util.B.B b = new jmaster.util.B.B();
        b.A(this);
        b.A(this.S);
        b.A(true);
        (this.B = this.F.getModel().C().A(b)).start();
    }
    
    public boolean B() {
        return this.B == null;
    }
    
    public synchronized void F() {
        if (this.J.B()) {
            this.J.D("stopThread() of " + this);
        }
        final Thread b = this.B;
        this.B = null;
        b.interrupt();
    }
    
    private void E() throws Exception {
        final B d = this.D;
        if (this.J.B()) {
            this.J.D("" + this + " downloading " + d);
        }
        final File tempFile = File.createTempFile("___", d.getName());
        if (this.J.B()) {
            this.J.D("Created temporary file: " + tempFile.getAbsolutePath());
        }
        final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(tempFile));
        final String replaceAll = d.D().replaceAll(" ", "%20").replaceAll(Pattern.quote("{rnd}"), "" + System.currentTimeMillis());
        final URL url = new URL(replaceAll);
        this.J.D("Downloading from " + replaceAll);
        final HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
        try {
            final int responseCode = httpURLConnection.getResponseCode();
            if (responseCode != 200) {
                throw new Exception("Invalid server response: " + responseCode);
            }
            long n = 0L;
            d.setLength(httpURLConnection.getContentLength());
            final InputStream inputStream = httpURLConnection.getInputStream();
            final jmaster.jumploader.model.impl.C.A a = new jmaster.jumploader.model.impl.C.A();
            a.B(50);
            a.E(System.currentTimeMillis());
            a.D(d.getLength());
            a.C(System.currentTimeMillis());
            a.B(0L);
            d.A(a);
            final byte[] array = new byte[8192];
            int read;
            while ((read = inputStream.read(array)) != -1) {
                bufferedOutputStream.write(array, 0, read);
                n += read;
                a.A(n);
            }
            final String name = d.getName();
            d.A(tempFile, true);
            d.A(name);
            this.F.getModel().A().A(d);
        }
        finally {
            try {
                httpURLConnection.disconnect();
            }
            catch (Exception ex) {}
            try {
                bufferedOutputStream.close();
            }
            catch (Exception ex2) {}
            d.A((ITransferProgress)null);
        }
        if (this.J.B()) {
            this.J.D("" + this + " finished download of " + d);
            if (this.N != null) {
                this.J.D("" + this.N);
            }
        }
    }
    
    private boolean D() throws Exception {
        if (this.L || this.O) {
            this.M.removeFile(this.D);
        }
        final B d = this.D;
        this.M.updateFileStatus(d, 6);
        if (this.J.B()) {
            this.J.D("About to open file for edit: " + d.getPath());
        }
        final String s = this.P ? null : this.A(d.getPath());
        final File file = new File(d.getPath());
        long n = file.lastModified();
        long n2 = file.length();
        if (this.O) {
            final File file2 = new File(d.getPath());
            if (!file2.setReadOnly()) {
                this.J.C("Failed to set read only flag for file " + file2);
            }
        }
        if (this.G) {
            this.G();
        }
        else {
            final Process openFile = SystemHelper.getInstance().openFile(d.getPath());
            openFile.waitFor();
            if (this.J.B()) {
                this.J.D("Open file process finished with code " + openFile.exitValue());
            }
            if (this.O) {
                return false;
            }
            if (this.B(d.getPath())) {
                d.setDocumentFileWasLocked(true);
                if (this.J.B()) {
                    this.J.D("Waiting for file to be released by editor " + d.getPath());
                }
                do {
                    Thread.sleep(1111L);
                } while (this.A() && this.B(d.getPath()));
                if (this.J.B()) {
                    this.J.D("File released by editor " + d.getPath());
                }
                if (this.L) {
                    this.M.B(this.D);
                }
                if (this.M.indexOfFile(d) == -1) {
                    if (this.J.B()) {
                        this.J.D("File " + d.getPath() + " removed, halting document processing.");
                    }
                    return false;
                }
                if (!this.P) {
                    if (this.J.B()) {
                        this.J.D("Checking whether crc modified for " + d.getPath());
                    }
                    if (s.equals(this.A(d.getPath()))) {
                        throw new Exception("Not modified");
                    }
                }
                else {
                    final File file3 = new File(d.getPath());
                    if (file3.lastModified() == n && file3.length() == n2) {
                        throw new Exception("Not modified");
                    }
                }
            }
            else {
                if (this.J.B()) {
                    this.J.D("Failed to invoke editor or editor does not lock the file");
                }
                while (this.A()) {
                    Thread.sleep(1111L);
                    final File file4 = new File(d.getPath());
                    if (file4.lastModified() != n || file4.length() != n2) {
                        n = file4.lastModified();
                        n2 = file4.length();
                        final String name = d.getName();
                        d.A(d.getFile(), true);
                        d.A(name);
                        if (this.L) {
                            this.M.B(this.D);
                        }
                        this.H();
                        while (this.A() && !d.isFinished() && !d.isFailed()) {
                            Thread.sleep(1111L);
                        }
                        if (!this.L) {
                            continue;
                        }
                        this.M.removeFile(this.D);
                    }
                }
                if (this.M.indexOfFile(d) == -1) {
                    return false;
                }
                if (this.J.B()) {
                    this.J.D("File modified " + d.getPath());
                }
            }
        }
        final String name2 = d.getName();
        d.A(d.getFile(), true);
        d.A(name2);
        return true;
    }
    
    private boolean G() throws Exception {
        final jmaster.jumploader.model.api.B model = this.F.getModel();
        final IMainView view = this.F.getView();
        final File file = this.D.getFile();
        model.D().applyTransformations(this.D);
        final IImageView imageView = view.getImageView();
        view.setCurrentView(imageView);
        final BufferedImage d = model.L().D(model.L().C(this.D.getFile()));
        final C b = model.A().B(this.D);
        BufferedImage d2 = null;
        if (b != null && b.A() != null) {
            d2 = model.L().D(b.A());
        }
        imageView.setImage(this.D, d, d2);
        imageView.setZoomToFit();
        while (imageView.equals(view.getCurrentView())) {
            Thread.sleep(555L);
        }
        return !file.equals(this.D.getFile());
    }
    
    protected boolean A() {
        return this.L || (!this.L && this.M.indexOfFile(this.D) != -1);
    }
    
    private String A(final String s) throws Exception {
        if (this.J.B()) {
            this.J.D("Calculating crc for " + s);
        }
        final MessageDigest instance = MessageDigest.getInstance("MD5");
        final byte[] array = new byte[16384];
        InputStream inputStream = null;
        FilterInputStream filterInputStream = null;
        try {
            filterInputStream = new BufferedInputStream(inputStream = new FileInputStream(s));
            int read;
            while ((read = filterInputStream.read(array)) != -1) {
                instance.update(array, 0, read);
            }
        }
        finally {
            try {
                ((BufferedInputStream)filterInputStream).close();
            }
            catch (Exception ex) {}
            try {
                inputStream.close();
            }
            catch (Exception ex2) {}
        }
        final byte[] digest = instance.digest();
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < digest.length; ++i) {
            final String hexString = Integer.toHexString(digest[i] & 0xFF);
            if (hexString.length() < 2) {
                sb.append('0');
            }
            sb.append(hexString);
        }
        final String string = sb.toString();
        if (this.J.B()) {
            this.J.D("Crc for " + s + " is " + string);
        }
        return string;
    }
    
    private boolean B(final String s) {
        boolean b = false;
        RandomAccessFile randomAccessFile = null;
        FileChannel channel = null;
        FileLock tryLock = null;
        try {
            randomAccessFile = new RandomAccessFile(s, "rw");
            channel = randomAccessFile.getChannel();
            tryLock = channel.tryLock();
            b = (tryLock == null);
        }
        catch (Exception ex) {
            b = true;
        }
        finally {
            if (tryLock != null) {
                try {
                    tryLock.release();
                }
                catch (Exception ex2) {}
            }
            if (channel != null) {
                try {
                    channel.close();
                }
                catch (Exception ex3) {}
            }
            if (randomAccessFile != null) {
                try {
                    randomAccessFile.close();
                }
                catch (Exception ex4) {}
            }
        }
        return b;
    }
    
    private void H() throws jmaster.jumploader.model.api.A.C {
        final B d = this.D;
        d.setUploadedPartitionCount(0);
        this.M.updateFileStatus(d, 0);
        if (this.A && this.M.canStartUpload()) {
            this.M.startUpload();
        }
    }
}
