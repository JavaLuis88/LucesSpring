package esmeralda.libs.RPI.GPIO;

public class OperationNotWorkException extends Exception {


    ////////////////
    //Constructor//
    //////////////


    public OperationNotWorkException() {//constructor

        super("Operation not work exception");



    }//constructor


    public OperationNotWorkException(String msg) {//constructor

        super(msg);



    }//constructor

}
