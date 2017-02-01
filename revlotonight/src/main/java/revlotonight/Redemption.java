package revlotonight;

import com.google.gson.Gson;

public class Redemption {
	private Integer reward_id;
	private Integer redemption_id;
	private String created_at;
	private String refunded;
	private String completed;
	private Object user_input;
	private String username;
	
	public Integer getRewardID() {
		return reward_id;
	}
	
	public Object getUserInput() {
		return user_input;
	}
	
	public String getUsername() {
		return username;
	}
}
