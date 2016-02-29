package be.vdab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import be.vdab.entities.Ingredient;
import be.vdab.entities.Saus;

public class SausDAO extends AbstractDAO {

	private static final String SQL_READ = "SELECT sauzen.id as sausid, sauzen.naam as sausnaam"
			+ " FROM sauzen LEFT JOIN sauzeningredienten ON sauzen.id=sauzeningredienten.sausid"
			+ " LEFT JOIN ingredienten ON sauzeningredienten.ingredientid=ingredienten.id"
			+ " WHERE sauzen.id=?";
	private static final String SQL_FIND_ALL = "SELECT sauzen.id as sausid, sauzen.naam as sausnaam,"
			+ " ingredienten.id as ingredientid, ingredienten.naam as ingredientnaam" + " FROM sauzen"
			+ " LEFT JOIN sauzeningredienten" + " ON sauzen.id=sauzeningredienten.sausid" + " LEFT JOIN ingredienten"
			+ " ON sauzeningredienten.ingredientid=ingredienten.id" + " ORDER BY sauzen.naam";
	private static final String SQL_FIND_BY_INGREDIENT = "SELECT sauzen.id as sausid, sauzen.naam as sausnaam,"
			+ " ingredienten.id as ingredientid, ingredienten.naam as ingredientnaam" + " FROM sauzen"
			+ " LEFT JOIN sauzeningredienten" + " ON sauzen.id=sauzeningredienten.sausid" + " LEFT JOIN ingredienten"
			+ " ON sauzeningredienten.ingredientid=ingredienten.id" + " WHERE ingredienten.naam = ?"
			+ " ORDER BY sauzen.naam";
	private static final String SQL_DELETE = "DELETE FROM sauzen WHERE naam=?";

	// private static Map<Long, Saus> SAUZEN = new ConcurrentHashMap<>();

	// static {
	// List<String> cocktailIngr = Arrays.asList("ketchup", "mayonaise",
	// "mierikswortel");
	// SAUZEN.put(1L, new Saus(1L, "cocktail", cocktailIngr));
	//
	// List<String> mayoIngr = Arrays.asList("eierdooiers", "olie");
	// SAUZEN.put(2L, new Saus(2L, "mayonaise", mayoIngr));
	//
	// List<String> mosterdIngr = Arrays.asList("mosterdzaad", "azijn", "water",
	// "zout");
	// SAUZEN.put(3L, new Saus(3L, "mosterd", mosterdIngr));
	//
	// List<String> tartareIngr = Arrays.asList("mayonaise", "ei", "peterselie",
	// "sjalotjes");
	// SAUZEN.put(4L, new Saus(4L, "tartare", tartareIngr));
	//
	// List<String> vinaigretteIngr = Arrays.asList("olijfolie", "wijnazijn",
	// "zout", "peper", "tuinkruiden", "mosterd");
	// SAUZEN.put(5L, new Saus(5L, "vinaigrette", vinaigretteIngr));
	// }

	public List<Saus> findAll() {
		try (Connection connection = dataSource.getConnection();
				Statement statement = connection.createStatement();
				ResultSet results = statement.executeQuery(SQL_FIND_ALL)) {

			List<Saus> sauzen = new ArrayList<>();

			long previousRowID = -1;

			while (results.next()) {
				if (previousRowID != results.getLong("sausid")) {
					sauzen.add(mapRowToNewSaus(results));
				} else {
					sauzen.get(sauzen.size() - 1).addIngredient(
							new Ingredient(results.getLong("ingredientid"), results.getString("ingredientnaam")));
				}
				previousRowID = results.getLong("sausid");
			}

			return sauzen;
		} catch (SQLException ex) {
			throw new DAOException(ex);
		}
	}

	public List<Saus> findByIngredient(String ingredient) {
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_INGREDIENT)) {

			statement.setString(1, ingredient);

			try (ResultSet results = statement.executeQuery()) {

				List<Saus> gevondenSauzen = new ArrayList<>();

				while (results.next()) {
					gevondenSauzen.add(mapRowToNewSaus(results));
				}
				return gevondenSauzen;

			}
		} catch (SQLException ex) {
			throw new DAOException(ex);
		}

	}

	public List<Saus> findByMultIngredients(List<String> ingredienten) {

		List<Saus> gevondenSauzen = new ArrayList<>();

		for (String ingredient : ingredienten) {
			gevondenSauzen.addAll(findByIngredient(ingredient));
		}

		return gevondenSauzen;

	}

	public void deleteByName(List<String> teVerwijderenSauzen) {

		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_DELETE)) {

			for (String saus : teVerwijderenSauzen) {
				statement.setString(1, saus);
				statement.executeUpdate();
			}

		} catch (SQLException ex) {
			throw new DAOException(ex);
		}

	}

	public Saus read(long id) {

		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_READ)) {

			statement.setLong(1, id);
			ResultSet results = statement.executeQuery();
			
			if (results.next()) {
				return mapRowToNewSaus(results);
			}
			
			return null;
			
		} catch (SQLException ex) {
			throw new DAOException(ex);
		}

	}

	private Saus mapRowToNewSaus(ResultSet results) throws SQLException {
		List<Ingredient> ingredienten = new ArrayList<>();
		ingredienten.add(new Ingredient(results.getLong("ingredientid"), results.getString("ingredientnaam")));
		return new Saus(results.getLong("sausid"), results.getString("sausnaam"), ingredienten);
	}

}
