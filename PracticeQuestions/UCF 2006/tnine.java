
import java.util.*;
import java.io.*;

public class tnine {

	String digits;
	ArrayList<String> words;

	final static String[] nums = { "2", "2", "2", "3", "3", "3", "4", "4", "4", "5", "5", "5", "6", "6", "6", "7", "7",
			"7", "7", "8", "8", "8", "9", "9", "9", "9" };

	public tnine(String d, String w) {
		digits = d;
		words = new ArrayList<String>();
		words.add(w);
	}

	public void add(String w) {
		words.add(w);
	}

	public static void main(String[] args) throws IOException {

		ArrayList<tnine> dictionary = new ArrayList<tnine>();

		Scanner fin = new Scanner(
				new File("C:\\Users\\mathl\\Desktop\\Coding Practices\\PracticeQuestions\\UCF 2006\\tnine.in"));

		int size = fin.nextInt();

		for (int i = 0; i < size; i++) {
			String word = fin.next();
			String textmap = getText(word);

			int index = getIndex(dictionary, textmap);

			if (index < 0)
				dictionary.add(new tnine(textmap, word));

			else
				dictionary.get(index).add(word);
		}
		int numcases = fin.nextInt();
		String garbage = fin.nextLine();

		for (int i = 1; i <= numcases; i++) {
			StringTokenizer tok = new StringTokenizer(fin.nextLine());

			String answer = "";

			int total = 1;

			while (tok.hasMoreElements()) {
				String number = tok.nextToken();

				int location = getIndex(dictionary, number);
				int numchoices = 1;

				if (location < 0) {
					total = 0;
					break;
				} else
					numchoices = dictionary.get(location).words.size();

				if (numchoices > 1) {
					total *= numchoices;

					System.out.println(numchoices + " ");
					for (int j = 0; j < dictionary.get(location).words.size(); j++)
						System.out.println(dictionary.get(location).words.get(j) + " ");
					System.out.println();
				} else {

					String realword = dictionary.get(location).words.get(0);
					if (answer.equals(""))
						answer += realword;
					else
						answer = answer + " " + realword;
				}
			}

			System.out.println("Message #" + i + ": ");
			if (total == 0)
				System.out.println("Not a valid text");
			else if (total == 1)
				System.out.println(answer);
			else
				System.out.println("There are " + total + " possible messages");
		}

		fin.close();

	}

	public static String getText(String w) {
		String ans = "";
		for (int i = 0; i < w.length(); i++)
			ans += nums[w.charAt(i) - 'a'];
		return ans;
	}

	public static int getIndex(ArrayList<tnine> dictionary, String textmap) {
		for (int i = 0; i < dictionary.size(); i++)
			if (dictionary.get(i).digits.equals(textmap))
				return i;
		return -1;
	}

}
