package scheduler;

import java.util.PriorityQueue;

import org.junit.Test;

public class SchedulerTest {

	@Test
	public void RMSTest() {
		int computation_time = 1;
		int period = 3;
		int deadline = 3;
		Task rms_task_1 = new RMSTask(computation_time, period, deadline);
		Task rms_task_2 = new RMSTask(1, 5, 5);
		Task rms_task_3 = new RMSTask(1, 6, 6);
		Task rms_task_4 = new RMSTask(2, 10, 10);
		PriorityQueue<Task> task_queue = new PriorityQueue<Task>();
		task_queue.add(rms_task_1);
		task_queue.add(rms_task_2);
		task_queue.add(rms_task_3);
		task_queue.add(rms_task_4);
		
		int total_time = 48;
		Schedule schedule_instance = new Schedule(task_queue, total_time);
		try {
			schedule_instance.runAll();
			schedule_instance.printSchedule();
		} catch (UnschedulableException e) {
			// TODO Auto-generated catch block
			System.out.println("Task set can't be scheduled!");
		}
	}
	
	@Test
	public void EDFTest() {
		int computation_time = 1;
		int period = 8;
		int deadline = 8;
		Task edf_task_1 = new EDFTask(computation_time, period, deadline, 11);
		Task edf_task_2 = new EDFTask(2, 5, 5, 12);
		Task edf_task_3 = new EDFTask(4, 10, 10, 13);
		PriorityQueue<Task> task_queue = new PriorityQueue<Task>();
		task_queue.add(edf_task_1);
		task_queue.add(edf_task_2);
		task_queue.add(edf_task_3);
		
		int total_time = 48;
		Schedule schedule_instance = new Schedule(task_queue, total_time);
		try {
			schedule_instance.runAll();
			System.out.println("EDF*****");
			schedule_instance.printSchedule();
		} catch (UnschedulableException e) {
			// TODO Auto-generated catch block
			System.out.println("Task set can't be scheduled!");
		}
	}

}
