package qcm.dal.dao;

import java.util.List;

import fr.eni.tp.web.common.dal.exception.DaoException;
import qcm.bo.SectionTest;

public interface SectionTestDAO extends GenericDAO<SectionTest, Integer> {

	List<SectionTest> selectByIdTest(Integer idTest) throws DaoException;
	

}
