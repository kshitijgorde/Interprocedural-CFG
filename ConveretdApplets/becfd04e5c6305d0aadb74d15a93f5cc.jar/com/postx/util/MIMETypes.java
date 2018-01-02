// 
// Decompiled by Procyon v0.5.30
// 

package com.postx.util;

import java.util.StringTokenizer;
import java.util.Vector;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.IOException;
import com.postx.util.logging.Level;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.net.URL;
import java.io.File;
import java.util.Hashtable;
import com.postx.util.logging.Logger;

public class MIMETypes
{
    public static final String Ident = "$Id: MIMETypes.java,v 1.3 2011/01/10 05:13:52 blm Exp $";
    private static final Logger log;
    private static final boolean logConfig;
    private static final boolean logFine;
    private static final boolean logFiner;
    private static final String DEF_MIME_TYPE = "application/octet-stream";
    private static final String BUILTIN_TYPES = "builtin_types.txt";
    private static final String GLOBAL_TYPES = "global_types.txt";
    private static final String LOCAL_TYPES = "local_types.txt";
    private final Hashtable contentTypes;
    private final Hashtable needCharsets;
    
    public MIMETypes(final File file) {
        this.contentTypes = new Hashtable();
        this.needCharsets = new Hashtable();
        this.loadTypes("builtin_types.txt", this.getClass().getResource("/data/" + "builtin_types.txt"));
        this.loadTypes("global_types.txt", new File(file, "global_types.txt"));
        this.loadTypes("local_types.txt", new File(file, "local_types.txt"));
        if (MIMETypes.logConfig) {
            final int size = this.contentTypes.size();
            MIMETypes.log.config("Have " + size + " suffix/type pair" + ((size == 1) ? "" : "s"));
            final int size2 = this.needCharsets.size();
            MIMETypes.log.config("Have " + size2 + " type" + ((size2 == 1) ? "" : "s") + " needing a charset parameter");
        }
    }
    
    public boolean needCharset(final String s) {
        return this.needCharsets.containsKey(s);
    }
    
    private void loadTypes(final String s, final Object o) {
        if (o == null) {
            MIMETypes.log.fine(s + " MIME types not found");
            return;
        }
        InputStream inputStream = null;
        try {
            inputStream = ((o instanceof URL) ? ((URL)o).openStream() : new FileInputStream((File)o));
            this.loadTypes(s, inputStream);
        }
        catch (FileNotFoundException ex2) {
            MIMETypes.log.fine(s + " MIME types not found at " + o);
        }
        catch (Exception ex) {
            MIMETypes.log.log(Level.WARNING, "Can't read " + s + " MIME types", ex);
        }
        finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                }
                catch (IOException ex3) {}
            }
        }
    }
    
    private void loadTypes(final String s, final InputStream inputStream) throws IOException {
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "ISO-8859-1"));
        final Vector vector = new Vector<String>();
        String s2 = "";
        int n = 0;
        int n2 = 0;
        String s3;
        while ((s3 = bufferedReader.readLine()) != null) {
            ++n2;
            vector.removeAllElements();
            final int index;
            if ((index = s3.indexOf(35)) != -1) {
                s3 = s3.substring(0, index);
            }
            final StringTokenizer stringTokenizer = new StringTokenizer(s3, "\t");
            while (stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                if (nextToken.length() > 0) {
                    vector.addElement(nextToken);
                }
            }
            switch (vector.size()) {
                case 3: {
                    s2 = vector.elementAt(2);
                }
                case 2: {
                    final String s4 = vector.elementAt(0);
                    final String s5 = vector.elementAt(1);
                    if (!this.contentTypes.containsKey(s4)) {
                        ++n;
                    }
                    if (MIMETypes.logFiner) {
                        if (this.contentTypes.containsKey(s4)) {
                            MIMETypes.log.finer("Changing '" + s4 + "', '" + this.contentTypes.get(s4) + "' -> '" + s5 + "'");
                        }
                        else {
                            MIMETypes.log.finer("Mapping '" + s4 + "' -> '" + s5 + "'");
                        }
                    }
                    this.contentTypes.put(s4, s5);
                    if (s2.equals("1")) {
                        if (MIMETypes.logFiner) {
                            MIMETypes.log.finer("'" + s5 + "' needs a charset parameter");
                        }
                        this.needCharsets.put(s5, "");
                        s2 = "";
                        continue;
                    }
                    continue;
                }
                default: {
                    MIMETypes.log.warning(s + ", " + n2 + ": too many fields (" + vector.size() + "), using only first three");
                }
                case 1: {
                    MIMETypes.log.warning(s + ", " + n2 + ": only 1 field");
                }
                case 0: {
                    continue;
                }
            }
        }
        if (MIMETypes.logFine) {
            MIMETypes.log.fine("Read " + n + " new suffix/type pair" + ((n == 1) ? "" : "s") + " from " + n2 + " line" + ((n2 == 1) ? "" : "s") + " in " + s);
        }
    }
    
    static {
        log = Logger.global;
        logConfig = MIMETypes.log.isLoggable(Level.CONFIG);
        logFine = MIMETypes.log.isLoggable(Level.FINE);
        logFiner = MIMETypes.log.isLoggable(Level.FINER);
    }
    
    public String contentType(final String s) {
        final int lastIndex = s.lastIndexOf(46);
        final int lastIndex2 = s.lastIndexOf(46, lastIndex - 1);
        final String s2;
        if (lastIndex2 != -1 && (s2 = this.contentTypes.get(s.substring(lastIndex2 + 1))) != null) {
            return s2;
        }
        final String s3;
        if (lastIndex != -1 && (s3 = this.contentTypes.get(s.substring(lastIndex + 1))) != null) {
            return s3;
        }
        return "application/octet-stream";
    }
}
