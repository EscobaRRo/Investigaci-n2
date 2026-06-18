package ucr.ac.cr.creativeSpace.model;

import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
@Table(name="tb_reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    //Muchas reservas para un espacio
    @ManyToOne
    @JoinColumn(name = "space_id", nullable = false,
                foreignKey = @ForeignKey(name = "fk_reservation_space"))
    private Space space;

    //Muchas reservas para un usuario
    @ManyToOne
    @JoinColumn(name = "user_email",
                nullable = false,
                foreignKey = @ForeignKey(name = "fk_reservation_user"))
    private User user;

    @Column(name="dateReserved", nullable = false)
    private LocalDate dateReserved;
    @Column(name = "status", nullable = false, length = 20)
    private String status;

    public Reservation(Integer id, Space space, User user, LocalDate dateReserved, String status) {
        this.id = id;
        this.space = space;
        this.user = user;
        this.dateReserved = dateReserved;
        this.status = status;
    }

    public Reservation() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Space getSpace() {
        return space;
    }

    public void setSpace(Space space) {
        this.space = space;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getDateReserved() {
        return dateReserved;
    }

    public void setDateReserved(LocalDate dateReserved) {
        this.dateReserved = dateReserved;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
