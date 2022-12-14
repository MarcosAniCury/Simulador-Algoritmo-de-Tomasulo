package Constants;

public class BufferInstruction {
    public static final String STATE_ISSUE = "issue";
    public static final String STATE_EXECUTE = "execute";
    public static final String STATE_WRITE_RESULT = "write_result";
    public static final String STATE_COMMIT = "commit";
    public static enum StateEnum {
        issue,
        execute,
        write_result,
        commit
    };
}
