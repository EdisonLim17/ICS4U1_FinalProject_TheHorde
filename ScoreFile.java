/**
 * Singleton class for reading and writing to a score file.
 * 
 * @author (Vaughn Chan) 
 * @version (2.0: 01/22/2022)
 */
import java.util.ArrayList;
import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ScoreFile
{
    // Instance object (the only object of this class allowed to exist)
    private static ScoreFile instance = new ScoreFile();
    
    // The directory path to the score file. "./" means the "current directory
    private static String scorePath = "./data/score.txt";
    
    // An array that holds the current scores. New scores added are added to the variable,
    // and then is used to update the file. This is similar to a "save" functionality.
    private ArrayList<Integer> scoreData = new ArrayList<Integer>();
    
    /**
     * Instance referencer (as a singleton, this replaces the constructor of this class)
     *
     * @return the scorefile
     */
    public static ScoreFile getInstance()
    {
        // When initialized, make sure score file actually exists
        createScoreFile();
        return instance;
    }
    
    /**
     * Method to get all scores from the file (used to display the scores)
     *
     * @return all scores from the file
     */
    public ArrayList<Integer> getScoreData()
    {
        parseFileToData();
        return scoreData;
    }
    
    /**
     * Method to add a new score to the file
     *
     * @param newScore a new score
     */
    public void addScoreData(int newScore)
    {
        scoreData.add(newScore);
        sortScore();
        parseDataToFile();
    }
    
    /**
     * Helper method for checking if score file exists
     */
    private static void createScoreFile()
    {
        try
        {
            File scoreFile = new File(scorePath);
            if (!scoreFile.exists()){
                scoreFile.createNewFile();
            }
        }
        catch (IOException e)
        {
            System.out.println("Something went wrong...");
            e.printStackTrace(System.out);
        }
    }
    
    /**
     * Method to sort the score in reverse order (largest to smallest)
     *
     */
    private void sortScore()
    {
        // Initialize sorted data
        int[] sortedData = new int[scoreData.size()];
        for (int i = 0; i < scoreData.size(); i++)
        {
            sortedData[i] = scoreData.get(i);
        }
        
        // Sort the data and wipe out old data
        sortedData = Sort.reverseMergeSort(sortedData);
        scoreData.clear();
        
        // Replace current data with new data
        for (int score: sortedData)
        {
            scoreData.add(score);
        }
    }
    
    /**
     * Method to convert data from file into the arraylist
     */
    private void parseFileToData()
    {
        scoreData.clear();
        try
        {
             BufferedReader br = new BufferedReader(new FileReader(scorePath));
             String line;
             
             // logic for parsing file is here
             while ((line = br.readLine()) != null) {
                 scoreData.add(Integer.parseInt(line));
             }
             
             br.close();
        }
        catch (IOException e)
        {
            e.printStackTrace(System.out);
        }
    }
    
    /**
     * Method to convert data from the arraylist into the file
     *
     */
    private void parseDataToFile()
    {
        try
        {
            BufferedWriter bw = new BufferedWriter(new FileWriter(scorePath, false));
            
            for (int score : scoreData)
            {
                bw.write(score + "\n");
            }
            bw.close();
        }
        catch (IOException e) { };
    }
}