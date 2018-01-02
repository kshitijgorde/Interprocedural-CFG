// 
// Decompiled by Procyon v0.5.30
// 

package irc;

import java.io.InputStream;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Hashtable;

public class FileTextProvider implements TextProvider
{
    private Hashtable _mainlist;
    private Hashtable _backlist;
    
    public FileTextProvider(final String s, final String s2, final String s3, final String s4, final FileHandler fileHandler) {
        this._mainlist = new Hashtable();
        this._backlist = new Hashtable();
        this.load(this._mainlist, s, s2, fileHandler);
        this.load(this._backlist, s3, s4, fileHandler);
    }
    
    private void parse(final Hashtable hashtable, final String s) {
        try {
            final int index = s.indexOf(32);
            if (index == -1) {
                return;
            }
            final String substring = s.substring(0, index);
            String s2 = s.substring(index + 1).trim();
            if (s.indexOf(91) != -1) {
                final int index2 = s.indexOf(93);
                if (index2 == -1) {
                    return;
                }
                s2 = s.substring(index2 + 1).trim();
            }
            hashtable.put(new Integer(Integer.parseInt(substring, 16)), this.replace(s2, "\\s", " "));
        }
        catch (Exception ex) {
            throw new RuntimeException(ex.toString());
        }
    }
    
    private void load(final Hashtable hashtable, final String s, final String s2, final FileHandler fileHandler) {
        final InputStream inputStream = fileHandler.getInputStream(s);
        if (inputStream == null) {
            return;
        }
        BufferedReader bufferedReader;
        try {
            if (s2.length() > 0) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream, s2));
            }
            else {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            }
        }
        catch (Exception ex2) {
            return;
        }
        try {
            for (String s3 = bufferedReader.readLine(); s3 != null; s3 = bufferedReader.readLine()) {
                final String trim = s3.trim();
                if (trim.length() > 0 && trim.charAt(0) != '#') {
                    this.parse(hashtable, trim);
                }
            }
            bufferedReader.close();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private String replace(String string, final String s, final String s2) {
        for (int i = string.indexOf(s); i >= 0; i = string.indexOf(s)) {
            string = string.substring(0, i) + s2 + string.substring(i + s.length());
        }
        return string;
    }
    
    public String getString(final int n, final String[] array) {
        String replace = this._mainlist.get(new Integer(n));
        if (replace == null) {
            replace = this._backlist.get(new Integer(n));
        }
        if (replace == null) {
            replace = this._mainlist.get(new Integer(65535));
        }
        if (replace == null) {
            replace = this._backlist.get(new Integer(65535));
        }
        if (replace == null) {
            return this.getStringP(65535);
        }
        for (int i = array.length - 1; i >= 0; --i) {
            replace = this.replace(replace, "%" + (i + 1), array[i]);
        }
        return replace;
    }
    
    public String getString(final int n) {
        return this.getString(n, new String[0]);
    }
    
    public String getString(final int n, final String s) {
        return this.getString(n, new String[] { s });
    }
    
    public String getString(final int n, final String s, final String s2) {
        return this.getString(n, new String[] { s, s2 });
    }
    
    public String getString(final int n, final String s, final String s2, final String s3) {
        return this.getString(n, new String[] { s, s2, s3 });
    }
    
    private String getStringP(final int n) {
        switch (n) {
            case 65535: {
                return "Undefined string";
            }
            default: {
                return null;
            }
        }
    }
}
