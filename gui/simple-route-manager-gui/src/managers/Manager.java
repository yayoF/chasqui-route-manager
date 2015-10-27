package managers;

import Util.MHibernateUtil;
import models.Model;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Joca
 */
public class Manager {



    public Session getSession() throws HibernateException {
        Session sess = null;
        try {
            sess = MHibernateUtil.getSessionFactory().getCurrentSession();
        } catch (org.hibernate.HibernateException he) {
            sess = MHibernateUtil.getSessionFactory().openSession();
        }
        return sess;
    }

    public void addEntity(Model m) {
        Session s = this.getSession();

        s.beginTransaction();

        s.save(m);

        s.getTransaction().commit();

        s.clear();
    }

    public void deleteEntity(int id) {

    }
}
