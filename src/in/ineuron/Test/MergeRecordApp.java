package in.ineuron.Test;

import java.io.IOException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import in.ineuron.Model.Student;
import in.ineuron.Util.HibernateUtil;

public class MergeRecordApp {

	public static void main(String[] args) throws IOException {
		
		Session session=null;
		Transaction transaction=null;
		boolean flag = false;
		Student std = null;
		
		try {
			
		Student student	=new Student();
		student.setSid(8);
		student.setSname("Sachin");
		student.setSaddress("DD");
		student.setSage(48);
		session=HibernateUtil.geSession();
		
		if(session !=null)
		{
			transaction = session.beginTransaction();
			if(transaction != null)
			{
				
				 std=(Student) session.merge(student);
				
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
			System.out.println("Object saved to database " + std);
			
			
			}
			else {
				transaction.rollback();
				System.out.println("Object not saved to database " + std);
			
			}
			
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}

	}

}
