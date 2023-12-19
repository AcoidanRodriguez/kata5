package software.ulpgc.kata5;

import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class SparkCommandExecutor {
    private final Request request;
    private final Response response;
    private static final Map<String,Command> commands = new HashMap<>();

    private SparkCommandExecutor(Request request, Response response) {
        this.request = request;
        this.response = response;
    }

    public static SparkCommandExecutor with(Request request, Response response){
        return new SparkCommandExecutor(request,response);
    }

    public static void put(String name, Command command){
        commands.put(name, command);
    }

    public String execute(String command){
        return execute(commands.get(command));
    }

    private String execute(Command command) {
        return unwrap(command.execute(input()));
    }

    private String unwrap(Command.Output execute) {
        response.status(execute.response());
        return execute.result();
    }

    private Command.Input input() {
        return request::queryParams;
    }

}
