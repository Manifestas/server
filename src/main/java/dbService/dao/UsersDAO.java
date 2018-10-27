package dbService.dao;

import accounts.UserProfile;
import dataSets.UsersDataSet;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class UsersDAO {

    private Session session;

    public UsersDAO(Session session) {
        this.session = session;
    }

    public UsersDataSet get(long id) {
        return session.get(UsersDataSet.class, id);
    }

    public UsersDataSet get(String name) {
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        // create a CriteriaQuery object with the specified result type.
        CriteriaQuery<UsersDataSet> criteriaQuery = criteriaBuilder.createQuery(UsersDataSet.class);
        // create and add a query root corresponding to the given entity.
        Root<UsersDataSet> usersDataSetRoot = criteriaQuery.from(UsersDataSet.class);
        criteriaQuery.where(criteriaBuilder.equal(usersDataSetRoot.get("name"), name));
        Query<UsersDataSet> query = session.createQuery(criteriaQuery);
        UsersDataSet result = query.getSingleResult();
        return result;
    }

    public long getUserId(String name) {
        UsersDataSet user = get(name);
        if (user != null) {
            return user.getId();
        }
        return 0;

    }

    public long insertUser(String name) {
        return (Long) session.save(new UsersDataSet(name));
    }

    public long insertUser(UserProfile userProfile) {
        return (Long) session.save(new UsersDataSet(userProfile));
    }
}
