package employeeManagement.branch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import employeeManagement.DBConnection.DbConnection;

/**
 * <p>
 * Implementation to access/insert/delete Branch in database.
 * </p>
 */
class BranchDAO {
  
	/**
   * <p>
   * It get the branch if exist from database.
   * </p>
   * @param id is used for searching the branch from Branch Data by id.
   * @return The Branch object if exist or null.
   */
  public Branch getBranchIfExist(int id) {
    try(Connection conn = DbConnection.getInstance()) {
			Statement statement = conn.createStatement();
			String sql = "select * from branch where id = " + id;
			ResultSet resultSet = statement.executeQuery(sql);
			if(resultSet.next()) {
				return new Branch(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
			} else {
				return null;
      }
		} catch(SQLException e) {
			System.out.println(e);
			return null;
		}
  }
  
	/**
   * <p>
   * It insert the branch into database.
   * </p>
   * @param branch is used for insert branch object into database.
   * @return The Branch object.
   */
  public Branch insertBranch(Branch branch) {
		try(Connection conn = DbConnection.getInstance()) {
			Statement statement = conn.createStatement();
			String name = branch.getName();
			String location = branch.getLocation();
			String sql = "insert into branch(name, location) values(?, ?)";
			PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, location);
			preparedStatement.executeUpdate();
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			resultSet.next();
			branch.setId(resultSet.getInt(1));
			System.out.println(branch);
			return branch;
		} catch(SQLException e) {
			System.out.println(e);
			return null;
		}
  }
  
	/**
   * <p>
   * It updates the branch in the database.
   * </p>
   * @param branch is used for find specific branch in database.
   */
	public void updateBranchInDb(Branch branch) {
		try(Connection conn = DbConnection.getInstance()) {
			String name = branch.getName();
			String location = branch.getLocation();
			String sql = "update branch set name = ?, location = ? where id = " + branch.getId();
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, location);
			int execution = preparedStatement.executeUpdate();
		} catch(SQLException e) {
			System.out.println(e);
		}
	}
  
}  