package be.vdab.dao;

import javax.sql.DataSource;

abstract class AbstractDAO {

	public static final String JNDI_NAME = "jdbc/MijnFrituur";
	
	protected DataSource dataSource;

	/**
	 * @param dataSource the dataSource to set
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
}
