package gestion_atelier_db.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;


public interface IMySql<T>{
    Connection connexion();
    //ArrayList<T> executeSelect(String rqt,Class<T> cl, Map<String, Integer> tableauAssociatif, boolean bool);
    ResultSet executeSelect(String rqt,Map<String, Integer> tableauAssociatif);
    PreparedStatement executeUpdate(String rqt);
}
