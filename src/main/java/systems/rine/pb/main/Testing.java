package systems.rine.pb.main;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import systems.rine.pb.crawler.Skill;

public class Testing {

	public static void main(String[] args) throws IOException, InterruptedException {
		URL url = new URL("https://api.guildwars2.com/v2/skills");
		url.openConnection();
		Gson gson = new GsonBuilder().create();

		int[] arr = gson.fromJson(new String(url.openStream().readAllBytes()), int[].class);
			for (int i = 0; i < arr.length; i++) {
				try {
					url = new URL("https://api.guildwars2.com/v2/skills/" + arr[i] + "?lang=en");
					String temp = new String(url.openStream().readAllBytes());
					Skill sk = gson.fromJson(temp, Skill.class);
					System.out.println(sk.name);
				}catch(IOException e) {
					i--;
					Thread.sleep(1000);
				}
				System.out.println(i);
			}
		
		
		
		//Profession prof = gson.fromJson(new String(url.openStream().readAllBytes()), Profession.class);
		
//		for(SkillId k :  prof.skills) {
//			url = new URL("https://api.guildwars2.com/v2/skills/" + k.id + "?lang=en");
//			String temp = new String(url.openStream().readAllBytes());
//			Skill sk = gson.fromJson(temp, Skill.class);
//			System.out.println(sk.name);
//		}
//		System.out.println();
//		System.out.println("===============================================");
//		System.out.println();
//		for(SkillId k :  prof.weapons) {
//			url = new URL("https://api.guildwars2.com/v2/skills/" + k.id + "?lang=en");
//			String temp = new String(url.openStream().readAllBytes());
//			Skill sk = gson.fromJson(temp, Skill.class);
//			System.out.println(sk.name);
//		}
	}

}
