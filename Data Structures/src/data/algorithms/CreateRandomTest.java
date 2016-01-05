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

public class CreateRandomTest {
	public static void main(String[] args) throws IOException {
		// The name of the file to open.
		 String inFileName =
		 "E:/IMDB Project/Matrix Factorization/imdbUsers2.txt";
		

		String train = "E:/IMDB Project/Matrix Factorization/imdbFileTrain.txt";
		String test = "E:/IMDB Project/Matrix Factorization/imdbFileTest.txt";

		// This will reference one line at a time
		String line = null;

		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(inFileName);

			FileWriter fileWriter1 = new FileWriter(train);
			FileWriter fileWriter2 = new FileWriter(test);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			BufferedWriter trainWriter = new BufferedWriter(fileWriter1);
			BufferedWriter testWriter = new BufferedWriter(fileWriter2);

			String temp = "";

			ArrayList<String> tempList = new ArrayList<>();
			ArrayList<String> tempTestList = new ArrayList<String>();
			ArrayList<String> tempTrainList = new ArrayList<String>();
			while ((line = bufferedReader.readLine()) != null) {

				String[] sp = line.split("::");

				if (sp[0].equals(temp.split("::")[0])) {
					tempList.add(temp);

				} else {

					if (!temp.isEmpty()) {
						tempList.add(temp);
						for (int i = 0; i < tempList.size(); i++) {
							if (i >= (tempList.size() / 2)) {
								tempTestList.add(tempList.get(i));
							
							} else {
								tempTrainList.add(tempList.get(i));
							}
						}
					}
					tempList = null;
					tempList = new ArrayList<>();

				}

				temp = line;

			}

			for (int i = 0; i < tempList.size(); i++) {
				if (i >= (tempList.size() / 2)) {
					tempTestList.add(tempList.get(i));
					
				} else {
					tempTrainList.add(tempList.get(i));
				}
			}

			for (String s : tempTrainList) {

				trainWriter.write(s + "\n");
			}

			int totalSize = tempTestList.size() + tempTrainList.size();

			Collections.shuffle(tempTestList);

			int reqSize = 0;
			int pctSize = (int) (0.1 * totalSize);
			for (String s : tempTestList) {

				if (reqSize <= pctSize) {
					reqSize++;
					testWriter.write(s + "\n");

				} else {
					trainWriter.write(s + "\n");

				}

			}

			trainWriter.write(temp);

			// Always close files.
			bufferedReader.close();

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

}
