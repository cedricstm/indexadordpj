package net.marcoreis.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public class BaixadorDPJ {
	private static Logger logger = Logger.getLogger(BaixadorDPJ.class);

	public static void main(String[] args) throws IOException {

		Date data = new Date();
		SimpleDateFormat formatador = new SimpleDateFormat();
		formatador.applyPattern("dd");
		String dia = formatador.format(data);
		formatador.applyPattern("MM");
		String mes = formatador.format(data);
		formatador.applyPattern("yyyy");
		String ano = formatador.format(data);

		String nomeDoArquivo = null;
		nomeDoArquivo = "dpj-" + ano + mes + dia + ".pdf";

		System.out.println(nomeDoArquivo);

		URL url = new URL("http://diario.tjrr.jus.br/dpj/" + nomeDoArquivo);
		String destino = "/home/cedric/dpj/" + nomeDoArquivo;

		InputStream is = url.openStream();

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		int codigo = conn.getResponseCode();
		String texto;
		if (codigo == conn.HTTP_NOT_FOUND) {
			texto = "fora do ar";
			logger.info(texto);
		} else {
			texto = "DPJ " + nomeDoArquivo + " publicado . . .";
			logger.info(texto+conn.getContentLength()/1024+ " Kbytes");

			FileOutputStream fos = new FileOutputStream(destino);
			int bytes = 0;
			while ((bytes = is.read()) != -1) {
				fos.write(bytes);
			}
			is.close();
			fos.close();

		}

	}
}