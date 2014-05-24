package com.ernkebe.converter;

/**
 * Pagrindine klase atsakinga uz pdf failu konvertavimo inicializavima
 * @author nesta
 *
 */
public class Converter {

	/**
	 * Metodas sukuria html faila pagal pdf failo duomenis.
	 * Html failas sukuriamas projekto pagrindiniame kataloge
	 * @param file pdf failo adresas
	 */
	public static void convertPdfToHtml(String file) 
	{
		if(file != null)
		{
			PdfContextConverter.setFileName(file);
			TextToHtmlConverter.generateHtmlFile();
		}
		else
		{
			System.out.println("Converter.main() no file location");
		}
	}

	
}
