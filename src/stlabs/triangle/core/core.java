package stlabs.triangle.core;

import StoneLabs.sutil.Debug;
import stlabs.triangle.math.Line3f;
import stlabs.triangle.math.Vector3f;

import static org.lwjgl.opengl.GL11.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class core
{
	protected static Window m_window;
	
	@SuppressWarnings("static-access")
	public static void main(String[] args)
	{
		Debug.Log("Starting...");
		m_window = new Window();
		m_window.createWindow(640, 480, "Triangle plotter");
		Debug.Log("OpenGL version: " + getOpenGLVersion());

		glClearColor(1.0f, 0.0f, 1.0f, 0.0f);
		
		Debug.Log("Calling Initialization");
		initialize();

		Debug.Log("Starting loop...");
		while (!m_window.isCloseRequested()) 
		{
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			render();
			m_window.render();
		}
		Debug.Log("Terminating program...");
		Debug.Log("Goodbye!");
	}

	public static String getOpenGLVersion()	{ return glGetString(GL_VERSION); }
	
	
	////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////// GAME DEFINITION /////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////


	@SuppressWarnings("unused")
	private static Triangle m_triangle2;
	private static Triangle m_triangle1;
	
	private static Shader m_shader;
	
	static int m_vbo_vertex_handle;
	static int m_ibo_index_handle;	
	static int m_ibo_size;
	
	public static void initialize()
	{
		m_shader = new Shader();
		try 
		{
			String workingDirectory = System.getProperty("user.dir");
			Debug.Log("Loading shader... [WD: " + workingDirectory + "]");
			m_shader.addVertexShader(new String(Files.readAllBytes(Paths.get(workingDirectory + "/res/simple.vs"))));
			m_shader.addFragmentShader(new String(Files.readAllBytes(Paths.get(workingDirectory + "/res/simple.fs"))));
		} 
		catch (IOException e) 
		{
			Debug.Error(e.getMessage());
		}
		m_shader.compileShader();
		
		Line3f lineA = new Line3f(new Vector3f(-1.0f, -1.0f, 0), new Vector3f( 1.0f, -1.0f, 0));
		Line3f lineB = new Line3f(new Vector3f( 1.0f, -1.0f, 0), new Vector3f( 0.0f,  1.0f, 0));
		Line3f lineC = new Line3f(new Vector3f( 0.0f,  1.0f, 0), new Vector3f(-1.0f, -1.0f, 0));
		
		m_triangle1 = new Triangle(lineA, lineB, lineC);
		
//		Vector3f v1 = new Vector3f(-1, -1, 0);
//		Vector3f v2 = new Vector3f(-1,  1, 0);
//		Vector3f v3 = new Vector3f( 1,  1, 0);
//		Vector3f v4 = new Vector3f( 1, -1, 0);
//
//		m_triangle1 = new Triangle(v1, v2, v3);
//		m_triangle2 = new Triangle(v3, v4, v1);
	}
	public static void render()
	{
		m_shader.bind();
		m_triangle1.render();
//		m_triangle2.render();
	}
	
}
