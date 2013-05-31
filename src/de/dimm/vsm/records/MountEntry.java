
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.dimm.vsm.records;

import de.dimm.vsm.net.RemoteFSElem;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;


/**
 *
 * @author mw
 *  */
@Entity
public class MountEntry implements Serializable
{
    static final long serialVersionUID = 1L;
    public static final String TYP_SNAPSHOT = "SN";
    public static final String TYP_TIMESTAMP = "TS";
    public static final String TYP_RDONLY = "RD";
    public static final String TYP_RDWR = "WR";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long idx;

    private String name;


    @ManyToOne
    private StoragePool pool;
    
    
    String subPath;


    private String typ;
    
    private boolean disabled; 
    private boolean showDeleted;
    private boolean autoMount;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date ts;
        
    @ManyToOne
    Snapshot snapShot;

    String ip;
    int port;
    private RemoteFSElem  mountPath;
    String username;
  

    @Override
    public String toString()
    {
        return name +" " + (username != null ? username + "@" : "") + " " + typ + " " + ip + "/" + (mountPath != null ? mountPath.getPath() : "");
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

    public void setPool(StoragePool pool) {
        this.pool = pool;
    }

    public StoragePool getPool() {
        return pool;
    }

    public void setDisabled( boolean disabled )
    {
        this.disabled = disabled;
    }

    public boolean isDisabled()
    {
        return disabled;
    }

    public String getIp()
    {
        return ip;
    }

    public void setIp( String ip )
    {
        this.ip = ip;
    }

    public int getPort()
    {
        return port;
    }

    public void setPort( int port )
    {
        this.port = port;
    }

    public void setMountPath( RemoteFSElem mountPath )
    {
        this.mountPath = mountPath;
    }

    public RemoteFSElem getMountPath()
    {
        return mountPath;
    }
   
    public void setName( String name )
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

   

    /**
     * @return the showDeleted
     */
    public boolean isShowDeleted() {
        return showDeleted;
    }

    /**
     * @param showDeleted the showDeleted to set
     */
    public void setShowDeleted(boolean showDeleted) {
        this.showDeleted = showDeleted;
    }

    /**
     * @return the typ
     */
    public String getTyp() {
        return typ;
    }

    /**
     * @param typ the typ to set
     */
    public void setTyp(String typ) {
        this.typ = typ;
    }

  

    public void setAutoMount(boolean autoMount) {
        this.autoMount = autoMount;
    }

    public boolean isAutoMount() {
        return autoMount;
    }

    public String getSubPath() {
        return subPath;
    }

    public Snapshot getSnapShot() {
        return snapShot;
    }

    public void setSubPath(String subPath) {
        this.subPath = subPath;
    }

    public void setSnapShot(Snapshot snapShot) {
        this.snapShot = snapShot;
    }

    public boolean isReadOnly() {
        return !typ.equals(TYP_RDWR);
    }

    @Override
    public boolean equals( Object obj )
    {
        if (obj instanceof MountEntry)
        {
            MountEntry me = (MountEntry)obj;
            return (me.getIdx() == getIdx() && me.getPool().getIdx() == getPool().getIdx());
        }
        return super.equals( obj );
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 19 * hash + (int) (this.idx ^ (this.idx >>> 32));
        hash = 19 * hash + Objects.hashCode( this.pool );
        return hash;
    }

    public String getKey()
    {
        return getIdx() + "#" + (getPool() != null ? getPool().getIdx() : "");
    }

    

    
    

    
}

