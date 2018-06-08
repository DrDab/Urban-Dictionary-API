package examples;

import org.json.JSONArray;

import urbanapi.Definition;
import urbanapi.UDParser;

public class APITestDriver 
{
	public static void main (String[] args)
	{
		UDParser udparser = new UDParser("http://api.urbandictionary.com/v0/define?term=");
		String JSONData = udparser.getJSONData("minecraft");
		Definition[] test = udparser.getDefinitionsByKeyword(JSONData);
		for(int i = 0; i < test.length; i++)
		{
			System.out.println(test[i].getWordName());
			System.out.println(test[i].getDefinition());
			System.out.println(test[i].getWrittenDate());
			System.out.println(test[i].getPermalink());
		}
		JSONArray keywordTags = udparser.getTagsByKeyword(JSONData);
		for(int i = 0; i < keywordTags.length(); i++)
		{
			System.out.println(keywordTags.get(i));
		}
	}
}
