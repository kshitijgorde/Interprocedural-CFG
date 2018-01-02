// 
// Decompiled by Procyon v0.5.30
// 

package HTTPClient;

import java.io.IOException;

class RetryModule implements HTTPClientModule, GlobalConstants
{
    public int requestHandler(final Request request, final Response[] array) {
        return 0;
    }
    
    public void responsePhase1Handler(final Response response, final RoRequest roRequest) throws IOException, ModuleException {
        try {
            response.getStatusCode();
        }
        catch (RetryException ex) {
            if (GlobalConstants.DebugMods) {
                Util.logLine("RtryM: Caught RetryException");
            }
            boolean b = false;
            try {
                synchronized (ex.first) {
                    b = true;
                    final IdempotentSequence idempotentSequence = new IdempotentSequence();
                    for (RetryException ex2 = ex.first; ex2 != null; ex2 = ex2.next) {
                        idempotentSequence.add(ex2.request);
                    }
                    for (RetryException ex3 = ex.first; ex3 != null; ex3 = ex3.next) {
                        final Request request = ex3.request;
                        final HTTPConnection connection = request.getConnection();
                        if (Thread.currentThread().isInterrupted() || !idempotentSequence.isIdempotent(request) || (connection.ServProtVersKnown && connection.ServerProtocolVersion >= 65537 && request.num_retries > 0) || ((!connection.ServProtVersKnown || connection.ServerProtocolVersion <= 65536) && request.num_retries > 4) || ex3.response.got_headers) {
                            ex3.first = null;
                        }
                        else if (request.getStream() != null) {
                            ex3.first = null;
                            request.getStream().reset();
                            ex3.response.setRetryRequest(true);
                        }
                        else {
                            if (request.getData() != null && ex3.conn_reset) {
                                if (connection.ServProtVersKnown && connection.ServerProtocolVersion >= 65537) {
                                    request.setHeaders(Util.addToken(request.getHeaders(), "Expect", "100-continue"));
                                }
                                else {
                                    request.delay_entity = 5000L << request.num_retries;
                                }
                            }
                            if (ex3.next != null && ex3.next.request.getData() != null && (!connection.ServProtVersKnown || connection.ServerProtocolVersion < 65537) && ex3.conn_reset) {
                                request.setHeaders(Util.addToken(request.getHeaders(), "Connection", "close"));
                            }
                            if (connection.ServProtVersKnown && connection.ServerProtocolVersion >= 65537 && ex3.conn_reset) {
                                request.dont_pipeline = true;
                            }
                            if (GlobalConstants.DebugDemux) {
                                Util.logLine("RtryM: Retrying request '" + request.getMethod() + " " + request.getRequestURI() + "'");
                            }
                            if (ex3.conn_reset) {
                                final Request request2 = request;
                                ++request2.num_retries;
                            }
                            ex3.response.http_resp.set(request, connection.sendRequest(request, ex3.response.timeout));
                            ex3.exception = null;
                            ex3.first = null;
                        }
                    }
                }
            }
            catch (NullPointerException ex4) {
                if (b) {
                    throw ex4;
                }
            }
            catch (ParseException ex5) {
                throw new IOException(ex5.getMessage());
            }
            if (ex.exception != null) {
                throw ex.exception;
            }
            ex.restart = true;
            throw ex;
        }
    }
    
    public int responsePhase2Handler(final Response response, final Request request) {
        request.delay_entity = 0L;
        request.dont_pipeline = false;
        request.num_retries = 0;
        return 10;
    }
    
    public void responsePhase3Handler(final Response response, final RoRequest roRequest) {
    }
    
    public void trailerHandler(final Response response, final RoRequest roRequest) {
    }
}
