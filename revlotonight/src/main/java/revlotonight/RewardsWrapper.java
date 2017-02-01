package revlotonight;

import java.util.ArrayList;

public class RewardsWrapper {
	private ArrayList<Reward> rewards;
	
	public Reward getReward(int pos) {
		return rewards.get(pos);
	}
	
	public ArrayList<Reward> getRewards() {
		return rewards;
	}
}
