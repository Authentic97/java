package gestion_atelier_db.services;

import java.util.ArrayList;


public interface IService<orion> {
      int  add(orion data);
       ArrayList<orion> getAll();
       int update(orion data);
       orion show(int id);
       int remove(int id);
       int[] remove(int[] ids);
}
