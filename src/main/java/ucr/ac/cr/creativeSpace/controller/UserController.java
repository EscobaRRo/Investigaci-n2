package ucr.ac.cr.creativeSpace.controller;

import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ucr.ac.cr.creativeSpace.model.DTO.LoginDTO;
import ucr.ac.cr.creativeSpace.model.DTO.UserDTO;
import ucr.ac.cr.creativeSpace.model.User;
import ucr.ac.cr.creativeSpace.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
@CrossOrigin(origins = "*")//dar permiso por direccionamiento ip, aqui se le pone la direccion. En intranet se le pone las ip de las computudoras
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    //listar
    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll()
    {
        return ResponseEntity.ok(this.userService.findAll());
    }


    //obtener por id
    @GetMapping("/id/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id)
    {
        UserDTO dto=this.userService.getById(id);
        if (dto==null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Persona no encontrada");
        }
        return ResponseEntity.ok(dto);
    }
    //crea espacio
    @PostMapping("/save")
    public ResponseEntity<?> saveUser(@Validated @RequestBody User user, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }
        UserDTO dto = this.userService.saveUser(user);
        if (dto == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El usuario con el ID: " + user.getId() + " ya se encuentra registrado!");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    //actualiza espacio
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editUser (@Validated @PathVariable Integer id, @RequestBody User user, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }
        UserDTO dto= this.userService.getById(id);
        if (dto==null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario con el ID: " + id + " no existe!");
        }
        return ResponseEntity.ok(this.userService.editUser(id, user));
    }

    //elimina espacio
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser (@PathVariable Integer id)
    {
        UserDTO dto= this.userService.getById(id);
        if (dto==null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario con el ID: " + id + " no existe!");
        }
        this.userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
    //por tipo

    //por ubicación


    @GetMapping("/name/{name}")
    public ResponseEntity<?> findByName(@PathVariable String name) {
        if (this.userService.findByName(name).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("El nombre no se encuentra registrado");
        }
        return ResponseEntity.ok(this.userService.findByName(name));
    }

    @GetMapping("/order")
    public ResponseEntity<?> findAllByOrderByNameAsc() {
        return ResponseEntity.ok(this.userService.findAllByOrderByNameAsc());
    }

    @GetMapping("/list-rol/{rol}")
    public ResponseEntity<?> buscarRoles(@PathVariable String rol) {
        return ResponseEntity.ok(this.userService.buscarRol(rol));
    }

    @GetMapping("/{email}/{password}")
    public ResponseEntity<?> findByEmailAndPassword(@PathVariable String email, @PathVariable String password) {
        return ResponseEntity.ok(this.userService.findByEmailAndPassword(email, password));
    }

    @PostMapping("login")
    public ResponseEntity<?> login (@Valid @Validated @RequestBody LoginDTO dto, BindingResult result)
    {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }
        User user = this.userService.login(dto.getEmail(), dto.getPassword());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectos");
        }
        return ResponseEntity.ok("Bienvenido "+user.getName());
    }



//    @GetMapping("/login/{email}/{password}")
//    public ResponseEntity<?> login(@PathVariable String email, @PathVariable String password)
//    {
//        return ResponseEntity.ok(this.userService.login(email, password));
//    }

}
