package com.github.jtreport.datasource;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import java.util.*;

/**
 * Data source for dynamic type of report.
 * 
 * @author Fabio Rubino.
 * 
 */
public class DataSource implements JRDataSource {

	private final String[] columns;
	private final List<Map<String, Object>> values;
	private Iterator<Map<String, Object>> iterator;

	private Map<String, Object> currentRecord;

	/**
	 * Data sources constructor
	 * 
	 * @param columns
	 *            Ordered name of columns
	 */
	public DataSource(final String... columns) {
		this.columns = columns;
		this.values = new Vector<Map<String, Object>>();
	}

	/**
	 * Add values of columns in order of position.
	 * 
	 * @param values
	 *            Ordered name of columns
	 */
	public void add(final Object... values) {
		final Map<String, Object> row = new TreeMap<String, Object>();
		for (int i = 0; i < values.length; i++) {
			row.put(this.columns[i], values[i]);
		}
		this.values.add(row);
	}

	@Override
	public Object getFieldValue(final JRField field) throws JRException {
		return this.currentRecord.get(field.getName());
	}

	public List<Map<String, Object>> getValues() {
		return this.values;
	}

	@Override
	public boolean next() throws JRException {
		if (this.iterator == null) {
			this.iterator = this.values.iterator();
		}
		final boolean hasNext = this.iterator.hasNext();
		if (hasNext) {
			this.currentRecord = this.iterator.next();
		}
		return hasNext;
	}
}
