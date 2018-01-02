import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Frame;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

public class ProgBarFrame extends Dialog implements Runnable
{
    ProgBar p;
    String title;
    int maxValue;
    
    public ProgBarFrame(final Frame frame, final String title, final int n, final int n2, final int n3) {
        super(frame, title, false);
        this.p = new ProgBar();
        this.p.maxValue = n;
        this.title = title;
        this.maxValue = n;
        this.setResizable(false);
        this.setLayout(new GridLayout(1, 1));
        this.add(this.p);
        this.pack();
        if (n2 == 0 && n3 == 0) {
            j_util.centerDialog(this, frame);
            SpellEngine.setProgPos(this.location());
            return;
        }
        this.move(n2, n3);
    }
    
    public void run() {
        this.show();
    }
    
    public void setProgress(final int progress) {
        this.p.setProgress(progress);
        this.setTitle(String.valueOf(this.title) + " " + Integer.toString((int)(progress * 100.0 / this.maxValue)) + "%");
    }
}
