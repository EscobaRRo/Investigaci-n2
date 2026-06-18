package ucr.ac.cr.creativeSpace.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="tb_creativeSpaces")
public class Space {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique=true, nullable=false)
    private Integer id;
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @Column(name = "ubication", nullable = false, length = 150)
    private String ubication;
    @Column(name = "type", nullable = false, length = 50)
    private String type;
    @Column(name = "price", nullable = false)
    private Double price;

    @OneToMany(mappedBy ="space")
    @JsonIgnore
    private List<Reservation> listReservation;
    public Space() {
    }

    public Space(Integer id, String name, String ubication, String type, double price) {
        this.id = id;
        this.name = name;
        this.ubication = ubication;
        this.type = type;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUbication() {
        return ubication;
    }

    public void setUbication(String ubication) {
        this.ubication = ubication;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
