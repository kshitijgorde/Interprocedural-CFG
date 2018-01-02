// 
// Decompiled by Procyon v0.5.30
// 

package irc;

import java.util.Locale;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.util.Hashtable;

public class StreamParameterProvider implements ParameterProvider
{
    private Hashtable _table;
    
    public StreamParameterProvider(final InputStream inputStream) {
        this._table = new Hashtable();
        if (inputStream == null) {
            return;
        }
        try {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            for (String s = bufferedReader.readLine(); s != null; s = bufferedReader.readLine()) {
                final String trim = s.trim();
                if (trim.length() > 0 && trim.charAt(0) != '#') {
                    this.parse(trim);
                }
            }
            bufferedReader.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void parse(final String s) {
        final int index = s.indexOf(61);
        if (index < 0) {
            return;
        }
        this._table.put(s.substring(0, index).trim().toLowerCase(Locale.ENGLISH), s.substring(index + 1).trim());
    }
    
    public String getParameter(final String s) {
        return this._table.get(s.toLowerCase(Locale.ENGLISH));
    }
}
