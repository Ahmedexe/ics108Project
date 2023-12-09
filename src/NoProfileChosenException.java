public class NoProfileChosenException extends Exception {

    public NoProfileChosenException() {
        super("No Profile Has Been Chosen to Do the Specified Task");
    }

    public NoProfileChosenException(String s) {
        super(s);
    }
}
