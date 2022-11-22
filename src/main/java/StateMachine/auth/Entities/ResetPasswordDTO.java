package StateMachine.auth.Entities;

public class ResetPasswordDTO {

    private String newPassword;
    private String resetPasswordCode;

    public ResetPasswordDTO(String newPassword, String resetPasswordCode) {
        this.newPassword = newPassword;
        this.resetPasswordCode = resetPasswordCode;
    }

    public ResetPasswordDTO() {
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getResetPasswordCode() {
        return resetPasswordCode;
    }

    public void setResetPasswordCode(String resetPasswordCode) {
        this.resetPasswordCode = resetPasswordCode;
    }
}
