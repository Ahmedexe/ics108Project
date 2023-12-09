public class EmptyTextFeildException extends Exception {

    public EmptyTextFeildException() {
        super("Please Fill the Text Feild");
    }

    public EmptyTextFeildException(String s) {
        super(s);
    }
}
