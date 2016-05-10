package net.marcoreis.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.text.DateFormatter;

import org.apache.log4j.Logger;

public class BaixadorDPJ {
	private static Logger logger = Logger.getLogger(BaixadorDPJ.class);

	public static void main(String[] args) throws IOException {

		URL url = new URL("http://diario.tjrr.jus.br/dpj/dpj-20160411.pdf");
		String destino = "/home/cedric/dpj/dpj-20160411.pdf";

		InputStream is = url.openStream();

		FileOutputStream fos = new FileOutputStream(destino);

		int bytes = 0;

		while ((bytes = is.read()) != -1) {
			fos.write(bytes);
		}

		is.close();

		fos.close();

	}
}