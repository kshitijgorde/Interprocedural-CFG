// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.common.transfer;

import java.io.Serializable;

public class RequestInfoDetails implements Serializable
{
    public static final String KEY_REQUESTINFO_APPSERVER = "RequestInfoApplicationserver";
    public static final String KEY_REQUESTINFO_WEBSERVER = "RequestInfoWebserver";
    public String command;
    public int duration;
    public int externalDuration;
    public String funktion;
    public long requestId;
    public int resultRet;
    public String resultText;
    public String sessionId;
    public String url;
    public String username;
    
    public static RequestInfoDetails getAppserverInfoFromReply(final Reply reply) {
        return getServerInfoFromReply(reply, "RequestInfoApplicationserver");
    }
    
    private static RequestInfoDetails getServerInfoFromReply(final Reply reply, final String key) {
        if (reply != null && reply.infos != null) {
            return reply.infos.get(key);
        }
        return null;
    }
    
    public static RequestInfoDetails getWebserverInfoFromReply(final Reply reply) {
        return getServerInfoFromReply(reply, "RequestInfoWebserver");
    }
    
    public String toString() {
        return String.valueOf(this.requestId) + ", url:" + this.url + ", text:" + this.resultText + ", ret:" + this.resultRet + ", sid:" + this.sessionId + ", f:" + this.funktion + ", c:" + this.command + ", d: " + this.duration + ", ed:" + this.externalDuration;
    }
}
