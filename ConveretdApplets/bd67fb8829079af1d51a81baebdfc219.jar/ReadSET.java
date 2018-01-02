import java.util.Hashtable;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Properties;

// 
// Decompiled by Procyon v0.5.30
// 

public class ReadSET
{
    public static void main(final String[] array) {
        try {
            getEnv("windir");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static String getEnv(final String s) {
        final Runtime runtime = Runtime.getRuntime();
        final Properties properties = new Properties();
        final String property = System.getProperty("os.name");
        System.out.println("THE OS NAME IS : " + property);
        System.out.println("INSIDE  READSET JAVA CODE.  ");
        try {
            Process process;
            if (property.equals("Windows 2000")) {
                process = runtime.exec("cmd /c set ");
            }
            else if (property.equals("Windows XP")) {
                process = runtime.exec("cmd /c set ");
            }
            else if (property.equals("Windows 2003")) {
                process = runtime.exec("cmd /c set ");
            }
            else if (property.equals("Windows Vista")) {
                process = runtime.exec("cmd /c set ");
            }
            else if (property.startsWith("Windows NT")) {
                process = runtime.exec("cmd /c set ");
            }
            else {
                process = runtime.exec("command.com /c set");
            }
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = "";
            while (line != null) {
                String substring = "";
                String substring2 = "";
                try {
                    line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }
                    final int index = line.indexOf(61);
                    substring = line.substring(0, index);
                    substring2 = line.substring(index + 1);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
                if (substring.equalsIgnoreCase("windir")) {
                    System.out.println("KEY   : " + substring);
                    System.out.println("VALUE : " + substring2);
                    ((Hashtable<String, String>)properties).put(substring, substring2);
                    break;
                }
                if (substring.equalsIgnoreCase("SYSTEMROOT")) {
                    System.out.println("KEY   : " + substring);
                    System.out.println("VALUE : " + substring2);
                    ((Hashtable<String, String>)properties).put(s, substring2);
                    break;
                }
            }
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
        return properties.getProperty(s);
    }
}
