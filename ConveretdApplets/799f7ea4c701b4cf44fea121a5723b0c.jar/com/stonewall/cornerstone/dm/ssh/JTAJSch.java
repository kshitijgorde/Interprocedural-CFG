// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dm.ssh;

import java.io.IOException;
import java.util.List;
import java.io.OutputStream;
import java.io.InputStream;
import org.xmodel.log.Log;
import com.stonewall.cornerstone.dm.cli.CLI;

public class JTAJSch extends CLI
{
    protected static final Log log;
    private static final String MENU = "JSch";
    private static final String MENU_HTTP_PROXY = "Http Proxy ...";
    private static final String MENU_SOCKS_PROXY = "Socks Proxy ...";
    private static final String MENU_X11 = "X11 ...";
    private static final String MENU_LOCAL_PORT = "Local Port ...";
    private static final String MENU_REMOTE_PORT = "Remote Port ...";
    private static final JSch jSch_;
    private String proxyHttpHost_;
    private int proxyHttpPort_;
    private String proxySOCKS5Host_;
    private int proxySOCKS5Port_;
    private String xHost_;
    private int xPort_;
    protected FilterPlugin source;
    protected SshIO handler;
    protected Session session_;
    private Channel channel_;
    private InputStream in_;
    private OutputStream out_;
    private String host_;
    private int port_;
    private static final int debug = 0;
    private volatile boolean auth;
    protected MyUserInfo userInfo_;
    private byte[] buffer;
    private int pos;
    
    public JTAJSch() {
        throw new Error("Unresolved compilation problems: \n\tThe import com.jcraft cannot be resolved\n\tThe import com.jcraft cannot be resolved\n\tThe import com.jcraft cannot be resolved\n\tThe import com.jcraft cannot be resolved\n\tThe import com.jcraft cannot be resolved\n\tThe import de cannot be resolved\n\tJSch cannot be resolved to a type\n\tJSch cannot be resolved to a type\n\tFilterPlugin cannot be resolved to a type\n\tSession cannot be resolved to a type\n\tChannel cannot be resolved to a type\n\tFilterPlugin cannot be resolved to a type\n\tFilterPlugin cannot be resolved to a type\n\tFilterPlugin cannot be resolved to a type\n\tFilterPlugin cannot be resolved to a type\n\tFilterPlugin cannot be resolved to a type\n\tSession cannot be resolved to a type\n\tJSch cannot be resolved to a type\n\tSession cannot be resolved to a type\n\tSession cannot be resolved to a type\n\tChannel cannot be resolved to a type\n\tSession cannot be resolved to a type\n\tChannel cannot be resolved to a type\n\tChannel cannot be resolved to a type\n\tChannel cannot be resolved to a type\n\tJSchException cannot be resolved to a type\n\tSession cannot be resolved to a type\n\tSession cannot be resolved to a type\n\tJSchException cannot be resolved to a type\n\tSession cannot be resolved to a type\n\tJSchException cannot be resolved to a type\n\tUserInfo cannot be resolved to a type\n");
    }
    
    public void setFilterSource(final FilterPlugin filterPlugin) {
        throw new Error("Unresolved compilation problems: \n\tFilterPlugin cannot be resolved to a type\n\tFilterPlugin cannot be resolved to a type\n");
    }
    
    public FilterPlugin getFilterSource() {
        throw new Error("Unresolved compilation problems: \n\tFilterPlugin cannot be resolved to a type\n\tFilterPlugin cannot be resolved to a type\n");
    }
    
    public String openSession(final String s, final String s2, final String s3, final int n) {
        throw new Error("Unresolved compilation problems: \n\tSession cannot be resolved to a type\n\tJSch cannot be resolved to a type\n\tSession cannot be resolved to a type\n\tSession cannot be resolved to a type\n\tChannel cannot be resolved to a type\n\tSession cannot be resolved to a type\n\tChannel cannot be resolved to a type\n\tChannel cannot be resolved to a type\n\tChannel cannot be resolved to a type\n\tJSchException cannot be resolved to a type\n");
    }
    
    public void closeSession() {
        throw new Error("Unresolved compilation problem: \n\tSession cannot be resolved to a type\n");
    }
    
    public String execute(final String s, final List<String> list) throws Exception {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    @Override
    public int read(final byte[] array) throws IOException {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    public void write(final byte[] array) throws IOException {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    private boolean isProxyHttp() {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    private String getProxyHttpHost() {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    private int getProxyHttpPort() {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    private void setProxyHttp(final String s, final int n) {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    private boolean isProxySOCKS5() {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    private String getProxySOCKS5Host() {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    private int getProxySOCKS5Port() {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    private void setProxySOCKS5(final String s, final int n) {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    private boolean isXForwarding() {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    private String getXHost() {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    private int getXPort() {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    private void setXForwarding(final String s, final int n) {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    private void setPortForwardingR(final int n, final String s, final int n2) {
        throw new Error("Unresolved compilation problems: \n\tSession cannot be resolved to a type\n\tJSchException cannot be resolved to a type\n");
    }
    
    private void setPortForwardingL(final int n, final String s, final int n2) {
        throw new Error("Unresolved compilation problems: \n\tSession cannot be resolved to a type\n\tJSchException cannot be resolved to a type\n");
    }
    
    public class MyUserInfo
    {
        private final String user_;
        private final String password_;
        private final String passphrase_;
        
        public MyUserInfo(final JTAJSch jtajSch, final String s, final String s2, final String s3) {
            throw new Error("Unresolved compilation problems: \n\tThe import com.jcraft cannot be resolved\n\tThe import com.jcraft cannot be resolved\n\tThe import com.jcraft cannot be resolved\n\tThe import com.jcraft cannot be resolved\n\tThe import com.jcraft cannot be resolved\n\tThe import de cannot be resolved\n\tJSch cannot be resolved to a type\n\tJSch cannot be resolved to a type\n\tFilterPlugin cannot be resolved to a type\n\tSession cannot be resolved to a type\n\tChannel cannot be resolved to a type\n\tFilterPlugin cannot be resolved to a type\n\tFilterPlugin cannot be resolved to a type\n\tFilterPlugin cannot be resolved to a type\n\tFilterPlugin cannot be resolved to a type\n\tFilterPlugin cannot be resolved to a type\n\tSession cannot be resolved to a type\n\tJSch cannot be resolved to a type\n\tSession cannot be resolved to a type\n\tSession cannot be resolved to a type\n\tChannel cannot be resolved to a type\n\tSession cannot be resolved to a type\n\tChannel cannot be resolved to a type\n\tChannel cannot be resolved to a type\n\tChannel cannot be resolved to a type\n\tJSchException cannot be resolved to a type\n\tSession cannot be resolved to a type\n\tSession cannot be resolved to a type\n\tJSchException cannot be resolved to a type\n\tSession cannot be resolved to a type\n\tJSchException cannot be resolved to a type\n\tUserInfo cannot be resolved to a type\n");
        }
        
        public String getUser() {
            throw new Error("Unresolved compilation problem: \n");
        }
        
        public String getPassphrase() {
            throw new Error("Unresolved compilation problem: \n");
        }
        
        public String getPassword() {
            throw new Error("Unresolved compilation problem: \n");
        }
        
        public boolean promptPassphrase(final String s) {
            throw new Error("Unresolved compilation problem: \n");
        }
        
        public boolean promptPassword(final String s) {
            throw new Error("Unresolved compilation problem: \n");
        }
        
        public boolean promptYesNo(final String s) {
            throw new Error("Unresolved compilation problem: \n");
        }
        
        public void showMessage(final String s) {
            throw new Error("Unresolved compilation problem: \n");
        }
    }
}
