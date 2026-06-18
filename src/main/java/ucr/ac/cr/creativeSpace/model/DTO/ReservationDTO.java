package ucr.ac.cr.creativeSpace.model.DTO;

import java.time.LocalDate;

public class ReservationDTO {
    private Integer id;
    private Integer spaceId;
    private String spaceName;
    private String userEmail;
    private String userName;
    private LocalDate dateReserved;
    private String status;

    public ReservationDTO() {
    }

    public ReservationDTO(Integer id, Integer spaceId, String spaceName, String userEmail, String userName, LocalDate dateReserved, String status) {
        this.id = id;
        this.spaceId = spaceId;
        this.spaceName = spaceName;
        this.userEmail = userEmail;
        this.userName = userName;
        this.dateReserved = dateReserved;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public Integer getSpaceId() {
        return spaceId;
    }

    public String getSpaceName() {
        return spaceName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public LocalDate getDateReserved() {
        return dateReserved;
    }

    public String getStatus() {
        return status;
    }
}
