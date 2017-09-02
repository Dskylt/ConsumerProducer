package test;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class testLocker {
	static final int CAP = 1;
   Lock lock =new ReentrantLock();
   Condition notEmpty = lock.newCondition();
   Condition notFull = lock.newCondition();
   LinkedList<Integer> queue = new LinkedList<>();
   @SuppressWarnings("finally")
public int read(){
	   lock.lock();
	  int value=0;
		   try {
			   while(queue.isEmpty()){
				   System.out.println("empty now!");
				   notEmpty.await();
			   }
			   value = queue.pop();
			   notFull.signal();
		   } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		   }
			 finally {
				lock.unlock();
				return value;
			}
	   
	   
   }
   public void write(int value){
	   lock.lock();
	   
		   try {
			   while(CAP==queue.size()){
				   System.out.println("full now!");
				   notFull.await();
			   }
			   queue.offer(value);
			   notEmpty.signal();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			lock.unlock();
		}
	   
   }
}
