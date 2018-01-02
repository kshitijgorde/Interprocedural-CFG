// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.console.plugins.helpers.servlet;

public class ServletHelper
{
    public static String filter(final String input) {
        if (input == null) {
            return null;
        }
        final StringBuffer filtered = new StringBuffer(input.length() * 2);
        for (int i = 0; i < input.length(); ++i) {
            final char c = input.charAt(i);
            switch (c) {
                case '<': {
                    filtered.append("&lt;");
                    break;
                }
                case '>': {
                    filtered.append("&gt;");
                    break;
                }
                case '\"': {
                    filtered.append("&quot;");
                    break;
                }
                case '&': {
                    filtered.append("&amp;");
                    break;
                }
                default: {
                    filtered.append(c);
                    break;
                }
            }
        }
        return filtered.toString();
    }
}
