package qcm.dal.dao;

import java.util.ArrayList;

import fr.eni.tp.web.common.dal.exception.DaoException;
import qcm.bo.Proposition;

public interface PropositionDAO extends GenericDAO<Proposition, Integer> {
	
	ArrayList<Proposition> selectByIdQuestion(Integer idQuestion) throws DaoException;
}
