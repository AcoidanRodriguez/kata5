package software.ulpgc.kata5;

import java.util.stream.IntStream;

import static java.lang.Integer.parseInt;

public class FactorialCommand implements Command{
    private static final Output NullNumberOutput = new Output() {
        @Override
        public int response() {
            return 0;
        }

        @Override
        public String result() {
            return null;
        }
    };

    @Override
    public Output execute(Input input) {
        return execute(input.get("number"));
    }

    private Output execute(String number) {
        return number != null ?
                result(factorial(parseInt(number))) :
                NullNumberOutput;
    }

    private Output result(int factorial) {
        return new Output() {
            @Override
            public int response() {
                return 200;
            }

            @Override
            public String result() {
                return String.valueOf(factorial);
            }
        };
    }

    private int factorial(int value) {
        return IntStream.range(2,value+1).reduce(1,(a,i)-> a*i);
    }
}
