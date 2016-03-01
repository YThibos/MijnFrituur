package be.vdab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import be.vdab.entities.Gbentry;

public class GbentryDAO extends AbstractDAO {

	private static final String SQL_SELECT_ALL = 
			"SELECT id, naam, bericht, datum FROM gbentries ORDER BY datum DESC";
	private static final String SQL_INSERT_ENTRY = 
			"INSERT INTO gbentries (naam, bericht) VALUES (?, ?)";
	
	public List<Gbentry> findAll() {
		
		try (Connection connection = dataSource.getConnection();
				Statement statement = connection.createStatement();
				ResultSet results = statement.executeQuery(SQL_SELECT_ALL)) {
			
			List<Gbentry> entries = new ArrayList<>();
			
			while (results.next()) {
				entries.add(mapEntry(results));
			}
			
			return entries;
			
		}
		catch (SQLException ex ) {
			throw new DAOException(ex);			
		}
		
	}
	
	private Gbentry mapEntry(ResultSet results) throws SQLException {
		return new Gbentry(results.getLong("id"), results.getString("naam"), results.getString("bericht"), results.getTimestamp("datum").toLocalDateTime());
	}
	
	public void write(Gbentry entry) {
		
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_INSERT_ENTRY)) {
			
			statement.setString(1, entry.getNaam());
			statement.setString(2, entry.getBericht());
			
			statement.executeUpdate();
			
		}
		catch (SQLException ex ) {
			throw new DAOException(ex);
		}
		
	}
	
}
