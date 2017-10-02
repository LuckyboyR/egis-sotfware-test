package za.co.egis.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

 /**
 * @author Luckyboy.Rapudi
 *
 */
public class EgisTestMain {

	public static void main(String[] args) {

		final String HTML = getUrlContents("https://github.com/egis/handbook/blob/master/Tech-Stack.md");
		Document document = Jsoup.parse(HTML);
		Element a = document.select("article").first();
		Elements arrayName = a.getElementsByTag("h2");
		for (int t = 0; t < arrayName.size(); t++) {

			switch (t) {
			case 1:
				JSONObject jsonObj1 = new JSONObject();
				JSONArray jsonArr1 = new JSONArray();
				Element table1 = document.select("table").get(t);
				Elements header1 = table1.getElementsByTag("th");
				Elements col1 = table1.getElementsByTag("td");
				JSONObject jo1 = new JSONObject();

				int k1 = 0;
				for (int i = 0; i < header1.size(); i++) {
					k1 = i;
					String key = header1.get(i).text();

					for (; k1 < col1.size();) {
						String value = col1.get(k1).text();
						k1 = k1 + 4;
						jo1.append(key, value);

					}

				}
				jsonArr1.put(jo1);
				jsonObj1.put(arrayName.get(t).text(), jsonArr1);
				System.out.println(jsonObj1.toString());
				break;
			case 3:
				break;
			default:
				JSONObject jsonObj = new JSONObject();
				JSONArray jsonArr = new JSONArray();
				Element table = document.select("table").get(t);
				Elements header = table.getElementsByTag("th");
				Elements col = table.getElementsByTag("td");
				JSONObject jo = new JSONObject();

				int k = 0;
				for (int i = 0; i < header.size(); i++) {
					k = i;
					String key = header.get(i).text();

					for (; k < col.size();) {
						String value = col.get(k).text();
						k = k + 3;
						jo.append(key, value);

					}

				}
				jsonArr.put(jo);
				jsonObj.put(arrayName.get(t).text(), jsonArr);
				System.out.println(jsonObj.toString());
				break;
			}

		}

	}

	private static String getUrlContents(String theUrl) {
		StringBuilder content = new StringBuilder();
		try {
			// proxy setting-=====================================
			System.getProperties().put("http.proxyHost", "eohproxy.eoh.co.za");
			System.getProperties().put("http.proxyPort", "8080");
			System.getProperties().put("http.proxyUser", "luckyboy.rapudi");
			System.getProperties().put("http.proxyPassword", "Tucky.910105");
			System.getProperties().put("http.proxySet", "true");

			System.getProperties().put("https.proxyHost", "eohproxy.eoh.co.za");
			System.getProperties().put("https.proxyPort", "8080");
			System.getProperties().put("https.proxyUser", "luckyboy.rapudi");
			System.getProperties().put("https.proxyPassword", "Tucky.910105");
			System.getProperties().put("https.proxySet", "true");
			// ===================================================
		
			URL url = new URL(theUrl);
			URLConnection urlConnection = url.openConnection();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			String line;

			while ((line = bufferedReader.readLine()) != null) {
				content.append(line + "\n");
			}
			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content.toString();

	}

}
