// 
// Decompiled by Procyon v0.5.30
// 

package HTTPClient;

import java.net.ProtocolException;
import java.io.IOException;
import java.util.Hashtable;

class AuthorizationModule implements HTTPClientModule, GlobalConstants
{
    private static Hashtable proxy_cntxt_list;
    private static Hashtable deferred_auth_list;
    private int auth_lst_idx;
    private int prxy_lst_idx;
    private int auth_scm_idx;
    private int prxy_scm_idx;
    private AuthorizationInfo auth_sent;
    private AuthorizationInfo prxy_sent;
    private boolean auth_from_4xx;
    private boolean prxy_from_4xx;
    private int num_tries;
    private Request saved_req;
    private Response saved_resp;
    
    AuthorizationModule() {
        this.auth_lst_idx = 0;
        this.prxy_lst_idx = 0;
        this.auth_scm_idx = 0;
        this.prxy_scm_idx = 0;
        this.auth_sent = null;
        this.prxy_sent = null;
        this.auth_from_4xx = false;
        this.prxy_from_4xx = false;
        this.num_tries = 0;
        this.saved_req = null;
        this.saved_resp = null;
    }
    
    public int requestHandler(final Request request, final Response[] array) throws IOException, AuthSchemeNotImplException {
        final HTTPConnection connection = request.getConnection();
        final AuthorizationHandler authHandler = AuthorizationInfo.getAuthHandler();
        final HttpOutputStream stream = request.getStream();
        if (stream != null && AuthorizationModule.deferred_auth_list.get(stream) != null) {
            this.copyFrom((AuthorizationModule)AuthorizationModule.deferred_auth_list.get(stream));
            request.copyFrom(this.saved_req);
            AuthorizationModule.deferred_auth_list.remove(stream);
            if (GlobalConstants.DebugMods) {
                Util.logLine("AuthM: Handling deferred auth challenge");
            }
            this.handle_auth_challenge(request, this.saved_resp);
            if (GlobalConstants.DebugMods) {
                if (this.auth_sent != null) {
                    Util.logLine("AuthM: Sending request with Authorization '" + this.auth_sent + "'");
                }
                else {
                    Util.logLine("AuthM: Sending request with Proxy-Authorization '" + this.prxy_sent + "'");
                }
            }
            return 1;
        }
        Label_0337: {
            if (connection.getProxyHost() != null && !this.prxy_from_4xx) {
                AuthorizationInfo fixupAuthInfo = Util.getList(AuthorizationModule.proxy_cntxt_list, request.getConnection().getContext()).get(connection.getProxyHost() + ":" + connection.getProxyPort());
                if (fixupAuthInfo != null) {
                    if (authHandler != null) {
                        try {
                            fixupAuthInfo = authHandler.fixupAuthInfo(fixupAuthInfo, request, null, null, true);
                        }
                        catch (AuthSchemeNotImplException ex) {
                            break Label_0337;
                        }
                        if (fixupAuthInfo == null) {
                            break Label_0337;
                        }
                    }
                    request.setHeaders(Util.setValue(request.getHeaders(), "Proxy-Authorization", fixupAuthInfo.toString()));
                    this.prxy_sent = fixupAuthInfo;
                    this.prxy_from_4xx = false;
                    if (GlobalConstants.DebugMods) {
                        Util.logLine("AuthM: Preemptively sending Proxy-Authorization '" + fixupAuthInfo + "'");
                    }
                }
            }
        }
        if (!this.auth_from_4xx) {
            AuthorizationInfo auth_sent = AuthorizationInfo.findBest(request);
            if (auth_sent != null) {
                if (authHandler != null) {
                    try {
                        auth_sent = authHandler.fixupAuthInfo(auth_sent, request, null, null, false);
                    }
                    catch (AuthSchemeNotImplException ex2) {
                        return 0;
                    }
                    if (auth_sent == null) {
                        return 0;
                    }
                }
                request.setHeaders(Util.setValue(request.getHeaders(), "Authorization", auth_sent.toString()));
                this.auth_sent = auth_sent;
                this.auth_from_4xx = false;
                if (GlobalConstants.DebugMods) {
                    Util.logLine("AuthM: Preemptively sending Authorization '" + auth_sent + "'");
                }
            }
        }
        return 0;
    }
    
    public void responsePhase1Handler(final Response response, final RoRequest roRequest) throws IOException {
        if (response.getStatusCode() != 401 && response.getStatusCode() != 407) {
            if (this.auth_sent != null && this.auth_from_4xx) {
                try {
                    AuthorizationInfo.getAuthorization(this.auth_sent, roRequest, response, false, false).addPath(roRequest.getRequestURI());
                }
                catch (AuthSchemeNotImplException ex) {}
            }
            this.num_tries = 0;
        }
        this.auth_from_4xx = false;
        this.prxy_from_4xx = false;
    }
    
    public int responsePhase2Handler(final Response response, final Request request) throws IOException, AuthSchemeNotImplException {
        final AuthorizationHandler authHandler = AuthorizationInfo.getAuthHandler();
        if (authHandler != null) {
            authHandler.handleAuthHeaders(response, request, this.auth_sent, this.prxy_sent);
        }
        final int statusCode = response.getStatusCode();
        switch (statusCode) {
            case 401:
            case 407: {
                ++this.num_tries;
                if (this.num_tries > 10) {
                    throw new ProtocolException("Bug in authorization handling: server refused the given info 10 times");
                }
                if (request.getStream() != null) {
                    this.saved_req = (Request)request.clone();
                    this.saved_resp = (Response)response.clone();
                    AuthorizationModule.deferred_auth_list.put(request.getStream(), this);
                    request.getStream().reset();
                    response.setRetryRequest(true);
                    if (GlobalConstants.DebugMods) {
                        Util.logLine("AuthM: Handling of status " + statusCode + " deferred because an output stream was" + " used");
                    }
                    return 10;
                }
                if (GlobalConstants.DebugMods) {
                    Util.logLine("AuthM: Handling status: " + statusCode + " " + response.getReasonLine());
                }
                this.handle_auth_challenge(request, response);
                if (this.auth_sent == null && this.prxy_sent == null) {
                    if (GlobalConstants.DebugMods) {
                        Util.logLine("AuthM: No Auth Info found - status " + statusCode + " not handled");
                    }
                    return 10;
                }
                try {
                    response.getInputStream().close();
                }
                catch (IOException ex) {}
                if (GlobalConstants.DebugMods) {
                    if (this.auth_sent != null) {
                        Util.logLine("AuthM: Resending request with Authorization '" + this.auth_sent + "'");
                    }
                    if (this.prxy_sent != null) {
                        Util.logLine("AuthM: Resending request with Proxy-Authorization '" + this.prxy_sent + "'");
                    }
                }
                return 13;
            }
            default: {
                return 10;
            }
        }
    }
    
    public void responsePhase3Handler(final Response response, final RoRequest roRequest) {
    }
    
    public void trailerHandler(final Response response, final RoRequest roRequest) throws IOException {
        final AuthorizationHandler authHandler = AuthorizationInfo.getAuthHandler();
        if (authHandler != null) {
            authHandler.handleAuthTrailers(response, roRequest, this.auth_sent, this.prxy_sent);
        }
    }
    
    private void handle_auth_challenge(final Request request, final Response response) throws AuthSchemeNotImplException, IOException {
        final int[] array = { this.auth_lst_idx, this.auth_scm_idx };
        this.auth_sent = this.setAuthHeaders(response.getHeader("WWW-Authenticate"), request, response, "Authorization", false, array, this.auth_sent);
        if (this.auth_sent != null) {
            this.auth_from_4xx = true;
        }
        this.auth_lst_idx = array[0];
        this.auth_scm_idx = array[1];
        array[0] = this.prxy_lst_idx;
        array[1] = this.prxy_scm_idx;
        this.prxy_sent = this.setAuthHeaders(response.getHeader("Proxy-Authenticate"), request, response, "Proxy-Authorization", true, array, this.prxy_sent);
        if (this.prxy_sent != null) {
            this.prxy_from_4xx = true;
        }
        this.prxy_lst_idx = array[0];
        this.prxy_scm_idx = array[1];
        if (this.prxy_sent != null) {
            final HTTPConnection connection = request.getConnection();
            Util.getList(AuthorizationModule.proxy_cntxt_list, connection.getContext()).put(connection.getProxyHost() + ":" + connection.getProxyPort(), this.prxy_sent);
        }
        if (this.auth_sent != null || this.prxy_sent != null || response.getHeader("WWW-Authenticate") != null || response.getHeader("Proxy-Authenticate") != null) {
            return;
        }
        if (response.getStatusCode() == 401) {
            throw new ProtocolException("Missing WWW-Authenticate header");
        }
        throw new ProtocolException("Missing Proxy-Authenticate header");
    }
    
    private AuthorizationInfo setAuthHeaders(final String s, final Request request, final RoResponse roResponse, final String s2, final boolean b, final int[] array, final AuthorizationInfo authorizationInfo) throws ProtocolException, AuthSchemeNotImplException {
        if (s == null) {
            return null;
        }
        final HTTPConnection connection = request.getConnection();
        AuthorizationInfo[] array2;
        if (b && connection.getProxyHost() != null) {
            array2 = AuthorizationInfo.parseAuthString(s, connection.getProxyHost(), connection.getProxyPort());
        }
        else {
            array2 = AuthorizationInfo.parseAuthString(s, connection.getHost(), connection.getPort());
        }
        if (GlobalConstants.DebugMods) {
            Util.logLine("AuthM: parsed " + array2.length + " challenges:");
            for (int i = 0; i < array2.length; ++i) {
                Util.logLine("AuthM: Challenge " + array2[i]);
            }
        }
        if (authorizationInfo != null && authorizationInfo.getScheme().equalsIgnoreCase("Basic")) {
            for (int j = 0; j < array2.length; ++j) {
                if (authorizationInfo.getRealm().equals(array2[j].getRealm()) && authorizationInfo.getScheme().equalsIgnoreCase(array2[j].getScheme())) {
                    AuthorizationInfo.removeAuthorization(authorizationInfo, connection.getContext());
                }
            }
        }
        AuthorizationInfo authorizationInfo2 = null;
        final AuthorizationHandler authHandler = AuthorizationInfo.getAuthHandler();
        if (authHandler != null) {
            array2 = authHandler.orderChallenges(array2, request, roResponse, b);
            if (array2 == null) {
                return null;
            }
        }
        while (authorizationInfo2 == null && array[0] != -1) {
            authorizationInfo2 = AuthorizationInfo.getAuthorization(array2[array[0]], request, roResponse, b, false);
            if (authHandler != null && authorizationInfo2 != null) {
                authorizationInfo2 = authHandler.fixupAuthInfo(authorizationInfo2, request, array2[array[0]], roResponse, b);
            }
            if (++array[0] == array2.length) {
                array[0] = -1;
            }
        }
        if (authorizationInfo2 == null) {
            int k = 0;
            while (k < array2.length) {
                try {
                    authorizationInfo2 = AuthorizationInfo.queryAuthHandler(array2[array[1]], request, roResponse, b);
                    break;
                }
                catch (AuthSchemeNotImplException ex) {
                    if (k == array2.length - 1) {
                        throw ex;
                    }
                    ++k;
                }
            }
            if (++array[1] == array2.length) {
                array[1] = 0;
            }
        }
        if (authorizationInfo2 == null) {
            return null;
        }
        request.setHeaders(Util.setValue(request.getHeaders(), s2, authorizationInfo2.toString()));
        return authorizationInfo2;
    }
    
    private void copyFrom(final AuthorizationModule authorizationModule) {
        this.auth_lst_idx = authorizationModule.auth_lst_idx;
        this.prxy_lst_idx = authorizationModule.prxy_lst_idx;
        this.auth_scm_idx = authorizationModule.auth_scm_idx;
        this.prxy_scm_idx = authorizationModule.prxy_scm_idx;
        this.auth_sent = authorizationModule.auth_sent;
        this.prxy_sent = authorizationModule.prxy_sent;
        this.auth_from_4xx = authorizationModule.auth_from_4xx;
        this.prxy_from_4xx = authorizationModule.prxy_from_4xx;
        this.num_tries = authorizationModule.num_tries;
        this.saved_req = authorizationModule.saved_req;
        this.saved_resp = authorizationModule.saved_resp;
    }
    
    static {
        AuthorizationModule.proxy_cntxt_list = new Hashtable();
        AuthorizationModule.deferred_auth_list = new Hashtable();
    }
}
