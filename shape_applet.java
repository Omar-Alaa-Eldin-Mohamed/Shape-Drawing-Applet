/*First of all I imported the all the Classes within the Packages that i need*/
import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.Button;
import java.awt.event.*;


/* Creating a main class named shape_applet, This Class contains inner classes */
public class shape_applet extends Applet
{	int og_X;
	int og_Y;
	int dr_X;
	int dr_Y;
	
/*Creating a Reference from the Buttons to use them Later */
	Button rect_button;
	Button oval_button;
	Button line_button;
	Button solid_button;
	Button red_button;
	Button green_button;
	Button blue_button;
	Button eraser_button;
	Button clear_button;
	Button freehand_button;
/*Creating my ArrayList to Start the storing process in it */
	ArrayList<Shapes> ShapeList = new ArrayList<>();
/*Creating a flags and emty string CurrentShape to store the state in it */
	String currentShape = "";
	boolean isSolid = false;
	boolean isEraser = false;
	Freehand currentFreehand = null;	
	Color currentColor = Color.BLACK;
	public void init()
	{
/*Now creating the buttons that i refrenced earlier While giving them names*/		
		rect_button = new Button("Draw Rect");
		oval_button = new Button("Draw Oval");
		line_button = new Button("Draw Line");		
		solid_button = new Button("Solid");	
		red_button = new Button("Red");	
		green_button = new Button("Green");	
		blue_button = new Button("Blue");	
		eraser_button = new Button("Eraser");					
		clear_button = new Button("clear");
		freehand_button = new Button("Freehand");
/*In the Next Lines i add them to my Applet in order for the user to be able to see them*/
		add(rect_button);
		add(oval_button);
		add(line_button);
		add(solid_button);
		add(red_button);
		add(green_button);
		add(blue_button);
		add(eraser_button);	
		add(clear_button);
		add(freehand_button);
/*Creating the actionListeners for my buttons to handle each event with each button*/		
	rect_button.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e) 
		{
		/*Storing in the Current shape the word Rectangle so i can use it later*/	
			currentShape = "rectangle";
		/*Handling the isEraser flag with false here to make sure that the eraser is not working */
			isEraser = false;
        }
			
    });
	
	oval_button.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e) 
		{
			/*Same thing goes with the Oval word and the eraser flag*/
			currentShape = "oval";
			isEraser = false;
        }
			
    });
	
	line_button.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e) 
		{
			currentShape = "line";
			isEraser = false;
        }
			
    });	
	solid_button.addActionListener(new ActionListener() 
	{
		public void actionPerformed(ActionEvent e)
		{
		/*now i want to handle solid button, to make the user able to draw solid shapes*/	
        isSolid = !isSolid;
		}
	});
	
	red_button.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e) 
		{
			/*here i want to color the next drawing for the user with red*/
			/*While Maintaining the color red if the user didn't change it to another color*/
			currentColor = Color.RED;
			isEraser = false;
        }
			
    });

	green_button.addActionListener(new ActionListener()
	{
		/*Same thing goes with the Green color as the red color earlier*/
		public void actionPerformed(ActionEvent e) 
		{
			currentColor = Color.GREEN;     
			isEraser = false;
		}
			
    });	


	blue_button.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e) 
		{
			currentColor = Color.BLUE;
			isEraser = false;
			
        }
			
    });	
	
	clear_button.addActionListener(new ActionListener()
	{
		/*for the clear button i wanted to*/
		/* 1- Reset the Current shape */
		/* 2- Reset the Current Color with black */
		/* 3- Reset the solid drawing to be normal */
		/* 4- Reset the eraser */
		/* And lastly repainting the applet in order for those Changes to take a place */
		public void actionPerformed(ActionEvent e) 
		{
			ShapeList.clear();
			currentShape= "";
			currentColor = Color.BLACK;
			isSolid = false;
			isEraser = false;
		
			repaint();
			
        }
			
    });	
	eraser_button.addActionListener(new ActionListener()
	{
		/*for the eraser button we*/ 
		/*1- Reset the current shape*/
		/*2- Raising the flag of the eraser*/
		/*3- Resetting the Current Freehand flag into false*/
			public void actionPerformed(ActionEvent e) {
			currentShape = "";
			isEraser = true;
			currentFreehand = null;
		}
	});

	freehand_button.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e) {
			currentShape = "freehand";
			isEraser = false;
			currentFreehand = null;
		}
	});	
	addMouseListener(new MouseAdapter ()
	{
		/*I want to store the coordinates in terms of X and Y when the user press on the appelt*/
		public void mousePressed(MouseEvent e)
		{
			og_X=e.getX();
			og_Y=e.getY();
	
	    }
		public void mouseReleased(MouseEvent e)
		{
			/*Here We make sure that we are not in the eraser mode*/
			if(!isEraser)
			{
				/*now we reset the shape in order to create a new shape based on the current shape*/
				Shapes shape = null;
				if (currentShape.equals("rectangle")) 
				{
					shape = new rectangle();
				}else if (currentShape.equals("oval")) 
				
				{
					shape = new oval();
				} else if (currentShape.equals("line"))
				{
					shape = new line();
				}
				
				if (shape != null)
				{
/*In this part we make sure that we can draw while moving the mouse cursor from right to left*/
					int startX, startY, width, height;
					if (og_X < dr_X) 
					{
						startX = og_X;
		/*now we adjust the width of after subtracting the dragged minus the orignal coordinates*/
						width = dr_X - og_X;
					} else 
					{
					
						startX = dr_X;
						width = og_X - dr_X;
					}
					if (og_Y < dr_Y) 
					{
					/*Same goes with adjusting the height and drawing from right to left*/
						startY = og_Y;
						height = dr_Y - og_Y;
					} else 
					{
						startY = dr_Y;
						height = og_Y - dr_Y;
					}
		/*Now we need to set the values that will be taken for the shapes as an input*/
					shape.set_x1(startX);
					shape.set_y1(startY);
					shape.set_x2(startX + width);
					shape.set_y2(startY + height);
					shape.color = currentColor;
					shape.solid = isSolid;
				/*after that we append it to the shapelist*/
					ShapeList.add(shape);
				/*after we finish we will repaint the applet*/	
					repaint();
			    }
			}	
		}
	});
		
	addMouseMotionListener(new MouseMotionAdapter()
	{
			
		public void mouseDragged(MouseEvent e)
		{
		/*Getting the values of the x and y  for the mouse dragged*/		
			dr_X = e.getX();
			dr_Y = e.getY();
			if(isEraser)
			{
		/*if the isEraser flag is true then we create an object and give it the dragging coordinates*/
				Shapes eraser = new Eraser(dr_X,dr_Y);
		/*Then we add it to the arraylist */
				ShapeList.add(eraser);
			}else if (currentShape.equals("freehand"))
			{
				/*we handle the state of the freehand and it's current state*/
				if (currentFreehand == null)
				{
					/*we create a new freehand and add it to the shapelist */
					currentFreehand = new Freehand();
					ShapeList.add(currentFreehand);
				}
				currentFreehand.addPoint(dr_X,dr_Y);
			}
				/*after we finish all of that we repaint the applet*/
				repaint();
			
		}
	});	
		
		
		
		
	
	}
	/*For our Paint Method we need to iterate for the shapes and draw them*/
	public void paint (Graphics g)
	{
		int startX, startY, width, height;
		for(Shapes shape : ShapeList)
		{
			shape.draw(g);
		}
		if (currentShape.equals("rectangle")) 
		{

			if (og_X < dr_X) 
			{
				startX = og_X;
				width = dr_X - og_X;
			} else 
			{
				startX = dr_X;
				width = og_X - dr_X;
			}
			if (og_Y < dr_Y) 
			{
				startY = og_Y;
				height = dr_Y - og_Y;
			} else 
			{
				startY = dr_Y;
				height = og_Y - dr_Y;
			}
			/*After we make sure that the dimensions of the rectangle are set correctly*/
			/*we pass it to the drawrect function to draw them*/
			g.drawRect(startX, startY, width, height);
        } else if (currentShape.equals("oval")) 
		{

			if (og_X < dr_X) 
			{
				startX = og_X;
				width = dr_X - og_X;
			} else
			{
				startX = dr_X;
				width = og_X - dr_X;
			}

			if (og_Y < dr_Y)
			{
				startY = og_Y;
				height = dr_Y - og_Y;
			} else 
			{
				startY = dr_Y;
				height = og_Y - dr_Y;
			}
			/*Same thing goes with the oval shape*/
			g.drawOval(startX, startY, width, height);
		
		
        } else if (currentShape.equals("line")) 
		
		{
			g.drawLine(og_X, og_Y, dr_X, dr_Y);
        }
	}
		
	
	/*inner class*/
	
	
	/*The Shapes Class is the parent class */
	/*This class got 5 Childs*/
	/*1- Rectangle */
	/*2- oval */
	/*3- line */
	/*4- Eraser */
	/*5- Freehand */
	abstract class Shapes {
	
	private int x1;
	private int x2;
	private int y1;
	private int y2;
		
	Color color;
	boolean solid;

	/*the setters*/
	public void set_x1 (int q){

		x1 = q;
	}
	public void set_x2 (int w){

		x2 = w;
	}
	public void set_y1 (int e){

		y1 = e;
	}
	public void set_y2 (int r){

		y2 = r;
	}
	/*The Getters*/
	public  int get_x1(){
		return x1;
	}
	public  int get_x2(){
		return x2;
	}
	public  int get_y1(){
		return y1;
	}
	public  int get_y2(){
		return y2;
	}

	/*Making the draw function abstract to force every child to override it on his way*/
	public abstract void draw(Graphics g);
		
	}
	/*First Child */
	class rectangle extends Shapes
	{	
/*Rectangle Class overrides the draw function with the fillrect or drawrect based on the solid state*/
		public void draw(Graphics g)
			{
				g.setColor(color);
				if(isSolid)
				{
					g.fillRect(get_x1(),get_y1(),get_x2() - get_x1(),get_y2() - get_y1()

);
				}
				else
				{
					g.drawRect(get_x1(),get_y1(),get_x2() - get_x1(),get_y2() - get_y1());
				}
			}	
			
	}
	/*Second Child*/
	class oval extends Shapes
	{
	/*Same thing happens with the oval*/		
		public void draw(Graphics g)
		{
			g.setColor(color);
			if(isSolid)
			{
				g.fillOval(get_x1(),get_y1(),get_x2()-get_x1(),get_y2()-get_y1());
			}
			else
			{
				g.drawOval(get_x1(),get_y1(),get_x2()-get_x1(),get_y2()-get_y1());
			}
		}	
		
	}
/*Third Child*/
	class line extends Shapes
	{
			
		public void draw(Graphics g)
		{
			g.setColor(color);	
			g.drawLine(get_x1(),get_y1(),get_x2(),get_y2());

		}	
		
	}
/*Fourth Child*/
	class Eraser extends Shapes
	{
		private int xdim;
		private int ydim;
		/*instead of using setters we can give the data to the constructor */
		public Eraser(int x, int y) {
			xdim = x;
			ydim = y;
		}

		
		public void draw(Graphics g) 
		{
			/* Draw a small white rectangle to simulate erasing*/
			g.setColor(Color.WHITE);
			g.fillRect(xdim - 5, ydim - 5, 10, 10); 
		}
	}
	/*Last Child */
	class Freehand extends Shapes
	{
		/*got his own arraylist that stores points */
		private ArrayList<int[]> points;

		public Freehand() 
		{
			points =new ArrayList<>();
		}
/*we add points To the array list while maintaining the datatype of the entered data to be integer */
		public void addPoint(int x, int y) {
			points.add(new int[]{x, y});
		}
		public void draw(Graphics g) 
		{
			g.setColor(color);
			/*here we iterate with the array size */
			/*drawing the the free hand as a very small lines */
			/*Knowing that the free hand is a small lines */
			/*the end point of the first line is gonna be the start point of the next line */
			for(int i =1 ;i< points.size();i++)
			{
			int[] p1 = points.get(i-1);
			int[] p2 = points.get(i);
			g.drawLine(p1[0],p1[1],p2[0],p2[1]);
			}
		}
	}
		
}
