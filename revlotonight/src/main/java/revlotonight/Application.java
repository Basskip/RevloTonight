package revlotonight;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import com.google.gson.Gson;

public class Application {
	private static final String REVLO_API_KEY = "bT3bY_6AvR3mUlmqTm-ERn1Z235yUeEQJZwiLcEkWWU";

	public static void main(String[] args) {
		try {
			getRewards();
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
	
	public static String getRewardIDByName(RewardsWrapper rewards, String name) {
		for (Reward r : rewards.getRewards()) {
			if (r.getTitle().equals(name)) {
				return r.getReward_id();
			}
		}
		return null;
	}
	
	public static List<String> getSongRequests() {
		String revlo_url = ""
		
		return null;
	}

}
