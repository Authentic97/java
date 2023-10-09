package gestion_atelier_db.repositories.bd;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import gestion_atelier_db.entities.Categorie;

public class CategorieRepository extends MySql<Categorie>{

    private Categorie cat = null;

    public CategorieRepository(){
        super();
        tableName ="categorie";
    }

    @Override
    public ArrayList<Categorie> findAll() {
        ArrayList<Categorie> categories = new ArrayList<>();
        try {
            result = executeSelect(null, null);
            while(result.next()){
                cat = new Categorie(result.getInt("id"),result.getString("libelle"));
                categories.add(cat);
            }
            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public Categorie findById(int id) {
        try{
            String sql = "SELECT * FROM "+tableName+" where id=?";
            Map<String, Integer> tableauAssociatif = new HashMap<>();
            tableauAssociatif.put("id", id);
            result = executeSelect(sql, tableauAssociatif);
            while(result.next()){
                cat = new Categorie(result.getInt("id"),result.getString("libelle"));
            }
            closeAll();
        }
        catch(Exception e){
        System.out.println(e);
        }
        return cat;
    }

    @Override
    public int update(Categorie data) {
        try{
            String sql = "UPDATE "+tableName+" SET `libelle`=? WHERE `categorie`.`id`=? ";
            stmt = executeUpdate(sql);
            stmt.setString(1, data.getLibelle());
            stmt.setInt(2, data.getId());
            closeReturnNbreLigne(sql);
        }
        catch(Exception e){ 
        System.out.println(e);
        }
        return nbreLigne;
    }

    @Override
    public int insert(Categorie data) {
        try{
            String sql = "INSERT INTO " + tableName + " (libelle) VALUES (?)";
            stmt = executeUpdate(sql);
            stmt.setString(1, data.getLibelle());
            closeReturnNbreLigne(sql);
        }
        catch(Exception e){ 
        System.out.println(e);
        }
        return nbreLigne;
    }

    @Override
    public int delete(int id) {
       try{
            String sql = "DELETE FROM "+tableName+" WHERE `categorie`.`id`=? ";
            stmt = executeUpdate(sql);
            stmt.setInt(1, id);
            closeReturnNbreLigne(sql);
        }
        catch(Exception e){ 
        System.out.println(e);
        }
        return nbreLigne;
    }


    


}
