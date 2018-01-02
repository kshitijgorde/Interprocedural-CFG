// 
// Decompiled by Procyon v0.5.30
// 

package JUpload;

import java.awt.Component;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JFrame;

public class AboutWindow extends JFrame
{
    AboutWindow() {
        this.setTitle("JUpload - About Page");
        this.setSize(300, 200);
        this.setBackground(Color.orange);
        final String strApache = "* ====================================================================\n *\n * The Apache Software License, Version 1.1\n *\n * Copyright (c) 1999-2003 The Apache Software Foundation.  All rights\n * reserved.\n *\n * Redistribution and use in source and binary forms, with or without\n * modification, are permitted provided that the following conditions\n * are met:\n *\n * 1. Redistributions of source code must retain the above copyright\n *    notice, this list of conditions and the following disclaimer.\n *\n * 2. Redistributions in binary form must reproduce the above copyright\n *    notice, this list of conditions and the following disclaimer in\n *    the documentation and/or other materials provided with the\n *    distribution.\n *\n * 3. The end-user documentation included with the redistribution, if\n *    any, must include the following acknowlegement:\n *       \"This product includes software developed by the\n *        Apache Software Foundation (http://www.apache.org/).\"\n *    Alternately, this acknowlegement may appear in the software itself,\n *    if and wherever such third-party acknowlegements normally appear.\n *\n * 4. The names \"The Jakarta Project\", \"Commons\", and \"Apache Software\n *    Foundation\" must not be used to endorse or promote products derived\n *    from this software without prior written permission. For written\n *    permission, please contact apache@apache.org.\n *\n * 5. Products derived from this software may not be called \"Apache\"\n *    nor may \"Apache\" appear in their names without prior written\n *    permission of the Apache Group.\n *\n * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED\n * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES\n * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE\n * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR\n * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,\n * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT\n * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF\n * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND\n * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,\n * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT\n * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF\n * SUCH DAMAGE.\n * ====================================================================\n *\n * This software consists of voluntary contributions made by many\n * individuals on behalf of the Apache Software Foundation.  For more\n * information on the Apache Software Foundation, please see\n * <http://www.apache.org/>.\n *\n * [Additional notices, if required by prior licensing conditions]\n *\n */";
        final JTextArea ta = new JTextArea("JUpload\n\nupload applet for multiple transfers at once\n\nCopyright 2003 Haller Systemservice\nhttp://www.haller-systemservice.net/\ninfo@haller-systemservice.net\n\n" + strApache);
        ta.setBackground(Color.orange);
        final JScrollPane scroll = new JScrollPane(ta);
        this.getContentPane().add(scroll);
        this.setVisible(true);
        this.show();
    }
}
