package tests;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.eni.tp.web.common.dal.exception.DaoException;
import fr.eni.tp.web.common.exception.FunctionalException;
import qcm.bo.Utilisateur;
import qcm.common.JdbcTools;
import qcm.dal.dao.impl.UtilisateurDaoImpl;

public class utilisateurTest {
	//(et installer le plugin eclipse EclEmma Java Code Coverage 3.1.1)
	
	protected UtilisateurDaoImpl utilisateurDao = new UtilisateurDaoImpl();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws FunctionalException, DaoException {
		
		//test connexion bdd:
		Connection testMMSQL = null;
		try {
			testMMSQL = JdbcTools.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			fail("1) [EXCEPTION THROWED] "+e.getMessage());
		}
		assertNotNull(testMMSQL);
		
		
		//insertion des utilisateurs de test (admin et toto):
		Utilisateur userAdmin = null;
		Utilisateur userToto = null;
		String emailUserAdmin = "admin@campus-eni.fr";
		String emailUserToto = "toto@campus-eni.fr";
		String passwordUserAdmin = "admin";
		String passwordUserToto = "toto";
		try {
			userAdmin = utilisateurDao.selectByEmail(emailUserAdmin);
		}
		catch(Exception e){
			//ElementNotFoundException/ManagerException
			//no problem it happens on first launch
		}
		try {
			userToto = utilisateurDao.selectByEmail(emailUserToto);
		}
		catch(Exception e){
			//ElementNotFoundException/ManagerException
			//no problem it happens on first launch
		}
		
		if(userAdmin==null){
			userAdmin = new Utilisateur();
			userAdmin.setNom("totoNom");
			userAdmin.setPrenom("totoPrenom");
			userAdmin.setEmail(emailUserAdmin);
			userAdmin.setPassword(passwordUserAdmin);
			userAdmin.setType("admin");
			userAdmin = utilisateurDao.insert(userAdmin);
		}
		if(userToto==null){
			userToto = new Utilisateur();
			userToto.setNom("adminNom");
			userToto.setPrenom("adminPrenom");
			userToto.setEmail(emailUserToto);
			userToto.setPassword(passwordUserToto);
			userToto.setType("user");
			userToto = utilisateurDao.insert(userToto);
		}
		
		//on peut maintenant tester checkLogin() :
		Utilisateur result;
		result = utilisateurDao.checkLogin("fail", "fail");
		assertNull(result);
		result = utilisateurDao.checkLogin(emailUserAdmin, passwordUserAdmin);
		assertNotNull(result);
		assertEquals("admin", result.getType());
		result = utilisateurDao.checkLogin(emailUserToto, passwordUserToto);
		assertNotNull(result);
		assertNotEquals("admin", result.getType());
		
	}

}
