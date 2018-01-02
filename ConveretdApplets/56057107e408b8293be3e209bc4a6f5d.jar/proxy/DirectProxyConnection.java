// 
// Decompiled by Procyon v0.5.30
// 

package proxy;

import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPClient;
import anon.util.Util;
import anon.infoservice.ImmutableProxyInterface;
import anon.shared.ProxyConnection;
import anon.infoservice.ListenerInterface;
import anon.infoservice.HTTPConnectionFactory;
import java.net.SocketException;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.UnknownHostException;
import java.net.URL;
import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.ByteArrayOutputStream;
import logging.LogHolder;
import logging.LogType;
import java.util.StringTokenizer;
import java.io.DataInputStream;
import java.io.PushbackInputStream;
import java.util.Vector;
import java.text.NumberFormat;
import java.text.DateFormat;
import java.io.InputStream;
import java.net.Socket;

final class DirectProxyConnection
{
    private Socket m_clientSocket;
    private InputStream m_socketInputStream;
    private int m_threadNumber;
    private static int m_threadCount;
    private InputStream m_inputStream;
    private String m_requestLine;
    private String m_strMethod;
    private String m_strURI;
    private String m_strProtocol;
    private String m_strVersion;
    private String m_strHost;
    private String m_strFile;
    private int m_iPort;
    private static DateFormat m_DateFormat;
    private static NumberFormat m_NumberFormat;
    private DirectProxy m_parentProxy;
    private Vector m_vecThreads;
    
    public DirectProxyConnection(final Socket clientSocket, final InputStream socketInputStream, final DirectProxy parentProxy) {
        this.m_inputStream = null;
        this.m_requestLine = null;
        this.m_strMethod = "";
        this.m_strURI = "";
        this.m_strProtocol = "";
        this.m_strVersion = "";
        this.m_strHost = "";
        this.m_strFile = "";
        this.m_iPort = -1;
        this.m_parentProxy = parentProxy;
        this.m_clientSocket = clientSocket;
        this.m_socketInputStream = socketInputStream;
        this.m_vecThreads = new Vector();
        final Thread thread = new Thread(new Runnable() {
            public void run() {
                DirectProxyConnection.this.handleRequest(null);
                synchronized (DirectProxyConnection.this.m_vecThreads) {
                    DirectProxyConnection.this.m_vecThreads.removeElement(Thread.currentThread());
                    DirectProxyConnection.this.m_vecThreads.notifyAll();
                }
            }
        });
        this.m_vecThreads.addElement(thread);
        thread.start();
    }
    
    public void stop() {
        synchronized (this.m_vecThreads) {
            if (this.m_vecThreads.size() > 0) {
                final Thread thread = this.m_vecThreads.elementAt(0);
                while (thread.isAlive()) {
                    thread.interrupt();
                    try {
                        this.m_vecThreads.wait(250L);
                    }
                    catch (InterruptedException ex) {
                        break;
                    }
                }
            }
        }
    }
    
    private static String readLine(final InputStream inputStream, final byte[] array, final int[] array2) throws Exception {
        String string = "";
        array2[0] = 0;
        try {
            int n = inputStream.read();
            if (array.length > array2[0]) {
                array[array2[0]] = (byte)n;
                final int n2 = 0;
                ++array2[n2];
            }
            while (n != 10 && n != -1) {
                if (n != 13) {
                    string += (char)n;
                }
                n = inputStream.read();
                if (array.length > array2[0]) {
                    array[array2[0]] = (byte)n;
                    final int n3 = 0;
                    ++array2[n3];
                }
            }
        }
        catch (Exception ex) {
            throw ex;
        }
        return string;
    }
    
    public static String readLine(final InputStream inputStream) throws Exception {
        String string = "";
        try {
            for (int n = inputStream.read(); n != 10 && n != -1; n = inputStream.read()) {
                if (n != 13) {
                    string += (char)n;
                }
            }
        }
        catch (Exception ex) {
            throw ex;
        }
        return string;
    }
    
    public static DirectProxy.RequestInfo getURI(final PushbackInputStream pushbackInputStream, final int n) {
        if (pushbackInputStream == null) {
            return null;
        }
        DirectProxy.RequestInfo requestInfo = null;
        final DataInputStream dataInputStream = new DataInputStream(pushbackInputStream);
        final byte[] array = new byte[n];
        final int[] array2 = { 0 };
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(readLine(dataInputStream, array, array2));
            final String nextToken = stringTokenizer.nextToken();
            String s = stringTokenizer.nextToken();
            int int1 = 80;
            if (s != null && s.length() > 0) {
                final int index = s.indexOf("//");
                if (index > 0 && s.length() > 2) {
                    s = s.substring(index + 2, s.length());
                }
                final int index2 = s.indexOf("/");
                if (index2 > 0) {
                    s = s.substring(0, index2);
                }
                final int lastIndex = s.lastIndexOf(":");
                if (lastIndex > 0 && s.length() > lastIndex + 1) {
                    try {
                        int1 = Integer.parseInt(s.substring(lastIndex + 1, s.length()));
                    }
                    catch (NumberFormatException ex) {
                        LogHolder.log(3, LogType.NET, "Could not parse port!", ex);
                    }
                    s = s.substring(0, lastIndex);
                }
                final int lastIndex2 = s.lastIndexOf(".");
                if (lastIndex2 > 0 && s.length() > lastIndex2 + 1) {
                    try {
                        Integer.parseInt(s.substring(lastIndex2 + 1, s.length()));
                    }
                    catch (NumberFormatException ex4) {
                        final StringTokenizer stringTokenizer2 = new StringTokenizer(s, ".");
                        while (stringTokenizer2.countTokens() > 2) {
                            stringTokenizer2.nextToken();
                        }
                        s = stringTokenizer2.nextToken() + "." + stringTokenizer2.nextToken();
                    }
                }
            }
            requestInfo = new DirectProxy.RequestInfo(s, nextToken, int1);
        }
        catch (Exception ex2) {
            LogHolder.log(3, LogType.NET, ex2);
        }
        if (array2[0] > 0) {
            try {
                final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
                dataOutputStream.write(array, 0, array2[0]);
                dataOutputStream.flush();
                pushbackInputStream.unread(byteArrayOutputStream.toByteArray());
            }
            catch (Exception ex3) {
                LogHolder.log(2, LogType.NET, "Could not unread request line!", ex3);
            }
        }
        return requestInfo;
    }
    
    private void handleRequest(final InputStream inputStream) {
        this.m_threadNumber = this.getThreadNumber();
        LogHolder.log(7, LogType.NET, "C(" + this.m_threadNumber + ") - New connection handler started.");
        try {
            if (inputStream != null) {
                this.m_inputStream = inputStream;
            }
            else if (this.m_socketInputStream != null) {
                this.m_inputStream = new DataInputStream(this.m_socketInputStream);
            }
            else {
                this.m_inputStream = new DataInputStream(this.m_clientSocket.getInputStream());
            }
            this.m_requestLine = readLine(this.m_inputStream);
            LogHolder.log(7, LogType.NET, "C(" + this.m_threadNumber + ") - RequestLine: >" + this.m_requestLine + "<");
            final StringTokenizer stringTokenizer = new StringTokenizer(this.m_requestLine);
            this.m_strMethod = stringTokenizer.nextToken();
            this.m_strURI = stringTokenizer.nextToken();
            if (stringTokenizer.hasMoreTokens()) {
                this.m_strVersion = stringTokenizer.nextToken();
            }
        }
        catch (Exception ex3) {
            this.badRequest();
            return;
        }
        try {
            if (this.m_strMethod.equalsIgnoreCase("CONNECT")) {
                final int index = this.m_strURI.indexOf(58);
                if (index > 0) {
                    this.m_strHost = this.m_strURI.substring(0, index);
                    this.m_iPort = Integer.parseInt(this.m_strURI.substring(index + 1));
                    this.handleCONNECT();
                }
                else {
                    this.badRequest();
                }
            }
            else if (this.m_strMethod.equalsIgnoreCase("GET") || this.m_strMethod.equalsIgnoreCase("POST") || this.m_strMethod.equalsIgnoreCase("PUT") || this.m_strMethod.equalsIgnoreCase("DELETE") || this.m_strMethod.equalsIgnoreCase("TRACE") || this.m_strMethod.equalsIgnoreCase("OPTIONS") || this.m_strMethod.equalsIgnoreCase("HEAD")) {
                final URL url = new URL(this.m_strURI);
                this.m_strProtocol = url.getProtocol();
                this.m_strHost = url.getHost();
                this.m_iPort = url.getPort();
                if (this.m_iPort == -1) {
                    this.m_iPort = 80;
                }
                this.m_strFile = url.getFile();
                if (this.m_strProtocol.equalsIgnoreCase("http")) {
                    this.handleHTTP(this.m_strMethod.equalsIgnoreCase("POST"));
                }
                else if (this.m_strProtocol.equalsIgnoreCase("ftp")) {
                    this.handleFTP();
                }
                else {
                    this.unknownProtocol();
                }
            }
            else {
                this.badRequest();
            }
        }
        catch (UnknownHostException ex4) {
            this.cannotConnect();
        }
        catch (Exception ex) {
            LogHolder.log(5, LogType.NET, "C(" + this.m_threadNumber + ")", ex);
            this.badRequest();
        }
        try {
            this.m_clientSocket.close();
        }
        catch (Exception ex2) {
            LogHolder.log(2, LogType.NET, "C(" + this.m_threadNumber + ") - Exception while closing socket: " + ex2);
        }
    }
    
    private void responseTemplate(final String s, final String s2) {
        try {
            final BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(this.m_clientSocket.getOutputStream()));
            bufferedWriter.write("HTTP/1.0 " + s + "\r\n");
            bufferedWriter.write("Content-type: text/html\r\n");
            bufferedWriter.write("Pragma: no-cache\r\n");
            bufferedWriter.write("Cache-Control: no-cache\r\n\r\n");
            bufferedWriter.write("<HTML><TITLE>" + s2 + "</TITLE>");
            bufferedWriter.write("<H1>" + s + "</H1>");
            bufferedWriter.write("<P>" + s2 + "</P>");
            bufferedWriter.write("</HTML>\n");
            bufferedWriter.flush();
            bufferedWriter.close();
        }
        catch (SocketException ex) {
            LogHolder.log(6, LogType.NET, "C(" + this.m_threadNumber + ") - Exception: ", ex);
        }
        catch (Exception ex2) {
            LogHolder.log(2, LogType.NET, "C(" + this.m_threadNumber + ") - Exception: ", ex2);
        }
    }
    
    private void cannotConnect() {
        this.responseTemplate("404 Connection error", "Cannot connect to " + this.m_strHost + ":" + this.m_iPort + ".");
    }
    
    private void unknownProtocol() {
        this.responseTemplate("501 Not implemented", "Protocol <B>" + this.m_strProtocol + "</B> not implemented, supported or unknown.");
    }
    
    private void badRequest() {
        this.responseTemplate("400 Bad Request", "Bad request: " + this.m_requestLine);
    }
    
    private void handleCONNECT() throws Exception {
        try {
            final Socket socket = new Socket(this.m_strHost, this.m_iPort);
            String s = readLine(this.m_inputStream);
            LogHolder.log(7, LogType.NET, "C(" + this.m_threadNumber + ") - Header: >" + s + "<");
            while (s.length() != 0) {
                s = readLine(this.m_inputStream);
                LogHolder.log(7, LogType.NET, "C(" + this.m_threadNumber + ") - Header: >" + s + "<");
            }
            final OutputStream outputStream = socket.getOutputStream();
            final BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(this.m_clientSocket.getOutputStream()));
            bufferedWriter.write("HTTP/1.0 200 Connection established\r\n\r\n");
            bufferedWriter.flush();
            final Thread thread = new Thread(new DirectProxyResponse(socket.getInputStream(), this.m_clientSocket.getOutputStream()), "JAP - DirectProxyResponse");
            thread.setDaemon(true);
            thread.start();
            final byte[] array = new byte[1000];
            int read;
            while ((read = this.m_inputStream.read(array)) != -1) {
                if (read > 0) {
                    outputStream.write(array, 0, read);
                    outputStream.flush();
                }
            }
            LogHolder.log(7, LogType.NET, "\n");
            LogHolder.log(7, LogType.THREAD, "C(" + this.m_threadNumber + ") - Waiting for resonse thread...");
            thread.join();
            LogHolder.log(7, LogType.THREAD, "C(" + this.m_threadNumber + ") -                           ...finished!");
            bufferedWriter.close();
            outputStream.close();
            this.m_inputStream.close();
            socket.close();
        }
        catch (Exception ex) {
            throw ex;
        }
    }
    
    private void handleHTTP(final boolean b) throws Exception {
        Exception ex = null;
        Socket socket = null;
        OutputStream outputStream = null;
        boolean b2 = false;
        try {
            ProxyConnection proxyConnection;
            if (this.m_parentProxy.getProxyInterface() != null && this.m_parentProxy.getProxyInterface().isValid() && this.m_parentProxy.getProxyInterface().getProtocol() == 3) {
                proxyConnection = new ProxyConnection(HTTPConnectionFactory.getInstance().createHTTPConnection(new ListenerInterface(this.m_strHost, this.m_iPort), this.m_parentProxy.getProxyInterface()).Connect());
            }
            else {
                proxyConnection = new ProxyConnection(HTTPConnectionFactory.getInstance().createHTTPConnection(new ListenerInterface(this.m_strHost, this.m_iPort), null).Connect());
            }
            socket = proxyConnection.getSocket();
            outputStream = socket.getOutputStream();
            final String string = "" + this.m_strMethod + " " + this.m_strFile + " " + "HTTP/1.0";
            LogHolder.log(7, LogType.NET, "C(" + this.m_threadNumber + ") - ProtocolString: >" + string + "<");
            outputStream.write((string + "\r\n").getBytes());
            String s = readLine(this.m_inputStream);
            LogHolder.log(7, LogType.NET, "C(" + this.m_threadNumber + ") - Header: >" + s + "<");
            long long1 = 0L;
            while (s.length() != 0) {
                if (!this.filter(s)) {
                    if (b && s.toLowerCase().indexOf("content-length:") >= 0) {
                        final StringTokenizer stringTokenizer = new StringTokenizer(s, ":");
                        stringTokenizer.nextToken();
                        if (stringTokenizer.hasMoreTokens()) {
                            try {
                                long1 = Long.parseLong(stringTokenizer.nextToken().trim());
                            }
                            catch (Exception ex2) {
                                LogHolder.log(2, LogType.NET, "Could not parse post content length!", ex2);
                            }
                        }
                    }
                    outputStream.write((s + "\r\n").getBytes());
                }
                else {
                    LogHolder.log(7, LogType.NET, "C(" + this.m_threadNumber + ") - Header " + s + " filtered");
                }
                s = readLine(this.m_inputStream);
                LogHolder.log(7, LogType.NET, "C(" + this.m_threadNumber + ") - Header: >" + s + "<");
            }
            outputStream.write("\r\n".getBytes());
            outputStream.flush();
            final Thread thread = new Thread(new DirectProxyResponse(socket.getInputStream(), this.m_clientSocket.getOutputStream()), "JAP - DirectProxyResponse");
            thread.start();
            LogHolder.log(7, LogType.NET, "C(" + this.m_threadNumber + ") - Headers sent, POST data may follow");
            final byte[] array = new byte[1000];
            final PushbackInputStream pushbackInputStream = new PushbackInputStream(this.m_inputStream, 1000);
            try {
                int read;
                while ((read = pushbackInputStream.read(array)) != -1) {
                    int n = read;
                    if (long1 > 0L) {
                        if (read <= long1) {
                            long1 -= read;
                        }
                        else {
                            n = (int)long1;
                            LogHolder.log(4, LogType.NET, "Overbuffered POST: " + (read - n));
                            pushbackInputStream.unread(array, n, read - n);
                            long1 = 0L;
                        }
                    }
                    else {
                        final String upperCase = new String(array, 0, read).toUpperCase();
                        if (upperCase.startsWith("GET") || upperCase.startsWith("POST") || upperCase.startsWith("HEAD") || upperCase.startsWith("PUT") || upperCase.startsWith("DELETE") || upperCase.startsWith("TRACE") || upperCase.startsWith("OPTIONS") || upperCase.startsWith("CONNECT")) {
                            pushbackInputStream.unread(array, 0, read);
                            final Thread thread2 = new Thread(new Runnable() {
                                public void run() {
                                    DirectProxyConnection.this.handleRequest(pushbackInputStream);
                                    synchronized (DirectProxyConnection.this.m_vecThreads) {
                                        DirectProxyConnection.this.m_vecThreads.removeElement(Thread.currentThread());
                                        DirectProxyConnection.this.m_vecThreads.notifyAll();
                                    }
                                }
                            });
                            this.m_vecThreads.addElement(thread2);
                            thread2.start();
                            b2 = true;
                            break;
                        }
                    }
                    if (n > 0) {
                        outputStream.write(array, 0, n);
                        outputStream.flush();
                    }
                }
            }
            catch (SocketException ex4) {
                LogHolder.log(7, LogType.NET, "Socket seams to be closed.");
            }
            LogHolder.log(7, LogType.THREAD, "C(" + this.m_threadNumber + ") - Waiting for resonse thread...");
            thread.join();
            LogHolder.log(7, LogType.THREAD, "C(" + this.m_threadNumber + ") -                  ...finished!");
        }
        catch (Exception ex3) {
            ex = ex3;
        }
        Util.closeStream(outputStream);
        if (!b2) {
            Util.closeStream(this.m_inputStream);
        }
        try {
            socket.close();
        }
        catch (Exception ex5) {}
        if (ex != null) {
            throw ex;
        }
    }
    
    private void handleFTP() {
        FTPClient ftpClient = null;
        OutputStream outputStream = null;
        try {
            final String s = "</pre></body></html>";
            outputStream = this.m_clientSocket.getOutputStream();
            ftpClient = new FTPClient();
            ftpClient.setDefaultTimeout(30000);
            ftpClient.connect(this.m_strHost);
            ftpClient.setSoTimeout(30000);
            ftpClient.setDataTimeout(30000);
            ftpClient.login("anonymous", "JAP@xxx.com");
            ftpClient.enterLocalPassiveMode();
            if (ftpClient.changeWorkingDirectory(this.m_strFile)) {
                ftpClient.changeToParentDirectory();
                final String printWorkingDirectory = ftpClient.printWorkingDirectory();
                String s2 = this.m_strURI;
                if (!s2.endsWith("/")) {
                    s2 += "/";
                }
                outputStream.write("HTTP/1.0 200 Ok\n\rContent-Type: text/html\r\n\r\n<html><head><title>FTP directory at ".getBytes());
                outputStream.write(s2.getBytes());
                outputStream.write("</title></head><body><h2>FTP directory at ".getBytes());
                outputStream.write(s2.getBytes());
                outputStream.write(("</h2><hr><pre> DIR  | <A HREF=\"" + printWorkingDirectory + "\">..</A>\n").getBytes());
                final FTPFile[] listFiles = ftpClient.listFiles(this.m_strFile);
                if (listFiles == null) {
                    outputStream.write(("No files in Directory!\nServer replied:\n" + ftpClient.getReplyString()).getBytes());
                }
                else {
                    int length = 0;
                    for (int i = 0; i < listFiles.length; ++i) {
                        if (listFiles[i].getName().length() > length) {
                            length = listFiles[i].getName().length();
                        }
                        for (int j = i + 1; j < listFiles.length; ++j) {
                            if (listFiles[i].isFile() && !listFiles[j].isFile()) {
                                final FTPFile ftpFile = listFiles[i];
                                listFiles[i] = listFiles[j];
                                listFiles[j] = ftpFile;
                            }
                        }
                    }
                    final StringBuffer sb = new StringBuffer(256);
                    for (int k = 0; k < listFiles.length; ++k) {
                        final String name = listFiles[k].getName();
                        if (!name.equals(".")) {
                            if (!name.equals("..")) {
                                final String string = "            " + DirectProxyConnection.m_NumberFormat.format(listFiles[k].getSize());
                                final String substring = string.substring(string.length() - 12);
                                final String string2 = listFiles[k].getName() + "</A>                                        ";
                                final String substring2 = string2.substring(0, Math.min(length + 5, string2.length() - 1));
                                if (listFiles[k].isDirectory() || listFiles[k].isSymbolicLink()) {
                                    sb.append(" DIR  | ");
                                    sb.append("<a href=\"");
                                    sb.append(s2);
                                    if (listFiles[k].isSymbolicLink()) {
                                        sb.append(listFiles[k].getLink());
                                    }
                                    else {
                                        sb.append(listFiles[k].getName());
                                    }
                                    sb.append("/\"><b>");
                                    sb.append(substring2);
                                    sb.append("</b></a>\n");
                                }
                                else {
                                    sb.append(" FILE | ");
                                    sb.append("<a href=\"");
                                    sb.append(s2);
                                    sb.append(listFiles[k].getName());
                                    sb.append("\">");
                                    sb.append(substring2);
                                    sb.append(" | ");
                                    sb.append(substring + " | " + DirectProxyConnection.m_DateFormat.format(listFiles[k].getTimestamp().getTime()) + "\n");
                                }
                                outputStream.write(sb.toString().getBytes());
                                sb.setLength(0);
                            }
                        }
                    }
                }
                outputStream.write(s.getBytes());
            }
            else {
                ftpClient.setFileType(2);
                outputStream.write(("HTTP/1.0 200 Ok\r\nContent-Type: application/octet-stream\r\nContent-Length: " + Long.toString(ftpClient.listFiles(this.m_strFile)[0].getSize()) + "\r\n\r\n").getBytes());
                ftpClient.retrieveFile(this.m_strFile, outputStream);
            }
            outputStream.flush();
            ftpClient.disconnect();
            outputStream.close();
            outputStream = null;
        }
        catch (Exception ex) {
            LogHolder.log(5, LogType.NET, "C(" + this.m_threadNumber + ") - Exception in handleFTP()!", ex);
            try {
                ftpClient.disconnect();
                outputStream.flush();
                outputStream.close();
            }
            catch (Throwable t) {}
        }
    }
    
    private boolean filter(final String s) {
        final String[] array = { "Proxy-Connection", "Pragma" };
        for (int i = 0; i < array.length; ++i) {
            if (s.regionMatches(true, 0, array[i], 0, array[i].length())) {
                return true;
            }
        }
        return false;
    }
    
    private synchronized int getThreadNumber() {
        return DirectProxyConnection.m_threadCount++;
    }
    
    static {
        DirectProxyConnection.m_DateFormat = DateFormat.getDateTimeInstance();
        DirectProxyConnection.m_NumberFormat = NumberFormat.getInstance();
    }
}
