// 
// Decompiled by Procyon v0.5.30
// 

package rene.util.list;

public class Tree
{
    ListClass Children;
    Object Content;
    ListElement Le;
    Tree Parent;
    
    public Tree(final Object content) {
        this.Content = content;
        this.Children = new ListClass();
        this.Le = null;
        this.Parent = null;
    }
    
    public void addchild(final Tree tree) {
        final ListElement le = new ListElement(tree);
        this.Children.append(le);
        tree.Le = le;
        tree.Parent = this;
    }
    
    public void insertchild(final Tree parent) {
        if (!this.haschildren()) {
            this.addchild(parent);
            return;
        }
        parent.Children = this.Children;
        this.Children = new ListClass();
        final ListElement le = new ListElement(parent);
        this.Children.append(le);
        parent.Le = le;
        parent.Parent = this;
        for (ListElement listElement = parent.Children.first(); listElement != null; listElement = listElement.next()) {
            ((Tree)listElement.content()).Parent = parent;
        }
    }
    
    public void remove(final Tree tree) {
        if (tree.parent() != this) {
            return;
        }
        this.Children.remove(tree.Le);
    }
    
    public void removeall() {
        this.Children.removeall();
    }
    
    public boolean haschildren() {
        return this.Children.first() != null;
    }
    
    public Tree firstchild() {
        return (Tree)this.Children.first().content();
    }
    
    public Tree lastchild() {
        return (Tree)this.Children.last().content();
    }
    
    public Tree parent() {
        return this.Parent;
    }
    
    public ListClass children() {
        return this.Children;
    }
    
    public Object content() {
        return this.Content;
    }
    
    public void content(final Object content) {
        this.Content = content;
    }
    
    public ListElement listelement() {
        return this.Le;
    }
}
