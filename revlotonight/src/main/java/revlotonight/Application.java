package revlotonight;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import com.google.gson.Gson;

/**
 * @author Cameron.Scott
 *
 */
public class Application {
	private static String REVLO_API_KEY;

	public static void main(String[] args) {
		REVLO_API_KEY = args[0];
		try {
			RewardsWrapper rewards = getRewards();
			Integer songRewardID = getRewardIDByName(rewards, "Song Request");
			ArrayList<Redemption> songRequests = getRedemptionsByID(songRewardID);
			
			for (Redemption red : songRequests) {
				System.out.println("Song :" + red.getUserInput().toString() + " Username: " + red.getUsername());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static RewardsWrapper getRewards() throws IOException {
		String revlo_url = "https://api.revlo.co/1/rewards?page=1";
		URL url;

		url = new URL(revlo_url);
		HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.addRequestProperty("x-api-key", REVLO_API_KEY);

		int responseCode = con.getResponseCode();
		System.out.println("Response code recieved: " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		Gson gson = new Gson();
		RewardsWrapper rewards = gson.fromJson(response.toString(), RewardsWrapper.class);
		return rewards;
	}

	/**
	 * @param rewards
	 * @param name
	 * @return
	 */
	public static Integer getRewardIDByName(RewardsWrapper rewards, String name) {
		for (Reward r : rewards.getRewards()) {
			if (r.getTitle().equals(name)) {
				return r.getReward_id();
			}
		}
		return null;
	}

	public static ArrayList<Redemption> getRedemptionsByID(Integer id) throws IOException {
		RedemptionsWrapper page1 = getRedemptionsPage(1);
		ArrayList<Redemption> matches = new ArrayList<Redemption>();

		Integer total = page1.getTotal();
		Integer pagesize = page1.getPage_size();

		Integer pagecount = (total + pagesize - 1) / pagesize;
		RedemptionsWrapper page;
		for (int i = 1; i <= pagecount; i++) {
			page = getRedemptionsPage(i);
			for (Redemption r : page.getRedemptions()) {
				if (r.getRewardID().equals(id)) {
					matches.add(r);
				}
			}
		}

		return matches;
	}

	public static RedemptionsWrapper getRedemptionsPage(int page) throws IOException {
		String revlo_url = String.format("https://api.revlo.co/1/redemptions?page=%d&refunded=false", page);
		URL url;

		url = new URL(revlo_url);
		HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.addRequestProperty("x-api-key", REVLO_API_KEY);

		int responseCode = con.getResponseCode();
		System.out.println("Response code recieved: " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		Gson gson = new Gson();
		RedemptionsWrapper redemptions = gson.fromJson(response.toString(), RedemptionsWrapper.class);

		return redemptions;
	}

}
