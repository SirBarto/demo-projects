package pl.bgawron.hibernatewithspring.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import pl.bgawron.hibernatewithspring.config.HibernateFactory;
import pl.bgawron.hibernatewithspring.model.Employee;

import java.util.List;

@Repository
public class EmployeeDAO {

    private final HibernateFactory hibernateFactory = new HibernateFactory();

    public List<Employee> getAllEmployee()
    {
        Session session = hibernateFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try
        {
            List<Employee> result = session.createQuery("Select a from Employee a",Employee.class).list();
            session.getTransaction().commit();
            return result;
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

    public void updateEmployee(Employee employee)
    {
        Session session = hibernateFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try
        {
            String getEmployeeById = "Select e FROM Employee e WHERE id=:id";
            

            String hql = "UPDATE Employee set firstName=:firstName, lastName=:lastName WHERE id =:id";
            Query query = session.createQuery(hql);
            query.setParameter("firstName",employee.getFirstName());
            query.setParameter("lastName",employee.getLastName());
            int result = query.executeUpdate();
            //session.update(employee);
            System.out.println("Rows affected: "+result);
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

    public void deleteEmployee(Employee employee)
    {
        Session session = hibernateFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try
        {
            session.delete(employee);
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
