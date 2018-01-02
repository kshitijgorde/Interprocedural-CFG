// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.utils;

public class Trie
{
    public static final int ALPHA_SIZE = 128;
    Node m_Root;
    
    public Trie() {
        this.m_Root = new Node();
    }
    
    public Object get(final String key) {
        final int len = key.length();
        Node node = this.m_Root;
        for (int i = 0; i < len; ++i) {
            try {
                node = node.m_nextChar[Character.toUpperCase(key.charAt(i))];
            }
            catch (ArrayIndexOutOfBoundsException ex) {
                node = null;
            }
            if (node == null) {
                return null;
            }
        }
        return node.m_Value;
    }
    
    public Object put(final String key, final Object value) {
        final int len = key.length();
        Node node = this.m_Root;
        for (int i = 0; i < len; ++i) {
            final Node nextNode = node.m_nextChar[Character.toUpperCase(key.charAt(i))];
            if (nextNode == null) {
                while (i < len) {
                    final Node newNode = new Node();
                    node.m_nextChar[Character.toUpperCase(key.charAt(i))] = newNode;
                    node = newNode;
                    ++i;
                }
                break;
            }
            node = nextNode;
        }
        final Object ret = node.m_Value;
        node.m_Value = value;
        return ret;
    }
    
    class Node
    {
        Node[] m_nextChar;
        Object m_Value;
        
        Node() {
            this.m_nextChar = new Node[128];
            this.m_Value = null;
        }
    }
}
