package Database.DBObjects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;


@Getter
@Setter
@AllArgsConstructor
public class Relationship {

    public Relationship() {
        keypairs = new ArrayList<Keypair>();
    }

    public ArrayList<Keypair> keypairs;
    public Table childtable;

}
