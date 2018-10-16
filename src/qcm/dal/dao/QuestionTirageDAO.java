package qcm.dal.dao;
import java.util.ArrayList;

import fr.eni.tp.web.common.dal.exception.DaoException;
import qcm.bo.QuestionTirage;

public interface QuestionTirageDAO extends GenericDAO<QuestionTirage, Integer> {

	ArrayList<QuestionTirage> selectByIdEpreuve(Integer idEpreuve) throws DaoException;


}
