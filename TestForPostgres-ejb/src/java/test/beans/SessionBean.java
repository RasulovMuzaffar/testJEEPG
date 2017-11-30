/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.beans;

import java.util.List;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import test.entities.Roles;
import test.entities.Users;

/**
 *
 * @author Muzaffar
 */
@Stateless(name = "SessionBean", mappedName = "TestForPostgres-ejbPU")
public class SessionBean implements SessionBeanLocal {

    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "TestForPostgres-ejbPU")
    private EntityManager em;

    public SessionBean() {
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    @Override
    public Object queryByRange(String jpqlStmt, int firstResult, int maxResult) {
        Query query = em.createQuery(jpqlStmt);
        if (firstResult > 0) {
            query = query.setFirstResult(firstResult);
        }
        if (maxResult > 0) {
            query = query.setMaxResults(maxResult);
        }
        return query.getResultList();
    }

    @Override
    public <T> T persistEntity(T entity) {
        em.persist(entity);
        return entity;
    }

    @Override
    public <T> T mergeEntity(T entity) {
        return em.merge(entity);
    }

    @Override
    public List<Users> getAllUsers() {
        return em.createNamedQuery("Users.findAll", Users.class).getResultList();
    }

    @Override
    public List<Roles> getAllRoles() {
        return (List<Roles>) em.createNamedQuery("Roles.findAll", Roles.class).getResultList();
    }

    @Override
    @Transactional
    public Users getUserByLoginPassword(String login, String password) {
        try {
            return (Users)em.createNamedQuery("Users.findByLoginPassword", Users.class)
                    .setParameter("login", login)
                    .setParameter("password", password).getSingleResult();
        } catch (Exception e) {
            System.out.println("--->>> "+e);
            return null;
        }
    }

    @Override
    public Roles getRoleById(int id) {
        return (Roles)em.find(Roles.class, id);
    }

    @Override
    public Users getUserById(int id) {
        return (Users)em.find(Users.class, id);
    }
    
    @Override
    public void removeUser(Users user) {
        user = em.find(Users.class, user.getId());
        em.remove(user);
    }


}
