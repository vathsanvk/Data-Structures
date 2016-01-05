import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MergeInputFiles {
	
	static String outCleanedFile = "E:/IMDB Project/Matrix Factorization/imdbCleanedFile.txt";
	static String[] cleaned = {
			"E:/IMDB Project/Matrix Factorization/imdbFile1Cleaned.txt",
			"E:/IMDB Project/Matrix Factorization/imdbFile2Cleaned.txt",
			"E:/IMDB Project/Matrix Factorization/imdbFile3Cleaned.txt",
			"E:/IMDB Project/Matrix Factorization/imdbFile4Cleaned.txt",
			"E:/IMDB Project/Matrix Factorization/imdbFile5Cleaned.txt",
			"E:/IMDB Project/Matrix Factorization/imdbFile6Cleaned.txt" };

	

	public static void main(String[] args) throws IOException {
		// The name of the file to open.

		writeCleanedFile();
	
	}

	static void writeCleanedFile() {
		try {
			// FileReader reads text files in the default encoding.

			FileWriter fileWriter = new FileWriter(outCleanedFile, true);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			for (String cleanedFile : cleaned) {
				FileReader fileReader = new FileReader(cleanedFile);

				// Always wrap FileReader in BufferedReader.
				BufferedReader bufferedReader = new BufferedReader(fileReader);

				String line = null;
				while ((line = bufferedReader.readLine()) != null) {

					bufferedWriter.write(line + "\n");

				}

				// Always close files.
				bufferedReader.close();
			}
			bufferedWriter.close();

		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();

		}
	}

	
}
