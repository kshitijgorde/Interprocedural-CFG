// 
// Decompiled by Procyon v0.5.30
// 

package ji.graphic;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.FocusEvent;
import ji.io.h;
import java.awt.Container;
import ji.awt.jiAttachmentDataCheckbox;
import java.awt.event.FocusListener;
import java.awt.Label;
import java.awt.event.ActionListener;
import java.awt.Button;
import java.awt.Color;
import ji.awt.gv;
import java.awt.ScrollPane;
import java.awt.event.ItemListener;
import java.awt.CheckboxGroup;
import ji.res.s;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.event.WindowListener;
import ji.document.gm;
import java.awt.Frame;
import java.awt.Dialog;
import java.awt.Component;
import java.awt.TextField;
import ji.awt.fs;
import java.util.Vector;
import ji.document.b2;
import ji.v1base.bl;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

class xz extends WindowAdapter
{
    private final /* synthetic */ b1 a;
    
    xz(final b1 a) {
        this.a = a;
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        this.a.f();
    }
}
