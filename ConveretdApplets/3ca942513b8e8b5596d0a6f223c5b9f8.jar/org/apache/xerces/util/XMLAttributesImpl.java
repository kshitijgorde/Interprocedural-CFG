// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.util;

import org.apache.xerces.xni.Augmentations;
import org.apache.xerces.xni.QName;
import org.apache.xerces.xni.XMLAttributes;

public class XMLAttributesImpl implements XMLAttributes
{
    protected static final int TABLE_SIZE = 101;
    protected static final int SIZE_LIMIT = 20;
    protected boolean fNamespaces;
    protected int fLargeCount;
    protected int fLength;
    protected Attribute[] fAttributes;
    protected Attribute[] fAttributeTableView;
    protected int[] fAttributeTableViewChainState;
    protected int fTableViewBuckets;
    protected boolean fIsTableViewConsistent;
    
    public XMLAttributesImpl() {
        this(101);
    }
    
    public XMLAttributesImpl(final int fTableViewBuckets) {
        this.fNamespaces = true;
        this.fLargeCount = 1;
        this.fAttributes = new Attribute[4];
        this.fTableViewBuckets = fTableViewBuckets;
        for (int i = 0; i < this.fAttributes.length; ++i) {
            this.fAttributes[i] = new Attribute();
        }
    }
    
    public void setNamespaces(final boolean fNamespaces) {
        this.fNamespaces = fNamespaces;
    }
    
    public int addAttribute(final QName values, final String type, final String s) {
        int n;
        if (this.fLength < 20) {
            n = ((values.uri != null && !values.uri.equals("")) ? this.getIndexFast(values.uri, values.localpart) : this.getIndexFast(values.rawname));
            if (n == -1) {
                n = this.fLength;
                if (this.fLength++ == this.fAttributes.length) {
                    final Attribute[] fAttributes = new Attribute[this.fAttributes.length + 4];
                    System.arraycopy(this.fAttributes, 0, fAttributes, 0, this.fAttributes.length);
                    for (int i = this.fAttributes.length; i < fAttributes.length; ++i) {
                        fAttributes[i] = new Attribute();
                    }
                    this.fAttributes = fAttributes;
                }
            }
        }
        else if (values.uri == null || values.uri.length() == 0 || (n = this.getIndexFast(values.uri, values.localpart)) == -1) {
            if (!this.fIsTableViewConsistent || this.fLength == 20) {
                this.prepareAndPopulateTableView();
                this.fIsTableViewConsistent = true;
            }
            final int tableViewBucket = this.getTableViewBucket(values.rawname);
            if (this.fAttributeTableViewChainState[tableViewBucket] != this.fLargeCount) {
                n = this.fLength;
                if (this.fLength++ == this.fAttributes.length) {
                    final Attribute[] fAttributes2 = new Attribute[this.fAttributes.length << 1];
                    System.arraycopy(this.fAttributes, 0, fAttributes2, 0, this.fAttributes.length);
                    for (int j = this.fAttributes.length; j < fAttributes2.length; ++j) {
                        fAttributes2[j] = new Attribute();
                    }
                    this.fAttributes = fAttributes2;
                }
                this.fAttributeTableViewChainState[tableViewBucket] = this.fLargeCount;
                this.fAttributes[n].next = null;
                this.fAttributeTableView[tableViewBucket] = this.fAttributes[n];
            }
            else {
                Attribute next;
                for (next = this.fAttributeTableView[tableViewBucket]; next != null && next.name.rawname != values.rawname; next = next.next) {}
                if (next == null) {
                    n = this.fLength;
                    if (this.fLength++ == this.fAttributes.length) {
                        final Attribute[] fAttributes3 = new Attribute[this.fAttributes.length << 1];
                        System.arraycopy(this.fAttributes, 0, fAttributes3, 0, this.fAttributes.length);
                        for (int k = this.fAttributes.length; k < fAttributes3.length; ++k) {
                            fAttributes3[k] = new Attribute();
                        }
                        this.fAttributes = fAttributes3;
                    }
                    this.fAttributes[n].next = this.fAttributeTableView[tableViewBucket];
                    this.fAttributeTableView[tableViewBucket] = this.fAttributes[n];
                }
                else {
                    n = this.getIndexFast(values.rawname);
                }
            }
        }
        final Attribute attribute = this.fAttributes[n];
        attribute.name.setValues(values);
        attribute.type = type;
        attribute.value = s;
        attribute.nonNormalizedValue = s;
        attribute.specified = false;
        attribute.augs.removeAllItems();
        return n;
    }
    
    public void removeAllAttributes() {
        this.fLength = 0;
    }
    
    public void removeAttributeAt(final int n) {
        this.fIsTableViewConsistent = false;
        if (n < this.fLength - 1) {
            final Attribute attribute = this.fAttributes[n];
            System.arraycopy(this.fAttributes, n + 1, this.fAttributes, n, this.fLength - n - 1);
            this.fAttributes[this.fLength - 1] = attribute;
        }
        --this.fLength;
    }
    
    public void setName(final int n, final QName values) {
        this.fAttributes[n].name.setValues(values);
    }
    
    public void getName(final int n, final QName qName) {
        qName.setValues(this.fAttributes[n].name);
    }
    
    public void setType(final int n, final String type) {
        this.fAttributes[n].type = type;
    }
    
    public void setValue(final int n, final String s) {
        final Attribute attribute = this.fAttributes[n];
        attribute.value = s;
        attribute.nonNormalizedValue = s;
    }
    
    public void setNonNormalizedValue(final int n, String value) {
        if (value == null) {
            value = this.fAttributes[n].value;
        }
        this.fAttributes[n].nonNormalizedValue = value;
    }
    
    public String getNonNormalizedValue(final int n) {
        return this.fAttributes[n].nonNormalizedValue;
    }
    
    public void setSpecified(final int n, final boolean specified) {
        this.fAttributes[n].specified = specified;
    }
    
    public boolean isSpecified(final int n) {
        return this.fAttributes[n].specified;
    }
    
    public int getLength() {
        return this.fLength;
    }
    
    public String getType(final int n) {
        if (n < 0 || n >= this.fLength) {
            return null;
        }
        return this.getReportableType(this.fAttributes[n].type);
    }
    
    public String getType(final String s) {
        final int index = this.getIndex(s);
        return (index != -1) ? this.getReportableType(this.fAttributes[index].type) : null;
    }
    
    public String getValue(final int n) {
        if (n < 0 || n >= this.fLength) {
            return null;
        }
        return this.fAttributes[n].value;
    }
    
    public String getValue(final String s) {
        final int index = this.getIndex(s);
        return (index != -1) ? this.fAttributes[index].value : null;
    }
    
    public String getName(final int n) {
        if (n < 0 || n >= this.fLength) {
            return null;
        }
        return this.fAttributes[n].name.rawname;
    }
    
    public int getIndex(final String s) {
        for (int i = 0; i < this.fLength; ++i) {
            final Attribute attribute = this.fAttributes[i];
            if (attribute.name.rawname != null && attribute.name.rawname.equals(s)) {
                return i;
            }
        }
        return -1;
    }
    
    public int getIndex(final String s, final String s2) {
        for (int i = 0; i < this.fLength; ++i) {
            final Attribute attribute = this.fAttributes[i];
            if (attribute.name.localpart != null && attribute.name.localpart.equals(s2) && (s == attribute.name.uri || (s != null && attribute.name.uri != null && attribute.name.uri.equals(s)))) {
                return i;
            }
        }
        return -1;
    }
    
    public String getLocalName(final int n) {
        if (!this.fNamespaces) {
            return "";
        }
        if (n < 0 || n >= this.fLength) {
            return null;
        }
        return this.fAttributes[n].name.localpart;
    }
    
    public String getQName(final int n) {
        if (n < 0 || n >= this.fLength) {
            return null;
        }
        final String rawname = this.fAttributes[n].name.rawname;
        return (rawname != null) ? rawname : "";
    }
    
    public String getType(final String s, final String s2) {
        if (!this.fNamespaces) {
            return null;
        }
        final int index = this.getIndex(s, s2);
        return (index != -1) ? this.getReportableType(this.fAttributes[index].type) : null;
    }
    
    public String getPrefix(final int n) {
        if (n < 0 || n >= this.fLength) {
            return null;
        }
        final String prefix = this.fAttributes[n].name.prefix;
        return (prefix != null) ? prefix : "";
    }
    
    public String getURI(final int n) {
        if (n < 0 || n >= this.fLength) {
            return null;
        }
        return this.fAttributes[n].name.uri;
    }
    
    public String getValue(final String s, final String s2) {
        final int index = this.getIndex(s, s2);
        return (index != -1) ? this.getValue(index) : null;
    }
    
    public Augmentations getAugmentations(final String s, final String s2) {
        final int index = this.getIndex(s, s2);
        return (index != -1) ? this.fAttributes[index].augs : null;
    }
    
    public Augmentations getAugmentations(final String s) {
        final int index = this.getIndex(s);
        return (index != -1) ? this.fAttributes[index].augs : null;
    }
    
    public Augmentations getAugmentations(final int n) {
        if (n < 0 || n >= this.fLength) {
            return null;
        }
        return this.fAttributes[n].augs;
    }
    
    public void setAugmentations(final int n, final Augmentations augs) {
        this.fAttributes[n].augs = augs;
    }
    
    public void setURI(final int n, final String uri) {
        this.fAttributes[n].name.uri = uri;
    }
    
    public void setSchemaId(final int n, final boolean schemaId) {
        this.fAttributes[n].schemaId = schemaId;
    }
    
    public boolean getSchemaId(final int n) {
        return n >= 0 && n < this.fLength && this.fAttributes[n].schemaId;
    }
    
    public boolean getSchemaId(final String s) {
        final int index = this.getIndex(s);
        return index != -1 && this.fAttributes[index].schemaId;
    }
    
    public boolean getSchemaId(final String s, final String s2) {
        if (!this.fNamespaces) {
            return false;
        }
        final int index = this.getIndex(s, s2);
        return index != -1 && this.fAttributes[index].schemaId;
    }
    
    public int getIndexFast(final String s) {
        for (int i = 0; i < this.fLength; ++i) {
            if (this.fAttributes[i].name.rawname == s) {
                return i;
            }
        }
        return -1;
    }
    
    public void addAttributeNS(final QName values, final String type, final String s) {
        final int fLength = this.fLength;
        if (this.fLength++ == this.fAttributes.length) {
            Attribute[] fAttributes;
            if (this.fLength < 20) {
                fAttributes = new Attribute[this.fAttributes.length + 4];
            }
            else {
                fAttributes = new Attribute[this.fAttributes.length << 1];
            }
            System.arraycopy(this.fAttributes, 0, fAttributes, 0, this.fAttributes.length);
            for (int i = this.fAttributes.length; i < fAttributes.length; ++i) {
                fAttributes[i] = new Attribute();
            }
            this.fAttributes = fAttributes;
        }
        final Attribute attribute = this.fAttributes[fLength];
        attribute.name.setValues(values);
        attribute.type = type;
        attribute.value = s;
        attribute.nonNormalizedValue = s;
        attribute.specified = false;
        attribute.augs.removeAllItems();
    }
    
    public QName checkDuplicatesNS() {
        if (this.fLength <= 20) {
            for (int i = 0; i < this.fLength - 1; ++i) {
                final Attribute attribute = this.fAttributes[i];
                for (int j = i + 1; j < this.fLength; ++j) {
                    final Attribute attribute2 = this.fAttributes[j];
                    if (attribute.name.localpart == attribute2.name.localpart && attribute.name.uri == attribute2.name.uri) {
                        return attribute2.name;
                    }
                }
            }
        }
        else {
            this.fIsTableViewConsistent = false;
            this.prepareTableView();
            for (int k = this.fLength - 1; k >= 0; --k) {
                final Attribute attribute3 = this.fAttributes[k];
                final int tableViewBucket = this.getTableViewBucket(attribute3.name.localpart, attribute3.name.uri);
                if (this.fAttributeTableViewChainState[tableViewBucket] != this.fLargeCount) {
                    this.fAttributeTableViewChainState[tableViewBucket] = this.fLargeCount;
                    attribute3.next = null;
                    this.fAttributeTableView[tableViewBucket] = attribute3;
                }
                else {
                    for (Attribute next = this.fAttributeTableView[tableViewBucket]; next != null; next = next.next) {
                        if (next.name.localpart == attribute3.name.localpart && next.name.uri == attribute3.name.uri) {
                            return attribute3.name;
                        }
                    }
                    attribute3.next = this.fAttributeTableView[tableViewBucket];
                    this.fAttributeTableView[tableViewBucket] = attribute3;
                }
            }
        }
        return null;
    }
    
    public int getIndexFast(final String s, final String s2) {
        for (int i = 0; i < this.fLength; ++i) {
            final Attribute attribute = this.fAttributes[i];
            if (attribute.name.localpart == s2 && attribute.name.uri == s) {
                return i;
            }
        }
        return -1;
    }
    
    private String getReportableType(final String s) {
        if (s.charAt(0) == '(') {
            return "NMTOKEN";
        }
        return s;
    }
    
    protected int getTableViewBucket(final String s) {
        return (s.hashCode() & Integer.MAX_VALUE) % this.fTableViewBuckets;
    }
    
    protected int getTableViewBucket(final String s, final String s2) {
        if (s2 == null) {
            return (s.hashCode() & Integer.MAX_VALUE) % this.fTableViewBuckets;
        }
        return (s.hashCode() + s2.hashCode() & Integer.MAX_VALUE) % this.fTableViewBuckets;
    }
    
    protected void cleanTableView() {
        if (++this.fLargeCount < 0) {
            if (this.fAttributeTableViewChainState != null) {
                for (int i = this.fTableViewBuckets - 1; i >= 0; --i) {
                    this.fAttributeTableViewChainState[i] = 0;
                }
            }
            this.fLargeCount = 1;
        }
    }
    
    protected void prepareTableView() {
        if (this.fAttributeTableView == null) {
            this.fAttributeTableView = new Attribute[this.fTableViewBuckets];
            this.fAttributeTableViewChainState = new int[this.fTableViewBuckets];
        }
        else {
            this.cleanTableView();
        }
    }
    
    protected void prepareAndPopulateTableView() {
        this.prepareTableView();
        for (int i = 0; i < this.fLength; ++i) {
            final Attribute attribute = this.fAttributes[i];
            final int tableViewBucket = this.getTableViewBucket(attribute.name.rawname);
            if (this.fAttributeTableViewChainState[tableViewBucket] != this.fLargeCount) {
                this.fAttributeTableViewChainState[tableViewBucket] = this.fLargeCount;
                attribute.next = null;
                this.fAttributeTableView[tableViewBucket] = attribute;
            }
            else {
                attribute.next = this.fAttributeTableView[tableViewBucket];
                this.fAttributeTableView[tableViewBucket] = attribute;
            }
        }
    }
    
    static class Attribute
    {
        public QName name;
        public String type;
        public String value;
        public String nonNormalizedValue;
        public boolean specified;
        public boolean schemaId;
        public Augmentations augs;
        public Attribute next;
        
        Attribute() {
            this.name = new QName();
            this.augs = new AugmentationsImpl();
        }
    }
}
