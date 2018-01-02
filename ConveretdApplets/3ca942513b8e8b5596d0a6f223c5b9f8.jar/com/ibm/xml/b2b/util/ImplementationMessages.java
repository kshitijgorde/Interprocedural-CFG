// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.util;

import java.util.Locale;

public final class ImplementationMessages
{
    public static final String URI = "http://w3.xml.ibm.com/#B2BXML";
    public static final String CLASSNAME = "com.ibm.xml.b2b.util.msg.ImplementationMessagesBundle";
    public static final int Message0 = 0;
    public static final int Message1 = 1;
    public static final int Message2 = 2;
    public static final int Message3 = 3;
    public static final int Message4 = 4;
    public static final int Message5 = 5;
    public static final int Message6 = 6;
    public static final int Message7 = 7;
    public static final int Message8 = 8;
    public static final int Message9 = 9;
    
    public static String formatMessage(final int n, final Object[] array) {
        return MessageProviderRegistry.getMessageProvider("http://w3.xml.ibm.com/#B2BXML").createMessage(null, n, array);
    }
}
