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

/**
 *
 * @author Administrator
 */
@Entity
public class StoragePoolNub  implements Serializable
{
    static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long idx;

    //private String name;
    private long poolIdx;
    boolean disabled;

    private String jdbcConnectString;

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

//
//    /**
//     * @return the name
//     */
//    public String getName()
//    {
//        return name;
//    }
//
//    /**
//     * @param name the name to set
//     */
//    public void setName( String name )
//    {
//        this.name = name;
//    }

    public String getJdbcConnectString()
    {
        return jdbcConnectString;
    }

    public void setJdbcConnectString( String jdbcConnectString )
    {
        this.jdbcConnectString = jdbcConnectString;
    }

    public long getPoolIdx()
    {
        return poolIdx;
    }

    public void setPoolIdx( long poolIdx )
    {
        this.poolIdx = poolIdx;
    }

    

    public void setDisabled( boolean disabled )
    {
        this.disabled = disabled;
    }

    public boolean isDisabled()
    {
        return disabled;
    }
    
    public String getDbName() 
    {
        return "db_" + getIdx();
    }

    
}
