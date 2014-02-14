
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.dimm.vsm.records;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.eclipse.persistence.annotations.Index;


/**
 *
 * @author mw
 *  */
@Entity
public class TextBase implements Serializable
{
    static final long serialVersionUID = 1L;
   
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long idx;

    @Index
    String langId;

    @Index
    String messageId;

    String messageText;


    @Override
    public String toString()
    {
        return langId + " " + messageId;
    }

     /**
     * @return the idx
     */
    public long getIdx()
    {
        return idx;
    }

    /**
     * @param idx the idx to set
     */
    public void setIdx( long idx )
    {
        this.idx = idx;
    }

    public String getLangId()
    {
        return langId;
    }

    public String getMessageId()
    {
        return messageId;
    }

    public String getMessageText()
    {
        return messageText;
    }

    public void setMessageText( String messageText )
    {
        this.messageText = messageText;
    }

    public void setMessageId( String messageId )
    {
        this.messageId = messageId;
    }

    public void setLangId( String langId )
    {
        this.langId = langId;
    }

    
    

}

