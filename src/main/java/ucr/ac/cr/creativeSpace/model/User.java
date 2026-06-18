package ucr.ac.cr.creativeSpace.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="tb_usuarios")
public class User {
    @Id//llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)//hace que la variable sea autoincremental
    @Column(name = "id")//darle nombre a la columna, si no lo tuviera le pondria la que tiene automáticamente
    private Integer id;
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @Column(name = "email", nullable = false, length = 100, unique = true)
    private String email;
    @Column(name = "password", nullable = false, length = 255)
    private String password;
    @Column(name = "rol", nullable = false, length = 20)
    private String rol;

    @OneToMany(mappedBy = "user")
    @JsonIgnore//anotacion que dice que no mande la info por JSON, sino por objeto.
    private List<Reservation> listReservation;

    public User() {
    }

    public User(Integer id, String name, String email, String password, String rol) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.rol = rol;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
