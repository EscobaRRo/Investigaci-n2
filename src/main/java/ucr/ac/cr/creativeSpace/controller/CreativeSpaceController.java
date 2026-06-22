package ucr.ac.cr.creativeSpace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ucr.ac.cr.creativeSpace.model.Space;
import ucr.ac.cr.creativeSpace.model.DTO.CreativeSpaceDTO;
import ucr.ac.cr.creativeSpace.service.SpaceService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/space")
public class CreativeSpaceController {
    @Autowired
    private SpaceService service;

    //Obtener todos
    @GetMapping
    public ResponseEntity<List<CreativeSpaceDTO>> findAll() {
        return ResponseEntity.ok(this.service.findAll());
    }
    //Guardar
    @PostMapping("/save")
    public ResponseEntity<?> save(@Validated @RequestBody Space space, BindingResult result)
    {
        if (result.hasErrors())
        {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors())
            {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }
        CreativeSpaceDTO space1=this.service.saveCreativeSpace(space);
        if(space1==null)
        {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El usuario ya se encuentra registrado");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(space1);
    }
    //obtener por id
    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        CreativeSpaceDTO cp = this.service.findById(id);
        if (cp==null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario no existe");
        }
        return ResponseEntity.ok(cp);
    }
    //actualiza espacio
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateSpace(@Validated @RequestBody Space cp, BindingResult result, @PathVariable Integer id) {
        if (result.hasErrors())
        {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors())
            {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }
        CreativeSpaceDTO dto=this.service.findById(id);
        if (dto==null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario no existe");
        }
        return ResponseEntity.ok(dto);
    }
    //elimina espacio
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSpace(@PathVariable Integer id) {
        CreativeSpaceDTO dto=this.service.findById(id);
        if (dto==null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario no existe");
        }
        this.service.deleteCreativeSpace(id);
        return ResponseEntity.noContent().build();
    }
    //por ubicacion
    @GetMapping("/byUbication/{ubication}")
    public ResponseEntity<?> byUbication(@PathVariable String ubication) {
        CreativeSpaceDTO dto=this.service.searchByUbication(ubication);
        if (dto==null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario no existe");
        }
        return ResponseEntity.ok(dto);
    }
    //por tipo
    @GetMapping("/byType/{type}")
    public ResponseEntity<?> byType(@PathVariable String type) {
        CreativeSpaceDTO dto=this.service.searchByType(type);
        if (dto==null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario no existe");
        }
        return ResponseEntity.ok(dto);
    }
}
