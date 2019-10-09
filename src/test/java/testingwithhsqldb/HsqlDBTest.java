package testingwithhsqldb;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import org.hsqldb.cmdline.SqlFile;
import org.hsqldb.cmdline.SqlToolError;


public class HsqlDBTest {
	private static DataSource myDataSource;
	private static Connection myConnection ;
	
	private DAO myObject;
	
	@Before
	public  void setUp() throws IOException, SqlToolError, SQLException {
		// On crée la connection vers la base de test "in memory"
		myDataSource = getDataSource();
		myConnection = myDataSource.getConnection();
		// On crée le schema de la base de test
		executeSQLScript(myConnection, "schema.sql");
		// On y met des données
		executeSQLScript(myConnection, "bigtestdata.sql");		

            	myObject = new DAO(myDataSource);
	}
	
	private void executeSQLScript(Connection connexion, String filename)  throws IOException, SqlToolError, SQLException {
		// On initialise la base avec le contenu d'un fichier de test
		String sqlFilePath = HsqlDBTest.class.getResource(filename).getFile();
		SqlFile sqlFile = new SqlFile(new File(sqlFilePath));

		sqlFile.setConnection(connexion);
		sqlFile.execute();
		sqlFile.closeReader();		
	}
		
	@After
	public void tearDown() throws IOException, SqlToolError, SQLException {
		myConnection.close(); // La base de données de test est détruite ici
             	myObject = null; // Pas vraiment utile

	}

	@Test
	public void findExistingCustomer() throws SQLException {
		String name = myObject.nameOfCustomer(0);
		assertNotNull("Customer exists, name should not be null", name);
		assertEquals("Bad name found !", "Steel", name);
	}

	@Test
	public void nonExistingCustomerReturnsNull() throws SQLException {
		String name = myObject.nameOfCustomer(-1);
		assertNull("name should be null, customer does not exist !", name);
	}

	public static DataSource getDataSource() {
		org.hsqldb.jdbc.JDBCDataSource ds = new org.hsqldb.jdbc.JDBCDataSource();
		ds.setDatabase("jdbc:hsqldb:mem:testcase;shutdown=true");
		ds.setUser("sa");
		ds.setPassword("sa");
		return ds;
	}
        
        @Test
	public void ExistingProduct() throws SQLException {
		Product product = myObject.getProduct(0);
                String name = product.getName();
                assertEquals("Iron Iron",name);
	}
        @Test
	public void nonExistingProduct() throws SQLException {
		Product product = myObject.getProduct(-1);
                assertNull("product should be null, product does not exist !", product);
	}
        
        @Test
	public void InsertProduct() throws SQLException {
                Product product = myObject.getProduct(666);
                assertNull("product should be null, product does not exist !", product);
                
                Product newproduct = new Product(666,"bleu",10.6);
                assertEquals(1, myObject.addProduct(newproduct));
                
                Product product2 = myObject.getProduct(666);
                String name = product2.getName();
                assertEquals("bleu",name);
	}
}
