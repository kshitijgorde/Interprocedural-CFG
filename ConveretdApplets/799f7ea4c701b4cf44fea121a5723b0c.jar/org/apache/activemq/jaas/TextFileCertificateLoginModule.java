// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.jaas;

import java.util.Hashtable;
import java.util.HashSet;
import java.util.Set;
import java.util.Enumeration;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.util.Properties;
import javax.security.auth.login.LoginException;
import java.security.cert.X509Certificate;
import java.util.Map;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.Subject;
import java.io.File;

public class TextFileCertificateLoginModule extends CertificateLoginModule
{
    private static final String USER_FILE = "org.apache.activemq.jaas.textfiledn.user";
    private static final String GROUP_FILE = "org.apache.activemq.jaas.textfiledn.group";
    private File baseDir;
    private String usersFilePathname;
    private String groupsFilePathname;
    
    @Override
    public void initialize(final Subject subject, final CallbackHandler callbackHandler, final Map sharedState, final Map options) {
        super.initialize(subject, callbackHandler, sharedState, options);
        if (System.getProperty("java.security.auth.login.config") != null) {
            this.baseDir = new File(System.getProperty("java.security.auth.login.config")).getParentFile();
        }
        else {
            this.baseDir = new File(".");
        }
        this.usersFilePathname = options.get("org.apache.activemq.jaas.textfiledn.user") + "";
        this.groupsFilePathname = options.get("org.apache.activemq.jaas.textfiledn.group") + "";
    }
    
    @Override
    protected String getUserNameForCertificates(final X509Certificate[] certs) throws LoginException {
        if (certs == null) {
            throw new LoginException("Client certificates not found. Cannot authenticate.");
        }
        final File usersFile = new File(this.baseDir, this.usersFilePathname);
        final Properties users = new Properties();
        try {
            final FileInputStream in = new FileInputStream(usersFile);
            users.load(in);
            in.close();
        }
        catch (IOException ioe) {
            throw new LoginException("Unable to load user properties file " + usersFile);
        }
        final String dn = this.getDistinguishedName(certs);
        final Enumeration<Object> keys = ((Hashtable<Object, V>)users).keys();
        final Enumeration vals = users.elements();
        while (vals.hasMoreElements()) {
            if (vals.nextElement().equals(dn)) {
                return keys.nextElement();
            }
            keys.nextElement();
        }
        return null;
    }
    
    @Override
    protected Set<String> getUserGroups(final String username) throws LoginException {
        final File groupsFile = new File(this.baseDir, this.groupsFilePathname);
        final Properties groups = new Properties();
        try {
            final FileInputStream in = new FileInputStream(groupsFile);
            groups.load(in);
            in.close();
        }
        catch (IOException ioe) {
            throw new LoginException("Unable to load group properties file " + groupsFile);
        }
        final Set<String> userGroups = new HashSet<String>();
        final Enumeration enumeration = groups.keys();
        while (enumeration.hasMoreElements()) {
            final String groupName = enumeration.nextElement();
            final String[] userList = (groups.getProperty(groupName) + "").split(",");
            for (int i = 0; i < userList.length; ++i) {
                if (username.equals(userList[i])) {
                    userGroups.add(groupName);
                    break;
                }
            }
        }
        return userGroups;
    }
}
