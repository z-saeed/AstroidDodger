import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;


public class GameOverState extends BasicGameState{

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		
	}
	
	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException {
		
	}


	@Override
	public void render(GameContainer container, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.drawString("Game Over", 300, 300);
		g.drawString("Points: " + SetupClass.score, 300, 350);
		g.drawString("HighScore: " + SetupClass.highScore, 300, 400);
		
		g.drawString("Press Space to restart", 300, 500);
		
		if(container.getInput().isKeyPressed(Input.KEY_SPACE)){
			sbg.enterState(0, new FadeOutTransition(), new FadeInTransition());
			SetupClass.lives = 5;
			SetupClass.score = 0;
		}
		
		if(SetupClass.score > SetupClass.highScore){
			
			g.drawString("NEW HIGHSCORE", 300, 450);
		
			String fileName = "score.txt";
			
			try{
				// Assume default encoding.
	            FileWriter fileWriter =
	                new FileWriter(fileName);
	
	            // Always wrap FileWriter in BufferedWriter.
	            BufferedWriter bufferedWriter =
	                new BufferedWriter(fileWriter);
	
	            // Note that write() does not automatically
	            // append a newline character.
	            bufferedWriter.write(Integer.toString(SetupClass.score));
	         
	            // Always close files.
	            bufferedWriter.close();
			}catch(IOException ex){
				System.out.println("Error writing to file '" + fileName + "'");
		            // Or we could just do this:
		            // ex.printStackTrace();
			}
			
		}
		
	}

	@Override
	public int getID() {
		return 1;
	}
	
	
	
}