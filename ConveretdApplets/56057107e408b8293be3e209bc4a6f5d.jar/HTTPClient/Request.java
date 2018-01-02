// 
// Decompiled by Procyon v0.5.30
// 

package HTTPClient;

public final class Request implements RoRequest, Cloneable
{
    private HTTPConnection connection;
    private String method;
    private String req_uri;
    private NVPair[] headers;
    private byte[] data;
    private HttpOutputStream stream;
    private boolean allow_ui;
    long delay_entity;
    int num_retries;
    boolean dont_pipeline;
    boolean aborted;
    boolean internal_subrequest;
    
    public Request(final HTTPConnection connection, final String method, final String req_uri, final NVPair[] headers, final byte[] data, final HttpOutputStream stream, final boolean allow_ui) {
        this.delay_entity = 0L;
        this.num_retries = 0;
        this.dont_pipeline = false;
        this.aborted = false;
        this.internal_subrequest = false;
        this.connection = connection;
        this.method = method;
        this.req_uri = req_uri;
        this.headers = headers;
        this.data = data;
        this.stream = stream;
        this.allow_ui = allow_ui;
    }
    
    public HTTPConnection getConnection() {
        return this.connection;
    }
    
    public void setConnection(final HTTPConnection connection) {
        this.connection = connection;
    }
    
    public String getMethod() {
        return this.method;
    }
    
    public void setMethod(final String method) {
        this.method = method;
    }
    
    public String getRequestURI() {
        return this.req_uri;
    }
    
    public void setRequestURI(final String req_uri) {
        this.req_uri = req_uri;
    }
    
    public NVPair[] getHeaders() {
        return this.headers;
    }
    
    public void setHeaders(final NVPair[] headers) {
        this.headers = headers;
    }
    
    public byte[] getData() {
        return this.data;
    }
    
    public void setData(final byte[] data) {
        this.data = data;
    }
    
    public HttpOutputStream getStream() {
        return this.stream;
    }
    
    public void setStream(final HttpOutputStream stream) {
        this.stream = stream;
    }
    
    public boolean allowUI() {
        return this.allow_ui;
    }
    
    public void setAllowUI(final boolean allow_ui) {
        this.allow_ui = allow_ui;
    }
    
    public Object clone() {
        Request request;
        try {
            request = (Request)super.clone();
        }
        catch (CloneNotSupportedException ex) {
            throw new InternalError(ex.toString());
        }
        request.headers = new NVPair[this.headers.length];
        System.arraycopy(this.headers, 0, request.headers, 0, this.headers.length);
        return request;
    }
    
    public void copyFrom(final Request request) {
        this.connection = request.connection;
        this.method = request.method;
        this.req_uri = request.req_uri;
        this.headers = request.headers;
        this.data = request.data;
        this.stream = request.stream;
        this.allow_ui = request.allow_ui;
        this.delay_entity = request.delay_entity;
        this.num_retries = request.num_retries;
        this.dont_pipeline = request.dont_pipeline;
        this.aborted = request.aborted;
        this.internal_subrequest = request.internal_subrequest;
    }
    
    public String toString() {
        return this.getClass().getName() + ": " + this.method + " " + this.req_uri;
    }
}
