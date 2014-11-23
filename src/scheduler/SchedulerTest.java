package scheduler;

import java.util.PriorityQueue;

import org.junit.Test;

public class SchedulerTest {

	@Test
	public void RMSTest() {
		// TODO Auto-generated method stub
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

}
