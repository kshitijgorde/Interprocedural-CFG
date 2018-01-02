// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.util;

public final class JMXSupport
{
    public static String encodeObjectNamePart(final String part) {
        String answer = part.replaceAll("[\\:\\,\\'\\\"]", "_");
        answer = answer.replaceAll("\\?", "&qe;");
        answer = answer.replaceAll("=", "&amp;");
        answer = answer.replaceAll("\\*", "&ast;");
        return answer;
    }
}
