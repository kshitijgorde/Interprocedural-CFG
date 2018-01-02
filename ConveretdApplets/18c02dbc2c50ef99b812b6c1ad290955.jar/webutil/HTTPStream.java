// 
// Decompiled by Procyon v0.5.30
// 

package webutil;

import javautil.Version;
import java.nio.channels.FileChannel;
import java.nio.ByteBuffer;
import java.io.File;
import java.io.FileInputStream;
import fileutil.FileUtil;
import fileutil.SFile;
import action.upload.params.UploadTaskParam;
import java.net.ProtocolException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import java.io.UnsupportedEncodingException;
import java.util.zip.InflaterInputStream;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import java.io.IOException;
import action.FileStationHandler;
import java.lang.reflect.Method;
import java.io.InputStreamReader;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;

public class HTTPStream
{
    private String _ServerAddr;
    private String _Boundary;
    private HttpURLConnection _ServerConnection;
    private DataOutputStream _OutputStream;
    private InputStreamReader _InputStream;
    private static Method setLongFixedLengthStreamingMode;
    private static int BUFFERSIZE;
    
    public HTTPStream(final String serverAddr) {
        this._ServerAddr = null;
        this._Boundary = null;
        this._ServerConnection = null;
        this._InputStream = null;
        this._ServerAddr = serverAddr;
    }
    
    public void closeConnection() {
        try {
            if (this._OutputStream != null) {
                this._OutputStream.close();
                this._OutputStream = null;
            }
            if (this._InputStream != null) {
                this._InputStream.close();
                this._InputStream = null;
            }
            if (this._ServerConnection != null) {
                this._ServerConnection.disconnect();
                this._ServerConnection = null;
            }
        }
        catch (IOException ex) {
            FileStationHandler.log(ex);
        }
    }
    
    public InputStreamReader getResponse() throws UnsupportedEncodingException, IOException {
        if (null == this._ServerConnection) {
            throw new IOException();
        }
        final String contentEncoding = this._ServerConnection.getContentEncoding();
        if (null != contentEncoding) {
            if (-1 != contentEncoding.indexOf("gzip")) {
                this._InputStream = new InputStreamReader(new GZIPInputStream(this._ServerConnection.getInputStream()), "utf-8");
            }
            else if (-1 != contentEncoding.indexOf("deflate")) {
                this._InputStream = new InputStreamReader(new InflaterInputStream(this._ServerConnection.getInputStream()), "utf-8");
            }
        }
        else {
            this._InputStream = new InputStreamReader(this._ServerConnection.getInputStream(), "utf-8");
        }
        return this._InputStream;
    }
    
    private String generateRandomBoundary() {
        final StringBuffer sb = new StringBuffer();
        sb.append("---------------------------1");
        final Random random = new Random();
        for (int i = 0; i < 13; ++i) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
    
    private HttpURLConnection openConnection() throws MalformedURLException, IOException, ProtocolException {
        final HttpURLConnection httpProfile = (HttpURLConnection)new URL(this._ServerAddr).openConnection();
        this.setHTTPProfile(httpProfile);
        return httpProfile;
    }
    
    private void setHTTPProfile(final HttpURLConnection httpURLConnection) throws IOException {
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setDefaultUseCaches(false);
        httpURLConnection.setRequestProperty("Accept", "*/*");
        httpURLConnection.setRequestProperty("Accept-Encoding", "gzip,deflate");
        httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");
        httpURLConnection.setRequestProperty("Keep-Alive", "300");
        httpURLConnection.setRequestProperty("Connection", "keep-alive");
        this._Boundary = this.generateRandomBoundary();
        httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + this._Boundary);
    }
    
    private DataOutputStream getOutputStream(final HttpURLConnection httpURLConnection) throws IOException {
        return new DataOutputStream(httpURLConnection.getOutputStream());
    }
    
    private void prepareParams(final StringBuffer sb, final UploadTaskParam uploadTaskParam, final boolean b) throws IOException {
        sb.append("--" + this._Boundary + "\r\n");
        if (b) {
            this.writeParam(sb, "blCheckSkip", String.valueOf(b));
        }
        this.writeParam(sb, "isDir", String.valueOf(!uploadTaskParam.getCurrentUFile().getFile().isFile()));
        this.writeParam(sb, "filesize", String.valueOf(uploadTaskParam.getCurrentUFile().getFile().length()));
        this.writeParam(sb, "path", uploadTaskParam.getCurrentUFile().getRemoteDir());
        this.writeFileParam(sb, uploadTaskParam.getCurrentUFile().getFile());
    }
    
    private void writeParam(final StringBuffer sb, final String s, String s2) {
        if (null == s) {
            throw new IllegalArgumentException();
        }
        if (null == s2) {
            s2 = "";
        }
        sb.append("Content-Disposition: form-data; name=\"" + s + "\"");
        sb.append("\r\n\r\n");
        sb.append(s2).append("\r\n");
        sb.append("--" + this._Boundary + "\r\n");
    }
    
    private void writeFileParam(final StringBuffer sb, final SFile sFile) throws IOException {
        final String s = sFile.isFile() ? "file" : "dir";
        final String mimetype = FileUtil.getMimetype(sFile.getName());
        sb.append("Content-Disposition: form-data; name=\"" + s + "\"; " + "filename=\"");
        sb.append(sFile.getName());
        sb.append("\"\r\nContent-Type: " + mimetype + "\r\n\r\n");
    }
    
    private void sendFile(final DataOutputStream dataOutputStream, final UploadTaskParam uploadTaskParam) throws IOException {
        if (uploadTaskParam.getCurrentUFile().getFile().isFile()) {
            this.uploadFile(dataOutputStream, uploadTaskParam);
        }
        else {
            uploadTaskParam.updateTimeAndSpace(uploadTaskParam.getCurrentUFile().getFile().length());
        }
    }
    
    private void uploadFile(final DataOutputStream dataOutputStream, final UploadTaskParam uploadTaskParam) throws IOException, NullPointerException {
        FileInputStream fileInputStream;
        FileChannel channel;
        ByteBuffer allocateDirect;
        try {
            fileInputStream = new FileInputStream(uploadTaskParam.getCurrentUFile().getFile());
            channel = fileInputStream.getChannel();
            allocateDirect = ByteBuffer.allocateDirect(HTTPStream.BUFFERSIZE * 2);
        }
        catch (Exception ex) {
            throw new NullPointerException();
        }
        final byte[] array = new byte[HTTPStream.BUFFERSIZE];
        int read;
        while ((read = channel.read(allocateDirect)) != -1) {
            if (read == 0) {
                continue;
            }
            allocateDirect.position(0);
            allocateDirect.limit(read);
            while (allocateDirect.hasRemaining()) {
                final int min = Math.min(allocateDirect.remaining(), HTTPStream.BUFFERSIZE);
                allocateDirect.get(array, 0, min);
                dataOutputStream.write(array, 0, min);
                dataOutputStream.flush();
                uploadTaskParam.updateTimeAndSpace(min);
            }
            allocateDirect.clear();
        }
        try {
            fileInputStream.close();
        }
        catch (IOException ex2) {
            throw new NullPointerException();
        }
    }
    
    public void setStreamingMode(final long n) {
        try {
            HTTPStream.setLongFixedLengthStreamingMode.invoke(this._ServerConnection, n);
        }
        catch (Exception ex) {
            if (n <= 2147483647L) {
                this._ServerConnection.setFixedLengthStreamingMode((int)n);
            }
            else {
                this._ServerConnection.setChunkedStreamingMode(HTTPStream.BUFFERSIZE);
            }
        }
    }
    
    public boolean checkSkipHandler(final UploadTaskParam uploadTaskParam) throws MalformedURLException, ProtocolException, IOException, NullPointerException {
        if (uploadTaskParam.isOverwrite() || !uploadTaskParam.getCurrentUFile().getFile().isFile()) {
            return false;
        }
        final StringBuffer sb = new StringBuffer();
        this._ServerConnection = this.openConnection();
        this.prepareParams(sb, uploadTaskParam, true);
        final byte[] bytes = sb.toString().getBytes("utf-8");
        this.setStreamingMode(bytes.length);
        (this._OutputStream = this.getOutputStream(this._ServerConnection)).write(bytes, 0, bytes.length);
        return true;
    }
    
    public void uploadHander(final UploadTaskParam uploadTaskParam) throws MalformedURLException, ProtocolException, IOException, NullPointerException {
        final StringBuffer sb = new StringBuffer();
        this._ServerConnection = this.openConnection();
        this.prepareParams(sb, uploadTaskParam, false);
        final byte[] bytes = sb.toString().getBytes("utf-8");
        if (uploadTaskParam.getCurrentUFile().getFile().isFile()) {
            this.setStreamingMode(bytes.length + uploadTaskParam.getCurrentUFile().getFile().length());
        }
        else {
            this.setStreamingMode(bytes.length);
        }
        (this._OutputStream = this.getOutputStream(this._ServerConnection)).write(bytes, 0, bytes.length);
        this.sendFile(this._OutputStream, uploadTaskParam);
    }
    
    static {
        HTTPStream.BUFFERSIZE = 131072;
        final Version version = new Version(System.getProperty("java.version"));
        HTTPStream.setLongFixedLengthStreamingMode = null;
        if (version.compare(new Version("1.7.0")) >= 0) {
            try {
                HTTPStream.setLongFixedLengthStreamingMode = Class.forName("java.net.HttpURLConnection").getDeclaredMethod("setFixedLengthStreamingMode", Long.TYPE);
            }
            catch (Throwable t) {}
        }
    }
}
