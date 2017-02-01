package revlotonight;

import java.util.ArrayList;

public class Reward {
	private String reward_id;
	private String created_at;
	private String title;
	private String bot_command;
	private String enabled;
	private String points;
	private String sub_only;
	private ArrayList<String> input_fields;
	private String description;
	private String img_url;
	
	public String getReward_id() {
		return reward_id;
	}
	public void setReward_id(String reward_id) {
		this.reward_id = reward_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
