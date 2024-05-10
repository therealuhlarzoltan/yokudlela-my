package hu.yokudlela.yokudlela.domain.enumeration;

public enum OrderState {
    IN_PROGRESS("In Progress"),
    COMPLETED("Completed"),
    SERVED("Served"),
    PAID("Paid");

    private final String name;

    private OrderState(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
