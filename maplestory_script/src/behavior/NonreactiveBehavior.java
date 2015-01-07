package behavior;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NonreactiveBehavior extends Behavior{
	public NonreactiveBehavior(){
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		
		log("nonreactive_behavior_operations_log.txt", "Non-reactive behavior created on " + df.format(new Date()));
		
	}
}
