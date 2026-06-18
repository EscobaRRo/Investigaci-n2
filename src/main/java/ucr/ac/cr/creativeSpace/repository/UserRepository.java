package ucr.ac.cr.creativeSpace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ucr.ac.cr.creativeSpace.model.DTO.LoginDTO;
import ucr.ac.cr.creativeSpace.model.User;

import javax.management.relation.Role;
import java.util.List;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByName(String name);
    List<User> findAllByOrderByNameAsc();
    User findByEmailAndPassword(String email, String password);
    //JPQL
    @Query("SELECT u FROM User u WHERE u.rol = :rol")
    List<User> buscarRol(@Param("rol") String rol);

    @Query("SELECT u FROM User u WHERE u.email = :email AND u.password = :password")
    User login(@Param("email") String email,
               @Param("password") String password);

    //User findByEmail(String email);
    //List<User> findByRol(String rol);

}