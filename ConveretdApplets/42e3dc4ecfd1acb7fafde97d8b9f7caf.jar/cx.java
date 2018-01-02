import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.FileOutputStream;
import java.awt.FileDialog;
import au.com.rocketdog.project.awc.applet.Main;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class cx implements ActionListener, ClipboardOwner
{
    private cl a;
    private j b;
    private Clipboard c;
    private p d;
    private int e;
    private int f;
    
    public int a() {
        return this.e;
    }
    
    public void a(final int e) {
        this.e = e;
    }
    
    public int b() {
        return this.f;
    }
    
    public void b(final int f) {
        this.f = f;
    }
    
    public void lostOwnership(final Clipboard clipboard, final Transferable transferable) {
    }
    
    public cx(final cl a, final j b) {
        this.c = Toolkit.getDefaultToolkit().getSystemClipboard();
        this.a = a;
        this.b = b;
        this.d = p.a();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final MenuItem menuItem = (MenuItem)actionEvent.getSource();
        if (menuItem.getName().equals("detach")) {
            this.b.h(this.a.getName());
        }
        else if (menuItem.getName().equals("attach")) {
            this.b.c(this.a.getName());
        }
        else if (menuItem.getName().equals("exit")) {
            this.a.c();
        }
        else if (menuItem.getName().equals("help")) {
            Main.b("http://" + Main.b + "/awc/html/lang_" + n.b().s() + "/content/popup/chat_help.html", "chathelp");
        }
        else if (menuItem.getName().equals("log")) {
            final FileDialog fileDialog = new FileDialog(Main.h(), Main.p.a("chat.setlogfile"), 1);
            fileDialog.setDirectory(Main.i);
            fileDialog.setFile(this.a.getName() + ".log");
            fileDialog.setVisible(true);
            try {
                this.a.a(new PrintStream(new FileOutputStream(fileDialog.getDirectory() + fileDialog.getFile())));
            }
            catch (IOException ex) {
                b.a(ex, 3);
            }
        }
        else if (menuItem.getName().equals("close")) {
            final al al = new al(Main.h(), Main.p.a("chat.makeroom.dialog.confirmdelete") + " " + this.a.getName() + "?", 100, 200);
            al.setLocation(this.a.getLocation().x + this.a.getSize().width / 2, this.a.getLocation().y + this.a.getSize().height / 2);
            al.a.addActionListener(new c7(this.a));
            al.setVisible(true);
        }
        else if (menuItem.getName().equals("messops")) {
            final ce ce = new ce(Main.h(), Main.p.a("chat.makeroom.dialog.opmessage"));
            ce.a.addActionListener(new dd(this.a, 3, ce));
            ce.setSize(300, 100);
            ce.setVisible(true);
        }
        else if (menuItem.getName().equals("summon")) {
            final ce ce2 = new ce(Main.h(), Main.p.a("chat.makeroom.dialog.summonname"));
            ce2.a.addActionListener(new dd(this.a, 5, ce2));
            ce2.setSize(300, 100);
            ce2.setVisible(true);
        }
        else if (menuItem.getName().equals("topic")) {
            final ce ce3 = new ce(Main.h(), Main.p.a("chat.makeroom.dialog.newtopic"));
            ce3.a.addActionListener(new dd(this.a, 2, ce3));
            ce3.setSize(300, 100);
            ce3.setVisible(true);
        }
        else if (menuItem.getName().equals("ban")) {
            this.d.j("MODE " + this.a.g() + " +b");
        }
        else if (menuItem.getName().equals("enable_pm")) {
            p.a(true);
            menuItem.setLabel("Disable PM");
            menuItem.setName("disable_pm");
        }
        else if (menuItem.getName().equals("disable_pm")) {
            p.a(false);
            menuItem.setLabel("Enable PM");
            menuItem.setName("enable_pm");
        }
        else if (menuItem.getName().equals("ignore")) {
            Main.f(this.a.getName());
        }
        else if (menuItem.getName().equals("close")) {
            this.d.j("PRIVMSG ChanServ :DROP " + this.a.g() + " awc");
            this.a.c();
        }
        else if (menuItem.getName().equals("call")) {
            this.c.setContents(new StringSelection(this.a.i()), this);
        }
        else if (menuItem.getName().equals("cline")) {
            this.c.setContents(new StringSelection(this.a.b(this.a(), this.b())), this);
        }
    }
}
