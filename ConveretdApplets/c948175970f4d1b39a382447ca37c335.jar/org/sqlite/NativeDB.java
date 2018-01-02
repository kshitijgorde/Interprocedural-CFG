// 
// Decompiled by Procyon v0.5.30
// 

package org.sqlite;

import java.sql.SQLException;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.File;

final class NativeDB extends DB
{
    long pointer;
    private static Boolean loaded;
    private long udfdatalist;
    static /* synthetic */ Class class$org$sqlite$NativeDB;
    
    NativeDB() {
        this.pointer = 0L;
        this.udfdatalist = 0L;
    }
    
    static boolean load() {
        if (NativeDB.loaded != null) {
            return NativeDB.loaded == Boolean.TRUE;
        }
        final String property = System.getProperty("org.sqlite.lib.path");
        String s = System.getProperty("org.sqlite.lib.name");
        if (s == null) {
            s = System.mapLibraryName("sqlitejdbc");
        }
        try {
            if (property == null) {
                System.loadLibrary("sqlitejdbc");
            }
            else {
                System.load(new File(property, s).getAbsolutePath());
            }
            NativeDB.loaded = Boolean.TRUE;
            return true;
        }
        catch (UnsatisfiedLinkError unsatisfiedLinkError) {
            String lowerCase = System.getProperty("os.name").toLowerCase();
            String property2 = System.getProperty("os.arch");
            if (lowerCase.startsWith("mac os")) {
                lowerCase = "mac";
                property2 = "universal";
            }
            if (lowerCase.startsWith("windows")) {
                lowerCase = "win";
            }
            if (lowerCase.startsWith("sunos")) {
                lowerCase = "solaris";
            }
            if (property2.startsWith("i") && property2.endsWith("86")) {
                property2 = "x86";
            }
            final String string = lowerCase + '-' + property2 + ".lib";
            try {
                final InputStream resourceAsStream = ((NativeDB.class$org$sqlite$NativeDB == null) ? (NativeDB.class$org$sqlite$NativeDB = class$("org.sqlite.NativeDB")) : NativeDB.class$org$sqlite$NativeDB).getClassLoader().getResourceAsStream(string);
                if (resourceAsStream == null) {
                    throw new Exception("libname: " + string + " not found");
                }
                final File tempFile = File.createTempFile("libsqlitejdbc-", ".lib");
                tempFile.deleteOnExit();
                final FileOutputStream fileOutputStream = new FileOutputStream(tempFile);
                final byte[] array = new byte[1024];
                int read;
                while ((read = resourceAsStream.read(array)) != -1) {
                    fileOutputStream.write(array, 0, read);
                }
                resourceAsStream.close();
                fileOutputStream.close();
                System.load(tempFile.getAbsolutePath());
                NativeDB.loaded = Boolean.TRUE;
                return true;
            }
            catch (Exception ex) {
                NativeDB.loaded = Boolean.FALSE;
                return false;
            }
        }
    }
    
    protected synchronized native void _open(final String p0) throws SQLException;
    
    protected synchronized native void _close() throws SQLException;
    
    synchronized native int shared_cache(final boolean p0);
    
    synchronized native void interrupt();
    
    synchronized native void busy_timeout(final int p0);
    
    protected synchronized native long prepare(final String p0) throws SQLException;
    
    synchronized native String errmsg();
    
    synchronized native String libversion();
    
    synchronized native int changes();
    
    protected synchronized native int finalize(final long p0);
    
    protected synchronized native int step(final long p0);
    
    protected synchronized native int reset(final long p0);
    
    synchronized native int clear_bindings(final long p0);
    
    synchronized native int bind_parameter_count(final long p0);
    
    synchronized native int column_count(final long p0);
    
    synchronized native int column_type(final long p0, final int p1);
    
    synchronized native String column_decltype(final long p0, final int p1);
    
    synchronized native String column_table_name(final long p0, final int p1);
    
    synchronized native String column_name(final long p0, final int p1);
    
    synchronized native String column_text(final long p0, final int p1);
    
    synchronized native byte[] column_blob(final long p0, final int p1);
    
    synchronized native double column_double(final long p0, final int p1);
    
    synchronized native long column_long(final long p0, final int p1);
    
    synchronized native int column_int(final long p0, final int p1);
    
    synchronized native int bind_null(final long p0, final int p1);
    
    synchronized native int bind_int(final long p0, final int p1, final int p2);
    
    synchronized native int bind_long(final long p0, final int p1, final long p2);
    
    synchronized native int bind_double(final long p0, final int p1, final double p2);
    
    synchronized native int bind_text(final long p0, final int p1, final String p2);
    
    synchronized native int bind_blob(final long p0, final int p1, final byte[] p2);
    
    synchronized native void result_null(final long p0);
    
    synchronized native void result_text(final long p0, final String p1);
    
    synchronized native void result_blob(final long p0, final byte[] p1);
    
    synchronized native void result_double(final long p0, final double p1);
    
    synchronized native void result_long(final long p0, final long p1);
    
    synchronized native void result_int(final long p0, final int p1);
    
    synchronized native void result_error(final long p0, final String p1);
    
    synchronized native int value_bytes(final Function p0, final int p1);
    
    synchronized native String value_text(final Function p0, final int p1);
    
    synchronized native byte[] value_blob(final Function p0, final int p1);
    
    synchronized native double value_double(final Function p0, final int p1);
    
    synchronized native long value_long(final Function p0, final int p1);
    
    synchronized native int value_int(final Function p0, final int p1);
    
    synchronized native int value_type(final Function p0, final int p1);
    
    synchronized native int create_function(final String p0, final Function p1);
    
    synchronized native int destroy_function(final String p0);
    
    synchronized native void free_functions();
    
    synchronized native boolean[][] column_metadata(final long p0);
    
    static void throwex(final String s) throws SQLException {
        throw new SQLException(s);
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        NativeDB.loaded = null;
    }
}
