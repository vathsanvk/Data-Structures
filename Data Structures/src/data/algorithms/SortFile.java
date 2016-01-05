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

import com.google.common.collect.ComparisonChain;

public class SortFile {
	public static void main(String[] args) throws IOException {
		// The name of the file to open.
		String inFileName = "E:/IMDB Project/Matrix Factorization/imdbFile1Cleaned.txt";
		String outFileName = "E:/IMDB Project/Matrix Factorization/imdbFile1SortedbyUser.txt";
		String train = "E:/IMDB Project/Matrix Factorization/imdbFile1Train.txt";
		String test = "E:/IMDB Project/Matrix Factorization/imdbFile1Test.txt";
		ArrayList<String> newList = new ArrayList<>();

		// This will reference one line at a time
		String line = null;
		StringBuilder sb = new StringBuilder();

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

			int count = 0;
			while ((line = bufferedReader.readLine()) != null) {
				StringBuilder builder = new StringBuilder();
				String[] sSplit = line.split("::");
				builder.append(sSplit[4].replaceAll("\\s+","").replaceAll(",",""));
				builder.append("::");
				builder.append(sSplit[1].trim());
				builder.append("::");
				builder.append(sSplit[6].trim());

				if (!sSplit[6].trim().equals("None") && !sSplit[4].equals(".")) {
					bufferedWriter.write(builder + "\n");
				    newList.add(builder.toString());
				}
			}

			//Collections
				//	.sort(list, new UserAndDateSort().new SampleComparator());
			
			int length = newList.size();
			int size = 0;
			for (String s : newList) {
				
				    
					if (size <= (0.9 * length)) {
						trainWriter.write(s + "\n");
					} else {
						testWriter.write(s + "\n");
					}
					
					size++;
				}
		
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
						.compare(array1[4].toLowerCase(),
								array2[4].toLowerCase())
						.compare(f.parse(array1[7]), f.parse(array2[7]))
						.result();

			} catch (ParseException e) {

				e.printStackTrace();
			}
			return 0;
		}
	}
}
