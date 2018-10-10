package tests;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.eni.tp.web.common.bll.exception.ElementNotFoundException;
import fr.eni.tp.web.common.bll.exception.ManagerException;
import fr.eni.tp.web.common.exception.FunctionalException;
import qcm.bll.factory.ManagerFactory;
import qcm.bll.manager.UtilisateurManager;
import qcm.bo.Utilisateur;
import qcm.dal.factory.JdbcTools;

public class utilisateurTest {
	//(et installer le plugin eclipse EclEmma Java Code Coverage 3.1.1)
	
	private UtilisateurManager utilisateurManager = ManagerFactory.utilisateurManager();

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
	public void test() throws ManagerException, FunctionalException {
		
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
			userAdmin = utilisateurManager.findByEmail(emailUserAdmin);
		}
		catch(Exception e){
			//ElementNotFoundException/ManagerException
			//no problem it happens on first launch
		}
		try {
			userToto = utilisateurManager.findByEmail(emailUserToto);
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
			userAdmin = utilisateurManager.save(userAdmin);
		}
		if(userToto==null){
			userToto = new Utilisateur();
			userToto.setNom("adminNom");
			userToto.setPrenom("adminPrenom");
			userToto.setEmail(emailUserToto);
			userToto.setPassword(passwordUserToto);
			userToto.setType("user");
			userToto = utilisateurManager.save(userToto);
		}
		
		//on peut maintenant tester checkLogin() :
		Utilisateur result;
		result = utilisateurManager.checkLogin("fail", "fail");
		assertNull(result);
		result = utilisateurManager.checkLogin(emailUserAdmin, passwordUserAdmin);
		assertNotNull(result);
		assertEquals("admin", result.getType());
		result = utilisateurManager.checkLogin(emailUserToto, passwordUserToto);
		assertNotNull(result);
		assertNotEquals("admin", result.getType());
		
	}

}
