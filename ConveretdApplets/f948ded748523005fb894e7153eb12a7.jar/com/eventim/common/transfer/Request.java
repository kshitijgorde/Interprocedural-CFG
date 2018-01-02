// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.common.transfer;

import java.io.OutputStream;
import java.net.URLConnection;
import com.eventim.common.utils.ExceptionUtils;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.Hashtable;
import com.eventim.common.utils.CompressionUtils;
import java.io.Serializable;

public class Request implements Serializable
{
    public static Reply compressReply(final Reply reply, final int compressionType) throws Exception {
        if (reply != null && reply.objects != null) {
            final Object temp = CompressionUtils.compressObject(reply.objects, compressionType);
            (reply.objects = new Object[1])[0] = temp;
        }
        return reply;
    }
    
    private static Reply decompressReply(final Reply reply, final int compressionType) throws Exception {
        if (reply != null && reply.objects != null && reply.objects.length == 1) {
            reply.objects = (Object[])CompressionUtils.decompressObject((byte[])reply.objects[0], compressionType);
        }
        return reply;
    }
    
    public static int getCommand(final Hashtable request) {
        try {
            return Integer.parseInt(request.get("Command"));
        }
        catch (Exception e) {
            return 0;
        }
    }
    
    public static int getCompressionType(final Hashtable request) {
        try {
            return Integer.parseInt(request.get("CompressionType"));
        }
        catch (Exception e) {
            return 0;
        }
    }
    
    public static int getTryCount(final Hashtable request) {
        try {
            return Integer.parseInt(request.get("TryCount"));
        }
        catch (Exception e) {
            return 0;
        }
    }
    
    public static Reply sendRequest(final String protocol, final String host, final int port, final String servletString, final Hashtable request) {
        Reply reply = null;
        int compressionType = 0;
        String errorBuffer = "";
        int i = 0;
        while (i < 2) {
            try {
                if (i == 0) {
                    compressionType = getCompressionType(request);
                }
                else {
                    compressionType = 0;
                }
                final URL serverUrl = new URL(protocol, host, port, servletString);
                final URLConnection con = serverUrl.openConnection();
                con.setDoOutput(true);
                con.setDoInput(true);
                con.setDefaultUseCaches(false);
                con.setUseCaches(false);
                final OutputStream os = con.getOutputStream();
                final ObjectOutputStream out = new ObjectOutputStream(os);
                out.writeObject(request);
                out.flush();
                out.close();
                final ObjectInputStream in = new ObjectInputStream(con.getInputStream());
                reply = (Reply)in.readObject();
                in.close();
                if (compressionType > 0) {
                    reply = decompressReply(reply, compressionType);
                }
                final StringBuffer sb = new StringBuffer();
                final Reply reply2 = reply;
                reply2.text = sb.append(reply2.text).append(" (via ").append(protocol).append(")").toString();
                if (i > 0) {
                    final StringBuffer sb2 = new StringBuffer();
                    final Reply reply3 = reply;
                    reply3.text = sb2.append(reply3.text).append(errorBuffer).toString();
                }
                return reply;
            }
            catch (Exception e) {
                reply = new Reply(0);
                reply.ret = 0;
                reply.text = "Exception in Request.sendRequest(Protocol = " + protocol + "; Host = " + host + "; Port = " + port + "; ServletString = " + servletString + "; CompressionType = " + compressionType + "): " + ExceptionUtils.getExceptionStackTrace(e) + "\r\n***********************************************************\r\n" + errorBuffer;
                if (compressionType == 0) {
                    return reply;
                }
                setCompressionType(request, 0);
                errorBuffer = " (1. Versuch: " + reply.text + ")";
                ++i;
            }
        }
        return reply;
    }
    
    public static Reply sendRequest(final String host, final int port, final String servletString, final Hashtable request, final boolean secure) {
        if (secure) {
            return sendRequest("https", host, port, servletString, request);
        }
        return sendRequest("http", host, port, servletString, request);
    }
    
    public static void setCommand(final Hashtable request, final int command) {
        request.put("Command", String.valueOf(command));
    }
    
    public static void setCompressionType(final Hashtable request, final int compressionType) {
        request.put("CompressionType", String.valueOf(compressionType));
    }
    
    public static void setTryCount(final Hashtable request, final int tryCount) {
        request.put("TryCount", String.valueOf(tryCount));
    }
}
