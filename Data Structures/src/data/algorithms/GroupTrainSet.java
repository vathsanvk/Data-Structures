import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class GroupTrainSet {
	public static void main(String[] args) throws IOException {
		// The name of the file to open.
		String inFileName = "E:/IMDB Project/Matrix Factorization/imdbFileTrain.txt";
		String usersRevAtleast2 = "E:/IMDB Project/Matrix Factorization/imdbTrainMin500.txt";
		
		
		// This will reference one line at a time
		String line = null;

		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(inFileName);

			FileWriter fileWriter = new FileWriter(usersRevAtleast2);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			HashMap<String, Integer> map = new HashMap<>();
			ArrayList<String> data = new ArrayList<>();
        
			while ((line = bufferedReader.readLine()) != null) {
				
				data.add(line);
				
				String[] sp = line.split("::");

				if(map.containsKey(sp[0])){
					
					map.put(sp[0], map.get(sp[0]) + 1);
				}else{
					map.put(sp[0], 1);
				}
				
			}
		
			
			for(String s : data){
				
				if(map.containsKey(s.split("::")[0]) && map.get(s.split("::")[0]) >= 500 ){
					bufferedWriter.write(s + "\n");
					
				}
			}

			

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
