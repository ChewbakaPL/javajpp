package qcm.dal.dao;
import java.util.List;

import fr.eni.tp.web.common.dal.exception.DaoException;
import qcm.bo.Question;

public interface QuestionDAO extends GenericDAO<Question, Integer> {

	List<Question> selectByTheme(Integer idTheme, Integer nbQuestionATirer) throws DaoException;

}
