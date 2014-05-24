package com.ernkebe.converter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;

/**
 * Klase atsakinga uz tekstiu duomenu konvertavima i html faila
 * @author nesta
 *
 */
public class TextToHtmlConverter {

	/**
	 * Metodas generuoja html faila
	 */
	public static void generateHtmlFile()
	{
		FileWriter fWriter = null;
        BufferedWriter writer = null;
        try {
            //Creating a new FileWriter object with the file location
        	String fileName = PdfContextConverter.getPdfFileName();
            fWriter = new FileWriter(fileName+".html");
            //creating a buffered writer for the file object
            writer = new BufferedWriter(fWriter);
            //Adding the initial HTML tags
            writer.write(generateHtmlText());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * Metodas atsakingas uz html failo iseities tekto generavima
	 * @return tekstine reiksme atitinkanti pilna html koda
	 */
	private static String generateHtmlText()
	{
		String text1 = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\"\n\"http://www.w3.org/TR/html4/strict.dtd\">\n<HTML>\n<HEAD>\n <TITLE>";
		String text2 = "Title=" + PdfContextConverter.getPdfFileName();
		String text3 = "</TITLE>\n</HEAD>\n";
		String text4 = toHtml(PdfContextConverter.pdftoText(PdfContextConverter.getFileName()));
		return text1+text2+text3+text4;
	}
	
	/**
	 * Metodas is nurodyto teksto generuoja html kodo BODY daly
	 * @param string tekstas kuris bus patomas html faile
	 * @return	sugeneruotas html kodas grazinamas kaip tekstine reiksme
	 */
	public static String toHtml( String string )
	  {

	    BufferedReader st = new BufferedReader( new StringReader( string ) );
	    StringBuffer buf = new StringBuffer( "<BODY>" );

	    try
	    {
	      String str = st.readLine();

	      while( str != null )
	      {
	        if( str.equalsIgnoreCase( "<br/>" ) )
	        {
	          str = "<br>";
	        }

	        buf.append( str );

	        if( !str.equalsIgnoreCase( "<br>" ) )
	        {
	          buf.append( "<br>" );
	        }

	        str = st.readLine();
	      }
	    }
	    catch( IOException e )
	    {
	      e.printStackTrace();
	    }

	    buf.append( "\n</BODY>\n</HTML>" );
	    string = buf.toString();
	    return string;
	  }
}
