// 
// Decompiled by Procyon v0.5.30
// 

class IsPresentThread extends Thread
{
    String szReceiveBuffer;
    String szServerURL;
    JNode Node;
    JTOC toc;
    JTreeView TreeView;
    boolean bLoadChildren;
    
    public IsPresentThread(final JNode node, final JTreeView treeView, final boolean bLoadChildren) {
        TocDebug.TraceL3("IsPresentThread:IsPresentThread");
        this.Node = node;
        this.bLoadChildren = bLoadChildren;
        this.TreeView = treeView;
        this.setPriority(4);
    }
    
    public void run() {
        TocDebug.TraceL3("IsPresentThreadThread::run");
        this.Node.remoteIsPresent(true);
        this.TreeView.repaint();
    }
}
