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

public class SSH2 extends CLI
{
    protected static final Log log;
    private static final JSch jSch_;
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
    
    public SSH2() {
        throw new Error("Unresolved compilation problems: \n\tThe import com.jcraft cannot be resolved\n\tThe import com.jcraft cannot be resolved\n\tThe import com.jcraft cannot be resolved\n\tThe import com.jcraft cannot be resolved\n\tThe import com.jcraft cannot be resolved\n\tThe import de cannot be resolved\n\tJSch cannot be resolved to a type\n\tJSch cannot be resolved to a type\n\tFilterPlugin cannot be resolved to a type\n\tSession cannot be resolved to a type\n\tChannel cannot be resolved to a type\n\tFilterPlugin cannot be resolved to a type\n\tSession cannot be resolved to a type\n\tJSch cannot be resolved to a type\n\tSession cannot be resolved to a type\n\tSession cannot be resolved to a type\n\tChannel cannot be resolved to a type\n\tSession cannot be resolved to a type\n\tChannel cannot be resolved to a type\n\tChannel cannot be resolved to a type\n\tChannel cannot be resolved to a type\n\tJSchException cannot be resolved to a type\n\tSession cannot be resolved to a type\n\tUserInfo cannot be resolved to a type\n");
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
    
    public class MyUserInfo
    {
        private final String user_;
        private final String password_;
        private final String passphrase_;
        
        public MyUserInfo(final SSH2 ssh2, final String s, final String s2, final String s3) {
            throw new Error("Unresolved compilation problems: \n\tThe import com.jcraft cannot be resolved\n\tThe import com.jcraft cannot be resolved\n\tThe import com.jcraft cannot be resolved\n\tThe import com.jcraft cannot be resolved\n\tThe import com.jcraft cannot be resolved\n\tThe import de cannot be resolved\n\tJSch cannot be resolved to a type\n\tJSch cannot be resolved to a type\n\tFilterPlugin cannot be resolved to a type\n\tSession cannot be resolved to a type\n\tChannel cannot be resolved to a type\n\tFilterPlugin cannot be resolved to a type\n\tSession cannot be resolved to a type\n\tJSch cannot be resolved to a type\n\tSession cannot be resolved to a type\n\tSession cannot be resolved to a type\n\tChannel cannot be resolved to a type\n\tSession cannot be resolved to a type\n\tChannel cannot be resolved to a type\n\tChannel cannot be resolved to a type\n\tChannel cannot be resolved to a type\n\tJSchException cannot be resolved to a type\n\tSession cannot be resolved to a type\n\tUserInfo cannot be resolved to a type\n");
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
