/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.HashMap;
import javax.persistence.Persistence;

/**
 *
 * @author umita
 */
public class Structure {
     public static void main(String[] args)
    {
        HashMap<String, Object> puproperties = new HashMap();
        
        puproperties.put("javax.persistence.sql-load-script-source", "scripts/ClearDB.sql");
        
        Persistence.generateSchema("seedMavenP", puproperties);
        
        Persistence.generateSchema("seedMavenP", null);
    }
}
