package Constants;

public class ReservationStation {
    //Finals
    public static final String TYPE_LOGIC = "logic";
    public static final String TYPE_MEMORY = "memory";
    public static final String TYPE_MULT = "mult";
    public static final String TYPE_ARITMETIC = "aritmetic";
    public static final String TYPE_BEQ = "beq";

    //Arrays
    public static final String[] TypeArray = {TYPE_LOGIC, TYPE_MEMORY, TYPE_MULT, TYPE_ARITMETIC, TYPE_BEQ};

    //Enums
    public static enum TypeEnum {
        TYPE_LOGIC,
        TYPE_MEMORY,
        TYPE_MULT,
        TYPE_ARITMETIC,
        TYPE_BEQ
    }
}
