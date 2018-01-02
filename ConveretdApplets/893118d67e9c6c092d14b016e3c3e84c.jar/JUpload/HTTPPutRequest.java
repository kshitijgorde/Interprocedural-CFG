// 
// Decompiled by Procyon v0.5.30
// 

package JUpload;

import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.Reader;
import java.io.BufferedReader;
import org.apache.commons.httpclient.HttpConnection;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpConnectionManager;
import java.net.ConnectException;
import org.apache.commons.httpclient.HttpClient;
import javax.swing.JOptionPane;
import java.util.Iterator;
import org.apache.commons.httpclient.methods.MultipartPostMethod;
import sun.misc.BASE64Encoder;
import java.net.SocketException;
import java.util.StringTokenizer;
import java.io.IOException;
import org.apache.commons.httpclient.protocol.SecureProtocolSocketFactory;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.contrib.ssl.EasySSLProtocolSocketFactory;
import java.awt.Component;
import java.io.File;
import java.net.Socket;
import java.io.OutputStreamWriter;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.io.InputStream;
import javax.swing.DefaultListModel;
import java.util.Vector;
import java.net.URL;

public class HTTPPutRequest extends Thread implements HTTPRequest
{
    protected static int threadCounter;
    String strHostname;
    String tagName;
    URL actionURL;
    Vector arrFilenames;
    int counter;
    int iPort;
    private DefaultListModel tModel;
    private InputStream httpIn;
    private InputStreamReader reader;
    private JUpload applet;
    private OutputStream httpOut;
    private OutputStreamWriter writer;
    private Socket socket;
    private String newline;
    private String response;
    private String strHeader;
    private UploadStatus myStatus;
    private Vector arrMimeHeaders;
    private boolean LastRequest;
    private boolean finished;
    private boolean running;
    private boolean status;
    
    static {
        HTTPPutRequest.threadCounter = 0;
    }
    
    public HTTPPutRequest(final JUpload parent, final DefaultListModel tModel) {
        this.tagName = Configurator.getHTTPTagName();
        this.arrFilenames = new Vector();
        this.counter = 0;
        this.newline = System.getProperty("line.separator");
        this.arrMimeHeaders = new Vector();
        this.LastRequest = false;
        this.finished = false;
        this.running = false;
        this.status = false;
        this.debug("HTTPPutRequest() Constructor. parent is " + parent);
        this.setPriority(1);
        this.tModel = tModel;
        this.applet = parent;
    }
    
    public void setActionURL(final URL url) {
        this.debug("HTTPPutRequest() setting action URL");
        this.actionURL = url;
        if (url.getPort() != -1) {
            this.iPort = url.getPort();
        }
        else {
            this.iPort = 80;
        }
        this.strHostname = url.getHost();
    }
    
    public boolean isFinished() {
        return this.finished;
    }
    
    public void setLastRequest(final boolean b) {
        this.LastRequest = b;
    }
    
    public boolean isRunning() {
        return this.running;
    }
    
    public void addFile(final File fileToUpload) {
        this.arrFilenames.addElement(fileToUpload);
    }
    
    public void debug(final String s) {
        if (Configurator.getDebug()) {
            System.out.println(s);
        }
    }
    
    public boolean performRequest() throws IOException {
        this.debug("HTTPPutRequest() starting REQUEST");
        this.myStatus = new UploadStatus();
        this.applet.statuspanel.add(this.myStatus);
        this.applet.controlPanel.validate();
        if (this.actionURL.getProtocol().equalsIgnoreCase("https")) {
            this.debug("HTTPPutRequest() performRequest() registering Secure protocol");
            try {
                Protocol.registerProtocol("https", new Protocol("https", new EasySSLProtocolSocketFactory(), 443));
            }
            catch (Exception e) {
                this.debug("***PROBLEM REGISTERING SECURE HTTP***");
                e.printStackTrace();
            }
        }
        if (!this.createHttpConnection()) {
            this.debug("HTTPPutRequest() could not create connection to webserver: " + this.actionURL.getHost());
            this.applet.statuspanel.remove(this.myStatus);
            this.applet.controlPanel.repaint();
            this.running = false;
            this.finished = true;
            return false;
        }
        this.debug("HTTPPutRequest() create header");
        this.createHeaders();
        this.debug("HTTPPutRequest() performRequest() creating streams");
        this.createStreams(this.httpOut, this.httpIn);
        if (this.writer == null) {
            this.applet.statuspanel.remove(this.myStatus);
            this.applet.controlPanel.repaint();
            this.running = false;
            this.finished = true;
            return false;
        }
        this.debug("HTTPPutRequest() performRequest() iterating files");
        this.iterateFiles();
        this.debug("HTTPPutRequest() performRequest() flushing writer buffer");
        this.writer.flush();
        this.debug("HTTPPutRequest() REQUEST ends");
        this.debug("Upload finished.");
        if (this.LastRequest) {
            String strRequest;
            if (ProxyConfig.useProxy) {
                strRequest = "HEAD " + this.actionURL + " HTTP/1.1\r\nHost: " + this.actionURL.getHost() + "\r\n" + "Connection: close\r\n\r\n";
            }
            else {
                strRequest = "HEAD " + this.actionURL.getPath() + " HTTP/1.1\r\nHost: " + this.actionURL.getHost() + "\r\n" + "Connection: close\r\n\r\n";
            }
            this.debug("HTTPPostRequest() performRequest() closing http connection...");
            this.writer.write(strRequest);
            this.writer.flush();
            this.httpOut.flush();
            final String strResponse = this.receiveResponse();
            this.debug("HTTPPutRequest() performRequest() uploadapplet is " + this.applet.applet);
            final URL urlCompleteURL = Configurator.getCompleteURL();
            if (urlCompleteURL != null) {
                this.debug("HTTPPutRequest() performRequest() completeURL is not empty. Redirecting user...to=" + urlCompleteURL);
                this.applet.applet.context.showDocument(urlCompleteURL);
            }
        }
        this.debug("HTTPPutRequest() performRequest() finished.");
        return true;
    }
    
    public void run() {
        this.running = true;
        this.debug("Upload thread started and running...");
        try {
            this.performRequest();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        this.debug("HTTPPutRequest() Upload thread ended...");
        this.debug("HTTPPutRequest() removing status bar and repainting");
        this.applet.statuspanel.remove(this.myStatus);
        this.applet.controlPanel.repaint();
        this.applet.doLayout();
        this.applet.validate();
        this.running = false;
        this.finished = true;
    }
    
    private long getRemoteFilesize(final MimeHeader mimeheader) throws IOException, SocketException {
        final String strHeadHeader = mimeheader.getHeadHeader(this.actionURL.getHost());
        this.writer.write(strHeadHeader);
        this.debug("HTTPPutRequest() flushing output buffer");
        this.writer.flush();
        this.debug("HTTPPutRequest() receiving answer...");
        final String strResponse = this.receiveResponse();
        if (strResponse.indexOf("Connection: close") > 0) {
            this.debug("HTTPPutRequest() server requested CLOSE. re-opening streams");
            this.writer.close();
            this.reader.close();
            this.createHttpConnection();
            this.createStreams(this.httpOut, this.httpIn);
            this.debug("HTTPPutRequest() re-opened streams");
        }
        long remoteSize = 0L;
        int status = 0;
        final StringTokenizer st1 = new StringTokenizer(strResponse, "\r\n");
        String strReasonPhrase = null;
        while (st1.hasMoreTokens()) {
            final StringTokenizer st2 = new StringTokenizer(st1.nextToken());
            final String strKey = st2.nextToken();
            final String strValue = st2.nextToken();
            if (strKey.equalsIgnoreCase("content-length:")) {
                remoteSize = Long.parseLong(strValue);
            }
            if (strKey.equalsIgnoreCase("http/1.1")) {
                status = Integer.parseInt(strValue);
                strReasonPhrase = st2.nextToken("\r\n");
            }
            if (strKey.equalsIgnoreCase("http/1.0")) {
                status = Integer.parseInt(strValue);
                strReasonPhrase = st2.nextToken("\r\n");
            }
        }
        if (status >= 500) {
            this.showErrorMessage(String.valueOf(status) + ": Server Error - The server failed to fulfill an apparently valid request\n" + "Reason: " + strReasonPhrase);
            return 0L;
        }
        if (status >= 400 && status != 404) {
            this.showErrorMessage(String.valueOf(status) + ": Client Error - The request contains bad syntax or cannot be fulfilled\n" + "Reason: " + strReasonPhrase);
            return 0L;
        }
        if (status == 200) {
            return remoteSize;
        }
        return 0L;
    }
    
    private String WWWAuthentificationHeader() {
        final LoginFrame loginframe = new LoginFrame();
        final StringBuffer theUID = new StringBuffer().append(loginframe.getUsername());
        if (loginframe.getPassword() != null) {
            theUID.append(":").append(loginframe.getPassword());
        }
        final String encoding = new BASE64Encoder().encode(theUID.toString().getBytes());
        final String strAuthHeader = "Authorization: Basic " + encoding + this.newline;
        this.debug("HTTPPutRequest() WWWAuthentificationHeader() is [" + strAuthHeader + "]");
        return strAuthHeader;
    }
    
    private void addFilesToPost(final MultipartPostMethod filePost) {
        try {
            for (final MimeHeader mimeheader : this.arrMimeHeaders) {
                final File targetFile = mimeheader.getFile();
                filePost.addParameter(targetFile.getName(), targetFile);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private boolean checkSuccess(final String response) {
        final StringTokenizer tok = new StringTokenizer(response);
        final String strHTTPVersion = tok.nextToken();
        final String strStatusCode = tok.nextToken();
        final String strReasonPhrase = tok.nextToken("\r\n");
        final int iStatusCode = Integer.parseInt(strStatusCode);
        if (iStatusCode >= 500) {
            this.showErrorMessage(String.valueOf(strStatusCode) + ": Server Error - The server failed to fulfill an apparently valid request\n" + "Reason: " + strReasonPhrase);
            return false;
        }
        if (iStatusCode >= 400) {
            this.showErrorMessage(String.valueOf(strStatusCode) + ": Client Error - The request contains bad syntax or cannot be fulfilled\n" + "Reason: " + strReasonPhrase);
            return false;
        }
        if (iStatusCode >= 300) {
            this.showErrorMessage(String.valueOf(strStatusCode) + ": Redirection - Further action must be taken in order to complete the request\n" + "Reason: " + strReasonPhrase);
            return false;
        }
        if (iStatusCode == 200 && Configurator.getShowSuccessDialog()) {
            JOptionPane.showMessageDialog(this.applet, Configurator.getSuccessDialogMessage(), Configurator.getSuccessDialogTitle(), 1);
        }
        return true;
    }
    
    private void closeStreams() throws IOException {
        this.writer.close();
        try {
            this.reader.close();
            this.socket.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void createHeaders() {
        for (int i = 0; i < this.arrFilenames.size(); ++i) {
            final MyFile f = this.arrFilenames.elementAt(i);
            this.arrMimeHeaders.addElement(new MimeHeader(f));
        }
    }
    
    private boolean createHttpConnection() {
        HttpClient client = null;
        client = new HttpClient();
        this.debug("performRequest() HttpClient client = " + client);
        client.setConnectionTimeout(5000);
        HttpConnectionManager hcm = null;
        hcm = client.getHttpConnectionManager();
        this.debug("performRequest() hcm=" + hcm);
        HostConfiguration hconf = null;
        hconf = client.getHostConfiguration();
        this.debug("performRequest() hconf=" + hconf);
        hconf.setHost(this.actionURL.getHost(), this.actionURL.getPort(), this.actionURL.getProtocol());
        if (ProxyConfig.useProxy) {
            this.debug("HTTPPostRequest() using proxy settings.");
            try {
                hconf.setProxy(ProxyConfig.proxyHostname, Integer.parseInt(ProxyConfig.proxyPort));
            }
            catch (NumberFormatException nfe) {
                this.debug("*** Error: could not set proxy settings.");
                ProxyConfig.useProxy = false;
            }
        }
        final HttpConnection hconn = hcm.getConnection(hconf);
        this.debug("performRequest() hconn=" + hconn);
        try {
            this.debug("performRequest() opening hconn connection");
            hconn.open();
        }
        catch (ConnectException e7) {
            JOptionPane.showMessageDialog(null, "Could not connect to server", "Could not connect to server.", 0);
            return false;
        }
        catch (IOException e4) {
            e4.printStackTrace();
        }
        this.httpOut = null;
        this.httpIn = null;
        try {
            this.httpOut = hconn.getRequestOutputStream();
            this.httpIn = hconn.getResponseInputStream();
        }
        catch (IllegalStateException e5) {
            e5.printStackTrace();
        }
        catch (IOException e6) {
            e6.printStackTrace();
        }
        return true;
    }
    
    private void createStreams(final OutputStream outputstream, final InputStream inputstream) {
        this.debug("HTTPPutRequest() createStreams() outputstream=" + outputstream);
        this.debug("HTTPPutRequest() createStreams() inputstream=" + inputstream);
        this.writer = null;
        this.reader = null;
        this.writer = new OutputStreamWriter(outputstream);
        this.debug("HTTPPutRequest() createStreams() writer=" + this.writer);
        this.reader = new InputStreamReader(inputstream);
        this.debug("HTTPPutRequest() createStreams() reader=" + this.reader);
    }
    
    private void iterateFiles() throws IOException, SocketException {
        this.debug("HTTPPutRequest() iterateFiles()");
        for (final MimeHeader mimeheader : this.arrMimeHeaders) {
            long remoteSize = 0L;
            boolean stop = false;
            int retryCounter = 0;
            final long lastOffset = 0L;
            do {
                if (retryCounter > 3) {
                    stop = true;
                }
                remoteSize = this.getRemoteFilesize(mimeheader);
                this.writer.flush();
                this.httpOut.flush();
                this.debug("  getRemoteFilesize() said " + remoteSize);
                if (remoteSize == mimeheader.getFile().length()) {
                    this.debug(" iterateFiles() File already on server with correct file size. Skipping");
                    this.debug(" doing layout");
                    this.tModel.removeElement(mimeheader.getFile());
                    this.applet.doLayout();
                    break;
                }
                final long offset = remoteSize;
                if (offset == lastOffset) {
                    ++retryCounter;
                }
                final long maxlength = 65536L;
                final String putHeader = mimeheader.getPutHeader(this.actionURL.getHost(), maxlength, offset);
                this.writer.write(putHeader);
                final long bytesSent = this.sendFileData(mimeheader, offset, maxlength);
                this.debug("HTTPPutRequest() iterateFiles() file content sent:" + bytesSent + " bytes");
                final String strResponse = this.receiveResponse();
                if (strResponse.indexOf("Connection: close") <= 0) {
                    continue;
                }
                this.debug("HTTPPutRequest() server requested CLOSE. re-opening streams");
                this.writer.close();
                this.reader.close();
                this.httpIn.close();
                this.httpOut.close();
                this.createHttpConnection();
                this.createStreams(this.httpOut, this.httpIn);
                this.debug("HTTPPutRequest() re-opened streams");
            } while (remoteSize != mimeheader.getFile().length() && !stop);
            this.debug("  next file...");
        }
    }
    
    private void receiveCheckResponse() throws IOException {
        if (this.reader != null) {
            this.debug(this.response = this.receiveResponse());
            if (Configurator.getCheckResponse() && Configurator.getShowSuccessDialog()) {
                this.checkSuccess(this.response);
            }
        }
    }
    
    private String receiveResponse() throws IOException {
        String line = null;
        String response = new String();
        final int i = 0;
        this.debug("HTTPPutRequest() receiveResponse() creating buffered reader from " + this.reader);
        BufferedReader br = null;
        try {
            br = new BufferedReader(this.reader);
            do {
                try {
                    line = br.readLine();
                    if (line != null) {
                        response = String.valueOf(response) + line + this.newline;
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                    this.debug("HTTPPutRequest() receiveResponse() re-opening http connection and streams");
                    this.createHttpConnection();
                    this.createStreams(this.httpOut, this.httpIn);
                    return null;
                }
                if (line == null) {
                    break;
                }
            } while (br.ready());
        }
        catch (Exception e2) {
            e2.printStackTrace();
        }
        return response;
    }
    
    private long sendFileData(final MimeHeader mimeheader, final long offset, final long maxlength) throws IOException {
        this.myStatus.setFilename(mimeheader.getFile().getName());
        this.writer.flush();
        FileInputStream fin = null;
        try {
            fin = new FileInputStream(mimeheader.getFile());
            fin.skip(offset);
        }
        catch (FileNotFoundException e1) {
            e1.printStackTrace();
            return 0L;
        }
        long bytesSent = 0L;
        final byte[] buffer = new byte[4096];
        try {
            int c;
            while ((c = fin.read(buffer)) != -1) {
                if (bytesSent < maxlength) {
                    try {
                        this.httpOut.write(buffer, 0, c);
                        this.httpOut.flush();
                    }
                    catch (SocketException se) {
                        se.printStackTrace();
                        JOptionPane.showMessageDialog(this.applet.applet, "Server closed connection.");
                        return bytesSent;
                    }
                    this.counter += c;
                    bytesSent += c;
                    this.myStatus.setValue((int)(offset + bytesSent), (int)mimeheader.getFile().length());
                }
                else {
                    final int rest = (int)(maxlength - bytesSent);
                    if (rest > 0) {
                        this.httpOut.write(buffer, 0, rest);
                        this.httpOut.flush();
                        this.counter += rest;
                        bytesSent += rest;
                        break;
                    }
                    break;
                }
            }
            fin.close();
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
        return bytesSent;
    }
    
    private void showErrorMessage(final String string) {
        JOptionPane.showMessageDialog(this.applet, string, "JUpload Error Status", 0);
    }
}
