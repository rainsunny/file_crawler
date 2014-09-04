package index;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * User: wangjie
 * Date: 14-3-13
 */
public class CounterUser
{

	private String inFileName;
	private String outFileName;

	public CounterUser(String inFileName, String outFileName)
	{
		this.inFileName = inFileName;
		this.outFileName = outFileName;
	}

	public static void main(String[] args) throws IOException
	{
		File conf = new File("D:\\workspace\\file_crawler\\src\\index\\conf.txt");
		Scanner scanner = new Scanner(conf);
		String inFileName = scanner.nextLine();
		String outFileName = scanner.nextLine();

		CounterUser counterUser = new CounterUser(inFileName, outFileName);
		counterUser.execute();
	}

	private void execute() throws IOException
	{
		List<String> list = new LinkedList<String>();

		BufferedReader bufferedReader = new BufferedReader(new FileReader(inFileName));

		String line;
		while ((line = bufferedReader.readLine()) != null)
		{
			list.add(line);
		}

		PrintWriter writer = new PrintWriter(outFileName);
		WordCounter wordCounter = new WordCounter(list, writer);
		wordCounter.count();
		wordCounter.writeResult();
	}
}
