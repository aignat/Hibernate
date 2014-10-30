import domain.Department;
import domain.Employee;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.List;


/**
 * @author aignat
 */
public class EventManager {

    public static void main(String[] args) {

        //open a session
        Session session = HibernateUtil.getSessionFactory().openSession();

        //begin transaction
        session.beginTransaction();

        Department department = new Department("java2");
        session.save(department);

        session.save(new Employee("Jakab Gipsz2", department));
        session.save(new Employee("Captain Nemo2", department));

        //end transaction, commit
        session.getTransaction().commit();

        session.beginTransaction();

        Query employeeQuery = session.createQuery("From Employee ");
        List<Employee> resultList = employeeQuery.list();

        for (Employee next : resultList) {
            System.out.println("next employee: " + next);
        }

        Query departmentQuery = session.createQuery("From Department");
        List<Department> departmentList = departmentQuery.list();

        for (Department d : departmentList) {
            System.out.println(d.getName());
        }
        session.getTransaction().commit();

        session.beginTransaction();

        Employee e1 = (Employee) session.get(Employee.class, 1L);
        System.out.println("Employee name: " + e1.getName());

        Employee e2 = (Employee) session.load(Employee.class, 2L);
        System.out.println("Employee name: " + e2.getName());

        session.getTransaction().commit();

        //close the session
        session.close();

        //close the session factory
        HibernateUtil.getSessionFactory().close();

    }

}
