package qcm.dal.dao;
import java.util.List;

import fr.eni.tp.web.common.dal.exception.DaoException;
import qcm.bo.ReponseUtilisateur;

public interface ReponseUtilisateurDAO extends GenericDAO<ReponseUtilisateur, Integer> {

	List<ReponseUtilisateur> selectByQuestionTirage() throws DaoException;

}
