// 
// Decompiled by Procyon v0.5.30
// 

package webphone;

import a.a.a.a.b;
import java.awt.Component;
import java.awt.LayoutManager;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import a.a.a.a.a;
import javax.swing.JPanel;

public class bs extends JPanel
{
    a a;
    JScrollPane do;
    JTextArea if;
    
    public bs() {
        this.a = new a();
        this.do = new JScrollPane();
        this.if = new JTextArea();
        try {
            this.a();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void a() throws Exception {
        this.setLayout(this.a);
        this.a.if(400);
        this.a.a(249);
        this.do.getViewport().add(this.if);
        this.add(this.do, new b(9, 8, 380, 230));
    }
}
