package ucr.ac.cr.creativeSpace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucr.ac.cr.creativeSpace.model.DTO.ReservationDTO;
import ucr.ac.cr.creativeSpace.model.Space;
import ucr.ac.cr.creativeSpace.model.DTO.UserDTO;
import ucr.ac.cr.creativeSpace.model.Reservation;
import ucr.ac.cr.creativeSpace.repository.ReservationRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private SpaceService spaceService;

    public Reservation create_reservation(Reservation reservation)
    {
        UserDTO user = userService.findByIdUser(reservation.getUser().getId());
        Space space = spaceService.getSpace(reservation.getSpace().getId());

        if (user != null && space != null) {
            return reservationRepository.save(reservation);

        }
        return null;
    }

    public List<ReservationDTO> getReservationByUser(Integer id) {
        List<Reservation> lisR=reservationRepository.findByUserId(id);
        List<ReservationDTO> listReservationDTO=new ArrayList<>();
        for(Reservation reser:lisR){
            listReservationDTO.add(convertDTO(reser));
        }
        return listReservationDTO;
    }

    public ReservationDTO convertDTO(Reservation reservation)
    {
        return new ReservationDTO(reservation.getId(),
                reservation.getSpace().getId(),
                reservation.getSpace().getName(),
                reservation.getUser().getEmail(),
                reservation.getUser().getName(),
                reservation.getDateReserved(),
                reservation.getStatus()
        );

    }


}
