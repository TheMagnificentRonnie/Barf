package Database.DBObjects;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Database {
    String packageName;
    String name;
    @JsonManagedReference
    private ArrayList<Schema> schemas;
     public Database(String packageName, String name)
     {

         this.name = name;
         this.packageName = packageName;


         schemas = new ArrayList<Schema>();

     }


}
