import java.io.FileWriter;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.StringWriter;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;

// 
// Decompiled by Procyon v0.5.30
// 

class FilePrims extends Primitives
{
    String readtext;
    int textoffset;
    static String[] primlist;
    static /* synthetic */ Class class$FilePrims;
    
    public String[] primlist() {
        return FilePrims.primlist;
    }
    
    public Object dispatch(final int n, final Object[] array, final LContext lContext) {
        switch (n) {
            case 0: {
                return this.prim_filetostring(array[0], lContext);
            }
            case 1: {
                return this.prim_resourcetostring(array[0], lContext);
            }
            case 2: {
                return this.prim_load(array[0], lContext);
            }
            case 3: {
                return this.prim_stringtofile(array[0], array[1], lContext);
            }
            case 4: {
                return this.prim_filep(array[0], lContext);
            }
            case 5: {
                return this.prim_setread(array[0], lContext);
            }
            case 6: {
                return this.prim_readline(lContext);
            }
            case 7: {
                return this.prim_eot(lContext);
            }
            case 8: {
                return this.prim_lineback(lContext);
            }
            case 9: {
                return this.prim_filenamefrompath(array[0], lContext);
            }
            case 10: {
                return this.prim_dirnamefrompath(array[0], lContext);
            }
            case 11: {
                return this.prim_dir(array[0], lContext);
            }
            default: {
                return null;
            }
        }
    }
    
    Object prim_filetostring(final Object o, final LContext lContext) {
        return this.fileToString(Logo.prs(o), lContext);
    }
    
    Object prim_resourcetostring(final Object o, final LContext lContext) {
        return this.resourceToString(Logo.prs(o), lContext);
    }
    
    Object prim_load(final Object o, final LContext lContext) {
        final String prs = Logo.prs(o);
        System.getProperty("file.separator");
        Logo.readAllFunctions(this.resourceToString(prs + ".logo", lContext), lContext);
        return null;
    }
    
    String resourceToString(final String s, final LContext lContext) {
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(((FilePrims.class$FilePrims == null) ? (FilePrims.class$FilePrims = class$("FilePrims")) : FilePrims.class$FilePrims).getResourceAsStream(s)));
        final StringWriter stringWriter = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(new BufferedWriter(stringWriter), true);
        try {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                printWriter.println(line);
            }
            return stringWriter.toString();
        }
        catch (IOException ex) {
            Logo.error("Can't open file " + s, lContext);
            return null;
        }
    }
    
    String fileToString(final String s, final LContext lContext) {
        byte[] array = null;
        try {
            final File file = new File(s);
            final int n = (int)file.length();
            final FileInputStream fileInputStream = new FileInputStream(file);
            final DataInputStream dataInputStream = new DataInputStream(fileInputStream);
            array = new byte[n];
            dataInputStream.readFully(array);
            fileInputStream.close();
        }
        catch (IOException ex) {
            Logo.error("Can't open file " + s, lContext);
        }
        return new String(array);
    }
    
    Object prim_stringtofile(final Object o, final Object o2, final LContext lContext) {
        final String prs = Logo.prs(o);
        final String prs2 = Logo.prs(o2);
        try {
            final FileWriter fileWriter = new FileWriter(prs);
            fileWriter.write(prs2, 0, prs2.length());
            fileWriter.close();
        }
        catch (IOException ex) {
            Logo.error("Can't write file " + prs, lContext);
        }
        return null;
    }
    
    Object prim_filep(final Object o, final LContext lContext) {
        return new Boolean(new File(Logo.prs(o)).exists());
    }
    
    Object prim_setread(final Object o, final LContext lContext) {
        this.readtext = Logo.prs(o);
        this.textoffset = 0;
        return null;
    }
    
    Object prim_readline(final LContext lContext) {
        String s = "";
        final int index = this.readtext.indexOf("\n", this.textoffset);
        if (index == -1) {
            if (this.textoffset < this.readtext.length()) {
                s = this.readtext.substring(this.textoffset, this.readtext.length());
                this.textoffset = this.readtext.length();
            }
        }
        else {
            s = this.readtext.substring(this.textoffset, index);
            this.textoffset = index + 1;
        }
        if (s.length() == 0) {
            return s;
        }
        if (s.charAt(s.length() - 1) == '\r') {
            s = s.substring(0, s.length() - 1);
        }
        return s;
    }
    
    Object prim_eot(final LContext lContext) {
        return new Boolean(this.textoffset >= this.readtext.length());
    }
    
    Object prim_lineback(final LContext lContext) {
        final int lastIndex = this.readtext.lastIndexOf("\n", this.textoffset - 2);
        if (lastIndex < 0) {
            this.textoffset = 0;
        }
        else {
            this.textoffset = lastIndex + 1;
        }
        return null;
    }
    
    Object prim_filenamefrompath(final Object o, final LContext lContext) {
        return new File(Logo.prs(o)).getName();
    }
    
    Object prim_dirnamefrompath(final Object o, final LContext lContext) {
        final File file = new File(Logo.prs(o));
        if (file.isDirectory()) {
            return file.getPath();
        }
        return file.getParent();
    }
    
    Object prim_dir(final Object o, final LContext lContext) {
        final String[] list = new File(Logo.prs(o)).list();
        if (list == null) {
            return new Object[0];
        }
        return list;
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
        FilePrims.primlist = new String[] { "filetostring", "1", "resourcetostring", "1", "load", "1", "stringtofile", "2", "file?", "1", "setread", "1", "readline", "0", "eot?", "0", "lineback", "0", "filenamefrompath", "1", "dirnamefrompath", "1", "dir", "1" };
    }
}
