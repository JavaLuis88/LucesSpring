package esmeralda.libs.RPI.GPIO;

import esmeralda.libs.RPI.GPIO.OperationNotWorkException;
import esmeralda.libs.RPI.GPIO.PinNumberNovalidException;

import java.io.IOException;

public interface IDigitalOuput extends AutoCloseable {//IDigitalOuput


    /////////////////////
    //Métodos Públicos//
    ///////////////////

    public abstract void setPin(int pin) throws PinNumberNovalidException,IOException;
    public abstract int  getPin();
    public abstract void pinOn() throws PinNumberNovalidException, IOException, OperationNotWorkException;
    public abstract void pinOff() throws PinNumberNovalidException, IOException, OperationNotWorkException;
    public abstract boolean getPinStatus() throws PinNumberNovalidException, IOException,OperationNotWorkException;
    public abstract void destroyPin(int pinnumber) throws PinNumberNovalidException,IOException,OperationNotWorkException;
    public abstract void destroyAllPins() throws IOException,OperationNotWorkException;










}//IDigitalOuput
