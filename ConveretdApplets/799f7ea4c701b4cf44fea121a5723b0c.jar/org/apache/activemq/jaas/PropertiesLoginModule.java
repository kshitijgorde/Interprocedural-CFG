// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.jaas;

import org.slf4j.LoggerFactory;
import java.util.Enumeration;
import java.util.Collection;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.Callback;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.util.Map;
import java.util.HashSet;
import java.io.File;
import java.security.Principal;
import java.util.Set;
import java.util.Properties;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.Subject;
import org.slf4j.Logger;
import javax.security.auth.spi.LoginModule;

public class PropertiesLoginModule implements LoginModule
{
    private static final String USER_FILE = "org.apache.activemq.jaas.properties.user";
    private static final String GROUP_FILE = "org.apache.activemq.jaas.properties.group";
    private static final Logger LOG;
    private Subject subject;
    private CallbackHandler callbackHandler;
    private boolean debug;
    private boolean reload;
    private static String usersFile;
    private static String groupsFile;
    private static Properties users;
    private static Properties groups;
    private String user;
    private Set<Principal> principals;
    private File baseDir;
    private boolean loginSucceeded;
    
    public PropertiesLoginModule() {
        this.reload = true;
        this.principals = new HashSet<Principal>();
    }
    
    @Override
    public void initialize(final Subject subject, final CallbackHandler callbackHandler, final Map sharedState, final Map options) {
        this.subject = subject;
        this.callbackHandler = callbackHandler;
        this.loginSucceeded = false;
        this.debug = "true".equalsIgnoreCase(options.get("debug"));
        if (options.get("reload") != null) {
            this.reload = "true".equalsIgnoreCase(options.get("reload"));
        }
        if (this.reload || PropertiesLoginModule.users == null) {
            this.setBaseDir();
            PropertiesLoginModule.usersFile = options.get("org.apache.activemq.jaas.properties.user") + "";
            final File uf = new File(this.baseDir, PropertiesLoginModule.usersFile);
            try {
                PropertiesLoginModule.users = new Properties();
                final FileInputStream in = new FileInputStream(uf);
                PropertiesLoginModule.users.load(in);
                in.close();
            }
            catch (IOException ioe) {
                PropertiesLoginModule.LOG.warn("Unable to load user properties file " + uf);
            }
            if (this.debug) {
                PropertiesLoginModule.LOG.debug("Using usersFile=" + PropertiesLoginModule.usersFile);
            }
        }
        if (this.reload || PropertiesLoginModule.groups == null) {
            this.setBaseDir();
            PropertiesLoginModule.groupsFile = options.get("org.apache.activemq.jaas.properties.group") + "";
            final File gf = new File(this.baseDir, PropertiesLoginModule.groupsFile);
            try {
                PropertiesLoginModule.groups = new Properties();
                final FileInputStream in = new FileInputStream(gf);
                PropertiesLoginModule.groups.load(in);
                in.close();
            }
            catch (IOException ioe) {
                PropertiesLoginModule.LOG.warn("Unable to load group properties file " + gf);
            }
            if (this.debug) {
                PropertiesLoginModule.LOG.debug("Using groupsFile=" + PropertiesLoginModule.groupsFile);
            }
        }
    }
    
    private void setBaseDir() {
        if (this.baseDir == null) {
            if (System.getProperty("java.security.auth.login.config") != null) {
                this.baseDir = new File(System.getProperty("java.security.auth.login.config")).getParentFile();
            }
            else {
                this.baseDir = new File(".");
            }
            if (this.debug) {
                PropertiesLoginModule.LOG.debug("Using basedir=" + this.baseDir);
            }
        }
    }
    
    @Override
    public boolean login() throws LoginException {
        final Callback[] callbacks = { new NameCallback("Username: "), new PasswordCallback("Password: ", false) };
        try {
            this.callbackHandler.handle(callbacks);
        }
        catch (IOException ioe) {
            throw new LoginException(ioe.getMessage());
        }
        catch (UnsupportedCallbackException uce) {
            throw new LoginException(uce.getMessage() + " not available to obtain information from user");
        }
        this.user = ((NameCallback)callbacks[0]).getName();
        char[] tmpPassword = ((PasswordCallback)callbacks[1]).getPassword();
        if (tmpPassword == null) {
            tmpPassword = new char[0];
        }
        final String password = PropertiesLoginModule.users.getProperty(this.user);
        if (password == null) {
            throw new FailedLoginException("User does exist");
        }
        if (!password.equals(new String(tmpPassword))) {
            throw new FailedLoginException("Password does not match");
        }
        this.loginSucceeded = true;
        if (this.debug) {
            PropertiesLoginModule.LOG.debug("login " + this.user);
        }
        return this.loginSucceeded;
    }
    
    @Override
    public boolean commit() throws LoginException {
        final boolean result = this.loginSucceeded;
        if (result) {
            this.principals.add(new UserPrincipal(this.user));
            final Enumeration enumeration = PropertiesLoginModule.groups.keys();
            while (enumeration.hasMoreElements()) {
                final String name = enumeration.nextElement();
                final String[] userList = (PropertiesLoginModule.groups.getProperty(name) + "").split(",");
                for (int i = 0; i < userList.length; ++i) {
                    if (this.user.equals(userList[i])) {
                        this.principals.add(new GroupPrincipal(name));
                        break;
                    }
                }
            }
            this.subject.getPrincipals().addAll(this.principals);
        }
        this.clear();
        if (this.debug) {
            PropertiesLoginModule.LOG.debug("commit, result: " + result);
        }
        return result;
    }
    
    @Override
    public boolean abort() throws LoginException {
        this.clear();
        if (this.debug) {
            PropertiesLoginModule.LOG.debug("abort");
        }
        return true;
    }
    
    @Override
    public boolean logout() throws LoginException {
        this.subject.getPrincipals().removeAll(this.principals);
        this.principals.clear();
        this.clear();
        if (this.debug) {
            PropertiesLoginModule.LOG.debug("logout");
        }
        return true;
    }
    
    private void clear() {
        this.user = null;
        this.loginSucceeded = false;
    }
    
    static {
        LOG = LoggerFactory.getLogger(PropertiesLoginModule.class);
    }
}
