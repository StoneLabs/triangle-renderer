package stlabs.triangle.core;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

public class Renderable
{	
	protected int m_vbo_vertex_handle;
	protected int m_ibo_index_handle;	
	protected int m_ibo_size;
	
	public void setVertices(Vertex[] vertices, int[] indices)
	{
		m_vbo_vertex_handle = glGenBuffers();
		m_ibo_index_handle = glGenBuffers();
		
		glBindBuffer(GL_ARRAY_BUFFER, m_vbo_vertex_handle);
		glBufferData(GL_ARRAY_BUFFER, Util.createFlippedBuffer(vertices), GL_STATIC_DRAW);
		
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, m_ibo_index_handle);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, Util.createFlippedBuffer(indices), GL_STATIC_DRAW);
		
		m_ibo_size = indices.length;
	}
	public void render()
	{
		glEnableVertexAttribArray(0);
		
		glBindBuffer(GL_ARRAY_BUFFER, m_vbo_vertex_handle);
		glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);  //pos 		(OFFSET: 0 ; SIZE = 3*float = 12b)
		
		glDrawArrays(GL_TRIANGLES, 0, 3);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, m_ibo_index_handle);
		glDrawElements(GL_TRIANGLES, m_ibo_size, GL_UNSIGNED_INT, 0);
		
		glDisableVertexAttribArray(0);
	}
}