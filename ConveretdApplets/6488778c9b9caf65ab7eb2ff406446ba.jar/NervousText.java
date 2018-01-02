import java.awt.Event;
import java.awt.Graphics;
import java.awt.Font;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class NervousText extends Applet implements Runnable
{
    char[] separated;
    String s;
    Thread killme;
    int i;
    int x_coord;
    int y_coord;
    String num;
    int speed;
    int counter;
    boolean threadSuspended;
    
    public void init() {
        this.resize(150, 50);
        this.setFont(new Font("TimesRoman", 1, 36));
        this.s = this.getParameter("text");
        if (this.s == null) {
            this.s = "HotJava";
        }
        this.separated = new char[this.s.length()];
        this.s.getChars(0, this.s.length(), this.separated, 0);
    }
    
    public void start() {
        if (this.killme == null) {
            (this.killme = new Thread(this)).start();
        }
    }
    
    public void stop() {
        this.killme = null;
    }
    
    public void run() {
        while (this.killme != null) {
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException ex) {}
            this.repaint();
        }
        this.killme = null;
    }
    
    public void paint(final Graphics g) {
        this.i = 0;
        while (this.i < this.s.length()) {
            this.x_coord = (int)(Math.random() * 10.0 + 15 * this.i);
            this.y_coord = (int)(Math.random() * 10.0 + 36.0);
            g.drawChars(this.separated, this.i, 1, this.x_coord, this.y_coord);
            ++this.i;
        }
    }
    
    public boolean mouseDown(final Event evt, final int x, final int y) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0         /* this */
        //     1: getfield        NervousText.threadSuspended:Z
        //     4: ifeq            17
        //     7: aload_0         /* this */
        //     8: getfield        NervousText.killme:Ljava/lang/Thread;
        //    11: invokevirtual   java/lang/Thread.resume:()V
        //    14: goto            24
        //    17: aload_0         /* this */
        //    18: getfield        NervousText.killme:Ljava/lang/Thread;
        //    21: invokevirtual   java/lang/Thread.suspend:()V
        //    24: aload_0         /* this */
        //    25: aload_0         /* this */
        //    26: getfield        NervousText.threadSuspended:Z
        //    29: ifeq            36
        //    32: iconst_0       
        //    33: goto            37
        //    36: iconst_1       
        //    37: putfield        NervousText.threadSuspended:Z
        //    40: iconst_1       
        //    41: ireturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ----------------
        //  0      42      0     this  LNervousText;
        //  0      42      1     evt   Ljava/awt/Event;
        //  0      42      2     x     I
        //  0      42      3     y     I
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException: 1
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:1974)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public NervousText() {
        this.speed = 35;
        this.threadSuspended = false;
    }
}
