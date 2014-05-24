package com.ernkebe.converter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
 
/**
 * Klase atsakinga uz pdf failo informacijos isgavimą.
 * Faile esanti informacija nuskaitoma ir isgaunama tekstiniu pavidalu.
 * @author nesta
 *
 */
public class PdfContextConverter {
 
	/**
	 * Parametras saugantis pdf failo vieta
	 */
	private static String fileLocation;
	
	static String pdftoText(String fileName) 
	{
		PDFParser parser;
		String parsedText = null;;
		PDFTextStripper pdfStripper = null;
		PDDocument pdDoc = null;
		COSDocument cosDoc = null;
		File file = new File(fileName);
		if (!file.isFile()) {
			System.err.println("File " + fileName + " does not exist.");
			return null;
		}
		try {
			parser = new PDFParser(new FileInputStream(file));
		} catch (IOException e) {
			System.err.println("Unable to open PDF Parser. " + e.getMessage());
			return null;
		}
		try {
			parser.parse();
			cosDoc = parser.getDocument();
			pdfStripper = new PDFTextStripper();
			pdDoc = new PDDocument(cosDoc);
			pdfStripper.setStartPage(1);
			pdfStripper.setEndPage(5);
			parsedText = pdfStripper.getText(pdDoc);
		} catch (Exception e) {
			System.err
					.println("An exception occured in parsing the PDF Document."
							+ e.getMessage());
		} finally {
			try {
				if (cosDoc != null)
					cosDoc.close();
				if (pdDoc != null)
					pdDoc.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return parsedText;
	}
 
	/**
	 * Metodas atsakingas uz konkretaus pdf failo pavadinimo formatavima
	 * @return tekstinis pdf failo pavadinimas
	 */
	public static String getPdfFileName()
	{
		String rez= "";
		String title = fileLocation;
		if(title.contains("\\"))
		{
			title = title.substring(title.lastIndexOf("\\")+1, title.lastIndexOf("."));
		}
		else
		{
			title = title.substring(0, title.lastIndexOf("."));
		}
		rez = title;
		return rez;
	}

	/**
	 * Pdf failo pavadinimo grazinimas
	 * @return tekstinis pdf failo pavadinimas
	 */
	public static String getFileName() {
		return fileLocation;
	}

	/**
	 * Metodas nustato parametro fileLocation reiksme
	 * @param fileName tekstine pdf failo vietos reiksme
	 */
	public static void setFileName(String fileName) {
		PdfContextConverter.fileLocation = fileName;
	}
}
