package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.eni.tp.web.common.bll.exception.ElementNotFoundException;
import fr.eni.tp.web.common.bll.exception.ManagerException;
import qcm.bll.factory.ManagerFactory;
import qcm.bll.manager.UtilisateurManager;
import qcm.bo.Utilisateur;

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
	public void test() {
		
		//insertion des utilisateurs de test (admin et toto):
		Utilisateur userAdmin = null;
		Utilisateur userToto = null;
		String emailUserAdmin = "admin@campus-eni.fr";
		String emailUserToto = "toto@campus-eni.fr";
		String passwordUserAdmin = "admin";
		String passwordUserToto = "toto";
		try {
			userAdmin = utilisateurManager.findByEmail(emailUserAdmin);
			if(userAdmin==null){
				userAdmin = new Utilisateur();
				userAdmin.setNom("totoNom");
				userAdmin.setPrenom("totoPrenom");
				userAdmin.setEmail(emailUserAdmin);
				userAdmin.setPassword(passwordUserAdmin);
				userAdmin = utilisateurManager.save(userAdmin);
			}
			userToto = utilisateurManager.findByEmail(emailUserToto);
			if(userToto==null){
				userToto = new Utilisateur();
				userToto.setNom("adminNom");
				userToto.setPrenom("adminPrenom");
				userToto.setEmail(emailUserToto);
				userToto.setPassword(passwordUserToto);
				userToto = utilisateurManager.save(userToto);
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail("[EXCEPTION THROWED]");
		}
		
		//on peut maintenant tester checkLogin() :
		Utilisateur result;
		result = utilisateurManager.checkLogin("fail", "fail");
		assertNull(result);
		result = utilisateurManager.checkLogin(emailUserAdmin, passwordUserAdmin);
		assertNotNull(result);
		assertEquals("admin", result.getType());
		result = utilisateurManager.checkLogin(emailUserToto, passwordUserAdmin);
		assertNotNull(result);
		assertNotEquals("admin", result.getType());
		
	}

}
