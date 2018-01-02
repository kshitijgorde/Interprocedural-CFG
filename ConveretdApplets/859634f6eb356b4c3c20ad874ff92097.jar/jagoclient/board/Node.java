// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient.board;

import rene.util.list.Tree;
import rene.util.xml.XmlWriter;
import java.io.PrintWriter;
import rene.util.list.ListElement;
import rene.util.list.ListClass;

class Node
{
    ListClass Actions;
    int N;
    boolean Main;
    ListClass Changes;
    public int Pw;
    public int Pb;
    
    public Node(final int n) {
        this.Actions = new ListClass();
        this.N = n;
        this.Main = false;
        this.Changes = new ListClass();
        final boolean b = false;
        this.Pb = (b ? 1 : 0);
        this.Pw = (b ? 1 : 0);
    }
    
    public void addaction(final Action action) {
        this.Actions.append(new ListElement(action));
    }
    
    public void expandaction(final Action action) {
        final ListElement find = this.find(action.type());
        if (find == null) {
            this.addaction(action);
            return;
        }
        ((Action)find.content()).addargument(action.argument());
    }
    
    public void toggleaction(final Action action) {
        final ListElement find = this.find(action.type());
        if (find == null) {
            this.addaction(action);
            return;
        }
        ((Action)find.content()).toggleargument(action.argument());
    }
    
    ListElement find(final String s) {
        for (ListElement listElement = this.Actions.first(); listElement != null; listElement = listElement.next()) {
            if (((Action)listElement.content()).type().equals(s)) {
                return listElement;
            }
        }
        return null;
    }
    
    public boolean contains(final String s) {
        return this.find(s) != null;
    }
    
    public void prependaction(final Action action) {
        this.Actions.prepend(new ListElement(action));
    }
    
    public void insertaction(final Action action, final ListElement listElement) {
        this.Actions.insert(new ListElement(action), listElement);
    }
    
    public void removeaction(final ListElement listElement) {
        this.Actions.remove(listElement);
    }
    
    public void setaction(final String s, final String s2, final boolean b) {
        ListElement listElement = this.Actions.first();
        while (listElement != null) {
            final Action action = (Action)listElement.content();
            if (action.type().equals(s)) {
                if (s2.equals("")) {
                    this.Actions.remove(listElement);
                    return;
                }
                final ListElement arguments = action.arguments();
                if (arguments != null) {
                    arguments.content(s2);
                    return;
                }
                action.addargument(s2);
                return;
            }
            else {
                listElement = listElement.next();
            }
        }
        if (b) {
            this.prependaction(new Action(s, s2));
            return;
        }
        this.addaction(new Action(s, s2));
    }
    
    public void setaction(final String s, final String s2) {
        this.setaction(s, s2, false);
    }
    
    public String getaction(final String s) {
        ListElement listElement = this.Actions.first();
        while (listElement != null) {
            final Action action = (Action)listElement.content();
            if (action.type().equals(s)) {
                final ListElement arguments = action.arguments();
                if (arguments != null) {
                    return (String)arguments.content();
                }
                return "";
            }
            else {
                listElement = listElement.next();
            }
        }
        return "";
    }
    
    public void print(final PrintWriter printWriter) {
        printWriter.print(";");
        for (ListElement listElement = this.Actions.first(); listElement != null; listElement = listElement.next()) {
            ((Action)listElement.content()).print(printWriter);
        }
        printWriter.println("");
    }
    
    public void print(final XmlWriter xmlWriter, final int n) {
        xmlWriter.startTagStart("Node");
        if (this.contains("N")) {
            xmlWriter.printArg("name", this.getaction("N"));
        }
        xmlWriter.startTagEndNewLine();
        ListElement listElement = this.Actions.first();
        int n2 = this.N - 1;
        while (listElement != null) {
            final Action action = (Action)listElement.content();
            action.print(xmlWriter, n, n2);
            if (action.type().equals("B") || action.type().equals("W")) {
                ++n2;
            }
            listElement = listElement.next();
        }
        xmlWriter.endTagNewLine("Node");
    }
    
    public void removeactions() {
        this.Actions = new ListClass();
    }
    
    public void addchange(final Change change) {
        this.Changes.append(new ListElement(change));
    }
    
    public void clearchanges() {
        this.Changes.removeall();
    }
    
    public void main(final boolean main) {
        this.Main = main;
    }
    
    public void main(final Tree tree) {
        this.Main = false;
        try {
            if (((Node)tree.content()).main()) {
                this.Main = (this == tree.firstchild().content());
                return;
            }
            if (tree.parent() == null) {
                this.Main = true;
            }
        }
        catch (Exception ex) {}
    }
    
    public void number(final int n) {
        this.N = n;
    }
    
    public ListElement actions() {
        return this.Actions.first();
    }
    
    public ListElement lastaction() {
        return this.Actions.last();
    }
    
    public ListElement changes() {
        return this.Changes.first();
    }
    
    public ListElement lastchange() {
        return this.Changes.last();
    }
    
    public int number() {
        return this.N;
    }
    
    public boolean main() {
        return this.Main;
    }
}
