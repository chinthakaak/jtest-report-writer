package com.github.jtreport.core;

/**
 * Printer supported <li>HTML</li> <li>PDF</li> <li>XML</li> <li>CSV</li>
 * 
 * @author Fabio Rubino
 * 
 */
public enum ReportTypePrinterEnum {
	/**
	 * HTML printer.
	 */
	HTML,
	/**
	 * XHTML printer.
	 */
	XHTML,
	/**
	 * PDF printer.
	 */
	PDF,
    /**
     * PDF TestCase printer.
     */
    PDFTC,
    /**
     * PDF TestResult printer.
     */
    PDFTR,
	/**
	 * XML printer.
	 */
	XML,
	/**
	 * CSV printer.
	 */
	CSV,
	/**
	 * ODT printer.
	 */
	ODT,
	/**
	 * ODS printer.
	 */
	ODS,
	/**
	 * PNG printer.
	 */
	PNG,
	/**
	 * JRXML printer.
	 */
	JRXML

}
