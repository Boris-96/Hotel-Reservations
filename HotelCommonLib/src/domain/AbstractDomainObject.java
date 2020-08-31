/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author Boris
 */
public abstract class AbstractDomainObject {
    
    public abstract String getAllQuery();
    public abstract PreparedStatement getQueryForInsert(Connection conn)throws SQLException;
    public abstract PreparedStatement getQueryForEdit(Connection conn)throws SQLException;
    public abstract PreparedStatement getQueryForDelete(Connection conn)throws SQLException;
    public abstract List<AbstractDomainObject> getList(ResultSet rs)throws SQLException;
    public abstract void setId(ResultSet rs)throws SQLException;
    
}
