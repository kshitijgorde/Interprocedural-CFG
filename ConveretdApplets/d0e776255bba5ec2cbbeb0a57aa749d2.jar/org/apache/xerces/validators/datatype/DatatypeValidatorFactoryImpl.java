// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.validators.datatype;

import java.util.Vector;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Constructor;
import java.util.Hashtable;

public class DatatypeValidatorFactoryImpl implements DatatypeValidatorFactory
{
    private static final boolean fDebug = false;
    private Hashtable fRegistry;
    private boolean fRegistryExpanded;
    static /* synthetic */ Class class$org$apache$xerces$validators$datatype$DatatypeValidator;
    static /* synthetic */ Class class$java$util$Hashtable;
    
    public DatatypeValidatorFactoryImpl() {
        this.fRegistryExpanded = false;
    }
    
    public void initializeDTDRegistry() {
        if (this.fRegistry == null) {
            this.fRegistry = new Hashtable();
            this.fRegistryExpanded = false;
        }
        if (!this.fRegistryExpanded) {
            try {
                this.fRegistry.put("string", new StringDatatypeValidator());
                this.fRegistry.put("ID", new IDDatatypeValidator());
                this.fRegistry.put("IDREF", new IDREFDatatypeValidator());
                this.fRegistry.put("ENTITY", new ENTITYDatatypeValidator());
                this.fRegistry.put("NOTATION", new NOTATIONDatatypeValidator());
                this.createDatatypeValidator("IDREFS", new IDREFDatatypeValidator(), null, true);
                this.createDatatypeValidator("ENTITIES", new ENTITYDatatypeValidator(), null, true);
                final Hashtable<String, String> hashtable = new Hashtable<String, String>();
                hashtable.put("pattern", "\\c+");
                hashtable.put("whiteSpace", "collapse");
                this.createDatatypeValidator("NMTOKEN", new StringDatatypeValidator(), hashtable, false);
                this.createDatatypeValidator("NMTOKENS", this.getDatatypeValidator("NMTOKEN"), null, true);
            }
            catch (InvalidDatatypeFacetException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void expandRegistryToFullSchemaSet() {
        if (this.fRegistry == null) {
            this.fRegistry = new Hashtable();
            this.fRegistryExpanded = false;
        }
        if (!this.fRegistryExpanded) {
            try {
                this.fRegistry.put("boolean", new BooleanDatatypeValidator());
                this.fRegistry.put("float", new FloatDatatypeValidator());
                this.fRegistry.put("double", new DoubleDatatypeValidator());
                this.fRegistry.put("decimal", new DecimalDatatypeValidator());
                this.fRegistry.put("timeDuration", new TimeDurationDatatypeValidator());
                this.fRegistry.put("recurringDuration", new RecurringDurationDatatypeValidator());
                this.fRegistry.put("binary", new BinaryDatatypeValidator());
                this.fRegistry.put("uriReference", new URIReferenceDatatypeValidator());
                this.fRegistry.put("QName", new QNameDatatypeValidator());
                if (this.fRegistry.get("IDREF") == null) {
                    this.initializeDTDRegistry();
                }
                final Hashtable<String, String> hashtable = new Hashtable<String, String>();
                hashtable.put("whiteSpace", "replace");
                this.createDatatypeValidator("CDATA", new StringDatatypeValidator(), hashtable, false);
                final Hashtable<String, String> hashtable2 = new Hashtable<String, String>();
                hashtable2.put("whiteSpace", "collapse");
                this.createDatatypeValidator("token", this.getDatatypeValidator("CDATA"), hashtable2, false);
                final Hashtable<String, String> hashtable3 = new Hashtable<String, String>();
                hashtable3.put("pattern", "([a-zA-Z]{2}|[iI]-[a-zA-Z]+|[xX]-[a-zA-Z]+)(-[a-zA-Z]+)*");
                this.createDatatypeValidator("language", this.getDatatypeValidator("token"), hashtable3, false);
                final Hashtable<String, String> hashtable4 = new Hashtable<String, String>();
                hashtable4.put("pattern", "\\i\\c*");
                this.createDatatypeValidator("Name", this.getDatatypeValidator("token"), hashtable4, false);
                final Hashtable<String, String> hashtable5 = new Hashtable<String, String>();
                hashtable5.put("pattern", "[\\i-[:]][\\c-[:]]*");
                this.createDatatypeValidator("NCName", this.getDatatypeValidator("token"), hashtable5, false);
                final Hashtable<String, String> hashtable6 = new Hashtable<String, String>();
                hashtable6.put("scale", "0");
                this.createDatatypeValidator("integer", new DecimalDatatypeValidator(), hashtable6, false);
                final Hashtable<String, String> hashtable7 = new Hashtable<String, String>();
                hashtable7.put("maxInclusive", "0");
                this.createDatatypeValidator("nonPositiveInteger", this.getDatatypeValidator("integer"), hashtable7, false);
                final Hashtable<String, String> hashtable8 = new Hashtable<String, String>();
                hashtable8.put("maxInclusive", "-1");
                this.createDatatypeValidator("negativeInteger", this.getDatatypeValidator("nonPositiveInteger"), hashtable8, false);
                final Hashtable<String, String> hashtable9 = new Hashtable<String, String>();
                hashtable9.put("maxInclusive", "9223372036854775807");
                hashtable9.put("minInclusive", "-9223372036854775808");
                this.createDatatypeValidator("long", this.getDatatypeValidator("integer"), hashtable9, false);
                final Hashtable<String, String> hashtable10 = new Hashtable<String, String>();
                hashtable10.put("maxInclusive", "2147483647");
                hashtable10.put("minInclusive", "-2147483648");
                this.createDatatypeValidator("int", this.getDatatypeValidator("long"), hashtable10, false);
                final Hashtable<String, String> hashtable11 = new Hashtable<String, String>();
                hashtable11.put("maxInclusive", "32767");
                hashtable11.put("minInclusive", "-32768");
                this.createDatatypeValidator("short", this.getDatatypeValidator("int"), hashtable11, false);
                final Hashtable<String, String> hashtable12 = new Hashtable<String, String>();
                hashtable12.put("maxInclusive", "127");
                hashtable12.put("minInclusive", "-128");
                this.createDatatypeValidator("byte", this.getDatatypeValidator("short"), hashtable12, false);
                final Hashtable<String, String> hashtable13 = new Hashtable<String, String>();
                hashtable13.put("minInclusive", "0");
                this.createDatatypeValidator("nonNegativeInteger", this.getDatatypeValidator("integer"), hashtable13, false);
                final Hashtable<String, String> hashtable14 = new Hashtable<String, String>();
                hashtable14.put("maxInclusive", "18446744073709551615");
                this.createDatatypeValidator("unsignedLong", this.getDatatypeValidator("nonNegativeInteger"), hashtable14, false);
                final Hashtable<String, String> hashtable15 = new Hashtable<String, String>();
                hashtable15.put("maxInclusive", "4294967295");
                this.createDatatypeValidator("unsignedInt", this.getDatatypeValidator("unsignedLong"), hashtable15, false);
                final Hashtable<String, String> hashtable16 = new Hashtable<String, String>();
                hashtable16.put("maxInclusive", "65535");
                this.createDatatypeValidator("unsignedShort", this.getDatatypeValidator("unsignedInt"), hashtable16, false);
                final Hashtable<String, String> hashtable17 = new Hashtable<String, String>();
                hashtable17.put("maxInclusive", "255");
                this.createDatatypeValidator("unsignedByte", this.getDatatypeValidator("unsignedShort"), hashtable17, false);
                final Hashtable<String, String> hashtable18 = new Hashtable<String, String>();
                hashtable18.put("minInclusive", "1");
                this.createDatatypeValidator("positiveInteger", this.getDatatypeValidator("nonNegativeInteger"), hashtable18, false);
                final Hashtable<String, String> hashtable19 = new Hashtable<String, String>();
                hashtable19.put("duration", "P0Y");
                hashtable19.put("period", "P0Y");
                this.createDatatypeValidator("timeInstant", this.getDatatypeValidator("recurringDuration"), hashtable19, false);
                final Hashtable<String, String> hashtable20 = new Hashtable<String, String>();
                hashtable20.put("duration", "P0Y");
                this.createDatatypeValidator("time", this.getDatatypeValidator("recurringDuration"), hashtable20, false);
                final Hashtable<String, String> hashtable21 = new Hashtable<String, String>();
                hashtable21.put("period", "P0Y");
                this.createDatatypeValidator("timePeriod", this.getDatatypeValidator("recurringDuration"), hashtable21, false);
                final Hashtable<String, String> hashtable22 = new Hashtable<String, String>();
                hashtable22.put("duration", "PT24H");
                this.createDatatypeValidator("date", this.getDatatypeValidator("timePeriod"), hashtable22, false);
                final Hashtable<String, String> hashtable23 = new Hashtable<String, String>();
                hashtable23.put("duration", "P1M");
                this.createDatatypeValidator("month", this.getDatatypeValidator("timePeriod"), hashtable23, false);
                final Hashtable<String, String> hashtable24 = new Hashtable<String, String>();
                hashtable24.put("duration", "P1Y");
                this.createDatatypeValidator("year", this.getDatatypeValidator("timePeriod"), hashtable24, false);
                final Hashtable<String, String> hashtable25 = new Hashtable<String, String>();
                hashtable25.put("duration", "P100Y");
                this.createDatatypeValidator("century", this.getDatatypeValidator("timePeriod"), hashtable25, false);
                final Hashtable<String, String> hashtable26 = new Hashtable<String, String>();
                hashtable26.put("period", "P1Y");
                hashtable26.put("duration", "PT24H");
                this.createDatatypeValidator("recurringDate", this.getDatatypeValidator("recurringDuration"), hashtable26, false);
                this.fRegistryExpanded = true;
            }
            catch (InvalidDatatypeFacetException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void resetRegistry() {
        if (this.fRegistry != null) {
            this.fRegistry.clear();
            this.fRegistryExpanded = false;
        }
    }
    
    public DatatypeValidator createDatatypeValidator(final String s, final DatatypeValidator datatypeValidator, final Hashtable hashtable, final boolean b) throws InvalidDatatypeFacetException {
        DatatypeValidator datatypeValidator2 = null;
        if (false == true) {
            System.out.println("type name = " + s);
        }
        if (datatypeValidator != null) {
            if (b) {
                datatypeValidator2 = new ListDatatypeValidator(datatypeValidator, hashtable, b);
            }
            else {
                try {
                    final String s2 = hashtable.get("whiteSpace");
                    if (datatypeValidator instanceof StringDatatypeValidator) {
                        final short wsFacet = datatypeValidator.getWSFacet();
                        if (s2 == null) {
                            hashtable.put("whiteSpace", (wsFacet != 2) ? ((wsFacet == 1) ? "replace" : "preserve") : "collapse");
                        }
                        else if (wsFacet == 1 && !s2.equals("collapse")) {
                            hashtable.put("whiteSpace", "replace");
                        }
                        else if (wsFacet == 2) {
                            hashtable.put("whiteSpace", "collapse");
                        }
                    }
                    else if (s2 != null) {
                        hashtable.remove("whiteSpace");
                    }
                    datatypeValidator2 = (DatatypeValidator)createDatatypeValidator(datatypeValidator.getClass().getConstructor((DatatypeValidatorFactoryImpl.class$org$apache$xerces$validators$datatype$DatatypeValidator == null) ? (DatatypeValidatorFactoryImpl.class$org$apache$xerces$validators$datatype$DatatypeValidator = class$("org.apache.xerces.validators.datatype.DatatypeValidator")) : DatatypeValidatorFactoryImpl.class$org$apache$xerces$validators$datatype$DatatypeValidator, (DatatypeValidatorFactoryImpl.class$java$util$Hashtable == null) ? (DatatypeValidatorFactoryImpl.class$java$util$Hashtable = class$("java.util.Hashtable")) : DatatypeValidatorFactoryImpl.class$java$util$Hashtable, Boolean.TYPE), new Object[] { datatypeValidator, hashtable, Boolean.FALSE });
                }
                catch (NoSuchMethodException ex) {
                    ex.printStackTrace();
                }
            }
            if (datatypeValidator2 != null) {
                this.addValidator(s, datatypeValidator2);
            }
        }
        return datatypeValidator2;
    }
    
    private static Object createDatatypeValidator(final Constructor constructor, final Object[] array) throws InvalidDatatypeFacetException {
        try {
            return constructor.newInstance(array);
        }
        catch (InstantiationException ex2) {
            return null;
        }
        catch (IllegalAccessException ex3) {
            return null;
        }
        catch (IllegalArgumentException ex4) {
            return null;
        }
        catch (InvocationTargetException ex) {
            throw new InvalidDatatypeFacetException(ex.getTargetException().getMessage());
        }
    }
    
    public DatatypeValidator createDatatypeValidator(final String s, final Vector vector) {
        DatatypeValidator datatypeValidator = null;
        if (vector != null) {
            datatypeValidator = new UnionDatatypeValidator(vector);
        }
        if (datatypeValidator != null) {
            this.addValidator(s, datatypeValidator);
        }
        return datatypeValidator;
    }
    
    public DatatypeValidator getDatatypeValidator(final String s) {
        DatatypeValidator datatypeValidator = null;
        if (s != null && this.fRegistry != null && this.fRegistry.containsKey(s)) {
            datatypeValidator = this.fRegistry.get(s);
        }
        return datatypeValidator;
    }
    
    private void addValidator(final String s, final DatatypeValidator datatypeValidator) {
        this.fRegistry.put(s, datatypeValidator);
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}
