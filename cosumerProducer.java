package test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class cosumerProducer {
     private static testLocker tl = new testLocker();
     
     public static void main(String[] args) {
    	 //Controller of the threads
		ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.execute(new ProducerTask());
		executor.execute(new ConsumerTask());
	}
     
     
 //inner class     
 private static class ProducerTask implements Runnable{

    		@Override
    		public void run() {
    			// TODO Auto-generated method stub
    			int i =1;
    			
			try {
				while(true){
    				System.out.println("producing:"+i);
    				tl.write(i++);
				Thread.sleep((int)Math.random()*10000);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
    		
    	}
 private static class ConsumerTask implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			int i =0;
			
		try {
			while(true){
				i = tl.read();
				System.out.println("Consuming:"+i);
				
			Thread.sleep((int)Math.random()*10000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		
	}
}
