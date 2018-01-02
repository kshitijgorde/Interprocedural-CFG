// 
// Decompiled by Procyon v0.5.30
// 

package JUpload;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileInputStream;
import java.io.Reader;
import java.io.BufferedReader;
import org.apache.commons.httpclient.HttpConnection;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpConnectionManager;
import java.net.ConnectException;
import org.apache.commons.httpclient.HttpClient;
import javax.swing.JOptionPane;
import java.util.StringTokenizer;
import java.util.Iterator;
import sun.misc.BASE64Encoder;
import java.io.IOException;
import org.apache.commons.httpclient.protocol.SecureProtocolSocketFactory;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.contrib.ssl.EasySSLProtocolSocketFactory;
import org.apache.commons.httpclient.methods.MultipartPostMethod;
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

public class HTTPPostRequest extends Thread implements HTTPRequest
{
    protected static int threadCounter;
    String boundary;
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
    private int contentLength;
    
    static {
        HTTPPostRequest.threadCounter = 0;
    }
    
    public HTTPPostRequest(final JUpload parent, final DefaultListModel tModel) {
        this.boundary = "-----------------------v7sdfosd7idf9wkfzh7ylqa8DyIq";
        this.tagName = Configurator.getHTTPTagName();
        this.arrFilenames = new Vector();
        this.counter = 0;
        this.newline = System.getProperty("line.separator");
        this.arrMimeHeaders = new Vector();
        this.LastRequest = false;
        this.finished = false;
        this.running = false;
        this.status = false;
        this.debug("HTTPPostRequest() Constructor. parent is " + parent);
        this.setPriority(1);
        this.tModel = tModel;
        this.applet = parent;
    }
    
    public void setActionURL(final URL url) {
        this.debug("HTTPPostRequest() setting action URL");
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
        this.debug("HTTPPostRequest() starting REQUEST");
        this.myStatus = new UploadStatus();
        this.applet.statuspanel.add(this.myStatus);
        this.applet.controlPanel.validate();
        final MultipartPostMethod filePost = new MultipartPostMethod(this.actionURL.toString());
        this.addFilesToPost(filePost);
        if (this.actionURL.getProtocol().equalsIgnoreCase("https")) {
            this.debug("HTTPPostRequest() performRequest() registering Secure protocol");
            try {
                Protocol.registerProtocol("https", new Protocol("https", new EasySSLProtocolSocketFactory(), 443));
            }
            catch (Exception e) {
                this.debug("***PROBLEM REGISTERING SECURE HTTP***");
                e.printStackTrace();
            }
        }
        if (!this.createHttpConnection()) {
            this.debug("HTTPPostRequest() could not create connection to webserver.");
            this.applet.statuspanel.remove(this.myStatus);
            this.applet.controlPanel.repaint();
            return false;
        }
        this.debug("HTTPPostRequest() performRequest() creating files headers");
        this.createHeaders();
        this.debug("HTTPPostRequest() performRequest() calculating sizes of files");
        this.calculateSizes();
        this.debug("HTTPPostRequest() performRequest() creating http header string");
        this.createHTTPHeader();
        this.debug("HTTPPostRequest() performRequest() creating streams");
        this.createStreams(this.httpOut, this.httpIn);
        this.debug("HTTPPostRequest() performRequest() sending http header");
        this.sendHeader();
        this.debug("HTTPPostRequest() performRequest() checking for writer=" + this.writer);
        if (this.writer == null) {
            this.applet.statuspanel.remove(this.myStatus);
            this.applet.controlPanel.repaint();
            return false;
        }
        this.writer.write("--" + this.boundary);
        this.debug("HTTPPostRequest() performRequest() iterating files");
        this.iterateFiles();
        this.debug("HTTPPostRequest() performRequest() flushing writer buffer");
        this.writer.flush();
        this.receiveCheckResponse();
        this.debug("HTTPPostRequest() removing status bar and repainting");
        this.applet.statuspanel.remove(this.myStatus);
        this.applet.controlPanel.repaint();
        this.applet.validate();
        this.applet.doLayout();
        this.debug("HTTPPostRequest() REQUEST ends");
        this.debug("Upload finished.");
        if (this.LastRequest) {
            this.debug("HTTPPostRequest() performRequest() uploadapplet is " + this.applet.applet);
            final URL urlCompleteURL = Configurator.getCompleteURL();
            if (urlCompleteURL != null) {
                this.debug("HTTPPostRequest() performRequest() completeURL is not empty. Redirecting user...to=" + urlCompleteURL);
                this.applet.applet.context.showDocument(urlCompleteURL);
            }
        }
        this.debug("HTTPPostRequest() performRequest() finished.");
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
        this.debug("Upload thread ended...");
        this.applet.validate();
        this.running = false;
        this.finished = true;
    }
    
    private String WWWAuthentificationHeader() {
        final LoginFrame loginframe = new LoginFrame();
        final StringBuffer theUID = new StringBuffer().append(loginframe.getUsername());
        if (loginframe.getPassword() != null) {
            theUID.append(":").append(loginframe.getPassword());
        }
        final String encoding = new BASE64Encoder().encode(theUID.toString().getBytes());
        final String strAuthHeader = "Authorization: Basic " + encoding + this.newline;
        this.debug("HTTPPostRequest() WWWAuthentificationHeader() is [" + strAuthHeader + "]");
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
    
    private void calculateSizes() {
        this.contentLength = 0;
        for (final MimeHeader element : this.arrMimeHeaders) {
            this.contentLength += element.getHeader().length();
            this.contentLength += (int)element.getContentLength();
            this.contentLength += element.getFooter().length();
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
        if (iStatusCode >= 100 && iStatusCode < 200) {
            this.showErrorMessage(String.valueOf(strStatusCode) + ": Informational - Request received, continuing process\n" + "Reason: " + strReasonPhrase);
            return false;
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
    
    private void createHTTPHeader() {
        String strQueryString = this.actionURL.getQuery();
        if (strQueryString == null) {
            strQueryString = "";
        }
        if (strQueryString.equalsIgnoreCase("")) {
            this.debug("HTTPPostRequest() createHTTPHeader() actionURL.getQuery is empty.");
            strQueryString = "";
        }
        else {
            this.debug("HTTPPostRequest() createHTTPHeader() query string added.");
            strQueryString = "?" + this.actionURL.getQuery();
        }
        if (ProxyConfig.useProxy) {
            this.strHeader = "POST " + this.actionURL + " HTTP/1.1";
        }
        else {
            this.strHeader = "POST " + this.actionURL.getPath() + strQueryString + " HTTP/1.1";
        }
        this.strHeader = String.valueOf(this.strHeader) + this.newline;
        this.strHeader = String.valueOf(this.strHeader) + "Host: " + this.actionURL.getHost();
        this.strHeader = String.valueOf(this.strHeader) + this.newline;
        this.strHeader = String.valueOf(this.strHeader) + "Connection: close";
        this.strHeader = String.valueOf(this.strHeader) + this.newline;
        if (!Configurator.getBrowserCookie().equalsIgnoreCase("")) {
            this.debug("HTTPPostRequest() createHTTPHeader() adding Cookie header from parameter");
            this.strHeader = String.valueOf(this.strHeader) + "Cookie: " + Configurator.getBrowserCookie();
            this.strHeader = String.valueOf(this.strHeader) + this.newline;
        }
        this.strHeader = String.valueOf(this.strHeader) + "User-agent: JUpload Applet (http://www.haller-systemservice.net/jupload/)";
        this.strHeader = String.valueOf(this.strHeader) + this.newline;
        try {
            if (Configurator.getAskAuthentificate()) {
                this.debug("HTTPPostRequest() createHTTPHeader() WWW-Authentification is configured as 'obligatory'. User must enter username and password.");
                this.strHeader = String.valueOf(this.strHeader) + this.WWWAuthentificationHeader();
            }
            else {
                this.debug("HTTPPostRequest() createHTTPHeader() No WWW-Authentification is needed.");
            }
        }
        catch (NullPointerException e) {
            this.debug("HTTPPostRequest() createHTTPHeader() Could not find WWW-Authentification option.");
        }
        this.strHeader = String.valueOf(this.strHeader) + "Content-length: " + this.contentLength;
        this.strHeader = String.valueOf(this.strHeader) + this.newline;
        this.strHeader = String.valueOf(this.strHeader) + "Content-Type: multipart/form-data; boundary=" + this.boundary;
        this.strHeader = String.valueOf(this.strHeader) + this.newline + this.newline;
        this.debug("HTTPPostRequest() http header=" + this.strHeader);
    }
    
    private void createHeaders() {
        for (int i = 0; i < this.arrFilenames.size(); ++i) {
            final MyFile f = this.arrFilenames.elementAt(i);
            this.arrMimeHeaders.addElement(new MimeHeader(f, String.valueOf(this.tagName) + String.valueOf(i), this.boundary));
        }
    }
    
    private boolean createHttpConnection() {
        final HttpClient client = new HttpClient();
        this.debug("performRequest() HttpClient client = " + client);
        client.setConnectionTimeout(5000);
        final HttpConnectionManager hcm = client.getHttpConnectionManager();
        this.debug("performRequest() hcm=" + hcm);
        final HostConfiguration hconf = client.getHostConfiguration();
        hconf.setHost(this.actionURL.getHost(), this.actionURL.getPort(), this.actionURL.getProtocol());
        if (ProxyConfig.useProxy) {
            this.debug("HTTPPostRequest() using proxy settings.");
            hconf.setProxy(ProxyConfig.proxyHostname, Integer.parseInt(ProxyConfig.proxyPort));
        }
        this.debug("performRequest() hconf=" + hconf);
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
        this.debug("HTTPPostRequest() createStreams() outputstream=" + outputstream);
        this.debug("HTTPPostRequest() createStreams() inputstream=" + inputstream);
        this.writer = new OutputStreamWriter(outputstream);
        this.debug("HTTPPostRequest() createStreams() writer=" + this.writer);
        this.reader = new InputStreamReader(inputstream);
        this.debug("HTTPPostRequest() createStreams() reader=" + this.reader);
    }
    
    private void iterateFiles() throws IOException {
        final Iterator iter = this.arrMimeHeaders.iterator();
        while (iter.hasNext()) {
            final MimeHeader mimeheader = iter.next();
            this.debug("HTTPPostRequest() iterateFiles() file header: [" + mimeheader.getHeader() + "]");
            this.writer.write(mimeheader.getHeader());
            this.sendFileData(mimeheader);
            this.debug("HTTPPostRequest() iterateFiles() file content sent. removing file from queue.");
            this.tModel.removeElement(mimeheader.getFile());
            this.applet.doLayout();
            this.debug("HTTPPostRequest() iterateFiles() sending mime footer.");
            if (iter.hasNext()) {
                this.writer.write(mimeheader.getFooter());
            }
            else {
                this.writer.write(mimeheader.getLastFooter());
            }
        }
    }
    
    private void receiveCheckResponse() {
        if (this.reader != null) {
            this.debug(this.response = this.receiveResponse());
            if (Configurator.getCheckResponse() && Configurator.getShowSuccessDialog()) {
                this.checkSuccess(this.response);
            }
        }
    }
    
    private String receiveResponse() {
        String line = null;
        String response = new String();
        final int i = 0;
        final BufferedReader br = new BufferedReader(this.reader);
        do {
            try {
                line = br.readLine();
                if (line != null) {
                    response = String.valueOf(response) + line + this.newline;
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        } while (line != null);
        return response;
    }
    
    private void sendFileData(final MimeHeader mimeheader) throws IOException {
        this.myStatus.setFilename(mimeheader.getFile().getName());
        this.writer.flush();
        FileInputStream fin = null;
        FileReader fir = null;
        try {
            fin = new FileInputStream(mimeheader.getFile());
            fir = new FileReader(mimeheader.getFile());
        }
        catch (FileNotFoundException e1) {
            e1.printStackTrace();
            return;
        }
        final byte[] buffer = new byte[1024];
        try {
            int c;
            while ((c = fin.read(buffer)) != -1) {
                this.httpOut.write(buffer, 0, c);
                this.httpOut.flush();
                this.counter += c;
                this.myStatus.setValue(this.counter, this.contentLength);
            }
            fin.close();
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
    }
    
    private void sendHeader() throws IOException {
        this.debug("HTTPPostRequest() sendHeader() sending header to writer");
        this.debug("HTTPPostRequest() sendHeader() strHeader has " + this.strHeader.length() + " bytes");
        this.debug("HTTPPostRequest() sendHeader() writer=" + this.writer);
        this.writer.write(this.strHeader);
    }
    
    private void showErrorMessage(final String string) {
        JOptionPane.showMessageDialog(this.applet, string, "JUpload Error Status", 0);
    }
}
