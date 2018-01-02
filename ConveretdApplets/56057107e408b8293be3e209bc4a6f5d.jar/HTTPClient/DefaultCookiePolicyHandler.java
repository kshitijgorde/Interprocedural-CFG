// 
// Decompiled by Procyon v0.5.30
// 

package HTTPClient;

class DefaultCookiePolicyHandler implements CookiePolicyHandler
{
    private String[] accept_domains;
    private String[] reject_domains;
    private static CookiePrompter prompter;
    
    DefaultCookiePolicyHandler() {
        this.accept_domains = new String[0];
        this.reject_domains = new String[0];
        String property;
        try {
            property = System.getProperty("HTTPClient.cookies.hosts.accept");
        }
        catch (Exception ex) {
            property = null;
        }
        final String[] splitProperty = Util.splitProperty(property);
        for (int i = 0; i < splitProperty.length; ++i) {
            this.addAcceptDomain(splitProperty[i].toLowerCase());
        }
        String property2;
        try {
            property2 = System.getProperty("HTTPClient.cookies.hosts.reject");
        }
        catch (Exception ex2) {
            property2 = null;
        }
        final String[] splitProperty2 = Util.splitProperty(property2);
        for (int j = 0; j < splitProperty2.length; ++j) {
            this.addRejectDomain(splitProperty2[j].toLowerCase());
        }
    }
    
    public boolean acceptCookie(final Cookie cookie, final RoRequest roRequest, final RoResponse roResponse) {
        String s = roRequest.getConnection().getHost();
        if (s.indexOf(46) == -1) {
            s += ".local";
        }
        for (int i = 0; i < this.reject_domains.length; ++i) {
            if (this.reject_domains[i].length() == 0 || (this.reject_domains[i].charAt(0) == '.' && s.endsWith(this.reject_domains[i])) || (this.reject_domains[i].charAt(0) != '.' && s.equals(this.reject_domains[i]))) {
                return false;
            }
        }
        for (int j = 0; j < this.accept_domains.length; ++j) {
            if (this.accept_domains[j].length() == 0 || (this.accept_domains[j].charAt(0) == '.' && s.endsWith(this.accept_domains[j])) || (this.accept_domains[j].charAt(0) != '.' && s.equals(this.accept_domains[j]))) {
                return true;
            }
        }
        return !roRequest.allowUI() || DefaultCookiePolicyHandler.prompter == null || DefaultCookiePolicyHandler.prompter.accept(cookie, this, s);
    }
    
    public boolean sendCookie(final Cookie cookie, final RoRequest roRequest) {
        return true;
    }
    
    void addAcceptDomain(String string) {
        if (string.indexOf(46) == -1) {
            string += ".local";
        }
        for (int i = 0; i < this.accept_domains.length; ++i) {
            if (string.endsWith(this.accept_domains[i])) {
                return;
            }
            if (this.accept_domains[i].endsWith(string)) {
                this.accept_domains[i] = string;
                return;
            }
        }
        (this.accept_domains = Util.resizeArray(this.accept_domains, this.accept_domains.length + 1))[this.accept_domains.length - 1] = string;
    }
    
    void addRejectDomain(String string) {
        if (string.indexOf(46) == -1) {
            string += ".local";
        }
        for (int i = 0; i < this.reject_domains.length; ++i) {
            if (string.endsWith(this.reject_domains[i])) {
                return;
            }
            if (this.reject_domains[i].endsWith(string)) {
                this.reject_domains[i] = string;
                return;
            }
        }
        (this.reject_domains = Util.resizeArray(this.reject_domains, this.reject_domains.length + 1))[this.reject_domains.length - 1] = string;
    }
    
    static {
        DefaultCookiePolicyHandler.prompter = null;
    }
}
