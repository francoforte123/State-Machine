package StateMachine.Order.Entities;

public enum OrderState {

    CREATED,
    ACCEPTED,
    IN_PREPARATION,
    READY,
    DELIVERING,
    COMPLETED,
    CLOSED
}
