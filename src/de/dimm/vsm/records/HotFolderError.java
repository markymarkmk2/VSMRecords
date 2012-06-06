
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
public class HotFolderError implements Serializable
{
    static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long idx;

    @ManyToOne
    private HotFolder hotfolder;

    private RemoteFSElem  elem;

    private String errtext;

  

    @Override
    public String toString()
    {
        return elem.getPath();
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

    public HotFolder getHotfolder()
    {
        return hotfolder;
    }

    public void setHotfolder( HotFolder hotfolder )
    {
        this.hotfolder = hotfolder;
    }

    public RemoteFSElem getElem()
    {
        return elem;
    }

    public void setElem( RemoteFSElem elem )
    {
        this.elem = elem;
    }

    public void setErrtext( String errtext )
    {
        this.errtext = errtext;
    }

    public String getErrtext()
    {
        return errtext;
    }


    

   
    
}

