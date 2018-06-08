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
			System.out.println("WORD");
			System.out.println(test[i].getWordName());
			System.out.println("DEFINITION");
			System.out.println(test[i].getDefinition());
			System.out.println("AUTHOR");
			System.out.println(test[i].getAuthor());
			System.out.println("WRITTEN DATE");
			System.out.println(test[i].getWrittenDate());
			System.out.println("PERMALINK");
			System.out.println(test[i].getPermalink());
		}
		JSONArray keywordTags = udparser.getTagsByKeyword(JSONData);
		System.out.println("TAGS");
		for(int i = 0; i < keywordTags.length(); i++)
		{
			System.out.println(keywordTags.get(i));
		}
	}
}
