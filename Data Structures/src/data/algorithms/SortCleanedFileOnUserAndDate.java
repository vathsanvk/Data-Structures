import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.google.common.collect.ComparisonChain;

public class SortCleanedFileOnUserAndDate {
	public static void main(String[] args) throws IOException {
		// The name of the file to open.
		String inFileName = "E:/IMDB Project/Matrix Factorization/imdbCleanedFile.txt";
		String outFileName = "E:/IMDB Project/Matrix Factorization/imdbFileSortedbyUser.txt";

		ArrayList<String> list = new ArrayList<String>();

		// This will reference one line at a time
		String line = null;

		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(inFileName);

			FileWriter fileWriter = new FileWriter(outFileName);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			while ((line = bufferedReader.readLine()) != null) {

				StringBuilder builder = new StringBuilder();
				String[] sSplit = line.split("::");

				builder.append(sSplit[4].replaceAll("\\s+", "")
						.replaceAll(",", "").replaceAll(";", ""));
				builder.append("::");
				builder.append(sSplit[1].trim());
				builder.append("::");
				builder.append(sSplit[6].trim());
			    builder.append("::");
			    builder.append(sSplit[7].trim());

				if (!sSplit[6].trim().equals("None") && !sSplit[7].trim().equals(".") && builder.toString().split("::").length == 4) {
				
					list.add(builder.toString());
				}

			}

			Collections.sort(list,
					new SortCleanedFileOnUserAndDate().new SampleComparator());

			for (String s : list) {
				bufferedWriter.write(s + "\n");
			}

			// Always close files.
			bufferedReader.close();
			bufferedWriter.close();

		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + inFileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + inFileName + "'");
			// Or we could just do this:
			// ex.printStackTrace();
		}

	}

	public class SampleComparator implements Comparator<String> {
		DateFormat f = new SimpleDateFormat("yyyy-MM-dd");

		@Override
		public int compare(String o1, String o2) {
			String array1[] = o1.split("::");
			String array2[] = o2.split("::");
			try {
				return ComparisonChain
						.start()
						.compare(array1[0].toLowerCase().trim(),
								array2[0].toLowerCase().trim())
						.compare(f.parse(array1[3]), f.parse(array2[3]))
						.result();

			} catch (Exception e) {

				e.printStackTrace();
			}
			return 0;
		}
	}
}
