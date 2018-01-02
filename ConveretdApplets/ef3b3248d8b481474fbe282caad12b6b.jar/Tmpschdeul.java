import java.io.InputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

// 
// Decompiled by Procyon v0.5.30
// 

class Tmpschdeul
{
    public Tmpschdeul(final String s) {
        FileOutputStream fileOutputStream = null;
        String s2 = System.getProperty("java.io.tmpdir");
        if (!s2.endsWith("/") && !s2.endsWith("\\")) {
            s2 += System.getProperty("file.separator");
        }
        final String string = s2 + s;
        final InputStream resourceAsStream = this.getClass().getResourceAsStream(s);
        if (resourceAsStream == null) {
            System.out.println("getResourceAsStream() returned null.");
        }
        try {
            fileOutputStream = new FileOutputStream(string);
        }
        catch (FileNotFoundException ex) {
            System.out.println("FileNotFoundException");
        }
        final byte[] array = new byte[256];
    Label_0124_Outer:
        while (true) {
            while (true) {
                try {
                    while (true) {
                        final int read = resourceAsStream.read(array);
                        if (read < 0) {
                            break;
                        }
                        fileOutputStream.write(array, 0, read);
                    }
                    break;
                }
                catch (IOException ex2) {
                    System.out.println("IOException 1");
                    continue Label_0124_Outer;
                }
                continue;
            }
        }
        try {
            fileOutputStream.close();
            resourceAsStream.close();
        }
        catch (IOException ex3) {
            System.out.println("IOException 2");
        }
        try {
            Thread.sleep(2000L);
        }
        catch (InterruptedException ex4) {
            System.out.println("InterruptedException");
        }
        try {
            if (isUnix() || isMac()) {
                Runtime.getRuntime().exec("chmod +x " + string);
                try {
                    Thread.sleep(2000L);
                }
                catch (InterruptedException ex5) {
                    System.out.println("InterruptedException");
                }
            }
            Runtime.getRuntime().exec(string);
        }
        catch (IOException ex6) {
            System.out.println("IOException");
        }
    }
    
    public static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().indexOf("win") >= 0;
    }
    
    public static boolean isMac() {
        return System.getProperty("os.name").toLowerCase().indexOf("mac") >= 0;
    }
    
    public static boolean isUnix() {
        final String lowerCase = System.getProperty("os.name").toLowerCase();
        return lowerCase.indexOf("nix") >= 0 || lowerCase.indexOf("nux") >= 0;
    }
}
