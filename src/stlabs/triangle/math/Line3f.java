package stlabs.triangle.math;

public class Line3f 
{
	private Vector3f m_start, m_end;
	
	public Vector3f getStart() {return m_start;}
	public Vector3f getEnd() {return m_start;}
	public Vector3f getDirection() { return (m_end.sub(m_start)).normalize(); }
	
	public Line3f(Vector3f start, Vector3f end)
	{
		m_start = start; m_end = end;
	}
}
