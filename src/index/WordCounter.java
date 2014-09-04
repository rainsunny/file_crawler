package index;

import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * User: wangjie
 * Date: 14-3-13
 */
public class WordCounter
{
	private Collection<String> lines;

	private Map<String, Integer> countMap;

	private PrintWriter writer;

	public WordCounter(Collection<String> lines, PrintWriter writer)
	{
		this.lines = lines;
		this.writer = writer;
		this.countMap = new HashMap<String, Integer>();
	}

	public void count()
	{
		for (String line : lines)
		{
			if (line == null || line.trim().length() == 0)
				continue;

			String[] tokens = line.split("\\s");

			for (String token : tokens)
			{
				token = convertToken(token);
				if (token == null)
					continue;

				Integer c = countMap.get(token);
				if (c == null)
				{
					countMap.put(token, 1);
				}
				else
				{
					countMap.put(token, c + 1);
				}
			}
		}
	}

	private final Pattern wordPattern = Pattern.compile("^\\w+$");

	private String convertToken(String token)
	{
		if (token == null || token.trim().length() == 0)
			return null;

		if (wordPattern.matcher(token).matches())
		{
			return token.toLowerCase();
		}
		// todo 首尾带特殊字符的如何过滤
		return null;
	}

	public void writeResult()
	{
		try
		{
			for (Map.Entry<String, Integer> entry : countMap.entrySet())
			{
				writer.println(entry.getKey() + "\t" + entry.getValue());
			}
			writer.flush();
		}
		finally
		{
			if (writer != null)
				writer.close();
		}
	}
}
