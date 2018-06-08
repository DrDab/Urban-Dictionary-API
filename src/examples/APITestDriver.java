package examples;

import urbanapi.Definition;
import urbanapi.UDParser;

public class APITestDriver 
{
	public static void main (String[] args)
	{
		UDParser udparser = new UDParser("http://api.urbandictionary.com/v0/define?term=");
		Definition[] test = udparser.getDefinitionsByKeyword(udparser.getJSONData("sadfjoasfjoj"));
		for(int i = 0; i < test.length; i++)
		{
			System.out.println(test[i]);
			System.out.println(test[i].getPermalink());
		}
	}
}
