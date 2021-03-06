package de.dimm.vsm.records;
// Generated 09.07.2009 10:42:43 by Hibernate Tools 3.2.1.GA

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Role generated by hbm2java
 */
@Entity
public class MailNotifications implements java.io.Serializable
{
    static final long serialVersionUID = 1L;
   

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idx;

    private String keyString;
       
    
    @OneToOne
    MailGroup  group;

    boolean disabled;

    public MailNotifications()
    {        
    }


    public long getIdx()
    {
        return this.idx;
    }

    public void setIdx( long id )
    {
        this.idx = id;
    }

    public String getKeyString()
    {
        return keyString;
    }

    public void setKeyString( String keyString )
    {
        this.keyString = keyString;
    }


    public MailGroup getGroup()
    {
        return group;
    }

    public void setGroup( MailGroup group )
    {
        this.group = group;
    }

    public boolean isDisabled()
    {
        return disabled;
    }

    public void setDisabled( boolean disabled )
    {
        this.disabled = disabled;
    }

    @Override
    public String toString()
    {
        return keyString + idx;
    }


}
