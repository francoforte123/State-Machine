package StateMachine.auth.Entities;

public class SignupActivactionDTO {

    private String activationCode;

    public SignupActivactionDTO(String activationCode) {
        this.activationCode = activationCode;
    }

    public SignupActivactionDTO() {
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }
}
