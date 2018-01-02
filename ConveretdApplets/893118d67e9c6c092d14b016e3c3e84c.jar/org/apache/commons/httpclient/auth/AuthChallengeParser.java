// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.httpclient.auth;

import java.util.List;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.util.ParameterParser;
import java.util.HashMap;
import java.util.Map;

public final class AuthChallengeParser
{
    public static Map extractParams(final String challengeStr) throws MalformedChallengeException {
        if (challengeStr == null) {
            throw new IllegalArgumentException("Challenge may not be null");
        }
        final int idx = challengeStr.indexOf(32);
        if (idx == -1) {
            throw new MalformedChallengeException("Invalid challenge: " + challengeStr);
        }
        final Map map = new HashMap();
        final ParameterParser parser = new ParameterParser();
        final List params = parser.parse(challengeStr.substring(idx + 1, challengeStr.length()), ',');
        for (int i = 0; i < params.size(); ++i) {
            final NameValuePair param = params.get(i);
            map.put(param.getName().toLowerCase(), param.getValue());
        }
        return map;
    }
    
    public static String extractScheme(final String challengeStr) throws MalformedChallengeException {
        if (challengeStr == null) {
            throw new IllegalArgumentException("Challenge may not be null");
        }
        final int idx = challengeStr.indexOf(32);
        String s = null;
        if (idx == -1) {
            s = challengeStr;
        }
        else {
            s = challengeStr.substring(0, idx);
        }
        if (s.equals("")) {
            throw new MalformedChallengeException("Invalid challenge: " + challengeStr);
        }
        return s.toLowerCase();
    }
}
