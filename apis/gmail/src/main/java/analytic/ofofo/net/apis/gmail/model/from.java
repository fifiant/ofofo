package analytic.ofofo.net.apis.gmail.model;

/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class from extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"from\",\"fields\":[{\"name\":\"real_name\",\"type\":[\"null\",\"string\"],\"doc\":\"\"},{\"name\":\"address\",\"type\":[\"null\",\"string\"],\"doc\":\"\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  /**  */
  @Deprecated public java.lang.CharSequence real_name;
  /**  */
  @Deprecated public java.lang.CharSequence address;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>. 
   */
  public from() {}

  /**
   * All-args constructor.
   */
  public from(java.lang.CharSequence real_name, java.lang.CharSequence address) {
    this.real_name = real_name;
    this.address = address;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call. 
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return real_name;
    case 1: return address;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
  // Used by DatumReader.  Applications should not call. 
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: real_name = (java.lang.CharSequence)value$; break;
    case 1: address = (java.lang.CharSequence)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'real_name' field.
   *    */
  public java.lang.CharSequence getRealName() {
    return real_name;
  }

  /**
   * Sets the value of the 'real_name' field.
   *    * @param value the value to set.
   */
  public void setRealName(java.lang.CharSequence value) {
    this.real_name = value;
  }

  /**
   * Gets the value of the 'address' field.
   *    */
  public java.lang.CharSequence getAddress() {
    return address;
  }

  /**
   * Sets the value of the 'address' field.
   *    * @param value the value to set.
   */
  public void setAddress(java.lang.CharSequence value) {
    this.address = value;
  }

  /** Creates a new from RecordBuilder */
  public static from.Builder newBuilder() {
    return new from.Builder();
  }
  
  /** Creates a new from RecordBuilder by copying an existing Builder */
  public static from.Builder newBuilder(from.Builder other) {
    return new from.Builder(other);
  }
  
  /** Creates a new from RecordBuilder by copying an existing from instance */
  public static from.Builder newBuilder(from other) {
    return new from.Builder(other);
  }
  
  /**
   * RecordBuilder for from instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<from>
    implements org.apache.avro.data.RecordBuilder<from> {

    private java.lang.CharSequence real_name;
    private java.lang.CharSequence address;

    /** Creates a new Builder */
    private Builder() {
      super(from.SCHEMA$);
    }
    
    /** Creates a Builder by copying an existing Builder */
    private Builder(from.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.real_name)) {
        this.real_name = data().deepCopy(fields()[0].schema(), other.real_name);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.address)) {
        this.address = data().deepCopy(fields()[1].schema(), other.address);
        fieldSetFlags()[1] = true;
      }
    }
    
    /** Creates a Builder by copying an existing from instance */
    private Builder(from other) {
            super(from.SCHEMA$);
      if (isValidValue(fields()[0], other.real_name)) {
        this.real_name = data().deepCopy(fields()[0].schema(), other.real_name);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.address)) {
        this.address = data().deepCopy(fields()[1].schema(), other.address);
        fieldSetFlags()[1] = true;
      }
    }

    /** Gets the value of the 'real_name' field */
    public java.lang.CharSequence getRealName() {
      return real_name;
    }
    
    /** Sets the value of the 'real_name' field */
    public from.Builder setRealName(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.real_name = value;
      fieldSetFlags()[0] = true;
      return this; 
    }
    
    /** Checks whether the 'real_name' field has been set */
    public boolean hasRealName() {
      return fieldSetFlags()[0];
    }
    
    /** Clears the value of the 'real_name' field */
    public from.Builder clearRealName() {
      real_name = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /** Gets the value of the 'address' field */
    public java.lang.CharSequence getAddress() {
      return address;
    }
    
    /** Sets the value of the 'address' field */
    public from.Builder setAddress(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.address = value;
      fieldSetFlags()[1] = true;
      return this; 
    }
    
    /** Checks whether the 'address' field has been set */
    public boolean hasAddress() {
      return fieldSetFlags()[1];
    }
    
    /** Clears the value of the 'address' field */
    public from.Builder clearAddress() {
      address = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    public from build() {
      try {
        from record = new from();
        record.real_name = fieldSetFlags()[0] ? this.real_name : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.address = fieldSetFlags()[1] ? this.address : (java.lang.CharSequence) defaultValue(fields()[1]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }
}
