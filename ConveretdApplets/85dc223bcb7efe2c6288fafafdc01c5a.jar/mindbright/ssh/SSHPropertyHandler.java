// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.ssh;

import mindbright.net.SocksProxySocket;
import mindbright.net.WebProxyTunnelSocket;
import java.net.Socket;
import mindbright.security.RSAPublicKey;
import mindbright.terminal.TerminalDefProps;
import mindbright.security.AccessDeniedException;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import mindbright.terminal.Terminal;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.util.Enumeration;
import mindbright.security.SecureRandom;
import java.net.UnknownHostException;
import java.net.InetAddress;
import java.util.NoSuchElementException;
import java.io.File;
import java.io.IOException;
import mindbright.util.EncryptedProperties;
import java.util.Hashtable;
import java.util.Properties;
import mindbright.net.ProxyAuthenticator;

public final class SSHPropertyHandler implements SSHClientUser, SSHAuthenticator, ProxyAuthenticator
{
    public static final int PROP_NAME = 0;
    public static final int PROP_VALUE = 1;
    public static final int PROP_DESC = 2;
    public static final int PROP_ALLOWED = 3;
    public static final String PROPS_FILE_EXT = ".mtp";
    public static final String GLOB_PROPS_FILE = "mindterm.mtp";
    public static final String DEF_IDFILE = "identity";
    public static final Properties defaultProperties;
    public static final Hashtable defaultPropertyNames;
    public static final String[][] defaultPropDesc;
    String sshHomeDir;
    String knownHosts;
    SSHRSAKeyFile keyFile;
    SSHClient client;
    SSHInteractor interactor;
    EncryptedProperties props;
    boolean activeProps;
    protected String currentPropsFile;
    protected String currentAlias;
    boolean autoSaveProps;
    boolean autoLoadProps;
    boolean savePasswords;
    boolean readonly;
    private String propertyPassword;
    public Properties initTermProps;
    protected boolean propsChanged;
    
    public SSHPropertyHandler(final Properties initProps) {
        this.knownHosts = "known_hosts";
        this.setProperties(initProps);
        this.activeProps = false;
        this.propsChanged = false;
    }
    
    public SSHPropertyHandler(final SSHPropertyHandler clone) {
        this(clone.props);
        this.sshHomeDir = clone.sshHomeDir;
        this.keyFile = clone.keyFile;
        this.initTermProps = clone.initTermProps;
        this.propertyPassword = clone.propertyPassword;
        this.readonly = true;
    }
    
    public static SSHPropertyHandler fromFile(final String fileName, final String password) throws IOException {
        final SSHPropertyHandler fileProps = new SSHPropertyHandler(new Properties());
        fileProps.setPropertyPassword(password);
        fileProps.loadAbsoluteFile(fileName, false);
        return fileProps;
    }
    
    public void setInteractor(final SSHInteractor interactor) {
        this.interactor = interactor;
    }
    
    public void setClient(final SSHClient client) {
        this.client = client;
    }
    
    public void setAutoLoadProps(final boolean value) {
        if (this.sshHomeDir != null) {
            this.autoLoadProps = value;
        }
    }
    
    public void setAutoSaveProps(final boolean value) {
        if (this.sshHomeDir != null) {
            this.autoSaveProps = value;
        }
    }
    
    public void setSavePasswords(final boolean value) {
        this.savePasswords = value;
    }
    
    public void setReadOnly(final boolean value) {
        this.readonly = value;
    }
    
    public boolean isReadOnly() {
        return this.readonly;
    }
    
    public void setPropertyPassword(final String password) {
        if (password != null) {
            this.propertyPassword = password;
        }
    }
    
    public boolean emptyPropertyPassword() {
        return this.propertyPassword == null;
    }
    
    public void setSSHHomeDir(String sshHomeDir) {
        if (sshHomeDir == null || sshHomeDir.trim().length() == 0) {
            return;
        }
        if (sshHomeDir != null && !sshHomeDir.endsWith(File.separator)) {
            sshHomeDir += File.separator;
        }
        try {
            final File sshDir = new File(sshHomeDir.substring(0, sshHomeDir.length() - 1));
            if (!sshDir.exists()) {
                if (this.interactor.askConfirmation("MindTerm home directory: '" + sshHomeDir + "' does not exist, create it?", true)) {
                    try {
                        sshDir.mkdir();
                    }
                    catch (Throwable t) {
                        this.interactor.alert("Could not create home directory, file operations disabled.");
                        sshHomeDir = null;
                    }
                }
                else {
                    this.interactor.report("No home directory, file operations disabled.");
                    sshHomeDir = null;
                }
            }
        }
        catch (Throwable t2) {
            if (this.interactor != null && this.interactor.isVerbose()) {
                this.interactor.report("Can't access local file system, file operations disabled.");
            }
            sshHomeDir = null;
        }
        this.sshHomeDir = sshHomeDir;
        if (this.sshHomeDir == null) {
            this.autoSaveProps = false;
            this.autoLoadProps = false;
        }
        if (this.interactor != null) {
            this.interactor.propsStateChanged(this);
        }
    }
    
    public String getSSHHomeDir() {
        return this.sshHomeDir;
    }
    
    public static boolean isProperty(final String key) {
        return SSHPropertyHandler.defaultPropertyNames.containsKey(key) || key.indexOf("local") == 0 || key.indexOf("remote") == 0;
    }
    
    public String getProperty(final String key) {
        return this.props.getProperty(key);
    }
    
    public void setProperty(final String key, final String value) throws IllegalArgumentException, NoSuchElementException {
        if (value == null) {
            return;
        }
        final boolean equalProp = !value.equals(this.getProperty(key));
        this.validateProperty(key, value);
        if (this.activeProps) {
            this.activateProperty(key, value);
        }
        if (equalProp) {
            if (this.interactor != null) {
                this.interactor.propsStateChanged(this);
            }
            this.propsChanged = equalProp;
        }
        ((Hashtable<String, String>)this.props).put(key, value);
    }
    
    final void validateProperty(final String key, String value) throws IllegalArgumentException, NoSuchElementException {
        if (key.equals("cipher")) {
            if (SSH.getCipherType(value) == 8) {
                throw new IllegalArgumentException("Cipher " + value + " not supported");
            }
        }
        else if (key.equals("authtyp")) {
            SSH.getAuthTypes(value);
        }
        else if (key.equals("x11fwd") || key.equals("prvport") || key.equals("forcpty") || key.equals("remfwd") || key.equals("idhost") || key.equals("portftp")) {
            if (!value.equals("true") && !value.equals("false")) {
                throw new IllegalArgumentException("Value for " + key + " must be 'true' or 'false'");
            }
        }
        else {
            Label_0409: {
                if (!key.equals("port") && !key.equals("proxyport") && !key.equals("mtu") && !key.equals("secrand")) {
                    if (!key.equals("alive")) {
                        break Label_0409;
                    }
                }
                try {
                    final int val = Integer.valueOf(value);
                    if ((key.equals("port") || key.equals("proxyport")) && (val > 65535 || val < 0)) {
                        throw new IllegalArgumentException("Not a valid port number: " + value);
                    }
                    if (key.equals("mtu") && val != 0 && (val > 262144 || val < 4096)) {
                        throw new IllegalArgumentException("Mtu must be between 4k and 256k");
                    }
                    if (key.equals("alive")) {
                        if (val < 0 || val > 600) {
                            throw new IllegalArgumentException("Alive interval must be 0-600");
                        }
                    }
                    else if (key.equals("secrand") && (val < 0 || val > 2)) {
                        throw new IllegalArgumentException("Secrand must be 0-2");
                    }
                    return;
                }
                catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Value for " + key + " must be an integer");
                }
            }
            if (key.equals("server")) {
                if (this.client != null && this.client.isOpened()) {
                    throw new IllegalArgumentException("Server can only be set while not connected");
                }
            }
            else {
                Label_0499: {
                    if (!key.equals("realsrv")) {
                        if (!key.equals("localhst")) {
                            break Label_0499;
                        }
                    }
                    try {
                        InetAddress.getByName(value);
                        return;
                    }
                    catch (UnknownHostException e2) {
                        throw new IllegalArgumentException(key + " address must be a legal/known host name");
                    }
                }
                if (key.equals("proxytype")) {
                    SSH.getProxyType(value);
                }
                else {
                    Label_0614: {
                        if (!key.startsWith("local")) {
                            if (!key.startsWith("remote")) {
                                break Label_0614;
                            }
                        }
                        try {
                            if (value.startsWith("/general/")) {
                                value = value.substring(9);
                            }
                            if (key.startsWith("local")) {
                                this.addLocalPortForward(value, false);
                            }
                            else {
                                this.addRemotePortForward(value, false);
                            }
                            return;
                        }
                        catch (Exception e3) {
                            throw new IllegalArgumentException("Not a valid port forward: " + key + " : " + value);
                        }
                    }
                    if (!isProperty(key)) {
                        throw new NoSuchElementException("Unknown ssh property '" + key + "'");
                    }
                }
            }
        }
    }
    
    void activateProperty(final String key, String value) {
        if (key.equals("remfwd")) {
            try {
                SSHListenChannel.setAllowRemoteConnect(new Boolean(value));
            }
            catch (Throwable t) {}
        }
        else if (key.equals("portftp")) {
            this.client.havePORTFtp = new Boolean(value);
            if (this.client.havePORTFtp && SSHProtocolPlugin.getPlugin("ftp") != null) {
                SSHProtocolPlugin.getPlugin("ftp").initiate(this.client);
            }
        }
        else if (key.equals("alive")) {
            this.client.setAliveInterval(Integer.valueOf(value));
        }
        else if (key.equals("secrand")) {
            SecureRandom.secureLevel = Integer.valueOf(value);
        }
        else if (key.equals("realsrv")) {
            try {
                if (value != null && value.length() > 0) {
                    this.client.setServerRealAddr(InetAddress.getByName(value));
                }
                else {
                    this.client.setServerRealAddr(null);
                }
            }
            catch (UnknownHostException e2) {}
        }
        else {
            if (key.equals("localhst")) {
                try {
                    this.client.setLocalAddr(value);
                    return;
                }
                catch (UnknownHostException e2) {
                    throw new IllegalArgumentException("localhost address must be a legal/known host name");
                }
            }
            if (key.startsWith("local")) {
                final int n = Integer.parseInt(key.substring(5));
                if (n > this.client.localForwards.size()) {
                    throw new IllegalArgumentException("Port forwards must be given in unbroken sequence");
                }
                if (value.startsWith("/general/")) {
                    value = value.substring(9);
                }
                try {
                    this.addLocalPortForward(value, true);
                }
                catch (IOException e) {
                    throw new IllegalArgumentException("Error creating tunnel: " + e.getMessage());
                }
            }
            else if (key.startsWith("remote")) {
                try {
                    final int n = Integer.parseInt(key.substring(6));
                    if (n > this.client.remoteForwards.size()) {
                        throw new IllegalArgumentException("Port forwards must be given in unbroken sequence");
                    }
                    if (value.startsWith("/general/")) {
                        value = value.substring(9);
                    }
                    this.addRemotePortForward(value, true);
                }
                catch (Exception e3) {
                    throw new IllegalArgumentException("Not a valid port forward: " + key + " : " + value);
                }
            }
        }
    }
    
    public void setProperties(final Properties newProps) throws IllegalArgumentException, NoSuchElementException {
        this.props = new EncryptedProperties(SSHPropertyHandler.defaultProperties);
        this.mergeProperties(newProps);
    }
    
    public Properties getProperties() {
        return this.props;
    }
    
    public void mergeProperties(final Properties newProps) throws IllegalArgumentException, NoSuchElementException {
        final Enumeration enum1 = newProps.propertyNames();
        while (enum1.hasMoreElements()) {
            final String name = enum1.nextElement();
            final String value = newProps.getProperty(name);
            if (!isProperty(name)) {
                throw new NoSuchElementException("Unknown ssh property '" + name + "'");
            }
            ((Hashtable<String, String>)this.props).put(name, value);
        }
    }
    
    public Properties getInitTerminalProperties() {
        return this.initTermProps;
    }
    
    public void activateProperties() {
        if (this.activeProps) {
            return;
        }
        final Enumeration enum1 = SSHPropertyHandler.defaultPropertyNames.keys();
        this.activeProps = true;
        while (enum1.hasMoreElements()) {
            final String name = enum1.nextElement();
            final String value = this.props.getProperty(name);
            if (value != null) {
                this.activateProperty(name, value);
            }
        }
        String value;
        for (int i = 0; (value = this.props.getProperty("local" + i)) != null; ++i) {
            this.activateProperty("local" + i, value);
        }
        for (int i = 0; (value = this.props.getProperty("remote" + i)) != null; ++i) {
            this.activateProperty("remote" + i, value);
        }
    }
    
    public void passivateProperties() {
        this.activeProps = false;
    }
    
    private void saveProperties(final String fname) throws IOException {
        final Terminal term = this.getTerminal();
        final Properties termProps = (term != null) ? term.getProperties() : null;
        if (termProps != null) {
            final Enumeration e = termProps.keys();
            while (e.hasMoreElements()) {
                final String key = e.nextElement();
                final String val = termProps.getProperty(key);
                ((Hashtable<String, String>)this.props).put(key, val);
            }
        }
        final FileOutputStream f = new FileOutputStream(fname);
        if (this.savePasswords) {
            if (this.propertyPassword == null) {
                this.propertyPassword = "";
            }
            this.props.save(f, "MindTerm ssh settings", this.propertyPassword, SSH.cipherClasses[3][0]);
        }
        else {
            final String stdPwd = this.props.getProperty("password");
            final String prxPwd = this.props.getProperty("prxpassword");
            final String tisPwd = this.props.getProperty("tispassword");
            final String rsaPwd = this.props.getProperty("rsapassword");
            this.clearPasswords();
            this.props.save(f, "MindTerm ssh settings");
            if (stdPwd != null) {
                ((Hashtable<String, String>)this.props).put("password", stdPwd);
            }
            if (prxPwd != null) {
                ((Hashtable<String, String>)this.props).put("prxpassword", prxPwd);
            }
            if (tisPwd != null) {
                ((Hashtable<String, String>)this.props).put("tispassword", tisPwd);
            }
            if (rsaPwd != null) {
                ((Hashtable<String, String>)this.props).put("rsapassword", rsaPwd);
            }
        }
        f.close();
        this.propsChanged = false;
        if (term != null) {
            term.setPropsChanged(false);
        }
        this.interactor.propsStateChanged(this);
    }
    
    private void loadProperties(final String fname, final boolean promptPwd) throws IOException {
        final Terminal term = this.getTerminal();
        final FileInputStream f = new FileInputStream(fname);
        final byte[] bytes = new byte[f.available()];
        f.read(bytes);
        final ByteArrayInputStream bytein = new ByteArrayInputStream(bytes);
        f.close();
        final EncryptedProperties loadProps = new EncryptedProperties();
        Label_0203: {
            try {
                loadProps.load(bytein, "");
            }
            catch (AccessDeniedException e) {
                try {
                    bytein.reset();
                    loadProps.load(bytein, this.propertyPassword);
                }
                catch (AccessDeniedException ee) {
                    try {
                        if (promptPwd) {
                            bytein.reset();
                            loadProps.load(bytein, this.propertyPassword = this.interactor.promptPassword("File " + fname + " password: "));
                            break Label_0203;
                        }
                        throw new AccessDeniedException("");
                    }
                    catch (AccessDeniedException eee) {
                        this.clearServerSetting();
                        throw new SSHClient.AuthFailException("Access denied for '" + fname + "'");
                    }
                }
            }
        }
        this.savePasswords = !loadProps.isNormalPropsFile();
        final Properties sshProps = new Properties();
        final Properties termProps = new Properties();
        final Enumeration enum1 = loadProps.keys();
        while (enum1.hasMoreElements()) {
            final String name = enum1.nextElement();
            if (isProperty(name)) {
                ((Hashtable<String, String>)sshProps).put(name, loadProps.getProperty(name));
            }
            else if (TerminalDefProps.isProperty(name)) {
                ((Hashtable<String, String>)termProps).put(name, loadProps.getProperty(name));
            }
            else if (this.interactor != null) {
                this.interactor.report("Unknown property '" + name + "' found in file: " + fname);
            }
            else {
                System.out.println("Unknown property '" + name + "' found in file: " + fname);
            }
        }
        if (this.client != null) {
            this.client.clearAllForwards();
        }
        this.passivateProperties();
        this.setProperties(sshProps);
        this.initTermProps = termProps;
        if (term != null) {
            term.setProperties(this.initTermProps, false);
            term.setPropsChanged(false);
        }
        this.propsChanged = false;
        if (this.interactor != null) {
            this.interactor.propsStateChanged(this);
        }
    }
    
    final void clearPasswords() {
        this.props.remove("password");
        this.props.remove("tispassword");
        this.props.remove("rsapassword");
        this.props.remove("prxpassword");
    }
    
    final void clearServerSetting() {
        this.setProperty("server", "eskimo.com");
        this.currentPropsFile = null;
        this.currentAlias = null;
        if (this.interactor != null) {
            this.interactor.propsStateChanged(this);
        }
    }
    
    final void clearAllForwards() {
        int i = 0;
        if (this.client != null) {
            this.client.clearAllForwards();
        }
        for (i = 0; i < 1024; ++i) {
            final String key = "local" + i;
            if (!this.props.containsKey(key)) {
                break;
            }
            this.props.remove(key);
        }
        for (i = 0; i < 1024; ++i) {
            final String key = "remote" + i;
            if (!this.props.containsKey(key)) {
                break;
            }
            this.props.remove(key);
        }
    }
    
    public boolean wantSave() {
        final boolean somePropsChanged = this.propsChanged || (this.getTerminal() != null && this.getTerminal().getPropsChanged());
        return !this.isReadOnly() && somePropsChanged && this.sshHomeDir != null && this.currentAlias != null;
    }
    
    public final void checkSave() throws IOException {
        if (this.autoSaveProps) {
            this.saveCurrentFile();
        }
    }
    
    public void saveCurrentFile() throws IOException {
        if (this.currentPropsFile != null && this.wantSave()) {
            this.saveProperties(this.currentPropsFile);
        }
    }
    
    public void saveAsCurrentFile(final String fileName) throws IOException {
        this.propsChanged = true;
        this.currentPropsFile = fileName;
        this.saveCurrentFile();
        this.currentAlias = null;
    }
    
    public void loadAbsoluteFile(final String fileName, final boolean promptPwd) throws IOException {
        this.currentAlias = null;
        this.loadProperties(this.currentPropsFile = fileName, promptPwd);
        if (this.interactor != null) {
            this.interactor.propsStateChanged(this);
        }
    }
    
    public void setAlias(final String alias) {
        if (this.sshHomeDir == null) {
            return;
        }
        this.currentAlias = alias;
        this.currentPropsFile = this.sshHomeDir + alias + ".mtp";
    }
    
    public String getAlias() {
        return this.currentAlias;
    }
    
    public void loadAliasFile(final String alias, final boolean promptPwd) throws IOException {
        final String oldAlias = this.currentAlias;
        this.setAlias(alias);
        if (oldAlias == null || !oldAlias.equals(alias)) {
            this.loadProperties(this.currentPropsFile, promptPwd);
        }
    }
    
    public String[] availableAliases() {
        if (this.sshHomeDir == null) {
            return null;
        }
        final File dir = new File(this.sshHomeDir.substring(0, this.sshHomeDir.length() - 1));
        int cnt = 0;
        final String[] list = dir.list();
        for (int i = 0; i < list.length; ++i) {
            if (!list[i].endsWith(".mtp")) {
                list[i] = null;
                ++cnt;
            }
        }
        if (cnt == list.length) {
            return null;
        }
        final String[] alist = new String[list.length - cnt];
        cnt = 0;
        for (int i = 0; i < list.length; ++i) {
            if (list[i] != null) {
                final int pi = list[i].lastIndexOf(".mtp");
                alist[cnt++] = list[i].substring(0, pi);
            }
        }
        return alist;
    }
    
    public boolean isAlias(final String alias) {
        final String[] aliases = this.availableAliases();
        boolean isAlias = false;
        if (aliases != null) {
            for (int i = 0; i < aliases.length; ++i) {
                if (alias.equals(aliases[i])) {
                    isAlias = true;
                    break;
                }
            }
        }
        return isAlias;
    }
    
    public boolean isAbsolutFile(final String fileName) {
        if (this.sshHomeDir == null) {
            return false;
        }
        final File file = new File(fileName);
        return file.isFile() && file.exists();
    }
    
    public Terminal getTerminal() {
        if (this.client == null || this.client.console == null) {
            return null;
        }
        final Terminal term = this.client.console.getTerminal();
        return term;
    }
    
    public void removeLocalTunnelAt(final int idx, final boolean kill) {
        final int sz = this.client.localForwards.size();
        this.props.remove("local" + idx);
        for (int i = idx; i < sz - 1; ++i) {
            ((Hashtable<String, Object>)this.props).put("local" + idx, ((Hashtable<K, Object>)this.props).get("local" + (idx + 1)));
            this.props.remove("local" + idx + 1);
        }
        this.propsChanged = true;
        if (kill) {
            final SSHClient.LocalForward fwd = this.client.localForwards.elementAt(idx);
            this.client.delLocalPortForward(fwd.localHost, fwd.localPort);
        }
        else {
            this.client.localForwards.removeElementAt(idx);
        }
    }
    
    public void removeRemoteTunnelAt(final int idx) {
        final int sz = this.client.remoteForwards.size();
        this.props.remove("remote" + idx);
        for (int i = idx; i < sz - 1; ++i) {
            ((Hashtable<String, Object>)this.props).put("remote" + idx, ((Hashtable<K, Object>)this.props).get("remote" + (idx + 1)));
            this.props.remove("remote" + idx + 1);
        }
        this.propsChanged = true;
        this.client.remoteForwards.removeElementAt(idx);
    }
    
    public void addLocalPortForward(String fwdSpec, final boolean commit) throws IllegalArgumentException, IOException {
        String localHost = null;
        String plugin;
        if (fwdSpec.charAt(0) == '/') {
            final int i = fwdSpec.lastIndexOf(47);
            if (i == 0) {
                throw new IllegalArgumentException("Invalid port forward spec. " + fwdSpec);
            }
            plugin = fwdSpec.substring(1, i);
            fwdSpec = fwdSpec.substring(i + 1);
        }
        else {
            plugin = "general";
        }
        final int d1 = fwdSpec.indexOf(58);
        final int d2 = fwdSpec.lastIndexOf(58);
        if (d1 == d2) {
            throw new IllegalArgumentException("Invalid port forward spec. " + fwdSpec);
        }
        final int d3 = fwdSpec.indexOf(58, d1 + 1);
        int localPort;
        String remoteHost;
        if (d3 != d2) {
            localHost = fwdSpec.substring(0, d1);
            localPort = Integer.parseInt(fwdSpec.substring(d1 + 1, d3));
            remoteHost = fwdSpec.substring(d3 + 1, d2);
        }
        else {
            localPort = Integer.parseInt(fwdSpec.substring(0, d1));
            remoteHost = fwdSpec.substring(d1 + 1, d2);
        }
        final String tmp = fwdSpec.substring(d2 + 1);
        final int remotePort = Integer.parseInt(tmp);
        if (commit) {
            if (localHost == null) {
                this.client.addLocalPortForward(localPort, remoteHost, remotePort, plugin);
            }
            else {
                this.client.addLocalPortForward(localHost, localPort, remoteHost, remotePort, plugin);
            }
        }
    }
    
    public void addRemotePortForward(String fwdSpec, final boolean commit) throws IllegalArgumentException {
        String plugin;
        if (fwdSpec.charAt(0) == '/') {
            final int i = fwdSpec.lastIndexOf(47);
            if (i == 0) {
                throw new IllegalArgumentException("Invalid port forward spec.");
            }
            plugin = fwdSpec.substring(1, i);
            fwdSpec = fwdSpec.substring(i + 1);
        }
        else {
            plugin = "general";
        }
        final int d1 = fwdSpec.indexOf(58);
        final int d2 = fwdSpec.lastIndexOf(58);
        if (d1 == d2) {
            throw new IllegalArgumentException("Invalid port forward spec.");
        }
        String tmp = fwdSpec.substring(0, d1);
        final int remotePort = Integer.parseInt(tmp);
        final String localHost = fwdSpec.substring(d1 + 1, d2);
        tmp = fwdSpec.substring(d2 + 1);
        final int localPort = Integer.parseInt(tmp);
        if (commit) {
            this.client.addRemotePortForward(remotePort, localHost, localPort, plugin);
        }
    }
    
    public String getUsername(final SSHClientUser origin) throws IOException {
        String username = this.getProperty("usrname");
        if (!this.interactor.quietPrompts() || username == null || username.equals("")) {
            final String username2 = this.interactor.promptLine(this.getProperty("server") + " login: ", username);
            if (!username2.equals(username)) {
                this.clearPasswords();
                username = username2;
            }
            this.setProperty("usrname", username);
        }
        return username;
    }
    
    public String getPassword(final SSHClientUser origin) throws IOException {
        String password = this.getProperty("password");
        if (password == null) {
            password = this.interactor.promptPassword(this.getProperty("usrname") + "@" + this.getProperty("server") + "'s password: ");
            this.setProperty("password", password);
        }
        return password;
    }
    
    public String getChallengeResponse(final SSHClientUser origin, final String challenge) throws IOException {
        String tisPassword = this.getProperty("tispassword");
        if (tisPassword == null) {
            tisPassword = this.interactor.promptPassword(challenge);
            this.setProperty("tispassword", tisPassword);
        }
        return tisPassword;
    }
    
    public int[] getAuthTypes(final SSHClientUser origin) {
        return SSH.getAuthTypes(this.getProperty("authtyp"));
    }
    
    public int getCipher(final SSHClientUser origin) {
        return SSH.getCipherType(this.getProperty("cipher"));
    }
    
    public SSHRSAKeyFile getIdentityFile(final SSHClientUser origin) throws IOException {
        String idFile = this.getProperty("idfile");
        if (idFile.indexOf(File.separator) == -1) {
            idFile = this.sshHomeDir + idFile;
        }
        return this.keyFile = new SSHRSAKeyFile(idFile);
    }
    
    public String getIdentityPassword(final SSHClientUser origin) throws IOException {
        String rsaPassword = this.getProperty("rsapassword");
        if (rsaPassword == null) {
            rsaPassword = this.interactor.promptPassword("key file '" + this.keyFile.getComment() + "' password: ");
            this.setProperty("rsapassword", rsaPassword);
        }
        return rsaPassword;
    }
    
    public boolean verifyKnownHosts(final RSAPublicKey hostPub) throws IOException {
        if (!Boolean.valueOf(this.getProperty("idhost"))) {
            return true;
        }
        String fileName = null;
        InputStream knownHostsIn = null;
        int hostCheck = 0;
        boolean confirm = true;
        SSHRSAPublicKeyFile file = null;
        knownHostsIn = this.getClass().getResourceAsStream("/defaults/known_hosts.txt");
        try {
            boolean tryingResource = true;
            while (tryingResource) {
                if (knownHostsIn != null) {
                    fileName = "<resource>/defaults/known_hosts.txt";
                    if (this.interactor.isVerbose()) {
                        this.interactor.report("Found preinstalled 'known_hosts' file.");
                    }
                }
                else {
                    tryingResource = false;
                    if (this.sshHomeDir == null) {
                        if (this.interactor.isVerbose()) {
                            this.interactor.report("File operations disabled, server identity can't be verified");
                        }
                        return true;
                    }
                    fileName = this.sshHomeDir + this.knownHosts;
                    final File tmpFile = new File(fileName);
                    if (!tmpFile.exists()) {
                        if (!this.interactor.askConfirmation("File '" + fileName + "' not found, create it?", true)) {
                            this.interactor.report("Verification of server key disabled in this session.");
                            return true;
                        }
                        final FileOutputStream f = new FileOutputStream(tmpFile);
                        f.close();
                    }
                    knownHostsIn = new FileInputStream(fileName);
                }
                file = new SSHRSAPublicKeyFile(knownHostsIn, fileName, true);
                if ((hostCheck = file.checkPublic(hostPub.getN(), this.getProperty("server"))) == 0) {
                    return true;
                }
                if (tryingResource && !this.interactor.askConfirmation("Host was not found in preinstalled 'known_hosts' file! Continue anyway?", false)) {
                    return false;
                }
                knownHostsIn = null;
            }
            if (hostCheck == 1) {
                if (this.interactor.isVerbose()) {
                    this.interactor.report("Host key not found from the list of known hosts.");
                }
                if (!this.interactor.askConfirmation("Do you want to add this host to your set of known hosts", true)) {
                    this.interactor.report("Verification of server key disabled in this session.");
                    return true;
                }
                confirm = true;
            }
            else {
                this.interactor.alert("WARNING: HOST IDENTIFICATION HAS CHANGED! IT IS POSSIBLE THAT SOMEONE IS DOING SOMETHING NASTY, ONLY PROCEED IF YOU KNOW WHAT YOU ARE DOING!");
                confirm = this.interactor.askConfirmation("Do you want to replace the identification of this host?", false);
                file.removePublic(this.getProperty("server"));
            }
            if (!confirm) {
                return false;
            }
            file.addPublic(this.getProperty("server"), null, hostPub.getE(), hostPub.getN());
            final File tmpFile = new File(fileName + ".tmp");
            File oldFile = new File(fileName);
            oldFile.renameTo(tmpFile);
            try {
                file.saveToFile(fileName);
            }
            catch (IOException e) {
                oldFile = new File(fileName);
                tmpFile.renameTo(oldFile);
                throw e;
            }
            tmpFile.delete();
        }
        finally {
            try {
                knownHostsIn.close();
            }
            catch (Exception ex) {}
        }
        return true;
    }
    
    public String getProxyUsername(final String type, final String challenge) throws IOException {
        String username = this.getProperty("proxyuser");
        if (!this.interactor.quietPrompts() || username == null || username.equals("")) {
            final String chStr = (challenge != null) ? (" '" + challenge + "'") : "";
            username = this.interactor.promptLine(type + chStr + " username: ", username);
            this.setProperty("proxyuser", username);
        }
        return username;
    }
    
    public String getProxyPassword(final String type, final String challenge) throws IOException {
        String prxPassword = this.getProperty("prxpassword");
        if (prxPassword == null) {
            final String chStr = (challenge != null) ? (" '" + challenge + "'") : "";
            prxPassword = this.interactor.promptPassword(type + chStr + " password: ");
            this.setProperty("prxpassword", prxPassword);
        }
        return prxPassword;
    }
    
    public String getSrvHost() throws IOException {
        String host = this.getProperty("server");
        if (!this.interactor.quietPrompts() || host == null || host.equals("")) {
            if (this.currentAlias != null) {
                host = this.currentAlias;
            }
            do {
                host = host.trim();
            } while ("".equals(host));
            if (this.autoLoadProps) {
                if (this.isAlias(host)) {
                    this.loadAliasFile(host, true);
                }
                else if (this.isAbsolutFile(host)) {
                    this.loadAbsoluteFile(host, true);
                }
                else if (this.sshHomeDir != null) {
                    String pwdChk = "";
                    String alias;
                    do {
                        alias = "eskimo.com";
                        if (this.savePasswords) {
                            pwdChk = this.interactor.promptPassword(alias + " file password: ");
                            if (pwdChk.length() <= 0) {
                                continue;
                            }
                            this.propertyPassword = this.interactor.promptPassword(alias + " password again: ");
                        }
                    } while ("".equals(alias) || (!pwdChk.equals("") && !pwdChk.equals(this.propertyPassword)));
                    this.setAlias(alias);
                    this.setProperty("server", host);
                    this.clearPasswords();
                    this.clearAllForwards();
                    this.props.remove("usrname");
                    this.propsChanged = true;
                }
                host = this.getProperty("server");
            }
            else {
                this.setProperty("server", host);
            }
        }
        this.activateProperties();
        return host;
    }
    
    public int getSrvPort() {
        return Integer.valueOf(this.getProperty("port"));
    }
    
    public Socket getProxyConnection() throws IOException {
        final String proxyType = this.getProperty("proxytype");
        int proxyTypeId = 0;
        try {
            proxyTypeId = SSH.getProxyType(proxyType);
        }
        catch (IllegalArgumentException e) {
            throw new IOException(e.getMessage());
        }
        if (proxyTypeId == 0) {
            return null;
        }
        final String prxHost = this.getProperty("proxyhost");
        int prxPort = -1;
        try {
            prxPort = Integer.valueOf(this.getProperty("proxyport"));
        }
        catch (Exception e2) {
            prxPort = -1;
        }
        if (prxHost == null || prxPort == -1) {
            throw new IOException("When 'proxytype' is set, 'proxyhost' and 'proxyport' must also be set");
        }
        final String sshHost = this.getProperty("server");
        final int sshPort = this.getSrvPort();
        final String prxProt = this.getProperty("proxyproto");
        Socket proxySocket = null;
        switch (proxyTypeId) {
            case 1: {
                proxySocket = WebProxyTunnelSocket.getProxy(sshHost, sshPort, prxHost, prxPort, prxProt, this, "MindTerm/$Name:  $");
                break;
            }
            case 2: {
                proxySocket = SocksProxySocket.getSocks4Proxy(sshHost, sshPort, prxHost, prxPort, this.getProxyUsername("SOCKS4", null));
                break;
            }
            case 3: {
                proxySocket = SocksProxySocket.getSocks5Proxy(sshHost, sshPort, prxHost, prxPort, false, this);
                break;
            }
            case 4: {
                proxySocket = SocksProxySocket.getSocks5Proxy(sshHost, sshPort, prxHost, prxPort, true, this);
                break;
            }
        }
        return proxySocket;
    }
    
    public String getDisplay() {
        return this.getProperty("display");
    }
    
    public int getMaxPacketSz() {
        return Integer.valueOf(this.getProperty("mtu"));
    }
    
    public int getAliveInterval() {
        return Integer.valueOf(this.getProperty("alive"));
    }
    
    public boolean wantX11Forward() {
        return Boolean.valueOf(this.getProperty("x11fwd"));
    }
    
    public boolean wantPrivileged() {
        return Boolean.valueOf(this.getProperty("prvport"));
    }
    
    public boolean wantPTY() {
        return Boolean.valueOf(this.getProperty("forcpty"));
    }
    
    public SSHInteractor getInteractor() {
        return this.interactor;
    }
    
    static {
        defaultProperties = new Properties();
        defaultPropertyNames = new Hashtable();
        defaultPropDesc = new String[][] { { "server", null, "name of server to connect to", "" }, { "realsrv", null, "real address of sshd if it is behind a firewall", "" }, { "localhst", "0.0.0.0", "address to use as localhost", "" }, { "port", String.valueOf(22), "port on server to connect to", "" }, { "proxytype", "none", "type of proxy server to connect through", SSH.listSupportedProxyTypes() }, { "proxyhost", null, "name of proxy server to connect through", "" }, { "proxyport", null, "port on proxy server to connect through", "" }, { "proxyuser", null, "username for authentication on proxy server", "" }, { "proxyproto", null, "protocol for proxy connection (e.g. 'http://')", "" }, { "usrname", null, "username to login as", "" }, { "password", null, "password for normal authentication", "" }, { "tispassword", null, "password for TIS authentication", "" }, { "rsapassword", null, "password for RSA authentication (key file)", "" }, { "prxpassword", null, "password for proxy authentication", "" }, { "cipher", SSH.getCipherName(3), "name of block cipher to use", "( " + SSH.listSupportedCiphers() + ")" }, { "authtyp", "passwd", "method of authentication", "( " + SSH.listSupportedAuthTypes() + ")" }, { "idfile", "identity", "name of file containing identity (rsa key)", "" }, { "display", "localhost:0", "local display definition (i.e. <host>:<screen>)", "" }, { "mtu", "0", "maximum packet size to use (0 means use default)", "(4096 - 256k)" }, { "escseq", "~$", "sequence of characters to type to enter local command shell", "" }, { "secrand", "0", "level of security in random seed (for generating session key)", "(0-2, 0=low and 2=high)" }, { "alive", "0", "Connection keep-alive interval in seconds (0 means none)", "(0-600)" }, { "x11fwd", "false", "indicates whether X11 display is forwarded or not", "(true/false)" }, { "prvport", "false", "indicates whether to use a privileged port or not (locally)", "(true/false)" }, { "forcpty", "true", "indicates whether to allocate a pty or not", "(true/false)" }, { "remfwd", "false", "indicates whether we allow remote connects to local forwards", "(true/false)" }, { "idhost", "true", "indicates whether to check host's host key in 'known_hosts'", "(true/false)" }, { "portftp", "false", "indicates whether to enable ftp 'PORT' command support", "(true/false)" } };
        for (int i = 0; i < SSHPropertyHandler.defaultPropDesc.length; ++i) {
            final String name = SSHPropertyHandler.defaultPropDesc[i][0];
            final String value = SSHPropertyHandler.defaultPropDesc[i][1];
            SSHPropertyHandler.defaultPropertyNames.put(name, "");
            if (value != null) {
                ((Hashtable<String, String>)SSHPropertyHandler.defaultProperties).put(name, value);
            }
        }
    }
}
