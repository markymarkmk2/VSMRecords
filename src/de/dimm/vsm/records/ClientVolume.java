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
 * @author Administrator
 */
@Entity
public class ClientVolume  implements Serializable
{

    static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long idx;

    @ManyToOne
    ClientInfo clinfo;



    private RemoteFSElem  volumePath;
    private boolean disabled;
    private boolean cdp;
    private boolean snapshot;
    private boolean staylocal;
    private String fsType;

    public long getIdx()
    {
        return idx;
    }

    public void setIdx( long idx )
    {
        this.idx = idx;
    }

    public ClientInfo getClinfo()
    {
        return clinfo;
    }

    public void setClinfo( ClientInfo clinfo )
    {
        this.clinfo = clinfo;
    }

    
    @Override
    public String toString()
    {
        if (volumePath != null)
            return volumePath.toString();
        return "";
    }


    /**
     * @return the path
     */
    public RemoteFSElem getVolumePath()
    {
        return volumePath;
    }

    /**
     * @param path the path to set
     */
    public void setVolumePath( RemoteFSElem path )
    {
        this.volumePath = path;
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
     * @return the cdp
     */
    public boolean getCdp()
    {
        return cdp;
    }

    /**
     * @param cdp the cdp to set
     */
    public void setCdp( boolean cdp )
    {
        this.cdp = cdp;
    }

    /**
     * @return the snapshot
     */
    public boolean getSnapshot()
    {
        return snapshot;
    }

    /**
     * @param snapshot the snapshot to set
     */
    public void setSnapshot( boolean snapshot )
    {
        this.snapshot = snapshot;
    }

    /**
     * @return the staylocal
     */
    public boolean getStaylocal()
    {
        return staylocal;
    }

    /**
     * @param staylocal the staylocal to set
     */
    public void setStaylocal( boolean staylocal )
    {
        this.staylocal = staylocal;
    }

    /**
     * @return the fs_type
     */
    public String getFsType()
    {
        return fsType;
    }

    /**
     * @param fs_type the fs_type to set
     */
    public void setFsType( String fs_type )
    {
        this.fsType = fs_type;
    }


}
