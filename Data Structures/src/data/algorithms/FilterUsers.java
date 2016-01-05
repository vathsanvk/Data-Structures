import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class FilterUsers {
	public static void main(String[] args) throws IOException {
		// The name of the file to open.
		String inFileName = "E:/IMDB Project/Matrix Factorization/imdbFileSortedbyUser.txt";
		String usersRevAtleast2 = "E:/IMDB Project/Matrix Factorization/imdbUsersRevAtleast2.txt";



		// This will reference one line at a time
		String line = null;

		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(inFileName);

			FileWriter fileWriter = new FileWriter(usersRevAtleast2);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			String temp = "";
			int count = 1;
			while ((line = bufferedReader.readLine()) != null) {
				String[] sp = line.split("::");

				if (sp[0].equals(temp.split("::")[0])) {
					count++;
				} else {
					if (count > 1) {
						bufferedWriter.write(temp + "\n");
					}
					count = 1;
				}

				
				if (count > 1) {
					bufferedWriter.write(temp + "\n");
				}
				
				temp = line;
				

			}

		    bufferedWriter.write(temp);

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

}
