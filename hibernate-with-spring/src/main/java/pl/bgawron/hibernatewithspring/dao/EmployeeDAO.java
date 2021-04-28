package pl.bgawron.hibernatewithspring.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import pl.bgawron.hibernatewithspring.config.HibernateFactory;
import pl.bgawron.hibernatewithspring.model.Employee;

@Repository
public class EmployeeDAO {

    private final HibernateFactory hibernateFactory = new HibernateFactory();

    public void addEmployee(Employee employee)
    {
        Session session = hibernateFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try
        {
            session.save(employee);
            session.getTransaction().commit();
        }
        catch (Exception e)
        {
            transaction.rollback();
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        finally
        {
            session.close();
            hibernateFactory.getSessionFactory().close();
        }
    }


}
