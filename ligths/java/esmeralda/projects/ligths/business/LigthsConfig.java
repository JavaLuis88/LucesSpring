package esmeralda.projects.ligths.business;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;


public class LigthsConfig implements ILigthsConfig {

	private Properties ligthsproperties;
	private IAppPaths apppaths;
	public LigthsConfig(IAppPaths apppaths) throws IOException {
		// TODO Auto-generated constructor stub

		File ligthsconfigpath;

		this.apppaths = apppaths;
		ligthsconfigpath = new File(this.apppaths.getCurrentconfigpath(), "lights.properties");

		this.ligthsproperties = new Properties();
		try (FileInputStream channel = new FileInputStream(ligthsconfigpath)) {

			this.ligthsproperties.load(channel);

		}

	}

	@Override
	public void setSecretKey(String secretkey) throws IllegalArgumentException {
		// TODO Auto-generated method stub

		if (secretkey == null || secretkey.trim().equals("") == true || secretkey.length() < 4
				|| secretkey.length() > 16) {

			throw new IllegalArgumentException("Clave secreta no es v&acute;lida");

		} else {

			this.ligthsproperties.setProperty("secretkey", secretkey);

		}

	}

	@Override
	public String getSecretKey() {
		// TODO Auto-generated method stub
		return this.ligthsproperties.getProperty("secretkey");
	}

	@Override
	public void setPin(int pin) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		if (pin < 1 || pin > 18) {

			throw new IllegalArgumentException("Numero de pin no es v&acute;lido");

		} else {

			this.ligthsproperties.setProperty("pin", Integer.toString(pin));

		}

	}

	@Override
	public int getPin() {
		// TODO Auto-generated method stub

		int retval;

		retval = 1;

		try {
			retval = Integer.parseInt(this.ligthsproperties.getProperty("pin"), 10);
		} catch (NumberFormatException e) {

		}

		return retval;
	}

	@Override
	public void saveConfig() throws IOException {
		// TODO Auto-generated method stub

		File ligthsconfigpath;

		ligthsconfigpath = new File(this.apppaths.getCurrentconfigpath(), "lights.properties");

		try (FileOutputStream channel = new FileOutputStream(ligthsconfigpath)) {

			this.ligthsproperties.store(channel, "");

			
			
			
		}

	}

}
