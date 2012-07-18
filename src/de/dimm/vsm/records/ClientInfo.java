/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.dimm.vsm.records;

import de.dimm.vsm.fsengine.ArrayLazyList;
import de.dimm.vsm.fsengine.LazyList;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Administrator
 */
@Entity
public class ClientInfo  implements Serializable
{
    static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long idx;



    private String ip;
    private int port;
    private boolean disabled;
    private boolean compression;
    private boolean onlyNewer;
    private boolean encryption;

    
    @OneToMany(mappedBy = "clinfo", fetch=FetchType.EAGER, orphanRemoval=true, cascade = {CascadeType.REMOVE,CascadeType.DETACH})
    private List<Excludes> exclList;

    @OneToMany(mappedBy = "clinfo", fetch=FetchType.EAGER, orphanRemoval=true, cascade = {CascadeType.REMOVE,CascadeType.DETACH})
    private List<ClientVolume> volumeList;

    @ManyToOne
    Schedule sched;
    
    public ClientInfo()
    {
        volumeList = new ArrayLazyList<ClientVolume>();
        exclList = new ArrayLazyList<Excludes>();
    }


    @Override
    public String toString()
    {
        return ip + ":" + port;
    }

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

    public Schedule getSched()
    {
        return sched;
    }

    public void setSched( Schedule sched )
    {
        this.sched = sched;
    }




    /**
     * @return the ip
     */
    public String getIp()
    {
        return ip;
    }

    /**
     * @param ip the ip to set
     */
    public void setIp( String ip )
    {
        this.ip = ip;
    }

    /**
     * @return the port
     */
    public int getPort()
    {
        return port;
    }

    /**
     * @param port the port to set
     */
    public void setPort( int port )
    {
        this.port = port;
    }

    /**
     * @return the disabled
     */
    public boolean getDisabled()
    {
        return disabled;
    }

    /**
     * @param disabled the disabled to set
     */
    public void setDisabled( boolean disabled )
    {
        this.disabled = disabled;
    }

    /**
     * @return the compression
     */
    public boolean getCompression()
    {
        return compression;
    }

    public boolean isCompression()
    {
        return compression;
    }

    public boolean isDisabled()
    {
        return disabled;
    }


    /**
     * @param compression the compression to set
     */
    public void setCompression( boolean compression )
    {
        this.compression = compression;
    }

    /**
     * @return the exclList
     */
    public LazyList<Excludes> getExclList()
    {
        return (LazyList<Excludes>)exclList;
    }

    /**
     * @param exclList the exclList to set
     */
    public void setExclList( LazyList<Excludes> exclList )
    {
        this.exclList = exclList;
    }

    /**
     * @return the volumeList
     */
    public LazyList<ClientVolume> getVolumeList()
    {
        return (LazyList<ClientVolume>)volumeList;
    }

    /**
     * @param volumeList the volumeList to set
     */
    public void setVolumeList( LazyList<ClientVolume> volumeList )
    {
        this.volumeList = volumeList;
    }

    public void setOnlyNewer( boolean onlyNewer )
    {
        this.onlyNewer = onlyNewer;
    }

    public boolean isOnlyNewer()
    {
        return onlyNewer;
    }

    public boolean isEncryption()
    {
        return encryption;
    }

    public void setEncryption( boolean encryption )
    {
        this.encryption = encryption;
    }




}
