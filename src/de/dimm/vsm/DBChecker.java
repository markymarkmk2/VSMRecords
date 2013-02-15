/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dimm.vsm;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/*
 *       <property name="eclipselink.target-database" value="org.eclipse.persistence.platform.database.DerbyPlatform"/>
<property name="javax.persistence.jdbc.url" value="jdbc:derby:db/VSMParams;create=true"/>
<property name="javax.persistence.jdbc.password" value="12345"/>
<property name="javax.persistence.jdbc.driver" value="org.apache.derby.jdbc.EmbeddedDriver"/>
<property name="javax.persistence.jdbc.user" value="mw"/>
<property name="eclipselink.ddl-generation" value="create-tables"/>
 */
/**
 *
 * @author Administrator
 */
public class DBChecker
{

    static long actDBRelease = 21;
    
    public static long getActDBRelease()
    {
        return actDBRelease;
    }

    public static boolean check_pool_db_changes( Connection conn )
    {

        // DERBY MAPPING
        // BOOLEAN SMALLINT
        // LONG  BIGINT
        // String varchar(255)

//                check_db_changes( change_session, "select count(smtp_user) from mandant", true, "alter table mandant add smtp_user varchar(80)", "update mandant set smtp_user=''" );

//
//        Object url = emf.getProperties().get("javax.persistence.jdbc.url");
//        Object pwd = emf.getProperties().get("javax.persistence.jdbc.password");
//        Object user = emf.getProperties().get("javax.persistence.jdbc.user");
//        Object driver = emf.getProperties().get("javax.persistence.jdbc.driver");
//
//        Connection conn = null;
        Statement stm = null;
        try
        {
//            // LOAD DRIVER
//            Class cl = Class.forName(driver.toString());
//
//            conn = DriverManager.getConnection(url.toString(), user.toString(), pwd.toString());

            stm = conn.createStatement();
        }
        catch (Exception e)
        {
            System.out.println("Change db failed: " + e.getMessage());
            return false;
        }




        check_db_changes(stm, "select count(landingZone) from StoragePool where idx=0", true, "alter table StoragePool add landingZone smallInt", "update StoragePool set landingZone=1 where landingZone is null");
        check_db_changes(stm, "select count(mode) from Retention where idx=0", true, "alter table Retention add mode varchar(40)", "update Retention set mode='ba' where mode is null");
        check_db_changes(stm, "select count(onlyNewer) from Clientinfo where idx=0", true, "alter table Clientinfo add onlyNewer smallInt", "update Clientinfo set onlyNewer=0 where onlyNewer is null");
        check_db_changes(stm, "select count(encryption) from Clientinfo where idx=0", true, "alter table Clientinfo add encryption smallInt", "update Clientinfo set encryption=0 where encryption is null");


        check_db_changes(stm, "select count(aclinfo) from FILESYSTEMELEMATTRIBUTES where idx=0", true, "alter table FILESYSTEMELEMATTRIBUTES add aclinfo int", "update FILESYSTEMELEMATTRIBUTES set aclinfo=0 where aclinfo is null");
        check_db_changes(stm, "select count(streamInfo) from XANODE where idx=0", true, "alter table XANODE add streamInfo int", "update XANODE set streamInfo=0 where streamInfo is null");

//        check_db_changes(stm, "select count(aclinfo) from FILESYSTEMELEMATTRIBUTES", false, "alter table FILESYSTEMELEMATTRIBUTES drop aclinfo", null);
        check_db_changes(stm, "select count(xasize) from FILESYSTEMELEMATTRIBUTES where idx=0", true, "alter table FILESYSTEMELEMATTRIBUTES add xasize bigInt", "update FILESYSTEMELEMATTRIBUTES set xasize=0 where xasize is null");
        check_db_changes(stm, "select count(uidName) from FILESYSTEMELEMATTRIBUTES where idx=0", true, "alter table FILESYSTEMELEMATTRIBUTES add uidName varchar(255)", "update FILESYSTEMELEMATTRIBUTES set uidName='' where uidName is null");
        check_db_changes(stm, "select count(gidName) from FILESYSTEMELEMATTRIBUTES where idx=0", true, "alter table FILESYSTEMELEMATTRIBUTES add gidName varchar(255)", "update FILESYSTEMELEMATTRIBUTES set gidName='' where gidName is null");
        check_db_changes(stm, "select count(disabled) from SCHEDULE where idx=0", true, "alter table SCHEDULE add disabled smallInt", "update SCHEDULE set disabled=0 where disabled is null");
        check_db_changes(stm, "select count(isCycle) from SCHEDULE where idx=0", true, "alter table SCHEDULE add isCycle smallInt", "update SCHEDULE set isCycle=1 where isCycle is null");
        check_db_changes(stm, "select count(cycleLengthMs) from SCHEDULE where idx=0", true, "alter table SCHEDULE add cycleLengthMs bigInt", "update SCHEDULE set cycleLengthMs=86400000 where cycleLengthMs is null");
        check_db_changes(stm, "select count(dayNumber) from JOB where idx=0", true, "alter table JOB add dayNumber int", "update JOB set dayNumber=0 where dayNumber is null");

        check_db_changes(stm, "select count(flags) from FILESYSTEMELEMATTRIBUTES where idx=0", true, "alter table FILESYSTEMELEMATTRIBUTES add flags bigInt", "update FILESYSTEMELEMATTRIBUTES set flags=0 where flags is null");
        check_db_changes(stm, "select count(flags) from FILESYSTEMELEMNODE where idx=0", true, "alter table FILESYSTEMELEMNODE add flags bigInt", "update FILESYSTEMELEMNODE set flags=0 where flags is null");

        check_db_changes(stm, "select count(ignorecase) from EXCLUDES where idx=0", true, "alter table EXCLUDES add ignorecase smallInt", "update EXCLUDES set ignorecase=0 where ignorecase is null");

        check_db_changes(stm, "select count(cloneNode_idx) from AbstractStorageNode where idx=0", true, "alter table AbstractStorageNode add cloneNode_idx bigInt", null);
       

        try
        {
            stm.close();
            conn.commit();
        }
        catch (SQLException sQLException)
        {
        }

        return true;
    }

    public static boolean check_db_changes( Connection conn )
    {

        // DERBY MAPPING
        // BOOLEAN SMALLINT
        // LONG  BIGINT
        // String varchar(255)

//                check_db_changes( change_session, "select count(smtp_user) from mandant", true, "alter table mandant add smtp_user varchar(80)", "update mandant set smtp_user=''" );

//
//        Object url = emf.getProperties().get("javax.persistence.jdbc.url");
//        Object pwd = emf.getProperties().get("javax.persistence.jdbc.password");
//        Object user = emf.getProperties().get("javax.persistence.jdbc.user");
//        Object driver = emf.getProperties().get("javax.persistence.jdbc.driver");
//
//        Connection conn = null;
        Statement stm = null;
        try
        {
//            // LOAD DRIVER
//            Class cl = Class.forName(driver.toString());
//
//            conn = DriverManager.getConnection(url.toString(), user.toString(), pwd.toString());

            stm = conn.createStatement();
        }
        catch (Exception e)
        {
            System.out.println("Change db failed: " + e.getMessage());
            return false;
        }


        check_db_changes(stm, "select count(groupIdentifier) from AccountConnector where idx=0", true, "alter table AccountConnector add groupIdentifier varchar(255)", "update AccountConnector set groupIdentifier='' where groupIdentifier is null");
        check_db_changes(stm, "select count(ntDomainName) from AccountConnector where idx=0", true, "alter table AccountConnector add ntDomainName varchar(255)", "update AccountConnector set ntDomainName='' where ntDomainName is null");


        check_db_changes(stm, "select count(basePath) from HotFolder where idx=0", true, "alter table HotFolder add basePath varchar(255)", "update HotFolder set basePath='' where basePath is null");
        check_db_changes(stm, "select count(createDateSubdir) from HotFolder where idx=0", true, "alter table HotFolder add createDateSubdir smallInt", "update HotFolder set createDateSubdir=0 where createDateSubdir is null");

        check_db_changes(stm, "select count(mmArchive) from HotFolder where idx=0", true, "alter table HotFolder add mmArchive smallInt", "update HotFolder set mmArchive=0 where mmArchive is null");
        check_db_changes(stm, "select count(mmVerify) from HotFolder where idx=0", true, "alter table HotFolder add mmVerify smallInt", "update HotFolder set mmVerify=0 where mmVerify is null");
        check_db_changes(stm, "select count(mmMediaType) from HotFolder where idx=0", true, "alter table HotFolder add mmMediaType varchar(255)", "update HotFolder set mmMediaType='' where mmMediaType is null");
        check_db_changes(stm, "select count(mmIP) from HotFolder where idx=0", true, "alter table HotFolder add mmIP varchar(255)", "update HotFolder set mmIP='' where mmIP is null");
        check_db_changes(stm, "select count(mmMountPath) from HotFolder where idx=0", true, "alter table HotFolder add mmMountPath varchar(255)", "update HotFolder set mmMountPath='' where mmMountPath is null");
        check_db_changes(stm, "select count(hfcompression) from HotFolder where idx=0", true, "alter table HotFolder add hfcompression smallInt", "update HotFolder set hfcompression=0 where hfcompression is null");
        check_db_changes(stm, "select count(hfencryption) from HotFolder where idx=0", true, "alter table HotFolder add hfencryption smallInt", "update HotFolder set hfencryption=0 where hfencryption is null");

        check_db_changes(stm, "select count(optionStr) from RoleOption where idx=0", true, "alter table RoleOption add optionStr varchar(255)", "update RoleOption set optionStr='' where optionStr is null");
        check_db_changes(stm, "select count(disabled) from MailNotifications where idx=0", true, "alter table MailNotifications add disabled smallInt", "update MailNotifications set disabled=0 where disabled is null");
        check_db_changes(stm, "select count(smtpdata_idx) from MailGroup where idx=0", true, "alter table MailGroup add smtpdata_idx bigInt", null);



        try
        {
            stm.close();
            conn.commit();
        }
        catch (SQLException sQLException)
        {
        }

        return true;
    }

    static boolean check_db_changes( Statement stm, String check_qry, boolean on_fail, String alter_cmd, String fill_cmd )
    {

        boolean failed = false;
        boolean changed = false;

        ResultSet rs = null;
        try
        {

            rs = stm.executeQuery(check_qry);

            if (!rs.next())
            {
                throw new Exception("Missing field");
            }

            rs.close();
        }
        catch (Exception hibernateException)
        {
            failed = true;
        }
        finally
        {
            try
            {
                if (rs != null)
                    rs.close();
            }
            catch (SQLException sQLException)
            {
            }
        }

        if ((failed && on_fail) || (!failed && !on_fail))
        {
            System.out.println("Performing database update: " + alter_cmd);
            try
            {
                stm.execute(alter_cmd);
                int ret = stm.getUpdateCount();

                changed = true;
            }
            catch (Exception hibernateException1)
            {
                System.out.println("Cannot change table struct " + alter_cmd + " " + hibernateException1.getMessage());
                return false;
            }
            if (fill_cmd != null)
            {
                try
                {
                    stm.execute(fill_cmd);
                    int ret = stm.getUpdateCount();
                }
                catch (Exception hibernateException)
                {
                    System.out.println("Cannot fill changed table struct " + fill_cmd + " " + hibernateException.getMessage());
                    return changed;
                }
            }
        }

        return changed;
    }
}
