
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.dimm.vsm.records;

import de.dimm.vsm.net.RemoteFSElem;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


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

    long poolIdx;
//    @ManyToOne
//    private StoragePool pool;

    private String typ;
    
    private boolean disabled; 
    private boolean showDeleted; 
    private long ts;
    
    private boolean mounted;
    
    private long snapshotIdx;
  

    String ip;
    int port;
    private RemoteFSElem  mountPath;
    String username;



    public MountEntry()
    {
    }
  

    @Override
    public String toString()
    {
        return username + "@" + ip + ":" + port + "/" + mountPath;
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

    public void setPoolIdx( long poolIdx )
    {
        this.poolIdx = poolIdx;
    }

    public long getPoolIdx()
    {
        return poolIdx;
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

    public long getTs() {
        return ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isMounted() {
        return mounted;
    }

    public void setMounted(boolean mounted) {
        this.mounted = mounted;
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

    public long getSnapshotIdx() {
        return snapshotIdx;
    }

    public void setSnapshot(long snapshotIdx) {
        this.snapshotIdx = snapshotIdx;
    }

    

    
}

