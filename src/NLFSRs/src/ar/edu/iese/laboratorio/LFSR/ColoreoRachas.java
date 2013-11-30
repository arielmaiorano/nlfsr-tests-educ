package ar.edu.iese.laboratorio.LFSR;

import java.awt.Color;

import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter.DefaultHighlightPainter;

public class ColoreoRachas {

	/**
	 * Método temporario, provisorio, para conteo y coloreo de rachas
	 * actualizando contenido de un JTextArea.
	 * 
	 * @param txtBitStream
	 *            Textarea conteniendo cadena de bits.
	 * @throws RuntimeException
	 *             Conteniendo mensaje descriptivo del problema.
	 */
	public static void textarea(JTextArea txtBitStream) throws RuntimeException {

		String strBitStream = txtBitStream.getText();
		txtBitStream.append("\n\n");

		// total de bits
		final int N = strBitStream.length();
		txtBitStream.append("Tamaño de entrada: " + N + "bits\n");

		// total de 0s y 1s
		int total0 = 0;
		int total1 = 0;
		for (int i = 0; i < N; i++) {
			if (strBitStream.charAt(i) == '1') {
				total1++;
			} else {
				total0++;
			}
		}
		txtBitStream.append("Total de bits en 0: " + total0 + "\n");
		txtBitStream.append("Total de bits en 1: " + total1 + "\n");

		// coloreo de rachas 2..13
		for (int i = 2; i <= 15; i++) {
			int rachasCeros = 0;
			int rachasUnos = 0;
			String strCeros = "";
			String strUnos = "";
			for (int j = 0; j < i; j++) {
				strCeros += "0";
				strUnos += "1";
			}
			// colores
			DefaultHighlightPainter dhpCeros = new DefaultHighlightPainter(
					new Color((int) (Math.sin(0.2 * i + 0) * 127 + 128),
							(int) (Math.sin(0.2 * i + 2) * 127 + 128),
							(int) (Math.sin(0.2 * i + 4) * 127 + 128)));
			DefaultHighlightPainter dhpUnos = new DefaultHighlightPainter(
					new Color((int) (Math.sin(0.4 * i + 0) * 127 + 128),
							(int) (Math.sin(0.4 * i + 2) * 127 + 128),
							(int) (Math.sin(0.4 * i + 4) * 127 + 128)));
			// coloreo
			try {
				int start;
				int end;
				// highlight ceros
				start = 0;
				end = 0;
				while ((end = strBitStream.indexOf("1" + strCeros + "1", start)) >= 0) {
					txtBitStream.getHighlighter().addHighlight(end + 1,
							end + 1 + i, dhpCeros);
					rachasCeros++;
					start = end + 1;
				}
				// highlight unos
				start = 0;
				end = 0;
				while ((end = strBitStream.indexOf("0" + strUnos + "0", start)) >= 0) {
					txtBitStream.getHighlighter().addHighlight(end + 1,
							end + 1 + i, dhpUnos);
					rachasUnos++;
					start = end + 1;
				}
				// totales
				txtBitStream.append("Total de rachas " + strCeros + " (" + i
						+ " ceros): " + rachasCeros + " \n");
				txtBitStream.getHighlighter().addHighlight(
						txtBitStream.getText().lastIndexOf(strCeros),
						txtBitStream.getText().lastIndexOf(strCeros)
								+ strCeros.length(), dhpCeros);
				txtBitStream.append("Total de rachas " + strUnos + " (" + i
						+ " unos): " + rachasUnos + " \n");
				txtBitStream.getHighlighter().addHighlight(
						txtBitStream.getText().lastIndexOf(strUnos),
						txtBitStream.getText().lastIndexOf(strUnos)
								+ strUnos.length(), dhpUnos);

			} catch (BadLocationException e) {
				throw new RuntimeException("Error al colorear: "
						+ e.getMessage());
			}

		}
	}
}
