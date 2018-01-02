// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.io.IOException;
import java.awt.datatransfer.DataFlavor;
import java.util.Date;
import java.text.DateFormat;
import com.hw.client.util.a;
import java.util.Vector;
import com.wimba.clients.vboard.n;
import java.awt.datatransfer.Transferable;
import javax.swing.tree.DefaultMutableTreeNode;

public final class aR extends DefaultMutableTreeNode implements Transferable
{
    private n a;
    private Vector b;
    private aH c;
    private boolean d;
    
    public aR() {
    }
    
    public aR(final n a, final boolean d, final aH c) {
        this.d = d;
        this.setParent(this.parent);
        this.a = a;
        this.b = a.W();
        this.c = c;
        final StringBuffer sb;
        (sb = new StringBuffer(200)).append("<html><body>&nbsp;<font color=\"black\">");
        if (this.a.D() || this.a.G()) {
            if (this.c.c() != null) {
                this.b(sb);
            }
            if (!this.b.contains("empty")) {
                if (this.b.contains("default")) {
                    this.a(sb);
                    this.d(sb);
                    this.c(sb);
                }
                else {
                    if (this.b.contains("author")) {
                        this.a(sb);
                    }
                    if (this.b.contains("length")) {
                        this.d(sb);
                    }
                    if (this.b.contains("time")) {
                        this.c(sb);
                    }
                }
            }
            if (this.c.g()) {
                sb.append("<img src=\"").append(this.getClass().getClassLoader().getResource("images/vboard/sound_small_icon.png")).append("\"></img>");
            }
            if (this.c.h()) {
                sb.append("<img src=\"").append(this.getClass().getClassLoader().getResource("images/vboard/text_small_icon.png")).append("\"></img>");
            }
        }
        else if (this.d) {
            this.b(sb);
        }
        else {
            this.a(sb);
        }
        final String string = sb.append("</font></body></html>").toString();
        com.hw.client.util.a.b("VBTreeNode.buildItem: " + string);
        this.setUserObject(string);
    }
    
    public final aH a() {
        return this.c;
    }
    
    public final boolean b() {
        return this.d;
    }
    
    private void a(final StringBuffer sb) {
        if (this.c.d() != null) {
            final String e = this.a.e("main_tree_from");
            if (this.a.D() || this.a.G()) {
                sb.append(" ").append(e).append(" ");
            }
            sb.append("<b>").append(cs.a.a(this.c.d())).append("</b>");
        }
    }
    
    private void b(final StringBuffer sb) {
        com.hw.client.util.a.b("headers.getSubject():" + this.c.c());
        com.hw.client.util.a.b("Escaped: " + cs.a.a(this.c.c()));
        sb.append("<b>").append(cs.a.a(this.c.c())).append("</b>");
    }
    
    private void c(final StringBuffer sb) {
        if (this.c.f() != -1L) {
            sb.append(" ").append(cs.a.a(DateFormat.getDateInstance(3).format(new Date(this.c.f()))));
        }
    }
    
    private void d(final StringBuffer sb) {
        if (this.c.g() && this.c.k() != -1) {
            final int k = this.c.k();
            final StringBuffer sb2;
            (sb2 = new StringBuffer()).append(" (").append(k / 60).append("'").append(k % 60).append("\"").append(")");
            sb.append(cs.a.a(sb2.toString()));
        }
    }
    
    public final boolean isLeaf() {
        if (this.c == null) {
            return super.isLeaf();
        }
        return !this.c.i() && super.isLeaf();
    }
    
    public final Object getTransferData(final DataFlavor dataFlavor) {
        if (com.hw.client.util.a.a()) {
            com.hw.client.util.a.b("VBTreeNode.getTransferData: " + dataFlavor.toString());
        }
        if (!this.a.K()) {
            if (com.hw.client.util.a.a()) {
                com.hw.client.util.a.b("VBTreeNode.getTransferData: cannot reorder");
            }
            return null;
        }
        try {
            if (dataFlavor.match(new DataFlavor("application/x-java-serialized-object; class=com.wimba.common.vboard.MessageHeaders"))) {
                com.hw.client.util.a.c("required MessageHeaders type");
                return this.c;
            }
            return null;
        }
        catch (Exception ex) {
            com.hw.client.util.a.b("Error while getting data", ex);
            throw new IOException(ex.getMessage());
        }
    }
    
    public final DataFlavor[] getTransferDataFlavors() {
        if (com.hw.client.util.a.a()) {
            com.hw.client.util.a.b("VBTreeNode.getTransferDataFlavors");
        }
        if (!this.a.K() && !this.a.J()) {
            if (com.hw.client.util.a.a()) {
                com.hw.client.util.a.b("VBTreeNode.getTransferData: cannot reorder or import");
            }
            return null;
        }
        try {
            return new DataFlavor[] { new DataFlavor("application/x-java-serialized-object; class=com.wimba.common.vboard.MessageHeaders") };
        }
        catch (ClassNotFoundException ex) {
            return null;
        }
    }
    
    public final boolean isDataFlavorSupported(final DataFlavor dataFlavor) {
        try {
            if (com.hw.client.util.a.a()) {
                com.hw.client.util.a.b("VBTreeNode.isDataFlavorSupported");
            }
            final boolean match = dataFlavor.match(new DataFlavor("application/x-java-serialized-object; class=com.wimba.common.vboard.MessageHeaders"));
            if (com.hw.client.util.a.a()) {
                com.hw.client.util.a.b("VBTreeNode.isDataFlavorSupported: return : " + match);
            }
            return match;
        }
        catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
