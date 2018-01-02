// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.ssh;

import java.net.Socket;
import mindbright.security.Cipher;
import mindbright.terminal.Terminal;
import java.io.FileOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PipedOutputStream;
import java.io.PipedInputStream;
import java.io.File;

public class SSHSCP extends SSHClientUserAdaptor implements SSHConsole
{
    SSHClient client;
    SSHInteractor interactor;
    SSHClientUser proxyUser;
    SSHSCPIndicator indicator;
    SSHInteractor ourInteractAdapter;
    File cwd;
    boolean recursive;
    boolean verbose;
    String sshHost;
    PipedInputStream inTop;
    PipedOutputStream inBottom;
    
    public SSHSCP(final String sshHost, final int port, final SSHAuthenticator authenticator, final File cwd, final boolean recursive, final boolean verbose) throws IOException {
        super(sshHost, port);
        this.interactor = null;
        this.proxyUser = null;
        this.indicator = null;
        this.ourInteractAdapter = null;
        this.ourInteractAdapter = new SSHInteractorAdapter() {
            public void open(final SSHClient client) {
                SSHSCP.this.open(client);
            }
            
            public void disconnected(final SSHClient client, final boolean graceful) {
                SSHSCP.this.disconnected(client, graceful);
            }
            
            public void alert(final String msg) {
                SSHSCP.this.alert(msg);
            }
        };
        this.client = new SSHClient(authenticator, this);
        this.inTop = new PipedInputStream();
        this.inBottom = new PipedOutputStream(this.inTop);
        this.cwd = cwd;
        this.recursive = recursive;
        this.verbose = verbose;
        this.sshHost = sshHost;
        this.client.setConsole(this);
        this.client.activateTunnels = false;
    }
    
    public void setInteractor(final SSHInteractor interactor) {
        this.interactor = interactor;
    }
    
    public void setClientUser(final SSHClientUser proxyUser) {
        this.proxyUser = proxyUser;
    }
    
    public void setIndicator(final SSHSCPIndicator indicator) {
        this.indicator = indicator;
    }
    
    public void abort() {
        this.interactor = null;
        this.indicator = null;
        this.client.forcedDisconnect();
    }
    
    public void copyToRemote(final String localFile, String remoteFile) throws IOException {
        File lf = new File(localFile);
        if (!lf.isAbsolute()) {
            lf = new File(this.cwd, localFile);
        }
        if (!lf.exists()) {
            throw new IOException("File: " + localFile + " does not exist");
        }
        if (!lf.isFile() && !lf.isDirectory()) {
            throw new IOException("File: " + localFile + " is not a regular file or directory");
        }
        if (lf.isDirectory() && !this.recursive) {
            throw new IOException("File: " + localFile + " is a directory, use recursive mode");
        }
        if (remoteFile == null || remoteFile.equals("")) {
            remoteFile = ".";
        }
        this.client.doSingleCommand("scp " + (lf.isDirectory() ? "-d " : "") + "-t " + (this.recursive ? "-r " : "") + (this.verbose ? "-v " : "") + remoteFile, true, 0L);
        this.readResponse("After starting remote scp");
        this.writeFileToRemote(lf);
        this.client.forcedDisconnect();
    }
    
    public void copyToRemote(final String[] localFiles, String remoteFile) throws IOException {
        if (remoteFile == null || remoteFile.equals("")) {
            remoteFile = ".";
        }
        if (localFiles.length == 1) {
            this.copyToRemote(localFiles[0], remoteFile);
        }
        else {
            this.client.doSingleCommand("scp -d -t " + (this.recursive ? "-r " : "") + (this.verbose ? "-v " : "") + remoteFile, true, 0L);
            this.readResponse("After starting remote scp");
            for (int i = 0; i < localFiles.length; ++i) {
                File lf = new File(localFiles[i]);
                if (!lf.isAbsolute()) {
                    lf = new File(this.cwd, localFiles[i]);
                }
                if (!lf.isFile() && !lf.isDirectory()) {
                    this.alert("File: " + lf.getName() + " is not a regular file or directory");
                }
                else {
                    this.writeFileToRemote(lf);
                }
            }
            this.client.forcedDisconnect();
        }
    }
    
    public void copyToLocal(String localFile, final String remoteFile) throws IOException {
        if (localFile == null || localFile.equals("")) {
            localFile = ".";
        }
        File lf = new File(localFile);
        if (!lf.isAbsolute()) {
            lf = new File(this.cwd, localFile);
        }
        if (lf.exists() && !lf.isFile() && !lf.isDirectory()) {
            throw new IOException("File: " + localFile + " is not a regular file or directory");
        }
        this.client.doSingleCommand("scp -f " + (this.recursive ? "-r " : "") + (this.verbose ? "-v " : "") + remoteFile, true, 0L);
        this.readFromRemote(lf);
        this.client.forcedDisconnect();
    }
    
    boolean writeDirToRemote(final File dir) throws IOException {
        if (!this.recursive) {
            this.writeError("File " + dir.getName() + " is a directory, use recursive mode");
            return false;
        }
        this.writeString("D0755 0 " + dir.getName() + "\n");
        if (this.indicator != null) {
            this.indicator.startDir(dir.getAbsolutePath());
        }
        this.readResponse("After sedning dirdata");
        final String[] dirList = dir.list();
        for (int i = 0; i < dirList.length; ++i) {
            final File f = new File(dir, dirList[i]);
            this.writeFileToRemote(f);
        }
        this.writeString("E\n");
        if (this.indicator != null) {
            this.indicator.endDir();
        }
        return true;
    }
    
    void writeFileToRemote(final File file) throws IOException {
        if (file.isDirectory()) {
            if (!this.writeDirToRemote(file)) {
                return;
            }
        }
        else {
            if (!file.isFile()) {
                throw new IOException("Not ordinary file: " + file.getName());
            }
            this.writeString("C0644 " + file.length() + " " + file.getName() + "\n");
            if (this.indicator != null) {
                this.indicator.startFile(file.getName(), (int)file.length());
            }
            this.readResponse("After sending filedata");
            final FileInputStream fi = new FileInputStream(file);
            this.writeFully(fi, (int)file.length());
            this.writeByte(0);
            if (this.indicator != null) {
                this.indicator.endFile();
            }
        }
        this.readResponse("After writing file");
    }
    
    void readFromRemote(final File file) throws IOException {
        final String[] cmdParts = new String[3];
        this.writeByte(0);
        while (true) {
            String cmd;
            try {
                cmd = this.readString();
            }
            catch (EOFException e) {
                return;
            }
            if (cmd != null) {
                final char cmdChar = cmd.charAt(0);
                switch (cmdChar) {
                    case 'E': {
                        this.writeByte(0);
                    }
                    case 'T': {
                        System.out.println("(T)ime not supported: " + cmd);
                        continue;
                    }
                    case 'C':
                    case 'D': {
                        String targetName = file.getAbsolutePath();
                        this.parseCommand(cmd, cmdParts);
                        if (file.isDirectory()) {
                            targetName = targetName + File.separator + cmdParts[2];
                        }
                        final File targetFile = new File(targetName);
                        if (cmdChar != 'D') {
                            final FileOutputStream fo = new FileOutputStream(targetFile);
                            this.writeByte(0);
                            final int len = Integer.parseInt(cmdParts[1]);
                            if (this.indicator != null) {
                                this.indicator.startFile(targetFile.getName(), len);
                            }
                            this.readFully(fo, len);
                            this.readResponse("After reading file");
                            if (this.indicator != null) {
                                this.indicator.endFile();
                            }
                            this.writeByte(0);
                            continue;
                        }
                        if (targetFile.exists()) {
                            if (!targetFile.isDirectory()) {
                                this.writeError("Invalid target " + targetFile.getName() + ", must be a directory");
                            }
                        }
                        else if (!targetFile.mkdir()) {
                            this.writeError("Could not create directory: " + targetFile.getName());
                        }
                        if (this.indicator != null) {
                            this.indicator.startDir(targetFile.getAbsolutePath());
                        }
                        this.readFromRemote(targetFile);
                        if (this.indicator != null) {
                            this.indicator.endDir();
                            continue;
                        }
                        continue;
                    }
                    default: {
                        this.writeError("Unexpected cmd: " + cmd);
                        throw new IOException("Unexpected cmd: " + cmd);
                    }
                }
            }
        }
    }
    
    void parseCommand(final String cmd, final String[] cmdParts) throws IOException {
        final int l = cmd.indexOf(32);
        final int r = cmd.indexOf(32, l + 1);
        if (l == -1 || r == -1) {
            this.writeError("Syntax error in cmd");
            throw new IOException("Syntax error in cmd");
        }
        cmdParts[0] = cmd.substring(1, l);
        cmdParts[1] = cmd.substring(l + 1, r);
        cmdParts[2] = cmd.substring(r + 1);
    }
    
    void readResponse(final String where) throws IOException {
        final int r = this.readByte();
        if (r == 0) {
            return;
        }
        final String errMsg = this.readString();
        if (r == 2) {
            throw new IOException(errMsg);
        }
        this.alert(errMsg);
    }
    
    void writeError(final String reason) throws IOException {
        this.writeByte(1);
        this.writeString(reason);
        this.alert(reason);
    }
    
    int readByte() throws IOException {
        return this.inTop.read();
    }
    
    String readString() throws IOException {
        final byte[] buf = new byte[2048];
        int i = 0;
        int ch;
        while ((ch = this.readByte()) != 10 && ch >= 0) {
            buf[i++] = (byte)ch;
        }
        if (ch == -1) {
            throw new EOFException();
        }
        if (buf[0] == 10) {
            throw new IOException("Unexpected <NL>");
        }
        if (buf[0] != 2 && buf[0] != 1) {
            return new String(buf, 0, i);
        }
        final String errMsg = new String(buf, 1, i - 1);
        if (buf[0] == 2) {
            throw new IOException(errMsg);
        }
        this.alert(errMsg);
        return null;
    }
    
    void readFully(final FileOutputStream file, final int size) throws IOException {
        final byte[] buf = new byte[2048];
        int cnt = 0;
        while (cnt < size) {
            final int n = this.inTop.read(buf, 0, (size - cnt < 2048) ? (size - cnt) : 2048);
            if (n == -1) {
                this.alert("Premature EOF");
                throw new IOException("Premature EOF");
            }
            cnt += n;
            file.write(buf, 0, n);
            if (this.indicator == null) {
                continue;
            }
            this.indicator.progress(n);
        }
        file.close();
    }
    
    void writeByte(final int b) throws IOException {
        final byte[] buf = { (byte)b };
        this.client.stdinWriteString(buf);
    }
    
    void writeString(final String str) throws IOException {
        final byte[] buf = str.getBytes();
        this.client.stdinWriteString(buf);
    }
    
    void writeFully(final FileInputStream file, final int size) throws IOException {
        final byte[] buf = new byte[2048];
        int cnt = 0;
        while (cnt < size) {
            final int n = file.read(buf, 0, (size - cnt < 2048) ? (size - cnt) : 2048);
            if (n == -1) {
                throw new IOException("Premature EOF");
            }
            cnt += n;
            this.client.stdinWriteString(buf, 0, n);
            if (this.indicator != null) {
                this.indicator.progress(n);
            }
            Thread.yield();
        }
        file.close();
    }
    
    public void stdoutWriteString(final byte[] str) {
        try {
            this.inBottom.write(str);
        }
        catch (IOException e) {
            try {
                this.inBottom.close();
            }
            catch (IOException ex) {}
            this.alert("Error writing data to stdout-pipe");
        }
    }
    
    public void stderrWriteString(final byte[] str) {
        if (this.verbose) {
            this.alert("Remote warning/error: " + new String(str));
        }
    }
    
    public Terminal getTerminal() {
        return null;
    }
    
    public void print(final String str) {
    }
    
    public void println(final String str) {
    }
    
    public void serverConnect(final SSHChannelController controller, final Cipher sndCipher) {
    }
    
    public void serverDisconnect(final String reason) {
    }
    
    public boolean wantPTY() {
        return false;
    }
    
    public void open(final SSHClient client) {
        if (this.indicator != null) {
            this.indicator.connected(this.sshHost);
        }
    }
    
    public void disconnected(final SSHClient client, final boolean graceful) {
        try {
            this.inBottom.close();
        }
        catch (IOException ex) {}
    }
    
    public void alert(final String msg) {
        if (this.interactor != null) {
            this.interactor.alert(msg);
        }
    }
    
    public Socket getProxyConnection() throws IOException {
        if (this.proxyUser != null) {
            return this.proxyUser.getProxyConnection();
        }
        return null;
    }
    
    public SSHInteractor getInteractor() {
        return this.ourInteractAdapter;
    }
}
