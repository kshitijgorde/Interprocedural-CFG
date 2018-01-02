// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.key;

import org.xmodel.xpath.expression.StatefulContext;
import org.xmodel.xpath.expression.IContext;
import org.xidget.ifeature.IWidgetCreationFeature;
import org.xidget.ifeature.IWidgetContextFeature;
import org.xidget.Creator;
import java.awt.Component;
import org.xmodel.xaction.IXAction;
import org.xidget.IXidget;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.AWTEvent;
import org.xidget.ifeature.IKeyFeature;
import java.util.HashMap;
import java.util.Map;
import org.xidget.util.KeyTree;
import java.awt.event.AWTEventListener;

public class KeyManager
{
    private AWTEventListener listener;
    private KeyTree<KeyBinding> tree;
    private static KeyManager instance;
    private static final Map<Integer, String> lookup;
    
    static {
        (lookup = new HashMap<Integer, String>()).put(48, "0");
        KeyManager.lookup.put(49, "1");
        KeyManager.lookup.put(50, "2");
        KeyManager.lookup.put(51, "3");
        KeyManager.lookup.put(52, "4");
        KeyManager.lookup.put(53, "5");
        KeyManager.lookup.put(54, "6");
        KeyManager.lookup.put(55, "7");
        KeyManager.lookup.put(56, "8");
        KeyManager.lookup.put(57, "9");
        KeyManager.lookup.put(65, "a");
        KeyManager.lookup.put(66, "b");
        KeyManager.lookup.put(67, "c");
        KeyManager.lookup.put(68, "d");
        KeyManager.lookup.put(69, "e");
        KeyManager.lookup.put(70, "f");
        KeyManager.lookup.put(71, "g");
        KeyManager.lookup.put(72, "h");
        KeyManager.lookup.put(73, "i");
        KeyManager.lookup.put(74, "j");
        KeyManager.lookup.put(75, "k");
        KeyManager.lookup.put(76, "l");
        KeyManager.lookup.put(77, "m");
        KeyManager.lookup.put(78, "n");
        KeyManager.lookup.put(79, "o");
        KeyManager.lookup.put(80, "p");
        KeyManager.lookup.put(81, "q");
        KeyManager.lookup.put(82, "r");
        KeyManager.lookup.put(83, "s");
        KeyManager.lookup.put(84, "t");
        KeyManager.lookup.put(85, "u");
        KeyManager.lookup.put(86, "v");
        KeyManager.lookup.put(87, "w");
        KeyManager.lookup.put(88, "x");
        KeyManager.lookup.put(89, "y");
        KeyManager.lookup.put(90, "z");
        KeyManager.lookup.put(150, "&");
        KeyManager.lookup.put(512, "@");
        KeyManager.lookup.put(519, "(");
        KeyManager.lookup.put(522, ")");
        KeyManager.lookup.put(161, "{");
        KeyManager.lookup.put(162, "}");
        KeyManager.lookup.put(91, "[");
        KeyManager.lookup.put(93, "]");
        KeyManager.lookup.put(153, "<");
        KeyManager.lookup.put(160, ">");
        KeyManager.lookup.put(47, "/");
        KeyManager.lookup.put(92, "\\");
        KeyManager.lookup.put(45, "-");
        KeyManager.lookup.put(521, "+");
        KeyManager.lookup.put(151, "*");
        KeyManager.lookup.put(520, "#");
        KeyManager.lookup.put(46, ".");
        KeyManager.lookup.put(222, "'");
        KeyManager.lookup.put(152, "\"");
        KeyManager.lookup.put(59, ";");
        KeyManager.lookup.put(523, "_");
        KeyManager.lookup.put(513, ":");
        KeyManager.lookup.put(112, IKeyFeature.Key.f1.toString());
        KeyManager.lookup.put(113, IKeyFeature.Key.f2.toString());
        KeyManager.lookup.put(114, IKeyFeature.Key.f3.toString());
        KeyManager.lookup.put(115, IKeyFeature.Key.f4.toString());
        KeyManager.lookup.put(116, IKeyFeature.Key.f5.toString());
        KeyManager.lookup.put(117, IKeyFeature.Key.f6.toString());
        KeyManager.lookup.put(118, IKeyFeature.Key.f7.toString());
        KeyManager.lookup.put(119, IKeyFeature.Key.f8.toString());
        KeyManager.lookup.put(120, IKeyFeature.Key.f9.toString());
        KeyManager.lookup.put(121, IKeyFeature.Key.f10.toString());
        KeyManager.lookup.put(122, IKeyFeature.Key.f11.toString());
        KeyManager.lookup.put(123, IKeyFeature.Key.f12.toString());
        KeyManager.lookup.put(61440, IKeyFeature.Key.f13.toString());
        KeyManager.lookup.put(61441, IKeyFeature.Key.f14.toString());
        KeyManager.lookup.put(61442, IKeyFeature.Key.f15.toString());
        KeyManager.lookup.put(61443, IKeyFeature.Key.f16.toString());
        KeyManager.lookup.put(61444, IKeyFeature.Key.f17.toString());
        KeyManager.lookup.put(61445, IKeyFeature.Key.f18.toString());
        KeyManager.lookup.put(61446, IKeyFeature.Key.f19.toString());
        KeyManager.lookup.put(61447, IKeyFeature.Key.f20.toString());
        KeyManager.lookup.put(61448, IKeyFeature.Key.f21.toString());
        KeyManager.lookup.put(61449, IKeyFeature.Key.f22.toString());
        KeyManager.lookup.put(61450, IKeyFeature.Key.f23.toString());
        KeyManager.lookup.put(61451, IKeyFeature.Key.f24.toString());
        KeyManager.lookup.put(18, IKeyFeature.Key.alt.toString());
        KeyManager.lookup.put(65406, IKeyFeature.Key.altgraph.toString());
        KeyManager.lookup.put(17, IKeyFeature.Key.control.toString());
        KeyManager.lookup.put(157, IKeyFeature.Key.meta.toString());
        KeyManager.lookup.put(16, IKeyFeature.Key.shift.toString());
        KeyManager.lookup.put(9, IKeyFeature.Key.tab.toString());
        KeyManager.lookup.put(27, IKeyFeature.Key.escape.toString());
        KeyManager.lookup.put(8, IKeyFeature.Key.backspace.toString());
        KeyManager.lookup.put(10, IKeyFeature.Key.enter.toString());
        KeyManager.lookup.put(20, IKeyFeature.Key.capslock.toString());
        KeyManager.lookup.put(144, IKeyFeature.Key.numlock.toString());
        KeyManager.lookup.put(145, IKeyFeature.Key.scrolllock.toString());
        KeyManager.lookup.put(36, IKeyFeature.Key.home.toString());
        KeyManager.lookup.put(35, IKeyFeature.Key.end.toString());
        KeyManager.lookup.put(155, IKeyFeature.Key.insert.toString());
        KeyManager.lookup.put(33, IKeyFeature.Key.pageup.toString());
        KeyManager.lookup.put(34, IKeyFeature.Key.pagedown.toString());
        KeyManager.lookup.put(65489, IKeyFeature.Key.cut.toString());
        KeyManager.lookup.put(65487, IKeyFeature.Key.paste.toString());
        KeyManager.lookup.put(65485, IKeyFeature.Key.copy.toString());
        KeyManager.lookup.put(106, IKeyFeature.Key.multiply.toString());
        KeyManager.lookup.put(109, IKeyFeature.Key.subtract.toString());
        KeyManager.lookup.put(30, IKeyFeature.Key.accept.toString());
        KeyManager.lookup.put(65481, IKeyFeature.Key.again.toString());
        KeyManager.lookup.put(256, IKeyFeature.Key.allcandidates.toString());
        KeyManager.lookup.put(240, IKeyFeature.Key.alphanumeric.toString());
        KeyManager.lookup.put(65368, IKeyFeature.Key.begin.toString());
        KeyManager.lookup.put(3, IKeyFeature.Key.cancel.toString());
        KeyManager.lookup.put(12, IKeyFeature.Key.clear.toString());
        KeyManager.lookup.put(525, IKeyFeature.Key.contextmenu.toString());
        KeyManager.lookup.put(28, IKeyFeature.Key.convert.toString());
        KeyManager.lookup.put(516, IKeyFeature.Key.eurosign.toString());
        KeyManager.lookup.put(65488, IKeyFeature.Key.find.toString());
        KeyManager.lookup.put(243, IKeyFeature.Key.fullwidth.toString());
        KeyManager.lookup.put(244, IKeyFeature.Key.halfwidth.toString());
        KeyManager.lookup.put(156, IKeyFeature.Key.help.toString());
        KeyManager.lookup.put(224, IKeyFeature.Key.keypadup.toString());
        KeyManager.lookup.put(226, IKeyFeature.Key.keypadleft.toString());
        KeyManager.lookup.put(227, IKeyFeature.Key.keypadright.toString());
        KeyManager.lookup.put(225, IKeyFeature.Key.keypaddown.toString());
        KeyManager.lookup.put(38, IKeyFeature.Key.up.toString());
        KeyManager.lookup.put(37, IKeyFeature.Key.left.toString());
        KeyManager.lookup.put(39, IKeyFeature.Key.right.toString());
        KeyManager.lookup.put(40, IKeyFeature.Key.down.toString());
        KeyManager.lookup.put(96, IKeyFeature.Key.numpad0.toString());
        KeyManager.lookup.put(97, IKeyFeature.Key.numpad1.toString());
        KeyManager.lookup.put(98, IKeyFeature.Key.numpad2.toString());
        KeyManager.lookup.put(99, IKeyFeature.Key.numpad3.toString());
        KeyManager.lookup.put(100, IKeyFeature.Key.numpad4.toString());
        KeyManager.lookup.put(101, IKeyFeature.Key.numpad5.toString());
        KeyManager.lookup.put(102, IKeyFeature.Key.numpad6.toString());
        KeyManager.lookup.put(103, IKeyFeature.Key.numpad7.toString());
        KeyManager.lookup.put(104, IKeyFeature.Key.numpad8.toString());
        KeyManager.lookup.put(105, IKeyFeature.Key.numpad9.toString());
        KeyManager.lookup.put(19, IKeyFeature.Key.pause.toString());
        KeyManager.lookup.put(154, IKeyFeature.Key.printscreen.toString());
        KeyManager.lookup.put(108, IKeyFeature.Key.separator.toString());
        KeyManager.lookup.put(32, IKeyFeature.Key.space.toString());
        KeyManager.lookup.put(65480, IKeyFeature.Key.stop.toString());
        KeyManager.lookup.put(65483, IKeyFeature.Key.undo.toString());
        KeyManager.lookup.put(524, IKeyFeature.Key.windows.toString());
        KeyManager.lookup.put(44, IKeyFeature.Key.comma.toString());
    }
    
    protected KeyManager() {
        this.listener = new AWTEventListener() {
            @Override
            public void eventDispatched(final AWTEvent awtEvent) {
                if (awtEvent instanceof KeyEvent) {
                    final KeyEvent keyEvent = (KeyEvent)awtEvent;
                    if (keyEvent.getID() == 401) {
                        KeyManager.this.keyPressed(keyEvent);
                    }
                    else if (keyEvent.getID() == 402) {
                        KeyManager.this.keyReleased(keyEvent);
                    }
                }
                else if (awtEvent instanceof FocusEvent) {
                    KeyManager.this.tree.reset();
                }
            }
        };
        this.tree = new KeyTree<KeyBinding>();
        Toolkit.getDefaultToolkit().addAWTEventListener(this.listener, 12L);
    }
    
    public void bind(final IXidget xidget, final String s, final boolean b, final IXAction ixAction) {
        this.tree.bind(s, new KeyBinding(xidget, b, ixAction));
    }
    
    public void unbind(final IXidget xidget, final String s, final IXAction ixAction) {
        this.tree.unbind(s);
    }
    
    private IXidget findXidget(Component parent) {
        while (parent != null) {
            final IXidget xidget = Creator.getInstance().getXidget(parent);
            if (xidget != null) {
                return xidget;
            }
            parent = parent.getParent();
        }
        return null;
    }
    
    private void keyPressed(final KeyEvent keyEvent) {
        final String s = KeyManager.lookup.get(keyEvent.getKeyCode());
        if (s != null) {
            final KeyBinding keyBinding = this.tree.keyDown(s);
            if (keyBinding != null) {
                final IXidget xidget = this.findXidget(keyEvent.getComponent());
                if (xidget != null) {
                    if (keyBinding.override) {
                        keyEvent.consume();
                    }
                    final StatefulContext context = xidget.getFeature(IWidgetContextFeature.class).getContext(xidget.getFeature(IWidgetCreationFeature.class).getLastWidgets()[0]);
                    context.set("here", keyBinding.xidget.getConfig());
                    keyBinding.script.run(context);
                }
            }
        }
    }
    
    private void keyReleased(final KeyEvent keyEvent) {
        final String s = KeyManager.lookup.get(keyEvent.getKeyCode());
        if (s != null) {
            this.tree.keyUp(s);
        }
    }
    
    public static KeyManager getInstance() {
        if (KeyManager.instance == null) {
            KeyManager.instance = new KeyManager();
        }
        return KeyManager.instance;
    }
    
    private static class KeyBinding
    {
        public IXidget xidget;
        public boolean override;
        public IXAction script;
        
        public KeyBinding(final IXidget xidget, final boolean override, final IXAction script) {
            this.xidget = xidget;
            this.override = override;
            this.script = script;
        }
    }
}
