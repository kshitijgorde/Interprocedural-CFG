// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.model.impl.upload;

import java.io.IOException;
import java.awt.Image;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.awt.image.BufferedImage;
import jmaster.jumploader.model.impl.image.WatermarkConfig;
import java.awt.image.ImageObserver;
import java.util.zip.ZipOutputStream;
import java.io.FileOutputStream;
import java.util.StringTokenizer;
import java.awt.Dimension;
import java.util.Arrays;
import jmaster.jumploader.app.JumpLoaderVersion;
import java.net.URLEncoder;
import java.util.HashMap;
import jmaster.jumploader.model.api.common.IAttribute;
import java.util.Map;
import java.io.File;
import jmaster.jumploader.model.api.common.IAttributeSet;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.StringReader;
import java.net.URL;
import jmaster.jumploader.model.impl.C.C;
import jmaster.jumploader.model.api.upload.IUploadFile;
import jmaster.util.http.B;
import jmaster.jumploader.model.api.config.UploaderConfig;
import jmaster.jumploader.model.api.upload.D;

public class A implements D, Runnable, jmaster.util.http.A
{
    public static final String J = "fileId:";
    public static final String C = "partitionIndex:";
    protected jmaster.util.log.A I;
    protected jmaster.util.C.A L;
    private E P;
    private UploaderConfig K;
    private B B;
    private Thread D;
    private String R;
    private jmaster.jumploader.model.impl.upload.B F;
    private jmaster.jumploader.model.impl.C.A Q;
    private Exception A;
    private int O;
    private int G;
    private long N;
    private long M;
    private int H;
    private jmaster.jumploader.model.api.B E;
    static /* synthetic */ Class class$jmaster$jumploader$model$api$upload$A;
    
    public A(final jmaster.jumploader.model.api.B e, final E p4, final UploaderConfig k, final String r) {
        this.I = jmaster.util.log.B.getInstance().getLog(this.getClass());
        this.L = new jmaster.util.C.A((jmaster.jumploader.model.impl.upload.A.class$jmaster$jumploader$model$api$upload$A == null) ? (jmaster.jumploader.model.impl.upload.A.class$jmaster$jumploader$model$api$upload$A = class$("jmaster.jumploader.model.api.upload.A")) : jmaster.jumploader.model.impl.upload.A.class$jmaster$jumploader$model$api$upload$A);
        this.O = 0;
        this.G = 0;
        this.H = 0;
        this.E = e;
        this.P = p4;
        this.K = k;
        this.R = r;
    }
    
    public String toString() {
        return "uploadThread " + this.R + ", current file: " + this.F;
    }
    
    public void A(final jmaster.jumploader.model.api.upload.A a) {
        this.L.C(a);
    }
    
    public void B(final jmaster.jumploader.model.api.upload.A a) {
        this.L.A(a);
    }
    
    private void D() {
        for (int i = 0; i < this.L.C(); ++i) {
            ((jmaster.jumploader.model.api.upload.A)this.L.A(i)).A(this);
        }
    }
    
    private void A(final Exception ex) {
        for (int i = 0; i < this.L.C(); ++i) {
            ((jmaster.jumploader.model.api.upload.A)this.L.A(i)).A(this, ex);
        }
    }
    
    private void B(final IUploadFile uploadFile) {
        for (int i = 0; i < this.L.C(); ++i) {
            ((jmaster.jumploader.model.api.upload.A)this.L.A(i)).B(this, uploadFile);
        }
    }
    
    private void A(final IUploadFile uploadFile) {
        for (int i = 0; i < this.L.C(); ++i) {
            ((jmaster.jumploader.model.api.upload.A)this.L.A(i)).A(this, uploadFile);
        }
    }
    
    public IUploadFile A() {
        return this.F;
    }
    
    public void run() {
        Exception ex = null;
        try {
            this.D();
            while (!this.E() && (this.F = this.P.E()) != null) {
                this.B(this.F);
                try {
                    this.A(this.F);
                    if (this.A != null) {
                        throw this.A;
                    }
                    continue;
                }
                catch (Throwable t) {
                    this.I.E("uploadFile( " + this.F + " ) failed for " + this.R, t);
                    if (t instanceof OutOfMemoryError) {
                        this.A = new Exception("Out of memory");
                    }
                    else {
                        this.A = (Exception)((t instanceof Exception) ? t : new Exception(t));
                    }
                }
                finally {
                    this.F.G(false);
                    this.P.A(this.F, this.A);
                    this.A((IUploadFile)this.F);
                    this.F = null;
                }
            }
        }
        catch (Exception ex2) {
            ex = ex2;
            this.I.E("Unexpected error in upload thread " + this.R, ex2);
        }
        finally {
            this.D = null;
            this.F = null;
            this.A(ex);
        }
    }
    
    public void A(final B b, final long n, final long n2) {
        this.Q.A(this.M * n / n2 + this.G * this.K.getPartitionLength());
        this.F.G(this.Q.getCompletion() == 1.0);
    }
    
    public void A(final B b, final Exception a) {
        this.A = a;
    }
    
    public void A(final B b) {
    }
    
    public void A(final B b, final long n) {
    }
    
    public boolean E() {
        return this.D == null || this.D.isInterrupted();
    }
    
    public synchronized void B() {
        if (this.I.B()) {
            this.I.D("startThread() of " + this);
        }
        final jmaster.util.B.B b = new jmaster.util.B.B();
        b.A(this);
        b.A(this.R);
        b.A(true);
        (this.D = this.E.C().A(b)).start();
    }
    
    public synchronized void C() {
        if (this.I.B()) {
            this.I.D("stopThread() of " + this);
        }
        this.H();
        final Thread d = this.D;
        this.D = null;
        d.interrupt();
    }
    
    public void H() {
        if (this.I.B()) {
            this.I.D("stopUploadCurrentFile() of " + this);
        }
        this.B.setCancel(true);
    }
    
    public int I() {
        return this.O;
    }
    
    private void A(final jmaster.jumploader.model.impl.upload.B b) throws Throwable {
        if (this.I.B()) {
            this.I.D("" + this + " uploading " + b);
        }
        this.P.applyTransformations(b);
        final long currentTimeMillis = System.currentTimeMillis();
        this.A = null;
        final String a = this.A(b, b.getName());
        final File file = b.getFile();
        final long length = file.length();
        b.setLength(length);
        (this.Q = (jmaster.jumploader.model.impl.C.A)b.getTransferProgress()).D(length);
        this.Q.A(0L);
        final long partitionLength = this.K.getPartitionLength();
        final boolean b2 = partitionLength > 0L;
        final boolean b3 = b2 && length % partitionLength == 0L;
        if (b2) {
            this.H = (int)(length / partitionLength + (b3 ? 0 : 1));
            if (this.H == 0) {
                this.H = 1;
            }
        }
        else {
            this.H = 1;
        }
        if (this.K.getResumeCheckUrl() != null) {
            try {
                final Map g = this.G();
                final Map a2 = this.A(b, length, a);
                this.B = this.F();
                final StringBuffer responseContent = new StringBuffer();
                this.B.setResponseContent(responseContent);
                final C responseHeaderFields = new C();
                this.B.setResponseHeaderFields(responseHeaderFields);
                this.B.setUrl(new URL(this.K.getResumeCheckUrl()));
                this.B.setRequestProperties(g);
                a2.remove("fileId");
                this.B.setRequestParameters(a2);
                this.B.setListener(this);
                while (jmaster.util.C.B.A(this.B, "upload").isAlive()) {
                    Thread.sleep(100L);
                    if (this.E() || this.B.isCancel()) {
                        throw new jmaster.jumploader.model.api.A.A();
                    }
                }
                if (this.B.getUploadError() != null) {
                    throw this.B.getUploadError();
                }
                if (this.B.isUploadOk()) {
                    if (this.I.B()) {
                        this.I.D("Response headers for resume check for " + this.F + ":\r\n");
                        for (int i = 0; i < responseHeaderFields.getAttributeCount(); ++i) {
                            final IAttribute attribute = responseHeaderFields.getAttributeAt(i);
                            this.I.D(attribute.getName() + "=" + attribute.getValue());
                        }
                        this.I.D("Response content for resume check for " + this.F + ":\r\n");
                        this.I.D(responseContent.toString());
                    }
                    String trim = null;
                    final BufferedReader bufferedReader = new BufferedReader(new StringReader(responseContent.toString()));
                    Integer n = null;
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        if (line.startsWith("fileId:")) {
                            trim = line.substring("fileId:".length()).trim();
                        }
                        if (line.startsWith("partitionIndex:")) {
                            n = new Integer(Integer.parseInt(line.substring("partitionIndex:".length()).trim()));
                        }
                    }
                    if (trim != null) {
                        b.setId(trim);
                    }
                    if (n != null) {
                        b.setUploadedPartitionCount(n);
                    }
                }
            }
            catch (Exception ex) {
                if (ex instanceof jmaster.jumploader.model.api.A.A) {
                    throw ex;
                }
                this.I.E(ex, ex);
            }
        }
        this.G = b.getUploadedPartitionCount();
        while (this.A == null && this.G < this.H) {
            final boolean b4 = this.G == this.H - 1;
            final jmaster.util.http.D d = new jmaster.util.http.D();
            d.C(file.getAbsolutePath());
            d.D(a);
            d.B(this.K.getFileParameterName());
            if (this.K.getPartitionLength() < 1L) {
                this.N = 0L;
                this.M = b.getLength();
            }
            else {
                this.N = this.G * partitionLength;
                this.M = Math.min(this.K.getPartitionLength(), b.getLength() - this.N);
            }
            d.A(this.N);
            d.B(this.M);
            d.A(b.getMimeType());
            final Map g2 = this.G();
            final Map a3 = this.A(b, length, a);
            a3.put("partitionIndex", "" + this.G);
            a3.put("partitionCount", "" + this.H);
            if (this.K.isUseMd5() && b4) {
                if (this.I.B()) {
                    this.I.D("Calculating MD5...");
                }
                try {
                    final String a4 = jmaster.util.F.B.A(file.getAbsolutePath());
                    if (this.I.B()) {
                        this.I.D("MD5=" + a4);
                    }
                    a3.put("md5", a4);
                }
                catch (Exception ex2) {
                    this.I.E("Failed to calculate MD5 for " + b, ex2);
                }
            }
            if (this.K.isUsePartitionMd5()) {
                if (this.I.B()) {
                    this.I.D("Calculating partition MD5...");
                }
                try {
                    final String a5 = jmaster.util.F.B.A(file.getAbsolutePath(), d.A(), d.D());
                    if (this.I.B()) {
                        this.I.D("partition MD5=" + a5);
                    }
                    a3.put("partitionMd5", a5);
                }
                catch (Exception ex3) {
                    this.I.E("Failed to calculate MD5 for " + b, ex3);
                }
            }
            jmaster.util.http.D[] files = { d };
            if (b.C() != null && b.C().size() > 0) {
                files = new jmaster.util.http.D[b.C().size()];
                for (int j = 0; j < b.C().size(); ++j) {
                    files[j] = (jmaster.util.http.D)b.C().get(j);
                }
            }
            final StringBuffer responseContent2 = new StringBuffer();
            final C responseHeaderFields2 = new C();
            final Long transferRateMax = (this.K.getMaxTransferRate() > 0L) ? new Long(this.K.getMaxTransferRate()) : null;
            (this.B = this.F()).setRequestEncoding(this.K.getRequestEncoding());
            this.B.setUrl(new URL((b.F() == null) ? this.K.getUploadUrl() : b.F()));
            this.B.setRequestProperties(g2);
            this.B.setRequestParameters(a3);
            this.B.setFiles(files);
            this.B.setResponseContent(responseContent2);
            this.B.setResponseHeaderFields(responseHeaderFields2);
            this.B.setTransferRateMax(transferRateMax);
            this.B.setListener(this);
            final boolean upload = this.B.upload();
            b.E(responseContent2.toString());
            b.A((IAttributeSet)responseHeaderFields2);
            if (!upload && this.B.isCancel()) {
                this.A = new jmaster.jumploader.model.api.A.A();
            }
            if (this.I.B()) {
                this.I.D("Response headers for " + this.F + ":\r\n");
                for (int k = 0; k < responseHeaderFields2.getAttributeCount(); ++k) {
                    final IAttribute attribute2 = responseHeaderFields2.getAttributeAt(k);
                    this.I.D(attribute2.getName() + "=" + attribute2.getValue());
                }
                this.I.D("Response content for " + this.F + ":\r\n");
                this.I.D(responseContent2.toString());
            }
            if (this.A == null) {
                final String string = responseContent2.toString();
                if (string != null && string.startsWith("Error:")) {
                    this.A = new jmaster.jumploader.model.api.A.B(string.substring("Error:".length()));
                }
            }
            if (this.A == null) {
                b.setUploadedPartitionCount(b.getUploadedPartitionCount() + 1);
            }
            ++this.G;
        }
        if (this.Q.getBytesTransferred() > 0L) {
            final long n2 = System.currentTimeMillis() - currentTimeMillis;
            if (n2 > 0L) {
                this.O = (int)(this.Q.getBytesTransferred() * 1000L / n2);
            }
        }
        if (this.A == null && b.isTempFile()) {
            final File file2 = b.getFile();
            if (this.I.B()) {
                this.I.D("Deleting temp file: " + file2.getAbsolutePath());
            }
            try {
                file2.delete();
            }
            catch (Exception ex4) {
                this.I.E("Failed to delete file: " + file2.getAbsolutePath(), ex4);
            }
            this.F.E(false);
            this.F.B(new File(this.F.getOriginalFilePath()));
        }
        if (this.I.B()) {
            this.I.D("" + this + " finished upload of " + b);
            if (this.A != null) {
                this.I.D("" + this.A);
            }
        }
    }
    
    private Map A(final jmaster.jumploader.model.impl.upload.B b, final long n, final String s) throws Exception {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("fileId", b.getId());
        hashMap.put("fileLength", "" + n);
        hashMap.put("fileName", this.K.isUrlEncodeParameters() ? URLEncoder.encode("" + s, "UTF-8") : s);
        if (this.K.isSendFilePath()) {
            final String originalFilePath = b.getOriginalFilePath();
            hashMap.put("filePath", this.K.isUrlEncodeParameters() ? URLEncoder.encode(originalFilePath, "UTF-8") : originalFilePath);
        }
        this.A(hashMap, this.P.getAttributeSet());
        this.A(hashMap, b.getAttributeSet());
        return hashMap;
    }
    
    private Map G() {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        if (!jmaster.util.B.A.C(this.K.getUserAgent())) {
            hashMap.put("User-Agent", this.K.getUserAgent());
        }
        else {
            hashMap.put("User-Agent", JumpLoaderVersion.getApplicationName());
        }
        if (!jmaster.util.B.A.C(this.K.getCookie())) {
            hashMap.put("Cookie", this.K.getCookie());
        }
        return hashMap;
    }
    
    private String A(final jmaster.jumploader.model.impl.upload.B b, final String s) throws Throwable {
        String string = s;
        Arrays.fill(new byte[31457280], (byte)1);
        File file = new File(b.getPath());
        if (this.K.isUploadScaledImages()) {
            final jmaster.jumploader.model.api.B.A l = this.E.L();
            if (l.B(file)) {
                Image image = null;
                final Dimension dimension = new Dimension();
                try {
                    image = l.A(b.getFile(), dimension);
                }
                catch (OutOfMemoryError outOfMemoryError) {
                    this.I.E("Failed to load image: " + b, outOfMemoryError);
                    System.gc();
                    for (int n = 6, i = 2; i <= n; ++i) {
                        this.I.D("Trying to load using subsampling " + i + " from file " + b);
                        try {
                            image = l.A(file, new Integer(i));
                        }
                        catch (OutOfMemoryError outOfMemoryError2) {
                            System.gc();
                            this.I.E("Failed to load image using subsampling " + i + " from file " + b, outOfMemoryError2);
                            if (i == n) {
                                return string;
                            }
                        }
                    }
                }
                final Image image2 = image;
                final StringTokenizer stringTokenizer = new StringTokenizer(this.K.getScaledInstanceNames(), ",");
                final int countTokens = stringTokenizer.countTokens();
                final boolean b2 = !this.K.isUploadScaledImagesNoZip() && (countTokens > 1 || this.K.isUploadOriginalImage());
                final File file2 = b2 ? File.createTempFile("tmp_", ".zip") : null;
                if (b2 && this.I.B()) {
                    this.I.D("Preparing ZIP for " + b + ", path=" + file2.getAbsolutePath());
                }
                OutputStream outputStream = null;
                ZipOutputStream zipOutputStream = null;
                if (file2 != null) {
                    string += ".zip";
                    file2.deleteOnExit();
                    outputStream = new FileOutputStream(file2);
                    zipOutputStream = new ZipOutputStream(outputStream);
                }
                try {
                    final StringTokenizer stringTokenizer2 = new StringTokenizer(this.K.getScaledInstanceDimensions(), ",");
                    final StringTokenizer stringTokenizer3 = jmaster.util.B.A.C(this.K.getScaledInstanceQualityFactors()) ? null : new StringTokenizer(this.K.getScaledInstanceQualityFactors(), ",");
                    for (int j = 0; j < countTokens; ++j) {
                        Image image3 = image2;
                        final String nextToken = stringTokenizer.nextToken();
                        final String nextToken2 = stringTokenizer2.nextToken();
                        final Dimension dimension2 = new Dimension();
                        final StringTokenizer stringTokenizer4 = new StringTokenizer(nextToken2, "x");
                        dimension2.width = Integer.parseInt(stringTokenizer4.nextToken());
                        dimension2.height = Integer.parseInt(stringTokenizer4.nextToken());
                        final String s2 = stringTokenizer4.hasMoreTokens() ? stringTokenizer4.nextToken() : "fit";
                        final String s3 = (stringTokenizer3 != null && stringTokenizer3.hasMoreElements()) ? stringTokenizer3.nextToken() : "800";
                        final int int1 = Integer.parseInt(s3);
                        if (this.I.B()) {
                            this.I.D("Preparing scaled instance for " + b + ", instanceName=" + nextToken + ", dimension=" + nextToken2 + ", modifier=" + s2 + ", qualityFactor=" + s3);
                        }
                        final int width = image3.getWidth(null);
                        final int height = image3.getHeight(null);
                        final boolean b3 = dimension2.width > dimension2.height;
                        final boolean b4 = !(((width == height) ? b3 : (width > height)) ^ b3);
                        final boolean stretchImages = this.K.isStretchImages();
                        boolean b5 = width <= dimension2.width && height <= dimension2.height;
                        boolean b6 = width > dimension2.width && height > dimension2.height;
                        final boolean b7 = width != dimension.width || height != dimension.height;
                        final Image image4 = image3;
                        int n2 = 0;
                        try {
                            if ("crop".equalsIgnoreCase(s2)) {
                                if (!b5 || stretchImages) {
                                    image3 = l.A(image3, dimension2.width, dimension2.height, null);
                                }
                            }
                            else if ("cropRotate".equalsIgnoreCase(s2)) {
                                if (!b4) {
                                    image3 = l.F(image3);
                                    b5 = (height <= dimension2.width && width <= dimension2.height);
                                }
                                if (!b5 || stretchImages) {
                                    image3 = l.A(image3, dimension2.width, dimension2.height, null);
                                }
                            }
                            else if ("cover".equalsIgnoreCase(s2)) {
                                if (b6 || stretchImages) {
                                    image3 = l.B(image3, dimension2.width, dimension2.height, null, true);
                                }
                            }
                            else if ("coverRotate".equalsIgnoreCase(s2)) {
                                if (!b4) {
                                    image3 = l.F(image3);
                                    b6 = (height > dimension2.width && width > dimension2.height);
                                }
                                if (b6 || stretchImages) {
                                    image3 = l.B(image3, dimension2.width, dimension2.height, null, true);
                                }
                            }
                            else if ("fitRotate".equalsIgnoreCase(s2)) {
                                if (!b4) {
                                    image3 = l.F(image3);
                                    b5 = (height <= dimension2.width && width <= dimension2.height);
                                }
                                if (!b5 || stretchImages) {
                                    image3 = l.A(image3, dimension2.width, dimension2.height, null, true);
                                }
                            }
                            else if (!b5 || stretchImages) {
                                image3 = l.A(image3, dimension2.width, dimension2.height, null, true);
                            }
                        }
                        catch (OutOfMemoryError outOfMemoryError3) {
                            this.I.A("Failed to transform image", outOfMemoryError3);
                            System.gc();
                            if (n2++ == 0) {}
                        }
                        boolean b8 = !image4.equals(image3);
                        if (this.K.getScaledInstanceWatermarks() != null && this.K.getScaledInstanceWatermarks().size() > j) {
                            final WatermarkConfig watermarkConfig = this.K.getScaledInstanceWatermarks().get(j);
                            if (watermarkConfig != null) {
                                image3 = l.D(image3);
                                l.A((BufferedImage)image3, watermarkConfig);
                                b8 = true;
                            }
                        }
                        String s4 = "jpg";
                        if (this.K.isPreserveImageFormat()) {
                            final String c = jmaster.util.F.A.C(b.getFile());
                            if ("gif".equalsIgnoreCase(c)) {
                                s4 = "gif";
                            }
                            if ("png".equalsIgnoreCase(c)) {
                                s4 = "png";
                            }
                        }
                        if (file2 != null) {
                            zipOutputStream.putNextEntry(new ZipEntry(nextToken + "." + s4));
                            this.A(zipOutputStream, image3, int1, b8, b.getFile(), s4);
                            zipOutputStream.closeEntry();
                        }
                        else {
                            final File tempFile = File.createTempFile("jumploader", "." + s4);
                            tempFile.deleteOnExit();
                            final FileOutputStream fileOutputStream = new FileOutputStream(tempFile);
                            this.A(fileOutputStream, image3, int1, b8, b.getFile(), s4);
                            jmaster.util.F.A.A(fileOutputStream);
                            final jmaster.util.http.D d = new jmaster.util.http.D();
                            d.C(tempFile.getAbsolutePath());
                            d.D(nextToken + ".jpg");
                            d.B(nextToken);
                            b.A(d);
                        }
                    }
                    if (this.K.isUploadOriginalImage()) {
                        if (b2) {
                            zipOutputStream.putNextEntry(new ZipEntry(b.getName()));
                            final FileInputStream fileInputStream = new FileInputStream(b.getFile());
                            jmaster.util.F.A.A(fileInputStream, zipOutputStream);
                            fileInputStream.close();
                            zipOutputStream.closeEntry();
                        }
                        else {
                            final jmaster.util.http.D d2 = new jmaster.util.http.D();
                            d2.C(b.getFile().getAbsolutePath());
                            d2.D(b.getFile().getName());
                            d2.B(this.K.getFileParameterName());
                            b.A(d2);
                        }
                    }
                    if (b2) {
                        file = file2;
                        b.E(true);
                    }
                }
                finally {
                    jmaster.util.F.A.A(zipOutputStream);
                    jmaster.util.F.A.A(outputStream);
                }
            }
        }
        b.A(file, true);
        return string;
    }
    
    private boolean A(final OutputStream outputStream, final Image image, final int n, final boolean b, final File file, final String s) throws IOException {
        boolean b2 = false;
        final String c = jmaster.util.F.A.C(file);
        if (!b && ("jpg".equalsIgnoreCase(c) || "jpe".equalsIgnoreCase(c) || "jpeg".equalsIgnoreCase(c))) {
            final FileInputStream fileInputStream = new FileInputStream(file);
            try {
                jmaster.util.F.A.A(fileInputStream, outputStream);
            }
            finally {
                jmaster.util.F.A.A(fileInputStream);
            }
            b2 = true;
        }
        if (!b2) {
            final jmaster.jumploader.model.api.B.A l = this.E.L();
            if ("gif".equalsIgnoreCase(s)) {
                jmaster.util.D.A.B(l.D(image), outputStream);
            }
            else if ("png".equalsIgnoreCase(s)) {
                jmaster.util.D.A.A(l.D(image), outputStream);
            }
            else {
                jmaster.util.D.A.D(l.D(image), n / 1000.0, outputStream);
            }
        }
        return b2;
    }
    
    private void A(final Map map, final IAttributeSet set) {
        for (int i = 0; i < set.getAttributeCount(); ++i) {
            final IAttribute attribute = set.getAttributeAt(i);
            if (attribute.isSendToServer()) {
                map.put(attribute.getName(), attribute.getValue());
            }
        }
    }
    
    protected B F() throws Exception {
        return (B)Class.forName(this.K.getHttpUploaderClassName()).newInstance();
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError().initCause(ex);
        }
    }
}
