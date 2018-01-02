// 
// Decompiled by Procyon v0.5.30
// 

package HTTPClient;

import java.io.IOException;
import java.util.Date;
import java.util.Hashtable;

public class RetryAfterModule implements HTTPClientModule, GlobalConstants
{
    private static int threshold;
    private static Hashtable retry_list;
    private int delay;
    
    RetryAfterModule() {
        this.delay = -1;
    }
    
    public int requestHandler(final Request request, final Response[] array) {
        final Integer value;
        if (request.getStream() != null && (value = RetryAfterModule.retry_list.get(request.getStream())) != null) {
            this.delay = value;
            RetryAfterModule.retry_list.remove(request.getStream());
        }
        if (this.delay > RetryAfterModule.threshold) {
            if (GlobalConstants.DebugMods) {
                Util.logLine("ReAfM: delay exceeds threshold (" + this.delay + " > " + RetryAfterModule.threshold + ") - aborting request");
            }
            return 4;
        }
        if (this.delay >= 0) {
            if (GlobalConstants.DebugMods) {
                Util.logLine("ReAfM: delaying request by " + this.delay + " sec");
            }
            try {
                Thread.sleep(this.delay * 1000L);
            }
            catch (InterruptedException ex) {}
            this.delay = -1;
        }
        return 0;
    }
    
    public void responsePhase1Handler(final Response response, final RoRequest roRequest) throws IOException, ModuleException {
        this.delay = -1;
        if (response.getHeader("Retry-After") == null) {
            return;
        }
        try {
            this.delay = response.getHeaderAsInt("Retry-After");
        }
        catch (NumberFormatException ex) {
            Date headerAsDate;
            try {
                headerAsDate = response.getHeaderAsDate("Retry-After");
            }
            catch (IllegalArgumentException ex2) {
                throw new ModuleException("Illegal value in Retry-After header: '" + response.getHeader("Retry-After") + "'");
            }
            if (headerAsDate == null) {
                return;
            }
            Date headerAsDate2;
            try {
                headerAsDate2 = response.getHeaderAsDate("Date");
            }
            catch (IllegalArgumentException ex3) {
                throw new ModuleException("Illegal value in Date header: '" + response.getHeader("Date") + "'");
            }
            if (headerAsDate2 == null) {
                headerAsDate2 = new Date();
            }
            this.delay = (int)((headerAsDate.getTime() - headerAsDate2.getTime()) / 1000L);
            if (GlobalConstants.DebugMods) {
                Util.logLine("ReAfM: delay = " + this.delay + " sec");
            }
        }
    }
    
    public int responsePhase2Handler(final Response response, final Request request) throws IOException {
        if (response.getStatusCode() != 503 || this.delay < 0) {
            return 10;
        }
        if (this.delay > RetryAfterModule.threshold) {
            if (GlobalConstants.DebugMods) {
                Util.logLine("ReAfM: delay exceeds threshold (" + this.delay + " > " + RetryAfterModule.threshold + ") - not retrying request");
            }
            return 10;
        }
        if (request.getStream() != null) {
            RetryAfterModule.retry_list.put(request.getStream(), new Integer(this.delay));
            request.getStream().reset();
            response.setRetryRequest(true);
            return 10;
        }
        if (GlobalConstants.DebugMods) {
            Util.logLine("ReAfM: handling 503 status - retrying request");
        }
        return 13;
    }
    
    public void responsePhase3Handler(final Response response, final RoRequest roRequest) {
    }
    
    public void trailerHandler(final Response response, final RoRequest roRequest) {
    }
    
    public static void setThreshold(final int threshold) {
        RetryAfterModule.threshold = threshold;
    }
    
    static {
        RetryAfterModule.retry_list = new Hashtable();
        try {
            RetryAfterModule.threshold = Integer.getInteger("HTTPClient.retryafter.threshold", 30);
        }
        catch (Exception ex) {
            RetryAfterModule.threshold = 30;
        }
    }
}
