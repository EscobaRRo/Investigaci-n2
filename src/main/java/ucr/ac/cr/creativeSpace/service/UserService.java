package ucr.ac.cr.creativeSpace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucr.ac.cr.creativeSpace.model.DTO.LoginDTO;
import ucr.ac.cr.creativeSpace.model.DTO.UserDTO;
import ucr.ac.cr.creativeSpace.model.User;
import ucr.ac.cr.creativeSpace.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDTO saveUser(User user){
        Optional<User> opt = this.userRepository.findById(user.getId());
        if (opt.isPresent())
        {
            return null;
        }
        return this.convertUserDTO(this.userRepository.save(user));
    }

    public List<UserDTO> findAll()
    {
        return this.convertListDTO(this.userRepository.findAll());
    }

    public UserDTO findByIdUser(Integer id)
    {
        Optional<User> optional= this.userRepository.findById(id);
        if (optional.isPresent())
        {
            this.convertUserDTO(optional.get());
        }
        return null;
    }

    public UserDTO getById(Integer id)
    {
        return this.convertUserDTO(this.userRepository.findById(id).get());
    }
    public void deleteUser(Integer id)
    {
        this.userRepository.deleteById(id);
    }

    public UserDTO editUser(Integer id, User userEdit)
    {
        Optional<User> userOp=this.userRepository.findById(id);
        if (userOp.isPresent())
        {
            User user=userOp.get();
            user=userEdit;
            return this.convertUserDTO(this.userRepository.save(user));
        }
        return null;
    }
    //convert DTO
    //creo nuevo objeto de tipo DTO y seteo con el que entr
    public UserDTO convertUserDTO(User user)
    {
        UserDTO dto=new UserDTO();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        return dto;
    }
    //Convertir lista DTO
    public List<UserDTO> convertListDTO(List<User> listUser)
    {
        List<UserDTO> listDTO=new ArrayList<>();
        for (User user: listUser)
        {
            listDTO.add(this.convertUserDTO(user));
        }
        return listDTO;
    }



    public List<UserDTO> findByName(String name)
    {
        return this.convertListDTO(this.userRepository.findByName(name));
    }

    public List<User> findAllByOrderByNameAsc()
    {
        return this.userRepository.findAllByOrderByNameAsc();
    }

    public List<User> buscarRol(String rol)
    {
        return this.userRepository.buscarRol(rol);
    }

    public User findByEmailAndPassword(String email, String password)
    {
        return this.userRepository.findByEmailAndPassword(email, password);
    }

//    public LoginDTO login(String email, String password)
//    {
//        return this.convertLoginDTO(this.userRepository.login(email, password));
//    }

    public User login(String email, String password)
    {
        return this.userRepository.login(email, password);
    }

//    public LoginDTO convertLoginDTO(User user)
//    {
//        LoginDTO dto=new LoginDTO();
//        dto.setEmail(user.getEmail());
//        dto.setPassword(user.getPassword());
//        return dto;
//    }

}
