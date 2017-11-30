/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.beans;

import java.util.List;
import javax.ejb.Local;
import test.entities.Roles;
import test.entities.Users;

/**
 *
 * @author Muzaffar
 */
@Local
public interface SessionBeanLocal {

    Object queryByRange(String jpqlStmt, int firstResult, int maxResults);

    <T> T persistEntity(T entity);

    <T> T mergeEntity(T entity);

    List<Users> getAllUsers();

    List<Roles> getAllRoles();

    Users getUserByLoginPassword(String login, String password);

    Roles getRoleById(int id);

    Users getUserById(int id);

    void removeUser(Users user);
    
}
