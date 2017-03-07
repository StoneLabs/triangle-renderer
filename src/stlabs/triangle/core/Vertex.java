package stlabs.triangle.core;

import stlabs.triangle.math.Vector3f;

public class Vertex
{
	public static final int SIZE = 3; // 11 floats = 44 bytes;
	
	private Vector3f m_pos;

	public Vertex(Vector3f pos)
	{
		m_pos = pos;
	}	
	
	public Vector3f getPos() {
		return m_pos;
	}

	public void setPos(Vector3f pos) {
		this.m_pos = pos;
	}
}
