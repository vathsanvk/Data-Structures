import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import com.google.common.collect.ComparisonChain;

public class UserAndDateSort {
	public static void main(String[] args) throws IOException {
		// The name of the file to open.
		String inFileName = "E:/IMDB Project/Matrix Factorization/imdbFile5Cleaned.txt";
		String outFileName = "E:/IMDB Project/Matrix Factorization/imdbFile5SortedbyUser.txt";
		String train = "E:/IMDB Project/Matrix Factorization/imdbFile5Train.txt";
		String test = "E:/IMDB Project/Matrix Factorization/imdbFile5Test.txt";
		ArrayList<String> list = new ArrayList<String>();

		// This will reference one line at a time
		String line = null;

		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(inFileName);

			FileWriter fileWriter = new FileWriter(outFileName);

			FileWriter fileWriter1 = new FileWriter(train);
			FileWriter fileWriter2 = new FileWriter(test);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			BufferedWriter trainWriter = new BufferedWriter(fileWriter1);
			BufferedWriter testWriter = new BufferedWriter(fileWriter2);

			while ((line = bufferedReader.readLine()) != null) {

				list.add(line);
			}

			Collections
					.sort(list, new UserAndDateSort().new SampleComparator());

			ArrayList<String> newList = new ArrayList<>();

			for (String s : list) {
				StringBuilder builder = new StringBuilder();
				String[] sSplit = s.split("::");

				builder.append(sSplit[4].replaceAll("\\s+", "").replaceAll(",",
						"").replaceAll(";", ""));
				builder.append("::");
				builder.append(sSplit[1].trim());
				builder.append("::");
				builder.append(sSplit[6].trim());

				if (!sSplit[6].trim().equals("None")) {
					bufferedWriter.write(builder + "\n");
					newList.add(builder.toString());
				}
			}

			// Collections.shuffle(newList);

			int length = newList.size();
			int size = 0;
			String temp = "";
			int userCount = 0;
			for (String s : newList) {

				/*
				 * if (size <= (0.9 * length)) { trainWriter.write(s + "\n"); }
				 * else { testWriter.write(s + "\n"); }
				 */

				String[] sp = s.split("::");

				if (sp[0].equals(temp.split("::")[0])) {

					userCount++;
				}

				if (!sp[0].equals(temp.split("::")[0]) && userCount >= 1) {

					if (size <= (0.1 * length)) {
						if (!temp.equals("")) {
							testWriter.write(temp + "\n");
							size++;
						}
					} else {
						trainWriter.write(temp + "\n");
					}
					userCount = 0;

				} else {
					if (!temp.equals("")) {
						trainWriter.write(temp + "\n");
					}

				}

				temp = s;

				// size++;
			}

			trainWriter.write(newList.get(newList.size() - 1));

			// Always close files.
			bufferedReader.close();
			bufferedWriter.close();
			trainWriter.close();
			testWriter.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + inFileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + inFileName + "'");
			// Or we could just do this:
			// ex.printStackTrace();
		}

	}

	public class SampleComparator implements Comparator<String> {
		DateFormat f = new SimpleDateFormat(" yyyy-MM-dd ");

		@Override
		public int compare(String o1, String o2) {
			String array1[] = o1.split("::");
			String array2[] = o2.split("::");
			try {
				return ComparisonChain
						.start()
						.compare(array1[4].toLowerCase().trim(),
								array2[4].toLowerCase().trim())
						.compare(f.parse(array1[7]), f.parse(array2[7]))
						.result();

			} catch (Exception e) {

				e.printStackTrace();
			}
			return 0;
		}
	}
}
