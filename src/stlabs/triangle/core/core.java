package stlabs.triangle.core;

import StoneLabs.sutil.Debug;
import stlabs.triangle.math.Line3f;
import stlabs.triangle.math.Vector3f;

import static org.lwjgl.opengl.GL11.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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
	private static List<Renderable> m_triangles = new ArrayList<Renderable>();
	
	private static Shader m_shader;
	
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
		
		Vector3f v1 = new Vector3f(-0.5f, -0.5f, 0);
		Vector3f v2 = new Vector3f(-0.5f,  0.5f, 0);
		Vector3f v3 = new Vector3f( 0.5f,  0.5f, 0);
		Vector3f v4 = new Vector3f( 0.5f, -0.5f, 0);

		m_triangles.add(new Triangle(v3, v2, v1)); //BACKFACE
		m_triangles.add(new Triangle(v3, v4, v1)); //FRONTFACE
	}
	public static void render()
	{
		m_shader.bind();
		
		for (Renderable r : m_triangles)
			r.render();
	}
	
}
