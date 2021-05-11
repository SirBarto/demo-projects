package pl.bgawron.hibernatewithspring.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import pl.bgawron.hibernatewithspring.config.HibernateFactory;
import pl.bgawron.hibernatewithspring.repository.dao.EmployeeDAO;
import pl.bgawron.hibernatewithspring.model.Employee;

import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

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

    public Optional<Employee> getById(Long id)
    {
        Session session = hibernateFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        final String hql = "Select a from Employee a WHERE a.id=:id";

        try
        {
            Employee employee = session.createQuery(hql, Employee.class)
                    .setParameter("id", id).uniqueResult();

            //Employee employee = session.find(Employee.class,id);

            session.getTransaction().commit();
            return Optional.ofNullable(employee);
        }
        catch (Exception e)
        {
            System.out.println("Employee by id: "+id+" doesnt exits");
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

    public Employee addEmployee(Employee employee)
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
        return employee;
    }

    public void updateEmployee(Employee employee)
    {
        Session session = hibernateFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        try
        {
            String getEmployeeById = "Select e FROM Employee e WHERE id=:id";

           /* String hql = "UPDATE Employee e set e.firstName=:firstName, e.lastName=:lastName, e.gender=:gender WHERE e.id=:id";

            Query query = session.createQuery(hql);
            query.setParameter("firstName",employee.getFirstName());
            query.setParameter("lastName",employee.getLastName());
            query.setParameter("gender",employee.getGender());
            query.setParameter("id",employee.getId());
            int result = query.executeUpdate();
            */
            session.update(employee);
            //System.out.println("Rows affected: "+result);
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

        final String hql = "DELETE FROM Employee a WHERE a.id=:id";

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
