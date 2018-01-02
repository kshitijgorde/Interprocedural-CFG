// 
// Decompiled by Procyon v0.5.30
// 

package com.controlj.green.applets.comm;

import java.io.OutputStream;
import java.net.URLConnection;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.zip.GZIPInputStream;
import java.net.URL;

public class ServletCommunicator
{
    private URL baseURL;
    private String baseRequest;
    private boolean responseCompressed;
    
    public ServletCommunicator(final URL url, final String request) {
        this.responseCompressed = false;
        this.baseURL = url;
        this.setBaseRequest(request);
    }
    
    public void setBaseURL(final URL url) {
        this.baseURL = url;
    }
    
    public boolean isResponseCompressed() {
        return this.responseCompressed;
    }
    
    public void setResponseCompressed(final boolean responseCompressed) {
        this.responseCompressed = responseCompressed;
    }
    
    public void setBaseRequest(final String request) {
        this.baseRequest = request;
        if (this.baseRequest == null) {
            this.baseRequest = "";
        }
    }
    
    public void runServlet(final String requestString) {
        this.makeServletRequest(requestString, false);
    }
    
    public Object getObjectFromServlet(final String requestString) {
        return this.makeServletRequest(requestString, true);
    }
    
    private Object makeServletRequest(String requestString, final boolean expectReply) {
        if (requestString == null || requestString.equals("")) {
            requestString = this.baseRequest;
        }
        else {
            requestString = this.baseRequest + "&" + requestString;
        }
        if (this.isResponseCompressed()) {
            requestString += "&gzip=true";
        }
        Object obj = null;
        URLConnection connection = null;
        final OutputStream request = null;
        ObjectInputStream result = null;
        try {
            connection = this.baseURL.openConnection();
            connection.setDoOutput(true);
            final OutputStream output = connection.getOutputStream();
            output.write(requestString.getBytes());
            output.flush();
            if (expectReply) {
                if (this.isResponseCompressed()) {
                    result = new ObjectInputStream(new GZIPInputStream(connection.getInputStream()));
                }
                else {
                    result = new ObjectInputStream(connection.getInputStream());
                }
                obj = result.readObject();
            }
            else {
                connection.getInputStream();
                connection.getContent();
            }
        }
        catch (Exception ex) {
            System.out.println("<----  Exception in connecting to servlet using url " + this.baseURL.toString() + "---> ");
            System.out.println("<----  and request string " + requestString + "  ---> ");
            ex.printStackTrace();
            try {
                if (result != null) {
                    result.close();
                }
                if (request != null) {
                    request.close();
                }
            }
            catch (Exception e) {
                System.out.println("<----  Exception in closing servlets ---> ");
                e.printStackTrace();
            }
        }
        finally {
            try {
                if (result != null) {
                    result.close();
                }
                if (request != null) {
                    request.close();
                }
            }
            catch (Exception e2) {
                System.out.println("<----  Exception in closing servlets ---> ");
                e2.printStackTrace();
            }
        }
        return obj;
    }
}
