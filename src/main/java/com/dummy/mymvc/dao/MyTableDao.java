package com.dummy.mymvc.dao;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dummy.mymvc.entities.MyTable;

@Repository
@Transactional
public class MyTableDao {
	
	private SessionFactory sessionFactory;
	
	@Autowired
	public MyTableDao(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 * add new record to table
	 */
	public boolean insertRecord(MyTable myTable) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(myTable);
		session.flush();
		return Boolean.TRUE;
	}

}
