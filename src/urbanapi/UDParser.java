package urbanapi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class UDParser 
{
	private String apiUri;
	
	/**
	 * Initialize a new instance of a UDParser object using the URL for the UrbanDictionary API page.
	 * Examples for the API URL are "http://api.urbandictionary.com/v0/define?term=".
	 * 
	 * @param apiUri
	 */
	public UDParser(String apiUri)
	{
		this.apiUri = apiUri;
	}
	
	public Definition[] getDefinitionsByKeyword(String JSONData)
	{
		ArrayList<Definition> tmpArr = new ArrayList<Definition>();
		
		JSONObject obj = new JSONObject(JSONData);
		
		JSONArray wordList = obj.getJSONArray("list");
		
		for(int i = 0; i < wordList.length(); i++)
		{
			  JSONObject jsonobject = wordList.getJSONObject(i);
			  String wordName = jsonobject.getString("word");
			  String definition = jsonobject.getString("definition");
			  String example = jsonobject.getString("example");
			  String author = jsonobject.getString("author");
			  String writtenDate = jsonobject.getString("written_on");
			  int likes = (jsonobject.getInt("thumbs_up"));
			  int dislikes = (jsonobject.getInt("thumbs_down"));
			  int refID = (jsonobject.getInt("defid"));
			  tmpArr.add(new Definition(wordName, definition, example, author, writtenDate, refID, likes, dislikes));
		}
		
		// {"tags":[],"result_type":"no_results","list":[],"sounds":[]}
		//{"definition":"The superior form of \"retarded\". It's the most destructive word you can ever use against another living soul. Saying this word to someone will cause them to either die of a heart attack or become so sad that they commit suicide.","permalink":"http://rarted.urbanup.com/12582916","thumbs_up":226,"author":"Bladethegamer","word":"Rarted","defid":12582916,"current_vote":"","written_on":"2018-02-21T00:00:00.000Z","example":"Random Person on the Internet: \"Oh boy a new comment on my Youtube video!\"\n\nEdgelord9000: \"You are rarted!\"\n\nRandom Person on the Internet: *dies of heart attack*","thumbs_down":16}
		
		return tmpArr.toArray(new Definition[tmpArr.size()]);
	}
	
	/**
	 * Return the tags that are relevant to a keyword given the scraped JSON Data for that word.
	 * 
	 * @param JSONData
	 * @return the result type for a keyword.
	 */
	public JSONArray getTagsByKeyword(String JSONData)
	{
		JSONObject obj = new JSONObject(JSONData);
		JSONArray tags = obj.getJSONArray("tags");
		return tags;
	}
	
	/**
	 * Return the result status of a keyword given the scraped JSON Data for that word.
	 * If false, no current results exist for that keyword.
	 * If true, results exist for that keyword.
	 * 
	 * @param JSONData
	 * @return the result type for a keyword.
	 */
	public boolean getResultTypeByKeyword(String JSONData)
	{
		JSONObject obj = new JSONObject(JSONData);
		String result_type = obj.getString("result_type");
		return result_type.matches("exact");
	}
	
	public String getJSONData(String word)
	{
		 try 
		 {
			 // if the word contains spaces, replace them with "+" in the GET.
			 String url_base = apiUri + word;
			 URL jsonURL = new URL(url_base);
			 URLConnection yc = jsonURL.openConnection();
			 BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(), "UTF-8"));
			 String inputLine;
			 String returnData = "";
			 while ((inputLine = in.readLine()) != null)
			 {
				 returnData += inputLine;
			 }
			 return returnData;
		 } 
		 catch (Exception e)
		 {
			 return null;
		 }
	}
	
}
