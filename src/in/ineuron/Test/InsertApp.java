package in.ineuron.Test;

import java.io.IOException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import in.ineuron.Model.Student;
import in.ineuron.Util.HibernateUtil;

public class InsertApp {

	public static void main(String[] args) throws IOException {
		
		Session session=null;
		Transaction transaction=null;
		boolean flag = false;
		
		try {
		session=HibernateUtil.geSession();
		
		if(session !=null)
		{
			transaction = session.beginTransaction();
			if(transaction != null)
			{
				Student student = new Student();
				
				student.setSname("Dhoni");
				student.setSaddress("CSK");
				student.setSage(35);
				session.save(student);
				
				flag=true;
				
			}
			
		}
		}catch (HibernateException e) {
			e.printStackTrace();
			
		}catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			if(flag) {
				transaction.commit();
			System.out.println("Object saved to database");
			
			
			}
			else {
				transaction.rollback();
				System.out.println("Object not saved to database");
			
			}
			
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}

	}

}
