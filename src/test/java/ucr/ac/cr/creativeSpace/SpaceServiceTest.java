package ucr.ac.cr.creativeSpace;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ucr.ac.cr.creativeSpace.model.DTO.CreativeSpaceDTO;
import ucr.ac.cr.creativeSpace.model.Space;
import ucr.ac.cr.creativeSpace.repository.SpaceRepository;
import ucr.ac.cr.creativeSpace.service.SpaceService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpaceServiceTest {

    // Simula el repositorio para no usar la base de datos
    @Mock
    private SpaceRepository repository;

    // Inyecta el repositorio simulado dentro del servicio
    @InjectMocks
    private SpaceService service;

    @Test
    void testCreateSuccess() {

        // Crear un espacio de prueba
        Space space = new Space(1, "Sala A", "San José", "Música", 25000);

        // Simular que el repositorio guarda correctamente el espacio
        when(repository.save(space)).thenReturn(space);

        // Ejecutar el método del servicio
        Space resultado = service.create(space);

        // Verificar que el resultado sea el esperado
        assertNotNull(resultado);
        assertEquals("Sala A", resultado.getName());

        // Verificar que save() fue llamado una vez
        verify(repository, times(1)).save(space);
    }

    @Test
    void testCreateException() {

        // Crear un espacio de prueba
        Space space = new Space(1, "Sala A", "San José", "Música", 25000);

        // Simular un error al guardar
        when(repository.save(any(Space.class)))
                .thenThrow(new RuntimeException("Error en la base de datos"));

        // Verificar que el servicio lance la excepción
        assertThrows(RuntimeException.class, () -> service.create(space));

        // Verificar que se intentó guardar el espacio
        verify(repository).save(space);
    }

    @Test
    void testGetSpaceSuccess() {

        // Crear un espacio de prueba
        Space space = new Space(1, "Sala A", "San José", "Música", 25000);

        // Simular que el espacio existe
        when(repository.findById(1)).thenReturn(Optional.of(space));

        // Obtener el espacio
        Space resultado = service.getSpace(1);

        // Verificar el resultado
        assertNotNull(resultado);
        assertEquals(1, resultado.getId());

        // Verificar que se consultó el repositorio
        verify(repository).findById(1);
    }

    @Test
    void testGetSpaceNotFound() {

        // Simular que el espacio no existe
        when(repository.findById(1)).thenReturn(Optional.empty());

        // Buscar el espacio
        Space resultado = service.getSpace(1);

        // Verificar que no se encontró
        assertNull(resultado);

        // Verificar la consulta al repositorio
        verify(repository).findById(1);
    }

    @Test
    void testFindAllSuccess() {

        // Crear espacios de prueba
        Space space1 = new Space(1, "Sala A", "San José", "Música", 25000);
        Space space2 = new Space(2, "Sala B", "Heredia", "Arte", 30000);

        // Simular la lista que devuelve el repositorio
        when(repository.findAll()).thenReturn(List.of(space1, space2));

        // Obtener la lista de espacios
        List<CreativeSpaceDTO> lista = service.findAll();

        // Verificar la cantidad de espacios
        assertEquals(2, lista.size());

        // Verificar que se llamó al repositorio
        verify(repository).findAll();
    }

    @Test
    void testDeleteCreativeSpace() {

        // Eliminar un espacio
        service.deleteCreativeSpace(1);

        // Verificar que deleteById() fue llamado
        verify(repository, times(1)).deleteById(1);
    }

    @Test
    void testSaveCreativeSpaceAlreadyExists() {

        // Crear un espacio de prueba
        Space space = new Space(1, "Sala A", "San José", "Música", 25000);

        // Simular que el espacio ya existe
        when(repository.findById(1)).thenReturn(Optional.of(space));

        // Intentar guardar el espacio
        CreativeSpaceDTO dto = service.saveCreativeSpace(space);

        // Verificar que no se guardó
        assertNull(dto);

        // Verificar que solo se buscó el espacio
        verify(repository).findById(1);
        verify(repository, never()).save(any(Space.class));
    }

    @Test
    void testUpdateCreativeSpaceSuccess() {

        // Crear un espacio de prueba
        Space space = new Space(1, "Sala A", "San José", "Música", 25000);

        // Simular que el espacio existe
        when(repository.findById(1)).thenReturn(Optional.of(space));

        // Simular la actualización
        when(repository.save(space)).thenReturn(space);

        // Actualizar el espacio
        CreativeSpaceDTO dto = service.updateCreativeSpace(space);

        // Verificar que se actualizó correctamente
        assertNotNull(dto);
        assertEquals("Sala A", dto.getName());

        // Verificar las llamadas al repositorio
        verify(repository).findById(1);
        verify(repository).save(space);
    }
}