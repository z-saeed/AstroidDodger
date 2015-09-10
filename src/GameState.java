import java.util.*;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.*;
import org.newdawn.slick.geom.*;

public class GameState extends BasicGameState{

	private ArrayList<Circle> balls;
	private Circle greenBall;
	private Circle mouseBall;
	private int timePassed;
	private Random random;
	private Image player = null;
	private Image background = null;
	private Image asteroid = null;
	private Image satellite = null;
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		random = new Random();
		balls = new ArrayList<Circle>();
		greenBall = new Circle(random.nextInt(800),0,5);
		mouseBall = new Circle(0,0,10);
		player = new Image("images/player.png");
		background = new Image("images/bg.jpg");
		asteroid = new Image("images/asteroid-icon.png");
		satellite = new Image("images/satellite.png");
		timePassed = 0;
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta)
			throws SlickException {
		
		if(container.getInput().isKeyPressed(Input.KEY_2)){
			sbg.enterState(1, new FadeOutTransition(),new FadeInTransition());
		}
		
		
		
		mouseBall.setCenterX(container.getInput().getMouseX());
		mouseBall.setCenterY(container.getInput().getMouseY());
		
		timePassed += delta;
		if(timePassed > 50){
			timePassed = 0;
			balls.add(new Circle(100 + random.nextInt(600),0,5));
		}
		
		greenBall.setCenterY(greenBall.getCenterY() + (delta/4f));
		
		if(greenBall.getCenterY() > 605){
			greenBall.setLocation(random.nextInt(800), 0);
		}
		
		for(Circle c : balls){
			c.setCenterY(c.getCenterY() + (delta/4f));
		}
		
		for(int i=balls.size()-1; i >= 0; i--){
			Circle c = balls.get(i);
			if(c.getCenterY() > 605){
				balls.remove(i);
			}else if(c.intersects(mouseBall)){
				balls.remove(i);
				SetupClass.lives -= 1;
			}else if(c.intersects(greenBall)){
				greenBall.setCenterY(-20);
			}
		}
		
		if(greenBall.intersects(mouseBall)){
			greenBall.setLocation(random.nextInt(800), -20);
			SetupClass.score += 1;
		}
		
		if(SetupClass.lives == 0){
			sbg.enterState(1, new FadeOutTransition(), new FadeInTransition());
		}
	}

	@Override
	public void render(GameContainer container, StateBasedGame arg1, Graphics g)
			throws SlickException {
		
		background.draw(0,0);
		
		g.setColor(Color.white);
		g.fill(mouseBall);
		
		player.drawCentered(container.getInput().getMouseX(), container.getInput().getMouseY());
		
		g.setColor(Color.red);
		for(Circle c : balls){
			g.fill(c);
			asteroid.drawCentered(c.getCenterX(),c.getCenterY());
		}
		
		g.setColor(Color.green);
		g.fill(greenBall);
		
		satellite.drawCentered(greenBall.getCenterX(), greenBall.getCenterY());
		
		g.setColor(Color.white);
		g.drawString("Current balls: " + balls.size(), 20, 50);
		g.drawString("Score: " + SetupClass.score,20,70);
		g.drawString("Lives: " + SetupClass.lives, 20, 90);
	}

	@Override
	public int getID() {
		return 0;
	}
	
}