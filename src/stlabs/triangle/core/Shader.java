package stlabs.triangle.core;

import static org.lwjgl.opengl.GL20.*;

import StoneLabs.sutil.Debug;

public class Shader 
{
	private int program;
	
	public Shader()
	{
		program = glCreateProgram();
	}
	
	public void bind()
	{
		glUseProgram(program);
	}
	
	public void addVertexShader(String text)
	{
		addProgram(text, GL_VERTEX_SHADER);
	}
	
	public void addFragmentShader(String text)
	{
		addProgram(text, GL_FRAGMENT_SHADER);
	}
	
	@SuppressWarnings("deprecation")
	public void compileShader()
	{
		glLinkProgram(program);
		if (glGetProgram(program, GL_LINK_STATUS) == 0)
			Debug.Error(glGetProgramInfoLog(program, 1024));
	}
	
	@SuppressWarnings("deprecation")
	private void addProgram(String text, int type)
	{
		int shader = glCreateShader(type);
		
		if (shader == 0)
			Debug.Error("Shader Creation Failed: Could not find valid memory location when adding shader!");
		
		glShaderSource(shader, text);
		glCompileShader(shader);
		
		if (glGetShader(shader, GL_COMPILE_STATUS) == 0)
			Debug.Error(glGetShaderInfoLog(shader, 1024));
		
		glAttachShader(program, shader);
	}
}
