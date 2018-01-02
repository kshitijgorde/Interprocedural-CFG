// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.util;

import java.util.Hashtable;

public class MessageProviderRegistry
{
    private static Hashtable fgMessageProviders;
    
    public static void register(final String s, final String s2) {
        MessageProviderRegistry.fgMessageProviders.put(s, new ErrorMessageProvider(s2));
    }
    
    public static MessageProvider getMessageProvider(final String s) {
        return MessageProviderRegistry.fgMessageProviders.get(s);
    }
    
    static {
        MessageProviderRegistry.fgMessageProviders = new Hashtable();
        register("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", "com.ibm.xml.b2b.util.msg.DocumentEntityMessagesBundle");
        register("http://www.w3.org/TR/2000/REC-xml-20001006", "com.ibm.xml.b2b.util.msg.XMLMessagesBundle");
        register("http://w3.xml.ibm.com/#B2BXML", "com.ibm.xml.b2b.util.msg.ImplementationMessagesBundle");
    }
}
