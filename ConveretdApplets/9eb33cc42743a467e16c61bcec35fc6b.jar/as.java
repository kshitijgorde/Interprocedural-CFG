import java.net.Socket;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.util.Enumeration;
import java.net.UnknownHostException;
import java.net.InetAddress;
import java.util.NoSuchElementException;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;

// 
// Decompiled by Procyon v0.5.30
// 

public final class as implements b_
{
    public static final Properties e5;
    public static final Hashtable hs;
    public static final String[][] e4;
    public String hr;
    public String hq;
    public an hp;
    public b5 e9;
    public bo c;
    public a ce;
    public boolean ho;
    public String hn;
    public String hm;
    public boolean hl;
    public boolean hk;
    public boolean hj;
    public boolean hi;
    public String hh;
    public Properties hg;
    public boolean cd;
    
    public as(final Properties properties) {
        this.hq = "known_hosts";
        this.cm(properties);
        this.ho = false;
        this.cd = false;
    }
    
    public as(final as as) {
        this(as.ce);
        this.hr = as.hr;
        this.hp = as.hp;
        this.hg = as.hg;
        this.hh = as.hh;
        this.hi = true;
    }
    
    public static as hl(final String s, final String s2) throws IOException {
        final as as = new as(new Properties());
        as.hf(s2);
        as.gx(s, false);
        return as;
    }
    
    public final void hk(final bo c) {
        this.c = c;
    }
    
    public final void e4(final b5 e9) {
        this.e9 = e9;
    }
    
    public final void hj(final boolean hk) {
        if (this.hr != null) {
            this.hk = hk;
        }
    }
    
    public final void hi(final boolean hl) {
        if (this.hr != null) {
            this.hl = hl;
        }
    }
    
    public final void hh(final boolean hj) {
        this.hj = hj;
    }
    
    public final boolean hg() {
        return this.hi;
    }
    
    public final void hf(final String hh) {
        if (hh != null) {
            this.hh = hh;
        }
    }
    
    public final boolean he() {
        return this.hh == null;
    }
    
    public final void hd(String string) {
        if (string == null || string.trim().length() == 0) {
            return;
        }
        if (string != null && !string.endsWith(File.separator)) {
            string = String.valueOf(string) + File.separator;
        }
        try {
            final File file = new File(string.substring(0, string.length() - 1));
            if (!file.exists()) {
                if (this.c.jd("MindTerm home directory: '" + string + "' does not exist, create it?", true)) {
                    try {
                        file.mkdir();
                    }
                    catch (Throwable t) {
                        this.c.h9("Could not create home directory, file operations disabled.");
                        string = null;
                    }
                }
                else {
                    this.c.jf("No home directory, file operations disabled.");
                    string = null;
                }
            }
        }
        catch (Throwable t2) {
            if (this.c != null && this.c.ja()) {
                this.c.jf("Can't access local file system, file operations disabled.");
            }
            string = null;
        }
        this.hr = string;
        if (this.hr == null) {
            this.hl = false;
            this.hk = false;
        }
        if (this.c != null) {
            this.c.je(this);
        }
    }
    
    public final String hc() {
        return this.hr;
    }
    
    public static boolean dv(final String s) {
        return as.hs.containsKey(s) || s.indexOf("local") == 0 || s.indexOf("remote") == 0;
    }
    
    public final String ce(final String s) {
        return this.ce.getProperty(s);
    }
    
    public final void ci(final String s, final String s2) throws IllegalArgumentException, NoSuchElementException {
        if (s2 == null) {
            return;
        }
        final boolean cd = !s2.equals(this.ce(s));
        this.hb(s, s2);
        if (this.ho) {
            this.ha(s, s2);
        }
        if (cd) {
            if (this.c != null) {
                this.c.je(this);
            }
            this.cd = cd;
        }
        ((Hashtable<String, String>)this.ce).put(s, s2);
    }
    
    public final void hb(final String s, String substring) throws IllegalArgumentException, NoSuchElementException {
        if (s.equals("cipher")) {
            if (ca.mp(substring) == 8) {
                throw new IllegalArgumentException("Cipher " + substring + " not supported");
            }
        }
        else if (s.equals("authtyp")) {
            ca.gj(substring);
        }
        else if (s.equals("x11fwd") || s.equals("prvport") || s.equals("forcpty") || s.equals("remfwd") || s.equals("idhost") || s.equals("portftp")) {
            if (!substring.equals("true") && !substring.equals("false")) {
                throw new IllegalArgumentException("Value for " + s + " must be 'true' or 'false'");
            }
        }
        else {
            Label_0409: {
                if (!s.equals("port") && !s.equals("proxyport") && !s.equals("mtu") && !s.equals("secrand")) {
                    if (!s.equals("alive")) {
                        break Label_0409;
                    }
                }
                try {
                    final int intValue = Integer.valueOf(substring);
                    if ((s.equals("port") || s.equals("proxyport")) && (intValue > 65535 || intValue < 0)) {
                        throw new IllegalArgumentException("Not a valid port number: " + substring);
                    }
                    if (s.equals("mtu") && intValue != 0 && (intValue > 262144 || intValue < 4096)) {
                        throw new IllegalArgumentException("Mtu must be between 4k and 256k");
                    }
                    if (s.equals("alive")) {
                        if (intValue < 0 || intValue > 600) {
                            throw new IllegalArgumentException("Alive interval must be 0-600");
                        }
                        return;
                    }
                    else {
                        if (s.equals("secrand") && (intValue < 0 || intValue > 2)) {
                            throw new IllegalArgumentException("Secrand must be 0-2");
                        }
                        return;
                    }
                }
                catch (NumberFormatException ex) {
                    throw new IllegalArgumentException("Value for " + s + " must be an integer");
                }
            }
            if (s.equals("server")) {
                if (this.e9 != null && this.e9.kp()) {
                    throw new IllegalArgumentException("Server can only be set while not connected");
                }
            }
            else {
                Label_0504: {
                    if (!s.equals("realsrv")) {
                        if (!s.equals("localhst")) {
                            break Label_0504;
                        }
                    }
                    try {
                        InetAddress.getByName(substring);
                        return;
                    }
                    catch (UnknownHostException ex2) {
                        throw new IllegalArgumentException(String.valueOf(s) + " address must be a legal/known host name");
                    }
                }
                if (s.equals("proxytype")) {
                    ca.mr(substring);
                }
                else {
                    Label_0620: {
                        if (!s.startsWith("local")) {
                            if (!s.startsWith("remote")) {
                                break Label_0620;
                            }
                        }
                        try {
                            if (substring.startsWith("/general/")) {
                                substring = substring.substring(9);
                            }
                            if (s.startsWith("local")) {
                                this.go(substring, false);
                                return;
                            }
                            this.gn(substring, false);
                            return;
                        }
                        catch (Exception ex3) {
                            throw new IllegalArgumentException("Not a valid port forward: " + s + " : " + substring);
                        }
                    }
                    if (!dv(s)) {
                        throw new NoSuchElementException("Unknown ssh property '" + s + "'");
                    }
                }
            }
        }
    }
    
    public final void ha(final String s, String s2) {
        if (s.equals("remfwd")) {
            try {
                bm.kl = new Boolean(s2);
            }
            catch (Throwable t) {}
        }
        else if (s.equals("portftp")) {
            this.e9.lh = new Boolean(s2);
            if (this.e9.lh && ar.f1("ftp") != null) {
                ar.f1("ftp").fz(this.e9);
            }
        }
        else if (s.equals("alive")) {
            this.e9.kq(Integer.valueOf(s2));
        }
        else if (s.equals("secrand")) {
            cd.mu = Integer.valueOf(s2);
        }
        else if (s.equals("realsrv")) {
            try {
                if (s2 != null && s2.length() > 0) {
                    this.e9.lm(InetAddress.getByName(s2));
                }
                else {
                    this.e9.lm(null);
                }
            }
            catch (UnknownHostException ex2) {}
        }
        else {
            if (s.equals("localhst")) {
                try {
                    this.e9.lk(s2);
                    return;
                }
                catch (UnknownHostException ex3) {
                    throw new IllegalArgumentException("localhost address must be a legal/known host name");
                }
            }
            if (s.startsWith("local")) {
                if (Integer.parseInt(s.substring(5)) > this.e9.lq.size()) {
                    throw new IllegalArgumentException("Port forwards must be given in unbroken sequence");
                }
                if (s2.startsWith("/general/")) {
                    s2 = s2.substring(9);
                }
                try {
                    this.go(s2, true);
                    return;
                }
                catch (IOException ex) {
                    throw new IllegalArgumentException("Error creating tunnel: " + ex.getMessage());
                }
            }
            if (s.startsWith("remote")) {
                try {
                    if (Integer.parseInt(s.substring(6)) > this.e9.lp.size()) {
                        throw new IllegalArgumentException("Port forwards must be given in unbroken sequence");
                    }
                    if (s2.startsWith("/general/")) {
                        s2 = s2.substring(9);
                    }
                    this.gn(s2, true);
                }
                catch (Exception ex4) {
                    throw new IllegalArgumentException("Not a valid port forward: " + s + " : " + s2);
                }
            }
        }
    }
    
    public final void cm(final Properties properties) throws IllegalArgumentException, NoSuchElementException {
        this.ce = new a(as.e5);
        this.g9(properties);
    }
    
    public final void g9(final Properties properties) throws IllegalArgumentException, NoSuchElementException {
        final Enumeration<?> propertyNames = properties.propertyNames();
        while (propertyNames.hasMoreElements()) {
            final String s = (String)propertyNames.nextElement();
            final String property = properties.getProperty(s);
            if (!dv(s)) {
                throw new NoSuchElementException("Unknown ssh property '" + s + "'");
            }
            ((Hashtable<String, String>)this.ce).put(s, property);
        }
    }
    
    public final Properties g8() {
        return this.hg;
    }
    
    public final void g7() {
        if (this.ho) {
            return;
        }
        final Enumeration<String> keys = as.hs.keys();
        this.ho = true;
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            final String property = this.ce.getProperty(s);
            if (property != null) {
                this.ha(s, property);
            }
        }
        String property2;
        for (int n = 0; (property2 = this.ce.getProperty("local" + n)) != null; ++n) {
            this.ha("local" + n, property2);
        }
        String property3;
        for (int n2 = 0; (property3 = this.ce.getProperty("remote" + n2)) != null; ++n2) {
            this.ha("remote" + n2, property3);
        }
    }
    
    public final void g6() {
        this.ho = false;
    }
    
    private final void g5(final String s) throws IOException {
        final h ev = this.ev();
        final Properties properties = (ev != null) ? ev.cl() : null;
        if (properties != null) {
            final Enumeration<String> keys = ((Hashtable<String, V>)properties).keys();
            while (keys.hasMoreElements()) {
                final String s2 = keys.nextElement();
                ((Hashtable<String, String>)this.ce).put(s2, properties.getProperty(s2));
            }
        }
        final FileOutputStream fileOutputStream = new FileOutputStream(s);
        if (this.hj) {
            if (this.hh == null) {
                this.hh = "";
            }
            this.ce.f(fileOutputStream, "MindTerm ssh settings", this.hh, ca.mm[3][0]);
        }
        else {
            final String property = this.ce.getProperty("password");
            final String property2 = this.ce.getProperty("prxpassword");
            final String property3 = this.ce.getProperty("tispassword");
            final String property4 = this.ce.getProperty("rsapassword");
            this.g3();
            this.ce.save(fileOutputStream, "MindTerm ssh settings");
            if (property != null) {
                ((Hashtable<String, String>)this.ce).put("password", property);
            }
            if (property2 != null) {
                ((Hashtable<String, String>)this.ce).put("prxpassword", property2);
            }
            if (property3 != null) {
                ((Hashtable<String, String>)this.ce).put("tispassword", property3);
            }
            if (property4 != null) {
                ((Hashtable<String, String>)this.ce).put("rsapassword", property4);
            }
        }
        fileOutputStream.close();
        this.cd = false;
        if (ev != null) {
            ev.cj(false);
        }
        this.c.je(this);
    }
    
    private final void g4(final String s, final boolean b) throws IOException {
        final h ev = this.ev();
        final FileInputStream fileInputStream = new FileInputStream(s);
        final byte[] array = new byte[fileInputStream.available()];
        fileInputStream.read(array);
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(array);
        fileInputStream.close();
        final a a = new a();
        Label_0197: {
            try {
                a.e(byteArrayInputStream, "");
            }
            catch (cj cj) {
                try {
                    byteArrayInputStream.reset();
                    a.e(byteArrayInputStream, this.hh);
                }
                catch (cj cj2) {
                    try {
                        if (b) {
                            byteArrayInputStream.reset();
                            a.e(byteArrayInputStream, this.hh = this.c.jb("File " + s + " password: "));
                            break Label_0197;
                        }
                        throw new cj("");
                    }
                    catch (cj cj3) {
                        this.g2();
                        throw new b4("Access denied for '" + s + "'");
                    }
                }
            }
        }
        this.hj = !a.g();
        final Properties properties = new Properties();
        final Properties hg = new Properties();
        final Enumeration<String> keys = ((Hashtable<String, V>)a).keys();
        while (keys.hasMoreElements()) {
            final String s2 = keys.nextElement();
            if (dv(s2)) {
                ((Hashtable<String, String>)properties).put(s2, a.getProperty(s2));
            }
            else if (r.dv(s2)) {
                ((Hashtable<String, String>)hg).put(s2, a.getProperty(s2));
            }
            else if (this.c != null) {
                this.c.jf("Unknown property '" + s2 + "' found in file: " + s);
            }
            else {
                System.out.println("Unknown property '" + s2 + "' found in file: " + s);
            }
        }
        if (this.e9 != null) {
            this.e9.g1();
        }
        this.g6();
        this.cm(properties);
        this.hg = hg;
        if (ev != null) {
            ev.cm(this.hg, false);
            ev.cj(false);
        }
        this.cd = false;
        if (this.c != null) {
            this.c.je(this);
        }
    }
    
    public final void g3() {
        this.ce.remove("password");
        this.ce.remove("tispassword");
        this.ce.remove("rsapassword");
        this.ce.remove("prxpassword");
    }
    
    public final void g2() {
        this.ci("server", "");
        this.hn = null;
        this.hm = null;
        if (this.c != null) {
            this.c.je(this);
        }
    }
    
    public final void g1() {
        if (this.e9 != null) {
            this.e9.g1();
        }
        for (int i = 0; i < 1024; ++i) {
            final String string = "local" + i;
            if (!this.ce.containsKey(string)) {
                break;
            }
            this.ce.remove(string);
        }
        for (int j = 0; j < 1024; ++j) {
            final String string2 = "remote" + j;
            if (!this.ce.containsKey(string2)) {
                break;
            }
            this.ce.remove(string2);
        }
    }
    
    public final boolean g0() {
        final boolean b = this.cd || (this.ev() != null && this.ev().ck());
        return !this.hg() && b && this.hr != null && this.hm != null;
    }
    
    public final void g_() throws IOException {
        if (this.hl) {
            this.gz();
        }
    }
    
    public final void gz() throws IOException {
        if (this.hn != null && this.g0()) {
            this.g5(this.hn);
        }
    }
    
    public final void gy(final String hn) throws IOException {
        this.cd = true;
        this.hn = hn;
        this.gz();
        this.hm = null;
    }
    
    public final void gx(final String hn, final boolean b) throws IOException {
        this.hm = null;
        this.g4(this.hn = hn, b);
        if (this.c != null) {
            this.c.je(this);
        }
    }
    
    public final void gw(final String hm) {
        if (this.hr == null) {
            return;
        }
        this.hm = hm;
        this.hn = String.valueOf(this.hr) + hm + ".mtp";
    }
    
    public final String gv() {
        return this.hm;
    }
    
    public final void gu(final String s, final boolean b) throws IOException {
        final String hm = this.hm;
        this.gw(s);
        if (hm == null || !hm.equals(s)) {
            this.g4(this.hn, b);
        }
    }
    
    public final String[] gt() {
        if (this.hr == null) {
            return null;
        }
        final File file = new File(this.hr.substring(0, this.hr.length() - 1));
        int n = 0;
        final String[] list = file.list();
        for (int i = 0; i < list.length; ++i) {
            if (!list[i].endsWith(".mtp")) {
                list[i] = null;
                ++n;
            }
        }
        if (n == list.length) {
            return null;
        }
        final String[] array = new String[list.length - n];
        int n2 = 0;
        for (int j = 0; j < list.length; ++j) {
            if (list[j] != null) {
                array[n2++] = list[j].substring(0, list[j].lastIndexOf(".mtp"));
            }
        }
        return array;
    }
    
    public final boolean gs(final String s) {
        final String[] gt = this.gt();
        boolean b = false;
        if (gt != null) {
            for (int i = 0; i < gt.length; ++i) {
                if (s.equals(gt[i])) {
                    b = true;
                    break;
                }
            }
        }
        return b;
    }
    
    public final boolean gr(final String s) {
        if (this.hr == null) {
            return false;
        }
        final File file = new File(s);
        return file.isFile() && file.exists();
    }
    
    public final h ev() {
        if (this.e9 == null || this.e9.km == null) {
            return null;
        }
        return this.e9.km.ev();
    }
    
    public final void gq(final int n, final boolean b) {
        final int size = this.e9.lq.size();
        this.ce.remove("local" + n);
        for (int i = n; i < size - 1; ++i) {
            ((Hashtable<String, Object>)this.ce).put("local" + n, ((Hashtable<K, Object>)this.ce).get("local" + (n + 1)));
            this.ce.remove("local" + n + 1);
        }
        this.cd = true;
        if (b) {
            final b1 b2 = this.e9.lq.elementAt(n);
            this.e9.li(b2.lc, b2.fe);
        }
        else {
            this.e9.lq.removeElementAt(n);
        }
    }
    
    public final void gp(final int n) {
        final int size = this.e9.lp.size();
        this.ce.remove("remote" + n);
        for (int i = n; i < size - 1; ++i) {
            ((Hashtable<String, Object>)this.ce).put("remote" + n, ((Hashtable<K, Object>)this.ce).get("remote" + (n + 1)));
            this.ce.remove("remote" + n + 1);
        }
        this.cd = true;
        this.e9.lp.removeElementAt(n);
    }
    
    public final void go(String substring, final boolean b) throws IllegalArgumentException, IOException {
        String substring2 = null;
        String substring3;
        if (substring.charAt(0) == '/') {
            final int lastIndex = substring.lastIndexOf(47);
            if (lastIndex == 0) {
                throw new IllegalArgumentException("Invalid port forward spec. " + substring);
            }
            substring3 = substring.substring(1, lastIndex);
            substring = substring.substring(lastIndex + 1);
        }
        else {
            substring3 = "general";
        }
        final int index = substring.indexOf(58);
        final int lastIndex2 = substring.lastIndexOf(58);
        if (index == lastIndex2) {
            throw new IllegalArgumentException("Invalid port forward spec. " + substring);
        }
        final int index2 = substring.indexOf(58, index + 1);
        int n;
        String s;
        if (index2 != lastIndex2) {
            substring2 = substring.substring(0, index);
            n = Integer.parseInt(substring.substring(index + 1, index2));
            s = substring.substring(index2 + 1, lastIndex2);
        }
        else {
            n = Integer.parseInt(substring.substring(0, index));
            s = substring.substring(index + 1, lastIndex2);
        }
        final int int1 = Integer.parseInt(substring.substring(lastIndex2 + 1));
        if (b) {
            if (substring2 == null) {
                this.e9.go(n, s, int1, substring3);
            }
            else {
                this.e9.go(substring2, n, s, int1, substring3);
            }
        }
    }
    
    public final void gn(String substring, final boolean b) throws IllegalArgumentException {
        String substring2;
        if (substring.charAt(0) == '/') {
            final int lastIndex = substring.lastIndexOf(47);
            if (lastIndex == 0) {
                throw new IllegalArgumentException("Invalid port forward spec.");
            }
            substring2 = substring.substring(1, lastIndex);
            substring = substring.substring(lastIndex + 1);
        }
        else {
            substring2 = "general";
        }
        final int index = substring.indexOf(58);
        final int lastIndex2 = substring.lastIndexOf(58);
        if (index == lastIndex2) {
            throw new IllegalArgumentException("Invalid port forward spec.");
        }
        final int int1 = Integer.parseInt(substring.substring(0, index));
        final String substring3 = substring.substring(index + 1, lastIndex2);
        final int int2 = Integer.parseInt(substring.substring(lastIndex2 + 1));
        if (b) {
            this.e9.gn(int1, substring3, int2, substring2);
        }
    }
    
    public final String gm(final b_ b_) throws IOException {
        String ce = this.ce("usrname");
        if (!this.c.jc() || ce == null || ce.equals("")) {
            final String ex = this.c.ex(String.valueOf(this.ce("server")) + " login: ", ce);
            if (!ex.equals(ce)) {
                this.g3();
                ce = ex;
            }
            this.ci("usrname", ce);
        }
        return ce;
    }
    
    public final String gl(final b_ b_) throws IOException {
        String s = this.ce("password");
        if (s == null) {
            s = this.c.jb(String.valueOf(this.ce("usrname")) + "@" + this.ce("server") + "'s password: ");
            this.ci("password", s);
        }
        return s;
    }
    
    public final String gk(final b_ b_, final String s) throws IOException {
        String s2 = this.ce("tispassword");
        if (s2 == null) {
            s2 = this.c.jb(s);
            this.ci("tispassword", s2);
        }
        return s2;
    }
    
    public final int[] gj(final b_ b_) {
        return ca.gj(this.ce("authtyp"));
    }
    
    public final int gi(final b_ b_) {
        return ca.mp(this.ce("cipher"));
    }
    
    public final an gh(final b_ b_) throws IOException {
        String s = this.ce("idfile");
        if (s.indexOf(File.separator) == -1) {
            s = String.valueOf(this.hr) + s;
        }
        return this.hp = new an(s);
    }
    
    public final String gg(final b_ b_) throws IOException {
        String s = this.ce("rsapassword");
        if (s == null) {
            s = this.c.jb("key file '" + this.hp.ft() + "' password: ");
            this.ci("rsapassword", s);
        }
        return s;
    }
    
    public final boolean gf(final ce ce) throws IOException {
        if (!Boolean.valueOf(this.ce("idhost"))) {
            return true;
        }
        String string = null;
        int fo = 0;
        am am = null;
        InputStream resourceAsStream = this.getClass().getResourceAsStream("/defaults/known_hosts.txt");
        try {
            boolean b = true;
            while (b) {
                if (resourceAsStream != null) {
                    string = "<resource>/defaults/known_hosts.txt";
                    if (this.c.ja()) {
                        this.c.jf("Found preinstalled 'known_hosts' file.");
                    }
                }
                else {
                    b = false;
                    if (this.hr == null) {
                        if (this.c.ja()) {
                            this.c.jf("File operations disabled, server identity can't be verified");
                        }
                        return true;
                    }
                    string = String.valueOf(this.hr) + this.hq;
                    final File file = new File(string);
                    if (!file.exists()) {
                        if (!this.c.jd("File '" + string + "' not found, create it?", true)) {
                            this.c.jf("Verification of server key disabled in this session.");
                            return true;
                        }
                        new FileOutputStream(file).close();
                    }
                    resourceAsStream = new FileInputStream(string);
                }
                am = new am(resourceAsStream, string, true);
                if ((fo = am.fo(ce.m2(), this.ce("server"))) == 0) {
                    return true;
                }
                if (b && !this.c.jd("Host was not found in preinstalled 'known_hosts' file! Continue anyway?", false)) {
                    return false;
                }
                resourceAsStream = null;
            }
            boolean jd;
            if (fo == 1) {
                if (this.c.ja()) {
                    this.c.jf("Host key not found from the list of known hosts.");
                }
                if (!this.c.jd("Do you want to add this host to your set of known hosts", true)) {
                    this.c.jf("Verification of server key disabled in this session.");
                    return true;
                }
                jd = true;
            }
            else {
                this.c.h9("WARNING: HOST IDENTIFICATION HAS CHANGED! IT IS POSSIBLE THAT SOMEONE IS DOING SOMETHING NASTY, ONLY PROCEED IF YOU KNOW WHAT YOU ARE DOING!");
                jd = this.c.jd("Do you want to replace the identification of this host?", false);
                am.fm(this.ce("server"));
            }
            if (!jd) {
                return false;
            }
            am.fn(this.ce("server"), null, ce.m3(), ce.m2());
            final File file2 = new File(String.valueOf(string) + ".tmp");
            new File(string).renameTo(file2);
            try {
                am.fq(string);
            }
            catch (IOException ex) {
                file2.renameTo(new File(string));
                throw ex;
            }
            file2.delete();
        }
        finally {
            try {
                resourceAsStream.close();
            }
            catch (Exception ex2) {}
        }
        return true;
    }
    
    public final String ge(final String s, final String s2) throws IOException {
        String s3 = this.ce("proxyuser");
        if (!this.c.jc() || s3 == null || s3.equals("")) {
            s3 = this.c.ex(String.valueOf(s) + ((s2 != null) ? (" '" + s2 + "'") : "") + " username: ", s3);
            this.ci("proxyuser", s3);
        }
        return s3;
    }
    
    public final String gd(final String s, final String s2) throws IOException {
        String s3 = this.ce("prxpassword");
        if (s3 == null) {
            s3 = this.c.jb(String.valueOf(s) + ((s2 != null) ? (" '" + s2 + "'") : "") + " password: ");
            this.ci("prxpassword", s3);
        }
        return s3;
    }
    
    public final String gb() throws IOException {
        String s = this.ce("server");
        if (!this.c.jc() || s == null || s.equals("")) {
            if (this.hm != null) {
                s = this.hm;
            }
            do {
                s = this.c.ex("SSH Server/Alias: ", s).trim();
            } while ("".equals(s));
            if (this.hk) {
                if (this.gs(s)) {
                    this.gu(s, true);
                }
                else if (this.gr(s)) {
                    this.gx(s, true);
                }
                else if (this.hr != null) {
                    String jb = "";
                    String trim;
                    do {
                        trim = this.c.ex("No settings file for " + s + " found.\n\rSave as alias: ", s).trim();
                        if (this.hj) {
                            jb = this.c.jb(String.valueOf(trim) + " file password: ");
                            if (jb.length() <= 0) {
                                continue;
                            }
                            this.hh = this.c.jb(String.valueOf(trim) + " password again: ");
                        }
                    } while ("".equals(trim) || (!jb.equals("") && !jb.equals(this.hh)));
                    this.gw(trim);
                    this.ci("server", s);
                    this.g3();
                    this.g1();
                    this.ce.remove("usrname");
                    this.cd = true;
                }
                s = this.ce("server");
            }
            else {
                this.ci("server", s);
            }
        }
        this.g7();
        return s;
    }
    
    public final int ga() {
        return Integer.valueOf(this.ce("port"));
    }
    
    public final Socket f9() throws IOException {
        final String ce = this.ce("proxytype");
        int mr;
        try {
            mr = ca.mr(ce);
        }
        catch (IllegalArgumentException ex) {
            throw new IOException(ex.getMessage());
        }
        if (mr == 0) {
            return null;
        }
        final String ce2 = this.ce("proxyhost");
        int intValue;
        try {
            intValue = Integer.valueOf(this.ce("proxyport"));
        }
        catch (Exception ex2) {
            intValue = -1;
        }
        if (ce2 == null || intValue == -1) {
            throw new IOException("When 'proxytype' is set, 'proxyhost' and 'proxyport' must also be set");
        }
        final String ce3 = this.ce("server");
        final int ga = this.ga();
        final String ce4 = this.ce("proxyproto");
        Socket socket = null;
        switch (mr) {
            case 1: {
                socket = ck.nn(ce3, ga, ce2, intValue, ce4, this, "MindTerm/$Name: rel1-2-1 $");
                break;
            }
            case 2: {
                socket = cm.nq(ce3, ga, ce2, intValue, this.ge("SOCKS4", null));
                break;
            }
            case 3: {
                socket = cm.np(ce3, ga, ce2, intValue, false, this);
                break;
            }
            case 4: {
                socket = cm.np(ce3, ga, ce2, intValue, true, this);
                break;
            }
        }
        return socket;
    }
    
    public final String f8() {
        return this.ce("display");
    }
    
    public final int f7() {
        return Integer.valueOf(this.ce("mtu"));
    }
    
    public final int f6() {
        return Integer.valueOf(this.ce("alive"));
    }
    
    public final boolean f5() {
        return Boolean.valueOf(this.ce("x11fwd"));
    }
    
    public final boolean f4() {
        return Boolean.valueOf(this.ce("prvport"));
    }
    
    public final boolean f3() {
        return Boolean.valueOf(this.ce("forcpty"));
    }
    
    public final bo f2() {
        return this.c;
    }
    
    static {
        e5 = new Properties();
        hs = new Hashtable();
        e4 = new String[][] { { "server", null, "name of server to connect to", "" }, { "realsrv", null, "real address of sshd if it is behind a firewall", "" }, { "localhst", "0.0.0.0", "address to use as localhost", "" }, { "port", String.valueOf(22), "port on server to connect to", "" }, { "proxytype", "none", "type of proxy server to connect through", ca.mq() }, { "proxyhost", null, "name of proxy server to connect through", "" }, { "proxyport", null, "port on proxy server to connect through", "" }, { "proxyuser", null, "username for authentication on proxy server", "" }, { "proxyproto", null, "protocol for proxy connection (e.g. 'http://')", "" }, { "usrname", null, "username to login as", "" }, { "password", null, "password for normal authentication", "" }, { "tispassword", null, "password for TIS authentication", "" }, { "rsapassword", null, "password for RSA authentication (key file)", "" }, { "prxpassword", null, "password for proxy authentication", "" }, { "cipher", ca.mm[3][1], "name of block cipher to use", "( " + ca.mm() + ")" }, { "authtyp", "passwd", "method of authentication", "( " + ca.mk() + ")" }, { "idfile", "identity", "name of file containing identity (rsa key)", "" }, { "display", "localhost:0", "local display definition (i.e. <host>:<screen>)", "" }, { "mtu", "0", "maximum packet size to use (0 means use default)", "(4096 - 256k)" }, { "escseq", "~$", "sequence of characters to type to enter local command shell", "" }, { "secrand", "0", "level of security in random seed (for generating session key)", "(0-2, 0=low and 2=high)" }, { "alive", "0", "Connection keep-alive interval in seconds (0 means none)", "(0-600)" }, { "x11fwd", "false", "indicates whether X11 display is forwarded or not", "(true/false)" }, { "prvport", "false", "indicates whether to use a privileged port or not (locally)", "(true/false)" }, { "forcpty", "true", "indicates whether to allocate a pty or not", "(true/false)" }, { "remfwd", "false", "indicates whether we allow remote connects to local forwards", "(true/false)" }, { "idhost", "true", "indicates whether to check host's host key in 'known_hosts'", "(true/false)" }, { "portftp", "false", "indicates whether to enable ftp 'PORT' command support", "(true/false)" } };
        for (int i = 0; i < as.e4.length; ++i) {
            final String s = as.e4[i][0];
            final String s2 = as.e4[i][1];
            as.hs.put(s, "");
            if (s2 != null) {
                ((Hashtable<String, String>)as.e5).put(s, s2);
            }
        }
    }
}
