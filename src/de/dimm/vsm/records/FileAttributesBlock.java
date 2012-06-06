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
import javax.persistence.Lob;
import org.eclipse.persistence.annotations.Index;

/**
 *
 * @author Administrator
 */
@Entity
public class FileAttributesBlock  implements Serializable
{
    static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idx;


    private int blockLen;

    // HASH
    @Index(unique=true)
    private String hashvalue;

    @Lob
    private String attributeData;

    @Override
    public String toString()
    {
        return "Idx: " + idx + " Len: " + blockLen + " " + hashvalue;
    }




    public long getIdx()
    {
        return idx;
    }

    public void setIdx( long id )
    {
        this.idx = id;
    }


    /**
     * @return the blockLen
     */
    public int getBlockLen()
    {
        return blockLen;
    }

    /**
     * @param blockLen the blockLen to set
     */
    public void setBlockLen( int blockLen )
    {
        this.blockLen = blockLen;
    }


    /**
     * @return the hashvalue
     */
    public String getHashvalue()
    {
        return hashvalue;
    }

    /**
     * @param hashvalue the hashvalue to set
     */
    public void setHashvalue( String hashvalue )
    {
        this.hashvalue = hashvalue;
    }

    public void setAttributeData( String xattribute )
    {
        this.attributeData = xattribute;
    }

    public String getAttributeData()
    {
        return attributeData;
    }

    
    
}

