package stlabs.triangle.core;

import StoneLabs.sutil.Debug;
import stlabs.triangle.math.Line3f;
import stlabs.triangle.math.Vector3f;

public class Triangle extends Renderable
{
	public Triangle(Line3f sideA, Line3f sideB, Line3f sideC)
	{
		if (sideA.getEnd().equals(sideB.getStart())
		 && sideB.getEnd().equals(sideC.getStart())
		 && sideC.getEnd().equals(sideA.getStart()))
			Debug.Error("Invalid Arguments: The lines do not form an triangle!");
		
		setVertices(new Vertex[] {  new Vertex(sideA.getStart()), 
									new Vertex(sideB.getStart()), 
									new Vertex(sideC.getStart())},
					new int[] {0, 1, 2});
	}

	public Triangle(Vector3f vertexA, Vector3f vertexB, Vector3f vertexC)
	{
		setVertices(new Vertex[] {  
				new Vertex(vertexA), 
				new Vertex(vertexB), 
				new Vertex(vertexC)},
				new int[] {0, 1, 2});
	}
}
