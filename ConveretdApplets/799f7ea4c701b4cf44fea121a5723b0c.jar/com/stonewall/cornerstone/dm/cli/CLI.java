// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dm.cli;

import java.util.regex.Matcher;
import java.util.Iterator;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.io.IOException;
import org.xmodel.log.Log;

public abstract class CLI
{
    static final Log log;
    protected String prompt;
    
    static {
        log = Log.getLog(CLI.class);
    }
    
    public CLI() {
        this.prompt = null;
    }
    
    public abstract int read(final byte[] p0) throws IOException;
    
    protected String loginRegex(final String usrPrompt, final String pwdPrompt, final String cmdPrompt) {
        return "(.|\\s)*(" + usrPrompt + "(\\s)$|" + pwdPrompt + "(\\s)$|" + cmdPrompt + "(.|\\s)*$)";
    }
    
    public String waitfor() throws IOException {
        final byte[] b = new byte[8192];
        int n = 8192;
        String str = "";
        while (n >= 0 && n == 8192) {
            try {
                Thread.sleep(1000L);
                n = this.read(b);
                if (n <= 0) {
                    continue;
                }
                final String s = new String(b, 0, n);
                str = String.valueOf(str) + s;
                CLI.log.debug("read (no wait): " + s);
            }
            catch (SocketTimeoutException ex) {
                CLI.log.debug(this, ex);
                break;
            }
            catch (SocketException ex2) {
                CLI.log.debug(this, ex2);
                break;
            }
            catch (InterruptedException ex3) {
                CLI.log.debug(this, ex3);
                break;
            }
        }
        return str;
    }
    
    public String waitfor(final List<String> regexes) throws IOException {
        final byte[] b = new byte[8192];
        int n = 8192;
        String str = "";
        final List<Pattern> patterns = new ArrayList<Pattern>();
        for (final String regex : regexes) {
            patterns.add(Pattern.compile(regex));
        }
        while (n >= 0) {
            try {
                n = this.read(b);
                if (n <= 0) {
                    continue;
                }
                final String s = new String(b, 0, n);
                str = String.valueOf(str) + s;
                for (final Pattern p : patterns) {
                    CLI.log.debug("read (" + p.pattern() + "): " + s);
                    final Matcher m = p.matcher(str);
                    if (m.find()) {
                        return str;
                    }
                    if (str.trim().contains(p.pattern())) {
                        return str;
                    }
                }
            }
            catch (SocketTimeoutException ex) {
                CLI.log.debug(this, ex);
                break;
            }
            catch (SocketException ex2) {
                CLI.log.debug(this, ex2);
                break;
            }
        }
        return str;
    }
    
    public void setPrompt(final String prompt) {
        this.prompt = prompt;
    }
    
    public String getPrompt() {
        return this.prompt;
    }
}
