package uz.pdp.jakarta_ee.jakarta_ee.dao;

import lombok.NonNull;
import uz.pdp.jakarta_ee.jakarta_ee.entity.AuthUser;

import java.util.Optional;

public class AuthUserDAO extends BaseDAO<AuthUser, String> {


    public Optional<AuthUser> findByEmail(@NonNull String email) {
        try {
            begin();
            AuthUser authUser = em.createQuery("select t from AuthUser t where t.email ilike :email", AuthUser.class)
                    .setParameter("email", email)
                    .getSingleResult();
            commit();
            return Optional.ofNullable(authUser);
        } catch (Exception e) {
//            e.printStackTrace();
            rollBack();
            return Optional.empty();
        }
    }

}
