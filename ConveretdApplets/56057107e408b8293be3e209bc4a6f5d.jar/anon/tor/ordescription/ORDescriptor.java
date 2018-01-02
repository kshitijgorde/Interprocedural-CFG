// 
// Decompiled by Procyon v0.5.30
// 

package anon.tor.ordescription;

import anon.crypto.IMyPublicKey;
import anon.crypto.MyRSASignature;
import anon.tor.util.Base16;
import org.bouncycastle.crypto.digests.SHA1Digest;
import anon.util.Base64;
import java.util.StringTokenizer;
import java.io.LineNumberReader;
import java.util.Vector;
import anon.crypto.MyRSAPublicKey;

public class ORDescriptor
{
    private String m_address;
    private String m_name;
    private String m_fingerprint;
    private boolean m_hibernate;
    private int m_port;
    private int m_portDir;
    private int m_uptime;
    private String m_strSoftware;
    private String m_published;
    private String m_hash;
    private ORAcl m_acl;
    private boolean m_bIsExitNode;
    private MyRSAPublicKey m_onionkey;
    private MyRSAPublicKey m_signingkey;
    private Vector family;
    
    public ORDescriptor(final String address, final String name, final int port, final String strSoftware) {
        this.m_address = address;
        this.m_name = name;
        this.m_port = port;
        this.m_portDir = -1;
        this.m_strSoftware = strSoftware;
        this.m_acl = new ORAcl();
        this.m_bIsExitNode = false;
        this.m_uptime = 0;
        this.m_hibernate = false;
        this.family = null;
    }
    
    public void setPublished(final String published) {
        this.m_published = published;
    }
    
    public String getPublished() {
        return this.m_published;
    }
    
    public void setFingerprint(final String fingerprint) {
        this.m_fingerprint = fingerprint;
    }
    
    public String getFingerprint() {
        return this.m_fingerprint;
    }
    
    public void setHash(final String hash) {
        this.m_hash = hash;
    }
    
    public String getHash() {
        return this.m_hash;
    }
    
    public void setUptime(final int uptime) {
        this.m_uptime = uptime;
    }
    
    public int getUptime() {
        return this.m_uptime;
    }
    
    public Vector getFamily() {
        return this.family;
    }
    
    public void setHibernate(final boolean hibernate) {
        this.m_hibernate = hibernate;
    }
    
    public boolean getHibernate() {
        return this.m_hibernate;
    }
    
    public void setExitNode(final boolean bIsExitNode) {
        this.m_bIsExitNode = bIsExitNode;
    }
    
    public void setFamily(final Vector family) {
        this.family = family;
    }
    
    public boolean isExitNode() {
        return this.m_bIsExitNode;
    }
    
    public void setAcl(final ORAcl acl) {
        this.m_acl = acl;
    }
    
    public ORAcl getAcl() {
        return this.m_acl;
    }
    
    public boolean setOnionKey(final byte[] array) {
        this.m_onionkey = MyRSAPublicKey.getInstance(array);
        return this.m_onionkey != null;
    }
    
    public MyRSAPublicKey getOnionKey() {
        return this.m_onionkey;
    }
    
    public boolean setSigningKey(final byte[] array) {
        this.m_signingkey = MyRSAPublicKey.getInstance(array);
        return this.m_signingkey != null;
    }
    
    public MyRSAPublicKey getSigningKey() {
        return this.m_signingkey;
    }
    
    public String getAddress() {
        return this.m_address;
    }
    
    public String getName() {
        return this.m_name;
    }
    
    public void setDirPort(final int portDir) {
        this.m_portDir = portDir;
    }
    
    public int getPort() {
        return this.m_port;
    }
    
    public int getDirPort() {
        return this.m_portDir;
    }
    
    public String getSoftware() {
        return this.m_strSoftware;
    }
    
    public boolean isSimilar(final Object o) {
        if (o != null && o instanceof ORDescriptor) {
            final ORDescriptor orDescriptor = (ORDescriptor)o;
            if (this.m_address.equals(orDescriptor.getAddress()) && this.m_name.equals(orDescriptor.getName()) && this.m_port == orDescriptor.getPort()) {
                return true;
            }
            if (orDescriptor.family != null && this.family != null && orDescriptor.family.contains(this.m_name) && this.family.contains(orDescriptor.getName())) {
                return true;
            }
        }
        return false;
    }
    
    public static ORDescriptor parse(final LineNumberReader lineNumberReader) {
        try {
            final StringBuffer sb = new StringBuffer();
            final String line = lineNumberReader.readLine();
            sb.append(line);
            sb.append("\n");
            boolean exitNode = false;
            if (line == null || !line.startsWith("router")) {
                return null;
            }
            final StringTokenizer stringTokenizer = new StringTokenizer(line);
            stringTokenizer.nextToken();
            final String nextToken = stringTokenizer.nextToken();
            final String nextToken2 = stringTokenizer.nextToken();
            final String nextToken3 = stringTokenizer.nextToken();
            stringTokenizer.nextToken();
            final String nextToken4 = stringTokenizer.nextToken();
            Vector<String> family = null;
            byte[] decode = null;
            byte[] decode2 = null;
            final ORAcl acl = new ORAcl();
            String substring = "";
            String substring2 = "";
            String string = "";
            boolean hibernate = false;
            while (true) {
                String s = lineNumberReader.readLine();
                if (s == null) {
                    return null;
                }
                sb.append(s);
                sb.append("\n");
                if (s == null) {
                    return null;
                }
                if (s.startsWith("opt ")) {
                    s = s.substring(4);
                }
                if (s.startsWith("platform")) {
                    substring = s.substring(9);
                }
                else if (s.startsWith("published")) {
                    substring2 = s.substring(10);
                }
                else if (s.startsWith("accept")) {
                    acl.add(s);
                    exitNode = true;
                }
                else if (s.startsWith("reject")) {
                    acl.add(s);
                }
                else if (s.startsWith("fingerprint")) {
                    final StringBuffer sb2 = new StringBuffer();
                    final StringTokenizer stringTokenizer2 = new StringTokenizer(s);
                    stringTokenizer2.nextToken();
                    while (stringTokenizer2.hasMoreTokens()) {
                        sb2.append(stringTokenizer2.nextToken());
                    }
                    string = sb2.toString();
                }
                else if (s.startsWith("hibernate")) {
                    try {
                        hibernate = (Integer.parseInt(s.substring(10)) == 1);
                    }
                    catch (Exception ex) {}
                }
                else if (s.startsWith("onion-key")) {
                    final StringBuffer sb3 = new StringBuffer();
                    final String line2 = lineNumberReader.readLine();
                    if (line2 == null) {
                        return null;
                    }
                    sb.append(line2);
                    sb.append("\n");
                    while (true) {
                        final String line3 = lineNumberReader.readLine();
                        if (line3 == null) {
                            return null;
                        }
                        sb.append(line3);
                        sb.append("\n");
                        if (line3.startsWith("-----END")) {
                            decode = Base64.decode(sb3.toString());
                            break;
                        }
                        sb3.append(line3);
                    }
                }
                else if (s.startsWith("signing-key")) {
                    final StringBuffer sb4 = new StringBuffer();
                    final String line4 = lineNumberReader.readLine();
                    if (line4 == null) {
                        return null;
                    }
                    sb.append(line4);
                    sb.append("\n");
                    while (true) {
                        final String line5 = lineNumberReader.readLine();
                        if (line5 == null) {
                            return null;
                        }
                        sb.append(line5);
                        sb.append("\n");
                        if (line5.startsWith("-----END")) {
                            decode2 = Base64.decode(sb4.toString());
                            break;
                        }
                        sb4.append(line5);
                    }
                }
                else if (s.startsWith("family")) {
                    final StringTokenizer stringTokenizer3 = new StringTokenizer(s);
                    stringTokenizer3.nextToken();
                    family = new Vector<String>();
                    while (stringTokenizer3.hasMoreTokens()) {
                        family.addElement(stringTokenizer3.nextToken());
                    }
                }
                else {
                    if (!s.startsWith("router-signature")) {
                        continue;
                    }
                    final StringBuffer sb5 = new StringBuffer();
                    if (lineNumberReader.readLine() == null) {
                        return null;
                    }
                    while (true) {
                        final String line6 = lineNumberReader.readLine();
                        if (line6 == null) {
                            return null;
                        }
                        if (line6.startsWith("-----END")) {
                            final ORDescriptor orDescriptor = new ORDescriptor(nextToken2, nextToken, Integer.parseInt(nextToken3), substring);
                            if (!orDescriptor.setOnionKey(decode) || !orDescriptor.setSigningKey(decode2)) {
                                return null;
                            }
                            orDescriptor.setAcl(acl);
                            orDescriptor.setExitNode(exitNode);
                            orDescriptor.setFamily(family);
                            orDescriptor.setPublished(substring2);
                            orDescriptor.setFingerprint(string);
                            orDescriptor.setHibernate(hibernate);
                            orDescriptor.setHash(calcHash(sb.toString()));
                            try {
                                orDescriptor.setDirPort(Integer.parseInt(nextToken4));
                            }
                            catch (Exception ex2) {}
                            return orDescriptor;
                        }
                        else {
                            sb5.append(line6);
                        }
                    }
                }
            }
        }
        catch (Throwable t) {
            t.printStackTrace();
            return null;
        }
    }
    
    public String toString() {
        return "ORRouter: " + this.m_name + " on " + this.m_address + ":" + this.m_port + " Software : " + this.m_strSoftware + " isExitNode:" + this.m_bIsExitNode;
    }
    
    private static String calcHash(final String s) {
        final SHA1Digest sha1Digest = new SHA1Digest();
        final byte[] bytes = s.getBytes();
        final byte[] array = new byte[sha1Digest.getDigestSize()];
        sha1Digest.update(bytes, 0, bytes.length);
        sha1Digest.doFinal(array, 0);
        return Base16.encode(array);
    }
    
    private static boolean checkSignature(final byte[] array, final byte[] array2, final byte[] array3) {
        try {
            final MyRSAPublicKey instance = MyRSAPublicKey.getInstance(array3);
            final MyRSASignature myRSASignature = new MyRSASignature();
            myRSASignature.initVerify(instance);
            return myRSASignature.verify(array, array2);
        }
        catch (Throwable t) {
            return false;
        }
    }
}
