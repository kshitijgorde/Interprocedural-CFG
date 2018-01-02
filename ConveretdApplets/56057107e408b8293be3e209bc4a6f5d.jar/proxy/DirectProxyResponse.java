// 
// Decompiled by Procyon v0.5.30
// 

package proxy;

import java.io.IOException;
import logging.LogHolder;
import logging.LogType;
import java.io.InputStream;
import java.io.OutputStream;

final class DirectProxyResponse implements Runnable
{
    private int m_threadNumber;
    private static int ms_threadCount;
    private OutputStream m_outputStream;
    private InputStream m_inputStream;
    
    public DirectProxyResponse(final InputStream inputStream, final OutputStream outputStream) {
        this.m_inputStream = inputStream;
        this.m_outputStream = outputStream;
    }
    
    public void run() {
        this.m_threadNumber = this.getThreadNumber();
        LogHolder.log(7, LogType.NET, "R(" + this.m_threadNumber + ") - Response thread started.");
        try {
            final byte[] array = new byte[1000];
            int read;
            while ((read = this.m_inputStream.read(array)) != -1) {
                if (read > 0) {
                    if (LogHolder.isLogged(7, LogType.NET)) {
                        LogHolder.log(7, LogType.NET, "R(" + this.m_threadNumber + ") - " + new String(array, 0, read));
                    }
                    this.m_outputStream.write(array, 0, read);
                    this.m_outputStream.flush();
                }
            }
            LogHolder.log(7, LogType.NET, "R(" + this.m_threadNumber + ") - EOF from Server.");
        }
        catch (IOException ex3) {}
        catch (Exception ex) {
            LogHolder.log(5, LogType.NET, "R(" + this.m_threadNumber + ") - Exception during transmission: " + ex);
        }
        try {
            this.m_inputStream.close();
            this.m_outputStream.close();
        }
        catch (Exception ex2) {
            LogHolder.log(2, LogType.NET, "R(" + this.m_threadNumber + ") - Exception while closing: " + ex2.toString());
        }
        LogHolder.log(7, LogType.NET, "R(" + this.m_threadNumber + ") - Response thread stopped.");
    }
    
    private synchronized int getThreadNumber() {
        return DirectProxyResponse.ms_threadCount++;
    }
}
