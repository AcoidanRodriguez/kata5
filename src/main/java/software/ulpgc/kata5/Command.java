package software.ulpgc.kata5;

public interface Command {
    Output execute(Input input);
    interface Input{
        String get(String key);
    }

    interface Output{
        int response();
        String result();
    }
}
