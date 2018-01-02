// 
// Decompiled by Procyon v0.5.30
// 

package HTTPClient;

import java.io.IOException;

final class ResponseHandler implements GlobalConstants
{
    RespInputStream stream;
    Response resp;
    Request request;
    boolean eof;
    IOException exception;
    private boolean set_terminator;
    
    ResponseHandler(final Response resp, final Request request, final StreamDemultiplexor streamDemultiplexor) {
        this.eof = false;
        this.exception = null;
        this.set_terminator = false;
        this.resp = resp;
        this.request = request;
        this.stream = new RespInputStream(streamDemultiplexor, this);
        if (GlobalConstants.DebugDemux) {
            Util.logLine("Demux: Opening stream " + this.stream.hashCode() + " (" + streamDemultiplexor.hashCode() + ")");
        }
    }
    
    public void setupBoundary(final DemultiplexorInputStream demultiplexorInputStream) throws IOException, ParseException {
        if (this.set_terminator) {
            return;
        }
        final byte[] bytes = ("--" + Util.getParameter("boundary", this.resp.getHeader("Content-Type")) + "--\r\n").getBytes();
        demultiplexorInputStream.setTerminator(bytes, Util.compile_search(bytes));
        this.set_terminator = true;
    }
}
