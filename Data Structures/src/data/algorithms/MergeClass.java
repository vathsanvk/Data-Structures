import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MergeClass {
	static String outTrainFile = "E:/IMDB Project/Matrix Factorization/imdbFileTrain.txt";
	static String outTestFile = "E:/IMDB Project/Matrix Factorization/imdbFileTest.txt";
	static String[] train = {
			"E:/IMDB Project/Matrix Factorization/imdbFile1Train.txt",
			"E:/IMDB Project/Matrix Factorization/imdbFile2Train.txt",
			"E:/IMDB Project/Matrix Factorization/imdbFile3Train.txt",
			"E:/IMDB Project/Matrix Factorization/imdbFile4Train.txt",
			"E:/IMDB Project/Matrix Factorization/imdbFile5Train.txt",
			"E:/IMDB Project/Matrix Factorization/imdbFile6Train.txt" };
	
	static String[] test = {
		"E:/IMDB Project/Matrix Factorization/imdbFile1Test.txt",
		"E:/IMDB Project/Matrix Factorization/imdbFile2Test.txt",
		"E:/IMDB Project/Matrix Factorization/imdbFile3Test.txt",
		"E:/IMDB Project/Matrix Factorization/imdbFile4Test.txt",
		"E:/IMDB Project/Matrix Factorization/imdbFile5Test.txt",
		"E:/IMDB Project/Matrix Factorization/imdbFile6Test.txt" };

	public static void main(String[] args) throws IOException {
		// The name of the file to open.

	

		writeTrainFile();
		writeTestFile();

	}

	static void writeTrainFile() {
		try {
			// FileReader reads text files in the default encoding.

			FileWriter fileWriter = new FileWriter(outTrainFile,true);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			for (String trainFile : train) {
				FileReader fileReader = new FileReader(trainFile);

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
	
	static void writeTestFile() {
		try {
			// FileReader reads text files in the default encoding.

			FileWriter fileWriter = new FileWriter(outTestFile,true);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			for (String trainFile : test) {
				FileReader fileReader = new FileReader(trainFile);

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
