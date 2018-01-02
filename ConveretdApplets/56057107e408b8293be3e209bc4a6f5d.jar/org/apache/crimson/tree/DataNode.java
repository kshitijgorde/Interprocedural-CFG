// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.crimson.tree;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.DOMException;
import org.w3c.dom.CharacterData;

abstract class DataNode extends NodeBase implements CharacterData
{
    char[] data;
    static NodeListImpl childNodes;
    
    DataNode() {
    }
    
    DataNode(final char[] buf, final int offset, final int len) {
        System.arraycopy(buf, offset, this.data = new char[len], 0, len);
    }
    
    DataNode(final String s) {
        if (s != null) {
            this.data = new char[s.length()];
            s.getChars(0, this.data.length, this.data, 0);
        }
        else {
            this.data = new char[0];
        }
    }
    
    public char[] getText() {
        return this.data;
    }
    
    public void setText(final char[] buf) {
        this.data = buf;
    }
    
    public String toString() {
        if (this.data != null) {
            return new String(this.data);
        }
        return null;
    }
    
    public String getData() {
        return this.toString();
    }
    
    public void setData(final String data) {
        if (this.isReadonly()) {
            throw new DomEx((short)7);
        }
        if (data == null) {
            this.setText(new char[0]);
        }
        else {
            this.setText(data.toCharArray());
        }
    }
    
    public int getLength() {
        return (this.data == null) ? 0 : this.data.length;
    }
    
    public String substringData(final int offset, int count) throws DOMException {
        if (offset < 0 || offset > this.data.length || count < 0) {
            throw new DomEx((short)1);
        }
        count = Math.min(count, this.data.length - offset);
        return new String(this.data, offset, count);
    }
    
    public void appendData(final String newData) {
        if (this.isReadonly()) {
            throw new DomEx((short)7);
        }
        final int length = newData.length();
        final char[] tmp = new char[length + this.data.length];
        System.arraycopy(this.data, 0, tmp, 0, this.data.length);
        newData.getChars(0, length, tmp, this.data.length);
        this.data = tmp;
    }
    
    public void insertData(final int offset, final String newData) throws DOMException {
        if (this.isReadonly()) {
            throw new DomEx((short)7);
        }
        if (offset < 0 || offset >= this.data.length) {
            throw new DomEx((short)1);
        }
        final int length = newData.length();
        final char[] tmp = new char[length + this.data.length];
        System.arraycopy(this.data, 0, tmp, 0, offset);
        newData.getChars(0, length, tmp, offset);
        System.arraycopy(this.data, offset, tmp, offset + length, this.data.length - offset);
        this.data = tmp;
    }
    
    public void deleteData(final int offset, int count) throws DOMException {
        if (this.isReadonly()) {
            throw new DomEx((short)7);
        }
        if (offset < 0 || offset >= this.data.length || count < 0) {
            throw new DomEx((short)1);
        }
        count = Math.min(count, this.data.length - offset);
        final char[] tmp = new char[this.data.length - count];
        System.arraycopy(this.data, 0, tmp, 0, offset);
        System.arraycopy(this.data, offset + count, tmp, offset, tmp.length - offset);
        this.data = tmp;
    }
    
    public void replaceData(final int offset, final int count, final String arg) throws DOMException {
        if (this.isReadonly()) {
            throw new DomEx((short)7);
        }
        if (offset < 0 || offset >= this.data.length || count < 0) {
            throw new DomEx((short)1);
        }
        if (offset + count >= this.data.length) {
            this.deleteData(offset, count);
            this.appendData(arg);
        }
        else if (arg.length() == count) {
            arg.getChars(0, arg.length(), this.data, offset);
        }
        else {
            final char[] tmp = new char[this.data.length + (arg.length() - count)];
            System.arraycopy(this.data, 0, tmp, 0, offset);
            arg.getChars(0, arg.length(), tmp, offset);
            System.arraycopy(this.data, offset + count, tmp, offset + arg.length(), this.data.length - (offset + count));
            this.data = tmp;
        }
    }
    
    public NodeList getChildNodes() {
        return DataNode.childNodes;
    }
    
    public String getNodeValue() {
        return this.getData();
    }
    
    public void setNodeValue(final String value) {
        this.setData(value);
    }
    
    static {
        DataNode.childNodes = new NodeListImpl();
    }
    
    static final class NodeListImpl implements NodeList
    {
        public Node item(final int i) {
            return null;
        }
        
        public int getLength() {
            return 0;
        }
    }
}
