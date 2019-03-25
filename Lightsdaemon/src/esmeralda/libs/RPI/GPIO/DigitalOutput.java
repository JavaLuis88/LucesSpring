package esmeralda.libs.RPI.GPIO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.function.IntUnaryOperator;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DigitalOutput implements IDigitalOuput {// class

	////////////////
	// Constructor//
	//////////////
	private Runtime runtime;
	private int pin;

	public DigitalOutput() {// constructor

		this.runtime = Runtime.getRuntime();
		this.pin = 0;

	}// constructor

	public DigitalOutput(int pin) throws PinNumberNovalidException, IOException {// constructor

		this.runtime = Runtime.getRuntime();
		this.setPin(pin);

	}// constructor

	///////////////
	// Interfaces//
	/////////////
	@Override
	public void setPin(int pin) throws PinNumberNovalidException, IOException {// setPin

		if (pin <= 0 || pin > 18) {// if1

			throw new PinNumberNovalidException();

		} // if1
		else {// else1

			this.pin = pin;

		} // else1

	}// setPin

	@Override
	public int getPin() {// getPin

		return this.pin;

	}// getPin

	@Override
	public void pinOn() throws PinNumberNovalidException, IOException, OperationNotWorkException {// pinOn

		File filepath;

		if (this.pin <= 0 || this.pin > 18) {// if1

			throw new PinNumberNovalidException();

		} // if1
		else if (this.getPinStatus() == false) {// elseif1-1

			filepath = new File("/sys/class/gpio/export");

			try (FileOutputStream filechannel = new FileOutputStream(filepath)) {

				try (OutputStreamWriter filefilter = new OutputStreamWriter(filechannel)) {// try2

					try (BufferedWriter filebuffer = new BufferedWriter(filefilter)) {// try3

						filebuffer.write(Integer.toString(this.pin) + "\n");

					} // try3

				} // try2

			} // try1

			filepath = new File("/sys/class/gpio/gpio" + this.pin + "/direction");

			try (FileOutputStream filechannel = new FileOutputStream(filepath)) {

				try (OutputStreamWriter filefilter = new OutputStreamWriter(filechannel)) {// try2

					try (BufferedWriter filebuffer = new BufferedWriter(filefilter)) {// try3

						filebuffer.write("out\n");
						filebuffer.flush();

					} // try3

				} // try2

			} // try1

			filepath = new File("/sys/class/gpio/gpio" + this.pin + "/value");

			try (FileOutputStream filechannel = new FileOutputStream(filepath)) {

				try (OutputStreamWriter filefilter = new OutputStreamWriter(filechannel)) {// try2

					try (BufferedWriter filebuffer = new BufferedWriter(filefilter)) {// try3

						filebuffer.write("1\n");
						filebuffer.flush();

					} // try3

				} // try2

			} // try1

			if (this.getPinStatus() != true) {// if2

				throw new OperationNotWorkException();
			} // if2

		} // elseif1-1

	}// pinOn

	@Override
	public void pinOff() throws PinNumberNovalidException, IOException, OperationNotWorkException {// pinOff

		File filepath;

		if (this.pin <= 0 || this.pin > 18) {// if1

			throw new PinNumberNovalidException();

		} // if1
		else if (this.getPinStatus() == true) {// elseif1-1

			filepath = new File("/sys/class/gpio/gpio" + this.pin + "/value");

			try (FileOutputStream filechannel = new FileOutputStream(filepath)) {

				try (OutputStreamWriter filefilter = new OutputStreamWriter(filechannel)) {// try2

					try (BufferedWriter filebuffer = new BufferedWriter(filefilter)) {// try3

						filebuffer.write("0\n");
						filebuffer.flush();

					} // try3

				} // try2

			} // try1
			this.destroyPin(this.pin);

		} // elseif1-1

	}// pinOff

	@Override
	public boolean getPinStatus() throws PinNumberNovalidException, IOException, OperationNotWorkException {// getPinStatus

		File filepath;
		boolean retval;

		if (this.pin <= 0 || this.pin > 18) {// if1

			throw new PinNumberNovalidException();

		} // if1
		else {// else1

			filepath = new File("/sys/class/gpio/gpio" + this.pin + "/value");
			retval = (filepath.exists() == true && filepath.isFile() == true);
			if (retval == true) {// if2

				try (FileInputStream filechannel = new FileInputStream(filepath)) {

					try (InputStreamReader filefilter = new InputStreamReader(filechannel)) {// try2

						try (BufferedReader filebuffer = new BufferedReader(filefilter)) {// try3

							retval = (filebuffer.readLine().trim().equals("1"));

						} // try3

					} // try2

				} // try1
			} // if2

		} // else1

		return retval;

	}// getPinStatus

	@Override
	public void destroyPin(int pinnumber) throws PinNumberNovalidException, IOException, OperationNotWorkException {// destroyPin
		File filepath;

		if (pinnumber <= 0 || pinnumber > 18) {// if1

			throw new PinNumberNovalidException();

		} // if1
		else {// else1

			filepath = new File("/sys/class/gpio/unexport");

			try (FileOutputStream filechannel = new FileOutputStream(filepath)) {

				try (OutputStreamWriter filefilter = new OutputStreamWriter(filechannel)) {// try2

					try (BufferedWriter filebuffer = new BufferedWriter(filefilter)) {// try3

						filebuffer.write(Integer.toString(pinnumber) + "\n");
						filebuffer.flush();
					} // try3

				} // try2

			} // try1

			filepath = new File("/sys/class/gpio/gpio" + pinnumber + "/direction");
			if (filepath.exists() == true) {// if2

				throw new OperationNotWorkException();

			} // if2
		} // else1

	}// destroyPin

	@Override
	public void destroyAllPins() throws IOException, OperationNotWorkException {// destroyAllPins

		for (int i = 1; i <= 18; i++) {// for1
			try {// try1

				this.destroyPin(i);

			} // try1
			catch (PinNumberNovalidException e) {// catch1

			} // catch1
		} // for1

	}// destroyAllPins

	@Override
	public void close() {// close

		try {// try1

			this.destroyAllPins();

		} // try1
		catch (IOException e) {// catch1-1

		} // catch1-1
		catch (OperationNotWorkException e) {// catch1-2

		} // catch1-2

	}// close
}// class
