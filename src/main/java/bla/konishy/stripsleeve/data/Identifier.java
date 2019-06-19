package bla.konishy.stripsleeve.data;


import javax.persistence.*;

@Entity
@Table(name = "photon")
public class Identifier {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String name, lkp;

    public Identifier() {}

    public Identifier(String name, String lkp) {
        this.name = name;
        this.lkp = lkp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLkp() {
        return lkp;
    }

    public void setLkp(String lkp) {
        this.lkp = lkp;
    }

    @Override
    public String toString() {
        return String.format("Identifier {%d, %s, %s}", id, name, lkp);
    }
}
