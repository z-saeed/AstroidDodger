import java.io.*;

import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

public class SetupClass extends StateBasedGame{
	
	public static int lives = 5;
	public static int score = 0;
	public static int highScore = 0;
	
	public SetupClass(String title) {
		super(title);
	}
	
	public static void main(String[] args) throws SlickException{	
		 // This will reference one line at a time
        String line = null;

        String fileName = "score.txt";
		try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName );

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                highScore = Integer.valueOf(line);
            }    

            // Always close files.
            bufferedReader.close();            
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                   
            // Or we could just do this: 
            // ex.printStackTrace();
        }
		
		AppGameContainer app = new AppGameContainer(new SetupClass("Setup Test"));		
		app.setDisplayMode(800, 600, false);
		app.start();
		app.setTargetFrameRate(60);
	}
	
	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		this.addState(new GameState());
		this.addState(new GameOverState());
	}
	
	

}