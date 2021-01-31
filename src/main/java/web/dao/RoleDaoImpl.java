package web.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Transactional
@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Role findRoleByName(String name){
        String hql ="FROM Role Where name:=name";
        Query query = entityManager.createQuery(hql).setParameter("name",name);

        return (Role) query.getSingleResult();
    }

}
