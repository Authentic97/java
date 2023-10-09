package gestion_atelier_db.repositories.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import gestion_atelier_db.entities.AbsEntities;
import gestion_atelier_db.repositories.IMySql;
import gestion_atelier_db.repositories.ITables;

public class MySql<T extends AbsEntities> implements IMySql<T>,ITables<T>{

    protected ArrayList<T> tableau = new ArrayList<>();
    protected Connection connexion;
    protected String tableName="";
    protected  int lastId = -1, nbreLigne=0;

    protected ResultSet result;
    protected PreparedStatement stmt;

    public MySql() {
        this.connexion = connexion();
    }

    @Override
    public Connection connexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetphp", "root", "");
            return this.connexion;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    public ResultSet executeSelect(String rqt, Map<String, Integer> tableauAssociatif) {
        try {
            connexion();
            if(rqt == null || rqt == "") rqt = "Select * from "+tableName;
            stmt = connexion.prepareStatement(rqt);
            if (tableauAssociatif != null && tableauAssociatif.containsKey("id")) {
                stmt.setInt(1, tableauAssociatif.get("id"));
            }
            result = stmt.executeQuery();
            return result ;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public PreparedStatement executeUpdate(String rqt) {
        try {
            connexion();
            stmt = connexion.prepareStatement(rqt, PreparedStatement.RETURN_GENERATED_KEYS);
            return stmt;
        } catch (Exception e) {
             e.printStackTrace();
        }
        return null;
    }

    protected void closeAll() throws SQLException{
        connexion.close();
        result.close();
        stmt.close();
    }

    protected int takePreparedStatement(PreparedStatement stmt, String rqt) throws SQLException{
        nbreLigne = stmt.executeUpdate();
        if(rqt.toLowerCase().startsWith("insert")){
            if (nbreLigne == 1) {
                result = stmt.getGeneratedKeys();
                if (result.next()) {
                    lastId = result.getInt(1);
                }
                return lastId;
            }
        }
        return nbreLigne;
    }

    protected void closeReturnNbreLigne(String rqt) throws SQLException{
        nbreLigne = takePreparedStatement(stmt, rqt);
        closeAll();
    }

    @Override
    public int insert(T data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public int update(T data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public ArrayList<T> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public T findById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public int delete(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public int indexOf(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'indexOf'");
    }
}
