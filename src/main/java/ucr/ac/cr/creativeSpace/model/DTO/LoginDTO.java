package ucr.ac.cr.creativeSpace.model.DTO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class LoginDTO {
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")

    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).+$",
            message = "La contraseña debe tener mínimo 8 caracteres, una mayúscula, un número y un símbolo"
    )
    private String password;

    public LoginDTO() {}

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
}
