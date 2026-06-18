package ucr.ac.cr.creativeSpace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucr.ac.cr.creativeSpace.model.Space;
import ucr.ac.cr.creativeSpace.model.DTO.CreativeSpaceDTO;
import ucr.ac.cr.creativeSpace.repository.SpaceRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SpaceService {
    @Autowired
    private SpaceRepository repository;

    public Space create(Space space)
    {
        return repository.save(space);
    }

    //pra Mockito
    public Space getSpace(Integer id)
    {
        return repository.findById(id).orElse(null);
    }
//    public Space getSpace(Integer id)
//    {
//        Optional<Space> op=repository.findById(id);
//        return op.get();
//    }
//Listar
    public List<CreativeSpaceDTO> findAll()
    {
        return this.convertListDTO(this.repository.findAll());
    }

//Obtiene por id
    public CreativeSpaceDTO findById(Integer id) {

        Optional<Space> optional = this.repository.findById(id);
        if (optional.isPresent())
        {
            return this.convertCreativeSpacetoDTO(optional.get());
        }
        return null;
    }

//Crear
    public CreativeSpaceDTO saveCreativeSpace(Space space) {
        Optional<Space> opt = this.repository.findById(space.getId());
        if (opt.isPresent()) {
            return null;
        }
        return this.convertCreativeSpacetoDTO(this.repository.save(space));
    }

//Actualiza espacio
    public CreativeSpaceDTO updateCreativeSpace(Space space) {
        Optional<Space> opt = this.repository.findById(space.getId());
        if (opt.isPresent()) {
            return this.convertCreativeSpacetoDTO(this.repository.save(space));
        }
        return null;
    }
//Elimina espacio
    public void deleteCreativeSpace(Integer id) {
        this.repository.deleteById(id);
    }
//Por tipo
    public CreativeSpaceDTO searchByType(String type) {
        return this.convertCreativeSpacetoDTO(this.repository.findByType(type));
    }
//Por ubicación
    public CreativeSpaceDTO searchByUbication(String ubication) {
        return this.convertCreativeSpacetoDTO(this.repository.findByUbication(ubication));
    }

    //creo nuevo objeto de tipo DTO y seteo con el que entra por parametros, al final retorno
    public CreativeSpaceDTO convertCreativeSpacetoDTO(Space creativeSpace) {
        CreativeSpaceDTO dto=new CreativeSpaceDTO();
        dto.setId(creativeSpace.getId());
        dto.setName(creativeSpace.getName());
        dto.setType(creativeSpace.getType());
        dto.setPrice(creativeSpace.getPrice());
        return dto;
    }
    //Convertir lista DTO. Creo un nuevo arrayList, lo recorro con un for, se recorre la lista que se recibe por parametros. se agrega con add usando el convertUser
    public List<CreativeSpaceDTO> convertListDTO(List<Space> list)
    {
        List<CreativeSpaceDTO> listDTO=new ArrayList<>();
        for (Space creativeSpace : list)
        {
            listDTO.add(this.convertCreativeSpacetoDTO(creativeSpace));
        }
        return listDTO;
    }


}
