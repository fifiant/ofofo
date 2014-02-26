package analytic.ofofo.net.apis.gmail.model;


@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class Email extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Email\",\"fields\":[{\"name\":\"message_id\",\"type\":[\"null\",\"string\"],\"doc\":\"\"},{\"name\":\"thread_id\",\"type\":[\"null\",\"string\"],\"doc\":\"\"},{\"name\":\"in_reply_to\",\"type\":[\"string\",\"null\"]},{\"name\":\"subject\",\"type\":[\"string\",\"null\"]},{\"name\":\"body\",\"type\":[\"string\",\"null\"]},{\"name\":\"date\",\"type\":[\"string\",\"null\"]},{\"name\":\"from\",\"type\":{\"type\":\"record\",\"name\":\"from\",\"fields\":[{\"name\":\"real_name\",\"type\":[\"null\",\"string\"],\"doc\":\"\"},{\"name\":\"address\",\"type\":[\"null\",\"string\"],\"doc\":\"\"}]}},{\"name\":\"tos\",\"type\":[\"null\",{\"type\":\"array\",\"items\":[\"null\",{\"type\":\"record\",\"name\":\"to\",\"fields\":[{\"name\":\"real_name\",\"type\":[\"null\",\"string\"],\"doc\":\"\"},{\"name\":\"address\",\"type\":[\"null\",\"string\"],\"doc\":\"\"}]}]}],\"doc\":\"\"},{\"name\":\"ccs\",\"type\":[\"null\",{\"type\":\"array\",\"items\":[\"null\",{\"type\":\"record\",\"name\":\"cc\",\"fields\":[{\"name\":\"real_name\",\"type\":[\"null\",\"string\"],\"doc\":\"\"},{\"name\":\"address\",\"type\":[\"null\",\"string\"],\"doc\":\"\"}]}]}],\"doc\":\"\"},{\"name\":\"bccs\",\"type\":[\"null\",{\"type\":\"array\",\"items\":[\"null\",{\"type\":\"record\",\"name\":\"bcc\",\"fields\":[{\"name\":\"real_name\",\"type\":[\"null\",\"string\"],\"doc\":\"\"},{\"name\":\"address\",\"type\":[\"null\",\"string\"],\"doc\":\"\"}]}]}],\"doc\":\"\"},{\"name\":\"reply_tos\",\"type\":[\"null\",{\"type\":\"array\",\"items\":[\"null\",{\"type\":\"record\",\"name\":\"reply_to\",\"fields\":[{\"name\":\"real_name\",\"type\":[\"null\",\"string\"],\"doc\":\"\"},{\"name\":\"address\",\"type\":[\"null\",\"string\"],\"doc\":\"\"}]}]}],\"doc\":\"\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  /**  */
  @Deprecated public java.lang.CharSequence message_id;
  /**  */
  @Deprecated public java.lang.CharSequence thread_id;
  @Deprecated public java.lang.CharSequence in_reply_to;
  @Deprecated public java.lang.CharSequence subject;
  @Deprecated public java.lang.CharSequence body;
  @Deprecated public java.lang.CharSequence date;
  @Deprecated public From from;
  /**  */
  @Deprecated public java.util.List<To> tos;
  /**  */
  @Deprecated public java.util.List<Cc> ccs;
  /**  */
  @Deprecated public java.util.List<Bcc> bccs;
  /**  */
  @Deprecated public java.util.List<Reply_to> reply_tos;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>. 
   */
  public Email() {}

  /**
   * All-args constructor.
   */
  public Email(java.lang.CharSequence message_id, java.lang.CharSequence thread_id, java.lang.CharSequence in_reply_to, java.lang.CharSequence subject, java.lang.CharSequence body, java.lang.CharSequence date, From from, java.util.List<To> tos, java.util.List<Cc> ccs, java.util.List<Bcc> bccs, java.util.List<Reply_to> reply_tos) {
    this.message_id = message_id;
    this.thread_id = thread_id;
    this.in_reply_to = in_reply_to;
    this.subject = subject;
    this.body = body;
    this.date = date;
    this.from = from;
    this.tos = tos;
    this.ccs = ccs;
    this.bccs = bccs;
    this.reply_tos = reply_tos;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call. 
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return message_id;
    case 1: return thread_id;
    case 2: return in_reply_to;
    case 3: return subject;
    case 4: return body;
    case 5: return date;
    case 6: return from;
    case 7: return tos;
    case 8: return ccs;
    case 9: return bccs;
    case 10: return reply_tos;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
  // Used by DatumReader.  Applications should not call. 
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: message_id = (java.lang.CharSequence)value$; break;
    case 1: thread_id = (java.lang.CharSequence)value$; break;
    case 2: in_reply_to = (java.lang.CharSequence)value$; break;
    case 3: subject = (java.lang.CharSequence)value$; break;
    case 4: body = (java.lang.CharSequence)value$; break;
    case 5: date = (java.lang.CharSequence)value$; break;
    case 6: from = (From)value$; break;
    case 7: tos = (java.util.List<To>)value$; break;
    case 8: ccs = (java.util.List<Cc>)value$; break;
    case 9: bccs = (java.util.List<Bcc>)value$; break;
    case 10: reply_tos = (java.util.List<Reply_to>)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'message_id' field.
   *    */
  public java.lang.CharSequence getMessageId() {
    return message_id;
  }

  /**
   * Sets the value of the 'message_id' field.
   *    * @param value the value to set.
   */
  public void setMessageId(java.lang.CharSequence value) {
    this.message_id = value;
  }

  /**
   * Gets the value of the 'thread_id' field.
   *    */
  public java.lang.CharSequence getThreadId() {
    return thread_id;
  }

  /**
   * Sets the value of the 'thread_id' field.
   *    * @param value the value to set.
   */
  public void setThreadId(java.lang.CharSequence value) {
    this.thread_id = value;
  }

  /**
   * Gets the value of the 'in_reply_to' field.
   */
  public java.lang.CharSequence getInReplyTo() {
    return in_reply_to;
  }

  /**
   * Sets the value of the 'in_reply_to' field.
   * @param value the value to set.
   */
  public void setInReplyTo(java.lang.CharSequence value) {
    this.in_reply_to = value;
  }

  /**
   * Gets the value of the 'subject' field.
   */
  public java.lang.CharSequence getSubject() {
    return subject;
  }

  /**
   * Sets the value of the 'subject' field.
   * @param value the value to set.
   */
  public void setSubject(java.lang.CharSequence value) {
    this.subject = value;
  }

  /**
   * Gets the value of the 'body' field.
   */
  public java.lang.CharSequence getBody() {
    return body;
  }

  /**
   * Sets the value of the 'body' field.
   * @param value the value to set.
   */
  public void setBody(java.lang.CharSequence value) {
    this.body = value;
  }

  /**
   * Gets the value of the 'date' field.
   */
  public java.lang.CharSequence getDate() {
    return date;
  }

  /**
   * Sets the value of the 'date' field.
   * @param value the value to set.
   */
  public void setDate(java.lang.CharSequence value) {
    this.date = value;
  }

  /**
   * Gets the value of the 'from' field.
   */
  public From getFrom() {
    return from;
  }

  /**
   * Sets the value of the 'from' field.
   * @param value the value to set.
   */
  public void setFrom(From value) {
    this.from = value;
  }

  /**
   * Gets the value of the 'tos' field.
   *    */
  public java.util.List<To> getTos() {
    return tos;
  }

  /**
   * Sets the value of the 'tos' field.
   *    * @param value the value to set.
   */
  public void setTos(java.util.List<To> value) {
    this.tos = value;
  }

  /**
   * Gets the value of the 'ccs' field.
   *    */
  public java.util.List<Cc> getCcs() {
    return ccs;
  }

  /**
   * Sets the value of the 'ccs' field.
   *    * @param value the value to set.
   */
  public void setCcs(java.util.List<Cc> value) {
    this.ccs = value;
  }

  /**
   * Gets the value of the 'bccs' field.
   *    */
  public java.util.List<Bcc> getBccs() {
    return bccs;
  }

  /**
   * Sets the value of the 'bccs' field.
   *    * @param value the value to set.
   */
  public void setBccs(java.util.List<Bcc> value) {
    this.bccs = value;
  }

  /**
   * Gets the value of the 'reply_tos' field.
   *    */
  public java.util.List<Reply_to> getReplyTos() {
    return reply_tos;
  }

  /**
   * Sets the value of the 'reply_tos' field.
   *    * @param value the value to set.
   */
  public void setReplyTos(java.util.List<Reply_to> value) {
    this.reply_tos = value;
  }

  /** Creates a new Email RecordBuilder */
  public static Email.Builder newBuilder() {
    return new Email.Builder();
  }
  
  /** Creates a new Email RecordBuilder by copying an existing Builder */
  public static Email.Builder newBuilder(Email.Builder other) {
    return new Email.Builder(other);
  }
  
  /** Creates a new Email RecordBuilder by copying an existing Email instance */
  public static Email.Builder newBuilder(Email other) {
    return new Email.Builder(other);
  }
  
  /**
   * RecordBuilder for Email instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Email>
    implements org.apache.avro.data.RecordBuilder<Email> {

    private java.lang.CharSequence message_id;
    private java.lang.CharSequence thread_id;
    private java.lang.CharSequence in_reply_to;
    private java.lang.CharSequence subject;
    private java.lang.CharSequence body;
    private java.lang.CharSequence date;
    private From from;
    private java.util.List<To> tos;
    private java.util.List<Cc> ccs;
    private java.util.List<Bcc> bccs;
    private java.util.List<Reply_to> reply_tos;

    /** Creates a new Builder */
    private Builder() {
      super(Email.SCHEMA$);
    }
    
    /** Creates a Builder by copying an existing Builder */
    private Builder(Email.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.message_id)) {
        this.message_id = data().deepCopy(fields()[0].schema(), other.message_id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.thread_id)) {
        this.thread_id = data().deepCopy(fields()[1].schema(), other.thread_id);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.in_reply_to)) {
        this.in_reply_to = data().deepCopy(fields()[2].schema(), other.in_reply_to);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.subject)) {
        this.subject = data().deepCopy(fields()[3].schema(), other.subject);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.body)) {
        this.body = data().deepCopy(fields()[4].schema(), other.body);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.date)) {
        this.date = data().deepCopy(fields()[5].schema(), other.date);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.from)) {
        this.from = data().deepCopy(fields()[6].schema(), other.from);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.tos)) {
        this.tos = data().deepCopy(fields()[7].schema(), other.tos);
        fieldSetFlags()[7] = true;
      }
      if (isValidValue(fields()[8], other.ccs)) {
        this.ccs = data().deepCopy(fields()[8].schema(), other.ccs);
        fieldSetFlags()[8] = true;
      }
      if (isValidValue(fields()[9], other.bccs)) {
        this.bccs = data().deepCopy(fields()[9].schema(), other.bccs);
        fieldSetFlags()[9] = true;
      }
      if (isValidValue(fields()[10], other.reply_tos)) {
        this.reply_tos = data().deepCopy(fields()[10].schema(), other.reply_tos);
        fieldSetFlags()[10] = true;
      }
    }
    
    /** Creates a Builder by copying an existing Email instance */
    private Builder(Email other) {
            super(Email.SCHEMA$);
      if (isValidValue(fields()[0], other.message_id)) {
        this.message_id = data().deepCopy(fields()[0].schema(), other.message_id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.thread_id)) {
        this.thread_id = data().deepCopy(fields()[1].schema(), other.thread_id);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.in_reply_to)) {
        this.in_reply_to = data().deepCopy(fields()[2].schema(), other.in_reply_to);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.subject)) {
        this.subject = data().deepCopy(fields()[3].schema(), other.subject);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.body)) {
        this.body = data().deepCopy(fields()[4].schema(), other.body);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.date)) {
        this.date = data().deepCopy(fields()[5].schema(), other.date);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.from)) {
        this.from = data().deepCopy(fields()[6].schema(), other.from);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.tos)) {
        this.tos = data().deepCopy(fields()[7].schema(), other.tos);
        fieldSetFlags()[7] = true;
      }
      if (isValidValue(fields()[8], other.ccs)) {
        this.ccs = data().deepCopy(fields()[8].schema(), other.ccs);
        fieldSetFlags()[8] = true;
      }
      if (isValidValue(fields()[9], other.bccs)) {
        this.bccs = data().deepCopy(fields()[9].schema(), other.bccs);
        fieldSetFlags()[9] = true;
      }
      if (isValidValue(fields()[10], other.reply_tos)) {
        this.reply_tos = data().deepCopy(fields()[10].schema(), other.reply_tos);
        fieldSetFlags()[10] = true;
      }
    }

    /** Gets the value of the 'message_id' field */
    public java.lang.CharSequence getMessageId() {
      return message_id;
    }
    
    /** Sets the value of the 'message_id' field */
    public Email.Builder setMessageId(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.message_id = value;
      fieldSetFlags()[0] = true;
      return this; 
    }
    
    /** Checks whether the 'message_id' field has been set */
    public boolean hasMessageId() {
      return fieldSetFlags()[0];
    }
    
    /** Clears the value of the 'message_id' field */
    public Email.Builder clearMessageId() {
      message_id = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /** Gets the value of the 'thread_id' field */
    public java.lang.CharSequence getThreadId() {
      return thread_id;
    }
    
    /** Sets the value of the 'thread_id' field */
    public Email.Builder setThreadId(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.thread_id = value;
      fieldSetFlags()[1] = true;
      return this; 
    }
    
    /** Checks whether the 'thread_id' field has been set */
    public boolean hasThreadId() {
      return fieldSetFlags()[1];
    }
    
    /** Clears the value of the 'thread_id' field */
    public Email.Builder clearThreadId() {
      thread_id = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /** Gets the value of the 'in_reply_to' field */
    public java.lang.CharSequence getInReplyTo() {
      return in_reply_to;
    }
    
    /** Sets the value of the 'in_reply_to' field */
    public Email.Builder setInReplyTo(java.lang.CharSequence value) {
      validate(fields()[2], value);
      this.in_reply_to = value;
      fieldSetFlags()[2] = true;
      return this; 
    }
    
    /** Checks whether the 'in_reply_to' field has been set */
    public boolean hasInReplyTo() {
      return fieldSetFlags()[2];
    }
    
    /** Clears the value of the 'in_reply_to' field */
    public Email.Builder clearInReplyTo() {
      in_reply_to = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /** Gets the value of the 'subject' field */
    public java.lang.CharSequence getSubject() {
      return subject;
    }
    
    /** Sets the value of the 'subject' field */
    public Email.Builder setSubject(java.lang.CharSequence value) {
      validate(fields()[3], value);
      this.subject = value;
      fieldSetFlags()[3] = true;
      return this; 
    }
    
    /** Checks whether the 'subject' field has been set */
    public boolean hasSubject() {
      return fieldSetFlags()[3];
    }
    
    /** Clears the value of the 'subject' field */
    public Email.Builder clearSubject() {
      subject = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /** Gets the value of the 'body' field */
    public java.lang.CharSequence getBody() {
      return body;
    }
    
    /** Sets the value of the 'body' field */
    public Email.Builder setBody(java.lang.CharSequence value) {
      validate(fields()[4], value);
      this.body = value;
      fieldSetFlags()[4] = true;
      return this; 
    }
    
    /** Checks whether the 'body' field has been set */
    public boolean hasBody() {
      return fieldSetFlags()[4];
    }
    
    /** Clears the value of the 'body' field */
    public Email.Builder clearBody() {
      body = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    /** Gets the value of the 'date' field */
    public java.lang.CharSequence getDate() {
      return date;
    }
    
    /** Sets the value of the 'date' field */
    public Email.Builder setDate(java.lang.CharSequence value) {
      validate(fields()[5], value);
      this.date = value;
      fieldSetFlags()[5] = true;
      return this; 
    }
    
    /** Checks whether the 'date' field has been set */
    public boolean hasDate() {
      return fieldSetFlags()[5];
    }
    
    /** Clears the value of the 'date' field */
    public Email.Builder clearDate() {
      date = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    /** Gets the value of the 'from' field */
    public From getFrom() {
      return from;
    }
    
    /** Sets the value of the 'from' field */
    public Email.Builder setFrom(From value) {
      validate(fields()[6], value);
      this.from = value;
      fieldSetFlags()[6] = true;
      return this; 
    }
    
    /** Checks whether the 'from' field has been set */
    public boolean hasFrom() {
      return fieldSetFlags()[6];
    }
    
    /** Clears the value of the 'from' field */
    public Email.Builder clearFrom() {
      from = null;
      fieldSetFlags()[6] = false;
      return this;
    }

    /** Gets the value of the 'tos' field */
    public java.util.List<To> getTos() {
      return tos;
    }
    
    /** Sets the value of the 'tos' field */
    public Email.Builder setTos(java.util.List<To> value) {
      validate(fields()[7], value);
      this.tos = value;
      fieldSetFlags()[7] = true;
      return this; 
    }
    
    /** Checks whether the 'tos' field has been set */
    public boolean hasTos() {
      return fieldSetFlags()[7];
    }
    
    /** Clears the value of the 'tos' field */
    public Email.Builder clearTos() {
      tos = null;
      fieldSetFlags()[7] = false;
      return this;
    }

    /** Gets the value of the 'ccs' field */
    public java.util.List<Cc> getCcs() {
      return ccs;
    }
    
    /** Sets the value of the 'ccs' field */
    public Email.Builder setCcs(java.util.List<Cc> value) {
      validate(fields()[8], value);
      this.ccs = value;
      fieldSetFlags()[8] = true;
      return this; 
    }
    
    /** Checks whether the 'ccs' field has been set */
    public boolean hasCcs() {
      return fieldSetFlags()[8];
    }
    
    /** Clears the value of the 'ccs' field */
    public Email.Builder clearCcs() {
      ccs = null;
      fieldSetFlags()[8] = false;
      return this;
    }

    /** Gets the value of the 'bccs' field */
    public java.util.List<Bcc> getBccs() {
      return bccs;
    }
    
    /** Sets the value of the 'bccs' field */
    public Email.Builder setBccs(java.util.List<Bcc> value) {
      validate(fields()[9], value);
      this.bccs = value;
      fieldSetFlags()[9] = true;
      return this; 
    }
    
    /** Checks whether the 'bccs' field has been set */
    public boolean hasBccs() {
      return fieldSetFlags()[9];
    }
    
    /** Clears the value of the 'bccs' field */
    public Email.Builder clearBccs() {
      bccs = null;
      fieldSetFlags()[9] = false;
      return this;
    }

    /** Gets the value of the 'reply_tos' field */
    public java.util.List<Reply_to> getReplyTos() {
      return reply_tos;
    }
    
    /** Sets the value of the 'reply_tos' field */
    public Email.Builder setReplyTos(java.util.List<Reply_to> value) {
      validate(fields()[10], value);
      this.reply_tos = value;
      fieldSetFlags()[10] = true;
      return this; 
    }
    
    /** Checks whether the 'reply_tos' field has been set */
    public boolean hasReplyTos() {
      return fieldSetFlags()[10];
    }
    
    /** Clears the value of the 'reply_tos' field */
    public Email.Builder clearReplyTos() {
      reply_tos = null;
      fieldSetFlags()[10] = false;
      return this;
    }

    public Email build() {
      try {
        Email record = new Email();
        record.message_id = fieldSetFlags()[0] ? this.message_id : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.thread_id = fieldSetFlags()[1] ? this.thread_id : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.in_reply_to = fieldSetFlags()[2] ? this.in_reply_to : (java.lang.CharSequence) defaultValue(fields()[2]);
        record.subject = fieldSetFlags()[3] ? this.subject : (java.lang.CharSequence) defaultValue(fields()[3]);
        record.body = fieldSetFlags()[4] ? this.body : (java.lang.CharSequence) defaultValue(fields()[4]);
        record.date = fieldSetFlags()[5] ? this.date : (java.lang.CharSequence) defaultValue(fields()[5]);
        record.from = fieldSetFlags()[6] ? this.from : (From) defaultValue(fields()[6]);
        record.tos = fieldSetFlags()[7] ? this.tos : (java.util.List<To>) defaultValue(fields()[7]);
        record.ccs = fieldSetFlags()[8] ? this.ccs : (java.util.List<Cc>) defaultValue(fields()[8]);
        record.bccs = fieldSetFlags()[9] ? this.bccs : (java.util.List<Bcc>) defaultValue(fields()[9]);
        record.reply_tos = fieldSetFlags()[10] ? this.reply_tos : (java.util.List<Reply_to>) defaultValue(fields()[10]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }
}
