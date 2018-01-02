// 
// Decompiled by Procyon v0.5.30
// 

package javazoom.jl.converter;

import javazoom.jl.decoder.Crc16;
import javazoom.jl.decoder.JavaLayerException;
import java.io.OutputStream;
import java.io.PrintWriter;

public class jlc
{
    public static void main(final String[] array) {
        System.currentTimeMillis();
        final String[] array2 = new String[array.length + 1];
        array2[0] = "jlc";
        for (int i = 0; i < array.length; ++i) {
            array2[i + 1] = array[i];
        }
        final jlcArgs jlcArgs = new jlcArgs();
        if (!jlcArgs.processArgs(array2)) {
            System.exit(1);
        }
        final Converter converter = new Converter();
        final Converter.PrintWriterProgressListener printWriterProgressListener = new Converter.PrintWriterProgressListener(new PrintWriter(System.out, true), jlcArgs.verbose_mode ? jlcArgs.verbose_level : 0);
        try {
            converter.convert(jlcArgs.filename, jlcArgs.output_filename, printWriterProgressListener);
        }
        catch (JavaLayerException ex) {
            System.err.println("Convertion failure: " + ex);
        }
        System.exit(0);
    }
    
    static class jlcArgs
    {
        public int which_c;
        public int output_mode;
        public boolean use_own_scalefactor;
        public float scalefactor;
        public String output_filename;
        public String filename;
        public boolean verbose_mode;
        public int verbose_level;
        
        public jlcArgs() {
            this.verbose_level = 3;
            this.which_c = 0;
            this.use_own_scalefactor = false;
            this.scalefactor = 32768.0f;
            this.verbose_mode = false;
        }
        
        public boolean processArgs(final String[] array) {
            this.filename = null;
            final Crc16[] array2 = { null };
            final int length = array.length;
            this.verbose_mode = false;
            this.output_mode = 0;
            this.output_filename = "";
            if (length < 2 || array[1].equals("-h")) {
                return this.Usage();
            }
            for (int i = 1; i < length; ++i) {
                if (array[i].charAt(0) == '-') {
                    if (array[i].startsWith("-v")) {
                        this.verbose_mode = true;
                        if (array[i].length() > 2) {
                            try {
                                this.verbose_level = Integer.parseInt(array[i].substring(2));
                            }
                            catch (NumberFormatException ex) {
                                System.err.println("Invalid verbose level. Using default.");
                            }
                        }
                        System.out.println("Verbose Activated (level " + this.verbose_level + ")");
                    }
                    else {
                        if (!array[i].equals("-p")) {
                            return this.Usage();
                        }
                        if (++i == length) {
                            System.out.println("Please specify an output filename after the -p option!");
                            System.exit(1);
                        }
                        this.output_filename = array[i];
                    }
                }
                else {
                    this.filename = array[i];
                    System.out.println("FileName = " + array[i]);
                    if (this.filename == null) {
                        return this.Usage();
                    }
                }
            }
            return this.filename != null || this.Usage();
        }
        
        public boolean Usage() {
            System.out.println("JavaLayer Converter :");
            System.out.println("  -v[x]         verbose mode. ");
            System.out.println("                default = 2");
            System.out.println("  -p name    output as a PCM wave file");
            System.out.println("");
            System.out.println("  More info on http://www.javazoom.net");
            return false;
        }
    }
}
