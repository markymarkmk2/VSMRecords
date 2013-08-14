package de.dimm.vsm.records;
// Generated 09.07.2009 10:42:43 by Hibernate Tools 3.2.1.GA

import de.dimm.vsm.fsengine.ArrayLazyList;
import de.dimm.vsm.fsengine.LazyList;
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
 * Role generated by hbm2java
 */
@Entity
public class Role implements java.io.Serializable
{
    static final long serialVersionUID = 1L;

    public static final int DISABLED = 1;
    public static final int IS_ADMIN = 2;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idx;

    private String name;
    private String opts;
    private String accountmatch;
    private int license;
    private long flags;
    private String user4eyes;
    private String pwd4eyes;

    @ManyToOne
    private AccountConnector accountConnector;
    
    @OneToMany(mappedBy = "role",  fetch=FetchType.EAGER, orphanRemoval=true, cascade = {CascadeType.REMOVE,CascadeType.DETACH})
    private List<RoleOption> roleOptions;

    public Role()
    {
        roleOptions = new ArrayLazyList<RoleOption>();
    }

    public Role( Role r )
    {
        this.idx = r.idx;
        this.name = r.name;
        this.license = r.license;
        this.opts = r.opts;
        this.accountmatch = r.accountmatch;
        this.flags = r.flags;
        this.accountConnector = r.accountConnector;
        this.roleOptions = r.roleOptions;
        this.user4eyes = r.user4eyes;
        this.pwd4eyes = r.pwd4eyes;
    }

    public Role( int idx )
    {
        this.idx = idx;
        roleOptions = new ArrayLazyList<RoleOption>();
    }

    public Role( long idx, String name, String opts, String acm, Integer license, long flags, AccountConnector ac, LazyList<RoleOption> roleOptions, String user4eyes, String pwd4eyes )
    {
        this.idx = idx;
        this.name = name;
        this.license = license;
        this.opts = opts;
        accountmatch = acm;
        this.flags = flags;
        this.accountConnector = ac;
        this.roleOptions = roleOptions;
        this.user4eyes = user4eyes;
        this.pwd4eyes = pwd4eyes;
    }

    public long getIdx()
    {
        return this.idx;
    }

    public void setIdx( long id )
    {
        this.idx = id;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public Integer getLicense()
    {
        return this.license;
    }

    public void setLicense( Integer license )
    {
        this.license = license;
    }

    public String getOpts()
    {
        return this.opts;
    }

    public void setOpts( String opts )
    {
        this.opts = opts;
    }

    public String getAccountmatch()
    {
        return this.accountmatch;
    }

    public void setAccountmatch( String ac )
    {
        this.accountmatch = ac;
    }

    public long getFlags()
    {
        return this.flags;
    }

    public void setFlags( long flags )
    {
        this.flags = flags;
    }

    public AccountConnector getAccountConnector()
    {
        return this.accountConnector;
    }

    public void setAccountConnector( AccountConnector ac )
    {
        this.accountConnector = ac;
    }

    public LazyList<RoleOption> getRoleOptions()
    {
        //if (roleOptions instanceof LazyList)
            return ((LazyList<RoleOption>)roleOptions);//.getList();
        //return roleOptions;
    }

    public void setRoleOptions( LazyList<RoleOption> roleOptions )
    {
        this.roleOptions = roleOptions;
    }

    public String getPwd4eyes()
    {
        return pwd4eyes;
    }

    public String getUser4eyes()
    {
        return user4eyes;
    }

    public void setPwd4eyes( String pwd4eyes )
    {
        this.pwd4eyes = pwd4eyes;
    }

    public void setUser4eyes( String user4eyes )
    {
        this.user4eyes = user4eyes;
    }

    public boolean hasRoleOption(  String opt )
    {
        // IS ALLOWED, EAGER FETCH
        List<RoleOption> list = getRoleOptions();

        for (int i = 0; i < list.size(); i++)
        {
            RoleOption roleOption = list.get( i);
            if (roleOption.getToken() != null && roleOption.getToken().equals(opt))
                return true;
        }
        return false;
    }
}
