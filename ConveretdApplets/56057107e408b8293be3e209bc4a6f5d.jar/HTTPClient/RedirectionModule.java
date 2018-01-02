// 
// Decompiled by Procyon v0.5.30
// 

package HTTPClient;

import java.net.ProtocolException;
import java.io.IOException;
import java.util.Hashtable;

class RedirectionModule implements HTTPClientModule, GlobalConstants
{
    private static Hashtable perm_redir_cntxt_list;
    private static Hashtable deferred_redir_list;
    private int level;
    private URI lastURI;
    private boolean new_con;
    private Request saved_req;
    
    RedirectionModule() {
        this.level = 0;
        this.lastURI = null;
        this.saved_req = null;
    }
    
    public int requestHandler(final Request request, final Response[] array) {
        final HTTPConnection connection = request.getConnection();
        final HttpOutputStream stream = request.getStream();
        if (stream != null && RedirectionModule.deferred_redir_list.get(stream) != null) {
            this.copyFrom((RedirectionModule)RedirectionModule.deferred_redir_list.get(stream));
            request.copyFrom(this.saved_req);
            RedirectionModule.deferred_redir_list.remove(stream);
            if (this.new_con) {
                return 5;
            }
            return 1;
        }
        else {
            URI uri;
            try {
                uri = new URI(connection.getProtocol(), connection.getHost(), connection.getPort(), request.getRequestURI());
            }
            catch (ParseException ex2) {
                uri = null;
            }
            final URI uri2;
            if ((uri2 = Util.getList(RedirectionModule.perm_redir_cntxt_list, request.getConnection().getContext()).get(uri)) == null) {
                return 0;
            }
            final String path = uri2.getPath();
            request.setRequestURI(path);
            try {
                this.lastURI = new URI(uri2, path);
            }
            catch (ParseException ex3) {}
            if (GlobalConstants.DebugMods) {
                Util.logLine("RdirM: matched request in permanent redirection list - redoing request to " + this.lastURI);
            }
            if (!this.sameServer(connection, uri2)) {
                HTTPConnection connection2;
                try {
                    connection2 = new HTTPConnection(uri2);
                }
                catch (ProtocolNotSuppException ex) {
                    throw new Error("HTTPClient Internal Error: unexpected exception '" + ex + "'");
                }
                connection2.setContext(request.getConnection().getContext());
                request.setConnection(connection2);
                return 5;
            }
            return 1;
        }
    }
    
    public void responsePhase1Handler(final Response response, final RoRequest roRequest) throws IOException {
        final int statusCode = response.getStatusCode();
        if ((statusCode < 301 || statusCode > 307 || statusCode == 304) && this.lastURI != null) {
            response.setEffectiveURI(this.lastURI);
        }
    }
    
    public int responsePhase2Handler(final Response response, final Request request) throws IOException {
        int statusCode = response.getStatusCode();
        switch (statusCode) {
            case 302: {
                if (GlobalConstants.DebugMods) {
                    Util.logLine("RdirM: Received status: " + statusCode + " " + response.getReasonLine() + " - treating as 303");
                }
                statusCode = 303;
            }
            case 301:
            case 303:
            case 307: {
                if (GlobalConstants.DebugMods) {
                    Util.logLine("RdirM: Handling status: " + statusCode + " " + response.getReasonLine());
                }
                if (!request.getMethod().equals("GET") && !request.getMethod().equals("HEAD") && statusCode != 303) {
                    if (GlobalConstants.DebugMods) {
                        Util.logLine("RdirM: not redirected because method is neither HEAD nor GET");
                    }
                    if (statusCode == 301 && response.getHeader("Location") != null) {
                        update_perm_redir_list(request, this.resLocHdr(response.getHeader("Location"), request));
                    }
                    response.setEffectiveURI(this.lastURI);
                    return 10;
                }
            }
            case 305:
            case 306: {
                if (GlobalConstants.DebugMods && (statusCode == 305 || statusCode == 306)) {
                    Util.logLine("RdirM: Handling status: " + statusCode + " " + response.getReasonLine());
                }
                if (statusCode == 305 && request.getConnection().getProxyHost() != null) {
                    if (GlobalConstants.DebugMods) {
                        Util.logLine("RdirM: 305 ignored because a proxy is already in use");
                    }
                    response.setEffectiveURI(this.lastURI);
                    return 10;
                }
                if (this.level == 15 || response.getHeader("Location") == null) {
                    if (GlobalConstants.DebugMods) {
                        if (this.level == 15) {
                            Util.logLine("RdirM: not redirected because there were too many levels of redirection");
                        }
                        else {
                            Util.logLine("RdirM: not redirected because no Location header was present");
                        }
                    }
                    response.setEffectiveURI(this.lastURI);
                    return 10;
                }
                ++this.level;
                final URI resLocHdr = this.resLocHdr(response.getHeader("Location"), request);
                this.new_con = false;
                HTTPConnection connection;
                String requestURI;
                if (statusCode == 305) {
                    connection = new HTTPConnection(request.getConnection().getProtocol(), request.getConnection().getHost(), request.getConnection().getPort());
                    connection.setCurrentProxy(resLocHdr.getHost(), resLocHdr.getPort());
                    connection.setContext(request.getConnection().getContext());
                    this.new_con = true;
                    requestURI = request.getRequestURI();
                    request.setMethod("GET");
                    request.setData(null);
                    request.setStream(null);
                }
                else {
                    if (statusCode == 306) {
                        return 10;
                    }
                    if (this.sameServer(request.getConnection(), resLocHdr)) {
                        connection = request.getConnection();
                        requestURI = resLocHdr.getPath();
                    }
                    else {
                        try {
                            connection = new HTTPConnection(resLocHdr);
                            requestURI = resLocHdr.getPath();
                        }
                        catch (ProtocolNotSuppException ex) {
                            if (request.getConnection().getProxyHost() == null || !resLocHdr.getScheme().equalsIgnoreCase("ftp")) {
                                return 10;
                            }
                            connection = new HTTPConnection("http", request.getConnection().getProxyHost(), request.getConnection().getProxyPort());
                            connection.setCurrentProxy(null, 0);
                            requestURI = resLocHdr.toExternalForm();
                        }
                        connection.setContext(request.getConnection().getContext());
                        this.new_con = true;
                    }
                    if (statusCode == 303 && !request.getMethod().equals("HEAD")) {
                        request.setMethod("GET");
                        request.setData(null);
                        request.setStream(null);
                    }
                    else {
                        if (request.getStream() != null) {
                            this.saved_req = (Request)request.clone();
                            RedirectionModule.deferred_redir_list.put(request.getStream(), this);
                            request.getStream().reset();
                            response.setRetryRequest(true);
                        }
                        if (statusCode == 301) {
                            try {
                                update_perm_redir_list(request, new URI(resLocHdr, requestURI));
                            }
                            catch (ParseException ex2) {}
                        }
                    }
                    Util.updateValue(request.getHeaders(), "Referer", request.getConnection() + request.getRequestURI());
                }
                request.setConnection(connection);
                request.setRequestURI(requestURI);
                try {
                    response.getInputStream().close();
                }
                catch (IOException ex3) {}
                if (statusCode == 305 || statusCode == 306) {
                    if (GlobalConstants.DebugMods) {
                        Util.logLine("RdirM: resending request using proxy " + connection.getProxyHost() + ":" + connection.getProxyPort());
                    }
                }
                else {
                    try {
                        this.lastURI = new URI(resLocHdr, requestURI);
                    }
                    catch (ParseException ex4) {}
                    if (GlobalConstants.DebugMods) {
                        Util.logLine("RdirM: request redirected to " + this.lastURI + " using method " + request.getMethod());
                    }
                }
                if (request.getStream() != null) {
                    return 10;
                }
                if (this.new_con) {
                    return 15;
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
    
    public void trailerHandler(final Response response, final RoRequest roRequest) {
    }
    
    private static void update_perm_redir_list(final RoRequest roRequest, final URI uri) {
        final HTTPConnection connection = roRequest.getConnection();
        URI uri2 = null;
        try {
            uri2 = new URI(connection.getProtocol(), connection.getHost(), connection.getPort(), roRequest.getRequestURI());
        }
        catch (ParseException ex) {}
        if (!uri2.equals(uri)) {
            Util.getList(RedirectionModule.perm_redir_cntxt_list, connection.getContext()).put(uri2, uri);
        }
    }
    
    private URI resLocHdr(final String s, final RoRequest roRequest) throws ProtocolException {
        try {
            return new URI(s);
        }
        catch (ParseException ex) {
            try {
                return new URI(new URI(roRequest.getConnection().getProtocol(), roRequest.getConnection().getHost(), roRequest.getConnection().getPort(), roRequest.getRequestURI()), s);
            }
            catch (ParseException ex2) {
                throw new ProtocolException("Malformed URL in Location header: " + s);
            }
        }
    }
    
    private boolean sameServer(final HTTPConnection httpConnection, final URI uri) {
        return uri.getScheme().equalsIgnoreCase(httpConnection.getProtocol()) && uri.getHost().equalsIgnoreCase(httpConnection.getHost()) && uri.getPort() == httpConnection.getPort();
    }
    
    private void copyFrom(final RedirectionModule redirectionModule) {
        this.level = redirectionModule.level;
        this.lastURI = redirectionModule.lastURI;
        this.saved_req = redirectionModule.saved_req;
    }
    
    static {
        RedirectionModule.perm_redir_cntxt_list = new Hashtable();
        RedirectionModule.deferred_redir_list = new Hashtable();
    }
}
