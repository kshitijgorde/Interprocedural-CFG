// 
// Decompiled by Procyon v0.5.30
// 

public class ZOrderList
{
    private Node head;
    private Node tail;
    private Node cursor;
    private Node freelist;
    private int numItems;
    private boolean ssMode;
    
    ZOrderList() {
        this.head = null;
        this.tail = null;
        this.cursor = null;
        this.freelist = null;
        this.numItems = 0;
    }
    
    public Metadata[] getSnapshot() {
        if (this.numItems == 0) {
            return null;
        }
        final Metadata[] array = new Metadata[this.numItems];
        Node node = this.head;
        int n = 0;
        while (node != null) {
            array[n] = node.scene;
            ++n;
            node = node.next;
        }
        return array;
    }
    
    public void setSingleSceneMode() {
        this.ssMode = true;
    }
    
    public void setMultipleSceneMode() {
        this.ssMode = false;
    }
    
    private Node findSceneNode(final Metadata metadata) {
        Node node = null;
        if (this.ssMode) {
            for (Node node2 = this.head; node2 != null; node2 = node2.next) {
                final Metadata scene = node2.scene;
                if (scene.gridCol == metadata.gridCol && scene.gridRow == metadata.gridRow) {
                    node = node2;
                    break;
                }
            }
        }
        else {
            for (Node node3 = this.head; node3 != null; node3 = node3.next) {
                if (metadata == node3.scene) {
                    node = node3;
                    break;
                }
            }
        }
        return node;
    }
    
    private Node getFreeNode() {
        Node freelist;
        if (this.freelist != null) {
            freelist = this.freelist;
            this.freelist = this.freelist.next;
        }
        else {
            freelist = new Node();
        }
        return freelist;
    }
    
    public void putOnTop(final Metadata metadata) {
        final Node sceneNode = this.findSceneNode(metadata);
        if (sceneNode != null) {
            sceneNode.scene = metadata;
            if (this.head == sceneNode) {
                return;
            }
            if (sceneNode.prev != null) {
                sceneNode.prev.next = sceneNode.next;
            }
            else {
                this.head = sceneNode.next;
            }
            if (sceneNode.next != null) {
                sceneNode.next.prev = sceneNode.prev;
            }
            else {
                this.tail = sceneNode.prev;
            }
            this.head.prev = sceneNode;
            sceneNode.next = this.head;
            sceneNode.prev = null;
            if (sceneNode.next != null) {
                sceneNode.next.prev = sceneNode;
            }
            this.head = sceneNode;
        }
        else {
            final Node freeNode = this.getFreeNode();
            freeNode.scene = metadata;
            freeNode.next = this.head;
            freeNode.prev = null;
            if (freeNode.next != null) {
                freeNode.next.prev = freeNode;
            }
            if (this.tail == null) {
                this.tail = freeNode;
            }
            this.head = freeNode;
            ++this.numItems;
        }
    }
    
    public void putOnBottom(final Metadata metadata) {
        final Node sceneNode = this.findSceneNode(metadata);
        if (sceneNode != null) {
            sceneNode.scene = metadata;
            if (this.tail == sceneNode) {
                return;
            }
            if (sceneNode.prev != null) {
                sceneNode.prev.next = sceneNode.next;
            }
            else {
                this.head = sceneNode.next;
            }
            if (sceneNode.next != null) {
                sceneNode.next.prev = sceneNode.prev;
            }
            else {
                this.tail = sceneNode.prev;
            }
            this.tail.next = sceneNode;
            sceneNode.prev = this.tail;
            sceneNode.next = null;
            if (sceneNode.prev != null) {
                sceneNode.prev.next = sceneNode;
            }
            this.tail = sceneNode;
        }
        else {
            final Node freeNode = this.getFreeNode();
            freeNode.scene = metadata;
            freeNode.next = null;
            freeNode.prev = this.tail;
            if (freeNode.prev != null) {
                freeNode.prev.next = freeNode;
            }
            if (this.head == null) {
                this.head = freeNode;
            }
            this.tail = freeNode;
            ++this.numItems;
        }
    }
    
    public void changeScene(final Metadata scene) {
        if (this.ssMode) {
            final Node sceneNode = this.findSceneNode(scene);
            if (sceneNode != null) {
                sceneNode.scene = scene;
            }
        }
    }
    
    public void insertByCloudCover(final Metadata scene) {
        if (this.ssMode) {
            System.out.println("Bug! Inserting by cloud cover in ssMode");
        }
        final int cloudCover = scene.cloudCover;
        Node next = null;
        for (Node node = this.head; node != null; node = node.next) {
            if (cloudCover <= node.scene.cloudCover) {
                next = node;
                break;
            }
        }
        final Node freeNode = this.getFreeNode();
        freeNode.scene = scene;
        if (next == null) {
            if (this.tail == null) {
                freeNode.next = null;
                freeNode.prev = null;
                this.head = freeNode;
                this.tail = freeNode;
            }
            else {
                freeNode.next = null;
                freeNode.prev = this.tail;
                this.tail.next = freeNode;
                this.tail = freeNode;
            }
        }
        else {
            if (this.head == next) {
                this.head = freeNode;
            }
            freeNode.prev = next.prev;
            freeNode.next = next;
            next.prev = freeNode;
            if (freeNode.prev != null) {
                freeNode.prev.next = freeNode;
            }
        }
        ++this.numItems;
    }
    
    public void empty() {
        for (Node node = this.head; node != null; node = node.next) {
            node.scene = null;
            node.prev = null;
        }
        if (this.tail != null) {
            this.tail.next = this.freelist;
        }
        this.freelist = this.head;
        this.tail = null;
        this.head = null;
        this.cursor = null;
        this.numItems = 0;
    }
    
    public void top() {
        this.cursor = this.head;
    }
    
    public boolean isTop() {
        return this.cursor == null;
    }
    
    public Metadata down() {
        if (this.cursor != null) {
            final Metadata scene = this.cursor.scene;
            this.cursor = this.cursor.next;
            return scene;
        }
        return null;
    }
    
    public void bottom() {
        this.cursor = this.tail;
    }
    
    public Metadata up() {
        if (this.cursor != null) {
            final Metadata scene = this.cursor.scene;
            this.cursor = this.cursor.prev;
            return scene;
        }
        return null;
    }
    
    class Node
    {
        Node next;
        Node prev;
        Metadata scene;
        
        Node() {
            this.next = null;
            this.prev = null;
            this.scene = null;
        }
    }
}
