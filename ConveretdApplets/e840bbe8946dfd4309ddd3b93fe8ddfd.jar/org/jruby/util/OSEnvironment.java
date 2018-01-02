// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util;

import org.jruby.RubyString;
import java.util.Iterator;
import org.jcodings.Encoding;
import org.jruby.ext.posix.util.Platform;
import java.util.Set;
import java.util.Properties;
import java.util.HashMap;
import java.util.Map;
import org.jruby.Ruby;

public class OSEnvironment
{
    public Map getEnvironmentVariableMap(final Ruby runtime) {
        Map envs = null;
        if (runtime.getInstanceConfig().getEnvironment() != null) {
            return getAsMapOfRubyStrings(runtime, runtime.getInstanceConfig().getEnvironment().entrySet());
        }
        if (Ruby.isSecurityRestricted()) {
            envs = new HashMap();
        }
        else {
            final Map variables = System.getenv();
            envs = getAsMapOfRubyStrings(runtime, variables.entrySet());
        }
        return envs;
    }
    
    public Map getSystemPropertiesMap(final Ruby runtime) {
        if (Ruby.isSecurityRestricted()) {
            return new HashMap();
        }
        return getAsMapOfRubyStrings(runtime, ((Properties)System.getProperties().clone()).entrySet());
    }
    
    private static Map getAsMapOfRubyStrings(final Ruby runtime, final Set<Map.Entry<Object, Object>> entrySet) {
        final Map envs = new HashMap();
        final Encoding encoding = runtime.getEncodingService().getLocaleEncoding();
        if (Platform.IS_WINDOWS) {
            addRubyKeyValuePair(runtime, envs, "HOME", System.getProperty("user.home"), encoding);
            addRubyKeyValuePair(runtime, envs, "USER", System.getProperty("user.name"), encoding);
        }
        for (final Map.Entry<Object, Object> entry : entrySet) {
            final String value = entry.getValue();
            final String key = entry.getKey();
            addRubyKeyValuePair(runtime, envs, key, value, encoding);
        }
        return envs;
    }
    
    private static void addRubyKeyValuePair(final Ruby runtime, final Map map, final String key, final String value, final Encoding encoding) {
        final ByteList keyBytes = new ByteList(key.getBytes(), encoding);
        final ByteList valueBytes = new ByteList(value.getBytes(), encoding);
        final RubyString keyString = runtime.newString(keyBytes);
        final RubyString valueString = runtime.newString(valueBytes);
        keyString.setFrozen(true);
        valueString.setFrozen(true);
        map.put(keyString, valueString);
    }
}
