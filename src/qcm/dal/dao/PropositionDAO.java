package qcm.dal.dao;

import java.util.ArrayList;

import fr.eni.tp.web.common.dal.exception.DaoException;
import qcm.bo.Proposition;
import qcm.bo.Question;

public interface PropositionDAO extends GenericDAO<Question, Integer> {
	
	ArrayList<Proposition> selectByIdQuestion(Integer idQuestion) throws DaoException;
}
