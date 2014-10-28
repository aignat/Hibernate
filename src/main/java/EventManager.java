import domain.Department;
import domain.Employee;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.List;


/**
 * @author aignat
 */
public class EventManager {

    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
        Department department = new Department("java2");
        session.save(department);

        session.save(new Employee("Jakab Gipsz2", department));
        session.save(new Employee("Captain Nemo2", department));

        session.getTransaction().commit();

        Query q = session.createQuery("From Employee ");
        List<Employee> resultList = q.list();
        System.out.println("num of employess:" + resultList.size());

        for (Employee next : resultList) {
            System.out.println("next employee: " + next);
        }

        Query q2 = session.createQuery("From Department");
        List<Department> departmentList = q2.list();

        for (Department d : departmentList) {
            System.out.println(d.getName());
        }

        session.close();

    }

}
