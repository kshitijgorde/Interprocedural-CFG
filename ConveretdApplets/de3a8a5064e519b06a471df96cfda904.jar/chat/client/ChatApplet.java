// 
// Decompiled by Procyon v0.5.30
// 

package chat.client;

import java.awt.Component;
import chat.ae;
import chat.bh;
import chat.bk;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import chat.h;
import java.awt.Color;
import chat.aK;
import java.io.IOException;
import chat.ak;
import java.io.InputStream;
import chat.aD;
import java.util.zip.GZIPInputStream;
import java.net.URL;
import chat.aV;

public class ChatApplet extends aV
{
    public final void a() {
        super.b.a();
        System.runFinalization();
        System.gc();
    }
    
    public void init() {
        aV.c = this.getParameter("altHost");
        final URL codeBase = this.getCodeBase();
        if ((aV.b = this.getParameter("language")) == null || aV.b.length() == 0) {
            aV.b = "eng.lang";
        }
        while (true) {
            if (!aV.b.equals("ar.lang")) {
                aV.b = "eng.lang";
                try {
                    try {
                        final ak ak = new ak(new aD(new GZIPInputStream(new URL(codeBase.toString() + "Resources/languages/" + aV.b + "z").openStream())));
                    }
                    catch (NoClassDefFoundError noClassDefFoundError) {
                        final ak ak2 = new ak(new aD(new URL(codeBase.toString() + "Resources/languages/" + aV.b).openStream()));
                    }
                    catch (IOException ex2) {
                        final ak ak3 = new ak(new aD(new URL(codeBase.toString() + "Resources/languages/" + aV.b).openStream()));
                    }
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
                super.a = ak.a(548);
                System.out.println(super.a + " " + aK.a());
                Color background = Color.white;
                Color foreground = Color.black;
                final String parameter = this.getParameter("background");
                final String parameter2 = this.getParameter("textcolor");
                final String parameter3 = this.getParameter("themeID");
                final String parameter4 = this.getParameter("preferredPort");
                this.getParameter("COPPA1");
                this.getParameter("COPPA2");
                try {
                    if (parameter != null) {
                        background = h.a(parameter);
                    }
                }
                catch (NumberFormatException ex3) {}
                try {
                    if (parameter2 != null) {
                        foreground = h.a(parameter2);
                    }
                }
                catch (NumberFormatException ex4) {}
                try {
                    if (parameter3 != null) {
                        aV.c = Integer.parseInt(parameter3);
                    }
                }
                catch (NumberFormatException ex5) {}
                try {
                    if (parameter4 != null) {
                        aV.d = Integer.parseInt(parameter4);
                    }
                }
                catch (NumberFormatException ex6) {}
                this.setBackground(background);
                this.setForeground(foreground);
                this.setLayout(new BorderLayout());
                final String parameter5;
                if ((parameter5 = this.getParameter("embedded")) != null && parameter5.equalsIgnoreCase("true")) {
                    aV.a = true;
                }
                try {
                    final String parameter6;
                    if ((parameter6 = this.getParameter(aV.a ? "height" : "WindowHeight")) != null) {
                        aV.a = Integer.parseInt(parameter6);
                    }
                    final String parameter7;
                    if ((parameter7 = this.getParameter(aV.a ? "width" : "WindowWidth")) != null) {
                        aV.b = Integer.parseInt(parameter7);
                    }
                }
                catch (NumberFormatException ex7) {}
                aV.a = this;
                (super.a = new bk(this)).a(codeBase);
                if (bh.h == null) {
                    bh.h = super.a.a("icon_display.gif", false);
                }
                final String parameter8;
                if ((parameter8 = this.getParameter("RoomsTab")) != null && parameter8.equalsIgnoreCase("false")) {
                    super.a.e();
                }
                final String parameter9;
                if ((parameter9 = this.getParameter("UsersTab")) != null && parameter9.equalsIgnoreCase("false")) {
                    super.a.f();
                }
                super.b = new ae(super.a, super.a.c == null, super.a.o == -999);
                aV.a = super.b;
                this.add("Center", super.b);
                super.b.validate();
                return;
            }
            continue;
        }
    }
    
    public void stop() {
        if (super.a != null && (aV.a || "TRUE".equalsIgnoreCase(this.getParameter("BrowserClose")))) {
            super.a.c();
        }
        System.runFinalization();
        System.gc();
    }
}
