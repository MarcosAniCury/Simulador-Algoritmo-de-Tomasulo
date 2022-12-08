package Constants;

public class BufferInstruction {
    public static final String STATE_ISSUE = "issue";
    public static final String STATE_EXECUTE = "execute";
    public static final String STATE_WRITE_RESULT = "write_result";
    public static final String STATE_COMMIT = "commit";
    public static enum StateEnum {
        STATE_ISSUE,
        STATE_EXECUTE,
        STATE_WRITE_RESULT,
        STATE_COMMIT
    };
}
